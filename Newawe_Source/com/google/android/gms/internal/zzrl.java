package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.signin.internal.zzg;
import com.google.android.gms.signin.internal.zzh;

public final class zzrl {
    public static final Api<zzro> API;
    public static final zzc<zzh> zzUI;
    public static final com.google.android.gms.common.api.Api.zza<zzh, zzro> zzUJ;
    public static final Scope zzWW;
    public static final Scope zzWX;
    public static final Api<zza> zzaoG;
    public static final zzc<zzh> zzavN;
    static final com.google.android.gms.common.api.Api.zza<zzh, zza> zzbgS;
    public static final zzrm zzbgT;

    /* renamed from: com.google.android.gms.internal.zzrl.1 */
    static class C11901 extends com.google.android.gms.common.api.Api.zza<zzh, zzro> {
        C11901() {
        }

        public zzh zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, zzro com_google_android_gms_internal_zzro, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzh(context, looper, true, com_google_android_gms_common_internal_zzf, com_google_android_gms_internal_zzro == null ? zzro.zzbgV : com_google_android_gms_internal_zzro, connectionCallbacks, onConnectionFailedListener);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzrl.2 */
    static class C11912 extends com.google.android.gms.common.api.Api.zza<zzh, zza> {
        C11912() {
        }

        public zzh zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, zza com_google_android_gms_internal_zzrl_zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzh(context, looper, false, com_google_android_gms_common_internal_zzf, com_google_android_gms_internal_zzrl_zza.zzFF(), connectionCallbacks, onConnectionFailedListener);
        }
    }

    public static class zza implements HasOptions {
        private final Bundle zzbgU;

        public Bundle zzFF() {
            return this.zzbgU;
        }
    }

    static {
        zzUI = new zzc();
        zzavN = new zzc();
        zzUJ = new C11901();
        zzbgS = new C11912();
        zzWW = new Scope(Scopes.PROFILE);
        zzWX = new Scope(Scopes.EMAIL);
        API = new Api("SignIn.API", zzUJ, zzUI);
        zzaoG = new Api("SignIn.INTERNAL_API", zzbgS, zzavN);
        zzbgT = new zzg();
    }
}
