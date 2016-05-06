package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.concurrent.Future;

@zzhb
public class zzee {

    /* renamed from: com.google.android.gms.internal.zzee.1 */
    class C05671 implements Runnable {
        final /* synthetic */ VersionInfoParcel zzAM;
        final /* synthetic */ zza zzAN;
        final /* synthetic */ zzan zzAO;
        final /* synthetic */ String zzAP;
        final /* synthetic */ zzee zzAQ;
        final /* synthetic */ Context zzxh;

        C05671(zzee com_google_android_gms_internal_zzee, Context context, VersionInfoParcel versionInfoParcel, zza com_google_android_gms_internal_zzee_zza, zzan com_google_android_gms_internal_zzan, String str) {
            this.zzAQ = com_google_android_gms_internal_zzee;
            this.zzxh = context;
            this.zzAM = versionInfoParcel;
            this.zzAN = com_google_android_gms_internal_zzee_zza;
            this.zzAO = com_google_android_gms_internal_zzan;
            this.zzAP = str;
        }

        public void run() {
            this.zzAQ.zza(this.zzxh, this.zzAM, this.zzAN, this.zzAO).zzaa(this.zzAP);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzee.2 */
    class C11572 implements com.google.android.gms.internal.zzed.zza {
        final /* synthetic */ zza zzAN;
        final /* synthetic */ zzee zzAQ;

        C11572(zzee com_google_android_gms_internal_zzee, zza com_google_android_gms_internal_zzee_zza) {
            this.zzAQ = com_google_android_gms_internal_zzee;
            this.zzAN = com_google_android_gms_internal_zzee_zza;
        }

        public void zzeo() {
            this.zzAN.zzg(this.zzAN.zzAR);
        }
    }

    private static class zza<JavascriptEngine> extends zzjd<JavascriptEngine> {
        JavascriptEngine zzAR;

        private zza() {
        }
    }

    private zzed zza(Context context, VersionInfoParcel versionInfoParcel, zza<zzed> com_google_android_gms_internal_zzee_zza_com_google_android_gms_internal_zzed, zzan com_google_android_gms_internal_zzan) {
        zzed com_google_android_gms_internal_zzef = new zzef(context, versionInfoParcel, com_google_android_gms_internal_zzan);
        com_google_android_gms_internal_zzee_zza_com_google_android_gms_internal_zzed.zzAR = com_google_android_gms_internal_zzef;
        com_google_android_gms_internal_zzef.zza(new C11572(this, com_google_android_gms_internal_zzee_zza_com_google_android_gms_internal_zzed));
        return com_google_android_gms_internal_zzef;
    }

    public Future<zzed> zza(Context context, VersionInfoParcel versionInfoParcel, String str, zzan com_google_android_gms_internal_zzan) {
        Future com_google_android_gms_internal_zzee_zza = new zza();
        zzir.zzMc.post(new C05671(this, context, versionInfoParcel, com_google_android_gms_internal_zzee_zza, com_google_android_gms_internal_zzan, str));
        return com_google_android_gms_internal_zzee_zza;
    }
}
