package com.google.android.gms.internal;

import com.google.android.gms.internal.zzez.zza;

@zzhb
public final class zzeq extends zza {
    private zzes.zza zzCb;
    private zzep zzCc;
    private final Object zzpV;

    public zzeq() {
        this.zzpV = new Object();
    }

    public void onAdClicked() {
        synchronized (this.zzpV) {
            if (this.zzCc != null) {
                this.zzCc.zzaY();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzpV) {
            if (this.zzCc != null) {
                this.zzCc.zzaZ();
            }
        }
    }

    public void onAdFailedToLoad(int error) {
        synchronized (this.zzpV) {
            if (this.zzCb != null) {
                this.zzCb.zzr(error == 3 ? 1 : 2);
                this.zzCb = null;
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzpV) {
            if (this.zzCc != null) {
                this.zzCc.zzba();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.zzpV) {
            if (this.zzCb != null) {
                this.zzCb.zzr(0);
                this.zzCb = null;
                return;
            }
            if (this.zzCc != null) {
                this.zzCc.zzbc();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.zzpV) {
            if (this.zzCc != null) {
                this.zzCc.zzbb();
            }
        }
    }

    public void zza(zzep com_google_android_gms_internal_zzep) {
        synchronized (this.zzpV) {
            this.zzCc = com_google_android_gms_internal_zzep;
        }
    }

    public void zza(zzes.zza com_google_android_gms_internal_zzes_zza) {
        synchronized (this.zzpV) {
            this.zzCb = com_google_android_gms_internal_zzes_zza;
        }
    }

    public void zza(zzfa com_google_android_gms_internal_zzfa) {
        synchronized (this.zzpV) {
            if (this.zzCb != null) {
                this.zzCb.zza(0, com_google_android_gms_internal_zzfa);
                this.zzCb = null;
                return;
            }
            if (this.zzCc != null) {
                this.zzCc.zzbc();
            }
        }
    }
}
