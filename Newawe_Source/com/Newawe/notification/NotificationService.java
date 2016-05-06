package com.Newawe.notification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class NotificationService extends Service {
    private static final int WEB_VIEW_KEEP_ALIVE_WAIT = 5000;
    private static final int WEB_VIEW_TTL = 300000;
    private LinearLayout _injectView;
    private Handler _keepAliveWaitHandler;
    private Handler _removeWebViewHandler;
    private WakeLock _wakeLock;
    private WebView _webView;
    private Handler _webViewThreadHandler;
    private WifiLock _wifiLock;
    private WindowManager _windowManager;
    private Runnable removeWebViewAndStop;

    /* renamed from: com.Newawe.notification.NotificationService.1 */
    class C02481 implements Runnable {
        C02481() {
        }

        public void run() {
            if (!(NotificationService.this._windowManager == null || NotificationService.this._injectView == null)) {
                try {
                    NotificationService.this._windowManager.removeViewImmediate(NotificationService.this._injectView);
                    NotificationService.this._webView.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (NotificationService.this._wakeLock.isHeld()) {
                        NotificationService.this._wakeLock.release();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    if (NotificationService.this._wifiLock.isHeld()) {
                        NotificationService.this._wifiLock.release();
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
            NotificationService.this.stopSelf();
        }
    }

    public NotificationService() {
        this._webViewThreadHandler = new Handler();
        this.removeWebViewAndStop = new C02481();
        this._removeWebViewHandler = new Handler();
        this._keepAliveWaitHandler = new Handler();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Broadcast received", "WBVSRVCE");
        String _url = intent.getExtras().getString(NotificationChecker.CHECKER_URL_KEY);
        WebView wv = _createWebView(this);
        this._removeWebViewHandler.postDelayed(this.removeWebViewAndStop, 300000);
        this._keepAliveWaitHandler.postDelayed(this.removeWebViewAndStop, 5000);
        Log.d("WebView created", "WBVSRVCE");
        wv.loadUrl(_url);
        Log.d("Loading check page", "WBVSRVCE");
        this._wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, "notificationWakeLock");
        if (!this._wakeLock.isHeld()) {
            this._wakeLock.acquire();
        }
        this._wifiLock = ((WifiManager) getSystemService("wifi")).createWifiLock(1, "MyWifiLock");
        if (!this._wifiLock.isHeld()) {
            this._wifiLock.acquire();
        }
        return 2;
    }

    private WebView _createWebView(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this._windowManager = windowManager;
        LayoutParams params = new LayoutParams(-2, -2, 2006, 16, -3);
        params.gravity = 51;
        params.x = 0;
        params.y = 0;
        params.width = 0;
        params.height = 0;
        LinearLayout view = new LinearLayout(context);
        this._injectView = view;
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this._webView = new WebView(context);
        this._webView.getSettings().setJavaScriptEnabled(true);
        this._webView.addJavascriptInterface(new NotificationJavascriptInterface(context, this), NotificationJavascriptInterface.JS_INTERFACE_NAME);
        this._webView.setWebViewClient(new NotificationWebViewClient(context));
        this._webView.setWebChromeClient(new NotificationWebChromeClient());
        this._webView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        view.addView(this._webView);
        windowManager.addView(this._injectView, params);
        return this._webView;
    }

    public void removeWebViewAndStop() {
        this._removeWebViewHandler.post(this.removeWebViewAndStop);
    }

    public WebView getWebView() {
        return this._webView;
    }

    public Handler getWebViewThreadHandler() {
        return this._webViewThreadHandler;
    }

    public void keepAlive() {
        this._keepAliveWaitHandler.removeCallbacks(this.removeWebViewAndStop);
    }
}
