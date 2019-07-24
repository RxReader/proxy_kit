import 'dart:async';

import 'package:flutter/services.dart';

class Proxy {
  static const MethodChannel _channel =
      MethodChannel('v7lin.github.io/fake_proxy');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
