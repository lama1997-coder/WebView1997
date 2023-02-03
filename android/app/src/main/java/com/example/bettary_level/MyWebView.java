package com.example.bettary_level;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import android.content.ContextWrapper;
import android.content.Context;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.view.View;
import android.os.Build.VERSION;
import android.webkit.*;
import android.view.View;
import android.webkit.WebViewClient;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.flutter.plugin.common.MethodCall;
import android.os.Build.VERSION_CODES;
import io.flutter.plugin.platform.PlatformView;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformView;
import android.webkit.*;
import android.net.http.SslError;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.graphics.Bitmap;
import android.webkit.WebViewClient;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView implements PlatformView, MethodCallHandler {
    private static WebView webView;
    // private static String CHANNEL = "samples.flutter.dev/battery";
    private static MethodChannel CHANNEL;
    // private final Context context;

    // @Override
    // public PlatformView create() {
    // return webView;
    // }
    // MainActivity(Context context) {
    // webView = WebView(context);

    // @Override
    // onCreate(Bundle savedInstanceState) {
    // super.onCreate(savedInstanceState);
    // GeneratedPluginRegistrant.registerWith(this);
    // WebViewPlugin.registerWith(this.registrarFor("webview"));
    // }

    @Override
    public void dispose() {
    }

    @Override
    public View getView() {
        return webView;
    }

    MyWebView(Context context, BinaryMessenger messenger, int id) {
        // this.context=context;
        // textView = new TextView(context);

        webView = new WebView(context);
        CHANNEL = new MethodChannel(messenger, "webview");
        CHANNEL.setMethodCallHandler(this);
    }

    public MyWebView() {
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
//        super.configureFlutterEngine(flutterEngine);
        if (call.method.equals("lunchUrl")) {

            //

            // webView = WebView(this);

            loadUrlFunction(call, result);
        } else {
            result.notImplemented();
        }

    }

    // private int getBatteryLevel() {
    //     int batteryLevel = -1;
    //     if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
    //         BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
    //         batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    //     } else {
    //         Intent intent = new ContextWrapper(getApplicationContext()).registerReceiver(null,
    //                 new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    //         batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
    //                 intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    //     }

    //     return batteryLevel;
    // }

    private void loadUrlFunction(MethodCall method, MethodChannel.Result result) {

        String url = method.argument("url").toString();
        webView.loadUrl(url);
        result.success(null);
    }
}
