package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class GcmListenerService extends Service {
    private int zzaLy;
    private int zzaLz;
    private final Object zzpV;

    /* renamed from: com.google.android.gms.gcm.GcmListenerService.1 */
    class C05381 implements Runnable {
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ GcmListenerService zzaLA;

        C05381(GcmListenerService gcmListenerService, Intent intent) {
            this.zzaLA = gcmListenerService;
            this.val$intent = intent;
        }

        public void run() {
            this.zzaLA.zzo(this.val$intent);
        }
    }

    /* renamed from: com.google.android.gms.gcm.GcmListenerService.2 */
    class C05392 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ GcmListenerService zzaLA;

        C05392(GcmListenerService gcmListenerService, Intent intent) {
            this.zzaLA = gcmListenerService;
            this.val$intent = intent;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return zzb((Void[]) objArr);
        }

        protected Void zzb(Void... voidArr) {
            this.zzaLA.zzo(this.val$intent);
            return null;
        }
    }

    public GcmListenerService() {
        this.zzpV = new Object();
        this.zzaLz = 0;
    }

    private void zzm(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("com.google.android.gms.gcm.PENDING_INTENT");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("GcmListenerService", "Notification pending intent canceled");
            }
        }
        if (zzx(intent.getExtras())) {
            zza.zzf(this, intent);
        }
    }

    @TargetApi(11)
    private void zzn(Intent intent) {
        if (VERSION.SDK_INT >= 11) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new C05381(this, intent));
        } else {
            new C05392(this, intent).execute(new Void[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzo(android.content.Intent r4) {
        /*
        r3 = this;
        r1 = r4.getAction();	 Catch:{ all -> 0x004a }
        r0 = -1;
        r2 = r1.hashCode();	 Catch:{ all -> 0x004a }
        switch(r2) {
            case 214175003: goto L_0x003c;
            case 366519424: goto L_0x0032;
            default: goto L_0x000c;
        };	 Catch:{ all -> 0x004a }
    L_0x000c:
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x004f;
            default: goto L_0x000f;
        };	 Catch:{ all -> 0x004a }
    L_0x000f:
        r0 = "GcmListenerService";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004a }
        r1.<init>();	 Catch:{ all -> 0x004a }
        r2 = "Unknown intent action: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x004a }
        r2 = r4.getAction();	 Catch:{ all -> 0x004a }
        r1 = r1.append(r2);	 Catch:{ all -> 0x004a }
        r1 = r1.toString();	 Catch:{ all -> 0x004a }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x004a }
    L_0x002b:
        r3.zzyh();	 Catch:{ all -> 0x004a }
        android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r4);
        return;
    L_0x0032:
        r2 = "com.google.android.c2dm.intent.RECEIVE";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x004a }
        if (r1 == 0) goto L_0x000c;
    L_0x003a:
        r0 = 0;
        goto L_0x000c;
    L_0x003c:
        r2 = "com.google.android.gms.gcm.NOTIFICATION_DISMISS";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x004a }
        if (r1 == 0) goto L_0x000c;
    L_0x0044:
        r0 = 1;
        goto L_0x000c;
    L_0x0046:
        r3.zzp(r4);	 Catch:{ all -> 0x004a }
        goto L_0x002b;
    L_0x004a:
        r0 = move-exception;
        android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r4);
        throw r0;
    L_0x004f:
        r0 = r4.getExtras();	 Catch:{ all -> 0x004a }
        r0 = zzx(r0);	 Catch:{ all -> 0x004a }
        if (r0 == 0) goto L_0x002b;
    L_0x0059:
        com.google.android.gms.gcm.zza.zzg(r3, r4);	 Catch:{ all -> 0x004a }
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmListenerService.zzo(android.content.Intent):void");
    }

    private void zzp(Intent intent) {
        String stringExtra = intent.getStringExtra(GCMConstants.EXTRA_SPECIAL_MESSAGE);
        if (stringExtra == null) {
            stringExtra = GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE;
        }
        Object obj = -1;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
                    obj = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
                    obj = null;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                    obj = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT)) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case DurationDV.DURATION_TYPE /*0*/:
                if (zzx(intent.getExtras())) {
                    zza.zze(this, intent);
                }
                zzq(intent);
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                onDeletedMessages();
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                onMessageSent(intent.getStringExtra("google.message_id"));
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                onSendError(intent.getStringExtra("google.message_id"), intent.getStringExtra(GCMConstants.EXTRA_ERROR));
            default:
                Log.w("GcmListenerService", "Received message with unknown type: " + stringExtra);
        }
    }

    private void zzq(Intent intent) {
        Bundle extras = intent.getExtras();
        extras.remove(GCMConstants.EXTRA_SPECIAL_MESSAGE);
        extras.remove("android.support.content.wakelockid");
        if (zzb.zzy(extras)) {
            if (zzb.zzaI(this)) {
                if (zzx(intent.getExtras())) {
                    zza.zzh(this, intent);
                }
                zzb.zzz(extras);
            } else {
                zzb.zzc(this, getClass()).zzA(extras);
                return;
            }
        }
        String string = extras.getString("from");
        extras.remove("from");
        zzw(extras);
        onMessageReceived(string, extras);
    }

    static void zzw(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    static boolean zzx(Bundle bundle) {
        return SchemaSymbols.ATTVAL_TRUE_1.equals(bundle.getString("google.c.a.e"));
    }

    private void zzyh() {
        synchronized (this.zzpV) {
            this.zzaLz--;
            if (this.zzaLz == 0) {
                zzhd(this.zzaLy);
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        return null;
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String from, Bundle data) {
    }

    public void onMessageSent(String msgId) {
    }

    public void onSendError(String msgId, String error) {
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        synchronized (this.zzpV) {
            this.zzaLy = startId;
            this.zzaLz++;
        }
        if (intent == null) {
            zzyh();
            return 2;
        }
        if ("com.google.android.gms.gcm.NOTIFICATION_OPEN".equals(intent.getAction())) {
            zzm(intent);
            zzyh();
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        } else {
            zzn(intent);
        }
        return 3;
    }

    boolean zzhd(int i) {
        return stopSelfResult(i);
    }
}
