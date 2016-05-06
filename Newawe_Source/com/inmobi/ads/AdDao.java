package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.commons.core.p002b.DbStore;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* renamed from: com.inmobi.ads.c */
public class AdDao {
    public static final String[] f1080a;
    private static final String f1081b;
    private static AdDao f1082c;
    private static Object f1083d;

    static {
        f1081b = AdDao.class.getSimpleName();
        f1083d = new Object();
        f1080a = new String[]{"id", "ad_content", "ad_type", "ad_size", "placement_id", "insertion_ts", "imp_id"};
    }

    public static AdDao m1191a() {
        AdDao adDao = f1082c;
        if (adDao == null) {
            synchronized (f1083d) {
                adDao = f1082c;
                if (adDao == null) {
                    f1082c = new AdDao();
                    adDao = f1082c;
                }
            }
        }
        return adDao;
    }

    private AdDao() {
        DbStore a = DbStore.m1275a();
        a.m1280a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL)");
        a.m1283b();
    }

    public void m1193a(String str, long j) {
        DbStore a = DbStore.m1275a();
        long currentTimeMillis = System.currentTimeMillis() - (1000 * j);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1081b, "Deleted " + a.m1278a("ad", "ad_type=? AND insertion_ts<?", new String[]{str, String.valueOf(currentTimeMillis)}) + " expired ads from cache of type:" + str);
        a.m1283b();
    }

    public int m1192a(long j, String str) {
        int b;
        DbStore a = DbStore.m1275a();
        if (str == null || str.trim().length() == 0) {
            b = a.m1282b("ad", "placement_id=?", new String[]{String.valueOf(j)});
        } else {
            b = a.m1282b("ad", "placement_id=? AND ad_size=?", new String[]{String.valueOf(j), str});
        }
        a.m1283b();
        return b;
    }

    public synchronized Ad m1195b(long j, String str) {
        List a;
        DbStore a2 = DbStore.m1275a();
        if (str == null || str.trim().length() == 0) {
            a = a2.m1279a("ad", f1080a, "placement_id=?", new String[]{String.valueOf(j)}, null, null, "insertion_ts", SchemaSymbols.ATTVAL_TRUE_1);
        } else {
            a = a2.m1279a("ad", f1080a, "placement_id=? AND ad_size=?", new String[]{String.valueOf(j), str}, null, null, "insertion_ts", SchemaSymbols.ATTVAL_TRUE_1);
        }
        a2.m1278a("ad", "id=?", new String[]{String.valueOf(((ContentValues) a.get(0)).getAsInteger("id").intValue())});
        return new Ad(r1);
    }

    public synchronized void m1194a(List<Ad> list, int i, String str) {
        if (i != 0) {
            if (list.size() != 0) {
                int i2;
                DbStore a = DbStore.m1275a();
                for (i2 = 0; i2 < list.size(); i2++) {
                    a.m1281a("ad", ((Ad) list.get(i2)).m1129a());
                }
                int b = a.m1282b("ad", "ad_type=?", new String[]{str}) - i;
                if (b > 0) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", str);
                    hashMap.put("count", Integer.valueOf(b));
                    TelemetryComponent.m4448a().m4470a("ads", "DbSpaceOverflow", hashMap);
                    List a2 = a.m1279a("ad", new String[]{"id"}, "ad_type=?", new String[]{str}, null, null, "insertion_ts ASC", String.valueOf(b));
                    String[] strArr = new String[a2.size()];
                    for (i2 = 0; i2 < a2.size(); i2++) {
                        strArr[i2] = String.valueOf(((ContentValues) a2.get(i2)).getAsInteger("id"));
                    }
                    a.m1278a("ad", "id IN " + Arrays.toString(strArr).replace("[", "(").replace("]", ")"), null);
                }
                a.m1283b();
            }
        }
    }
}
