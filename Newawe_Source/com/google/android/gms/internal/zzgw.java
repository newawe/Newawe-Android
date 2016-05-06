package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.Newawe.storage.DatabaseOpenHelper;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.Method;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzgw implements Callable<zzif> {
    private static final long zzGF;
    private final Context mContext;
    private final zziw zzGG;
    private final zzp zzGH;
    private final zzee zzGI;
    private boolean zzGJ;
    private List<String> zzGK;
    private JSONObject zzGL;
    private final com.google.android.gms.internal.zzif.zza zzGd;
    private int zzGu;
    private final Object zzpV;
    private final zzan zzyt;

    /* renamed from: com.google.android.gms.internal.zzgw.2 */
    class C06062 implements Runnable {
        final /* synthetic */ zzgw zzGP;
        final /* synthetic */ zzjd zzGQ;
        final /* synthetic */ String zzGR;

        C06062(zzgw com_google_android_gms_internal_zzgw, zzjd com_google_android_gms_internal_zzjd, String str) {
            this.zzGP = com_google_android_gms_internal_zzgw;
            this.zzGQ = com_google_android_gms_internal_zzjd;
            this.zzGR = str;
        }

        public void run() {
            this.zzGQ.zzg(this.zzGP.zzGH.zzbv().get(this.zzGR));
        }
    }

    public interface zza<T extends com.google.android.gms.ads.internal.formats.zzh.zza> {
        T zza(zzgw com_google_android_gms_internal_zzgw, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException;
    }

    class zzb {
        final /* synthetic */ zzgw zzGP;
        public zzdf zzHb;

        zzb(zzgw com_google_android_gms_internal_zzgw) {
            this.zzGP = com_google_android_gms_internal_zzgw;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzgw.1 */
    class C11701 implements zzdf {
        final /* synthetic */ zzed zzGM;
        final /* synthetic */ zzb zzGN;
        final /* synthetic */ zzjd zzGO;
        final /* synthetic */ zzgw zzGP;

        C11701(zzgw com_google_android_gms_internal_zzgw, zzed com_google_android_gms_internal_zzed, zzb com_google_android_gms_internal_zzgw_zzb, zzjd com_google_android_gms_internal_zzjd) {
            this.zzGP = com_google_android_gms_internal_zzgw;
            this.zzGM = com_google_android_gms_internal_zzed;
            this.zzGN = com_google_android_gms_internal_zzgw_zzb;
            this.zzGO = com_google_android_gms_internal_zzjd;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzGM.zzb("/nativeAdPreProcess", this.zzGN.zzHb);
            try {
                String str = (String) map.get("success");
                if (!TextUtils.isEmpty(str)) {
                    this.zzGO.zzg(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                    return;
                }
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Malformed native JSON response.", e);
            }
            this.zzGP.zzF(0);
            zzx.zza(this.zzGP.zzgn(), (Object) "Unable to set the ad state error!");
            this.zzGO.zzg(null);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzgw.3 */
    class C11713 implements zzdf {
        final /* synthetic */ zzgw zzGP;
        final /* synthetic */ zzf zzGS;

        C11713(zzgw com_google_android_gms_internal_zzgw, zzf com_google_android_gms_ads_internal_formats_zzf) {
            this.zzGP = com_google_android_gms_internal_zzgw;
            this.zzGS = com_google_android_gms_ads_internal_formats_zzf;
        }

        public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
            this.zzGP.zzb(this.zzGS, (String) map.get("asset"));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzgw.4 */
    class C11724 implements com.google.android.gms.internal.zzjf.zza<List<zzc>, com.google.android.gms.ads.internal.formats.zza> {
        final /* synthetic */ zzgw zzGP;
        final /* synthetic */ String zzGT;
        final /* synthetic */ Integer zzGU;
        final /* synthetic */ Integer zzGV;
        final /* synthetic */ int zzGW;
        final /* synthetic */ int zzGX;
        final /* synthetic */ int zzGY;

        C11724(zzgw com_google_android_gms_internal_zzgw, String str, Integer num, Integer num2, int i, int i2, int i3) {
            this.zzGP = com_google_android_gms_internal_zzgw;
            this.zzGT = str;
            this.zzGU = num;
            this.zzGV = num2;
            this.zzGW = i;
            this.zzGX = i2;
            this.zzGY = i3;
        }

        public /* synthetic */ Object zzf(Object obj) {
            return zzh((List) obj);
        }

        public com.google.android.gms.ads.internal.formats.zza zzh(List<zzc> list) {
            com.google.android.gms.ads.internal.formats.zza com_google_android_gms_ads_internal_formats_zza;
            if (list != null) {
                try {
                    if (!list.isEmpty()) {
                        com_google_android_gms_ads_internal_formats_zza = new com.google.android.gms.ads.internal.formats.zza(this.zzGT, zzgw.zzf((List) list), this.zzGU, this.zzGV, this.zzGW > 0 ? Integer.valueOf(this.zzGW) : null, this.zzGX + this.zzGY);
                        return com_google_android_gms_ads_internal_formats_zza;
                    }
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not get attribution icon", e);
                    return null;
                }
            }
            com_google_android_gms_ads_internal_formats_zza = null;
            return com_google_android_gms_ads_internal_formats_zza;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzgw.5 */
    class C11735 implements com.google.android.gms.internal.zziw.zza<zzc> {
        final /* synthetic */ String zzDr;
        final /* synthetic */ zzgw zzGP;
        final /* synthetic */ boolean zzGZ;
        final /* synthetic */ double zzHa;

        C11735(zzgw com_google_android_gms_internal_zzgw, boolean z, double d, String str) {
            this.zzGP = com_google_android_gms_internal_zzgw;
            this.zzGZ = z;
            this.zzHa = d;
            this.zzDr = str;
        }

        public zzc zzg(InputStream inputStream) {
            byte[] zzk;
            try {
                zzk = zzna.zzk(inputStream);
            } catch (IOException e) {
                zzk = null;
            }
            if (zzk == null) {
                this.zzGP.zza(2, this.zzGZ);
                return null;
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzk, 0, zzk.length);
            if (decodeByteArray == null) {
                this.zzGP.zza(2, this.zzGZ);
                return null;
            }
            decodeByteArray.setDensity((int) (160.0d * this.zzHa));
            return new zzc(new BitmapDrawable(Resources.getSystem(), decodeByteArray), Uri.parse(this.zzDr), this.zzHa);
        }

        public zzc zzgo() {
            this.zzGP.zza(2, this.zzGZ);
            return null;
        }

        public /* synthetic */ Object zzgp() {
            return zzgo();
        }

        public /* synthetic */ Object zzh(InputStream inputStream) {
            return zzg(inputStream);
        }
    }

    static {
        zzGF = TimeUnit.SECONDS.toMillis(60);
    }

    public zzgw(Context context, zzp com_google_android_gms_ads_internal_zzp, zzee com_google_android_gms_internal_zzee, zziw com_google_android_gms_internal_zziw, zzan com_google_android_gms_internal_zzan, com.google.android.gms.internal.zzif.zza com_google_android_gms_internal_zzif_zza) {
        this.zzpV = new Object();
        this.mContext = context;
        this.zzGH = com_google_android_gms_ads_internal_zzp;
        this.zzGG = com_google_android_gms_internal_zziw;
        this.zzGI = com_google_android_gms_internal_zzee;
        this.zzGd = com_google_android_gms_internal_zzif_zza;
        this.zzyt = com_google_android_gms_internal_zzan;
        this.zzGJ = false;
        this.zzGu = -2;
        this.zzGK = null;
    }

    private com.google.android.gms.ads.internal.formats.zzh.zza zza(zzed com_google_android_gms_internal_zzed, zza com_google_android_gms_internal_zzgw_zza, JSONObject jSONObject) throws ExecutionException, InterruptedException, JSONException {
        if (zzgn()) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] zzc = zzc(jSONObject2, "impression_tracking_urls");
        this.zzGK = zzc == null ? null : Arrays.asList(zzc);
        this.zzGL = jSONObject2.optJSONObject("active_view");
        com.google.android.gms.ads.internal.formats.zzh.zza zza = com_google_android_gms_internal_zzgw_zza.zza(this, jSONObject);
        if (zza == null) {
            com.google.android.gms.ads.internal.util.client.zzb.m853e("Failed to retrieve ad assets.");
            return null;
        }
        zza.zzb(new zzi(this.mContext, this.zzGH, com_google_android_gms_internal_zzed, this.zzyt, jSONObject, zza, this.zzGd.zzLd.zzrl));
        return zza;
    }

    private zzif zza(com.google.android.gms.ads.internal.formats.zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza) {
        int i;
        synchronized (this.zzpV) {
            i = this.zzGu;
            if (com_google_android_gms_ads_internal_formats_zzh_zza == null && this.zzGu == -2) {
                i = 0;
            }
        }
        return new zzif(this.zzGd.zzLd.zzHt, null, this.zzGd.zzLe.zzBQ, i, this.zzGd.zzLe.zzBR, this.zzGK, this.zzGd.zzLe.orientation, this.zzGd.zzLe.zzBU, this.zzGd.zzLd.zzHw, false, null, null, null, null, null, 0, this.zzGd.zzrp, this.zzGd.zzLe.zzHS, this.zzGd.zzKY, this.zzGd.zzKZ, this.zzGd.zzLe.zzHY, this.zzGL, i != -2 ? null : com_google_android_gms_ads_internal_formats_zzh_zza, null, null, null, this.zzGd.zzLe.zzIm);
    }

    private zzjg<zzc> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString(DatabaseOpenHelper.HISTORY_ROW_URL) : jSONObject.optString(DatabaseOpenHelper.HISTORY_ROW_URL);
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? new zzje(new zzc(null, Uri.parse(string), optDouble)) : this.zzGG.zza(string, new C11735(this, z, optDouble, string));
        } else {
            zza(0, z);
            return new zzje(null);
        }
    }

    private void zza(com.google.android.gms.ads.internal.formats.zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza, zzed com_google_android_gms_internal_zzed) {
        if (com_google_android_gms_ads_internal_formats_zzh_zza instanceof zzf) {
            zzf com_google_android_gms_ads_internal_formats_zzf = (zzf) com_google_android_gms_ads_internal_formats_zzh_zza;
            zzb com_google_android_gms_internal_zzgw_zzb = new zzb(this);
            zzdf c11713 = new C11713(this, com_google_android_gms_ads_internal_formats_zzf);
            com_google_android_gms_internal_zzgw_zzb.zzHb = c11713;
            com_google_android_gms_internal_zzed.zza("/nativeAdCustomClick", c11713);
        }
    }

    private Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject zzb(zzed com_google_android_gms_internal_zzed) throws TimeoutException, JSONException {
        if (zzgn()) {
            return null;
        }
        zzjd com_google_android_gms_internal_zzjd = new zzjd();
        zzb com_google_android_gms_internal_zzgw_zzb = new zzb(this);
        zzdf c11701 = new C11701(this, com_google_android_gms_internal_zzed, com_google_android_gms_internal_zzgw_zzb, com_google_android_gms_internal_zzjd);
        com_google_android_gms_internal_zzgw_zzb.zzHb = c11701;
        com_google_android_gms_internal_zzed.zza("/nativeAdPreProcess", c11701);
        com_google_android_gms_internal_zzed.zza("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.zzGd.zzLe.body));
        return (JSONObject) com_google_android_gms_internal_zzjd.get(zzGF, TimeUnit.MILLISECONDS);
    }

    private void zzb(zzcp com_google_android_gms_internal_zzcp, String str) {
        try {
            zzct zzs = this.zzGH.zzs(com_google_android_gms_internal_zzcp.getCustomTemplateId());
            if (zzs != null) {
                zzs.zza(com_google_android_gms_internal_zzcp, str);
            }
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to call onCustomClick for asset " + str + ".", e);
        }
    }

    private String[] zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private static List<Drawable> zzf(List<zzc> list) throws RemoteException {
        List<Drawable> arrayList = new ArrayList();
        for (zzc zzdJ : list) {
            arrayList.add((Drawable) zze.zzp(zzdJ.zzdJ()));
        }
        return arrayList;
    }

    private zzed zzgm() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (zzgn()) {
            return null;
        }
        zzed com_google_android_gms_internal_zzed = (zzed) this.zzGI.zza(this.mContext, this.zzGd.zzLd.zzrl, (this.zzGd.zzLe.zzEF.indexOf("https") == 0 ? "https:" : "http:") + ((String) zzbt.zzwC.get()), this.zzyt).get(zzGF, TimeUnit.MILLISECONDS);
        com_google_android_gms_internal_zzed.zza(this.zzGH, this.zzGH, this.zzGH, this.zzGH, false, null, null, null, null);
        return com_google_android_gms_internal_zzed;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzgl();
    }

    public void zzF(int i) {
        synchronized (this.zzpV) {
            this.zzGJ = true;
            this.zzGu = i;
        }
    }

    public zzjg<zzc> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public List<zzjg<zzc>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        List<zzjg<zzc>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            zza(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzc> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public void zza(int i, boolean z) {
        if (z) {
            zzF(i);
        }
    }

    protected zza zze(JSONObject jSONObject) throws JSONException, TimeoutException {
        if (zzgn()) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.zzGd.zzLd.zzrD != null ? this.zzGd.zzLd.zzrD.zzyA : false;
        boolean z2 = this.zzGd.zzLd.zzrD != null ? this.zzGd.zzLd.zzrD.zzyC : false;
        if ("2".equals(string)) {
            return new zzgx(z, z2);
        }
        if (SchemaSymbols.ATTVAL_TRUE_1.equals(string)) {
            return new zzgy(z, z2);
        }
        if ("3".equals(string)) {
            String string2 = jSONObject.getString("custom_template_id");
            zzjd com_google_android_gms_internal_zzjd = new zzjd();
            zzir.zzMc.post(new C06062(this, com_google_android_gms_internal_zzjd, string2));
            if (com_google_android_gms_internal_zzjd.get(zzGF, TimeUnit.MILLISECONDS) != null) {
                return new zzgz(z);
            }
            com.google.android.gms.ads.internal.util.client.zzb.m853e("No handler for custom template: " + jSONObject.getString("custom_template_id"));
        } else {
            zzF(0);
        }
        return null;
    }

    public zzjg<com.google.android.gms.ads.internal.formats.zza> zzf(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzje(null);
        }
        String optString = optJSONObject.optString(Method.TEXT);
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", DateUtils.MILLIS_IN_SECOND);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        return zzjf.zza(zzjf.zzl(arrayList), new C11724(this, optString, zzb2, zzb, optInt, optInt3, optInt2));
    }

    public zzif zzgl() {
        try {
            zzed zzgm = zzgm();
            JSONObject zzb = zzb(zzgm);
            com.google.android.gms.ads.internal.formats.zzh.zza zza = zza(zzgm, zze(zzb), zzb);
            zza(zza, zzgm);
            return zza(zza);
        } catch (CancellationException e) {
            if (!this.zzGJ) {
                zzF(0);
            }
            return zza(null);
        } catch (ExecutionException e2) {
            if (this.zzGJ) {
                zzF(0);
            }
            return zza(null);
        } catch (InterruptedException e3) {
            if (this.zzGJ) {
                zzF(0);
            }
            return zza(null);
        } catch (Throwable e4) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Malformed native JSON response.", e4);
            if (this.zzGJ) {
                zzF(0);
            }
            return zza(null);
        } catch (Throwable e42) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Timeout when loading native ad.", e42);
            if (this.zzGJ) {
                zzF(0);
            }
            return zza(null);
        }
    }

    public boolean zzgn() {
        boolean z;
        synchronized (this.zzpV) {
            z = this.zzGJ;
        }
        return z;
    }
}
