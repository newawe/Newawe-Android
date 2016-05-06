package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.UidMap;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.inmobi.signals.f */
public class CarbPostListNetworkRequest extends NetworkRequest {
    private int f3887d;
    private int f3888e;
    private CarbGetListNetworkResponse f3889f;
    private List<CarbInfo> f3890g;

    public CarbPostListNetworkRequest(String str, int i, int i2, UidMap uidMap, List<CarbInfo> list, CarbGetListNetworkResponse carbGetListNetworkResponse) {
        super(RequestType.POST, str, true, uidMap);
        this.f3887d = i;
        this.f3888e = i2;
        this.f3890g = list;
        this.f3889f = carbGetListNetworkResponse;
        this.c.put("req_id", this.f3889f.m1928c());
        this.c.put("i_till", Integer.toString(this.f3889f.m1929d()));
        this.c.put("p_a_apps", m4573d());
    }

    private String m4573d() {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.f3890g.size(); i++) {
            jSONArray.put(((CarbInfo) this.f3890g.get(i)).m1932b());
        }
        return jSONArray.toString();
    }

    public int m4574b() {
        return this.f3887d;
    }

    public int m4575c() {
        return this.f3888e;
    }
}
