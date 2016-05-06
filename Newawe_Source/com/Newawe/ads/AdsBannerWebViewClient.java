package com.Newawe.ads;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AdsBannerWebViewClient extends WebViewClient {
    OnPageFinishedListener _onPageFinishedlistener;
    OnPageStartedListener _onPageStartedlistener;

    public interface OnPageFinishedListener {
        void loadFinished(WebView webView, String str);
    }

    public interface OnPageStartedListener {
        boolean loadStarted(WebView webView, String str, Bitmap bitmap);
    }

    public void setOnPageFinishedListener(OnPageFinishedListener listener) {
        this._onPageFinishedlistener = listener;
    }

    public void setOnPageStartedListener(OnPageStartedListener listener) {
        this._onPageStartedlistener = listener;
    }

    public void onPageFinished(WebView view, String url) {
        if (this._onPageFinishedlistener != null) {
            this._onPageFinishedlistener.loadFinished(view, url);
        }
        super.onPageFinished(view, url);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this._onPageStartedlistener == null || this._onPageStartedlistener.loadStarted(view, url, null)) {
            return false;
        }
        return true;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (this._onPageStartedlistener == null || this._onPageStartedlistener.loadStarted(view, url, favicon)) {
            super.onPageStarted(view, url, favicon);
        }
    }
}
