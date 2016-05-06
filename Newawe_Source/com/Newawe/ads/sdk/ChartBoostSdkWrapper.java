package com.Newawe.ads.sdk;

import android.app.Activity;
import com.Newawe.server.StatController;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

public class ChartBoostSdkWrapper extends SdkWrapper {
    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_APP_SIGNATURE = "app_signature";

    /* renamed from: com.Newawe.ads.sdk.ChartBoostSdkWrapper.1 */
    class C02091 implements Runnable {
        final /* synthetic */ Activity val$activity;

        /* renamed from: com.Newawe.ads.sdk.ChartBoostSdkWrapper.1.1 */
        class C13001 extends ChartboostDelegate {
            C13001() {
            }

            public void didFailToLoadInPlay(String location, CBImpressionError error) {
                super.didFailToLoadInPlay(location, error);
            }

            public void didFailToLoadInterstitial(String location, CBImpressionError error) {
                super.didFailToLoadInterstitial(location, error);
            }

            public void didFailToLoadRewardedVideo(String location, CBImpressionError error) {
                super.didFailToLoadRewardedVideo(location, error);
            }

            public void didFailToLoadMoreApps(String location, CBImpressionError error) {
                super.didFailToLoadMoreApps(location, error);
            }

            public void didCloseInterstitial(String location) {
                super.didCloseInterstitial(location);
                Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
            }

            public void didClickInterstitial(String location) {
                super.didClickInterstitial(location);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CHARTBOOST_INTERSTITIAL_CLICK_URL);
            }

            public void didDisplayInterstitial(String location) {
                super.didDisplayInterstitial(location);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CHARTBOOST_INTERSTITIAL_IMPRESSION_URL);
            }
        }

        C02091(Activity activity) {
            this.val$activity = activity;
        }

        public void run() {
            super.startSession(this.val$activity);
            Chartboost.startWithAppId(this.val$activity, (String) ChartBoostSdkWrapper.this.parameters.get(ChartBoostSdkWrapper.KEY_APP_ID), (String) ChartBoostSdkWrapper.this.parameters.get(ChartBoostSdkWrapper.KEY_APP_SIGNATURE));
            Chartboost.onCreate(this.val$activity);
            Chartboost.onStart(this.val$activity);
            Chartboost.onResume(this.val$activity);
            Chartboost.setDelegate(new C13001());
            Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
        }
    }

    /* renamed from: com.Newawe.ads.sdk.ChartBoostSdkWrapper.2 */
    class C02102 implements Runnable {
        C02102() {
        }

        public void run() {
            super.showFsBanner();
            Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CHARTBOOST_INTERSTITIAL_REQUEST_URL);
        }
    }

    public void startSession(Activity activity) {
        activity.runOnUiThread(new C02091(activity));
    }

    public void showFsBanner() {
        this._activity.runOnUiThread(new C02102());
    }
}
