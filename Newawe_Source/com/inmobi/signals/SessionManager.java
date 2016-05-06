package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.SessionInfo;
import java.util.UUID;

/* renamed from: com.inmobi.signals.o */
public class SessionManager {
    private static final String f1765a;
    private static SessionManager f1766b;
    private static Object f1767c;

    static {
        f1765a = SessionManager.class.getSimpleName();
        f1767c = new Object();
    }

    public static SessionManager m1995a() {
        SessionManager sessionManager = f1766b;
        if (sessionManager == null) {
            synchronized (f1767c) {
                sessionManager = f1766b;
                if (sessionManager == null) {
                    f1766b = new SessionManager();
                    sessionManager = f1766b;
                }
            }
        }
        return sessionManager;
    }

    private SessionManager() {
    }

    void m1996b() {
        if (SignalsComponent.m4580a().m4585e().m2069i()) {
            SessionInfo.m1525a().m1528a(UUID.randomUUID().toString());
            SessionInfo.m1525a().m1527a(System.currentTimeMillis());
            SessionInfo.m1525a().m1531b(0);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1765a, "Session tracking started.");
        }
    }

    void m1997c() {
        if (SignalsComponent.m4580a().m4585e().m2069i()) {
            SessionInfo.m1525a().m1531b(System.currentTimeMillis());
            Logger.m1440a(InternalLogLevel.INTERNAL, f1765a, "Session tracking stopped.");
        }
    }

    SessionInfo m1998d() {
        return SessionInfo.m1525a();
    }
}
