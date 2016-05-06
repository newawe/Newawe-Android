package com.chartboost.sdk.impl;

import android.os.Process;
import com.chartboost.sdk.impl.C0402b.C0401a;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.chartboost.sdk.impl.c */
public class C0459c extends Thread {
    private static final boolean f712a;
    private final BlockingQueue<C0467l<?>> f713b;
    private final BlockingQueue<C0467l<?>> f714c;
    private final C0402b f715d;
    private final C0473o f716e;
    private volatile boolean f717f;

    /* renamed from: com.chartboost.sdk.impl.c.1 */
    class C04581 implements Runnable {
        final /* synthetic */ C0459c f710a;
        private final /* synthetic */ C0467l f711b;

        C04581(C0459c c0459c, C0467l c0467l) {
            this.f710a = c0459c;
            this.f711b = c0467l;
        }

        public void run() {
            try {
                this.f710a.f714c.put(this.f711b);
            } catch (InterruptedException e) {
            }
        }
    }

    static {
        f712a = C0478t.f778b;
    }

    public C0459c(BlockingQueue<C0467l<?>> blockingQueue, BlockingQueue<C0467l<?>> blockingQueue2, C0402b c0402b, C0473o c0473o) {
        this.f717f = false;
        this.f713b = blockingQueue;
        this.f714c = blockingQueue2;
        this.f715d = c0402b;
        this.f716e = c0473o;
    }

    public void m771a() {
        this.f717f = true;
        interrupt();
    }

    public void run() {
        if (f712a) {
            C0478t.m837a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.f715d.m595a();
        while (true) {
            try {
                C0467l c0467l = (C0467l) this.f713b.take();
                c0467l.m788a("cache-queue-take");
                if (c0467l.m798h()) {
                    c0467l.m792b("cache-discard-canceled");
                } else {
                    C0401a a = this.f715d.m594a(c0467l.m795e());
                    if (a == null) {
                        c0467l.m788a("cache-miss");
                        this.f714c.put(c0467l);
                    } else if (a.m592a()) {
                        c0467l.m788a("cache-hit-expired");
                        c0467l.m782a(a);
                        this.f714c.put(c0467l);
                    } else {
                        c0467l.m788a("cache-hit");
                        C0472n a2 = c0467l.m786a(new C0464i(a.f520a, a.f525f));
                        c0467l.m788a("cache-hit-parsed");
                        if (a.m593b()) {
                            c0467l.m788a("cache-hit-refresh-needed");
                            c0467l.m782a(a);
                            a2.f769d = true;
                            this.f716e.m829a(c0467l, a2, new C04581(this, c0467l));
                        } else {
                            this.f716e.m828a(c0467l, a2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.f717f) {
                    return;
                }
            }
        }
    }
}
