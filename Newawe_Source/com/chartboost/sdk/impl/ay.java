package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Observable;

public class ay extends Observable {
    private static ay f474c;
    private static C0398b f475d;
    private boolean f476a;
    private boolean f477b;
    private C0397a f478e;

    /* renamed from: com.chartboost.sdk.impl.ay.a */
    private class C0397a extends BroadcastReceiver {
        final /* synthetic */ ay f467a;

        public C0397a(ay ayVar) {
            this.f467a = ayVar;
        }

        public void onReceive(Context context, Intent intent) {
            ay a = ay.m543a();
            a.m545a(context);
            a.notifyObservers();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ay.b */
    public enum C0398b {
        CONNECTION_UNKNOWN(-1),
        CONNECTION_ERROR(0),
        CONNECTION_WIFI(1),
        CONNECTION_MOBILE(2);
        
        private int f473e;

        private C0398b(int i) {
            this.f473e = i;
        }

        public int m542a() {
            return this.f473e;
        }
    }

    static {
        f474c = null;
        f475d = C0398b.CONNECTION_UNKNOWN;
    }

    private ay() {
        this.f476a = true;
        this.f477b = false;
        this.f478e = null;
        this.f478e = new C0397a(this);
    }

    public static ay m543a() {
        if (f474c == null) {
            f474c = new ay();
        }
        return f474c;
    }

    public int m547b() {
        return f475d.m542a();
    }

    public void m545a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                m546a(false);
                f475d = C0398b.CONNECTION_ERROR;
                CBLogging.m75a("CBReachability", "NETWORK TYPE: NO Network");
                return;
            }
            m546a(true);
            if (activeNetworkInfo.getType() == 1) {
                f475d = C0398b.CONNECTION_WIFI;
                CBLogging.m75a("CBReachability", "NETWORK TYPE: TYPE_WIFI");
            } else if (activeNetworkInfo.getType() == 0) {
                f475d = C0398b.CONNECTION_MOBILE;
                CBLogging.m75a("CBReachability", "NETWORK TYPE: TYPE_MOBILE");
            }
        } catch (SecurityException e) {
            f475d = C0398b.CONNECTION_UNKNOWN;
            CBLogging.m77b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
    }

    public void notifyObservers() {
        if (this.f476a) {
            setChanged();
            super.notifyObservers(this);
        }
    }

    public void m546a(boolean z) {
        this.f476a = z;
    }

    public boolean m551c() {
        return this.f476a;
    }

    public Intent m548b(Context context) {
        if (context == null || this.f477b) {
            return null;
        }
        m549b(true);
        CBLogging.m75a("CBReachability", "Network broadcast successfully registered");
        return context.registerReceiver(this.f478e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void m550c(Context context) {
        if (context != null && this.f477b) {
            context.unregisterReceiver(this.f478e);
            m549b(false);
            CBLogging.m75a("CBReachability", "Network broadcast successfully unregistered");
        }
    }

    public void m549b(boolean z) {
        this.f477b = z;
    }

    public static Integer m544d() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C0351c.m378y().getSystemService("connectivity")).getActiveNetworkInfo();
            Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? null : 1;
            if (obj != null) {
                TelephonyManager telephonyManager = (TelephonyManager) C0351c.m378y().getSystemService("phone");
                if (telephonyManager != null) {
                    return Integer.valueOf(telephonyManager.getNetworkType());
                }
            }
        } catch (SecurityException e) {
            CBLogging.m77b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
        return null;
    }
}
