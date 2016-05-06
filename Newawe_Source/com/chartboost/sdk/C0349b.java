package com.chartboost.sdk;

import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0328g.C0327k;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1020a;
import com.chartboost.sdk.impl.C0464i;
import com.chartboost.sdk.impl.C0467l;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.C0469m;
import com.chartboost.sdk.impl.C0472n;
import com.chartboost.sdk.impl.C0472n.C0470a;
import com.chartboost.sdk.impl.C0475s;
import com.chartboost.sdk.impl.C1061d;
import com.chartboost.sdk.impl.C1063h;
import com.chartboost.sdk.impl.C1066q;
import com.chartboost.sdk.impl.C1067r;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.az.C0399c;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bd;
import com.chartboost.sdk.impl.bd.C0416a;
import com.chartboost.sdk.impl.bv;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import mf.org.apache.xml.serialize.Method;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.b */
public class C0349b {
    public static volatile ArrayList<C0343a> f277a;
    public static C0349b f278b;
    private static final String f279c;
    private static C0330h f280d;
    private static C0469m f281e;
    private static C0348b f282f;
    private static C0348b f283g;
    private static AtomicInteger f284h;
    private static AtomicInteger f285i;
    private static String f286j;
    private static boolean f287k;
    private static long f288l;
    private static long f289m;
    private static int f290n;
    private static int f291o;
    private static ConcurrentHashMap<String, C0347a> f292p;
    private static ConcurrentHashMap<String, JSONArray> f293q;
    private static ConcurrentHashMap<String, String> f294r;
    private static ConcurrentHashMap<String, Integer> f295s;
    private static C0321a f296t;
    private static C0321a f297u;
    private static C0399c f298v;

    /* renamed from: com.chartboost.sdk.b.2 */
    static class C03462 implements Runnable {
        final /* synthetic */ C0343a f266a;

        C03462(C0343a c0343a) {
            this.f266a = c0343a;
        }

        public void run() {
            this.f266a.m286u().m419d(this.f266a);
        }
    }

    /* renamed from: com.chartboost.sdk.b.a */
    private static class C0347a {
        public String f267a;
        public String f268b;
        public String f269c;
        public String f270d;
        public C0321a f271e;
        public ArrayList<String> f272f;
        public boolean f273g;

        public C0347a(String str, String str2, String str3, String str4, C0321a c0321a) {
            this.f267a = str;
            this.f268b = str2;
            this.f269c = str3;
            this.f270d = str4;
            this.f271e = c0321a;
            this.f272f = new ArrayList();
            this.f272f.add(this.f267a);
            this.f273g = false;
        }
    }

    /* renamed from: com.chartboost.sdk.b.b */
    public enum C0348b {
        kCBInitial,
        kCBInProgress
    }

    /* renamed from: com.chartboost.sdk.b.1 */
    static class C10211 implements C0399c {
        C10211() {
        }

