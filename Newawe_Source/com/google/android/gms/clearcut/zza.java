package com.google.android.gms.clearcut;

import android.content.Context;

public class zza {
    private static int zzaeO;
    public static final zza zzaeP;

    static {
        zzaeO = -1;
        zzaeP = new zza();
    }

    protected zza() {
    }

    public int zzah(Context context) {
        if (zzaeO < 0) {
            zzaeO = context.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
        }
        return zzaeO;
    }
}
