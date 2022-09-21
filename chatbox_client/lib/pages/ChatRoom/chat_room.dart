import 'package:chatbox_client/config/configurations.dart';
import 'package:chatbox_client/controller/chat_room_controller.dart';
import 'package:chatbox_client/model/chat_message.dart';
import 'package:chatbox_client/pages/ChatRoom/components/chat_tile.dart';
import 'package:chatbox_client/pages/ChatRoom/components/chat_tile_server.dart';
import 'package:chatbox_client/pages/ChatRoom/components/glow_button.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:get/instance_manager.dart';

class ChatRoom extends StatelessWidget {
  const ChatRoom({super.key});

  @override
  Widget build(BuildContext context) {
    var chatController = Get.put(ChatRoomController());

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: kPrimaryColor,
        elevation: 6,
        leadingWidth: 54,
        leading: Align(
          alignment: Alignment.centerRight,
          child: IconButton(
            icon: const Icon(Icons.question_mark),
            onPressed: () {},
          ),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.exit_to_app),
            onPressed: () {},
          ),
        ],
        title: const Text(
          'Public Chat',
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 10),
        child: Column(
          children: [
            Expanded(
              flex: 10,
              child: Obx(
                () => ListView.builder(
                  controller: chatController.scrollController,
                  itemCount: chatController.chatMessages.length,
                  itemBuilder: ((context, index) {
                    if (chatController.chatMessages[index].senderType ==
                        SenderType.server) {
                      return ChatTileServer(
                        message:
                            chatController.chatMessages[index].message ?? "",
                      );
                    }

                    return ChatTile(
                      username: chatController.chatMessages[index].username,
                      message: chatController.chatMessages[index].message ?? "",
                      isOwn: chatController.chatMessages[index].senderType ==
                          SenderType.self,
                      time: chatController.chatMessages[index].date,
                    );
                  }),
                ),
              ),
            ),
            Expanded(
              flex: 2,
              child: ChatActionBar(
                chatRoomController: chatController,
              ),
            )
          ],
        ),
      ),
    );
  }
}

class ChatActionBar extends StatelessWidget {
  final ChatRoomController chatRoomController;
  ChatActionBar({
    super.key,
    required this.chatRoomController,
  });

  final messageTextController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Container(
          decoration: BoxDecoration(
            border: Border(
              right: BorderSide(
                width: 2,
                color: Theme.of(context).dividerColor,
              ),
            ),
          ),
          child: const Padding(
            padding: EdgeInsets.symmetric(horizontal: 16.0),
            child: Icon(
              CupertinoIcons.camera_fill,
            ),
          ),
        ),
        Expanded(
          child: Padding(
            padding: const EdgeInsets.only(left: 16.0),
            child: TextField(
                controller: messageTextController,
                style: const TextStyle(fontSize: 14),
                decoration: const InputDecoration(
                  hintText: 'Type something...',
                  border: InputBorder.none,
                ),
                onSubmitted: (_) => ((value) {})),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            left: 12,
            right: 24.0,
          ),
          child: GlowButton(
            color: kPrimaryColor,
            icon: Icons.send_rounded,
            onPressed: () {
              chatRoomController.sendMessage(messageTextController.text);
              messageTextController.clear();
            },
          ),
        ),
      ],
    );
  }
}
