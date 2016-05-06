package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.C0402b.C0401a;

/* renamed from: com.chartboost.sdk.impl.n */
public class C0472n<T> {
    public final T f766a;
    public final C0401a f767b;
    public final C0475s f768c;
    public boolean f769d;

    /* renamed from: com.chartboost.sdk.impl.n.a */
    public interface C0470a {
        void m823a(C0475s c0475s);
    }

    /* renamed from: com.chartboost.sdk.impl.n.b */
    public interface C0471b<T> {
        void m824a(T t);
    }

    public static <T> C0472n<T> m826a(T t, C0401a c0401a) {
        return new C0472n(t, c0401a);
    }

    public static <T> C0472n<T> m825a(C0475s c0475s) {
        return new C0472n(c0475s);
    }

    public boolean m827a() {
        return this.f768c == null;
    }

    private C0472n(T t, C0401a c0401a) {
        this.f769d = false;
        this.f766a = t;
        this.f767b = c0401a;
        this.f768c = null;
    }

    private C0472n(C0475s c0475s) {
        this.f769d = false;
        this.f766a = null;
        this.f767b = null;
        this.f768c = c0475s;
    }
}
