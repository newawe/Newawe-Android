package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import mf.org.apache.xml.serialize.Method;
import org.apache.http.protocol.HTTP;

public class at extends ap {
    private WebView f3497a;
    private OnClickListener f3498b;

    /* renamed from: com.chartboost.sdk.impl.at.1 */
    class C03911 extends WebViewClient {
        final /* synthetic */ at f452a;

        C03911(at atVar) {
            this.f452a = atVar;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) {
                return false;
            }
            if (url.contains("chartboost") && url.contains("click") && this.f452a.f3498b != null) {
                this.f452a.f3498b.onClick(this.f452a);
            }
            return true;
        }
    }

    public at(aw awVar, Context context) {
        super(context);
        this.f3498b = null;
        this.f3497a = new WebView(context);
        addView(this.f3497a, new LayoutParams(-1, -1));
        this.f3497a.setBackgroundColor(0);
        this.f3497a.setWebViewClient(new C03911(this));
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.f3498b = clickListener;
    }

    public void m3896a(C0321a c0321a, int i) {
        String e = c0321a.m138e(Method.HTML);
        if (e != null) {
            try {
                this.f3497a.loadDataWithBaseURL("file:///android_res/", e, "text/html", HTTP.UTF_8, null);
            } catch (Throwable e2) {
                CBLogging.m78b("AppCellWebView", "Exception raised loading data into webview", e2);
            }
        }
    }

    public int m3895a() {
        return CBUtility.m86a(100, getContext());
    }
}
