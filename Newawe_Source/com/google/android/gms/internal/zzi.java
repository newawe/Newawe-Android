package com.google.android.gms.internal;

import java.util.Map;
import org.apache.http.HttpStatus;

public class zzi {
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzA;
    public final boolean zzB;
    public final long zzC;

    public zzi(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.zzA = map;
        this.zzB = z;
        this.zzC = j;
    }

    public zzi(byte[] bArr, Map<String, String> map) {
        this(HttpStatus.SC_OK, bArr, map, false, 0);
    }
}
