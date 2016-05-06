package com.startapp.android.publish;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.p022h.StartAppSDK;

/* compiled from: StartAppSDK */
public class VideoJsInterface extends JsInterface {
    private static final String TAG = "VideoJsInterface";
    private Runnable replayCallback;
    private Runnable skipCallback;
    private Runnable toggleSoundCallback;

    public VideoJsInterface(Context context, Runnable closeCallback, Runnable replayCallback, Runnable skipCallback, Runnable toggleSoundCallback, StartAppSDK params) {
        this(context, closeCallback, null, replayCallback, skipCallback, toggleSoundCallback, params);
    }

    public VideoJsInterface(Context context, Runnable closeCallback, Runnable clickCallback, Runnable replayCallback, Runnable skipCallback, Runnable toggleSoundCallback, StartAppSDK params) {
        super(context, closeCallback, clickCallback, params);
        this.replayCallback = null;
        this.skipCallback = null;
        this.toggleSoundCallback = null;
        this.replayCallback = replayCallback;
        this.skipCallback = skipCallback;
        this.toggleSoundCallback = toggleSoundCallback;
    }

    @JavascriptInterface
    public void replayVideo() {
        StartAppSDK.m2928a(TAG, 3, "replayVideo called");
        if (this.replayCallback != null) {
            new Handler(Looper.getMainLooper()).post(this.replayCallback);
        }
    }

    @JavascriptInterface
    public void skipVideo() {
        StartAppSDK.m2928a(TAG, 3, "skipVideo called");
        if (this.skipCallback != null) {
            new Handler(Looper.getMainLooper()).post(this.skipCallback);
        }
    }

    @JavascriptInterface
    public void toggleSound() {
        StartAppSDK.m2928a(TAG, 3, "toggleSound called");
        if (this.toggleSoundCallback != null) {
            new Handler(Looper.getMainLooper()).post(this.toggleSoundCallback);
        }
    }
}
