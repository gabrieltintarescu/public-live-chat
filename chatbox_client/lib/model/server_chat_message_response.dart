// To parse this JSON data, do
//
//     final serverChatMessageResponse = serverChatMessageResponseFromJson(jsonString);

import 'dart:convert';

ServerChatMessageResponse serverChatMessageResponseFromJson(String str) =>
    ServerChatMessageResponse.fromJson(json.decode(str));

String serverChatMessageResponseToJson(ServerChatMessageResponse data) =>
    json.encode(data.toJson());

class ServerChatMessageResponse {
  ServerChatMessageResponse({
    required this.username,
    required this.message,
  });

  final String username;
  final String message;

  factory ServerChatMessageResponse.fromJson(Map<String, dynamic> json) =>
      ServerChatMessageResponse(
        username: json["username"],
        message: json["message"],
      );

  Map<String, dynamic> toJson() => {
        "username": username,
        "message": message,
      };
}
