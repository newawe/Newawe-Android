package com.startapp.android.publish.p022h;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.startapp.android.publish.Ad.AdType;
import com.startapp.android.publish.OverlayActivity;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.NameValueObject;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.h.v */
public class StartAppSDK {
    public static final byte[] f2997a;
    private static Map<Activity, Integer> f2998b;
    private static ThreadPoolExecutor f2999c;
    private static ProgressDialog f3000d;
    private static boolean f3001e;

    /* renamed from: com.startapp.android.publish.h.v.1 */
    static class StartAppSDK implements Runnable {
        final /* synthetic */ Context f2977a;
        final /* synthetic */ String f2978b;
        final /* synthetic */ StartAppSDK f2979c;

        StartAppSDK(Context context, String str, StartAppSDK startAppSDK) {
            this.f2977a = context;
            this.f2978b = str;
            this.f2979c = startAppSDK;
        }

        public void run() {
            try {
                com.startapp.android.publish.p021g.StartAppSDK.m2842a(this.f2977a, this.f2978b + this.f2979c.m2965c() + StartAppSDK.m2889a(StartAppSDK.m3027b(this.f2978b)), null);
            } catch (Throwable e) {
                StartAppSDK.m2927a(6, "Error sending tracking message", e);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.h.v.2 */
    static class StartAppSDK implements Runnable {
        final /* synthetic */ Context f2980a;
        final /* synthetic */ String f2981b;

        StartAppSDK(Context context, String str) {
            this.f2980a = context;
            this.f2981b = str;
        }

        public void run() {
            try {
                com.startapp.android.publish.p021g.StartAppSDK.m2842a(this.f2980a, this.f2981b, null);
            } catch (Throwable e) {
                StartAppSDK.m2927a(6, "Error sending tracking message", e);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.h.v.3 */
    static class StartAppSDK implements OnCancelListener {
        final /* synthetic */ WebView f2982a;

        StartAppSDK(WebView webView) {
            this.f2982a = webView;
        }

        public void onCancel(DialogInterface dialog) {
            this.f2982a.stopLoading();
        }
    }

    /* renamed from: com.startapp.android.publish.h.v.a */
    private static class StartAppSDK extends WebViewClient {
        private String f2984a;
        private String f2985b;
        private boolean f2986c;
        private boolean f2987d;
        private long f2988e;
        private boolean f2989f;
        private String f2990g;
        private ProgressDialog f2991h;
        private Runnable f2992i;
        private boolean f2993j;
        private boolean f2994k;
        private List<String> f2995l;
        private Context f2996m;

        /* renamed from: com.startapp.android.publish.h.v.a.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f2983a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f2983a = startAppSDK;
            }

            public void run() {
                try {
                    Thread.sleep(this.f2983a.f2988e);
                } catch (InterruptedException e) {
                }
                if (!this.f2983a.f2986c) {
                    if (this.f2983a.f2994k) {
                        com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.f2983a.f2996m, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.FAILED_SMART_REDIRECT, "Timeout - Page Finished", this.f2983a.f2984a, this.f2983a.f2985b);
                    } else {
                        com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.f2983a.f2996m, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.FAILED_SMART_REDIRECT, HttpHeaders.TIMEOUT, this.f2983a.f2984a, this.f2983a.f2985b);
                    }
                    this.f2983a.f2993j = true;
                    StartAppSDK.m3035c(this.f2983a.f2996m);
                    if (this.f2983a.f2989f && MetaData.getInstance().isInAppBrowser()) {
                        StartAppSDK.m3030b(this.f2983a.f2996m, this.f2983a.f2984a, this.f2983a.f2985b);
                    } else {
                        StartAppSDK.m2998a(this.f2983a.f2996m, this.f2983a.f2984a, this.f2983a.f2985b);
                    }
                    if (this.f2983a.f2992i != null) {
                        this.f2983a.f2992i.run();
                    }
                }
            }
        }

        public StartAppSDK(Context context, long j, boolean z, ProgressDialog progressDialog, String str, String str2, String str3, Runnable runnable) {
            this.f2984a = StringUtils.EMPTY;
            this.f2986c = false;
            this.f2987d = false;
            this.f2989f = true;
            this.f2993j = false;
            this.f2994k = false;
            this.f2995l = new ArrayList();
            this.f2996m = context;
            this.f2988e = j;
            this.f2989f = z;
            this.f2991h = progressDialog;
            this.f2984a = str;
            this.f2990g = str2;
            this.f2985b = str3;
            this.f2992i = runnable;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            StartAppSDK.m2926a(2, "MyWebViewClientSmartRedirect::onPageStarted - [" + url + "]");
            super.onPageStarted(view, url, favicon);
            if (!this.f2987d) {
                m2975a();
                this.f2987d = true;
            }
            this.f2994k = false;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            StartAppSDK.m2926a(2, "MyWebViewClientSmartRedirect::shouldOverrideUrlLoading - [" + url + "]");
            try {
                this.f2984a = url;
                this.f2995l.add(this.f2984a);
                if (!StartAppSDK.m3050g(url.toLowerCase())) {
                    return false;
                }
                if (this.f2993j) {
                    return true;
                }
                this.f2986c = true;
                StartAppSDK.m3035c(this.f2996m);
                StartAppSDK.m3036c(this.f2996m, url);
                if (this.f2990g != null && !this.f2990g.equals(StringUtils.EMPTY) && !this.f2984a.toLowerCase().contains(this.f2990g.toLowerCase())) {
                    com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.f2996m, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.WRONG_PACKAGE_REACHED, "Wrong package name reached", "Expected: " + this.f2990g + " Link: " + this.f2984a, this.f2985b);
                } else if (Math.random() * 100.0d < ((double) MetaData.getInstance().getAnalyticsConfig().m2714e())) {
                    com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.f2996m, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.SUCCEEDED_SMART_REDIRECT, "Smart redirect succeeded", this.f2995l.toString(), this.f2985b);
                }
                if (this.f2992i == null) {
                    return true;
                }
                this.f2992i.run();
                return true;
            } catch (Exception e) {
                StartAppSDK.m2926a(6, "StartAppWall.UtilExcpetion - view to attached to window - Load Progress");
                return true;
            }
        }

        public void onPageFinished(WebView view, String url) {
            StartAppSDK.m2926a(2, "MyWebViewClientSmartRedirect::onPageFinished - [" + url + "]");
            if (!(this.f2986c || this.f2993j || !this.f2984a.equals(url) || url == null || StartAppSDK.m3050g(url) || (!url.startsWith("http://") && !url.startsWith("https://")))) {
                this.f2994k = true;
            }
            super.onPageFinished(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            StartAppSDK.m2926a(2, "MyWebViewClientSmartRedirect::onReceivedError - [" + description + "], [" + failingUrl + "]");
            if (!(failingUrl == null || StartAppSDK.m3050g(failingUrl) || (!failingUrl.startsWith("http://") && !failingUrl.startsWith("https://")))) {
                com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.f2996m, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.FAILED_SMART_REDIRECT, Integer.toString(errorCode), failingUrl, this.f2985b);
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        private void m2975a() {
            new Thread(new StartAppSDK(this)).start();
        }
    }

    static {
        f2997a = new byte[]{(byte) 12, (byte) 31, (byte) 86, (byte) 96, (byte) 103, (byte) 10, (byte) 28, (byte) 15, (byte) 17, (byte) 28, (byte) 36, (byte) 84, (byte) 64, (byte) 82, (byte) 84, (byte) 64, (byte) 80, (byte) 80, (byte) 69, (byte) 78, (byte) 67, (byte) 82, (byte) 89, (byte) 80, (byte) 84, (byte) 73, (byte) 79, (byte) 78, (byte) 75, (byte) 69, (byte) 89, (byte) 4, (byte) 32, (byte) 18, (byte) 16, (byte) 18, (byte) 11, (byte) 53, (byte) 45, (byte) 34};
        f2998b = new WeakHashMap();
        f2999c = new ThreadPoolExecutor(1, 4, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        f3001e = false;
    }

    public static String m2989a() {
        String str = "3.2.2";
        if (str.equals("${project.version}")) {
            str = SchemaSymbols.ATTVAL_FALSE_0;
        }
        str = str + StartAppSDK.m3045f();
        StartAppSDK.m2926a(3, "SDK version: [" + str + "]");
        return str;
    }

    private static String m3045f() {
        if (StartAppSDK.m3031b()) {
            return "_Unity";
        }
        if (StartAppSDK.m3054i()) {
            return "_Cordova";
        }
        if (StartAppSDK.m3049g()) {
            return "_AdMob";
        }
        if (StartAppSDK.m3051h()) {
            return "_MoPub";
        }
        if (StartAppSDK.m3056j()) {
            return "_B4A";
        }
        return StringUtils.EMPTY;
    }

    private static boolean m3049g() {
        return StartAppSDK.m3044e("com.startapp.android.mediation.admob.StartAppCustomEvent");
    }

    private static boolean m3051h() {
        return StartAppSDK.m3044e("com.mopub.mobileads.StartAppCustomEventInterstitial");
    }

    public static boolean m3031b() {
        return com.startapp.android.publish.StartAppSDK.m2799a().m2826h() != null;
    }

    private static boolean m3054i() {
        return StartAppSDK.m3044e("org.apache.cordova.CordovaPlugin");
    }

    private static boolean m3056j() {
        return StartAppSDK.m3044e("anywheresoftware.b4a.BA");
    }

    private static boolean m3044e(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean m3017a(Activity activity) {
        boolean z = activity.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        if ((activity.getWindow().getAttributes().flags & NodeFilter.SHOW_DOCUMENT_FRAGMENT) != 0) {
            return true;
        }
        return z;
    }

    public static String m2990a(Context context, String str) {
        try {
            return context.getResources().getString(context.getApplicationInfo().labelRes);
        } catch (NotFoundException e) {
            CharSequence applicationLabel;
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
            } catch (NameNotFoundException e2) {
            }
            if (applicationInfo != null) {
                applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            } else {
                Object obj = str;
            }
            return (String) applicationLabel;
        }
    }

    public static boolean m3018a(Context context) {
        if (com.startapp.android.publish.StartAppSDK.OVERRIDE_HOST != null || com.startapp.android.publish.StartAppSDK.OVERRIDE_NETWORK.booleanValue()) {
            return true;
        }
        if (StartAppSDK.m2877a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static void m3006a(Editor editor) {
        StartAppSDK.m2864a(editor);
    }

    public static String m2992a(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(str3, str2.length() + indexOf);
        if (indexOf2 != -1) {
            return str.substring(str2.length() + indexOf, indexOf2);
        }
        return null;
    }

    public static void m3013a(List<NameValueObject> list, String str, Object obj, boolean z) {
        StartAppSDK.m3014a((List) list, str, obj, z, true);
    }

    public static void m3014a(List<NameValueObject> list, String str, Object obj, boolean z, boolean z2) {
        if (z && obj == null) {
            throw new StartAppSDK("Required key: [" + str + "] is missing", null);
        } else if (obj != null && !obj.toString().equals(StringUtils.EMPTY)) {
            try {
                NameValueObject nameValueObject = new NameValueObject();
                nameValueObject.setName(str);
                String obj2 = obj.toString();
                if (z2) {
                    obj2 = URLEncoder.encode(obj2, HTTP.UTF_8);
                }
                nameValueObject.setValue(obj2);
                list.add(nameValueObject);
            } catch (Throwable e) {
                if (z) {
                    throw new StartAppSDK("failed encoding value: [" + obj + "]", e);
                }
            }
        }
    }

    public static void m3015a(List<NameValueObject> list, String str, Set<String> set, boolean z) {
        if (z && set == null) {
            throw new StartAppSDK("Required key: [" + str + "] is missing", null);
        } else if (set != null) {
            NameValueObject nameValueObject = new NameValueObject();
            nameValueObject.setName(str);
            Set hashSet = new HashSet();
            for (String encode : set) {
                try {
                    hashSet.add(URLEncoder.encode(encode, HTTP.UTF_8));
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (z && hashSet.size() == 0) {
                throw new StartAppSDK("failed encoding value: [" + set + "]", null);
            }
            nameValueObject.setValueSet(hashSet);
            list.add(nameValueObject);
        }
    }

    public static String m3025b(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return "landscape";
        }
        if (context.getResources().getConfiguration().orientation == 1) {
            return "portrait";
        }
        return "undefined";
    }

    public static int m2984a(Activity activity, int i, boolean z) {
        if (z) {
            if (!f2998b.containsKey(activity)) {
                f2998b.put(activity, Integer.valueOf(activity.getRequestedOrientation()));
            }
            if (i == activity.getResources().getConfiguration().orientation) {
                return StartAppSDK.m2857a(activity, i, false);
            }
            return StartAppSDK.m2857a(activity, i, true);
        } else if (!f2998b.containsKey(activity)) {
            return -1;
        } else {
            int intValue = ((Integer) f2998b.get(activity)).intValue();
            activity.setRequestedOrientation(intValue);
            f2998b.remove(activity);
            return intValue;
        }
    }

    public static void m2994a(Activity activity, boolean z) {
        StartAppSDK.m2984a(activity, activity.getResources().getConfiguration().orientation, z);
    }

    public static int m2986a(String str) {
        String[] split = str.split("&");
        return Integer.parseInt(split[split.length - 1].split("=")[1]);
    }

    public static void m3016a(List<com.startapp.android.publish.StartAppSDK> list, List<String> list2) {
        StartAppSDK.m2926a(3, "in getAppPresenceDParameter()");
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (com.startapp.android.publish.StartAppSDK startAppSDK : list) {
            if (!startAppSDK.m2707c()) {
                String f = StartAppSDK.m3047f(startAppSDK.m2702a());
                if (startAppSDK.m2708d()) {
                    arrayList.add("d=" + f);
                } else {
                    arrayList2.add("d=" + f);
                }
            }
        }
        StartAppSDK.m2926a(3, "appPresence tracking size = " + arrayList.size() + " normal size = " + arrayList2.size());
        if (!arrayList.isEmpty()) {
            list2.addAll(StartAppSDK.m2993a(arrayList, SchemaSymbols.ATTVAL_FALSE, SchemaSymbols.ATTVAL_TRUE));
        }
        if (!arrayList2.isEmpty()) {
            list2.addAll(StartAppSDK.m2993a(arrayList2, SchemaSymbols.ATTVAL_FALSE, SchemaSymbols.ATTVAL_FALSE));
        }
    }

    private static String m3047f(String str) {
        return str.split("tracking/adImpression[?]d=")[1];
    }

    private static List<String> m2993a(List<String> list, String str, String str2) {
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i += 5) {
            arrayList.add(com.startapp.android.publish.StartAppSDK.f2830f + "?" + TextUtils.join("&", list.subList(i, Math.min(i + 5, list.size()))) + "&isShown=" + str + (str2 != null ? "&appPresence=" + str2 : StringUtils.EMPTY));
        }
        StartAppSDK.m2926a(3, "newUrlList size = " + arrayList.size());
        return arrayList;
    }

    public static void m3005a(Context context, String[] strArr, String str, String str2) {
        StartAppSDK.m3004a(context, strArr, str, 0, str2);
    }

    public static void m3004a(Context context, String[] strArr, String str, int i, String str2) {
        StartAppSDK.m3003a(context, strArr, new StartAppSDK(str).m2962a(i).m2963a(str2));
    }

    public static void m2997a(Context context, String str, StartAppSDK startAppSDK) {
        if (str != null && !str.equalsIgnoreCase(StringUtils.EMPTY)) {
            StartAppSDK.m2926a(3, "Sending Impression: [" + str + "]");
            StartAppSDK.m3037c(context, str, startAppSDK);
        }
    }

    public static void m3003a(Context context, String[] strArr, StartAppSDK startAppSDK) {
        if (strArr != null) {
            for (String a : strArr) {
                StartAppSDK.m2997a(context, a, startAppSDK);
            }
        }
    }

    public static final void m2999a(Context context, String str, String str2, StartAppSDK startAppSDK, boolean z) {
        if (!(str2 == null || str2.equals(StringUtils.EMPTY))) {
            StartAppSDK.m3029b(context, str2, startAppSDK);
        }
        com.startapp.android.publish.StartAppSDK.m2799a().m2815b();
        String a = StartAppSDK.m2991a(str, str2);
        String str3 = "InAppBrowser";
        try {
            if (MetaData.getInstance().isInAppBrowser() && z) {
                StartAppSDK.m3030b(context, str, a);
                return;
            }
            str3 = "externalBrowser";
            StartAppSDK.m3036c(context, str);
        } catch (Exception e) {
            com.startapp.android.publish.p010d.StartAppSDK.m2729a(context, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.EXCEPTION, "Util.clickWithoutSmartRedirect(): Couldn't start activity for " + str3, e.getMessage(), a);
            StartAppSDK.m2926a(6, "Cannot start activity to handle url: [" + str + "]");
        }
    }

    public static final void m3001a(Context context, String str, String str2, String str3, StartAppSDK startAppSDK, long j, boolean z) {
        StartAppSDK.m3002a(context, str, str2, str3, startAppSDK, j, z, null);
    }

    public static final void m3002a(Context context, String str, String str2, String str3, StartAppSDK startAppSDK, long j, boolean z, Runnable runnable) {
        com.startapp.android.publish.StartAppSDK.m2799a().m2815b();
        String a = StartAppSDK.m2991a(str, str2);
        if (!(str2 == null || str2.equals(StringUtils.EMPTY))) {
            StartAppSDK.m3029b(context, str2, startAppSDK);
        }
        StartAppSDK.m3000a(context, str + (MetaData.getInstance().isDisableTwoClicks() ? StartAppSDK.m2889a(a) : StringUtils.EMPTY), str3, a, j, z, runnable);
    }

    public static void m3029b(Context context, String str, StartAppSDK startAppSDK) {
        StartAppSDK.m3037c(context, str, startAppSDK);
    }

    private static void m3037c(Context context, String str, StartAppSDK startAppSDK) {
        if (!str.equals(StringUtils.EMPTY)) {
            f2999c.execute(new StartAppSDK(context, str, startAppSDK));
        }
    }

    public static void m3028b(Context context, String str) {
        f2999c.execute(new StartAppSDK(context, str));
    }

    private static final void m3000a(Context context, String str, String str2, String str3, long j, boolean z, Runnable runnable) {
        StartAppSDK.m2915a(context).m2920a(new Intent("com.startapp.android.OnClickCallback"));
        if (StartAppSDK.m3050g(str)) {
            if (!(str2 == null || str2.equals(StringUtils.EMPTY) || str.toLowerCase().contains(str2.toLowerCase()))) {
                com.startapp.android.publish.p010d.StartAppSDK.m2729a(context, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.WRONG_PACKAGE_REACHED, "Wrong package name reached", "Expected: " + str2 + " Link: " + str, str3);
            }
            StartAppSDK.m2998a(context, str, str3);
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        if (context instanceof Activity) {
            StartAppSDK.m2994a((Activity) context, true);
        }
        WebView webView = new WebView(context);
        if (f3000d == null && (context instanceof Activity) && !((Activity) context).isFinishing()) {
            f3000d = ProgressDialog.show(context, null, "Loading....", false, false, new StartAppSDK(webView));
            f3000d.setCancelable(false);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new StartAppSDK(context, j, z, f3000d, str, str2, str3, runnable));
        webView.loadUrl(str);
    }

    private static boolean m3050g(String str) {
        return str.startsWith("market") || str.startsWith("http://play.google.com") || str.startsWith("https://play.google.com");
    }

    public static final void m3035c(Context context) {
        if (context != null && (context instanceof Activity)) {
            StartAppSDK.m2994a((Activity) context, false);
        }
        StartAppSDK.m3034c();
    }

    public static final void m3034c() {
        StartAppSDK.m3057k();
    }

    private static void m3057k() {
        if (f3000d != null) {
            synchronized (f3000d) {
                if (f3000d != null && f3000d.isShowing()) {
                    try {
                        f3000d.cancel();
                    } catch (Throwable e) {
                        StartAppSDK.m2927a(6, "Error while cancelling progress", e);
                    }
                    f3000d = null;
                }
            }
        }
    }

    public static void m3036c(Context context, String str) {
        StartAppSDK.m2998a(context, str, null);
    }

    public static void m2998a(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(76021760);
        if (MetaData.getInstance().isDisableInAppStore() || !(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        StartAppSDK.m2995a(context, intent);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            com.startapp.android.publish.p010d.StartAppSDK.m2729a(context, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.EXCEPTION, "Util.openUrlExternally(): Couldn't start activity", e.getMessage(), str2);
            StartAppSDK.m2926a(6, "Cannot find activity to handle url: [" + str + "]");
        }
    }

    public static void m3030b(Context context, String str, String str2) {
        if (StartAppSDK.m3050g(str)) {
            StartAppSDK.m2998a(context, str, str2);
        }
        Intent intent = new Intent(context, OverlayActivity.class);
        intent.addFlags(AccessibilityNodeInfoCompat.ACTION_COLLAPSE);
        intent.setData(Uri.parse(str));
        intent.putExtra("placement", Placement.INAPP_BROWSER);
        intent.putExtra("activityShouldLockOrientation", false);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            com.startapp.android.publish.p010d.StartAppSDK.m2729a(context, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.EXCEPTION, "Util.OpenAsInAppBrowser(): Couldn't start activity", e.getMessage(), str2);
            StartAppSDK.m2926a(6, "Cannot find activity to handle url: [" + str + "]");
        }
    }

    public static void m2996a(Context context, AdPreferences adPreferences) {
        String a = StartAppSDK.m2907a(context, "shared_prefs_devId", null);
        String a2 = StartAppSDK.m2907a(context, "shared_prefs_appId", null);
        if (adPreferences.getPublisherId() == null) {
            adPreferences.setPublisherId(a);
        }
        if (adPreferences.getProductId() == null) {
            if (a2 == null) {
                adPreferences.setProductId(a2);
            } else {
                adPreferences.setProductId(a2);
            }
        }
        if (adPreferences.getProductId() == null && !f3001e) {
            f3001e = true;
            Log.e("StartApp", "Integration Error - App ID is missing");
        }
    }

    public static void m3038c(Context context, String str, String str2) {
        if (str != null) {
            StartAppSDK.m2912b(context, "shared_prefs_devId", str.trim());
        } else {
            StartAppSDK.m2912b(context, "shared_prefs_devId", null);
        }
        StartAppSDK.m2912b(context, "shared_prefs_appId", str2.trim());
    }

    public static String m3039d() {
        return "&position=" + StartAppSDK.m3042e();
    }

    public static String m3042e() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i = 0;
        while (i < 8) {
            if (stackTrace[i].getMethodName().compareTo("doHome") == 0) {
                return "home";
            }
            if (stackTrace[i].getMethodName().compareTo("onBackPressed") != 0) {
                i++;
            } else if (!com.startapp.android.publish.StartAppSDK.m2799a().m2823e() && !StartAppSDK.m3031b()) {
                return "interstitial";
            } else {
                com.startapp.android.publish.StartAppSDK.m2799a().m2825g();
                return "back";
            }
        }
        return "interstitial";
    }

    public static void m3007a(WebView webView, String str) {
        try {
            webView.loadDataWithBaseURL("http://www.startappexchange.com", str, "text/html", "utf-8", null);
        } catch (Exception e) {
            StartAppSDK.m2926a(6, "StartAppWall.UtilError while encoding html");
        }
    }

    public static void m3040d(Context context) {
        StartAppSDK.m2912b(context, "shared_prefs_simple_token", StartAppSDK.m2949a(context));
    }

    public static String m3043e(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        if (MetaData.getInstance().getAdInformationConfig().m2527e().m2585a(context)) {
            stringBuffer.append(StartAppSDK.m2907a(context, "shared_prefs_simple_token", StringUtils.EMPTY));
        }
        return stringBuffer.toString();
    }

    public static void m3012a(String str, String str2, String str3, Context context, StartAppSDK startAppSDK) {
        StartAppSDK.m3037c(context, str3, startAppSDK);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    launchIntentForPackage.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (Throwable e) {
                StartAppSDK.m2927a(6, "Couldn't parse intent details json!", e);
            }
        }
        try {
            context.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            com.startapp.android.publish.p010d.StartAppSDK.m2729a(context, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.EXCEPTION, "Util.handleCPEClick(): Couldn't start activity", e2.getMessage(), StartAppSDK.m2991a(str3, null));
            StartAppSDK.m2926a(6, "Cannot find activity to handle url: [" + str3 + "]");
        }
    }

    public static int m2985a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static String m3046f(Context context) {
        String str = StringUtils.EMPTY;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return str;
        }
        str = resolveActivity.activityInfo.packageName;
        if (str != null) {
            return str.toLowerCase();
        }
        return str;
    }

    public static void m2995a(Context context, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(com.startapp.android.publish.StartAppSDK.f2832h)) {
                intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            }
        }
    }

    public static boolean m3020a(AdPreferences adPreferences, String str) {
        return ((Boolean) StartAppSDK.m2988a(adPreferences.getClass(), str, (Object) adPreferences)).booleanValue();
    }

    public static String m3026b(AdPreferences adPreferences, String str) {
        return (String) StartAppSDK.m2988a(adPreferences.getClass(), str, (Object) adPreferences);
    }

    public static AdType m3032c(AdPreferences adPreferences, String str) {
        return (AdType) StartAppSDK.m2988a(adPreferences.getClass(), str, (Object) adPreferences);
    }

    public static void m3010a(AdPreferences adPreferences, String str, boolean z) {
        StartAppSDK.m3011a(adPreferences.getClass(), str, (Object) adPreferences, Boolean.valueOf(z));
    }

    public static void m3009a(AdPreferences adPreferences, String str, AdType adType) {
        StartAppSDK.m3011a(adPreferences.getClass(), str, (Object) adPreferences, (Object) adType);
    }

    private static Object m2988a(Class cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static void m3011a(Class cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public static String m3027b(String str) {
        return StartAppSDK.m2991a(str, null);
    }

    public static String m2991a(String str, String str2) {
        String str3;
        if (str2 == null || str2.equals(StringUtils.EMPTY)) {
            str3 = str.split("[?&]d=")[1];
        } else {
            str3 = str2.split("[?&]d=")[1];
        }
        return str3.split("[?&]")[0];
    }

    public static String[] m3023a(com.startapp.android.publish.StartAppSDK startAppSDK) {
        if (startAppSDK instanceof com.startapp.android.publish.p028a.StartAppSDK) {
            return ((com.startapp.android.publish.p028a.StartAppSDK) startAppSDK).getTrackingUrls();
        }
        if (startAppSDK instanceof com.startapp.android.publish.p028a.StartAppSDK) {
            return StartAppSDK.m3024a(((com.startapp.android.publish.p028a.StartAppSDK) startAppSDK).m4751b());
        }
        return new String[0];
    }

    public static String[] m3024a(List<AdDetails> list) {
        List arrayList = new ArrayList();
        if (list != null) {
            for (AdDetails trackingUrl : list) {
                arrayList.add(trackingUrl.getTrackingUrl());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static String m3048g(Context context) {
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    public static void m3008a(WebView webView, String str, Object... objArr) {
        if (webView != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("(");
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    if (objArr[i] instanceof String) {
                        stringBuilder.append("\"").append(objArr[i]).append("\"");
                    } else {
                        stringBuilder.append(objArr[i]);
                    }
                    if (i < objArr.length - 1) {
                        stringBuilder.append(",");
                    }
                }
            }
            stringBuilder.append(")");
            StartAppSDK.m2928a("StartAppWall.Util", 3, "runJavascript: " + stringBuilder.toString());
            webView.loadUrl("javascript:" + stringBuilder.toString());
        }
    }

    public static Class<?> m2987a(Context context, Class<? extends Activity> cls, Class<? extends Activity> cls2) {
        if (StartAppSDK.m3019a(context, (Class) cls) || !StartAppSDK.m3019a(context, (Class) cls2)) {
            return cls;
        }
        Log.w("StartAppWall.Util", "Expected activity " + cls.getName() + " is missing from AndroidManifest.xml");
        return cls2;
    }

    public static boolean m3019a(Context context, Class<? extends Activity> cls) {
        try {
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                if (activityInfo.name.equals(cls.getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean m3052h(Context context) {
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            int i = 0;
            boolean z = false;
            while (!z) {
                try {
                    if (i >= activityInfoArr.length) {
                        return z;
                    }
                    int i2 = i + 1;
                    ActivityInfo activityInfo = activityInfoArr[i];
                    if (activityInfo.name.equals("com.startapp.android.publish.AppWallActivity") || activityInfo.name.equals("com.startapp.android.publish.OverlayActivity") || activityInfo.name.equals("com.startapp.android.publish.FullScreenActivity")) {
                        if ((activityInfo.flags & XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE) == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                    i = i2;
                } catch (NameNotFoundException e) {
                    return z;
                }
            }
            return z;
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    public static String m3053i(Context context) {
        String str = null;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return str;
        }
    }

    public static int m3055j(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public static String m3033c(String str) {
        int hashCode = f2997a.hashCode();
        long hashCode2 = (long) str.getBytes().hashCode();
        if (((long) hashCode) > hashCode2) {
            hashCode2 = ((hashCode2 * 29509871405L) + 11) & 16777215;
            int i = (int) (hashCode2 >>> 17);
            if (hashCode >= DateUtils.MILLIS_IN_SECOND) {
                hashCode = i % hashCode;
            } else if (((long) ((-hashCode) & hashCode)) == hashCode2) {
                hashCode = (int) ((((long) i) * ((long) hashCode)) >> 31);
            } else {
                hashCode = (int) (((((long) i) * ((long) hashCode)) * 2) >> 32);
            }
        }
        try {
            str = StartAppSDK.m2891a(StartAppSDK.m3022a(StartAppSDK.m3022a(str.getBytes(), new String(f2997a).substring(f2997a[5], f2997a[33]).getBytes()), new String(f2997a).substring(f2997a[35], f2997a[1]).getBytes()), 0);
        } catch (Exception e) {
        }
        return str;
    }

    public static byte[] m3022a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    public static byte[] m3021a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[Math.min(bArr.length, i)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 % i;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i2]);
        }
        return bArr2;
    }
}
