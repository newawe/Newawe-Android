package com.startapp.android.publish.splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdDisplayListener;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.adrules.AdRulesResult;
import com.startapp.android.publish.splash.SplashConfig.MaxAdDisplayTime;
import com.startapp.android.publish.splash.SplashConfig.Orientation;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.startapp.android.publish.splash.b */
public class StartAppSDK {
    private Activity f3242a;
    private SplashConfig f3243b;
    private StartAppSDK f3244c;
    private com.startapp.android.publish.p008b.StartAppSDK f3245d;
    private Handler f3246e;
    private boolean f3247f;
    private StartAppSDK f3248g;
    private AdPreferences f3249h;
    private Runnable f3250i;
    private Runnable f3251j;
    private AdEventListener f3252k;

    /* renamed from: com.startapp.android.publish.splash.b.1 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3236a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3236a = startAppSDK;
        }

        public void run() {
            if (this.f3236a.m3569d()) {
                this.f3236a.m3572e();
                this.f3236a.m3574f();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3237a;

        /* renamed from: com.startapp.android.publish.splash.b.2.1 */
        class StartAppSDK implements AdDisplayListener {
            final /* synthetic */ StartAppSDK f4184a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f4184a = startAppSDK;
            }

            public void adHidden(Ad ad) {
                this.f4184a.f3237a.f3244c.m3555b();
            }

            public void adDisplayed(Ad ad) {
                this.f4184a.f3237a.f3244c.m3557c();
            }

            public void adClicked(Ad ad) {
                this.f4184a.f3237a.f3244c.m3561g();
            }

            public void adNotDisplayed(Ad ad) {
            }
        }

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3237a = startAppSDK;
        }

        public void run() {
            if (!this.f3237a.f3247f && this.f3237a.f3248g != null) {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Displaying Splash ad");
                this.f3237a.f3248g.showAd(new StartAppSDK(this));
                this.f3237a.m3575g();
                this.f3237a.f3242a.finish();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3238a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3238a = startAppSDK;
        }

        public void run() {
            if (this.f3238a.f3244c.m3556b(this.f3238a.f3251j, this.f3238a.f3245d)) {
                this.f3238a.f3248g = null;
                this.f3238a.f3245d = null;
            }
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.5 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3239a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3239a = startAppSDK;
        }

        public void run() {
            this.f3239a.f3244c.m3554a(this.f3239a.f3251j, this.f3239a.f3245d);
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.6 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3240a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3240a = startAppSDK;
        }

        public void run() {
            this.f3240a.f3244c.m3552a(this.f3240a.f3248g);
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.7 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f3241a;

        static {
            f3241a = new int[Orientation.values().length];
            try {
                f3241a[Orientation.PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3241a[Orientation.LANDSCAPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.3 */
    class StartAppSDK implements AdEventListener {
        final /* synthetic */ StartAppSDK f4185a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f4185a = startAppSDK;
        }

        public void onReceiveAd(Ad ad) {
            this.f4185a.f3244c.m3553a(this.f4185a.f3251j);
        }

        public void onFailedToReceiveAd(Ad ad) {
            if (this.f4185a.f3248g != null) {
                this.f4185a.f3244c.m3551a();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.splash.b.a */
    private static class StartAppSDK extends StartAppAd {
        private static final long serialVersionUID = 1;

        public StartAppSDK(Context context) {
            super(context);
            this.placement = Placement.INAPP_SPLASH;
        }

        protected AdRulesResult shouldDisplayAd(String adTag, Placement adPlacement) {
            return new AdRulesResult(true);
        }
    }

    public StartAppSDK(Activity activity, SplashConfig splashConfig, AdPreferences adPreferences) {
        this.f3246e = new Handler();
        this.f3247f = false;
        this.f3250i = new StartAppSDK(this);
        this.f3251j = new StartAppSDK(this);
        this.f3252k = new StartAppSDK(this);
        this.f3242a = activity;
        this.f3243b = splashConfig;
        splashConfig.initSplashLogo(activity);
        this.f3249h = adPreferences;
        this.f3244c = new StartAppSDK(activity);
    }

    public void m3581a(Bundle bundle) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "========= Splash Screen Feature =========");
        this.f3244c.m3562h();
        if (m3568c()) {
            this.f3246e.postDelayed(this.f3250i, 100);
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Splash screen orientation is being modified");
            return;
        }
        this.f3246e.post(this.f3250i);
    }

    public void m3580a() {
        this.f3246e.removeCallbacks(this.f3250i);
        this.f3244c.m3558d();
    }

    private boolean m3568c() {
        boolean z = true;
        int i = this.f3242a.getResources().getConfiguration().orientation;
        if (this.f3243b.getOrientation() == Orientation.AUTO) {
            if (i == 2) {
                this.f3243b.setOrientation(Orientation.LANDSCAPE);
            } else {
                this.f3243b.setOrientation(Orientation.PORTRAIT);
            }
        }
        switch (StartAppSDK.f3241a[this.f3243b.getOrientation().ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (i != 2) {
                    z = false;
                }
                com.startapp.android.publish.p022h.StartAppSDK.m2861a(this.f3242a);
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (i != 1) {
                    z = false;
                }
                com.startapp.android.publish.p022h.StartAppSDK.m2879b(this.f3242a);
                break;
            default:
                z = false;
                break;
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Set Orientation: [" + this.f3243b.getOrientation().toString() + "]");
        return z;
    }

    private boolean m3569d() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Displaying Splash screen");
        if (this.f3243b.validate(this.f3242a)) {
            this.f3242a.setContentView(this.f3243b.getLayout(this.f3242a), new LayoutParams(-1, -1));
            return true;
        }
        throw new IllegalArgumentException(this.f3243b.getErrorMessage());
    }

    private void m3572e() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Loading Splash Ad");
        this.f3248g = new StartAppSDK(this.f3242a.getApplicationContext());
        this.f3245d = this.f3248g.loadSplash(this.f3249h, this.f3252k);
    }

    private void m3574f() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Started Splash Loading Timer");
        this.f3246e.postDelayed(new StartAppSDK(this), this.f3243b.getMaxLoadAdTimeout().longValue());
        this.f3246e.postDelayed(new StartAppSDK(this), this.f3243b.getMinSplashTime().getIndex());
    }

    private void m3575g() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Splash", 4, "Started Splash Display Timer");
        if (this.f3243b.getMaxAdDisplayTime() != MaxAdDisplayTime.FOR_EVER) {
            this.f3246e.postDelayed(new StartAppSDK(this), this.f3243b.getMaxAdDisplayTime().getIndex());
        }
    }

    public void m3582b() {
        this.f3247f = true;
        this.f3244c.m3560f();
    }
}
