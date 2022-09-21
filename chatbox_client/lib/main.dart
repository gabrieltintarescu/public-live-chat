import 'package:chatbox_client/pages/ChatRoom/chat_room.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';

import 'pages/Welcome/welcome_page.dart';

void main() {
  final box = GetStorage();
  final accessToken = box.read('access_token');
  final refreshToken = box.read('refresh_token');
  final username = box.read('username');
  Widget page = const ChatRoom();
  print('$accessToken - $refreshToken - $username');
  if (accessToken == null || refreshToken == null || username == null) {
    page = const WelcomePage();
  }

  runApp(GetMaterialApp(
    title: 'ChatBox',
    debugShowCheckedModeBanner: false,
    theme: ThemeData(fontFamily: 'Nexa'),
    home: page,
  ));
}
