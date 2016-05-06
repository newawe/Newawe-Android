package com.inmobi.signals;

import com.inmobi.commons.core.p002b.KeyValueStore;

/* renamed from: com.inmobi.signals.a */
public class CarbDao {
    private KeyValueStore f1697a;

    public static String m1868a() {
        return KeyValueStore.m1285a("carb_store");
    }

    public CarbDao() {
        this.f1697a = KeyValueStore.m1286b("carb_store");
    }

    public long m1870b() {
        return this.f1697a.m1290b("carb_last_update_ts", 0);
    }

    public void m1869a(long j) {
        this.f1697a.m1287a("carb_last_update_ts", j);
    }
}
