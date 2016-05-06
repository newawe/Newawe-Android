package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.c */
public class CarbGetListNetworkResponse {
    private static final String f1724a;
    private NetworkResponse f1725b;
    private boolean f1726c;
    private List<CarbInfo> f1727d;
    private String f1728e;
    private int f1729f;

    static {
        f1724a = CarbGetListNetworkResponse.class.getSimpleName();
    }

    public CarbGetListNetworkResponse(NetworkResponse networkResponse) {
        this.f1726c = true;
        this.f1729f = 0;
        this.f1725b = networkResponse;
        this.f1727d = new ArrayList();
        m1925f();
        if (this.f1726c) {
            TelemetryComponent.m4448a().m4468a(new TelemetryEvent("signals", "InvalidCarbGetResponse"));
        }
    }

    private void m1925f() {
        if (this.f1725b.m1433a()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1724a, "Error response for Carb list received. Error code:" + this.f1725b.m1435c().m1395a());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f1725b.m1434b());
            if (jSONObject.getBoolean("success")) {
                jSONObject = jSONObject.getJSONObject("data");
                this.f1728e = jSONObject.getString("req_id");
                JSONArray jSONArray = jSONObject.getJSONArray("p_apps");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("bid", null);
                    String optString2 = jSONObject2.optString("inm_id", null);
                    if (!(optString == null || optString2 == null || optString2.trim().length() <= 0)) {
                        this.f1727d.add(new CarbInfo(optString, optString2));
                    }
                    this.f1729f = i + 1;
                }
            } else {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1724a, "Error response for Carb list received. Error code:" + jSONObject.optInt("errorCode"));
            }
            this.f1726c = false;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1724a, "Bad response for Carb list received.", e);
        }
    }

    public boolean m1926a() {
        return this.f1726c;
    }

    public List<CarbInfo> m1927b() {
        return this.f1727d;
    }

    public String m1928c() {
        return this.f1728e;
    }

    public int m1929d() {
        return this.f1729f;
    }

    public boolean m1930e() {
        return this.f1729f == 0;
    }
}
