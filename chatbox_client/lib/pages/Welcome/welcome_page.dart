import 'package:chatbox_client/components/background.dart';
import 'package:chatbox_client/pages/Welcome/components/welcome_buttons.dart';
import 'package:chatbox_client/pages/Welcome/components/welcome_image.dart';
import 'package:flutter/material.dart';

class WelcomePage extends StatelessWidget {
  const WelcomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Background(
      child: SingleChildScrollView(
        child: SafeArea(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const WelcomeImage(),
              Row(
                children: const [
                  Spacer(),
                  Expanded(
                    flex: 5,
                    child: WelcomeButtons(),
                  ),
                  Spacer(),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
