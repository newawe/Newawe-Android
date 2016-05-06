package com.startapp.android.publish.p021g;

import android.content.Context;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.model.BaseRequest;
import com.startapp.android.publish.model.BaseResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.xinclude.XIncludeHandler;
import org.apache.http.protocol.HTTP;

/* renamed from: com.startapp.android.publish.g.c */
public class StartAppSDK {
    public static <T extends BaseResponse> T m2836a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, Class<T> cls) {
        return StartAppSDK.m2837a(context, str, baseRequest, (Map) map, (Class) cls, new Gson());
    }

    public static <T extends BaseResponse> T m2837a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, Class<T> cls, Gson gson) {
        StringBuilder stringBuilder = new StringBuilder();
        StartAppSDK.m2840a(context, str, baseRequest, map, stringBuilder, 3, 0);
        try {
            return (BaseResponse) gson.fromJson(stringBuilder.toString(), (Class) cls);
        } catch (Throwable e) {
            throw new com.startapp.android.publish.p022h.StartAppSDK("Failed parsing the JSON response to " + cls.getName(), e);
        }
    }

    public static String m2838a(Context context, String str, BaseRequest baseRequest, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        StartAppSDK.m2840a(context, str, baseRequest, map, stringBuilder, 3, 0);
        return stringBuilder.toString();
    }

    public static boolean m2842a(Context context, String str, Map<String, String> map) {
        StartAppSDK.m2840a(context, str, null, map, null, 3, 0);
        return true;
    }

    public static boolean m2841a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, int i, long j) {
        StartAppSDK.m2840a(context, str, baseRequest, map, null, i, j);
        return true;
    }

    private static void m2840a(Context context, String str, BaseRequest baseRequest, Map<String, String> map, StringBuilder stringBuilder, int i, long j) {
        if (baseRequest != null) {
            str = str + baseRequest.getRequestString();
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Transport", 3, "Sending get to URL: " + str);
        Map a = StartAppSDK.m2839a(context, map);
        Object obj = null;
        int i2 = 1;
        while (obj == null) {
            try {
                com.startapp.android.publish.p022h.StartAppSDK.m2940a(context, str, a, stringBuilder);
                obj = 1;
            } catch (com.startapp.android.publish.p022h.StartAppSDK e) {
                if (!e.m2948a() || i2 >= i) {
                    throw e;
                }
                i2++;
                if (j > 0) {
                    try {
                        Thread.sleep(j);
                    } catch (InterruptedException e2) {
                    }
                }
            }
        }
    }

    private static Map<String, String> m2839a(Context context, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        Object a = com.startapp.android.publish.p022h.StartAppSDK.m2852a(context).m2846a();
        try {
            a = URLEncoder.encode(a, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        map.put("device-id", a);
        map.put(XIncludeHandler.HTTP_ACCEPT_LANGUAGE, Locale.getDefault().toString());
        return map;
    }
}
