package com.google.android.gms.internal;

import android.os.Process;
import com.chartboost.sdk.CBLocation;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@zzhb
public final class zziq {
    private static final ExecutorService zzLU;
    private static final ExecutorService zzLV;

    /* renamed from: com.google.android.gms.internal.zziq.1 */
    static class C06181 implements Callable<Void> {
        final /* synthetic */ Runnable zzLW;

        C06181(Runnable runnable) {
            this.zzLW = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() {
            this.zzLW.run();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zziq.2 */
    static class C06192 implements Callable<Void> {
        final /* synthetic */ Runnable zzLW;

        C06192(Runnable runnable) {
            this.zzLW = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() {
            this.zzLW.run();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zziq.3 */
    static class C06203 implements Runnable {
        final /* synthetic */ zzjd zzLX;
        final /* synthetic */ Callable zzLY;

        C06203(zzjd com_google_android_gms_internal_zzjd, Callable callable) {
            this.zzLX = com_google_android_gms_internal_zzjd;
            this.zzLY = callable;
        }

        public void run() {
            try {
                Process.setThreadPriority(10);
                this.zzLX.zzg(this.zzLY.call());
            } catch (Throwable e) {
                zzr.zzbF().zzb(e, true);
                this.zzLX.cancel(true);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zziq.4 */
    static class C06214 implements Runnable {
        final /* synthetic */ zzjd zzLX;
        final /* synthetic */ Future zzLZ;

        C06214(zzjd com_google_android_gms_internal_zzjd, Future future) {
            this.zzLX = com_google_android_gms_internal_zzjd;
            this.zzLZ = future;
        }

        public void run() {
            if (this.zzLX.isCancelled()) {
                this.zzLZ.cancel(true);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zziq.5 */
    static class C06225 implements ThreadFactory {
        private final AtomicInteger zzMa;
        final /* synthetic */ String zzMb;

        C06225(String str) {
            this.zzMb = str;
            this.zzMa = new AtomicInteger(1);
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AdWorker(" + this.zzMb + ") #" + this.zzMa.getAndIncrement());
        }
    }

    static {
        zzLU = Executors.newFixedThreadPool(10, zzaB(CBLocation.LOCATION_DEFAULT));
        zzLV = Executors.newFixedThreadPool(5, zzaB("Loader"));
    }

    public static zzjg<Void> zza(int i, Runnable runnable) {
        return i == 1 ? zza(zzLV, new C06181(runnable)) : zza(zzLU, new C06192(runnable));
    }

    public static zzjg<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzjg<T> zza(Callable<T> callable) {
        return zza(zzLU, (Callable) callable);
    }

    public static <T> zzjg<T> zza(ExecutorService executorService, Callable<T> callable) {
        Object com_google_android_gms_internal_zzjd = new zzjd();
        try {
            com_google_android_gms_internal_zzjd.zzc(new C06214(com_google_android_gms_internal_zzjd, executorService.submit(new C06203(com_google_android_gms_internal_zzjd, callable))));
        } catch (Throwable e) {
            zzb.zzd("Thread execution is rejected.", e);
            com_google_android_gms_internal_zzjd.cancel(true);
        }
        return com_google_android_gms_internal_zzjd;
    }

    private static ThreadFactory zzaB(String str) {
        return new C06225(str);
    }
}
