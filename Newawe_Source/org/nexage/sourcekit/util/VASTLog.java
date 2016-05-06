package org.nexage.sourcekit.util;

import android.util.Log;

public class VASTLog {
    private static LOG_LEVEL LEVEL;
    private static String TAG;

    public enum LOG_LEVEL {
        verbose(1),
        debug(2),
        info(3),
        warning(4),
        error(5),
        none(6);
        
        private int value;

        private LOG_LEVEL(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static {
        TAG = "VAST";
        LEVEL = LOG_LEVEL.error;
    }

    public static void m3662v(String tag, String msg) {
        if (LEVEL.getValue() <= LOG_LEVEL.verbose.getValue()) {
            Log.v(tag, msg);
        }
    }

    public static void m3658d(String tag, String msg) {
        if (LEVEL.getValue() <= LOG_LEVEL.debug.getValue()) {
            Log.d(tag, msg);
        }
    }

    public static void m3661i(String tag, String msg) {
        if (LEVEL.getValue() <= LOG_LEVEL.info.getValue()) {
            Log.i(tag, msg);
        }
    }

    public static void m3663w(String tag, String msg) {
        if (LEVEL.getValue() <= LOG_LEVEL.warning.getValue()) {
            Log.w(tag, msg);
        }
    }

    public static void m3659e(String tag, String msg) {
        if (LEVEL.getValue() <= LOG_LEVEL.error.getValue()) {
            Log.e(tag, msg);
        }
    }

    public static void m3660e(String tag, String msg, Throwable tr) {
        if (LEVEL.getValue() <= LOG_LEVEL.error.getValue()) {
            Log.e(tag, msg, tr);
        }
    }

    public static void setLoggingLevel(LOG_LEVEL logLevel) {
        Log.i(TAG, "Changing logging level from :" + LEVEL + ". To:" + logLevel);
        LEVEL = logLevel;
    }

    public static LOG_LEVEL getLoggingLevel() {
        return LEVEL;
    }
}
