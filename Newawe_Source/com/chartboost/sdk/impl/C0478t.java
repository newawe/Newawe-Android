package com.chartboost.sdk.impl;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.chartboost.sdk.impl.t */
public class C0478t {
    public static String f777a;
    public static boolean f778b;

    /* renamed from: com.chartboost.sdk.impl.t.a */
    static class C0477a {
        public static final boolean f774a;
        private final List<C0476a> f775b;
        private boolean f776c;

        /* renamed from: com.chartboost.sdk.impl.t.a.a */
        private static class C0476a {
            public final String f771a;
            public final long f772b;
            public final long f773c;

            public C0476a(String str, long j, long j2) {
                this.f771a = str;
                this.f772b = j;
                this.f773c = j2;
            }
        }

        C0477a() {
            this.f775b = new ArrayList();
            this.f776c = false;
        }

        static {
            f774a = C0478t.f778b;
        }

        public synchronized void m836a(String str, long j) {
            if (this.f776c) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.f775b.add(new C0476a(str, j, SystemClock.elapsedRealtime()));
        }

        public synchronized void m835a(String str) {
            this.f776c = true;
            if (m834a() > 0) {
                long j = ((C0476a) this.f775b.get(0)).f773c;
                C0478t.m839b("(%-4d ms) %s", Long.valueOf(r2), str);
                long j2 = j;
                for (C0476a c0476a : this.f775b) {
                    C0478t.m839b("(+%-4d) [%2d] %s", Long.valueOf(c0476a.f773c - j2), Long.valueOf(c0476a.f772b), c0476a.f771a);
                    j2 = c0476a.f773c;
                }
            }
        }

        protected void finalize() throws Throwable {
            if (!this.f776c) {
                m835a("Request on the loose");
                C0478t.m840c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        private long m834a() {
            if (this.f775b.size() == 0) {
                return 0;
            }
            return ((C0476a) this.f775b.get(this.f775b.size() - 1)).f773c - ((C0476a) this.f775b.get(0)).f773c;
        }
    }

    static {
        f777a = "Volley";
        f778b = Log.isLoggable(f777a, 2);
    }

    public static void m837a(String str, Object... objArr) {
        if (f778b) {
            Log.v(f777a, C0478t.m841d(str, objArr));
        }
    }

    public static void m839b(String str, Object... objArr) {
        Log.d(f777a, C0478t.m841d(str, objArr));
    }

    public static void m840c(String str, Object... objArr) {
        Log.e(f777a, C0478t.m841d(str, objArr));
    }

    public static void m838a(Throwable th, String str, Object... objArr) {
        Log.e(f777a, C0478t.m841d(str, objArr), th);
    }

    private static String m841d(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str3 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(C0478t.class)) {
                str3 = stackTrace[i].getClassName();
                str3 = str3.substring(str3.lastIndexOf(46) + 1);
                str2 = str3.substring(str3.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
        }
        str2 = str3;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }
}
