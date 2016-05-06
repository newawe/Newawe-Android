package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;

abstract class zzn extends BroadcastReceiver {
    protected Context mContext;

    zzn() {
    }

    @Nullable
    public static <T extends zzn> T zza(Context context, T t) {
        return zza(context, t, zzc.zzoK());
    }

    @Nullable
    public static <T extends zzn> T zza(Context context, T t, zzc com_google_android_gms_common_zzc) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(t, intentFilter);
        t.mContext = context;
        if (com_google_android_gms_common_zzc.zzi(context, zze.GOOGLE_PLAY_SERVICES_PACKAGE)) {
            return t;
        }
        t.zzpJ();
        t.unregister();
        return null;
    }

    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        Object obj = null;
        if (data != null) {
            obj = data.getSchemeSpecificPart();
        }
        if (zze.GOOGLE_PLAY_SERVICES_PACKAGE.equals(obj)) {
            zzpJ();
            unregister();
        }
    }

    public synchronized void unregister() {
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this);
        }
        this.mContext = null;
    }

    protected abstract void zzpJ();
}
