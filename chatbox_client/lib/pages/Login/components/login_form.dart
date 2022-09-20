import 'package:chatbox_client/components/already_have_account_check.dart';
import 'package:chatbox_client/components/rounded_button.dart';
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
        Hero(
          tag: "login_btn",
          child: RoundedButton(
              color: kPrimaryColor,
              onPress: () => loginController.login(
                  usernameTextController.text, passwordTextController.text),
              text: 'Login',
              textColor: Colors.white),
        ),
        const SizedBox(height: defaultPadding * 2),
        AlreadyHaveAnAccountCheck(
          press: () => Get.to(
            () => const SignupPage(),
            transition: Transition.rightToLeft,
          ),
        ),
      ],
    );
  }
}

class TextFieldContainer extends StatelessWidget {
  final Widget child;
  const TextFieldContainer({Key? key, required this.child}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 5),
      margin: const EdgeInsets.symmetric(vertical: 10),
      width: size.width * 0.8,
      decoration: BoxDecoration(
        color: kPrimaryLightColor,
        borderRadius: BorderRadius.circular(29),
      ),
      child: child,
    );
  }
}
