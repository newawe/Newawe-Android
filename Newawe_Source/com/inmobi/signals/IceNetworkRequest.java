package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.UidMap;

/* renamed from: com.inmobi.signals.k */
public class IceNetworkRequest extends NetworkRequest {
    private static final String f3893d;
    private int f3894e;
    private int f3895f;
    private IceSample f3896g;

    static {
        f3893d = IceNetworkRequest.class.getSimpleName();
    }

    public IceNetworkRequest(String str, int i, int i2, UidMap uidMap, IceSample iceSample) {
        super(RequestType.POST, str, true, uidMap);
        this.f3894e = i;
        this.f3895f = i2;
        this.f3896g = iceSample;
        String jSONObject = this.f3896g.m1967a().toString();
        this.c.put("payload", jSONObject);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3893d, "Ice payload being sent:" + jSONObject);
    }

    public int m4578b() {
        return this.f3894e;
    }

    public int m4579c() {
        return this.f3895f;
    }
}
