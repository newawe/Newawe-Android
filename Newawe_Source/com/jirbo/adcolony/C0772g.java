package com.jirbo.adcolony;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.jirbo.adcolony.g */
class C0772g {
    static String f2202a;
    static boolean f2203b;

    C0772g() {
    }

    static String m2298a() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        return Secure.getString(AdColony.activity().getContentResolver(), "android_id");
    }

    static String m2299b() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        String networkOperatorName = ((TelephonyManager) AdColony.activity().getSystemService("phone")).getNetworkOperatorName();
        if (networkOperatorName.length() == 0) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return networkOperatorName;
    }

    static int m2300c() {
        if (C0745a.f1979P == null) {
            return 0;
        }
        Context applicationContext = C0745a.m2148b().getApplicationContext();
        C0745a.m2148b();
        return ((ActivityManager) applicationContext.getSystemService("activity")).getMemoryClass();
    }

    static long m2301d() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) AccessibilityNodeInfoCompat.ACTION_DISMISS);
    }

    static String m2302e() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        return ai.m2232a(C0745a.m2148b());
    }

    static int m2303f() {
        if (C0745a.f1979P == null) {
            return 0;
        }
        return C0745a.m2148b().getWindowManager().getDefaultDisplay().getWidth();
    }

    static int m2304g() {
        if (C0745a.f1979P == null) {
            return 0;
        }
        return C0745a.m2148b().getWindowManager().getDefaultDisplay().getHeight();
    }

    static String m2305h() {
        return StringUtils.EMPTY;
    }

    static boolean m2306i() {
        if (C0745a.f1979P == null) {
            return false;
        }
        if (C0745a.ah == null) {
            DisplayMetrics displayMetrics = AdColony.activity().getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
            if (Math.sqrt((double) ((f2 * f2) + (f * f))) < 6.0d) {
                return false;
            }
            return true;
        } else if (C0745a.ah.equals("tablet")) {
            return true;
        } else {
            return false;
        }
    }

    static String m2307j() {
        return Locale.getDefault().getLanguage();
    }

    static String m2308k() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        try {
            return ((WifiManager) AdColony.activity().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (RuntimeException e) {
            return null;
        }
    }

    static String m2309l() {
        return Build.MANUFACTURER;
    }

    static String m2310m() {
        return Build.MODEL;
    }

    static String m2311n() {
        return StringUtils.EMPTY;
    }

    static String m2312o() {
        return VERSION.RELEASE;
    }
}
