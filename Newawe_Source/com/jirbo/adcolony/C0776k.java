package com.jirbo.adcolony;

import com.jirbo.adcolony.ADCData.C0721i;
import com.jirbo.adcolony.ADCData.C1235b;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1238e;
import com.jirbo.adcolony.ADCData.C1239f;
import com.jirbo.adcolony.ADCData.C1240g;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.HttpStatus;

/* renamed from: com.jirbo.adcolony.k */
class C0776k {
    static C1259y f2238a;

    C0776k() {
    }

    static {
        f2238a = new C1259y();
    }

    static void m2331a(C0771f c0771f, C0721i c0721i) {
        ae a = c0771f.m2294a();
        if (c0721i == null) {
            a.m2216b("null");
        } else {
            c0721i.m2080a(a);
            a.m2221d();
        }
        a.m4746b();
    }

    static void m2330a(C0771f c0771f, C1240g c1240g) {
        ae a = c0771f.m2294a();
        if (c1240g != null) {
            c1240g.m4648a(a);
            a.m2221d();
        } else {
            C0777l.f2240b.m2353b((Object) "Saving empty property table.");
            a.m2216b("{}");
        }
        a.m4746b();
    }

    static void m2329a(C0771f c0771f, C1236c c1236c) {
        ae a = c0771f.m2294a();
        if (c1236c != null) {
            c1236c.m4617a(a);
            a.m2221d();
        } else {
            C0777l.f2240b.m2353b((Object) "Saving empty property list.");
            a.m2216b("[]");
        }
        a.m4746b();
    }

    static C0721i m2326a(C0771f c0771f) {
        C0812s b = c0771f.m2296b();
        if (b == null) {
            return null;
        }
        return C0776k.m2327a(b);
    }

    static C1240g m2333b(C0771f c0771f) {
        C0721i a = C0776k.m2326a(c0771f);
        if (a == null || !a.m2092m()) {
            return null;
        }
        return a.m2093n();
    }

    static C1236c m2336c(C0771f c0771f) {
        C0721i a = C0776k.m2326a(c0771f);
        if (a == null || !a.m2087f()) {
            return null;
        }
        return a.m2089h();
    }

    static C0721i m2328a(String str) {
        if (str == null) {
            return null;
        }
        return C0776k.m2327a(new C0812s(str));
    }

    static C1240g m2334b(String str) {
        C0721i a = C0776k.m2328a(str);
        if (a == null || !a.m2092m()) {
            return null;
        }
        return a.m2093n();
    }

    static C1236c m2337c(String str) {
        C0721i a = C0776k.m2328a(str);
        if (a == null || !a.m2087f()) {
            return null;
        }
        return a.m2089h();
    }

    static C0721i m2327a(C0812s c0812s) {
        try {
            C0776k.m2335b(c0812s);
            char b = c0812s.m2496b();
            if (b == '{') {
                return C0776k.m2338c(c0812s);
            }
            if (b == '[') {
                return C0776k.m2339d(c0812s);
            }
            if (b == '-') {
                return C0776k.m2343h(c0812s);
            }
            if (b >= '0' && b <= '9') {
                return C0776k.m2343h(c0812s);
            }
            String e;
            if (b == '\"' || b == '\'') {
                e = C0776k.m2340e(c0812s);
                if (e.length() == 0) {
                    return new C1239f(StringUtils.EMPTY);
                }
                b = e.charAt(0);
                if (b == 't' && e.equals(SchemaSymbols.ATTVAL_TRUE)) {
                    return ADCData.f1795a;
                }
                if (b == 'f' && e.equals(SchemaSymbols.ATTVAL_FALSE)) {
                    return ADCData.f1796b;
                }
                if (b == 'n' && e.equals("null")) {
                    return ADCData.f1797c;
                }
                return new C1239f(e);
            } else if ((b < 'a' || b > 'z') && ((b < 'A' || b > 'Z') && b != '_' && b != StrSubstitutor.DEFAULT_ESCAPE)) {
                return null;
            } else {
                e = C0776k.m2342g(c0812s);
                if (e.length() == 0) {
                    return new C1239f(StringUtils.EMPTY);
                }
                b = e.charAt(0);
                if (b == 't' && e.equals(SchemaSymbols.ATTVAL_TRUE)) {
                    return ADCData.f1795a;
                }
                if (b == 'f' && e.equals(SchemaSymbols.ATTVAL_FALSE)) {
                    return ADCData.f1796b;
                }
                if (b == 'n' && e.equals("null")) {
                    return ADCData.f1797c;
                }
                return new C1239f(e);
            }
        } catch (RuntimeException e2) {
            return null;
        }
    }

    static void m2335b(C0812s c0812s) {
        char b = c0812s.m2496b();
        while (c0812s.m2493a()) {
            if (b <= ' ' || b > '~') {
                c0812s.m2499c();
                b = c0812s.m2496b();
            } else {
                return;
            }
        }
    }

    static C1240g m2338c(C0812s c0812s) {
        C0776k.m2335b(c0812s);
        if (!c0812s.m2494a('{')) {
            return null;
        }
        C0776k.m2335b(c0812s);
        C1240g c1240g = new C1240g();
        if (c0812s.m2494a('}')) {
            return c1240g;
        }
        boolean z = true;
        while (true) {
            if (!z && !c0812s.m2494a(',')) {
                break;
            }
            z = false;
            String g = C0776k.m2342g(c0812s);
            C0776k.m2335b(c0812s);
            if (c0812s.m2494a(':')) {
                C0776k.m2335b(c0812s);
                c1240g.m4649a(g, C0776k.m2327a(c0812s));
            } else {
                c1240g.m4656b(g, true);
            }
            C0776k.m2335b(c0812s);
        }
        if (c0812s.m2494a('}')) {
            return c1240g;
        }
        return null;
    }

