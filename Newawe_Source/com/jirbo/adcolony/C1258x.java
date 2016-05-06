package com.jirbo.adcolony;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.jirbo.adcolony.x */
class C1258x extends ae {
    static final int f4042a = 1024;
    String f4043b;
    OutputStream f4044c;
    byte[] f4045d;
    int f4046e;
    int f4047f;
    int f4048g;

    C1258x(String str) {
        this.f4045d = new byte[f4042a];
        this.f4046e = 0;
        this.f4043b = str;
        if (C0745a.f2003n != 0) {
            this.f4048g = 23;
            this.f4047f = this.f4048g;
        }
        try {
            if (!(C0745a.f2001l == null || C0745a.f2001l.f2146f == null)) {
                C0745a.f2001l.f2146f.m2110b();
            }
            this.f4044c = new FileOutputStream(str);
        } catch (IOException e) {
            m4745a(e);
        }
    }

    C1258x(String str, OutputStream outputStream) {
        this.f4045d = new byte[f4042a];
        this.f4046e = 0;
        this.f4043b = str;
        this.f4044c = outputStream;
    }

    void m4744a(char c) {
        this.f4045d[this.f4046e] = (byte) (this.f4047f ^ c);
        this.f4047f += this.f4048g;
        int i = this.f4046e + 1;
        this.f4046e = i;
        if (i == f4042a) {
            m4743a();
        }
    }

    void m4743a() {
        if (this.f4046e > 0 && this.f4044c != null) {
            try {
                this.f4044c.write(this.f4045d, 0, this.f4046e);
                this.f4046e = 0;
                this.f4044c.flush();
            } catch (IOException e) {
                this.f4046e = 0;
                m4745a(e);
            }
        }
    }

    void m4746b() {
        m4743a();
        try {
            if (this.f4044c != null) {
                this.f4044c.close();
                this.f4044c = null;
            }
        } catch (IOException e) {
            this.f4044c = null;
            m4745a(e);
        }
    }

    void m4745a(IOException iOException) {
        C0777l.f2242d.m2349a("Error writing \"").m2349a(this.f4043b).m2353b((Object) "\":");
        C0777l.f2242d.m2353b(iOException.toString());
        m4746b();
    }

    public static void m4742a(String[] strArr) {
        C1258x c1258x = new C1258x("test.txt");
        c1258x.m2216b("A king who was mad at the time");
        c1258x.m2216b("Declared limerick writing a crime");
        c1258x.i += 2;
        c1258x.m2216b("So late in the night");
        c1258x.m2216b("All the poets would write");
        c1258x.i -= 2;
        c1258x.m2216b("Verses without any rhyme or meter");
        c1258x.m2221d();
        c1258x.i += 4;
        c1258x.m2216b("David\nGerrold");
        c1258x.i += 2;
        c1258x.m2213b(4.0d);
        c1258x.i += 2;
        c1258x.m2213b(0.0d);
        c1258x.i += 2;
        c1258x.m2213b(-100023.0d);
        c1258x.i += 2;
        c1258x.m2220c(-6);
        c1258x.i += 2;
        c1258x.m2220c(0);
        c1258x.i += 2;
        c1258x.m2220c(234);
        c1258x.i += 2;
        c1258x.m2220c(Long.MIN_VALUE);
        c1258x.i += 2;
        c1258x.m2217b(true);
        c1258x.i += 2;
        c1258x.m2217b(false);
        c1258x.i += 2;
        c1258x.m4746b();
    }
}
