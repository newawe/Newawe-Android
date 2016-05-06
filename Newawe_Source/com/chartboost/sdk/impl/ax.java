package com.chartboost.sdk.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class ax {
    private static ExecutorService f465a;
    private static ThreadFactory f466b;

    /* renamed from: com.chartboost.sdk.impl.ax.1 */
    static class C03961 implements ThreadFactory {
        private final AtomicInteger f464a;

        C03961() {
            this.f464a = new AtomicInteger(1);
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, "Chartboost Thread #" + this.f464a.getAndIncrement());
        }
    }

    static {
        f465a = null;
        f466b = null;
    }

    public static ExecutorService m541a() {
        if (f466b == null) {
            f466b = new C03961();
        }
        if (f465a == null) {
            f465a = Executors.newFixedThreadPool(5, f466b);
        }
        return f465a;
    }
}
