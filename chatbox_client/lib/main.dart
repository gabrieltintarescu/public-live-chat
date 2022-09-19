import 'package:chatbox_client/pages/Welcome/welcome_page.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

void main() {
  runApp(GetMaterialApp(
    title: 'ChatBox',
    debugShowCheckedModeBanner: true,
    theme: ThemeData(fontFamily: 'Nexa'),
    home: const WelcomePage(),
  ));
}
