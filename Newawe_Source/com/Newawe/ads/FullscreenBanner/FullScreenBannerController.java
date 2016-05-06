package com.Newawe.ads.FullscreenBanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Newawe.BrowserActivity;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.MainNavigationActivity.ApplicationState;
import com.Newawe.browser.SimpleWebViewClient;
import com.Newawe.server.AppsGeyserServerClient;
import com.Newawe.server.StatController;
import com.Newawe.storage.DatabaseOpenHelper;
import com.Newawe.ui.dialog.ProgressDialogManager;
import com.Newawe.ui.views.BrowserWebView;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

public class FullScreenBannerController {
    private static final long LOADING_TIMEOUT_ON_EXIT = 5000;
    private static final long LOADING_TIMEOUT_ON_START = 35000;
    private static final long NO_TIMER = -1;
    private boolean _backKeyLocked;
    private BrowserWebView _browser;
    private String _clickHandlerUrl;
    private Runnable _closeRunnable;
    private ViewGroup _fullScreenBannerViewContainer;
    private Handler _handler;
    private boolean _isOnScreen;
    private boolean _keepAliveCalled;
    private MainNavigationActivity _mainActivity;
    private String _onExitBannerUrl;
    private String _onStartAppsgeyserStartupBannerUrl;
    private String _onStartBannerUrl;
    private boolean _openInNativeBrowser;
    private long _timerDuration;
    private boolean allowRedirects;
    private IFullScreenBannerListener listener;
    private Runnable showBannerRunnable;

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.1 */
    class C01991 implements Runnable {
        C01991() {
        }

