package com.inmobi.signals.p007b;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.b.a */
public class WifiInfo {
    private static final String f1710a;
    private long f1711b;
    private String f1712c;
    private int f1713d;
    private int f1714e;

    static {
        f1710a = WifiInfo.class.getSimpleName();
    }

    public void m1893a(long j) {
        this.f1711b = j;
    }

    public void m1894a(String str) {
        this.f1712c = str;
    }

    public void m1892a(int i) {
        this.f1713d = i;
    }

    public void m1896b(int i) {
        this.f1714e = i;
    }

    public long m1891a() {
        return this.f1711b;
    }

    public JSONObject m1895b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", this.f1711b);
            jSONObject.put("essid", this.f1712c);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1710a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
