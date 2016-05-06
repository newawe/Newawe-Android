package com.chartboost.sdk.impl;

import java.util.Collections;
import java.util.Map;

/* renamed from: com.chartboost.sdk.impl.b */
public interface C0402b {

    /* renamed from: com.chartboost.sdk.impl.b.a */
    public static class C0401a {
        public byte[] f520a;
        public String f521b;
        public long f522c;
        public long f523d;
        public long f524e;
        public Map<String, String> f525f;

        public C0401a() {
            this.f525f = Collections.emptyMap();
        }

        public boolean m592a() {
            return this.f523d < System.currentTimeMillis();
        }

        public boolean m593b() {
            return this.f524e < System.currentTimeMillis();
        }
    }

    C0401a m594a(String str);

    void m595a();

    void m596a(String str, C0401a c0401a);
}
