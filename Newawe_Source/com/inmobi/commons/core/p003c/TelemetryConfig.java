package com.inmobi.commons.core.p003c;

import com.Newawe.storage.DatabaseOpenHelper;
import com.inmobi.commons.core.configs.Config;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.c.c */
class TelemetryConfig extends Config {
    private TelemetryComponentConfig f3824a;
    private String f3825b;
    private int f3826c;
    private int f3827d;
    private int f3828e;
    private int f3829f;
    private int f3830g;
    private int f3831h;

    public TelemetryConfig() {
        this.f3825b = "http://tm.inmobi.com/telemetry-collector/v1/collect";
        this.f3826c = HttpStatus.SC_MULTIPLE_CHOICES;
        this.f3827d = 60;
        this.f3828e = 50;
        this.f3829f = 3;
        this.f3830g = DateUtils.MILLIS_IN_SECOND;
        this.f3831h = 10;
        this.f3824a = new TelemetryComponentConfig();
    }

    public String m4482a() {
        return "telemetry";
    }

    public boolean m4485c() {
        if (this.f3824a == null || this.f3824a.m1312c() < 0 || this.f3825b.trim().length() == 0) {
            return false;
        }
        if ((this.f3825b.startsWith("http://") || this.f3825b.startsWith("https://")) && this.f3827d >= 0 && this.f3826c >= 0 && this.f3829f >= 0 && this.f3831h > 0 && this.f3828e > 0 && this.f3830g > 0) {
            return true;
        }
        return false;
    }

    public void m4483a(JSONObject jSONObject) throws JSONException {
        super.m1346a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.f3824a.m1310b(jSONObject2.getBoolean("enabled"));
        this.f3824a.m1307a(jSONObject2.getInt("samplingFactor"));
        this.f3824a.m1308a(jSONObject2.getBoolean("metricEnabled"));
        m4476a(jSONObject.getString(DatabaseOpenHelper.HISTORY_ROW_URL));
        m4475a(jSONObject.getInt("processingInterval"));
        m4481f(jSONObject.getInt("retryInterval"));
        m4478c(jSONObject.getInt("maxBatchSize"));
        m4477b(jSONObject.getInt("maxRetryCount"));
        m4479d(jSONObject.getInt("maxEventsToPersist"));
        m4480e(jSONObject.getInt("memoryThreshold"));
    }

    public JSONObject m4484b() throws JSONException {
        JSONObject b = super.m1347b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.f3824a.m1311b());
        jSONObject.put("samplingFactor", this.f3824a.m1312c());
        jSONObject.put("metricEnabled", this.f3824a.m1313d());
        b.put("base", jSONObject);
        b.put(DatabaseOpenHelper.HISTORY_ROW_URL, m4488f());
        b.put("processingInterval", m4493k());
        b.put("retryInterval", m4489g());
        b.put("maxBatchSize", m4491i());
        b.put("maxRetryCount", m4492j());
        b.put("maxEventsToPersist", m4494l());
        b.put("memoryThreshold", m4490h());
        return b;
    }

    public boolean m4487e() {
        return this.f3824a.m1311b();
    }

    private void m4476a(String str) {
        this.f3825b = str;
    }

    public String m4488f() {
        return this.f3825b;
    }

    private void m4475a(int i) {
        this.f3826c = i;
    }

    private void m4477b(int i) {
        this.f3829f = i;
    }

    private void m4478c(int i) {
        this.f3828e = i;
    }

    private void m4479d(int i) {
        this.f3830g = i;
    }

    private void m4480e(int i) {
        this.f3831h = i;
    }

    private void m4481f(int i) {
        this.f3827d = i;
    }

    public int m4489g() {
        return this.f3827d;
    }

    public int m4490h() {
        return this.f3831h;
    }

    public int m4491i() {
        return this.f3828e;
    }

    public int m4492j() {
        return this.f3829f;
    }

    public int m4493k() {
        return this.f3826c;
    }

    public int m4494l() {
        return this.f3830g;
    }

    public Config m4486d() {
        return new TelemetryConfig();
    }

    public TelemetryComponentConfig m4495m() {
        return this.f3824a;
    }
}
