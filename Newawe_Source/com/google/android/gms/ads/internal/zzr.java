package com.google.android.gms.ads.internal;

import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.ads.internal.request.zza;
import com.google.android.gms.internal.zzbq;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzbw;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhk;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zzix;
import com.google.android.gms.internal.zzjr;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;

@zzhb
public class zzr {
    private static zzr zzqN;
    private static final Object zzqy;
    private final zza zzqO;
    private final com.google.android.gms.ads.internal.overlay.zza zzqP;
    private final zze zzqQ;
    private final zzgr zzqR;
    private final zzir zzqS;
    private final zzjr zzqT;
    private final zzis zzqU;
    private final zzih zzqV;
    private final zzmq zzqW;
    private final zzbw zzqX;
    private final zzhk zzqY;
    private final zzbr zzqZ;
    private final zzbq zzra;
    private final zzbs zzrb;
    private final zzi zzrc;
    private final zzdy zzrd;
    private final zzix zzre;
    private final zzet zzrf;
    private final zzo zzrg;
    private final zzdq zzrh;

    static {
        zzqy = new Object();
        zza(new zzr());
    }

    protected zzr() {
        this.zzqO = new zza();
        this.zzqP = new com.google.android.gms.ads.internal.overlay.zza();
        this.zzqQ = new zze();
        this.zzqR = new zzgr();
        this.zzqS = new zzir();
        this.zzqT = new zzjr();
        this.zzqU = zzis.zzP(VERSION.SDK_INT);
        this.zzqV = new zzih(this.zzqS);
        this.zzqW = new zzmt();
        this.zzqX = new zzbw();
        this.zzqY = new zzhk();
        this.zzqZ = new zzbr();
        this.zzra = new zzbq();
        this.zzrb = new zzbs();
        this.zzrc = new zzi();
        this.zzrd = new zzdy();
        this.zzre = new zzix();
        this.zzrf = new zzet();
        this.zzrg = new zzo();
        this.zzrh = new zzdq();
    }

    protected static void zza(zzr com_google_android_gms_ads_internal_zzr) {
        synchronized (zzqy) {
            zzqN = com_google_android_gms_ads_internal_zzr;
        }
    }

    public static zze zzbA() {
        return zzbx().zzqQ;
    }

    public static zzgr zzbB() {
        return zzbx().zzqR;
    }

    public static zzir zzbC() {
        return zzbx().zzqS;
    }

    public static zzjr zzbD() {
        return zzbx().zzqT;
    }

    public static zzis zzbE() {
        return zzbx().zzqU;
    }

    public static zzih zzbF() {
        return zzbx().zzqV;
    }

    public static zzmq zzbG() {
        return zzbx().zzqW;
    }

    public static zzbw zzbH() {
        return zzbx().zzqX;
    }

    public static zzhk zzbI() {
        return zzbx().zzqY;
    }

    public static zzbr zzbJ() {
        return zzbx().zzqZ;
    }

    public static zzbq zzbK() {
        return zzbx().zzra;
    }

    public static zzbs zzbL() {
        return zzbx().zzrb;
    }

    public static zzi zzbM() {
        return zzbx().zzrc;
    }

    public static zzdy zzbN() {
        return zzbx().zzrd;
    }

    public static zzix zzbO() {
        return zzbx().zzre;
    }

    public static zzet zzbP() {
        return zzbx().zzrf;
    }

    public static zzo zzbQ() {
        return zzbx().zzrg;
    }

    public static zzdq zzbR() {
        return zzbx().zzrh;
    }

    private static zzr zzbx() {
        zzr com_google_android_gms_ads_internal_zzr;
        synchronized (zzqy) {
            com_google_android_gms_ads_internal_zzr = zzqN;
        }
        return com_google_android_gms_ads_internal_zzr;
    }

    public static zza zzby() {
        return zzbx().zzqO;
    }

    public static com.google.android.gms.ads.internal.overlay.zza zzbz() {
        return zzbx().zzqP;
    }
}
