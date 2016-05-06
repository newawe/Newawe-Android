package com.jirbo.adcolony;

import android.content.Intent;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.C0807n.ad;
import com.jirbo.adcolony.aa.C0746a;
import java.util.ArrayList;

/* renamed from: com.jirbo.adcolony.d */
class C0762d {
    C0760c f2141a;
    C1246b f2142b;
    C1255o f2143c;
    C1256t f2144d;
    C0814u f2145e;
    ADCStorage f2146f;
    ag f2147g;
    ArrayList<C0775j> f2148h;
    ArrayList<C0775j> f2149i;
    volatile boolean f2150j;
    boolean f2151k;
    boolean f2152l;
    C0746a f2153m;

    /* renamed from: com.jirbo.adcolony.d.1 */
    class C07611 implements Runnable {
        final /* synthetic */ C0775j f2139a;
        final /* synthetic */ C0762d f2140b;

        C07611(C0762d c0762d, C0775j c0775j) {
            this.f2140b = c0762d;
            this.f2139a = c0775j;
        }

        public void run() {
            synchronized (this.f2140b.f2148h) {
                if (C0745a.m2155d()) {
                    this.f2140b.f2148h.add(this.f2139a);
                    if (!this.f2140b.f2150j) {
                        this.f2140b.m2273g();
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.d.2 */
    class C12472 extends C0775j {
        final /* synthetic */ C0762d f4001a;

        C12472(C0762d c0762d, C0762d c0762d2) {
            this.f4001a = c0762d;
            super(c0762d2);
        }

        void m4708a() {
            this.o.f2145e.m2504c();
        }
    }

    /* renamed from: com.jirbo.adcolony.d.3 */
    class C12483 extends C0775j {
        final /* synthetic */ C0762d f4002a;

        C12483(C0762d c0762d, C0762d c0762d2) {
            this.f4002a = c0762d;
            super(c0762d2);
        }

        void m4709a() {
            this.o.f2145e.m2505d();
        }
    }

    /* renamed from: com.jirbo.adcolony.d.4 */
    class C12494 extends C0775j {
        final /* synthetic */ C0762d f4003a;

        C12494(C0762d c0762d, C0762d c0762d2) {
            this.f4003a = c0762d;
            super(c0762d2);
        }

        void m4710a() {
            this.o.f2145e.m2506e();
        }
    }

    /* renamed from: com.jirbo.adcolony.d.5 */
    class C12505 extends C0775j {
        final /* synthetic */ AdColonyAd f4004a;
        final /* synthetic */ C0762d f4005b;

        C12505(C0762d c0762d, C0762d c0762d2, AdColonyAd adColonyAd) {
            this.f4005b = c0762d;
            this.f4004a = adColonyAd;
            super(c0762d2);
        }

        void m4711a() {
            if (AdColony.isZoneV4VC(this.f4004a.f1841h) || this.f4004a.f1843j.f2255A == null || !this.f4004a.f1843j.f2255A.f2458a || (this.f4004a.f1843j.f2255A.f2458a && this.f4004a.f1854u && this.f4004a.f1845l.equals("fullscreen"))) {
                this.f4005b.m2252a("start", "{\"ad_slot\":" + (C0745a.f2001l.f2145e.f2599j + 1) + ", \"replay\":" + this.f4004a.f1854u + "}", this.f4004a);
                this.f4004a.f1843j.f2273q = true;
                this.f4004a.f1843j.f2274r = true;
                C0745a.m2163h();
                this.f4004a.f1842i.f2313j.m4613a(this.f4004a.f1843j.f2257a);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.d.6 */
    class C12516 extends C0775j {
        final /* synthetic */ AdColonyAd f4006a;
        final /* synthetic */ double f4007b;
        final /* synthetic */ C0762d f4008c;

        C12516(C0762d c0762d, C0762d c0762d2, AdColonyAd adColonyAd, double d) {
            this.f4008c = c0762d;
            this.f4006a = adColonyAd;
            this.f4007b = d;
            super(c0762d2);
        }

        void m4712a() {
            if (!C0745a.f2004o && this.f4006a.f1843j.f2279w != null && this.f4006a.f1843j.f2279w.f2361a && this.f4007b > 0.9d) {
                C0777l.f2241c.m2353b((Object) "V4VC validated.");
                C0745a.f2004o = true;
            }
            this.o.f2144d.m4729a(this.f4007b, this.f4006a);
        }
    }

    /* renamed from: com.jirbo.adcolony.d.7 */
    class C12527 extends C0775j {
        final /* synthetic */ String f4009a;
        final /* synthetic */ int f4010b;
        final /* synthetic */ AdColonyAd f4011c;
        final /* synthetic */ C0762d f4012d;

        C12527(C0762d c0762d, C0762d c0762d2, String str, int i, AdColonyAd adColonyAd) {
            this.f4012d = c0762d;
            this.f4009a = str;
            this.f4010b = i;
            this.f4011c = adColonyAd;
            super(c0762d2);
        }

        void m4713a() {
            C1240g c1240g = new C1240g();
            c1240g.m4655b("v4vc_name", this.f4009a);
            c1240g.m4654b("v4vc_amount", this.f4010b);
            this.o.f2144d.m4731a("reward_v4vc", c1240g, this.f4011c);
        }
    }

    /* renamed from: com.jirbo.adcolony.d.8 */
    class C12538 extends C0775j {
        final /* synthetic */ String f4013a;
        final /* synthetic */ String f4014b;
        final /* synthetic */ C0762d f4015c;

        C12538(C0762d c0762d, C0762d c0762d2, String str, String str2) {
            this.f4015c = c0762d;
            this.f4013a = str;
            this.f4014b = str2;
            super(c0762d2);
        }

        void m4714a() {
            this.o.f2144d.m4730a(this.f4013a, C0776k.m2334b(this.f4014b));
        }
    }

    /* renamed from: com.jirbo.adcolony.d.9 */
    class C12549 extends C0775j {
        final /* synthetic */ String f4016a;
        final /* synthetic */ String f4017b;
        final /* synthetic */ AdColonyAd f4018c;
        final /* synthetic */ C0762d f4019d;

        C12549(C0762d c0762d, C0762d c0762d2, String str, String str2, AdColonyAd adColonyAd) {
            this.f4019d = c0762d;
            this.f4016a = str;
            this.f4017b = str2;
            this.f4018c = adColonyAd;
            super(c0762d2);
        }

        void m4715a() {
            this.o.f2144d.m4731a(this.f4016a, C0776k.m2334b(this.f4017b), this.f4018c);
        }
    }

    C0762d() {
        this.f2141a = new C0760c(this);
        this.f2142b = new C1246b(this);
        this.f2143c = new C1255o(this);
        this.f2144d = new C1256t(this);
        this.f2145e = new C0814u(this);
        this.f2146f = new ADCStorage(this);
        this.f2147g = new ag(this);
        this.f2148h = new ArrayList();
        this.f2149i = new ArrayList();
        this.f2153m = new C0746a();
    }

    void m2250a(C0775j c0775j) {
        new Thread(new C07611(this, c0775j)).start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m2243a() {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.f2151k;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = com.jirbo.adcolony.C0745a.m2155d();
        if (r0 == 0) goto L_0x0005;
    L_0x000c:
        r0 = r3.f2151k;	 Catch:{ RuntimeException -> 0x0051 }
        if (r0 == 0) goto L_0x001c;
    L_0x0010:
        r0 = r3.f2150j;	 Catch:{ RuntimeException -> 0x0051 }
        if (r0 != 0) goto L_0x0062;
    L_0x0014:
        r0 = r3.f2148h;	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r0.size();	 Catch:{ RuntimeException -> 0x0051 }
        if (r0 <= 0) goto L_0x0062;
    L_0x001c:
        r0 = 1;
        r3.f2151k = r0;	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r3.f2149i;	 Catch:{ RuntimeException -> 0x0051 }
        r1 = r3.f2148h;	 Catch:{ RuntimeException -> 0x0051 }
        r0.addAll(r1);	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r3.f2148h;	 Catch:{ RuntimeException -> 0x0051 }
        r0.clear();	 Catch:{ RuntimeException -> 0x0051 }
        r1 = r2;
    L_0x002c:
        r0 = r3.f2149i;	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r0.size();	 Catch:{ RuntimeException -> 0x0051 }
        if (r1 >= r0) goto L_0x004b;
    L_0x0034:
        r0 = r3.f2149i;	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r0.get(r1);	 Catch:{ RuntimeException -> 0x0051 }
        if (r0 == 0) goto L_0x0047;
    L_0x003c:
        r0 = r3.f2149i;	 Catch:{ RuntimeException -> 0x0051 }
        r0 = r0.get(r1);	 Catch:{ RuntimeException -> 0x0051 }
        r0 = (com.jirbo.adcolony.C0775j) r0;	 Catch:{ RuntimeException -> 0x0051 }
        r0.m2324a();	 Catch:{ RuntimeException -> 0x0051 }
    L_0x0047:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x002c;
    L_0x004b:
        r0 = r3.f2149i;	 Catch:{ RuntimeException -> 0x0051 }
        r0.clear();	 Catch:{ RuntimeException -> 0x0051 }
        goto L_0x000c;
    L_0x0051:
        r0 = move-exception;
        r3.f2151k = r2;
        r1 = r3.f2149i;
        r1.clear();
        r1 = r3.f2148h;
        r1.clear();
        com.jirbo.adcolony.C0745a.m2142a(r0);
        goto L_0x0005;
    L_0x0062:
        r0 = 0;
        r3.f2151k = r0;	 Catch:{ RuntimeException -> 0x0051 }
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jirbo.adcolony.d.a():void");
    }

    void m2258b() {
        this.f2150j = true;
        C12472 c12472 = new C12472(this, this);
    }

    void m2264c() {
        this.f2150j = false;
        C12483 c12483 = new C12483(this, this);
    }

    void m2267d() {
        C12494 c12494 = new C12494(this, this);
    }

    synchronized void m2246a(AdColonyAd adColonyAd) {
        this.f2141a.f2127o = 0.0d;
        C0777l.f2239a.m2353b((Object) "Tracking ad event - start");
        af afVar = adColonyAd.f1842i.f2321r;
        afVar.f2090d++;
        if (!adColonyAd.m2117b()) {
            adColonyAd.f1842i.m2407m();
        }
        C12505 c12505 = new C12505(this, this, adColonyAd);
    }

    void m2244a(double d, AdColonyAd adColonyAd) {
        C12516 c12516 = new C12516(this, this, adColonyAd, d);
    }

    synchronized void m2255a(boolean z, String str, int i) {
        C0745a.f1987X.m2135a(z, str, i);
    }

    synchronized void m2254a(boolean z, AdColonyAd adColonyAd) {
        Object obj = 1;
        synchronized (this) {
            if (adColonyAd != null) {
                m2244a(1.0d, adColonyAd);
                if (!z && adColonyAd.m2117b()) {
                    adColonyAd.f1842i.m2407m();
                    ad adVar = adColonyAd.f1842i;
                    adVar.f2322s++;
                    AdColonyV4VCAd adColonyV4VCAd = (AdColonyV4VCAd) C0745a.f1983T;
                    String rewardName = adColonyV4VCAd.getRewardName();
                    int rewardAmount = adColonyV4VCAd.getRewardAmount();
                    if (!adColonyAd.f1842i.m2394b()) {
                        obj = null;
                    }
                    if (obj != null) {
                        if (adColonyV4VCAd.i.f2317n.f2328e && C0745a.f2004o) {
                            m2255a(true, rewardName, rewardAmount);
                        }
                        C12527 c12527 = new C12527(this, this, rewardName, rewardAmount, adColonyAd);
                    }
                }
            }
        }
    }

    void m2251a(String str, String str2) {
        C12538 c12538 = new C12538(this, this, str, str2);
    }

    void m2252a(String str, String str2, AdColonyAd adColonyAd) {
        C12549 c12549 = new C12549(this, this, str, str2, adColonyAd);
    }

    synchronized double m2242a(String str) {
        double f;
        try {
            f = this.f2141a.f2121i.m4660f(str);
        } catch (RuntimeException e) {
            C0745a.m2142a(e);
            f = 0.0d;
        }
        return f;
    }

    synchronized int m2257b(String str) {
        int g;
        try {
            g = this.f2141a.f2121i.m4661g(str);
        } catch (RuntimeException e) {
            C0745a.m2142a(e);
            g = 0;
        }
        return g;
    }

    synchronized boolean m2265c(String str) {
        boolean h;
        try {
            h = this.f2141a.f2121i.m4663h(str);
        } catch (RuntimeException e) {
            C0745a.m2142a(e);
            h = false;
        }
        return h;
    }

    synchronized String m2266d(String str) {
        String e;
        try {
            e = this.f2141a.f2121i.m4659e(str);
        } catch (RuntimeException e2) {
            C0745a.m2142a(e2);
            e = null;
        }
        return e;
    }

    synchronized String m2268e() {
        return this.f2142b.m4701c();
    }

    synchronized String m2270f() {
        return this.f2142b.m4703d();
    }

    synchronized boolean m2269e(String str) {
        return m2256a(str, false, true);
    }

    synchronized boolean m2256a(String str, boolean z, boolean z2) {
        boolean z3 = false;
        synchronized (this) {
            try {
                if (C0745a.m2155d()) {
                    if (this.f2142b.m4700b(str, z)) {
                        z3 = this.f2142b.f4000i.f2378n.m2412a(str).m2395b(z2);
                    }
                }
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
        return z3;
    }

    synchronized boolean m2271f(String str) {
        return m2263b(str, false, true);
    }

    synchronized boolean m2263b(String str, boolean z, boolean z2) {
        boolean z3 = false;
        synchronized (this) {
            try {
                if (C0745a.m2155d()) {
                    if (this.f2142b.m4700b(str, z)) {
                        z3 = this.f2142b.f4000i.f2378n.m2412a(str).m2397c(z2);
                    }
                }
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
        return z3;
    }

    synchronized void m2249a(AdColonyVideoAd adColonyVideoAd) {
        this.f2141a.m2240b(adColonyVideoAd.h);
    }

    synchronized void m2247a(AdColonyInterstitialAd adColonyInterstitialAd) {
        this.f2141a.m2240b(adColonyInterstitialAd.h);
    }

    synchronized boolean m2262b(AdColonyVideoAd adColonyVideoAd) {
        boolean z = false;
        synchronized (this) {
            try {
                C0745a.f1983T = adColonyVideoAd;
                Object obj = adColonyVideoAd.h;
                if (m2269e(obj)) {
                    C0777l.f2239a.m2349a("Showing ad for zone ").m2353b(obj);
                    m2249a(adColonyVideoAd);
                    z = m2259b((AdColonyAd) adColonyVideoAd);
                }
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
        return z;
    }

    synchronized boolean m2260b(AdColonyInterstitialAd adColonyInterstitialAd) {
        boolean z = false;
        synchronized (this) {
            try {
                C0745a.f1983T = adColonyInterstitialAd;
                Object obj = adColonyInterstitialAd.h;
                if (m2269e(obj)) {
                    C0777l.f2239a.m2349a("Showing ad for zone ").m2353b(obj);
                    m2247a(adColonyInterstitialAd);
                    z = m2259b((AdColonyAd) adColonyInterstitialAd);
                }
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
        return z;
    }

    synchronized ad m2272g(String str) {
        return this.f2142b.f4000i.f2378n.m2412a(str);
    }

    synchronized void m2248a(AdColonyV4VCAd adColonyV4VCAd) {
        this.f2141a.m2241c(adColonyV4VCAd.h);
    }

    synchronized boolean m2261b(AdColonyV4VCAd adColonyV4VCAd) {
        boolean z = false;
        synchronized (this) {
            try {
                C0745a.f1983T = adColonyV4VCAd;
                Object obj = adColonyV4VCAd.h;
                if (m2271f(obj)) {
                    C0777l.f2239a.m2349a("Showing v4vc for zone ").m2353b(obj);
                    m2248a(adColonyV4VCAd);
                    z = m2259b((AdColonyAd) adColonyV4VCAd);
                }
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
        return z;
    }

    synchronized boolean m2259b(AdColonyAd adColonyAd) {
        boolean z;
        if (this.f2141a.f2125m.m2398d()) {
            C0745a.f1983T.f1839f = 3;
            z = false;
        } else {
            ADCVideo.m4677a();
            if (C0745a.f2002m) {
                C0777l.f2239a.m2353b((Object) "Launching AdColonyOverlay");
                C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyOverlay.class));
            } else {
                C0777l.f2239a.m2353b((Object) "Launching AdColonyFullscreen");
                C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyFullscreen.class));
            }
            z = true;
        }
        return z;
    }

    synchronized void m2253a(String str, String str2, String[] strArr) {
        try {
            m2245a(C0745a.f2003n);
            C0777l.f2241c.m2349a("==== Configuring AdColony ").m2349a(this.f2141a.f2115b).m2353b((Object) " with app/zone ids: ====");
            C0777l.f2241c.m2353b((Object) str2);
            for (Object b : strArr) {
                C0777l.f2241c.m2353b(b);
            }
            C0777l.f2239a.m2349a("package name: ").m2353b(aa.m2183f());
            this.f2141a.f2123k = str2;
            this.f2141a.f2124l = strArr;
            this.f2141a.m2237a(str);
            this.f2153m.m2167a();
        } catch (RuntimeException e) {
            C0745a.m2142a(e);
        }
    }

    synchronized void m2273g() {
        if (!C0745a.m2153c()) {
            try {
                m2243a();
                if (!C0745a.f2013x) {
                    if (C0772g.m2311n() != null || this.f2153m.m2168b() > 5.0d) {
                        this.f2141a.m2236a();
                        C0745a.f2013x = true;
                    }
                    C0745a.f2015z = true;
                    this.f2142b.m4705f();
                }
                this.f2143c.m4724e();
                this.f2145e.m2503b();
                this.f2144d.m4739d();
                this.f2147g.m2229d();
            } catch (RuntimeException e) {
                C0745a.m2142a(e);
            }
        }
    }

    void m2245a(int i) {
        C0745a.m2136a(i);
    }
}
