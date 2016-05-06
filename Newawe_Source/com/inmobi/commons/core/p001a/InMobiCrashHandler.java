package com.inmobi.commons.core.p001a;

import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent.ConfigComponent;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.inmobi.commons.core.a.c */
public class InMobiCrashHandler implements UncaughtExceptionHandler {
    private static final String f1150b;
    private static boolean f1151c;
    private static InMobiCrashHandler f1152d;
    private UncaughtExceptionHandler f1153a;

    /* renamed from: com.inmobi.commons.core.a.c.a */
    static class InMobiCrashHandler implements ConfigComponent {
        InMobiCrashHandler() {
        }

        public void m4446a(Config config) {
            TelemetryComponent.m4448a().m4471a(config.m1345a(), ((CrashConfig) config).m4445e());
        }
    }

    static {
        f1150b = InMobiCrashHandler.class.getSimpleName();
        f1151c = false;
    }

    private InMobiCrashHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f1153a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (m1274a(th)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1150b, "Crash due to inmobi, will report it");
            TelemetryComponent.m4448a().m4468a(new CrashEvent(thread, th));
        }
        this.f1153a.uncaughtException(thread, th);
    }

    private boolean m1274a(Throwable th) {
        for (StackTraceElement className : th.getStackTrace()) {
            if (className.getClassName().contains("com.inmobi.")) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void m1273a() {
        synchronized (InMobiCrashHandler.class) {
            if (!f1151c) {
                Config crashConfig = new CrashConfig();
                f1152d = new InMobiCrashHandler();
                com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(crashConfig, f1152d);
                TelemetryComponent.m4448a().m4471a(crashConfig.m4440a(), crashConfig.m4445e());
                Thread.setDefaultUncaughtExceptionHandler(new InMobiCrashHandler(Thread.getDefaultUncaughtExceptionHandler()));
                f1151c = true;
            }
        }
    }
}
