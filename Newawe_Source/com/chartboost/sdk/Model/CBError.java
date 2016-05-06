package com.chartboost.sdk.Model;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class CBError {
    private C0336a f201a;
    private String f202b;
    private boolean f203c;

    /* renamed from: com.chartboost.sdk.Model.CBError.1 */
    static /* synthetic */ class C03351 {
        static final /* synthetic */ int[] f190a;

        static {
            f190a = new int[C0336a.values().length];
            try {
                f190a[C0336a.MISCELLANEOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f190a[C0336a.UNEXPECTED_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f190a[C0336a.PUBLIC_KEY_MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f190a[C0336a.INTERNET_UNAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f190a[C0336a.HTTP_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f190a[C0336a.NETWORK_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f190a[C0336a.INVALID_RESPONSE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum CBClickError {
        URI_INVALID,
        URI_UNRECOGNIZED,
        AGE_GATE_FAILURE,
        NO_HOST_ACTIVITY,
        INTERNAL
    }

    public enum CBImpressionError {
        INTERNAL,
        INTERNET_UNAVAILABLE,
        TOO_MANY_CONNECTIONS,
        WRONG_ORIENTATION,
        FIRST_SESSION_INTERSTITIALS_DISABLED,
        NETWORK_FAILURE,
        NO_AD_FOUND,
        SESSION_NOT_STARTED,
        IMPRESSION_ALREADY_VISIBLE,
        NO_HOST_ACTIVITY,
        USER_CANCELLATION,
        INVALID_LOCATION,
        VIDEO_UNAVAILABLE,
        VIDEO_ID_MISSING,
        ERROR_PLAYING_VIDEO,
        INVALID_RESPONSE,
        ASSETS_DOWNLOAD_FAILURE,
        ERROR_CREATING_VIEW,
        ERROR_DISPLAYING_VIEW,
        INCOMPATIBLE_API_VERSION,
        ERROR_LOADING_WEB_VIEW,
        ASSET_PREFETCH_IN_PROGRESS,
        EMPTY_LOCAL_AD_LIST,
        ACTIVITY_MISSING_IN_MANIFEST,
        EMPTY_LOCAL_VIDEO_LIST
    }

    /* renamed from: com.chartboost.sdk.Model.CBError.a */
    public enum C0336a {
        MISCELLANEOUS,
        INTERNET_UNAVAILABLE,
        INVALID_RESPONSE,
        UNEXPECTED_RESPONSE,
        NETWORK_FAILURE,
        PUBLIC_KEY_MISSING,
        HTTP_NOT_FOUND
    }

    public CBError(C0336a error, String errorDesc) {
        this.f201a = error;
        this.f202b = errorDesc;
        this.f203c = true;
    }

    public C0336a m250a() {
        return this.f201a;
    }

    public String m251b() {
        return this.f202b;
    }

    public CBImpressionError m252c() {
        switch (C03351.f190a[this.f201a.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return CBImpressionError.INTERNAL;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return CBImpressionError.INTERNET_UNAVAILABLE;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                return CBImpressionError.NO_AD_FOUND;
            default:
                return CBImpressionError.NETWORK_FAILURE;
        }
    }
}
