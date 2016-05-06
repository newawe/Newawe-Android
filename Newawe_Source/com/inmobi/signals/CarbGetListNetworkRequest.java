package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.UidMap;

/* renamed from: com.inmobi.signals.b */
public class CarbGetListNetworkRequest extends NetworkRequest {
    private int f3885d;
    private int f3886e;

    public CarbGetListNetworkRequest(String str, int i, int i2, UidMap uidMap) {
        super(RequestType.POST, str, true, uidMap);
        this.f3885d = i;
        this.f3886e = i2;
    }
}