        public void run() {
            if (!FullScreenBannerController.this._keepAliveCalled) {
                FullScreenBannerController.this.closeBanner();
            }
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.2 */
    class C02002 implements OnTouchListener {
        C02002() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == 1) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_WEBWIEW_TAP);
            }
            return false;
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.3 */
    class C02013 extends WebChromeClient {
        C02013() {
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.4 */
    class C02034 implements Runnable {

        /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.4.1 */
        class C02021 implements Runnable {
            C02021() {
            }

            public void run() {
                ProgressDialogManager.getInstance(FullScreenBannerController.this._mainActivity).dismissProgressDialog();
                FullScreenBannerController.this._mainActivity.showFullscreenBannerView();
            }
        }

        C02034() {
        }

        public void run() {
            FullScreenBannerController.this._mainActivity.runOnUiThread(new C02021());
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.5 */
    class C02045 implements Runnable {
        C02045() {
        }

        public void run() {
            ProgressDialogManager.getInstance(FullScreenBannerController.this._mainActivity).showProgressDialog();
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.6 */
    class C02056 implements Runnable {
        C02056() {
        }

        public void run() {
            if (MainNavigationActivity.getApplicationState() == null || !MainNavigationActivity.getApplicationState().equals(ApplicationState.EXITING)) {
                FullScreenBannerController.this._mainActivity.showContentView();
            }
        }
    }

    /* renamed from: com.Newawe.ads.FullscreenBanner.FullScreenBannerController.7 */
    class C02067 implements Runnable {
        final /* synthetic */ String val$adUnitID;
        final /* synthetic */ String val$birthday;
        final /* synthetic */ String val$genderString;
        final /* synthetic */ String val$keywords;
        final /* synthetic */ String val$latitude;
        final /* synthetic */ String val$longtitude;

        C02067(String str, String str2, String str3, String str4, String str5, String str6) {
            this.val$adUnitID = str;
            this.val$keywords = str2;
            this.val$genderString = str3;
            this.val$birthday = str4;
            this.val$latitude = str5;
            this.val$longtitude = str6;
        }

        public void run() {
            FullScreenBannerController.this._isOnScreen = false;
            FullScreenBannerController.this._handler.removeCallbacks(FullScreenBannerController.this.showBannerRunnable);
            ProgressDialogManager.getInstance(FullScreenBannerController.this._mainActivity).dismissProgressDialog();
            FullScreenBannerController.this._mainActivity.showContentView();
            FullScreenBannerController.this._mainActivity.getAdMobFSBannerController().loadAdMobFSBanner(this.val$adUnitID, this.val$keywords, this.val$genderString, this.val$birthday, this.val$latitude, this.val$longtitude);
        }
    }

    private class LoadUrlRunnable implements Runnable {
        private String _url;

        public LoadUrlRunnable(String url) {
            this._url = url;
        }

        public void run() {
            FullScreenBannerController.this._browser.loadUrl(this._url);
        }
    }

    private class FullScreenBannerWebViewClient extends SimpleWebViewClient {
        public FullScreenBannerWebViewClient(Activity activity) {
            super(activity);
        }

        public void onPageFinished(WebView view, String url) {
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            _handleRedirect(view, url);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return _handleRedirect(view, url);
        }

        private boolean _handleRedirect(WebView view, String url) {
            if (url.equalsIgnoreCase(FullScreenBannerController.this._onStartBannerUrl) || url.equalsIgnoreCase(FullScreenBannerController.this._onExitBannerUrl) || url.equalsIgnoreCase(FullScreenBannerController.this._onStartAppsgeyserStartupBannerUrl) || FullScreenBannerController.this.allowRedirects) {
                return false;
            }
            Intent intent;
            view.stopLoading();
            HashMap<String, String> details = new HashMap();
            details.put(DatabaseOpenHelper.HISTORY_ROW_URL, url);
            if (FullScreenBannerController.this._openInNativeBrowser) {
                intent = new Intent(FullScreenBannerController.this._mainActivity, BrowserActivity.class);
                intent.putExtra(BrowserActivity.KEY_BROWSER_URL, url);
                intent.putExtra(BrowserActivity.KEY_BANNER_TYPE, BrowserActivity.BANNER_TYPE_FULLSCREEN);
                intent.putExtra(BrowserActivity.KEY_TIMER_DURATION, FullScreenBannerController.this._timerDuration);
                intent.addFlags(268435456);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_REDIRECT_START, details);
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_EXTERNAL_BROWSER, details);
            }
            FullScreenBannerController.this._mainActivity.startActivity(intent);
            FullScreenBannerController.this.closeBanner();
            if (FullScreenBannerController.this._clickHandlerUrl != null && FullScreenBannerController.this._clickHandlerUrl.length() > 0) {
                AppsGeyserServerClient.getInstance(FullScreenBannerController.this._mainActivity).SendClickInfo(FullScreenBannerController.this._clickHandlerUrl);
            }
            return true;
        }
    }

    public FullScreenBannerController(ViewGroup container, MainNavigationActivity activity) {
        this._handler = new Handler();
        this._keepAliveCalled = false;
        this._onStartBannerUrl = StringUtils.EMPTY;
        this._onStartAppsgeyserStartupBannerUrl = StringUtils.EMPTY;
        this._onExitBannerUrl = StringUtils.EMPTY;
        this._clickHandlerUrl = StringUtils.EMPTY;
        this._openInNativeBrowser = true;
        this._backKeyLocked = true;
        this._isOnScreen = false;
        this.allowRedirects = false;
        this._timerDuration = NO_TIMER;
        this._closeRunnable = new C01991();
        this.showBannerRunnable = new C02034();
        if (container != null) {
            this._mainActivity = activity;
            this._fullScreenBannerViewContainer = container;
            this._browser = (BrowserWebView) this._fullScreenBannerViewContainer.findViewById(C0186R.id.startupScreenWebView);
            _setupWebView();
            this._browser.addJavascriptInterface(new FullscreenBannerJsInterface(this), FullscreenBannerJsInterface.JS_INTERFACE_NAME);
            this._onStartBannerUrl = AppsGeyserServerClient.getInstance(this._mainActivity).getOnStartFullScreenBannerUrl();
            this._onStartAppsgeyserStartupBannerUrl = this._onStartBannerUrl + "&startup_appsgeyser_banner=1";
            this._onExitBannerUrl = AppsGeyserServerClient.getInstance(this._mainActivity).getOnExitFullScreenBannerUrl();
            this._browser.setOnTouchListener(new C02002());
        }
    }

    private void _setupWebView() {
        this._browser.setWebViewClient(new FullScreenBannerWebViewClient(this._mainActivity));
        this._browser.setWebChromeClient(new C02013());
        this._browser.setScrollBarStyle(33554432);
        Context ctx = this._mainActivity.getApplicationContext();
        String appCachePath = ctx.getDir("appcache", 0).getPath();
        String databasePath = ctx.getDir("databases", 0).getPath();
        String geolocationDatabasePath = ctx.getDir("geolocation", 0).getPath();
        WebSettings settings = this._browser.getSettings();
        settings.setAppCachePath(appCachePath);
        settings.setDatabasePath(databasePath);
        settings.setGeolocationDatabasePath(geolocationDatabasePath);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
    }

    public void loadBanner() {
        this._timerDuration = NO_TIMER;
        ApplicationState applicationState = MainNavigationActivity.getApplicationState();
        if (applicationState.equals(ApplicationState.STARTED)) {
            this._mainActivity.runOnUiThread(new LoadUrlRunnable(this._onStartBannerUrl));
            closeOnTimeout(LOADING_TIMEOUT_ON_START);
        } else if (applicationState.equals(ApplicationState.EXITING)) {
            ProgressDialogManager.getInstance(this._mainActivity).showProgressDialog();
            this._mainActivity.runOnUiThread(new LoadUrlRunnable(this._onExitBannerUrl));
            closeOnTimeout(LOADING_TIMEOUT_ON_EXIT);
        }
        this._keepAliveCalled = false;
    }

    public void showUrlInPopup(String url) {
        this.allowRedirects = true;
        this._mainActivity.runOnUiThread(new LoadUrlRunnable(url));
    }

    public void stayAlive() {
        this._keepAliveCalled = true;
        this._isOnScreen = true;
        this._mainActivity.runOnUiThread(new C02045());
        this._handler.postDelayed(this.showBannerRunnable, 2000);
    }

    public void closeBanner() {
        this._isOnScreen = false;
        this.allowRedirects = false;
        ProgressDialogManager.getInstance(this._mainActivity).dismissProgressDialog();
        if (this._mainActivity != null) {
            this._mainActivity.runOnUiThread(new C02056());
        }
        if (this.listener != null) {
            this.listener.onAdClosed();
        }
    }

    public void closeOnTimeout(long timeout) {
        this._handler.postDelayed(this._closeRunnable, timeout);
    }

    public void setClickUrl(String clickUrl) {
        this._clickHandlerUrl = clickUrl;
    }

    public void showAdMobFSBanner(String adUnitID, String keywords, String genderString, String birthday, String latitude, String longtitude) {
        if (this._mainActivity != null) {
            this._mainActivity.runOnUiThread(new C02067(adUnitID, keywords, genderString, birthday, latitude, longtitude));
        }
    }

    public void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this._openInNativeBrowser = openInNativeBrowser;
    }

    public WebView getFullscreenBannerWebView() {
        return this._browser;
    }

    public void setFullScreenBannerListener(IFullScreenBannerListener listener) {
        this.listener = listener;
    }

    public boolean isOnScreen() {
        return this._isOnScreen;
    }

    public void setBackKeyLocked(boolean locked) {
        this._backKeyLocked = locked;
    }

    public boolean isBackKeyLocked() {
        return this._backKeyLocked;
    }

    public void setShowTimer(long ms) {
        if (ms > 0) {
            this._timerDuration = ms;
        }
    }
}
