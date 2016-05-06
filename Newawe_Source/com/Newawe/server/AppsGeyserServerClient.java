package com.Newawe.server;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.MainNavigationActivity.ApplicationMode;
import com.Newawe.server.BaseServerClient.OnRequestDoneListener;
import com.Newawe.utils.Geolocation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;

public class AppsGeyserServerClient extends BaseServerClient implements OnRequestDoneListener {
    private static final String FIRST_START_KEY = "isFirstStart";
    private static final int FORBIDDEN_RESPONSE = 403;
    private static final int OK_RESPONSE = 200;
    private static AppsGeyserServerClient instance;
    private String _appGuid;
    private Handler handler;
    private ApplicationMode mAppMode;

    /* renamed from: com.Newawe.server.AppsGeyserServerClient.1 */
    class C02521 extends Handler {
        C02521() {
        }

        public void handleMessage(Message msg) {
            if (msg.what == RequestType.AFTERINSTALL.ordinal() && AppsGeyserServerClient.this._config.getPushAccount().length() == 0) {
                AppsGeyserServerClient.this._activity.updatePushAccount();
            }
            super.handleMessage(msg);
        }
    }

    /* renamed from: com.Newawe.server.AppsGeyserServerClient.2 */
    class C02532 implements Runnable {
        C02532() {
        }

        public void run() {
            AppsGeyserServerClient.this._activity.setApplicationMode(AppsGeyserServerClient.this.mAppMode);
        }
    }

    /* renamed from: com.Newawe.server.AppsGeyserServerClient.3 */
    class C02543 implements Runnable {
        C02543() {
        }

        public void run() {
            AppsGeyserServerClient.this._activity.showPausedContentInfo();
        }
    }

    enum RequestType {
        AFTERINSTALL,
        USAGE,
        CLICK,
        APPMODE
    }

    public static AppsGeyserServerClient getInstance(MainNavigationActivity activity) {
        if (instance == null) {
            instance = new AppsGeyserServerClient(activity);
        }
        return instance;
    }

    public void destroy() {
        instance = null;
    }

    private AppsGeyserServerClient(MainNavigationActivity activity) {
        super(activity);
        this._appGuid = StringUtils.EMPTY;
        this.mAppMode = ApplicationMode.UNKNOWN;
        this.handler = new C02521();
        this._appGuid = this._config.getAppGuid();
        if (this._appGuid.length() == 0) {
            this._appGuid = this._config.loadGuid(activity);
        }
    }

    public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
        if (tag == RequestType.APPMODE.ordinal()) {
            if (response.getStatusLine().getStatusCode() == OK_RESPONSE) {
                this.mAppMode = ApplicationMode.CUSTOM;
            } else if (response.getStatusLine().getStatusCode() == FORBIDDEN_RESPONSE) {
                this.mAppMode = ApplicationMode.COMMON;
            }
            this._activity.runOnUiThread(new C02532());
        }
        if (response.getStatusLine().getStatusCode() == OK_RESPONSE) {
            this.handler.sendEmptyMessage(tag);
        } else if (response.getStatusLine().getStatusCode() == FORBIDDEN_RESPONSE && tag == RequestType.USAGE.ordinal()) {
            this._activity.runOnUiThread(new C02543());
        } else {
            this.handler.sendEmptyMessage(-1);
        }
    }

    public void SendAfterInstallInfo() {
        if (_isFirstStart()) {
            sendRequestAsync(this._config.getRegisteredUrl() + "?action=install&name=" + String.valueOf(this._config.getApplicationId()) + "&id=" + this._appGuid + "&v=" + this._context.getString(C0186R.string.platformVersion) + "&p=android", RequestType.AFTERINSTALL.ordinal(), this);
        }
    }

    public void SendUsageInfo() {
        sendRequestAsync(this._config.getAddUsageUrl() + "?action=usage&name=" + String.valueOf(this._config.getApplicationId()) + "&id=" + this._appGuid + "&v=" + this._context.getString(C0186R.string.platformVersion) + "&p=android", RequestType.USAGE.ordinal(), this);
    }

    public void GetApplicationMode() {
        sendRequestAsync(this._context.getResources().getString(C0186R.string.getAppModeUrl) + "?wid=" + String.valueOf(this._config.getApplicationId()), RequestType.APPMODE.ordinal(), this);
    }

    public String GetBannerUrl() {
        return Factory.getInstance().getMainNavigationActivity().getResources().getString(C0186R.string.adsDomainUrl) + _getBaseBannerQueryString();
    }

    public String getOnExitFullScreenBannerUrl() {
        return Factory.getInstance().getMainNavigationActivity().getResources().getString(C0186R.string.fullScreenBannerUrl) + _getOnExitBannerQueryString();
    }

    public String getOnStartFullScreenBannerUrl() {
        return Factory.getInstance().getMainNavigationActivity().getResources().getString(C0186R.string.fullScreenBannerUrl) + _getOnStartBannerQueryString();
    }

    private String _getOnStartBannerQueryString() {
        return _getBaseBannerQueryString() + "&action=onstart";
    }

    private String _getOnExitBannerQueryString() {
        return _getBaseBannerQueryString() + "&action=onexit";
    }

    private String _getBaseBannerQueryString() {
        MainNavigationActivity activity = Factory.getInstance().getMainNavigationActivity();
        String version = activity.getResources().getString(C0186R.string.platformVersion);
        String strUrl = StringUtils.EMPTY;
        String deviceIdSection = StringUtils.EMPTY;
        String advId = activity.getDeviceIdParameters().getAdvid();
        String limitAdTrackingEnabled = activity.getDeviceIdParameters().getLimitAdTrackingEnabled().toString().toLowerCase();
        String aid = activity.getDeviceIdParameters().getAid();
        if (advId == null || advId.equals(StringUtils.EMPTY)) {
            deviceIdSection = "&aid=" + aid;
        } else {
            deviceIdSection = "&advid=" + advId + "&limit_ad_tracking_enabled=" + limitAdTrackingEnabled;
        }
        double[] coords = Geolocation.getCoords(activity);
        return "?widgetid=" + this._config.getApplicationId() + "&guid=" + this._appGuid + "&v=" + version + deviceIdSection + "&tlat=" + coords[0] + "&tlon=" + coords[1] + "&p=android";
    }

    public void SendClickInfo(String reportUrl) {
        sendRequestAsync(reportUrl, RequestType.CLICK.ordinal(), this);
    }

    private boolean _isFirstStart() {
        SharedPreferences preferences = this._activity.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0);
        boolean firstStart = preferences.getBoolean(FIRST_START_KEY, true);
        if (firstStart) {
            preferences.edit().putBoolean(FIRST_START_KEY, false).commit();
        }
        return firstStart;
    }
}
