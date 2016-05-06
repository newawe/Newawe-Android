package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzn {
    private static final Object zzqy;
    private static zzn zzur;
    private final zza zzus;
    private final zze zzut;
    private final zzl zzuu;
    private final zzaf zzuv;
    private final zzcv zzuw;
    private final zzf zzux;

    static {
        zzqy = new Object();
        zza(new zzn());
    }

    protected zzn() {
        this.zzus = new zza();
        this.zzut = new zze();
        this.zzuu = new zzl();
        this.zzuv = new zzaf();
        this.zzuw = new zzcv();
        this.zzux = new zzf();
    }

    protected static void zza(zzn com_google_android_gms_ads_internal_client_zzn) {
        synchronized (zzqy) {
            zzur = com_google_android_gms_ads_internal_client_zzn;
        }
    }

    private static zzn zzcR() {
        zzn com_google_android_gms_ads_internal_client_zzn;
        synchronized (zzqy) {
            com_google_android_gms_ads_internal_client_zzn = zzur;
        }
        return com_google_android_gms_ads_internal_client_zzn;
    }

    public static zza zzcS() {
        return zzcR().zzus;
    }

    public static zze zzcT() {
        return zzcR().zzut;
    }

    public static zzl zzcU() {
        return zzcR().zzuu;
    }

    public static zzaf zzcV() {
        return zzcR().zzuv;
    }

    public static zzcv zzcW() {
        return zzcR().zzuw;
    }

    public static zzf zzcX() {
        return zzcR().zzux;
    }
}
