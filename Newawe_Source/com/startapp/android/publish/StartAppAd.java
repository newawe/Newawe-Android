package com.startapp.android.publish;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.Ad.AdState;
import com.startapp.android.publish.AdDisplayListener.NotDisplayedReason;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.adrules.AdDisplayEvent;
import com.startapp.android.publish.model.adrules.AdRulesResult;
import com.startapp.android.publish.model.adrules.SessionManager;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashHideListener;
import com.startapp.android.publish.video.VideoListener;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* compiled from: StartAppSDK */
public class StartAppAd extends Ad {
    private static final String TAG = "StartAppAd";
    private static final long serialVersionUID = 1;
    private static boolean testMode;
    private StartAppSDK ad;
    private com.startapp.android.publish.p008b.StartAppSDK adKey;
    private AdMode adMode;
    private AdDisplayListener callback;
    private BroadcastReceiver callbackBroadcastReceiver;
    private VideoListener videoListener;

    /* renamed from: com.startapp.android.publish.StartAppAd.1 */
    class StartAppSDK extends BroadcastReceiver {
        final /* synthetic */ StartAppAd f2624a;

        /* renamed from: com.startapp.android.publish.StartAppAd.1.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f2623a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f2623a = startAppSDK;
            }

            public void run() {
                this.f2623a.f2624a.videoListener.onVideoCompleted();
            }
        }

        StartAppSDK(StartAppAd startAppAd) {
            this.f2624a = startAppAd;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.startapp.android.ShowFailedDisplayBroadcastListener")) {
                if (intent.getExtras().containsKey("showFailedReason")) {
                    this.f2624a.setNotDisplayedReason((NotDisplayedReason) intent.getExtras().getSerializable("showFailedReason"));
                }
                if (this.f2624a.callback != null) {
                    this.f2624a.callback.adNotDisplayed(this.f2624a);
                }
                m2516a(context);
            } else if (intent.getAction().equals("com.startapp.android.ShowDisplayBroadcastListener")) {
                if (this.f2624a.callback != null) {
                    this.f2624a.callback.adDisplayed(this.f2624a);
                }
            } else if (intent.getAction().equals("com.startapp.android.OnClickCallback")) {
                if (this.f2624a.callback != null) {
                    this.f2624a.callback.adClicked(this.f2624a);
                }
            } else if (!intent.getAction().equals("com.startapp.android.OnVideoCompleted")) {
                if (this.f2624a.callback != null) {
                    this.f2624a.callback.adHidden(this.f2624a);
                }
                m2516a(context);
            } else if (this.f2624a.videoListener != null) {
                new Handler(Looper.getMainLooper()).post(new StartAppSDK(this));
            }
            this.f2624a.ad = null;
        }

        private void m2516a(Context context) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a(context).m2918a((BroadcastReceiver) this);
        }
    }

    /* renamed from: com.startapp.android.publish.StartAppAd.2 */
    static class StartAppSDK extends BroadcastReceiver {
        final /* synthetic */ Activity f2625a;
        final /* synthetic */ SplashHideListener f2626b;

        StartAppSDK(Activity activity, SplashHideListener splashHideListener) {
            this.f2625a = activity;
            this.f2626b = splashHideListener;
        }

        public void onReceive(Context context, Intent intent) {
            com.startapp.android.publish.p022h.StartAppSDK.m2994a(this.f2625a, false);
            if (this.f2626b != null) {
                this.f2626b.splashHidden();
            }
            com.startapp.android.publish.p022h.StartAppSDK.m2915a(this.f2625a).m2918a((BroadcastReceiver) this);
        }
    }

