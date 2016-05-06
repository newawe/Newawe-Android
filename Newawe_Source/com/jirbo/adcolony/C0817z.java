package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.jirbo.adcolony.z */
class C0817z {
    static String f2610a;
    static ArrayList<C0816a> f2611b;
    static ArrayList<C0816a> f2612c;
    static ArrayList<Runnable> f2613d;
    static ArrayList<Runnable> f2614e;
    static volatile boolean f2615f;

    /* renamed from: com.jirbo.adcolony.z.a */
    static class C0816a extends Thread {
        Runnable f2609a;

        C0816a() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r3 = this;
        L_0x0000:
            r0 = r3.f2609a;
            if (r0 == 0) goto L_0x000c;
        L_0x0004:
            r0 = r3.f2609a;	 Catch:{ RuntimeException -> 0x0011 }
            r0.run();	 Catch:{ RuntimeException -> 0x0011 }
        L_0x0009:
            r0 = 0;
            r3.f2609a = r0;
        L_0x000c:
            r0 = com.jirbo.adcolony.C0817z.f2615f;
            if (r0 == 0) goto L_0x0031;
        L_0x0010:
            return;
        L_0x0011:
            r0 = move-exception;
            r1 = "Exception caught in reusable thread.";
            com.jirbo.adcolony.C0745a.m2157e(r1);
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r1 = r1.append(r0);
            r2 = "";
            r1 = r1.append(r2);
            r1 = r1.toString();
            com.jirbo.adcolony.C0745a.m2157e(r1);
            r0.printStackTrace();
            goto L_0x0009;
        L_0x0031:
            monitor-enter(r3);
            r1 = com.jirbo.adcolony.C0817z.f2610a;	 Catch:{ all -> 0x0040 }
            monitor-enter(r1);	 Catch:{ all -> 0x0040 }
            r0 = com.jirbo.adcolony.C0817z.f2611b;	 Catch:{ all -> 0x0043 }
            r0.add(r3);	 Catch:{ all -> 0x0043 }
            monitor-exit(r1);	 Catch:{ all -> 0x0043 }
            r3.wait();	 Catch:{ InterruptedException -> 0x0046 }
        L_0x003e:
            monitor-exit(r3);	 Catch:{ all -> 0x0040 }
            goto L_0x0000;
        L_0x0040:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0040 }
            throw r0;
        L_0x0043:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0043 }
            throw r0;	 Catch:{ all -> 0x0040 }
        L_0x0046:
            r0 = move-exception;
            goto L_0x003e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jirbo.adcolony.z.a.run():void");
        }
    }

    C0817z() {
    }

    static {
        f2610a = new String("mutex");
        f2611b = new ArrayList();
        f2612c = new ArrayList();
        f2613d = new ArrayList();
        f2614e = new ArrayList();
    }

    static void m2511a() {
        C0817z.m2514c();
        synchronized (f2610a) {
            f2613d.clear();
        }
        C0817z.m2513b();
    }

    static void m2512a(Runnable runnable) {
        synchronized (f2610a) {
            if (f2615f) {
                f2613d.add(runnable);
                return;
            }
            if (null == null) {
                new Thread(runnable).start();
            }
        }
    }

    static void m2513b() {
        synchronized (f2610a) {
            f2615f = false;
            f2614e.clear();
            f2614e.addAll(f2613d);
            f2613d.clear();
            f2612c.clear();
        }
        Iterator it = f2614e.iterator();
        while (it.hasNext()) {
            C0817z.m2512a((Runnable) it.next());
        }
    }

    static void m2514c() {
        synchronized (f2610a) {
            f2615f = true;
            Iterator it = f2611b.iterator();
            while (it.hasNext()) {
                C0816a c0816a = (C0816a) it.next();
                synchronized (c0816a) {
                    c0816a.notify();
                }
            }
            synchronized (f2610a) {
                f2611b.clear();
            }
        }
    }
}
