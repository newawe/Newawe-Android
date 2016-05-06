package com.Newawe.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Newawe.BrowserActivity;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.ads.AdsBannerWebViewClient.OnPageFinishedListener;
import com.Newawe.ads.AdsBannerWebViewClient.OnPageStartedListener;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorFactory.ClickBehavior;
import com.Newawe.ads.behavior.BehaviorVisitor;
import com.Newawe.ads.behavior.loaderBehaviors.LoaderBehavior;
import com.Newawe.server.AppsGeyserServerClient;
import com.Newawe.ui.views.BrowserWebView;
import com.Newawe.ui.views.TransparentPanel;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;

public class AdsLoader implements BehaviorAcceptor, OnPageFinishedListener, OnPageStartedListener {
    final float DEFAULT_HIDE_TIMEOUT;
    Activity _activity;
    BrowserWebView _bannerBrowser;
    BottomBannerLayout _bannerLayout;
    String _bannerUrl;
    AdsBannerWebViewClient _browserClient;
    ClickBehavior _clickBehavior;
    String _clickUrl;
    Thread _closeBannerThread;
    HeadersReceiver _headersReceiver;
    Map<String, List<String>> _lastResponseHeaders;
    AdsLoadingFinishedListener _loadingFinishedListener;
    private boolean _openInNativeBrowser;
    Timer _refreshTimer;
    AppsGeyserServerClient _serverClient;