        public void m3776a(C0321a c0321a, az azVar) {
            C0349b.f282f = C0348b.kCBInitial;
            try {
                if (c0321a.m134c()) {
                    C0321a a = c0321a.m127a("ad_units");
                    CBLogging.m75a(C0349b.f279c, "Got Asset list for Web Prefetch from server :)" + c0321a);
                    C0349b.m300a(a, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            C1020a.m3746b("complete-success");
        }

        public void m3777a(C0321a c0321a, az azVar, CBError cBError) {
            try {
                C0349b.f282f = C0348b.kCBInitial;
                C1020a.m3746b("complete-failure");
                C0349b.m300a(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.b.c */
    private static class C1022c extends C0467l<Object> {
        private String f3411a;
        private long f3412b;
        private String f3413c;
        private C0347a f3414d;

        public C1022c(int i, String str, C1023d c1023d, String str2, C0347a c0347a) {
            super(i, str, c1023d);
            this.f3411a = str2;
            this.f3413c = str;
            this.f3412b = System.currentTimeMillis();
            this.f3414d = c0347a;
        }

        protected void m3783b(Object obj) {
        }

        protected C0472n<Object> m3782a(C0464i c0464i) {
            if (c0464i != null) {
                File k;
                File s;
                C1020a.m3755d(this.f3411a, Long.valueOf((System.currentTimeMillis() - this.f3412b) / 1000).toString(), this.f3413c);
                CBLogging.m75a(C0349b.f279c, "Asset download Success. Storing asset in cache" + this.f3411a);
                if (this.f3414d.f269c.equalsIgnoreCase("css")) {
                    k = C0349b.f280d.m220k();
                } else if (this.f3414d.f269c.equalsIgnoreCase("js")) {
                    k = C0349b.f280d.m221l();
                } else if (this.f3414d.f269c.equalsIgnoreCase("media")) {
                    k = C0349b.f280d.m222m();
                } else if (this.f3414d.f269c.contains(Method.HTML)) {
                    CharSequence a = C0349b.m305b(new String(c0464i.f730b, Charset.defaultCharset()));
                    k = C0349b.f280d.m226q();
                    if (TextUtils.isEmpty(a)) {
                        if (C0349b.f295s.containsKey(this.f3414d.f267a)) {
                            C0349b.f295s.remove(this.f3414d.f267a);
                        }
                        CBLogging.m77b(C0349b.f279c, "Error happened while injecting the html file, invalid injection params in the html or injection params missing in the prefetch");
                    } else {
                        C0349b.f294r.put(this.f3414d.f267a, a);
                    }
                } else {
                    k = null;
                }
                if (k != null) {
                    if (k != null) {
                        try {
                            bv.m762a(new File(k, this.f3414d.f268b), c0464i.f730b);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        s = C0349b.f280d.m228s();
                        if (s != null) {
                            Iterator it = this.f3414d.f272f.iterator();
                            while (it.hasNext()) {
                                File file = new File(s, (String) it.next());
                                if (!file.exists()) {
                                    file.mkdir();
                                }
                                try {
                                    bv.m762a(new File(file, this.f3414d.f268b.split("\\.(?=[^\\.]+$)")[0]), this.f3414d.f271e.toString().getBytes());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (C0349b.f295s.containsKey(this.f3414d.f267a)) {
                    int intValue = ((Integer) C0349b.f295s.get(this.f3414d.f267a)).intValue() - 1;
                    if (intValue > 0) {
                        C0349b.f295s.put(this.f3414d.f267a, Integer.valueOf(intValue));
                    } else {
                        ArrayList d;
                        C0349b.f295s.remove(this.f3414d.f267a);
                        s = C0349b.f280d.m229t();
                        File u = C0349b.f280d.m230u();
                        if (C0330h.m199i()) {
                            d = C0330h.m193d(u);
                        } else {
                            d = C0330h.m193d(s);
                        }
                        if (!(d == null || d.isEmpty() || !d.contains(this.f3414d.f267a))) {
                            d.remove(this.f3414d.f267a);
                        }
                        if (C0330h.m199i()) {
                            CBLogging.m83e(C0349b.f279c, "##### Serializing blacklist ad id to " + u);
                            C0330h.m189a(d, u, false);
                        } else {
                            CBLogging.m83e(C0349b.f279c, "##### Serializing blacklist ad id to " + s);
                            C0330h.m189a(d, s, false);
                        }
                    }
                }
            }
            CBLogging.m75a(C0349b.f279c, "Current Download count:" + C0349b.f284h.get());
            if (C0349b.f284h.incrementAndGet() == C0349b.f285i.get()) {
                CBLogging.m83e(C0349b.f279c, "##### Success Response callback : Asset Prefetch Download Complete");
                C0349b.f284h.set(0);
                C0349b.f285i.set(0);
                C0349b.f283g = C0348b.kCBInitial;
                C0349b.m321o();
            }
            return C0472n.m826a(null, null);
        }

        public C0466a m3784s() {
            return C0466a.LOW;
        }
    }

    /* renamed from: com.chartboost.sdk.b.d */
    private static class C1023d implements C0470a {
        private C1022c f3415a;

        private C1023d() {
        }

        public void m3786a(C0475s c0475s) {
            synchronized (C0349b.m298a()) {
                try {
                    if ((c0475s instanceof C1067r) || (c0475s instanceof C1066q) || (c0475s instanceof C1063h)) {
                        if (this.f3415a != null) {
                            C1020a.m3752c(this.f3415a.f3411a, Long.valueOf((System.currentTimeMillis() - this.f3415a.f3412b) / 1000).toString(), this.f3415a.f3413c, c0475s.getMessage());
                        }
                        CBLogging.m77b(C0349b.f279c, "Error downloading asset " + c0475s.getMessage() + this.f3415a.f3411a);
                    }
                    C0347a d = this.f3415a.f3414d;
                    if (C0349b.f295s.containsKey(d.f267a)) {
                        C0349b.f295s.remove(d.f267a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (C0349b.f284h.incrementAndGet() == C0349b.f285i.get()) {
                    CBLogging.m83e(C0349b.f279c, "##### Failure response callback : Asset Prefetch Download Complete");
                    C0349b.f284h.set(0);
                    C0349b.f285i.set(0);
                    C0349b.f283g = C0348b.kCBInitial;
                    C0349b.m321o();
                }
            }
        }
    }

    static {
        f279c = C0349b.class.getSimpleName();
        f284h = new AtomicInteger();
        f285i = new AtomicInteger();
        f286j = "blacklist";
        f287k = false;
        f290n = 7;
        f291o = 10;
        f298v = new C10211();
    }

    private C0349b() {
    }

    public static synchronized C0349b m298a() {
        C0349b c0349b;
        synchronized (C0349b.class) {
            if (f278b == null) {
                f278b = new C0349b();
                C0349b.m320n();
            }
            c0349b = f278b;
        }
        return c0349b;
    }

    private static synchronized void m320n() {
        synchronized (C0349b.class) {
            if (!f287k) {
                f287k = true;
                f280d = new C0330h(true);
                f282f = C0348b.kCBInitial;
                f283g = C0348b.kCBInitial;
                f281e = ba.m610a(C0351c.m378y()).m623a();
                f277a = new ArrayList();
                f288l = C0330h.m200w();
                f289m = C0330h.m201x();
                f290n = C0351c.m326D();
                f291o = C0351c.m327E();
                f295s = new ConcurrentHashMap();
                f294r = new ConcurrentHashMap();
            }
        }
    }

    public static synchronized void m306b() {
        synchronized (C0349b.class) {
            synchronized (f278b) {
                if (C0351c.m377x()) {
                    CBLogging.m75a(f279c, "Prefetching the asset list");
                    if (C0348b.kCBInProgress == f282f || C0348b.kCBInProgress == f283g) {
                    } else {
                        f282f = C0348b.kCBInProgress;
                        f285i.set(0);
                        f284h.set(0);
                        bd bdVar = new bd(C0351c.m325C());
                        C0349b.m303b(false);
                        bdVar.m3959a("ad_units", f297u, C0416a.AD);
                        C0327k[] c0327kArr = new C0327k[2];
                        c0327kArr[0] = C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a);
                        C0327k[] c0327kArr2 = new C0327k[2];
                        c0327kArr2[0] = C0328g.m178a("id", C0328g.m172a());
                        c0327kArr2[1] = C0328g.m178a("elements", C0328g.m180b(C0328g.m176a(C0328g.m178a("type", C0328g.m172a()), C0328g.m178a("name", C0328g.m172a()), C0328g.m178a("value", C0328g.m172a()))));
                        c0327kArr[1] = C0328g.m178a("ad_units", C0328g.m180b(C0328g.m176a(c0327kArr2)));
                        bdVar.m559a(C0328g.m176a(c0327kArr));
                        bdVar.m571b(true);
                        bdVar.m560a(f298v);
                        C1020a.m3753d();
                        C1020a.m3743a(C0330h.m202y().m139e());
                    }
                }
            }
        }
    }

    private static void m307b(C0321a c0321a) {
        int i;
        int o = c0321a.m154o();
        if (o > f291o) {
            i = f291o;
        } else {
            i = o;
        }
        for (int i2 = 0; i2 < i; i2++) {
            C0321a c = c0321a.m133c(i2);
            Object e = c.m138e("id");
            C0321a a = c.m127a("elements");
            JSONArray jSONArray = new JSONArray();
            if (!(TextUtils.isEmpty(e) || a == null || a.m154o() <= 0)) {
                for (int i3 = 0; i3 < a.m154o(); i3++) {
                    C0321a c2 = a.m133c(i3);
                    String e2 = c2.m138e("type");
                    Object e3 = c2.m138e("name");
                    Object e4 = c2.m138e("value");
                    if (!(TextUtils.isEmpty(e2) || TextUtils.isEmpty(e3) || TextUtils.isEmpty(e4))) {
                        C0347a c0347a;
                        if (f292p.containsKey(e3)) {
                            c0347a = (C0347a) f292p.get(e3);
                            c0347a.f272f.add(e);
                            f292p.put(e3, c0347a);
                        } else {
                            c0347a = new C0347a(e, e3, e2, e4, c2);
                            if (e2.equals("param")) {
                                File file = new File(f280d.m228s(), c0347a.f267a);
                                if (!file.exists()) {
                                    file.mkdir();
                                }
                                try {
                                    bv.m762a(new File(file, c0347a.f268b.split("\\.(?=[^\\.]+$)")[0]), c0347a.f271e.toString().getBytes());
                                } catch (IOException e5) {
                                    CBLogging.m77b(f279c, "Failed to add the injection values into the log");
                                    e5.printStackTrace();
                                }
                                jSONArray.put(c0347a);
                            } else {
                                f292p.put(e3, c0347a);
                            }
                        }
                    }
                }
                if (jSONArray.length() > 0 && !f293q.containsKey(e)) {
                    f293q.put(e, jSONArray);
                }
            }
        }
    }

    public static void m309c() {
        C0349b.m303b(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m300a(com.chartboost.sdk.Libraries.C0323e.C0321a r12, boolean r13) {
        /*
        r4 = f278b;	 Catch:{ Exception -> 0x013e }
        monitor-enter(r4);	 Catch:{ Exception -> 0x013e }
        r0 = f294r;	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x0014;
    L_0x0007:
        r0 = f294r;	 Catch:{ all -> 0x013b }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x013b }
        if (r0 != 0) goto L_0x0014;
    L_0x000f:
        r0 = f294r;	 Catch:{ all -> 0x013b }
        r0.clear();	 Catch:{ all -> 0x013b }
    L_0x0014:
        com.chartboost.sdk.Tracking.C1020a.m3756e();	 Catch:{ all -> 0x013b }
        r0 = new java.util.concurrent.ConcurrentHashMap;	 Catch:{ all -> 0x013b }
        r0.<init>();	 Catch:{ all -> 0x013b }
        f292p = r0;	 Catch:{ all -> 0x013b }
        r0 = new java.util.concurrent.ConcurrentHashMap;	 Catch:{ all -> 0x013b }
        r0.<init>();	 Catch:{ all -> 0x013b }
        f293q = r0;	 Catch:{ all -> 0x013b }
        r0 = f296t;	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x003e;
    L_0x0029:
        r0 = f296t;	 Catch:{ all -> 0x013b }
        r0 = r0.m134c();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x003e;
    L_0x0031:
        r0 = f296t;	 Catch:{ all -> 0x013b }
        r0 = r0.m154o();	 Catch:{ all -> 0x013b }
        if (r0 <= 0) goto L_0x003e;
    L_0x0039:
        r0 = f296t;	 Catch:{ all -> 0x013b }
        com.chartboost.sdk.C0349b.m307b(r0);	 Catch:{ all -> 0x013b }
    L_0x003e:
        if (r12 == 0) goto L_0x004f;
    L_0x0040:
        r0 = r12.m134c();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x004f;
    L_0x0046:
        r0 = r12.m154o();	 Catch:{ all -> 0x013b }
        if (r0 <= 0) goto L_0x004f;
    L_0x004c:
        com.chartboost.sdk.C0349b.m307b(r12);	 Catch:{ all -> 0x013b }
    L_0x004f:
        r5 = com.chartboost.sdk.Libraries.C0330h.m191b();	 Catch:{ all -> 0x013b }
        r0 = f280d;	 Catch:{ all -> 0x013b }
        r6 = r0.m229t();	 Catch:{ all -> 0x013b }
        r0 = f280d;	 Catch:{ all -> 0x013b }
        r7 = r0.m230u();	 Catch:{ all -> 0x013b }
        r0 = com.chartboost.sdk.Libraries.C0330h.m199i();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x0147;
    L_0x0065:
        r0 = com.chartboost.sdk.Libraries.C0330h.m193d(r7);	 Catch:{ all -> 0x013b }
        r3 = r0;
    L_0x006a:
        if (r5 == 0) goto L_0x0165;
    L_0x006c:
        r0 = r5.isEmpty();	 Catch:{ all -> 0x013b }
        if (r0 != 0) goto L_0x0165;
    L_0x0072:
        r0 = f292p;	 Catch:{ all -> 0x013b }
        r0 = r0.keySet();	 Catch:{ all -> 0x013b }
        r8 = r0.iterator();	 Catch:{ all -> 0x013b }
    L_0x007c:
        r0 = r8.hasNext();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x0165;
    L_0x0082:
        r0 = r8.next();	 Catch:{ all -> 0x013b }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x013b }
        r1 = r5.containsKey(r0);	 Catch:{ all -> 0x013b }
        if (r1 == 0) goto L_0x007c;
    L_0x008e:
        r1 = r5.get(r0);	 Catch:{ all -> 0x013b }
        r1 = (java.io.File) r1;	 Catch:{ all -> 0x013b }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x013b }
        r1.setLastModified(r10);	 Catch:{ all -> 0x013b }
        r1 = f292p;	 Catch:{ all -> 0x013b }
        r1 = r1.get(r0);	 Catch:{ all -> 0x013b }
        r1 = (com.chartboost.sdk.C0349b.C0347a) r1;	 Catch:{ all -> 0x013b }
        r2 = r1.f269c;	 Catch:{ all -> 0x013b }
        r9 = "html";
        r2 = r2.equals(r9);	 Catch:{ all -> 0x013b }
        if (r2 == 0) goto L_0x00dd;
    L_0x00ad:
        r2 = com.chartboost.sdk.Libraries.C0330h.C0329a.Html;	 Catch:{ all -> 0x013b }
        r2 = com.chartboost.sdk.Libraries.C0330h.m186a(r2, r0);	 Catch:{ all -> 0x013b }
        r9 = "";
        if (r2 == 0) goto L_0x0156;
    L_0x00b7:
        r9 = r2.exists();	 Catch:{ all -> 0x013b }
        if (r9 == 0) goto L_0x0156;
    L_0x00bd:
        r9 = new java.lang.String;	 Catch:{ all -> 0x013b }
        r10 = f280d;	 Catch:{ all -> 0x013b }
        r2 = r10.m211b(r2);	 Catch:{ all -> 0x013b }
        r10 = java.nio.charset.Charset.defaultCharset();	 Catch:{ all -> 0x013b }
        r9.<init>(r2, r10);	 Catch:{ all -> 0x013b }
        r2 = com.chartboost.sdk.C0349b.m305b(r9);	 Catch:{ all -> 0x013b }
        r9 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x013b }
        if (r9 != 0) goto L_0x014e;
    L_0x00d6:
        r9 = f294r;	 Catch:{ all -> 0x013b }
        r10 = r1.f267a;	 Catch:{ all -> 0x013b }
        r9.put(r10, r2);	 Catch:{ all -> 0x013b }
    L_0x00dd:
        if (r1 == 0) goto L_0x007c;
    L_0x00df:
        r2 = r1.f267a;	 Catch:{ all -> 0x013b }
        r2 = r3.contains(r2);	 Catch:{ all -> 0x013b }
        if (r2 != 0) goto L_0x007c;
    L_0x00e7:
        r2 = r1.f272f;	 Catch:{ Exception -> 0x0120 }
        r9 = r2.iterator();	 Catch:{ Exception -> 0x0120 }
    L_0x00ed:
        r2 = r9.hasNext();	 Catch:{ Exception -> 0x0120 }
        if (r2 == 0) goto L_0x015e;
    L_0x00f3:
        r2 = r9.next();	 Catch:{ Exception -> 0x0120 }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x0120 }
        r10 = new java.io.File;	 Catch:{ Exception -> 0x0120 }
        r11 = f280d;	 Catch:{ Exception -> 0x0120 }
        r11 = r11.m228s();	 Catch:{ Exception -> 0x0120 }
        r10.<init>(r11, r2);	 Catch:{ Exception -> 0x0120 }
        r2 = r10.exists();	 Catch:{ Exception -> 0x0120 }
        if (r2 != 0) goto L_0x010d;
    L_0x010a:
        r10.mkdir();	 Catch:{ Exception -> 0x0120 }
    L_0x010d:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0120 }
        r2.<init>(r10, r0);	 Catch:{ Exception -> 0x0120 }
        r10 = r1.f271e;	 Catch:{ Exception -> 0x0120 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x0120 }
        r10 = r10.getBytes();	 Catch:{ Exception -> 0x0120 }
        com.chartboost.sdk.impl.bv.m762a(r2, r10);	 Catch:{ Exception -> 0x0120 }
        goto L_0x00ed;
    L_0x0120:
        r1 = move-exception;
        r1 = f279c;	 Catch:{ all -> 0x013b }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013b }
        r2.<init>();	 Catch:{ all -> 0x013b }
        r9 = "Error writing asset file log information for";
        r2 = r2.append(r9);	 Catch:{ all -> 0x013b }
        r0 = r2.append(r0);	 Catch:{ all -> 0x013b }
        r0 = r0.toString();	 Catch:{ all -> 0x013b }
        com.chartboost.sdk.Libraries.CBLogging.m77b(r1, r0);	 Catch:{ all -> 0x013b }
        goto L_0x007c;
    L_0x013b:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x013b }
        throw r0;	 Catch:{ Exception -> 0x013e }
    L_0x013e:
        r0 = move-exception;
        r0 = f279c;
        r1 = "Error while syncrhonizing assets";
        com.chartboost.sdk.Libraries.CBLogging.m77b(r0, r1);
    L_0x0146:
        return;
    L_0x0147:
        r0 = com.chartboost.sdk.Libraries.C0330h.m193d(r6);	 Catch:{ all -> 0x013b }
        r3 = r0;
        goto L_0x006a;
    L_0x014e:
        r2 = f279c;	 Catch:{ all -> 0x013b }
        r9 = "Error happened while injection on updating the html file in cache";
        com.chartboost.sdk.Libraries.CBLogging.m77b(r2, r9);	 Catch:{ all -> 0x013b }
        goto L_0x00dd;
    L_0x0156:
        r2 = f279c;	 Catch:{ all -> 0x013b }
        r9 = "Html file path doesn't exist";
        com.chartboost.sdk.Libraries.CBLogging.m77b(r2, r9);	 Catch:{ all -> 0x013b }
        goto L_0x00dd;
    L_0x015e:
        r1 = f292p;	 Catch:{ Exception -> 0x0120 }
        r1.remove(r0);	 Catch:{ Exception -> 0x0120 }
        goto L_0x007c;
    L_0x0165:
        r0 = com.chartboost.sdk.Libraries.C0330h.m199i();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x0170;
    L_0x016b:
        if (r13 == 0) goto L_0x0170;
    L_0x016d:
        com.chartboost.sdk.C0349b.m301a(r5);	 Catch:{ all -> 0x013b }
    L_0x0170:
        r0 = f292p;	 Catch:{ all -> 0x013b }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x013b }
        if (r0 != 0) goto L_0x0220;
    L_0x0178:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x013b }
        r2.<init>();	 Catch:{ all -> 0x013b }
        r0 = f292p;	 Catch:{ all -> 0x013b }
        r0 = r0.entrySet();	 Catch:{ all -> 0x013b }
        r3 = r0.iterator();	 Catch:{ all -> 0x013b }
    L_0x0187:
        r0 = r3.hasNext();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x01ea;
    L_0x018d:
        r0 = r3.next();	 Catch:{ all -> 0x013b }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x013b }
        r0 = r0.getValue();	 Catch:{ all -> 0x013b }
        r0 = (com.chartboost.sdk.C0349b.C0347a) r0;	 Catch:{ all -> 0x013b }
        r1 = f295s;	 Catch:{ all -> 0x013b }
        r5 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1 = r1.containsKey(r5);	 Catch:{ all -> 0x013b }
        if (r1 != 0) goto L_0x01ce;
    L_0x01a3:
        r1 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1 = r2.contains(r1);	 Catch:{ all -> 0x013b }
        if (r1 != 0) goto L_0x01b0;
    L_0x01ab:
        r1 = r0.f267a;	 Catch:{ all -> 0x013b }
        r2.add(r1);	 Catch:{ all -> 0x013b }
    L_0x01b0:
        r1 = f295s;	 Catch:{ all -> 0x013b }
        r5 = r0.f267a;	 Catch:{ all -> 0x013b }
        r8 = 1;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x013b }
        r1.put(r5, r8);	 Catch:{ all -> 0x013b }
        r1 = f294r;	 Catch:{ all -> 0x013b }
        r5 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1 = r1.containsKey(r5);	 Catch:{ all -> 0x013b }
        if (r1 == 0) goto L_0x0187;
    L_0x01c6:
        r1 = f294r;	 Catch:{ all -> 0x013b }
        r0 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1.remove(r0);	 Catch:{ all -> 0x013b }
        goto L_0x0187;
    L_0x01ce:
        r1 = f295s;	 Catch:{ all -> 0x013b }
        r5 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1 = r1.get(r5);	 Catch:{ all -> 0x013b }
        r1 = (java.lang.Integer) r1;	 Catch:{ all -> 0x013b }
        r1 = r1.intValue();	 Catch:{ all -> 0x013b }
        r1 = r1 + 1;
        r5 = f295s;	 Catch:{ all -> 0x013b }
        r0 = r0.f267a;	 Catch:{ all -> 0x013b }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x013b }
        r5.put(r0, r1);	 Catch:{ all -> 0x013b }
        goto L_0x0187;
    L_0x01ea:
        r0 = com.chartboost.sdk.Libraries.C0330h.m199i();	 Catch:{ all -> 0x013b }
        if (r0 == 0) goto L_0x0223;
    L_0x01f0:
        r0 = f279c;	 Catch:{ all -> 0x013b }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013b }
        r1.<init>();	 Catch:{ all -> 0x013b }
        r3 = "##### Serializing blacklist ad id to ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x013b }
        r1 = r1.append(r7);	 Catch:{ all -> 0x013b }
        r1 = r1.toString();	 Catch:{ all -> 0x013b }
        com.chartboost.sdk.Libraries.CBLogging.m83e(r0, r1);	 Catch:{ all -> 0x013b }
        r0 = 1;
        com.chartboost.sdk.Libraries.C0330h.m189a(r2, r7, r0);	 Catch:{ all -> 0x013b }
    L_0x020c:
        r0 = com.chartboost.sdk.C0349b.C0348b.kCBInProgress;	 Catch:{ all -> 0x013b }
        f283g = r0;	 Catch:{ all -> 0x013b }
        r0 = f285i;	 Catch:{ all -> 0x013b }
        r1 = f292p;	 Catch:{ all -> 0x013b }
        r1 = r1.size();	 Catch:{ all -> 0x013b }
        r0.set(r1);	 Catch:{ all -> 0x013b }
        r0 = f292p;	 Catch:{ all -> 0x013b }
        com.chartboost.sdk.C0349b.m308b(r0);	 Catch:{ all -> 0x013b }
    L_0x0220:
        monitor-exit(r4);	 Catch:{ all -> 0x013b }
        goto L_0x0146;
    L_0x0223:
        r0 = f279c;	 Catch:{ all -> 0x013b }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013b }
        r1.<init>();	 Catch:{ all -> 0x013b }
        r3 = "##### Serializing blacklist ad id to ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x013b }
        r1 = r1.append(r6);	 Catch:{ all -> 0x013b }
        r1 = r1.toString();	 Catch:{ all -> 0x013b }
        com.chartboost.sdk.Libraries.CBLogging.m83e(r0, r1);	 Catch:{ all -> 0x013b }
        r0 = 1;
        com.chartboost.sdk.Libraries.C0330h.m189a(r2, r6, r0);	 Catch:{ all -> 0x013b }
        goto L_0x020c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.b.a(com.chartboost.sdk.Libraries.e$a, boolean):void");
    }

    private static long m295a(ConcurrentHashMap<String, C0347a> concurrentHashMap) {
        long j = 0;
        int i = 0;
        for (Entry entry : concurrentHashMap.entrySet()) {
            int i2;
            long j2;
            if (!((C0347a) entry.getValue()).f269c.equals("media")) {
                i2 = i;
                j2 = j + 15000;
            } else if (((C0347a) entry.getValue()).f268b.contains("mp4")) {
                i2 = 1255000 + i;
                j2 = j;
            } else {
                i2 = 125000 + i;
                j2 = j;
            }
            j = j2;
            i = i2;
        }
        return j;
    }

    private static synchronized void m308b(ConcurrentHashMap<String, C0347a> concurrentHashMap) {
        synchronized (C0349b.class) {
            for (C0347a c0347a : concurrentHashMap.values()) {
                C1023d c1023d = new C1023d();
                C0467l c1022c = new C1022c(0, c0347a.f270d, c1023d, c0347a.f268b, c0347a);
                c1023d.f3415a = c1022c;
                c1022c.m785a((Object) Integer.valueOf(f278b.hashCode()));
                c1022c.m784a(new C1061d(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                f281e.m815a(c1022c);
                C1020a.m3736a(c0347a.f270d, c0347a.f268b);
                CBLogging.m75a(f279c, "Downloading Asset:" + c0347a.f270d);
            }
        }
    }

    private static String m305b(String str) {
        if (f293q.isEmpty() || str.isEmpty()) {
            return null;
        }
        for (Entry value : f293q.entrySet()) {
            JSONArray jSONArray = (JSONArray) value.getValue();
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        C0347a c0347a = (C0347a) jSONArray.get(i);
                        String str2 = c0347a.f268b;
                        str = str.replaceAll(Pattern.quote(str2), c0347a.f270d);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CBLogging.m77b(f279c, "Error while injecting values into the html");
                    }
                }
            }
        }
        if (!str.contains("{{") && !str.contains("}}")) {
            return str;
        }
        CBLogging.m77b(f279c, " Html data still contains mustache injection values, cannot load the web view ad");
        return null;
    }

    public static synchronized C0321a m296a(boolean z) {
        C0321a a;
        synchronized (C0349b.class) {
            Object jSONArray = new JSONArray();
            try {
                if (f294r.isEmpty() && z) {
                    C0349b.m309c();
                    C0349b.m300a(null, true);
                }
                if (!f294r.isEmpty()) {
                    for (String str : f294r.keySet()) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("id", str);
                            jSONArray.put(jSONObject);
                        } catch (Exception e) {
                            CBLogging.m77b(f279c, "getAvailableAdIdList(): Error while loading ad id into json array");
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a = C0321a.m122a(jSONArray);
        }
        return a;
    }

    public static ConcurrentHashMap<String, String> m310d() {
        return f294r;
    }

    public static C0321a m303b(boolean z) {
        Object jSONArray = new JSONArray();
        Object jSONArray2 = new JSONArray();
        File t = f280d.m229t();
        File u = f280d.m230u();
        File s = f280d.m228s();
        ArrayList d;
        if (C0330h.m199i()) {
            d = C0330h.m193d(u);
        } else {
            d = C0330h.m193d(t);
        }
        if (s != null) {
            File[] listFiles = s.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File t2 : listFiles) {
                    try {
                        if (t2.isDirectory()) {
                            C0321a a = C0321a.m121a();
                            C0321a a2 = C0321a.m121a();
                            JSONArray jSONArray3 = new JSONArray();
                            String name = t2.getName();
                            if (TextUtils.isEmpty(name) || r2.contains(name)) {
                                CBLogging.m77b(f279c, "Black list Ad id found: " + name);
                            } else {
                                a2.m128a("id", name);
                                jSONArray2.put(a2.m139e());
                                String[] list = t2.list();
                                if (list != null && list.length > 0) {
                                    File file = new File(s, name);
                                    for (String file2 : list) {
                                        byte[] b = f280d.m211b(new File(file, file2));
                                        if (b != null) {
                                            jSONArray3.put(C0321a.m123k(new String(b)).m139e());
                                        }
                                    }
                                    if (jSONArray3.length() > 0) {
                                        a.m128a("id", name);
                                        a.m128a("elements", jSONArray3);
                                        jSONArray.put(a.m139e());
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        CBLogging.m77b(f279c, e.getMessage());
                    }
                }
            }
        }
        f296t = C0321a.m122a(jSONArray);
        f297u = C0321a.m122a(jSONArray2);
        if (z) {
            return f296t;
        }
        return f297u;
    }

    public static synchronized void m311e() {
        synchronized (C0349b.class) {
            if (f278b != null) {
                f281e.m818a(Integer.valueOf(f278b.hashCode()));
            }
        }
    }

    public static boolean m302a(C0321a c0321a) {
        if (c0321a == null || c0321a.m154o() == 0) {
            return false;
        }
        CharSequence e = c0321a.m138e("ad_unit_id");
        if (TextUtils.isEmpty(e)) {
            CBLogging.m77b(f279c, "Cannot find ad_unit_id. ad_unit_id is empty or null in the response");
            return false;
        } else if (!f294r.containsKey(e)) {
            return false;
        } else {
            CBLogging.m75a(f279c, "Found the ad id in the html mapping object.");
            return true;
        }
    }

    public static synchronized boolean m312f() {
        boolean z;
        synchronized (C0349b.class) {
            if (f282f == C0348b.kCBInProgress || f283g == C0348b.kCBInProgress) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    private static void m321o() {
        C0349b.m303b(true);
        Iterator it;
        C0343a c0343a;
        if (f297u.m134c() && f297u.m154o() > 0 && f277a.size() > 0) {
            it = f277a.iterator();
            while (it.hasNext()) {
                c0343a = (C0343a) it.next();
                CBUtility.m96e().post(new C03462(c0343a));
                f277a.remove(c0343a);
            }
        } else if (f277a.size() > 0) {
            it = f277a.iterator();
            while (it.hasNext()) {
                c0343a = (C0343a) it.next();
                c0343a.m286u().m406a(c0343a, CBImpressionError.EMPTY_LOCAL_AD_LIST);
            }
            f277a.clear();
        }
    }

    private static synchronized void m301a(HashMap<String, File> hashMap) {
        synchronized (C0349b.class) {
            ArrayList arrayList = new ArrayList(hashMap.values());
            ArrayList arrayList2 = new ArrayList();
            if (!arrayList.isEmpty()) {
                long a = C0349b.m295a(f292p);
                f288l = C0330h.m200w();
                f289m = C0330h.m201x();
                CBLogging.m75a(f279c, "Total local file count:" + arrayList.size());
                CBLogging.m75a(f279c, "Total max cache size available :" + Long.toString(f288l));
                CBLogging.m75a(f279c, "Total space occupied:" + Long.toString(f289m));
                CBLogging.m75a(f279c, "Estimated Cache Size needed:" + Long.toString(a));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    File file = (File) it.next();
                    if (C0330h.m190a(file, f290n) || f288l < a) {
                        a -= file.length();
                        CBLogging.m75a(f279c, "Deleting file at path:" + file.getPath());
                        file.delete();
                        File s = f280d.m228s();
                        if (s != null) {
                            File[] listFiles = s.listFiles();
                            if (listFiles != null && listFiles.length > 0) {
                                for (File s2 : listFiles) {
                                    arrayList2.add(s2.getName());
                                    if (f294r.containsKey(arrayList2)) {
                                        f294r.remove(arrayList2);
                                    }
                                    File[] listFiles2 = s2.listFiles();
                                    if (listFiles2 != null && listFiles2.length > 0) {
                                        for (File file2 : listFiles2) {
                                            if (file2.getName().equalsIgnoreCase(file.getName().split("\\.(?=[^\\.]+$)")[0])) {
                                                CBLogging.m75a(f279c, "Deleting log file info:" + file2);
                                                file2.delete();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    a = a;
                }
            }
            if (C0330h.m199i()) {
                C0330h.m189a(arrayList2, f280d.m230u(), true);
            } else {
                C0330h.m189a(arrayList2, f280d.m229t(), true);
            }
        }
    }
}
