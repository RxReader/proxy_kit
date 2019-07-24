import 'package:flutter/material.dart';
import 'package:fake_proxy/fake_proxy.dart';

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
          title: const Text('Fake Proxy'),
        ),
        body: ListView(
          children: <Widget>[
            ListTile(
              title: const Text('Proxy'),
              onTap: () async {
                String proxy = await Proxy.getProxy();
                print('proxy: ${proxy ?? ''}');
              },
            ),
          ],
        ),
      ),
    );
  }
}
