package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.C0810p.C0809a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

/* renamed from: com.jirbo.adcolony.a */
class C0745a {
    static boolean f1964A = false;
    static boolean f1965B = false;
    static boolean f1966C = false;
    static boolean f1967D = false;
    static boolean f1968E = false;
    static int f1969F = 0;
    static double f1970G = 0.0d;
    static boolean f1971H = false;
    static boolean f1972I = false;
    static boolean f1973J = false;
    static boolean f1974K = false;
    static boolean f1975L = false;
    static boolean f1976M = false;
    static boolean f1977N = false;
    static boolean f1978O = false;
    static Activity f1979P = null;
    static boolean f1980Q = false;
    static boolean f1981R = false;
    static C0773h f1982S = null;
    static AdColonyAd f1983T = null;
    static ADCVideo f1984U = null;
    static ADCVideo f1985V = null;
    static C0743a f1986W = null;
    static C0744b f1987X = null;
    static boolean f1988Y = false;
    static boolean f1989Z = false;
    public static final boolean f1990a = false;
    static boolean aa = false;
    static boolean ab = false;
    static int ac = 0;
    static String ad = null;
    static String ae = null;
    static String af = null;
    static String ag = null;
    static String ah = null;
    static ArrayList<String> ai = null;
    static C1236c aj = null;
    static boolean ak = false;
    static long al = 0;
    static int am = 0;
    static ArrayList<Bitmap> an = null;
    static ArrayList<AdColonyV4VCListener> ao = null;
    static ArrayList<AdColonyAdAvailabilityListener> ap = null;
    static ArrayList<AdColonyNativeAdView> aq = null;
    static HashMap ar = null;
    public static final boolean f1991b = false;
    public static final boolean f1992c = false;
    public static final boolean f1993d = false;
    public static String f1994e = null;
    public static final String f1995f;
    public static final int f1996g = 0;
    public static final int f1997h = 1;
    public static final int f1998i = 2;
    public static final int f1999j = 3;
    static final String f2000k = "AdColony";
    static C0762d f2001l;
    static boolean f2002m;
    static int f2003n;
    static boolean f2004o;
    static boolean f2005p;
    static double f2006q;
    static boolean f2007r;
    static boolean f2008s;
    static long f2009t;
    static long f2010u;
    static AdColonyAd f2011v;
    static boolean f2012w;
    static boolean f2013x;
    static boolean f2014y;
    static boolean f2015z;

    /* renamed from: com.jirbo.adcolony.a.a */
    static class C0743a extends Handler {
        AdColonyAd f1963a;

        C0743a() {
        }

        public void m2133a(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.f1963a = C0745a.f1983T;
            } else {
                this.f1963a = adColonyAd;
            }
            sendMessage(obtainMessage(C0745a.f1997h));
        }

