package com.immersion.hapticmediasdk;

import android.content.Context;
import android.os.Handler;
import com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.immersion.hapticmediasdk.controllers.MediaController;
import com.immersion.hapticmediasdk.utils.Log;
import com.immersion.hapticmediasdk.utils.RuntimeInfo;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class MediaTaskManager implements Runnable {
    private static final String f874a = "MediaTaskManager";
    public static int f875b04150415 = 2;
    public static int f876b04150415 = 0;
    public static int f877b0415 = 1;
    public static int f878b04210421042104210421 = 37;
    private final Object f879b;
    private final Object f880c;
    private long f881d;
    private long f882e;
    private Handler f883f;
    private volatile SDKStatus f884g;
    private MediaController f885h;
    private String f886i;
    private boolean f887j;
    private Context f888k;
    private RuntimeInfo f889l;

    public MediaTaskManager(Handler handler, Context context, RuntimeInfo runtimeInfo) {
        if (((f878b04210421042104210421 + f877b0415) * f878b04210421042104210421) % f875b04150415 != m1004b0415()) {
            f878b04210421042104210421 = 47;
            f877b0415 = m1005b0415();
        }
        try {
            this.f879b = new Object();
            try {
                this.f880c = new Object();
                this.f884g = SDKStatus.NOT_INITIALIZED;
                this.f883f = handler;
                this.f888k = context;
                this.f889l = runtimeInfo;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int m1000a() {
        try {
            this.f883f.removeCallbacks(this);
            if (!(this.f885h == null || m1007d() == 0)) {
                Log.m1114e(f874a, "Could not dispose haptics, reset anyway.");
            }
            try {
                this.f886i = null;
                this.f881d = 0;
                this.f884g = SDKStatus.NOT_INITIALIZED;
                int i = f878b04210421042104210421;
                switch ((i * (m1003b04150415() + i)) % f875b04150415) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    default:
                        f878b04210421042104210421 = m1005b0415();
                        f877b0415 = 55;
                        break;
                }
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int m1001a(SDKStatus sDKStatus) {
        int i = f878b04210421042104210421;
        switch ((i * (f877b0415 + i)) % f875b04150415) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f878b04210421042104210421 = 19;
                f877b0415 = m1005b0415();
                break;
        }
        try {
            try {
                this.f883f.removeCallbacks(this);
                this.f884g = sDKStatus;
                if (this.f886i == null) {
                    return -4;
                }
                this.f885h = new MediaController(this.f883f.getLooper(), this);
                Handler controlHandler = this.f885h.getControlHandler();
                this.f885h.initHapticPlayback(new HapticPlaybackThread(this.f888k, this.f886i, controlHandler, this.f887j, this.f889l));
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int m1002b() {
        this.f883f.removeCallbacks(this);
        int onPrepared = this.f885h.onPrepared();
        if (onPrepared == 0) {
            this.f884g = SDKStatus.PLAYING;
            Handler handler = this.f883f;
            int i = f878b04210421042104210421;
            switch ((i * (f877b0415 + i)) % f875b04150415) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f878b04210421042104210421 = m1005b0415();
                    f876b04150415 = 68;
                    break;
            }
            handler.postDelayed(this, 1500);
        }
        return onPrepared;
    }

    public static int m1003b04150415() {
        return 1;
    }

    public static int m1004b0415() {
        return 0;
    }

    public static int m1005b0415() {
        return 54;
    }

    private int m1006c() {
        try {
            this.f883f.removeCallbacks(this);
            this.f881d = 0;
            if (((f878b04210421042104210421 + f877b0415) * f878b04210421042104210421) % f875b04150415 != f876b04150415) {
                f878b04210421042104210421 = m1005b0415();
                f876b04150415 = m1005b0415();
            }
            try {
                int stopHapticPlayback = this.f885h.stopHapticPlayback();
                if (stopHapticPlayback == 0) {
                    this.f884g = SDKStatus.STOPPED;
                }
                return stopHapticPlayback;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m1007d() {
        /*
        r3 = this;
        r0 = r3.m1006c();
        if (r0 != 0) goto L_0x0030;
    L_0x0006:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0006;
            default: goto L_0x000a;
        };
    L_0x000a:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0006;
            case 1: goto L_0x000f;
            default: goto L_0x000e;
        };
    L_0x000e:
        goto L_0x000a;
    L_0x000f:
        r1 = r3.f885h;
        r2 = r3.f883f;
        r1.onDestroy(r2);
        r1 = f878b04210421042104210421;
        r2 = f877b0415;
        r1 = r1 + r2;
        r2 = f878b04210421042104210421;
        r1 = r1 * r2;
        r2 = f875b04150415;
        r1 = r1 % r2;
        r2 = f876b04150415;
        if (r1 == r2) goto L_0x002d;
    L_0x0025:
        r1 = 80;
        f878b04210421042104210421 = r1;
        r1 = 44;
        f876b04150415 = r1;
    L_0x002d:
        r1 = 0;
        r3.f885h = r1;
    L_0x0030:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.d():int");
    }

    private int m1008e() {
        int i = 2;
        try {
            this.f883f.removeCallbacks(this);
            try {
                int onPause = this.f885h.onPause();
                if (onPause == 0) {
                    while (true) {
                        try {
                            i /= 0;
                        } catch (Exception e) {
                            f878b04210421042104210421 = m1005b0415();
                            this.f884g = SDKStatus.PAUSED;
                        }
                    }
                }
                return onPause;
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e22) {
            throw e22;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m1009f() {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.f883f;
        r1.removeCallbacks(r4);
        r1 = r4.f883f;
        r2 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r1 = r1.postDelayed(r4, r2);
        if (r1 == 0) goto L_0x0034;
    L_0x0010:
        switch(r0) {
            case 0: goto L_0x0018;
            case 1: goto L_0x0010;
            default: goto L_0x0013;
        };
    L_0x0013:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0018;
            default: goto L_0x0017;
        };
    L_0x0017:
        goto L_0x0013;
    L_0x0018:
        r1 = m1005b0415();
        r2 = f877b0415;
        r1 = r1 + r2;
        r2 = m1005b0415();
        r1 = r1 * r2;
        r2 = f875b04150415;
        r1 = r1 % r2;
        r2 = f876b04150415;
        if (r1 == r2) goto L_0x0033;
    L_0x002b:
        r1 = 70;
        f878b04210421042104210421 = r1;
        r1 = 50;
        f876b04150415 = r1;
    L_0x0033:
        return r0;
    L_0x0034:
        r0 = -1;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.f():int");
    }

    private int m1010g() {
        try {
            int onPause = this.f885h.onPause();
            if (onPause == 0) {
                if (((f878b04210421042104210421 + f877b0415) * f878b04210421042104210421) % f875b04150415 != m1004b0415()) {
                    f878b04210421042104210421 = 64;
                    f876b04150415 = 32;
                }
                this.f884g = SDKStatus.PAUSED_DUE_TO_TIMEOUT;
            }
            return onPause;
        } catch (Exception e) {
            throw e;
        }
    }

    private int m1011h() {
        try {
            int onPause = this.f885h.onPause();
            if (onPause == 0) {
                this.f884g = SDKStatus.PAUSED_DUE_TO_BUFFERING;
            }
            if (((f878b04210421042104210421 + f877b0415) * f878b04210421042104210421) % f875b04150415 != f876b04150415) {
                f878b04210421042104210421 = 29;
                f876b04150415 = m1005b0415();
            }
            return onPause;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m1012i() {
        /*
        r2 = this;
        r0 = f878b04210421042104210421;
        r1 = f877b0415;
        r0 = r0 + r1;
        r1 = f878b04210421042104210421;
        r0 = r0 * r1;
        r1 = f875b04150415;
        r0 = r0 % r1;
        r1 = f876b04150415;
        if (r0 == r1) goto L_0x0017;
    L_0x000f:
        r0 = 78;
        f878b04210421042104210421 = r0;
        r0 = 14;
        f876b04150415 = r0;
    L_0x0017:
        r0 = r2.m1002b();
        if (r0 != 0) goto L_0x0021;
    L_0x001d:
        r0 = r2.m1009f();
    L_0x0021:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x002a;
            case 1: goto L_0x0021;
            default: goto L_0x0025;
        };
    L_0x0025:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0021;
            case 1: goto L_0x002a;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0025;
    L_0x002a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.i():int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int SeekTo(int r4) {
        /*
        r3 = this;
        r2 = 1;
        r0 = m1005b0415();
        r1 = m1003b04150415();
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f875b04150415;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0018;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = 10;
        f878b04210421042104210421 = r0;
        r0 = 6;
        f877b0415 = r0;
    L_0x0018:
        r0 = (long) r4;
        r3.setMediaTimestamp(r0);
        r0 = r3.f885h;
        r0.seekTo(r4);
        r0 = r3.getSDKStatus();
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;
        if (r0 != r1) goto L_0x0037;
    L_0x0029:
        switch(r2) {
            case 0: goto L_0x0029;
            case 1: goto L_0x0030;
            default: goto L_0x002c;
        };
    L_0x002c:
        switch(r2) {
            case 0: goto L_0x0029;
            case 1: goto L_0x0030;
            default: goto L_0x002f;
        };
    L_0x002f:
        goto L_0x002c;
    L_0x0030:
        r0 = r3.f885h;
        r0 = r0.prepareHapticPlayback();
    L_0x0036:
        return r0;
    L_0x0037:
        r0 = 0;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.SeekTo(int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getMediaReferenceTime() {
        /*
        r4 = this;
        r1 = r4.f880c;
        monitor-enter(r1);
        r2 = r4.f882e;	 Catch:{ all -> 0x0007 }
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        return r2;
    L_0x0007:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
    L_0x0009:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0012;
            case 1: goto L_0x0009;
            default: goto L_0x000d;
        };
    L_0x000d:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        goto L_0x000d;
    L_0x0012:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.getMediaReferenceTime():long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getMediaTimestamp() {
        /*
        r4 = this;
        r1 = r4.f880c;
        monitor-enter(r1);
        r2 = r4.f881d;	 Catch:{ all -> 0x0007 }
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        return r2;
    L_0x0007:
        r0 = move-exception;
    L_0x0008:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0011;
            default: goto L_0x000c;
        };	 Catch:{ all -> 0x0007 }
    L_0x000c:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x0011;
            case 1: goto L_0x0008;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0007 }
    L_0x0010:
        goto L_0x000c;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0007 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.getMediaTimestamp():long");
    }

    public SDKStatus getSDKStatus() {
        SDKStatus sDKStatus;
        synchronized (this.f879b) {
            sDKStatus = this.f884g;
        }
        return sDKStatus;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        r2 = 1;
        java.lang.System.currentTimeMillis();
        r0 = f878b04210421042104210421;
        r1 = f877b0415;
        r0 = r0 + r1;
        r1 = f878b04210421042104210421;
        r0 = r0 * r1;
        r1 = f875b04150415;
        r0 = r0 % r1;
        r1 = f876b04150415;
        if (r0 == r1) goto L_0x001b;
    L_0x0013:
        r0 = 91;
        f878b04210421042104210421 = r0;
        r0 = 30;
        f876b04150415 = r0;
    L_0x001b:
        switch(r2) {
            case 0: goto L_0x001b;
            case 1: goto L_0x0022;
            default: goto L_0x001e;
        };
    L_0x001e:
        switch(r2) {
            case 0: goto L_0x001b;
            case 1: goto L_0x0022;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x001e;
    L_0x0022:
        r0 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED_DUE_TO_TIMEOUT;
        r3.transitToState(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.run():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHapticsUrl(java.lang.String r4, boolean r5) {
        /*
        r3 = this;
        r2 = 0;
        r1 = r3.f879b;
        monitor-enter(r1);
        r3.f886i = r4;	 Catch:{ all -> 0x000a }
        r3.f887j = r5;	 Catch:{ all -> 0x000a }
        monitor-exit(r1);	 Catch:{ all -> 0x000a }
        return;
    L_0x000a:
        r0 = move-exception;
    L_0x000b:
        switch(r2) {
            case 0: goto L_0x0012;
            case 1: goto L_0x000b;
            default: goto L_0x000e;
        };	 Catch:{ all -> 0x000a }
    L_0x000e:
        switch(r2) {
            case 0: goto L_0x0012;
            case 1: goto L_0x000b;
            default: goto L_0x0011;
        };	 Catch:{ all -> 0x000a }
    L_0x0011:
        goto L_0x000e;
    L_0x0012:
        monitor-exit(r1);	 Catch:{ all -> 0x000a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.setHapticsUrl(java.lang.String, boolean):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMediaReferenceTime() {
        /*
        r4 = this;
        r1 = r4.f880c;
        monitor-enter(r1);
        r0 = r4.f884g;	 Catch:{ all -> 0x001f }
    L_0x0005:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000e;
            default: goto L_0x0009;
        };	 Catch:{ all -> 0x001f }
    L_0x0009:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x000e;
            case 1: goto L_0x0005;
            default: goto L_0x000d;
        };	 Catch:{ all -> 0x001f }
    L_0x000d:
        goto L_0x0009;
    L_0x000e:
        r2 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001f }
        if (r0 != r2) goto L_0x0017;
    L_0x0012:
        r0 = r4.f885h;	 Catch:{ all -> 0x001f }
        r0.waitHapticStopped();	 Catch:{ all -> 0x001f }
    L_0x0017:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x001f }
        r4.f882e = r2;	 Catch:{ all -> 0x001f }
        monitor-exit(r1);	 Catch:{ all -> 0x001f }
        return;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.setMediaReferenceTime():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMediaTimestamp(long r6) {
        /*
        r5 = this;
        r4 = 1;
        r1 = r5.f880c;
        monitor-enter(r1);
        r0 = r5.f884g;	 Catch:{ all -> 0x0020 }
        r2 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x0020 }
        if (r0 != r2) goto L_0x000f;
    L_0x000a:
        r0 = r5.f885h;	 Catch:{ all -> 0x0020 }
        r0.waitHapticStopped();	 Catch:{ all -> 0x0020 }
    L_0x000f:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0020 }
        r5.f882e = r2;	 Catch:{ all -> 0x0020 }
    L_0x0015:
        switch(r4) {
            case 0: goto L_0x0015;
            case 1: goto L_0x001c;
            default: goto L_0x0018;
        };	 Catch:{ all -> 0x0020 }
    L_0x0018:
        switch(r4) {
            case 0: goto L_0x0015;
            case 1: goto L_0x001c;
            default: goto L_0x001b;
        };	 Catch:{ all -> 0x0020 }
    L_0x001b:
        goto L_0x0018;
    L_0x001c:
        r5.f881d = r6;	 Catch:{ all -> 0x0020 }
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        return;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.setMediaTimestamp(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int transitToState(com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus r7) {
        /*
        r6 = this;
        r1 = 0;
        r0 = -1;
        r2 = r6.f879b;
        monitor-enter(r2);
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.NOT_INITIALIZED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x000f;
    L_0x0009:
        r0 = r6.m1000a();	 Catch:{ all -> 0x001e }
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
    L_0x000e:
        return r0;
    L_0x000f:
        r3 = rrrrrr.crccrr.f3324b0425042504250425;	 Catch:{ all -> 0x001e }
        r4 = r6.f884g;	 Catch:{ all -> 0x001e }
        r4 = r4.ordinal();	 Catch:{ all -> 0x001e }
        r3 = r3[r4];	 Catch:{ all -> 0x001e }
        switch(r3) {
            case 1: goto L_0x0021;
            case 2: goto L_0x002a;
            case 3: goto L_0x0067;
            case 4: goto L_0x0091;
            case 5: goto L_0x00dd;
            case 6: goto L_0x00f2;
            case 7: goto L_0x0119;
            default: goto L_0x001c;
        };	 Catch:{ all -> 0x001e }
    L_0x001c:
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
        goto L_0x000e;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001e }
        throw r0;
    L_0x0021:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.INITIALIZED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0025:
        r0 = r6.m1001a(r7);	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x002a:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0051;
    L_0x002e:
        r0 = r6.m1012i();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0033:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0037:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.f884g = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0040:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00f9;
    L_0x0044:
        r0 = r6.f885h;	 Catch:{ all -> 0x001e }
        r4 = r6.f881d;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.setRequestBufferPosition(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.m1012i();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0051:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x005a;
    L_0x0055:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x005a:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x005e:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.f884g = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0067:
        r3 = 1;
        switch(r3) {
            case 0: goto L_0x0067;
            case 1: goto L_0x006f;
            default: goto L_0x006b;
        };	 Catch:{ all -> 0x001e }
    L_0x006b:
        switch(r1) {
            case 0: goto L_0x006f;
            case 1: goto L_0x0067;
            default: goto L_0x006e;
        };	 Catch:{ all -> 0x001e }
    L_0x006e:
        goto L_0x006b;
    L_0x006f:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0078;
    L_0x0073:
        r0 = r6.m1009f();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0078:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0081;
    L_0x007c:
        r0 = r6.m1008e();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0081:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED_DUE_TO_TIMEOUT;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00aa;
    L_0x0085:
        r0 = "MediaTaskManager";
        r1 = "Haptic playback is paused due to update time-out. Call update() to resume playback";
        com.immersion.hapticmediasdk.utils.Log.m1117w(r0, r1);	 Catch:{ all -> 0x001e }
        r0 = r6.m1010g();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0091:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00a3;
    L_0x0095:
        r0 = r6.f885h;	 Catch:{ all -> 0x001e }
        r4 = r6.f881d;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.setRequestBufferPosition(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.m1012i();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00a3:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x00c5;
    L_0x00a7:
        r0 = r1;
        goto L_0x001c;
    L_0x00aa:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED_DUE_TO_BUFFERING;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00bb;
    L_0x00ae:
        r0 = r6.m1011h();	 Catch:{ all -> 0x001e }
        r1 = "MediaTaskManager";
        r3 = "Haptic playback is paused due to slow data buffering...";
        com.immersion.hapticmediasdk.utils.Log.m1117w(r1, r3);	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00bb:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x0033;
    L_0x00bf:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00c5:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00cf;
    L_0x00c9:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00cf:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x00d3:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.f884g = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00dd:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED_DUE_TO_TIMEOUT;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0040;
    L_0x00e1:
        r0 = r1;
        goto L_0x001c;
    L_0x00e4:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x00e8:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.f884g = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x00f2:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED_DUE_TO_BUFFERING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0123;
    L_0x00f6:
        r0 = r1;
        goto L_0x001c;
    L_0x00f9:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0104;
    L_0x00fd:
        r0 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        r6.f884g = r0;	 Catch:{ all -> 0x001e }
        r0 = r1;
        goto L_0x001c;
    L_0x0104:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x00e4;
    L_0x0108:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x010e:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x0135;
    L_0x0112:
        r0 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PAUSED;	 Catch:{ all -> 0x001e }
        r6.f884g = r0;	 Catch:{ all -> 0x001e }
        r0 = r1;
        goto L_0x001c;
    L_0x0119:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x014d;
    L_0x011d:
        r0 = r6.m1012i();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0123:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.PLAYING;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x010e;
    L_0x0127:
        r0 = r6.f885h;	 Catch:{ all -> 0x001e }
        r4 = r6.f881d;	 Catch:{ all -> 0x001e }
        r1 = (int) r4;	 Catch:{ all -> 0x001e }
        r0.setRequestBufferPosition(r1);	 Catch:{ all -> 0x001e }
        r0 = r6.m1012i();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x0135:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x013f;
    L_0x0139:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x013f:
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        if (r7 != r1) goto L_0x001c;
    L_0x0143:
        r0 = r6.m1006c();	 Catch:{ all -> 0x001e }
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;	 Catch:{ all -> 0x001e }
        r6.f884g = r1;	 Catch:{ all -> 0x001e }
        goto L_0x001c;
    L_0x014d:
        r3 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED;	 Catch:{ all -> 0x001e }
        if (r7 != r3) goto L_0x001c;
    L_0x0151:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.MediaTaskManager.transitToState(com.immersion.hapticmediasdk.HapticContentSDK$SDKStatus):int");
    }
}
