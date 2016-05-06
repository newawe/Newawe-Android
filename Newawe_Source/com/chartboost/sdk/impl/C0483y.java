package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.C0402b.C0401a;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.chartboost.sdk.impl.y */
public class C0483y {
    public static C0401a m851a(C0464i c0464i) {
        long a;
        long j;
        long a2;
        Object obj = null;
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = c0464i.f731c;
        String str = (String) map.get(HTTP.DATE_HEADER);
        if (str != null) {
            a = C0483y.m850a(str);
        } else {
            a = 0;
        }
        str = (String) map.get(HttpHeaders.CACHE_CONTROL);
        if (str != null) {
            String[] split = str.split(",");
            long j3 = 0;
            for (String trim : split) {
                String trim2 = trim2.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    j3 = 0;
                }
            }
            j = j3;
            obj = 1;
        } else {
            j = 0;
        }
        str = (String) map.get(HttpHeaders.EXPIRES);
        if (str != null) {
            a2 = C0483y.m850a(str);
        } else {
            a2 = 0;
        }
        str = (String) map.get(HttpHeaders.ETAG);
        if (obj != null) {
            j2 = (1000 * j) + currentTimeMillis;
        } else if (a > 0 && a2 >= a) {
            j2 = (a2 - a) + currentTimeMillis;
        }
        C0401a c0401a = new C0401a();
        c0401a.f520a = c0464i.f730b;
        c0401a.f521b = str;
        c0401a.f524e = j2;
        c0401a.f523d = c0401a.f524e;
        c0401a.f522c = a;
        c0401a.f525f = map;
        return c0401a;
    }

    public static long m850a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }
}