    /* renamed from: com.startapp.android.publish.StartAppAd.3 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f2627a;

        static {
            f2627a = new int[AdMode.values().length];
            try {
                f2627a[AdMode.FULLPAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2627a[AdMode.OFFERWALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2627a[AdMode.OVERLAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2627a[AdMode.REWARDED_VIDEO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    public enum AdMode {
        AUTOMATIC,
        FULLPAGE,
        OFFERWALL,
        REWARDED_VIDEO,
        OVERLAY
    }

    static {
        testMode = false;
    }

    public StartAppAd(Context context) {
        super(context, null);
        this.adKey = null;
        this.ad = null;
        this.adMode = AdMode.AUTOMATIC;
        this.videoListener = null;
        this.callback = null;
        this.callbackBroadcastReceiver = new StartAppSDK(this);
    }

    public static void init(Context context, String devId, String appId) {
        StartAppSDK.init(context, devId, appId);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener callback) {
        this.adKey = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2590a(this.context, this, this.adMode, adPreferences, callback);
        return this.adKey != null;
    }

    protected void loadAds(AdPreferences adPreferences, AdEventListener callback) {
    }

    @Deprecated
    public boolean show(String adTag, AdDisplayListener listener) {
        boolean z;
        AdRulesResult adRulesResult;
        boolean z2 = false;
        setNotDisplayedReason(null);
        this.callback = new StartAppSDK(listener);
        if (!isNetworkAvailable()) {
            setNotDisplayedReason(NotDisplayedReason.NETWORK_PROBLEM);
            z = false;
            adRulesResult = null;
        } else if (isReady()) {
            Placement placement = getPlacement();
            AdRulesResult shouldDisplayAd = shouldDisplayAd(adTag, placement);
            if (shouldDisplayAd.shouldDisplayAd()) {
                this.ad = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2593a(this.adKey, this);
                if (this.ad != null) {
                    z = this.ad.m2750a(adTag);
                    if (z) {
                        SessionManager.getInstance().addAdDisplayEvent(new AdDisplayEvent(placement, adTag));
                        z2 = z;
                    } else if (this.ad instanceof Ad) {
                        setNotDisplayedReason(((Ad) this.ad).getNotDisplayedReason());
                        z2 = z;
                    } else {
                        z2 = z;
                    }
                }
            } else {
                setNotDisplayedReason(NotDisplayedReason.AD_RULES);
                if (StartAppSDK.m2732a().booleanValue()) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2954a().m2955a(this.context, shouldDisplayAd.getReason());
                }
            }
            z = z2;
            adRulesResult = shouldDisplayAd;
        } else {
            setNotDisplayedReason(NotDisplayedReason.AD_NOT_READY);
            z = false;
            adRulesResult = null;
        }
        if (z) {
            registerBroadcastReceiver("com.startapp.android.HideDisplayBroadcastListener");
            registerBroadcastReceiver("com.startapp.android.ShowDisplayBroadcastListener");
            registerBroadcastReceiver("com.startapp.android.ShowFailedDisplayBroadcastListener");
            registerBroadcastReceiver("com.startapp.android.OnClickCallback");
            registerBroadcastReceiver("com.startapp.android.OnVideoCompleted");
        } else {
            if (getNotDisplayedReason() == null) {
                setNotDisplayedReason(NotDisplayedReason.INTERNAL_ERROR);
            }
            if (getNotDisplayedReason() != NotDisplayedReason.NETWORK_PROBLEM) {
                if (getNotDisplayedReason() != null && getNotDisplayedReason() != NotDisplayedReason.AD_RULES) {
                    com.startapp.android.publish.p022h.StartAppSDK.m3005a(this.context, com.startapp.android.publish.p022h.StartAppSDK.m3023a(this.ad != null ? this.ad : com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey)), adTag, getNotDisplayedReason().toString());
                } else if (adRulesResult != null) {
                    com.startapp.android.publish.p022h.StartAppSDK.m3005a(this.context, com.startapp.android.publish.p022h.StartAppSDK.m3023a(com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey)), adTag, adRulesResult.getSimpleReason());
                }
            }
            this.ad = null;
            if (this.callback != null) {
                this.callback.adNotDisplayed(this);
            }
        }
        return z;
    }

    protected AdRulesResult shouldDisplayAd(String adTag, Placement adPlacement) {
        return MetaData.getInstance().getAdRules().shouldDisplayAd(adPlacement, adTag);
    }

    protected Placement getPlacement() {
        Placement placement = super.getPlacement();
        if (placement != null || this.adKey == null || com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey) == null) {
            return placement;
        }
        return ((Ad) com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey)).getPlacement();
    }

    protected String getAdHtml() {
        StartAppSDK a = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey);
        if (a == null || !(a instanceof com.startapp.android.publish.p028a.StartAppSDK)) {
            return null;
        }
        return ((com.startapp.android.publish.p028a.StartAppSDK) a).getHtml();
    }

    private void registerBroadcastReceiver(String filter) {
        com.startapp.android.publish.p022h.StartAppSDK.m2915a(this.context).m2919a(this.callbackBroadcastReceiver, new IntentFilter(filter));
    }

    @Deprecated
    public boolean show() {
        return show(null, null);
    }

    private void setAdMode(AdMode adMode) {
        this.adMode = adMode;
    }

    public void loadAd() {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), null);
    }

    public void loadAd(AdPreferences adPrefrences) {
        loadAd(AdMode.AUTOMATIC, adPrefrences, null);
    }

    public void loadAd(AdEventListener listener) {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), listener);
    }

    public void loadAd(AdPreferences adPrefrences, AdEventListener listener) {
        loadAd(AdMode.AUTOMATIC, adPrefrences, listener);
    }

    public void loadAd(AdMode adMode) {
        loadAd(adMode, new AdPreferences(), null);
    }

    public void loadAd(AdMode adMode, AdPreferences adPrefrences) {
        loadAd(adMode, adPrefrences, null);
    }

    public void loadAd(AdMode adMode, AdEventListener listener) {
        loadAd(adMode, new AdPreferences(), listener);
    }

    public void loadAd(AdMode adMode, AdPreferences adPrefrences, AdEventListener listener) {
        setAdMode(adMode);
        load(adPrefrences, listener);
    }

    public boolean showAd() {
        return showAd(null, null);
    }

    public boolean showAd(String adTag) {
        return showAd(adTag, null);
    }

    public boolean showAd(AdDisplayListener listener) {
        return show(null, listener);
    }

    public boolean showAd(String adTag, AdDisplayListener listener) {
        return show(adTag, listener);
    }

    public void setVideoListener(VideoListener listener) {
        this.videoListener = listener;
    }

    public void onResume() {
        if (!isReady()) {
            loadAd();
        }
    }

    public void onPause() {
    }

    public void onBackPressed() {
        if (!showAd("exit_ad")) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Could not display StartAppAd onBackPressed");
        }
        StartAppSDK.m2799a().m2825g();
    }

    public void onSaveInstanceState(Bundle outState) {
        int i = 0;
        switch (StartAppSDK.f2627a[this.adMode.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                i = 1;
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                i = 2;
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                i = 3;
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                i = 4;
                break;
        }
        outState.putInt("AdMode", i);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int i = savedInstanceState.getInt("AdMode");
        this.adMode = AdMode.AUTOMATIC;
        if (i == 1) {
            this.adMode = AdMode.FULLPAGE;
        } else if (i == 2) {
            this.adMode = AdMode.OFFERWALL;
        } else if (i == 3) {
            this.adMode = AdMode.OVERLAY;
        } else if (i == 4) {
            this.adMode = AdMode.REWARDED_VIDEO;
        }
        load();
    }

    public void close() {
        if (this.callbackBroadcastReceiver != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a(this.context).m2918a(this.callbackBroadcastReceiver);
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2915a(this.context).m2920a(new Intent("com.startapp.android.CloseAdActivity"));
    }

    public boolean isReady() {
        StartAppSDK a = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey);
        if (a != null) {
            return a.isReady();
        }
        return false;
    }

    public boolean isNetworkAvailable() {
        return com.startapp.android.publish.p022h.StartAppSDK.m3018a(this.context);
    }

    public com.startapp.android.publish.p008b.StartAppSDK loadSplash(AdPreferences adPreferences, AdEventListener callback) {
        this.adKey = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2591a(this.context, this, adPreferences, callback);
        return this.adKey;
    }

    public static void showSplash(Activity activity, Bundle savedInstanceState) {
        showSplash(activity, savedInstanceState, new SplashConfig());
    }

    public static void showSplash(Activity activity, Bundle savedInstanceState, SplashConfig configuration) {
        showSplash(activity, savedInstanceState, configuration, new AdPreferences());
    }

    public static void showSplash(Activity activity, Bundle savedInstanceState, AdPreferences adPreferences) {
        showSplash(activity, savedInstanceState, new SplashConfig(), adPreferences);
    }

    public static void showSplash(Activity activity, Bundle savedInstanceState, SplashConfig configuration, AdPreferences adPreferences) {
        showSplash(activity, savedInstanceState, configuration, adPreferences, null);
    }

    public static void showSplash(Activity activity, Bundle savedInstanceState, SplashConfig configuration, AdPreferences adPreferences, SplashHideListener splashHideListener) {
        if (savedInstanceState == null) {
            configuration.setDefaults(activity);
            com.startapp.android.publish.p022h.StartAppSDK.m2994a(activity, true);
            Intent intent = new Intent(activity, com.startapp.android.publish.p022h.StartAppSDK.m2987a((Context) activity, OverlayActivity.class, AppWallActivity.class));
            intent.putExtra("SplashConfig", configuration);
            intent.putExtra("AdPreference", adPreferences);
            intent.putExtra("testMode", testMode);
            intent.putExtra("fullscreen", com.startapp.android.publish.p022h.StartAppSDK.m3017a(activity));
            intent.putExtra("placement", Placement.INAPP_SPLASH.getIndex());
            intent.addFlags(1140883456);
            activity.startActivity(intent);
            com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) activity).m2919a(new StartAppSDK(activity, splashHideListener), new IntentFilter("com.startapp.android.splashHidden"));
        }
    }

    public static void showSlider(Activity activity) {
        com.startapp.android.publish.slider.StartAppSDK startAppSDK = new com.startapp.android.publish.slider.StartAppSDK(activity);
    }

    public static void showSlider(Activity activity, AdPreferences adPReferences) {
        com.startapp.android.publish.slider.StartAppSDK startAppSDK = new com.startapp.android.publish.slider.StartAppSDK(activity, adPReferences);
    }

    protected String getLauncherName() {
        StartAppSDK a = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey);
        if (a != null) {
            return a.getLauncherName();
        }
        return com.startapp.android.publish.p022h.StartAppSDK.m3046f(getContext());
    }

    public AdState getState() {
        StartAppSDK a = com.startapp.android.publish.p008b.StartAppSDK.m2586a().m2592a(this.adKey);
        if (a != null) {
            return a.getState();
        }
        return AdState.UN_INITIALIZED;
    }
}
