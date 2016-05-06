package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzbm;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhd;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzji.zzc;

@zzhb
public abstract class zzd implements com.google.android.gms.ads.internal.request.zzc.zza, zzit<Void> {
    private final zzji<AdRequestInfoParcel> zzHl;
    private final com.google.android.gms.ads.internal.request.zzc.zza zzHm;
    private final Object zzpV;

    /* renamed from: com.google.android.gms.ads.internal.request.zzd.1 */
    class C10801 implements zzc<AdRequestInfoParcel> {
        final /* synthetic */ zzj zzHn;
        final /* synthetic */ zzd zzHo;

        C10801(zzd com_google_android_gms_ads_internal_request_zzd, zzj com_google_android_gms_ads_internal_request_zzj) {
            this.zzHo = com_google_android_gms_ads_internal_request_zzd;
            this.zzHn = com_google_android_gms_ads_internal_request_zzj;
        }

        public void zzc(AdRequestInfoParcel adRequestInfoParcel) {
            if (!this.zzHo.zza(this.zzHn, adRequestInfoParcel)) {
                this.zzHo.zzgr();
            }
        }

        public /* synthetic */ void zze(Object obj) {
            zzc((AdRequestInfoParcel) obj);
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.request.zzd.2 */
    class C10812 implements com.google.android.gms.internal.zzji.zza {
        final /* synthetic */ zzd zzHo;

        C10812(zzd com_google_android_gms_ads_internal_request_zzd) {
            this.zzHo = com_google_android_gms_ads_internal_request_zzd;
        }

        public void run() {
            this.zzHo.zzgr();
        }
    }

    @zzhb
    public static final class zza extends zzd {
        private final Context mContext;

        public zza(Context context, zzji<AdRequestInfoParcel> com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            super(com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.mContext = context;
        }

        public /* synthetic */ Object zzgd() {
            return super.zzga();
        }

        public void zzgr() {
        }

        public zzj zzgs() {
            return zzhd.zza(this.mContext, new zzbm((String) zzbt.zzvB.get()), zzhc.zzgA());
        }
    }

    @zzhb
    public static class zzb extends zzd implements ConnectionCallbacks, OnConnectionFailedListener {
        private Context mContext;
        private zzji<AdRequestInfoParcel> zzHl;
        private final com.google.android.gms.ads.internal.request.zzc.zza zzHm;
        protected zze zzHp;
        private boolean zzHq;
        private VersionInfoParcel zzpT;
        private final Object zzpV;

        public zzb(Context context, VersionInfoParcel versionInfoParcel, zzji<AdRequestInfoParcel> com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            Looper zzhC;
            super(com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.zzpV = new Object();
            this.mContext = context;
            this.zzpT = versionInfoParcel;
            this.zzHl = com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
            this.zzHm = com_google_android_gms_ads_internal_request_zzc_zza;
            if (((Boolean) zzbt.zzwa.get()).booleanValue()) {
                this.zzHq = true;
                zzhC = zzr.zzbO().zzhC();
            } else {
                zzhC = context.getMainLooper();
            }
            this.zzHp = new zze(context, zzhC, this, this, this.zzpT.zzNa);
            connect();
        }

        protected void connect() {
            this.zzHp.zzqG();
        }

        public void onConnected(Bundle connectionHint) {
            zzga();
        }

        public void onConnectionFailed(@NonNull ConnectionResult result) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaI("Cannot connect to remote service, fallback to local instance.");
            zzgt().zzgd();
            Bundle bundle = new Bundle();
            bundle.putString("action", "gms_connection_failed_fallback_to_local");
            zzr.zzbC().zzb(this.mContext, this.zzpT.afmaVersion, "gmob-apps", bundle, true);
        }

        public void onConnectionSuspended(int cause) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaI("Disconnected from remote ad request service.");
        }

        public /* synthetic */ Object zzgd() {
            return super.zzga();
        }

        public void zzgr() {
            synchronized (this.zzpV) {
                if (this.zzHp.isConnected() || this.zzHp.isConnecting()) {
                    this.zzHp.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzHq) {
                    zzr.zzbO().zzhD();
                    this.zzHq = false;
                }
            }
        }

        public zzj zzgs() {
            zzj zzgw;
            synchronized (this.zzpV) {
                try {
                    zzgw = this.zzHp.zzgw();
                } catch (IllegalStateException e) {
                    zzgw = null;
                    return zzgw;
                } catch (DeadObjectException e2) {
                    zzgw = null;
                    return zzgw;
                }
            }
            return zzgw;
        }

        zzit zzgt() {
            return new zza(this.mContext, this.zzHl, this.zzHm);
        }
    }

    public zzd(zzji<AdRequestInfoParcel> com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
        this.zzpV = new Object();
        this.zzHl = com_google_android_gms_internal_zzji_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
        this.zzHm = com_google_android_gms_ads_internal_request_zzc_zza;
    }

    public void cancel() {
        zzgr();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean zza(com.google.android.gms.ads.internal.request.zzj r5, com.google.android.gms.ads.internal.request.AdRequestInfoParcel r6) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        r2 = new com.google.android.gms.ads.internal.request.zzg;	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
        r2.<init>(r4);	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
        r5.zza(r6, r2);	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service.";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r3, r2);
        r3 = com.google.android.gms.ads.internal.zzr.zzbF();
        r3.zzb(r2, r0);
    L_0x0018:
        r0 = r4.zzHm;
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r2.<init>(r1);
        r0.zzb(r2);
        r0 = r1;
        goto L_0x000a;
    L_0x0024:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r3, r2);
        r3 = com.google.android.gms.ads.internal.zzr.zzbF();
        r3.zzb(r2, r0);
        goto L_0x0018;
    L_0x0032:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r3, r2);
        r3 = com.google.android.gms.ads.internal.zzr.zzbF();
        r3.zzb(r2, r0);
        goto L_0x0018;
    L_0x0040:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r3, r2);
        r3 = com.google.android.gms.ads.internal.zzr.zzbF();
        r3.zzb(r2, r0);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.request.zzd.zza(com.google.android.gms.ads.internal.request.zzj, com.google.android.gms.ads.internal.request.AdRequestInfoParcel):boolean");
    }

    public void zzb(AdResponseParcel adResponseParcel) {
        synchronized (this.zzpV) {
            this.zzHm.zzb(adResponseParcel);
            zzgr();
        }
    }

    public Void zzga() {
        zzj zzgs = zzgs();
        if (zzgs == null) {
            this.zzHm.zzb(new AdResponseParcel(0));
            zzgr();
        } else {
            this.zzHl.zza(new C10801(this, zzgs), new C10812(this));
        }
        return null;
    }

    public /* synthetic */ Object zzgd() {
        return zzga();
    }

    public abstract void zzgr();

    public abstract zzj zzgs();
}
