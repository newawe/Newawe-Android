package com.inmobi.signals;

import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.q */
class SignalsConfig extends Config {
    private static final String f3904a;
    private SignalsConfig f3905b;
    private SignalsConfig f3906c;
    private JSONObject f3907d;

    /* renamed from: com.inmobi.signals.q.a */
    public static class SignalsConfig {
        private boolean f1768a;
        private String f1769b;
        private String f1770c;
        private int f1771d;
        private int f1772e;
        private int f1773f;
        private int f1774g;
        private long f1775h;

        public SignalsConfig() {
            this.f1768a = false;
            this.f1769b = "http://dock.inmobi.com/carb/v1/i";
            this.f1770c = "http://dock.inmobi.com/carb/v1/o";
            this.f1771d = 86400;
            this.f1772e = 3;
            this.f1773f = 60;
            this.f1774g = 60;
            this.f1775h = 307200;
        }

        public boolean m2015a() {
            return this.f1768a;
        }

        public String m2016b() {
            return this.f1769b;
        }

        public String m2017c() {
            return this.f1770c;
        }

        public int m2018d() {
            return this.f1771d;
        }

        public int m2019e() {
            return this.f1772e;
        }

        public int m2020f() {
            return this.f1773f;
        }

        public int m2021g() {
            return this.f1774g;
        }

        public long m2022h() {
            return this.f1775h;
        }
    }

    /* renamed from: com.inmobi.signals.q.b */
    public static class SignalsConfig {
        private boolean f1776a;
        private int f1777b;
        private int f1778c;
        private int f1779d;
        private String f1780e;
        private int f1781f;
        private int f1782g;
        private boolean f1783h;
        private boolean f1784i;
        private int f1785j;
        private boolean f1786k;
        private boolean f1787l;
        private int f1788m;
        private boolean f1789n;
        private boolean f1790o;
        private boolean f1791p;
        private boolean f1792q;
        private int f1793r;
        private int f1794s;

        public SignalsConfig() {
            this.f1776a = false;
            this.f1777b = HttpStatus.SC_MULTIPLE_CHOICES;
            this.f1778c = 3;
            this.f1779d = 50;
            this.f1780e = "https://sdkm.w.inmobi.com/user/e.asm";
            this.f1781f = 3;
            this.f1782g = 60;
            this.f1783h = false;
            this.f1784i = false;
            this.f1785j = 0;
            this.f1786k = false;
            this.f1787l = false;
            this.f1788m = 0;
            this.f1789n = false;
            this.f1790o = false;
            this.f1791p = false;
            this.f1792q = false;
            this.f1793r = 180;
            this.f1794s = 50;
        }

        public boolean m2061a() {
            return this.f1776a;
        }

        public int m2062b() {
            return this.f1777b;
        }

        public int m2063c() {
            return this.f1778c;
        }

        public int m2064d() {
            return this.f1779d;
        }

        public String m2065e() {
            return this.f1780e;
        }

        public int m2066f() {
            return this.f1781f;
        }

        public int m2067g() {
            return this.f1782g;
        }

        public boolean m2068h() {
            return this.f1783h && this.f1776a;
        }

        public boolean m2069i() {
            return this.f1784i && this.f1776a;
        }

        public int m2070j() {
            return this.f1785j;
        }

        public boolean m2071k() {
            return this.f1786k && this.f1776a;
        }

        public boolean m2072l() {
            return this.f1787l && this.f1776a;
        }

        public int m2073m() {
            return this.f1788m;
        }

        public boolean m2074n() {
            return this.f1789n && this.f1776a;
        }

        public boolean m2075o() {
            return this.f1790o && this.f1776a;
        }

        public boolean m2076p() {
            return this.f1791p && this.f1776a;
        }

        public boolean m2077q() {
            return this.f1792q && this.f1776a;
        }

        public int m2078r() {
            return this.f1793r;
        }

        public int m2079s() {
            return this.f1794s;
        }
    }

    static {
        f3904a = Config.class.getSimpleName();
    }

