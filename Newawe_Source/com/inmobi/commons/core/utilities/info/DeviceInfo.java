package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.inmobi.commons.p000a.SdkContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.core.utilities.info.b */
public class DeviceInfo {
    private static String m1493b() {
        return Locale.getDefault().toString();
    }

    private static String m1494c() {
        Context b = SdkContext.m1258b();
        String str = StringUtils.EMPTY;
        if (b.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    int type = activeNetworkInfo.getType();
                    int subtype = activeNetworkInfo.getSubtype();
                    if (type == 1) {
                        return "wifi";
                    }
                    if (type == 0) {
                        String str2 = "carrier";
                        if (subtype == 1) {
                            return "gprs";
                        }
                        if (subtype == 2) {
                            return "edge";
                        }
                        if (subtype == 3) {
                            return "umts";
                        }
                        if (subtype == 0) {
                            return "carrier";
                        }
                        return str2;
                    }
                }
            }
        }
        return str;
    }

    public static Map<String, String> m1492a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("d-netType", DeviceInfo.m1494c());
        hashMap.put("d-localization", DeviceInfo.m1493b());
        return hashMap;
    }
}
