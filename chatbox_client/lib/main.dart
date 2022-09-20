import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'pages/Welcome/welcome_page.dart';

void main() {
  runApp(GetMaterialApp(
    title: 'ChatBox',
    debugShowCheckedModeBanner: false,
    theme: ThemeData(fontFamily: 'Nexa'),
    home: const WelcomePage(),
  ));
}
