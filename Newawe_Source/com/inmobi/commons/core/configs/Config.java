package com.inmobi.commons.core.configs;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.configs.a */
public abstract class Config {
    private Config f1201a;

    /* renamed from: com.inmobi.commons.core.configs.a.a */
    public static final class Config {
        private HashMap<String, Boolean> f1200a;

        public Config() {
            this.f1200a = new HashMap();
            this.f1200a.put("O1", Boolean.valueOf(true));
            this.f1200a.put("SID", Boolean.valueOf(true));
            this.f1200a.put("LID", Boolean.valueOf(true));
            this.f1200a.put("UM5", Boolean.valueOf(true));
            this.f1200a.put("GPID", Boolean.valueOf(true));
            this.f1200a.put("IMID", Boolean.valueOf(true));
            this.f1200a.put("AIDL", Boolean.valueOf(true));
        }

        public HashMap<String, Boolean> m1344a() {
            return this.f1200a;
        }
    }

    public abstract String m1345a();

    public abstract boolean m1348c();

    public abstract Config m1349d();

    public Config() {
        this.f1201a = new Config();
    }

    public void m1346a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("includeIds");
        for (int i = 0; i < jSONObject2.length(); i++) {
            this.f1201a.f1200a.put("O1", Boolean.valueOf(jSONObject2.getBoolean("O1")));
            this.f1201a.f1200a.put("SID", Boolean.valueOf(jSONObject2.getBoolean("SID")));
            this.f1201a.f1200a.put("LID", Boolean.valueOf(jSONObject2.getBoolean("LID")));
            this.f1201a.f1200a.put("UM5", Boolean.valueOf(jSONObject2.getBoolean("UM5")));
            this.f1201a.f1200a.put("GPID", Boolean.valueOf(jSONObject2.getBoolean("GPID")));
            this.f1201a.f1200a.put("IMID", Boolean.valueOf(jSONObject2.getBoolean("IMID")));
            this.f1201a.f1200a.put("AIDL", Boolean.valueOf(jSONObject2.getBoolean("AIDL")));
        }
    }

    public JSONObject m1347b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("O1", this.f1201a.f1200a.get("O1"));
        jSONObject2.put("SID", this.f1201a.f1200a.get("SID"));
        jSONObject2.put("LID", this.f1201a.f1200a.get("LID"));
        jSONObject2.put("UM5", this.f1201a.f1200a.get("UM5"));
        jSONObject2.put("GPID", this.f1201a.f1200a.get("GPID"));
        jSONObject2.put("IMID", this.f1201a.f1200a.get("IMID"));
        jSONObject2.put("AIDL", this.f1201a.f1200a.get("AIDL"));
        jSONObject.put("includeIds", jSONObject2);
        return jSONObject;
    }

    public Config m1350n() {
        return this.f1201a;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass() && m1345a().equals(((Config) obj).m1345a())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return m1345a().hashCode();
    }
}
