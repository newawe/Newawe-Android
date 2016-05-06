package com.chartboost.sdk.Tracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0315b;
import com.chartboost.sdk.Libraries.C0318c;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.ba;
import java.lang.reflect.Method;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Tracking.a */
public class C1020a implements C0314a {
    private static final String f3400b;
    private static C1020a f3401k;
    private static boolean f3402l;
    private String f3403c;
    private String f3404d;
    private JSONArray f3405e;
    private long f3406f;
    private long f3407g;
    private long f3408h;
    private C0330h f3409i;
    private C0330h f3410j;

    static {
        f3400b = C1020a.class.getSimpleName();
        f3402l = false;
    }

    private C1020a() {
        this.f3408h = System.currentTimeMillis();
        this.f3404d = m3774o();
        this.f3405e = new JSONArray();
        this.f3409i = new C0330h(false);
        this.f3410j = new C0330h(false);
    }

    public static C1020a m3733a() {
        if (f3401k == null) {
            synchronized (Chartboost.class) {
                if (f3401k == null) {
                    f3401k = new C1020a();
                }
            }
        }
        return f3401k;
    }

    public static void m3745b() {
        C1020a.m3735a("start");
        C1020a.m3735a("did-become-active");
    }

    public static void m3735a(String str) {
        f3401k.m3760a("session", str, null, null, null, null, "session");
    }

    public void m3763c() {
        m3762a(false);
    }

    public void m3762a(boolean z) {
        C0321a a = C0321a.m121a();
        a.m128a("complete", Boolean.valueOf(z));
        f3401k.m3761a("session", "end", null, null, null, null, a.m139e(), "session");
        C1020a.m3735a("did-become-active");
    }

    public static void m3741a(String str, String str2, String str3, JSONObject jSONObject, String str4) {
        f3401k.m3761a("webview-track", str, str2, str3, null, null, jSONObject, str4);
    }

    public static void m3743a(JSONObject jSONObject) {
        f3401k.m3761a("folder", C0351c.m328F().booleanValue() ? "web" : "native", null, null, null, null, jSONObject, "system");
    }

    public static void m3742a(String str, String str2, String str3, boolean z) {
        f3401k.m3761a("ad-get", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, C1020a.m3744b(z), "single", null, "system");
    }

    public static void m3737a(String str, String str2, String str3) {
        f3401k.m3760a("ad-show", str, str2, str3, null, null, "system");
    }

    public static void m3748b(String str, String str2, String str3) {
        f3401k.m3760a("ad-click", str, str2, str3, null, null, "system");
    }

    public static void m3751c(String str, String str2, String str3) {
        f3401k.m3760a("ad-close", str, str2, str3, null, null, "system");
    }

