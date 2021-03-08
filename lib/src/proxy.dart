import 'dart:async';

import 'package:flutter/services.dart';

class Proxy {
  static const MethodChannel _channel =
      MethodChannel('v7lin.github.io/proxy_kit');

  static Future<String?> getProxy() {
    return _channel.invokeMethod<String>('getProxy');
  }
}
