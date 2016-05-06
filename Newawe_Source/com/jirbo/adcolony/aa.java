package com.jirbo.adcolony;

import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

class aa {
    static byte[] f2018a;
    static StringBuilder f2019b;

    /* renamed from: com.jirbo.adcolony.aa.a */
    static class C0746a {
        long f2016a;

        C0746a() {
            this.f2016a = System.currentTimeMillis();
        }

        void m2167a() {
            this.f2016a = System.currentTimeMillis();
        }

        double m2168b() {
            return ((double) (System.currentTimeMillis() - this.f2016a)) / 1000.0d;
        }

        public String toString() {
            return aa.m2173a(m2168b(), 2);
        }
    }

    /* renamed from: com.jirbo.adcolony.aa.b */
    static class C0747b {
        double f2017a;

        C0747b(double d) {
            this.f2017a = (double) System.currentTimeMillis();
            m2169a(d);
        }

        void m2169a(double d) {
            this.f2017a = (((double) System.currentTimeMillis()) / 1000.0d) + d;
        }

        boolean m2170a() {
            return m2171b() == 0.0d;
        }

        double m2171b() {
            double currentTimeMillis = this.f2017a - (((double) System.currentTimeMillis()) / 1000.0d);
            if (currentTimeMillis <= 0.0d) {
                return 0.0d;
            }
            return currentTimeMillis;
        }

        public String toString() {
            return aa.m2173a(m2171b(), 2);
        }
    }

    aa() {
    }

    static {
        f2018a = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
        f2019b = new StringBuilder();
    }

    static boolean m2176a(String str) {
        if (C0745a.f1979P == null) {
            return false;
        }
        try {
            AdColony.activity().getApplication().getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String m2172a() {
        if (C0745a.f1979P == null) {
            return "1.0";
        }
        try {
            return AdColony.activity().getPackageManager().getPackageInfo(AdColony.activity().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            C0745a.m2157e("Failed to retrieve package info.");
            return "1.0";
        }
    }

    static String m2178b(String str) {
        try {
            return ah.m2230a(str);
        } catch (Exception e) {
            return null;
        }
    }

    static String m2177b() {
        return UUID.randomUUID().toString();
    }

    static double m2179c() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    static String m2173a(double d, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        m2175a(d, i, stringBuilder);
        return stringBuilder.toString();
    }

    static void m2175a(double d, int i, StringBuilder stringBuilder) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            stringBuilder.append(d);
            return;
        }
        if (d < 0.0d) {
            d = -d;
            stringBuilder.append('-');
        }
        if (i == 0) {
            stringBuilder.append(Math.round(d));
            return;
        }
        long pow = (long) Math.pow(10.0d, (double) i);
        long round = Math.round(((double) pow) * d);
        stringBuilder.append(round / pow);
        stringBuilder.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        long j = round % pow;
        if (j == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                stringBuilder.append('0');
            }
            return;
        }
        for (round = j * 10; round < pow; round *= 10) {
            stringBuilder.append('0');
        }
        stringBuilder.append(j);
    }

    static boolean m2181d() {
        if (new File(C0745a.f2001l.f2146f.m2111c() + "/../lib/libImmEndpointWarpJ.so").exists()) {
            return true;
        }
        return false;
    }

    static String m2180c(String str) {
        return m2174a(str, StringUtils.EMPTY);
    }

    static String m2174a(String str, String str2) {
        if (str == null) {
            return StringUtils.EMPTY;
        }
        try {
            String stringBuilder;
            C0777l.f2239a.m2349a("Loading ").m2353b((Object) str);
            FileInputStream fileInputStream = new FileInputStream(str);
            synchronized (f2018a) {
                f2019b.setLength(0);
                f2019b.append(str2);
                for (int read = fileInputStream.read(f2018a, 0, f2018a.length); read != -1; read = fileInputStream.read(f2018a, 0, f2018a.length)) {
                    for (int i = 0; i < read; i++) {
                        f2019b.append((char) f2018a[i]);
                    }
                }
                fileInputStream.close();
                stringBuilder = f2019b.toString();
            }
            return stringBuilder;
        } catch (IOException e) {
            C0777l.f2242d.m2349a("Unable to load ").m2353b((Object) str);
            return StringUtils.EMPTY;
        }
    }

    static boolean m2182e() {
        if (C0745a.f1979P != null && C0745a.m2148b().checkCallingOrSelfPermission("android.permission.VIBRATE") == 0) {
            return true;
        }
        return false;
    }

    static String m2183f() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        return C0745a.m2148b().getPackageName();
    }
}
