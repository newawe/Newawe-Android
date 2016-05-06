package com.chartboost.sdk.impl;

import com.android.volley.DefaultRetryPolicy;

/* renamed from: com.chartboost.sdk.impl.d */
public class C1061d implements C0474p {
    private int f3620a;
    private int f3621b;
    private final int f3622c;
    private final float f3623d;

    public C1061d() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public C1061d(int i, int i2, float f) {
        this.f3620a = i;
        this.f3622c = i2;
        this.f3623d = f;
    }

    public int m4076a() {
        return this.f3620a;
    }

    public int m4078b() {
        return this.f3621b;
    }

    public void m4077a(C0475s c0475s) throws C0475s {
        this.f3621b++;
        this.f3620a = (int) (((float) this.f3620a) + (((float) this.f3620a) * this.f3623d));
        if (!m4079c()) {
            throw c0475s;
        }
    }

    protected boolean m4079c() {
        return this.f3621b <= this.f3622c;
    }
}
