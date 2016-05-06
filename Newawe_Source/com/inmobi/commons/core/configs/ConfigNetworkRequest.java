package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.commons.p000a.SdkContext;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.configs.e */
final class ConfigNetworkRequest extends NetworkRequest {
    private static final String f3836d;
    private static String f3837h;
    private int f3838e;
    private int f3839f;
    private Map<String, Config> f3840g;

    static {
        f3836d = ConfigNetworkRequest.class.getSimpleName();
        f3837h = "http://config.inmobi.com/config-server/v1/config/secure.cfg";
    }

    public ConfigNetworkRequest(Map<String, Config> map, UidMap uidMap, String str, int i, int i2) {
        RequestType requestType = RequestType.POST;
        if (str == null || str.trim().length() == 0) {
            str = f3837h;
        }
        super(requestType, str, true, uidMap);
        this.f3840g = map;
        this.f3838e = i;
        this.f3839f = i2;
    }

    public void m4504a() {
        super.m1399a();
        this.c.put("p", m4503e());
        this.c.put("im-accid", SdkContext.m1260c());
    }

    private String m4503e() {
        ConfigDao configDao = new ConfigDao();
        String str = StringUtils.EMPTY;
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.f3840g.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("n", entry.getKey());
                jSONObject.put("t", configDao.m1372b((String) entry.getKey()));
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3836d, "Problem while creating config network request's payload.", e);
            return str;
        }
    }

    public Map<String, Config> m4506b() {
        return this.f3840g;
    }

    public int m4507c() {
        return this.f3838e;
    }

    public int m4508d() {
        return this.f3839f;
    }

    public void m4505a(String str) {
        this.f3840g.remove(str);
    }
}
