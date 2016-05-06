package com.jirbo.adcolony;

import com.Newawe.storage.DatabaseOpenHelper;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.jirbo.adcolony.ADCData.C0721i;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.ADCDownload.Listener;
import com.jirbo.adcolony.C0807n.C0786f;
import com.jirbo.adcolony.C0807n.C0805y;
import com.jirbo.adcolony.C0807n.ad;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.jirbo.adcolony.t */
class C1256t implements Listener {
    C0762d f4030a;
    ArrayList<C0813a> f4031b;
    ArrayList<C0813a> f4032c;
    int f4033d;
    boolean f4034e;
    int f4035f;
    HashMap<Integer, Boolean> f4036g;

    /* renamed from: com.jirbo.adcolony.t.a */
    static class C0813a {
        String f2578a;
        String f2579b;
        String f2580c;
        String f2581d;
        double f2582e;
        int f2583f;
        int f2584g;
        int f2585h;
        int f2586i;
        boolean f2587j;
        boolean f2588k;
        boolean f2589l;

        C0813a() {
            this.f2586i = -1;
        }
    }

    C1256t(C0762d c0762d) {
        this.f4031b = new ArrayList();
        this.f4032c = new ArrayList();
        this.f4033d = 0;
        this.f4034e = false;
        this.f4036g = new HashMap();
        this.f4030a = c0762d;
    }

    void m4732a(String str, AdColonyAd adColonyAd) {
        if (this.f4030a != null && this.f4030a.f2142b != null && this.f4030a.f2142b.f4000i != null && this.f4030a.f2142b.f4000i.f2378n != null && this.f4030a.f2142b.f4000i.f2378n.m2412a(str) != null) {
            C0777l.f2239a.m2349a("Ad request for zone ").m2353b((Object) str);
            ad a = this.f4030a.f2142b.f4000i.f2378n.m2412a(str);
            if (a != null && a.f2315l != null && a.f2315l.f2323a != null) {
                C1240g c1240g = new C1240g();
                if (adColonyAd.f1840g == 0) {
                    c1240g.m4656b("request_denied", false);
                } else {
                    c1240g.m4656b("request_denied", true);
                }
                c1240g.m4654b("request_denied_reason", adColonyAd.f1840g);
                m4734a("request", a.f2315l.f2323a, c1240g, adColonyAd);
                C0777l.f2239a.m2349a("Tracking ad request - URL : ").m2353b(a.f2315l.f2323a);
            }
        }
    }

    void m4730a(String str, C1240g c1240g) {
        C0786f c0786f = this.f4030a.f2142b.f4000i.f2375k;
        if (c0786f != null) {
            m4733a(str, c0786f.f2387h.m4659e(str), c1240g);
        }
        C0805y c0805y = this.f4030a.f2142b.f4000i.f2376l;
        if (c0805y != null) {
            m4735a(str, (ArrayList) c0805y.f2527d.get(str));
        }
    }

    void m4737b(String str, AdColonyAd adColonyAd) {
        m4731a(str, null, adColonyAd);
    }

    void m4731a(String str, C1240g c1240g, AdColonyAd adColonyAd) {
        if (str == null) {
            C0777l.f2242d.m2353b((Object) "No such event type:").m2353b((Object) str);
            return;
        }
        if (str.equals("start") || str.equals("native_start")) {
            C0814u c0814u = C0745a.f2001l.f2145e;
            c0814u.f2599j++;
        }
        if (c1240g == null) {
            c1240g = new C1240g();
            c1240g.m4656b("replay", adColonyAd.f1854u);
        }
        c1240g.m4654b("s_imp_count", C0745a.f2001l.f2145e.f2599j);
        m4734a(str, (String) adColonyAd.f1837A.get(str), c1240g, adColonyAd);
        m4735a(str, (ArrayList) adColonyAd.f1838B.get(str));
    }

    void m4733a(String str, String str2, C1240g c1240g) {
        m4734a(str, str2, c1240g, null);
    }