        public void m2134b(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.f1963a = C0745a.f1983T;
            } else {
                this.f1963a = adColonyAd;
            }
            sendMessage(obtainMessage(C0745a.f1996g));
        }

        public void handleMessage(Message m) {
            switch (m.what) {
                case C0745a.f1996g /*0*/:
                    C0745a.m2144a(SchemaSymbols.ATTVAL_SKIP, this.f1963a);
                    if (C0745a.f1983T != null) {
                        C0745a.f1983T.f1839f = C0745a.f1997h;
                        C0745a.f1983T.m2115a();
                    }
                case C0745a.f1997h /*1*/:
                    C1240g c1240g = new C1240g();
                    if (C0745a.f1985V.f3944H.f2052Q) {
                        c1240g.m4656b("html5_endcard_loading_started", C0745a.f1985V.f3963k);
                    }
                    if (C0745a.f1985V.f3944H.f2052Q) {
                        c1240g.m4656b("html5_endcard_loading_finished", C0745a.f1985V.f3964l);
                    }
                    if (C0745a.f1985V.f3944H.f2052Q) {
                        c1240g.m4653b("html5_endcard_loading_time", C0745a.f1985V.f3968p);
                    }
                    if (C0745a.f1985V.f3944H.f2052Q) {
                        c1240g.m4656b("html5_endcard_loading_timeout", C0745a.f1985V.f3965m);
                    }
                    if (C0745a.f1985V.f3969q < 60000.0d) {
                        c1240g.m4653b("endcard_time_spent", C0745a.f1985V.f3969q);
                    }
                    c1240g.m4656b("endcard_dissolved", C0745a.f1985V.f3966n);
                    ADCVideo aDCVideo = C0745a.f1985V;
                    c1240g.m4656b("replay", ADCVideo.f3932e);
                    c1240g.m4656b("reward", C0745a.f1985V.f3967o);
                    C0745a.f2001l.f2144d.m4731a("continue", c1240g, this.f1963a);
                    if (C0745a.f1983T != null) {
                        C0745a.f1983T.f1839f = 4;
                        C0745a.f1983T.m2115a();
                    }
                default:
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.a.b */
    static class C0744b extends Handler {
        C0744b() {
        }

        public void handleMessage(Message m) {
            int i = C0745a.f1996g;
            String str = (String) m.obj;
            int i2 = m.what;
            boolean z = str != null ? true : C0745a.f1993d;
            if (!z) {
                str = StringUtils.EMPTY;
            }
            AdColonyV4VCReward adColonyV4VCReward = new AdColonyV4VCReward(z, str, i2);
            while (i < C0745a.ao.size()) {
                ((AdColonyV4VCListener) C0745a.ao.get(i)).onAdColonyV4VCReward(adColonyV4VCReward);
                i += C0745a.f1997h;
            }
        }

        public void m2135a(boolean z, String str, int i) {
            if (!z) {
                str = null;
            }
            sendMessage(obtainMessage(i, str));
        }
    }

    C0745a() {
    }

    static {
        f1994e = null;
        f1995f = null;
        f2001l = new C0762d();
        f2003n = f1998i;
        f1967D = f1993d;
        f1968E = true;
        f1969F = f1996g;
        f1970G = 1.0d;
        f1971H = f1993d;
        f1972I = f1993d;
        f1973J = f1993d;
        f1974K = f1993d;
        f1975L = true;
        f1978O = f1993d;
        ai = new ArrayList();
        aj = new C1236c();
        am = f1996g;
        an = new ArrayList();
        ao = new ArrayList();
        ap = new ArrayList();
        aq = new ArrayList();
    }

    static void m2138a(Activity activity) {
        if (activity != f1979P && activity != null) {
            f1979P = activity;
            f1986W = new C0743a();
            f1987X = new C0744b();
            if (!f2008s) {
                C0809a c0809a = new C0809a();
            }
        }
    }

    static void m2149b(Activity activity) {
        f2013x = f1993d;
        C0745a.m2138a(activity);
        f1982S = null;
        f2002m = C0772g.m2306i();
        if (f1980Q) {
            f1980Q = f1993d;
            f2012w = f1993d;
            f2001l = new C0762d();
        }
    }

    static boolean m2147a() {
        if (f1979P == null) {
            return true;
        }
        return f1993d;
    }

    static Activity m2148b() {
        if (f1979P != null) {
            return f1979P;
        }
        throw new AdColonyException("AdColony.configure() must be called before any other AdColony methods. If you have called AdColony.configure(), the Activity reference you passed in via AdColony.configure()/AdColony.resume() is null OR you have not called AdColony.resume() as appropriate.");
    }

    static boolean m2153c() {
        return (f1980Q || f2014y || !f2012w) ? true : f1993d;
    }

    static boolean m2155d() {
        return (!f2012w || f1980Q || f2014y) ? f1993d : true;
    }

    static void m2143a(String str) {
        f1980Q = true;
        C0745a.m2157e(str);
    }

    static void m2142a(RuntimeException runtimeException) {
        f1980Q = true;
        C0745a.m2157e(runtimeException.toString());
        runtimeException.printStackTrace();
    }

    static void m2156e() {
        C0745a.m2148b();
    }

    static void m2136a(int i) {
        boolean z;
        boolean z2 = f1993d;
        f2003n = i;
        C0777l.f2239a.f2244f = i <= 0 ? true : f1993d;
        C0777l c0777l = C0777l.f2240b;
        if (i <= f1997h) {
            z = true;
        } else {
            z = f1993d;
        }
        c0777l.f2244f = z;
        c0777l = C0777l.f2241c;
        if (i <= f1998i) {
            z = true;
        } else {
            z = f1993d;
        }
        c0777l.f2244f = z;
        C0777l c0777l2 = C0777l.f2242d;
        if (i <= f1999j) {
            z2 = true;
        }
        c0777l2.f2244f = z2;
        if (i <= 0) {
            C0745a.m2150b("DEVELOPER LOGGING ENABLED");
        }
        if (i <= f1997h) {
            C0745a.m2152c("DEBUG LOGGING ENABLED");
        }
    }

    static boolean m2151b(int i) {
        return f2003n <= i ? true : f1993d;
    }

    static boolean m2159f() {
        return f2003n <= 0 ? true : f1993d;
    }

    static boolean m2161g() {
        return f2003n <= f1997h ? true : f1993d;
    }

    static void m2137a(int i, String str) {
        if (f2003n <= i) {
            switch (i) {
                case f1996g /*0*/:
                case f1997h /*1*/:
                    Log.d(f2000k, str);
                case f1998i /*2*/:
                    Log.i(f2000k, str);
                case f1999j /*3*/:
                    Log.e(f2000k, str);
                default:
            }
        }
    }

    static void m2150b(String str) {
        C0745a.m2137a((int) f1996g, str);
    }

    static void m2152c(String str) {
        C0745a.m2137a((int) f1997h, str);
    }

    static void m2154d(String str) {
        C0745a.m2137a((int) f1998i, str);
    }

    static void m2157e(String str) {
        C0745a.m2137a((int) f1999j, str);
    }

    static void m2158f(String str) {
        Toast.makeText(C0745a.m2148b(), str, f1996g).show();
    }

    static double m2160g(String str) {
        return f2001l.m2242a(str);
    }

    static int m2162h(String str) {
        return f2001l.m2257b(str);
    }

    static boolean m2164i(String str) {
        return f2001l.m2265c(str);
    }

    static String m2165j(String str) {
        return f2001l.m2266d(str);
    }

    static void m2166k(String str) {
        f2001l.m2251a(str, null);
    }

    static void m2145a(String str, String str2) {
        f2001l.m2251a(str, str2);
    }

    static void m2144a(String str, AdColonyAd adColonyAd) {
        f2001l.m2252a(str, null, adColonyAd);
    }

    static void m2146a(String str, String str2, AdColonyAd adColonyAd) {
        f2001l.m2252a(str, str2, adColonyAd);
    }

    static void m2163h() {
        if (f2001l != null && ap.size() != 0 && ar != null) {
            for (Entry entry : ar.entrySet()) {
                boolean b;
                boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
                if (AdColony.isZoneV4VC((String) entry.getKey())) {
                    b = f2001l.m2263b((String) entry.getKey(), true, f1993d);
                } else {
                    b = f2001l.m2256a((String) entry.getKey(), true, (boolean) f1993d);
                }
                boolean b2 = (!AdColony.isZoneNative((String) entry.getKey()) || f1979P == null) ? f1979P == null ? f1993d : b : new AdColonyNativeAdView(C0745a.m2148b(), (String) entry.getKey(), (int) HttpStatus.SC_MULTIPLE_CHOICES, true).m2130b(true);
                if (booleanValue != b2) {
                    ar.put(entry.getKey(), Boolean.valueOf(b2));
                    for (int i = f1996g; i < ap.size(); i += f1997h) {
                        ((AdColonyAdAvailabilityListener) ap.get(i)).onAdColonyAdAvailabilityChange(b2, (String) entry.getKey());
                    }
                }
            }
        }
    }

    static void m2139a(AdColonyAd adColonyAd) {
        f2011v = adColonyAd;
    }

    static void m2140a(AdColonyNativeAdView adColonyNativeAdView) {
        aq.add(adColonyNativeAdView);
    }

    static void m2141a(C0775j c0775j) {
        f2001l.m2250a(c0775j);
    }
}
