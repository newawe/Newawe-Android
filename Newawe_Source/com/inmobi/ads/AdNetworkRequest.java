package com.inmobi.ads;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.info.AppInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.PublisherProvidedUserInfo;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.signals.LocationInfo;
import com.inmobi.signals.p006a.CellularInfoUtil;
import com.inmobi.signals.p007b.WifiInfoUtil;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.inmobi.ads.e */
public final class AdNetworkRequest extends NetworkRequest {
    private long f3790d;
    private String f3791e;
    private String f3792f;
    private int f3793g;
    private String f3794h;
    private String f3795i;
    private Map<String, String> f3796j;
    private Map<String, String> f3797k;

    public AdNetworkRequest(String str, long j, UidMap uidMap) {
        super(RequestType.POST, str, true, uidMap);
        this.f3791e = "json";
        this.f3793g = 1;
        this.f3790d = j;
        this.c.put("im-plid", String.valueOf(this.f3790d));
        this.c.putAll(PublisherProvidedUserInfo.m1502a());
        this.c.putAll(DisplayInfo.m1483c());
        this.c.put("u-appIS", AppInfo.m1487a().m1490b());
        this.c.putAll(LocationInfo.m1981a().m1994d());
        this.c.putAll(WifiInfoUtil.m1907b());
        this.c.putAll(WifiInfoUtil.m1910d());
        this.c.putAll(CellularInfoUtil.m1862c());
        this.c.putAll(CellularInfoUtil.m1863d());
    }

    public void m4420a() {
        super.m1399a();
        this.c.put("format", this.f3791e);
        this.c.put("mk-ads", String.valueOf(this.f3793g));
        this.c.put("adtype", this.f3794h);
        if (this.f3795i != null) {
            this.c.put("p-keywords", this.f3795i);
        }
        if (this.f3796j != null) {
            for (Entry entry : this.f3796j.entrySet()) {
                if (!this.c.containsKey(entry.getKey())) {
                    this.c.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.f3797k != null) {
            this.c.putAll(this.f3797k);
        }
    }

    public void m4422a(String str) {
        this.f3794h = str;
    }

    public String m4424b() {
        return this.f3794h;
    }

    public void m4425b(String str) {
        this.f3791e = str;
    }

    public String m4427c() {
        return this.f3792f;
    }

    public void m4428c(String str) {
        this.f3792f = str;
    }

    public void m4421a(int i) {
        this.f3793g = i;
    }

    public int m4429d() {
        return this.f3793g;
    }

    public long m4431e() {
        return this.f3790d;
    }

    public void m4430d(String str) {
        this.f3795i = str;
    }

    public void m4423a(Map<String, String> map) {
        this.f3796j = map;
    }

    public void m4426b(Map<String, String> map) {
        this.f3797k = map;
    }
}
