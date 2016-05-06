package com.inmobi.signals.activityrecognition;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.activityrecognition.a */
public class ActivityInfo {
    private static final String f1701a;
    private int f1702b;
    private long f1703c;

    static {
        f1701a = ActivityInfo.class.getSimpleName();
    }

    public ActivityInfo(int i, long j) {
        this.f1702b = i;
        this.f1703c = j;
    }

    public JSONObject m1881a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f1702b);
            jSONObject.put("ts", this.f1703c);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1701a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
