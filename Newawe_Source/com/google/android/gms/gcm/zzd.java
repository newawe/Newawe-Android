package com.google.android.gms.gcm;

import android.os.Bundle;

public class zzd {
    public static final zzd zzaMc;
    public static final zzd zzaMd;
    private final int zzaMe;
    private final int zzaMf;
    private final int zzaMg;

    static {
        zzaMc = new zzd(0, 30, 3600);
        zzaMd = new zzd(1, 30, 3600);
    }

    private zzd(int i, int i2, int i3) {
        this.zzaMe = i;
        this.zzaMf = i2;
        this.zzaMg = i3;
    }

    public Bundle zzF(Bundle bundle) {
        bundle.putInt("retry_policy", this.zzaMe);
        bundle.putInt("initial_backoff_seconds", this.zzaMf);
        bundle.putInt("maximum_backoff_seconds", this.zzaMg);
        return bundle;
    }

    public int zzym() {
        return this.zzaMe;
    }

    public int zzyn() {
        return this.zzaMf;
    }

    public int zzyo() {
        return this.zzaMg;
    }
}
