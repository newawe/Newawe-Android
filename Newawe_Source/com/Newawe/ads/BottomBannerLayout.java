package com.Newawe.ads;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.ads.AdMobUtils.AdMobParameters;
import com.Newawe.ads.AdMobUtils.ParameterizedRequest;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorVisitor;
import com.Newawe.ads.behavior.bannerBehaviors.BannerLayoutBehavior;
import com.Newawe.javascriptinterface.BannerJavascriptInterface;
import com.Newawe.ui.views.BrowserWebView;
import com.Newawe.ui.views.TransparentPanel;
import com.android.volley.DefaultRetryPolicy;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BottomBannerLayout implements BehaviorAcceptor {
    static final int AD_MOB_VIEW_ID = 4660;
    static final int DEFAULT_BANNER_WIDTH = 320;
    static final float UNSET_DENSITY = -1.0f;
    static final float UNSET_PX = -1.0f;
    Activity _activity;
    BrowserWebView _bannerBrowser;
    private int _defaultTabContentsVisibility;
    private int _defaultTagsPanelVisibility;
    float _density;
    int _height;
    private float _px;
    int _width;

    public enum BannerPosition {
        TOP,
        BOTTOM,
        HIDE
    }

    public BottomBannerLayout() {
        this._px = UNSET_PX;
        this._density = UNSET_PX;
        this._defaultTagsPanelVisibility = 0;
        this._defaultTabContentsVisibility = 0;
    }

    public int getDefaultTagsPanelVisibility() {
        return this._defaultTagsPanelVisibility;
    }

    public void setDefaultTagsPanelVisibility(int defaultTagsPanelVisibility) {
        this._defaultTagsPanelVisibility = defaultTagsPanelVisibility;
    }

    public void init(Activity activity, AdsLoader adsLoader) {
        this._activity = activity;
        this._bannerBrowser = (BrowserWebView) activity.findViewById(C0186R.id.banner_webkit);
        this._bannerBrowser.addJavascriptInterface(new BannerJavascriptInterface(this, adsLoader), BannerJavascriptInterface.JS_INTERFACE_NAME);
        if (this._activity instanceof MainNavigationActivity) {
            View tabtagsPanel = this._activity.findViewById(C0186R.id.tabtags_panel);
            View tabContentsPanel = this._activity.findViewById(C0186R.id.tabcontents_panel);
            this._defaultTagsPanelVisibility = tabtagsPanel.getVisibility();
            this._defaultTabContentsVisibility = tabContentsPanel.getVisibility();
            applyDefaultSettings();
        }
    }

    public void applyDefaultSettings() {
        this._bannerBrowser.setVerticalScrollBarEnabled(false);
        this._bannerBrowser.setHorizontalScrollBarEnabled(false);
        WebSettings settings = this._bannerBrowser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        this._width = this._activity.getResources().getInteger(C0186R.integer.bannerWidth);
        this._height = this._activity.getResources().getInteger(C0186R.integer.bannerHeight);
        _applySize();
        setFullscreenMode(false);
    }

    public void setPosition(BannerPosition position) {
        if (position == BannerPosition.BOTTOM) {
            View bannerPanel = this._activity.findViewById(C0186R.id.banner_panel);
            ViewGroup contentFrame = (ViewGroup) this._activity.findViewById(C0186R.id.contentFrame);
            if (contentFrame != null) {
                contentFrame.removeView(bannerPanel);
                contentFrame.addView(bannerPanel, contentFrame.getChildCount());
            }
        }
    }

    public void setWidth(int width) {
        if (width > 0) {
            this._width = width;
            _applySize();
        }
    }

    public void setHeight(int height) {
        if (height > 0) {
            this._height = height;
            _applySize();
        }
    }

    public void setFullscreenMode(boolean isFullScreen) {
        if (isFullScreen) {
            DisplayMetrics dm = new DisplayMetrics();
            this._activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            this._width = dm.widthPixels;
            this._height = dm.heightPixels;
            _applySize();
            _hideViewsForFullscreen();
            return;
        }
        _showViewsForFullscreen();
    }

    protected void _hideViewsForFullscreen() {
        if (this._activity instanceof MainNavigationActivity) {
            View tabtagsPanel = this._activity.findViewById(C0186R.id.tabtags_panel);
            View tabContentsPanel = this._activity.findViewById(C0186R.id.tabcontents_panel);
            this._defaultTagsPanelVisibility = tabtagsPanel.getVisibility();
            this._defaultTabContentsVisibility = tabContentsPanel.getVisibility();
            this._activity.findViewById(C0186R.id.tabtags_panel).setVisibility(8);
            tabContentsPanel.setVisibility(8);
        }
    }

    protected void _applySize() {
        TransparentPanel panel = (TransparentPanel) this._activity.findViewById(C0186R.id.banner_panel);
        DisplayMetrics dm = new DisplayMetrics();
        this._activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (this._px == UNSET_PX) {
            this._px = TypedValue.applyDimension(1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, this._activity.getResources().getDisplayMetrics());
        }
        if (this._density == UNSET_PX && this._activity != null) {
            this._density = this._activity.getApplicationContext().getResources().getDisplayMetrics().density;
        }
        if (this._density < 0.0f) {
            this._density = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        this._width = (int) (this._density * ((float) this._width));
        this._height = (int) (this._density * ((float) this._height));
        if (dm.widthPixels < this._width) {
            panel.getLayoutParams().width = dm.widthPixels;
            panel.getLayoutParams().height = (int) (((((float) (dm.widthPixels - 6)) / ((float) this._width)) * ((float) this._height)) + 6.0f);
            return;
        }
        panel.getLayoutParams().width = this._width + 6;
        panel.getLayoutParams().height = this._height + 6;
    }

    private int getScale() {
        return Double.valueOf(Double.valueOf(Double.valueOf((double) this._width).doubleValue() / Double.valueOf(320.0d).doubleValue()).doubleValue() * 100.0d).intValue();
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        if (visitor instanceof BannerLayoutBehavior) {
            visitor.visit(this);
        }
    }

    public void hideBanner() {
        ((TransparentPanel) this._activity.findViewById(C0186R.id.banner_panel)).setVisibility(8);
        this._bannerBrowser.setWebViewClient(null);
        _showViewsForFullscreen();
    }

    public void showBanner() {
        ((TransparentPanel) this._activity.findViewById(C0186R.id.banner_panel)).setVisibility(0);
        _showViewsForFullscreen();
    }

    protected void _showViewsForFullscreen() {
        if (this._activity instanceof MainNavigationActivity) {
            this._activity.findViewById(C0186R.id.tabtags_panel).setVisibility(this._defaultTagsPanelVisibility);
            this._activity.findViewById(C0186R.id.tabcontents_panel).setVisibility(this._defaultTabContentsVisibility);
        }
    }

    public void switchToHtmlAd() {
        FrameLayout layout = (FrameLayout) this._activity.findViewById(C0186R.id.ad_container);
        View adMobView = this._activity.findViewById(AD_MOB_VIEW_ID);
        if (adMobView != null) {
            layout.removeView(adMobView);
        }
        this._activity.findViewById(C0186R.id.banner_webkit).setVisibility(0);
    }

    public void switchToAdMobAd(AdMobParameters adMobParameters) {
        this._activity.findViewById(C0186R.id.banner_webkit).setVisibility(8);
        AdView adView = _replaceAdMobView(adMobParameters.getPublisherId());
        adView.setVisibility(0);
        adView.loadAd(new ParameterizedRequest(adMobParameters).getRequest());
    }

    public WebView getBannerWebView() {
        return this._bannerBrowser;
    }

    private AdView _replaceAdMobView(String publisherId) {
        FrameLayout layout = (FrameLayout) this._activity.findViewById(C0186R.id.ad_container);
        layout.removeView(this._activity.findViewById(AD_MOB_VIEW_ID));
        AdView view = new AdView(this._activity);
        view.setAdUnitId(publisherId);
        view.setAdSize(AdSize.BANNER);
        view.setId(AD_MOB_VIEW_ID);
        layout.addView(view);
        return view;
    }
}
