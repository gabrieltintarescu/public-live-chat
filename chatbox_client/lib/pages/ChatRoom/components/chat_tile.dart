import 'package:chatbox_client/config/configurations.dart';
import 'package:flutter/material.dart';

class ChatTile extends StatelessWidget {
  final String message, username;
  final bool isOwn;
  const ChatTile({
    super.key,
    required this.message,
    required this.isOwn,
    required this.username,
  });
  static const _borderRadius = 26.0;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(
        horizontal: 8,
        vertical: 4,
      ),
      child: Align(
        alignment: isOwn ? Alignment.centerRight : Alignment.centerLeft,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment:
              isOwn ? CrossAxisAlignment.end : CrossAxisAlignment.start,
          children: [
            Container(
              decoration: BoxDecoration(
                color: !isOwn ? kPrimaryLightColor : kPrimaryColor,
                borderRadius: BorderRadius.only(
                  topLeft: const Radius.circular(_borderRadius),
                  topRight: Radius.circular(isOwn ? 0 : _borderRadius),
                  bottomLeft: Radius.circular(isOwn ? _borderRadius : 0),
                  bottomRight: const Radius.circular(_borderRadius),
                ),
              ),
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 12,
                  vertical: 20,
                ),
                child: Text(
                  message,
                  style: TextStyle(
                    fontSize: 14,
                    color: isOwn ? Colors.white : Colors.black,
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 8),
              child: Text(
                isOwn ? '22:12' : '22:12 - $username',
                style: const TextStyle(
                  fontSize: 11,
                  fontWeight: FontWeight.w300,
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
