package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.ads.AdUnit.C0680a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.startapp.android.publish.model.MetaData;
import java.lang.ref.WeakReference;
import java.util.Map;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class InMobiInterstitial {
    private static final String TAG;
    private C0687a mClientCallbackHandler;
    private final C0680a mInterstitialAdListener;
    private InterstitialAdUnit mInterstitialAdUnit;
    private InterstitialAdListener mlistener;

    public interface InterstitialAdListener {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    /* renamed from: com.inmobi.ads.InMobiInterstitial.a */
    private static final class C0687a extends Handler {
        private WeakReference<InterstitialAdListener> f1042a;
        private WeakReference<InMobiInterstitial> f1043b;

        public C0687a(InMobiInterstitial inMobiInterstitial, InterstitialAdListener interstitialAdListener) {
            super(Looper.getMainLooper());
            this.f1043b = new WeakReference(inMobiInterstitial);
            this.f1042a = new WeakReference(interstitialAdListener);
        }

        public void handleMessage(Message message) {
            Map map = null;
            InMobiInterstitial inMobiInterstitial = (InMobiInterstitial) this.f1043b.get();
            InterstitialAdListener interstitialAdListener = (InterstitialAdListener) this.f1042a.get();
            if (inMobiInterstitial != null && interstitialAdListener != null) {
                switch (message.what) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        interstitialAdListener.onAdLoadSucceeded(inMobiInterstitial);
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        interstitialAdListener.onAdLoadFailed(inMobiInterstitial, (InMobiAdRequestStatus) message.obj);
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        interstitialAdListener.onAdDisplayed(inMobiInterstitial);
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        interstitialAdListener.onAdDismissed(inMobiInterstitial);
                    case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        interstitialAdListener.onAdInteraction(inMobiInterstitial, map);
                    case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                        interstitialAdListener.onUserLeftApplication(inMobiInterstitial);
                    case ConnectionResult.NETWORK_ERROR /*7*/:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        interstitialAdListener.onAdRewardActionCompleted(inMobiInterstitial, map);
                    default:
                        Logger.m1440a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                }
            }
        }
    }

    /* renamed from: com.inmobi.ads.InMobiInterstitial.1 */
    class C12271 implements C0680a {
        final /* synthetic */ InMobiInterstitial f3772a;

        C12271(InMobiInterstitial inMobiInterstitial) {
            this.f3772a = inMobiInterstitial;
        }

        public void m4384a() {
            this.f3772a.mClientCallbackHandler.sendEmptyMessage(1);
        }

        public void m4385a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = inMobiAdRequestStatus;
            this.f3772a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void m4387b() {
            this.f3772a.mClientCallbackHandler.sendEmptyMessage(3);
        }

        public void m4389c() {
            this.f3772a.mClientCallbackHandler.sendEmptyMessage(4);
        }

        public void m4386a(Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = map;
            this.f3772a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void m4390d() {
            this.f3772a.mClientCallbackHandler.sendEmptyMessage(6);
        }

        public void m4388b(Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = map;
            this.f3772a.mClientCallbackHandler.sendMessage(obtain);
        }
    }

    static {
        TAG = InMobiInterstitial.class.getSimpleName();
    }

    public InMobiInterstitial(Context context, long j, InterstitialAdListener interstitialAdListener) {
        this.mInterstitialAdListener = new C12271(this);
        if (!SdkContext.m1257a()) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
        } else if (interstitialAdListener == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "The Ad unit cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Unable to create ad unit with NULL context object.");
        } else {
            this.mInterstitialAdUnit = new InterstitialAdUnit(context, j, this.mInterstitialAdListener);
            this.mlistener = interstitialAdListener;
            this.mClientCallbackHandler = new C0687a(this, interstitialAdListener);
        }
    }

    public void setKeywords(String str) {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m4343a(str);
        }
    }

    public void load() {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m5171n();
        }
    }

    public void show() {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m5175v();
        }
    }

    public void show(int i, int i2) {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m5162a(i, i2);
        }
    }

    public boolean isReady() {
        if (this.mInterstitialAdUnit == null) {
            return false;
        }
        return this.mInterstitialAdUnit.m5177x();
    }

    public void setExtras(Map<String, String> map) {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m4344a((Map) map);
        }
    }

    public void disableHardwareAcceleration() {
        if (this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.m5178y();
        }
    }
}
