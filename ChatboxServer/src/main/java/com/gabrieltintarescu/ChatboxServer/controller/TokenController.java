package com.gabrieltintarescu.ChatboxServer.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrieltintarescu.ChatboxServer.exception.ErrorDetails;
import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.security.util.JwtUtil;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token/")
public class TokenController {

    private final UserService userService;

    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtUtil.STRING_BEARER)) {
            try {
                String refreshToken = authorizationHeader.substring(JwtUtil.BearerLength());
                JWTVerifier verifier = JWT.require(JwtUtil.getAlgorithm()).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.ACCESS_TOKEN_EXP * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(JwtUtil.getAlgorithm());

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                int code = 0;
                String message = "Internal server error";
                String details = e.getLocalizedMessage();
                if (e instanceof TokenExpiredException) {
                    code = 2;
                    message = "Refresh token expired";
                    details = "Please authenticate again.";
                }
                response.setStatus(FORBIDDEN.value());
                ErrorDetails errorDetails = ErrorDetails.builder()
                        .timestamp(new Date())
                        .code(code)
                        .message(message)
                        .details(details)
                        .path(request.getServletPath())
                        .build();
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errorDetails);
            }

        } else {
            throw new RuntimeException("Refresh Token is missing!");
        }
    }

}
