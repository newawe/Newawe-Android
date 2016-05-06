package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.media.TransportMediator;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.core.utilities.info.a */
public class AppInfo {
    private static final String f1278a;
    private static AppInfo f1279b;
    private static Object f1280c;
    private String f1281d;
    private String f1282e;
    private String f1283f;
    private String f1284g;
    private Map<String, String> f1285h;

    static {
        f1278a = AppInfo.class.getSimpleName();
        f1280c = new Object();
    }

    private AppInfo() {
        this.f1285h = new HashMap();
        m1488a(SdkContext.m1258b());
        m1489d();
    }

    private void m1488a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), TransportMediator.FLAG_KEY_MEDIA_NEXT);
            if (applicationInfo != null) {
                this.f1281d = applicationInfo.packageName;
                this.f1282e = applicationInfo.loadLabel(packageManager).toString();
                this.f1284g = packageManager.getInstallerPackageName(this.f1281d);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), TransportMediator.FLAG_KEY_MEDIA_NEXT);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals(StringUtils.EMPTY)) {
                    str = packageInfo.versionCode + StringUtils.EMPTY;
                }
            }
            if (str != null && !str.equals(StringUtils.EMPTY)) {
                this.f1283f = str;
            }
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1278a, "Failed to fetch app info completely", e);
        }
    }

    private void m1489d() {
        this.f1285h.put("u-appbid", this.f1281d);
        this.f1285h.put("u-appdnm", this.f1282e);
        this.f1285h.put("u-appver", this.f1283f);
    }

    public static AppInfo m1487a() {
        AppInfo appInfo = f1279b;
        if (appInfo == null) {
            synchronized (f1280c) {
                appInfo = f1279b;
                if (appInfo == null) {
                    f1279b = new AppInfo();
                    appInfo = f1279b;
                }
            }
        }
        return appInfo;
    }

    public String m1490b() {
        return this.f1284g;
    }

    public Map<String, String> m1491c() {
        return this.f1285h;
    }
}
