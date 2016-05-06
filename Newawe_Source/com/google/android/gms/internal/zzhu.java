package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzif.zza;

@zzhb
public class zzhu extends zzim implements zzhv, zzhy {
    private final Context mContext;
    private final String zzCd;
    private final zza zzGd;
    private int zzGu;
    private final zzia zzKB;
    private final zzhy zzKC;
    private final String zzKD;
    private final String zzKE;
    private int zzKF;
    private final Object zzpV;
    private final String zzrG;

    /* renamed from: com.google.android.gms.internal.zzhu.1 */
    class C06131 implements Runnable {
        final /* synthetic */ zzey zzKG;
        final /* synthetic */ zzhu zzKH;
        final /* synthetic */ AdRequestParcel zzpW;

        C06131(zzhu com_google_android_gms_internal_zzhu, AdRequestParcel adRequestParcel, zzey com_google_android_gms_internal_zzey) {
            this.zzKH = com_google_android_gms_internal_zzhu;
            this.zzpW = adRequestParcel;
            this.zzKG = com_google_android_gms_internal_zzey;
        }

        public void run() {
            this.zzKH.zza(this.zzpW, this.zzKG);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhu.2 */
    class C06142 implements Runnable {
        final /* synthetic */ zzey zzKG;
        final /* synthetic */ zzhu zzKH;
        final /* synthetic */ zzhx zzKI;
        final /* synthetic */ AdRequestParcel zzpW;

        C06142(zzhu com_google_android_gms_internal_zzhu, zzey com_google_android_gms_internal_zzey, AdRequestParcel adRequestParcel, zzhx com_google_android_gms_internal_zzhx) {
            this.zzKH = com_google_android_gms_internal_zzhu;
            this.zzKG = com_google_android_gms_internal_zzey;
            this.zzpW = adRequestParcel;
            this.zzKI = com_google_android_gms_internal_zzhx;
        }

        public void run() {
            try {
                this.zzKG.zza(zze.zzC(this.zzKH.mContext), this.zzpW, this.zzKH.zzrG, this.zzKI, this.zzKH.zzKD);
            } catch (Throwable e) {
                zzb.zzd("Fail to initialize adapter " + this.zzKH.zzCd, e);
                this.zzKH.zza(this.zzKH.zzCd, 0);
            }
        }
    }

    public zzhu(Context context, String str, String str2, String str3, String str4, zza com_google_android_gms_internal_zzif_zza, zzia com_google_android_gms_internal_zzia, zzhy com_google_android_gms_internal_zzhy) {
        this.zzKF = 0;
        this.zzGu = 3;
        this.mContext = context;
        this.zzCd = str;
        this.zzrG = str2;
        this.zzKD = str3;
        this.zzKE = str4;
        this.zzGd = com_google_android_gms_internal_zzif_zza;
        this.zzKB = com_google_android_gms_internal_zzia;
        this.zzpV = new Object();
        this.zzKC = com_google_android_gms_internal_zzhy;
    }

    private void zza(AdRequestParcel adRequestParcel, zzey com_google_android_gms_internal_zzey) {
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzCd)) {
                com_google_android_gms_internal_zzey.zza(adRequestParcel, this.zzKD, this.zzKE);
            } else {
                com_google_android_gms_internal_zzey.zzb(adRequestParcel, this.zzKD);
            }
        } catch (Throwable e) {
            zzb.zzd("Fail to load ad from adapter.", e);
            zza(this.zzCd, 0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzk(long r4) {
        /*
        r3 = this;
    L_0x0000:
        r1 = r3.zzpV;
        monitor-enter(r1);
        r0 = r3.zzKF;	 Catch:{ all -> 0x0011 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r3.zzf(r4);	 Catch:{ all -> 0x0011 }
        if (r0 != 0) goto L_0x0014;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        goto L_0x0008;
    L_0x0011:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        throw r0;
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhu.zzk(long):void");
    }

    public void onStop() {
    }

    public void zzN(int i) {
        zza(this.zzCd, 0);
    }

    public void zza(String str, int i) {
        synchronized (this.zzpV) {
            this.zzKF = 2;
            this.zzGu = i;
            this.zzpV.notify();
        }
    }

    public void zzax(String str) {
        synchronized (this.zzpV) {
            this.zzKF = 1;
            this.zzpV.notify();
        }
    }

    public void zzbr() {
        if (this.zzKB != null && this.zzKB.zzgQ() != null && this.zzKB.zzgP() != null) {
            zzhx zzgQ = this.zzKB.zzgQ();
            zzgQ.zza((zzhy) this);
            zzgQ.zza((zzhv) this);
            AdRequestParcel adRequestParcel = this.zzGd.zzLd.zzHt;
            zzey zzgP = this.zzKB.zzgP();
            try {
                if (zzgP.isInitialized()) {
                    com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C06131(this, adRequestParcel, zzgP));
                } else {
                    com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C06142(this, zzgP, adRequestParcel, zzgQ));
                }
            } catch (Throwable e) {
                zzb.zzd("Fail to check if adapter is initialized.", e);
                zza(this.zzCd, 0);
            }
            zzk(zzr.zzbG().elapsedRealtime());
            zzgQ.zza(null);
            zzgQ.zza(null);
            if (this.zzKF == 1) {
                this.zzKC.zzax(this.zzCd);
            } else {
                this.zzKC.zza(this.zzCd, this.zzGu);
            }
        }
    }

    protected boolean zzf(long j) {
        long elapsedRealtime = 20000 - (zzr.zzbG().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzpV.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void zzgN() {
        zza(this.zzGd.zzLd.zzHt, this.zzKB.zzgP());
    }
}
