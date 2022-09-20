import 'package:flutter/material.dart';

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
          style: const TextStyle(fontSize: 16),
        ),
      ),
    );
  }
}
