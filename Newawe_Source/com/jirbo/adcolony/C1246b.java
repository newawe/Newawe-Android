package com.jirbo.adcolony;

import android.app.Activity;
import android.os.Looper;
import com.google.android.gcm.GCMConstants;
import com.jirbo.adcolony.ADCData.C0721i;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.ADCDownload.Listener;
import com.jirbo.adcolony.C0807n.C0781a;
import com.jirbo.adcolony.C0807n.C0785e;
import com.jirbo.adcolony.C0807n.ad;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

/* renamed from: com.jirbo.adcolony.b */
class C1246b implements Listener {
    C0762d f3992a;
    boolean f3993b;
    boolean f3994c;
    boolean f3995d;
    boolean f3996e;
    boolean f3997f;
    boolean f3998g;
    double f3999h;
    C0785e f4000i;

    /* renamed from: com.jirbo.adcolony.b.1 */
    class C07591 implements Runnable {
        final /* synthetic */ C1246b f2096a;

        C07591(C1246b c1246b) {
            this.f2096a = c1246b;
        }

        public void run() {
            for (int i = 0; i < HttpStatus.SC_INTERNAL_SERVER_ERROR && C0745a.m2147a(); i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    C1246b(C0762d c0762d) {
        this.f3996e = true;
        this.f3997f = false;
        this.f3998g = true;
        this.f4000i = new C0785e();
        this.f3992a = c0762d;
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    void m4693a() {
    }

    void m4698b() {
        C0777l.f2240b.m2353b((Object) "Attempting to load backup manifest from file.");
        C0771f c0771f = new C0771f("manifest.txt");
        C1240g b = C0776k.m2333b(c0771f);
        if (b != null) {
            boolean z;
            C1236c c = b.m4652b(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT).m4657c("zones");
            if (c != null) {
                for (int i = 0; i < c.m4628i(); i++) {
                    C1240g b2 = c.m4619b(i);
                    boolean z2 = false;
                    for (Object equals : C0745a.f2001l.f2141a.f2124l) {
                        if (b2.m4659e("uuid").equals(equals)) {
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            C0745a.f1966C = true;
            if (z && m4695a(b)) {
                this.f3993b = true;
                this.f4000i.m2429a();
                C0745a.f1966C = false;
                return;
            }
            C0777l.f2240b.m2353b((Object) "Invalid manifest loaded.");
            c0771f.m2297c();
            C0745a.f1966C = false;
            this.f3993b = false;
        }
    }

    String m4701c() {
        if (!this.f3993b) {
            return null;
        }
        String str = null;
        for (int i = 0; i < this.f4000i.f2378n.m2415b(); i++) {
            ad a = this.f4000i.f2378n.m2411a(i);
            if (a.m2401g()) {
                str = a.f2304a;
                if (a.m2396c()) {
                    return a.f2304a;
                }
            }
        }
        return str;
    }

    String m4703d() {
        if (!this.f3993b) {
            return null;
        }
        String str = null;
        for (int i = 0; i < this.f4000i.f2378n.m2415b(); i++) {
            ad a = this.f4000i.f2378n.m2411a(i);
            if (a.m2403i()) {
                str = a.f2304a;
                if (a.m2396c()) {
                    return a.f2304a;
                }
            }
        }
        return str;
    }

    boolean m4696a(String str) {
        return m4697a(str, true);
    }

    boolean m4697a(String str, boolean z) {
        for (int i = 0; i < this.f4000i.f2378n.m2415b(); i++) {
            ad a = this.f4000i.f2378n.m2411a(i);
            if (a.m2397c(z) && a.f2304a.equals(str)) {
                return true;
            }
        }
        return false;
    }

    boolean m4699b(String str) {
        return m4700b(str, false);
    }

    boolean m4700b(String str, boolean z) {
        if (z) {
            return m4702c(str, z);
        }
        if (!this.f3993b) {
            return C0777l.f2241c.m2355b("Ads are not ready to be played, as they are still downloading.");
        }
        if (z) {
            return this.f4000i.m2432a(str, true, false);
        }
        return this.f4000i.m2432a(str, false, true);
    }

    boolean m4702c(String str, boolean z) {
        if (!this.f3993b) {
            return false;
        }
        if (z) {
            return this.f4000i.m2432a(str, true, false);
        }
        return this.f4000i.m2432a(str, false, true);
    }

    void m4704e() {
    }

    void m4705f() {
        this.f3994c = true;
        if (this.f3994c) {
            this.f3994c = false;
            if (C0772g.m2300c() >= 32) {
                this.f3999h = aa.m2179c() + 600.0d;
                m4707h();
            }
        }
        if (C0811q.m2489c()) {
            if (!C0745a.f1975L) {
                C0745a.m2163h();
            }
            C0745a.f1975L = true;
            return;
        }
        if (C0745a.f1975L) {
            C0745a.m2163h();
        }
        C0745a.f1975L = false;
    }

    void m4706g() {
        new Thread(new C07591(this)).start();
        m4707h();
    }

    void m4707h() {
        m4694a(null);
    }

    void m4694a(Activity activity) {
        C0745a.f2015z = true;
        if (!this.f3995d) {
            this.f3995d = true;
            if (!this.f3993b) {
                m4698b();
            }
        }
        if (activity != null) {
            C0745a.f1984U = null;
        }
        C1240g c1240g = this.f3992a.f2141a.f2122j;
        c1240g.m4655b("app_id", this.f3992a.f2141a.f2123k);
        C0721i c1236c = new C1236c();
        if (this.f3992a.f2141a.f2124l != null) {
            for (String str : this.f3992a.f2141a.f2124l) {
                ad g = C0745a.f2001l.m2272g(str);
                if (g == null || (g != null && (g.m2390a() || System.currentTimeMillis() - g.f2320q > g.f2318o))) {
                    c1236c.m4613a(str);
                }
            }
        }
        if (c1236c.m4628i() != 0) {
            c1240g.m4649a("zones", c1236c);
            C0721i c1240g2 = new C1240g();
            C0721i c1240g3 = new C1240g();
            C0721i c1240g4 = new C1240g();
            if (this.f3992a.f2141a.f2124l != null) {
                for (String str2 : this.f3992a.f2141a.f2124l) {
                    ad g2 = C0745a.f2001l.m2272g(str2);
                    C0721i c1236c2 = new C1236c();
                    C0721i c1236c3 = new C1236c();
                    if (!(g2 == null || g2.f2316m == null || g2.f2316m.f2364a == null)) {
                        Iterator it = g2.f2316m.f2364a.iterator();
                        while (it.hasNext()) {
                            C0781a c0781a = (C0781a) it.next();
                            if (!c0781a.m2378b()) {
                                c1236c2.m4613a(c0781a.f2257a);
                            }
                            if (c0781a.m2379c()) {
                                c1236c3.m4613a(c0781a.f2257a);
                            }
                        }
                    }
                    if (c1236c2.m4628i() > 0) {
                        c1240g2.m4649a(str2, c1236c2);
                    }
                    if (c1236c3.m4628i() > 0) {
                        c1240g3.m4649a(str2, c1236c3);
                    }
                    if (g2 != null && g2.f2313j.m4628i() > 0) {
                        c1240g4.m4649a(str2, g2.f2313j);
                    }
                }
            }
            c1240g.m4649a("ad_queue", c1240g2);
            c1240g.m4649a("ad_playing", c1240g3);
            c1240g.m4649a("ad_history", c1240g4);
            String str3 = C0745a.f2001l.f2145e.f2600k == null ? StringUtils.EMPTY : C0745a.f2001l.f2145e.f2600k;
            int i = C0745a.f2001l.f2145e.f2599j;
            c1240g.m4655b("carrier", this.f3992a.f2141a.f2136x);
            if (C0811q.m2484a()) {
                c1240g.m4655b("network_type", "wifi");
            } else if (C0811q.m2487b()) {
                c1240g.m4655b("network_type", "cell");
            } else {
                c1240g.m4655b("network_type", "none");
            }
            c1240g.m4655b("custom_id", this.f3992a.f2141a.f2137y);
            c1240g.m4655b("sid", str3);
            c1240g.m4654b("s_imp_count", i);
            if (!C0745a.f2005p) {
                C0777l a = C0777l.f2240b.m2349a("Downloading ad manifest from ");
                C0760c c0760c = this.f3992a.f2141a;
                a.m2349a(C0760c.f2097c).m2353b((Object) " with the following post content: ");
                C0777l.f2240b.m2353b(c1240g.toString());
                C0745a.f2005p = true;
                C0762d c0762d = this.f3992a;
                C0760c c0760c2 = this.f3992a.f2141a;
                new ADCDownload(c0762d, C0760c.f2097c, this).m4674a("application/json", c1240g.m2095q()).m4676b();
            }
        }
    }

    public void on_download_finished(ADCDownload download) {
        C0745a.f2015z = true;
        if (download.f3922i) {
            C0777l.f2241c.m2353b((Object) "Received ad server response from:");
            C0777l.f2241c.m2353b(download.f3916c);
            C1240g b = C0776k.m2334b(download.f3927n);
            if (b == null) {
                C0777l.f2239a.m2353b((Object) "Invalid JSON in manifest.  Raw data:");
                C0777l.f2239a.m2353b(download.f3927n);
                return;
            } else if (m4695a(b)) {
                int i;
                C0777l.f2240b.m2353b((Object) "Ad manifest updated.");
                C1236c c = C0776k.m2337c(b.m4652b(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT).m4657c("zones").toString());
                for (i = 0; i < this.f4000i.f2378n.m2415b(); i++) {
                    boolean z;
                    for (int i2 = 0; i2 < c.m4628i(); i2++) {
                        if (((ad) this.f4000i.f2378n.f2330a.get(i)).f2304a.equals(c.m4619b(i2).m4659e("uuid"))) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    if (!z) {
                        c.m4612a(this.f4000i.f2378n.m2411a(i).f2305b);
                    }
                }
                b.m4652b(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT).m4657c("zones").m4629j();
                for (int i3 = 0; i3 < c.m4628i(); i3++) {
                    boolean z2 = false;
                    for (String equals : C0745a.f2001l.f2141a.f2124l) {
                        if (equals.equals(c.m4619b(i3).m4659e("uuid"))) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        c.m4619b(i3).m4655b("last_config_ms", new Long(System.currentTimeMillis()).toString());
                        b.m4652b(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT).m4657c("zones").m4612a(c.m4619b(i3));
                    }
                }
                new C0771f("manifest.txt").m2295a(b.toString());
                this.f3993b = true;
                this.f4000i.m2429a();
                if (this.f4000i.f2373i == null || this.f4000i.f2373i.equals(StringUtils.EMPTY)) {
                    this.f4000i.f2373i = "all";
                }
                if (this.f4000i.f2374j == null || this.f4000i.f2374j.equals(StringUtils.EMPTY)) {
                    this.f4000i.f2374j = "all";
                }
                C0745a.m2163h();
                return;
            } else {
                C0777l.f2240b.m2353b((Object) "Invalid manifest.");
                return;
            }
        }
        C0777l.f2241c.m2353b((Object) "Error retrieving ad server response from:");
        C0777l.f2241c.m2353b(download.f3916c);
    }

    boolean m4695a(C1240g c1240g) {
        if (c1240g == null || !c1240g.m4659e(NotificationCompatApi21.CATEGORY_STATUS).equals("success") || !this.f4000i.m2430a(c1240g.m4652b(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT))) {
            return false;
        }
        C0777l.f2239a.m2353b((Object) "Finished parsing manifest");
        C0745a.m2163h();
        if (this.f4000i.f2372h.equalsIgnoreCase("none")) {
            C0745a.m2136a(2);
        } else {
            C0777l.f2241c.m2353b((Object) "Enabling debug logging.");
            C0745a.m2136a(1);
        }
        return true;
    }
}
