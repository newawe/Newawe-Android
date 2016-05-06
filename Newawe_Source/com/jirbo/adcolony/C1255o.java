package com.jirbo.adcolony;

import com.Newawe.storage.DatabaseOpenHelper;
import com.jirbo.adcolony.ADCData.C0721i;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.ADCDownload.Listener;
import com.jirbo.adcolony.aa.C0747b;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.jirbo.adcolony.o */
class C1255o implements Listener {
    C0762d f4020a;
    ArrayList<C0808a> f4021b;
    HashMap<String, C0808a> f4022c;
    int f4023d;
    C0747b f4024e;
    int f4025f;
    ArrayList<String> f4026g;
    boolean f4027h;
    boolean f4028i;
    double f4029j;

    /* renamed from: com.jirbo.adcolony.o.a */
    static class C0808a {
        String f2557a;
        String f2558b;
        String f2559c;
        boolean f2560d;
        boolean f2561e;
        int f2562f;
        int f2563g;
        double f2564h;

        C0808a() {
        }
    }

    C1255o(C0762d c0762d) {
        this.f4021b = new ArrayList();
        this.f4022c = new HashMap();
        this.f4023d = 1;
        this.f4024e = new C0747b(2.0d);
        this.f4026g = new ArrayList();
        this.f4020a = c0762d;
    }

    void m4717a() {
        m4721b();
        this.f4027h = true;
    }

    void m4721b() {
        C0777l.f2239a.m2353b((Object) "Loading media info");
        C1240g b = C0776k.m2333b(new C0771f("media_info.txt"));
        if (b == null) {
            b = new C1240g();
            C0777l.f2239a.m2353b((Object) "No saved media info exists.");
        } else {
            C0777l.f2239a.m2353b((Object) "Loaded media info");
        }
        this.f4023d = b.m4661g("next_file_number");
        if (this.f4023d <= 0) {
            this.f4023d = 1;
        }
        C1236c c = b.m4657c("assets");
        if (c != null) {
            this.f4021b.clear();
            for (int i = 0; i < c.m4628i(); i++) {
                C1240g b2 = c.m4619b(i);
                C0808a c0808a = new C0808a();
                c0808a.f2557a = b2.m4659e(DatabaseOpenHelper.HISTORY_ROW_URL);
                c0808a.f2558b = b2.m4659e("filepath");
                c0808a.f2559c = b2.m4659e("last_modified");
                c0808a.f2562f = b2.m4661g("file_number");
                c0808a.f2563g = b2.m4661g("size");
                c0808a.f2561e = b2.m4663h("ready");
                c0808a.f2564h = b2.m4660f("last_accessed");
                if (c0808a.f2562f > this.f4023d) {
                    this.f4023d = c0808a.f2562f + 1;
                }
                this.f4021b.add(c0808a);
            }
        }
        m4722c();
    }

