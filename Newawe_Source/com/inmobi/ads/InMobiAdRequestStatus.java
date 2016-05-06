package com.inmobi.ads;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class InMobiAdRequestStatus {
    private static final String AD_ACTIVE_MESSAGE = "The Ad Request could not be submitted as the user is viewing another Ad.";
    private static final String AD_EARLY_REFRESH_MESSAGE = "The Ad Request cannot be done so frequently. Please wait for some time before loading another ad.";
    private static final String INTERNAL_ERROR_MESSAGE = "The InMobi SDK encountered an internal error.";
    private static final String NETWORK_UNREACHABLE_MESSAGE = "The Internet is unreachable. Please check your Internet connection.";
    private static final String NO_FILL_MESSAGE = "Ad request successful but no ad served.";
    private static final String REQUEST_INVALID_MESSAGE = "An invalid ad request was sent and was rejected by the Ad Network. Please validate the ad request and try again";
    private static final String REQUEST_PENDING_MESSAGE = "The SDK is pending response to a previous ad request. Please wait for the previous ad request to complete before requesting for another ad.";
    private static final String REQUEST_TIMED_OUT_MESSAGE = "The Ad Request timed out waiting for a response from the network. This can be caused due to a bad network connection. Please try again after a few minutes.";
    private static final String SERVER_ERROR_MESSAGE = "The Ad Server encountered an error when processing the ad request. This may be a transient issue. Please try again in a few minutes";
    private String mMessage;
    private StatusCode mStatusCode;

    /* renamed from: com.inmobi.ads.InMobiAdRequestStatus.1 */
    static /* synthetic */ class C06811 {
        static final /* synthetic */ int[] f1034a;

        static {
            f1034a = new int[StatusCode.values().length];
            try {
                f1034a[StatusCode.INTERNAL_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1034a[StatusCode.NETWORK_UNREACHABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1034a[StatusCode.REQUEST_INVALID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1034a[StatusCode.REQUEST_PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1034a[StatusCode.REQUEST_TIMED_OUT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1034a[StatusCode.SERVER_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1034a[StatusCode.NO_FILL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1034a[StatusCode.AD_ACTIVE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f1034a[StatusCode.EARLY_REFRESH_REQUEST.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public enum StatusCode {
        NETWORK_UNREACHABLE,
        NO_FILL,
        REQUEST_INVALID,
        REQUEST_PENDING,
        REQUEST_TIMED_OUT,
        INTERNAL_ERROR,
        SERVER_ERROR,
        AD_ACTIVE,
        EARLY_REFRESH_REQUEST
    }

    public InMobiAdRequestStatus(StatusCode statusCode) {
        this.mStatusCode = statusCode;
        setMessage();
    }

    public InMobiAdRequestStatus setCustomMessage(String str) {
        if (str != null) {
            this.mMessage = str;
        }
        return this;
    }

    private void setMessage() {
        switch (C06811.f1034a[this.mStatusCode.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.mMessage = INTERNAL_ERROR_MESSAGE;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.mMessage = NETWORK_UNREACHABLE_MESSAGE;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.mMessage = REQUEST_INVALID_MESSAGE;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                this.mMessage = REQUEST_PENDING_MESSAGE;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                this.mMessage = REQUEST_TIMED_OUT_MESSAGE;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                this.mMessage = SERVER_ERROR_MESSAGE;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                this.mMessage = NO_FILL_MESSAGE;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                this.mMessage = AD_ACTIVE_MESSAGE;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                this.mMessage = AD_EARLY_REFRESH_MESSAGE;
            default:
        }
    }

    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }

    public String getMessage() {
        return this.mMessage;
    }
}
