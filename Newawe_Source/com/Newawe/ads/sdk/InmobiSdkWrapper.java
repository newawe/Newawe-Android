package com.Newawe.ads.sdk;

import android.app.Activity;
import com.Newawe.server.StatController;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiInterstitial.InterstitialAdListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class InmobiSdkWrapper extends SdkWrapper {
    private static final String KEY_ACCOUNT_ID = "account_id";
    private static final String KEY_PLACEMENT_ID = "placement_id";
    private String accountId;
    private InMobiInterstitial interstitial;
    private String placementId;

    /* renamed from: com.Newawe.ads.sdk.InmobiSdkWrapper.1 */
    class C02111 implements Runnable {
        final /* synthetic */ Activity val$activity;

        C02111(Activity activity) {
            this.val$activity = activity;
        }

        public void run() {
            super.startSession(this.val$activity);
            InmobiSdkWrapper.this.accountId = (String) InmobiSdkWrapper.this.parameters.get(InmobiSdkWrapper.KEY_ACCOUNT_ID);
            InmobiSdkWrapper.this.placementId = (String) InmobiSdkWrapper.this.parameters.get(InmobiSdkWrapper.KEY_PLACEMENT_ID);
            InMobiSdk.init(this.val$activity, InmobiSdkWrapper.this.accountId);
        }
    }

    /* renamed from: com.Newawe.ads.sdk.InmobiSdkWrapper.2 */
    class C02122 implements Runnable {

        /* renamed from: com.Newawe.ads.sdk.InmobiSdkWrapper.2.1 */
        class C09911 implements InterstitialAdListener {
            C09911() {
            }

            public void onUserLeftApplication(InMobiInterstitial arg0) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_CLICK_URL);
            }

            public void onAdRewardActionCompleted(InMobiInterstitial arg0, Map<Object, Object> map) {
            }

            public void onAdLoadSucceeded(InMobiInterstitial ad) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_SUCCEEDED_URL);
                if (ad.isReady()) {
                    ad.show();
                    return;
                }
                new HashMap().put(StatController.KEY_GET_PARAM_DETAILS, "ad not ready");
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_FAIL_URL);
            }

            public void onAdLoadFailed(InMobiInterstitial arg0, InMobiAdRequestStatus arg1) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_NO_FILL_URL);
                if (arg1 != null) {
                    String details = StringUtils.EMPTY;
                    String message = arg1.getMessage();
                    StatusCode statusCode = arg1.getStatusCode();
                    if (!(message == null || message == StringUtils.EMPTY)) {
                        details = details + "message=" + message;
                    }
                    if (statusCode != null) {
                        details = details + "&status_code=" + statusCode.toString();
                    }
                    details = (details + "&account_id=" + InmobiSdkWrapper.this.accountId) + "&placement_id=" + InmobiSdkWrapper.this.placementId;
                    HashMap<String, String> d = new HashMap();
                    d.put(StatController.KEY_GET_PARAM_DETAILS, details);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_FAIL_URL, d);
                }
            }

            public void onAdInteraction(InMobiInterstitial arg0, Map<Object, Object> map) {
            }

            public void onAdDisplayed(InMobiInterstitial arg0) {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_IMPRESSION_URL);
            }

            public void onAdDismissed(InMobiInterstitial arg0) {
            }
        }

        C02122() {
        }

        public void run() {
            super.showFsBanner();
            InmobiSdkWrapper.this.interstitial = new InMobiInterstitial(InmobiSdkWrapper.this._activity, Long.parseLong(InmobiSdkWrapper.this.placementId), new C09911());
            InmobiSdkWrapper.this.interstitial.load();
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_INTERSTITIAL_REQUEST_URL);
        }
    }

    public InmobiSdkWrapper() {
        this.interstitial = null;
        this.accountId = StringUtils.EMPTY;
        this.placementId = StringUtils.EMPTY;
    }

    public void startSession(Activity activity) {
        activity.runOnUiThread(new C02111(activity));
    }

    public void showFsBanner() {
        this._activity.runOnUiThread(new C02122());
    }
}
