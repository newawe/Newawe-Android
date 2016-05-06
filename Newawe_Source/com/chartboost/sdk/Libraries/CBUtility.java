package com.chartboost.sdk.Libraries;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.C0351c;
import com.google.android.gms.common.ConnectionResult;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

public final class CBUtility {
    private static Handler f86a;

    private CBUtility() {
    }

    public static SharedPreferences m87a() {
        if (C0351c.m378y() != null) {
            return C0351c.m378y().getSharedPreferences("cbPrefs", 0);
        }
        CBLogging.m77b("CBUtility", "The context must be set through the Chartboost method onCreate() before modifying or accessing preferences.");
        return null;
    }

    public static boolean m92b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String m88a(Map<String, Object> map) {
        if (map == null) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            stringBuilder.append("?");
        }
        for (String str : map.keySet()) {
            String str2;
            if (stringBuilder.length() > 1) {
                stringBuilder.append("&");
            }
            String obj = map.get(str2).toString();
            if (str2 != null) {
                try {
                    str2 = URLEncoder.encode(str2, HTTP.UTF_8);
                } catch (Throwable e) {
                    CBLogging.m78b("CBUtility", "This method requires UTF-8 encoding support", e);
                    return null;
                }
            }
            str2 = StringUtils.EMPTY;
            stringBuilder.append(str2);
            stringBuilder.append("=");
            stringBuilder.append(obj != null ? URLEncoder.encode(obj, HTTP.UTF_8) : StringUtils.EMPTY);
        }
        return stringBuilder.toString();
    }

    public static float m85a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int m86a(int i, Context context) {
        return Math.round(((float) i) * m85a(context));
    }

    public static float m84a(float f, Context context) {
        return m85a(context) * f;
    }

    public static C0324f m93c() {
        int i;
        Context y = C0351c.m378y();
        Display defaultDisplay = ((WindowManager) y.getSystemService("window")).getDefaultDisplay();
        int i2 = y.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            Object obj = 3;
        } else if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            i = 1;
        } else {
            i = 2;
        }
        if (obj == 1) {
            obj = 1;
        } else if (obj == 2) {
            obj = null;
        } else {
            if (obj == 3) {
                if (i2 == 1) {
                    i = 1;
                } else if (i2 == 2) {
                    obj = null;
                }
            }
            i = 1;
        }
        if (!(rotation == 0 || rotation == 2)) {
            if (obj == null) {
                i = 1;
            } else {
                obj = null;
            }
        }
        if (obj != null) {
            switch (rotation) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    return C0324f.f115g;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    return C0324f.PORTRAIT_REVERSE;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    return C0324f.f116h;
                default:
                    return C0324f.PORTRAIT;
            }
        }
        switch (rotation) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return C0324f.f113e;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return C0324f.LANDSCAPE_REVERSE;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return C0324f.f114f;
            default:
                return C0324f.LANDSCAPE;
        }
    }

    public static void throwProguardError(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            CBLogging.m77b("CBUtility", "Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
        } else if (ex == null || ex.getMessage() == null) {
            CBLogging.m77b("CBUtility", "Unknown Proguard error");
        } else {
            CBLogging.m77b("CBUtility", ex.getMessage());
        }
    }

    public static String m95d() {
        String str = "%s %s %s";
        Object[] objArr = new Object[3];
        objArr[0] = "Chartboost-Android-SDK";
        objArr[1] = C0351c.m343b() == null ? StringUtils.EMPTY : C0351c.m343b();
        objArr[2] = "6.0.2";
        return String.format(str, objArr);
    }

    public static Handler m96e() {
        if (f86a == null) {
            f86a = new Handler(Looper.getMainLooper());
        }
        return f86a;
    }

    public static void m90a(Handler handler) {
        f86a = handler;
    }

    public static boolean m97f() {
        return m99h() || m100i() || m101j();
    }

    public static String m98g() {
        SimpleDateFormat simpleDateFormat;
        if (VERSION.SDK_INT >= 18) {
            simpleDateFormat = new SimpleDateFormat("ZZZZ", Locale.US);
        } else {
            simpleDateFormat = new SimpleDateFormat("'GMT'ZZZZ", Locale.US);
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date());
    }

    private static boolean m99h() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean m100i() {
        return new File("/system/app/Superuser.apk").exists();
    }

    private static boolean m101j() {
        for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    public static void m89a(Activity activity) {
        if (activity != null) {
            C0324f c = m93c();
            if (c == C0324f.PORTRAIT) {
                activity.setRequestedOrientation(1);
            } else if (c == C0324f.PORTRAIT_REVERSE) {
                activity.setRequestedOrientation(9);
            } else if (c == C0324f.LANDSCAPE) {
                activity.setRequestedOrientation(0);
            } else {
                activity.setRequestedOrientation(8);
            }
        }
    }

    public static void m91b(Activity activity) {
        if (activity != null) {
            activity.setRequestedOrientation(-1);
        }
    }

    public static void m94c(Activity activity) {
        if (activity != null && VERSION.SDK_INT >= 11) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }
}
