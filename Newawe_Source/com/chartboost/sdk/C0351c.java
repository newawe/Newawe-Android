package com.chartboost.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.chartboost.sdk.C0356d.C0354a;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.az.C0399c;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.c */
public final class C0351c {
    private static boolean f299A;
    private static boolean f300B;
    private static boolean f301C;
    private static boolean f302D;
    private static boolean f303E;
    public static C0354a f304a;
    private static final String f305b;
    private static String f306c;
    private static String f307d;
    private static C0345a f308e;
    private static boolean f309f;
    private static boolean f310g;
    private static boolean f311h;
    private static CBFramework f312i;
    private static String f313j;
    private static String f314k;
    private static String f315l;
    private static CBMediation f316m;
    private static String f317n;
    private static String f318o;
    private static SharedPreferences f319p;
    private static boolean f320q;
    private static volatile boolean f321r;
    private static Context f322s;
    private static Application f323t;
    private static boolean f324u;
    private static boolean f325v;
    private static boolean f326w;
    private static boolean f327x;
    private static float f328y;
    private static boolean f329z;

    /* renamed from: com.chartboost.sdk.c.a */
    public interface C0350a {
        void m322a();
    }

    /* renamed from: com.chartboost.sdk.c.1 */
    static class C10241 implements C0399c {
        final /* synthetic */ C0350a f3416a;

        C10241(C0350a c0350a) {
            this.f3416a = c0350a;
        }

        public void m3787a(C0321a c0321a, az azVar) {
            if (c0321a.m134c()) {
                C0321a a = c0321a.m127a("response");
                if (a.m134c()) {
                    C0351c.m336a(a);
                }
            }
            if (this.f3416a != null) {
                this.f3416a.m322a();
            }
        }

        public void m3788a(C0321a c0321a, az azVar, CBError cBError) {
            if (this.f3416a != null) {
                this.f3416a.m322a();
            }
        }
    }

    static {
        f305b = C0351c.class.getSimpleName();
        f309f = false;
        f310g = false;
        f311h = false;
        f312i = null;
        f313j = null;
        f314k = null;
        f315l = null;
        f316m = null;
        f317n = null;
        f318o = null;
        f319p = null;
        f320q = true;
        f321r = false;
        f322s = null;
        f323t = null;
        f324u = false;
        f325v = true;
        f326w = false;
        f327x = true;
        f328y = 250.0f;
        f329z = false;
        f299A = true;
        f300B = true;
        f301C = true;
        f302D = true;
        f303E = true;
    }

    private C0351c() {
    }

    private static SharedPreferences m329G() {
        if (f319p == null) {
            f319p = CBUtility.m87a();
        }
        return f319p;
    }

    public static boolean m341a() {
        return f299A;
    }

    public static void m332a(CBFramework cBFramework) {
        if (!C0351c.m373t()) {
            return;
        }
        if (cBFramework == null) {
            CBLogging.m77b(f305b, "Pass a valid CBFramework enum value");
        } else {
            f312i = cBFramework;
        }
    }

    public static void m333a(CBFramework cBFramework, String str) {
        C0351c.m332a(cBFramework);
        f313j = str;
    }

    public static CBFramework m343b() {
        C0351c.m373t();
        return f312i == null ? null : f312i;
    }

    public static String m348c() {
        return String.format("%s %s", new Object[]{f312i, f313j});
    }

    public static void m339a(String str) {
        if (!C0351c.m373t()) {
            return;
        }
        if (f312i == null) {
            CBLogging.m77b(f305b, "Set a valid CBFramework first");
        } else if (TextUtils.isEmpty(str)) {
            CBLogging.m77b(f305b, "Invalid Version String");
        } else {
            f314k = str;
        }
    }

    public static String m351d() {
        C0351c.m373t();
        return f314k;
    }

    public static void m334a(CBMediation cBMediation, String str) {
        if (C0351c.m373t()) {
            f316m = cBMediation;
            f317n = str;
            f315l = f316m + " " + f317n;
        }
    }

    public static String m355e() {
        if (!C0351c.m373t()) {
            return StringUtils.EMPTY;
        }
        f306c = C0351c.m329G().getString("appId", f306c);
        return f306c;
    }

    public static void m344b(String str) {
        f306c = str;
        C0351c.m329G().edit().putString("appId", str).commit();
    }

