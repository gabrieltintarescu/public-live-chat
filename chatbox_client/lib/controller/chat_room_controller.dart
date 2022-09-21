import 'dart:convert';

import 'package:chatbox_client/model/chat_message.dart';
import 'package:chatbox_client/model/server_chat_message.dart';
import 'package:chatbox_client/model/token_response.dart';
import 'package:chatbox_client/pages/Welcome/welcome_page.dart';
import 'package:chatbox_client/util/api_urls.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';
import 'package:socket_io_client/socket_io_client.dart';
import 'package:http/http.dart' as http;

class ChatRoomController extends GetxController {
  var chatMessages = <ChatMessage>[].obs;
  final box = GetStorage();
  late String accessToken;
  late String refreshToken;
  late String username;
  Socket socket = io(
      SOCKET_URL,
      OptionBuilder()
          .setTransports(['websocket']) // for Flutter or Dart VM
          .disableAutoConnect() // disable auto-connection
          .build());

  @override
  void onInit() {
    accessToken = box.read('access_token');
    refreshToken = box.read('refresh_token');
    username = box.read('username');

    registerConnectionEvents();
    registerServerEvents();
    socket.connect();
  }

  @override
  void onClose() {
    // TODO: implement onClose
    super.onClose();
    socket.disconnect();
  }

  void registerConnectionEvents() {
    socket.onConnect((data) {
      print('Connected!');
      notifyConnection();
    });
    socket.onDisconnect((data) => print('Disconnected'));
  }

  void notifyConnection() {
    socket.emit('user-connect', accessToken);
  }

  void registerServerEvents() {
    socket.on('server-message', (data) {
      print('Message from sever: $data');
      chatMessages.add(ChatMessage(
        senderType: SenderType.server,
        username: 'server',
        message: data,
        date: DateTime.now(),
      ));
    });
    socket.on(
      'client-disconnect',
      (_) => chatMessages.add(
        ChatMessage(
            senderType: SenderType.server,
            username: 'server',
            message: 'A user has disconnected.',
            date: DateTime.now()),
      ),
    );

    socket.on('banned', (bannedUser) {
      print('Banned:$bannedUser');
      if (bannedUser != username) {
        return;
      }
      disconnectUser();
      Get.defaultDialog(
        title: 'Permanent Ban',
        titlePadding: const EdgeInsets.all(25),
        contentPadding: const EdgeInsets.all(20),
        middleText:
            'Sorry, but you have ben permanently banned from this seerver.',
        confirm: TextButton(
          onPressed: () => Get.back(),
          child: const Text('OK'),
        ),
      );
    });

    socket.on('chat-message', (data) {
      var msg = jsonDecode(data);
      chatMessages.add(ChatMessage(
        senderType:
            msg['username'] == username ? SenderType.self : SenderType.user,
        username: msg['username'],
        message: msg['message'],
        date: DateTime.now(),
      ));
    });

    socket.on('invalid-token', (_) {
      Get.snackbar('Token Error',
          'Something went wrong with your authentication token. Please re-login.');
      disconnectUser();
    });
    socket.on('expired-token', (oldMessage) {
      refreshTokenRequest().then((tokenResponse) {
        if (tokenResponse == null) {
          Get.snackbar('Token Error',
              'Something went wrong with your authentication token. Please re-login.');
          disconnectUser();
          return;
        }
        print('refreshing token');
        accessToken = tokenResponse.accessToken;
        box.write('access_token', tokenResponse.accessToken);
        box.save();
        sendMessage(oldMessage);
      });
    });
  }

  void sendMessage(String message) {
    if (message.trimLeft().toLowerCase().startsWith('/ban')) {
      var userToBan = message.split(' ')[1];
      print(userToBan);
      if (userToBan.isNotEmpty) {
        var newMessage =
            ServerChatMessage(token: accessToken, message: userToBan);
        socket.emit('ban', newMessage);
        return;
      }
    }

    var newMessage = ServerChatMessage(token: accessToken, message: message);
    socket.emit('msg', newMessage);
  }

  void disconnectUser() {
    chatMessages.clear();
    box.erase();
    box.save();
    Get.offAll(() => const WelcomePage(), transition: Transition.cupertino);
  }

  Future<TokenResponse?> refreshTokenRequest() async {
    final response =
        await http.get(Uri.parse(API_TOKEN_REFRESH), headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
      'Authorization': 'Bearer $refreshToken'
    });
    if (response.statusCode == 200) {
      return TokenResponse.fromJson(jsonDecode(response.body));
    }
    return null;
  }

  void disconnectAskUser() {
    Get.defaultDialog(
      title: 'Disconnect',
      titlePadding: const EdgeInsets.all(25),
      contentPadding: const EdgeInsets.all(20),
      middleText: 'Are you sure you want to disconnect?',
      confirm: TextButton(
        onPressed: () => disconnectUser(),
        child: const Text('Yes'),
      ),
      cancel: TextButton(
        onPressed: () => Get.back(),
        child: const Text('No'),
      ),
    );
  }
}
