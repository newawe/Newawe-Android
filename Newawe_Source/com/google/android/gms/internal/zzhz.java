package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzif.zza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Future;

@zzhb
public class zzhz extends zzim implements zzhy {
    private final Context mContext;
    private final zza zzGd;
    private final ArrayList<Future> zzKL;
    private final ArrayList<String> zzKM;
    private final HashSet<String> zzKN;
    private final zzht zzKO;
    private final Object zzpV;
    private final String zzrG;

    /* renamed from: com.google.android.gms.internal.zzhz.1 */
    class C06151 implements Runnable {
        final /* synthetic */ zzhz zzKP;
        final /* synthetic */ zzif zzpF;

        C06151(zzhz com_google_android_gms_internal_zzhz, zzif com_google_android_gms_internal_zzif) {
            this.zzKP = com_google_android_gms_internal_zzhz;
            this.zzpF = com_google_android_gms_internal_zzif;
        }

        public void run() {
            this.zzKP.zzKO.zzb(this.zzpF);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhz.2 */
    class C06162 implements Runnable {
        final /* synthetic */ zzhz zzKP;
        final /* synthetic */ zzif zzpF;

        C06162(zzhz com_google_android_gms_internal_zzhz, zzif com_google_android_gms_internal_zzif) {
            this.zzKP = com_google_android_gms_internal_zzhz;
            this.zzpF = com_google_android_gms_internal_zzif;
        }

        public void run() {
            this.zzKP.zzKO.zzb(this.zzpF);
        }
    }

    public zzhz(Context context, String str, zza com_google_android_gms_internal_zzif_zza, zzht com_google_android_gms_internal_zzht) {
        this.zzKL = new ArrayList();
        this.zzKM = new ArrayList();
        this.zzKN = new HashSet();
        this.zzpV = new Object();
        this.mContext = context;
        this.zzrG = str;
        this.zzGd = com_google_android_gms_internal_zzif_zza;
        this.zzKO = com_google_android_gms_internal_zzht;
    }

    private zzif zza(int i, @Nullable String str, @Nullable zzen com_google_android_gms_internal_zzen) {
        return new zzif(this.zzGd.zzLd.zzHt, null, this.zzGd.zzLe.zzBQ, i, this.zzGd.zzLe.zzBR, this.zzGd.zzLe.zzHV, this.zzGd.zzLe.orientation, this.zzGd.zzLe.zzBU, this.zzGd.zzLd.zzHw, this.zzGd.zzLe.zzHT, com_google_android_gms_internal_zzen, null, str, this.zzGd.zzKV, null, this.zzGd.zzLe.zzHU, this.zzGd.zzrp, this.zzGd.zzLe.zzHS, this.zzGd.zzKY, this.zzGd.zzLe.zzHX, this.zzGd.zzLe.zzHY, this.zzGd.zzKT, null, this.zzGd.zzLe.zzIj, this.zzGd.zzLe.zzIk, this.zzGd.zzLe.zzIl, this.zzGd.zzLe.zzIm);
    }

    private zzif zza(String str, zzen com_google_android_gms_internal_zzen) {
        return zza(-2, str, com_google_android_gms_internal_zzen);
    }

    private void zzd(String str, String str2, String str3) {
        synchronized (this.zzpV) {
            zzia zzaw = this.zzKO.zzaw(str);
            if (zzaw == null || zzaw.zzgQ() == null || zzaw.zzgP() == null) {
                return;
            }
            this.zzKL.add(new zzhu(this.mContext, str, this.zzrG, str2, str3, this.zzGd, zzaw, this).zzhn());
            this.zzKM.add(str);
        }
    }

    private zzif zzgO() {
        return zza(3, null, null);
    }

    public void onStop() {
    }

    public void zza(String str, int i) {
    }

    public void zzax(String str) {
        synchronized (this.zzpV) {
            this.zzKN.add(str);
        }
    }

    public void zzbr() {
        for (zzen com_google_android_gms_internal_zzen : this.zzGd.zzKV.zzBO) {
            String str = com_google_android_gms_internal_zzen.zzBG;
            for (String zzd : com_google_android_gms_internal_zzen.zzBB) {
                zzd(zzd, str, com_google_android_gms_internal_zzen.zzBz);
            }
        }
        int i = 0;
        while (i < this.zzKL.size()) {
            try {
                ((Future) this.zzKL.get(i)).get();
                synchronized (this.zzpV) {
                    if (this.zzKN.contains(this.zzKM.get(i))) {
                        com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C06151(this, zza((String) this.zzKM.get(i), (zzen) this.zzGd.zzKV.zzBO.get(i))));
                        return;
                    }
                    i++;
                }
            } catch (InterruptedException e) {
            } catch (Exception e2) {
            }
        }
        com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C06162(this, zzgO()));
    }
}
