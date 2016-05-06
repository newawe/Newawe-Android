package com.startapp.android.publish.nativead;

import android.content.Context;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.adrules.AdDisplayEvent;
import com.startapp.android.publish.model.adrules.AdRulesResult;
import com.startapp.android.publish.model.adrules.SessionManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class StartAppNativeAd extends Ad implements com.startapp.android.publish.nativead.NativeAdDetails.StartAppSDK {
    private static final String TAG = "StartAppNativeAd";
    private static final long serialVersionUID = 1;
    private StartAppSDK adEventDelegate;
    private boolean isLoading;
    private List<NativeAdDetails> listNativeAds;
    private com.startapp.android.publish.p028a.StartAppSDK nativeAd;
    private NativeAdPreferences preferences;
    private int totalObjectsLoaded;

    /* renamed from: com.startapp.android.publish.nativead.StartAppNativeAd.b */
    public enum StartAppSDK {
        LAUNCH_APP,
        OPEN_MARKET
    }

    /* renamed from: com.startapp.android.publish.nativead.StartAppNativeAd.a */
    private class StartAppSDK implements AdEventListener {
        final /* synthetic */ StartAppNativeAd f4164a;
        private AdEventListener f4165b;

        public StartAppSDK(StartAppNativeAd startAppNativeAd, AdEventListener adEventListener) {
            this.f4164a = startAppNativeAd;
            this.f4165b = null;
            this.f4165b = new com.startapp.android.publish.StartAppSDK(adEventListener);
        }

        public void onReceiveAd(Ad ad) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(StartAppNativeAd.TAG, 3, "NativeAd Received");
            this.f4164a.initNativeAdList();
        }

        public void onFailedToReceiveAd(Ad ad) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(StartAppNativeAd.TAG, 3, "NativeAd Failed to load");
            this.f4164a.setErrorMessage(ad.getErrorMessage());
            if (this.f4165b != null) {
                this.f4165b.onFailedToReceiveAd(this.f4164a);
                this.f4165b = null;
            }
            this.f4164a.isLoading = false;
            this.f4164a.initNativeAdList();
        }

        public AdEventListener m4863a() {
            return this.f4165b;
        }
    }

    public StartAppNativeAd(Context context) {
        super(context, Placement.INAPP_NATIVE);
        this.totalObjectsLoaded = 0;
        this.listNativeAds = new ArrayList();
        this.isLoading = false;
    }

    private NativeAdPreferences getPreferences() {
        return this.preferences;
    }

    private void setPreferences(NativeAdPreferences preferences) {
        this.preferences = preferences;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== StartAppNativeAd =====\n");
        for (int i = 0; i < getNumberOfAds(); i++) {
            stringBuffer.append(this.listNativeAds.get(i));
        }
        stringBuffer.append("===== End StartAppNativeAd =====");
        return stringBuffer.toString();
    }

    private void initNativeAdList() {
        this.totalObjectsLoaded = 0;
        if (this.listNativeAds == null) {
            this.listNativeAds = new ArrayList();
        }
        this.listNativeAds.clear();
        if (this.nativeAd != null && this.nativeAd.m4751b() != null) {
            for (int i = 0; i < this.nativeAd.m4751b().size(); i++) {
                this.listNativeAds.add(new NativeAdDetails((AdDetails) this.nativeAd.m4751b().get(i), getPreferences(), i, this));
            }
        }
    }

    public void onNativeAdDetailsLoaded(int identifier) {
        this.totalObjectsLoaded++;
        if (this.nativeAd.m4751b() != null && this.totalObjectsLoaded == this.nativeAd.m4751b().size()) {
            onNativeAdLoaded();
        }
    }

    private void onNativeAdLoaded() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Ad Loaded successfully");
        this.isLoading = false;
        setErrorMessage(null);
        if (this.adEventDelegate != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Calling original RecienedAd callback");
            AdEventListener a = this.adEventDelegate.m4863a();
            if (a != null) {
                a.onReceiveAd(this);
            }
        }
    }

    public int getNumberOfAds() {
        if (this.listNativeAds != null) {
            return this.listNativeAds.size();
        }
        return 0;
    }

    public boolean loadAd() {
        return loadAd(new NativeAdPreferences(), null);
    }

    public boolean loadAd(AdEventListener listener) {
        return loadAd(new NativeAdPreferences(), listener);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences) {
        return loadAd(nativeAdPreferences, null);
    }

    public boolean loadAd(NativeAdPreferences adPrefrences, AdEventListener listener) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Start loading StartAppNativeAd");
        this.adEventDelegate = new StartAppSDK(this, listener);
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "Configurtaion: " + adPrefrences);
        setPreferences(adPrefrences);
        if (this.isLoading) {
            setErrorMessage("Ad is currently being loaded");
            return false;
        }
        this.isLoading = true;
        this.nativeAd = new com.startapp.android.publish.p028a.StartAppSDK(this.context, getPreferences());
        return this.nativeAd.load(adPrefrences, this.adEventDelegate);
    }

    protected void loadAds(AdPreferences adPreferences, AdEventListener callback) {
    }

    public ArrayList<NativeAdDetails> getNativeAds() {
        return getNativeAds(null);
    }

    public ArrayList<NativeAdDetails> getNativeAds(String adTag) {
        ArrayList<NativeAdDetails> arrayList = new ArrayList();
        AdRulesResult shouldDisplayAd = MetaData.getInstance().getAdRules().shouldDisplayAd(Placement.INAPP_NATIVE, adTag);
        if (!shouldDisplayAd.shouldDisplayAd()) {
            com.startapp.android.publish.p022h.StartAppSDK.m3005a(this.context, com.startapp.android.publish.p022h.StartAppSDK.m3024a(getAdDetailsList()), adTag, shouldDisplayAd.getSimpleReason());
            if (com.startapp.android.publish.StartAppSDK.m2732a().booleanValue()) {
                com.startapp.android.publish.p022h.StartAppSDK.m2954a().m2955a(this.context, shouldDisplayAd.getReason());
            }
        } else if (this.listNativeAds != null) {
            for (NativeAdDetails nativeAdDetails : this.listNativeAds) {
                nativeAdDetails.m4862a(adTag);
                arrayList.add(nativeAdDetails);
            }
            SessionManager.getInstance().addAdDisplayEvent(new AdDisplayEvent(Placement.INAPP_NATIVE, adTag));
        }
        return arrayList;
    }

    private List<AdDetails> getAdDetailsList() {
        List<AdDetails> arrayList = new ArrayList();
        if (this.listNativeAds != null) {
            for (NativeAdDetails a : this.listNativeAds) {
                arrayList.add(a.m4860a());
            }
        }
        return arrayList;
    }
}