    public static String m357f() {
        if (!C0351c.m373t()) {
            return StringUtils.EMPTY;
        }
        f307d = C0351c.m329G().getString("appSignature", f307d);
        return f307d;
    }

    public static void m349c(String str) {
        f307d = str;
        C0351c.m329G().edit().putString("appSignature", str).commit();
    }

    public static C0345a m359g() {
        if (C0351c.m373t()) {
            return f308e;
        }
        return null;
    }

    public static void m337a(C0345a c0345a) {
        if (C0351c.m373t()) {
            f308e = c0345a;
        }
    }

    public static boolean m361h() {
        return true;
    }

    public static boolean m362i() {
        if (C0351c.m373t()) {
            return f311h;
        }
        return false;
    }

    public static boolean m363j() {
        return f320q;
    }

    public static void m340a(boolean z) {
        f320q = z;
    }

    public static JSONObject m364k() {
        if (!C0351c.m373t()) {
            return null;
        }
        Object string = C0351c.m329G().getString("trackingLevels", StringUtils.EMPTY);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        C0321a k = C0321a.m123k(string);
        if (k.m134c()) {
            return k.m139e();
        }
        return null;
    }

    public static boolean m365l() {
        C0351c.m373t();
        return C0351c.m329G().getBoolean("retriesEnabled", true);
    }

    public static boolean m366m() {
        if (!C0351c.m373t()) {
            return false;
        }
        JSONObject k = C0351c.m364k();
        if (k == null) {
            return false;
        }
        if (k.optBoolean("debug") || k.optBoolean("session") || k.optBoolean("system") || k.optBoolean("user")) {
            return true;
        }
        return false;
    }

    public static void m335a(Level level) {
        C0351c.m373t();
        CBLogging.f84a = level;
    }

    public static Level m367n() {
        C0351c.m373t();
        return CBLogging.f84a;
    }

    public static String m368o() {
        if (C0351c.m373t()) {
            return f318o;
        }
        return StringUtils.EMPTY;
    }

    public static void m352d(String str) {
        if (C0351c.m373t()) {
            f318o = str;
        }
    }

    public static void m336a(C0321a c0321a) {
        try {
            if (c0321a.m134c()) {
                Map f = c0321a.m141f();
                if (f != null) {
                    Editor edit = C0351c.m329G().edit();
                    for (String str : f.keySet()) {
                        Object obj = f.get(str);
                        if (obj instanceof String) {
                            edit.putString(str, (String) obj);
                        } else if (obj instanceof Integer) {
                            edit.putInt(str, ((Integer) obj).intValue());
                        } else if (obj instanceof Float) {
                            edit.putFloat(str, ((Float) obj).floatValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(str, ((Long) obj).longValue());
                        } else if (obj instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) obj).booleanValue());
                        } else if (obj != null) {
                            edit.putString(str, obj.toString());
                        }
                    }
                    edit.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m338a(C0350a c0350a) {
        az azVar = new az("/api/config");
        azVar.m567a(false);
        azVar.m571b(false);
        azVar.m562a(C0466a.HIGH);
        azVar.m559a(C0328g.m176a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a)));
        azVar.m560a(new C10241(c0350a));
    }

    public static boolean m369p() {
        return f321r;
    }

    protected static void m345b(boolean z) {
        f321r = z;
    }

    public static boolean m370q() {
        if (C0351c.m373t() && C0351c.m372s() && C0351c.m371r()) {
            return true;
        }
        return false;
    }

