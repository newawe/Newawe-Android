package com.Newawe.browser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.DefaultUrlsHolder;
import com.Newawe.configuration.WebWidgetConfiguration.RedirectionTypes;
import com.Newawe.controllers.WebContentController;
import com.Newawe.ui.dialog.SslErrorDialog;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import com.Newawe.utils.JavaScriptInjector;

public class BrowserWebViewClient extends SimpleWebViewClient {
    private RedirectionTypes _isRedirectEnabled;
    private JavaScriptInjector _javaScriptInjector;
    private MainNavigationActivity _mainActivity;
    private WebContentController _webContentController;

    public BrowserWebViewClient(WebContentController webContentController, WebView view) {
        super(Factory.getInstance().getMainNavigationActivity());
        this._javaScriptInjector = null;
        this._isRedirectEnabled = RedirectionTypes.NO_REDIRECT;
        this._mainActivity = Factory.getInstance().getMainNavigationActivity();
        this._webContentController = webContentController;
        DefaultUrlsHolder.getInstance().addUrl(webContentController.getWidgetInfo().getLink());
        this._isRedirectEnabled = this._mainActivity.getConfig().getIsRedirectEnabled();
        this._context = this._mainActivity;
    }

    public void onPageFinished(WebView view, String url) {
        if (url.equals(view.getUrl())) {
            view.loadUrl("javascript:(function(){ " + this._webContentController.getInjectJSContent(url) + " })()");
            if (!url.startsWith("https://")) {
                view.loadUrl("javascript:(function(){ " + this._webContentController.getBannerInjectionJs() + " })()");
            }
        }
        view.getSettings().setBlockNetworkImage(false);
        super.onPageFinished(view, url);
        if (this._isRedirectEnabled == RedirectionTypes.REDIRECT_ALL) {
            WebContentController tabController = Factory.getInstance().getTabsController().getSelectedTab();
            if (tabController != null) {
                INavigationWidget navigationWidget = tabController.getNavigationWidget();
                if (navigationWidget != null) {
                    navigationWidget.onPageFinished(view, url);
                }
            }
        }
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.getSettings().setBlockNetworkImage(true);
        if ((this._isRedirectEnabled != RedirectionTypes.NO_REDIRECT || DefaultUrlsHolder.getInstance().isDefaultUrl(url)) && (this._isRedirectEnabled != RedirectionTypes.REDIRECT_EXTERNAL || DefaultUrlsHolder.getInstance().isPermittedDomain(url))) {
            view.getSettings().setBlockNetworkImage(false);
            this._webContentController.showProgressBarPanel();
            INavigationWidget navigationWidget = this._webContentController.getNavigationWidget();
            if (navigationWidget != null) {
                navigationWidget.onPageStart(view, url);
            }
            if (!url.contains("file:///android_asset/content")) {
                WebSettings settings = view.getSettings();
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                view.setInitialScale(0);
            }
            super.onPageStarted(view, url, favicon);
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            view.stopLoading();
            if (this._mainActivity.getPackageManager().resolveActivity(intent, 0) != null) {
                this._mainActivity.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Log.e("ANFE", "onPageStarted() :" + e.getMessage());
        }
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        String url = view.getUrl();
        INavigationWidget navigationWidget = null;
        if (this._isRedirectEnabled == RedirectionTypes.REDIRECT_ALL) {
            WebContentController tabController = Factory.getInstance().getTabsController().getSelectedTab();
            if (tabController != null) {
                navigationWidget = tabController.getNavigationWidget();
            }
        }
        if (url != null && url.equalsIgnoreCase(failingUrl) && navigationWidget == null) {
            view.loadUrl(this._mainActivity.getString(C0186R.string.errorHtmlPath));
        }
        if (errorCode == -8) {
            this._mainActivity.showConnectionErrorDialog();
        }
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        new SslErrorDialog(this._context).execute(view, handler, error);
    }

    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        String username = null;
        String password = null;
        if (handler.useHttpAuthUsernamePassword() && view != null) {
            String[] credentials = view.getHttpAuthUsernamePassword(host, realm);
            if (credentials != null && credentials.length == 2) {
                username = credentials[0];
                password = credentials[1];
            }
        }
        if (username == null || password == null) {
            this._mainActivity.showHttpAuthentication(this._webContentController.getWebView(), handler, host, realm, null, null, null, 0);
        } else {
            handler.proceed(username, password);
        }
    }
}
