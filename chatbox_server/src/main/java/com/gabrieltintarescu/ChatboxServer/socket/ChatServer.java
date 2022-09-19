package com.gabrieltintarescu.ChatboxServer.socket;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.gabrieltintarescu.ChatboxServer.model.ChatMessage;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.security.util.JwtUtil;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/19/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatServer implements CommandLineRunner {


    private final UserService userService;
    private SocketIOServer server;

    @Override
    public void run(String... args) throws Exception {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8080);

        server = new SocketIOServer(config);

        server.addEventListener("msg", ChatMessage.class, new DataListener<ChatMessage>() {
            @Override
            public void onData(SocketIOClient client, ChatMessage data, AckRequest ackRequest) {
                try {
                    JWTVerifier verifier = JWT.require(JwtUtil.getAlgorithm()).build();
                    DecodedJWT decodedJWT = verifier.verify(data.getToken());
                    String isMuted = decodedJWT.getClaim("isMuted").asString();
                    String username = decodedJWT.getSubject();

                    // Check if user is muted.
                    if(isMuted != null && isMuted.equalsIgnoreCase("true")){
                        return;
                    }
                    // broadcast messages to all clients
                    log.info("Sending message");
                    server.getBroadcastOperations().sendEvent("chat-message", data.getMessage());
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage());
                    if(e instanceof JWTDecodeException){
                        client.sendEvent("invalid-token");
                    } else if(e instanceof TokenExpiredException){
                        client.sendEvent("expired-token");
                    }
                }
            }
        });

        server.addEventListener("connect", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String token, AckRequest ackRequest) throws Exception {
                try {
                    JWTVerifier verifier = JWT.require(JwtUtil.getAlgorithm()).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();

                    // Check if user is banned
                    User user = userService.getUser(username);
                    if(user.isBanned()){
                        client.sendEvent("client-banned");
                        return;
                    }


                    server.getBroadcastOperations().sendEvent("client-connect", username);
                } catch (Exception e) {
                }
            }
        });

        server.addEventListener("ban", ChatMessage.class, new DataListener<ChatMessage>() {
            @Override
            public void onData(SocketIOClient client, ChatMessage data, AckRequest ackRequest) throws Exception {
                try {
                    JWTVerifier verifier = JWT.require(JwtUtil.getAlgorithm()).build();
                    DecodedJWT decodedJWT = verifier.verify(data.getToken());
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    // Check if user has mute privilege
                    if(!Arrays.asList(roles).contains("ROLE_ADMIN")){
                        return;
                    }
                    // broadcast messages to all clients
                    userService.banUser(data.getMessage());
                    log.info("Banning user: " + data.getMessage());
                    client.sendEvent("banned");
                    server.getBroadcastOperations().sendEvent("server-message", username + " has banned " + data.getMessage() + " permanently.");
                } catch (Exception e) {
                    if(e instanceof JWTDecodeException){
                        client.sendEvent("invalid-token");
                    } else if(e instanceof TokenExpiredException){
                        client.sendEvent("expired-token");
                    }
                }
            }
        });

        server.addConnectListener((client -> {
            log.info("New connection: " + client.toString());
        }));

        server.start();

    }

    @PreDestroy
    public void onExit() {
        server.stop();
    }
}
