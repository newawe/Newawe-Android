package com.inmobi.commons.core.network;

/* renamed from: com.inmobi.commons.core.network.d */
public final class SyncNetworkTask {
    private NetworkRequest f1250a;

    public SyncNetworkTask(NetworkRequest networkRequest) {
        this.f1250a = networkRequest;
    }

    public NetworkResponse m1436a() {
        return new NetworkConnection(this.f1250a).m1429a();
    }
}
