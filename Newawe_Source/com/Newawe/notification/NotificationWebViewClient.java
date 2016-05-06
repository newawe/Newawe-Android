package com.Newawe.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.Newawe.browser.SimpleWebViewClient;

public class NotificationWebViewClient extends SimpleWebViewClient {
    public NotificationWebViewClient(Context context) {
        super(context);
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("WBVSRVCE", "Should override url: " + url);
        return false;
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.cancel();
    }
}
