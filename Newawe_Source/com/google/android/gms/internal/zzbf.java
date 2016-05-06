package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.List;
import mf.org.apache.xml.serialize.LineSeparator;
import mf.org.apache.xml.serialize.Method;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
@zzhb
public class zzbf extends Thread {
    private boolean mStarted;
    private boolean zzam;
    private final Object zzpV;
    private final int zzsK;
    private final int zzsM;
    private boolean zzsY;
    private final zzbe zzsZ;
    private final zzbd zzta;
    private final zzha zztb;
    private final int zztc;
    private final int zztd;
    private final int zzte;

    /* renamed from: com.google.android.gms.internal.zzbf.1 */
    class C05501 implements Runnable {
        final /* synthetic */ View zztf;
        final /* synthetic */ zzbf zztg;

        C05501(zzbf com_google_android_gms_internal_zzbf, View view) {
            this.zztg = com_google_android_gms_internal_zzbf;
            this.zztf = view;
        }

        public void run() {
            this.zztg.zzf(this.zztf);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzbf.2 */
    class C05522 implements Runnable {
        final /* synthetic */ zzbf zztg;
        ValueCallback<String> zzth;
        final /* synthetic */ zzbc zzti;
        final /* synthetic */ WebView zztj;
        final /* synthetic */ boolean zztk;

        /* renamed from: com.google.android.gms.internal.zzbf.2.1 */
        class C05511 implements ValueCallback<String> {
            final /* synthetic */ C05522 zztl;

            C05511(C05522 c05522) {
                this.zztl = c05522;
            }

            public /* synthetic */ void onReceiveValue(Object obj) {
                zzt((String) obj);
            }

            public void zzt(String str) {
                this.zztl.zztg.zza(this.zztl.zzti, this.zztl.zztj, str, this.zztl.zztk);
            }
        }

        C05522(zzbf com_google_android_gms_internal_zzbf, zzbc com_google_android_gms_internal_zzbc, WebView webView, boolean z) {
            this.zztg = com_google_android_gms_internal_zzbf;
            this.zzti = com_google_android_gms_internal_zzbc;
            this.zztj = webView;
            this.zztk = z;
            this.zzth = new C05511(this);
        }

        public void run() {
            if (this.zztj.getSettings().getJavaScriptEnabled()) {
                try {
                    this.zztj.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzth);
                } catch (Throwable th) {
                    this.zzth.onReceiveValue(StringUtils.EMPTY);
                }
            }
        }
    }

    @zzhb
    class zza {
        final /* synthetic */ zzbf zztg;
        final int zztm;
        final int zztn;

        zza(zzbf com_google_android_gms_internal_zzbf, int i, int i2) {
            this.zztg = com_google_android_gms_internal_zzbf;
            this.zztm = i;
            this.zztn = i2;
        }
    }

    public zzbf(zzbe com_google_android_gms_internal_zzbe, zzbd com_google_android_gms_internal_zzbd, zzha com_google_android_gms_internal_zzha) {
        this.mStarted = false;
        this.zzsY = false;
        this.zzam = false;
        this.zzsZ = com_google_android_gms_internal_zzbe;
        this.zzta = com_google_android_gms_internal_zzbd;
        this.zztb = com_google_android_gms_internal_zzha;
        this.zzpV = new Object();
        this.zzsK = ((Integer) zzbt.zzwk.get()).intValue();
        this.zztd = ((Integer) zzbt.zzwl.get()).intValue();
        this.zzsM = ((Integer) zzbt.zzwm.get()).intValue();
        this.zzte = ((Integer) zzbt.zzwn.get()).intValue();
        this.zztc = ((Integer) zzbt.zzwo.get()).intValue();
        setName("ContentFetchTask");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
    L_0x0000:
        r0 = r3.zzam;
        if (r0 != 0) goto L_0x0052;
    L_0x0004:
        r0 = r3.zzcH();	 Catch:{ Throwable -> 0x0018 }
        if (r0 == 0) goto L_0x0044;
    L_0x000a:
        r0 = r3.zzsZ;	 Catch:{ Throwable -> 0x0018 }
        r0 = r0.getActivity();	 Catch:{ Throwable -> 0x0018 }
        if (r0 != 0) goto L_0x0038;
    L_0x0012:
        r0 = "ContentFetchThread: no activity";
        com.google.android.gms.ads.internal.util.client.zzb.zzaI(r0);	 Catch:{ Throwable -> 0x0018 }
        goto L_0x0000;
    L_0x0018:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.ads.internal.util.client.zzb.zzb(r1, r0);
        r1 = r3.zztb;
        r2 = 1;
        r1.zza(r0, r2);
    L_0x0024:
        r1 = r3.zzpV;
        monitor-enter(r1);
    L_0x0027:
        r0 = r3.zzsY;	 Catch:{ all -> 0x004f }
        if (r0 == 0) goto L_0x004d;
    L_0x002b:
        r0 = "ContentFetchTask: waiting";
        com.google.android.gms.ads.internal.util.client.zzb.zzaI(r0);	 Catch:{ InterruptedException -> 0x0036 }
        r0 = r3.zzpV;	 Catch:{ InterruptedException -> 0x0036 }
        r0.wait();	 Catch:{ InterruptedException -> 0x0036 }
        goto L_0x0027;
    L_0x0036:
        r0 = move-exception;
        goto L_0x0027;
    L_0x0038:
        r3.zza(r0);	 Catch:{ Throwable -> 0x0018 }
    L_0x003b:
        r0 = r3.zztc;	 Catch:{ Throwable -> 0x0018 }
        r0 = r0 * 1000;
        r0 = (long) r0;	 Catch:{ Throwable -> 0x0018 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x0018 }
        goto L_0x0024;
    L_0x0044:
        r0 = "ContentFetchTask: sleeping";
        com.google.android.gms.ads.internal.util.client.zzb.zzaI(r0);	 Catch:{ Throwable -> 0x0018 }
        r3.zzcJ();	 Catch:{ Throwable -> 0x0018 }
        goto L_0x003b;
    L_0x004d:
        monitor-exit(r1);	 Catch:{ all -> 0x004f }
        goto L_0x0000;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004f }
        throw r0;
    L_0x0052:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbf.run():void");
    }

