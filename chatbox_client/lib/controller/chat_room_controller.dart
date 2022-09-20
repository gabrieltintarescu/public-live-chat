import 'package:chatbox_client/model/chat_message.dart';
import 'package:chatbox_client/util/api_urls.dart';
import 'package:get/get.dart';
import 'package:socket_io_client/socket_io_client.dart';

class ChatRoomController extends GetxController {
  var chatMessages = <ChatMessage>[].obs;
  late Socket socket;
  @override
  void onInit() {
    Socket socket = io(
        SOCKET_URL,
        OptionBuilder()
            .setTransports(['websocket']) // for Flutter or Dart VM
            .disableAutoConnect() // disable auto-connection
            .setExtraHeaders({'username': 'john'}) // optional
            .build());
    socket.onConnectError((data) => print(data));

    socket.connect();
  }

  @override
  void onClose() {
    // TODO: implement onClose
    super.onClose();
    socket.disconnect();
  }
}
