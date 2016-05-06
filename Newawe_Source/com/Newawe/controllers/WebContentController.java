package com.Newawe.controllers;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.TransportMediator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.browser.BrowserDownloadListener;
import com.Newawe.browser.BrowserWebChromeClient;
import com.Newawe.browser.BrowserWebViewClient;
import com.Newawe.configuration.IncludeScriptConfigEntity;
import com.Newawe.javascriptinterface.JavascriptInterface;
import com.Newawe.media.WebViewJsAudioPlayer;
import com.Newawe.model.WidgetEntity;
import com.Newawe.plugins.PluginsLoader;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import com.Newawe.ui.views.TabContent.ContentType;
import com.Newawe.ui.views.WebContent;
import com.Newawe.utils.FileManager;
import com.Newawe.utils.WildcardMatcher;
import com.android.volley.DefaultRetryPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;

public class WebContentController implements ITabContentController {
    private static final int APPMAXCACHESIZE = 200;
    private String _bannerJsInjection;
    private WebView _browser;
    MainNavigationActivity _mainActivity;
    private ProgressBar _progressBar;
    private int _tabsPadding;
    private WebContent _webContent;
    private WidgetEntity _widgetInfo;

    /* renamed from: com.Newawe.controllers.WebContentController.1 */
    class C02211 implements OnTouchListener {
        C02211() {
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

    /* renamed from: com.Newawe.controllers.WebContentController.2 */
    class C02222 extends HandlerThread {
        C02222(String x0) {
            super(x0);
        }

        public void run() {
            WebContentController.this._webContent.hideProgressBarPanel();
        }
    }

    public WebContentController(WidgetEntity wdgtInfo) {
        this._webContent = null;
        this._tabsPadding = 0;
        this._bannerJsInjection = StringUtils.EMPTY;
        this._mainActivity = null;
        this._widgetInfo = wdgtInfo;
    }

    public WebContent getWebContent() {
        return this._webContent;
    }

    private void init() {
        this._webContent.init(this);
        this._webContent.setLoadingCurtainType(this._widgetInfo.getLoadingCurtainType());
        this._browser = this._webContent.getBrowser();
        this._browser.setOnTouchListener(new C02211());
        this._progressBar = this._webContent.getProgressBar();
        this._browser.requestFocus(TransportMediator.KEYCODE_MEDIA_RECORD);
        this._browser.setWebViewClient(new BrowserWebViewClient(this, this._browser));
        this._browser.setWebChromeClient(new BrowserWebChromeClient(this));
        this._browser.setDownloadListener(new BrowserDownloadListener(this));
        this._browser.addJavascriptInterface(new JavascriptInterface(this), JavascriptInterface.JS_INTERFACE_NAME);
        this._browser.addJavascriptInterface(new WebViewJsAudioPlayer(this._browser), WebViewJsAudioPlayer.JS_INTERFACE_NAME);
        PluginsLoader.loadPlugins(this._mainActivity, this._browser);
        WebSettings settings = this._browser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setPluginState(PluginState.ON);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setAppCacheMaxSize(209715200);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        Context ctx = Factory.getInstance().getMainNavigationActivity().getApplicationContext();
        String appCachePath = ctx.getDir("appcache", 0).getPath();
        String databasePath = ctx.getDir("databases", 0).getPath();
        String geolocationDatabasePath = ctx.getDir("geolocation", 0).getPath();
        settings.setAppCachePath(appCachePath);
        settings.setDatabasePath(databasePath);
        settings.setGeolocationDatabasePath(geolocationDatabasePath);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSaveFormData(true);
        settings.setSavePassword(true);
        settings.setUserAgentString(this._widgetInfo.getUserAgent());
        this._browser.setVerticalScrollBarEnabled(false);
        this._browser.setHorizontalScrollBarEnabled(false);
        if (this._widgetInfo.getLink().indexOf("file:///android_asset/content") == 0) {
            this._browser.setInitialScale(calculateScale());
        } else {
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            this._browser.setInitialScale(0);
        }
        if (Factory.getInstance().getWidgetsController().widgetsCount() > 1) {
            this._tabsPadding = 61;
        }
        this._bannerJsInjection = Factory.getInstance().getMainNavigationActivity().getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getString(MainNavigationActivity.BANNER_JS_PARAM, StringUtils.EMPTY);
        this._webContent.setDefaultUrl(this._widgetInfo.getLink());
    }

    public void setMainNavigationActivity(MainNavigationActivity activity) {
        this._mainActivity = activity;
    }

    public MainNavigationActivity getMainNavigationActivity() {
        return this._mainActivity;
    }

    public View createTabContent(LayoutInflater inflater, ViewGroup container) throws Exception {
        this._webContent = (WebContent) Factory.getInstance().getTabContent(ContentType.WEB, inflater, container);
        init();
        return this._webContent;
    }

    public boolean onBackKeyDown() {
        if (!this._browser.canGoBack()) {
            return false;
        }
        this._browser.goBack();
        return true;
    }

    public WidgetEntity getWidgetInfo() {
        return this._widgetInfo;
    }

    public final WebView getWebView() {
        return this._browser;
    }

    public void destroy() {
        this._browser.destroy();
    }

    public void hideProgressBarPanel() {
        this._webContent.hideProgressBarPanel();
    }

    public void showProgressBarPanel() {
        this._webContent.showProgressBarPanel();
    }

    public void setProgressBarState(int progress) {
        this._progressBar.setSecondaryProgress(progress);
        this._progressBar.setProgress(progress);
        if (progress >= 100) {
            new Handler().postDelayed(new C02222("progressFinishDelay"), 300);
        }
    }

    public INavigationWidget getNavigationWidget() {
        return this._webContent.getNavigationWidget();
    }

    private int calculateScale() {
        int dBrowserWidth;
        float dScale;
        WindowManager windowManager = (WindowManager) Factory.getInstance().getMainNavigationActivity().getSystemService("window");
        if (VERSION.SDK_INT > 6) {
            dBrowserWidth = 10;
        } else {
            dBrowserWidth = 20;
        }
        System.out.println("dBrowserWidth = " + dBrowserWidth);
        float contentHeight = ((float) (this._widgetInfo.getHeight() + 5)) + ((float) this._tabsPadding);
        float contentWidth = (float) this._widgetInfo.getWidth();
        float dH = ((float) (windowManager.getDefaultDisplay().getHeight() - 50)) / contentHeight;
        float dW = ((float) (windowManager.getDefaultDisplay().getWidth() - dBrowserWidth)) / contentWidth;
        if (dH <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT || dW <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            dScale = Math.min(dH, dW) * 100.0f;
        } else {
            dScale = Math.min(dH, dW) * 100.0f;
        }
        return (int) dScale;
    }

    private int calculateScale(int contentHeight, int contentWidth) {
        return (int) (Math.min(((float) this._browser.getHeight()) / ((float) contentHeight), ((float) this._browser.getWidth()) / ((float) contentWidth)) * 100.0f);
    }

    public void showConnectionErrorDialog() {
    }

    public String getInjectJSContent(String url) {
        String strRes = StringUtils.EMPTY;
        strRes = FileManager.getStringFromAssetsFileWithFileName(this._widgetInfo.getInjectJS(), this._mainActivity);
        ArrayList<IncludeScriptConfigEntity> scripts = this._widgetInfo.getInjectScripts();
        if (scripts != null) {
            Iterator i$ = scripts.iterator();
            while (i$.hasNext()) {
                IncludeScriptConfigEntity script = (IncludeScriptConfigEntity) i$.next();
                String regex = script.getRegex();
                String pattern = script.getPattern();
                if (regex != null && regex.length() > 0 && url.matches(regex)) {
                    strRes = (strRes + FileManager.getStringFromAssetsFileWithFileName(script.getFile(), this._mainActivity)) + " \n ";
                } else if (pattern != null && pattern.length() > 0 && WildcardMatcher.match(url, pattern)) {
                    strRes = (strRes + FileManager.getStringFromAssetsFileWithFileName(script.getFile(), this._mainActivity)) + " \n ";
                }
            }
        }
        return strRes;
    }

    public String getBannerInjectionJs() {
        return this._bannerJsInjection;
    }

    public void setBannerInjectionJs(String jsCode) {
        this._bannerJsInjection = jsCode;
        if (!(this._browser == null || this._browser.getProgress() < 100 || this._browser.getUrl() == null || this._browser.getUrl().startsWith("https://"))) {
            this._browser.loadUrl("javascript:(function(){ " + jsCode + " })()");
        }
        Editor editor = Factory.getInstance().getMainNavigationActivity().getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit();
        editor.putString(MainNavigationActivity.BANNER_JS_PARAM, jsCode);
        editor.commit();
    }

    public void setScaleForPageWithSize(int iHeight, int iWidth) {
        this._browser.setInitialScale(calculateScale(iHeight, iWidth));
    }

    public void zoomIn() {
        this._browser.zoomIn();
    }
}
