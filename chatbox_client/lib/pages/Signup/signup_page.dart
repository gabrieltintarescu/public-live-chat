import 'package:chatbox_client/components/already_have_account_check.dart';
import 'package:chatbox_client/components/background.dart';
import 'package:chatbox_client/components/rounded_button.dart';
import 'package:chatbox_client/components/text_field_container.dart';
import 'package:chatbox_client/config/configurations.dart';
import 'package:chatbox_client/controller/signup_controller.dart';
import 'package:chatbox_client/pages/Login/login_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:get/get.dart';

class SignupPage extends StatelessWidget {
  SignupPage({super.key});

  final TextEditingController nameTextController = TextEditingController(),
      usernameTextController = TextEditingController(),
      mailTextController = TextEditingController(),
      passwordTextController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    var signupController = Get.put(SignupController());
    return Scaffold(
      body: Background(
        bottomImage: "assets/images/main_bottom.png",
        isSignup: true,
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                "Sign up to ChatBox",
                style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SvgPicture.asset(
                "assets/icons/signup.svg",
                width: context.height * 0.2,
              ),
              const SizedBox(height: 15),
              TextFieldContainer(
                child: TextField(
                  controller: nameTextController,
                  decoration: const InputDecoration(
                    icon: Icon(Icons.person, color: kPrimaryColor),
                    hintText: "Name",
                    border: InputBorder.none,
                  ),
                ),
              ),
              TextFieldContainer(
                child: TextField(
                  controller: usernameTextController,
                  decoration: const InputDecoration(
                    icon: Icon(Icons.person, color: kPrimaryColor),
                    hintText: "Username",
                    border: InputBorder.none,
                  ),
                ),
              ),
              TextFieldContainer(
                child: TextField(
                  controller: mailTextController,
                  decoration: const InputDecoration(
                    icon: Icon(Icons.mail, color: kPrimaryColor),
                    hintText: "Email",
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
                  onPress: () => signupController.signUp(
                        name: nameTextController.text,
                        email: mailTextController.text,
                        username: usernameTextController.text,
                        password: passwordTextController.text,
                      ),
                  text: 'Sign up',
                  textColor: Colors.white),
              const SizedBox(height: defaultPadding * 2),
              AlreadyHaveAnAccountCheck(
                login: false,
                press: () => Get.to(
                  () => const LoginPage(),
                  transition: Transition.rightToLeft,
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
