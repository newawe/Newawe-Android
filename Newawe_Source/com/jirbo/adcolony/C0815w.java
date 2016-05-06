package com.jirbo.adcolony;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import mf.org.w3c.dom.traversal.NodeFilter;

/* renamed from: com.jirbo.adcolony.w */
class C0815w extends InputStream {
    InputStream f2601a;
    byte[] f2602b;
    int f2603c;
    int f2604d;
    int f2605e;
    int f2606f;
    int f2607g;
    int f2608h;

    C0815w(String str) throws IOException {
        this.f2602b = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
        if (C0745a.f2003n != 0) {
            this.f2608h = 23;
            this.f2607g = 23;
        }
        this.f2603c = (int) new File(str).length();
        this.f2601a = new FileInputStream(str);
    }

    public int available() throws IOException {
        return (this.f2605e - this.f2606f) + this.f2601a.available();
    }

    public void close() throws IOException {
        this.f2601a.close();
    }

    public void mark(int read_limit) {
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        if (this.f2604d == this.f2603c) {
            return -1;
        }
        if (this.f2606f >= this.f2605e) {
            m2510a();
        }
        this.f2604d++;
        byte[] bArr = this.f2602b;
        int i = this.f2606f;
        this.f2606f = i + 1;
        return bArr[i];
    }

    public int read(byte[] array) throws IOException {
        return read(array, 0, array.length);
    }

    public int read(byte[] array, int offset, int count) throws IOException {
        if (this.f2604d == this.f2603c) {
            return -1;
        }
        int count2 = this.f2603c - this.f2604d;
        if (count > count2) {
            count = count2;
        }
        int i = 0;
        while (count > 0) {
            if (this.f2606f == this.f2605e) {
                m2510a();
            }
            int i2 = count < this.f2605e ? count : this.f2605e;
            int i3 = 0;
            int offset2 = offset;
            while (i3 < i2) {
                offset = offset2 + 1;
                byte[] bArr = this.f2602b;
                int i4 = this.f2606f;
                this.f2606f = i4 + 1;
                array[offset2] = bArr[i4];
                i3++;
                offset2 = offset;
            }
            count -= i2;
            i += i2;
            this.f2604d = i2 + this.f2604d;
            offset = offset2;
        }
        return i;
    }

    public void reset() throws IOException {
        throw new IOException("ADCStreamReader does not support reset().");
    }

    public long skip(long n) throws IOException {
        throw new IOException("ADCStreamReader does not support skip().");
    }

    void m2510a() throws IOException {
        this.f2605e = 0;
        while (this.f2605e == 0) {
            this.f2605e = this.f2601a.read(this.f2602b, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
        for (int i = 0; i < this.f2605e; i++) {
            this.f2602b[i] = (byte) (this.f2602b[i] ^ this.f2607g);
            this.f2607g += this.f2608h;
        }
        this.f2606f = 0;
    }
}
