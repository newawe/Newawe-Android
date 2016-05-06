package com.jirbo.adcolony;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.ClassUtils;

abstract class ae {
    boolean f2085h;
    int f2086i;

    abstract void m2206a(char c);

    ae() {
        this.f2085h = true;
        this.f2086i = 0;
    }

    void m2218c() {
        if (this.f2085h) {
            this.f2085h = false;
            int i = this.f2086i;
            while (true) {
                i--;
                if (i >= 0) {
                    m2206a(' ');
                } else {
                    return;
                }
            }
        }
    }

    void m2212b(char c) {
        if (this.f2085h) {
            m2218c();
        }
        m2206a(c);
        if (c == '\n') {
            this.f2085h = true;
        }
    }

    void m2209a(Object obj) {
        if (this.f2085h) {
            m2218c();
        }
        if (obj == null) {
            m2210a("null");
        } else {
            m2210a(obj.toString());
        }
    }

    void m2210a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            m2212b(str.charAt(i));
        }
    }

    void m2207a(double d) {
        if (this.f2085h) {
            m2218c();
        }
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            m2210a("0.0");
            return;
        }
        if (d < 0.0d) {
            d = -d;
            m2206a('-');
        }
        long pow = (long) Math.pow(10.0d, (double) 4);
        long round = Math.round(((double) pow) * d);
        m2208a(round / pow);
        m2206a((char) ClassUtils.PACKAGE_SEPARATOR_CHAR);
        round %= pow;
        if (round == 0) {
            for (int i = 0; i < 4; i++) {
                m2206a('0');
            }
            return;
        }
        for (long j = round * 10; j < pow; j *= 10) {
            m2206a('0');
        }
        m2208a(round);
    }

    void m2208a(long j) {
        if (this.f2085h) {
            m2218c();
        }
        if (j == 0) {
            m2206a('0');
        } else if (j == (-j)) {
            m2210a("-9223372036854775808");
        } else if (j < 0) {
            m2206a('-');
            m2208a(-j);
        } else {
            m2214b(j);
        }
    }

    void m2214b(long j) {
        if (j != 0) {
            m2214b(j / 10);
            m2206a((char) ((int) (48 + (j % 10))));
        }
    }

    void m2211a(boolean z) {
        if (z) {
            m2210a(SchemaSymbols.ATTVAL_TRUE);
        } else {
            m2210a(SchemaSymbols.ATTVAL_FALSE);
        }
    }

    void m2219c(char c) {
        m2212b(c);
        m2212b('\n');
    }

    void m2215b(Object obj) {
        m2209a(obj);
        m2212b('\n');
    }

    void m2216b(String str) {
        m2210a(str);
        m2212b('\n');
    }

    void m2213b(double d) {
        m2207a(d);
        m2212b('\n');
    }

    void m2220c(long j) {
        m2208a(j);
        m2212b('\n');
    }

    void m2217b(boolean z) {
        m2211a(z);
        m2212b('\n');
    }

    void m2221d() {
        m2212b('\n');
    }

    public static void m2205b(String[] strArr) {
        System.out.println("Test...");
    }
}
