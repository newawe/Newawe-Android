package com.inmobi.commons.p000a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.inmobi.commons.core.utilities.FileUtils;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.a.a */
public final class SdkContext {
    private static final String f1145a;
    private static Context f1146b;
    private static String f1147c;
    private static String f1148d;
    private static AtomicBoolean f1149e;

    /* renamed from: com.inmobi.commons.a.a.1 */
    static class SdkContext implements Runnable {
        final /* synthetic */ Context f1144a;

        SdkContext(Context context) {
            this.f1144a = context;
        }

        public void run() {
            SdkContext.f1147c = new WebView(this.f1144a).getSettings().getUserAgentString();
        }
    }

    static {
        f1145a = SdkContext.class.getSimpleName();
        f1147c = StringUtils.EMPTY;
        f1148d = StringUtils.EMPTY;
        f1149e = new AtomicBoolean();
    }

    public static void m1254a(Context context, String str) {
        if (!SdkContext.m1257a()) {
            f1146b = context.getApplicationContext();
            f1148d = str;
            f1149e.set(true);
            SdkContext.m1259b(context);
            SdkContext.m1261c(context);
        }
    }

    public static boolean m1257a() {
        return f1146b != null;
    }

    public static Context m1258b() {
        return f1146b;
    }

    public static String m1260c() {
        return f1148d;
    }

    public static String m1262d() {
        return f1147c;
    }

    public static boolean m1263e() {
        return f1149e.get();
    }

    public static void m1256a(boolean z) {
        f1149e.set(z);
    }

    public static File m1252a(Context context) {
        return new File(context.getCacheDir(), "im_cached_content");
    }

    public static void m1255a(File file, String str) {
        if (str == null || str.trim().length() == 0) {
            FileUtils.m1469a(file);
        } else {
            FileUtils.m1469a(new File(file, str));
        }
    }

    private static void m1259b(Context context) {
        if (VERSION.SDK_INT >= 17) {
            f1147c = SdkContext.m1264f();
        } else {
            new Handler(context.getMainLooper()).post(new SdkContext(context));
        }
    }

    private static void m1261c(Context context) {
        File a = SdkContext.m1252a(context);
        SdkContext.m1255a(a, null);
        if (!a.mkdir() && !a.isDirectory()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1145a, "Cannot create media cache directory");
        }
    }

    @TargetApi(17)
    private static String m1264f() {
        return WebSettings.getDefaultUserAgent(SdkContext.m1258b());
    }
}
