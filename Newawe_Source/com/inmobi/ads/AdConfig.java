package com.inmobi.ads;

import android.graphics.Color;
import com.Newawe.storage.DatabaseOpenHelper;
import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.ads.b */
public final class AdConfig extends Config {
    private static final String f3774a;
    private static final String f3775b;
    private String f3776c;
    private int f3777d;
    private int f3778e;
    private int f3779f;
    private AdConfig f3780g;
    private Map<String, AdConfig> f3781h;
    private AdConfig f3782i;
    private AdConfig f3783j;
    private AdConfig f3784k;
    private AdConfig f3785l;
    private JSONObject f3786m;

    /* renamed from: com.inmobi.ads.b.a */
    static final class AdConfig {
        private int f1053a;
        private int f1054b;
        private int f1055c;
        private long f1056d;

        AdConfig() {
        }

        public boolean m1140a() {
            if (this.f1054b <= 0 || this.f1053a < 0 || this.f1055c < 0 || this.f1056d < 0) {
                return false;
            }
            return true;
        }

        public int m1141b() {
            return this.f1053a;
        }

        public int m1142c() {
            return this.f1054b;
        }

        public int m1143d() {
            return this.f1055c;
        }

        public long m1144e() {
            return this.f1056d;
        }
    }

    /* renamed from: com.inmobi.ads.b.b */
    public static final class AdConfig {
        private int f1057a;
        private int f1058b;
        private int f1059c;
        private int f1060d;
        private int f1061e;

        public AdConfig() {
            this.f1057a = 3;
            this.f1058b = 60;
            this.f1059c = 120;
            this.f1060d = HttpStatus.SC_INTERNAL_SERVER_ERROR;
            this.f1061e = 10;
        }

        public int m1150a() {
            return this.f1057a;
        }

        public int m1151b() {
            return this.f1058b;
        }

        public int m1152c() {
            return this.f1059c;
        }

        public int m1153d() {
            return this.f1060d;
        }

        public int m1154e() {
            return this.f1061e;
        }
    }

    /* renamed from: com.inmobi.ads.b.c */
    public static final class AdConfig {
        private long f1062a;
        private int f1063b;
        private int f1064c;
        private String f1065d;

        public AdConfig() {
            this.f1062a = 432000;
            this.f1063b = 3;
            this.f1064c = 60;
            this.f1065d = "https://inmobisdk-a.akamaihd.net/sdk/500/android/mraid.js";
        }

        public long m1159a() {
            return this.f1062a;
        }

        public int m1160b() {
            return this.f1063b;
        }

        public int m1161c() {
            return this.f1064c;
        }

        public String m1162d() {
            return this.f1065d;
        }
    }

    /* renamed from: com.inmobi.ads.b.d */
    public static final class AdConfig {
        private int f1066a;
        private int f1067b;
        private int f1068c;
        private int f1069d;
        private String f1070e;
        private int f1071f;
        private int f1072g;
        private int f1073h;
        private long f1074i;
        private ArrayList<String> f1075j;

        public AdConfig() {
            this.f1066a = 60;
            this.f1067b = 320;
            this.f1068c = 480;
            this.f1069d = 100;
            this.f1070e = "#00000000";
            this.f1071f = Color.parseColor("#00000000");
            this.f1072g = 5;
            this.f1073h = 20;
            this.f1074i = 5242880;
            this.f1075j = new ArrayList(Arrays.asList(new String[]{"video/mp4"}));
        }

        public int m1174a() {
            return this.f1067b;
        }

        public int m1175b() {
            return this.f1068c;
        }

        public int m1176c() {
            return this.f1069d;
        }

        public int m1177d() {
            return this.f1071f;
        }

        public int m1178e() {
            return this.f1072g;
        }

        public int m1179f() {
            return this.f1073h;
        }

        public long m1180g() {
            return this.f1074i;
        }

        public ArrayList<String> m1181h() {
            return this.f1075j;
        }

