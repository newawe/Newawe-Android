package com.inmobi.commons.core.utilities;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class Logger {
    private static InternalLogLevel f1255a;

    /* renamed from: com.inmobi.commons.core.utilities.Logger.1 */
    static /* synthetic */ class C06891 {
        static final /* synthetic */ int[] f1254a;

        static {
            f1254a = new int[InternalLogLevel.values().length];
            try {
                f1254a[InternalLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1254a[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1254a[InternalLogLevel.INTERNAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum InternalLogLevel {
        NONE,
        ERROR,
        DEBUG,
        INTERNAL
    }

    public static void m1440a(InternalLogLevel internalLogLevel, String str, String str2) {
        if (internalLogLevel.ordinal() <= f1255a.ordinal()) {
            switch (C06891.f1254a[internalLogLevel.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    Log.e("[InMobi]", str2);
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    Log.d("[InMobi]", str2);
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    Log.d(str, str2);
                default:
            }
        }
    }

    public static void m1441a(InternalLogLevel internalLogLevel, String str, String str2, Throwable th) {
        if (internalLogLevel.ordinal() <= f1255a.ordinal()) {
            switch (C06891.f1254a[internalLogLevel.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    Log.e("[InMobi]", str2, th);
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    Log.d("[InMobi]", str2, th);
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    Log.d(str, str2, th);
                default:
            }
        }
    }

    public static void m1439a(InternalLogLevel internalLogLevel) {
        f1255a = internalLogLevel;
    }

    static {
        f1255a = "production".equalsIgnoreCase("staging") ? InternalLogLevel.INTERNAL : InternalLogLevel.NONE;
    }
}
