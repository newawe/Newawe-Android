package com.startapp.android.publish.banner.bannerstandard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.Ad.AdState;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.BannerInterface;
import com.startapp.android.publish.JsInterface;
import com.startapp.android.publish.banner.Banner;
import com.startapp.android.publish.banner.BannerListener;
import com.startapp.android.publish.banner.BannerOptions;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class BannerStandard extends com.startapp.android.publish.banner.StartAppSDK implements AdEventListener, BannerInterface {
    private static final int ID_WEBVIEW = 159868225;
    private static final String TAG = "BannerHtml";
    private static final int TIMEOUT_SMART_REDIRECT = 5000;
    private com.startapp.android.publish.p028a.StartAppSDK adHtml;
    private RelativeLayout adInformationContatiner;
    private com.startapp.android.publish.adinformation.StartAppSDK adInformationLayout;
    private AdPreferences adPreferences;
    private boolean callbackSent;
    private boolean defaultLoad;
    private boolean initBannerCalled;
    private BannerListener listener;
    private boolean loaded;
    private BannerOptions options;
    private String prevAdId;
    private boolean sentImpression;
    private com.startapp.android.publish.banner.StartAppSDK size;
    private boolean visible;
    private WebView webView;

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.1 */
    class StartAppSDK extends WebView {
        StartAppSDK(Context x0) {
            super(x0);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            BannerStandard.this.makeImpression();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.2 */
    class StartAppSDK implements OnTouchListener {
        StartAppSDK() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return event.getAction() == 2;
        }
    }

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.3 */
    class StartAppSDK implements OnLongClickListener {
        StartAppSDK() {
        }

        public boolean onLongClick(View v) {
            return true;
        }
    }

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.4 */
    class StartAppSDK implements OnGlobalLayoutListener {
        StartAppSDK() {
        }

        public void onGlobalLayout() {
            com.startapp.android.publish.p022h.StartAppSDK.m2869a(BannerStandard.this.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            BannerStandard.this.setHardwareAcceleration(BannerStandard.this.adPreferences);
            BannerStandard.this.initBanner();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.5 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            BannerStandard.this.initBanner();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.bannerstandard.BannerStandard.6 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
        }
    }

    /* compiled from: StartAppSDK */
    private class MyWebViewClient extends WebViewClient {
        private boolean callbackSent;

        private MyWebViewClient() {
            this.callbackSent = false;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!this.callbackSent) {
                this.callbackSent = true;
                if (BannerStandard.this.listener != null) {
                    BannerStandard.this.listener.onClick(BannerStandard.this);
                }
            }
            if (url.contains("index=")) {
                try {
                    int a = com.startapp.android.publish.p022h.StartAppSDK.m2986a(url);
                    if (BannerStandard.this.adHtml.getSmartRedirect(a)) {
                        String str;
                        String str2;
                        Context context = BannerStandard.this.getContext();
                        if (a < BannerStandard.this.adHtml.getTrackingClickUrls().length) {
                            str = BannerStandard.this.adHtml.getTrackingClickUrls()[a];
                        } else {
                            str = null;
                        }
                        if (a < BannerStandard.this.adHtml.getPackageNames().length) {
                            str2 = BannerStandard.this.adHtml.getPackageNames()[a];
                        } else {
                            str2 = null;
                        }
                        com.startapp.android.publish.p022h.StartAppSDK.m3001a(context, url, str, str2, new com.startapp.android.publish.p022h.StartAppSDK(BannerStandard.this.getAdTag()), 5000, BannerStandard.this.adHtml.isInAppBrowserEnabled());
                    } else {
                        String str3;
                        Context context2 = BannerStandard.this.getContext();
                        if (a < BannerStandard.this.adHtml.getTrackingClickUrls().length) {
                            str3 = BannerStandard.this.adHtml.getTrackingClickUrls()[a];
                        } else {
                            str3 = null;
                        }
                        com.startapp.android.publish.p022h.StartAppSDK.m2999a(context2, url, str3, new com.startapp.android.publish.p022h.StartAppSDK(BannerStandard.this.getAdTag()), BannerStandard.this.adHtml.isInAppBrowserEnabled());
                    }
                } catch (Exception e) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a(BannerStandard.TAG, 6, "Error while trying parsing index from url");
                    return false;
                }
            } else if (BannerStandard.this.adHtml.getSmartRedirect(0)) {
                com.startapp.android.publish.p022h.StartAppSDK.m3001a(BannerStandard.this.getContext(), url, BannerStandard.this.adHtml.getTrackingClickUrls()[0], BannerStandard.this.adHtml.getPackageNames()[0], new com.startapp.android.publish.p022h.StartAppSDK(BannerStandard.this.getAdTag()), 5000, BannerStandard.this.adHtml.isInAppBrowserEnabled());
            } else {
                com.startapp.android.publish.p022h.StartAppSDK.m2999a(BannerStandard.this.getContext(), url, BannerStandard.this.adHtml.getTrackingClickUrls()[0], new com.startapp.android.publish.p022h.StartAppSDK(BannerStandard.this.getAdTag()), BannerStandard.this.adHtml.isInAppBrowserEnabled());
            }
            BannerStandard.this.webView.stopLoading();
            BannerStandard.this.setClicked(true);
            return true;
        }
    }

    public BannerStandard(Context context) {
        this(context, true, null);
    }

    public BannerStandard(Context context, AdPreferences adPreferences) {
        this(context, true, adPreferences);
    }

    public BannerStandard(Context context, BannerListener listener) {
        this(context, true, null);
        setBannerListener(listener);
    }

    public BannerStandard(Context context, AdPreferences adPreferences, BannerListener listener) {
        this(context, true, adPreferences);
        setBannerListener(listener);
    }

    public BannerStandard(Context context, boolean defaultLoad) {
        this(context, defaultLoad, null);
    }

    public BannerStandard(Context context, boolean defaultLoad, AdPreferences adPreferences) {
        super(context);
        this.prevAdId = StringUtils.EMPTY;
        this.loaded = false;
        this.defaultLoad = true;
        this.visible = true;
        this.sentImpression = false;
        this.initBannerCalled = false;
        this.callbackSent = false;
        this.adInformationLayout = null;
        this.adInformationContatiner = null;
        this.defaultLoad = defaultLoad;
        this.adPreferences = adPreferences;
        init();
    }

    public BannerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.prevAdId = StringUtils.EMPTY;
        this.loaded = false;
        this.defaultLoad = true;
        this.visible = true;
        this.sentImpression = false;
        this.initBannerCalled = false;
        this.callbackSent = false;
        this.adInformationLayout = null;
        this.adInformationContatiner = null;
        init();
    }

    public BannerStandard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.prevAdId = StringUtils.EMPTY;
        this.loaded = false;
        this.defaultLoad = true;
        this.visible = true;
        this.sentImpression = false;
        this.initBannerCalled = false;
        this.callbackSent = false;
        this.adInformationLayout = null;
        this.adInformationContatiner = null;
        init();
    }

    private void addAdInformationLayout() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        layoutParams.addRule(13);
        if (this.adInformationLayout == null && this.adInformationContatiner == null) {
            this.adInformationContatiner = new RelativeLayout(getContext());
            this.adInformationLayout = new com.startapp.android.publish.adinformation.StartAppSDK(getContext(), com.startapp.android.publish.adinformation.StartAppSDK.StartAppSDK.SMALL, Placement.INAPP_BANNER, this.adHtml.getAdInfoOverride());
            this.adInformationLayout.m2548a(this.adInformationContatiner);
        }
        try {
            ViewGroup viewGroup = (ViewGroup) this.adInformationContatiner.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.adInformationContatiner);
            }
        } catch (Exception e) {
        }
        addView(this.adInformationContatiner, layoutParams);
    }

    public void hideBanner() {
        this.visible = false;
        setVisibility(8);
    }

    public void showBanner() {
        this.visible = true;
        setVisibility(0);
    }

    private void init() {
        if (isInEditMode()) {
            setMinimumWidth(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) HttpStatus.SC_MULTIPLE_CHOICES));
            setMinimumHeight(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), 50));
            setBackgroundColor(Color.rgb(169, 169, 169));
            View textView = new TextView(getContext());
            textView.setText("StartApp Standard Banner");
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            addView(textView, layoutParams);
            return;
        }
        this.webView = new StartAppSDK(getContext());
        this.options = new BannerOptions();
        if (this.adPreferences == null) {
            this.adPreferences = new AdPreferences();
        }
        this.size = new com.startapp.android.publish.banner.StartAppSDK(HttpStatus.SC_MULTIPLE_CHOICES, 50);
        this.adHtml = new com.startapp.android.publish.p028a.StartAppSDK(getContext(), getOffset());
        this.webView.setId(ID_WEBVIEW);
        setVisibility(8);
        this.webView.setBackgroundColor(0);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setOnTouchListener(new StartAppSDK());
        this.webView.setOnLongClickListener(new StartAppSDK());
        this.webView.setLongClickable(false);
        this.options = MetaData.getInstance().getBannerOptionsCopy();
        getViewTreeObserver().addOnGlobalLayoutListener(new StartAppSDK());
    }

    public void setLayoutParams(LayoutParams params) {
        super.setLayoutParams(params);
        if (params.width > 0 && params.height > 0) {
            new Handler().post(new StartAppSDK());
        }
    }

    private void initBanner() {
        if (!this.initBannerCalled) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Initializing BannerHtml");
            this.initBannerCalled = true;
            int a = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.size.m2645a());
            int a2 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.size.m2648b());
            setMinimumWidth(a);
            setMinimumHeight(a2);
            this.webView.addJavascriptInterface(new JsInterface(getContext(), new StartAppSDK(), new com.startapp.android.publish.p022h.StartAppSDK(getAdTag())), "startappwall");
            this.webView.setWebViewClient(new MyWebViewClient());
            if (this.loaded) {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "BannerHTML already Loaded");
                onReceiveAd(this.adHtml);
            } else if (this.defaultLoad) {
                loadBanner();
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a2);
            layoutParams.addRule(13);
            View relativeLayout = new RelativeLayout(getContext());
            relativeLayout.addView(this.webView, layoutParams);
            if (getLayoutParams() != null) {
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(getLayoutParams().width, getLayoutParams().height);
                layoutParams2.addRule(13);
                addView(relativeLayout, layoutParams2);
                return;
            }
            addView(relativeLayout);
        }
    }

    protected void reload() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Loading from network");
        if (this.adPreferences == null) {
            this.adPreferences = new AdPreferences();
        }
        Point availableSize = getAvailableSize();
        this.adHtml.setState(AdState.UN_INITIALIZED);
        this.adHtml.setSize(availableSize.x, availableSize.y);
        this.adHtml.load(this.adPreferences, this);
    }

    private Point getAvailableSize() {
        Point point = new Point();
        if (getLayoutParams() != null && getLayoutParams().width > 0) {
            point.x = com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), getLayoutParams().width + 1);
        }
        if (getLayoutParams() != null && getLayoutParams().height > 0) {
            point.y = com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), getLayoutParams().height + 1);
        }
        if (getLayoutParams() == null || getLayoutParams().width <= 0 || getLayoutParams().height <= 0) {
            Context context = getContext();
            if (context instanceof Activity) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view = (View) getParent();
                    if (view instanceof Banner) {
                        view = (View) view.getParent();
                    }
                    while (view != null && (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0)) {
                        if (view.getMeasuredWidth() > 0) {
                            setPointWidthIfNotSet(point, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                        }
                        if (view.getMeasuredHeight() > 0) {
                            setPointHeightIfNotSet(point, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                        }
                        view = (View) view.getParent();
                    }
                    if (view == null) {
                        determineSizeByScreen(point, decorView);
                    } else {
                        setPointWidthIfNotSet(point, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                        setPointHeightIfNotSet(point, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                    }
                } catch (Exception e) {
                    determineSizeByScreen(point, decorView);
                }
            } else {
                WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
                setPointWidthIfNotSet(point, HttpStatus.SC_MULTIPLE_CHOICES);
                setPointHeightIfNotSet(point, 50);
                if (!(windowManager == null || context == null)) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2863a(context, windowManager, point);
                }
            }
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "============ exit Application Size [" + point.x + "," + point.y + "] =========");
        return point;
    }

    private void determineSizeByScreen(Point result, View window) {
        setPointWidthIfNotSet(result, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), window.getMeasuredWidth()));
        setPointHeightIfNotSet(result, com.startapp.android.publish.p022h.StartAppSDK.m2973b(getContext(), window.getMeasuredHeight()));
    }

    private void setPointWidthIfNotSet(Point point, int width) {
        if (point.x <= 0) {
            point.x = width;
        }
    }

    private void setPointHeightIfNotSet(Point point, int height) {
        if (point.y <= 0) {
            point.y = height;
        }
    }

    public void onReceiveAd(Ad ad) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, " Html Ad Recievied OK");
        this.sentImpression = false;
        removeView(this.adInformationContatiner);
        if (this.adHtml == null || this.adHtml.getHtml() == null || this.adHtml.getHtml().compareTo(StringUtils.EMPTY) == 0) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 6, "No Banner recieved");
            onFailedToReceiveBanner();
        } else {
            String a = com.startapp.android.publish.p022h.StartAppSDK.m2992a(this.adHtml.getHtml(), "@adId@", "@adId@");
            if (a == null || this.prevAdId == null || this.prevAdId.compareTo(a) != 0) {
                this.prevAdId = a;
                loadHtml();
                try {
                    if (setSize(Integer.parseInt(com.startapp.android.publish.p022h.StartAppSDK.m2992a(this.adHtml.getHtml(), "@width@", "@width@")), Integer.parseInt(com.startapp.android.publish.p022h.StartAppSDK.m2992a(this.adHtml.getHtml(), "@height@", "@height@")))) {
                        this.loaded = true;
                        addAdInformationLayout();
                        System.err.println("onReceive: makeImpression1");
                    } else {
                        onFailedToReceiveBanner();
                    }
                } catch (NumberFormatException e) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 6, "Error Casting width & height from HTML");
                    onFailedToReceiveBanner();
                }
            } else {
                addAdInformationLayout();
                System.err.println("onReceive: makeImpression2");
            }
            addDisplayEventOnLoad();
            if (!(this.listener == null || this.callbackSent)) {
                this.callbackSent = true;
                this.listener.onReceiveAd(this);
            }
        }
        if (this.loaded) {
            if (this.visible) {
                setVisibility(0);
            }
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Done Loading HTML Banner");
        }
    }

    private void onFailedToReceiveBanner() {
        if (this.listener != null && !this.callbackSent) {
            this.callbackSent = true;
            this.listener.onFailedToReceiveAd(this);
        }
    }

    private void loadHtml() {
        com.startapp.android.publish.p022h.StartAppSDK.m3007a(this.webView, this.adHtml.getHtml());
    }

    private void makeImpression() {
        if (!this.sentImpression && this.visible && isShown()) {
            com.startapp.android.publish.p022h.StartAppSDK.m3003a(getContext(), this.adHtml.getTrackingUrls(), new com.startapp.android.publish.p022h.StartAppSDK(getAdTag()));
            this.sentImpression = true;
        }
    }

    private boolean setSize(int width, int height) {
        Point availableSize = getAvailableSize();
        LayoutParams layoutParams;
        if (availableSize.x < width || availableSize.y < height) {
            Point point = new Point(0, 0);
            layoutParams = this.webView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(point.x, point.y);
            } else {
                layoutParams.width = point.x;
                layoutParams.height = point.y;
            }
            this.webView.setLayoutParams(layoutParams);
            return false;
        }
        this.size.m2647a(width, height);
        int a = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.size.m2645a());
        int a2 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), this.size.m2648b());
        setMinimumWidth(a);
        setMinimumHeight(a2);
        layoutParams = this.webView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(a, a2);
        } else {
            layoutParams.width = a;
            layoutParams.height = a2;
        }
        this.webView.setLayoutParams(layoutParams);
        return true;
    }

    public void onFailedToReceiveAd(Ad ad) {
        onFailedToReceiveBanner();
    }

    private void onResume() {
        if (this.webView != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2885c(this.webView);
        }
    }

    private void onPause() {
        if (this.webView != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2881b(this.webView);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onResume();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onPause();
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            onResume();
        } else {
            onPause();
        }
    }

    public void setBannerListener(BannerListener listener) {
        this.listener = listener;
    }

    protected int getRefreshRate() {
        return this.options.m2636i();
    }

    protected int getOffset() {
        if (this.adHtml == null) {
            return 0;
        }
        return this.adHtml.m5199a();
    }
}
