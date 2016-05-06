package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.media.TransportMediator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0315b;
import com.chartboost.sdk.Libraries.C0318c;
import com.chartboost.sdk.Libraries.C0318c.C0317a;
import com.chartboost.sdk.Libraries.C0323e;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0328g.C0326a;
import com.chartboost.sdk.Libraries.C0328g.C0327k;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.google.android.gcm.GCMConstants;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xinclude.XIncludeHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.protocol.HTTP;

public class az {
    protected static C0400e f503b;
    private static C0321a f504h;
    protected C0321a f505a;
    private String f506c;
    private String f507d;
    private Map<String, Object> f508e;
    private Map<String, Object> f509f;
    private String f510g;
    private C0399c f511i;
    private boolean f512j;
    private boolean f513k;
    private C0326a f514l;
    private ba f515m;
    private int f516n;
    private boolean f517o;
    private boolean f518p;
    private C0466a f519q;

    /* renamed from: com.chartboost.sdk.impl.az.c */
    public interface C0399c {
        void m552a(C0321a c0321a, az azVar);

        void m553a(C0321a c0321a, az azVar, CBError cBError);
    }

    /* renamed from: com.chartboost.sdk.impl.az.e */
    class C0400e {
        public String f479a;
        public String f480b;
        public String f481c;
        public String f482d;
        public String f483e;
        public String f484f;
        public String f485g;
        public String f486h;
        public String f487i;
        public String f488j;
        public String f489k;
        public String f490l;
        public String f491m;
        public String f492n;
        public String f493o;
        public String f494p;
        public C0321a f495q;
        public String f496r;
        public String f497s;
        public String f498t;
        public boolean f499u;
        public String f500v;
        public Integer f501w;
        final /* synthetic */ az f502x;

