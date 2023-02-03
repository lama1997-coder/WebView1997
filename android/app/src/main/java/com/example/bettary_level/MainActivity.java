package com.example.bettary_level;

import android.os.Bundle;
// import io.flutter.app.FlutterActivity;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        WebViewPlugin.registerWith(this.registrarFor("webview"));
    }
}
