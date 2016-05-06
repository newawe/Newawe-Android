package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzes;
import com.google.android.gms.internal.zzfb;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq.zza;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzm {

    /* renamed from: com.google.android.gms.ads.internal.zzm.1 */
    static class C10861 implements zza {
        final /* synthetic */ zzd zzqq;
        final /* synthetic */ String zzqr;
        final /* synthetic */ zzjp zzqs;

        C10861(zzd com_google_android_gms_ads_internal_formats_zzd, String str, zzjp com_google_android_gms_internal_zzjp) {
            this.zzqq = com_google_android_gms_ads_internal_formats_zzd;
            this.zzqr = str;
            this.zzqs = com_google_android_gms_internal_zzjp;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, boolean z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("headline", this.zzqq.getHeadline());
                jSONObject.put("body", this.zzqq.getBody());
                jSONObject.put("call_to_action", this.zzqq.getCallToAction());
                jSONObject.put("price", this.zzqq.getPrice());
                jSONObject.put("star_rating", String.valueOf(this.zzqq.getStarRating()));
                jSONObject.put("store", this.zzqq.getStore());
                jSONObject.put("icon", zzm.zza(this.zzqq.zzdK()));
                JSONArray jSONArray = new JSONArray();
                List<Object> images = this.zzqq.getImages();
                if (images != null) {
                    for (Object zzd : images) {
                        jSONArray.put(zzm.zza(zzm.zzc(zzd)));
                    }
                }
                jSONObject.put("images", jSONArray);
                jSONObject.put("extras", zzm.zza(this.zzqq.getExtras(), this.zzqr));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("assets", jSONObject);
                jSONObject2.put("template_id", "2");
                this.zzqs.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
            } catch (Throwable e) {
                zzb.zzd("Exception occurred when loading assets", e);
            }
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzm.2 */
    static class C10872 implements zza {
        final /* synthetic */ String zzqr;
        final /* synthetic */ zzjp zzqs;
        final /* synthetic */ zze zzqt;

        C10872(zze com_google_android_gms_ads_internal_formats_zze, String str, zzjp com_google_android_gms_internal_zzjp) {
            this.zzqt = com_google_android_gms_ads_internal_formats_zze;
            this.zzqr = str;
            this.zzqs = com_google_android_gms_internal_zzjp;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, boolean z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("headline", this.zzqt.getHeadline());
                jSONObject.put("body", this.zzqt.getBody());
                jSONObject.put("call_to_action", this.zzqt.getCallToAction());
                jSONObject.put("advertiser", this.zzqt.getAdvertiser());
                jSONObject.put("logo", zzm.zza(this.zzqt.zzdO()));
                JSONArray jSONArray = new JSONArray();
                List<Object> images = this.zzqt.getImages();
                if (images != null) {
                    for (Object zzd : images) {
                        jSONArray.put(zzm.zza(zzm.zzc(zzd)));
                    }
                }
                jSONObject.put("images", jSONArray);
                jSONObject.put("extras", zzm.zza(this.zzqt.getExtras(), this.zzqr));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("assets", jSONObject);
                jSONObject2.put("template_id", SchemaSymbols.ATTVAL_TRUE_1);
                this.zzqs.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
            } catch (Throwable e) {
                zzb.zzd("Exception occurred when loading assets", e);
            }
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzm.3 */
    static class C10883 implements zzdf {
        final /* synthetic */ CountDownLatch zzqu;

        C10883(CountDownLatch countDownLatch) {
            this.zzqu = countDownLatch;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzqu.countDown();
            View view = com_google_android_gms_internal_zzjp.getView();
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzm.4 */
    static class C10894 implements zzdf {
        final /* synthetic */ CountDownLatch zzqu;

        C10894(CountDownLatch countDownLatch) {
            this.zzqu = countDownLatch;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            zzb.zzaK("Adapter returned an ad, but assets substitution failed");
            this.zzqu.countDown();
            com_google_android_gms_internal_zzjp.destroy();
        }
    }

    /* renamed from: com.google.android.gms.ads.internal.zzm.5 */
    static class C10905 implements zzdf {
        final /* synthetic */ zzfb zzqv;
        final /* synthetic */ zzf.zza zzqw;
        final /* synthetic */ zzfc zzqx;

        C10905(zzfb com_google_android_gms_internal_zzfb, zzf.zza com_google_android_gms_ads_internal_zzf_zza, zzfc com_google_android_gms_internal_zzfc) {
            this.zzqv = com_google_android_gms_internal_zzfb;
            this.zzqw = com_google_android_gms_ads_internal_zzf_zza;
            this.zzqx = com_google_android_gms_internal_zzfc;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            View view = com_google_android_gms_internal_zzjp.getView();
            if (view != null) {
                try {
                    if (this.zzqv != null) {
                        if (this.zzqv.getOverrideClickHandling()) {
                            zzm.zza(com_google_android_gms_internal_zzjp);
                            return;
                        }
                        this.zzqv.zzc(com.google.android.gms.dynamic.zze.zzC(view));
                        this.zzqw.onClick();
                    } else if (this.zzqx == null) {
                    } else {
                        if (this.zzqx.getOverrideClickHandling()) {
                            zzm.zza(com_google_android_gms_internal_zzjp);
                            return;
                        }
                        this.zzqx.zzc(com.google.android.gms.dynamic.zze.zzC(view));
                        this.zzqw.onClick();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Unable to call handleClick on mapper", e);
                }
            }
        }
    }

    private static zzd zza(zzfb com_google_android_gms_internal_zzfb) throws RemoteException {
        return new zzd(com_google_android_gms_internal_zzfb.getHeadline(), com_google_android_gms_internal_zzfb.getImages(), com_google_android_gms_internal_zzfb.getBody(), com_google_android_gms_internal_zzfb.zzdK(), com_google_android_gms_internal_zzfb.getCallToAction(), com_google_android_gms_internal_zzfb.getStarRating(), com_google_android_gms_internal_zzfb.getStore(), com_google_android_gms_internal_zzfb.getPrice(), null, com_google_android_gms_internal_zzfb.getExtras());
    }

    private static zze zza(zzfc com_google_android_gms_internal_zzfc) throws RemoteException {
        return new zze(com_google_android_gms_internal_zzfc.getHeadline(), com_google_android_gms_internal_zzfc.getImages(), com_google_android_gms_internal_zzfc.getBody(), com_google_android_gms_internal_zzfc.zzdO(), com_google_android_gms_internal_zzfc.getCallToAction(), com_google_android_gms_internal_zzfc.getAdvertiser(), null, com_google_android_gms_internal_zzfc.getExtras());
    }

    static zzdf zza(zzfb com_google_android_gms_internal_zzfb, zzfc com_google_android_gms_internal_zzfc, zzf.zza com_google_android_gms_ads_internal_zzf_zza) {
        return new C10905(com_google_android_gms_internal_zzfb, com_google_android_gms_ads_internal_zzf_zza, com_google_android_gms_internal_zzfc);
    }

    static zzdf zza(CountDownLatch countDownLatch) {
        return new C10883(countDownLatch);
    }

    private static String zza(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzb.zzaK("Bitmap is null. Returning empty string");
            return StringUtils.EMPTY;
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    static String zza(zzch com_google_android_gms_internal_zzch) {
        if (com_google_android_gms_internal_zzch == null) {
            zzb.zzaK("Image is null. Returning empty string");
            return StringUtils.EMPTY;
        }
        try {
            Uri uri = com_google_android_gms_internal_zzch.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzb.zzaK("Unable to get image uri. Trying data uri next");
        }
        return zzb(com_google_android_gms_internal_zzch);
    }

    private static JSONObject zza(Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if ("image".equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzb.zzaK("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzb.zzaK("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    public static void zza(zzif com_google_android_gms_internal_zzif, zzf.zza com_google_android_gms_ads_internal_zzf_zza) {
        if (zzg(com_google_android_gms_internal_zzif)) {
            zzjp com_google_android_gms_internal_zzjp = com_google_android_gms_internal_zzif.zzED;
            View view = com_google_android_gms_internal_zzjp.getView();
            if (view == null) {
                zzb.zzaK("AdWebView is null");
                return;
            }
            try {
                List list = com_google_android_gms_internal_zzif.zzCp.zzBM;
                if (list == null || list.isEmpty()) {
                    zzb.zzaK("No template ids present in mediation response");
                    return;
                }
                zzfb zzeF = com_google_android_gms_internal_zzif.zzCq.zzeF();
                zzfc zzeG = com_google_android_gms_internal_zzif.zzCq.zzeG();
                if (list.contains("2") && zzeF != null) {
                    zzeF.zzd(com.google.android.gms.dynamic.zze.zzC(view));
                    if (!zzeF.getOverrideImpressionRecording()) {
                        zzeF.recordImpression();
                    }
                    com_google_android_gms_internal_zzjp.zzhU().zza("/nativeExpressViewClicked", zza(zzeF, null, com_google_android_gms_ads_internal_zzf_zza));
                } else if (!list.contains(SchemaSymbols.ATTVAL_TRUE_1) || zzeG == null) {
                    zzb.zzaK("No matching template id and mapper");
                } else {
                    zzeG.zzd(com.google.android.gms.dynamic.zze.zzC(view));
                    if (!zzeG.getOverrideImpressionRecording()) {
                        zzeG.recordImpression();
                    }
                    com_google_android_gms_internal_zzjp.zzhU().zza("/nativeExpressViewClicked", zza(null, zzeG, com_google_android_gms_ads_internal_zzf_zza));
                }
            } catch (Throwable e) {
                zzb.zzd("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    private static void zza(zzjp com_google_android_gms_internal_zzjp) {
        OnClickListener zzif = com_google_android_gms_internal_zzjp.zzif();
        if (zzif != null) {
            zzif.onClick(com_google_android_gms_internal_zzjp.getView());
        }
    }

    private static void zza(zzjp com_google_android_gms_internal_zzjp, zzd com_google_android_gms_ads_internal_formats_zzd, String str) {
        com_google_android_gms_internal_zzjp.zzhU().zza(new C10861(com_google_android_gms_ads_internal_formats_zzd, str, com_google_android_gms_internal_zzjp));
    }

    private static void zza(zzjp com_google_android_gms_internal_zzjp, zze com_google_android_gms_ads_internal_formats_zze, String str) {
        com_google_android_gms_internal_zzjp.zzhU().zza(new C10872(com_google_android_gms_ads_internal_formats_zze, str, com_google_android_gms_internal_zzjp));
    }

    private static void zza(zzjp com_google_android_gms_internal_zzjp, CountDownLatch countDownLatch) {
        com_google_android_gms_internal_zzjp.zzhU().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        com_google_android_gms_internal_zzjp.zzhU().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }

    public static boolean zza(zzjp com_google_android_gms_internal_zzjp, zzes com_google_android_gms_internal_zzes, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = zzb(com_google_android_gms_internal_zzjp, com_google_android_gms_internal_zzes, countDownLatch);
        } catch (Throwable e) {
            zzb.zzd("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static zzdf zzb(CountDownLatch countDownLatch) {
        return new C10894(countDownLatch);
    }

    private static String zzb(zzch com_google_android_gms_internal_zzch) {
        try {
            com.google.android.gms.dynamic.zzd zzdJ = com_google_android_gms_internal_zzch.zzdJ();
            if (zzdJ == null) {
                zzb.zzaK("Drawable is null. Returning empty string");
                return StringUtils.EMPTY;
            }
            Drawable drawable = (Drawable) com.google.android.gms.dynamic.zze.zzp(zzdJ);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzb.zzaK("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return StringUtils.EMPTY;
        } catch (RemoteException e) {
            zzb.zzaK("Unable to get drawable. Returning empty string");
            return StringUtils.EMPTY;
        }
    }

    private static boolean zzb(zzjp com_google_android_gms_internal_zzjp, zzes com_google_android_gms_internal_zzes, CountDownLatch countDownLatch) throws RemoteException {
        View view = com_google_android_gms_internal_zzjp.getView();
        if (view == null) {
            zzb.zzaK("AdWebView is null");
            return false;
        }
        view.setVisibility(4);
        List list = com_google_android_gms_internal_zzes.zzCp.zzBM;
        if (list == null || list.isEmpty()) {
            zzb.zzaK("No template ids present in mediation response");
            return false;
        }
        zza(com_google_android_gms_internal_zzjp, countDownLatch);
        zzfb zzeF = com_google_android_gms_internal_zzes.zzCq.zzeF();
        zzfc zzeG = com_google_android_gms_internal_zzes.zzCq.zzeG();
        if (list.contains("2") && zzeF != null) {
            zza(com_google_android_gms_internal_zzjp, zza(zzeF), com_google_android_gms_internal_zzes.zzCp.zzBL);
        } else if (!list.contains(SchemaSymbols.ATTVAL_TRUE_1) || zzeG == null) {
            zzb.zzaK("No matching template id and mapper");
            return false;
        } else {
            zza(com_google_android_gms_internal_zzjp, zza(zzeG), com_google_android_gms_internal_zzes.zzCp.zzBL);
        }
        String str = com_google_android_gms_internal_zzes.zzCp.zzBJ;
        String str2 = com_google_android_gms_internal_zzes.zzCp.zzBK;
        if (str2 != null) {
            com_google_android_gms_internal_zzjp.loadDataWithBaseURL(str2, str, "text/html", HTTP.UTF_8, null);
        } else {
            com_google_android_gms_internal_zzjp.loadData(str, "text/html", HTTP.UTF_8);
        }
        return true;
    }

    private static zzch zzc(Object obj) {
        return obj instanceof IBinder ? zzch.zza.zzt((IBinder) obj) : null;
    }

    public static View zzf(zzif com_google_android_gms_internal_zzif) {
        if (com_google_android_gms_internal_zzif == null) {
            zzb.m853e("AdState is null");
            return null;
        } else if (zzg(com_google_android_gms_internal_zzif)) {
            return com_google_android_gms_internal_zzif.zzED.getView();
        } else {
            try {
                com.google.android.gms.dynamic.zzd view = com_google_android_gms_internal_zzif.zzCq.getView();
                if (view != null) {
                    return (View) com.google.android.gms.dynamic.zze.zzp(view);
                }
                zzb.zzaK("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzb.zzd("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzg(zzif com_google_android_gms_internal_zzif) {
        return (com_google_android_gms_internal_zzif == null || !com_google_android_gms_internal_zzif.zzHT || com_google_android_gms_internal_zzif.zzCp == null || com_google_android_gms_internal_zzif.zzCp.zzBJ == null) ? false : true;
    }
}
