package com.Newawe.ads.sdk;

import android.app.Activity;
import com.Newawe.server.StatController;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyInterstitialAd;

public class AdColonySdkWrapper extends SdkWrapper {
    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_CLIENT_OPTIONS = "client_options";
    private static final String KEY_ZONE_ID = "zone_id";

    /* renamed from: com.Newawe.ads.sdk.AdColonySdkWrapper.1 */
    class C02071 implements Runnable {
        final /* synthetic */ Activity val$activity;

        /* renamed from: com.Newawe.ads.sdk.AdColonySdkWrapper.1.1 */
        class C09891 implements AdColonyAdAvailabilityListener {
            C09891() {
            }

            public void onAdColonyAdAvailabilityChange(boolean arg0, String arg1) {
            }
        }

        C02071(Activity activity) {
            this.val$activity = activity;
        }

        public void run() {
            super.startSession(this.val$activity);
            AdColony.configure(this.val$activity, (String) AdColonySdkWrapper.this.parameters.get(AdColonySdkWrapper.KEY_CLIENT_OPTIONS), (String) AdColonySdkWrapper.this.parameters.get(AdColonySdkWrapper.KEY_APP_ID), (String) AdColonySdkWrapper.this.parameters.get(AdColonySdkWrapper.KEY_ZONE_ID));
            AdColony.addAdAvailabilityListener(new C09891());
        }
    }

    /* renamed from: com.Newawe.ads.sdk.AdColonySdkWrapper.2 */
    class C02082 implements Runnable {

        /* renamed from: com.Newawe.ads.sdk.AdColonySdkWrapper.2.1 */
        class C09901 implements AdColonyAdListener {
            C09901() {
            }

            public void onAdColonyAdStarted(AdColonyAd arg0) {
            }

            public void onAdColonyAdAttemptFinished(AdColonyAd ad) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADCOLONY_INTERSTITIAL_IMPRESSION_URL);
            }
        }

        C02082() {
        }

        public void run() {
            super.showFsBanner();
            AdColony.resume(AdColonySdkWrapper.this._activity);
            AdColonyInterstitialAd ad = new AdColonyInterstitialAd().withListener(new C09901());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADCOLONY_INTERSTITIAL_REQUEST_URL);
            ad.show();
        }
    }

    public void startSession(Activity activity) {
        activity.runOnUiThread(new C02071(activity));
    }

    public void showFsBanner() {
        this._activity.runOnUiThread(new C02082());
    }
}
