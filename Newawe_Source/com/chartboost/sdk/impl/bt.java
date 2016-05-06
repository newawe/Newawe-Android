package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import java.util.Map;

public class bt extends WebView {
    private bs f687a;
    private boolean f688b;

    public bt(Context context) {
        super(context);
        this.f688b = false;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void setWebChromeClient(WebChromeClient client) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        if (VERSION.SDK_INT >= 11) {
            settings.setAllowContentAccess(true);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 11) {
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
        }
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        if (client instanceof bs) {
            this.f687a = (bs) client;
        }
        super.setWebChromeClient(client);
    }

    public void loadData(String data, String mimeType, String encoding) {
        m759a();
        super.loadData(data, mimeType, encoding);
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        m759a();
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void loadUrl(String url) {
        m759a();
        super.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        m759a();
        super.loadUrl(url, additionalHttpHeaders);
    }

    private void m759a() {
        if (!this.f688b) {
            this.f688b = true;
        }
    }
}
