package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import com.inmobi.commons.core.p002b.KeyValueStore;

/* renamed from: com.inmobi.commons.core.utilities.uid.b */
class UidDao {
    private KeyValueStore f1319a;

    public UidDao() {
        this.f1319a = KeyValueStore.m1286b("uid_store");
    }

    public UidDao(Context context) {
        this.f1319a = KeyValueStore.m1284a(context, "uid_store");
    }

    public void m1540a(String str) {
        this.f1319a.m1288a("adv_id", str);
    }

    public String m1538a() {
        return this.f1319a.m1291b("adv_id", null);
    }

    public void m1541a(boolean z) {
        this.f1319a.m1289a("limit_ad_tracking", z);
    }

    public boolean m1543b() {
        return this.f1319a.m1292b("limit_ad_tracking", true);
    }

    public void m1542b(String str) {
        this.f1319a.m1288a("app_id", str);
    }

    public String m1544c() {
        return this.f1319a.m1291b("app_id", null);
    }

    public void m1545c(String str) {
        this.f1319a.m1288a("im_id", str);
    }

    public String m1546d() {
        return this.f1319a.m1291b("im_id", null);
    }

    public void m1539a(long j) {
        this.f1319a.m1287a("imid_timestamp", j);
    }

    public long m1548e() {
        return this.f1319a.m1290b("imid_timestamp", 0);
    }

    public void m1547d(String str) {
        this.f1319a.m1288a("appended_id", str);
    }

    public String m1549f() {
        return this.f1319a.m1291b("appended_id", null);
    }
}
