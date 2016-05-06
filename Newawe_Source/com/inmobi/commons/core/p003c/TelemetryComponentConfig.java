package com.inmobi.commons.core.p003c;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.c.b */
public class TelemetryComponentConfig {
    private int f1178a;
    private String f1179b;
    private boolean f1180c;
    private boolean f1181d;
    private Map<String, TelemetryComponentConfig> f1182e;

    /* renamed from: com.inmobi.commons.core.c.b.a */
    static final class TelemetryComponentConfig {
        private String f1175a;
        private int f1176b;
        private boolean f1177c;

        public TelemetryComponentConfig(String str, int i, boolean z) {
            m1300a(str);
            m1299a(i);
            m1301a(z);
        }

        public String m1298a() {
            return this.f1175a;
        }

        public int m1302b() {
            return this.f1176b;
        }

        public void m1300a(String str) {
            this.f1175a = str;
        }

        public void m1299a(int i) {
            this.f1176b = i;
        }

        public void m1301a(boolean z) {
            this.f1177c = z;
        }

        public boolean m1303c() {
            return this.f1177c;
        }
    }

    public TelemetryComponentConfig() {
        this.f1178a = 0;
        this.f1179b = "telemetry";
        this.f1180c = false;
        this.f1181d = false;
        this.f1182e = new HashMap();
    }

    public TelemetryComponentConfig(String str, JSONObject jSONObject, TelemetryComponentConfig telemetryComponentConfig) {
        this.f1178a = 0;
        this.f1179b = "telemetry";
        this.f1180c = false;
        this.f1181d = false;
        this.f1182e = new HashMap();
        if (jSONObject == null) {
            m1304a(str, telemetryComponentConfig);
            return;
        }
        String str2;
        JSONArray jSONArray;
        int i;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    str2 = str;
                    this.f1179b = str2;
                    this.f1180c = jSONObject.has("enabled") ? jSONObject.getBoolean("enabled") : true;
                    m1307a(jSONObject.has("samplingFactor") ? jSONObject.getInt("samplingFactor") : telemetryComponentConfig.m1312c());
                    m1308a(jSONObject.has("metricEnabled") ? jSONObject.getBoolean("metricEnabled") : telemetryComponentConfig.m1313d());
                    this.f1182e = new HashMap();
                    if (jSONObject.has("events")) {
                        jSONArray = jSONObject.getJSONArray("events");
                        for (i = 0; i < jSONArray.length(); i++) {
                            TelemetryComponentConfig telemetryComponentConfig2 = new TelemetryComponentConfig();
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            telemetryComponentConfig2.m1300a(jSONObject2.getString("type"));
                            telemetryComponentConfig2.m1299a(jSONObject2.has("samplingFactor") ? jSONObject2.getInt("samplingFactor") : m1312c());
                            telemetryComponentConfig2.m1301a(jSONObject2.has("metricEnabled") ? jSONObject2.getBoolean("metricEnabled") : m1313d());
                            this.f1182e.put(telemetryComponentConfig2.m1298a(), telemetryComponentConfig2);
                        }
                    }
                }
            } catch (JSONException e) {
                m1304a(str, telemetryComponentConfig);
                return;
            }
        }
        str2 = telemetryComponentConfig.m1306a();
        this.f1179b = str2;
        if (jSONObject.has("enabled")) {
        }
        this.f1180c = jSONObject.has("enabled") ? jSONObject.getBoolean("enabled") : true;
        if (jSONObject.has("samplingFactor")) {
        }
        m1307a(jSONObject.has("samplingFactor") ? jSONObject.getInt("samplingFactor") : telemetryComponentConfig.m1312c());
        if (jSONObject.has("metricEnabled")) {
        }
        m1308a(jSONObject.has("metricEnabled") ? jSONObject.getBoolean("metricEnabled") : telemetryComponentConfig.m1313d());
        this.f1182e = new HashMap();
        if (jSONObject.has("events")) {
            jSONArray = jSONObject.getJSONArray("events");
            for (i = 0; i < jSONArray.length(); i++) {
                TelemetryComponentConfig telemetryComponentConfig22 = new TelemetryComponentConfig();
                JSONObject jSONObject22 = jSONArray.getJSONObject(i);
                telemetryComponentConfig22.m1300a(jSONObject22.getString("type"));
                if (jSONObject22.has("samplingFactor")) {
                }
                telemetryComponentConfig22.m1299a(jSONObject22.has("samplingFactor") ? jSONObject22.getInt("samplingFactor") : m1312c());
                if (jSONObject22.has("metricEnabled")) {
                }
                telemetryComponentConfig22.m1301a(jSONObject22.has("metricEnabled") ? jSONObject22.getBoolean("metricEnabled") : m1313d());
                this.f1182e.put(telemetryComponentConfig22.m1298a(), telemetryComponentConfig22);
            }
        }
    }

    public String m1306a() {
        return this.f1179b;
    }

    public boolean m1311b() {
        return this.f1180c;
    }

    public TelemetryComponentConfig m1305a(String str) {
        TelemetryComponentConfig telemetryComponentConfig = (TelemetryComponentConfig) this.f1182e.get(str);
        return telemetryComponentConfig != null ? telemetryComponentConfig : new TelemetryComponentConfig(str, m1312c(), m1313d());
    }

    private void m1304a(String str, TelemetryComponentConfig telemetryComponentConfig) {
        m1310b(true);
        m1309b(str);
    }

    public int m1312c() {
        return this.f1178a;
    }

    public boolean m1313d() {
        return this.f1181d;
    }

    public void m1308a(boolean z) {
        this.f1181d = z;
    }

    public void m1310b(boolean z) {
        this.f1180c = z;
    }

    public void m1307a(int i) {
        this.f1178a = i;
    }

    public void m1309b(String str) {
        this.f1179b = str;
    }
}
