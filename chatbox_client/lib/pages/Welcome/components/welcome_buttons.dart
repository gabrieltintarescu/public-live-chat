import 'package:chatbox_client/config/configurations.dart';
import 'package:chatbox_client/pages/Login/login_page.dart';
import 'package:chatbox_client/pages/Signup/signup_page.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class WelcomeButtons extends StatelessWidget {
  const WelcomeButtons({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Hero(
          tag: "login_btn",
          child: RoundedButton(
              text: 'Login',
              onPress: () => Get.to(
                    () => const LoginPage(),
                    transition: Transition.rightToLeft,
                  ),
              color: kPrimaryColor,
              textColor: Colors.white),
        ),
        const SizedBox(height: 16),
        FittedBox(
          child: RoundedButton(
              text: 'Sign up',
              onPress: () => Get.to(
                    () => const SignupPage(),
                    transition: Transition.leftToRight,
                  ),
              color: kPrimaryLightColor,
              textColor: Colors.black),
        ),
      ],
    );
  }
}

class RoundedButton extends StatelessWidget {
  final String text;
  final VoidCallback onPress;
  final Color color, textColor;

  const RoundedButton(
      {Key? key,
      required this.text,
      required this.onPress,
      required this.color,
      required this.textColor})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FittedBox(
      child: TextButton(
        style: ButtonStyle(
          padding: MaterialStateProperty.all(
              const EdgeInsets.symmetric(vertical: 20, horizontal: 60)),
          backgroundColor: MaterialStateProperty.all(color),
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(18.0),
              //side: const BorderSide(color: Colors.deepPurple),
            ),
          ),
          foregroundColor: MaterialStateProperty.all(textColor),
        ),
        onPressed: onPress,
        child: Text(
          text.toUpperCase(),
        ),
      ),
    );
  }
}
