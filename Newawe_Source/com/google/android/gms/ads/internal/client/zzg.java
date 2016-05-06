package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.startapp.android.publish.model.MetaData;
import java.util.List;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class zzg implements Creator<AdRequestParcel> {
    static void zza(AdRequestParcel adRequestParcel, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zztC);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zztD);
        zzb.zzb(parcel, 5, adRequestParcel.zztE, false);
        zzb.zza(parcel, 6, adRequestParcel.zztF);
        zzb.zzc(parcel, 7, adRequestParcel.zztG);
        zzb.zza(parcel, 8, adRequestParcel.zztH);
        zzb.zza(parcel, 9, adRequestParcel.zztI, false);
        zzb.zza(parcel, 10, adRequestParcel.zztJ, i, false);
        zzb.zza(parcel, 11, adRequestParcel.zztK, i, false);
        zzb.zza(parcel, 12, adRequestParcel.zztL, false);
        zzb.zza(parcel, 13, adRequestParcel.zztM, false);
        zzb.zza(parcel, 14, adRequestParcel.zztN, false);
        zzb.zzb(parcel, 15, adRequestParcel.zztO, false);
        zzb.zza(parcel, 17, adRequestParcel.zztQ, false);
        zzb.zza(parcel, 16, adRequestParcel.zztP, false);
        zzb.zza(parcel, 18, adRequestParcel.zztR);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzl(i);
    }

    public AdRequestParcel zzb(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        List list = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        List list2 = null;
        String str3 = null;
        String str4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    j = zza.zzi(parcel, zzat);
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    bundle = zza.zzr(parcel, zzat);
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    list = zza.zzD(parcel, zzat);
                    break;
                case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                case ConnectionResult.NETWORK_ERROR /*7*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case ConnectionResult.INTERNAL_ERROR /*8*/:
                    z2 = zza.zzc(parcel, zzat);
                    break;
                case ConnectionResult.SERVICE_INVALID /*9*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case MetaData.DEFAULT_MAX_ADS /*10*/:
                    searchAdRequestParcel = (SearchAdRequestParcel) zza.zza(parcel, zzat, SearchAdRequestParcel.CREATOR);
                    break;
                case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                    location = (Location) zza.zza(parcel, zzat, Location.CREATOR);
                    break;
                case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case ConnectionResult.CANCELED /*13*/:
                    bundle2 = zza.zzr(parcel, zzat);
                    break;
                case ConnectionResult.TIMEOUT /*14*/:
                    bundle3 = zza.zzr(parcel, zzat);
                    break;
                case ConnectionResult.INTERRUPTED /*15*/:
                    list2 = zza.zzD(parcel, zzat);
                    break;
                case ConnectionResult.API_UNAVAILABLE /*16*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case ConnectionResult.SIGN_IN_FAILED /*17*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case ConnectionResult.SERVICE_UPDATING /*18*/:
                    z3 = zza.zzc(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, str, searchAdRequestParcel, location, str2, bundle2, bundle3, list2, str3, str4, z3);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AdRequestParcel[] zzl(int i) {
        return new AdRequestParcel[i];
    }
}
