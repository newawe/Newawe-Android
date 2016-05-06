package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.utilities.info.f */
public class SessionInfo {
    private static final String f1309a;
    private static SessionInfo f1310b;
    private static Object f1311c;
    private String f1312d;
    private long f1313e;
    private long f1314f;
    private boolean f1315g;

    static {
        f1309a = SessionInfo.class.getSimpleName();
        f1311c = new Object();
    }

    public static SessionInfo m1525a() {
        SessionInfo sessionInfo = f1310b;
        if (sessionInfo == null) {
            synchronized (f1311c) {
                sessionInfo = f1310b;
                if (sessionInfo == null) {
                    f1310b = new SessionInfo();
                    sessionInfo = f1310b;
                }
            }
        }
        return sessionInfo;
    }

    private SessionInfo() {
    }

    public void m1528a(String str) {
        this.f1312d = str;
    }

    public void m1527a(long j) {
        this.f1313e = j;
    }

    public void m1531b(long j) {
        this.f1314f = j;
    }

    public JSONObject m1530b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", this.f1312d);
            jSONObject.put("s-ts", this.f1313e);
            jSONObject.put("e-ts", this.f1314f);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1309a, "Problem converting session object to Json.", e);
        }
        return jSONObject;
    }

    public void m1529a(boolean z) {
        this.f1315g = z;
        if (!this.f1315g) {
            m1526d();
        }
    }

    private void m1526d() {
        this.f1312d = null;
        this.f1313e = 0;
        this.f1314f = 0;
    }

    public HashMap<String, String> m1532c() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.f1315g && this.f1312d != null) {
            hashMap.put("u-s-id", this.f1312d);
        }
        return hashMap;
    }
}
