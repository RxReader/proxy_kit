import 'dart:async';

import 'package:flutter/services.dart';

class Proxy {
  static const MethodChannel _channel =
      MethodChannel('v7lin.github.io/fake_proxy');

  static const String _METHOD_GETPROXY = 'getProxy';

  static Future<String> getProxy() async {
    return _channel.invokeMethod(_METHOD_GETPROXY);
  }
}