    private JSONObject m4590h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public SignalsConfig() {
        this.f3905b = new SignalsConfig();
        this.f3906c = new SignalsConfig();
        try {
            this.f3907d = m4590h();
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3904a, "Default telemetry config provided for ads is invalid.", e);
        }
    }

    public String m4591a() {
        return "signals";
    }

    public void m4592a(JSONObject jSONObject) throws JSONException {
        super.m1346a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("ice");
        this.f3905b.f1777b = jSONObject2.getInt("sampleInterval");
        this.f3905b.f1779d = jSONObject2.getInt("sampleHistorySize");
        this.f3905b.f1778c = jSONObject2.getInt("stopRequestTimeout");
        this.f3905b.f1776a = jSONObject2.getBoolean("enabled");
        this.f3905b.f1780e = jSONObject2.getString("endPoint");
        this.f3905b.f1781f = jSONObject2.getInt("maxRetries");
        this.f3905b.f1782g = jSONObject2.getInt("retryInterval");
        this.f3905b.f1783h = jSONObject2.getBoolean("locationEnabled");
        this.f3905b.f1784i = jSONObject2.getBoolean("sessionEnabled");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("w");
        this.f3905b.f1785j = jSONObject3.getInt("wf");
        this.f3905b.f1787l = jSONObject3.getBoolean("cwe");
        this.f3905b.f1786k = jSONObject3.getBoolean("vwe");
        jSONObject3 = jSONObject2.getJSONObject("c");
        this.f3905b.f1789n = jSONObject3.getBoolean("oe");
        this.f3905b.f1791p = jSONObject3.getBoolean("cce");
        this.f3905b.f1790o = jSONObject3.getBoolean("vce");
        this.f3905b.f1788m = jSONObject3.getInt("cof");
        jSONObject2 = jSONObject2.getJSONObject("ar");
        this.f3905b.f1792q = jSONObject2.getBoolean("e");
        this.f3905b.f1793r = jSONObject2.getInt("sampleInterval");
        this.f3905b.f1794s = jSONObject2.getInt("maxHistorySize");
        jSONObject2 = jSONObject.getJSONObject("carb");
        this.f3906c.f1768a = jSONObject2.getBoolean("enabled");
        this.f3906c.f1769b = jSONObject2.getString("getEndPoint");
        this.f3906c.f1770c = jSONObject2.getString("postEndPoint");
        this.f3906c.f1771d = jSONObject2.getInt("retrieveFrequency");
        this.f3906c.f1772e = jSONObject2.getInt("maxRetries");
        this.f3906c.f1773f = jSONObject2.getInt("retryInterval");
        this.f3906c.f1774g = jSONObject2.getInt("timeoutInterval");
        this.f3906c.f1775h = jSONObject2.getLong("maxGetResponseSize");
        this.f3907d = jSONObject.optJSONObject("telemetry");
    }

    public JSONObject m4593b() throws JSONException {
        JSONObject b = super.m1347b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sampleInterval", this.f3905b.f1777b);
        jSONObject.put("stopRequestTimeout", this.f3905b.f1778c);
        jSONObject.put("sampleHistorySize", this.f3905b.f1779d);
        jSONObject.put("enabled", this.f3905b.f1776a);
        jSONObject.put("endPoint", this.f3905b.f1780e);
        jSONObject.put("maxRetries", this.f3905b.f1781f);
        jSONObject.put("retryInterval", this.f3905b.f1782g);
        jSONObject.put("locationEnabled", this.f3905b.f1783h);
        jSONObject.put("sessionEnabled", this.f3905b.f1784i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("wf", this.f3905b.f1785j);
        jSONObject2.put("vwe", this.f3905b.f1786k);
        jSONObject2.put("cwe", this.f3905b.f1787l);
        jSONObject.put("w", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("cof", this.f3905b.f1788m);
        jSONObject2.put("vce", this.f3905b.f1790o);
        jSONObject2.put("cce", this.f3905b.f1791p);
        jSONObject2.put("oe", this.f3905b.f1789n);
        jSONObject.put("c", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("e", this.f3905b.f1792q);
        jSONObject2.put("sampleInterval", this.f3905b.f1793r);
        jSONObject2.put("maxHistorySize", this.f3905b.f1794s);
        jSONObject.put("ar", jSONObject2);
        b.put("ice", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("enabled", this.f3906c.f1768a);
        jSONObject.put("getEndPoint", this.f3906c.f1769b);
        jSONObject.put("postEndPoint", this.f3906c.f1770c);
        jSONObject.put("retrieveFrequency", this.f3906c.f1771d);
        jSONObject.put("maxRetries", this.f3906c.f1772e);
        jSONObject.put("retryInterval", this.f3906c.f1773f);
        jSONObject.put("timeoutInterval", this.f3906c.f1774g);
        jSONObject.put("maxGetResponseSize", this.f3906c.m2022h());
        b.put("carb", jSONObject);
        b.put("telemetry", this.f3907d);
        return b;
    }

    public boolean m4594c() {
        if (this.f3905b.f1777b < 0 || this.f3905b.f1779d < 0 || this.f3905b.f1778c < 0 || this.f3905b.f1780e.trim().length() == 0 || this.f3905b.f1781f < 0 || this.f3905b.f1782g < 0 || this.f3905b.m2070j() < 0 || this.f3905b.m2073m() < 0 || this.f3905b.f1794s < 0 || this.f3905b.f1793r < 0 || this.f3906c.f1769b.trim().length() == 0 || this.f3906c.f1770c.trim().length() == 0) {
            return false;
        }
        if (!this.f3906c.f1769b.startsWith("http://") && !this.f3906c.f1769b.startsWith("https://")) {
            return false;
        }
        if ((this.f3906c.f1770c.startsWith("http://") || this.f3906c.f1770c.startsWith("https://")) && this.f3906c.f1771d >= 0 && this.f3906c.f1772e >= 0 && this.f3906c.f1773f >= 0 && this.f3906c.f1774g >= 0 && this.f3906c.f1775h >= 0) {
            return true;
        }
        return false;
    }

    public Config m4595d() {
        return new SignalsConfig();
    }

    public JSONObject m4596e() {
        return this.f3907d;
    }

    public SignalsConfig m4597f() {
        return this.f3905b;
    }

    public SignalsConfig m4598g() {
        return this.f3906c;
    }
}