        public C0400e(az azVar) {
            int width;
            int height;
            Throwable th;
            DisplayMetrics displayMetrics;
            int i;
            Object obj = null;
            int i2 = 0;
            this.f502x = azVar;
            this.f496r = StringUtils.EMPTY;
            this.f497s = StringUtils.EMPTY;
            this.f498t = StringUtils.EMPTY;
            Context y = C0351c.m378y();
            this.f493o = C0351c.m355e();
            if ("sdk".equals(Build.PRODUCT)) {
                this.f479a = "Android Simulator";
            } else {
                this.f479a = Build.MODEL;
            }
            this.f494p = Build.MANUFACTURER + " " + Build.MODEL;
            this.f480b = "Android " + VERSION.RELEASE;
            this.f481c = Locale.getDefault().getCountry();
            this.f482d = Locale.getDefault().getLanguage();
            this.f485g = "6.0.2";
            this.f491m = String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue());
            this.f492n = StringUtils.EMPTY + y.getResources().getDisplayMetrics().density;
            try {
                String packageName = y.getPackageName();
                this.f483e = y.getPackageManager().getPackageInfo(packageName, TransportMediator.FLAG_KEY_MEDIA_NEXT).versionName;
                this.f484f = packageName;
            } catch (Throwable e) {
                CBLogging.m78b("CBRequest", "Exception raised getting package mager object", e);
            }
            if (az.f504h == null) {
                TelephonyManager telephonyManager = (TelephonyManager) y.getSystemService("phone");
                if (telephonyManager == null || telephonyManager.getPhoneType() == 0) {
                    az.f504h = C0321a.m121a();
                } else {
                    Object obj2;
                    String simOperator = telephonyManager.getSimOperator();
                    if (TextUtils.isEmpty(simOperator)) {
                        obj2 = null;
                    } else {
                        obj2 = simOperator.substring(0, 3);
                        obj = simOperator.substring(3);
                    }
                    az.f504h = C0323e.m157a(C0323e.m158a("carrier-name", telephonyManager.getNetworkOperatorName()), C0323e.m158a("mobile-country-code", obj2), C0323e.m158a("mobile-network-code", obj), C0323e.m158a("iso-country-code", telephonyManager.getNetworkCountryIso()), C0323e.m158a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
                }
            }
            this.f495q = az.f504h;
            this.f496r = C0351c.m368o();
            if (C0351c.m343b() != null) {
                Object d = C0351c.m351d();
                if (!TextUtils.isEmpty(d)) {
                    this.f497s = d;
                }
                d = C0351c.m348c();
                if (!TextUtils.isEmpty(d)) {
                    this.f498t = d;
                }
            }
            this.f499u = CBUtility.m97f();
            this.f500v = CBUtility.m98g();
            this.f501w = ay.m544d();
            try {
                if (y instanceof Activity) {
                    Activity activity = (Activity) y;
                    Rect rect = new Rect();
                    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    width = rect.width();
                    try {
                        height = rect.height();
                        i2 = width;
                    } catch (Throwable e2) {
                        Throwable th2 = e2;
                        height = width;
                        th = th2;
                        CBLogging.m80c("CBRequest", "Exception getting activity size", th);
                        i2 = height;
                        height = 0;
                        displayMetrics = y.getResources().getDisplayMetrics();
                        width = displayMetrics.widthPixels;
                        i = displayMetrics.heightPixels;
                        this.f488j = StringUtils.EMPTY + width;
                        this.f489k = StringUtils.EMPTY + i;
                        this.f490l = StringUtils.EMPTY + displayMetrics.densityDpi;
                        i2 = width;
                        height = i;
                        this.f486h = StringUtils.EMPTY + i2;
                        this.f487i = StringUtils.EMPTY + height;
                    }
                }
                height = 0;
            } catch (Throwable e22) {
                th = e22;
                height = 0;
                CBLogging.m80c("CBRequest", "Exception getting activity size", th);
                i2 = height;
                height = 0;
                displayMetrics = y.getResources().getDisplayMetrics();
                width = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
                this.f488j = StringUtils.EMPTY + width;
                this.f489k = StringUtils.EMPTY + i;
                this.f490l = StringUtils.EMPTY + displayMetrics.densityDpi;
                i2 = width;
                height = i;
                this.f486h = StringUtils.EMPTY + i2;
                this.f487i = StringUtils.EMPTY + height;
            }
            displayMetrics = y.getResources().getDisplayMetrics();
            width = displayMetrics.widthPixels;
            i = displayMetrics.heightPixels;
            this.f488j = StringUtils.EMPTY + width;
            this.f489k = StringUtils.EMPTY + i;
            this.f490l = StringUtils.EMPTY + displayMetrics.densityDpi;
            if (i2 <= 0 || i2 > width) {
                i2 = width;
            }
            if (height <= 0 || height > i) {
                height = i;
            }
            this.f486h = StringUtils.EMPTY + i2;
            this.f487i = StringUtils.EMPTY + height;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.az.a */
    private static class C1049a implements C0399c {
        private C1051d f3536a;
        private C1050b f3537b;

        public C1049a(C1051d c1051d, C1050b c1050b) {
            this.f3536a = c1051d;
            this.f3537b = c1050b;
        }

        public void m3943a(C0321a c0321a, az azVar) {
            if (this.f3536a != null) {
                this.f3536a.m552a(c0321a, azVar);
            }
        }

        public void m3944a(C0321a c0321a, az azVar, CBError cBError) {
            if (this.f3537b != null) {
                this.f3537b.m553a(c0321a, azVar, cBError);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.az.b */
    public static abstract class C1050b implements C0399c {
    }

    /* renamed from: com.chartboost.sdk.impl.az.d */
    public static abstract class C1051d implements C0399c {
        public void m3945a(C0321a c0321a, az azVar, CBError cBError) {
        }
    }

    static {
        f504h = null;
    }

    public az(String str) {
        this.f511i = null;
        this.f512j = false;
        this.f513k = false;
        this.f514l = null;
        this.f517o = false;
        this.f518p = true;
        this.f519q = C0466a.NORMAL;
        this.f506c = str;
        this.f510g = HttpPost.METHOD_NAME;
        this.f515m = ba.m610a(C0351c.m378y());
        m558a(0);
        if (f503b == null) {
            f503b = new C0400e(this);
        }
    }

    protected void m557a() {
        if (this.f509f == null) {
            this.f509f = new HashMap();
        }
        this.f509f.put(XIncludeHandler.HTTP_ACCEPT, "application/json");
        this.f509f.put("X-Chartboost-Client", CBUtility.m95d());
        this.f509f.put("X-Chartboost-API", "6.0.2");
        this.f509f.put("X-Chartboost-Client", CBUtility.m95d());
    }

    protected String m569b() {
        return "application/json";
    }

    public void m565a(String str, Object obj) {
        if (this.f505a == null) {
            this.f505a = C0321a.m121a();
        }
        this.f505a.m128a(str, obj);
    }

    public void m566a(String str, String str2) {
        if (this.f509f == null) {
            this.f509f = new HashMap();
        }
        this.f509f.put(str, str2);
    }

    public void m564a(String str, C0321a c0321a) {
        if (c0321a != null && c0321a.m135c(str)) {
            m565a(str, c0321a.m138e(str));
        }
    }

    protected void m572c() {
        int i = 0;
        m565a(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT, f503b.f493o);
        m565a("model", f503b.f479a);
        m565a("device_type", f503b.f494p);
        m565a("os", f503b.f480b);
        m565a("country", f503b.f481c);
        m565a(SchemaSymbols.ATTVAL_LANGUAGE, f503b.f482d);
        m565a("sdk", f503b.f485g);
        m565a("timestamp", f503b.f491m);
        m565a("session", Integer.valueOf(CBUtility.m87a().getInt("cbPrefSessionCount", 0)));
        m565a("reachability", Integer.valueOf(ay.m543a().m547b()));
        m565a("scale", f503b.f492n);
        String str = "is_portrait";
        if (CBUtility.m93c().m165a()) {
            i = 1;
        }
        m565a(str, Integer.valueOf(i));
        m565a("bundle", f503b.f483e);
        m565a("bundle_id", f503b.f484f);
        m565a("carrier", f503b.f495q);
        m565a("custom_id", f503b.f496r);
        if (C0351c.m343b() != null) {
            if (!TextUtils.isEmpty(f503b.f493o)) {
                m565a("framework_version", f503b.f497s);
            }
            if (!TextUtils.isEmpty(f503b.f493o)) {
                m565a("wrapper_version", f503b.f498t);
            }
        }
        m565a("rooted_device", Boolean.valueOf(f503b.f499u));
        m565a("timezone", f503b.f500v);
        m565a("mobile_network", f503b.f501w);
        m565a("dw", f503b.f488j);
        m565a("dh", f503b.f489k);
        m565a("dpi", f503b.f490l);
        m565a("w", f503b.f486h);
        m565a("h", f503b.f487i);
        m565a(HTTP.IDENTITY_CODING, C0318c.m109b());
        C0317a c = C0318c.m111c();
        if (c.m105b()) {
            m565a("tracking", Integer.valueOf(c.m104a()));
        }
    }

    public void m574d() {
        String e = C0351c.m355e();
        String f = C0351c.m357f();
        f = C0315b.m103b(C0315b.m102a(String.format(Locale.US, "%s %s\n%s\n%s", new Object[]{this.f510g, m576e(), f, m577f()}).getBytes()));
        m566a("X-Chartboost-App", e);
        m566a("X-Chartboost-Signature", f);
    }

    public String m576e() {
        return m578g() + CBUtility.m88a(this.f508e);
    }

    public String m577f() {
        return this.f505a.toString();
    }

    public String m578g() {
        if (this.f506c == null) {
            return "/";
        }
        return (this.f506c.startsWith("/") ? StringUtils.EMPTY : "/") + this.f506c;
    }

    public void m563a(String str) {
        this.f506c = str;
    }

    public boolean m579h() {
        return m578g().equals("/api/track");
    }

    public C0321a m580i() {
        return this.f505a;
    }

    public Map<String, Object> m581j() {
        return this.f509f;
    }

    public boolean m582k() {
        return this.f513k;
    }

    public void m567a(boolean z) {
        this.f513k = z;
    }

    public C0326a m583l() {
        return this.f514l;
    }

    public boolean m584m() {
        return this.f517o;
    }

    public void m571b(boolean z) {
        this.f517o = z;
    }

    public void m559a(C0326a c0326a) {
        if (!C0328g.m184c(c0326a)) {
            CBLogging.m77b("CBRequest", "Validation predicate must be a dictionary style -- either VDictionary, VDictionaryExact, VDictionaryWithValues, or just a list of KV pairs.");
        }
        this.f514l = c0326a;
    }

    public void m568a(C0327k... c0327kArr) {
        this.f514l = C0328g.m176a(c0327kArr);
    }

    public void m570b(String str) {
        this.f507d = str;
    }

    public void m562a(C0466a c0466a) {
        this.f519q = c0466a;
    }

    public C0466a m585n() {
        return this.f519q;
    }

    public int m586o() {
        return this.f516n;
    }

    public void m558a(int i) {
        this.f516n = i;
    }

    public boolean m587p() {
        return this.f518p;
    }

    public void m573c(boolean z) {
        this.f518p = z;
    }

    public boolean m588q() {
        return this.f512j;
    }

    public void m575d(boolean z) {
        this.f512j = z;
    }

    public C0399c m589r() {
        return this.f511i;
    }

    public void m590s() {
        m561a(null, null);
    }

    public void m560a(C0399c c0399c) {
        if (!C0351c.m365l()) {
            this.f513k = false;
            this.f517o = false;
        }
        this.f511i = c0399c;
        m575d(true);
        this.f515m.m624a(this, c0399c);
    }

    public void m561a(C1051d c1051d, C1050b c1050b) {
        if (!C0351c.m365l()) {
            this.f513k = false;
            this.f517o = false;
        }
        m575d(true);
        this.f511i = new C1049a(c1051d, c1050b);
        this.f515m.m624a(this, this.f511i);
    }

    public static az m554a(C0321a c0321a) {
        try {
            az azVar = new az(c0321a.m138e(ClientCookie.PATH_ATTR));
            azVar.f510g = c0321a.m138e(OutputKeys.METHOD);
            azVar.f508e = c0321a.m127a("query").m141f();
            azVar.f505a = c0321a.m127a("body");
            azVar.f509f = c0321a.m127a("headers").m141f();
            azVar.f513k = c0321a.m149j("ensureDelivery");
            azVar.f507d = c0321a.m138e("eventType");
            azVar.f506c = c0321a.m138e(ClientCookie.PATH_ATTR);
            azVar.f516n = c0321a.m140f("retryCount");
            if (c0321a.m127a("callback") instanceof C0399c) {
                azVar.f511i = (C0399c) c0321a.m127a("callback");
            }
            return azVar;
        } catch (Throwable e) {
            CBLogging.m82d("CBRequest", "Unable to deserialize failed request", e);
            return null;
        }
    }

    public C0321a m591t() {
        return C0323e.m157a(C0323e.m158a(ClientCookie.PATH_ATTR, this.f506c), C0323e.m158a(OutputKeys.METHOD, this.f510g), C0323e.m158a("query", C0323e.m163a(this.f508e)), C0323e.m158a("body", this.f505a), C0323e.m158a("eventType", this.f507d), C0323e.m158a("headers", C0323e.m163a(this.f509f)), C0323e.m158a("ensureDelivery", Boolean.valueOf(this.f513k)), C0323e.m158a("retryCount", Integer.valueOf(this.f516n)), C0323e.m158a("callback", this.f511i));
    }
}
