package com.example.bettary_level;

import io.flutter.plugin.common.PluginRegistry.Registrar;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;

class WebViewPlugin implements FlutterPlugin {

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        binding
                .getPlatformViewRegistry()
                .registerViewFactory("webview", new WebViewFactory(binding.getFlutterEngine().getDartExecutor(),binding.getApplicationContext()));
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}