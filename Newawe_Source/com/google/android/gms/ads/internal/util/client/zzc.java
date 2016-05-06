package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class zzc implements Creator<VersionInfoParcel> {
    static void zza(VersionInfoParcel versionInfoParcel, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, versionInfoParcel.versionCode);
        zzb.zza(parcel, 2, versionInfoParcel.afmaVersion, false);
        zzb.zzc(parcel, 3, versionInfoParcel.zzMZ);
        zzb.zzc(parcel, 4, versionInfoParcel.zzNa);
        zzb.zza(parcel, 5, versionInfoParcel.zzNb);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzR(i);
    }

    public VersionInfoParcel[] zzR(int i) {
        return new VersionInfoParcel[i];
    }

    public VersionInfoParcel zzp(Parcel parcel) {
        boolean z = false;
        int zzau = zza.zzau(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new VersionInfoParcel(i3, str, i2, i, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }
}
