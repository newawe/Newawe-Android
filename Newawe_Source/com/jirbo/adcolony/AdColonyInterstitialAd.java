package com.jirbo.adcolony;

import android.graphics.Bitmap;

public class AdColonyInterstitialAd extends AdColonyAd {
    AdColonyNativeAdListener f3981C;
    boolean f3982D;

    /* renamed from: com.jirbo.adcolony.AdColonyInterstitialAd.1 */
    class C12421 extends C0775j {
        final /* synthetic */ AdColonyInterstitialAd f3979a;

        C12421(AdColonyInterstitialAd adColonyInterstitialAd, C0762d c0762d) {
            this.f3979a = adColonyInterstitialAd;
            super(c0762d);
        }

        void m4680a() {
            if (this.f3979a.h != null) {
                this.o.f2144d.m4732a(this.f3979a.h, this.f3979a);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyInterstitialAd.2 */
    class C12432 extends C0775j {
        final /* synthetic */ AdColonyInterstitialAd f3980a;

        C12432(AdColonyInterstitialAd adColonyInterstitialAd, C0762d c0762d) {
            this.f3980a = adColonyInterstitialAd;
            super(c0762d);
        }

        void m4681a() {
            this.o.f2144d.m4732a(this.f3980a.h, this.f3980a);
        }
    }

    public AdColonyInterstitialAd() {
        C0745a.f1967D = false;
        C0745a.m2156e();
        this.k = "interstitial";
        this.l = "fullscreen";
        this.f3982D = false;
        this.m = aa.m2177b();
    }

    public AdColonyInterstitialAd(String zone_id) {
        this.k = "interstitial";
        this.l = "fullscreen";
        C0745a.m2156e();
        this.h = zone_id;
        this.f3982D = false;
        this.m = aa.m2177b();
    }

    boolean m4684b() {
        return false;
    }

    public AdColonyInterstitialAd withListener(AdColonyAdListener listener) {
        this.y = listener;
        return this;
    }

    boolean m4683a(boolean z) {
        boolean z2 = false;
        if (this.h == null) {
            this.h = C0745a.f2001l.m2268e();
            if (this.h == null) {
                return false;
            }
        }
        C0762d c0762d = C0745a.f2001l;
        String str = this.h;
        if (!z) {
            z2 = true;
        }
        return c0762d.m2256a(str, z, z2);
    }

    public boolean isReady() {
        if (this.h == null) {
            this.h = C0745a.f2001l.m2268e();
            if (this.h == null) {
                return false;
            }
        }
        if (!AdColony.isZoneNative(this.h)) {
            return C0745a.f2001l.m2269e(this.h);
        }
        C0745a.am = 12;
        return false;
    }

    public void show() {
        if (this.f3982D) {
            C0777l.f2242d.m2353b((Object) "Show attempt on out of date ad object. Please instantiate a new ad object for each ad attempt.");
            return;
        }
        C0745a.am = 0;
        this.k = "interstitial";
        this.l = "fullscreen";
        if (isReady()) {
            this.g = C0745a.am;
            this.f3982D = true;
            if (C0745a.f1968E) {
                C12432 c12432 = new C12432(this, C0745a.f2001l);
                C0745a.f1968E = false;
                m2119c();
                C0745a.f1983T = this;
                if (!C0745a.f2001l.m2260b(this)) {
                    if (this.y != null) {
                        this.y.onAdColonyAdAttemptFinished(this);
                    }
                    C0745a.f1968E = true;
                    return;
                } else if (this.y != null) {
                    this.y.onAdColonyAdStarted(this);
                }
            }
            this.f = 4;
            return;
        }
        this.g = C0745a.am;
        C12421 c12421 = new C12421(this, C0745a.f2001l);
        this.f = 2;
        if (this.y != null) {
            this.y.onAdColonyAdAttemptFinished(this);
        }
        this.f3982D = true;
    }

    void m4682a() {
        this.k = "interstitial";
        this.l = "fullscreen";
        if (this.y != null && !this.w) {
            this.y.onAdColonyAdAttemptFinished(this);
        } else if (this.f3981C != null) {
            if (canceled()) {
                this.x.f1917I = true;
            } else {
                this.x.f1917I = false;
            }
            this.f3981C.onAdColonyNativeAdFinished(true, this.x);
        }
        System.gc();
        if (!(C0745a.f1967D || AdColonyBrowser.f1872B)) {
            for (int i = 0; i < C0745a.an.size(); i++) {
                ((Bitmap) C0745a.an.get(i)).recycle();
            }
            C0745a.an.clear();
        }
        this.w = true;
        C0745a.f1984U = null;
        C0745a.f1968E = true;
    }
}
