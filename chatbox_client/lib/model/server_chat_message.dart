// To parse this JSON data, do
//
//     final serverChatMessage = serverChatMessageFromJson(jsonString);

import 'dart:convert';

ServerChatMessage serverChatMessageFromJson(String str) =>
    ServerChatMessage.fromJson(json.decode(str));

String serverChatMessageToJson(ServerChatMessage data) =>
    json.encode(data.toJson());

class ServerChatMessage {
  ServerChatMessage({
    required this.token,
    required this.message,
  });

  final String token;
  final String message;

  factory ServerChatMessage.fromJson(Map<String, dynamic> json) =>
      ServerChatMessage(
        token: json["token"],
        message: json["message"],
      );

  Map<String, dynamic> toJson() => {
        "token": token,
        "message": message,
      };
}
