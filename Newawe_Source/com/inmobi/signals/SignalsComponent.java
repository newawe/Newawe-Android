package com.inmobi.signals;

import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent.ConfigComponent;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.SessionInfo;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.signals.SignalsConfig.SignalsConfig;

/* renamed from: com.inmobi.signals.p */
public class SignalsComponent implements ConfigComponent {
    private static final String f3897a;
    private static final Object f3898b;
    private static volatile SignalsComponent f3899c;
    private IceCollector f3900d;
    private CarbWorker f3901e;
    private SignalsConfig f3902f;
    private boolean f3903g;

    static {
        f3897a = SignalsComponent.class.getSimpleName();
        f3898b = new Object();
    }

    public static SignalsComponent m4580a() {
        SignalsComponent signalsComponent = f3899c;
        if (signalsComponent == null) {
            synchronized (f3898b) {
                signalsComponent = f3899c;
                if (signalsComponent == null) {
                    signalsComponent = new SignalsComponent();
                    f3899c = signalsComponent;
                }
            }
        }
        return signalsComponent;
    }

    private SignalsComponent() {
        this.f3903g = false;
        this.f3902f = new SignalsConfig();
        com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(this.f3902f, (ConfigComponent) this);
        SessionInfo.m1525a().m1529a(m4585e().m2069i());
        LocationInfo.m1981a().m1991a(m4585e().m2068h());
        TelemetryComponent.m4448a().m4471a(this.f3902f.m4591a(), this.f3902f.m4596e());
    }

    public void m4581a(Config config) {
        this.f3902f = (SignalsConfig) config;
        LocationInfo.m1981a().m1991a(m4585e().m2068h());
        SessionInfo.m1525a().m1529a(m4585e().m2069i());
        TelemetryComponent.m4448a().m4471a(this.f3902f.m4591a(), this.f3902f.m4596e());
    }

    public synchronized void m4582b() {
        if (!this.f3903g) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "Starting signals component.");
            this.f3903g = true;
            m4587g();
        }
    }

    public synchronized void m4583c() {
        if (this.f3903g) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "Stopping signals component.");
            this.f3903g = false;
            m4588h();
        }
    }

    UidMap m4584d() {
        return new UidMap(this.f3902f.m1350n().m1344a());
    }

    public SignalsConfig m4585e() {
        return this.f3902f.m4597f();
    }

    public SignalsConfig m4586f() {
        return this.f3902f.m4598g();
    }

    synchronized void m4587g() {
        if (!this.f3903g) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "Ice can not be started as Signals component has not been started.");
        } else if (m4585e().m2061a()) {
            SessionManager.m1995a().m1996b();
            if (this.f3900d == null) {
                this.f3900d = new IceCollector();
                this.f3900d.m1961a();
            } else {
                this.f3900d.m1961a();
            }
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "User data collection is disabled.");
        }
    }

    void m4588h() {
        SessionManager.m1995a().m1997c();
        if (this.f3900d != null) {
            this.f3900d.m1963c();
        }
    }

    public void m4589i() {
        if (!this.f3903g) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "Carb can not be started as Signals component has not been started.");
        } else if (!m4586f().m2015a()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3897a, "Carb is disabled.");
        } else if (this.f3901e == null) {
            this.f3901e = new CarbWorker();
            this.f3901e.m1946a(m4586f());
        } else {
            this.f3901e.m1946a(m4586f());
        }
    }
}
