import 'dart:convert';

import 'package:chatbox_client/model/chat_message.dart';
import 'package:chatbox_client/model/server_chat_message.dart';
import 'package:chatbox_client/util/api_urls.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';
import 'package:socket_io_client/socket_io_client.dart';

class ChatRoomController extends GetxController {
  var chatMessages = <ChatMessage>[].obs;
  final box = GetStorage();
  late final String access_token;
  late final String refresh_token;
  late final String username;
  Socket socket = io(
      SOCKET_URL,
      OptionBuilder()
          .setTransports(['websocket']) // for Flutter or Dart VM
          .disableAutoConnect() // disable auto-connection
          .build());

  var scrollController = ScrollController();
  @override
  void onInit() {
    access_token = box.read('access_token');
    refresh_token = box.read('refresh_token');
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
    socket.emit('user-connect', access_token);
  }

  void registerServerEvents() {
    socket.on(
      'client-connect',
      (user) => chatMessages.add(
        ChatMessage(
            senderType: SenderType.server,
            username: 'server',
            message: '$user has connected.',
            date: DateTime.now()),
      ),
    );
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

    socket.on('chat-message', (data) {
      var msg = jsonDecode(data);
      print(msg);

      chatMessages.add(ChatMessage(
        senderType:
            msg['username'] == username ? SenderType.self : SenderType.user,
        username: msg['username'],
        message: msg['message'],
        date: DateTime.now(),
      ));
      scrollController.jumpTo(scrollController.position.maxScrollExtent);
    });
  }

  void sendMessage(String message) {
    var newMessage = ServerChatMessage(token: access_token, message: message);
    socket.emit('msg', newMessage);
  }
}
