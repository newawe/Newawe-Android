package com.inmobi.commons.core.network;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.commons.p000a.SdkContext;

/* renamed from: com.inmobi.commons.core.network.e */
public class WebViewNetworkTask {
    private NetworkRequest f1251a;
    private WebViewClient f1252b;
    private WebView f1253c;

    public WebViewNetworkTask(NetworkRequest networkRequest, WebViewClient webViewClient) {
        this.f1252b = webViewClient;
        this.f1251a = networkRequest;
    }

    public void m1438a() {
        m1437b();
        String h = this.f1251a.m1409h();
        if (!(this.f1251a.m1411j() == null || this.f1251a.m1411j().trim().length() == 0)) {
            h = h + "?" + this.f1251a.m1411j();
        }
        this.f1253c.loadUrl(h);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m1437b() {
        this.f1253c = new WebView(SdkContext.m1258b());
        this.f1253c.setWebViewClient(this.f1252b);
        this.f1253c.getSettings().setJavaScriptEnabled(true);
        this.f1253c.getSettings().setCacheMode(2);
    }
}
