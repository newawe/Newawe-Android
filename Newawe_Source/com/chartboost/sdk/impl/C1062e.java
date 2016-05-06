package com.chartboost.sdk.impl;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.chartboost.sdk.impl.e */
public class C1062e implements C0473o {
    private final Executor f3624a;

    /* renamed from: com.chartboost.sdk.impl.e.1 */
    class C04601 implements Executor {
        final /* synthetic */ C1062e f718a;
        private final /* synthetic */ Handler f719b;

        C04601(C1062e c1062e, Handler handler) {
            this.f718a = c1062e;
            this.f719b = handler;
        }

        public void execute(Runnable command) {
            this.f719b.post(command);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.e.a */
    private class C0461a implements Runnable {
        final /* synthetic */ C1062e f720a;
        private final C0467l f721b;
        private final C0472n f722c;
        private final Runnable f723d;

        public C0461a(C1062e c1062e, C0467l c0467l, C0472n c0472n, Runnable runnable) {
            this.f720a = c1062e;
            this.f721b = c0467l;
            this.f722c = c0472n;
            this.f723d = runnable;
        }

        public void run() {
            if (this.f721b.m798h()) {
                this.f721b.m792b("canceled-at-delivery");
                return;
            }
            if (this.f722c.m827a()) {
                this.f721b.m791b(this.f722c.f766a);
            } else {
                this.f721b.m790b(this.f722c.f768c);
            }
            if (this.f722c.f769d) {
                this.f721b.m788a("intermediate-response");
            } else {
                this.f721b.m792b("done");
            }
            if (this.f723d != null) {
                this.f723d.run();
            }
        }
    }

    public C1062e(Handler handler) {
        this.f3624a = new C04601(this, handler);
    }

    public void m4080a(C0467l<?> c0467l, C0472n<?> c0472n) {
        m4081a(c0467l, c0472n, null);
    }

    public void m4081a(C0467l<?> c0467l, C0472n<?> c0472n, Runnable runnable) {
        c0467l.m812v();
        c0467l.m788a("post-response");
        this.f3624a.execute(new C0461a(this, c0467l, c0472n, runnable));
    }

    public void m4082a(C0467l<?> c0467l, C0475s c0475s) {
        c0467l.m788a("post-error");
        this.f3624a.execute(new C0461a(this, c0467l, C0472n.m825a(c0475s), null));
    }
}
