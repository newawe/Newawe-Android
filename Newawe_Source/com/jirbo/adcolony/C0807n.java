package com.jirbo.adcolony;

import com.Newawe.storage.DatabaseOpenHelper;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.cookie.ClientCookie;

/* renamed from: com.jirbo.adcolony.n */
class C0807n {

    /* renamed from: com.jirbo.adcolony.n.a */
    static class C0781a {
        C0796p f2255A;
        aa f2256B;
        String f2257a;
        String f2258b;
        int f2259c;
        int f2260d;
        int f2261e;
        int f2262f;
        int f2263g;
        int f2264h;
        long f2265i;
        boolean f2266j;
        boolean f2267k;
        boolean f2268l;
        boolean f2269m;
        boolean f2270n;
        boolean f2271o;
        boolean f2272p;
        boolean f2273q;
        boolean f2274r;
        boolean f2275s;
        C0794n f2276t;
        C0806z f2277u;
        C0793m f2278v;
        C0783c f2279w;
        C0782b f2280x;
        C0788h f2281y;
        ac f2282z;

        C0781a() {
        }

        boolean m2376a() {
            if (!this.f2278v.m2450a()) {
                return false;
            }
            if (this.f2279w.f2361a && !this.f2279w.m2418a()) {
                return false;
            }
            if (this.f2255A.f2458a && !this.f2255A.m2457a()) {
                return false;
            }
            if ((!this.f2281y.f2407d || this.f2281y.m2437a()) && this.f2282z.m2384a() && this.f2256B.m2381a() && !m2378b()) {
                return true;
            }
            return false;
        }

        boolean m2378b() {
            if (this.f2273q || System.currentTimeMillis() - this.f2265i > ((long) (this.f2264h * DateUtils.MILLIS_IN_SECOND))) {
                return true;
            }
            return false;
        }

        boolean m2379c() {
            if (this.f2274r) {
                return true;
            }
            return false;
        }

        boolean m2377a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2257a = c1240g.m4659e("uuid");
            this.f2258b = c1240g.m4659e(DatabaseOpenHelper.HISTORY_ROW_TITLE);
            this.f2259c = c1240g.m4661g("ad_campaign_id");
            this.f2260d = c1240g.m4661g("ad_id");
            this.f2261e = c1240g.m4661g("ad_group_id");
            this.f2262f = c1240g.m4661g("cpcv_bid");
            this.f2263g = c1240g.m4661g("net_earnings");
            this.f2264h = c1240g.m4642a(ClientCookie.EXPIRES_ATTR, 660);
            this.f2266j = c1240g.m4663h("enable_in_app_store");
            this.f2267k = c1240g.m4663h("video_events_on_replays");
            this.f2268l = c1240g.m4663h("test_ad");
            this.f2269m = c1240g.m4663h("fullscreen");
            this.f2270n = c1240g.m4663h("house_ad");
            this.f2271o = c1240g.m4663h("contracted");
            this.f2275s = false;
            this.f2265i = System.currentTimeMillis();
            this.f2276t = new C0794n();
            if (!this.f2276t.m2453a(c1240g.m4652b("limits"))) {
                return false;
            }
            this.f2277u = new C0806z();
            if (!this.f2277u.m2479a(c1240g.m4652b("third_party_tracking"))) {
                return false;
            }
            this.f2278v = new C0793m();
            if (!this.f2278v.m2451a(c1240g.m4652b("in_app_browser"))) {
                return false;
            }
            this.f2255A = new C0796p();
            if (!this.f2255A.m2458a(c1240g.m4652b("native"))) {
                return false;
            }
            this.f2279w = new C0783c();
            if (!this.f2279w.m2419a(c1240g.m4652b("v4vc"))) {
                return false;
            }
            this.f2280x = new C0782b();
            if (!this.f2280x.m2417a(c1240g.m4652b("ad_tracking"))) {
                return false;
            }
            this.f2281y = new C0788h();
            if (!this.f2281y.m2438a(c1240g.m4652b("companion_ad"))) {
                return false;
            }
            this.f2282z = new ac();
            if (!this.f2282z.m2385a(c1240g.m4652b("video"))) {
                return false;
            }
            this.f2256B = new aa();
            if (!this.f2256B.m2382a(c1240g.m4652b("v4iap"))) {
                return false;
            }
            C0777l.f2240b.m2353b((Object) "Finished parsing ad");
            return true;
        }

