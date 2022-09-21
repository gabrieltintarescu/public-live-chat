import 'dart:convert';

import 'package:chatbox_client/model/token_response.dart';
import 'package:chatbox_client/pages/ChatRoom/chat_room.dart';
import 'package:chatbox_client/util/api_urls.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';
import 'package:http/http.dart' as http;

class LoginController extends GetxController {
  void login(String username, String password) {
    if (username.isEmpty || password.isEmpty) {
      return;
    }
    loginRequest(username, password).then((tokenResponse) {
      if (tokenResponse == null) {
        Get.snackbar(
          'Erorr',
          'Incorrect username or password!',
          colorText: Colors.black,
          snackPosition: SnackPosition.TOP,
          barBlur: 100,
        );
        return;
      }
      GetStorage box = GetStorage();
      print('Saving $tokenResponse.accessToken');
      box.write('access_token', tokenResponse.accessToken);
      box.write('refresh_token', tokenResponse.refreshToken);
      box.write('username', username);
      box.save();
      Get.offAll(() => const ChatRoom(), transition: Transition.circularReveal);
    });
  }

  Future<TokenResponse?> loginRequest(String username, String password) async {
    final response = await http.post(
      Uri.parse(API_LOGIN),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(
          <String, String>{'username': username, 'password': password}),
    );
    if (response.statusCode == 200) {
      return TokenResponse.fromJson(jsonDecode(response.body));
    }
    return null;
  }
}
