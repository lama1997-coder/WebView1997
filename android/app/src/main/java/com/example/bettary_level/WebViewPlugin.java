package com.example.bettary_level;

import io.flutter.plugin.common.PluginRegistry.Registrar;

class WebViewPlugin {

    public static void registerWith(Registrar registrar) {
        registrar
                .platformViewRegistry()
                .registerViewFactory(
                        "webview", new WebViewFactory(registrar.messenger()));
    }
}