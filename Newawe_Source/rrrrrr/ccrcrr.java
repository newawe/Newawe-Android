package rrrrrr;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class ccrcrr implements Runnable {
    public static int f3310b044A044A044A044A = 2;
    public static int f3311b044A044A044A = 45;
    public static int f3312b044A044A044A = 1;
    private final byte[] f3313a;
    private final long f3314b;
    public final /* synthetic */ HapticPlaybackThread f3315b04250425;
    private final long f3316c;
    private final int f3317d;
    private final long f3318e;

    public ccrcrr(HapticPlaybackThread hapticPlaybackThread, long j, long j2, byte[] bArr, int i, long j3) {
        try {
            this.f3315b04250425 = hapticPlaybackThread;
            this.f3313a = bArr;
            this.f3314b = j;
            int i2 = f3311b044A044A044A;
            switch ((i2 * (f3312b044A044A044A + i2)) % f3310b044A044A044A044A) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f3311b044A044A044A = 15;
                    f3312b044A044A044A = m3664b044A044A044A();
                    break;
            }
            try {
                this.f3316c = j2;
                this.f3317d = i;
                this.f3318e = j3;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static int m3664b044A044A044A() {
        return 32;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r8 = this;
        r0 = r8.f3315b04250425;
        r0 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1030b0411041104110411(r0);
        if (r0 == 0) goto L_0x007f;
    L_0x0008:
        r0 = r8.f3315b04250425;
        r1 = r0.f937o;
        monitor-enter(r1);
        r0 = r8.f3315b04250425;	 Catch:{ all -> 0x0083 }
        r0 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1034b0411041104110411(r0);	 Catch:{ all -> 0x0083 }
        r0.remove(r8);	 Catch:{ all -> 0x0083 }
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        r0 = r8.f3314b;
        r2 = r8.f3316c;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x0070;
    L_0x0021:
        r0 = r8.f3315b04250425;
        r0 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1044b0411041104110411(r0);
        r0 = r0.areHapticsEnabled();
        if (r0 == 0) goto L_0x0049;
    L_0x002d:
        r0 = r8.f3315b04250425;
        r1 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1027b04110411041104110411(r0);
    L_0x0033:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0033;
            case 1: goto L_0x003c;
            default: goto L_0x0037;
        };
    L_0x0037:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x003c;
            case 1: goto L_0x0033;
            default: goto L_0x003b;
        };
    L_0x003b:
        goto L_0x0037;
    L_0x003c:
        r2 = r8.f3313a;
        r0 = r8.f3313a;
        r3 = r0.length;
        r4 = r8.f3318e;
        r0 = r8.f3317d;
        r6 = (long) r0;
        r1.update(r2, r3, r4, r6);
    L_0x0049:
        r0 = r8.f3315b04250425;
        r1 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1055b04110411(r0);
        monitor-enter(r1);
        r0 = r8.f3315b04250425;	 Catch:{ all -> 0x0080 }
        r2 = r8.f3315b04250425;	 Catch:{ all -> 0x0080 }
        r2 = r2.f927e;	 Catch:{ all -> 0x0080 }
        com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1039b041104110411(r0, r2);	 Catch:{ all -> 0x0080 }
        r0 = r8.f3315b04250425;	 Catch:{ all -> 0x0080 }
        r2 = r8.f3315b04250425;	 Catch:{ all -> 0x0080 }
        r2 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1031b0411041104110411(r2);	 Catch:{ all -> 0x0080 }
        com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1048b041104110411(r0, r2);	 Catch:{ all -> 0x0080 }
        r0 = r8.f3315b04250425;	 Catch:{ all -> 0x0080 }
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0080 }
        com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1052b041104110411(r0, r2);	 Catch:{ all -> 0x0080 }
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
    L_0x0070:
        r0 = r8.f3315b04250425;
        r0 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1046b041104110411(r0);
        r1 = r8.f3315b04250425;
        r1 = com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.m1035b0411041104110411(r1);
        r0.post(r1);
    L_0x007f:
        return;
    L_0x0080:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        throw r0;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rrrrrr.ccrcrr.run():void");
    }
}
