package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.C0349b;
import com.chartboost.sdk.C0367f;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1020a;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public final class bu extends C0372g {
    public String f3610k;
    protected int f3611l;
    private String f3612m;
    private String f3613n;
    private float f3614o;
    private float f3615p;
    private boolean f3616q;
    private long f3617r;
    private long f3618s;
    private C0457b f3619t;

    /* renamed from: com.chartboost.sdk.impl.bu.1 */
    class C04541 implements Runnable {
        final /* synthetic */ bu f689a;

        C04541(bu buVar) {
            this.f689a = buVar;
        }

        public void run() {
            String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"videoPlay\", \"\")";
            CBLogging.m75a("CBWebViewProtocol", "Calling native to javascript: " + str);
            this.f689a.m4074q().f3605b.loadUrl(str);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bu.2 */
    class C04552 implements Runnable {
        final /* synthetic */ bu f690a;

        C04552(bu buVar) {
            this.f690a = buVar;
        }

        public void run() {
            String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"videoPause\", \"\")";
            CBLogging.m75a("CBWebViewProtocol", "Calling native to javascript: " + str);
            this.f690a.m4074q().f3605b.loadUrl(str);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bu.a */
    private class C0456a extends WebViewClient {
        final /* synthetic */ bu f691a;

        private C0456a(bu buVar) {
            this.f691a = buVar;
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            this.f691a.f3618s = System.currentTimeMillis();
            CBLogging.m75a("CBWebViewProtocol", "Total web view load response time " + ((this.f691a.f3618s - this.f691a.f3617r) / 1000));
            C0367f a = C0367f.m440a();
            if (a != null) {
                a.m443a(this.f691a.f);
            } else {
                this.f691a.m4055d("View Controller instance is null");
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            this.f691a.m4055d(description);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bu.b */
    public enum C0457b {
        NONE,
        IDLE,
        PLAYING,
        PAUSED
    }

    /* renamed from: com.chartboost.sdk.impl.bu.c */
    public class C1060c extends C0370a {
        public bt f3605b;
        public bs f3606c;
        public RelativeLayout f3607d;
        public RelativeLayout f3608e;
        final /* synthetic */ bu f3609f;

        public C1060c(bu buVar, Context context, String str) {
            this.f3609f = buVar;
            super(buVar, context);
            setFocusable(false);
            this.f3607d = new RelativeLayout(context);
            this.f3608e = new RelativeLayout(context);
            this.f3605b = new bt(context);
            this.f3605b.setWebViewClient(new C0456a(null));
            this.f3606c = new bs(this.f3607d, this.f3608e, null, this.f3605b, buVar);
            this.f3605b.setWebChromeClient(this.f3606c);
            if (VERSION.SDK_INT >= 19) {
                bt btVar = this.f3605b;
                bt.setWebContentsDebuggingEnabled(true);
            }
            this.f3605b.loadDataWithBaseURL(buVar.f3613n, str, "text/html", "utf-8", null);
            this.f3607d.addView(this.f3605b);
            this.f3605b.getSettings().setSupportZoom(false);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.f3607d.setLayoutParams(layoutParams);
            this.f3605b.setLayoutParams(layoutParams);
            this.f3605b.setBackgroundColor(0);
            this.f3608e.setVisibility(8);
            this.f3608e.setLayoutParams(layoutParams);
            addView(this.f3607d);
            addView(this.f3608e);
            buVar.f3617r = System.currentTimeMillis();
        }

        protected void m4047a(int i, int i2) {
        }
    }

    public /* synthetic */ C0370a m4065e() {
        return m4074q();
    }

    public bu(C0343a c0343a) {
        super(c0343a);
        this.f3610k = "UNKNOWN";
        this.f3612m = null;
        this.f3613n = null;
        this.f3611l = 1;
        this.f3614o = 0.0f;
        this.f3615p = 0.0f;
        this.f3616q = false;
        this.f3617r = 0;
        this.f3618s = 0;
        this.f3619t = C0457b.NONE;
    }

    protected C0370a m4060b(Context context) {
        return new C1060c(this, context, this.f3612m);
    }

    public boolean m4059a(C0321a c0321a) {
        File a = C0330h.m185a();
        if (a == null) {
            CBLogging.m77b("CBWebViewProtocol", "External Storage path is unavailable or media not mounted");
            m468a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.f3613n = "file://" + a.getAbsolutePath() + "/";
        CharSequence e = c0321a.m138e("ad_unit_id");
        if (TextUtils.isEmpty(e)) {
            CBLogging.m77b("CBWebViewProtocol", "Invalid adId being passed in th response");
            m468a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            return false;
        }
        ConcurrentHashMap d = C0349b.m310d();
        if (d == null || d.isEmpty() || !d.containsKey(e)) {
            CBLogging.m77b("CBWebViewProtocol", "No html data found in memory");
            m468a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.f3612m = (String) d.get(e);
        m476b();
        return true;
    }

    public void m4066h() {
        super.m482h();
    }

    public void m4058a(JSONObject jSONObject, String str) {
        C1020a.m3741a(this.f.m286u().m420e(), this.f.f229e, this.f.m285t(), jSONObject, str);
    }

    public void m4062b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "Unknown Webview error";
        }
        C1020a.m3739a(this.f.m286u().m420e(), this.f.f229e, this.f.m285t(), str);
        CBLogging.m77b("CBWebViewProtocol", "Webview error occurred closing the webview" + str);
        m468a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        m4066h();
    }

    public void m4063c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "Unknown Webview warning message";
        }
        C1020a.m3749b(this.f.m286u().m420e(), this.f.f229e, this.f.m285t(), str);
        CBLogging.m81d("CBWebViewProtocol", "Webview warning occurred closing the webview" + str);
    }

    private void m4055d(String str) {
        C0367f a = C0367f.m440a();
        if (a != null) {
            a.m445b();
        }
        m468a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        if (TextUtils.isEmpty(str)) {
            str = StringUtils.EMPTY;
        }
        C1020a.m3739a(this.f.m286u().m420e(), this.f.f229e, this.f.m285t(), "OReceivedError called, error loading Web View" + str);
        CBLogging.m77b("CBWebViewProtocol", " OReceivedError called, error loading Web View" + str);
    }

    public boolean m4069l() {
        if (this.f3619t != C0457b.PLAYING) {
            C1060c q = m4074q();
            if (q != null) {
                q.f3606c.onHideCustomView();
            }
            m4066h();
        }
        return true;
    }

    public void m4070m() {
        super.m487m();
        if (this.f3619t == C0457b.PAUSED && m4074q() != null) {
            CBUtility.m96e().post(new C04541(this));
            C1020a.m3754d(this.f3610k, this.f.m285t());
        }
    }

    public void m4071n() {
        super.m488n();
        if (this.f3619t == C0457b.PLAYING && m4074q() != null) {
            CBUtility.m96e().post(new C04552(this));
            C1020a.m3757e(this.f3610k, this.f.m285t());
        }
    }

    public void m4072o() {
        if (this.f3611l <= 1) {
            this.f.m272g();
            this.f3611l++;
            C1020a.m3747b(this.f3610k, this.f.m285t());
        }
    }

    public void m4064d() {
        super.m478d();
    }

    public void m4073p() {
        C1020a.m3750c(this.f3610k, this.f.m285t());
    }

    public void m4057a(C0457b c0457b) {
        this.f3619t = c0457b;
    }

    public C1060c m4074q() {
        return (C1060c) super.m479e();
    }

    public void m4056a(float f) {
        this.f3615p = f;
    }

    public void m4061b(float f) {
        this.f3614o = f;
    }

    public float m4067j() {
        return this.f3614o;
    }

    public float m4068k() {
        return this.f3615p;
    }

    public void m4075r() {
        if (!this.f3616q) {
            C1020a.m3754d(StringUtils.EMPTY, this.f.m285t());
            this.f.m287v();
            this.f3616q = true;
        }
    }
}