    static C1236c m2339d(C0812s c0812s) {
        C0776k.m2335b(c0812s);
        if (!c0812s.m2494a('[')) {
            return null;
        }
        C0776k.m2335b(c0812s);
        C1236c c1236c = new C1236c();
        if (c0812s.m2494a(']')) {
            return c1236c;
        }
        Object obj = 1;
        while (true) {
            if (obj == null && !c0812s.m2494a(',')) {
                break;
            }
            obj = null;
            c1236c.m4612a(C0776k.m2327a(c0812s));
            C0776k.m2335b(c0812s);
        }
        if (c0812s.m2494a(']')) {
            return c1236c;
        }
        return null;
    }

    static String m2340e(C0812s c0812s) {
        char c = '\"';
        C0776k.m2335b(c0812s);
        if (!c0812s.m2494a('\"') && c0812s.m2494a('\'')) {
            c = '\'';
        }
        if (!c0812s.m2493a()) {
            return StringUtils.EMPTY;
        }
        f2238a.m4748a();
        char c2 = c0812s.m2499c();
        while (c0812s.m2493a() && c2 != r0) {
            if (c2 == '\\') {
                c2 = c0812s.m2499c();
                if (c2 == 'b') {
                    f2238a.m2212b('\b');
                } else if (c2 == 'f') {
                    f2238a.m2212b('\f');
                } else if (c2 == 'n') {
                    f2238a.m2212b('\n');
                } else if (c2 == 'r') {
                    f2238a.m2212b((char) CharUtils.CR);
                } else if (c2 == 't') {
                    f2238a.m2212b('\t');
                } else if (c2 == 'u') {
                    f2238a.m2212b(C0776k.m2341f(c0812s));
                } else {
                    f2238a.m2212b(c2);
                }
            } else {
                f2238a.m2212b(c2);
            }
            c2 = c0812s.m2499c();
        }
        return f2238a.toString();
    }

    static int m2325a(int i) {
        if (i >= 48 && i <= 57) {
            return i - 48;
        }
        if (i >= 97 && i <= HttpStatus.SC_PROCESSING) {
            return (i - 97) + 10;
        }
        if (i < 65 || i > 70) {
            return 0;
        }
        return (i - 65) + 10;
    }

    static char m2341f(C0812s c0812s) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            if (c0812s.m2493a()) {
                i = (i << 4) | C0776k.m2325a(c0812s.m2499c());
            }
        }
        return (char) i;
    }

    static String m2342g(C0812s c0812s) {
        C0776k.m2335b(c0812s);
        int b = c0812s.m2496b();
        if (b == 34 || b == 39) {
            return C0776k.m2340e(c0812s);
        }
        f2238a.m4748a();
        Object obj = null;
        while (obj == null && c0812s.m2493a()) {
            if ((b < 97 || b > 122) && !((b >= 65 && b <= 90) || b == 95 || b == 36)) {
                obj = 1;
            } else {
                c0812s.m2499c();
                f2238a.m2212b((char) b);
                b = c0812s.m2496b();
            }
        }
        return f2238a.toString();
    }

    static C0721i m2343h(C0812s c0812s) {
        double d;
        int b;
        C0776k.m2335b(c0812s);
        double d2 = 1.0d;
        if (c0812s.m2494a('-')) {
            d2 = -1.0d;
            C0776k.m2335b(c0812s);
        }
        double d3 = 0.0d;
        int b2 = c0812s.m2496b();
        while (c0812s.m2493a() && b2 >= 48 && b2 <= 57) {
            c0812s.m2499c();
            d3 = (d3 * 10.0d) + ((double) (b2 - 48));
            b2 = c0812s.m2496b();
        }
        Object obj = null;
        if (c0812s.m2494a((char) ClassUtils.PACKAGE_SEPARATOR_CHAR)) {
            d = 0.0d;
            double d4 = 0.0d;
            b = c0812s.m2496b();
            while (c0812s.m2493a() && b >= 48 && b <= 57) {
                c0812s.m2499c();
                d = (d * 10.0d) + ((double) (b - 48));
                d4 += 1.0d;
                b = c0812s.m2496b();
            }
            d3 += d / Math.pow(10.0d, d4);
            obj = 1;
        }
        if (c0812s.m2494a('e') || c0812s.m2494a('E')) {
            Object obj2 = null;
            if (!c0812s.m2494a('+') && c0812s.m2494a('-')) {
                obj2 = 1;
            }
            d = 0.0d;
            b = c0812s.m2496b();
            while (c0812s.m2493a() && b >= 48 && b <= 57) {
                c0812s.m2499c();
                d = (d * 10.0d) + ((double) (b - 48));
                b = c0812s.m2496b();
            }
            if (obj2 != null) {
                d3 /= Math.pow(10.0d, d);
            } else {
                d3 *= Math.pow(10.0d, d);
            }
        }
        d3 *= d2;
        if (obj == null && d3 == ((double) ((int) d3))) {
            return new C1235b((int) d3);
        }
        return new C1238e(d3);
    }

    public static void m2332a(String[] strArr) {
        System.out.println("==== ADCJSON Test ====");
        C0776k.m2333b(new C0771f("test.txt"));
        C0776k.m2331a(new C0771f("test2.txt"), C0776k.m2326a(new C0771f("test.txt")));
        C0776k.m2331a(new C0771f("test3.txt"), C0776k.m2326a(new C0771f("test2.txt")));
    }
}
