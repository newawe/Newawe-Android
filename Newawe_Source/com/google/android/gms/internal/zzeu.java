package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

@zzhb
public class zzeu implements zzem {
    private final Context mContext;
    private final zzeo zzCf;
    private final AdRequestInfoParcel zzCu;
    private final long zzCv;
    private final long zzCw;
    private final int zzCx;
    private boolean zzCy;
    private final Map<zzjg<zzes>, zzer> zzCz;
    private final Object zzpV;
    private final zzex zzpn;
    private final boolean zzsA;
    private final boolean zzuS;

    /* renamed from: com.google.android.gms.internal.zzeu.1 */
    class C05801 implements Callable<zzes> {
        final /* synthetic */ zzer zzCA;
        final /* synthetic */ zzeu zzCB;

        C05801(zzeu com_google_android_gms_internal_zzeu, zzer com_google_android_gms_internal_zzer) {
            this.zzCB = com_google_android_gms_internal_zzeu;
            this.zzCA = com_google_android_gms_internal_zzer;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzeE();
        }

        public zzes zzeE() throws Exception {
            synchronized (this.zzCB.zzpV) {
                if (this.zzCB.zzCy) {
                    return null;
                }
                return this.zzCA.zza(this.zzCB.zzCv, this.zzCB.zzCw);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzeu.2 */
    class C05812 implements Runnable {
        final /* synthetic */ zzeu zzCB;
        final /* synthetic */ zzjg zzCC;

        C05812(zzeu com_google_android_gms_internal_zzeu, zzjg com_google_android_gms_internal_zzjg) {
            this.zzCB = com_google_android_gms_internal_zzeu;
            this.zzCC = com_google_android_gms_internal_zzjg;
        }

        public void run() {
            for (zzjg com_google_android_gms_internal_zzjg : this.zzCB.zzCz.keySet()) {
                if (com_google_android_gms_internal_zzjg != this.zzCC) {
                    ((zzer) this.zzCB.zzCz.get(com_google_android_gms_internal_zzjg)).cancel();
                }
            }
        }
    }

    public zzeu(Context context, AdRequestInfoParcel adRequestInfoParcel, zzex com_google_android_gms_internal_zzex, zzeo com_google_android_gms_internal_zzeo, boolean z, boolean z2, long j, long j2, int i) {
        this.zzpV = new Object();
        this.zzCy = false;
        this.zzCz = new HashMap();
        this.mContext = context;
        this.zzCu = adRequestInfoParcel;
        this.zzpn = com_google_android_gms_internal_zzex;
        this.zzCf = com_google_android_gms_internal_zzeo;
        this.zzsA = z;
        this.zzuS = z2;
        this.zzCv = j;
        this.zzCw = j2;
        this.zzCx = i;
    }

    private void zza(zzjg<zzes> com_google_android_gms_internal_zzjg_com_google_android_gms_internal_zzes) {
        zzir.zzMc.post(new C05812(this, com_google_android_gms_internal_zzjg_com_google_android_gms_internal_zzes));
    }

    private zzes zzd(List<zzjg<zzes>> list) {
        Throwable e;
        synchronized (this.zzpV) {
            if (this.zzCy) {
                zzes com_google_android_gms_internal_zzes = new zzes(-1);
                return com_google_android_gms_internal_zzes;
            }
            for (zzjg com_google_android_gms_internal_zzjg : list) {
                try {
                    com_google_android_gms_internal_zzes = (zzes) com_google_android_gms_internal_zzjg.get();
                    if (com_google_android_gms_internal_zzes != null && com_google_android_gms_internal_zzes.zzCo == 0) {
                        zza(com_google_android_gms_internal_zzjg);
                        return com_google_android_gms_internal_zzes;
                    }
                } catch (InterruptedException e2) {
                    e = e2;
                    zzb.zzd("Exception while processing an adapter; continuing with other adapters", e);
                } catch (ExecutionException e3) {
                    e = e3;
                    zzb.zzd("Exception while processing an adapter; continuing with other adapters", e);
                }
            }
            zza(null);
            return new zzes(1);
        }
    }

    private zzes zze(List<zzjg<zzes>> list) {
        InterruptedException max;
        synchronized (this.zzpV) {
            if (this.zzCy) {
                zzes com_google_android_gms_internal_zzes = new zzes(-1);
                return com_google_android_gms_internal_zzes;
            }
            long j = -1;
            zzjg com_google_android_gms_internal_zzjg = null;
            com_google_android_gms_internal_zzes = null;
            long j2 = this.zzCf.zzBY != -1 ? this.zzCf.zzBY : 10000;
            long j3 = j2;
            for (zzjg com_google_android_gms_internal_zzjg2 : list) {
                zzes com_google_android_gms_internal_zzes2;
                zzfa com_google_android_gms_internal_zzfa;
                int zzeD;
                zzes com_google_android_gms_internal_zzes3;
                zzjg com_google_android_gms_internal_zzjg3;
                zzes com_google_android_gms_internal_zzes4;
                long currentTimeMillis = zzr.zzbG().currentTimeMillis();
                if (j3 == 0) {
                    try {
                        if (com_google_android_gms_internal_zzjg2.isDone()) {
                            com_google_android_gms_internal_zzes2 = (zzes) com_google_android_gms_internal_zzjg2.get();
                            if (com_google_android_gms_internal_zzes2 != null && com_google_android_gms_internal_zzes2.zzCo == 0) {
                                com_google_android_gms_internal_zzfa = com_google_android_gms_internal_zzes2.zzCt;
                                if (com_google_android_gms_internal_zzfa != null && com_google_android_gms_internal_zzfa.zzeD() > j) {
                                    zzeD = com_google_android_gms_internal_zzfa.zzeD();
                                    com_google_android_gms_internal_zzes3 = com_google_android_gms_internal_zzes2;
                                    com_google_android_gms_internal_zzjg3 = com_google_android_gms_internal_zzjg2;
                                    com_google_android_gms_internal_zzes4 = com_google_android_gms_internal_zzes3;
                                    com_google_android_gms_internal_zzjg = com_google_android_gms_internal_zzjg3;
                                    com_google_android_gms_internal_zzes3 = com_google_android_gms_internal_zzes4;
                                    max = Math.max(j3 - (zzr.zzbG().currentTimeMillis() - currentTimeMillis), 0);
                                    j = zzeD;
                                    com_google_android_gms_internal_zzes = com_google_android_gms_internal_zzes3;
                                    j3 = max;
                                }
                            }
                            com_google_android_gms_internal_zzes4 = com_google_android_gms_internal_zzes;
                            com_google_android_gms_internal_zzjg3 = com_google_android_gms_internal_zzjg;
                            zzeD = j;
                            com_google_android_gms_internal_zzjg = com_google_android_gms_internal_zzjg3;
                            com_google_android_gms_internal_zzes3 = com_google_android_gms_internal_zzes4;
                            max = Math.max(j3 - (zzr.zzbG().currentTimeMillis() - currentTimeMillis), 0);
                            j = zzeD;
                            com_google_android_gms_internal_zzes = com_google_android_gms_internal_zzes3;
                            j3 = max;
                        }
                    } catch (InterruptedException e) {
                        max = e;
                        try {
                            zzb.zzd("Exception while processing an adapter; continuing with other adapters", max);
                            j3 = max;
                        } finally {
                            com_google_android_gms_internal_zzes = j3 - (zzr.zzbG().currentTimeMillis() - currentTimeMillis);
                            j = 0;
                            Math.max(com_google_android_gms_internal_zzes, j);
                            j = j3;
                        }
                    } catch (ExecutionException e2) {
                        max = e2;
                        zzb.zzd("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    } catch (RemoteException e3) {
                        max = e3;
                        zzb.zzd("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    } catch (TimeoutException e4) {
                        max = e4;
                        zzb.zzd("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    }
                }
                com_google_android_gms_internal_zzes2 = (zzes) com_google_android_gms_internal_zzjg2.get(j3, TimeUnit.MILLISECONDS);
                com_google_android_gms_internal_zzfa = com_google_android_gms_internal_zzes2.zzCt;
                zzeD = com_google_android_gms_internal_zzfa.zzeD();
                com_google_android_gms_internal_zzes3 = com_google_android_gms_internal_zzes2;
                com_google_android_gms_internal_zzjg3 = com_google_android_gms_internal_zzjg2;
                com_google_android_gms_internal_zzes4 = com_google_android_gms_internal_zzes3;
                com_google_android_gms_internal_zzjg = com_google_android_gms_internal_zzjg3;
                com_google_android_gms_internal_zzes3 = com_google_android_gms_internal_zzes4;
                max = Math.max(j3 - (zzr.zzbG().currentTimeMillis() - currentTimeMillis), 0);
                j = zzeD;
                com_google_android_gms_internal_zzes = com_google_android_gms_internal_zzes3;
                j3 = max;
            }
            zza(com_google_android_gms_internal_zzjg);
            return com_google_android_gms_internal_zzes == null ? new zzes(1) : com_google_android_gms_internal_zzes;
        }
    }

    public void cancel() {
        synchronized (this.zzpV) {
            this.zzCy = true;
            for (zzer cancel : this.zzCz.values()) {
                cancel.cancel();
            }
        }
    }

    public zzes zzc(List<zzen> list) {
        zzb.zzaI("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        List arrayList = new ArrayList();
        for (zzen com_google_android_gms_internal_zzen : list) {
            zzb.zzaJ("Trying mediation network: " + com_google_android_gms_internal_zzen.zzBA);
            for (String com_google_android_gms_internal_zzer : com_google_android_gms_internal_zzen.zzBB) {
                zzer com_google_android_gms_internal_zzer2 = new zzer(this.mContext, com_google_android_gms_internal_zzer, this.zzpn, this.zzCf, com_google_android_gms_internal_zzen, this.zzCu.zzHt, this.zzCu.zzrp, this.zzCu.zzrl, this.zzsA, this.zzuS, this.zzCu.zzrD, this.zzCu.zzrH);
                zzjg zza = zziq.zza(newCachedThreadPool, new C05801(this, com_google_android_gms_internal_zzer2));
                this.zzCz.put(zza, com_google_android_gms_internal_zzer2);
                arrayList.add(zza);
            }
        }
        switch (this.zzCx) {
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return zze(arrayList);
            default:
                return zzd(arrayList);
        }
    }
}
