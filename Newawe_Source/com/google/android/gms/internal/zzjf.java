package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@zzhb
public class zzjf {

    /* renamed from: com.google.android.gms.internal.zzjf.1 */
    static class C06301 implements Runnable {
        final /* synthetic */ zzjd zzNf;
        final /* synthetic */ zza zzNg;
        final /* synthetic */ zzjg zzNh;

        C06301(zzjd com_google_android_gms_internal_zzjd, zza com_google_android_gms_internal_zzjf_zza, zzjg com_google_android_gms_internal_zzjg) {
            this.zzNf = com_google_android_gms_internal_zzjd;
            this.zzNg = com_google_android_gms_internal_zzjf_zza;
            this.zzNh = com_google_android_gms_internal_zzjg;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r3 = this;
            r0 = r3.zzNf;	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
            r1 = r3.zzNg;	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
            r2 = r3.zzNh;	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
            r2 = r2.get();	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
            r1 = r1.zzf(r2);	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
            r0.zzg(r1);	 Catch:{ CancellationException -> 0x001c, InterruptedException -> 0x001a, ExecutionException -> 0x0012 }
        L_0x0011:
            return;
        L_0x0012:
            r0 = move-exception;
        L_0x0013:
            r0 = r3.zzNf;
            r1 = 1;
            r0.cancel(r1);
            goto L_0x0011;
        L_0x001a:
            r0 = move-exception;
            goto L_0x0013;
        L_0x001c:
            r0 = move-exception;
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjf.1.run():void");
        }
    }

    /* renamed from: com.google.android.gms.internal.zzjf.2 */
    static class C06312 implements Runnable {
        final /* synthetic */ AtomicInteger zzNi;
        final /* synthetic */ int zzNj;
        final /* synthetic */ zzjd zzNk;
        final /* synthetic */ List zzNl;

        C06312(AtomicInteger atomicInteger, int i, zzjd com_google_android_gms_internal_zzjd, List list) {
            this.zzNi = atomicInteger;
            this.zzNj = i;
            this.zzNk = com_google_android_gms_internal_zzjd;
            this.zzNl = list;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r2 = this;
            r0 = r2.zzNi;
            r0 = r0.incrementAndGet();
            r1 = r2.zzNj;
            if (r0 < r1) goto L_0x0015;
        L_0x000a:
            r0 = r2.zzNk;	 Catch:{ ExecutionException -> 0x001d, InterruptedException -> 0x0016 }
            r1 = r2.zzNl;	 Catch:{ ExecutionException -> 0x001d, InterruptedException -> 0x0016 }
            r1 = com.google.android.gms.internal.zzjf.zzm(r1);	 Catch:{ ExecutionException -> 0x001d, InterruptedException -> 0x0016 }
            r0.zzg(r1);	 Catch:{ ExecutionException -> 0x001d, InterruptedException -> 0x0016 }
        L_0x0015:
            return;
        L_0x0016:
            r0 = move-exception;
        L_0x0017:
            r1 = "Unable to convert list of futures to a future of list";
            com.google.android.gms.ads.internal.util.client.zzb.zzd(r1, r0);
            goto L_0x0015;
        L_0x001d:
            r0 = move-exception;
            goto L_0x0017;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjf.2.run():void");
        }
    }

    public interface zza<D, R> {
        R zzf(D d);
    }

    public static <A, B> zzjg<B> zza(zzjg<A> com_google_android_gms_internal_zzjg_A, zza<A, B> com_google_android_gms_internal_zzjf_zza_A__B) {
        zzjg com_google_android_gms_internal_zzjd = new zzjd();
        com_google_android_gms_internal_zzjg_A.zzb(new C06301(com_google_android_gms_internal_zzjd, com_google_android_gms_internal_zzjf_zza_A__B, com_google_android_gms_internal_zzjg_A));
        return com_google_android_gms_internal_zzjd;
    }

    public static <V> zzjg<List<V>> zzl(List<zzjg<V>> list) {
        zzjg com_google_android_gms_internal_zzjd = new zzjd();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzjg zzb : list) {
            zzb.zzb(new C06312(atomicInteger, size, com_google_android_gms_internal_zzjd, list));
        }
        return com_google_android_gms_internal_zzjd;
    }

    private static <V> List<V> zzm(List<zzjg<V>> list) throws ExecutionException, InterruptedException {
        List<V> arrayList = new ArrayList();
        for (zzjg com_google_android_gms_internal_zzjg : list) {
            Object obj = com_google_android_gms_internal_zzjg.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
