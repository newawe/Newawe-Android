package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.p007b.WifiInfo;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.m */
public class IceWifiSample {
    private static final String f1754a;
    private long f1755b;
    private WifiInfo f1756c;
    private List<WifiInfo> f1757d;

    static {
        f1754a = IceWifiSample.class.getSimpleName();
    }

    public IceWifiSample() {
        m1974a(Calendar.getInstance().getTimeInMillis());
    }

    public void m1974a(long j) {
        this.f1755b = j;
    }

    public void m1975a(WifiInfo wifiInfo) {
        this.f1756c = wifiInfo;
    }

    public void m1976a(List<WifiInfo> list) {
        this.f1757d = list;
    }

    public boolean m1977a() {
        return (this.f1756c == null && this.f1757d == null) ? false : true;
    }

    public JSONObject m1978b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.f1755b);
            if (this.f1756c != null) {
                jSONObject.put("c-ap", this.f1756c.m1895b());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.f1757d != null) {
                for (int i = 0; i < this.f1757d.size(); i++) {
                    jSONArray.put(((WifiInfo) this.f1757d.get(i)).m1895b());
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("v-ap", jSONArray);
                }
            }
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1754a, "Error while converting IceWifiCellSample to string.", e);
        }
        return jSONObject;
    }
}
