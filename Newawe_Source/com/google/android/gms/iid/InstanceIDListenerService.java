package com.google.android.gms.iid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

public class InstanceIDListenerService extends Service {
    static String ACTION;
    private static String zzaLH;
    private static String zzaMY;
    private static String zzaMZ;
    MessengerCompat zzaMW;
    BroadcastReceiver zzaMX;
    int zzaNa;
    int zzaNb;

    /* renamed from: com.google.android.gms.iid.InstanceIDListenerService.1 */
    class C05441 extends Handler {
        final /* synthetic */ InstanceIDListenerService zzaNc;

        C05441(InstanceIDListenerService instanceIDListenerService, Looper looper) {
            this.zzaNc = instanceIDListenerService;
            super(looper);
        }

        public void handleMessage(Message msg) {
            this.zzaNc.zza(msg, MessengerCompat.zzc(msg));
        }
    }

    /* renamed from: com.google.android.gms.iid.InstanceIDListenerService.2 */
    class C05452 extends BroadcastReceiver {
        final /* synthetic */ InstanceIDListenerService zzaNc;

        C05452(InstanceIDListenerService instanceIDListenerService) {
            this.zzaNc = instanceIDListenerService;
        }

        public void onReceive(Context context, Intent intent) {
            if (Log.isLoggable("InstanceID", 3)) {
                intent.getStringExtra(GCMConstants.EXTRA_REGISTRATION_ID);
                Log.d("InstanceID", "Received GSF callback using dynamic receiver: " + intent.getExtras());
            }
            this.zzaNc.zzp(intent);
            this.zzaNc.stop();
        }
    }

    static {
        ACTION = "action";
        zzaMY = "google.com/iid";
        zzaMZ = "CMD";
        zzaLH = "gcm.googleapis.com/refresh";
    }

    public InstanceIDListenerService() {
        this.zzaMW = new MessengerCompat(new C05441(this, Looper.getMainLooper()));
        this.zzaMX = new C05452(this);
    }

    static void zza(Context context, zzd com_google_android_gms_iid_zzd) {
        com_google_android_gms_iid_zzd.zzyG();
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra(zzaMZ, "RST");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    private void zza(Message message, int i) {
        zzc.zzaN(this);
        getPackageManager();
        if (i == zzc.zzaNi || i == zzc.zzaNh) {
            zzp((Intent) message.obj);
        } else {
            Log.w("InstanceID", "Message from unexpected caller " + i + " mine=" + zzc.zzaNh + " appid=" + zzc.zzaNi);
        }
    }

    static void zzaM(Context context) {
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.setPackage(context.getPackageName());
        intent.putExtra(zzaMZ, "SYNC");
        context.startService(intent);
    }

    public IBinder onBind(Intent intent) {
        return (intent == null || !"com.google.android.gms.iid.InstanceID".equals(intent.getAction())) ? null : this.zzaMW.getBinder();
    }

    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter(GCMConstants.INTENT_FROM_GCM_REGISTRATION_CALLBACK);
        intentFilter.addCategory(getPackageName());
        registerReceiver(this.zzaMX, intentFilter, "com.google.android.c2dm.permission.RECEIVE", null);
    }

    public void onDestroy() {
        unregisterReceiver(this.zzaMX);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        zzhl(startId);
        if (intent == null) {
            stop();
            return 2;
        }
        try {
            if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
                if (VERSION.SDK_INT <= 18) {
                    Intent intent2 = (Intent) intent.getParcelableExtra("GSF");
                    if (intent2 != null) {
                        startService(intent2);
                        return 1;
                    }
                }
                zzp(intent);
            }
            stop();
            if (intent.getStringExtra("from") != null) {
                WakefulBroadcastReceiver.completeWakefulIntent(intent);
            }
            return 2;
        } finally {
            stop();
        }
    }

    public void onTokenRefresh() {
    }

    void stop() {
        synchronized (this) {
            this.zzaNa--;
            if (this.zzaNa == 0) {
                stopSelf(this.zzaNb);
            }
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Stop " + this.zzaNa + " " + this.zzaNb);
            }
        }
    }

    public void zzal(boolean z) {
        onTokenRefresh();
    }

    void zzhl(int i) {
        synchronized (this) {
            this.zzaNa++;
            if (i > this.zzaNb) {
                this.zzaNb = i;
            }
        }
    }

    public void zzp(Intent intent) {
        InstanceID instance;
        String stringExtra = intent.getStringExtra("subtype");
        if (stringExtra == null) {
            instance = InstanceID.getInstance(this);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("subtype", stringExtra);
            instance = InstanceID.zza(this, bundle);
        }
        String stringExtra2 = intent.getStringExtra(zzaMZ);
        if (intent.getStringExtra(GCMConstants.EXTRA_ERROR) == null && intent.getStringExtra(GCMConstants.EXTRA_REGISTRATION_ID) == null) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Service command " + stringExtra + " " + stringExtra2 + " " + intent.getExtras());
            }
            if (intent.getStringExtra(GCMConstants.EXTRA_UNREGISTERED) != null) {
                zzd zzyB = instance.zzyB();
                if (stringExtra == null) {
                    stringExtra = StringUtils.EMPTY;
                }
                zzyB.zzef(stringExtra);
                instance.zzyC().zzw(intent);
                return;
            } else if (zzaLH.equals(intent.getStringExtra("from"))) {
                instance.zzyB().zzef(stringExtra);
                zzal(false);
                return;
            } else if ("RST".equals(stringExtra2)) {
                instance.zzyA();
                zzal(true);
                return;
            } else if ("RST_FULL".equals(stringExtra2)) {
                if (!instance.zzyB().isEmpty()) {
                    instance.zzyB().zzyG();
                    zzal(true);
                    return;
                }
                return;
            } else if ("SYNC".equals(stringExtra2)) {
                instance.zzyB().zzef(stringExtra);
                zzal(false);
                return;
            } else if ("PING".equals(stringExtra2)) {
                try {
                    GoogleCloudMessaging.getInstance(this).send(zzaMY, zzc.zzyF(), 0, intent.getExtras());
                    return;
                } catch (IOException e) {
                    Log.w("InstanceID", "Failed to send ping response");
                    return;
                }
            } else {
                return;
            }
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Register result in service " + stringExtra);
        }
        instance.zzyC().zzw(intent);
    }
}
