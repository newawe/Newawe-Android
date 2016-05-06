package com.inmobi.ads;

import com.inmobi.commons.core.network.AsyncNetworkTask.AsyncNetworkTask;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* renamed from: com.inmobi.ads.d */
final class AdNetworkClient implements AsyncNetworkTask {
    private static final String f3787a;
    private AdNetworkRequest f3788b;
    private AdNetworkClient f3789c;

    /* renamed from: com.inmobi.ads.d.a */
    public interface AdNetworkClient {
        void m1196a(AdNetworkResponse adNetworkResponse);

        void m1197b(AdNetworkResponse adNetworkResponse);
    }

    static {
        f3787a = AdNetworkClient.class.getSimpleName();
    }

    public AdNetworkClient(AdNetworkRequest adNetworkRequest, AdNetworkClient adNetworkClient) {
        this.f3788b = adNetworkRequest;
        this.f3789c = adNetworkClient;
    }

    public void m4417a() {
        new com.inmobi.commons.core.network.AsyncNetworkTask(this.f3788b, this).m1422a();
    }

    public void m4418a(NetworkResponse networkResponse) {
        AdNetworkResponse adNetworkResponse = new AdNetworkResponse(this.f3788b, networkResponse);
        this.f3789c.m1196a(adNetworkResponse);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3787a, "Ad fetch succeeded. Response:" + adNetworkResponse.m1201c());
    }

    public void m4419b(NetworkResponse networkResponse) {
        AdNetworkResponse adNetworkResponse = new AdNetworkResponse(this.f3788b, networkResponse);
        this.f3789c.m1197b(adNetworkResponse);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3787a, "Ad fetch failed:" + adNetworkResponse.m1202d().m1396b());
    }
}
