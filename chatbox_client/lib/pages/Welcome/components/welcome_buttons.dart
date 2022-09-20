import 'package:chatbox_client/components/rounded_button.dart';
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
        RoundedButton(
            text: 'Login',
            onPress: () => Get.to(
                  () => const LoginPage(),
                  transition: Transition.rightToLeft,
                ),
            color: kPrimaryColor,
            textColor: Colors.white),
        const SizedBox(height: 16),
        FittedBox(
          child: RoundedButton(
              text: 'Sign up',
              onPress: () => Get.to(
                    () => SignupPage(),
                    transition: Transition.rightToLeft,
                  ),
              color: kPrimaryLightColor,
              textColor: Colors.black),
        ),
      ],
    );
  }
}