    public static void m3738a(String str, String str2, String str3, CBImpressionError cBImpressionError) {
        f3401k.m3760a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, cBImpressionError != null ? cBImpressionError.toString() : StringUtils.EMPTY, null, "system");
    }

    public static void m3739a(String str, String str2, String str3, String str4) {
        f3401k.m3760a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system");
    }

    public static void m3749b(String str, String str2, String str3, String str4) {
        f3401k.m3760a("ad-warning", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system");
    }

    public static void m3753d() {
        f3401k.m3760a("asset-prefetcher", "start", null, null, null, null, "system");
    }

    public static void m3746b(String str) {
        f3401k.m3761a("asset-prefetcher", C0351c.m328F().booleanValue() ? "web" : "native", str, null, null, null, null, "system");
    }

    public static void m3756e() {
        f3401k.m3761a("asset-prefetcher", C0351c.m328F().booleanValue() ? "web" : "native", null, null, null, null, null, "system");
    }

    public static void m3736a(String str, String str2) {
        f3401k.m3761a("asset-prefetcher", "start", C0351c.m328F().booleanValue() ? "web" : "native", str, str2, null, null, "system");
    }

    public static void m3752c(String str, String str2, String str3, String str4) {
        f3401k.m3761a("asset-prefetcher", "failure", C0351c.m328F().booleanValue() ? "web" : "native", str, str2, str4, null, "system");
    }

    public static void m3755d(String str, String str2, String str3) {
        f3401k.m3761a("asset-prefetcher", "success", C0351c.m328F().booleanValue() ? "web" : "native", str, str2, null, null, "system");
    }

    public static void m3747b(String str, String str2) {
        f3401k.m3760a("playback-complete", str, str2, null, null, null, "system");
    }

    public static void m3750c(String str, String str2) {
        f3401k.m3760a("replay", str, str2, null, null, null, "system");
    }

    public static void m3754d(String str, String str2) {
        f3401k.m3760a("playback-start", str, str2, null, null, null, "system");
    }

    public static void m3757e(String str, String str2) {
        f3401k.m3760a("playback-stop", str, str2, null, null, null, "system");
    }

    public static void m3740a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        f3401k.m3761a(str, str2, str3, str4, str5, str6, jSONObject, "system");
    }

    public void m3760a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        f3401k.m3761a(str, str2, str3, str4, str5, str6, new JSONObject(), str7);
    }

    public void m3761a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, String str7) {
        JSONObject jSONObject2;
        try {
            Method declaredMethod = C0351c.class.getDeclaredMethod("k", new Class[0]);
            declaredMethod.setAccessible(true);
            jSONObject2 = (JSONObject) declaredMethod.invoke(null, new Object[0]);
        } catch (Throwable e) {
            CBLogging.m78b(this, "Error encountered getting tracking levels", e);
            CBUtility.throwProguardError(e);
            jSONObject2 = null;
        }
        C0321a a = C0321a.m121a();
        if (jSONObject2 != null && jSONObject2.optBoolean(str7)) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.f3406f;
            currentTimeMillis -= this.f3408h;
            a.m128a(NotificationCompatApi21.CATEGORY_EVENT, C1020a.m3734a((Object) str));
            a.m128a("kingdom", C1020a.m3734a((Object) str2));
            a.m128a("phylum", C1020a.m3734a((Object) str3));
            a.m128a("class", C1020a.m3734a((Object) str4));
            a.m128a("family", C1020a.m3734a((Object) str5));
            a.m128a("genus", C1020a.m3734a((Object) str6));
            String str8 = "meta";
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            a.m128a(str8, jSONObject);
            a.m128a("clientTimestamp", Long.valueOf(System.currentTimeMillis() / 1000));
            a.m128a("session_id", m3770k());
            a.m128a("totalSessionTime", Long.valueOf(j / 1000));
            a.m128a("currentSessionTime", Long.valueOf(currentTimeMillis / 1000));
            synchronized (this) {
                if (m3765f()) {
                    m3775p();
                    ba.m619e();
                }
                this.f3405e.put(a.m139e());
                Object a2 = C0321a.m121a();
                a2.m128a("events", this.f3405e);
                CBLogging.m75a(f3400b, "###Writing" + C1020a.m3734a((Object) str) + "to tracking cache dir");
                this.f3409i.m206a(this.f3409i.m225p(), this.f3404d, C0321a.m122a(a2));
                m3769j();
            }
        }
    }

    public boolean m3765f() {
        if (this.f3405e == null || this.f3405e.length() < 50) {
            return false;
        }
        return true;
    }

    public String m3766g() {
        C0321a a = C0321a.m121a();
        a.m128a("startTime", Long.valueOf(System.currentTimeMillis()));
        a.m128a("deviceID", C0318c.m113e());
        this.f3403c = C0315b.m103b(a.toString().getBytes());
        return this.f3403c;
    }

    public void m3767h() {
        C0321a a = this.f3410j.m204a(this.f3410j.m224o(), "cb_previous_session_info");
        if (a != null) {
            this.f3407g = a.m147i("timestamp");
            this.f3406f = a.m147i("start_timestamp");
            this.f3403c = a.m138e("session_id");
            if (System.currentTimeMillis() - this.f3407g > 180000) {
                m3762a(true);
            } else if (!TextUtils.isEmpty(this.f3403c)) {
                m3769j();
                f3402l = false;
                return;
            }
        }
        m3768i();
        f3402l = true;
    }

    public void m3768i() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f3406f = currentTimeMillis;
        this.f3407g = currentTimeMillis;
        this.f3403c = m3766g();
        m3759a(currentTimeMillis, currentTimeMillis);
        SharedPreferences a = CBUtility.m87a();
        int i = a.getInt("cbPrefSessionCount", 0) + 1;
        Editor edit = a.edit();
        edit.putInt("cbPrefSessionCount", i);
        edit.commit();
    }

    public void m3769j() {
        m3759a(this.f3406f, System.currentTimeMillis());
    }

    public void m3759a(long j, long j2) {
        C0321a a = C0321a.m121a();
        a.m128a("start_timestamp", Long.valueOf(j));
        a.m128a("timestamp", Long.valueOf(j2));
        a.m128a("session_id", this.f3403c);
        this.f3410j.m206a(this.f3410j.m224o(), "cb_previous_session_info", a);
    }

    public az m3758a(C0321a c0321a) {
        az azVar = new az("/api/track");
        azVar.m565a("track", (Object) c0321a);
        azVar.m559a(C0328g.m176a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a)));
        azVar.m562a(C0466a.LOW);
        return azVar;
    }

    public String toString() {
        return "Session [ startTime: " + m3773n() + " sessionEvents: " + m3772m() + " ]";
    }

    public String m3770k() {
        return this.f3403c;
    }

    public C0330h m3771l() {
        return this.f3409i;
    }

    public JSONArray m3772m() {
        return this.f3405e;
    }

    public long m3773n() {
        return this.f3406f;
    }

    public String m3774o() {
        return new Long(System.nanoTime()).toString();
    }

    public static String m3744b(boolean z) {
        return z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0;
    }

    private static Object m3734a(Object obj) {
        return obj != null ? obj : StringUtils.EMPTY;
    }

    public boolean m3764c(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals("cb_previous_session_info");
    }

    public void m3775p() {
        this.f3405e = new JSONArray();
        this.f3404d = m3774o();
    }
}