    void m4734a(String str, String str2, C1240g c1240g, AdColonyAd adColonyAd) {
        if (str2 != null && !str2.equals(StringUtils.EMPTY)) {
            C0813a c0813a;
            if (c1240g == null) {
                c1240g = new C1240g();
            }
            String b = aa.m2177b();
            if (adColonyAd != null) {
                c1240g.m4655b("asi", adColonyAd.f1846m);
            }
            double c = aa.m2179c() - this.f4030a.f2145e.f2596g;
            if (c <= 0.0d || c < 604800.0d) {
                c1240g.m4653b("s_time", C0745a.f2001l.f2145e.f2598i);
                c1240g.m4655b("sid", this.f4030a.f2145e.f2600k);
                c1240g.m4655b("guid", b);
                c1240g.m4655b("guid_key", aa.m2178b(b + "DUBu6wJ27y6xs7VWmNDw67DD"));
                c0813a = new C0813a();
                c0813a.f2578a = str;
                c0813a.f2579b = str2;
                C0777l.f2239a.m2353b((Object) "EVENT ---------------------------");
                C0777l.f2239a.m2349a("EVENT - TYPE = ").m2353b((Object) str);
                C0777l.f2239a.m2349a("EVENT - URL  = ").m2353b((Object) str2);
                c0813a.f2580c = c1240g.m2095q();
            } else {
                c1240g.m4653b("s_time", C0745a.f2001l.f2145e.f2598i);
                c1240g.m4655b("sid", this.f4030a.f2145e.f2600k);
                c1240g.m4655b("guid", b);
                c1240g.m4655b("guid_key", aa.m2178b(b + "DUBu6wJ27y6xs7VWmNDw67DD"));
                c0813a = new C0813a();
                c0813a.f2578a = str;
                c0813a.f2579b = str2;
                C0777l.f2239a.m2353b((Object) "EVENT ---------------------------");
                C0777l.f2239a.m2349a("EVENT - TYPE = ").m2353b((Object) str);
                C0777l.f2239a.m2349a("EVENT - URL  = ").m2353b((Object) str2);
                c0813a.f2580c = c1240g.m2095q();
            }
            if (str.equals("reward_v4vc")) {
                c0813a.f2581d = c1240g.m4659e("v4vc_name");
                c0813a.f2585h = c1240g.m4661g("v4vc_amount");
            }
            this.f4031b.add(c0813a);
            this.f4034e = true;
            C0745a.f2015z = true;
        }
    }

