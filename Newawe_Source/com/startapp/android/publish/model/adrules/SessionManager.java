package com.startapp.android.publish.model.adrules;

import android.content.Context;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.MetaDataRequest.RequestReason;
import com.startapp.android.publish.p022h.StartAppSDK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class SessionManager {
    private static final String TAG = "SessionManager";
    private static SessionManager instance;
    private Map<String, List<AdDisplayEvent>> adTagToAdDisplayEvents;
    private Map<Placement, List<AdDisplayEvent>> placementToAdDisplayEvents;
    private RequestReason reason;
    private List<AdDisplayEvent> sessionAdDisplayEvents;
    private String sessionId;
    private long sessionStartTime;

    public SessionManager() {
        this.sessionAdDisplayEvents = new ArrayList();
        this.placementToAdDisplayEvents = new HashMap();
        this.adTagToAdDisplayEvents = new HashMap();
        this.sessionId = StringUtils.EMPTY;
        this.sessionStartTime = 0;
        this.reason = RequestReason.LAUNCH;
    }

    static {
        instance = new SessionManager();
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public long getSessionStartTime() {
        return this.sessionStartTime;
    }

    public RequestReason getSessionRequestReason() {
        return this.reason;
    }

    public List<AdDisplayEvent> getAdDisplayEvents() {
        return this.sessionAdDisplayEvents;
    }

    public List<AdDisplayEvent> getAdDisplayEvents(Placement placement) {
        return (List) this.placementToAdDisplayEvents.get(placement);
    }

    public List<AdDisplayEvent> getAdDisplayEvents(String adTag) {
        return (List) this.adTagToAdDisplayEvents.get(adTag);
    }

    public synchronized void startNewSession(Context context, RequestReason reason) {
        this.sessionId = UUID.randomUUID().toString();
        this.sessionStartTime = System.currentTimeMillis();
        this.reason = reason;
        StartAppSDK.m2928a(TAG, 3, "Starting new session: reason=" + reason + " sessionId=" + this.sessionId);
        this.sessionAdDisplayEvents.clear();
        this.placementToAdDisplayEvents.clear();
        this.adTagToAdDisplayEvents.clear();
        AdPreferences adPreferences = new AdPreferences();
        StartAppSDK.m2996a(context, adPreferences);
        MetaData.getInstance().loadFromServer(context, adPreferences, reason, false, null, true);
    }

    public synchronized void addAdDisplayEvent(AdDisplayEvent adDisplayEvent) {
        StartAppSDK.m2928a(TAG, 3, "Adding new " + adDisplayEvent);
        this.sessionAdDisplayEvents.add(0, adDisplayEvent);
        List list = (List) this.placementToAdDisplayEvents.get(adDisplayEvent.getPlacement());
        if (list == null) {
            list = new ArrayList();
            this.placementToAdDisplayEvents.put(adDisplayEvent.getPlacement(), list);
        }
        list.add(0, adDisplayEvent);
        list = (List) this.adTagToAdDisplayEvents.get(adDisplayEvent.getAdTag());
        if (list == null) {
            list = new ArrayList();
            this.adTagToAdDisplayEvents.put(adDisplayEvent.getAdTag(), list);
        }
        list.add(0, adDisplayEvent);
    }

    public int getNumOfAdsDisplayed() {
        return this.sessionAdDisplayEvents.size();
    }

    public static SessionManager getInstance() {
        return instance;
    }
}
