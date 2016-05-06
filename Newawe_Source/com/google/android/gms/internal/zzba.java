package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzeg.zzd;
import com.google.android.gms.internal.zzji.zza;
import com.google.android.gms.internal.zzji.zzc;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzba extends zzau {
    private zzd zzsF;
    private boolean zzsG;

    /* renamed from: com.google.android.gms.internal.zzba.1 */
    class C11191 implements zzc<zzeh> {
        final /* synthetic */ JSONObject zzsH;
        final /* synthetic */ zzba zzsI;

        C11191(zzba com_google_android_gms_internal_zzba, JSONObject jSONObject) {
            this.zzsI = com_google_android_gms_internal_zzba;
            this.zzsH = jSONObject;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zza(this.zzsH);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzba.2 */
    class C11202 implements zza {
        final /* synthetic */ zzba zzsI;

        C11202(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void run() {
        }
    }

    /* renamed from: com.google.android.gms.internal.zzba.3 */
    class C11213 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;

        C11213(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zzsG = true;
            this.zzsI.zzb(com_google_android_gms_internal_zzeh);
            this.zzsI.zzcd();
            this.zzsI.zzh(false);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzba.4 */
    class C11224 implements zza {
        final /* synthetic */ zzba zzsI;

        C11224(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void run() {
            this.zzsI.destroy();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzba.5 */
    class C11235 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;
        final /* synthetic */ JSONObject zzsJ;

        C11235(zzba com_google_android_gms_internal_zzba, JSONObject jSONObject) {
            this.zzsI = com_google_android_gms_internal_zzba;
            this.zzsJ = jSONObject;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            com_google_android_gms_internal_zzeh.zza("AFMA_updateActiveView", this.zzsJ);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzba.6 */
    class C11246 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;

        C11246(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zzc(com_google_android_gms_internal_zzeh);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    public zzba(Context context, AdSizeParcel adSizeParcel, zzif com_google_android_gms_internal_zzif, VersionInfoParcel versionInfoParcel, zzbb com_google_android_gms_internal_zzbb, zzeg com_google_android_gms_internal_zzeg) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzif, versionInfoParcel, com_google_android_gms_internal_zzbb);
        this.zzsF = com_google_android_gms_internal_zzeg.zzer();
        try {
            this.zzsF.zza(new C11191(this, zzd(com_google_android_gms_internal_zzbb.zzcq().zzco())), new C11202(this));
        } catch (JSONException e) {
        } catch (Throwable e2) {
            zzb.zzb("Failure while processing active view data.", e2);
        }
        this.zzsF.zza(new C11213(this), new C11224(this));
        zzb.zzaI("Tracking ad unit: " + this.zzrZ.zzcu());
    }

    protected void destroy() {
        synchronized (this.zzpV) {
            super.destroy();
            this.zzsF.zza(new C11246(this), new zzji.zzb());
            this.zzsF.release();
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zzsF.zza(new C11235(this, jSONObject), new zzji.zzb());
    }

    protected boolean zzcl() {
        return this.zzsG;
    }
}