    public static boolean m371r() {
        if (C0351c.m369p()) {
            return true;
        }
        try {
            throw new Exception("Session not started: Check if Chartboost.onStart() is called, if not the session won't be invoked");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m372s() {
        if (Chartboost.f54b != null) {
            return true;
        }
        try {
            throw new Exception("Chartboost Weak Activity reference is null");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m342a(Activity activity) {
        if (activity != null) {
            return true;
        }
        try {
            throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m373t() {
        try {
            if (C0351c.m379z() == null) {
                throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
            } else if (TextUtils.isEmpty(f306c)) {
                throw new Exception("SDK Initialization error. AppId is missing");
            } else if (!TextUtils.isEmpty(f307d)) {
                return true;
            } else {
                throw new Exception("SDK Initialization error. AppSignature is missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void m350c(boolean z) {
        if (f304a != null) {
            f304a.m380a(z);
        }
    }

    protected static void m353d(boolean z) {
        f324u = z;
    }

    public static boolean m374u() {
        return f324u;
    }

    public static void m356e(boolean z) {
        f325v = z;
    }

    public static boolean m375v() {
        return f325v;
    }

    public static void m358f(boolean z) {
        f326w = z;
    }

    public static boolean m376w() {
        return f326w;
    }

    public static void m360g(boolean z) {
        f327x = z;
    }

    public static boolean m377x() {
        return f327x;
    }

    public static void m331a(Context context) {
        f322s = context;
    }

    public static Context m378y() {
        return f322s;
    }

    public static Application m379z() {
        return f323t;
    }

    public static void m330a(Application application) {
        f323t = application;
    }

    public static boolean m346b(Activity activity) {
        if (activity == null) {
            try {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int checkSelfPermission;
        int checkSelfPermission2;
        int checkSelfPermission3;
        int checkSelfPermission4;
        int checkSelfPermission5;
        if (VERSION.SDK_INT >= 23) {
            checkSelfPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
        } else {
            checkSelfPermission = activity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkCallingOrSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
        }
        if (checkSelfPermission != 0) {
            f299A = true;
        } else {
            f299A = false;
        }
        if (checkSelfPermission3 != 0) {
            f300B = true;
            throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
        }
        f300B = false;
        if (checkSelfPermission2 != 0) {
            f301C = true;
            throw new RuntimeException("Please add the permission :  android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
        }
        f301C = false;
        if (checkSelfPermission4 == 0) {
            f302D = false;
        } else {
            f302D = true;
        }
        if (checkSelfPermission5 == 0) {
            f303E = false;
            return true;
        }
        f303E = true;
        return true;
    }

    public static boolean m347b(Context context) {
        try {
            for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(1)) {
                ActivityInfo[] activityInfoArr = packageInfo.activities;
                if (activityInfoArr != null) {
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        if (activityInfo.name.contains("com.chartboost.sdk.CBImpressionActivity")) {
                            f329z = true;
                        }
                    }
                }
            }
            if (f329z) {
                return true;
            }
            throw new RuntimeException("Please add             <activity android:name=\"com.chartboost.sdk.CBImpressionActivity\"\n                  android:excludeFromRecents=\"true\"\n                  android:theme=\"@android:style/Theme.Translucent.NoTitleBar.Fullscreen\"\n                  android:configChanges=\"keyboardHidden|orientation|screenSize\"/> in your android manifest.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String m323A() {
        if (C0351c.m328F().booleanValue()) {
            return "/webview/v1/interstitial/get";
        }
        return "/interstitial/get";
    }

    public static String m324B() {
        if (C0351c.m328F().booleanValue()) {
            return "/webview/v1/reward/get";
        }
        return "/reward/get";
    }

    public static String m325C() {
        if (C0351c.m328F().booleanValue()) {
            return "/webview/v1/prefetch";
        }
        return "/api/video-prefetch";
    }

    public static int m326D() {
        Float e = C0351c.m354e("cacheTTLs");
        if (e == null || e.floatValue() < 604800.0f) {
            return 7;
        }
        return (int) TimeUnit.SECONDS.toDays(e.longValue());
    }

    public static int m327E() {
        Float e = C0351c.m354e("cacheMaxUnits");
        if (e == null || e.floatValue() <= 0.0f) {
            return 10;
        }
        return e.intValue();
    }

    private static Float m354e(String str) {
        Object string = C0351c.m329G().getString("webview", StringUtils.EMPTY);
        if (!TextUtils.isEmpty(string)) {
            C0321a k = C0321a.m123k(string);
            if (k.m134c() && !k.m127a(str).m131b()) {
                return Float.valueOf(k.m142g(str));
            }
        }
        return null;
    }

    public static Boolean m328F() {
        Object string = C0351c.m329G().getString("webview", StringUtils.EMPTY);
        if (!TextUtils.isEmpty(string)) {
            C0321a k = C0321a.m123k(string);
            if (k.m134c() && k.m127a("enabled").m134c()) {
                if (VERSION.SDK_INT >= 11) {
                    return Boolean.valueOf(k.m149j("enabled"));
                }
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(false);
    }
}
