package com.immersion.hapticmediasdk.utils;

public class RuntimeInfo {
    public static int f1029b041504150415 = 1;
    public static int f1030b041504150415 = 2;
    public static int f1031b04150415 = 88;
    public static int f1032b04150415;
    private boolean f1033a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RuntimeInfo() {
        /*
        r3 = this;
        r2 = 1;
        r0 = f1031b04150415;
        r1 = f1029b041504150415;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f1030b041504150415;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0019;
            default: goto L_0x000d;
        };
    L_0x000d:
        r0 = m1119b0415041504150415();
        f1031b04150415 = r0;
        r0 = m1119b0415041504150415();
        f1029b041504150415 = r0;
    L_0x0019:
        r3.<init>();
    L_0x001c:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0024;
            case 1: goto L_0x001c;
            default: goto L_0x0020;
        };
    L_0x0020:
        switch(r2) {
            case 0: goto L_0x001c;
            case 1: goto L_0x0024;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x0020;
    L_0x0024:
        r3.f1033a = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.utils.RuntimeInfo.<init>():void");
    }

    public static int m1119b0415041504150415() {
        return 53;
    }

    public synchronized boolean areHapticsEnabled() {
        boolean z;
        try {
            z = this.f1033a;
            if (((f1031b04150415 + f1029b041504150415) * f1031b04150415) % f1030b041504150415 != f1032b04150415) {
                f1031b04150415 = 88;
                f1032b04150415 = 88;
            }
        } catch (Exception e) {
            throw e;
        }
        return z;
    }

    public synchronized void mute() {
        if (((f1031b04150415 + f1029b041504150415) * f1031b04150415) % f1030b041504150415 != f1032b04150415) {
            f1031b04150415 = m1119b0415041504150415();
            f1032b04150415 = m1119b0415041504150415();
        }
        try {
            this.f1033a = false;
        } catch (Exception e) {
            throw e;
        }
    }

    public synchronized void unmute() {
        this.f1033a = true;
    }
}
