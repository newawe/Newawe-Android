package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import com.google.android.gms.common.zze;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;

public class zzc {
    static String zzaNg;
    static int zzaNh;
    static int zzaNi;
    static int zzaNj;
    Context context;
    PendingIntent zzaLQ;
    Messenger zzaLU;
    Map<String, Object> zzaNk;
    Messenger zzaNl;
    MessengerCompat zzaNm;
    long zzaNn;
    long zzaNo;
    int zzaNp;
    int zzaNq;
    long zzaNr;

    /* renamed from: com.google.android.gms.iid.zzc.1 */
    class C05471 extends Handler {
        final /* synthetic */ zzc zzaNs;

        C05471(zzc com_google_android_gms_iid_zzc, Looper looper) {
            this.zzaNs = com_google_android_gms_iid_zzc;
            super(looper);
        }

        public void handleMessage(Message msg) {
            this.zzaNs.zze(msg);
        }
    }

    static {
        zzaNg = null;
        zzaNh = 0;
        zzaNi = 0;
        zzaNj = 0;
    }

    public zzc(Context context) {
        this.zzaNk = new HashMap();
        this.context = context;
    }

    private void zzE(Object obj) {
        synchronized (getClass()) {
            for (String str : this.zzaNk.keySet()) {
                Object obj2 = this.zzaNk.get(str);
                this.zzaNk.put(str, obj);
                zzg(obj2, obj);
            }
        }
    }

