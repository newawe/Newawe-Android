package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.core.utilities.uid.UidHelper;
import com.inmobi.commons.p000a.SdkInfo;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.inmobi.commons.core.utilities.info.d */
public class IdentityInfo {
    private static final String f1289a;

    static {
        f1289a = IdentityInfo.class.getSimpleName();
    }

    private static String m1499b() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    private static String m1500c() {
        Calendar instance = Calendar.getInstance();
        return String.valueOf(instance.get(16) + instance.get(15));
    }

    public static Map<String, String> m1498a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("mk-version", SdkInfo.m1265a());
        hashMap.put("u-id-adt", String.valueOf(UidHelper.m1550a().m1571m() ? 1 : 0));
        hashMap.put("ts", IdentityInfo.m1499b());
        hashMap.put("tz", IdentityInfo.m1500c());
        hashMap.putAll(SessionInfo.m1525a().m1532c());
        return hashMap;
    }
}
