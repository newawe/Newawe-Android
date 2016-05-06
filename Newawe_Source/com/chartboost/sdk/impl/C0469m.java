package com.chartboost.sdk.impl;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.chartboost.sdk.impl.m */
public class C0469m {
    private AtomicInteger f755a;
    private final Map<String, Queue<C0467l<?>>> f756b;
    private final Set<C0467l<?>> f757c;
    private final PriorityBlockingQueue<C0467l<?>> f758d;
    private final PriorityBlockingQueue<C0467l<?>> f759e;
    private final C0402b f760f;
    private final C0462f f761g;
    private final C0473o f762h;
    private C0463g[] f763i;
    private C0459c f764j;
    private boolean f765k;

    /* renamed from: com.chartboost.sdk.impl.m.a */
    public interface C0468a {
        boolean m814a(C0467l<?> c0467l);
    }

    /* renamed from: com.chartboost.sdk.impl.m.1 */
    class C10651 implements C0468a {
        final /* synthetic */ C0469m f3625a;
        private final /* synthetic */ Object f3626b;

        C10651(C0469m c0469m, Object obj) {
            this.f3625a = c0469m;
            this.f3626b = obj;
        }

        public boolean m4083a(C0467l<?> c0467l) {
            return c0467l.m789b() == this.f3626b;
        }
    }

    public C0469m(C0402b c0402b, C0462f c0462f, int i, C0473o c0473o) {
        this.f755a = new AtomicInteger();
        this.f756b = new HashMap();
        this.f757c = new HashSet();
        this.f758d = new PriorityBlockingQueue();
        this.f759e = new PriorityBlockingQueue();
        this.f765k = false;
        this.f760f = c0402b;
        this.f761g = c0462f;
        this.f763i = new C0463g[i];
        this.f762h = c0473o;
    }

    public C0469m(C0402b c0402b, C0462f c0462f, int i) {
        this(c0402b, c0462f, i, new C1062e(new Handler(Looper.getMainLooper())));
    }

    public C0469m(C0402b c0402b, C0462f c0462f) {
        this(c0402b, c0462f, 4);
    }

    public void m816a() {
        m820b();
        this.f764j = new C0459c(this.f758d, this.f759e, this.f760f, this.f762h);
        this.f764j.start();
        m819a(true);
        for (int i = 0; i < this.f763i.length; i++) {
            C0463g c0463g = new C0463g(this.f759e, this.f761g, this.f760f, this.f762h);
            this.f763i[i] = c0463g;
            c0463g.start();
        }
    }

    public void m820b() {
        int i = 0;
        m819a(false);
        if (this.f764j != null) {
            this.f764j.m771a();
        }
        while (i < this.f763i.length) {
            if (this.f763i[i] != null) {
                this.f763i[i].m775a();
            }
            i++;
        }
    }

    public int m822c() {
        return this.f755a.incrementAndGet();
    }

    public void m817a(C0468a c0468a) {
        synchronized (this.f757c) {
            for (C0467l c0467l : this.f757c) {
                if (c0468a.m814a(c0467l)) {
                    c0467l.m797g();
                }
            }
        }
    }

    public void m818a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        m817a(new C10651(this, obj));
    }

    public <T> C0467l<T> m815a(C0467l<T> c0467l) {
        c0467l.m783a(this);
        synchronized (this.f757c) {
            this.f757c.add(c0467l);
        }
        c0467l.m781a(m822c());
        c0467l.m788a("add-to-queue");
        if (c0467l.m808r()) {
            synchronized (this.f756b) {
                String e = c0467l.m795e();
                if (this.f756b.containsKey(e)) {
                    Queue queue = (Queue) this.f756b.get(e);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(c0467l);
                    this.f756b.put(e, queue);
                    if (C0478t.f778b) {
                        C0478t.m837a("Request for cacheKey=%s is in flight, putting on hold.", e);
                    }
                } else {
                    this.f756b.put(e, null);
                    this.f758d.add(c0467l);
                }
            }
        } else {
            this.f759e.add(c0467l);
        }
        return c0467l;
    }

    void m821b(C0467l<?> c0467l) {
        synchronized (this.f757c) {
            this.f757c.remove(c0467l);
        }
        if (c0467l.m808r()) {
            synchronized (this.f756b) {
                Queue queue = (Queue) this.f756b.remove(c0467l.m795e());
                if (queue != null) {
                    if (C0478t.f778b) {
                        C0478t.m837a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.f758d.addAll(queue);
                }
            }
        }
    }

    public boolean m819a(boolean z) {
        this.f765k = z;
        return z;
    }
}
