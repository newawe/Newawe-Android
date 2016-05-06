package com.chartboost.sdk.Libraries;

import android.util.Log;

public final class CBLogging {
    public static Level f84a;
    private static String f85b;

    public enum Level {
        NONE,
        INTEGRATION,
        ALL
    }

    static {
        f84a = Level.INTEGRATION;
        f85b = "Chartboost SDK";
    }

    private static String m74a(Object obj) {
        String name = (obj == null || (obj instanceof String)) ? obj : obj.getClass().getName();
        return name;
    }

    public static void m75a(Object obj, String str) {
        if (f84a == Level.ALL) {
            Log.d(m74a(obj), str);
        }
    }

    public static void m76a(Object obj, String str, Throwable th) {
        if (f84a == Level.ALL) {
            Log.d(m74a(obj), str, th);
        }
    }

    public static void m77b(Object obj, String str) {
        if (f84a == Level.ALL) {
            Log.e(m74a(obj), str);
        }
    }

    public static void m78b(Object obj, String str, Throwable th) {
        if (f84a == Level.ALL) {
            Log.e(m74a(obj), str, th);
        }
    }

    public static void m79c(Object obj, String str) {
        if (f84a == Level.ALL) {
            Log.v(m74a(obj), str);
        }
    }

    public static void m80c(Object obj, String str, Throwable th) {
        if (f84a == Level.ALL) {
            Log.v(m74a(obj), str, th);
        }
    }

    public static void m81d(Object obj, String str) {
        if (f84a == Level.ALL) {
            Log.w(m74a(obj), str);
        }
    }

    public static void m82d(Object obj, String str, Throwable th) {
        if (f84a == Level.ALL) {
            Log.w(m74a(obj), str, th);
        }
    }

    public static void m83e(Object obj, String str) {
        if (f84a == Level.ALL) {
            Log.i(m74a(obj), str);
        }
    }
}
