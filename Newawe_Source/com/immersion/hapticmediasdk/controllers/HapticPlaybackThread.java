package com.immersion.hapticmediasdk.controllers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.immersion.content.EndpointWarp;
import com.immersion.hapticmediasdk.utils.FileManager;
import com.immersion.hapticmediasdk.utils.Profiler;
import com.immersion.hapticmediasdk.utils.RuntimeInfo;
import java.util.ArrayList;
import java.util.Iterator;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import rrrrrr.ccrcrr;
import rrrrrr.crcrrr;
import rrrrrr.rccrrr;
import rrrrrr.rrcrrr;

public class HapticPlaybackThread extends Thread {
    private static final long f909D = 100;
    private static final int f910E = 5;
    public static final int HAPTIC_BYTES_AVAILABLE_TO_DOWNLOAD = 3;
    public static final int HAPTIC_DOWNLOAD_ERROR = 8;
    public static final String HAPTIC_DOWNLOAD_EXCEPTION_KEY = "haptic_download_exception";
    public static final int HAPTIC_PAUSE_PLAYBACK = 5;
    public static final int HAPTIC_PLAYBACK_FOR_TIME_CODE = 2;
    public static final int HAPTIC_PLAYBACK_IS_READY = 6;
    public static final int HAPTIC_QUIT_PLAYBACK = 9;
    public static final int HAPTIC_SET_BUFFERING_POSITION = 1;
    public static final int HAPTIC_STOP_PLAYBACK = 4;
    public static final int PAUSE_AV_FOR_HAPTIC_BUFFERING = 7;
    private static final String f911a = "HapticPlaybackThread";
    private static final int f912b = Integer.MIN_VALUE;
    public static int f913b0427042704270427 = 1;
    public static int f914b042704270427 = 0;
    public static int f915b042704270427 = 86;
    public static int f916b0427 = 2;
    private static final String f917c = "playback_timecode";
    private static final String f918d = "playback_uptime";
    private RuntimeInfo f919A;
    private boolean f920B;
    private FileManager f921C;
    private final Runnable f922F;
    private final Runnable f923G;
    public volatile boolean f924b0444044404440444;
    public Context f925b044404440444;
    public volatile boolean f926b04440444;
    private int f927e;
    private final String f928f;
    private Handler f929g;
    private final Handler f930h;
    private HapticDownloadThread f931i;
    private Looper f932j;
    private IHapticFileReader f933k;
    private EndpointWarp f934l;
    private final Profiler f935m;
    private Object f936n;
    private Object f937o;
    private int f938p;
    private int f939q;
    private int f940r;
    private long f941s;
    private int f942t;
    private int f943u;
    private int f944v;
    private long f945w;
    private boolean f946x;
    private boolean f947y;
    private ArrayList f948z;

