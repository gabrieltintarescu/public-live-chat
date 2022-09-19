import 'package:get/get.dart';

class LoginController extends GetxController {
  var isPasswordVisible = false.obs;

  void changePasswordVisibility() {
    print('Channging visibility!!');
    isPasswordVisible(!isPasswordVisible.value);
  }
}
