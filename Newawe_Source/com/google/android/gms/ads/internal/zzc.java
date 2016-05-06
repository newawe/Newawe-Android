package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzce;
import com.google.android.gms.internal.zzcf;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzjp;
import java.util.Map;

@zzhb
public abstract class zzc extends zzb implements zzg, zzft {

    /* renamed from: com.google.android.gms.ads.internal.zzc.2 */
    class C05122 implements Runnable {
        final /* synthetic */ zzc zzpr;
        final /* synthetic */ zza zzps;

        C05122(zzc com_google_android_gms_ads_internal_zzc, zza com_google_android_gms_internal_zzif_zza) {
            this.zzpr = com_google_android_gms_ads_internal_zzc;
            this.zzps = com_google_android_gms_internal_zzif_zza;
        }

        public void run() {
            this.zzpr.zzb(new zzif(this.zzps, null, null, null, null, null, null));
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzc.3 */
    class C05153 implements Runnable {
        final /* synthetic */ zzc zzpr;
        final /* synthetic */ zza zzps;
        final /* synthetic */ zzcb zzpt;

        /* renamed from: com.google.android.gms.ads.internal.zzc.3.1 */
        class C05131 implements OnTouchListener {
            final /* synthetic */ zze zzpu;
            final /* synthetic */ C05153 zzpv;

            C05131(C05153 c05153, zze com_google_android_gms_ads_internal_zze) {
                this.zzpv = c05153;
                this.zzpu = com_google_android_gms_ads_internal_zze;
            }

            public boolean onTouch(View v, MotionEvent event) {
                this.zzpu.recordClick();
                return false;
            }
        }

        /* renamed from: com.google.android.gms.ads.internal.zzc.3.2 */
        class C05142 implements OnClickListener {
            final /* synthetic */ zze zzpu;
            final /* synthetic */ C05153 zzpv;

            C05142(C05153 c05153, zze com_google_android_gms_ads_internal_zze) {
                this.zzpv = c05153;
                this.zzpu = com_google_android_gms_ads_internal_zze;
            }

            public void onClick(View v) {
                this.zzpu.recordClick();
            }
        }

        C05153(zzc com_google_android_gms_ads_internal_zzc, zza com_google_android_gms_internal_zzif_zza, zzcb com_google_android_gms_internal_zzcb) {
            this.zzpr = com_google_android_gms_ads_internal_zzc;
            this.zzps = com_google_android_gms_internal_zzif_zza;
            this.zzpt = com_google_android_gms_internal_zzcb;
        }

        public void run() {
            if (this.zzps.zzLe.zzIc && this.zzpr.zzpj.zzrE != null) {
                String str = null;
                if (this.zzps.zzLe.zzEF != null) {
                    str = zzr.zzbC().zzaC(this.zzps.zzLe.zzEF);
                }
                zzce com_google_android_gms_internal_zzcc = new zzcc(this.zzpr, str, this.zzps.zzLe.body);
                this.zzpr.zzpj.zzrL = 1;
                try {
                    this.zzpr.zzph = false;
                    this.zzpr.zzpj.zzrE.zza(com_google_android_gms_internal_zzcc);
                    return;
                } catch (Throwable e) {
                    zzb.zzd("Could not call the onCustomRenderedAdLoadedListener.", e);
                    this.zzpr.zzph = true;
                }
            }
            zze com_google_android_gms_ads_internal_zze = new zze();
            zzjp zza = this.zzpr.zza(this.zzps, com_google_android_gms_ads_internal_zze);
            com_google_android_gms_ads_internal_zze.zza(new zze.zzb(this.zzps, zza));
            zza.setOnTouchListener(new C05131(this, com_google_android_gms_ads_internal_zze));
            zza.setOnClickListener(new C05142(this, com_google_android_gms_ads_internal_zze));
            this.zzpr.zzpj.zzrL = 0;
            this.zzpr.zzpj.zzro = zzr.zzbB().zza(this.zzpr.zzpj.context, this.zzpr, this.zzps, this.zzpr.zzpj.zzrk, zza, this.zzpr.zzpn, this.zzpr, this.zzpt);
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzc.1 */
    class C10841 implements zzdf {
        final /* synthetic */ zzc zzpr;

        C10841(zzc com_google_android_gms_ads_internal_zzc) {
            this.zzpr = com_google_android_gms_ads_internal_zzc;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            if (this.zzpr.zzpj.zzrq != null) {
                this.zzpr.zzpl.zza(this.zzpr.zzpj.zzrp, this.zzpr.zzpj.zzrq, com_google_android_gms_internal_zzjp.getView(), (zzeh) com_google_android_gms_internal_zzjp);
            } else {
                zzb.zzaK("Request to enable ActiveView before adState is available.");
            }
        }
    }

    public zzc(Context context, AdSizeParcel adSizeParcel, String str, zzex com_google_android_gms_internal_zzex, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzex, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    protected zzjp zza(zza com_google_android_gms_internal_zzif_zza, zze com_google_android_gms_ads_internal_zze) {
        zzeh com_google_android_gms_internal_zzeh;
        View nextView = this.zzpj.zzrm.getNextView();
        zzjp com_google_android_gms_internal_zzjp;
        if (nextView instanceof zzjp) {
            zzb.zzaI("Reusing webview...");
            com_google_android_gms_internal_zzjp = (zzjp) nextView;
            com_google_android_gms_internal_zzjp.zza(this.zzpj.context, this.zzpj.zzrp, this.zzpe);
            com_google_android_gms_internal_zzeh = com_google_android_gms_internal_zzjp;
        } else {
            if (nextView != null) {
                this.zzpj.zzrm.removeView(nextView);
            }
            com_google_android_gms_internal_zzjp = zzr.zzbD().zza(this.zzpj.context, this.zzpj.zzrp, false, false, this.zzpj.zzrk, this.zzpj.zzrl, this.zzpe, this.zzpm);
            if (this.zzpj.zzrp.zzuj == null) {
                zzb(com_google_android_gms_internal_zzjp.getView());
            }
            Object obj = com_google_android_gms_internal_zzjp;
        }
        com_google_android_gms_internal_zzeh.zzhU().zzb(this, this, this, this, false, this, null, com_google_android_gms_ads_internal_zze, this);
        zza(com_google_android_gms_internal_zzeh);
        com_google_android_gms_internal_zzeh.zzaM(com_google_android_gms_internal_zzif_zza.zzLd.zzHI);
        return com_google_android_gms_internal_zzeh;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzaS();
    }

    public void zza(zzcf com_google_android_gms_internal_zzcf) {
        zzx.zzcD("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzpj.zzrE = com_google_android_gms_internal_zzcf;
    }

    protected void zza(zzeh com_google_android_gms_internal_zzeh) {
        com_google_android_gms_internal_zzeh.zza("/trackActiveViewUnit", new C10841(this));
    }

    protected void zza(zza com_google_android_gms_internal_zzif_zza, zzcb com_google_android_gms_internal_zzcb) {
        if (com_google_android_gms_internal_zzif_zza.errorCode != -2) {
            zzir.zzMc.post(new C05122(this, com_google_android_gms_internal_zzif_zza));
            return;
        }
        if (com_google_android_gms_internal_zzif_zza.zzrp != null) {
            this.zzpj.zzrp = com_google_android_gms_internal_zzif_zza.zzrp;
        }
        if (!com_google_android_gms_internal_zzif_zza.zzLe.zzHT || com_google_android_gms_internal_zzif_zza.zzLe.zzum) {
            zzir.zzMc.post(new C05153(this, com_google_android_gms_internal_zzif_zza, com_google_android_gms_internal_zzcb));
            return;
        }
        this.zzpj.zzrL = 0;
        this.zzpj.zzro = zzr.zzbB().zza(this.zzpj.context, this, com_google_android_gms_internal_zzif_zza, this.zzpj.zzrk, null, this.zzpn, this, com_google_android_gms_internal_zzcb);
    }

    protected boolean zza(zzif com_google_android_gms_internal_zzif, zzif com_google_android_gms_internal_zzif2) {
        if (this.zzpj.zzbW() && this.zzpj.zzrm != null) {
            this.zzpj.zzrm.zzcc().zzaF(com_google_android_gms_internal_zzif2.zzHY);
        }
        return super.zza(com_google_android_gms_internal_zzif, com_google_android_gms_internal_zzif2);
    }

    public void zzbd() {
        onAdClicked();
    }

    public void zzbe() {
        recordImpression();
        zzaP();
    }

    public void zzbf() {
        zzaQ();
    }

    public void zzc(View view) {
        this.zzpj.zzrK = view;
        zzb(new zzif(this.zzpj.zzrr, null, null, null, null, null, null));
    }
}
