package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import org.apache.http.HttpStatus;

public class GcmReceiver extends WakefulBroadcastReceiver {
    private static String zzaLH;

    static {
        zzaLH = "gcm.googleapis.com/refresh";
    }

    private void zzj(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            setResultCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        zzk(context, intent);
        try {
            ComponentName startWakefulService;
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService == null) {
                Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
                if (isOrderedBroadcast()) {
                    setResultCode(HttpStatus.SC_NOT_FOUND);
                }
            } else if (isOrderedBroadcast()) {
                setResultCode(-1);
            }
        } catch (Throwable e) {
            Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", e);
            if (isOrderedBroadcast()) {
                setResultCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }
    }

    private void zzk(Context context, Intent intent) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null || resolveService.serviceInfo == null) {
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
            return;
        }
        ServiceInfo serviceInfo = resolveService.serviceInfo;
        if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
            Log.e("GcmReceiver", "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + serviceInfo.packageName + "/" + serviceInfo.name);
            return;
        }
        String str = serviceInfo.name;
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        }
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "Restricting intent to a specific service: " + str);
        }
        intent.setClassName(context.getPackageName(), str);
    }

    public void onReceive(Context context, Intent intent) {
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("from");
        if (GCMConstants.INTENT_FROM_GCM_REGISTRATION_CALLBACK.equals(intent.getAction()) || "google.com/iid".equals(stringExtra) || zzaLH.equals(stringExtra)) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if (GCMConstants.INTENT_FROM_GCM_MESSAGE.equals(intent.getAction())) {
            zzi(context, intent);
        } else {
            zzj(context, intent);
        }
        if (isOrderedBroadcast() && getResultCode() == 0) {
            setResultCode(-1);
        }
    }

    public void zzi(Context context, Intent intent) {
        zzj(context, intent);
    }
}
