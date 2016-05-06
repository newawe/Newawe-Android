package com.startapp.android.publish.adinformation;

import android.webkit.JavascriptInterface;

/* compiled from: StartAppSDK */
public class AdInformationJsInterface {
    private Runnable acceptCallback;
    private Runnable declineCallback;
    private boolean processed;

    public AdInformationJsInterface(Runnable acceptCallback, Runnable declineCallback) {
        this.processed = false;
        this.declineCallback = null;
        this.acceptCallback = null;
        this.acceptCallback = acceptCallback;
        this.declineCallback = declineCallback;
    }

    @JavascriptInterface
    public void decline() {
        if (!this.processed) {
            this.processed = true;
            this.declineCallback.run();
        }
    }

    @JavascriptInterface
    public void accept() {
        if (!this.processed) {
            this.processed = true;
            this.acceptCallback.run();
        }
    }
}
