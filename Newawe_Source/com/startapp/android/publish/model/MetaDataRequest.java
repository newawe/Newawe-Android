package com.startapp.android.publish.model;

import android.content.Context;
import com.startapp.android.publish.p022h.StartAppSDK;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;

/* compiled from: StartAppSDK */
public class MetaDataRequest extends BaseRequest {
    private int daysSinceFirstSession;
    private float paidAmount;
    private boolean payingUser;
    private String profileId;
    private RequestReason reason;
    private int totalSessions;

    /* compiled from: StartAppSDK */
    public enum RequestReason {
        LAUNCH(1),
        APP_IDLE(2),
        IN_APP_PURCHASE(3),
        CUSTOM(4);
        
        private int index;

        private RequestReason(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public static RequestReason getByIndex(int index) {
            RequestReason requestReason = APP_IDLE;
            RequestReason[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    requestReason = values[i];
                }
            }
            return requestReason;
        }
    }

    public MetaDataRequest(Context context, RequestReason reason) {
        this.totalSessions = StartAppSDK.m2905a(context, "totalSessions", Integer.valueOf(0)).intValue();
        this.daysSinceFirstSession = calcDaysSinceFirstSession(context);
        this.paidAmount = StartAppSDK.m2904a(context, "inAppPurchaseAmount", Float.valueOf(0.0f)).floatValue();
        this.payingUser = StartAppSDK.m2903a(context, "payingUser", Boolean.valueOf(false)).booleanValue();
        this.profileId = MetaData.getInstance().getProfileId();
        this.reason = reason;
    }

    private int calcDaysSinceFirstSession(Context context) {
        return millisToDays(System.currentTimeMillis() - StartAppSDK.m2906a(context, "firstSessionTime", Long.valueOf(System.currentTimeMillis())).longValue());
    }

    private int millisToDays(long millis) {
        return (int) (millis / DateUtils.MILLIS_PER_DAY);
    }

    public int getTotalSessions() {
        return this.totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public int getDaysSinceFirstSession() {
        return this.daysSinceFirstSession;
    }

    public void setDaysSinceFirstSession(int daysSinceFirstSession) {
        this.daysSinceFirstSession = daysSinceFirstSession;
    }

    public boolean isPayingUser() {
        return this.payingUser;
    }

    public void setPayingUser(boolean payingUser) {
        this.payingUser = payingUser;
    }

    public float getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
        this.paidAmount = paidAmount;
    }

    public RequestReason getReason() {
        return this.reason;
    }

    public void setReason(RequestReason reason) {
        this.reason = reason;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String toString() {
        return "MetaDataRequest [totalSessions=" + this.totalSessions + ", daysSinceFirstSession=" + this.daysSinceFirstSession + ", payingUser=" + this.payingUser + ", paidAmount=" + this.paidAmount + ", reason=" + this.reason + ", profileId=" + this.profileId + "]";
    }

    public List<NameValueObject> getNameValueMap() {
        List nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new ArrayList();
        }
        StartAppSDK.m3013a(nameValueMap, "totalSessions", Integer.valueOf(this.totalSessions), true);
        StartAppSDK.m3013a(nameValueMap, "daysSinceFirstSession", Integer.valueOf(this.daysSinceFirstSession), true);
        StartAppSDK.m3013a(nameValueMap, "payingUser", Boolean.valueOf(this.payingUser), true);
        StartAppSDK.m3013a(nameValueMap, "profileId", this.profileId, false);
        StartAppSDK.m3013a(nameValueMap, "paidAmount", Float.valueOf(this.paidAmount), true);
        StartAppSDK.m3013a(nameValueMap, "reason", this.reason, true);
        return nameValueMap;
    }
}
