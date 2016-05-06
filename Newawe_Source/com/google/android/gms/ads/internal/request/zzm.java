package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzbm;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzeg.zzd;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhe;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzjp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzm extends zzim {
    private static zzdk zzIA;
    private static zzdf zzIB;
    static final long zzIw;
    private static boolean zzIx;
    private static zzeg zzIy;
    private static zzdg zzIz;
    private static final Object zzqy;
    private final Context mContext;
    private final Object zzGg;
    private final com.google.android.gms.ads.internal.request.zza.zza zzHg;
    private final com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza zzHh;
    private zzd zzIC;

    /* renamed from: com.google.android.gms.ads.internal.request.zzm.1 */
    class C05081 implements Runnable {
        final /* synthetic */ zzm zzID;
        final /* synthetic */ com.google.android.gms.internal.zzif.zza zzps;

        C05081(zzm com_google_android_gms_ads_internal_request_zzm, com.google.android.gms.internal.zzif.zza com_google_android_gms_internal_zzif_zza) {
            this.zzID = com_google_android_gms_ads_internal_request_zzm;
            this.zzps = com_google_android_gms_internal_zzif_zza;
        }

        public void run() {
            this.zzID.zzHg.zza(this.zzps);
            if (this.zzID.zzIC != null) {
                this.zzID.zzIC.release();
                this.zzID.zzIC = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.request.zzm.2 */
    class C05092 implements Runnable {
        final /* synthetic */ zzm zzID;
        final /* synthetic */ JSONObject zzIE;
        final /* synthetic */ String zzIF;

        /* renamed from: com.google.android.gms.ads.internal.request.zzm.2.1 */
        class C10821 implements com.google.android.gms.internal.zzji.zzc<zzeh> {
            final /* synthetic */ C05092 zzIG;

            C10821(C05092 c05092) {
                this.zzIG = c05092;
            }

            public void zzd(zzeh com_google_android_gms_internal_zzeh) {
                try {
                    com_google_android_gms_internal_zzeh.zza("AFMA_getAdapterLessMediationAd", this.zzIG.zzIE);
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Error requesting an ad url", e);
                    zzm.zzIA.zzS(this.zzIG.zzIF);
                }
            }

            public /* synthetic */ void zze(Object obj) {
                zzd((zzeh) obj);
            }
        }

        /* renamed from: com.google.android.gms.ads.internal.request.zzm.2.2 */
        class C10832 implements com.google.android.gms.internal.zzji.zza {
            final /* synthetic */ C05092 zzIG;

            C10832(C05092 c05092) {
                this.zzIG = c05092;
            }

            public void run() {
                zzm.zzIA.zzS(this.zzIG.zzIF);
            }
        }

        C05092(zzm com_google_android_gms_ads_internal_request_zzm, JSONObject jSONObject, String str) {
            this.zzID = com_google_android_gms_ads_internal_request_zzm;
            this.zzIE = jSONObject;
            this.zzIF = str;
        }

        public void run() {
            this.zzID.zzIC = zzm.zzIy.zzer();
            this.zzID.zzIC.zza(new C10821(this), new C10832(this));
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.request.zzm.3 */
    class C05103 implements Runnable {
        final /* synthetic */ zzm zzID;

        C05103(zzm com_google_android_gms_ads_internal_request_zzm) {
            this.zzID = com_google_android_gms_ads_internal_request_zzm;
        }

        public void run() {
            if (this.zzID.zzIC != null) {
                this.zzID.zzIC.release();
                this.zzID.zzIC = null;
            }
        }
    }

    public static class zza implements com.google.android.gms.internal.zzeg.zzb<zzed> {
        public void zza(zzed com_google_android_gms_internal_zzed) {
            zzm.zzd(com_google_android_gms_internal_zzed);
        }

        public /* synthetic */ void zze(Object obj) {
            zza((zzed) obj);
        }
    }

    public static class zzb implements com.google.android.gms.internal.zzeg.zzb<zzed> {
        public void zza(zzed com_google_android_gms_internal_zzed) {
            zzm.zzc(com_google_android_gms_internal_zzed);
        }

        public /* synthetic */ void zze(Object obj) {
            zza((zzed) obj);
        }
    }

    public static class zzc implements zzdf {
        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            String str = (String) map.get("request_id");
            com.google.android.gms.ads.internal.util.client.zzb.zzaK("Invalid request: " + ((String) map.get("errors")));
            zzm.zzIA.zzS(str);
        }
    }

    static {
        zzIw = TimeUnit.SECONDS.toMillis(10);
        zzqy = new Object();
        zzIx = false;
        zzIy = null;
        zzIz = null;
        zzIA = null;
        zzIB = null;
    }

    public zzm(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com.google.android.gms.ads.internal.request.zza.zza com_google_android_gms_ads_internal_request_zza_zza) {
        super(true);
        this.zzGg = new Object();
        this.zzHg = com_google_android_gms_ads_internal_request_zza_zza;
        this.mContext = context;
        this.zzHh = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza;
        synchronized (zzqy) {
            if (!zzIx) {
                zzIA = new zzdk();
                zzIz = new zzdg(context.getApplicationContext(), com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzrl);
                zzIB = new zzc();
                zzIy = new zzeg(this.mContext.getApplicationContext(), this.zzHh.zzrl, (String) zzbt.zzvB.get(), new zzb(), new zza());
                zzIx = true;
            }
        }
    }

    private JSONObject zza(AdRequestInfoParcel adRequestInfoParcel, String str) {
        Info advertisingIdInfo;
        Throwable e;
        Object obj;
        Map hashMap;
        JSONObject jSONObject = null;
        Bundle bundle = adRequestInfoParcel.zzHt.extras.getBundle("sdk_less_server_data");
        String string = adRequestInfoParcel.zzHt.extras.getString("sdk_less_network_id");
        if (bundle != null) {
            JSONObject zza = zzhe.zza(this.mContext, adRequestInfoParcel, zzr.zzbI().zzE(this.mContext), jSONObject, jSONObject, new zzbm((String) zzbt.zzvB.get()), jSONObject, jSONObject, new ArrayList(), jSONObject);
            if (zza != null) {
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
                } catch (IOException e2) {
                    e = e2;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzr.zzbC().zzG(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e3) {
                    e = e3;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzr.zzbC().zzG(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    e = e4;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzr.zzbC().zzG(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e5) {
                    e = e5;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzr.zzbC().zzG(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put("request_id", str);
                hashMap.put("network_id", string);
                hashMap.put("request_param", zza);
                hashMap.put("data", bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = zzr.zzbC().zzG(hashMap);
                } catch (JSONException e6) {
                }
            }
        }
        return jSONObject;
    }

    protected static void zzc(zzed com_google_android_gms_internal_zzed) {
        com_google_android_gms_internal_zzed.zza("/loadAd", zzIA);
        com_google_android_gms_internal_zzed.zza("/fetchHttpRequest", zzIz);
        com_google_android_gms_internal_zzed.zza("/invalidRequest", zzIB);
    }

    protected static void zzd(zzed com_google_android_gms_internal_zzed) {
        com_google_android_gms_internal_zzed.zzb("/loadAd", zzIA);
        com_google_android_gms_internal_zzed.zzb("/fetchHttpRequest", zzIz);
        com_google_android_gms_internal_zzed.zzb("/invalidRequest", zzIB);
    }

    private AdResponseParcel zze(AdRequestInfoParcel adRequestInfoParcel) {
        String uuid = UUID.randomUUID().toString();
        JSONObject zza = zza(adRequestInfoParcel, uuid);
        if (zza == null) {
            return new AdResponseParcel(0);
        }
        long elapsedRealtime = zzr.zzbG().elapsedRealtime();
        Future zzR = zzIA.zzR(uuid);
        com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C05092(this, zza, uuid));
        try {
            JSONObject jSONObject = (JSONObject) zzR.get(zzIw - (zzr.zzbG().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new AdResponseParcel(-1);
            }
            AdResponseParcel zza2 = zzhe.zza(this.mContext, adRequestInfoParcel, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.body)) ? zza2 : new AdResponseParcel(3);
        } catch (CancellationException e) {
            return new AdResponseParcel(-1);
        } catch (InterruptedException e2) {
            return new AdResponseParcel(-1);
        } catch (TimeoutException e3) {
            return new AdResponseParcel(2);
        } catch (ExecutionException e4) {
            return new AdResponseParcel(0);
        }
    }

    public void onStop() {
        synchronized (this.zzGg) {
            com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C05103(this));
        }
    }

    public void zzbr() {
        com.google.android.gms.ads.internal.util.client.zzb.zzaI("SdkLessAdLoaderBackgroundTask started.");
        AdRequestInfoParcel adRequestInfoParcel = new AdRequestInfoParcel(this.zzHh, null, -1);
        AdResponseParcel zze = zze(adRequestInfoParcel);
        AdSizeParcel adSizeParcel = null;
        com.google.android.gms.ads.internal.util.client.zza.zzMS.post(new C05081(this, new com.google.android.gms.internal.zzif.zza(adRequestInfoParcel, zze, null, adSizeParcel, zze.errorCode, zzr.zzbG().elapsedRealtime(), zze.zzHX, null)));
    }
}