    static String zza(KeyPair keyPair, String... strArr) {
        String str = null;
        try {
            byte[] bytes = TextUtils.join(LineSeparator.Web, strArr).getBytes(HTTP.UTF_8);
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                str = InstanceID.zzn(instance.sign());
            } catch (Throwable e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
            }
        } catch (Throwable e2) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e2);
        }
        return str;
    }

    public static String zzaN(Context context) {
        if (zzaNg != null) {
            return zzaNg;
        }
        zzaNh = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(GCMConstants.INTENT_TO_GCM_REGISTRATION), 0)) {
            if (packageManager.checkPermission("com.google.android.c2dm.permission.RECEIVE", resolveInfo.serviceInfo.packageName) == 0) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
                    Log.w("InstanceID/Rpc", "Found " + applicationInfo.uid);
                    zzaNi = applicationInfo.uid;
                    zzaNg = resolveInfo.serviceInfo.packageName;
                    return zzaNg;
                } catch (NameNotFoundException e) {
                }
            } else {
                Log.w("InstanceID/Rpc", "Possible malicious package " + resolveInfo.serviceInfo.packageName + " declares " + GCMConstants.INTENT_TO_GCM_REGISTRATION + " without permission");
            }
        }
        Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
        ApplicationInfo applicationInfo2;
        try {
            applicationInfo2 = packageManager.getApplicationInfo(zze.GOOGLE_PLAY_SERVICES_PACKAGE, 0);
            zzaNg = applicationInfo2.packageName;
            zzaNi = applicationInfo2.uid;
            return zzaNg;
        } catch (NameNotFoundException e2) {
            try {
                applicationInfo2 = packageManager.getApplicationInfo("com.google.android.gsf", 0);
                zzaNg = applicationInfo2.packageName;
                zzaNi = applicationInfo2.uid;
                return zzaNg;
            } catch (NameNotFoundException e3) {
                Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
                return null;
            }
        }
    }

    private Intent zzb(Bundle bundle, KeyPair keyPair) throws IOException {
        Intent intent;
        ConditionVariable conditionVariable = new ConditionVariable();
        String zzyF = zzyF();
        synchronized (getClass()) {
            this.zzaNk.put(zzyF, conditionVariable);
        }
        zza(bundle, keyPair, zzyF);
        conditionVariable.block(30000);
        synchronized (getClass()) {
            Object remove = this.zzaNk.remove(zzyF);
            if (remove instanceof Intent) {
                intent = (Intent) remove;
            } else if (remove instanceof String) {
                throw new IOException((String) remove);
            } else {
                Log.w("InstanceID/Rpc", "No response " + remove);
                throw new IOException(InstanceID.ERROR_TIMEOUT);
            }
        }
        return intent;
    }

    private void zzea(String str) {
        if ("com.google.android.gsf".equals(zzaNg)) {
            this.zzaNp++;
            if (this.zzaNp >= 3) {
                if (this.zzaNp == 3) {
                    this.zzaNq = new Random().nextInt(DateUtils.MILLIS_IN_SECOND) + DateUtils.MILLIS_IN_SECOND;
                }
                this.zzaNq *= 2;
                this.zzaNr = SystemClock.elapsedRealtime() + ((long) this.zzaNq);
                Log.w("InstanceID/Rpc", "Backoff due to " + str + " for " + this.zzaNq);
            }
        }
    }

    private void zzg(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                Log.w("InstanceID/Rpc", "Failed to send response " + e);
            }
        }
    }

    private void zzi(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.zzaNk.get(str);
            this.zzaNk.put(str, obj);
            zzg(obj2, obj);
        }
    }

    public static synchronized String zzyF() {
        String num;
        synchronized (zzc.class) {
            int i = zzaNj;
            zzaNj = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    Intent zza(Bundle bundle, KeyPair keyPair) throws IOException {
        Intent zzb = zzb(bundle, keyPair);
        return (zzb == null || !zzb.hasExtra("google.messenger")) ? zzb : zzb(bundle, keyPair);
    }

    void zza(Bundle bundle, KeyPair keyPair, String str) throws IOException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzaNr == 0 || elapsedRealtime > this.zzaNr) {
            zzyE();
            if (zzaNg == null) {
                throw new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE);
            }
            this.zzaNn = SystemClock.elapsedRealtime();
            Intent intent = new Intent(GCMConstants.INTENT_TO_GCM_REGISTRATION);
            intent.setPackage(zzaNg);
            bundle.putString("gmsv", Integer.toString(GoogleCloudMessaging.zzaK(this.context)));
            bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
            bundle.putString("app_ver", Integer.toString(InstanceID.zzaL(this.context)));
            bundle.putString("cliv", SchemaSymbols.ATTVAL_TRUE_1);
            bundle.putString("appid", InstanceID.zza(keyPair));
            bundle.putString("pub2", InstanceID.zzn(keyPair.getPublic().getEncoded()));
            bundle.putString("sig", zza(keyPair, this.context.getPackageName(), r1));
            intent.putExtras(bundle);
            zzt(intent);
            zzb(intent, str);
            return;
        }
        Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + (this.zzaNr - elapsedRealtime) + " interval: " + this.zzaNq);
        throw new IOException(InstanceID.ERROR_BACKOFF);
    }

    protected void zzb(Intent intent, String str) {
        this.zzaNn = SystemClock.elapsedRealtime();
        intent.putExtra("kid", "|ID|" + str + "|");
        intent.putExtra("X-kid", "|ID|" + str + "|");
        boolean equals = "com.google.android.gsf".equals(zzaNg);
        String stringExtra = intent.getStringExtra("useGsf");
        if (stringExtra != null) {
            equals = SchemaSymbols.ATTVAL_TRUE_1.equals(stringExtra);
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Sending " + intent.getExtras());
        }
        if (this.zzaNl != null) {
            intent.putExtra("google.messenger", this.zzaLU);
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                this.zzaNl.send(obtain);
                return;
            } catch (RemoteException e) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        if (equals) {
            Intent intent2 = new Intent("com.google.android.gms.iid.InstanceID");
            intent2.setPackage(this.context.getPackageName());
            intent2.putExtra("GSF", intent);
            this.context.startService(intent2);
            return;
        }
        intent.putExtra("google.messenger", this.zzaLU);
        intent.putExtra("messenger2", SchemaSymbols.ATTVAL_TRUE_1);
        if (this.zzaNm != null) {
            Message obtain2 = Message.obtain();
            obtain2.obj = intent;
            try {
                this.zzaNm.send(obtain2);
                return;
            } catch (RemoteException e2) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        this.context.startService(intent);
    }

    public void zze(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzaNm = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzaNl = (Messenger) parcelableExtra;
                    }
                }
                zzw((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    synchronized void zzt(Intent intent) {
        if (this.zzaLQ == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzaLQ = PendingIntent.getBroadcast(this.context, 0, intent2, 0);
        }
        intent.putExtra(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT, this.zzaLQ);
    }

    String zzu(Intent intent) throws IOException {
        if (intent == null) {
            throw new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE);
        }
        String stringExtra = intent.getStringExtra(GCMConstants.EXTRA_REGISTRATION_ID);
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra(GCMConstants.EXTRA_UNREGISTERED);
        }
        intent.getLongExtra(HttpHeaders.RETRY_AFTER, 0);
        if (stringExtra != null) {
            if (stringExtra == null) {
                return stringExtra;
            }
            stringExtra = intent.getStringExtra(GCMConstants.EXTRA_ERROR);
            if (stringExtra == null) {
                throw new IOException(stringExtra);
            }
            Log.w("InstanceID/Rpc", "Unexpected response from GCM " + intent.getExtras(), new Throwable());
            throw new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE);
        } else if (stringExtra == null) {
            return stringExtra;
        } else {
            stringExtra = intent.getStringExtra(GCMConstants.EXTRA_ERROR);
            if (stringExtra == null) {
                Log.w("InstanceID/Rpc", "Unexpected response from GCM " + intent.getExtras(), new Throwable());
                throw new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE);
            }
            throw new IOException(stringExtra);
        }
    }

    void zzv(Intent intent) {
        String stringExtra = intent.getStringExtra(GCMConstants.EXTRA_ERROR);
        if (stringExtra == null) {
            Log.w("InstanceID/Rpc", "Unexpected response, no error or registration id " + intent.getExtras());
            return;
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Received InstanceID error " + stringExtra);
        }
        String str = null;
        if (stringExtra.startsWith("|")) {
            String[] split = stringExtra.split("\\|");
            if (!SchemaSymbols.ATTVAL_ID.equals(split[1])) {
                Log.w("InstanceID/Rpc", "Unexpected structured response " + stringExtra);
            }
            if (split.length > 2) {
                str = split[2];
                stringExtra = split[3];
                if (stringExtra.startsWith(":")) {
                    stringExtra = stringExtra.substring(1);
                }
            } else {
                stringExtra = "UNKNOWN";
            }
            intent.putExtra(GCMConstants.EXTRA_ERROR, stringExtra);
        }
        if (str == null) {
            zzE(stringExtra);
        } else {
            zzi(str, stringExtra);
        }
        long longExtra = intent.getLongExtra(HttpHeaders.RETRY_AFTER, 0);
        if (longExtra > 0) {
            this.zzaNo = SystemClock.elapsedRealtime();
            this.zzaNq = ((int) longExtra) * DateUtils.MILLIS_IN_SECOND;
            this.zzaNr = SystemClock.elapsedRealtime() + ((long) this.zzaNq);
            Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.zzaNq);
        } else if (InstanceID.ERROR_SERVICE_NOT_AVAILABLE.equals(stringExtra) || GCMConstants.ERROR_AUTHENTICATION_FAILED.equals(stringExtra)) {
            zzea(stringExtra);
        }
    }

    void zzw(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (GCMConstants.INTENT_FROM_GCM_REGISTRATION_CALLBACK.equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
                action = intent.getStringExtra(GCMConstants.EXTRA_REGISTRATION_ID);
                String stringExtra = action == null ? intent.getStringExtra(GCMConstants.EXTRA_UNREGISTERED) : action;
                if (stringExtra == null) {
                    zzv(intent);
                    return;
                }
                this.zzaNn = SystemClock.elapsedRealtime();
                this.zzaNr = 0;
                this.zzaNp = 0;
                this.zzaNq = 0;
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "AppIDResponse: " + stringExtra + " " + intent.getExtras());
                }
                action = null;
                if (stringExtra.startsWith("|")) {
                    String[] split = stringExtra.split("\\|");
                    if (!SchemaSymbols.ATTVAL_ID.equals(split[1])) {
                        Log.w("InstanceID/Rpc", "Unexpected structured response " + stringExtra);
                    }
                    stringExtra = split[2];
                    if (split.length > 4) {
                        if ("SYNC".equals(split[3])) {
                            InstanceIDListenerService.zzaM(this.context);
                        } else if ("RST".equals(split[3])) {
                            InstanceIDListenerService.zza(this.context, InstanceID.getInstance(this.context).zzyB());
                            intent.removeExtra(GCMConstants.EXTRA_REGISTRATION_ID);
                            zzi(stringExtra, intent);
                            return;
                        }
                    }
                    action = split[split.length - 1];
                    if (action.startsWith(":")) {
                        action = action.substring(1);
                    }
                    intent.putExtra(GCMConstants.EXTRA_REGISTRATION_ID, action);
                    action = stringExtra;
                }
                if (action == null) {
                    zzE(intent);
                } else {
                    zzi(action, intent);
                }
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Unexpected response " + intent.getAction());
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
        }
    }

    void zzyE() {
        if (this.zzaLU == null) {
            zzaN(this.context);
            this.zzaLU = new Messenger(new C05471(this, Looper.getMainLooper()));
        }
    }
}
