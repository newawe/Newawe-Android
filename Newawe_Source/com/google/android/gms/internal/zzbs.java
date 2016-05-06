package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.zze;
import java.util.concurrent.Callable;

@zzhb
public class zzbs {
    private final Object zzpV;
    private boolean zzqA;
    private SharedPreferences zzvx;

    /* renamed from: com.google.android.gms.internal.zzbs.1 */
    class C05551 implements Callable<T> {
        final /* synthetic */ zzbp zzvy;
        final /* synthetic */ zzbs zzvz;

        C05551(zzbs com_google_android_gms_internal_zzbs, zzbp com_google_android_gms_internal_zzbp) {
            this.zzvz = com_google_android_gms_internal_zzbs;
            this.zzvy = com_google_android_gms_internal_zzbp;
        }

        public T call() {
            return this.zzvy.zza(this.zzvz.zzvx);
        }
    }

    public zzbs() {
        this.zzpV = new Object();
        this.zzqA = false;
        this.zzvx = null;
    }

    public void initialize(Context context) {
        synchronized (this.zzpV) {
            if (this.zzqA) {
                return;
            }
            Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                return;
            }
            this.zzvx = zzr.zzbJ().zzw(remoteContext);
            this.zzqA = true;
        }
    }

    public <T> T zzd(zzbp<T> com_google_android_gms_internal_zzbp_T) {
        synchronized (this.zzpV) {
            if (this.zzqA) {
                return zzjb.zzb(new C05551(this, com_google_android_gms_internal_zzbp_T));
            }
            T zzdq = com_google_android_gms_internal_zzbp_T.zzdq();
            return zzdq;
        }
    }
}
