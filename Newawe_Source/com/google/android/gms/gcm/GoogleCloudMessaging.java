package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzc;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang.StringUtils;

public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";
    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    public static int zzaLM;
    public static int zzaLN;
    public static int zzaLO;
    static GoogleCloudMessaging zzaLP;
    private static final AtomicInteger zzaLS;
    private Context context;
    private PendingIntent zzaLQ;
    private Map<String, Handler> zzaLR;
    private final BlockingQueue<Intent> zzaLT;
    final Messenger zzaLU;

    /* renamed from: com.google.android.gms.gcm.GoogleCloudMessaging.1 */
    class C05401 extends Handler {
        final /* synthetic */ GoogleCloudMessaging zzaLV;

        C05401(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
            this.zzaLV = googleCloudMessaging;
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (msg == null || !(msg.obj instanceof Intent)) {
                Log.w(GoogleCloudMessaging.INSTANCE_ID_SCOPE, "Dropping invalid message");
            }
            Intent intent = (Intent) msg.obj;
            if (GCMConstants.INTENT_FROM_GCM_REGISTRATION_CALLBACK.equals(intent.getAction())) {
                this.zzaLV.zzaLT.add(intent);
            } else if (!this.zzaLV.zzr(intent)) {
                intent.setPackage(this.zzaLV.context.getPackageName());
                this.zzaLV.context.sendBroadcast(intent);
            }
        }
    }

    static {
        zzaLM = 5000000;
        zzaLN = 6500000;
        zzaLO = 7000000;
        zzaLS = new AtomicInteger(1);
    }

    public GoogleCloudMessaging() {
        this.zzaLT = new LinkedBlockingQueue();
        this.zzaLR = Collections.synchronizedMap(new HashMap());
        this.zzaLU = new Messenger(new C05401(this, Looper.getMainLooper()));
    }

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (zzaLP == null) {
                zzaLP = new GoogleCloudMessaging();
                zzaLP.context = context.getApplicationContext();
            }
            googleCloudMessaging = zzaLP;
        }
        return googleCloudMessaging;
    }

    static String zza(Intent intent, String str) throws IOException {
        if (intent == null) {
            throw new IOException(ERROR_SERVICE_NOT_AVAILABLE);
        }
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null) {
            return stringExtra;
        }
        stringExtra = intent.getStringExtra(GCMConstants.EXTRA_ERROR);
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        throw new IOException(ERROR_SERVICE_NOT_AVAILABLE);
    }

    private void zza(String str, String str2, long j, int i, Bundle bundle) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        zzs(intent);
        intent.setPackage(zzaJ(this.context));
        intent.putExtra("google.to", str);
        intent.putExtra("google.message_id", str2);
        intent.putExtra("google.ttl", Long.toString(j));
        intent.putExtra("google.delay", Integer.toString(i));
        intent.putExtra("google.from", zzdZ(str));
        if (zzaJ(this.context).contains(".gsf")) {
            Bundle bundle2 = new Bundle();
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    bundle2.putString("gcm." + str3, (String) obj);
                }
            }
            bundle2.putString("google.to", str);
            bundle2.putString("google.message_id", str2);
            InstanceID.getInstance(this.context).zzc(INSTANCE_ID_SCOPE, "upstream", bundle2);
            return;
        }
        this.context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public static String zzaJ(Context context) {
        return zzc.zzaN(context);
    }

    public static int zzaK(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzaJ(context), 0).versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private String zzdZ(String str) {
        int indexOf = str.indexOf(64);
        if (indexOf > 0) {
            str = str.substring(0, indexOf);
        }
        return InstanceID.getInstance(this.context).zzyB().zzi(StringUtils.EMPTY, str, INSTANCE_ID_SCOPE);
    }

    private boolean zzr(Intent intent) {
        Object stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra(GCMConstants.EXTRA_ERROR)) {
            stringExtra = intent.getStringExtra("google.message_id");
        }
        if (stringExtra != null) {
            Handler handler = (Handler) this.zzaLR.remove(stringExtra);
            if (handler != null) {
                Message obtain = Message.obtain();
                obtain.obj = intent;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }

    private String zzyk() {
        return "google.rpc" + String.valueOf(zzaLS.getAndIncrement());
    }

    public void close() {
        zzaLP = null;
        zzb.zzaLC = null;
        zzyl();
    }

    public String getMessageType(Intent intent) {
        if (!GCMConstants.INTENT_FROM_GCM_MESSAGE.equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra(GCMConstants.EXTRA_SPECIAL_MESSAGE);
        return stringExtra == null ? MESSAGE_TYPE_MESSAGE : stringExtra;
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public synchronized String register(String... senderIds) throws IOException {
        String zzc;
        zzc = zzc(senderIds);
        Bundle bundle = new Bundle();
        if (zzaJ(this.context).contains(".gsf")) {
            bundle.putString("legacy.sender", zzc);
            zzc = InstanceID.getInstance(this.context).getToken(zzc, INSTANCE_ID_SCOPE, bundle);
        } else {
            bundle.putString(GCMConstants.EXTRA_SENDER, zzc);
            zzc = zza(zzE(bundle), GCMConstants.EXTRA_REGISTRATION_ID);
        }
        return zzc;
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(String to, String msgId, long timeToLive, Bundle data) throws IOException {
        zza(to, msgId, timeToLive, -1, data);
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(String to, String msgId, Bundle data) throws IOException {
        send(to, msgId, -1, data);
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public synchronized void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        }
        InstanceID.getInstance(this.context).deleteInstanceID();
    }

    @Deprecated
    Intent zzE(Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        } else if (zzaK(this.context) < 0) {
            throw new IOException("Google Play Services missing");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Intent intent = new Intent(GCMConstants.INTENT_TO_GCM_REGISTRATION);
            intent.setPackage(zzaJ(this.context));
            zzs(intent);
            intent.putExtra("google.message_id", zzyk());
            intent.putExtras(bundle);
            intent.putExtra("google.messenger", this.zzaLU);
            this.context.startService(intent);
            try {
                return (Intent) this.zzaLT.poll(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    String zzc(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder stringBuilder = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(',').append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    synchronized void zzs(Intent intent) {
        if (this.zzaLQ == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzaLQ = PendingIntent.getBroadcast(this.context, 0, intent2, 0);
        }
        intent.putExtra(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT, this.zzaLQ);
    }

    synchronized void zzyl() {
        if (this.zzaLQ != null) {
            this.zzaLQ.cancel();
            this.zzaLQ = null;
        }
    }
}
