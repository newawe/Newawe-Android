package com.chartboost.sdk.impl;

import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.chartboost.sdk.impl.i */
public class C0464i {
    public final int f729a;
    public final byte[] f730b;
    public final Map<String, String> f731c;
    public final boolean f732d;

    public C0464i(int i, byte[] bArr, Map<String, String> map, boolean z) {
        this.f729a = i;
        this.f730b = bArr;
        this.f731c = map;
        this.f732d = z;
    }

    public C0464i(byte[] bArr, Map<String, String> map) {
        this(HttpStatus.SC_OK, bArr, map, false);
    }
}