        void m2380d() {
            this.f2279w.m2420b();
            this.f2278v.m2452b();
            this.f2255A.m2459b();
            this.f2281y.m2439b();
            this.f2282z.m2387c();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.aa */
    static class aa {
        String f2283a;
        String f2284b;
        boolean f2285c;

        aa() {
        }

        boolean m2381a() {
            return true;
        }

        boolean m2382a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2285c = c1240g.m4663h("enabled");
            if (!this.f2285c) {
                return true;
            }
            this.f2283a = (String) c1240g.m4658d("product_ids").get(0);
            this.f2284b = c1240g.m4659e("in_progress");
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.ab */
    static class ab {
        int f2286a;
        int f2287b;
        int f2288c;

        ab() {
        }

        boolean m2383a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2286a = -1;
            this.f2287b = -1;
            this.f2288c = -1;
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.ac */
    static class ac {
        boolean f2289a;
        int f2290b;
        int f2291c;
        String f2292d;
        String f2293e;
        String f2294f;
        String f2295g;
        String f2296h;
        String f2297i;
        String f2298j;
        double f2299k;
        C0787g f2300l;
        C0787g f2301m;
        C0787g f2302n;
        C0791k f2303o;

        ac() {
        }

        boolean m2384a() {
            if (!this.f2289a) {
                return true;
            }
            if (!C0745a.f2001l.f2143c.m4719a(this.f2292d)) {
                return false;
            }
            if (!this.f2300l.m2434a()) {
                return false;
            }
            if (!this.f2301m.m2434a()) {
                return false;
            }
            if (!this.f2303o.m2444a()) {
                return false;
            }
            if (!this.f2302n.m2434a()) {
                return false;
            }
            if (C0745a.f2001l.f2142b.f4000i.f2373i.equals("online") && !C0811q.m2489c()) {
                C0745a.am = 9;
                return C0777l.f2241c.m2355b("Video not ready due to VIEW_FILTER_ONLINE");
            } else if (C0745a.f2001l.f2142b.f4000i.f2373i.equals("wifi") && !C0811q.m2484a()) {
                C0745a.am = 9;
                return C0777l.f2241c.m2355b("Video not ready due to VIEW_FILTER_WIFI");
            } else if (C0745a.f2001l.f2142b.f4000i.f2373i.equals("cell") && !C0811q.m2487b()) {
                C0745a.am = 9;
                return C0777l.f2241c.m2355b("Video not ready due to VIEW_FILTER_CELL");
            } else if (!C0745a.f2001l.f2142b.f4000i.f2373i.equals("offline") || !C0811q.m2489c()) {
                return true;
            } else {
                C0745a.am = 9;
                return C0777l.f2241c.m2355b("Video not ready due to VIEW_FILTER_OFFLINE");
            }
        }

        String m2386b() {
            return C0745a.f2001l.f2143c.m4720b(this.f2292d);
        }

        boolean m2385a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2289a = c1240g.m4663h("enabled");
            if (!this.f2289a) {
                return true;
            }
            this.f2290b = c1240g.m4661g("width");
            this.f2291c = c1240g.m4661g("height");
            this.f2292d = c1240g.m4659e(DatabaseOpenHelper.HISTORY_ROW_URL);
            this.f2293e = c1240g.m4659e("last_modified");
            this.f2294f = c1240g.m4659e("video_frame_rate");
            this.f2295g = c1240g.m4659e("audio_channels");
            this.f2296h = c1240g.m4659e("audio_codec");
            this.f2297i = c1240g.m4659e("audio_sample_rate");
            this.f2298j = c1240g.m4659e("video_codec");
            this.f2299k = c1240g.m4660f(SchemaSymbols.ATTVAL_DURATION);
            this.f2300l = new C0787g();
            if (!this.f2300l.m2435a(c1240g.m4652b("skip_video"))) {
                return false;
            }
            this.f2301m = new C0787g();
            if (!this.f2301m.m2435a(c1240g.m4652b("in_video_engagement"))) {
                return false;
            }
            this.f2303o = new C0791k();
            if (!this.f2303o.m2445a(c1240g.m4652b("haptic"))) {
                return false;
            }
            this.f2302n = new C0787g();
            if (this.f2302n.m2435a(c1240g.m4652b("in_video_engagement").m4652b("image_overlay"))) {
                return true;
            }
            return false;
        }

        void m2387c() {
            C0745a.f2001l.f2143c.m4718a(this.f2292d, this.f2293e);
            this.f2301m.m2436b();
            this.f2300l.m2436b();
            this.f2303o.m2446b();
            this.f2302n.m2436b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.ad */
    static class ad {
        String f2304a;
        C1240g f2305b;
        int f2306c;
        int f2307d;
        int f2308e;
        int f2309f;
        boolean f2310g;
        boolean f2311h;
        boolean f2312i;
        C1236c f2313j;
        ArrayList<String> f2314k;
        ae f2315l;
        C0784d f2316m;
        af f2317n;
        long f2318o;
        long f2319p;
        long f2320q;
        af f2321r;
        int f2322s;

        ad() {
            this.f2313j = new C1236c();
            this.f2318o = 600000;
            this.f2319p = DateUtils.MILLIS_PER_MINUTE;
            this.f2322s = 0;
        }

        boolean m2390a() {
            if (this.f2316m == null) {
                return true;
            }
            Iterator it = this.f2316m.f2364a.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                if (((C0781a) it.next()).m2378b()) {
                    i2 = i;
                } else {
                    i2 = i + 1;
                }
                i = i2;
            }
            if (i < this.f2309f) {
                return true;
            }
            return false;
        }

        boolean m2394b() {
            if (this.f2322s % this.f2317n.f2329f != 0) {
                return false;
            }
            this.f2322s = 0;
            return true;
        }

        boolean m2396c() {
            return m2393a(false, true);
        }

        boolean m2393a(boolean z, boolean z2) {
            if (!z2) {
                return m2392a(z);
            }
            if (!this.f2310g || !this.f2311h) {
                C0745a.am = 1;
                return C0777l.f2241c.m2355b("Ad is not ready to be played, as zone " + this.f2304a + " is disabled or inactive.");
            } else if (this.f2316m.m2426b() == 0 || this.f2314k.size() == 0) {
                C0745a.am = 5;
                return C0777l.f2241c.m2355b("Ad is not ready to be played, as AdColony currently has no videos available to be played in zone " + this.f2304a + ".");
            } else {
                C0781a k;
                int size = this.f2314k.size();
                for (int i = 0; i < size; i++) {
                    k = m2405k();
                    if (k == null) {
                        return C0777l.f2241c.m2355b("Ad is not ready to be played due to an unknown error.");
                    }
                    if (k.m2376a()) {
                        break;
                    }
                    m2407m();
                }
                k = null;
                if (k == null) {
                    C0745a.am = 6;
                    return C0777l.f2241c.m2355b("Ad is not ready to be played, as AdColony currently has no videos available to be played in zone " + this.f2304a + ".");
                } else if (m2389a(k) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        boolean m2392a(boolean z) {
            if (!z) {
                C0745a.m2163h();
            }
            if (!this.f2310g || !this.f2311h || this.f2316m.m2426b() == 0 || this.f2314k.size() == 0) {
                return false;
            }
            C0781a k;
            int size = this.f2314k.size();
            for (int i = 0; i < size; i++) {
                k = m2405k();
                if (k == null) {
                    return false;
                }
                if (k.m2376a()) {
                    break;
                }
                m2407m();
            }
            k = null;
            if (k == null || m2389a(k) == 0) {
                return false;
            }
            return true;
        }

        boolean m2398d() {
            if (this.f2306c <= 1) {
                return false;
            }
            C0745a.f2001l.f2147g.f2092b = true;
            af afVar = this.f2321r;
            int i = afVar.f2088b;
            afVar.f2088b = i + 1;
            if (i == 0) {
                return false;
            }
            if (this.f2321r.f2088b >= this.f2306c) {
                this.f2321r.f2088b = 0;
            }
            return true;
        }

        int m2388a(int i, int i2) {
            if (i2 <= 0) {
                return 0;
            }
            if (i == -1) {
                return i2;
            }
            if (i >= i2) {
                i = i2;
            }
            return i;
        }

        void m2399e() {
            C0745a.f2001l.f2142b.m4704e();
        }

        synchronized int m2400f() {
            return m2389a(m2405k());
        }

        synchronized int m2389a(C0781a c0781a) {
            int i;
            if (c0781a.f2273q) {
                i = 0;
            } else {
                i = 1;
            }
            return i;
        }

        boolean m2401g() {
            return m2395b(true);
        }

        boolean m2395b(boolean z) {
            if (!z) {
                return m2402h();
            }
            if (!this.f2310g || !this.f2311h) {
                C0745a.am = 1;
                return C0777l.f2241c.m2355b("Ad is not ready, as zone " + this.f2304a + " is disabled or inactive.");
            } else if (this.f2316m.m2426b() == 0) {
                C0745a.am = 5;
                return C0777l.f2241c.m2355b("Ad is not ready, as there are currently no ads to play in zone " + this.f2304a + ".");
            } else if (!this.f2316m.m2428c().f2279w.f2361a) {
                return true;
            } else {
                C0745a.am = 14;
                return C0777l.f2241c.m2355b("Ad is not ready, as zone " + this.f2304a + " is V4VC enabled and must be played using an AdColonyV4VCAd object.");
            }
        }

        boolean m2402h() {
            if (!this.f2310g || !this.f2311h || this.f2316m.m2426b() == 0 || this.f2316m.m2428c().f2279w.f2361a) {
                return false;
            }
            return true;
        }

        boolean m2403i() {
            return m2397c(true);
        }

        boolean m2397c(boolean z) {
            if (!z) {
                return m2404j();
            }
            if (!this.f2310g || !this.f2311h) {
                C0745a.am = 1;
                return C0777l.f2241c.m2355b("Ad is not ready, as zone " + this.f2304a + " is disabled or inactive.");
            } else if (this.f2316m.m2426b() == 0) {
                C0745a.am = 5;
                return C0777l.f2241c.m2355b("Ad is not ready, as there are currently no ads to play in zone " + this.f2304a + ".");
            } else if (this.f2316m.m2428c().f2279w.f2361a) {
                return true;
            } else {
                C0745a.am = 15;
                return C0777l.f2241c.m2355b("Ad is not ready, as zone " + this.f2304a + " is not V4VC enabled and must be played using an AdColonyVideoAd object.");
            }
        }

        boolean m2404j() {
            if (this.f2310g && this.f2311h && this.f2316m.m2426b() != 0 && this.f2316m.m2428c().f2279w.f2361a) {
                return true;
            }
            return false;
        }

        C0781a m2405k() {
            if (this.f2314k.size() > 0) {
                return this.f2316m.m2422a((String) this.f2314k.get(this.f2321r.f2089c % this.f2314k.size()));
            }
            return null;
        }

        C0781a m2406l() {
            if (this.f2314k.size() > 0) {
                return this.f2316m.m2427b(this.f2321r.f2089c % this.f2314k.size());
            }
            return null;
        }

        void m2407m() {
            if (this.f2314k.size() > 0) {
                this.f2321r.f2089c = (this.f2321r.f2089c + 1) % this.f2314k.size();
            }
        }

        boolean m2391a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2305b = c1240g;
            this.f2304a = c1240g.m4659e("uuid");
            this.f2310g = c1240g.m4663h("enabled");
            this.f2311h = c1240g.m4663h("active");
            this.f2320q = Long.parseLong(c1240g.m4646a("last_config_ms", SchemaSymbols.ATTVAL_FALSE_0)) == 0 ? System.currentTimeMillis() : Long.parseLong(c1240g.m4659e("last_config_ms"));
            this.f2312i = c1240g.m4663h("clear_ad_queue");
            if (!this.f2310g || !this.f2311h) {
                return true;
            }
            this.f2306c = c1240g.m4661g("play_interval");
            this.f2307d = c1240g.m4661g("daily_play_cap");
            this.f2308e = c1240g.m4661g("session_play_cap");
            this.f2309f = c1240g.m4642a("min_ad_thresh", 1);
            this.f2319p = ((long) c1240g.m4642a("min_config_win", 60)) * 1000;
            this.f2318o = ((long) c1240g.m4642a("max_config_win", 600)) * 1000;
            this.f2314k = new ArrayList();
            ArrayList d = c1240g.m4658d("play_order");
            this.f2314k = d;
            if (d == null) {
                return false;
            }
            this.f2315l = new ae();
            if (!this.f2315l.m2409a(c1240g.m4652b("tracking"))) {
                return false;
            }
            if (this.f2316m == null || this.f2312i) {
                this.f2316m = new C0784d();
            }
            if (!this.f2316m.m2425a(c1240g.m4657c("ads"))) {
                return false;
            }
            if (!C0745a.f1966C) {
                C0777l.f2241c.m2349a("Finished parsing response for zone \"").m2349a(this.f2304a).m2349a("\": ").m2347a(this.f2316m.m2426b()).m2353b((Object) " ad(s). Attempting to cache media assets.");
            }
            this.f2317n = new af();
            if (!this.f2317n.m2410a(c1240g.m4652b("v4vc"))) {
                return false;
            }
            this.f2321r = C0745a.f2001l.f2147g.m2225a(this.f2304a);
            C0777l.f2239a.m2353b((Object) "Finished parsing zone");
            return true;
        }

        void m2408n() {
            if (this.f2310g && this.f2311h) {
                for (int i = 0; i < this.f2316m.m2426b(); i++) {
                    this.f2316m.m2421a(i).m2380d();
                }
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.ae */
    static class ae {
        String f2323a;

        ae() {
        }

        boolean m2409a(C1240g c1240g) {
            if (c1240g != null) {
                this.f2323a = c1240g.m4646a("request", null);
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.af */
    static class af {
        boolean f2324a;
        ab f2325b;
        int f2326c;
        String f2327d;
        boolean f2328e;
        int f2329f;

        af() {
        }

        boolean m2410a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2324a = c1240g.m4663h("enabled");
            if (!this.f2324a) {
                return true;
            }
            this.f2325b = new ab();
            if (!this.f2325b.m2383a(c1240g.m4652b("limits"))) {
                return false;
            }
            this.f2326c = c1240g.m4661g("reward_amount");
            this.f2327d = c1240g.m4659e("reward_name");
            this.f2328e = c1240g.m4663h("client_side");
            this.f2329f = c1240g.m4661g("videos_per_reward");
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.ag */
    static class ag {
        ArrayList<ad> f2330a;

        ag() {
            this.f2330a = new ArrayList();
        }

        boolean m2414a(C1236c c1236c) {
            if (c1236c == null) {
                C0745a.f2005p = false;
                return false;
            }
            int i = 0;
            while (i < c1236c.m4628i()) {
                C1240g b = c1236c.m4619b(i);
                ad a = m2412a(b.m4659e("uuid"));
                if (a == null) {
                    a = new ad();
                }
                if (a.m2391a(b)) {
                    this.f2330a.add(a);
                    i++;
                } else {
                    C0745a.f2005p = false;
                    return false;
                }
            }
            C0745a.f2005p = false;
            return true;
        }

        void m2413a() {
            for (int i = 0; i < this.f2330a.size(); i++) {
                if (((ad) this.f2330a.get(i)).f2316m != null) {
                    ((ad) this.f2330a.get(i)).f2316m.m2423a();
                }
            }
        }

        int m2415b() {
            return this.f2330a.size();
        }

        ad m2411a(int i) {
            return (ad) this.f2330a.get(i);
        }

        ad m2416c() {
            return (ad) this.f2330a.get(0);
        }

        ad m2412a(String str) {
            for (int i = 0; i < this.f2330a.size(); i++) {
                ad adVar = (ad) this.f2330a.get(i);
                if (adVar.f2304a.equals(str)) {
                    return adVar;
                }
            }
            return null;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.b */
    static class C0782b {
        String f2331A;
        String f2332B;
        String f2333C;
        C1240g f2334D;
        String f2335a;
        String f2336b;
        String f2337c;
        String f2338d;
        String f2339e;
        String f2340f;
        String f2341g;
        String f2342h;
        String f2343i;
        String f2344j;
        String f2345k;
        String f2346l;
        String f2347m;
        String f2348n;
        String f2349o;
        String f2350p;
        String f2351q;
        String f2352r;
        String f2353s;
        String f2354t;
        String f2355u;
        String f2356v;
        String f2357w;
        String f2358x;
        String f2359y;
        String f2360z;

        C0782b() {
            this.f2334D = new C1240g();
        }

        boolean m2417a(C1240g c1240g) {
            if (c1240g != null) {
                this.f2335a = c1240g.m4646a("replay", null);
                this.f2336b = c1240g.m4646a("card_shown", null);
                this.f2337c = c1240g.m4646a("html5_interaction", null);
                this.f2338d = c1240g.m4646a("cancel", null);
                this.f2339e = c1240g.m4646a("download", null);
                this.f2340f = c1240g.m4646a(SchemaSymbols.ATTVAL_SKIP, null);
                this.f2341g = c1240g.m4646a("info", null);
                this.f2342h = c1240g.m4646a("custom_event", null);
                this.f2343i = c1240g.m4646a("midpoint", null);
                this.f2344j = c1240g.m4646a("card_dissolved", null);
                this.f2345k = c1240g.m4646a("start", null);
                this.f2346l = c1240g.m4646a("third_quartile", null);
                this.f2347m = c1240g.m4646a("complete", null);
                this.f2348n = c1240g.m4646a("continue", null);
                this.f2349o = c1240g.m4646a("in_video_engagement", null);
                this.f2350p = c1240g.m4646a("reward_v4vc", null);
                this.f2352r = c1240g.m4646a("first_quartile", null);
                this.f2351q = c1240g.m4646a("v4iap", null);
                this.f2353s = c1240g.m4646a("video_expanded", null);
                this.f2354t = c1240g.m4646a("sound_mute", null);
                this.f2355u = c1240g.m4646a("sound_unmute", null);
                this.f2356v = c1240g.m4646a("video_paused", null);
                this.f2357w = c1240g.m4646a("video_resumed", null);
                this.f2358x = c1240g.m4646a("native_start", null);
                this.f2359y = c1240g.m4646a("native_first_quartile", null);
                this.f2360z = c1240g.m4646a("native_midpoint", null);
                this.f2331A = c1240g.m4646a("native_third_quartile", null);
                this.f2332B = c1240g.m4646a("native_complete", null);
                this.f2333C = c1240g.m4646a("native_overlay_click", null);
                this.f2334D.m4655b("replay", this.f2335a);
                this.f2334D.m4655b("card_shown", this.f2336b);
                this.f2334D.m4655b("html5_interaction", this.f2337c);
                this.f2334D.m4655b("cancel", this.f2338d);
                this.f2334D.m4655b("download", this.f2339e);
                this.f2334D.m4655b(SchemaSymbols.ATTVAL_SKIP, this.f2340f);
                this.f2334D.m4655b("info", this.f2341g);
                this.f2334D.m4655b("custom_event", this.f2342h);
                this.f2334D.m4655b("midpoint", this.f2343i);
                this.f2334D.m4655b("card_dissolved", this.f2344j);
                this.f2334D.m4655b("start", this.f2345k);
                this.f2334D.m4655b("third_quartile", this.f2346l);
                this.f2334D.m4655b("complete", this.f2347m);
                this.f2334D.m4655b("continue", this.f2348n);
                this.f2334D.m4655b("in_video_engagement", this.f2349o);
                this.f2334D.m4655b("reward_v4vc", this.f2350p);
                this.f2334D.m4655b("first_quartile", this.f2352r);
                this.f2334D.m4655b("v4iap", this.f2351q);
                this.f2334D.m4655b("video_expanded", this.f2353s);
                this.f2334D.m4655b("sound_mute", this.f2354t);
                this.f2334D.m4655b("sound_unmute", this.f2355u);
                this.f2334D.m4655b("video_paused", this.f2356v);
                this.f2334D.m4655b("video_resumed", this.f2357w);
                this.f2334D.m4655b("native_start", this.f2358x);
                this.f2334D.m4655b("native_first_quartile", this.f2359y);
                this.f2334D.m4655b("native_midpoint", this.f2360z);
                this.f2334D.m4655b("native_third_quartile", this.f2331A);
                this.f2334D.m4655b("native_complete", this.f2332B);
                this.f2334D.m4655b("native_overlay_click", this.f2333C);
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.c */
    static class C0783c {
        boolean f2361a;
        C0803w f2362b;
        C0801u f2363c;

        C0783c() {
        }

        boolean m2418a() {
            if (this.f2362b.m2472a() && this.f2363c.m2466a()) {
                return true;
            }
            return false;
        }

        boolean m2419a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2361a = c1240g.m4663h("enabled");
            if (!this.f2361a) {
                return true;
            }
            this.f2362b = new C0803w();
            if (!this.f2362b.m2473a(c1240g.m4652b("pre_popup"))) {
                return false;
            }
            this.f2363c = new C0801u();
            if (this.f2363c.m2467a(c1240g.m4652b("post_popup"))) {
                return true;
            }
            return false;
        }

        void m2420b() {
            if (this.f2361a) {
                this.f2362b.m2474b();
                this.f2363c.m2468b();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.d */
    static class C0784d {
        ArrayList<C0781a> f2364a;

        C0784d() {
            this.f2364a = new ArrayList();
        }

        void m2423a() {
            for (int i = 0; i < this.f2364a.size(); i++) {
                C0781a c0781a = (C0781a) this.f2364a.get(i);
                if (c0781a.m2378b() && !c0781a.f2275s) {
                    C0745a.m2163h();
                    c0781a.f2275s = true;
                }
            }
        }

        boolean m2425a(C1236c c1236c) {
            if (c1236c == null) {
                return false;
            }
            for (int i = 0; i < this.f2364a.size(); i++) {
                if (((C0781a) this.f2364a.get(i)).m2378b()) {
                    this.f2364a.remove(i);
                }
            }
            for (int i2 = 0; i2 < c1236c.m4628i(); i2++) {
                C0781a c0781a = new C0781a();
                if (!c0781a.m2377a(c1236c.m4619b(i2))) {
                    return false;
                }
                this.f2364a.add(c0781a);
            }
            return true;
        }

        void m2424a(C0781a c0781a) {
            this.f2364a.add(c0781a);
        }

        int m2426b() {
            return this.f2364a.size();
        }

        C0781a m2421a(int i) {
            return (C0781a) this.f2364a.get(i);
        }

        C0781a m2428c() {
            return (C0781a) this.f2364a.get(0);
        }

        C0781a m2422a(String str) {
            for (int i = 0; i < this.f2364a.size(); i++) {
                C0781a c0781a = (C0781a) this.f2364a.get(i);
                if (c0781a.f2257a.equals(str)) {
                    return c0781a;
                }
            }
            return null;
        }

        C0781a m2427b(int i) {
            while (i < this.f2364a.size()) {
                C0781a c0781a = (C0781a) this.f2364a.get(i);
                if (c0781a.f2255A.f2458a) {
                    return c0781a;
                }
                i++;
            }
            for (int i2 = 0; i2 < this.f2364a.size(); i2++) {
                c0781a = (C0781a) this.f2364a.get(i2);
                if (c0781a.f2255A.f2458a) {
                    return c0781a;
                }
            }
            return null;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.e */
    static class C0785e {
        boolean f2365a;
        boolean f2366b;
        String f2367c;
        String f2368d;
        boolean f2369e;
        boolean f2370f;
        double f2371g;
        String f2372h;
        String f2373i;
        String f2374j;
        C0786f f2375k;
        C0805y f2376l;
        ArrayList<String> f2377m;
        ag f2378n;
        C0789i f2379o;

        C0785e() {
            this.f2369e = false;
            this.f2378n = new ag();
        }

        boolean m2431a(String str) {
            return m2432a(str, false, true);
        }

        boolean m2432a(String str, boolean z, boolean z2) {
            if (!this.f2365a) {
                return false;
            }
            ad a = this.f2378n.m2412a(str);
            if (a != null) {
                return a.m2393a(z, z2);
            }
            return false;
        }

        boolean m2430a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2365a = c1240g.m4663h("enabled");
            this.f2366b = c1240g.m4663h("log_screen_overlay");
            this.f2367c = c1240g.m4659e("last_country");
            this.f2368d = c1240g.m4659e("last_ip");
            this.f2370f = c1240g.m4663h("collect_iap_enabled");
            this.f2371g = c1240g.m4660f("media_pool_size");
            this.f2372h = c1240g.m4659e("log_level");
            this.f2373i = c1240g.m4659e("view_network_pass_filter");
            this.f2374j = c1240g.m4659e("cache_network_pass_filter");
            this.f2369e = c1240g.m4663h("hardware_acceleration_disabled");
            if (this.f2373i == null || this.f2373i.equals(StringUtils.EMPTY)) {
                this.f2373i = "all";
            }
            if (this.f2374j == null || this.f2374j.equals(StringUtils.EMPTY)) {
                this.f2374j = "all";
            }
            this.f2375k = new C0786f();
            if (!this.f2375k.m2433a(c1240g.m4652b("tracking"))) {
                return false;
            }
            this.f2376l = new C0805y();
            if (!this.f2376l.m2478a(c1240g.m4652b("third_party_tracking"))) {
                return false;
            }
            this.f2377m = c1240g.m4658d("console_messages");
            C0777l.f2239a.m2353b((Object) "Parsing zones");
            if (!this.f2378n.m2414a(c1240g.m4657c("zones"))) {
                return false;
            }
            this.f2379o = new C0789i();
            if (!this.f2379o.m2440a(c1240g.m4652b("device"))) {
                return false;
            }
            C0777l.f2239a.m2353b((Object) "Finished parsing app info");
            return true;
        }

        void m2429a() {
            C0777l.f2239a.m2353b((Object) "Caching media");
            if (this.f2365a) {
                for (int i = 0; i < this.f2378n.m2415b(); i++) {
                    this.f2378n.m2411a(i).m2408n();
                }
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.f */
    static class C0786f {
        String f2380a;
        String f2381b;
        String f2382c;
        String f2383d;
        String f2384e;
        String f2385f;
        String f2386g;
        C1240g f2387h;

        C0786f() {
        }

        boolean m2433a(C1240g c1240g) {
            if (c1240g != null) {
                this.f2380a = c1240g.m4646a("update", null);
                this.f2381b = c1240g.m4646a("install", null);
                this.f2382c = c1240g.m4646a("dynamic_interests", null);
                this.f2383d = c1240g.m4646a("user_meta_data", null);
                this.f2384e = c1240g.m4646a("in_app_purchase", null);
                this.f2386g = c1240g.m4646a("session_end", null);
                this.f2385f = c1240g.m4646a("session_start", null);
                this.f2387h = new C1240g();
                this.f2387h.m4655b("update", this.f2380a);
                this.f2387h.m4655b("install", this.f2381b);
                this.f2387h.m4655b("dynamic_interests", this.f2382c);
                this.f2387h.m4655b("user_meta_data", this.f2383d);
                this.f2387h.m4655b("in_app_purchase", this.f2384e);
                this.f2387h.m4655b("session_end", this.f2386g);
                this.f2387h.m4655b("session_start", this.f2385f);
                C0771f c0771f = new C0771f("iap_cache.txt");
                C1236c c = C0776k.m2336c(c0771f);
                if (c != null) {
                    for (int i = 0; i < c.m4628i(); i++) {
                        C0745a.f2001l.f2144d.m4730a("in_app_purchase", c.m4615a(i, new C1240g()));
                    }
                    c0771f.m2297c();
                    C0745a.aj.m4629j();
                }
                C0745a.f1978O = true;
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.g */
    static class C0787g {
        boolean f2388a;
        int f2389b;
        int f2390c;
        int f2391d;
        int f2392e;
        String f2393f;
        String f2394g;
        String f2395h;
        String f2396i;
        String f2397j;
        String f2398k;
        String f2399l;
        String f2400m;
        String f2401n;
        String f2402o;
        String f2403p;

        C0787g() {
        }

        boolean m2434a() {
            if (!this.f2388a) {
                return true;
            }
            if (!C0745a.f2001l.f2143c.m4719a(this.f2393f)) {
                return false;
            }
            if (C0745a.f2001l.f2143c.m4719a(this.f2395h)) {
                return true;
            }
            return false;
        }

        boolean m2435a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2388a = c1240g.m4651a("enabled", true);
            this.f2392e = c1240g.m4661g("delay");
            this.f2389b = c1240g.m4661g("width");
            this.f2390c = c1240g.m4661g("height");
            this.f2391d = c1240g.m4661g("scale");
            this.f2393f = c1240g.m4659e("image_normal");
            this.f2394g = c1240g.m4659e("image_normal_last_modified");
            this.f2395h = c1240g.m4659e("image_down");
            this.f2396i = c1240g.m4659e("image_down_last_modified");
            this.f2397j = c1240g.m4659e("click_action");
            this.f2398k = c1240g.m4659e("click_action_type");
            this.f2399l = c1240g.m4659e("label");
            this.f2400m = c1240g.m4659e("label_rgba");
            this.f2401n = c1240g.m4659e("label_shadow_rgba");
            this.f2402o = c1240g.m4659e("label_html");
            this.f2403p = c1240g.m4659e(NotificationCompatApi21.CATEGORY_EVENT);
            return true;
        }

        void m2436b() {
            C0745a.f2001l.f2143c.m4718a(this.f2393f, this.f2394g);
            C0745a.f2001l.f2143c.m4718a(this.f2395h, this.f2396i);
        }
    }

    /* renamed from: com.jirbo.adcolony.n.h */
    static class C0788h {
        String f2404a;
        int f2405b;
        int f2406c;
        boolean f2407d;
        boolean f2408e;
        boolean f2409f;
        double f2410g;
        C0804x f2411h;
        C0790j f2412i;

        C0788h() {
        }

        boolean m2437a() {
            if (this.f2412i.f2414a && !this.f2412i.m2441a()) {
                return false;
            }
            if (!this.f2407d) {
                return true;
            }
            if (this.f2411h.m2475a() || this.f2412i.m2441a()) {
                return true;
            }
            return false;
        }

        boolean m2438a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2407d = c1240g.m4663h("enabled");
            if (!this.f2407d) {
                return true;
            }
            this.f2404a = c1240g.m4659e("uuid");
            this.f2405b = c1240g.m4661g("ad_id");
            this.f2406c = c1240g.m4661g("ad_campaign_id");
            this.f2408e = c1240g.m4663h("dissolve");
            this.f2409f = c1240g.m4663h("enable_in_app_store");
            this.f2410g = c1240g.m4660f("dissolve_delay");
            this.f2411h = new C0804x();
            if (!this.f2411h.m2476a(c1240g.m4652b("static"))) {
                return false;
            }
            this.f2412i = new C0790j();
            if (this.f2412i.m2442a(c1240g.m4652b("html5"))) {
                return true;
            }
            return false;
        }

        void m2439b() {
            if (this.f2407d) {
                this.f2411h.m2477b();
                this.f2412i.m2443b();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.i */
    static class C0789i {
        String f2413a;

        C0789i() {
        }

        boolean m2440a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2413a = c1240g.m4646a("type", null);
            C0745a.ah = this.f2413a;
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.j */
    static class C0790j {
        boolean f2414a;
        double f2415b;
        boolean f2416c;
        boolean f2417d;
        String f2418e;
        C0795o f2419f;
        String f2420g;
        C0792l f2421h;
        C0787g f2422i;
        C0787g f2423j;

        C0790j() {
        }

        boolean m2441a() {
            if (!C0811q.m2489c()) {
                C0745a.am = 8;
                return C0777l.f2241c.m2355b("Ad not ready due to no network connection.");
            } else if (this.f2414a && this.f2419f.m2454a() && this.f2421h.m2447a() && this.f2422i.m2434a() && this.f2423j.m2434a()) {
                return true;
            } else {
                return false;
            }
        }

        boolean m2442a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2414a = c1240g.m4663h("enabled");
            this.f2415b = c1240g.m4660f("load_timeout");
            this.f2416c = c1240g.m4663h("load_timeout_enabled");
            this.f2417d = c1240g.m4663h("load_spinner_enabled");
            this.f2418e = c1240g.m4659e("background_color");
            this.f2420g = c1240g.m4659e("html5_tag");
            this.f2419f = new C0795o();
            if (!this.f2419f.m2455a(c1240g.m4652b("mraid_js"))) {
                return false;
            }
            this.f2421h = new C0792l();
            if (!this.f2421h.m2448a(c1240g.m4652b("background_logo"))) {
                return false;
            }
            this.f2422i = new C0787g();
            if (!this.f2422i.m2435a(c1240g.m4652b("replay"))) {
                return false;
            }
            this.f2423j = new C0787g();
            if (this.f2423j.m2435a(c1240g.m4652b("close"))) {
                return true;
            }
            return false;
        }

        void m2443b() {
            if (this.f2414a) {
                if (this.f2419f != null) {
                    this.f2419f.m2456b();
                }
                if (this.f2421h != null) {
                    this.f2421h.m2449b();
                }
                if (this.f2422i != null) {
                    this.f2422i.m2436b();
                }
                if (this.f2423j != null) {
                    this.f2423j.m2436b();
                }
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.k */
    static class C0791k {
        boolean f2424a;
        String f2425b;
        String f2426c;
        String f2427d;

        C0791k() {
        }

        boolean m2444a() {
            if (this.f2424a && !C0745a.f2001l.f2143c.m4719a(this.f2426c)) {
                return false;
            }
            return true;
        }

        boolean m2445a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2424a = c1240g.m4651a("enabled", false);
            this.f2426c = c1240g.m4659e("file_url");
            this.f2427d = c1240g.m4659e("last_modified");
            return true;
        }

        void m2446b() {
            C0745a.f2001l.f2143c.m4718a(this.f2426c, this.f2427d);
        }
    }

    /* renamed from: com.jirbo.adcolony.n.l */
    static class C0792l {
        int f2428a;
        int f2429b;
        int f2430c;
        String f2431d;
        String f2432e;
        boolean f2433f;

        C0792l() {
        }

        boolean m2447a() {
            if (this.f2433f) {
                return C0745a.f2001l.f2143c.m4719a(this.f2431d);
            }
            return true;
        }

        boolean m2448a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2433f = c1240g.m4651a("enabled", true);
            this.f2428a = c1240g.m4661g("width");
            this.f2429b = c1240g.m4661g("height");
            this.f2430c = c1240g.m4661g("scale");
            this.f2431d = c1240g.m4659e("image");
            this.f2432e = c1240g.m4659e("image_last_modified");
            if (!this.f2432e.equals(StringUtils.EMPTY)) {
                return true;
            }
            this.f2432e = c1240g.m4659e("last_modified");
            return true;
        }

        void m2449b() {
            C0745a.f2001l.f2143c.m4718a(this.f2431d, this.f2432e);
        }
    }

    /* renamed from: com.jirbo.adcolony.n.m */
    static class C0793m {
        String f2434a;
        String f2435b;
        String f2436c;
        String f2437d;
        String f2438e;
        String f2439f;
        String f2440g;
        C0792l f2441h;
        C0787g f2442i;
        C0787g f2443j;
        C0787g f2444k;
        C0787g f2445l;
        C0787g f2446m;

        C0793m() {
        }

        boolean m2450a() {
            if (C0745a.f2001l.f2143c.m4719a(this.f2434a) && C0745a.f2001l.f2143c.m4719a(this.f2436c) && C0745a.f2001l.f2143c.m4719a(this.f2438e) && this.f2441h.m2447a() && this.f2442i.m2434a() && this.f2443j.m2434a() && this.f2444k.m2434a() && this.f2445l.m2434a() && this.f2446m.m2434a()) {
                return true;
            }
            return false;
        }

        boolean m2451a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2434a = c1240g.m4659e("tiny_glow_image");
            this.f2435b = c1240g.m4659e("tiny_glow_image_last_modified;");
            this.f2436c = c1240g.m4659e("background_bar_image");
            this.f2437d = c1240g.m4659e("background_bar_image_last_modified");
            this.f2438e = c1240g.m4659e("background_tile_image");
            this.f2439f = c1240g.m4659e("background_tile_image_last_modified");
            this.f2440g = c1240g.m4659e("background_color");
            this.f2441h = new C0792l();
            if (!this.f2441h.m2448a(c1240g.m4652b("logo"))) {
                return false;
            }
            this.f2441h = new C0792l();
            if (!this.f2441h.m2448a(c1240g.m4652b("logo"))) {
                return false;
            }
            this.f2442i = new C0787g();
            if (!this.f2442i.m2435a(c1240g.m4652b("stop"))) {
                return false;
            }
            this.f2443j = new C0787g();
            if (!this.f2443j.m2435a(c1240g.m4652b("back"))) {
                return false;
            }
            this.f2444k = new C0787g();
            if (!this.f2444k.m2435a(c1240g.m4652b("close"))) {
                return false;
            }
            this.f2445l = new C0787g();
            if (!this.f2445l.m2435a(c1240g.m4652b("forward"))) {
                return false;
            }
            this.f2446m = new C0787g();
            if (this.f2446m.m2435a(c1240g.m4652b("reload"))) {
                return true;
            }
            return false;
        }

        void m2452b() {
            C0745a.f2001l.f2143c.m4718a(this.f2434a, this.f2435b);
            C0745a.f2001l.f2143c.m4718a(this.f2436c, this.f2437d);
            C0745a.f2001l.f2143c.m4718a(this.f2438e, this.f2439f);
            this.f2441h.m2449b();
            this.f2442i.m2436b();
            this.f2443j.m2436b();
            this.f2444k.m2436b();
            this.f2445l.m2436b();
            this.f2446m.m2436b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.n */
    static class C0794n {
        int f2447a;
        int f2448b;
        int f2449c;
        int f2450d;
        int f2451e;
        int f2452f;
        int f2453g;
        int f2454h;

        C0794n() {
        }

        boolean m2453a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2447a = -1;
            this.f2448b = -1;
            this.f2449c = -1;
            this.f2450d = -1;
            this.f2451e = -1;
            this.f2452f = -1;
            this.f2453g = -1;
            this.f2454h = -1;
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.o */
    static class C0795o {
        boolean f2455a;
        String f2456b;
        String f2457c;

        C0795o() {
        }

        boolean m2454a() {
            if (this.f2455a && !C0745a.f2001l.f2143c.m4719a(this.f2456b)) {
                return false;
            }
            return true;
        }

        boolean m2455a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2455a = c1240g.m4663h("enabled");
            if (!this.f2455a) {
                return true;
            }
            this.f2456b = c1240g.m4659e(DatabaseOpenHelper.HISTORY_ROW_URL);
            this.f2457c = c1240g.m4659e("last_modified");
            return true;
        }

        void m2456b() {
            C0745a.f2001l.f2143c.m4718a(this.f2456b, this.f2457c);
        }
    }

    /* renamed from: com.jirbo.adcolony.n.p */
    static class C0796p {
        boolean f2458a;
        boolean f2459b;
        String f2460c;
        String f2461d;
        String f2462e;
        C0799s f2463f;
        C0798r f2464g;
        C0797q f2465h;
        boolean f2466i;
        C0792l f2467j;
        C0792l f2468k;

        C0796p() {
        }

        boolean m2458a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2458a = c1240g.m4663h("enabled");
            this.f2460c = c1240g.m4659e("advertiser_name");
            this.f2461d = c1240g.m4659e("description");
            this.f2462e = c1240g.m4659e(DatabaseOpenHelper.HISTORY_ROW_TITLE);
            this.f2466i = false;
            this.f2463f = new C0799s();
            if (!this.f2463f.m2462a(c1240g.m4652b("thumb"))) {
                return false;
            }
            this.f2464g = new C0798r();
            if (!this.f2464g.m2461a(c1240g.m4652b("poster"))) {
                return false;
            }
            this.f2467j = new C0792l();
            if (!this.f2467j.m2448a(c1240g.m4652b("mute"))) {
                return false;
            }
            this.f2459b = this.f2467j.f2433f;
            this.f2468k = new C0792l();
            if (!this.f2468k.m2448a(c1240g.m4652b("unmute"))) {
                return false;
            }
            this.f2465h = new C0797q();
            if (this.f2465h.m2460a(c1240g.m4652b("overlay"))) {
                return true;
            }
            return false;
        }

        boolean m2457a() {
            if (this.f2458a && C0745a.f2001l.f2143c.m4719a(this.f2464g.f2474a) && C0745a.f2001l.f2143c.m4719a(this.f2463f.f2478a) && this.f2467j.m2447a() && this.f2468k.m2447a() && !this.f2466i) {
                return true;
            }
            return false;
        }

        void m2459b() {
            C0745a.f2001l.f2143c.m4718a(this.f2464g.f2474a, this.f2464g.f2475b);
            C0745a.f2001l.f2143c.m4718a(this.f2463f.f2478a, this.f2463f.f2479b);
            this.f2467j.m2449b();
            this.f2468k.m2449b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.q */
    static class C0797q {
        boolean f2469a;
        boolean f2470b;
        String f2471c;
        String f2472d;
        String f2473e;

        C0797q() {
        }

        boolean m2460a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2469a = c1240g.m4663h("enabled");
            if (!this.f2469a) {
                return true;
            }
            this.f2470b = c1240g.m4663h("in_app");
            this.f2471c = c1240g.m4659e("click_action_type");
            this.f2473e = c1240g.m4659e("click_action");
            this.f2472d = c1240g.m4659e("label");
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.r */
    static class C0798r {
        String f2474a;
        String f2475b;
        String f2476c;
        String f2477d;

        C0798r() {
        }

        boolean m2461a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2474a = c1240g.m4659e("image");
            this.f2475b = c1240g.m4659e("last_modified");
            this.f2476c = c1240g.m4659e("click_action_type");
            this.f2477d = c1240g.m4659e("click_action");
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.s */
    static class C0799s {
        String f2478a;
        String f2479b;

        C0799s() {
        }

        boolean m2462a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2478a = c1240g.m4659e("image");
            this.f2479b = c1240g.m4659e("last_modified");
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.t */
    static class C0800t {
        int f2480a;
        String f2481b;
        int f2482c;
        int f2483d;
        String f2484e;
        String f2485f;
        String f2486g;
        String f2487h;
        String f2488i;
        String f2489j;
        String f2490k;
        C0792l f2491l;
        C0787g f2492m;

        C0800t() {
        }

        boolean m2463a() {
            if (C0745a.f2001l.f2143c.m4719a(this.f2484e) && this.f2491l.m2447a() && this.f2492m.m2434a()) {
                return true;
            }
            return false;
        }

        boolean m2464a(C1240g c1240g) {
            this.f2480a = c1240g.m4661g("scale");
            this.f2481b = c1240g.m4659e("label_reward");
            this.f2482c = c1240g.m4661g("width");
            this.f2483d = c1240g.m4661g("height");
            this.f2484e = c1240g.m4659e("image");
            this.f2485f = c1240g.m4659e("image_last_modified");
            this.f2486g = c1240g.m4659e("label");
            this.f2487h = c1240g.m4659e("label_rgba");
            this.f2488i = c1240g.m4659e("label_shadow_rgba");
            this.f2489j = c1240g.m4659e("label_fraction");
            this.f2490k = c1240g.m4659e("label_html");
            this.f2491l = new C0792l();
            if (!this.f2491l.m2448a(c1240g.m4652b("logo"))) {
                return false;
            }
            this.f2492m = new C0787g();
            if (this.f2492m.m2435a(c1240g.m4652b("option_done"))) {
                return true;
            }
            return false;
        }

        void m2465b() {
            C0745a.f2001l.f2143c.m4718a(this.f2484e, this.f2485f);
            this.f2491l.m2449b();
            this.f2492m.m2436b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.u */
    static class C0801u {
        String f2493a;
        String f2494b;
        C0792l f2495c;
        C0800t f2496d;

        C0801u() {
        }

        boolean m2466a() {
            if (C0745a.f2001l.f2143c.m4719a(this.f2493a) && this.f2495c.m2447a() && this.f2496d.m2463a()) {
                return true;
            }
            return false;
        }

        boolean m2467a(C1240g c1240g) {
            this.f2493a = c1240g.m4659e("background_image");
            this.f2494b = c1240g.m4659e("background_image_last_modified");
            this.f2495c = new C0792l();
            if (!this.f2495c.m2448a(c1240g.m4652b("background_logo"))) {
                return false;
            }
            this.f2496d = new C0800t();
            if (this.f2496d.m2464a(c1240g.m4652b("dialog"))) {
                return true;
            }
            return false;
        }

        void m2468b() {
            C0745a.f2001l.f2143c.m4718a(this.f2493a, this.f2494b);
            this.f2496d.m2465b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.v */
    static class C0802v {
        int f2497a;
        String f2498b;
        int f2499c;
        int f2500d;
        String f2501e;
        String f2502f;
        String f2503g;
        String f2504h;
        String f2505i;
        String f2506j;
        String f2507k;
        C0792l f2508l;
        C0787g f2509m;
        C0787g f2510n;

        C0802v() {
        }

        boolean m2469a() {
            if (C0745a.f2001l.f2143c.m4719a(this.f2501e) && this.f2508l.m2447a() && this.f2509m.m2434a()) {
                return true;
            }
            return false;
        }

        boolean m2470a(C1240g c1240g) {
            this.f2497a = c1240g.m4661g("scale");
            this.f2498b = c1240g.m4659e("label_reward");
            this.f2499c = c1240g.m4661g("width");
            this.f2500d = c1240g.m4661g("height");
            this.f2501e = c1240g.m4659e("image");
            this.f2502f = c1240g.m4659e("image_last_modified");
            this.f2503g = c1240g.m4659e("label");
            this.f2504h = c1240g.m4659e("label_rgba");
            this.f2505i = c1240g.m4659e("label_shadow_rgba");
            this.f2506j = c1240g.m4659e("label_fraction");
            this.f2507k = c1240g.m4659e("label_html");
            this.f2508l = new C0792l();
            if (!this.f2508l.m2448a(c1240g.m4652b("logo"))) {
                return false;
            }
            this.f2509m = new C0787g();
            if (!this.f2509m.m2435a(c1240g.m4652b("option_yes"))) {
                return false;
            }
            this.f2510n = new C0787g();
            if (this.f2510n.m2435a(c1240g.m4652b("option_no"))) {
                return true;
            }
            return false;
        }

        void m2471b() {
            C0745a.f2001l.f2143c.m4718a(this.f2501e, this.f2502f);
            this.f2508l.m2449b();
            this.f2509m.m2436b();
            this.f2510n.m2436b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.w */
    static class C0803w {
        String f2511a;
        String f2512b;
        C0792l f2513c;
        C0802v f2514d;

        C0803w() {
        }

        boolean m2472a() {
            if (C0745a.f2001l.f2143c.m4719a(this.f2511a) && this.f2513c.m2447a() && this.f2514d.m2469a()) {
                return true;
            }
            return false;
        }

        boolean m2473a(C1240g c1240g) {
            this.f2511a = c1240g.m4659e("background_image");
            this.f2512b = c1240g.m4659e("background_image_last_modified");
            this.f2513c = new C0792l();
            if (!this.f2513c.m2448a(c1240g.m4652b("background_logo"))) {
                return false;
            }
            this.f2514d = new C0802v();
            if (this.f2514d.m2470a(c1240g.m4652b("dialog"))) {
                return true;
            }
            return false;
        }

        void m2474b() {
            C0745a.f2001l.f2143c.m4718a(this.f2511a, this.f2512b);
            this.f2513c.m2449b();
            this.f2514d.m2471b();
        }
    }

    /* renamed from: com.jirbo.adcolony.n.x */
    static class C0804x {
        boolean f2515a;
        int f2516b;
        int f2517c;
        String f2518d;
        String f2519e;
        C0787g f2520f;
        C0787g f2521g;
        C0787g f2522h;
        C0787g f2523i;

        C0804x() {
        }

        boolean m2475a() {
            if (!this.f2515a) {
                return true;
            }
            if (!C0745a.f2001l.f2143c.m4719a(this.f2518d)) {
                return false;
            }
            if (!this.f2522h.m2434a()) {
                return false;
            }
            if (!this.f2523i.m2434a()) {
                return false;
            }
            if (!this.f2521g.m2434a()) {
                return false;
            }
            if (this.f2520f.m2434a()) {
                return true;
            }
            return false;
        }

        boolean m2476a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2515a = c1240g.m4663h("enabled");
            if (!this.f2515a) {
                return true;
            }
            this.f2516b = c1240g.m4661g("width");
            this.f2517c = c1240g.m4661g("height");
            this.f2518d = c1240g.m4659e("background_image");
            this.f2519e = c1240g.m4659e("background_image_last_modified");
            if (C0745a.f1995f != null) {
                this.f2518d = C0745a.f1995f;
            }
            this.f2522h = new C0787g();
            if (!this.f2522h.m2435a(c1240g.m4652b("replay"))) {
                return false;
            }
            this.f2523i = new C0787g();
            if (!this.f2523i.m2435a(c1240g.m4652b("continue"))) {
                return false;
            }
            this.f2521g = new C0787g();
            if (!this.f2521g.m2435a(c1240g.m4652b("download"))) {
                return false;
            }
            this.f2520f = new C0787g();
            if (this.f2520f.m2435a(c1240g.m4652b("info"))) {
                return true;
            }
            return false;
        }

        void m2477b() {
            if (this.f2515a) {
                C0745a.f2001l.f2143c.m4718a(this.f2518d, this.f2519e);
                this.f2522h.m2436b();
                this.f2523i.m2436b();
                this.f2521g.m2436b();
                this.f2520f.m2436b();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.n.y */
    static class C0805y {
        ArrayList<String> f2524a;
        ArrayList<String> f2525b;
        ArrayList<String> f2526c;
        HashMap<String, ArrayList<String>> f2527d;

        C0805y() {
            this.f2524a = new ArrayList();
            this.f2525b = new ArrayList();
            this.f2526c = new ArrayList();
            this.f2527d = new HashMap();
        }

        boolean m2478a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            ArrayList d = c1240g.m4658d("update");
            this.f2524a = d;
            if (d == null) {
                return false;
            }
            d = c1240g.m4658d("install");
            this.f2525b = d;
            if (d == null) {
                return false;
            }
            d = c1240g.m4658d("session_start");
            this.f2526c = d;
            if (d == null) {
                return false;
            }
            this.f2527d.put("update", this.f2524a);
            this.f2527d.put("install", this.f2525b);
            this.f2527d.put("session_start", this.f2526c);
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.n.z */
    static class C0806z {
        ArrayList<String> f2528A;
        ArrayList<String> f2529B;
        HashMap<String, ArrayList<String>> f2530C;
        ArrayList<String> f2531a;
        ArrayList<String> f2532b;
        ArrayList<String> f2533c;
        ArrayList<String> f2534d;
        ArrayList<String> f2535e;
        ArrayList<String> f2536f;
        ArrayList<String> f2537g;
        ArrayList<String> f2538h;
        ArrayList<String> f2539i;
        ArrayList<String> f2540j;
        ArrayList<String> f2541k;
        ArrayList<String> f2542l;
        ArrayList<String> f2543m;
        ArrayList<String> f2544n;
        ArrayList<String> f2545o;
        ArrayList<String> f2546p;
        ArrayList<String> f2547q;
        ArrayList<String> f2548r;
        ArrayList<String> f2549s;
        ArrayList<String> f2550t;
        ArrayList<String> f2551u;
        ArrayList<String> f2552v;
        ArrayList<String> f2553w;
        ArrayList<String> f2554x;
        ArrayList<String> f2555y;
        ArrayList<String> f2556z;

        C0806z() {
            this.f2531a = new ArrayList();
            this.f2532b = new ArrayList();
            this.f2533c = new ArrayList();
            this.f2534d = new ArrayList();
            this.f2535e = new ArrayList();
            this.f2536f = new ArrayList();
            this.f2537g = new ArrayList();
            this.f2538h = new ArrayList();
            this.f2539i = new ArrayList();
            this.f2540j = new ArrayList();
            this.f2541k = new ArrayList();
            this.f2542l = new ArrayList();
            this.f2543m = new ArrayList();
            this.f2544n = new ArrayList();
            this.f2545o = new ArrayList();
            this.f2546p = new ArrayList();
            this.f2547q = new ArrayList();
            this.f2548r = new ArrayList();
            this.f2549s = new ArrayList();
            this.f2550t = new ArrayList();
            this.f2551u = new ArrayList();
            this.f2552v = new ArrayList();
            this.f2553w = new ArrayList();
            this.f2554x = new ArrayList();
            this.f2555y = new ArrayList();
            this.f2556z = new ArrayList();
            this.f2528A = new ArrayList();
            this.f2529B = new ArrayList();
            this.f2530C = new HashMap();
        }

        boolean m2479a(C1240g c1240g) {
            if (c1240g == null) {
                return false;
            }
            this.f2531a = c1240g.m4658d("replay");
            this.f2532b = c1240g.m4658d("card_shown");
            this.f2533c = c1240g.m4658d("html5_interaction");
            this.f2534d = c1240g.m4658d("cancel");
            this.f2535e = c1240g.m4658d("download");
            this.f2536f = c1240g.m4658d(SchemaSymbols.ATTVAL_SKIP);
            this.f2537g = c1240g.m4658d("info");
            this.f2538h = c1240g.m4658d("midpoint");
            this.f2539i = c1240g.m4658d("card_dissolved");
            this.f2540j = c1240g.m4658d("start");
            this.f2541k = c1240g.m4658d("third_quartile");
            this.f2542l = c1240g.m4658d("complete");
            this.f2543m = c1240g.m4658d("continue");
            this.f2544n = c1240g.m4658d("in_video_engagement");
            this.f2545o = c1240g.m4658d("reward_v4vc");
            this.f2546p = c1240g.m4658d("first_quartile");
            this.f2547q = c1240g.m4658d("v4iap");
            this.f2548r = c1240g.m4658d("video_expanded");
            this.f2549s = c1240g.m4658d("sound_mute");
            this.f2550t = c1240g.m4658d("sound_unmute");
            this.f2551u = c1240g.m4658d("video_paused");
            this.f2552v = c1240g.m4658d("video_resumed");
            this.f2553w = c1240g.m4658d("native_start");
            this.f2554x = c1240g.m4658d("native_first_quartile");
            this.f2555y = c1240g.m4658d("native_midpoint");
            this.f2556z = c1240g.m4658d("native_third_quartile");
            this.f2528A = c1240g.m4658d("native_complete");
            this.f2529B = c1240g.m4658d("native_overlay_click");
            this.f2530C.put("replay", this.f2531a);
            this.f2530C.put("card_shown", this.f2532b);
            this.f2530C.put("html5_interaction", this.f2533c);
            this.f2530C.put("cancel", this.f2534d);
            this.f2530C.put("download", this.f2535e);
            this.f2530C.put(SchemaSymbols.ATTVAL_SKIP, this.f2536f);
            this.f2530C.put("info", this.f2537g);
            this.f2530C.put("midpoint", this.f2538h);
            this.f2530C.put("card_dissolved", this.f2539i);
            this.f2530C.put("start", this.f2540j);
            this.f2530C.put("third_quartile", this.f2541k);
            this.f2530C.put("complete", this.f2542l);
            this.f2530C.put("continue", this.f2543m);
            this.f2530C.put("in_video_engagement", this.f2544n);
            this.f2530C.put("reward_v4vc", this.f2545o);
            this.f2530C.put("first_quartile", this.f2546p);
            this.f2530C.put("v4iap", this.f2547q);
            this.f2530C.put("video_expanded", this.f2548r);
            this.f2530C.put("sound_mute", this.f2549s);
            this.f2530C.put("sound_unmute", this.f2550t);
            this.f2530C.put("video_paused", this.f2551u);
            this.f2530C.put("video_resumed", this.f2552v);
            this.f2530C.put("native_start", this.f2553w);
            this.f2530C.put("native_first_quartile", this.f2554x);
            this.f2530C.put("native_midpoint", this.f2555y);
            this.f2530C.put("native_third_quartile", this.f2556z);
            this.f2530C.put("native_complete", this.f2528A);
            this.f2530C.put("native_overlay_click", this.f2529B);
            return true;
        }
    }

    C0807n() {
    }
}
