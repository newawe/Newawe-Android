package com.inmobi.signals.p007b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import java.util.List;

/* renamed from: com.inmobi.signals.b.c */
public class WifiScanner {
    private static final String f1715a;
    private static Context f1716b;
    private static WifiScanner f1717c;
    private static Handler f1718d;
    private static boolean f1719e;
    private static final IntentFilter f1720f;
    private static List<WifiInfo> f1721g;
    private static Runnable f1722h;
    private static final BroadcastReceiver f1723i;

    /* renamed from: com.inmobi.signals.b.c.1 */
    static class WifiScanner implements Runnable {
        WifiScanner() {
        }

        public void run() {
            WifiScanner b = WifiScanner.f1717c;
            WifiScanner.m1922f();
            if (b != null) {
                b.m1912a();
            }
        }
    }

    /* renamed from: com.inmobi.signals.b.c.2 */
    static class WifiScanner extends BroadcastReceiver {
        WifiScanner() {
        }

        public void onReceive(Context context, Intent intent) {
            WifiScanner b = WifiScanner.f1717c;
            WifiManager wifiManager = (WifiManager) WifiScanner.f1716b.getSystemService("wifi");
            WifiScanner.m1922f();
            if (b != null) {
                WifiScanner.f1721g = WifiInfoUtil.m1903a(wifiManager.getScanResults());
                b.m1913a(WifiScanner.f1721g);
            }
        }
    }

    /* renamed from: com.inmobi.signals.b.c.a */
    public interface WifiScanner {
        void m1912a();

        void m1913a(List<WifiInfo> list);
    }

    static {
        f1715a = WifiScanner.class.getSimpleName();
        f1716b = null;
        f1717c = null;
        f1718d = null;
        f1719e = false;
        f1720f = new IntentFilter("android.net.wifi.SCAN_RESULTS");
        f1722h = new WifiScanner();
        f1723i = new WifiScanner();
    }

    public static boolean m1917a(WifiScanner wifiScanner) {
        f1716b = SdkContext.m1258b();
        return WifiScanner.m1916a(Looper.myLooper(), wifiScanner, 10000, false);
    }

    public static List<WifiInfo> m1914a() {
        return f1721g;
    }

    private static synchronized boolean m1916a(Looper looper, WifiScanner wifiScanner, long j, boolean z) {
        boolean z2;
        synchronized (WifiScanner.class) {
            if (f1718d != null) {
                z2 = false;
            } else {
                WifiManager wifiManager = (WifiManager) SdkContext.m1258b().getSystemService("wifi");
                if (wifiManager.isWifiEnabled()) {
                    f1717c = wifiScanner;
                    f1718d = new Handler(looper);
                    f1718d.postDelayed(f1722h, j);
                    WifiScanner.m1924h();
                    z2 = wifiManager.startScan();
                } else {
                    z2 = false;
                }
            }
        }
        return z2;
    }

    private static synchronized void m1922f() {
        synchronized (WifiScanner.class) {
            if (f1718d != null) {
                f1718d.removeCallbacks(f1722h);
                WifiScanner.m1923g();
                f1718d = null;
                f1717c = null;
                f1716b = null;
            }
        }
    }

    private static void m1923g() {
        if (f1719e) {
            f1719e = false;
            try {
                f1716b.unregisterReceiver(f1723i);
            } catch (IllegalArgumentException e) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1715a, "Failed to register for Wifi scanning.");
            }
        }
    }

    private static void m1924h() {
        if (!f1719e) {
            f1719e = true;
            f1716b.registerReceiver(f1723i, f1720f, null, f1718d);
        }
    }
}
