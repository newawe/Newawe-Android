package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import com.inmobi.commons.core.utilities.p004a.UidEncryptionUtils;
import com.inmobi.commons.p000a.SdkContext;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.utilities.uid.d */
public class UidMap {
    private Map<String, Boolean> f1327a;

    public UidMap(Map<String, Boolean> map) {
        this.f1327a = map;
    }

    public HashMap<String, String> m1575a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", m1574c());
        return hashMap;
    }

    public Map<String, String> m1577b() {
        String num = Integer.toString(new Random().nextInt());
        String a = UidEncryptionUtils.m1459a(new JSONObject(m1576a(num, true)).toString());
        Map<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", a);
        hashMap.put("u-id-key", num);
        hashMap.put("u-key-ver", UidHelper.m1550a().m1564f());
        return hashMap;
    }

    private String m1574c() {
        return new JSONObject(m1576a(null, false)).toString();
    }

    public Map<String, String> m1576a(String str, boolean z) {
        Object a;
        Map<String, String> hashMap = new HashMap();
        if (((Boolean) this.f1327a.get("O1")).booleanValue() && !UidHelper.m1550a().m1570l()) {
            a = UidHelper.m1550a().m1556a(UidHelper.m1550a().m1567i());
            if (z) {
                a = m1573a((String) a, str);
            }
            hashMap.put("O1", a);
        }
        if (((Boolean) this.f1327a.get("UM5")).booleanValue() && !UidHelper.m1550a().m1570l()) {
            a = UidHelper.m1550a().m1559b(UidHelper.m1550a().m1567i());
            if (z) {
                a = m1573a((String) a, str);
            }
            hashMap.put("UM5", a);
        }
        if (((Boolean) this.f1327a.get("LID")).booleanValue()) {
            a = UidHelper.m1550a().m1565g();
            if (a != null && a.trim().length() > 0) {
                if (z) {
                    a = m1573a((String) a, str);
                }
                hashMap.put("LID", a);
            }
        }
        if (((Boolean) this.f1327a.get("SID")).booleanValue()) {
            a = UidHelper.m1550a().m1566h();
            if (a != null && a.trim().length() > 0) {
                if (z) {
                    a = m1573a((String) a, str);
                }
                hashMap.put("SID", a);
            }
        }
        if (((Boolean) this.f1327a.get("GPID")).booleanValue()) {
            AdvertisingIdInfo j = UidHelper.m1550a().m1568j();
            if (j != null) {
                a = j.m1537b();
                if (a != null) {
                    if (z) {
                        a = m1573a((String) a, str);
                    }
                    hashMap.put("GPID", a);
                }
            }
        }
        if (((Boolean) this.f1327a.get("IMID")).booleanValue()) {
            a = UidHelper.m1550a().m1555a(SdkContext.m1258b());
            if (a != null) {
                if (z) {
                    a = m1573a((String) a, str);
                }
                hashMap.put("IMID", a);
            }
        }
        if (((Boolean) this.f1327a.get("AIDL")).booleanValue()) {
            a = UidHelper.m1550a().m1558b(SdkContext.m1258b());
            if (a != null) {
                if (z) {
                    a = m1573a((String) a, str);
                }
                hashMap.put("AIDL", a);
            }
        }
        return hashMap;
    }

    private String m1573a(String str, String str2) {
        String str3 = StringUtils.EMPTY;
        try {
            byte[] bytes = str.getBytes(HTTP.UTF_8);
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes(HTTP.UTF_8);
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, 2), HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str3;
        }
    }
}
