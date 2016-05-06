package com.inmobi.commons.core.utilities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.inmobi.commons.p000a.SdkContext;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.inmobi.commons.core.utilities.d */
public class NetworkUtils {
    public static boolean m1479a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) SdkContext.m1258b().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String m1477a(Map<String, ? extends Object> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(String.format(Locale.US, "%s=%s", new Object[]{NetworkUtils.m1476a(str2), NetworkUtils.m1476a(map.get(str2).toString())}));
        }
        return stringBuilder.toString();
    }

    public static String m1476a(String str) {
        String str2 = StringUtils.EMPTY;
        try {
            str2 = URLEncoder.encode(str, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static void m1478a(Map<String, String> map) {
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            Map hashMap = new HashMap();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() == null || ((String) entry.getValue()).trim().length() == 0 || entry.getKey() == null || ((String) entry.getKey()).trim().length() == 0) {
                    it.remove();
                } else if (((String) entry.getKey()).equals(((String) entry.getKey()).trim())) {
                    hashMap.put(entry.getKey(), ((String) entry.getValue()).trim());
                } else {
                    it.remove();
                    hashMap.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                }
            }
            map.putAll(hashMap);
        }
    }
}
