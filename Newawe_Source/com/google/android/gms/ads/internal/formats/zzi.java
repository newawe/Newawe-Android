package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

@zzhb
public class zzi implements zzh {
    private final Context mContext;
    private zzjp zzpD;
    private final VersionInfoParcel zzpT;
    private final Object zzpV;
    private final zzp zzyn;
    private final JSONObject zzyq;
    private final zzed zzyr;
    private final zza zzys;
    private final zzan zzyt;
    private boolean zzyu;
    private String zzyv;
    private WeakReference<View> zzyw;

    /* renamed from: com.google.android.gms.ads.internal.formats.zzi.1 */
    class C10731 implements zzdf {
        final /* synthetic */ zzi zzyx;

        /* renamed from: com.google.android.gms.ads.internal.formats.zzi.1.1 */
        class C10721 implements zzjq.zza {
            final /* synthetic */ Map zzyy;
            final /* synthetic */ C10731 zzyz;

            C10721(C10731 c10731, Map map) {
                this.zzyz = c10731;
                this.zzyy = map;
            }

            public void zza(zzjp com_google_android_gms_internal_zzjp, boolean z) {
                this.zzyz.zzyx.zzyv = (String) this.zzyy.get("id");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("messageType", "htmlLoaded");
                    jSONObject.put("id", this.zzyz.zzyx.zzyv);
                    this.zzyz.zzyx.zzyr.zzb("sendMessageToNativeJs", jSONObject);
                } catch (Throwable e) {
                    zzb.zzb("Unable to dispatch sendMessageToNativeJsevent", e);
                }
            }
        }

        C10731(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzyx = com_google_android_gms_ads_internal_formats_zzi;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzyx.zzpD.zzhU().zza(new C10721(this, map));
            String str = (String) map.get("overlayHtml");
            String str2 = (String) map.get("baseUrl");
            if (TextUtils.isEmpty(str2)) {
                this.zzyx.zzpD.loadData(str, "text/html", HTTP.UTF_8);
            } else {
                this.zzyx.zzpD.loadDataWithBaseURL(str2, str, "text/html", HTTP.UTF_8, null);
            }
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.formats.zzi.2 */
    class C10742 implements zzdf {
        final /* synthetic */ zzi zzyx;

        C10742(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzyx = com_google_android_gms_ads_internal_formats_zzi;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzyx.zzpD.getView().setVisibility(0);
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.formats.zzi.3 */
    class C10753 implements zzdf {
        final /* synthetic */ zzi zzyx;

        C10753(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzyx = com_google_android_gms_ads_internal_formats_zzi;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzyx.zzpD.getView().setVisibility(8);
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.formats.zzi.4 */
    class C10764 implements zzdf {
        final /* synthetic */ zzi zzyx;

        C10764(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzyx = com_google_android_gms_ads_internal_formats_zzi;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzyx.zzpD.getView().setVisibility(8);
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.formats.zzi.5 */
    class C10775 implements zzdf {
        final /* synthetic */ zzi zzyx;

        C10775(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzyx = com_google_android_gms_ads_internal_formats_zzi;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            JSONObject jSONObject = new JSONObject();
            try {
                for (String str : map.keySet()) {
                    jSONObject.put(str, map.get(str));
                }
                jSONObject.put("id", this.zzyx.zzyv);
                this.zzyx.zzyr.zzb("sendMessageToNativeJs", jSONObject);
            } catch (Throwable e) {
                zzb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
            }
        }
    }

    public zzi(Context context, zzp com_google_android_gms_ads_internal_zzp, zzed com_google_android_gms_internal_zzed, zzan com_google_android_gms_internal_zzan, JSONObject jSONObject, zza com_google_android_gms_ads_internal_formats_zzh_zza, VersionInfoParcel versionInfoParcel) {
        this.zzpV = new Object();
        this.zzyw = null;
        this.mContext = context;
        this.zzyn = com_google_android_gms_ads_internal_zzp;
        this.zzyr = com_google_android_gms_internal_zzed;
        this.zzyt = com_google_android_gms_internal_zzan;
        this.zzyq = jSONObject;
        this.zzys = com_google_android_gms_ads_internal_formats_zzh_zza;
        this.zzpT = versionInfoParcel;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void recordImpression() {
        zzx.zzcD("recordImpression must be called on the main UI thread.");
        zzn(true);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzyq);
            this.zzyr.zza("google.afma.nativeAds.handleImpressionPing", jSONObject);
        } catch (Throwable e) {
            zzb.zzb("Unable to create impression JSON.", e);
        }
        this.zzyn.zza((zzh) this);
    }

    public zzb zza(OnClickListener onClickListener) {
        zza zzdN = this.zzys.zzdN();
        if (zzdN == null) {
            return null;
        }
        zzb com_google_android_gms_ads_internal_formats_zzb = new zzb(this.mContext, zzdN);
        com_google_android_gms_ads_internal_formats_zzb.setLayoutParams(new LayoutParams(-1, -1));
        com_google_android_gms_ads_internal_formats_zzb.zzdI().setOnClickListener(onClickListener);
        com_google_android_gms_ads_internal_formats_zzb.zzdI().setContentDescription("Ad attribution icon");
        return com_google_android_gms_ads_internal_formats_zzb;
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzx.zzcD("performClick must be called on the main UI thread.");
        for (Entry entry : map.entrySet()) {
            if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                zza((String) entry.getKey(), jSONObject, jSONObject2, jSONObject3);
                return;
            }
        }
    }

    public void zza(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzx.zzcD("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("asset", str);
            jSONObject4.put("template", this.zzys.zzdM());
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("ad", this.zzyq);
            jSONObject5.put("click", jSONObject4);
            jSONObject5.put("has_custom_click_handler", this.zzyn.zzs(this.zzys.getCustomTemplateId()) != null);
            if (jSONObject != null) {
                jSONObject5.put("view_rectangles", jSONObject);
            }
            if (jSONObject2 != null) {
                jSONObject5.put("click_point", jSONObject2);
            }
            if (jSONObject3 != null) {
                jSONObject5.put("native_view_rectangle", jSONObject3);
            }
            this.zzyr.zza("google.afma.nativeAds.handleClickGmsg", jSONObject5);
        } catch (Throwable e) {
            zzb.zzb("Unable to create click JSON.", e);
        }
    }

    public void zzb(MotionEvent motionEvent) {
        this.zzyt.zza(motionEvent);
    }

    public zzjp zzdR() {
        this.zzpD = zzdT();
        this.zzpD.getView().setVisibility(8);
        this.zzyr.zza("/loadHtml", new C10731(this));
        this.zzyr.zza("/showOverlay", new C10742(this));
        this.zzyr.zza("/hideOverlay", new C10753(this));
        this.zzpD.zzhU().zza("/hideOverlay", new C10764(this));
        this.zzpD.zzhU().zza("/sendMessageToSdk", new C10775(this));
        return this.zzpD;
    }

    public View zzdS() {
        return this.zzyw != null ? (View) this.zzyw.get() : null;
    }

    zzjp zzdT() {
        return zzr.zzbD().zza(this.mContext, AdSizeParcel.zzt(this.mContext), false, false, this.zzyt, this.zzpT);
    }

    public void zzg(View view) {
    }

    public void zzh(View view) {
        synchronized (this.zzpV) {
            if (this.zzyu) {
            } else if (!view.isShown()) {
            } else if (view.getGlobalVisibleRect(new Rect(), null)) {
                recordImpression();
            }
        }
    }

    public void zzi(View view) {
        this.zzyw = new WeakReference(view);
    }

    protected void zzn(boolean z) {
        this.zzyu = z;
    }
}
