import 'package:chatbox_client/config/configurations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class WelcomeImage extends StatelessWidget {
  const WelcomeImage({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        RichText(
          text: const TextSpan(
            // Note: Styles for TextSpans must be explicitly defined.
            // Child text spans will inherit styles from parent
            style: TextStyle(
                fontSize: 52.0, color: Colors.black, fontFamily: 'Nexa'),
            children: <TextSpan>[
              TextSpan(text: 'Chat'),
              TextSpan(
                text: 'Box',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
            ],
          ),
        ),
        const Text(
          "easy messaging",
          style: TextStyle(fontWeight: FontWeight.normal, fontSize: 18),
        ),
        const SizedBox(height: defaultPadding * 2),
        Row(
          children: [
            const Spacer(flex: 1),
            Expanded(
              flex: 4,
              child: SizedBox(
                height: 350,
                child: SvgPicture.asset(
                  "assets/icons/welcome_screen.svg",
                ),
              ),
            ),
            const Spacer(flex: 1),
          ],
        ),
        const SizedBox(height: defaultPadding),
      ],
    );
  }
}
