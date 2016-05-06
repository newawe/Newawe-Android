package com.inmobi.commons.core.network;

/* renamed from: com.inmobi.commons.core.network.a */
public final class AsyncNetworkTask {
    private NetworkRequest f1241a;
    private AsyncNetworkTask f1242b;

    /* renamed from: com.inmobi.commons.core.network.a.1 */
    class AsyncNetworkTask implements Runnable {
        final /* synthetic */ AsyncNetworkTask f1240a;

        AsyncNetworkTask(AsyncNetworkTask asyncNetworkTask) {
            this.f1240a = asyncNetworkTask;
        }

        public void run() {
            NetworkResponse a = new NetworkConnection(this.f1240a.f1241a).m1429a();
            if (a.m1433a()) {
                this.f1240a.f1242b.m1419b(a);
            } else {
                this.f1240a.f1242b.m1418a(a);
            }
        }
    }

    /* renamed from: com.inmobi.commons.core.network.a.a */
    public interface AsyncNetworkTask {
        void m1418a(NetworkResponse networkResponse);

        void m1419b(NetworkResponse networkResponse);
    }

    public AsyncNetworkTask(NetworkRequest networkRequest, AsyncNetworkTask asyncNetworkTask) {
        this.f1241a = networkRequest;
        this.f1242b = asyncNetworkTask;
    }

    public void m1422a() {
        new Thread(new AsyncNetworkTask(this)).start();
    }
}
