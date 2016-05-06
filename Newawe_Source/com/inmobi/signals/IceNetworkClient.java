package com.inmobi.signals;

import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.signals.j */
public class IceNetworkClient {
    private static final String f1745a;
    private IceNetworkRequest f1746b;

    /* renamed from: com.inmobi.signals.j.1 */
    class IceNetworkClient implements Runnable {
        final /* synthetic */ IceNetworkClient f1744a;

        IceNetworkClient(IceNetworkClient iceNetworkClient) {
            this.f1744a = iceNetworkClient;
        }

        public void run() {
            int i = 0;
            while (i <= this.f1744a.f1746b.m4578b()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, IceNetworkClient.f1745a, "Attempting to send samples to server.");
                if (new SyncNetworkTask(this.f1744a.f1746b).m1436a().m1433a()) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, IceNetworkClient.f1745a, "Sending samples to server failed.");
                    i++;
                    if (i > this.f1744a.f1746b.m4578b()) {
                        TelemetryComponent.m4448a().m4468a(new TelemetryEvent("signals", "RetryCountExceeded"));
                        return;
                    }
                    try {
                        Thread.sleep((long) (this.f1744a.f1746b.m4579c() * DateUtils.MILLIS_IN_SECOND));
                    } catch (Throwable e) {
                        Logger.m1441a(InternalLogLevel.INTERNAL, IceNetworkClient.f1745a, "User data network client interrupted while sleeping.", e);
                    }
                } else {
                    Logger.m1440a(InternalLogLevel.INTERNAL, IceNetworkClient.f1745a, "Sending samples to server succeeded.");
                    return;
                }
            }
        }
    }

    static {
        f1745a = IceNetworkClient.class.getSimpleName();
    }

    public IceNetworkClient(IceNetworkRequest iceNetworkRequest) {
        this.f1746b = iceNetworkRequest;
    }

    public void m1966a() {
        new Thread(new IceNetworkClient(this)).start();
    }
}
