package com.chartboost.sdk.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import mf.org.w3c.dom.traversal.NodeFilter;

public class ac extends ByteArrayOutputStream {
    private final C0480v f390a;

    public ac(C0480v c0480v, int i) {
        this.f390a = c0480v;
        this.buf = this.f390a.m845a(Math.max(i, NodeFilter.SHOW_DOCUMENT));
    }

    public void close() throws IOException {
        this.f390a.m844a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.f390a.m844a(this.buf);
    }

    private void m490a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.f390a.m845a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.f390a.m844a(this.buf);
            this.buf = a;
        }
    }

    public synchronized void write(byte[] buffer, int offset, int len) {
        m490a(len);
        super.write(buffer, offset, len);
    }

    public synchronized void write(int oneByte) {
        m490a(1);
        super.write(oneByte);
    }
}
