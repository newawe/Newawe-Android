package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class zzc implements Creator<AuthAccountRequest> {
    static void zza(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.zzakA, false);
        zzb.zza(parcel, 3, authAccountRequest.zzafT, i, false);
        zzb.zza(parcel, 4, authAccountRequest.zzakB, false);
        zzb.zza(parcel, 5, authAccountRequest.zzakC, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzam(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzbP(i);
    }

    public AuthAccountRequest zzam(Parcel parcel) {
        Integer num = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    iBinder = zza.zzq(parcel, zzat);
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzat, Scope.CREATOR);
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    num2 = zza.zzh(parcel, zzat);
                    break;
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    num = zza.zzh(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AuthAccountRequest(i, iBinder, scopeArr, num2, num);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AuthAccountRequest[] zzbP(int i) {
        return new AuthAccountRequest[i];
    }
}
