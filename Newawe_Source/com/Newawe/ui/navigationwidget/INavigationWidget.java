package com.Newawe.ui.navigationwidget;

import android.webkit.WebView;
import java.util.HashMap;

public interface INavigationWidget {
    void attachAutocomplete();

    HashMap<String, NavigationWidgetCustomIcon> getCustomIcons();

    boolean isVisible();

    void onPageFinished(WebView webView, String str);

    void onPageStart(WebView webView, String str);

    void setHideOnInternalUrls(boolean z);

    void setUrl(String str);
}
