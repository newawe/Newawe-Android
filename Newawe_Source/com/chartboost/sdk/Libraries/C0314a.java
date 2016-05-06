package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Libraries.C0328g.C0326a;
import com.chartboost.sdk.Libraries.C0328g.C1007e;
import org.apache.http.HttpStatus;

/* renamed from: com.chartboost.sdk.Libraries.a */
public interface C0314a {
    public static final C0326a f87a;

    /* renamed from: com.chartboost.sdk.Libraries.a.1 */
    static class C13051 extends C1007e {
        C13051() {
        }

        public boolean m5020a(Object obj) {
            int intValue = ((Number) obj).intValue();
            return intValue >= HttpStatus.SC_OK && intValue < HttpStatus.SC_MULTIPLE_CHOICES;
        }

        public String m5019a() {
            return "Must be a valid status code (>=200 && <300)";
        }
    }

    static {
        f87a = C0328g.m181b(C0328g.m179b(), new C13051());
    }
}
