package com.immersion.hapticmediasdk.controllers;

import android.os.Handler;
import android.os.Looper;
import com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus;
import com.immersion.hapticmediasdk.MediaTaskManager;
import com.immersion.hapticmediasdk.utils.Profiler;
import java.util.concurrent.atomic.AtomicInteger;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import rrrrrr.crrrrr;
import rrrrrr.rcrrrr;

public class MediaController {
    private static final String f955a = "MediaController";
    private static final int f956b = 1000;
    public static int f957b044604460446 = 35;
    public static int f958b044604460446 = 1;
    public static int f959b044604460446 = 2;
    public static int f960b04460446 = 0;
    private static final int f961c = 200;
    private AtomicInteger f962d;
    private AtomicInteger f963e;
    private Handler f964f;
    private HapticPlaybackThread f965g;
    private Profiler f966h;
    private MediaTaskManager f967i;
    private Runnable f968j;

    public MediaController(Looper looper, MediaTaskManager mediaTaskManager) {
        try {
            this.f962d = new AtomicInteger();
            this.f963e = new AtomicInteger();
            if (((f957b044604460446 + f958b044604460446) * f957b044604460446) % f959b044604460446 != f960b04460446) {
                f957b044604460446 = m1077b0446044604460446();
                f960b04460446 = m1077b0446044604460446();
            }
            try {
                this.f966h = new Profiler();
                this.f968j = new rcrrrr(this);
                this.f967i = mediaTaskManager;
                this.f964f = new crrrrr(this, looper);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int m1069a() {
        this.f965g.pauseHapticPlayback();
        return 0;
    }

    private void m1070a(int i) {
        this.f962d.set(i);
        this.f967i.transitToState(SDKStatus.PAUSED_DUE_TO_BUFFERING);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1071a(int r5, long r6) {
        /*
        r4 = this;
    L_0x0000:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = r4.f965g;
        r1 = f957b044604460446;
        r2 = f958b044604460446;
        r1 = r1 + r2;
        r2 = f957b044604460446;
        r1 = r1 * r2;
        r2 = f959b044604460446;
        r1 = r1 % r2;
        r2 = f960b04460446;
        if (r1 == r2) goto L_0x0021;
    L_0x001a:
        r1 = 10;
        f957b044604460446 = r1;
        r1 = 4;
        f960b04460446 = r1;
    L_0x0021:
        r0.playHapticForPlaybackPosition(r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.a(int, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1072a(android.os.Message r6) {
        /*
        r5 = this;
        r4 = 0;
        r0 = r6.getData();
        r1 = "haptic_download_exception";
        r0 = r0.getSerializable(r1);
        r0 = (java.lang.Exception) r0;
        r1 = r0 instanceof com.immersion.hapticmediasdk.models.HttpUnsuccessfulException;
        if (r1 == 0) goto L_0x0037;
    L_0x0011:
        r1 = r0;
        r1 = (com.immersion.hapticmediasdk.models.HttpUnsuccessfulException) r1;
        r2 = "MediaController";
        r3 = new java.lang.StringBuilder;
    L_0x0018:
        switch(r4) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0018;
            default: goto L_0x001b;
        };
    L_0x001b:
        switch(r4) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0018;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x001b;
    L_0x001f:
        r3.<init>();
        r4 = "caught HttpUnsuccessfulExcetion http status code = ";
        r3 = r3.append(r4);
        r1 = r1.getHttpStatusCode();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1114e(r2, r1);
    L_0x0037:
        r1 = "MediaController";
        r2 = f957b044604460446;
        r3 = f958b044604460446;
        r2 = r2 + r3;
        r3 = f957b044604460446;
        r2 = r2 * r3;
        r3 = f959b044604460446;
        r2 = r2 % r3;
        r3 = f960b04460446;
        if (r2 == r3) goto L_0x0052;
    L_0x0048:
        r2 = m1077b0446044604460446();
        f957b044604460446 = r2;
        r2 = 98;
        f960b04460446 = r2;
    L_0x0052:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "HapticDownloadError: ";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.immersion.hapticmediasdk.utils.Log.m1114e(r1, r0);
        r0 = r5.f967i;
        r1 = com.immersion.hapticmediasdk.HapticContentSDK.SDKStatus.STOPPED_DUE_TO_ERROR;
        r0.transitToState(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.a(android.os.Message):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1073a(boolean r7) {
        /*
        r6 = this;
        r2 = 0;
        r0 = r6.f965g;
        r0 = r0.isStarted();
        r1 = r2;
    L_0x0008:
        if (r7 == 0) goto L_0x0030;
    L_0x000a:
        if (r0 != 0) goto L_0x002c;
    L_0x000c:
        r3 = r6.f965g;
        monitor-enter(r3);
        r0 = r6.f965g;	 Catch:{ InterruptedException -> 0x0033 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0.wait(r4);	 Catch:{ InterruptedException -> 0x0033 }
    L_0x0016:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0016;
            case 1: goto L_0x001e;
            default: goto L_0x001a;
        };
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x001e;
            case 1: goto L_0x0016;
            default: goto L_0x001d;
        };
    L_0x001d:
        goto L_0x001a;
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        r0 = r6.f965g;
        r0 = r0.isStarted();
        r1 = r1 + 1;
        if (r7 != 0) goto L_0x0008;
    L_0x0029:
        r3 = 5;
        if (r1 < r3) goto L_0x0008;
    L_0x002c:
        return;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002d }
        throw r0;
    L_0x0030:
        if (r0 == 0) goto L_0x002c;
    L_0x0032:
        goto L_0x000c;
    L_0x0033:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.a(boolean):void");
    }

    public static /* synthetic */ void m1074b043B043B(MediaController mediaController, int i, long j) {
        int i2 = f957b044604460446;
        switch ((i2 * (f958b044604460446 + i2)) % f959b044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f957b044604460446 = 79;
                f960b04460446 = 74;
                break;
        }
        try {
            mediaController.m1071a(i, j);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void m1075b043B043B(com.immersion.hapticmediasdk.controllers.MediaController r2, android.os.Message r3) {
        /*
    L_0x0000:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = f957b044604460446;
        r1 = f958b044604460446;
        r0 = r0 + r1;
        r1 = f957b044604460446;
        r0 = r0 * r1;
        r1 = f959b044604460446;
        r0 = r0 % r1;
        r1 = m1083b044604460446();
        if (r0 == r1) goto L_0x0021;
    L_0x001a:
        r0 = 31;
        f957b044604460446 = r0;
        r0 = 2;
        f960b04460446 = r0;
    L_0x0021:
        r2.m1072a(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.b043Bл043Bллл(com.immersion.hapticmediasdk.controllers.MediaController, android.os.Message):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.util.concurrent.atomic.AtomicInteger m1076b043B(com.immersion.hapticmediasdk.controllers.MediaController r2) {
        /*
    L_0x0000:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = f957b044604460446;
        r1 = f958b044604460446;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f959b044604460446;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001d;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 31;
        f957b044604460446 = r0;
        r0 = 73;
        f960b04460446 = r0;
    L_0x001d:
        r0 = r2.f963e;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.b043Bллллл(com.immersion.hapticmediasdk.controllers.MediaController):java.util.concurrent.atomic.AtomicInteger");
    }

    public static int m1077b0446044604460446() {
        return 5;
    }

    public static int m1078b0446044604460446() {
        return 2;
    }

    public static /* synthetic */ AtomicInteger m1079b04110411041104110411(MediaController mediaController) {
        try {
            AtomicInteger atomicInteger = mediaController.f962d;
            if (((f957b044604460446 + f958b044604460446) * f957b044604460446) % f959b044604460446 != m1083b044604460446()) {
                f957b044604460446 = 20;
                f960b04460446 = 78;
            }
            return atomicInteger;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ HapticPlaybackThread m1080b043B043B(MediaController mediaController) {
        int i = f957b044604460446;
        switch ((i * (f958b044604460446 + i)) % f959b044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f957b044604460446 = 93;
                f960b04460446 = m1077b0446044604460446();
                break;
        }
        try {
            return mediaController.f965g;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ MediaTaskManager m1081b043B(MediaController mediaController) {
        MediaTaskManager mediaTaskManager = mediaController.f967i;
        if (((f957b044604460446 + f958b044604460446) * f957b044604460446) % f959b044604460446 != f960b04460446) {
            f957b044604460446 = 52;
            f960b04460446 = 73;
        }
        return mediaTaskManager;
    }

    public static int m1083b044604460446() {
        return 0;
    }

    public Handler getControlHandler() {
        if (((f957b044604460446 + f958b044604460446) * f957b044604460446) % f959b044604460446 != f960b04460446) {
            f957b044604460446 = 97;
            f960b04460446 = 45;
        }
        return this.f964f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCurrentPosition() {
        /*
        r2 = this;
        r0 = 0;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = f957b044604460446;
        r1 = f958b044604460446;
        r0 = r0 + r1;
        r1 = f957b044604460446;
        r0 = r0 * r1;
        r1 = f959b044604460446;
        r0 = r0 % r1;
        r1 = f960b04460446;
        if (r0 == r1) goto L_0x001f;
    L_0x0017:
        r0 = 51;
        f957b044604460446 = r0;
        r0 = 63;
        f960b04460446 = r0;
    L_0x001f:
        r0 = r2.f967i;
        r0 = r0.getMediaTimestamp();
        r0 = (int) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.getCurrentPosition():int");
    }

    public long getReferenceTimeForCurrentPosition() {
        int i = f957b044604460446;
        switch ((i * (f958b044604460446 + i)) % f959b044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f957b044604460446 = m1077b0446044604460446();
                f960b04460446 = 38;
                break;
        }
        try {
            return this.f967i.getMediaReferenceTime();
        } catch (Exception e) {
            throw e;
        }
    }

    public void initHapticPlayback(HapticPlaybackThread hapticPlaybackThread) {
        try {
            this.f965g = hapticPlaybackThread;
            this.f965g.start();
            if (((f957b044604460446 + f958b044604460446) * f957b044604460446) % f959b044604460446 != f960b04460446) {
                f957b044604460446 = m1077b0446044604460446();
                f960b04460446 = 24;
            }
            try {
                m1073a(true);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public boolean isPlaying() {
        try {
            return this.f967i.getSDKStatus() == SDKStatus.PLAYING;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy(android.os.Handler r4) {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.f965g;
        if (r0 == 0) goto L_0x0017;
    L_0x0005:
        r0 = r3.f965g;
        r0.quitHapticPlayback();
        r3.m1073a(r1);
    L_0x000d:
        switch(r1) {
            case 0: goto L_0x0014;
            case 1: goto L_0x000d;
            default: goto L_0x0010;
        };
    L_0x0010:
        switch(r1) {
            case 0: goto L_0x0014;
            case 1: goto L_0x000d;
            default: goto L_0x0013;
        };
    L_0x0013:
        goto L_0x0010;
    L_0x0014:
        r0 = 0;
        r3.f965g = r0;
    L_0x0017:
        r0 = r3.f967i;
        r1 = f957b044604460446;
        r2 = f958b044604460446;
        r1 = r1 + r2;
        r2 = f957b044604460446;
        r1 = r1 * r2;
        r2 = f959b044604460446;
        r1 = r1 % r2;
        r2 = f960b04460446;
        if (r1 == r2) goto L_0x0032;
    L_0x0028:
        r1 = m1077b0446044604460446();
        f957b044604460446 = r1;
        r1 = 29;
        f960b04460446 = r1;
    L_0x0032:
        r4.removeCallbacks(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.onDestroy(android.os.Handler):void");
    }

    public int onPause() {
        int a = m1069a();
        int i = f957b044604460446;
        switch ((i * (f958b044604460446 + i)) % f959b044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f957b044604460446 = 39;
                f960b04460446 = 73;
                break;
        }
        return a;
    }

    public int onPrepared() {
        return prepareHapticPlayback();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void playbackStarted() {
        /*
        r4 = this;
        r1 = 1;
        r0 = r4.f965g;
        if (r0 == 0) goto L_0x0032;
    L_0x0005:
        switch(r1) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000c;
            default: goto L_0x0008;
        };
    L_0x0008:
        switch(r1) {
            case 0: goto L_0x0005;
            case 1: goto L_0x000c;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0008;
    L_0x000c:
        r0 = r4.f965g;
        r1 = f957b044604460446;
        r2 = f958b044604460446;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f959b044604460446;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0026;
            default: goto L_0x001a;
        };
    L_0x001a:
        r1 = m1077b0446044604460446();
        f957b044604460446 = r1;
        r1 = m1077b0446044604460446();
        f960b04460446 = r1;
    L_0x0026:
        r0 = r0.getHandler();
        r1 = r4.f968j;
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0.postDelayed(r1, r2);
    L_0x0031:
        return;
    L_0x0032:
        r0 = "MediaController";
        r1 = "Can't start periodic sync since haptic playback thread stopped.";
        com.immersion.hapticmediasdk.utils.Log.m1114e(r0, r1);
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.playbackStarted():void");
    }

    public int prepareHapticPlayback() {
        try {
            this.f966h.startTiming();
            try {
                HapticPlaybackThread hapticPlaybackThread = this.f965g;
                int i = this.f962d.get();
                int i2 = f957b044604460446;
                switch ((i2 * (f958b044604460446 + i2)) % f959b044604460446) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    default:
                        f957b044604460446 = m1077b0446044604460446();
                        f960b04460446 = 98;
                        break;
                }
                hapticPlaybackThread.prepareHapticPlayback(i, this.f963e.incrementAndGet());
                return 0;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void seekTo(int i) {
        AtomicInteger atomicInteger = this.f962d;
        if (i <= 0) {
            i = 0;
        }
        atomicInteger.set(i);
        if (this.f965g != null) {
            Handler handler = this.f965g.getHandler();
            int i2 = f957b044604460446;
            switch ((i2 * (f958b044604460446 + i2)) % f959b044604460446) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f957b044604460446 = 66;
                    f960b04460446 = 5;
                    break;
            }
            handler.removeCallbacks(this.f968j);
            this.f965g.removePlaybackCallbacks();
        }
    }

    public void setRequestBufferPosition(int i) {
        try {
            AtomicInteger atomicInteger = this.f962d;
            int b044604460446цц0446 = m1077b0446044604460446();
            switch ((b044604460446цц0446 * (f958b044604460446 + b044604460446цц0446)) % f959b044604460446) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f957b044604460446 = 8;
                    f960b04460446 = m1077b0446044604460446();
                    break;
            }
            atomicInteger.set(i);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int stopHapticPlayback() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.f962d;
        r0.set(r3);
        r0 = r4.f965g;
    L_0x0008:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0010;
            default: goto L_0x000c;
        };
    L_0x000c:
        switch(r3) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0008;
            default: goto L_0x000f;
        };
    L_0x000f:
        goto L_0x000c;
    L_0x0010:
        r0.stopHapticPlayback();
        r0 = r4.f965g;
        r1 = f957b044604460446;
        r2 = f958b044604460446;
        r1 = r1 + r2;
        r2 = f957b044604460446;
        r1 = r1 * r2;
        r2 = f959b044604460446;
        r1 = r1 % r2;
        r2 = f960b04460446;
        if (r1 == r2) goto L_0x002c;
    L_0x0024:
        r1 = 48;
        f957b044604460446 = r1;
        r1 = 63;
        f960b04460446 = r1;
    L_0x002c:
        r0 = r0.getHandler();
        r1 = r4.f968j;
        r0.removeCallbacks(r1);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.stopHapticPlayback():int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void waitHapticStopped() {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6.f965g;
        r0 = r0.isStopped();
        r2 = r0;
        r0 = r1;
    L_0x0009:
        if (r2 != 0) goto L_0x002d;
    L_0x000b:
        r2 = 5;
        if (r0 >= r2) goto L_0x002d;
    L_0x000e:
        r2 = r6.f965g;
        monitor-enter(r2);
        r3 = r6.f965g;	 Catch:{ InterruptedException -> 0x002e }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r3.wait(r4);	 Catch:{ InterruptedException -> 0x002e }
    L_0x0018:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        r2 = r6.f965g;
        r2 = r2.isStopped();
        r0 = r0 + 1;
        goto L_0x0009;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
    L_0x0024:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0024;
            case 1: goto L_0x002c;
            default: goto L_0x0028;
        };
    L_0x0028:
        switch(r1) {
            case 0: goto L_0x002c;
            case 1: goto L_0x0024;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0028;
    L_0x002c:
        throw r0;
    L_0x002d:
        return;
    L_0x002e:
        r3 = move-exception;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MediaController.waitHapticStopped():void");
    }
}
