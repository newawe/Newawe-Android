package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class zzlv implements com.google.android.gms.clearcut.zzc {
    private static final Object zzafn;
    private static final zze zzafo;
    private static final long zzafp;
    private GoogleApiClient zzaaj;
    private final zza zzafq;
    private final Object zzafr;
    private long zzafs;
    private final long zzaft;
    private ScheduledFuture<?> zzafu;
    private final Runnable zzafv;
    private final zzmq zzqW;

    /* renamed from: com.google.android.gms.internal.zzlv.1 */
    class C06421 implements Runnable {
        final /* synthetic */ zzlv zzafw;

        C06421(zzlv com_google_android_gms_internal_zzlv) {
            this.zzafw = com_google_android_gms_internal_zzlv;
        }

        public void run() {
            synchronized (this.zzafw.zzafr) {
                if (this.zzafw.zzafs <= this.zzafw.zzqW.elapsedRealtime() && this.zzafw.zzaaj != null) {
                    Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                    this.zzafw.zzaaj.disconnect();
                    this.zzafw.zzaaj = null;
                }
            }
        }
    }

    public interface zza {
    }

    private static final class zze {
        private int mSize;

        private zze() {
            this.mSize = 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean zza(long r8, java.util.concurrent.TimeUnit r10) throws java.lang.InterruptedException {
            /*
            r7 = this;
            r2 = java.lang.System.currentTimeMillis();
            r0 = java.util.concurrent.TimeUnit.MILLISECONDS;
            r0 = r0.convert(r8, r10);
            monitor-enter(r7);
        L_0x000b:
            r4 = r7.mSize;	 Catch:{ all -> 0x001b }
            if (r4 != 0) goto L_0x0012;
        L_0x000f:
            r0 = 1;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
        L_0x0011:
            return r0;
        L_0x0012:
            r4 = 0;
            r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x001e;
        L_0x0018:
            r0 = 0;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x0011;
        L_0x001b:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            throw r0;
        L_0x001e:
            r7.wait(r0);	 Catch:{ all -> 0x001b }
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x001b }
            r4 = r4 - r2;
            r0 = r0 - r4;
            goto L_0x000b;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlv.zze.zza(long, java.util.concurrent.TimeUnit):boolean");
        }

        public synchronized void zzoH() {
            this.mSize++;
        }

        public synchronized void zzoI() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzlv.2 */
    class C11832 implements com.google.android.gms.common.api.PendingResult.zza {
        final /* synthetic */ zzlv zzafw;

        C11832(zzlv com_google_android_gms_internal_zzlv) {
            this.zzafw = com_google_android_gms_internal_zzlv;
        }

        public void zzu(Status status) {
            zzlv.zzafo.zzoI();
        }
    }

    public static class zzb implements zza {
    }

    static abstract class zzc<R extends Result> extends com.google.android.gms.common.api.internal.zza.zza<R, zzlw> {
        public zzc(GoogleApiClient googleApiClient) {
            super(com.google.android.gms.clearcut.zzb.zzUI, googleApiClient);
        }
    }

    final class zzd extends zzc<Status> {
        final /* synthetic */ zzlv zzafw;
        private final LogEventParcelable zzafx;

        /* renamed from: com.google.android.gms.internal.zzlv.zzd.1 */
        class C13221 extends com.google.android.gms.internal.zzlx.zza {
            final /* synthetic */ zzd zzafy;

            C13221(zzd com_google_android_gms_internal_zzlv_zzd) {
                this.zzafy = com_google_android_gms_internal_zzlv_zzd;
            }

            public void zzv(Status status) {
                this.zzafy.zza((Result) status);
            }
        }

        zzd(zzlv com_google_android_gms_internal_zzlv, LogEventParcelable logEventParcelable, GoogleApiClient googleApiClient) {
            this.zzafw = com_google_android_gms_internal_zzlv;
            super(googleApiClient);
            this.zzafx = logEventParcelable;
        }

        public boolean equals(Object rhs) {
            if (!(rhs instanceof zzd)) {
                return false;
            }
            return this.zzafx.equals(((zzd) rhs).zzafx);
        }

        public String toString() {
            return "MethodImpl(" + this.zzafx + ")";
        }

        protected void zza(zzlw com_google_android_gms_internal_zzlw) throws RemoteException {
            zzlx c13221 = new C13221(this);
            try {
                zzlv.zza(this.zzafx);
                com_google_android_gms_internal_zzlw.zza(c13221, this.zzafx);
            } catch (Throwable th) {
                Log.e("ClearcutLoggerApiImpl", "MessageNanoProducer " + this.zzafx.zzafl.toString() + " threw: " + th.toString());
            }
        }

        protected Status zzb(Status status) {
            return status;
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    static {
        zzafn = new Object();
        zzafo = new zze();
        zzafp = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    }

    public zzlv() {
        this(new zzmt(), zzafp, new zzb());
    }

    public zzlv(zzmq com_google_android_gms_internal_zzmq, long j, zza com_google_android_gms_internal_zzlv_zza) {
        this.zzafr = new Object();
        this.zzafs = 0;
        this.zzafu = null;
        this.zzaaj = null;
        this.zzafv = new C06421(this);
        this.zzqW = com_google_android_gms_internal_zzmq;
        this.zzaft = j;
        this.zzafq = com_google_android_gms_internal_zzlv_zza;
    }

    private static void zza(LogEventParcelable logEventParcelable) {
        if (logEventParcelable.zzafl != null && logEventParcelable.zzafk.zzbuY.length == 0) {
            logEventParcelable.zzafk.zzbuY = logEventParcelable.zzafl.zzoF();
        }
        if (logEventParcelable.zzafm != null && logEventParcelable.zzafk.zzbvf.length == 0) {
            logEventParcelable.zzafk.zzbvf = logEventParcelable.zzafm.zzoF();
        }
        logEventParcelable.zzafi = zzsu.toByteArray(logEventParcelable.zzafk);
    }

    private zzd zzb(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        zzafo.zzoH();
        zzd com_google_android_gms_internal_zzlv_zzd = new zzd(this, logEventParcelable, googleApiClient);
        com_google_android_gms_internal_zzlv_zzd.zza(new C11832(this));
        return com_google_android_gms_internal_zzlv_zzd;
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        zza(logEventParcelable);
        return googleApiClient.zza(zzb(googleApiClient, logEventParcelable));
    }

    public boolean zza(GoogleApiClient googleApiClient, long j, TimeUnit timeUnit) {
        try {
            return zzafo.zza(j, timeUnit);
        } catch (InterruptedException e) {
            Log.e("ClearcutLoggerApiImpl", "flush interrupted");
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
