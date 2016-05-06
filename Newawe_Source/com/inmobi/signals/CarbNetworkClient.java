package com.inmobi.signals;

import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.signals.e */
public class CarbNetworkClient {
    private static final String f1732a;

    static {
        f1732a = CarbNetworkClient.class.getSimpleName();
    }

    public CarbGetListNetworkResponse m1933a(CarbGetListNetworkRequest carbGetListNetworkRequest) {
        CarbGetListNetworkResponse carbGetListNetworkResponse = new CarbGetListNetworkResponse(new SyncNetworkTask(carbGetListNetworkRequest).m1436a());
        if (carbGetListNetworkResponse.m1926a()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1732a, "Getting Carb list from server failed.");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1732a, "Getting Carb list from server succeeded.");
        }
        return carbGetListNetworkResponse;
    }

    public boolean m1934a(CarbPostListNetworkRequest carbPostListNetworkRequest) {
        boolean z = false;
        int i = 0;
        while (i <= carbPostListNetworkRequest.m4574b()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1732a, "Attempting to send pruned Carb list to server.");
            if (!new SyncNetworkTask(carbPostListNetworkRequest).m1436a().m1433a()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1732a, "Sending pruned Carb list to server succeeded.");
                z = true;
                break;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, f1732a, "Sending pruned Carb list to server failed.");
            i++;
            if (i > carbPostListNetworkRequest.m4574b()) {
                break;
            }
            try {
                Thread.sleep((long) (carbPostListNetworkRequest.m4575c() * DateUtils.MILLIS_IN_SECOND));
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1732a, "User data network client interrupted while sleeping.", e);
            }
        }
        if (!z) {
            TelemetryComponent.m4448a().m4468a(new TelemetryEvent("signals", "CarbUploadDiscarded"));
        }
        return z;
    }
}
