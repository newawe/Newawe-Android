package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzj.zza;
import com.google.android.gms.ads.internal.request.zzk;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzeg.zzd;
import com.google.android.gms.internal.zzji.zzc;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public final class zzhd extends zza {
    private static zzhd zzIQ;
    private static final Object zzqy;
    private final Context mContext;
    private final zzhc zzIR;
    private final zzbm zzIS;
    private final zzeg zzIT;

    /* renamed from: com.google.android.gms.internal.zzhd.1 */
    static class C06071 implements Callable<Void> {
        final /* synthetic */ zzhc zzIU;
        final /* synthetic */ AdRequestInfoParcel zzIV;
        final /* synthetic */ Bundle zzIW;
        final /* synthetic */ Context zzxh;

        C06071(zzhc com_google_android_gms_internal_zzhc, Context context, AdRequestInfoParcel adRequestInfoParcel, Bundle bundle) {
            this.zzIU = com_google_android_gms_internal_zzhc;
            this.zzxh = context;
            this.zzIV = adRequestInfoParcel;
            this.zzIW = bundle;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() throws Exception {
            this.zzIU.zzIP.zza(this.zzxh, this.zzIV.zzHu.packageName, this.zzIW);
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.2 */
    static class C06082 implements Runnable {
        final /* synthetic */ zzeg zzIX;
        final /* synthetic */ zzhf zzIY;
        final /* synthetic */ zzbz zzIZ;
        final /* synthetic */ String zzJa;
        final /* synthetic */ zzcb zzpt;

        /* renamed from: com.google.android.gms.internal.zzhd.2.1 */
        class C11741 implements zzc<zzeh> {
            final /* synthetic */ zzbz zzJb;
            final /* synthetic */ C06082 zzJc;

            C11741(C06082 c06082, zzbz com_google_android_gms_internal_zzbz) {
                this.zzJc = c06082;
                this.zzJb = com_google_android_gms_internal_zzbz;
            }

            public void zzd(zzeh com_google_android_gms_internal_zzeh) {
                this.zzJc.zzpt.zza(this.zzJb, "jsf");
                this.zzJc.zzpt.zzdC();
                com_google_android_gms_internal_zzeh.zza("/invalidRequest", this.zzJc.zzIY.zzJk);
                com_google_android_gms_internal_zzeh.zza("/loadAdURL", this.zzJc.zzIY.zzJl);
                try {
                    com_google_android_gms_internal_zzeh.zze("AFMA_buildAdURL", this.zzJc.zzJa);
                } catch (Throwable e) {
                    zzb.zzb("Error requesting an ad url", e);
                }
            }

            public /* synthetic */ void zze(Object obj) {
                zzd((zzeh) obj);
            }
        }

        /* renamed from: com.google.android.gms.internal.zzhd.2.2 */
        class C11752 implements zzji.zza {
            final /* synthetic */ C06082 zzJc;

            C11752(C06082 c06082) {
                this.zzJc = c06082;
            }

            public void run() {
            }
        }

        C06082(zzeg com_google_android_gms_internal_zzeg, zzhf com_google_android_gms_internal_zzhf, zzcb com_google_android_gms_internal_zzcb, zzbz com_google_android_gms_internal_zzbz, String str) {
            this.zzIX = com_google_android_gms_internal_zzeg;
            this.zzIY = com_google_android_gms_internal_zzhf;
            this.zzpt = com_google_android_gms_internal_zzcb;
            this.zzIZ = com_google_android_gms_internal_zzbz;
            this.zzJa = str;
        }

        public void run() {
            zzd zzer = this.zzIX.zzer();
            this.zzIY.zzb(zzer);
            this.zzpt.zza(this.zzIZ, "rwc");
            zzer.zza(new C11741(this, this.zzpt.zzdB()), new C11752(this));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.3 */
    static class C06093 implements Runnable {
        final /* synthetic */ AdRequestInfoParcel zzIV;
        final /* synthetic */ zzhf zzIY;
        final /* synthetic */ zzbz zzIZ;
        final /* synthetic */ String zzJa;
        final /* synthetic */ zzbm zzJd;
        final /* synthetic */ zzcb zzpt;
        final /* synthetic */ Context zzxh;

        C06093(Context context, AdRequestInfoParcel adRequestInfoParcel, zzhf com_google_android_gms_internal_zzhf, zzcb com_google_android_gms_internal_zzcb, zzbz com_google_android_gms_internal_zzbz, String str, zzbm com_google_android_gms_internal_zzbm) {
            this.zzxh = context;
            this.zzIV = adRequestInfoParcel;
            this.zzIY = com_google_android_gms_internal_zzhf;
            this.zzpt = com_google_android_gms_internal_zzcb;
            this.zzIZ = com_google_android_gms_internal_zzbz;
            this.zzJa = str;
            this.zzJd = com_google_android_gms_internal_zzbm;
        }

        public void run() {
            zzjp zza = zzr.zzbD().zza(this.zzxh, new AdSizeParcel(), false, false, null, this.zzIV.zzrl);
            if (zzr.zzbF().zzhi()) {
                zza.clearCache(true);
            }
            zza.getWebView().setWillNotDraw(true);
            this.zzIY.zzh(zza);
            this.zzpt.zza(this.zzIZ, "rwc");
            zzjq.zza zzb = zzhd.zza(this.zzJa, this.zzpt, this.zzpt.zzdB());
            zzjq zzhU = zza.zzhU();
            zzhU.zza("/invalidRequest", this.zzIY.zzJk);
            zzhU.zza("/loadAdURL", this.zzIY.zzJl);
            zzhU.zza("/log", zzde.zzzf);
            zzhU.zza(zzb);
            zzb.zzaI("Loading the JS library.");
            zza.loadUrl(this.zzJd.zzdp());
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.4 */
    static class C06104 implements Runnable {
        final /* synthetic */ zzhc zzIU;
        final /* synthetic */ AdRequestInfoParcel zzIV;
        final /* synthetic */ zzhf zzIY;
        final /* synthetic */ Context zzxh;

        C06104(zzhc com_google_android_gms_internal_zzhc, Context context, zzhf com_google_android_gms_internal_zzhf, AdRequestInfoParcel adRequestInfoParcel) {
            this.zzIU = com_google_android_gms_internal_zzhc;
            this.zzxh = context;
            this.zzIY = com_google_android_gms_internal_zzhf;
            this.zzIV = adRequestInfoParcel;
        }

        public void run() {
            this.zzIU.zzIJ.zza(this.zzxh, this.zzIY, this.zzIV.zzrl);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.7 */
    class C06117 implements Runnable {
        final /* synthetic */ AdRequestInfoParcel zzIV;
        final /* synthetic */ zzhd zzJe;
        final /* synthetic */ zzk zzJf;

        C06117(zzhd com_google_android_gms_internal_zzhd, AdRequestInfoParcel adRequestInfoParcel, zzk com_google_android_gms_ads_internal_request_zzk) {
            this.zzJe = com_google_android_gms_internal_zzhd;
            this.zzIV = adRequestInfoParcel;
            this.zzJf = com_google_android_gms_ads_internal_request_zzk;
        }

        public void run() {
            AdResponseParcel zzd;
            try {
                zzd = this.zzJe.zzd(this.zzIV);
            } catch (Throwable e) {
                zzr.zzbF().zzb(e, true);
                zzb.zzd("Could not fetch ad response due to an Exception.", e);
                zzd = null;
            }
            if (zzd == null) {
                zzd = new AdResponseParcel(0);
            }
            try {
                this.zzJf.zzb(zzd);
            } catch (Throwable e2) {
                zzb.zzd("Fail to forward ad response.", e2);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.5 */
    static class C11765 implements zzjq.zza {
        final /* synthetic */ String zzJa;
        final /* synthetic */ zzbz zzJb;
        final /* synthetic */ zzcb zzpt;

        C11765(zzcb com_google_android_gms_internal_zzcb, zzbz com_google_android_gms_internal_zzbz, String str) {
            this.zzpt = com_google_android_gms_internal_zzcb;
            this.zzJb = com_google_android_gms_internal_zzbz;
            this.zzJa = str;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, boolean z) {
            this.zzpt.zza(this.zzJb, "jsf");
            this.zzpt.zzdC();
            com_google_android_gms_internal_zzjp.zze("AFMA_buildAdURL", this.zzJa);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzhd.6 */
    class C11776 implements zzeg.zzb<zzed> {
        final /* synthetic */ zzhd zzJe;

        C11776(zzhd com_google_android_gms_internal_zzhd) {
            this.zzJe = com_google_android_gms_internal_zzhd;
        }

        public void zza(zzed com_google_android_gms_internal_zzed) {
            com_google_android_gms_internal_zzed.zza("/log", zzde.zzzf);
        }

        public /* synthetic */ void zze(Object obj) {
            zza((zzed) obj);
        }
    }

    static {
        zzqy = new Object();
    }

    zzhd(Context context, zzbm com_google_android_gms_internal_zzbm, zzhc com_google_android_gms_internal_zzhc) {
        this.mContext = context;
        this.zzIR = com_google_android_gms_internal_zzhc;
        this.zzIS = com_google_android_gms_internal_zzbm;
        this.zzIT = new zzeg(context.getApplicationContext() != null ? context.getApplicationContext() : context, new VersionInfoParcel(8487000, 8487000, true), com_google_android_gms_internal_zzbm.zzdp(), new C11776(this), new zzeg.zzc());
    }

    private static AdResponseParcel zza(Context context, zzeg com_google_android_gms_internal_zzeg, zzbm com_google_android_gms_internal_zzbm, zzhc com_google_android_gms_internal_zzhc, AdRequestInfoParcel adRequestInfoParcel) {
        Bundle bundle;
        Future future;
        Throwable e;
        zzb.zzaI("Starting ad request from service.");
        zzbt.initialize(context);
        zzcb com_google_android_gms_internal_zzcb = new zzcb(((Boolean) zzbt.zzwg.get()).booleanValue(), "load_ad", adRequestInfoParcel.zzrp.zzuh);
        if (adRequestInfoParcel.versionCode > 10 && adRequestInfoParcel.zzHL != -1) {
            com_google_android_gms_internal_zzcb.zza(com_google_android_gms_internal_zzcb.zzb(adRequestInfoParcel.zzHL), "cts");
        }
        zzbz zzdB = com_google_android_gms_internal_zzcb.zzdB();
        Bundle bundle2 = (adRequestInfoParcel.versionCode < 4 || adRequestInfoParcel.zzHA == null) ? null : adRequestInfoParcel.zzHA;
        if (!((Boolean) zzbt.zzwp.get()).booleanValue() || com_google_android_gms_internal_zzhc.zzIP == null) {
            bundle = bundle2;
            future = null;
        } else {
            if (bundle2 == null && ((Boolean) zzbt.zzwq.get()).booleanValue()) {
                zzin.m4114v("contentInfo is not present, but we'll still launch the app index task");
                bundle2 = new Bundle();
            }
            if (bundle2 != null) {
                bundle = bundle2;
                future = zziq.zza(new C06071(com_google_android_gms_internal_zzhc, context, adRequestInfoParcel, bundle2));
            } else {
                bundle = bundle2;
                future = null;
            }
        }
        com_google_android_gms_internal_zzhc.zzIK.zzex();
        zzhj zzE = zzr.zzbI().zzE(context);
        if (zzE.zzKc == -1) {
            zzb.zzaI("Device is offline.");
            return new AdResponseParcel(2);
        }
        String uuid = adRequestInfoParcel.versionCode >= 7 ? adRequestInfoParcel.zzHI : UUID.randomUUID().toString();
        zzhf com_google_android_gms_internal_zzhf = new zzhf(uuid, adRequestInfoParcel.applicationInfo.packageName);
        if (adRequestInfoParcel.zzHt.extras != null) {
            String string = adRequestInfoParcel.zzHt.extras.getString("_ad");
            if (string != null) {
                return zzhe.zza(context, adRequestInfoParcel, string);
            }
        }
        Location zzd = com_google_android_gms_internal_zzhc.zzIK.zzd(250);
        String token = com_google_android_gms_internal_zzhc.zzIL.getToken(context, adRequestInfoParcel.zzrj, adRequestInfoParcel.zzHu.packageName);
        List zza = com_google_android_gms_internal_zzhc.zzII.zza(adRequestInfoParcel);
        String zzf = com_google_android_gms_internal_zzhc.zzIM.zzf(adRequestInfoParcel);
        zzhn.zza zzF = com_google_android_gms_internal_zzhc.zzIN.zzF(context);
        if (future != null) {
            try {
                zzin.m4114v("Waiting for app index fetching task.");
                future.get(((Long) zzbt.zzwr.get()).longValue(), TimeUnit.MILLISECONDS);
                zzin.m4114v("App index fetching task completed.");
            } catch (ExecutionException e2) {
                e = e2;
                zzb.zzd("Failed to fetch app index signal", e);
            } catch (InterruptedException e3) {
                e = e3;
                zzb.zzd("Failed to fetch app index signal", e);
            } catch (TimeoutException e4) {
                zzb.zzaI("Timed out waiting for app index fetching task");
            }
        }
        JSONObject zza2 = zzhe.zza(context, adRequestInfoParcel, zzE, zzF, zzd, com_google_android_gms_internal_zzbm, token, zzf, zza, bundle);
        if (adRequestInfoParcel.versionCode < 7) {
            try {
                zza2.put("request_id", uuid);
            } catch (JSONException e5) {
            }
        }
        if (zza2 == null) {
            return new AdResponseParcel(0);
        }
        String jSONObject = zza2.toString();
        com_google_android_gms_internal_zzcb.zza(zzdB, "arc");
        zzbz zzdB2 = com_google_android_gms_internal_zzcb.zzdB();
        if (((Boolean) zzbt.zzvC.get()).booleanValue()) {
            zzir.zzMc.post(new C06082(com_google_android_gms_internal_zzeg, com_google_android_gms_internal_zzhf, com_google_android_gms_internal_zzcb, zzdB2, jSONObject));
        } else {
            zzir.zzMc.post(new C06093(context, adRequestInfoParcel, com_google_android_gms_internal_zzhf, com_google_android_gms_internal_zzcb, zzdB2, jSONObject, com_google_android_gms_internal_zzbm));
        }
        AdResponseParcel adResponseParcel;
        try {
            zzhi com_google_android_gms_internal_zzhi = (zzhi) com_google_android_gms_internal_zzhf.zzgC().get(10, TimeUnit.SECONDS);
            if (com_google_android_gms_internal_zzhi == null) {
                adResponseParcel = new AdResponseParcel(0);
                return adResponseParcel;
            } else if (com_google_android_gms_internal_zzhi.getErrorCode() != -2) {
                adResponseParcel = new AdResponseParcel(com_google_android_gms_internal_zzhi.getErrorCode());
                zzir.zzMc.post(new C06104(com_google_android_gms_internal_zzhc, context, com_google_android_gms_internal_zzhf, adRequestInfoParcel));
                return adResponseParcel;
            } else {
                if (com_google_android_gms_internal_zzcb.zzdE() != null) {
                    com_google_android_gms_internal_zzcb.zza(com_google_android_gms_internal_zzcb.zzdE(), "rur");
                }
                String str = null;
                if (com_google_android_gms_internal_zzhi.zzgG()) {
                    str = com_google_android_gms_internal_zzhc.zzIH.zzaz(adRequestInfoParcel.zzHu.packageName);
                }
                adResponseParcel = zza(adRequestInfoParcel, context, adRequestInfoParcel.zzrl.afmaVersion, com_google_android_gms_internal_zzhi.getUrl(), str, com_google_android_gms_internal_zzhi.zzgH() ? token : null, com_google_android_gms_internal_zzhi, com_google_android_gms_internal_zzcb, com_google_android_gms_internal_zzhc);
                if (adResponseParcel.zzIf == 1) {
                    com_google_android_gms_internal_zzhc.zzIL.clearToken(context, adRequestInfoParcel.zzHu.packageName);
                }
                com_google_android_gms_internal_zzcb.zza(zzdB, "tts");
                adResponseParcel.zzIh = com_google_android_gms_internal_zzcb.zzdD();
                zzir.zzMc.post(new C06104(com_google_android_gms_internal_zzhc, context, com_google_android_gms_internal_zzhf, adRequestInfoParcel));
                return adResponseParcel;
            }
        } catch (Exception e6) {
            adResponseParcel = new AdResponseParcel(0);
            return adResponseParcel;
        } finally {
            zzir.zzMc.post(new C06104(com_google_android_gms_internal_zzhc, context, com_google_android_gms_internal_zzhf, adRequestInfoParcel));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.ads.internal.request.AdResponseParcel zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, com.google.android.gms.internal.zzhi r19, com.google.android.gms.internal.zzcb r20, com.google.android.gms.internal.zzhc r21) {
        /*
        if (r20 == 0) goto L_0x00f6;
    L_0x0002:
        r2 = r20.zzdB();
        r3 = r2;
    L_0x0007:
        r8 = new com.google.android.gms.internal.zzhg;	 Catch:{ IOException -> 0x010e }
        r8.<init>(r13);	 Catch:{ IOException -> 0x010e }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x010e }
        r2.<init>();	 Catch:{ IOException -> 0x010e }
        r4 = "AdRequestServiceImpl: Sending request: ";
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x010e }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x010e }
        r2 = r2.toString();	 Catch:{ IOException -> 0x010e }
        com.google.android.gms.ads.internal.util.client.zzb.zzaI(r2);	 Catch:{ IOException -> 0x010e }
        r4 = new java.net.URL;	 Catch:{ IOException -> 0x010e }
        r0 = r16;
        r4.<init>(r0);	 Catch:{ IOException -> 0x010e }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzr.zzbG();	 Catch:{ IOException -> 0x010e }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x010e }
        r6 = r2;
        r7 = r4;
    L_0x0036:
        if (r21 == 0) goto L_0x003f;
    L_0x0038:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgJ();	 Catch:{ IOException -> 0x010e }
    L_0x003f:
        r2 = r7.openConnection();	 Catch:{ IOException -> 0x010e }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x010e }
        r4 = com.google.android.gms.ads.internal.zzr.zzbC();	 Catch:{ all -> 0x0100 }
        r5 = 0;
        r4.zza(r14, r15, r5, r2);	 Catch:{ all -> 0x0100 }
        r4 = android.text.TextUtils.isEmpty(r17);	 Catch:{ all -> 0x0100 }
        if (r4 != 0) goto L_0x005a;
    L_0x0053:
        r4 = "x-afma-drt-cookie";
        r0 = r17;
        r2.addRequestProperty(r4, r0);	 Catch:{ all -> 0x0100 }
    L_0x005a:
        r4 = android.text.TextUtils.isEmpty(r18);	 Catch:{ all -> 0x0100 }
        if (r4 != 0) goto L_0x007a;
    L_0x0060:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0100 }
        r4.<init>();	 Catch:{ all -> 0x0100 }
        r5 = "Bearer ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0100 }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x0100 }
        r4 = r4.toString();	 Catch:{ all -> 0x0100 }
        r5 = "Authorization";
        r2.addRequestProperty(r5, r4);	 Catch:{ all -> 0x0100 }
    L_0x007a:
        if (r19 == 0) goto L_0x00a6;
    L_0x007c:
        r4 = r19.zzgF();	 Catch:{ all -> 0x0100 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0100 }
        if (r4 != 0) goto L_0x00a6;
    L_0x0086:
        r4 = 1;
        r2.setDoOutput(r4);	 Catch:{ all -> 0x0100 }
        r4 = r19.zzgF();	 Catch:{ all -> 0x0100 }
        r9 = r4.getBytes();	 Catch:{ all -> 0x0100 }
        r4 = r9.length;	 Catch:{ all -> 0x0100 }
        r2.setFixedLengthStreamingMode(r4);	 Catch:{ all -> 0x0100 }
        r5 = 0;
        r4 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x00fa }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x00fa }
        r4.<init>(r12);	 Catch:{ all -> 0x00fa }
        r4.write(r9);	 Catch:{ all -> 0x01d0 }
        com.google.android.gms.internal.zzna.zzb(r4);	 Catch:{ all -> 0x0100 }
    L_0x00a6:
        r9 = r2.getResponseCode();	 Catch:{ all -> 0x0100 }
        r12 = r2.getHeaderFields();	 Catch:{ all -> 0x0100 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r4) goto L_0x0136;
    L_0x00b2:
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r4) goto L_0x0136;
    L_0x00b6:
        r6 = r7.toString();	 Catch:{ all -> 0x0100 }
        r5 = 0;
        r4 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0130 }
        r7 = r2.getInputStream();	 Catch:{ all -> 0x0130 }
        r4.<init>(r7);	 Catch:{ all -> 0x0130 }
        r5 = com.google.android.gms.ads.internal.zzr.zzbC();	 Catch:{ all -> 0x01cd }
        r5 = r5.zza(r4);	 Catch:{ all -> 0x01cd }
        com.google.android.gms.internal.zzna.zzb(r4);	 Catch:{ all -> 0x0100 }
        zza(r6, r12, r5, r9);	 Catch:{ all -> 0x0100 }
        r8.zzb(r6, r12, r5);	 Catch:{ all -> 0x0100 }
        if (r20 == 0) goto L_0x00e4;
    L_0x00d7:
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x0100 }
        r5 = 0;
        r6 = "ufe";
        r4[r5] = r6;	 Catch:{ all -> 0x0100 }
        r0 = r20;
        r0.zza(r3, r4);	 Catch:{ all -> 0x0100 }
    L_0x00e4:
        r3 = r8.zzj(r10);	 Catch:{ all -> 0x0100 }
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x00f4;
    L_0x00ed:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x00f4:
        r2 = r3;
    L_0x00f5:
        return r2;
    L_0x00f6:
        r2 = 0;
        r3 = r2;
        goto L_0x0007;
    L_0x00fa:
        r3 = move-exception;
        r4 = r5;
    L_0x00fc:
        com.google.android.gms.internal.zzna.zzb(r4);	 Catch:{ all -> 0x0100 }
        throw r3;	 Catch:{ all -> 0x0100 }
    L_0x0100:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x010d;
    L_0x0106:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x010d:
        throw r3;	 Catch:{ IOException -> 0x010e }
    L_0x010e:
        r2 = move-exception;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Error while connecting to ad server: ";
        r3 = r3.append(r4);
        r2 = r2.getMessage();
        r2 = r3.append(r2);
        r2 = r2.toString();
        com.google.android.gms.ads.internal.util.client.zzb.zzaK(r2);
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x00f5;
    L_0x0130:
        r3 = move-exception;
        r4 = r5;
    L_0x0132:
        com.google.android.gms.internal.zzna.zzb(r4);	 Catch:{ all -> 0x0100 }
        throw r3;	 Catch:{ all -> 0x0100 }
    L_0x0136:
        r4 = r7.toString();	 Catch:{ all -> 0x0100 }
        r5 = 0;
        zza(r4, r12, r5, r9);	 Catch:{ all -> 0x0100 }
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r4) goto L_0x018f;
    L_0x0142:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r4) goto L_0x018f;
    L_0x0146:
        r4 = "Location";
        r4 = r2.getHeaderField(r4);	 Catch:{ all -> 0x0100 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0100 }
        if (r5 == 0) goto L_0x016b;
    L_0x0152:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.ads.internal.util.client.zzb.zzaK(r3);	 Catch:{ all -> 0x0100 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0100 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0100 }
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x0169;
    L_0x0162:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x0169:
        r2 = r3;
        goto L_0x00f5;
    L_0x016b:
        r5 = new java.net.URL;	 Catch:{ all -> 0x0100 }
        r5.<init>(r4);	 Catch:{ all -> 0x0100 }
        r4 = r6 + 1;
        r6 = 5;
        if (r4 <= r6) goto L_0x01ba;
    L_0x0175:
        r3 = "Too many redirects.";
        com.google.android.gms.ads.internal.util.client.zzb.zzaK(r3);	 Catch:{ all -> 0x0100 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0100 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0100 }
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x018c;
    L_0x0185:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x018c:
        r2 = r3;
        goto L_0x00f5;
    L_0x018f:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0100 }
        r3.<init>();	 Catch:{ all -> 0x0100 }
        r4 = "Received error HTTP response code: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0100 }
        r3 = r3.append(r9);	 Catch:{ all -> 0x0100 }
        r3 = r3.toString();	 Catch:{ all -> 0x0100 }
        com.google.android.gms.ads.internal.util.client.zzb.zzaK(r3);	 Catch:{ all -> 0x0100 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0100 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0100 }
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x01b7;
    L_0x01b0:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x01b7:
        r2 = r3;
        goto L_0x00f5;
    L_0x01ba:
        r8.zzj(r12);	 Catch:{ all -> 0x0100 }
        r2.disconnect();	 Catch:{ IOException -> 0x010e }
        if (r21 == 0) goto L_0x01c9;
    L_0x01c2:
        r0 = r21;
        r2 = r0.zzIO;	 Catch:{ IOException -> 0x010e }
        r2.zzgK();	 Catch:{ IOException -> 0x010e }
    L_0x01c9:
        r6 = r4;
        r7 = r5;
        goto L_0x0036;
    L_0x01cd:
        r3 = move-exception;
        goto L_0x0132;
    L_0x01d0:
        r3 = move-exception;
        goto L_0x00fc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhd.zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zzhi, com.google.android.gms.internal.zzcb, com.google.android.gms.internal.zzhc):com.google.android.gms.ads.internal.request.AdResponseParcel");
    }

    public static zzhd zza(Context context, zzbm com_google_android_gms_internal_zzbm, zzhc com_google_android_gms_internal_zzhc) {
        zzhd com_google_android_gms_internal_zzhd;
        synchronized (zzqy) {
            if (zzIQ == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzIQ = new zzhd(context, com_google_android_gms_internal_zzbm, com_google_android_gms_internal_zzhc);
            }
            com_google_android_gms_internal_zzhd = zzIQ;
        }
        return com_google_android_gms_internal_zzhd;
    }

    private static zzjq.zza zza(String str, zzcb com_google_android_gms_internal_zzcb, zzbz com_google_android_gms_internal_zzbz) {
        return new C11765(com_google_android_gms_internal_zzcb, com_google_android_gms_internal_zzbz, str);
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzb.zzQ(2)) {
            zzin.m4114v("Http Response: {\n  URL:\n    " + str + "\n  Headers:");
            if (map != null) {
                for (String str3 : map.keySet()) {
                    zzin.m4114v("    " + str3 + ":");
                    for (String str32 : (List) map.get(str32)) {
                        zzin.m4114v("      " + str32);
                    }
                }
            }
            zzin.m4114v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += DateUtils.MILLIS_IN_SECOND) {
                    zzin.m4114v(str2.substring(i2, Math.min(str2.length(), i2 + DateUtils.MILLIS_IN_SECOND)));
                }
            } else {
                zzin.m4114v("    null");
            }
            zzin.m4114v("  Response Code:\n    " + i + "\n}");
        }
    }

    public void zza(AdRequestInfoParcel adRequestInfoParcel, zzk com_google_android_gms_ads_internal_request_zzk) {
        zzr.zzbF().zzb(this.mContext, adRequestInfoParcel.zzrl);
        zziq.zza(new C06117(this, adRequestInfoParcel, com_google_android_gms_ads_internal_request_zzk));
    }

    public AdResponseParcel zzd(AdRequestInfoParcel adRequestInfoParcel) {
        return zza(this.mContext, this.zzIT, this.zzIS, this.zzIR, adRequestInfoParcel);
    }
}
