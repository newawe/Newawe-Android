package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.rendering.mraid.j */
public class MraidJsFetcher {
    private static final String f1657a;
    private String f1658b;
    private int f1659c;
    private int f1660d;
    private NetworkRequest f1661e;

    /* renamed from: com.inmobi.rendering.mraid.j.1 */
    class MraidJsFetcher implements Runnable {
        final /* synthetic */ MraidJsFetcher f1656a;

        MraidJsFetcher(MraidJsFetcher mraidJsFetcher) {
            this.f1656a = mraidJsFetcher;
        }

        public void run() {
            int i = 0;
            while (i <= this.f1656a.f1659c) {
                Logger.m1440a(InternalLogLevel.INTERNAL, MraidJsFetcher.f1657a, "Attempting to get MRAID Js.");
                NetworkResponse a = new SyncNetworkTask(this.f1656a.f1661e).m1436a();
                if (a.m1433a()) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, MraidJsFetcher.f1657a, "Getting MRAID Js from server failed.");
                    i++;
                    if (i <= this.f1656a.f1659c) {
                        try {
                            Thread.sleep((long) (this.f1656a.f1660d * DateUtils.MILLIS_IN_SECOND));
                        } catch (Throwable e) {
                            Logger.m1441a(InternalLogLevel.INTERNAL, MraidJsFetcher.f1657a, "MRAID Js client interrupted while sleeping.", e);
                        }
                    } else {
                        return;
                    }
                }
                Logger.m1440a(InternalLogLevel.INTERNAL, MraidJsFetcher.f1657a, "Getting MRAID Js from server succeeded. Response:" + a.m1434b());
                new MraidJsDao().m1815a(a.m1434b());
                return;
            }
        }
    }

    static {
        f1657a = MraidJsFetcher.class.getSimpleName();
    }

    public MraidJsFetcher(String str, int i, int i2) {
        this.f1658b = str;
        this.f1659c = i;
        this.f1660d = i2;
    }

    public void m1822a() {
        if (this.f1658b == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1657a, "MRAID Js Url provided is invalid.");
            return;
        }
        this.f1661e = new NetworkRequest(RequestType.GET, this.f1658b, false, null);
        new Thread(new MraidJsFetcher(this)).start();
    }
}
