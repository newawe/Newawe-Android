package com.Newawe.ui.views;

import android.content.Context;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.configuration.UrlBarMenuButton;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStates;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStyles;
import com.Newawe.controllers.ITabContentController;
import com.Newawe.model.WidgetEntity.LoadingCurtainType;
import com.Newawe.ui.navigationwidget.BottomNavigationWidget;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import com.Newawe.ui.navigationwidget.NavigationWidget;
import com.Newawe.ui.navigationwidget.TopNavigationWidget;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;

public class WebContent extends TabContent {
    public static volatile boolean firstTab;
    private BrowserWebView _browser;
    private String _defaultUrl;
    private boolean _initialiaed;
    private INavigationWidget _navigationWidget;
    private ProgressBar _progressBar;
    private ITabContentController _tabContentController;
    boolean firstStart;

    static {
        firstTab = true;
    }

    public WebContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._tabContentController = null;
        this._browser = null;
        this._progressBar = null;
        this._navigationWidget = null;
        this._defaultUrl = StringUtils.EMPTY;
        this.firstStart = true;
        this._initialiaed = false;
    }

    public WebContent(Context context) {
        super(context);
        this._tabContentController = null;
        this._browser = null;
        this._progressBar = null;
        this._navigationWidget = null;
        this._defaultUrl = StringUtils.EMPTY;
        this.firstStart = true;
        this._initialiaed = false;
    }

    public void init(ITabContentController tabContentController) {
        this._tabContentController = tabContentController;
        this._browser = (BrowserWebView) findViewById(C0186R.id.webView);
        this._browser.requestFocus(TransportMediator.KEYCODE_MEDIA_RECORD);
        this._progressBar = (ProgressBar) findViewById(C0186R.id.progressbar);
        WebWidgetConfiguration config = this._tabContentController.getMainNavigationActivity().getConfig();
        UrlBarStates urlBarState = config.getUrlOverlayState();
        if (urlBarState != UrlBarStates.DISABLED) {
            boolean z;
            String link = this._tabContentController.getWidgetInfo().getLink();
            Collection<UrlBarMenuButton> urlBarButtons = config.getUrlBarMenuButtons();
            if (config.getUrlBarStyle() == UrlBarStyles.TOP) {
                this._navigationWidget = new TopNavigationWidget(this, link, this._browser, urlBarButtons);
                ((NavigationWidget) this._navigationWidget).initHistory();
            } else {
                this._navigationWidget = new BottomNavigationWidget(this, link, this._browser, urlBarButtons);
                this._navigationWidget.attachAutocomplete();
            }
            INavigationWidget iNavigationWidget = this._navigationWidget;
            if (urlBarState == UrlBarStates.ENABLED_ON_EXTERNAL_URLS) {
                z = true;
            } else {
                z = false;
            }
            iNavigationWidget.setHideOnInternalUrls(z);
            Factory.getInstance().setNavigationWidget(this._navigationWidget);
            return;
        }
        ((LayoutParams) this._browser.getLayoutParams()).setMargins(0, 0, 0, 0);
    }

    public void setDefaultUrl(String url) {
        if (firstTab) {
            this._browser.loadUrl(url);
            firstTab = false;
        }
        this._defaultUrl = url;
        if (this._navigationWidget != null) {
            this._navigationWidget.setUrl(url);
        }
        this._progressBar.setProgress(0);
    }

    public INavigationWidget getNavigationWidget() {
        return this._navigationWidget;
    }

    public WebView getBrowser() {
        return this._browser;
    }

    public void loadDefaultUrl() {
        this._browser.loadUrl(this._defaultUrl);
        this._initialiaed = true;
    }

    public String getDefaultUrl() {
        return this._defaultUrl;
    }

    public ProgressBar getProgressBar() {
        return this._progressBar;
    }

    public void hideProgressBarPanel() {
        findViewById(C0186R.id.progressbarPanel).setVisibility(8);
    }

    public void showProgressBarPanel() {
        findViewById(C0186R.id.progressbarPanel).setVisibility(0);
    }

    public void setLoadingCurtainType(LoadingCurtainType type) {
        if (type == LoadingCurtainType.NONE) {
            findViewById(C0186R.id.loadingCurtainDefault).setVisibility(8);
        } else if (type == LoadingCurtainType.DEFAULT) {
            findViewById(C0186R.id.loadingCurtainDefault).setVisibility(0);
        }
    }

    public boolean isInitialized() {
        return this._initialiaed;
    }
}