    /* renamed from: com.Newawe.ads.AdsLoader.1 */
    class C01901 implements OnTouchListener {
        C01901() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case DurationDV.DURATION_TYPE /*0*/:
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (!v.hasFocus()) {
                        v.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.Newawe.ads.AdsLoader.2 */
    class C01912 extends WebChromeClient {
        C01912() {
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    /* renamed from: com.Newawe.ads.AdsLoader.3 */
    class C01923 extends Thread {
        C01923() {
        }

        public void run() {
            AdsLoader.this._refreshTimer.cancel();
            AdsLoader.this._bannerBrowser.stopLoading();
            AdsLoader.this._bannerLayout.hideBanner();
        }
    }

    /* renamed from: com.Newawe.ads.AdsLoader.4 */
    class C01934 implements Runnable {
        C01934() {
        }

        public void run() {
            AdsLoader.this._bannerLayout.switchToHtmlAd();
        }
    }

    /* renamed from: com.Newawe.ads.AdsLoader.5 */
    class C01955 extends Thread {

        /* renamed from: com.Newawe.ads.AdsLoader.5.1 */
        class C01941 implements Runnable {
            C01941() {
            }

            public void run() {
                AdsLoader.this._bannerBrowser.loadUrl(AdsLoader.this._bannerUrl);
            }
        }

        C01955() {
        }

        public void run() {
            Map<String, List<String>> headers = AdsLoader.this._serverClient.loadHeaders(AdsLoader.this._bannerUrl);
            AdsLoader.this._lastResponseHeaders = headers;
            if (headers == null) {
                return;
            }
            if (AdsLoader.this._headersReceiver == null || AdsLoader.this._headersReceiver.onAdHeadersReceived(AdsLoader.this._lastResponseHeaders)) {
                AdsLoader.this._activity.runOnUiThread(new C01941());
            }
        }
    }

    /* renamed from: com.Newawe.ads.AdsLoader.6 */
    class C01966 extends TimerTask {
        C01966() {
        }

        public void run() {
            AdsLoader.this.reload();
            AdsLoader.this._refreshTimer.cancel();
        }
    }

    public interface AdsLoadingFinishedListener {
        void onAdLoadFinished();
    }

    public interface HeadersReceiver {
        boolean onAdHeadersReceived(Map<String, List<String>> map);
    }

    public AdsLoader() {
        this.DEFAULT_HIDE_TIMEOUT = 60000.0f;
        this._closeBannerThread = null;
        this._refreshTimer = new Timer();
        this._openInNativeBrowser = true;
    }

    public void init(String bannerUrl, Activity activity) {
        this._activity = activity;
        if (this._activity instanceof MainNavigationActivity) {
            this._serverClient = AppsGeyserServerClient.getInstance((MainNavigationActivity) this._activity);
        } else {
            this._serverClient = AppsGeyserServerClient.getInstance(Factory.getInstance().getMainNavigationActivity());
        }
        this._bannerBrowser = (BrowserWebView) this._activity.findViewById(C0186R.id.banner_webkit);
        this._bannerBrowser.setOnTouchListener(new C01901());
        this._browserClient = new AdsBannerWebViewClient();
        this._browserClient.setOnPageFinishedListener(this);
        this._browserClient.setOnPageStartedListener(this);
        this._bannerBrowser.setWebChromeClient(new C01912());
        WebSettings settings = this._bannerBrowser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        Context ctx = activity.getApplicationContext();
        String appCachePath = ctx.getDir("appcache", 0).getPath();
        String databasePath = ctx.getDir("databases", 0).getPath();
        String geolocationDatabasePath = ctx.getDir("geolocation", 0).getPath();
        settings.setAppCachePath(appCachePath);
        settings.setDatabasePath(databasePath);
        settings.setGeolocationDatabasePath(geolocationDatabasePath);
        this._bannerUrl = bannerUrl;
        this._clickBehavior = ClickBehavior.HIDE;
        this._closeBannerThread = new C01923();
    }

    public String getClickUrl() {
        return this._clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this._clickUrl = clickUrl;
    }

    public void setBottomBannerLayout(BottomBannerLayout layout) {
        this._bannerLayout = layout;
    }

    public BottomBannerLayout getBottomBannerLayout() {
        return this._bannerLayout;
    }

    public void setHeaderReceiver(HeadersReceiver receiver) {
        this._headersReceiver = receiver;
    }

    public void setAdsLoadingFinishedListener(AdsLoadingFinishedListener listener) {
        this._loadingFinishedListener = listener;
    }

    public void reload() {
        try {
            this._activity.runOnUiThread(new C01934());
            this._bannerBrowser.setWebViewClient(this._browserClient);
            new C01955().start();
        } catch (Exception e) {
            Log.e("AdsLoader", e.getMessage());
        }
    }

    public Map<String, List<String>> getLastResponseHeaders() {
        return this._lastResponseHeaders;
    }

    public void changeClickBehavior(ClickBehavior clickBehavior) {
        this._clickBehavior = clickBehavior;
    }

    public void setRefreshTimeout(float seconds) {
        if (((double) seconds) > 0.0d) {
            this._refreshTimer.cancel();
            this._refreshTimer = new Timer();
            this._refreshTimer.scheduleAtFixedRate(new C01966(), (long) ((int) (1000.0f * seconds)), 100);
        }
    }

    public void setHideTimeout(float seconds) {
        if (((double) seconds) <= 0.0d) {
            seconds = 60000.0f;
        }
        TransparentPanel panel = (TransparentPanel) this._activity.findViewById(C0186R.id.banner_panel);
        panel.removeCallbacks(this._closeBannerThread);
        panel.postDelayed(this._closeBannerThread, (long) (1000.0f * seconds));
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        if (visitor instanceof LoaderBehavior) {
            ((LoaderBehavior) visitor).visit((BehaviorAcceptor) this);
        }
    }

    public void loadFinished(WebView view, String url) {
        if (url.equalsIgnoreCase(this._bannerUrl)) {
            _setDefaults();
            if (this._loadingFinishedListener != null) {
                this._loadingFinishedListener.onAdLoadFinished();
            }
        }
    }

    private void _setDefaults() {
        this._bannerLayout.showBanner();
        this._refreshTimer.cancel();
        setHideTimeout(60000.0f);
        this._bannerLayout.applyDefaultSettings();
    }

    public boolean loadStarted(WebView view, String url, Bitmap favicon) {
        if (url.equals(this._bannerUrl)) {
            return true;
        }
        Intent intent;
        if (this._clickBehavior == ClickBehavior.HIDE) {
            this._bannerLayout.hideBanner();
            this._refreshTimer.cancel();
        } else if (this._clickBehavior == ClickBehavior.REMAIN_ON_SCREEN) {
            reload();
        }
        view.stopLoading();
        url = url.replaceAll("&nostat=1", StringUtils.EMPTY);
        if (this._openInNativeBrowser) {
            intent = new Intent(this._activity, BrowserActivity.class);
            intent.putExtra(BrowserActivity.KEY_BROWSER_URL, url);
            intent.putExtra(BrowserActivity.KEY_BANNER_TYPE, BrowserActivity.BANNER_TYPE_SMALL);
            intent.addFlags(268435456);
        } else {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        }
        this._activity.startActivity(intent);
        if (this._clickUrl != null && this._clickUrl.length() > 0) {
            this._serverClient.SendClickInfo(this._clickUrl);
        }
        return false;
    }

    public void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this._openInNativeBrowser = openInNativeBrowser;
    }
}
