class ChatMessage {
  bool isOwn;
  String username;
  String message;
  String date;

  ChatMessage({
    required this.isOwn,
    required this.username,
    required this.message,
    required this.date,
  });
}
