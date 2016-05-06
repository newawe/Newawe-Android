package com.Newawe.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;

public class BrowserWebView extends WebView {
    private boolean _firstUrl;
    private INavigationWidget _navigationWidget;

    public BrowserWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._firstUrl = true;
        this._navigationWidget = null;
    }

    private void init() {
        this._navigationWidget = Factory.getInstance().getNavigationWidget();
        this._firstUrl = false;
    }

    public void loadUrl(String url) {
        if (this._firstUrl) {
            init();
        }
        if (!(this._navigationWidget == null || !this._navigationWidget.isVisible() || URLUtil.isValidUrl(url))) {
            if (("http://" + url).matches("(news|(ht|f)tp(s?)\\://){1}[\\S\\.]+\\.[\\S\\.]+")) {
                url = "http://" + url;
            } else {
                try {
                    url = getResources().getString(C0186R.string.searchUrl) + URLEncoder.encode(url, HTTP.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!url.contains("javascript:(function(){  })()")) {
            super.loadUrl(url);
        }
    }
}
