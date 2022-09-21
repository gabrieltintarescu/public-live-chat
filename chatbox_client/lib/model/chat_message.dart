class ChatMessage {
  SenderType senderType;
  String username;
  String? message;
  DateTime date;

  ChatMessage({
    required this.senderType,
    required this.username,
    this.message,
    required this.date,
  });
}

enum SenderType { self, user, server }
