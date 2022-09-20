import 'dart:convert';

import 'package:chatbox_client/pages/Login/login_page.dart';
import 'package:chatbox_client/util/api_urls.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

class SignupController extends GetxController {
  void signUp({
    required String name,
    required String email,
    required String username,
    required String password,
  }) {
    if (username.isEmpty || password.isEmpty || name.isEmpty || email.isEmpty) {
      Get.snackbar(
        'Erorr',
        'Please complete all fields!',
        colorText: Colors.black,
        snackPosition: SnackPosition.TOP,
        barBlur: 100,
      );
      return;
    }
    if (!RegExp(r'^[\w]+@[a-zA-Z]+[.][a-zA-Z]+$').hasMatch(email)) {
      print('Invalid mail!');
      return;
    }
    registerRequest(
      name: name,
      email: email,
      username: username,
      password: password,
    ).then((success) {
      if (!success) {
        Get.snackbar(
          'Erorr',
          'Something went wrong.',
          colorText: Colors.black,
          snackPosition: SnackPosition.TOP,
          barBlur: 100,
        );
        return;
      }
      Get.snackbar(
        'Sucess',
        'Account created successfully.',
        colorText: Colors.black,
        snackPosition: SnackPosition.TOP,
        barBlur: 100,
      );
      Get.off(() => const LoginPage(), transition: Transition.circularReveal);
    });
  }

  Future<bool> registerRequest({
    required String name,
    required String email,
    required String username,
    required String password,
  }) async {
    final response = await http.post(
      Uri.parse(API_REGISTER),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(
        <String, String>{
          'name': name,
          'email': email,
          'username': username,
          'password': password,
        },
      ),
    );
    if (response.statusCode == 200) {
      return true;
    }
    return false;
  }
}