    public HapticPlaybackThread(Context context, String str, Handler handler, boolean z, RuntimeInfo runtimeInfo) {
        super(f911a);
        this.f927e = 0;
        this.f935m = new Profiler();
        this.f936n = new Object();
        this.f937o = new Object();
        int i = f915b042704270427;
        switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f915b042704270427 = m1042b04270427();
                f913b0427042704270427 = m1042b04270427();
                break;
        }
        this.f946x = false;
        this.f947y = false;
        this.f924b0444044404440444 = false;
        this.f926b04440444 = false;
        this.f920B = false;
        this.f922F = new rrcrrr(this);
        this.f923G = new crcrrr(this);
        this.f928f = str;
        this.f930h = handler;
        this.f925b044404440444 = context;
        this.f920B = z;
        this.f921C = new FileManager(context);
        this.f919A = runtimeInfo;
        this.f948z = new ArrayList();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1022a() {
        /*
        r2 = this;
    L_0x0000:
        r0 = r2.f931i;
        r0 = r0.isAlive();
        if (r0 == 0) goto L_0x003d;
    L_0x0008:
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = f915b042704270427;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = m1058b04270427();
        if (r0 == r1) goto L_0x0023;
    L_0x0019:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 65;
        f913b0427042704270427 = r0;
    L_0x0023:
        r0 = r2.f931i;
        r0.terminate();
        r0 = r2.f931i;
        r0.interrupt();
        java.lang.Thread.currentThread();
    L_0x0030:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0039;
            case 1: goto L_0x0030;
            default: goto L_0x0034;
        };
    L_0x0034:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0030;
            case 1: goto L_0x0039;
            default: goto L_0x0038;
        };
    L_0x0038:
        goto L_0x0034;
    L_0x0039:
        java.lang.Thread.yield();
        goto L_0x0000;
    L_0x003d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.a():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1023a(int r9, long r10) {
        /*
        r8 = this;
        r4 = 0;
        r6 = 1;
        r0 = r8.f947y;
        if (r0 != 0) goto L_0x0045;
    L_0x0006:
        r0 = r8.f933k;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r8.f934l;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x0040;
    L_0x000f:
        r0 = r8.f933k;	 Catch:{ Error -> 0x001f }
        r0 = r0.getEncryptedHapticHeader();	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x002a;
    L_0x0017:
        r0 = "HapticPlaybackThread";
        r1 = "corrupted hapt file or unsupported format";
        com.immersion.hapticmediasdk.utils.Log.m1114e(r0, r1);	 Catch:{ Error -> 0x001f }
        goto L_0x000a;
    L_0x001f:
        r0 = move-exception;
        r1 = "HapticPlaybackThread";
        r0 = r0.getMessage();
        com.immersion.hapticmediasdk.utils.Log.m1114e(r1, r0);
        goto L_0x000a;
    L_0x002a:
        r1 = new com.immersion.content.EndpointWarp;	 Catch:{ Error -> 0x001f }
        r2 = r8.f925b044404440444;	 Catch:{ Error -> 0x001f }
        r3 = r0.length;	 Catch:{ Error -> 0x001f }
        r1.<init>(r2, r0, r3);	 Catch:{ Error -> 0x001f }
        r8.f934l = r1;	 Catch:{ Error -> 0x001f }
        r0 = r8.f934l;	 Catch:{ Error -> 0x001f }
        if (r0 != 0) goto L_0x0040;
    L_0x0038:
        r0 = "HapticPlaybackThread";
        r1 = "Error creating endpointwarp";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);	 Catch:{ Error -> 0x001f }
        goto L_0x000a;
    L_0x0040:
        r0 = r8.f934l;	 Catch:{ Error -> 0x001f }
        r0.start();	 Catch:{ Error -> 0x001f }
    L_0x0045:
        r8.f926b04440444 = r4;
        r8.f947y = r6;
        r8.f944v = r4;
        r1 = r8.f936n;
        monitor-enter(r1);
        r8.f943u = r9;	 Catch:{ all -> 0x0069 }
        r0 = r8.f943u;	 Catch:{ all -> 0x0069 }
        r8.f942t = r0;	 Catch:{ all -> 0x0069 }
        r2 = r8.f945w;	 Catch:{ all -> 0x0069 }
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0062;
    L_0x005c:
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0069 }
        r8.f945w = r2;	 Catch:{ all -> 0x0069 }
    L_0x0062:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        r8.f941s = r10;
        r8.m1064h();
        goto L_0x000a;
    L_0x0069:
        r0 = move-exception;
    L_0x006a:
        switch(r6) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0071;
            default: goto L_0x006d;
        };
    L_0x006d:
        switch(r6) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0071;
            default: goto L_0x0070;
        };
    L_0x0070:
        goto L_0x006d;
    L_0x0071:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.a(int, long):void");
    }

    private void m1024a(Message message) {
        this.f946x = true;
        Message obtainMessage = this.f930h.obtainMessage(HAPTIC_DOWNLOAD_ERROR);
        obtainMessage.setData(message.getData());
        this.f930h.sendMessage(obtainMessage);
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = 41;
            f914b042704270427 = m1042b04270427();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1025b() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.f931i;
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r4.m1022a();
        r4.f931i = r3;
    L_0x000a:
        r1 = r4.f937o;
        monitor-enter(r1);
        r0 = r4.f929g;	 Catch:{ all -> 0x0049 }
        r2 = 0;
        r0.removeCallbacksAndMessages(r2);	 Catch:{ all -> 0x0049 }
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        r0 = r4.f932j;
        if (r0 == 0) goto L_0x001f;
    L_0x0018:
        r0 = r4.f932j;
        r0.quit();
        r4.f932j = r3;
    L_0x001f:
        r0 = r4.f933k;
        if (r0 == 0) goto L_0x002a;
    L_0x0023:
        r0 = r4.f933k;
        r0.close();
        r4.f933k = r3;
    L_0x002a:
        r0 = r4.f934l;
        if (r0 == 0) goto L_0x0043;
    L_0x002e:
        r0 = r4.f934l;
        r0.stop();
        r0 = r4.f934l;
        r0.dispose();
    L_0x0038:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0038;
            default: goto L_0x003c;
        };
    L_0x003c:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0038;
            case 1: goto L_0x0041;
            default: goto L_0x0040;
        };
    L_0x0040:
        goto L_0x003c;
    L_0x0041:
        r4.f934l = r3;
    L_0x0043:
        r0 = r4.f921C;
        r0.deleteHapticStorage();
        return;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void m1026b04110411041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2, int r3, long r4) {
        /*
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = f915b042704270427;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = f914b042704270427;
        if (r0 == r1) goto L_0x0019;
    L_0x000f:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 45;
        f914b042704270427 = r0;
    L_0x0019:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0019;
            case 1: goto L_0x0022;
            default: goto L_0x001d;
        };
    L_0x001d:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0022;
            case 1: goto L_0x0019;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x001d;
    L_0x0022:
        r2.m1023a(r3, r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b04110411041104110411Б(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, int, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.content.EndpointWarp m1027b04110411041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r4) {
        /*
        r3 = 0;
        r1 = -1;
        r0 = 4;
        r2 = 0;
    L_0x0004:
        r0 = r0 / r2;
        goto L_0x0004;
    L_0x0006:
        r0 = move-exception;
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = r4.f934l;
    L_0x000f:
        switch(r3) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r3) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = move-exception;
        r0 = m1042b04270427();
        f915b042704270427 = r0;
    L_0x001e:
        r0 = new int[r1];	 Catch:{ Exception -> 0x0006 }
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b0411041104110411Б0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread):com.immersion.content.EndpointWarp");
    }

    public static /* synthetic */ void m1028b0411041104110411(HapticPlaybackThread hapticPlaybackThread) {
        int i = f915b042704270427;
        switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f915b042704270427 = 25;
                f914b042704270427 = 36;
                break;
        }
        try {
            hapticPlaybackThread.m1063g();
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int m1029b0411041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2, int r3) {
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
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001f;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 74;
        f915b042704270427 = r0;
        r0 = m1042b04270427();
        f914b042704270427 = r0;
    L_0x001f:
        r2.f938p = r3;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b04110411Б04110411Б(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, int):int");
    }

    public static /* synthetic */ boolean m1030b0411041104110411(HapticPlaybackThread hapticPlaybackThread) {
        try {
            boolean z = hapticPlaybackThread.f947y;
            if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
                f915b042704270427 = m1042b04270427();
                f914b042704270427 = m1042b04270427();
            }
            return z;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int m1031b0411041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2) {
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
        r0 = m1042b04270427();
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = m1042b04270427();
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = f914b042704270427;
        if (r0 == r1) goto L_0x0028;
    L_0x001c:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = m1042b04270427();
        f914b042704270427 = r0;
    L_0x0028:
        r0 = r2.f943u;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b04110411ББ04110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread):int");
    }

