import 'package:chatbox_client/config/configurations.dart';
import 'package:flutter/material.dart';

class ChatTileServer extends StatelessWidget {
  final String message;
  const ChatTileServer({super.key, required this.message});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10),
        child: Container(
          decoration: BoxDecoration(
            color: kCardColor,
            borderRadius: BorderRadius.circular(12),
          ),
          child: Padding(
            padding: const EdgeInsets.symmetric(
              vertical: 4,
              horizontal: 12,
            ),
            child: Text(
              message,
              style: const TextStyle(
                fontSize: 12,
                fontWeight: FontWeight.bold,
                color: kTextFaded,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
