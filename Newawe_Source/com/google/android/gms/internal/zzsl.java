package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import mf.org.w3c.dom.traversal.NodeFilter;

public class zzsl {
    private final byte[] zzbtW;
    private int zzbtX;
    private int zzbtY;

    public zzsl(byte[] bArr) {
        int i;
        this.zzbtW = new byte[NodeFilter.SHOW_DOCUMENT];
        for (i = 0; i < NodeFilter.SHOW_DOCUMENT; i++) {
            this.zzbtW[i] = (byte) i;
        }
        i = 0;
        for (int i2 = 0; i2 < NodeFilter.SHOW_DOCUMENT; i2++) {
            i = ((i + this.zzbtW[i2]) + bArr[i2 % bArr.length]) & MotionEventCompat.ACTION_MASK;
            byte b = this.zzbtW[i2];
            this.zzbtW[i2] = this.zzbtW[i];
            this.zzbtW[i] = b;
        }
        this.zzbtX = 0;
        this.zzbtY = 0;
    }

    public void zzC(byte[] bArr) {
        int i = this.zzbtX;
        int i2 = this.zzbtY;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & MotionEventCompat.ACTION_MASK;
            i2 = (i2 + this.zzbtW[i]) & MotionEventCompat.ACTION_MASK;
            byte b = this.zzbtW[i];
            this.zzbtW[i] = this.zzbtW[i2];
            this.zzbtW[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzbtW[(this.zzbtW[i] + this.zzbtW[i2]) & MotionEventCompat.ACTION_MASK]);
        }
        this.zzbtX = i;
        this.zzbtY = i2;
    }
}
