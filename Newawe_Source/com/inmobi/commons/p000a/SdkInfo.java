package com.inmobi.commons.p000a;

import android.content.Context;
import com.inmobi.commons.core.p002b.KeyValueStore;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.a.b */
public class SdkInfo {
    public static String m1265a() {
        String str = "pr-SAND";
        String str2 = StringUtils.EMPTY;
        char[] toCharArray = SdkInfo.m1268b().toCharArray();
        str = str2;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                str = str + "T";
            } else {
                str = str + ((char) ((toCharArray[i] - 48) + 65));
            }
        }
        return "pr-SAND-" + str + "-" + SdkInfo.m1271e();
    }

    public static String m1268b() {
        return "5.0.1";
    }

    public static String m1269c() {
        return "2.0";
    }

    public static String m1270d() {
        return "android";
    }

    public static String m1271e() {
        return "20151019";
    }

    public static String m1272f() {
        return "http://www.inmobi.com/products/sdk/#downloads";
    }

    public static String m1266a(Context context) {
        return KeyValueStore.m1284a(context, "sdk_version_store").m1291b("sdk_version", null);
    }

    public static void m1267a(Context context, String str) {
        KeyValueStore.m1284a(context, "sdk_version_store").m1288a("sdk_version", str);
    }
}
