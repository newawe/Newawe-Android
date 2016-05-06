package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;

@zzhb
public class zzc extends zzim implements ServiceConnection {
    private Context mContext;
    private zzgh zzAK;
    private boolean zzFB;
    private zzb zzFC;
    private zzh zzFD;
    private List<zzf> zzFE;
    private zzk zzFF;
    private final Object zzpV;

    /* renamed from: com.google.android.gms.ads.internal.purchase.zzc.1 */
    class C05031 implements Runnable {
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ zzf zzFG;
        final /* synthetic */ zzc zzFH;

        C05031(zzc com_google_android_gms_ads_internal_purchase_zzc, zzf com_google_android_gms_ads_internal_purchase_zzf, Intent intent) {
            this.zzFH = com_google_android_gms_ads_internal_purchase_zzc;
            this.zzFG = com_google_android_gms_ads_internal_purchase_zzf;
            this.val$intent = intent;
        }

        public void run() {
            try {
                if (this.zzFH.zzFF.zza(this.zzFG.zzFQ, -1, this.val$intent)) {
                    this.zzFH.zzAK.zza(new zzg(this.zzFH.mContext, this.zzFG.zzFR, true, -1, this.val$intent, this.zzFG));
                } else {
                    this.zzFH.zzAK.zza(new zzg(this.zzFH.mContext, this.zzFG.zzFR, false, -1, this.val$intent, this.zzFG));
                }
            } catch (RemoteException e) {
                zzb.zzaK("Fail to verify and dispatch pending transaction");
            }
        }
    }

    public zzc(Context context, zzgh com_google_android_gms_internal_zzgh, zzk com_google_android_gms_ads_internal_purchase_zzk) {
        this(context, com_google_android_gms_internal_zzgh, com_google_android_gms_ads_internal_purchase_zzk, new zzb(context), zzh.zzy(context.getApplicationContext()));
    }

    zzc(Context context, zzgh com_google_android_gms_internal_zzgh, zzk com_google_android_gms_ads_internal_purchase_zzk, zzb com_google_android_gms_ads_internal_purchase_zzb, zzh com_google_android_gms_ads_internal_purchase_zzh) {
        this.zzpV = new Object();
        this.zzFB = false;
        this.zzFE = null;
        this.mContext = context;
        this.zzAK = com_google_android_gms_internal_zzgh;
        this.zzFF = com_google_android_gms_ads_internal_purchase_zzk;
        this.zzFC = com_google_android_gms_ads_internal_purchase_zzb;
        this.zzFD = com_google_android_gms_ads_internal_purchase_zzh;
        this.zzFE = this.zzFD.zzg(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                zzin.m4114v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzFB);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = DateUtils.MILLIS_PER_MINUTE - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzpV.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            zzb.zzaK("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        synchronized (this.zzpV) {
            this.zzFC.zzN(service);
            zzfW();
            this.zzFB = true;
            this.zzpV.notify();
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        zzb.zzaJ("In-app billing service disconnected.");
        this.zzFC.destroy();
    }

    public void onStop() {
        synchronized (this.zzpV) {
            com.google.android.gms.common.stats.zzb.zzrP().zza(this.mContext, this);
            this.zzFC.destroy();
        }
    }

    protected void zza(zzf com_google_android_gms_ads_internal_purchase_zzf, String str, String str2) {
        Intent intent = new Intent();
        zzr.zzbM();
        intent.putExtra("RESPONSE_CODE", 0);
        zzr.zzbM();
        intent.putExtra("INAPP_PURCHASE_DATA", str);
        zzr.zzbM();
        intent.putExtra("INAPP_DATA_SIGNATURE", str2);
        zzir.zzMc.post(new C05031(this, com_google_android_gms_ads_internal_purchase_zzf, intent));
    }

    public void zzbr() {
        synchronized (this.zzpV) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage(zze.GOOGLE_PLAY_STORE_PACKAGE);
            com.google.android.gms.common.stats.zzb.zzrP().zza(this.mContext, intent, (ServiceConnection) this, 1);
            zze(SystemClock.elapsedRealtime());
            com.google.android.gms.common.stats.zzb.zzrP().zza(this.mContext, this);
            this.zzFC.destroy();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void zzfW() {
        /*
        r12 = this;
        r0 = r12.zzFE;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r6 = new java.util.HashMap;
        r6.<init>();
        r0 = r12.zzFE;
        r1 = r0.iterator();
    L_0x0014:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0026;
    L_0x001a:
        r0 = r1.next();
        r0 = (com.google.android.gms.ads.internal.purchase.zzf) r0;
        r2 = r0.zzFR;
        r6.put(r2, r0);
        goto L_0x0014;
    L_0x0026:
        r0 = 0;
    L_0x0027:
        r1 = r12.zzFC;
        r2 = r12.mContext;
        r2 = r2.getPackageName();
        r0 = r1.zzi(r2, r0);
        if (r0 != 0) goto L_0x0055;
    L_0x0035:
        r0 = r6.keySet();
        r1 = r0.iterator();
    L_0x003d:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0008;
    L_0x0043:
        r0 = r1.next();
        r0 = (java.lang.String) r0;
        r2 = r12.zzFD;
        r0 = r6.get(r0);
        r0 = (com.google.android.gms.ads.internal.purchase.zzf) r0;
        r2.zza(r0);
        goto L_0x003d;
    L_0x0055:
        r1 = com.google.android.gms.ads.internal.zzr.zzbM();
        r1 = r1.zzd(r0);
        if (r1 != 0) goto L_0x0035;
    L_0x005f:
        r1 = "INAPP_PURCHASE_ITEM_LIST";
        r7 = r0.getStringArrayList(r1);
        r1 = "INAPP_PURCHASE_DATA_LIST";
        r8 = r0.getStringArrayList(r1);
        r1 = "INAPP_DATA_SIGNATURE_LIST";
        r9 = r0.getStringArrayList(r1);
        r1 = "INAPP_CONTINUATION_TOKEN";
        r5 = r0.getString(r1);
        r0 = 0;
        r4 = r0;
    L_0x0079:
        r0 = r7.size();
        if (r4 >= r0) goto L_0x00bb;
    L_0x007f:
        r0 = r7.get(r4);
        r0 = r6.containsKey(r0);
        if (r0 == 0) goto L_0x00b7;
    L_0x0089:
        r0 = r7.get(r4);
        r0 = (java.lang.String) r0;
        r1 = r8.get(r4);
        r1 = (java.lang.String) r1;
        r2 = r9.get(r4);
        r2 = (java.lang.String) r2;
        r3 = r6.get(r0);
        r3 = (com.google.android.gms.ads.internal.purchase.zzf) r3;
        r10 = com.google.android.gms.ads.internal.zzr.zzbM();
        r10 = r10.zzaq(r1);
        r11 = r3.zzFQ;
        r10 = r11.equals(r10);
        if (r10 == 0) goto L_0x00b7;
    L_0x00b1:
        r12.zza(r3, r1, r2);
        r6.remove(r0);
    L_0x00b7:
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x0079;
    L_0x00bb:
        if (r5 == 0) goto L_0x0035;
    L_0x00bd:
        r0 = r6.isEmpty();
        if (r0 != 0) goto L_0x0035;
    L_0x00c3:
        r0 = r5;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzc.zzfW():void");
    }
}
