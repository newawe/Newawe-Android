package com.startapp.android.publish.p012f.p013a.p014a;

import java.io.Serializable;
import mf.org.apache.xerces.impl.io.UTF16Reader;

/* renamed from: com.startapp.android.publish.f.a.a.c */
public class StartAppSDK implements Serializable {
    static final /* synthetic */ boolean f2867a;
    private static final long serialVersionUID = -901334831550831262L;
    private final long[][] bits;
    private final int pageCount;
    private int wlen;

    static {
        f2867a = !StartAppSDK.class.desiredAssertionStatus() ? true : f2867a;
    }

    public StartAppSDK(long j) {
        int i = 0;
        this.wlen = m2759d(j);
        int i2 = this.wlen % UTF16Reader.DEFAULT_BUFFER_SIZE;
        int i3 = this.wlen / UTF16Reader.DEFAULT_BUFFER_SIZE;
        this.pageCount = (i2 == 0 ? 0 : 1) + i3;
        this.bits = new long[this.pageCount][];
        while (i < i3) {
            this.bits[i] = new long[UTF16Reader.DEFAULT_BUFFER_SIZE];
            i++;
        }
        if (i2 != 0) {
            this.bits[this.bits.length - 1] = new long[i2];
        }
    }

    long m2760a() {
        return ((long) this.wlen) << 6;
    }

    void m2761a(long j) {
        int b = m2756b(j);
        long j2 = 1 << (((int) j) & 63);
        long[] jArr = this.bits[b / UTF16Reader.DEFAULT_BUFFER_SIZE];
        b %= UTF16Reader.DEFAULT_BUFFER_SIZE;
        jArr[b] = j2 | jArr[b];
    }

    private int m2756b(long j) {
        int i = (int) (j >> 6);
        if (i >= this.wlen) {
            m2758c(1 + j);
            this.wlen = i + 1;
        }
        return i;
    }

    private void m2758c(long j) {
        m2757b(m2759d(j));
    }

    private int m2759d(long j) {
        return (int) (((j - 1) >>> 6) + 1);
    }

    private void m2757b(int i) {
        if (!f2867a && i > this.wlen) {
            throw new AssertionError("Growing of paged bitset is not supported");
        }
    }

    public int m2763b() {
        return this.wlen;
    }

    public int m2764c() {
        return this.pageCount;
    }

    public long[] m2762a(int i) {
        return this.bits[i];
    }
}