        public int m1182i() {
            return this.f1066a;
        }
    }

    /* renamed from: com.inmobi.ads.b.e */
    public static final class AdConfig {
        private int f1076a;
        private int f1077b;
        private int f1078c;
        private int f1079d;

        public AdConfig() {
            this.f1076a = 50;
            this.f1077b = DateUtils.MILLIS_IN_SECOND;
            this.f1078c = 100;
            this.f1079d = 250;
        }

        public int m1187a() {
            return this.f1076a;
        }

        public int m1188b() {
            return this.f1077b;
        }

        public int m1189c() {
            return this.f1078c;
        }

        public int m1190d() {
            return this.f1079d;
        }
    }

    static {
        f3774a = AdConfig.class.getSimpleName();
        f3775b = "production".equals("staging") ? "http://i.w.inmobi.com/showad.asm" : "http://i.w.inmobi.com/showad.asm";
    }

    private JSONObject m4399o() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 0);
        jSONObject2.put("fetchLimit", 1);
        jSONObject2.put("minThreshold", 0);
        jSONObject2.put("timeToLive", 0);
        jSONObject.put("base", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 100);
        jSONObject2.put("fetchLimit", 5);
        jSONObject2.put("minThreshold", 2);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("native", jSONObject2);
        return jSONObject;
    }

    private JSONObject m4400p() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public AdConfig() {
        this.f3776c = f3775b;
        this.f3777d = 20;
        this.f3778e = 60;
        this.f3779f = 60;
        this.f3782i = new AdConfig();
        this.f3783j = new AdConfig();
        this.f3784k = new AdConfig();
        this.f3785l = new AdConfig();
        try {
            m4398b(m4399o());
            this.f3786m = m4400p();
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3774a, "Default config provided for ads is invalid.", e);
        }
    }

    public String m4403a() {
        return "ads";
    }

    public void m4404a(JSONObject jSONObject) throws JSONException {
        super.m1346a(jSONObject);
        if (jSONObject.has(DatabaseOpenHelper.HISTORY_ROW_URL)) {
            this.f3776c = jSONObject.getString(DatabaseOpenHelper.HISTORY_ROW_URL);
        }
        this.f3777d = jSONObject.getInt("minimumRefreshInterval");
        this.f3778e = jSONObject.getInt("defaultRefreshInterval");
        this.f3779f = jSONObject.getInt("fetchTimeout");
        m4398b(jSONObject.getJSONObject("cache"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("imai");
        this.f3782i.f1057a = jSONObject2.getInt("maxRetries");
        this.f3782i.f1058b = jSONObject2.getInt("pingInterval");
        this.f3782i.f1059c = jSONObject2.getInt("pingTimeout");
        this.f3782i.f1060d = jSONObject2.getInt("maxDbEvents");
        this.f3782i.f1061e = jSONObject2.getInt("maxEventBatch");
        jSONObject2 = jSONObject.getJSONObject("rendering");
        this.f3783j.f1066a = jSONObject2.getInt("renderTimeout");
        this.f3783j.f1068c = jSONObject2.getInt("picHeight");
        this.f3783j.f1067b = jSONObject2.getInt("picWidth");
        this.f3783j.f1069d = jSONObject2.getInt("picQuality");
        this.f3783j.f1070e = jSONObject2.getString("webviewBackground");
        this.f3783j.f1072g = jSONObject2.getInt("maxVibrationDuration");
        this.f3783j.f1073h = jSONObject2.getInt("maxVibrationPatternLength");
        this.f3783j.f1074i = (long) jSONObject2.getJSONObject("savecontent").getInt("maxSaveSize");
        JSONArray jSONArray = jSONObject2.getJSONObject("savecontent").getJSONArray("allowedContentType");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f3783j.f1075j.add(jSONArray.getString(i));
        }
        jSONObject2 = jSONObject.getJSONObject("mraid");
        this.f3784k.f1062a = jSONObject2.getLong("expiry");
        this.f3784k.f1063b = jSONObject2.getInt("maxRetries");
        this.f3784k.f1064c = jSONObject2.getInt("retryInterval");
        this.f3784k.f1065d = jSONObject2.getString(DatabaseOpenHelper.HISTORY_ROW_URL);
        if (jSONObject.has("telemetry")) {
            this.f3786m = jSONObject.getJSONObject("telemetry");
        }
        jSONObject2 = jSONObject.getJSONObject("viewability");
        this.f3785l.f1076a = jSONObject2.getInt("impressionMinPercentageViewed");
        this.f3785l.f1077b = jSONObject2.getInt("impressionMinTimeViewed");
        this.f3785l.f1078c = jSONObject2.optInt("visibilityThrottleMillis", 100);
        this.f3785l.f1079d = jSONObject2.optInt("impressionPollIntervalMillis", 250);
    }

    private void m4398b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.f3780g = new AdConfig();
        this.f3780g.f1053a = jSONObject2.getInt("maxCacheSize");
        this.f3780g.f1054b = jSONObject2.getInt("fetchLimit");
        this.f3780g.f1055c = jSONObject2.getInt("minThreshold");
        this.f3780g.f1056d = jSONObject2.getLong("timeToLive");
        jSONObject.remove("base");
        this.f3781h = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(str);
            AdConfig adConfig = new AdConfig();
            adConfig.f1053a = jSONObject3.has("maxCacheSize") ? jSONObject3.getInt("maxCacheSize") : this.f3780g.f1053a;
            adConfig.f1054b = jSONObject3.has("fetchLimit") ? jSONObject3.getInt("fetchLimit") : this.f3780g.f1054b;
            adConfig.f1055c = jSONObject3.has("minThreshold") ? jSONObject3.getInt("minThreshold") : this.f3780g.f1055c;
            adConfig.f1056d = jSONObject3.has("timeToLive") ? (long) jSONObject3.getInt("timeToLive") : this.f3780g.f1056d;
            this.f3781h.put(str, adConfig);
        }
    }

    public JSONObject m4405b() throws JSONException {
        JSONObject b = super.m1347b();
        b.put(DatabaseOpenHelper.HISTORY_ROW_URL, this.f3776c);
        b.put("minimumRefreshInterval", this.f3777d);
        b.put("defaultRefreshInterval", this.f3778e);
        b.put("fetchTimeout", this.f3779f);
        b.put("cache", m4401q());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("maxRetries", this.f3782i.m1150a());
        jSONObject.put("pingInterval", this.f3782i.m1151b());
        jSONObject.put("pingTimeout", this.f3782i.m1152c());
        jSONObject.put("maxDbEvents", this.f3782i.m1153d());
        jSONObject.put("maxEventBatch", this.f3782i.m1154e());
        b.put("imai", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("renderTimeout", this.f3783j.m1182i());
        jSONObject.put("picWidth", this.f3783j.m1174a());
        jSONObject.put("picHeight", this.f3783j.m1175b());
        jSONObject.put("picQuality", this.f3783j.m1176c());
        jSONObject.put("webviewBackground", this.f3783j.f1070e);
        jSONObject.put("maxVibrationDuration", this.f3783j.m1178e());
        jSONObject.put("maxVibrationPatternLength", this.f3783j.m1179f());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxSaveSize", this.f3783j.m1180g());
        jSONObject2.put("allowedContentType", new JSONArray(this.f3783j.m1181h()));
        jSONObject.put("savecontent", jSONObject2);
        b.put("rendering", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("expiry", this.f3784k.m1159a());
        jSONObject.put("maxRetries", this.f3784k.m1160b());
        jSONObject.put("retryInterval", this.f3784k.m1161c());
        jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, this.f3784k.m1162d());
        b.put("mraid", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("impressionMinPercentageViewed", this.f3785l.m1187a());
        jSONObject.put("impressionMinTimeViewed", this.f3785l.m1188b());
        jSONObject.put("visibilityThrottleMillis", this.f3785l.m1189c());
        jSONObject.put("impressionPollIntervalMillis", this.f3785l.m1190d());
        b.put("viewability", jSONObject);
        if (this.f3786m != null) {
            b.put("telemetry", this.f3786m);
        }
        return b;
    }

    private JSONObject m4401q() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.f3780g.m1141b());
        jSONObject2.put("fetchLimit", this.f3780g.m1142c());
        jSONObject2.put("minThreshold", this.f3780g.m1143d());
        jSONObject2.put("timeToLive", this.f3780g.m1144e());
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.f3781h.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            AdConfig adConfig = (AdConfig) entry.getValue();
            jSONObject3.put("maxCacheSize", adConfig.m1141b());
            jSONObject3.put("fetchLimit", adConfig.m1142c());
            jSONObject3.put("minThreshold", adConfig.m1143d());
            jSONObject3.put("timeToLive", adConfig.m1144e());
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    public boolean m4406c() {
        if ((!this.f3776c.startsWith("http://") && !this.f3776c.startsWith("https://")) || this.f3777d < 0 || this.f3778e < 0 || this.f3779f <= 0) {
            return false;
        }
        if (this.f3780g == null || !this.f3780g.m1140a()) {
            return false;
        }
        for (Entry value : this.f3781h.entrySet()) {
            if (!((AdConfig) value.getValue()).m1140a()) {
                return false;
            }
        }
        if (this.f3782i.m1153d() < 0 || this.f3782i.m1154e() < 0 || this.f3782i.m1150a() < 0 || this.f3782i.m1151b() < 0 || this.f3782i.m1152c() <= 0) {
            return false;
        }
        if (this.f3784k.m1159a() < 0 || this.f3784k.m1161c() < 0 || this.f3784k.m1160b() < 0 || (!this.f3784k.m1162d().startsWith("http://") && !this.f3784k.m1162d().startsWith("https://"))) {
            return false;
        }
        if (this.f3783j.m1182i() < 0 || this.f3783j.m1175b() < 0 || this.f3783j.m1174a() < 0 || this.f3783j.m1176c() < 0 || this.f3783j.m1178e() < 0 || this.f3783j.m1179f() < 0 || this.f3783j.m1180g() < 0 || this.f3783j.f1070e == null || this.f3783j.f1070e.trim().length() == 0) {
            return false;
        }
        try {
            this.f3783j.f1071f = Color.parseColor(this.f3783j.f1070e);
            if (this.f3784k.m1160b() < 0 || this.f3784k.m1161c() < 0 || this.f3784k.m1162d() == null || this.f3784k.m1162d().trim().length() == 0) {
                return false;
            }
            if (this.f3785l.m1187a() <= 0 || this.f3785l.m1187a() > 100 || this.f3785l.m1188b() < 0 || this.f3785l.m1189c() < 50 || this.f3785l.m1189c() * 5 > this.f3785l.m1188b() || this.f3785l.m1190d() < 50 || this.f3785l.m1190d() * 4 > this.f3785l.m1188b()) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3774a, "Webview color specified in config is invalid.", e);
            return false;
        }
    }

    public Config m4407d() {
        return new AdConfig();
    }

    public String m4408e() {
        return this.f3776c;
    }

    public int m4409f() {
        return this.f3777d;
    }

    public int m4410g() {
        return this.f3778e;
    }

    public int m4411h() {
        return this.f3779f;
    }

    public AdConfig m4402a(String str) {
        AdConfig adConfig = (AdConfig) this.f3781h.get(str);
        if (adConfig == null) {
            return this.f3780g;
        }
        return adConfig;
    }

    public AdConfig m4412i() {
        return this.f3782i;
    }

    public AdConfig m4413j() {
        return this.f3783j;
    }

    public AdConfig m4414k() {
        return this.f3784k;
    }

    public AdConfig m4415l() {
        return this.f3785l;
    }

    public JSONObject m4416m() {
        return this.f3786m;
    }
}
