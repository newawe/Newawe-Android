package com.jirbo.adcolony;

import android.os.Build.VERSION;
import com.Newawe.storage.DatabaseOpenHelper;
import com.google.android.gms.common.zze;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.C0807n.C0781a;
import com.jirbo.adcolony.C0807n.C0783c;
import com.jirbo.adcolony.C0807n.C0790j;
import com.jirbo.adcolony.C0807n.C0804x;
import com.jirbo.adcolony.C0807n.ac;
import com.jirbo.adcolony.C0807n.ad;
import com.jirbo.adcolony.C0807n.ag;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.cookie.ClientCookie;

/* renamed from: com.jirbo.adcolony.c */
class C0760c {
    static String f2097c;
    String f2098A;
    String f2099B;
    String f2100C;
    String f2101D;
    String f2102E;
    String f2103F;
    String f2104G;
    String f2105H;
    String f2106I;
    String f2107J;
    String f2108K;
    String f2109L;
    String f2110M;
    String f2111N;
    boolean f2112O;
    boolean f2113P;
    C0762d f2114a;
    String f2115b;
    int f2116d;
    int f2117e;
    boolean f2118f;
    boolean f2119g;
    String f2120h;
    C1240g f2121i;
    C1240g f2122j;
    String f2123k;
    String[] f2124l;
    ad f2125m;
    C0781a f2126n;
    double f2127o;
    String f2128p;
    String f2129q;
    String f2130r;
    String f2131s;
    boolean f2132t;
    String f2133u;
    String f2134v;
    C1236c f2135w;
    String f2136x;
    String f2137y;
    String f2138z;

    static {
        f2097c = "https://androidads23.adcolony.com/configure";
    }

    C0760c(C0762d c0762d) {
        this.f2115b = "2.3.0";
        this.f2116d = HttpStatus.SC_MULTIPLE_CHOICES;
        this.f2117e = 0;
        this.f2118f = false;
        this.f2119g = false;
        this.f2121i = new C1240g();
        this.f2127o = 0.0d;
        this.f2128p = "android";
        this.f2129q = "android_native";
        this.f2130r = "1.0";
        this.f2131s = "google";
        this.f2132t = false;
        this.f2137y = StringUtils.EMPTY;
        this.f2114a = c0762d;
    }

    void m2237a(String str) {
        if (str == null) {
            str = StringUtils.EMPTY;
        }
        for (String split : r8.split(",")) {
            String split2;
            String[] split3 = split2.split(":");
            if (split3.length == 2) {
                String str2 = split3[0];
                split2 = split3[1];
                if (str2.equals(ClientCookie.VERSION_ATTR)) {
                    this.f2130r = split2;
                } else if (str2.equals("store")) {
                    if (split2.toLowerCase().equals("google") || split2.toLowerCase().equals("amazon")) {
                        this.f2131s = split2;
                    } else {
                        throw new AdColonyException("Origin store in client options must be set to either 'google' or 'amazon'");
                    }
                } else if (str2.equals("skippable")) {
                    this.f2132t = false;
                }
            } else if (split3[0].equals("skippable")) {
                this.f2132t = false;
            }
        }
    }

