package com.immersion.hapticmediasdk.utils;

import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class Log {
    private static final boolean f1020a = false;
    public static int f1021b044A044A044A = 48;
    public static int f1022b044A044A044A = 1;
    public static int f1023b044A044A = 0;
    public static int f1024b044A044A = 2;

    public Log() {
        if (((f1021b044A044A044A + f1022b044A044A044A) * f1021b044A044A044A) % m1112b044A044A044A() != f1023b044A044A) {
            f1021b044A044A044A = 0;
            f1023b044A044A = m1110b044A044A044A044A();
        }
    }

    public static int m1110b044A044A044A044A() {
        return 1;
    }

    public static int m1111b044A044A044A() {
        return 1;
    }

    public static int m1112b044A044A044A() {
        return 2;
    }

    public static void m1113d(String str, String str2) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1114e(java.lang.String r2, java.lang.String r3) {
        /*
        android.util.Log.e(r2, r3);
    L_0x0003:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000c;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0003;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0007;
    L_0x000c:
        r0 = m1110b044A044A044A044A();
        r1 = f1022b044A044A044A;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f1024b044A044A;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0024;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = m1110b044A044A044A044A();
        f1021b044A044A044A = r0;
        r0 = 56;
        f1023b044A044A = r0;
    L_0x0024:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.utils.Log.e(java.lang.String, java.lang.String):void");
    }

    public static void m1115i(String str, String str2) {
        int i = f1021b044A044A044A;
        switch ((i * (m1111b044A044A044A() + i)) % f1024b044A044A) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f1021b044A044A044A = 75;
                f1023b044A044A = 9;
                break;
        }
        android.util.Log.i(str, str2);
    }

    public static void m1116v(String str, String str2) {
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

    public static void m1117w(String str, String str2) {
        android.util.Log.w(str, str2);
    }
}
