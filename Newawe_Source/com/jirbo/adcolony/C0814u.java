package com.jirbo.adcolony;

import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;

/* renamed from: com.jirbo.adcolony.u */
class C0814u {
    C0762d f2590a;
    boolean f2591b;
    boolean f2592c;
    boolean f2593d;
    boolean f2594e;
    boolean f2595f;
    double f2596g;
    double f2597h;
    double f2598i;
    int f2599j;
    String f2600k;

    C0814u(C0762d c0762d) {
        this.f2593d = false;
        this.f2594e = false;
        this.f2595f = true;
        this.f2600k = "uuid";
        this.f2590a = c0762d;
        this.f2600k = aa.m2177b();
    }

    void m2501a() {
    }

    void m2503b() {
        if (this.f2590a.f2142b.f3993b) {
            if (this.f2593d) {
                this.f2593d = false;
                this.f2590a.f2144d.m4730a("install", null);
            }
            if (this.f2594e) {
                this.f2594e = false;
                this.f2590a.f2144d.m4730a("session_start", null);
            }
        }
    }

    void m2504c() {
        C0777l.f2240b.m2353b((Object) "AdColony resuming");
        C0745a.f2015z = true;
        if (this.f2591b) {
            C0777l.f2242d.m2353b((Object) "AdColony.onResume() called multiple times in succession.");
        }
        this.f2591b = true;
        m2508g();
        double c = aa.m2179c();
        if (this.f2592c) {
            if (c - this.f2597h > ((double) this.f2590a.f2141a.f2116d)) {
                m2502a(this.f2598i);
                this.f2596g = c;
                m2509h();
            }
            this.f2592c = false;
            m2507f();
        } else {
            this.f2596g = c;
            m2509h();
        }
        C0745a.m2163h();
    }

    void m2505d() {
        C0777l.f2240b.m2353b((Object) "AdColony suspending");
        C0745a.f2015z = true;
        if (!this.f2591b) {
            C0777l.f2242d.m2353b((Object) "AdColony.onPause() called without initial call to onResume().");
        }
        this.f2591b = false;
        this.f2592c = true;
        this.f2597h = aa.m2179c();
        m2507f();
    }

    void m2506e() {
        C0777l.f2240b.m2353b((Object) "AdColony terminating");
        C0745a.f2015z = true;
        m2502a(this.f2598i);
        this.f2592c = false;
        m2507f();
    }

    void m2507f() {
        C1240g c1240g = new C1240g();
        c1240g.m4656b("allow_resume", this.f2592c);
        c1240g.m4653b("start_time", this.f2596g);
        c1240g.m4653b("finish_time", this.f2597h);
        c1240g.m4653b("session_time", this.f2598i);
        C0776k.m2330a(new C0771f("session_info.txt"), c1240g);
    }

    void m2508g() {
        C1240g b = C0776k.m2333b(new C0771f("session_info.txt"));
        if (b != null) {
            this.f2592c = b.m4663h("allow_resume");
            this.f2596g = b.m4660f("start_time");
            this.f2597h = b.m4660f("finish_time");
            this.f2598i = b.m4660f("session_time");
            return;
        }
        this.f2593d = true;
    }

    void m2509h() {
        this.f2594e = true;
        if (!this.f2595f) {
            this.f2600k = aa.m2177b();
        }
        this.f2595f = false;
        this.f2598i = 0.0d;
        this.f2599j = 0;
        if (C0745a.f2001l != null && C0745a.f2001l.f2142b != null && C0745a.f2001l.f2142b.f4000i != null && C0745a.f2001l.f2142b.f4000i.f2378n != null) {
            for (int i = 0; i < C0745a.f2001l.f2142b.f4000i.f2378n.m2415b(); i++) {
                if (C0745a.f2001l.f2142b.f4000i.f2378n.m2411a(i).f2321r != null) {
                    C0745a.f2001l.f2142b.f4000i.f2378n.m2411a(i).f2321r.f2090d = 0;
                }
                if (C0745a.f2001l.f2142b.f4000i.f2378n.m2411a(i) != null) {
                    C0745a.f2001l.f2142b.f4000i.f2378n.m2411a(i).f2313j = new C1236c();
                }
            }
        }
    }

    void m2502a(double d) {
        C0777l.f2239a.m2349a("Submitting session duration ").m2351b(d);
        C1240g c1240g = new C1240g();
        c1240g.m4654b("session_length", (int) d);
        this.f2590a.f2144d.m4730a("session_end", c1240g);
    }
}
