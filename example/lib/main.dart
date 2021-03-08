import 'package:flutter/material.dart';
import 'package:proxy_kit/proxy_kit.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Proxy Kit'),
        ),
        body: ListView(
          children: <Widget>[
            ListTile(
              title: const Text('Proxy'),
              onTap: () async {
                final String? proxy = await Proxy.getProxy();
                print('proxy: ${proxy ?? ''}');
              },
            ),
          ],
        ),
      ),
    );
  }
}
