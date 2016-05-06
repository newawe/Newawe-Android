package com.inmobi.commons.core.configs;

import com.Newawe.storage.DatabaseOpenHelper;
import com.inmobi.commons.p000a.SdkInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.configs.g */
class RootConfig extends Config {
    private int f3845a;
    private int f3846b;
    private int f3847c;
    private List<RootConfig> f3848d;
    private RootConfig f3849e;
    private JSONObject f3850f;

    /* renamed from: com.inmobi.commons.core.configs.g.a */
    static final class RootConfig {
        private String f1216a;
        private long f1217b;
        private String f1218c;
        private String f1219d;

        RootConfig() {
        }

        public String m1385a() {
            return this.f1216a;
        }

        public Long m1386b() {
            return Long.valueOf(this.f1217b);
        }

        public String m1387c() {
            return this.f1218c;
        }

        public String m1388d() {
            return this.f1219d;
        }
    }

    /* renamed from: com.inmobi.commons.core.configs.g.b */
    public static final class RootConfig {
        private String f1220a;
        private String f1221b;

        public RootConfig() {
            this.f1220a = SdkInfo.m1268b();
            this.f1221b = SdkInfo.m1272f();
        }

        public String m1393a() {
            return this.f1220a;
        }

        public String m1394b() {
            return this.f1221b;
        }
    }

    public RootConfig() {
        this.f3845a = 3;
        this.f3846b = 60;
        this.f3847c = 3;
        this.f3848d = new ArrayList();
        this.f3849e = new RootConfig();
        this.f3850f = new JSONObject();
    }

    public String m4518a() {
        return "root";
    }

    public JSONObject m4521b() throws JSONException {
        JSONObject b = super.m1347b();
        JSONArray jSONArray = new JSONArray();
        b.put("maxRetries", this.f3845a);
        b.put("retryInterval", this.f3846b);
        b.put("waitTime", this.f3847c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ClientCookie.VERSION_ATTR, this.f3849e.f1220a);
        jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, this.f3849e.f1221b);
        b.put("latestSdkInfo", jSONObject);
        for (int i = 0; i < this.f3848d.size(); i++) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", ((RootConfig) this.f3848d.get(i)).f1216a);
            jSONObject2.put("expiry", ((RootConfig) this.f3848d.get(i)).f1217b);
            jSONObject2.put("protocol", ((RootConfig) this.f3848d.get(i)).f1218c);
            jSONObject2.put(DatabaseOpenHelper.HISTORY_ROW_URL, ((RootConfig) this.f3848d.get(i)).f1219d);
            jSONArray.put(jSONObject2);
        }
        b.put("components", jSONArray);
        return b;
    }

    public void m4519a(JSONObject jSONObject) throws JSONException {
        super.m1346a(jSONObject);
        this.f3845a = jSONObject.getInt("maxRetries");
        this.f3846b = jSONObject.getInt("retryInterval");
        this.f3847c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.f3849e.f1220a = jSONObject2.getString(ClientCookie.VERSION_ATTR);
        this.f3849e.f1221b = jSONObject2.getString(DatabaseOpenHelper.HISTORY_ROW_URL);
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            RootConfig rootConfig = new RootConfig();
            rootConfig.f1216a = jSONObject3.getString("type");
            rootConfig.f1217b = jSONObject3.getLong("expiry");
            rootConfig.f1218c = jSONObject3.getString("protocol");
            rootConfig.f1219d = jSONObject3.getString(DatabaseOpenHelper.HISTORY_ROW_URL);
            this.f3848d.add(rootConfig);
        }
    }

    public boolean m4522c() {
        if (this.f3848d == null || this.f3845a < 0 || this.f3846b < 0 || this.f3847c < 0 || this.f3849e.m1393a().trim().length() == 0) {
            return false;
        }
        if (!this.f3849e.m1394b().startsWith("http://") && !this.f3849e.m1394b().startsWith("https://")) {
            return false;
        }
        for (int i = 0; i < this.f3848d.size(); i++) {
            RootConfig rootConfig = (RootConfig) this.f3848d.get(i);
            if (rootConfig.m1385a().trim().length() == 0 || rootConfig.m1386b().longValue() < 0 || rootConfig.m1386b().longValue() > 864000 || rootConfig.m1387c().trim().length() == 0 || rootConfig.m1388d() == null || rootConfig.m1388d().trim().length() == 0) {
                return false;
            }
            if (!rootConfig.m1388d().startsWith("http://") && !rootConfig.m1388d().startsWith("https://")) {
                return false;
            }
        }
        return true;
    }

    public Config m4523d() {
        return new RootConfig();
    }

    public long m4517a(String str) {
        for (int i = 0; i < this.f3848d.size(); i++) {
            RootConfig rootConfig = (RootConfig) this.f3848d.get(i);
            if (str.equals(rootConfig.f1216a)) {
                return rootConfig.f1217b;
            }
        }
        return 86400;
    }

    public int m4524e() {
        return this.f3845a;
    }

    public int m4525f() {
        return this.f3846b;
    }

    public int m4526g() {
        return this.f3847c;
    }

    public String m4520b(String str) {
        for (int i = 0; i < this.f3848d.size(); i++) {
            RootConfig rootConfig = (RootConfig) this.f3848d.get(i);
            if (str.equals(rootConfig.f1216a)) {
                return rootConfig.f1219d;
            }
        }
        return StringUtils.EMPTY;
    }

    public RootConfig m4527h() {
        return this.f3849e;
    }

    public JSONObject m4528i() {
        return this.f3850f;
    }
}
