package com.startapp.android.publish;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.startapp.android.publish.b */
public class StartAppSDK implements AdEventListener {
    private AdEventListener f4069a;

    /* renamed from: com.startapp.android.publish.b.1 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ Ad f2688a;
        final /* synthetic */ StartAppSDK f2689b;

        StartAppSDK(StartAppSDK startAppSDK, Ad ad) {
            this.f2689b = startAppSDK;
            this.f2688a = ad;
        }

        public void run() {
            this.f2689b.f4069a.onReceiveAd(this.f2688a);
        }
    }

    /* renamed from: com.startapp.android.publish.b.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ Ad f2690a;
        final /* synthetic */ StartAppSDK f2691b;

        StartAppSDK(StartAppSDK startAppSDK, Ad ad) {
            this.f2691b = startAppSDK;
            this.f2690a = ad;
        }

        public void run() {
            this.f2691b.f4069a.onFailedToReceiveAd(this.f2690a);
        }
    }

    public StartAppSDK(AdEventListener adEventListener) {
        this.f4069a = adEventListener;
    }

    public void onReceiveAd(Ad ad) {
        if (this.f4069a != null) {
            Handler a = m4756a();
            if (a != null) {
                a.post(new StartAppSDK(this, ad));
            } else {
                this.f4069a.onReceiveAd(ad);
            }
        }
    }

    public void onFailedToReceiveAd(Ad ad) {
        if (this.f4069a != null) {
            Handler a = m4756a();
            if (a != null) {
                a.post(new StartAppSDK(this, ad));
            } else {
                this.f4069a.onFailedToReceiveAd(ad);
            }
        }
    }

    public Handler m4756a() {
        return new Handler(Looper.getMainLooper());
    }
}
