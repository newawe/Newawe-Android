package com.Newawe.notification;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.Newawe.MainNavigationActivity;
import com.Newawe.javascriptinterface.BaseBrowserJavascriptInterface;
import com.Newawe.utils.UrlConverter;
import org.apache.commons.lang.StringUtils;

public class NotificationJavascriptInterface extends BaseBrowserJavascriptInterface {
    public static final String JS_INTERFACE_NAME = "NotificationInterface";
    private Context _context;
    private NotificationService _service;

    /* renamed from: com.Newawe.notification.NotificationJavascriptInterface.1 */
    class C02471 implements Runnable {
        final /* synthetic */ String val$iconUrl;
        final /* synthetic */ String val$imageUrl;
        final /* synthetic */ Handler val$jsThreadHandler;
        final /* synthetic */ String val$launchMain;
        final /* synthetic */ String val$text;
        final /* synthetic */ String val$title;
        final /* synthetic */ String val$url;

        /* renamed from: com.Newawe.notification.NotificationJavascriptInterface.1.1 */
        class C02461 implements Runnable {
            final /* synthetic */ String val$absoluteIconUrl;
            final /* synthetic */ String val$absoluteImageUrl;
            final /* synthetic */ String val$absoluteUrl;

            C02461(String str, String str2, String str3) {
                this.val$absoluteUrl = str;
                this.val$absoluteIconUrl = str2;
                this.val$absoluteImageUrl = str3;
            }

            public void run() {
                AppNotificationManager.generateNotification(NotificationJavascriptInterface.this._context, C02471.this.val$text, C02471.this.val$title, AppNotificationManager.getLaunchIntent(NotificationJavascriptInterface.this._context, C02471.this.val$title, this.val$absoluteUrl, C02471.this.val$launchMain), this.val$absoluteIconUrl, this.val$absoluteImageUrl);
            }
        }

        C02471(String str, String str2, String str3, Handler handler, String str4, String str5, String str6) {
            this.val$url = str;
            this.val$iconUrl = str2;
            this.val$imageUrl = str3;
            this.val$jsThreadHandler = handler;
            this.val$title = str4;
            this.val$launchMain = str5;
            this.val$text = str6;
        }

        public void run() {
            UrlConverter converter = new UrlConverter(NotificationJavascriptInterface.this._service.getWebView());
            this.val$jsThreadHandler.post(new C02461(NotificationJavascriptInterface.this._toAbsoluteUrlPreserveEmpty(this.val$url, converter), NotificationJavascriptInterface.this._toAbsoluteUrlPreserveEmpty(this.val$iconUrl, converter), NotificationJavascriptInterface.this._toAbsoluteUrlPreserveEmpty(this.val$imageUrl, converter)));
        }
    }

    public NotificationJavascriptInterface(Context context, NotificationService service) {
        super(context, service.getWebView(), service.getWebViewThreadHandler());
        this._context = context;
        this._service = service;
    }

    private String _toAbsoluteUrlPreserveEmpty(String url, UrlConverter converter) {
        if (url == null || url.length() == 0) {
            return StringUtils.EMPTY;
        }
        return converter.toAbsolute(url);
    }

    @JavascriptInterface
    public void showNotification(String title, String text, String url, String launchMain, String iconUrl, String imageUrl) {
        this._service.getWebViewThreadHandler().post(new C02471(url, iconUrl, imageUrl, new Handler(), title, launchMain, text));
    }

    @JavascriptInterface
    public void keepAlive() {
        this._service.keepAlive();
    }

    @JavascriptInterface
    public void close() {
        this._service.removeWebViewAndStop();
    }

    @JavascriptInterface
    public boolean isAppRunning() {
        return MainNavigationActivity.isActive();
    }
}
