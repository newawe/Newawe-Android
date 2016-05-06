package com.chartboost.sdk.Libraries;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ba;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.c */
public final class C0318c {
    private static String f95a;
    private static String f96b;
    private static C0317a f97c;
    private static String f98d;

    /* renamed from: com.chartboost.sdk.Libraries.c.1 */
    static class C03161 implements Runnable {
        C03161() {
        }

        public void run() {
            try {
                C0318c.m110b(C0319d.m120a());
                ba.m616b();
            } catch (VerifyError e) {
                C0318c.m116h();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.c.a */
    public enum C0317a {
        PRELOAD(-1),
        LOADING(-1),
        UNKNOWN(-1),
        TRACKING_ENABLED(0),
        TRACKING_DISABLED(1);
        
        private int f94f;

        private C0317a(int i) {
            this.f94f = i;
        }

        public int m104a() {
            return this.f94f;
        }

        public boolean m105b() {
            return this.f94f != -1;
        }
    }

    static {
        f95a = null;
        f96b = null;
        f97c = C0317a.PRELOAD;
        f98d = null;
    }

    private C0318c() {
    }

    public static void m106a() {
        synchronized (C0319d.class) {
            if (C0318c.m111c() != C0317a.PRELOAD) {
                return;
            }
            C0318c.m107a(C0317a.LOADING);
            Class cls = null;
            try {
                cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            } catch (ClassNotFoundException e) {
            }
            if (cls == null) {
                C0318c.m116h();
            } else {
                ax.m541a().execute(new C03161());
            }
        }
    }

    private static void m116h() {
        CBLogging.m77b("CBIdentity", "WARNING: It looks like you've forgotten to include the Google Play Services library in your project. Please review the SDK documentation for more details.");
        C0318c.m107a(C0317a.UNKNOWN);
        ba.m616b();
    }

    public static String m109b() {
        if (f95a == null) {
            f95a = C0318c.m117i();
        }
        return f95a;
    }

    public static synchronized C0317a m111c() {
        C0317a c0317a;
        synchronized (C0318c.class) {
            c0317a = f97c;
        }
        return c0317a;
    }

    protected static synchronized void m107a(C0317a c0317a) {
        synchronized (C0318c.class) {
            f97c = c0317a;
        }
    }

    public static synchronized String m112d() {
        String str;
        synchronized (C0318c.class) {
            str = f96b;
        }
        return str;
    }

    private static synchronized void m110b(String str) {
        synchronized (C0318c.class) {
            f96b = str;
        }
    }

    private static String m117i() {
        Object e = C0318c.m113e();
        if (e == null || "9774d56d682e549c".equals(e)) {
            e = C0318c.m118j();
        }
        String f = C0318c.m114f();
        String d = C0318c.m112d();
        C0321a a = C0321a.m121a();
        a.m128a("uuid", e);
        a.m128a("macid", f);
        a.m128a("gaid", d);
        JSONObject e2 = a.m139e();
        if (e2 == null) {
            e2 = new JSONObject();
        }
        return Base64.encodeToString(e2.toString().getBytes(), 0);
    }

    public static String m113e() {
        return Secure.getString(C0351c.m378y().getContentResolver(), "android_id");
    }

    private static String m118j() {
        if (f98d == null) {
            SharedPreferences a = CBUtility.m87a();
            f98d = a.getString("cbUUID", null);
            if (f98d == null) {
                f98d = UUID.randomUUID().toString();
                Editor edit = a.edit();
                edit.putString("cbUUID", f98d);
                edit.commit();
            }
        }
        return f98d;
    }

    public static String m114f() {
        return C0315b.m103b(C0315b.m102a(C0318c.m119k()));
    }

    private static byte[] m119k() {
        try {
            String macAddress = ((WifiManager) C0351c.m378y().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null || macAddress.equals(StringUtils.EMPTY)) {
                return null;
            }
            String[] split = macAddress.split(":");
            byte[] bArr = new byte[6];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Integer.valueOf(Integer.parseInt(split[i], 16)).byteValue();
            }
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }
}
