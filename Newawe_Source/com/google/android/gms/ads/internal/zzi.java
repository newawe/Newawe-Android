package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr.zza;
import com.google.android.gms.ads.internal.client.zzx;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcs;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzir;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzhb
public class zzi extends zza {
    private final Context mContext;
    private final zzq zzpK;
    private final zzcr zzpL;
    private final zzcs zzpM;
    private final SimpleArrayMap<String, zzcu> zzpN;
    private final SimpleArrayMap<String, zzct> zzpO;
    private final NativeAdOptionsParcel zzpP;
    private final List<String> zzpQ;
    private final zzx zzpR;
    private final String zzpS;
    private final VersionInfoParcel zzpT;
    private WeakReference<zzp> zzpU;
    private final Object zzpV;
    private final zzd zzpm;
    private final zzex zzpn;

    /* renamed from: com.google.android.gms.ads.internal.zzi.1 */
    class C05161 implements Runnable {
        final /* synthetic */ AdRequestParcel zzpW;
        final /* synthetic */ zzi zzpX;

        C05161(zzi com_google_android_gms_ads_internal_zzi, AdRequestParcel adRequestParcel) {
            this.zzpX = com_google_android_gms_ads_internal_zzi;
            this.zzpW = adRequestParcel;
        }

        public void run() {
            synchronized (this.zzpX.zzpV) {
                zzp zzbm = this.zzpX.zzbm();
                this.zzpX.zzpU = new WeakReference(zzbm);
                zzbm.zzb(this.zzpX.zzpL);
                zzbm.zzb(this.zzpX.zzpM);
                zzbm.zza(this.zzpX.zzpN);
                zzbm.zza(this.zzpX.zzpK);
                zzbm.zzb(this.zzpX.zzpO);
                zzbm.zza(this.zzpX.zzbl());
                zzbm.zzb(this.zzpX.zzpP);
                zzbm.zza(this.zzpX.zzpR);
                zzbm.zzb(this.zzpW);
            }
        }
    }

    zzi(Context context, String str, zzex com_google_android_gms_internal_zzex, VersionInfoParcel versionInfoParcel, zzq com_google_android_gms_ads_internal_client_zzq, zzcr com_google_android_gms_internal_zzcr, zzcs com_google_android_gms_internal_zzcs, SimpleArrayMap<String, zzcu> simpleArrayMap, SimpleArrayMap<String, zzct> simpleArrayMap2, NativeAdOptionsParcel nativeAdOptionsParcel, zzx com_google_android_gms_ads_internal_client_zzx, zzd com_google_android_gms_ads_internal_zzd) {
        this.zzpV = new Object();
        this.mContext = context;
        this.zzpS = str;
        this.zzpn = com_google_android_gms_internal_zzex;
        this.zzpT = versionInfoParcel;
        this.zzpK = com_google_android_gms_ads_internal_client_zzq;
        this.zzpM = com_google_android_gms_internal_zzcs;
        this.zzpL = com_google_android_gms_internal_zzcr;
        this.zzpN = simpleArrayMap;
        this.zzpO = simpleArrayMap2;
        this.zzpP = nativeAdOptionsParcel;
        this.zzpQ = zzbl();
        this.zzpR = com_google_android_gms_ads_internal_client_zzx;
        this.zzpm = com_google_android_gms_ads_internal_zzd;
    }

    private List<String> zzbl() {
        List<String> arrayList = new ArrayList();
        if (this.zzpM != null) {
            arrayList.add(SchemaSymbols.ATTVAL_TRUE_1);
        }
        if (this.zzpL != null) {
            arrayList.add("2");
        }
        if (this.zzpN.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    public String getMediationAdapterClassName() {
        synchronized (this.zzpV) {
            if (this.zzpU != null) {
                zzp com_google_android_gms_ads_internal_zzp = (zzp) this.zzpU.get();
                String mediationAdapterClassName = com_google_android_gms_ads_internal_zzp != null ? com_google_android_gms_ads_internal_zzp.getMediationAdapterClassName() : null;
                return mediationAdapterClassName;
            }
            return null;
        }
    }

    public boolean isLoading() {
        synchronized (this.zzpV) {
            if (this.zzpU != null) {
                zzp com_google_android_gms_ads_internal_zzp = (zzp) this.zzpU.get();
                boolean isLoading = com_google_android_gms_ads_internal_zzp != null ? com_google_android_gms_ads_internal_zzp.isLoading() : false;
                return isLoading;
            }
            return false;
        }
    }

    protected void runOnUiThread(Runnable runnable) {
        zzir.zzMc.post(runnable);
    }

    protected zzp zzbm() {
        return new zzp(this.mContext, this.zzpm, AdSizeParcel.zzt(this.mContext), this.zzpS, this.zzpn, this.zzpT);
    }

    public void zzf(AdRequestParcel adRequestParcel) {
        runOnUiThread(new C05161(this, adRequestParcel));
    }
}
