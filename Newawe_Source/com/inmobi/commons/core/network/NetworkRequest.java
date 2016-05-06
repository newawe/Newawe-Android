package com.inmobi.commons.core.network;

import android.util.Base64;
import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent;
import com.inmobi.commons.core.configs.PkConfig;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.inmobi.commons.core.utilities.info.AppInfo;
import com.inmobi.commons.core.utilities.info.DeviceInfo;
import com.inmobi.commons.core.utilities.info.IdentityInfo;
import com.inmobi.commons.core.utilities.p004a.RequestEncryptionUtils;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.commons.p000a.SdkContext;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.protocol.HTTP;

public class NetworkRequest {
    private static final String f1225d;
    protected Map<String, String> f1226a;
    protected Map<String, String> f1227b;
    protected Map<String, String> f1228c;
    private RequestType f1229e;
    private String f1230f;
    private UidMap f1231g;
    private int f1232h;
    private int f1233i;
    private boolean f1234j;
    private boolean f1235k;
    private byte[] f1236l;
    private byte[] f1237m;
    private boolean f1238n;
    private long f1239o;

    public enum RequestType {
        GET,
        POST
    }

    static {
        f1225d = NetworkRequest.class.getSimpleName();
    }

    public NetworkRequest(RequestType requestType, String str, boolean z, UidMap uidMap) {
        this.f1226a = new HashMap();
        this.f1227b = new HashMap();
        this.f1228c = new HashMap();
        this.f1232h = DateUtils.MILLIS_IN_MINUTE;
        this.f1233i = DateUtils.MILLIS_IN_MINUTE;
        this.f1234j = true;
        this.f1238n = true;
        this.f1239o = -1;
        this.f1229e = requestType;
        this.f1230f = str;
        this.f1235k = z;
        this.f1231g = uidMap;
        this.f1226a.put(HTTP.USER_AGENT, SdkContext.m1262d());
    }

    public void m1400a(long j) {
        this.f1239o = j;
    }

    public long m1407f() {
        return this.f1239o;
    }

    public boolean m1408g() {
        return this.f1239o != -1;
    }

    public void m1401a(boolean z) {
        this.f1238n = z;
    }

    public String m1409h() {
        return this.f1230f;
    }

    public void m1405c(Map<String, String> map) {
        this.f1228c.putAll(map);
    }

    public Map<String, String> m1410i() {
        NetworkUtils.m1478a(this.f1226a);
        return this.f1226a;
    }

    public void m1399a() {
        if (!this.f1238n) {
            return;
        }
        if (this.f1229e == RequestType.GET) {
            m1398a(this.f1227b);
        } else if (this.f1229e == RequestType.POST) {
            m1398a(this.f1228c);
        }
    }

    public String m1411j() {
        NetworkUtils.m1478a(this.f1227b);
        String a = NetworkUtils.m1477a(this.f1227b, "&");
        Logger.m1440a(InternalLogLevel.INTERNAL, f1225d, "Get params: " + a);
        return a;
    }

    public String m1412k() {
        NetworkUtils.m1478a(this.f1228c);
        String a = NetworkUtils.m1477a(this.f1228c, "&");
        Logger.m1440a(InternalLogLevel.INTERNAL, f1225d, "Post body url: " + m1409h());
        Logger.m1440a(InternalLogLevel.INTERNAL, f1225d, "Post body: " + a);
        if (!m1417p()) {
            return a;
        }
        a = m1397a(a);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1225d, "Encrypted post body: " + a);
        return a;
    }

    public boolean m1413l() {
        return this.f1234j;
    }

    public void m1403b(boolean z) {
        this.f1234j = z;
    }

    public RequestType m1414m() {
        return this.f1229e;
    }

    public int m1415n() {
        return this.f1232h;
    }

    public void m1402b(int i) {
        this.f1232h = i;
    }

    public void m1404c(int i) {
        this.f1233i = i;
    }

    public int m1416o() {
        return this.f1233i;
    }

    public boolean m1417p() {
        return this.f1235k;
    }

    private void m1398a(Map<String, String> map) {
        map.putAll(AppInfo.m1487a().m1491c());
        map.putAll(DeviceInfo.m1492a());
        map.putAll(IdentityInfo.m1498a());
        if (this.f1231g == null) {
            return;
        }
        if (m1417p()) {
            map.putAll(this.f1231g.m1575a());
        } else {
            map.putAll(this.f1231g.m1577b());
        }
    }

    private String m1397a(String str) {
        byte[] a = RequestEncryptionUtils.m1451a(8);
        this.f1236l = RequestEncryptionUtils.m1451a(16);
        this.f1237m = RequestEncryptionUtils.m1450a();
        Map hashMap = new HashMap();
        Config pkConfig = new PkConfig();
        ConfigComponent.m1352a().m1364a(pkConfig, null);
        hashMap.put("sm", RequestEncryptionUtils.m1449a(str, this.f1237m, this.f1236l, a, pkConfig.m4515f(), pkConfig.m4514e()));
        hashMap.put("sn", pkConfig.m4516g());
        return NetworkUtils.m1477a(hashMap, "&");
    }

    protected String m1406e(String str) {
        if (str == null || str.trim().length() == 0) {
            return StringUtils.EMPTY;
        }
        byte[] a = RequestEncryptionUtils.m1455a(Base64.decode(str.getBytes(), 0), this.f1237m, this.f1236l);
        if (a != null) {
            return new String(a);
        }
        return null;
    }
}
