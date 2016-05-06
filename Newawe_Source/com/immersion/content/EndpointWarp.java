package com.immersion.content;

import android.content.Context;
import android.util.Log;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class EndpointWarp {
    private static final String f837b = "EndpointWarp";
    public static int f838b0415041504150415 = 39;
    public static int f839b0415041504150415 = 1;
    public static int f840b041504150415 = 2;
    public static int f841b04150415;
    long f842a;

    public EndpointWarp(Context context, byte b, byte b2, byte b3, byte b4, int i, short s, byte b5, byte[] bArr, byte b6) {
        if (((f838b0415041504150415 + m983b041504150415()) * f838b0415041504150415) % f840b041504150415 != f841b04150415) {
            f838b0415041504150415 = 10;
            f841b04150415 = m982b0415041504150415();
        }
        this.f842a = create(context, b, b2, b3, b4, i, s, b5, bArr, b6);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EndpointWarp(android.content.Context r3, byte[] r4, int r5) {
        /*
        r2 = this;
        r0 = 1;
        r2.<init>();
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0004;
            case 1: goto L_0x000b;
            default: goto L_0x0007;
        };
    L_0x0007:
        switch(r0) {
            case 0: goto L_0x0004;
            case 1: goto L_0x000b;
            default: goto L_0x000a;
        };
    L_0x000a:
        goto L_0x0007;
    L_0x000b:
        r0 = f838b0415041504150415;
        r1 = f839b0415041504150415;
        r0 = r0 + r1;
        r1 = f838b0415041504150415;
        r0 = r0 * r1;
        r1 = f840b041504150415;
        r0 = r0 % r1;
        r1 = m984b041504150415();
        if (r0 == r1) goto L_0x0028;
    L_0x001c:
        r0 = m982b0415041504150415();
        f838b0415041504150415 = r0;
        r0 = m982b0415041504150415();
        f841b04150415 = r0;
    L_0x0028:
        r0 = r2.createWarp(r3, r4, r5);
        r2.f842a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.content.EndpointWarp.<init>(android.content.Context, byte[], int):void");
    }

    public static int m982b0415041504150415() {
        return 29;
    }

    public static int m983b041504150415() {
        return 1;
    }

    public static int m984b041504150415() {
        return 0;
    }

    private native long create(Context context, byte b, byte b2, byte b3, byte b4, int i, short s, byte b5, byte[] bArr, byte b6);

    private native long createWarp(Context context, byte[] bArr, int i);

    private native void disposeWarp(long j);

    private native void flushWarp(long j);

    private native long getWarpCurrentPosition(long j);

    public static boolean loadSharedLibrary() {
        try {
            System.loadLibrary("ImmEndpointWarpJ");
            return true;
        } catch (UnsatisfiedLinkError e) {
            if (System.getProperty("java.vm.name").contains("Java HotSpot")) {
                return true;
            }
            Log.e(f837b, "Unable to load libImmEndpointWarpJ.so.Please make sure this file is in the libs/armeabi folder.");
            if (((m982b0415041504150415() + f839b0415041504150415) * m982b0415041504150415()) % f840b041504150415 != f841b04150415) {
                f838b0415041504150415 = m982b0415041504150415();
                f841b04150415 = m982b0415041504150415();
            }
            e.printStackTrace();
            return false;
        }
    }

    private native void startWarp(long j);

    private native void stopWarp(long j);

    private native void updateWarp(long j, byte[] bArr, int i, long j2, long j3);

    public void dispose() {
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                f838b0415041504150415 = 82;
                try {
                    disposeWarp(this.f842a);
                    return;
                } catch (Exception e2) {
                    throw e2;
                }
            }
        }
    }

    public void flush() {
        flushWarp(this.f842a);
    }

    public long getCurrentPosition() {
        if (((f838b0415041504150415 + f839b0415041504150415) * f838b0415041504150415) % f840b041504150415 != f841b04150415) {
            f838b0415041504150415 = m982b0415041504150415();
            f841b04150415 = m982b0415041504150415();
        }
        return getWarpCurrentPosition(this.f842a);
    }

    public void start() {
        try {
            long j = this.f842a;
            int i = f838b0415041504150415;
            switch ((i * (f839b0415041504150415 + i)) % f840b041504150415) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f838b0415041504150415 = 27;
                    f841b04150415 = m982b0415041504150415();
                    break;
            }
            try {
                startWarp(j);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void stop() {
        long j = this.f842a;
        if (((m982b0415041504150415() + f839b0415041504150415) * m982b0415041504150415()) % f840b041504150415 != f841b04150415) {
            f838b0415041504150415 = 12;
            f841b04150415 = m982b0415041504150415();
        }
        stopWarp(j);
    }

    public void update(byte[] bArr, int i, long j, long j2) {
        try {
            updateWarp(this.f842a, bArr, i, j, j2);
            if (((f838b0415041504150415 + f839b0415041504150415) * f838b0415041504150415) % f840b041504150415 != f841b04150415) {
                f838b0415041504150415 = m982b0415041504150415();
                f841b04150415 = m982b0415041504150415();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
