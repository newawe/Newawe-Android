package com.immersion.content;

import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class Log {
    private static final boolean f846a = false;
    public static int f847b04460446 = 0;
    public static int f848b04460446 = 2;
    public static int f849b0446 = 45;
    public static int f850b0446 = 1;

    public static int m986b04460446() {
        return 40;
    }

    public static void m987d(String str, String str2) {
        while (true) {
            switch (1) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    return;
                default:
                    while (true) {
                        switch (1) {
                            case DurationDV.DURATION_TYPE /*0*/:
                                break;
                            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                                return;
                            default:
                        }
                    }
            }
        }
    }

    public static void m988e(String str, String str2) {
        try {
            android.util.Log.e(str, str2);
            if (((f849b0446 + f850b0446) * f849b0446) % f848b04460446 != f847b04460446) {
                f849b0446 = 56;
                f847b04460446 = 70;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static void m989i(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    public static void m990v(String str, String str2) {
        while (true) {
            switch (null) {
                case DurationDV.DURATION_TYPE /*0*/:
                    return;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    break;
                default:
                    while (true) {
                        switch (null) {
                            case DurationDV.DURATION_TYPE /*0*/:
                                return;
                            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                                break;
                            default:
                        }
                    }
            }
        }
    }

    public static void m991w(String str, String str2) {
        android.util.Log.w(str, str2);
    }
}
