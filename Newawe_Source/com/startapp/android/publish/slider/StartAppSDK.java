package com.startapp.android.publish.slider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.slider.sliding.DrawerLayout;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.startapp.android.publish.slider.b */
public class StartAppSDK {
    private static boolean f3138i;
    private Activity f3139a;
    private WebView f3140b;
    private DrawerLayout f3141c;
    private Handler f3142d;
    private com.startapp.android.publish.p028a.StartAppSDK f3143e;
    private int f3144f;
    private Handler f3145g;
    private com.startapp.android.publish.adinformation.StartAppSDK f3146h;

    /* renamed from: com.startapp.android.publish.slider.b.2 */
    class StartAppSDK extends WebView {
        final /* synthetic */ StartAppSDK f3119a;

        StartAppSDK(StartAppSDK startAppSDK, Context context) {
            this.f3119a = startAppSDK;
            super(context);
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (event.getAction() == 0) {
                switch (keyCode) {
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        if (this.f3119a.f3140b != null && this.f3119a.f3140b.canGoBack()) {
                            this.f3119a.f3140b.goBack();
                        } else if (this.f3119a.f3141c != null) {
                            this.f3119a.f3141c.m3227b();
                        }
                        return true;
                }
            }
            return super.onKeyDown(keyCode, event);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.3 */
    class StartAppSDK implements OnTouchListener {
        final /* synthetic */ StartAppSDK f3120a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3120a = startAppSDK;
        }

        public boolean onTouch(View view, MotionEvent event) {
            this.f3120a.f3141c.requestDisallowInterceptTouchEvent(true);
            return false;
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ ImageButton f3121a;
        final /* synthetic */ TranslateAnimation f3122b;
        final /* synthetic */ StartAppSDK f3123c;

        StartAppSDK(StartAppSDK startAppSDK, ImageButton imageButton, TranslateAnimation translateAnimation) {
            this.f3123c = startAppSDK;
            this.f3121a = imageButton;
            this.f3122b = translateAnimation;
        }

        public void run() {
            this.f3121a.startAnimation(this.f3122b);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.5 */
    class StartAppSDK implements AnimationListener {
        final /* synthetic */ ImageButton f3124a;
        final /* synthetic */ TranslateAnimation f3125b;
        final /* synthetic */ StartAppSDK f3126c;

        StartAppSDK(StartAppSDK startAppSDK, ImageButton imageButton, TranslateAnimation translateAnimation) {
            this.f3126c = startAppSDK;
            this.f3124a = imageButton;
            this.f3125b = translateAnimation;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f3124a.startAnimation(this.f3125b);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.6 */
    class StartAppSDK implements AnimationListener {
        final /* synthetic */ ImageButton f3127a;
        final /* synthetic */ TranslateAnimation f3128b;
        final /* synthetic */ StartAppSDK f3129c;

        StartAppSDK(StartAppSDK startAppSDK, ImageButton imageButton, TranslateAnimation translateAnimation) {
            this.f3129c = startAppSDK;
            this.f3127a = imageButton;
            this.f3128b = translateAnimation;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f3129c.f3144f = this.f3129c.f3144f + 1;
            if (this.f3129c.f3144f > 12) {
                this.f3129c.f3144f = 0;
            } else {
                this.f3127a.startAnimation(this.f3128b);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.7 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ ImageButton f3130a;
        final /* synthetic */ TranslateAnimation f3131b;
        final /* synthetic */ StartAppSDK f3132c;

        StartAppSDK(StartAppSDK startAppSDK, ImageButton imageButton, TranslateAnimation translateAnimation) {
            this.f3132c = startAppSDK;
            this.f3130a = imageButton;
            this.f3131b = translateAnimation;
        }

        public void run() {
            this.f3130a.startAnimation(this.f3131b);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.8 */
    class StartAppSDK implements OnClickListener {
        final /* synthetic */ RelativeLayout f3133a;
        final /* synthetic */ StartAppSDK f3134b;

        StartAppSDK(StartAppSDK startAppSDK, RelativeLayout relativeLayout) {
            this.f3134b = startAppSDK;
            this.f3133a = relativeLayout;
        }

        public void onClick(View v) {
            this.f3134b.f3141c.m3236h(this.f3133a);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.a */
    private class StartAppSDK extends WebViewClient {
        final /* synthetic */ StartAppSDK f3137a;

        /* renamed from: com.startapp.android.publish.slider.b.a.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ String f3135a;
            final /* synthetic */ StartAppSDK f3136b;

            StartAppSDK(StartAppSDK startAppSDK, String str) {
                this.f3136b = startAppSDK;
                this.f3135a = str;
            }

            public void run() {
                this.f3136b.f3137a.f3140b.loadUrl(this.f3135a);
            }
        }

        private StartAppSDK(StartAppSDK startAppSDK) {
            this.f3137a = startAppSDK;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 2, "MyWebViewClient::onPageStarted - [" + url + "]");
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 2, "MyWebViewClient::shouldOverrideUrlLoading - [" + url + "]");
            String toLowerCase = url.toLowerCase();
            CharSequence charSequence = "searchmobileonline";
            try {
                charSequence = new URL(((AdDetails) this.f3137a.f3143e.m5210b().get(0)).getClickUrl()).getHost();
            } catch (MalformedURLException e) {
            }
            if (toLowerCase.contains(charSequence)) {
                return false;
            }
            if (toLowerCase.contains(MetaData.getInstance().getAdClickUrl().toLowerCase())) {
                com.startapp.android.publish.p022h.StartAppSDK.m3001a(this.f3137a.m3206a(), url + (MetaData.getInstance().isDisableTwoClicks() ? StringUtils.EMPTY : com.startapp.android.publish.p022h.StartAppSDK.m2889a(com.startapp.android.publish.p022h.StartAppSDK.m3027b(url))), null, null, new com.startapp.android.publish.p022h.StartAppSDK(), MetaData.getInstance().getSmartRedirectTimeout(), ((AdDetails) this.f3137a.f3143e.m5210b().get(0)).isStartappBrowserEnabled());
                return true;
            }
            com.startapp.android.publish.p022h.StartAppSDK.m3030b(this.f3137a.m3206a(), url, com.startapp.android.publish.p022h.StartAppSDK.m3027b(url));
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 2, "MyWebViewClient::onPageFinished - [" + url + "]");
            super.onPageFinished(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 2, "MyWebViewClient::onReceivedError - [" + description + "], [" + failingUrl + "]");
            this.f3137a.f3142d.postDelayed(new StartAppSDK(this, failingUrl), 5000);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.1 */
    class StartAppSDK implements AdEventListener {
        final /* synthetic */ StartAppSDK f4166a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f4166a = startAppSDK;
        }

        public void onReceiveAd(Ad ad) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 3, "Slider loaded");
            this.f4166a.m3194b();
            this.f4166a.m3199d();
        }

        public void onFailedToReceiveAd(Ad ad) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 6, "Error loading Slider");
        }
    }

    /* renamed from: com.startapp.android.publish.slider.b.9 */
    class StartAppSDK implements com.startapp.android.publish.slider.sliding.DrawerLayout.StartAppSDK {
        final /* synthetic */ StartAppSDK f4167a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f4167a = startAppSDK;
        }

        public void m4864a(int i) {
        }

        public void m4866a(View view, float f) {
        }

        public void m4865a(View view) {
            this.f4167a.m3201e();
        }

        public void m4867b(View view) {
            ((InputMethodManager) this.f4167a.m3206a().getSystemService("input_method")).hideSoftInputFromWindow(this.f4167a.f3140b.getWindowToken(), 0);
        }
    }

    static {
        f3138i = false;
    }

    public StartAppSDK(Activity activity) {
        this(activity, new AdPreferences());
    }

    public StartAppSDK(Activity activity, AdPreferences adPreferences) {
        this.f3142d = new Handler();
        this.f3144f = 0;
        this.f3145g = new Handler();
        this.f3146h = com.startapp.android.publish.adinformation.StartAppSDK.m2551a();
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Slider", 3, "new Slider Created");
        this.f3139a = activity;
        this.f3143e = new com.startapp.android.publish.p028a.StartAppSDK(activity);
        this.f3143e.m5208a(adPreferences, new StartAppSDK(this));
    }

    public Activity m3206a() {
        return this.f3139a;
    }

    private void m3194b() {
        this.f3140b = new StartAppSDK(this, m3206a());
        this.f3140b.setOnTouchListener(new StartAppSDK(this));
        WebViewJSInterface webViewJSInterface = new WebViewJSInterface(this.f3139a);
        this.f3140b.setLayoutParams(new LayoutParams(-1, -1));
        this.f3140b.getSettings().setJavaScriptEnabled(true);
        this.f3140b.addJavascriptInterface(webViewJSInterface, "HTMLOUT");
        this.f3140b.setWebChromeClient(new WebChromeClient());
        this.f3140b.setHorizontalScrollBarEnabled(false);
        this.f3140b.setVerticalScrollBarEnabled(true);
        if (VERSION.SDK_INT > 10) {
            this.f3140b.setLayerType(1, null);
        }
        this.f3140b.setWebViewClient(new StartAppSDK());
        String str = ((AdDetails) this.f3143e.m5210b().get(0)).getClickUrl() + m3197c() + "&ver=" + com.startapp.android.publish.StartAppSDK.f2831g;
        com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, "Slider URL: " + str);
        this.f3140b.loadUrl(str);
    }

    private String m3197c() {
        String e = com.startapp.android.publish.p022h.StartAppSDK.m3043e(m3206a());
        if (e == null || StringUtils.EMPTY.equals(e)) {
            return StringUtils.EMPTY;
        }
        try {
            return "&token=" + URLEncoder.encode(e, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e2) {
            return StringUtils.EMPTY;
        }
    }

    private void m3199d() {
        if (com.startapp.android.publish.p022h.StartAppSDK.m3018a(m3206a())) {
            ViewGroup viewGroup = (ViewGroup) this.f3139a.findViewById(16908290);
            View childAt = viewGroup.getChildAt(0);
            viewGroup.removeView(childAt);
            this.f3141c = new DrawerLayout(m3206a());
            View relativeLayout = new RelativeLayout(this.f3139a);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
            relativeLayout.addView(childAt);
            View imageButton = new ImageButton(this.f3139a);
            if (VERSION.SDK_INT >= 16) {
                imageButton.setBackground(null);
            } else {
                imageButton.setBackgroundDrawable(null);
            }
            imageButton.setPadding(0, 0, com.startapp.android.publish.p022h.StartAppSDK.m2985a(this.f3139a, 10), 0);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(15);
            imageButton.setLayoutParams(layoutParams);
            imageButton.setImageBitmap(com.startapp.android.publish.p022h.StartAppSDK.m2894a(this.f3139a, "tab_side.png"));
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -0.07f, 1, 0.07f);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.07f, 1, -0.07f);
            translateAnimation.setDuration(40);
            translateAnimation2.setDuration(40);
            StartAppSDK startAppSDK = new StartAppSDK(this, imageButton, translateAnimation);
            translateAnimation.setAnimationListener(new StartAppSDK(this, imageButton, translateAnimation2));
            translateAnimation2.setAnimationListener(new StartAppSDK(this, imageButton, translateAnimation));
            relativeLayout.addView(imageButton);
            if (!f3138i) {
                f3138i = true;
                this.f3145g.postDelayed(new StartAppSDK(this, imageButton, translateAnimation), 15000);
            }
            this.f3141c.setLayoutParams(new com.startapp.android.publish.slider.sliding.DrawerLayout.StartAppSDK(-1, -1));
            this.f3141c.addView(relativeLayout);
            RelativeLayout relativeLayout2 = new RelativeLayout(m3206a());
            ViewGroup.LayoutParams startAppSDK2 = new com.startapp.android.publish.slider.sliding.DrawerLayout.StartAppSDK(com.startapp.android.publish.p022h.StartAppSDK.m2985a(this.f3139a, 290), -1);
            startAppSDK2.f3150a = 3;
            relativeLayout2.setLayoutParams(startAppSDK2);
            relativeLayout2.addView(this.f3140b);
            this.f3141c.addView(relativeLayout2);
            viewGroup.addView(this.f3141c);
            new com.startapp.android.publish.adinformation.StartAppSDK(this.f3139a, com.startapp.android.publish.adinformation.StartAppSDK.StartAppSDK.LARGE, Placement.DEVICE_SIDEBAR, this.f3146h).m2548a(relativeLayout2);
            imageButton.setOnClickListener(new StartAppSDK(this, relativeLayout2));
            this.f3141c.setDrawerListener(new StartAppSDK(this));
        }
    }

    private void m3201e() {
        com.startapp.android.publish.p028a.StartAppSDK startAppSDK = this.f3143e;
        if (com.startapp.android.publish.p022h.StartAppSDK.m2907a(m3206a(), "trackingUrl", null) == null) {
            try {
                this.f3140b.loadUrl("javascript:window.HTMLOUT.processHTML(document.getElementById('SearchResults').innerHTML);");
            } catch (Throwable th) {
                com.startapp.android.publish.p022h.StartAppSDK.m2929a("Slider", 6, "Error reading SearchResults div ", th);
            }
        }
        new StartAppSDK(this.f3139a).m3191a(((AdDetails) startAppSDK.m5210b().get(0)).getTrackingUrl());
    }
}
