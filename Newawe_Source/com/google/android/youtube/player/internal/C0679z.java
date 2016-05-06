package com.google.android.youtube.player.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.zze;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* renamed from: com.google.android.youtube.player.internal.z */
public final class C0679z {
    private static final Uri f835a;
    private static final String[] f836b;

    static {
        f835a = Uri.parse("http://play.google.com/store/apps/details");
        f836b = new String[]{"com.google.android.youtube", "com.google.android.youtube.tv", "com.google.android.youtube.googletv", zze.GOOGLE_PLAY_SERVICES_PACKAGE, null};
    }

    public static Intent m972a(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    public static String m973a() {
        return 1 + ".2.2";
    }

    public static String m974a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        for (String str : f836b) {
            ResolveInfo resolveService = packageManager.resolveService(new Intent("com.google.android.youtube.api.service.START").setPackage(str), 0);
            if (resolveService != null && resolveService.serviceInfo != null && resolveService.serviceInfo.packageName != null) {
                return resolveService.serviceInfo.packageName;
            }
        }
        return packageManager.hasSystemFeature("android.software.leanback") ? "com.google.android.youtube.tv" : packageManager.hasSystemFeature("com.google.android.tv") ? "com.google.android.youtube.googletv" : "com.google.android.youtube";
    }

    public static boolean m975a(Context context, String str) {
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(str);
            if (str.equals("com.google.android.youtube.googletvdev")) {
                str = "com.google.android.youtube.googletv";
            }
            int identifier = resourcesForApplication.getIdentifier("youtube_api_version_code", SchemaSymbols.ATTVAL_INTEGER, str);
            return identifier == 0 || 12 > resourcesForApplication.getInteger(identifier) / 100;
        } catch (NameNotFoundException e) {
            return true;
        }
    }

    public static boolean m976a(PackageManager packageManager) {
        return packageManager.hasSystemFeature("com.google.android.tv");
    }

    public static Context m977b(Context context) {
        try {
            return context.createPackageContext(C0679z.m974a(context), 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Intent m978b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(f835a.buildUpon().appendQueryParameter("id", str).build());
        intent.setPackage(zze.GOOGLE_PLAY_STORE_PACKAGE);
        intent.addFlags(AccessibilityNodeInfoCompat.ACTION_COLLAPSE);
        return intent;
    }

    public static boolean m979b(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.software.leanback");
    }

    public static int m980c(Context context) {
        Context b = C0679z.m977b(context);
        int i = 0;
        if (b != null) {
            i = b.getResources().getIdentifier("clientTheme", "style", C0679z.m974a(context));
        }
        return i == 0 ? VERSION.SDK_INT >= 14 ? 16974120 : VERSION.SDK_INT >= 11 ? 16973931 : 16973829 : i;
    }

    public static String m981d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable e) {
            throw new IllegalStateException("Cannot retrieve calling Context's PackageInfo", e);
        }
    }
}
