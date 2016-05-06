package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.p002b.KeyValueStore;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.configs.c */
public class ConfigDao {
    private KeyValueStore f1211a;

    public static String m1368a() {
        return KeyValueStore.m1285a("config_store");
    }

    public ConfigDao() {
        this.f1211a = KeyValueStore.m1286b("config_store");
    }

    public void m1369a(Config config) {
        try {
            this.f1211a.m1288a(config.m1345a() + "_config", config.m1347b().toString());
            m1370a(config.m1345a(), System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void m1373b(Config config) {
        String b = this.f1211a.m1291b(config.m1345a() + "_config", null);
        if (b != null) {
            try {
                config.m1346a(new JSONObject(b));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean m1371a(String str) {
        return this.f1211a.m1291b(new StringBuilder().append(str).append("_config").toString(), null) != null;
    }

    public long m1372b(String str) {
        return this.f1211a.m1290b(str + "_config_update_ts", 0);
    }

    public void m1370a(String str, long j) {
        this.f1211a.m1287a(str + "_config_update_ts", j);
    }
}