    void m4722c() {
        int i;
        HashMap hashMap = new HashMap();
        String str = this.f4020a.f2146f.f1814c;
        String[] list = new File(str).list();
        String[] strArr;
        if (list == null) {
            strArr = new String[0];
        } else {
            strArr = list;
        }
        for (String str2 : r1) {
            String str22 = str + str22;
            hashMap.put(str22, str22);
        }
        HashMap hashMap2 = new HashMap();
        this.f4029j = 0.0d;
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < this.f4021b.size(); i++) {
            C0808a c0808a = (C0808a) this.f4021b.get(i);
            if (!c0808a.f2560d && c0808a.f2561e) {
                String str3 = c0808a.f2558b;
                if (hashMap.containsKey(str3) && new File(str3).length() == ((long) c0808a.f2563g)) {
                    this.f4029j += (double) c0808a.f2563g;
                    arrayList.add(c0808a);
                    hashMap2.put(str3, str3);
                }
            }
        }
        this.f4021b = arrayList;
        for (String str4 : r1) {
            Object obj = str + str4;
            if (!hashMap2.containsKey(obj)) {
                C0777l.f2240b.m2349a("Deleting unused media ").m2353b(obj);
                new File(obj).delete();
            }
        }
        this.f4022c.clear();
        for (int i2 = 0; i2 < this.f4021b.size(); i2++) {
            c0808a = (C0808a) this.f4021b.get(i2);
            this.f4022c.put(c0808a.f2557a, c0808a);
        }
        double d = this.f4020a.f2142b.f4000i.f2371g;
        if (d > 0.0d) {
            C0777l.f2240b.m2349a("Media pool at ").m2346a(this.f4029j / 1048576.0d).m2349a("/").m2346a(d / 1048576.0d).m2353b((Object) " MB");
        }
    }

    void m4723d() {
        C0777l.f2239a.m2353b((Object) "Saving media info");
        C0721i c1236c = new C1236c();
        for (int i = 0; i < this.f4021b.size(); i++) {
            C0808a c0808a = (C0808a) this.f4021b.get(i);
            if (c0808a.f2561e && !c0808a.f2560d) {
                C0721i c1240g = new C1240g();
                c1240g.m4655b(DatabaseOpenHelper.HISTORY_ROW_URL, c0808a.f2557a);
                c1240g.m4655b("filepath", c0808a.f2558b);
                c1240g.m4655b("last_modified", c0808a.f2559c);
                c1240g.m4654b("file_number", c0808a.f2562f);
                c1240g.m4654b("size", c0808a.f2563g);
                c1240g.m4656b("ready", c0808a.f2561e);
                c1240g.m4653b("last_accessed", c0808a.f2564h);
                c1236c.m4612a(c1240g);
            }
        }
        C1240g c1240g2 = new C1240g();
        c1240g2.m4654b("next_file_number", this.f4023d);
        c1240g2.m4649a("assets", c1236c);
        C0776k.m2330a(new C0771f("media_info.txt"), c1240g2);
        this.f4028i = false;
    }

    boolean m4719a(String str) {
        if (str == null || str.equals(StringUtils.EMPTY)) {
            return false;
        }
        C0808a c0808a = (C0808a) this.f4022c.get(str);
        if (c0808a == null) {
            this.f4020a.f2142b.f4000i.m2429a();
            return false;
        } else if (!c0808a.f2561e) {
            if (!c0808a.f2560d) {
                this.f4020a.f2142b.f4000i.m2429a();
            }
            return false;
        } else if (c0808a.f2560d) {
            return false;
        } else {
            c0808a.f2564h = aa.m2179c();
            return true;
        }
    }

    String m4720b(String str) {
        C0808a c0808a = (C0808a) this.f4022c.get(str);
        if (c0808a == null || c0808a.f2558b == null) {
            return "(file not found)";
        }
        c0808a.f2564h = aa.m2179c();
        this.f4028i = true;
        this.f4024e.m2169a(2.0d);
        return c0808a.f2558b;
    }

    void m4718a(String str, String str2) {
        if (str != null && !str.equals(StringUtils.EMPTY)) {
            if (str2 == null) {
                str2 = StringUtils.EMPTY;
            }
            C0808a c0808a = (C0808a) this.f4022c.get(str);
            if (c0808a != null) {
                c0808a.f2564h = aa.m2179c();
                if (c0808a.f2559c.equals(str2) && (c0808a.f2561e || c0808a.f2560d)) {
                    return;
                }
            }
            c0808a = new C0808a();
            c0808a.f2557a = str;
            this.f4021b.add(c0808a);
            c0808a.f2564h = aa.m2179c();
            this.f4022c.put(str, c0808a);
            if (c0808a.f2562f == 0) {
                int h = m4727h();
                String str3 = this.f4020a.f2146f.f1814c + m4716a(str, h);
                c0808a.f2562f = h;
                c0808a.f2558b = str3;
            }
            c0808a.f2559c = str2;
            c0808a.f2560d = true;
            c0808a.f2561e = false;
            C0777l.f2239a.m2349a("Adding ").m2349a(str).m2353b((Object) " to pending downloads.");
            this.f4026g.add(str);
            this.f4028i = true;
            this.f4024e.m2169a(2.0d);
            C0745a.f2015z = true;
        }
    }

    void m4724e() {
        m4725f();
        if (this.f4028i && this.f4024e.m2170a()) {
            m4726g();
            m4723d();
        }
    }

    void m4725f() {
        if (this.f4020a.f2142b.f4000i.f2374j.equals("wifi") && !C0811q.m2484a()) {
            C0777l.f2239a.m2353b((Object) "Skipping asset download due to CACHE_FILTER_WIFI");
        } else if (!this.f4020a.f2142b.f4000i.f2374j.equals("cell") || C0811q.m2487b()) {
            while (this.f4025f < 3 && this.f4026g.size() > 0) {
                String str = (String) this.f4026g.remove(0);
                C0808a c0808a = (C0808a) this.f4022c.get(str);
                if (c0808a != null && (str == null || str.equals(StringUtils.EMPTY))) {
                    C0777l.f2242d.m2353b((Object) "[ADC ERROR] - NULL URL");
                    new RuntimeException().printStackTrace();
                }
                if (!(c0808a == null || str == null || str.equals(StringUtils.EMPTY))) {
                    C0745a.f2015z = true;
                    this.f4025f++;
                    new ADCDownload(this.f4020a, str, this, c0808a.f2558b).m4673a(c0808a).m4676b();
                }
            }
        } else {
            C0777l.f2239a.m2353b((Object) "Skipping asset download due to CACHE_FILTER_CELL.");
        }
    }

    public void on_download_finished(ADCDownload download) {
        C0808a c0808a = (C0808a) download.f3918e;
        this.f4025f--;
        this.f4028i = true;
        this.f4024e.m2169a(2.0d);
        c0808a.f2561e = download.f3922i;
        c0808a.f2560d = false;
        if (download.f3922i) {
            c0808a.f2563g = download.f3926m;
            this.f4029j += (double) c0808a.f2563g;
            C0777l.f2239a.m2349a("Downloaded ").m2353b(c0808a.f2557a);
        }
        C0745a.m2163h();
        m4725f();
    }

    void m4726g() {
        double d = this.f4020a.f2142b.f4000i.f2371g;
        if (d != 0.0d) {
            while (this.f4029j > this.f4020a.f2142b.f4000i.f2371g) {
                int i = 0;
                C0808a c0808a = null;
                while (i < this.f4021b.size()) {
                    C0808a c0808a2 = (C0808a) this.f4021b.get(i);
                    if (!c0808a2.f2561e || (c0808a != null && c0808a2.f2564h >= c0808a.f2564h)) {
                        c0808a2 = c0808a;
                    }
                    i++;
                    c0808a = c0808a2;
                }
                if (c0808a == null) {
                    return;
                }
                if (c0808a.f2558b != null) {
                    C0777l.f2240b.m2349a("Deleting ").m2353b(c0808a.f2558b);
                    c0808a.f2561e = false;
                    new File(c0808a.f2558b).delete();
                    c0808a.f2558b = null;
                    this.f4029j -= (double) c0808a.f2563g;
                    C0777l.f2240b.m2349a("Media pool now at ").m2346a(this.f4029j / 1048576.0d).m2349a("/").m2346a(d / 1048576.0d).m2353b((Object) " MB");
                    this.f4028i = true;
                    this.f4024e.m2169a(2.0d);
                } else {
                    return;
                }
            }
        }
    }

    int m4727h() {
        this.f4028i = true;
        this.f4024e.m2169a(2.0d);
        int i = this.f4023d;
        this.f4023d = i + 1;
        return i;
    }

    String m4716a(String str, int i) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return i + StringUtils.EMPTY;
        }
        String substring = str.substring(lastIndexOf);
        if (substring.contains("/")) {
            substring = ".0";
        }
        return i + substring;
    }
}
