package com.example.bettary_level;
import android.content.Context;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class WebViewFactory extends PlatformViewFactory {
    private final BinaryMessenger messenger;
    private final Context mainCon;

    public WebViewFactory(BinaryMessenger messenger ,Context mainCon) {
        super(StandardMessageCodec.INSTANCE);
        this.messenger = messenger;
        this.mainCon=mainCon;
    }

    @Override
    public PlatformView create(Context context, int id, Object o) {
        return new MyWebView(context, messenger, id,mainCon);
    }



    
}