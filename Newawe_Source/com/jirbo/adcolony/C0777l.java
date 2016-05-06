package com.jirbo.adcolony;

/* renamed from: com.jirbo.adcolony.l */
class C0777l {
    static C0777l f2239a;
    static C0777l f2240b;
    static C0777l f2241c;
    static C0777l f2242d;
    int f2243e;
    boolean f2244f;
    StringBuilder f2245g;

    static {
        f2239a = new C0777l(0, false);
        f2240b = new C0777l(1, false);
        f2241c = new C0777l(2, true);
        f2242d = new C0777l(3, true);
    }

    C0777l(int i, boolean z) {
        this.f2245g = new StringBuilder();
        this.f2243e = i;
        this.f2244f = z;
    }

    synchronized C0777l m2345a(char c) {
        C0777l c0777l;
        if (this.f2244f) {
            this.f2245g.append(c);
            if (c == '\n') {
                C0745a.m2137a(this.f2243e, this.f2245g.toString());
                this.f2245g.setLength(0);
            }
            c0777l = this;
        } else {
            c0777l = this;
        }
        return c0777l;
    }

    synchronized C0777l m2349a(String str) {
        C0777l c0777l;
        if (this.f2244f) {
            if (str == null) {
                this.f2245g.append("null");
            } else {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    m2345a(str.charAt(i));
                }
            }
            c0777l = this;
        } else {
            c0777l = this;
        }
        return c0777l;
    }

    synchronized C0777l m2348a(Object obj) {
        if (this.f2244f) {
            if (obj == null) {
                m2349a("null");
            } else {
                m2349a(obj.toString());
            }
        }
        return this;
    }

    synchronized C0777l m2346a(double d) {
        C0777l c0777l;
        if (this.f2244f) {
            aa.m2175a(d, 2, this.f2245g);
            c0777l = this;
        } else {
            c0777l = this;
        }
        return c0777l;
    }

    synchronized C0777l m2347a(int i) {
        C0777l c0777l;
        if (this.f2244f) {
            this.f2245g.append(i);
            c0777l = this;
        } else {
            c0777l = this;
        }
        return c0777l;
    }

    synchronized C0777l m2350a(boolean z) {
        C0777l c0777l;
        if (this.f2244f) {
            this.f2245g.append(z);
            c0777l = this;
        } else {
            c0777l = this;
        }
        return c0777l;
    }

    synchronized C0777l m2353b(Object obj) {
        m2348a(obj);
        return m2345a('\n');
    }

    synchronized C0777l m2351b(double d) {
        m2346a(d);
        return m2345a('\n');
    }

    synchronized C0777l m2352b(int i) {
        m2347a(i);
        return m2345a('\n');
    }

    synchronized C0777l m2354b(boolean z) {
        m2350a(z);
        return m2345a('\n');
    }

    synchronized C0777l m2344a() {
        return m2345a('\n');
    }

    boolean m2355b(String str) {
        m2349a(str + '\n');
        return false;
    }

    int m2356c(String str) {
        m2349a(str + '\n');
        return 0;
    }
}