    void m2236a() {
        String a;
        while (!AdColony.f1830c && this.f2117e < 60) {
            try {
                this.f2117e++;
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
        this.f2117e = 0;
        this.f2114a.f2147g.m2226a();
        this.f2109L = m2235a(C0772g.f2202a, StringUtils.EMPTY);
        this.f2112O = C0772g.f2203b;
        this.f2133u = m2235a(C0772g.m2298a(), StringUtils.EMPTY);
        if (this.f2109L.equals(StringUtils.EMPTY)) {
            a = m2235a(aa.m2178b(this.f2133u), StringUtils.EMPTY);
        } else {
            a = StringUtils.EMPTY;
        }
        this.f2134v = a;
        this.f2136x = m2235a(C0772g.m2299b(), StringUtils.EMPTY);
        if (this.f2138z == null) {
            this.f2138z = m2235a(C0772g.m2302e(), StringUtils.EMPTY);
        }
        this.f2098A = m2235a(C0772g.m2309l(), StringUtils.EMPTY);
        this.f2099B = m2235a(C0772g.m2310m(), StringUtils.EMPTY);
        this.f2103F = m2235a(C0772g.m2307j(), "en");
        this.f2104G = m2235a(C0772g.m2311n(), StringUtils.EMPTY);
        this.f2105H = m2235a(C0772g.m2312o(), StringUtils.EMPTY);
        this.f2108K = m2235a(StringUtils.EMPTY + VERSION.SDK_INT, StringUtils.EMPTY);
        this.f2101D = m2235a(C0772g.m2305h(), StringUtils.EMPTY);
        this.f2102E = StringUtils.EMPTY;
        this.f2106I = m2235a(StringUtils.EMPTY + C0772g.m2300c(), StringUtils.EMPTY);
        this.f2107J = m2235a(StringUtils.EMPTY + C0772g.m2301d(), StringUtils.EMPTY);
        boolean z = aa.m2181d() && aa.m2182e();
        this.f2113P = z;
        C0745a.af = this.f2105H;
        C0745a.ag = this.f2115b;
        if (C0745a.f2002m) {
            this.f2100C = "tablet";
        } else {
            this.f2100C = "phone";
        }
        this.f2135w = new C1236c();
        if (aa.m2176a(zze.GOOGLE_PLAY_STORE_PACKAGE) || aa.m2176a("com.android.market")) {
            this.f2135w.m4613a("google");
        }
        if (aa.m2176a("com.amazon.venezia")) {
            this.f2135w.m4613a("amazon");
        }
        if (C0777l.f2240b.f2244f) {
            C0777l.f2240b.m2349a("sdk_version:").m2353b(this.f2115b);
            C0777l.f2240b.m2349a("ad_manifest_url:").m2353b(f2097c);
            C0777l.f2240b.m2349a("app_id:").m2353b(this.f2123k);
            C0777l.f2240b.m2349a("zone_ids:").m2353b(this.f2124l);
            C0777l.f2240b.m2349a("os_name:").m2353b(this.f2128p);
            C0777l.f2240b.m2349a("sdk_type:").m2353b(this.f2129q);
            C0777l.f2240b.m2349a("app_version:").m2353b(this.f2130r);
            C0777l.f2240b.m2349a("origin_store:").m2353b(this.f2131s);
            C0777l.f2240b.m2349a("skippable:").m2354b(this.f2132t);
            C0777l.f2240b.m2349a("android_id:").m2353b(this.f2133u);
            C0777l.f2240b.m2349a("android_id_sha1:").m2353b(this.f2134v);
            C0777l.f2240b.m2349a("available_stores:").m2353b(this.f2135w);
            C0777l.f2240b.m2349a("carrier_name:").m2353b(this.f2136x);
            C0777l.f2240b.m2349a("custom_id:").m2353b(this.f2137y);
            C0777l.f2240b.m2349a("device_id:").m2353b(this.f2138z);
            C0777l.f2240b.m2349a("device_manufacturer:").m2353b(this.f2098A);
            C0777l.f2240b.m2349a("device_model:").m2353b(this.f2099B);
            C0777l.f2240b.m2349a("device_type:").m2353b(this.f2100C);
            C0777l.f2240b.m2349a("imei:").m2353b(this.f2101D);
            C0777l.f2240b.m2349a("imei_sha1:").m2353b(this.f2102E);
            C0777l.f2240b.m2349a("language:").m2353b(this.f2103F);
            C0777l.f2240b.m2349a("open_udid:").m2353b(this.f2104G);
            C0777l.f2240b.m2349a("os_version:").m2353b(this.f2105H);
        }
        C1240g c1240g = new C1240g();
        c1240g.m4655b("os_name", this.f2128p);
        c1240g.m4655b("os_version", this.f2105H);
        c1240g.m4655b("device_api", this.f2108K);
        c1240g.m4655b("app_version", this.f2130r);
        c1240g.m4655b("android_id_sha1", this.f2134v);
        c1240g.m4655b("device_id", this.f2138z);
        c1240g.m4655b("open_udid", this.f2104G);
        c1240g.m4655b("device_type", this.f2100C);
        c1240g.m4655b("ln", this.f2103F);
        c1240g.m4655b("device_brand", this.f2098A);
        c1240g.m4655b("device_model", this.f2099B);
        c1240g.m4654b("screen_width", C0772g.m2303f());
        c1240g.m4654b("screen_height", C0772g.m2304g());
        c1240g.m4655b("sdk_type", this.f2129q);
        c1240g.m4655b("sdk_version", this.f2115b);
        c1240g.m4655b("origin_store", this.f2131s);
        c1240g.m4649a("available_stores", this.f2135w);
        c1240g.m4655b("imei_sha1", this.f2102E);
        c1240g.m4655b("memory_class", this.f2106I);
        c1240g.m4655b("memory_used_mb", this.f2107J);
        c1240g.m4655b("advertiser_id", this.f2109L);
        c1240g.m4656b("limit_tracking", this.f2112O);
        c1240g.m4656b("immersion", this.f2113P);
        this.f2122j = c1240g;
        this.f2114a.f2146f.m2109a();
        this.f2114a.f2143c.m4717a();
        this.f2114a.f2144d.m4728a();
        this.f2114a.f2142b.m4693a();
        this.f2114a.f2145e.m2501a();
        this.f2119g = true;
        C0745a.f2001l.f2142b.f4000i.f2378n = new ag();
        this.f2114a.f2142b.m4707h();
        if (this.f2114a.f2142b.f4000i.f2373i == null || this.f2114a.f2142b.f4000i.f2373i.equals(StringUtils.EMPTY)) {
            this.f2114a.f2142b.f4000i.f2373i = "all";
        }
        if (this.f2114a.f2142b.f4000i.f2374j == null || this.f2114a.f2142b.f4000i.f2374j.equals(StringUtils.EMPTY)) {
            this.f2114a.f2142b.f4000i.f2374j = "all";
        }
    }

    String m2235a(String str, String str2) {
        return str != null ? str : str2;
    }

    void m2240b(String str) {
        m2238a(str, null);
    }

    void m2238a(String str, C0781a c0781a) {
        this.f2125m = this.f2114a.f2142b.f4000i.f2378n.m2412a(str);
        if (this.f2125m != null) {
            if (c0781a == null) {
                this.f2126n = this.f2125m.m2405k();
            } else {
                this.f2126n = c0781a;
            }
            if (this.f2126n != null) {
                C1255o c1255o = this.f2114a.f2143c;
                ac acVar = this.f2126n.f2282z;
                this.f2121i.m4656b("video_enabled", acVar.f2289a);
                this.f2121i.m4655b("video_filepath", acVar.m2386b());
                this.f2121i.m4654b("video_width", acVar.f2290b);
                this.f2121i.m4654b("video_height", acVar.f2291c);
                this.f2121i.m4653b("video_duration", acVar.f2299k);
                this.f2121i.m4654b("engagement_delay", acVar.f2301m.f2392e);
                this.f2121i.m4654b("skip_delay", acVar.f2300l.f2392e);
                this.f2121i.m4655b("browser_close_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2444k.f2393f));
                this.f2121i.m4655b("browser_close_image_down", c1255o.m4720b(this.f2126n.f2278v.f2444k.f2395h));
                this.f2121i.m4655b("browser_reload_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2446m.f2393f));
                this.f2121i.m4655b("browser_reload_image_down", c1255o.m4720b(this.f2126n.f2278v.f2446m.f2395h));
                this.f2121i.m4655b("browser_back_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2443j.f2393f));
                this.f2121i.m4655b("browser_back_image_down", c1255o.m4720b(this.f2126n.f2278v.f2443j.f2395h));
                this.f2121i.m4655b("browser_forward_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2445l.f2393f));
                this.f2121i.m4655b("browser_forward_image_down", c1255o.m4720b(this.f2126n.f2278v.f2445l.f2395h));
                this.f2121i.m4655b("browser_stop_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2442i.f2393f));
                this.f2121i.m4655b("browser_stop_image_down", c1255o.m4720b(this.f2126n.f2278v.f2442i.f2395h));
                this.f2121i.m4655b("browser_glow_button", c1255o.m4720b(this.f2126n.f2278v.f2434a));
                this.f2121i.m4655b("browser_icon", c1255o.m4720b(this.f2126n.f2278v.f2441h.f2431d));
                this.f2121i.m4655b("mute", c1255o.m4720b(this.f2126n.f2255A.f2467j.f2431d));
                this.f2121i.m4655b("unmute", c1255o.m4720b(this.f2126n.f2255A.f2468k.f2431d));
                this.f2121i.m4655b("poster_image", c1255o.m4720b(this.f2126n.f2255A.f2464g.f2474a));
                this.f2121i.m4655b("thumb_image", c1255o.m4720b(this.f2126n.f2255A.f2463f.f2478a));
                this.f2121i.m4655b("advertiser_name", this.f2126n.f2255A.f2460c);
                this.f2121i.m4655b("description", this.f2126n.f2255A.f2461d);
                this.f2121i.m4655b(DatabaseOpenHelper.HISTORY_ROW_TITLE, this.f2126n.f2255A.f2462e);
                this.f2121i.m4656b("click_to_install", this.f2126n.f2255A.f2464g.f2476c.equals("install"));
                this.f2121i.m4655b("store_url", this.f2126n.f2255A.f2464g.f2477d);
                this.f2121i.m4656b("native_engagement_enabled", this.f2126n.f2255A.f2465h.f2469a);
                this.f2121i.m4655b("native_engagement_type", this.f2126n.f2255A.f2465h.f2471c);
                this.f2121i.m4655b("native_engagement_command", this.f2126n.f2255A.f2465h.f2473e);
                this.f2121i.m4655b("native_engagement_label", this.f2126n.f2255A.f2465h.f2472d);
                this.f2121i.m4655b("skip_video_image_normal", c1255o.m4720b(acVar.f2300l.f2393f));
                this.f2121i.m4655b("skip_video_image_down", c1255o.m4720b(acVar.f2300l.f2395h));
                this.f2121i.m4655b("engagement_image_normal", c1255o.m4720b(acVar.f2301m.f2393f));
                this.f2121i.m4655b("engagement_image_down", c1255o.m4720b(acVar.f2301m.f2395h));
                this.f2121i.m4654b("engagement_height", acVar.f2301m.f2390c);
                this.f2121i.m4656b("image_overlay_enabled", acVar.f2302n.f2388a);
                this.f2121i.m4655b("image_overlay_filepath", c1255o.m4720b(acVar.f2302n.f2393f));
                this.f2121i.m4656b("haptics_enabled", acVar.f2303o.f2424a);
                this.f2121i.m4655b("haptics_filepath", c1255o.m4720b(acVar.f2303o.f2426c));
                this.f2121i.m4656b("v4iap_enabled", this.f2126n.f2256B.f2285c);
                this.f2121i.m4655b("product_id", this.f2126n.f2256B.f2283a);
                this.f2121i.m4655b("in_progress", this.f2126n.f2256B.f2284b);
                m2239b();
            }
        }
    }

    void m2241c(String str) {
        this.f2125m = this.f2114a.f2142b.f4000i.f2378n.m2412a(str);
        this.f2126n = this.f2125m.m2405k();
        C1255o c1255o = this.f2114a.f2143c;
        ac acVar = this.f2126n.f2282z;
        this.f2121i.m4656b("video_enabled", acVar.f2289a);
        this.f2121i.m4655b("video_filepath", acVar.m2386b());
        this.f2121i.m4654b("video_width", acVar.f2290b);
        this.f2121i.m4654b("video_height", acVar.f2291c);
        this.f2121i.m4653b("video_duration", acVar.f2299k);
        C0745a.f2006q = acVar.f2299k;
        this.f2121i.m4654b("engagement_delay", acVar.f2301m.f2392e);
        this.f2121i.m4654b("skip_delay", acVar.f2300l.f2392e);
        m2239b();
        C0783c c0783c = this.f2126n.f2279w;
        this.f2121i.m4655b("pre_popup_bg", c1255o.m4720b(c0783c.f2362b.f2514d.f2501e));
        this.f2121i.m4655b("v4vc_logo", c1255o.m4720b(c0783c.f2362b.f2514d.f2508l.f2431d));
        this.f2121i.m4655b("no_button_normal", c1255o.m4720b(c0783c.f2362b.f2514d.f2510n.f2393f));
        this.f2121i.m4655b("no_button_down", c1255o.m4720b(c0783c.f2362b.f2514d.f2510n.f2395h));
        this.f2121i.m4655b("yes_button_normal", c1255o.m4720b(c0783c.f2362b.f2514d.f2509m.f2393f));
        this.f2121i.m4655b("yes_button_down", c1255o.m4720b(c0783c.f2362b.f2514d.f2509m.f2395h));
        this.f2121i.m4655b("done_button_normal", c1255o.m4720b(c0783c.f2363c.f2496d.f2492m.f2393f));
        this.f2121i.m4655b("done_button_down", c1255o.m4720b(c0783c.f2363c.f2496d.f2492m.f2395h));
        this.f2121i.m4655b("browser_close_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2444k.f2393f));
        this.f2121i.m4655b("browser_close_image_down", c1255o.m4720b(this.f2126n.f2278v.f2444k.f2395h));
        this.f2121i.m4655b("browser_reload_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2446m.f2393f));
        this.f2121i.m4655b("browser_reload_image_down", c1255o.m4720b(this.f2126n.f2278v.f2446m.f2395h));
        this.f2121i.m4655b("browser_back_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2443j.f2393f));
        this.f2121i.m4655b("browser_back_image_down", c1255o.m4720b(this.f2126n.f2278v.f2443j.f2395h));
        this.f2121i.m4655b("browser_forward_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2445l.f2393f));
        this.f2121i.m4655b("browser_forward_image_down", c1255o.m4720b(this.f2126n.f2278v.f2445l.f2395h));
        this.f2121i.m4655b("browser_stop_image_normal", c1255o.m4720b(this.f2126n.f2278v.f2442i.f2393f));
        this.f2121i.m4655b("browser_stop_image_down", c1255o.m4720b(this.f2126n.f2278v.f2442i.f2395h));
        this.f2121i.m4655b("browser_glow_button", c1255o.m4720b(this.f2126n.f2278v.f2434a));
        this.f2121i.m4655b("browser_icon", c1255o.m4720b(this.f2126n.f2278v.f2441h.f2431d));
        this.f2121i.m4655b("skip_video_image_normal", c1255o.m4720b(acVar.f2300l.f2393f));
        this.f2121i.m4655b("skip_video_image_down", c1255o.m4720b(acVar.f2300l.f2395h));
        this.f2121i.m4655b("engagement_image_normal", c1255o.m4720b(acVar.f2301m.f2393f));
        this.f2121i.m4655b("engagement_image_down", c1255o.m4720b(acVar.f2301m.f2395h));
        this.f2121i.m4654b("engagement_height", acVar.f2301m.f2390c);
        this.f2121i.m4656b("image_overlay_enabled", acVar.f2302n.f2388a);
        this.f2121i.m4655b("image_overlay_filepath", c1255o.m4720b(acVar.f2302n.f2393f));
        this.f2121i.m4656b("haptics_enabled", acVar.f2303o.f2424a);
        this.f2121i.m4655b("haptics_filepath", c1255o.m4720b(acVar.f2303o.f2426c));
        this.f2121i.m4656b("v4iap_enabled", this.f2126n.f2256B.f2285c);
        this.f2121i.m4655b("product_id", this.f2126n.f2256B.f2283a);
        this.f2121i.m4655b("in_progress", this.f2126n.f2256B.f2284b);
    }

    void m2239b() {
        C0804x c0804x = this.f2126n.f2281y.f2411h;
        C0790j c0790j = this.f2126n.f2281y.f2412i;
        C1255o c1255o = this.f2114a.f2143c;
        if (this.f2126n.f2281y.f2407d) {
            if (c0790j.m2441a()) {
                C0745a.f1988Y = true;
                C0745a.ad = c0790j.f2420g;
                C0745a.ae = c1255o.m4720b(c0790j.f2419f.f2456b);
                this.f2121i.m4655b("close_image_normal", c1255o.m4720b(c0790j.f2423j.f2393f));
                this.f2121i.m4655b("close_image_down", c1255o.m4720b(c0790j.f2423j.f2395h));
                this.f2121i.m4655b("reload_image_normal", c1255o.m4720b(c0790j.f2422i.f2393f));
                this.f2121i.m4655b("reload_image_down", c1255o.m4720b(c0790j.f2422i.f2395h));
            } else {
                C0745a.f1988Y = false;
                this.f2121i.m4655b("end_card_filepath", c1255o.m4720b(c0804x.f2518d));
                this.f2121i.m4655b("info_image_normal", c1255o.m4720b(c0804x.f2520f.f2393f));
                this.f2121i.m4655b("info_image_down", c1255o.m4720b(c0804x.f2520f.f2395h));
                this.f2121i.m4655b("info_url", c0804x.f2520f.f2397j);
                this.f2121i.m4655b("replay_image_normal", c1255o.m4720b(c0804x.f2522h.f2393f));
                this.f2121i.m4655b("replay_image_down", c1255o.m4720b(c0804x.f2522h.f2395h));
                this.f2121i.m4655b("continue_image_normal", c1255o.m4720b(c0804x.f2523i.f2393f));
                this.f2121i.m4655b("continue_image_down", c1255o.m4720b(c0804x.f2523i.f2395h));
                this.f2121i.m4655b("download_image_normal", c1255o.m4720b(c0804x.f2521g.f2393f));
                this.f2121i.m4655b("download_image_down", c1255o.m4720b(c0804x.f2521g.f2395h));
                this.f2121i.m4655b("download_url", c0804x.f2521g.f2397j);
            }
            ac acVar = this.f2126n.f2282z;
            this.f2121i.m4656b("end_card_enabled", this.f2126n.f2281y.f2407d);
            this.f2121i.m4656b("load_timeout_enabled", this.f2126n.f2281y.f2412i.f2416c);
            this.f2121i.m4653b("load_timeout", this.f2126n.f2281y.f2412i.f2415b);
            this.f2121i.m4656b("hardware_acceleration_disabled", this.f2114a.f2142b.f4000i.f2369e);
            return;
        }
        this.f2121i.m4656b("end_card_enabled", this.f2126n.f2281y.f2407d);
    }
}
