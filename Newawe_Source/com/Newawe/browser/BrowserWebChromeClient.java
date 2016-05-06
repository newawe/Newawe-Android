package com.Newawe.browser;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import com.Newawe.Factory;
import com.Newawe.controllers.WebContentController;
import com.Newawe.ui.dialog.SimpleDialogs;
import org.apache.commons.lang.StringUtils;

public class BrowserWebChromeClient extends WebChromeClient {
    public static final String WEB_VIEW_LOG_PREFIX = "webConsoleMessage";
    private WebContentController _webController;

    /* renamed from: com.Newawe.browser.BrowserWebChromeClient.1 */
    class C02151 implements OnClickListener {
        final /* synthetic */ JsResult val$result;

        C02151(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.confirm();
        }
    }

    /* renamed from: com.Newawe.browser.BrowserWebChromeClient.2 */
    class C02162 implements OnClickListener {
        final /* synthetic */ JsResult val$result;

        C02162(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.cancel();
        }
    }

    public BrowserWebChromeClient(WebContentController webController) {
        this._webController = webController;
    }

    public void onProgressChanged(WebView view, int progress) {
        this._webController.setProgressBarState(progress);
    }

    public View getVideoLoadingProgressView() {
        return Factory.getInstance().getMainNavigationActivity().getVideoLoadingProgressView();
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        Factory.getInstance().getMainNavigationActivity().onShowCustomView(view, callback);
    }

    public void onHideCustomView() {
        super.onHideCustomView();
        Factory.getInstance().getMainNavigationActivity().onHideCustomView();
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        Factory.getInstance().getMainNavigationActivity().openFileChooser(uploadMsg, acceptType);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, StringUtils.EMPTY);
    }

    public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, true);
    }

    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        SimpleDialogs.createConfirmDialog(Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName(), message, view.getContext(), new C02151(result), new C02162(result)).show();
        return true;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(WEB_VIEW_LOG_PREFIX, String.format("%s @ %d: %s", new Object[]{consoleMessage.message(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.sourceId()}));
        return true;
    }
}
