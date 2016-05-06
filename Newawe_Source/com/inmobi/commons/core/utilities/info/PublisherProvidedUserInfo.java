package com.inmobi.commons.core.utilities.info;

import android.location.Location;
import android.support.v4.widget.ExploreByTouchHelper;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.core.utilities.info.e */
public final class PublisherProvidedUserInfo {
    private static int f1290a;
    private static String f1291b;
    private static String f1292c;
    private static String f1293d;
    private static String f1294e;
    private static String f1295f;
    private static String f1296g;
    private static int f1297h;
    private static String f1298i;
    private static String f1299j;
    private static String f1300k;
    private static String f1301l;
    private static int f1302m;
    private static String f1303n;
    private static String f1304o;
    private static String f1305p;
    private static String f1306q;
    private static String f1307r;
    private static Location f1308s;

    static {
        f1290a = ExploreByTouchHelper.INVALID_ID;
        f1297h = ExploreByTouchHelper.INVALID_ID;
        f1302m = ExploreByTouchHelper.INVALID_ID;
    }

    public static void m1503a(int i) {
        f1290a = i;
    }

    public static void m1505a(String str) {
        f1291b = str;
    }

    public static void m1508b(String str) {
        f1292c = str;
    }

    public static void m1511c(String str) {
        f1293d = str;
    }

    public static void m1513d(String str) {
        f1294e = str;
    }

    public static void m1514e(String str) {
        f1295f = str;
    }

    public static void m1515f(String str) {
        f1296g = str;
    }

    public static void m1507b(int i) {
        f1297h = i;
    }

    public static void m1516g(String str) {
        f1298i = str;
    }

    public static void m1517h(String str) {
        f1299j = str;
    }

    public static void m1518i(String str) {
        f1300k = str;
    }

    public static void m1519j(String str) {
        f1301l = str;
    }

    public static void m1510c(int i) {
        f1302m = i;
    }

    public static void m1520k(String str) {
        f1303n = str;
    }

    public static void m1521l(String str) {
        f1304o = str;
    }

    public static void m1522m(String str) {
        f1305p = str;
    }

    public static HashMap<String, String> m1502a() {
        HashMap<String, String> hashMap = new HashMap();
        if (f1290a != ExploreByTouchHelper.INVALID_ID && f1290a > 0) {
            hashMap.put("u-age", String.valueOf(f1290a));
        }
        if (f1297h != ExploreByTouchHelper.INVALID_ID && f1297h > 0) {
            hashMap.put("u-yearofbirth", String.valueOf(f1297h));
        }
        if (f1302m != ExploreByTouchHelper.INVALID_ID && f1302m > 0) {
            hashMap.put("u-income", String.valueOf(f1302m));
        }
        String a = PublisherProvidedUserInfo.m1501a(f1294e, f1295f, f1296g);
        if (!(a == null || a.trim().length() == 0)) {
            hashMap.put("u-location", a);
        }
        if (f1291b != null) {
            hashMap.put("u-agegroup", f1291b.toString().toLowerCase(Locale.ENGLISH));
        }
        if (f1292c != null) {
            hashMap.put("u-areacode", f1292c);
        }
        if (f1293d != null) {
            hashMap.put("u-postalcode", f1293d);
        }
        if (f1298i != null) {
            hashMap.put("u-gender", f1298i);
        }
        if (f1299j != null) {
            hashMap.put("u-ethnicity", f1299j);
        }
        if (f1300k != null) {
            hashMap.put("u-education", f1300k);
        }
        if (f1301l != null) {
            hashMap.put("u-language", f1301l);
        }
        if (f1303n != null) {
            hashMap.put("u-householdincome", f1303n);
        }
        if (f1304o != null) {
            hashMap.put("u-interests", f1304o);
        }
        if (f1305p != null) {
            hashMap.put("u-nationality", f1305p);
        }
        return hashMap;
    }

    private static String m1501a(String str, String str2, String str3) {
        String str4 = StringUtils.EMPTY;
        if (!(str == null || str.trim().length() == 0)) {
            str4 = str.trim();
        }
        if (!(str2 == null || str2.trim().length() == 0)) {
            str4 = str4 + "-" + str2.trim();
        }
        if (str3 == null || str3.trim().length() == 0) {
            return str4;
        }
        return str4 + "-" + str3.trim();
    }

    public static String m1506b() {
        return f1306q;
    }

    public static void m1523n(String str) {
        f1306q = str;
    }

    public static String m1509c() {
        return f1307r;
    }

    public static void m1524o(String str) {
        f1307r = str;
    }

    public static Location m1512d() {
        return f1308s;
    }

    public static void m1504a(Location location) {
        f1308s = location;
    }
}