    public void wakeup() {
        synchronized (this.zzpV) {
            this.zzsY = false;
            this.zzpV.notifyAll();
            zzb.zzaI("ContentFetchThread: wakeup");
        }
    }

    zza zza(View view, zzbc com_google_android_gms_internal_zzbc) {
        int i = 0;
        if (view == null) {
            return new zza(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            com_google_android_gms_internal_zzbc.zzd(text.toString(), globalVisibleRect);
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzjp)) {
            com_google_android_gms_internal_zzbc.zzcC();
            return zza((WebView) view, com_google_android_gms_internal_zzbc, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                zza zza = zza(viewGroup.getChildAt(i), com_google_android_gms_internal_zzbc);
                i3 += zza.zztm;
                i2 += zza.zztn;
                i++;
            }
            return new zza(this, i3, i2);
        }
    }

    void zza(Activity activity) {
        if (activity != null) {
            View view = null;
            if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                view = activity.getWindow().getDecorView().findViewById(16908290);
            }
            if (view != null) {
                zze(view);
            }
        }
    }

    void zza(zzbc com_google_android_gms_internal_zzbc, WebView webView, String str, boolean z) {
        com_google_android_gms_internal_zzbc.zzcB();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString(Method.TEXT);
                if (TextUtils.isEmpty(webView.getTitle())) {
                    com_google_android_gms_internal_zzbc.zzc(optString, z);
                } else {
                    com_google_android_gms_internal_zzbc.zzc(webView.getTitle() + LineSeparator.Web + optString, z);
                }
            }
            if (com_google_android_gms_internal_zzbc.zzcx()) {
                this.zzta.zzb(com_google_android_gms_internal_zzbc);
            }
        } catch (JSONException e) {
            zzb.zzaI("Json string may be malformed.");
        } catch (Throwable th) {
            zzb.zza("Failed to get webview content.", th);
            this.zztb.zza(th, true);
        }
    }

    boolean zza(RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    @TargetApi(19)
    boolean zza(WebView webView, zzbc com_google_android_gms_internal_zzbc, boolean z) {
        if (!zzne.zzsk()) {
            return false;
        }
        com_google_android_gms_internal_zzbc.zzcC();
        webView.post(new C05522(this, com_google_android_gms_internal_zzbc, webView, z));
        return true;
    }

    public void zzcG() {
        synchronized (this.zzpV) {
            if (this.mStarted) {
                zzb.zzaI("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    boolean zzcH() {
        try {
            Context context = this.zzsZ.getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (zza(runningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && zzs(context)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public zzbc zzcI() {
        return this.zzta.zzcF();
    }

    public void zzcJ() {
        synchronized (this.zzpV) {
            this.zzsY = true;
            zzb.zzaI("ContentFetchThread: paused, mPause = " + this.zzsY);
        }
    }

    public boolean zzcK() {
        return this.zzsY;
    }

    boolean zze(View view) {
        if (view == null) {
            return false;
        }
        view.post(new C05501(this, view));
        return true;
    }

    void zzf(View view) {
        try {
            zzbc com_google_android_gms_internal_zzbc = new zzbc(this.zzsK, this.zztd, this.zzsM, this.zzte);
            zza zza = zza(view, com_google_android_gms_internal_zzbc);
            com_google_android_gms_internal_zzbc.zzcD();
            if (zza.zztm != 0 || zza.zztn != 0) {
                if (zza.zztn != 0 || com_google_android_gms_internal_zzbc.zzcE() != 0) {
                    if (zza.zztn != 0 || !this.zzta.zza(com_google_android_gms_internal_zzbc)) {
                        this.zzta.zzc(com_google_android_gms_internal_zzbc);
                    }
                }
            }
        } catch (Throwable e) {
            zzb.zzb("Exception in fetchContentOnUIThread", e);
            this.zztb.zza(e, true);
        }
    }

    boolean zzs(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }
}
