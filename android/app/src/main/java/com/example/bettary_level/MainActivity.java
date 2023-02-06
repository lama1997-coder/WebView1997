package com.example.bettary_level;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
// import io.flutter.app.FlutterActivity;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.embedding.engine.FlutterEngine;
import androidx.annotation.NonNull;


public class MainActivity extends FlutterActivity {
    private ValueCallback<Uri[]> afterLollipop;
    private ValueCallback<Uri> mUploadMessage;
    // public void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     GeneratedPluginRegistrant.registerWith(this);
    //     WebViewPlugin.registerWith(this.registrarFor("webview"));
    // }
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
       // super.configureFlutterEngine(flutterEngine);

        flutterEngine
            .getPlatformViewsController()
            .getRegistry()
            .registerViewFactory("webview", new WebViewFactory(flutterEngine.getDartExecutor(),this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    
        MyWebView.onActivityResult(requestCode, resultCode, intent);
        
        // switch (requestCode) {
        //     case 101:
        //         if (resultCode == RESULT_OK) {

        //             Uri result = intent == null || resultCode != RESULT_OK ? null
        //                     : intent.getData();
        //             if (mUploadMessage != null) {
        //                 mUploadMessage.onReceiveValue(result);
        //             } else if (afterLollipop != null) {

        //                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //                     afterLollipop
        //                             .onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
        //                     afterLollipop = null;
        //                 }
        //             }
        //             mUploadMessage = null;
        //         }
        // }

    }
}
