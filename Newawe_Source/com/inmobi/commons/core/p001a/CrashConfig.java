package com.inmobi.commons.core.p001a;

import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.a.a */
public class CrashConfig extends Config {
    private static final String f3805a;
    private JSONObject f3806b;

    static {
        f3805a = CrashConfig.class.getSimpleName();
    }

    private JSONObject m4439f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public CrashConfig() {
        try {
            this.f3806b = m4439f();
        } catch (Exception e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3805a, "Error in default telemetry config");
        }
    }

    public void m4441a(JSONObject jSONObject) {
        try {
            super.m1346a(jSONObject);
            this.f3806b = jSONObject.getJSONObject("telemetry");
        } catch (JSONException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3805a, "Error parsing Crash Config " + e.toString());
        }
    }

    public JSONObject m4442b() {
        try {
            JSONObject b = super.m1347b();
            b.put("telemetry", this.f3806b);
            return b;
        } catch (JSONException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3805a, "Error parsing Crash Config " + e.toString());
            return null;
        }
    }

    public JSONObject m4445e() {
        return this.f3806b;
    }

    public String m4440a() {
        return "crashReporting";
    }

    public boolean m4443c() {
        return true;
    }

    public Config m4444d() {
        return new CrashConfig();
    }
}
