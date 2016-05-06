package com.inmobi.commons.core.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.inmobi.commons.core.utilities.c */
public class NetworkConnectivityChangeObserver {
    private static final String f1272a;
    private static List<NetworkConnectivityChangeObserver> f1273b;
    private static final Object f1274c;
    private static volatile NetworkConnectivityChangeObserver f1275d;
    private static NetworkConnectivityChangeObserver f1276e;

    /* renamed from: com.inmobi.commons.core.utilities.c.a */
    public interface NetworkConnectivityChangeObserver {
        void m1470a(boolean z);
    }

    /* renamed from: com.inmobi.commons.core.utilities.c.b */
    static final class NetworkConnectivityChangeObserver extends BroadcastReceiver {
        private static final String f1271a;

        NetworkConnectivityChangeObserver() {
        }

        static {
            f1271a = NetworkConnectivityChangeObserver.class.getSimpleName();
        }

        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (intent != null && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        z = true;
                        NetworkConnectivityChangeObserver.m1474b(z);
                        Logger.m1440a(InternalLogLevel.INTERNAL, f1271a, "Network connectivity changed. Is network available:" + z);
                    }
                }
            }
            z = false;
            NetworkConnectivityChangeObserver.m1474b(z);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1271a, "Network connectivity changed. Is network available:" + z);
        }
    }

    static {
        f1272a = NetworkConnectivityChangeObserver.class.getSimpleName();
        f1273b = new ArrayList();
        f1274c = new Object();
    }

    public static NetworkConnectivityChangeObserver m1471a() {
        NetworkConnectivityChangeObserver networkConnectivityChangeObserver = f1275d;
        if (networkConnectivityChangeObserver == null) {
            synchronized (f1274c) {
                networkConnectivityChangeObserver = f1275d;
                if (networkConnectivityChangeObserver == null) {
                    networkConnectivityChangeObserver = new NetworkConnectivityChangeObserver();
                    f1275d = networkConnectivityChangeObserver;
                }
            }
        }
        return networkConnectivityChangeObserver;
    }

    public void m1475a(NetworkConnectivityChangeObserver networkConnectivityChangeObserver) {
        f1273b.add(networkConnectivityChangeObserver);
        if (f1273b.size() == 1) {
            m1473b();
        }
    }

    private static void m1474b(boolean z) {
        for (NetworkConnectivityChangeObserver a : f1273b) {
            a.m1470a(z);
        }
    }

    private void m1473b() {
        f1276e = new NetworkConnectivityChangeObserver();
        SdkContext.m1258b().registerReceiver(f1276e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
