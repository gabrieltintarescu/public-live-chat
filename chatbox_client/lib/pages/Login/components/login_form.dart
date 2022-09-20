import 'package:chatbox_client/components/already_have_account_check.dart';
import 'package:chatbox_client/components/rounded_button.dart';
import 'package:chatbox_client/components/text_field_container.dart';
import 'package:chatbox_client/config/configurations.dart';
import 'package:chatbox_client/controller/login_controller.dart';
import 'package:chatbox_client/pages/Signup/signup_page.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class LoginForm extends StatelessWidget {
  LoginForm({
    Key? key,
  }) : super(key: key);

  TextEditingController usernameTextController = TextEditingController(),
      passwordTextController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    var loginController = Get.put(LoginController());

    return Column(
      children: [
        TextFieldContainer(
          child: TextField(
            controller: usernameTextController,
            decoration: const InputDecoration(
              icon: Icon(Icons.person, color: kPrimaryColor),
              hintText: "Username or email",
              border: InputBorder.none,
            ),
          ),
        ),
        TextFieldContainer(
          child: TextField(
            controller: passwordTextController,
            obscureText: true,
            decoration: const InputDecoration(
              icon: Icon(Icons.lock, color: kPrimaryColor),
              hintText: "Password",
              border: InputBorder.none,
            ),
          ),
        ),
        const SizedBox(height: defaultPadding * 2),
        RoundedButton(
            color: kPrimaryColor,
            onPress: () => loginController.login(
                usernameTextController.text, passwordTextController.text),
            text: 'Log in',
            textColor: Colors.white),
        const SizedBox(height: defaultPadding * 2),
        AlreadyHaveAnAccountCheck(
          press: () => Get.to(
            () => SignupPage(),
            transition: Transition.rightToLeft,
          ),
        ),
      ],
    );
  }
}
