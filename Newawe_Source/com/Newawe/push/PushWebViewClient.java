package com.Newawe.push;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import com.Newawe.browser.SimpleWebViewClient;

public class PushWebViewClient extends SimpleWebViewClient {
    String _defaultUrl;

    public PushWebViewClient(String defaultUrl, Activity activity) {
        super(activity);
        this._defaultUrl = defaultUrl;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (!_proceedPageStarted(view, url)) {
            super.onPageStarted(view, url, favicon);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (super.shouldOverrideUrlLoading(view, url)) {
            return true;
        }
        return _proceedPageStarted(view, url);
    }

    private boolean _proceedPageStarted(WebView view, String url) {
        if (url.equals(this._defaultUrl)) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            view.loadUrl(this._defaultUrl);
            if (this._context.getPackageManager().resolveActivity(intent, 0) != null) {
                this._context.startActivity(intent);
            }
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("ANFE", "onPageStarted() :" + e.getMessage());
            return false;
        }
    }
}
