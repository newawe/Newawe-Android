package com.Newawe.utils;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.Newawe.controllers.WebContentController;

public class JavaScriptInjector {
    public static final String JS_INTERFACE_NAME = "AppsgeyserJSInjectorInterface";
    private final long INJECT_TRIES_INTERVAL;
    public final String JS_INJECTION_PREFIX;
    public final String JS_INJECTION_SUFFIX;
    private Handler _handler;
    private final WebContentController _webContentController;
    private final WebView _webView;
    private final StoppableRunnable injectContentRunnable;

    private abstract class StoppableRunnable implements Runnable {
        public boolean stop;

        private StoppableRunnable() {
            this.stop = false;
        }
    }

    /* renamed from: com.Newawe.utils.JavaScriptInjector.1 */
    class C09961 extends StoppableRunnable {
        C09961() {
            super(null);
        }

        public void run() {
            if (!this.stop) {
                String content = JavaScriptInjector.this._webContentController.getInjectJSContent(JavaScriptInjector.this._webView.getUrl());
                String bannerContent = JavaScriptInjector.this._webContentController.getBannerInjectionJs();
                JavaScriptInjector.this._webView.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage){" + content + "}" + " })()");
                JavaScriptInjector.this._webView.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage){" + bannerContent + "}" + " })()");
                JavaScriptInjector.this._webView.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage) { AppsgeyserJSInjectorInterface.injectedSuccessfully(); window.jsInjectionDoneOnThisPage = true; } })()");
                JavaScriptInjector.this._handler.postDelayed(this, 200);
            }
        }
    }

    public JavaScriptInjector(WebView webView, WebContentController webContentController) {
        this.JS_INJECTION_PREFIX = "javascript:(function(){ ";
        this.JS_INJECTION_SUFFIX = " })()";
        this.INJECT_TRIES_INTERVAL = 200;
        this._handler = new Handler();
        this.injectContentRunnable = new C09961();
        this._webView = webView;
        this._webContentController = webContentController;
        this._webView.addJavascriptInterface(this, JS_INTERFACE_NAME);
    }

    public void InjectJavaScript() {
    }

    @JavascriptInterface
    public void injectedSuccessfully() {
        this._handler.removeCallbacks(this.injectContentRunnable);
        this.injectContentRunnable.stop = true;
    }
}
