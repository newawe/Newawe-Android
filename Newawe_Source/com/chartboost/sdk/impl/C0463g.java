package com.chartboost.sdk.impl;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.chartboost.sdk.impl.g */
public class C0463g extends Thread {
    private final BlockingQueue<C0467l<?>> f724a;
    private final C0462f f725b;
    private final C0402b f726c;
    private final C0473o f727d;
    private volatile boolean f728e;

    public C0463g(BlockingQueue<C0467l<?>> blockingQueue, C0462f c0462f, C0402b c0402b, C0473o c0473o) {
        this.f728e = false;
        this.f724a = blockingQueue;
        this.f725b = c0462f;
        this.f726c = c0402b;
        this.f727d = c0473o;
    }

    public void m775a() {
        this.f728e = true;
        interrupt();
    }

    @TargetApi(14)
    private void m773a(C0467l<?> c0467l) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(c0467l.m793c());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                C0467l c0467l = (C0467l) this.f724a.take();
                try {
                    c0467l.m788a("network-queue-take");
                    if (c0467l.m798h()) {
                        c0467l.m792b("network-discard-cancelled");
                    } else {
                        m773a(c0467l);
                        C0464i a = this.f725b.m772a(c0467l);
                        c0467l.m788a("network-http-complete");
                        if (a.f732d && c0467l.m813w()) {
                            c0467l.m792b("not-modified");
                        } else {
                            C0472n a2 = c0467l.m786a(a);
                            c0467l.m788a("network-parse-complete");
                            if (c0467l.m808r() && a2.f767b != null) {
                                this.f726c.m596a(c0467l.m795e(), a2.f767b);
                                c0467l.m788a("network-cache-written");
                            }
                            c0467l.m812v();
                            this.f727d.m828a(c0467l, a2);
                        }
                    }
                } catch (C0475s e) {
                    m774a(c0467l, e);
                } catch (Throwable e2) {
                    C0478t.m838a(e2, "Unhandled exception %s", e2.toString());
                    this.f727d.m830a(c0467l, new C0475s(e2));
                }
            } catch (InterruptedException e3) {
                if (this.f728e) {
                    return;
                }
            }
        }
    }

    private void m774a(C0467l<?> c0467l, C0475s c0475s) {
        this.f727d.m830a((C0467l) c0467l, c0467l.m787a(c0475s));
    }
}
