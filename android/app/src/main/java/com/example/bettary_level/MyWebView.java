package com.example.bettary_level;

import static android.app.Activity.RESULT_OK;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.view.View;
import android.os.Build.VERSION;
import android.webkit.*;
import android.view.View;

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

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;

public class MyWebView implements PlatformView, MethodCallHandler {
    private static WebView webView;
    // private static String CHANNEL = "samples.flutter.dev/battery";
    private static MethodChannel CHANNEL;
    private static ValueCallback<Uri[]> afterLollipop;
    private static ValueCallback<Uri> mUploadMessage;
    private Context CONTEXT;
    private MainActivity ACTIVITY;

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

    MyWebView(Context context, BinaryMessenger messenger, int id, Context mainCon) {
        CONTEXT = mainCon;
        ACTIVITY = (MainActivity) CONTEXT;

        // textView = new TextView(context);

        webView = new WebView(context);
        CHANNEL = new MethodChannel(messenger, "webview");
        CHANNEL.setMethodCallHandler(this);
    }

    public MyWebView() {
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        // super.configureFlutterEngine(flutterEngine);
        if (call.method.equals("lunchUrl")) {

            //

            // webView = WebView(this);

            loadUrlFunction(call, result);
        } else {
            result.notImplemented();
        }

    }

    static void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("the webView ", String.valueOf(requestCode));

        switch (requestCode) {
            case 101:
                if (resultCode == RESULT_OK) {

                    Uri result = intent == null || resultCode != RESULT_OK ? null
                            : intent.getData();
                    if (mUploadMessage != null) {
                        mUploadMessage.onReceiveValue(result);
                    } else if (afterLollipop != null) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            afterLollipop
                                    .onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                            afterLollipop = null;
                        }
                    }
                    mUploadMessage = null;
                }
        }

    }
    // private int getBatteryLevel() {
    // int batteryLevel = -1;
    // if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
    // BatteryManager batteryManager = (BatteryManager)
    // getSystemService(BATTERY_SERVICE);
    // batteryLevel =
    // batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    // } else {
    // Intent intent = new
    // ContextWrapper(getApplicationContext()).registerReceiver(null,
    // new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    // batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
    // intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    // }

    // return batteryLevel;
    // }

    private void loadUrlFunction(MethodCall method, MethodChannel.Result result) {

        String url = method.argument("url").toString();
        Log.d("the webView ", url);

        webView.loadUrl(url);
        ////////////////////////

        webView.setWebChromeClient(new WebChromeClient() {

            // Log.i("DEBUG 1","");

            // For Android 3.0+ - undocumented method
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.i("DEBUG 1", acceptType);
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                // startActivityForResult(Intent.createChooser(i, "File Chooser"), 101);
                Log.i("DEBUG", "Open file Chooser");
                mUploadMessage = uploadMsg;
            }

            // For Android > 4.1 - undocumented method
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("DEBUG 2", capture);

                mUploadMessage = uploadMsg;
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // startActivityForResult(pickPhoto, 101);

            }

            // For Android > 5.0
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                Log.i("DEBUG 3", fileChooserParams.toString());
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;

                afterLollipop = filePathCallback;
                // if (true) {
                // Log.i("DEBUG 3 true", fileChooserParams.toString());

                ACTIVITY.startActivityForResult(fileChooserParams.createIntent(), 101);
                // } else {
                // Log.e("mContext should be an instanceof Activity.");
                // }
                return true;

            }

        });

        ///////////////////////
        // webView.setWebViewClient(new HostsWebClient());
        if (VERSION.SDK_INT >= VERSION_CODES.FROYO) {
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }

        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        // webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        // webView.setWebViewClient(new MyWebClient());

        result.success(null);
    }

}
