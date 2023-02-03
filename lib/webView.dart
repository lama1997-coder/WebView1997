import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void WebViewCreatedCallback(WebViewController controller);

class WebView extends StatefulWidget {
  const WebView({
    this.onWebViewCreated,
  });

  final WebViewCreatedCallback? onWebViewCreated;

  @override
  State<StatefulWidget> createState() => WebViewState();
}

class WebViewState extends State<WebView> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
          viewType: 'webview',
          onPlatformViewCreated: _onPlatformViewCreated,
        );
    }
    // TODO add other platforms
    return Text(
        '$defaultTargetPlatform is not yet supported by the map view plugin');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onWebViewCreated == null) {
      return;
    }
    widget.onWebViewCreated!(WebViewController(id, context));
  }
}

class WebViewController {
  WebViewController(int id, BuildContext context) {
    _channel = new MethodChannel('webview');
    context = context;
  }

  MethodChannel? _channel;
  BuildContext? context;

  Future<void> loadUrl(String url) async {
    return _channel!.invokeMethod(
        'loadUrl', <String, dynamic>{"url": url, "context": context});
  }
}
