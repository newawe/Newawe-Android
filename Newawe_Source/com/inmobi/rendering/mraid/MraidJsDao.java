package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.p002b.KeyValueStore;

/* renamed from: com.inmobi.rendering.mraid.i */
public class MraidJsDao {
    private KeyValueStore f1655a;

    public static String m1814a() {
        return KeyValueStore.m1285a("mraid_js_store");
    }

    public MraidJsDao() {
        this.f1655a = KeyValueStore.m1286b("mraid_js_store");
    }

    public void m1815a(String str) {
        this.f1655a.m1288a("mraid_js_string", str);
        this.f1655a.m1287a("last_updated_ts", System.currentTimeMillis() / 1000);
    }

    public String m1816b() {
        return this.f1655a.m1291b("mraid_js_string", null);
    }

    public long m1817c() {
        return this.f1655a.m1290b("last_updated_ts", 0);
    }
}
