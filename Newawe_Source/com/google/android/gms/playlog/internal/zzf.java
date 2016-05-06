package com.google.android.gms.playlog.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.playlog.internal.zzb.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzf extends zzj<zza> {
    private final String zzTJ;
    private final zzd zzbdT;
    private final zzb zzbdU;
    private boolean zzbdV;
    private final Object zzpV;

    public zzf(Context context, Looper looper, zzd com_google_android_gms_playlog_internal_zzd, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf) {
        super(context, looper, 24, com_google_android_gms_common_internal_zzf, com_google_android_gms_playlog_internal_zzd, com_google_android_gms_playlog_internal_zzd);
        this.zzTJ = context.getPackageName();
        this.zzbdT = (zzd) zzx.zzz(com_google_android_gms_playlog_internal_zzd);
        this.zzbdT.zza(this);
        this.zzbdU = new zzb();
        this.zzpV = new Object();
        this.zzbdV = true;
    }

    private void zzEW() {
        zzb.zzab(!this.zzbdV);
        if (!this.zzbdU.isEmpty()) {
            PlayLoggerContext playLoggerContext = null;
            try {
                List arrayList = new ArrayList();
                Iterator it = this.zzbdU.zzEU().iterator();
                while (it.hasNext()) {
                    zza com_google_android_gms_playlog_internal_zzb_zza = (zza) it.next();
                    if (com_google_android_gms_playlog_internal_zzb_zza.zzbdI != null) {
                        ((zza) zzqJ()).zza(this.zzTJ, com_google_android_gms_playlog_internal_zzb_zza.zzbdG, zzsu.toByteArray(com_google_android_gms_playlog_internal_zzb_zza.zzbdI));
                    } else {
                        PlayLoggerContext playLoggerContext2;
                        if (com_google_android_gms_playlog_internal_zzb_zza.zzbdG.equals(playLoggerContext)) {
                            arrayList.add(com_google_android_gms_playlog_internal_zzb_zza.zzbdH);
                            playLoggerContext2 = playLoggerContext;
                        } else {
                            if (!arrayList.isEmpty()) {
                                ((zza) zzqJ()).zza(this.zzTJ, playLoggerContext, arrayList);
                                arrayList.clear();
                            }
                            PlayLoggerContext playLoggerContext3 = com_google_android_gms_playlog_internal_zzb_zza.zzbdG;
                            arrayList.add(com_google_android_gms_playlog_internal_zzb_zza.zzbdH);
                            playLoggerContext2 = playLoggerContext3;
                        }
                        playLoggerContext = playLoggerContext2;
                    }
                }
                if (!arrayList.isEmpty()) {
                    ((zza) zzqJ()).zza(this.zzTJ, playLoggerContext, arrayList);
                }
                this.zzbdU.clear();
            } catch (RemoteException e) {
                Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
            }
        }
    }

    private void zzc(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        this.zzbdU.zza(playLoggerContext, logEvent);
    }

    private void zzd(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        try {
            zzEW();
            ((zza) zzqJ()).zza(this.zzTJ, playLoggerContext, logEvent);
        } catch (RemoteException e) {
            Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
            zzc(playLoggerContext, logEvent);
        } catch (IllegalStateException e2) {
            Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
            zzc(playLoggerContext, logEvent);
        }
    }

    public void start() {
        synchronized (this.zzpV) {
            if (isConnecting() || isConnected()) {
                return;
            }
            this.zzbdT.zzat(true);
            zzqG();
        }
    }

    public void stop() {
        synchronized (this.zzpV) {
            this.zzbdT.zzat(false);
            disconnect();
        }
    }

    protected /* synthetic */ IInterface zzW(IBinder iBinder) {
        return zzdO(iBinder);
    }

    void zzau(boolean z) {
        synchronized (this.zzpV) {
            boolean z2 = this.zzbdV;
            this.zzbdV = z;
            if (z2 && !this.zzbdV) {
                zzEW();
            }
        }
    }

    public void zzb(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        synchronized (this.zzpV) {
            if (this.zzbdV) {
                zzc(playLoggerContext, logEvent);
            } else {
                zzd(playLoggerContext, logEvent);
            }
        }
    }

    protected zza zzdO(IBinder iBinder) {
        return zza.zza.zzdN(iBinder);
    }

    protected String zzgu() {
        return "com.google.android.gms.playlog.service.START";
    }

    protected String zzgv() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }
}
