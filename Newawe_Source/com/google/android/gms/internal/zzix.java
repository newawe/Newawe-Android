package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzx;

@zzhb
public class zzix {
    private Handler mHandler;
    private HandlerThread zzMG;
    private int zzMH;
    private final Object zzpV;

    /* renamed from: com.google.android.gms.internal.zzix.1 */
    class C06291 implements Runnable {
        final /* synthetic */ zzix zzMI;

        C06291(zzix com_google_android_gms_internal_zzix) {
            this.zzMI = com_google_android_gms_internal_zzix;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r2 = this;
            r0 = r2.zzMI;
            r1 = r0.zzpV;
            monitor-enter(r1);
            r0 = "Suspending the looper thread";
            com.google.android.gms.internal.zzin.m4114v(r0);	 Catch:{ all -> 0x002a }
        L_0x000c:
            r0 = r2.zzMI;	 Catch:{ all -> 0x002a }
            r0 = r0.zzMH;	 Catch:{ all -> 0x002a }
            if (r0 != 0) goto L_0x002d;
        L_0x0014:
            r0 = r2.zzMI;	 Catch:{ InterruptedException -> 0x0023 }
            r0 = r0.zzpV;	 Catch:{ InterruptedException -> 0x0023 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0023 }
            r0 = "Looper thread resumed";
            com.google.android.gms.internal.zzin.m4114v(r0);	 Catch:{ InterruptedException -> 0x0023 }
            goto L_0x000c;
        L_0x0023:
            r0 = move-exception;
            r0 = "Looper thread interrupted.";
            com.google.android.gms.internal.zzin.m4114v(r0);	 Catch:{ all -> 0x002a }
            goto L_0x000c;
        L_0x002a:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002a }
            throw r0;
        L_0x002d:
            monitor-exit(r1);	 Catch:{ all -> 0x002a }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzix.1.run():void");
        }
    }

    public zzix() {
        this.zzMG = null;
        this.mHandler = null;
        this.zzMH = 0;
        this.zzpV = new Object();
    }

    public Looper zzhC() {
        Looper looper;
        synchronized (this.zzpV) {
            if (this.zzMH != 0) {
                zzx.zzb(this.zzMG, (Object) "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzMG == null) {
                zzin.m4114v("Starting the looper thread.");
                this.zzMG = new HandlerThread("LooperProvider");
                this.zzMG.start();
                this.mHandler = new Handler(this.zzMG.getLooper());
                zzin.m4114v("Looper thread started.");
            } else {
                zzin.m4114v("Resuming the looper thread");
                this.zzpV.notifyAll();
            }
            this.zzMH++;
            looper = this.zzMG.getLooper();
        }
        return looper;
    }

    public void zzhD() {
        synchronized (this.zzpV) {
            zzx.zzb(this.zzMH > 0, (Object) "Invalid state: release() called more times than expected.");
            int i = this.zzMH - 1;
            this.zzMH = i;
            if (i == 0) {
                this.mHandler.post(new C06291(this));
            }
        }
    }
}