    public static /* synthetic */ FileManager m1032b041104110411(HapticPlaybackThread hapticPlaybackThread) {
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                f915b042704270427 = 90;
                return hapticPlaybackThread.f921C;
            }
        }
    }

    public static /* synthetic */ int m1033b0411041104110411(HapticPlaybackThread hapticPlaybackThread, int i) {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = m1042b04270427();
            f914b042704270427 = 87;
        }
        hapticPlaybackThread.f940r = i;
        return i;
    }

    public static /* synthetic */ ArrayList m1034b0411041104110411(HapticPlaybackThread hapticPlaybackThread) {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = m1042b04270427();
            f914b042704270427 = 92;
        }
        try {
            return hapticPlaybackThread.f948z;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ Runnable m1035b0411041104110411(HapticPlaybackThread hapticPlaybackThread) {
        if (((m1042b04270427() + f913b0427042704270427) * m1042b04270427()) % f916b0427 != f914b042704270427) {
            f915b042704270427 = m1042b04270427();
            f914b042704270427 = m1042b04270427();
        }
        try {
            return hapticPlaybackThread.f923G;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ int m1036b041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r3, int r4) {
        /*
        r2 = 0;
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = f915b042704270427;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = f914b042704270427;
        if (r0 == r1) goto L_0x001a;
    L_0x0010:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 23;
        f914b042704270427 = r0;
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x0021;
            case 1: goto L_0x001a;
            default: goto L_0x001d;
        };
    L_0x001d:
        switch(r2) {
            case 0: goto L_0x0021;
            case 1: goto L_0x001a;
            default: goto L_0x0020;
        };
    L_0x0020:
        goto L_0x001d;
    L_0x0021:
        r3.f927e = r4;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b0411Б0411ББ0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Runnable m1037b041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r3) {
        /*
        r0 = 1;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = r3.f922F;
        r1 = f915b042704270427;
        r2 = f913b0427042704270427;
        r1 = r1 + r2;
        r2 = f915b042704270427;
        r1 = r1 * r2;
        r2 = f916b0427;
        r1 = r1 % r2;
        r2 = m1058b04270427();
        if (r1 == r2) goto L_0x0025;
    L_0x001b:
        r1 = m1042b04270427();
        f915b042704270427 = r1;
        r1 = 18;
        f914b042704270427 = r1;
    L_0x0025:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b0411ББ04110411Б(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread):java.lang.Runnable");
    }

    public static /* synthetic */ void m1038b041104110411(HapticPlaybackThread hapticPlaybackThread) {
        String str = null;
        hapticPlaybackThread.m1061e();
        while (true) {
            try {
                str.length();
            } catch (Exception e) {
                f915b042704270427 = m1042b04270427();
                return;
            }
        }
    }

    public static /* synthetic */ int m1039b041104110411(HapticPlaybackThread hapticPlaybackThread, int i) {
        int i2 = hapticPlaybackThread.f943u + i;
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = 55;
            f914b042704270427 = HAPTIC_SET_BUFFERING_POSITION;
        }
        hapticPlaybackThread.f943u = i2;
        return i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.controllers.IHapticFileReader m1040b04110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2, com.immersion.hapticmediasdk.controllers.IHapticFileReader r3) {
        /*
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
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = f915b042704270427;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = f914b042704270427;
        if (r0 == r1) goto L_0x0023;
    L_0x0017:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = m1042b04270427();
        f914b042704270427 = r0;
    L_0x0023:
        r2.f933k = r3;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.b0411ББББ0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, com.immersion.hapticmediasdk.controllers.IHapticFileReader):com.immersion.hapticmediasdk.controllers.IHapticFileReader");
    }

    public static int m1041b0427042704270427() {
        return HAPTIC_PLAYBACK_FOR_TIME_CODE;
    }

    public static int m1042b04270427() {
        return 41;
    }

    public static /* synthetic */ void m1043b0411041104110411(HapticPlaybackThread hapticPlaybackThread) {
        hapticPlaybackThread.m1060d();
        int i = f915b042704270427;
        switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
            default:
                f915b042704270427 = m1042b04270427();
                f914b042704270427 = m1042b04270427();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.utils.RuntimeInfo m1044b0411041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2) {
        /*
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0016;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 64;
        f914b042704270427 = r0;
    L_0x0016:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0016;
            case 1: goto L_0x001f;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0016;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x001a;
    L_0x001f:
        r0 = r2.f919A;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.bБ041104110411Б0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread):com.immersion.hapticmediasdk.utils.RuntimeInfo");
    }

    public static /* synthetic */ void m1045b041104110411(HapticPlaybackThread hapticPlaybackThread) {
        int b0427ЧЧЧ0427Ч = m1042b04270427();
        switch ((b0427ЧЧЧ0427Ч * (f913b0427042704270427 + b0427ЧЧЧ0427Ч)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f915b042704270427 = m1042b04270427();
                f914b042704270427 = m1042b04270427();
                break;
        }
        hapticPlaybackThread.m1062f();
    }

    public static /* synthetic */ Handler m1046b041104110411(HapticPlaybackThread hapticPlaybackThread) {
        try {
            Handler handler = hapticPlaybackThread.f929g;
            int i = f915b042704270427;
            switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f915b042704270427 = HAPTIC_QUIT_PLAYBACK;
                    f914b042704270427 = 62;
                    break;
            }
            return handler;
        } catch (Exception e) {
            throw e;
        }
    }

    public static /* synthetic */ void m1047b041104110411(HapticPlaybackThread hapticPlaybackThread) {
        hapticPlaybackThread.m1064h();
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                f915b042704270427 = m1042b04270427();
                return;
            }
        }
    }

    public static /* synthetic */ int m1048b041104110411(HapticPlaybackThread hapticPlaybackThread, int i) {
        int[] iArr;
        while (true) {
            try {
                iArr = new int[-1];
            } catch (Exception e) {
                f915b042704270427 = m1042b04270427();
                while (true) {
                    try {
                        iArr = new int[-1];
                    } catch (Exception e2) {
                        f915b042704270427 = m1042b04270427();
                        try {
                            hapticPlaybackThread.f942t = i;
                            return i;
                        } catch (Exception e3) {
                            throw e3;
                        }
                    }
                }
            }
        }
    }

    public static /* synthetic */ int m1050b041104110411(HapticPlaybackThread hapticPlaybackThread, int i) {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % m1041b0427042704270427() != f914b042704270427) {
            f915b042704270427 = HAPTIC_DOWNLOAD_ERROR;
            f914b042704270427 = m1042b04270427();
        }
        try {
            hapticPlaybackThread.f939q = i;
            return i;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ long m1052b041104110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r3, long r4) {
        /*
        r0 = 1;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r0 = r0 + r1;
        r1 = f915b042704270427;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        r1 = f914b042704270427;
        if (r0 == r1) goto L_0x0021;
    L_0x0017:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 90;
        f914b042704270427 = r0;
    L_0x0021:
        r3.f945w = r4;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.bББ0411Б04110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, long):long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void m1054b04110411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2, android.os.Message r3) {
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
        r0 = f915b042704270427;
        r1 = m1057b042704270427();
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001f;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = 56;
        f915b042704270427 = r0;
        r0 = 92;
        f914b042704270427 = r0;
    L_0x001f:
        r2.m1024a(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.bБББ0411Б0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread, android.os.Message):void");
    }

    public static /* synthetic */ Object m1055b04110411(HapticPlaybackThread hapticPlaybackThread) {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = 18;
            f914b042704270427 = m1042b04270427();
        }
        try {
            return hapticPlaybackThread.f936n;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.immersion.hapticmediasdk.controllers.IHapticFileReader m1056b0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread r2) {
        /*
        r0 = f915b042704270427;
        r1 = f913b0427042704270427;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f916b0427;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0016;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = m1042b04270427();
        f915b042704270427 = r0;
        r0 = 19;
        f914b042704270427 = r0;
    L_0x0016:
        r0 = r2.f933k;
    L_0x0018:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0021;
            case 1: goto L_0x0018;
            default: goto L_0x001c;
        };
    L_0x001c:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0018;
            case 1: goto L_0x0021;
            default: goto L_0x0020;
        };
    L_0x0020:
        goto L_0x001c;
    L_0x0021:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.bБББББ0411(com.immersion.hapticmediasdk.controllers.HapticPlaybackThread):com.immersion.hapticmediasdk.controllers.IHapticFileReader");
    }

    public static int m1057b042704270427() {
        return HAPTIC_SET_BUFFERING_POSITION;
    }

    public static int m1058b04270427() {
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1059c() {
        /*
        r1 = this;
        r0 = 0;
        monitor-enter(r1);
    L_0x0002:
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0002;
            default: goto L_0x0005;
        };
    L_0x0005:
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0002;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0005;
    L_0x0009:
        r1.notifyAll();	 Catch:{ all -> 0x000e }
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        return;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.c():void");
    }

    private void m1060d() {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
            f915b042704270427 = 74;
            f914b042704270427 = 21;
        }
        if (!this.f946x) {
            int i = this.f940r;
            this.f940r = i + HAPTIC_SET_BUFFERING_POSITION;
            if (i == HAPTIC_PAUSE_PLAYBACK) {
                this.f930h.sendMessage(this.f930h.obtainMessage(PAUSE_AV_FOR_HAPTIC_BUFFERING, this.f938p, 0));
                this.f929g.postDelayed(this.f922F, f909D);
            } else if (this.f933k == null || !this.f933k.bufferAtPlaybackPosition(this.f938p)) {
                this.f929g.postDelayed(this.f922F, f909D);
            } else if (this.f939q != f912b) {
                this.f930h.sendMessage(this.f930h.obtainMessage(HAPTIC_PLAYBACK_IS_READY, this.f938p, this.f939q));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1061e() {
        /*
        r5 = this;
        r4 = 0;
        r0 = 0;
        r5.m1025b();	 Catch:{ Exception -> 0x0030 }
    L_0x0005:
        r0.length();	 Catch:{ Exception -> 0x0009 }
        goto L_0x0005;
    L_0x0009:
        r1 = move-exception;
        r1 = m1042b04270427();
        f915b042704270427 = r1;
    L_0x0010:
        r0.length();	 Catch:{ Exception -> 0x0014 }
        goto L_0x0010;
    L_0x0014:
        r1 = move-exception;
        r1 = m1042b04270427();
        f915b042704270427 = r1;
    L_0x001b:
        r0.length();	 Catch:{ Exception -> 0x001f }
        goto L_0x001b;
    L_0x001f:
        r0 = move-exception;
        r0 = 38;
        f915b042704270427 = r0;
        r5.f924b0444044404440444 = r4;
        r5.m1059c();
    L_0x0029:
        return;
    L_0x002a:
        r5.f924b0444044404440444 = r4;
        r5.m1059c();
        goto L_0x0029;
    L_0x0030:
        r0 = move-exception;
        r1 = "HapticPlaybackThread";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r2.<init>();	 Catch:{ all -> 0x0055 }
        r3 = "quit() : ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0055 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0055 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x0055 }
        r0 = r0.toString();	 Catch:{ all -> 0x0055 }
        com.immersion.hapticmediasdk.utils.Log.m1114e(r1, r0);	 Catch:{ all -> 0x0055 }
    L_0x004d:
        switch(r4) {
            case 0: goto L_0x002a;
            case 1: goto L_0x004d;
            default: goto L_0x0050;
        };
    L_0x0050:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x004d;
            case 1: goto L_0x002a;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x0050;
    L_0x0055:
        r0 = move-exception;
        r5.f924b0444044404440444 = r4;
        r5.m1059c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.e():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1062f() {
        /*
        r8 = this;
        r6 = 0;
        r5 = 1;
        r4 = 0;
        r8.f947y = r4;
        r0 = r8.f934l;
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        r0 = r8.f934l;
        r0.stop();
    L_0x000f:
        switch(r4) {
            case 0: goto L_0x0016;
            case 1: goto L_0x000f;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r5) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        r0 = r8.f929g;
        r1 = r8.f922F;
        r0.removeCallbacks(r1);
        r8.removePlaybackCallbacks();
        r1 = r8.f936n;
        monitor-enter(r1);
        r0 = 0;
        r8.f943u = r0;	 Catch:{ all -> 0x0035 }
        r0 = 0;
        r8.f942t = r0;	 Catch:{ all -> 0x0035 }
        r2 = 0;
        r8.f945w = r2;	 Catch:{ all -> 0x0035 }
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        r8.f944v = r4;
        r8.f941s = r6;
        r8.f926b04440444 = r5;
        return;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0035 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.f():void");
    }

    private void m1063g() {
        this.f947y = false;
        removePlaybackCallbacks();
        int i = f915b042704270427;
        switch ((i * (m1057b042704270427() + i)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
            default:
                f915b042704270427 = m1042b04270427();
                f914b042704270427 = m1042b04270427();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1064h() {
        /*
        r13 = this;
        r12 = 0;
        r0 = r13.f947y;
        if (r0 == 0) goto L_0x0057;
    L_0x0005:
        r1 = r13.f936n;
        monitor-enter(r1);
        r2 = r13.f943u;	 Catch:{ all -> 0x0058 }
        r4 = r13.f942t;	 Catch:{ all -> 0x0058 }
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        r0 = r13.f933k;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r6 = r0.getBufferForPlaybackPosition(r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r0 = r13.f933k;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r8 = (long) r2;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r7 = r0.getHapticBlockIndex(r8);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r0 = r13.f933k;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r8 = (long) r2;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        r8 = r0.getBlockOffset(r8);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005b }
        if (r6 == 0) goto L_0x006e;
    L_0x0023:
        r0 = r13.f941s;
        r3 = r13.f944v;
        r10 = (long) r3;
        r10 = r10 + r0;
        r0 = new rrrrrr.ccrcrr;
        r2 = (long) r2;
        r4 = (long) r4;
        r1 = r13;
        r0.<init>(r1, r2, r4, r6, r7, r8);
        r1 = r13.f937o;
    L_0x0033:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0033;
            case 1: goto L_0x003b;
            default: goto L_0x0037;
        };
    L_0x0037:
        switch(r12) {
            case 0: goto L_0x003b;
            case 1: goto L_0x0033;
            default: goto L_0x003a;
        };
    L_0x003a:
        goto L_0x0037;
    L_0x003b:
        monitor-enter(r1);
        r2 = r13.f948z;	 Catch:{ all -> 0x006b }
        r2.add(r0);	 Catch:{ all -> 0x006b }
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        r1 = r13.f929g;
        r2 = r13.f927e;
        r2 = (long) r2;
        r2 = r2 + r10;
        r1.postAtTime(r0, r2);
        r0 = r13.f944v;
        r1 = r13.f927e;
        r0 = r0 + r1;
        r13.f944v = r0;
        r0 = r13.f935m;
        r0.startTimingII();
    L_0x0057:
        return;
    L_0x0058:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0058 }
        throw r0;
    L_0x005b:
        r0 = move-exception;
        r13.f947y = r12;
        r0 = r13.f930h;
        r1 = r13.f930h;
        r3 = 7;
        r1 = r1.obtainMessage(r3, r2, r12);
        r0.sendMessage(r1);
        goto L_0x0057;
    L_0x006b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        throw r0;
    L_0x006e:
        r1 = r13.f936n;
        monitor-enter(r1);
        r0 = 0;
        r13.f943u = r0;	 Catch:{ all -> 0x0081 }
        r0 = 0;
        r13.f942t = r0;	 Catch:{ all -> 0x0081 }
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        r13.f944v = r12;
        r0 = 0;
        r13.f941s = r0;
        r13.f947y = r12;
        goto L_0x0057;
    L_0x0081:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.h():void");
    }

    public Handler getHandler() {
        try {
            Handler handler = this.f929g;
            if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % f916b0427 != f914b042704270427) {
                f915b042704270427 = m1042b04270427();
                f914b042704270427 = 58;
            }
            return handler;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isStarted() {
        boolean z = this.f924b0444044404440444;
        int i = f915b042704270427;
        switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f915b042704270427 = 69;
                f914b042704270427 = m1042b04270427();
                break;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStopped() {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.f926b04440444;
    L_0x0003:
        switch(r1) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r1) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r1 = f915b042704270427;
        r2 = m1057b042704270427();
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f916b0427;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0022;
            default: goto L_0x0018;
        };
    L_0x0018:
        r1 = 11;
        f915b042704270427 = r1;
        r1 = m1042b04270427();
        f914b042704270427 = r1;
    L_0x0022:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.isStopped():boolean");
    }

    public void pauseHapticPlayback() {
        if (((f915b042704270427 + f913b0427042704270427) * f915b042704270427) % m1041b0427042704270427() != m1058b04270427()) {
            f915b042704270427 = 98;
            f914b042704270427 = 68;
        }
        this.f929g.sendEmptyMessage(HAPTIC_PAUSE_PLAYBACK);
        while (true) {
            switch (HAPTIC_SET_BUFFERING_POSITION) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                case HAPTIC_SET_BUFFERING_POSITION /*1*/:
                    return;
                default:
                    while (true) {
                        switch (HAPTIC_SET_BUFFERING_POSITION) {
                            case DurationDV.DURATION_TYPE /*0*/:
                                break;
                            case HAPTIC_SET_BUFFERING_POSITION /*1*/:
                                return;
                            default:
                        }
                    }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void playHapticForPlaybackPosition(int r7, long r8) {
        /*
        r6 = this;
        r5 = 2;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r6.removePlaybackCallbacks();
        r3 = r6.f929g;
        r3.removeMessages(r5);
        r3 = new android.os.Bundle;
    L_0x000e:
        switch(r0) {
            case 0: goto L_0x0015;
            case 1: goto L_0x000e;
            default: goto L_0x0011;
        };
    L_0x0011:
        switch(r0) {
            case 0: goto L_0x0015;
            case 1: goto L_0x000e;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0011;
    L_0x0015:
        r3.<init>();
        r4 = "playback_timecode";
        r3.putInt(r4, r7);
        r4 = "playback_uptime";
        r3.putLong(r4, r8);
        r4 = r6.f929g;
        r4 = r4.obtainMessage(r5);
    L_0x0028:
        r2.length();	 Catch:{ Exception -> 0x002c }
        goto L_0x0028;
    L_0x002c:
        r5 = move-exception;
        r5 = 75;
        f915b042704270427 = r5;
    L_0x0031:
        r0 = r0 / r1;
        goto L_0x0031;
    L_0x0033:
        r0 = move-exception;
        r0 = 38;
        f915b042704270427 = r0;
        r4.setData(r3);
        r0 = r6.f929g;
        r0.sendMessage(r4);
        return;
    L_0x0041:
        r0 = move-exception;
        r0 = m1042b04270427();
        f915b042704270427 = r0;
    L_0x0048:
        r2.length();	 Catch:{ Exception -> 0x0033 }
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.playHapticForPlaybackPosition(int, long):void");
    }

    public void prepareHapticPlayback(int i, int i2) {
        this.f929g.removeMessages(HAPTIC_SET_BUFFERING_POSITION);
        this.f929g.sendMessage(this.f929g.obtainMessage(HAPTIC_SET_BUFFERING_POSITION, i, i2));
    }

    public void quitHapticPlayback() {
        try {
            if (!this.f929g.sendEmptyMessage(HAPTIC_QUIT_PLAYBACK)) {
                if (((f915b042704270427 + m1057b042704270427()) * f915b042704270427) % f916b0427 != f914b042704270427) {
                    f915b042704270427 = m1042b04270427();
                    f914b042704270427 = 16;
                }
                this.f924b0444044404440444 = false;
                try {
                    m1059c();
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void removePlaybackCallbacks() {
        synchronized (this.f937o) {
            Iterator it = this.f948z.iterator();
            while (it.hasNext()) {
                this.f929g.removeCallbacks((ccrcrr) it.next());
            }
            this.f948z.clear();
        }
    }

    public void run() {
        String str = null;
        Process.setThreadPriority(-19);
        Looper.prepare();
        this.f932j = Looper.myLooper();
        this.f929g = new rccrrr();
        while (true) {
            try {
                str.length();
            } catch (Exception e) {
                f915b042704270427 = m1042b04270427();
                this.f931i = new HapticDownloadThread(this.f928f, this.f929g, this.f920B, this.f921C);
                this.f931i.start();
                this.f924b0444044404440444 = true;
                m1059c();
                Looper.loop();
                return;
            }
        }
    }

    public void stopHapticPlayback() {
        try {
            this.f929g.sendEmptyMessage(HAPTIC_STOP_PLAYBACK);
            int i = f915b042704270427;
            switch ((i * (f913b0427042704270427 + i)) % f916b0427) {
                case DurationDV.DURATION_TYPE /*0*/:
                default:
                    f915b042704270427 = 35;
                    f914b042704270427 = 24;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void syncUpdate(int r11, long r12) {
        /*
        r10 = this;
        r8 = 1;
        r1 = r10.f936n;
        monitor-enter(r1);
        r2 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x003f }
        r4 = (long) r11;	 Catch:{ all -> 0x003f }
        r6 = r2 - r12;
        r4 = r4 + r6;
        r0 = (int) r4;	 Catch:{ all -> 0x003f }
        r4 = r10.f943u;	 Catch:{ all -> 0x003f }
        r6 = r10.f945w;	 Catch:{ all -> 0x003f }
        r2 = r2 - r6;
        r2 = (int) r2;	 Catch:{ all -> 0x003f }
        r2 = r2 + r4;
        r2 = r0 - r2;
        r3 = 50;
        r4 = java.lang.Math.abs(r2);	 Catch:{ all -> 0x003f }
        if (r3 >= r4) goto L_0x003d;
    L_0x001e:
        r3 = r10.f943u;	 Catch:{ all -> 0x003f }
        r2 = r2 + r3;
        r10.f943u = r2;	 Catch:{ all -> 0x003f }
    L_0x0023:
        r2 = 0;
        switch(r2) {
            case 0: goto L_0x002b;
            case 1: goto L_0x0023;
            default: goto L_0x0027;
        };	 Catch:{ all -> 0x003f }
    L_0x0027:
        switch(r8) {
            case 0: goto L_0x0023;
            case 1: goto L_0x002b;
            default: goto L_0x002a;
        };	 Catch:{ all -> 0x003f }
    L_0x002a:
        goto L_0x0027;
    L_0x002b:
        r2 = r10.f943u;	 Catch:{ all -> 0x003f }
        r10.f942t = r2;	 Catch:{ all -> 0x003f }
        r2 = r10.f929g;	 Catch:{ all -> 0x003f }
        r3 = r10.f929g;	 Catch:{ all -> 0x003f }
        r4 = 1;
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r0 = r3.obtainMessage(r4, r0, r5);	 Catch:{ all -> 0x003f }
        r2.sendMessage(r0);	 Catch:{ all -> 0x003f }
    L_0x003d:
        monitor-exit(r1);	 Catch:{ all -> 0x003f }
        return;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticPlaybackThread.syncUpdate(int, long):void");
    }
}
