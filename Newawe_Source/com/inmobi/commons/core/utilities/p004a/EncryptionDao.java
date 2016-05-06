package com.inmobi.commons.core.utilities.p004a;

import com.inmobi.commons.core.p002b.KeyValueStore;

/* renamed from: com.inmobi.commons.core.utilities.a.a */
public class EncryptionDao {
    private KeyValueStore f1259a;

    public static String m1445a() {
        return KeyValueStore.m1285a("aes_key_store");
    }

    public EncryptionDao() {
        this.f1259a = KeyValueStore.m1286b("aes_key_store");
    }

    public void m1446a(String str) {
        this.f1259a.m1288a("aes_public_key", str);
        this.f1259a.m1287a("last_generated_ts", System.currentTimeMillis() / 1000);
    }

    public String m1447b() {
        return this.f1259a.m1291b("aes_public_key", null);
    }

    public long m1448c() {
        return this.f1259a.m1290b("last_generated_ts", 0);
    }
}
