package com.jirbo.adcolony;

import android.os.Handler;
import android.os.Message;
import com.jirbo.adcolony.C0807n.ad;
import java.util.Iterator;

/* renamed from: com.jirbo.adcolony.p */
class C0810p implements Runnable {
    public static final int f2565a = 5;
    public static final int f2566b = 10;
    static String f2567c;
    static volatile C0810p f2568d;
    static volatile long f2569e;
    long f2570f;

    /* renamed from: com.jirbo.adcolony.p.a */
    static class C0809a extends Handler {
        C0809a() {
            C0745a.f2008s = true;
            sendMessageDelayed(obtainMessage(), 1000);
        }

        public void handleMessage(Message m) {
            if (C0745a.f1979P == null || C0745a.m2148b().isFinishing()) {
                C0745a.m2150b("Monitor pinger exiting.");
                C0745a.f2008s = false;
                return;
            }
            if (!C0745a.f2007r) {
                C0810p.m2480a();
            }
            sendMessageDelayed(obtainMessage(), 1000);
        }
    }

    C0810p() {
    }

    static {
        f2567c = "MONITOR_MUTEX";
    }

    static void m2480a() {
        synchronized (f2567c) {
            f2569e = System.currentTimeMillis();
            if (f2568d == null) {
                C0745a.m2150b("Creating ADC Monitor singleton.");
                f2568d = new C0810p();
                new Thread(f2568d).start();
            }
        }
    }

    public void run() {
        C0745a.m2136a(C0745a.f2003n);
        C0777l.f2239a.m2353b((Object) "ADC Monitor Started.");
        C0745a.f2001l.m2258b();
        Object obj = null;
        while (C0745a.f1979P != null && !AdColony.activity().isFinishing()) {
            long j;
            Object obj2;
            long currentTimeMillis = System.currentTimeMillis();
            C0745a.f2015z = false;
            C0745a.f2001l.m2273g();
            if (C0745a.f2015z) {
                j = 50;
            } else {
                j = (long) (obj != null ? 2000 : 250);
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            int i = (int) ((currentTimeMillis2 - f2569e) / 1000);
            C0745a.f2001l.m2273g();
            if (obj == null) {
                if (i >= f2565a) {
                    C0745a.m2150b("AdColony is idle.");
                    obj = 1;
                    C0745a.f2001l.m2264c();
                }
                obj2 = obj;
            } else if (i >= f2566b) {
                break;
            } else {
                if (i < f2565a) {
                    C0745a.f2001l.m2258b();
                    C0745a.m2150b("AdColony is active.");
                    obj2 = null;
                }
                obj2 = obj;
            }
            if (C0811q.m2489c()) {
                if (!C0745a.f1975L) {
                    C0745a.m2163h();
                }
                C0745a.f1975L = true;
            } else {
                if (C0745a.f1975L) {
                    C0745a.m2163h();
                }
                C0745a.f1975L = false;
            }
            if (!(C0745a.f2001l.f2142b.f4000i == null || C0745a.f2001l.f2142b.f4000i.f2378n == null)) {
                C0745a.f2001l.f2142b.f4000i.f2378n.m2413a();
            }
            m2481a(j);
            j = System.currentTimeMillis();
            if (j - currentTimeMillis <= 3000 && j - currentTimeMillis > 0) {
                C0814u c0814u = C0745a.f2001l.f2145e;
                c0814u.f2598i = (((double) (j - currentTimeMillis)) / 1000.0d) + c0814u.f2598i;
                if (C0745a.f1979P != null && currentTimeMillis2 - this.f2570f > 1000) {
                    this.f2570f = System.currentTimeMillis();
                    Iterator it = C0745a.f2001l.f2142b.f4000i.f2378n.f2330a.iterator();
                    while (it.hasNext()) {
                        ad adVar = (ad) it.next();
                        if ((adVar.m2390a() && adVar.f2320q != 0 && System.currentTimeMillis() - adVar.f2320q > adVar.f2319p) || (adVar.f2320q != 0 && System.currentTimeMillis() - adVar.f2320q > adVar.f2318o)) {
                            if (!(C0745a.f1979P == null || C0745a.f2005p)) {
                                C0745a.f2001l.f2142b.m4694a(C0745a.f1979P);
                                C0745a.f2005p = true;
                            }
                        }
                    }
                }
            }
            obj = obj2;
        }
        synchronized (f2567c) {
            f2568d = null;
        }
        if (obj == null) {
            C0745a.f2001l.m2264c();
        }
        if (C0745a.f1979P != null && AdColony.activity().isFinishing()) {
            C0745a.f1964A = true;
            m2481a(5000);
            if (C0745a.f1964A) {
                C0777l.f2241c.m2353b((Object) "ADC.finishing, controller on_stop");
                C0745a.f2001l.m2267d();
                C0817z.m2511a();
            }
            m2481a(5000);
            if (C0745a.f1964A) {
                C0777l.f2241c.m2353b((Object) "Releasing Activity reference");
                C0745a.f1979P = null;
                C0745a.m2163h();
            }
        }
        System.out.println("Exiting monitor");
    }

    void m2481a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
        }
    }
}