    void m4735a(String str, ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String str2 = (String) arrayList.get(i);
                C0813a c0813a = new C0813a();
                c0813a.f2578a = str;
                c0813a.f2579b = str2;
                c0813a.f2589l = true;
                this.f4031b.add(c0813a);
            }
            this.f4034e = true;
            C0745a.f2015z = true;
        }
    }

    void m4729a(double d, AdColonyAd adColonyAd) {
        double d2 = adColonyAd.f1849p;
        if (d >= d2) {
            if (d2 < 0.25d && d >= 0.25d) {
                if (AdColony.isZoneV4VC(adColonyAd.f1841h) || !adColonyAd.f1845l.equals("native")) {
                    m4737b("first_quartile", adColonyAd);
                } else {
                    m4737b("native_first_quartile", adColonyAd);
                }
            }
            if (d2 < 0.5d && d >= 0.5d) {
                if (AdColony.isZoneV4VC(adColonyAd.f1841h) || !adColonyAd.f1845l.equals("native")) {
                    m4737b("midpoint", adColonyAd);
                } else {
                    m4737b("native_midpoint", adColonyAd);
                }
            }
            if (d2 < 0.75d && d >= 0.75d) {
                if (AdColony.isZoneV4VC(adColonyAd.f1841h) || !adColonyAd.f1845l.equals("native")) {
                    m4737b("third_quartile", adColonyAd);
                } else {
                    m4737b("native_third_quartile", adColonyAd);
                }
            }
            if (d2 < 1.0d && d >= 1.0d && !adColonyAd.f1845l.equals("native")) {
                C0777l.f2239a.m2349a("Tracking ad event - complete");
                C1240g c1240g = new C1240g();
                if (adColonyAd.f1853t) {
                    c1240g.m4654b("ad_slot", C0745a.f2001l.f2145e.f2599j);
                } else {
                    c1240g.m4654b("ad_slot", C0745a.f2001l.f2145e.f2599j);
                }
                c1240g.m4656b("replay", adColonyAd.f1854u);
                m4731a("complete", c1240g, adColonyAd);
                adColonyAd.f1843j.f2274r = false;
            }
            adColonyAd.f1849p = d;
        }
    }

    void m4728a() {
        m4736b();
        this.f4033d = 0;
    }

    void m4736b() {
        C0745a.f2015z = true;
        C1236c c = C0776k.m2336c(new C0771f("tracking_info.txt"));
        if (c != null) {
            this.f4031b.clear();
            for (int i = 0; i < c.m4628i(); i++) {
                C1240g b = c.m4619b(i);
                C0813a c0813a = new C0813a();
                c0813a.f2578a = b.m4659e("type");
                c0813a.f2579b = b.m4659e(DatabaseOpenHelper.HISTORY_ROW_URL);
                c0813a.f2580c = b.m4646a("payload", null);
                c0813a.f2583f = b.m4661g("attempts");
                c0813a.f2589l = b.m4663h("third_party");
                if (c0813a.f2578a.equals("v4vc_callback") || c0813a.f2578a.equals("reward_v4vc")) {
                    c0813a.f2581d = b.m4659e("v4vc_name");
                    c0813a.f2585h = b.m4661g("v4vc_amount");
                }
                this.f4031b.add(c0813a);
            }
        }
        C0777l.f2239a.m2349a("Loaded ").m2347a(this.f4031b.size()).m2353b((Object) " events");
    }

    void m4738c() {
        this.f4032c.clear();
        this.f4032c.addAll(this.f4031b);
        this.f4031b.clear();
        C1236c c1236c = new C1236c();
        for (int i = 0; i < this.f4032c.size(); i++) {
            C0813a c0813a = (C0813a) this.f4032c.get(i);
            if (!c0813a.f2587j) {
                this.f4031b.add(c0813a);
                C0721i c1240g = new C1240g();
                c1240g.m4655b("type", c0813a.f2578a);
                c1240g.m4655b(DatabaseOpenHelper.HISTORY_ROW_URL, c0813a.f2579b);
                if (c0813a.f2580c != null) {
                    c1240g.m4655b("payload", c0813a.f2580c);
                }
                c1240g.m4654b("attempts", c0813a.f2583f);
                if (c0813a.f2581d != null) {
                    c1240g.m4655b("v4vc_name", c0813a.f2581d);
                    c1240g.m4654b("v4vc_amount", c0813a.f2585h);
                }
                if (c0813a.f2589l) {
                    c1240g.m4656b("third_party", true);
                }
                c1236c.m4612a(c1240g);
            }
        }
        this.f4032c.clear();
        C0777l.f2239a.m2349a("Saving tracking_info (").m2347a(this.f4031b.size()).m2353b((Object) " events)");
        C0776k.m2329a(new C0771f("tracking_info.txt"), c1236c);
    }

    void m4739d() {
        if (this.f4034e) {
            this.f4034e = false;
            m4738c();
        }
        m4740e();
    }

    void m4740e() {
        if (this.f4031b.size() != 0) {
            while (this.f4031b.size() > DateUtils.MILLIS_IN_SECOND) {
                this.f4031b.remove(this.f4031b.size() - 1);
            }
            if (C0811q.m2489c()) {
                double c = aa.m2179c();
                for (int i = 0; i < this.f4031b.size(); i++) {
                    C0813a c0813a = (C0813a) this.f4031b.get(i);
                    if (c0813a.f2582e < c && !c0813a.f2588k) {
                        if (this.f4033d != 5) {
                            this.f4033d++;
                            c0813a.f2588k = true;
                            if (c0813a.f2578a.equals("v4vc_callback")) {
                                int i2 = this.f4035f;
                                this.f4035f = i2 + 1;
                                c0813a.f2586i = i2;
                                this.f4036g.put(Integer.valueOf(c0813a.f2586i), Boolean.valueOf(C0745a.f2004o));
                            }
                            ADCDownload a = new ADCDownload(this.f4030a, c0813a.f2579b, this).m4673a(c0813a);
                            if (c0813a.f2589l) {
                                a.f3921h = true;
                            }
                            if (c0813a.f2580c != null) {
                                a.m4674a("application/json", c0813a.f2580c);
                            }
                            C0777l.f2240b.m2349a("Submitting '").m2349a(c0813a.f2578a).m2353b((Object) "' event.");
                            a.m4676b();
                            C0745a.f2015z = true;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void on_download_finished(ADCDownload download) {
        boolean z;
        C0745a.f2015z = true;
        this.f4034e = true;
        this.f4033d--;
        C0813a c0813a = (C0813a) download.f3918e;
        C0777l.f2239a.m2349a("on_download_finished - event.type = ").m2353b(c0813a.f2578a);
        c0813a.f2588k = false;
        boolean z2 = download.f3922i;
        if (z2 && c0813a.f2580c != null) {
            C1240g b = C0776k.m2334b(download.f3927n);
            if (b != null) {
                z2 = b.m4659e(NotificationCompatApi21.CATEGORY_STATUS).equals("success");
                if (z2 && c0813a.f2578a.equals("reward_v4vc")) {
                    if (b.m4663h("v4vc_status")) {
                        String e = b.m4659e("v4vc_callback");
                        if (e.length() > 0) {
                            C0813a c0813a2 = new C0813a();
                            c0813a2.f2578a = "v4vc_callback";
                            c0813a2.f2579b = e;
                            c0813a2.f2581d = c0813a.f2581d;
                            c0813a2.f2585h = c0813a.f2585h;
                            this.f4031b.add(c0813a2);
                        } else {
                            if (C0745a.f1984U != null) {
                                C0745a.f1984U.f3967o = true;
                            }
                            C0777l.f2239a.m2353b((Object) "Client-side V4VC success");
                        }
                    } else {
                        C0777l.f2239a.m2353b((Object) "Client-side V4VC failure");
                    }
                }
            } else {
                z2 = false;
            }
        }
        if (z2 && c0813a.f2578a.equals("v4vc_callback")) {
            C0777l.f2239a.m2353b((Object) "v4vc_callback response:").m2353b(download.f3927n);
            if (download.f3927n.indexOf("vc_success") != -1 && ((Boolean) this.f4036g.get(Integer.valueOf(c0813a.f2586i))).booleanValue()) {
                if (C0745a.f1984U != null) {
                    C0745a.f1984U.f3967o = true;
                }
                C0777l.f2239a.m2353b((Object) "v4vc_callback success");
                this.f4030a.m2255a(true, c0813a.f2581d, c0813a.f2585h);
                z = z2;
            } else if (download.f3927n.indexOf("vc_decline") == -1 && download.f3927n.indexOf("vc_noreward") == -1) {
                C0777l.f2241c.m2349a("Server-side V4VC failure: ").m2353b(download.f3916c);
                z = false;
            } else {
                C0777l.f2241c.m2349a("Server-side V4VC failure: ").m2353b(download.f3916c);
                C0777l.f2239a.m2353b((Object) "v4vc_callback declined");
                this.f4030a.m2255a(false, StringUtils.EMPTY, 0);
                z = z2;
            }
        } else {
            z = z2;
        }
        if (z) {
            C0777l.f2239a.m2349a("Event submission SUCCESS for type: ").m2353b(c0813a.f2578a);
            c0813a.f2587j = true;
        } else {
            C0777l.f2239a.m2349a("Event submission FAILED for type: ").m2349a(c0813a.f2578a).m2349a(" on try ").m2352b(c0813a.f2583f + 1);
            c0813a.f2583f++;
            if (c0813a.f2583f >= 24) {
                C0777l.f2242d.m2353b((Object) "Discarding event after 24 attempts to report.");
                c0813a.f2587j = true;
                if (c0813a.f2578a.equals("v4vc_callback")) {
                    this.f4030a.m2255a(false, StringUtils.EMPTY, 0);
                }
            } else {
                int i = 20;
                if (c0813a.f2584g > 0) {
                    i = c0813a.f2584g * 3;
                }
                if (i > HapticContentSDK.f857b04440444044404440444) {
                    i = HapticContentSDK.f857b04440444044404440444;
                }
                C0777l.f2239a.m2349a("Retrying in ").m2347a(i).m2349a(" seconds (attempt ").m2347a(c0813a.f2583f).m2353b((Object) ")");
                c0813a.f2584g = i;
                c0813a.f2582e = aa.m2179c() + ((double) i);
            }
        }
        if (!this.f4030a.f2145e.f2591b) {
            m4738c();
        }
    }
}
