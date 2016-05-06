package com.inmobi.rendering.p005a;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import mf.org.apache.xerces.dom3.as.ASContentModel;

/* renamed from: com.inmobi.rendering.a.a */
final class Click {
    int f1521a;
    String f1522b;
    long f1523c;
    int f1524d;
    AtomicBoolean f1525e;
    boolean f1526f;
    boolean f1527g;

    public Click(String str, boolean z, boolean z2, int i) {
        this(ASContentModel.AS_UNBOUNDED & new Random().nextInt(), str, z, z2, i, System.currentTimeMillis());
    }

    public Click(int i, String str, boolean z, boolean z2, int i2, long j) {
        this.f1521a = i;
        this.f1522b = str;
        this.f1523c = j;
        this.f1524d = i2;
        this.f1525e = new AtomicBoolean(false);
        this.f1527g = z;
        this.f1526f = z2;
    }
}
