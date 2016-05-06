package com.jirbo.adcolony;

/* renamed from: com.jirbo.adcolony.y */
class C1259y extends ae {
    StringBuilder f4049a;

    C1259y() {
        this.f4049a = new StringBuilder();
    }

    void m4748a() {
        this.f4049a.setLength(0);
        this.i = 0;
    }

    void m4749a(char c) {
        this.f4049a.append(c);
    }

    public String toString() {
        return this.f4049a.toString();
    }

    public static void m4747a(String[] strArr) {
        C1259y c1259y = new C1259y();
        c1259y.m2216b("A king who was mad at the time");
        c1259y.m2216b("Declared limerick writing a crime");
        c1259y.i += 2;
        c1259y.m2216b("So late in the night");
        c1259y.m2216b("All the poets would write");
        c1259y.i -= 2;
        c1259y.m2216b("Verses without any rhyme or meter");
        c1259y.m2221d();
        c1259y.i += 4;
        c1259y.m2216b("David\nGerrold");
        c1259y.i += 2;
        c1259y.m2213b(4.0d);
        c1259y.i += 2;
        c1259y.m2213b(0.0d);
        c1259y.i += 2;
        c1259y.m2213b(-100023.0d);
        c1259y.i += 2;
        c1259y.m2220c(-6);
        c1259y.i += 2;
        c1259y.m2220c(0);
        c1259y.i += 2;
        c1259y.m2220c(234);
        c1259y.i += 2;
        c1259y.m2220c(Long.MIN_VALUE);
        c1259y.i += 2;
        c1259y.m2217b(true);
        c1259y.i += 2;
        c1259y.m2217b(false);
        c1259y.i += 2;
        System.out.println(c1259y);
    }
}
