package com.inmobi.signals;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.SignalsConfig.SignalsConfig;
import com.inmobi.signals.activityrecognition.ActivityRecognitionSampler;
import com.inmobi.signals.p006a.CellularInfoUtil;
import com.inmobi.signals.p007b.WifiInfo;
import com.inmobi.signals.p007b.WifiInfoUtil;
import com.inmobi.signals.p007b.WifiScanner.WifiScanner;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.signals.i */
class IceCollector {
    private static final String f1741a;
    private HandlerThread f1742b;
    private IceCollector f1743c;

    /* renamed from: com.inmobi.signals.i.a */
    static class IceCollector extends Handler {
        private List<IceWifiSample> f1740a;

        /* renamed from: com.inmobi.signals.i.a.1 */
        class IceCollector implements WifiScanner {
            final /* synthetic */ IceWifiSample f3891a;
            final /* synthetic */ IceCollector f3892b;

            IceCollector(IceCollector iceCollector, IceWifiSample iceWifiSample) {
                this.f3892b = iceCollector;
                this.f3891a = iceWifiSample;
            }

            public void m4576a() {
                Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "Wifi scan timeout.");
                this.f3892b.m1953a(this.f3891a);
            }

            public void m4577a(List<WifiInfo> list) {
                Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "Wifi scan successful.");
                this.f3891a.m1976a((List) list);
                this.f3892b.m1953a(this.f3891a);
            }
        }

        IceCollector(Looper looper) {
            super(looper);
            this.f1740a = new ArrayList();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    m1955b();
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    m1956c();
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "Polling for samples.");
                    if (VERSION.SDK_INT >= 14 || IceCollector.m1954a()) {
                        if (SignalsComponent.m4580a().m4585e().m2077q()) {
                            ActivityRecognitionSampler.m1882a().m1887b();
                        } else {
                            ActivityRecognitionSampler.m1882a().m1888c();
                        }
                        m1957d();
                        sendEmptyMessageDelayed(3, (long) (SignalsComponent.m4580a().m4585e().m2062b() * DateUtils.MILLIS_IN_SECOND));
                        return;
                    }
                    sendEmptyMessage(2);
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    m1952a(m1958e());
                    m1959f();
                default:
            }
        }

        private void m1955b() {
            Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "User data collection started.");
            LocationInfo.m1981a().m1992b();
            sendEmptyMessage(3);
        }

        private void m1956c() {
            Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "Stopping user data collection.");
            ActivityRecognitionSampler.m1882a().m1888c();
            removeMessages(3);
            sendEmptyMessage(4);
        }

        private void m1957d() {
            IceWifiSample iceWifiSample = new IceWifiSample();
            iceWifiSample.m1975a(WifiInfoUtil.m1900a());
            if (!SignalsComponent.m4580a().m4585e().m2071k() || !WifiInfoUtil.m1909c()) {
                m1953a(iceWifiSample);
            } else if (!com.inmobi.signals.p007b.WifiScanner.m1917a(new IceCollector(this, iceWifiSample))) {
                m1953a(iceWifiSample);
            }
        }

        private void m1953a(IceWifiSample iceWifiSample) {
            if (this.f1740a != null && iceWifiSample.m1977a()) {
                this.f1740a.add(iceWifiSample);
                if (this.f1740a.size() > SignalsComponent.m4580a().m4585e().m2064d()) {
                    TelemetryComponent.m4448a().m4468a(new TelemetryEvent("signals", "SampleSizeExceeded"));
                    while (this.f1740a.size() > SignalsComponent.m4580a().m4585e().m2064d()) {
                        this.f1740a.remove(0);
                    }
                }
            }
        }

        private IceSample m1958e() {
            IceSample iceSample = new IceSample();
            iceSample.m1970a(CellularInfoUtil.m1857a());
            iceSample.m1972a(LocationInfo.m1981a().m1993c());
            iceSample.m1971a(this.f1740a);
            iceSample.m1969a(SessionManager.m1995a().m1998d());
            iceSample.m1973b(ActivityRecognitionSampler.m1882a().m1889d());
            return iceSample;
        }

        private void m1959f() {
            ActivityRecognitionSampler.m1882a().m1890e();
            this.f1740a = new ArrayList();
        }

        private void m1952a(IceSample iceSample) {
            SignalsConfig e = SignalsComponent.m4580a().m4585e();
            new IceNetworkClient(new IceNetworkRequest(e.m2065e(), e.m2066f(), e.m2067g(), SignalsComponent.m4580a().m4584d(), iceSample)).m1966a();
        }

        public static boolean m1954a() {
            ActivityManager activityManager = (ActivityManager) SdkContext.m1258b().getSystemService("activity");
            if (activityManager != null) {
                try {
                    if (((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName().equalsIgnoreCase(SdkContext.m1258b().getPackageName())) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "Is app in foreground check for below ICS: true");
                        return true;
                    }
                } catch (Throwable e) {
                    Logger.m1441a(InternalLogLevel.INTERNAL, IceCollector.f1741a, "NPE while determining if app is in foreground for below ICS devices.", e);
                }
            }
            return false;
        }
    }

    static {
        f1741a = IceCollector.class.getSimpleName();
    }

    public IceCollector() {
        this.f1742b = new HandlerThread("DataCollectionHandler");
        this.f1742b.start();
        this.f1743c = new IceCollector(this.f1742b.getLooper());
    }

    public synchronized void m1961a() {
        if (VERSION.SDK_INT < 14 && !m1962b()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1741a, "User data collection can not be started as the data collector is not properly initialized.");
        } else if (this.f1743c.hasMessages(3)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1741a, "User data collection already running.");
        } else {
            this.f1743c.removeMessages(2);
            this.f1743c.sendEmptyMessage(1);
        }
    }

    public boolean m1962b() {
        return SdkContext.m1258b().checkCallingOrSelfPermission("android.permission.GET_TASKS") == 0;
    }

    public void m1963c() {
        this.f1743c.sendEmptyMessageDelayed(2, (long) (SignalsComponent.m4580a().m4585e().m2063c() * DateUtils.MILLIS_IN_SECOND));
    }
}
