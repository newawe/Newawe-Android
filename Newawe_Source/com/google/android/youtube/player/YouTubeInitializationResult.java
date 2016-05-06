package com.google.android.youtube.player;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.youtube.player.internal.C0662m;
import com.google.android.youtube.player.internal.C0678y;
import com.google.android.youtube.player.internal.C0679z;
import com.google.android.youtube.player.internal.ab;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public enum YouTubeInitializationResult {
    SUCCESS,
    INTERNAL_ERROR,
    UNKNOWN_ERROR,
    SERVICE_MISSING,
    SERVICE_VERSION_UPDATE_REQUIRED,
    SERVICE_DISABLED,
    SERVICE_INVALID,
    ERROR_CONNECTING_TO_SERVICE,
    CLIENT_LIBRARY_UPDATE_REQUIRED,
    NETWORK_ERROR,
    DEVELOPER_KEY_INVALID,
    INVALID_APPLICATION_SIGNATURE;

    /* renamed from: com.google.android.youtube.player.YouTubeInitializationResult.1 */
    static /* synthetic */ class C06481 {
        static final /* synthetic */ int[] f797a;

        static {
            f797a = new int[YouTubeInitializationResult.values().length];
            try {
                f797a[YouTubeInitializationResult.SERVICE_MISSING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f797a[YouTubeInitializationResult.SERVICE_DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f797a[YouTubeInitializationResult.SERVICE_VERSION_UPDATE_REQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.YouTubeInitializationResult.a */
    private static final class C0649a implements OnClickListener {
        private final Activity f798a;
        private final Intent f799b;
        private final int f800c;

        public C0649a(Activity activity, Intent intent, int i) {
            this.f798a = (Activity) ab.m879a((Object) activity);
            this.f799b = (Intent) ab.m879a((Object) intent);
            this.f800c = ((Integer) ab.m879a(Integer.valueOf(i))).intValue();
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            try {
                this.f798a.startActivityForResult(this.f799b, this.f800c);
                dialogInterface.dismiss();
            } catch (Throwable e) {
                C0678y.m970a("Can't perform resolution for YouTubeInitalizationError", e);
            }
        }
    }

    public final Dialog getErrorDialog(Activity activity, int i) {
        return getErrorDialog(activity, i, null);
    }

    public final Dialog getErrorDialog(Activity activity, int i, OnCancelListener onCancelListener) {
        Intent b;
        Builder builder = new Builder(activity);
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        switch (C06481.f797a[ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                b = C0679z.m978b(C0679z.m974a((Context) activity));
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                b = C0679z.m972a(C0679z.m974a((Context) activity));
                break;
            default:
                b = null;
                break;
        }
        OnClickListener c0649a = new C0649a(activity, b, i);
        C0662m c0662m = new C0662m(activity);
        switch (C06481.f797a[ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return builder.setTitle(c0662m.f810b).setMessage(c0662m.f811c).setPositiveButton(c0662m.f812d, c0649a).create();
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return builder.setTitle(c0662m.f813e).setMessage(c0662m.f814f).setPositiveButton(c0662m.f815g, c0649a).create();
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return builder.setTitle(c0662m.f816h).setMessage(c0662m.f817i).setPositiveButton(c0662m.f818j, c0649a).create();
            default:
                String str = "Unexpected errorReason: ";
                String valueOf = String.valueOf(name());
                throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public final boolean isUserRecoverableError() {
        switch (C06481.f797a[ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return true;
            default:
                return false;
        }
    }
}
