package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.zza;
import com.google.android.gms.common.ConnectionResult;
import java.util.Date;
import java.util.HashSet;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

@zzhb
public final class zzfl {

    /* renamed from: com.google.android.gms.internal.zzfl.1 */
    static /* synthetic */ class C05921 {
        static final /* synthetic */ int[] zzCS;
        static final /* synthetic */ int[] zzCT;

        static {
            zzCT = new int[ErrorCode.values().length];
            try {
                zzCT[ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                zzCT[ErrorCode.INVALID_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                zzCT[ErrorCode.NETWORK_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                zzCT[ErrorCode.NO_FILL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            zzCS = new int[Gender.values().length];
            try {
                zzCS[Gender.FEMALE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                zzCS[Gender.MALE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                zzCS[Gender.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static int zza(ErrorCode errorCode) {
        switch (C05921.zzCT[errorCode.ordinal()]) {
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return 1;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return 2;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return 3;
            default:
                return 0;
        }
    }

    public static AdSize zzb(AdSizeParcel adSizeParcel) {
        int i = 0;
        AdSize[] adSizeArr = new AdSize[]{AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        while (i < adSizeArr.length) {
            if (adSizeArr[i].getWidth() == adSizeParcel.width && adSizeArr[i].getHeight() == adSizeParcel.height) {
                return adSizeArr[i];
            }
            i++;
        }
        return new AdSize(zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzuh));
    }

    public static MediationAdRequest zzj(AdRequestParcel adRequestParcel) {
        return new MediationAdRequest(new Date(adRequestParcel.zztC), zzu(adRequestParcel.zztD), adRequestParcel.zztE != null ? new HashSet(adRequestParcel.zztE) : null, adRequestParcel.zztF, adRequestParcel.zztK);
    }

    public static Gender zzu(int i) {
        switch (i) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return Gender.MALE;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return Gender.FEMALE;
            default:
                return Gender.UNKNOWN;
        }
    }
}
