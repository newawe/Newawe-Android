package com.jirbo.adcolony;

import java.io.File;
import java.io.IOException;
import mf.org.w3c.dom.traversal.NodeFilter;

/* renamed from: com.jirbo.adcolony.f */
class C0771f {
    static byte[] f2200a;
    String f2201b;

    static {
        f2200a = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
    }

    C0771f(String str) {
        this.f2201b = C0745a.f2001l.f2146f.f1815d + str;
    }

    C1258x m2294a() {
        return new C1258x(this.f2201b);
    }

    C0812s m2296b() {
        try {
            return new C0812s(new C0815w(this.f2201b));
        } catch (IOException e) {
            return null;
        }
    }

    void m2295a(String str) {
        C1258x a = m2294a();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            a.m4744a(str.charAt(i));
        }
        a.m4746b();
    }

    void m2297c() {
        new File(this.f2201b).delete();
    }
}
