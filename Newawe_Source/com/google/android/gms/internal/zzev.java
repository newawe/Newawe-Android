package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.List;

@zzhb
public class zzev implements zzem {
    private final Context mContext;
    private zzer zzCD;
    private final zzeo zzCf;
    private final AdRequestInfoParcel zzCu;
    private final long zzCv;
    private final long zzCw;
    private boolean zzCy;
    private final Object zzpV;
    private final zzcb zzpe;
    private final zzex zzpn;
    private final boolean zzsA;
    private final boolean zzuS;

    /* renamed from: com.google.android.gms.internal.zzev.1 */
    class C05821 implements Runnable {
        final /* synthetic */ zzes zzCE;
        final /* synthetic */ zzev zzCF;

        C05821(zzev com_google_android_gms_internal_zzev, zzes com_google_android_gms_internal_zzes) {
            this.zzCF = com_google_android_gms_internal_zzev;
            this.zzCE = com_google_android_gms_internal_zzes;
        }

        public void run() {
            try {
                this.zzCE.zzCq.destroy();
            } catch (Throwable e) {
                zzb.zzd("Could not destroy mediation adapter.", e);
            }
        }
    }

    public zzev(Context context, AdRequestInfoParcel adRequestInfoParcel, zzex com_google_android_gms_internal_zzex, zzeo com_google_android_gms_internal_zzeo, boolean z, boolean z2, long j, long j2, zzcb com_google_android_gms_internal_zzcb) {
        this.zzpV = new Object();
        this.zzCy = false;
        this.mContext = context;
        this.zzCu = adRequestInfoParcel;
        this.zzpn = com_google_android_gms_internal_zzex;
        this.zzCf = com_google_android_gms_internal_zzeo;
        this.zzsA = z;
        this.zzuS = z2;
        this.zzCv = j;
        this.zzCw = j2;
        this.zzpe = com_google_android_gms_internal_zzcb;
    }

    public void cancel() {
        synchronized (this.zzpV) {
            this.zzCy = true;
            if (this.zzCD != null) {
                this.zzCD.cancel();
            }
        }
    }

    public zzes zzc(List<zzen> list) {
        zzb.zzaI("Starting mediation.");
        Iterable arrayList = new ArrayList();
        zzbz zzdB = this.zzpe.zzdB();
        for (zzen com_google_android_gms_internal_zzen : list) {
            zzb.zzaJ("Trying mediation network: " + com_google_android_gms_internal_zzen.zzBA);
            for (String str : com_google_android_gms_internal_zzen.zzBB) {
                zzbz zzdB2 = this.zzpe.zzdB();
                synchronized (this.zzpV) {
                    if (this.zzCy) {
                        zzes com_google_android_gms_internal_zzes = new zzes(-1);
                        return com_google_android_gms_internal_zzes;
                    }
                    this.zzCD = new zzer(this.mContext, str, this.zzpn, this.zzCf, com_google_android_gms_internal_zzen, this.zzCu.zzHt, this.zzCu.zzrp, this.zzCu.zzrl, this.zzsA, this.zzuS, this.zzCu.zzrD, this.zzCu.zzrH);
                    com_google_android_gms_internal_zzes = this.zzCD.zza(this.zzCv, this.zzCw);
                    if (com_google_android_gms_internal_zzes.zzCo == 0) {
                        zzb.zzaI("Adapter succeeded.");
                        this.zzpe.zzc("mediation_network_succeed", str);
                        if (!arrayList.isEmpty()) {
                            this.zzpe.zzc("mediation_networks_fail", TextUtils.join(",", arrayList));
                        }
                        this.zzpe.zza(zzdB2, "mls");
                        this.zzpe.zza(zzdB, "ttm");
                        return com_google_android_gms_internal_zzes;
                    }
                    arrayList.add(str);
                    this.zzpe.zza(zzdB2, "mlf");
                    if (com_google_android_gms_internal_zzes.zzCq != null) {
                        zzir.zzMc.post(new C05821(this, com_google_android_gms_internal_zzes));
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzpe.zzc("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        return new zzes(1);
    }
}
