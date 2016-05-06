package com.inmobi.rendering.p005a;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.ads.AdConfig.AdConfig;
import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent.ConfigComponent;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.network.WebViewNetworkTask;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkConnectivityChangeObserver.NetworkConnectivityChangeObserver;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.rendering.a.c */
public final class ClickManager implements ConfigComponent {
    private static final String f3865a;
    private static ClickManager f3866b;
    private static final Object f3867c;
    private static ExecutorService f3868d;
    private static ClickManager f3869e;
    private static HandlerThread f3870f;
    private static List<Click> f3871g;
    private static ClickDao f3872h;
    private static AtomicBoolean f3873i;
    private static AdConfig f3874j;
    private static final Object f3875k;
    private final ClickManager f3876l;

    /* renamed from: com.inmobi.rendering.a.c.1 */
    class ClickManager implements Runnable {
        final /* synthetic */ Click f1530a;
        final /* synthetic */ ClickManager f1531b;

        ClickManager(ClickManager clickManager, Click click) {
            this.f1531b = clickManager;
            this.f1530a = click;
        }

        public void run() {
            if (this.f1530a.f1526f) {
                new ClickManager(this.f1531b.f3876l).m1703a(this.f1530a);
            } else {
                new ClickManager(this.f1531b.f3876l).m1704a(this.f1530a);
            }
        }
    }

    /* renamed from: com.inmobi.rendering.a.c.a */
    final class ClickManager extends Handler {
        final /* synthetic */ ClickManager f1532a;

        /* renamed from: com.inmobi.rendering.a.c.a.1 */
        class ClickManager implements ClickManager {
            final /* synthetic */ ClickManager f3863a;

            ClickManager(ClickManager clickManager) {
                this.f3863a = clickManager;
            }

            public void m4549a(Click click) {
                this.f3863a.m1700c(click);
            }

            public void m4550a(Click click, ErrorCode errorCode) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + click.f1521a + ") via HTTP failed ...");
                this.f3863a.f1532a.m4556a(click);
                this.f3863a.m1701d(click);
            }
        }

        /* renamed from: com.inmobi.rendering.a.c.a.2 */
        class ClickManager implements ClickManager {
            final /* synthetic */ ClickManager f3864a;

            ClickManager(ClickManager clickManager) {
                this.f3864a = clickManager;
            }

            public void m4551a(Click click) {
                this.f3864a.m1700c(click);
            }

            public void m4552a(Click click, ErrorCode errorCode) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + click.f1521a + ") via WebView failed ...");
                this.f3864a.f1532a.m4556a(click);
                this.f3864a.m1701d(click);
            }
        }

        public ClickManager(ClickManager clickManager, Looper looper) {
            this.f1532a = clickManager;
            super(looper);
        }

        public void handleMessage(Message message) {
            Click click;
            int a;
            switch (message.what) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    ClickManager.f3871g = ClickManager.f3872h.m1690a(ClickManager.f3874j.m1154e(), ClickManager.f3874j.m1151b());
                    if (!ClickManager.f3871g.isEmpty()) {
                        click = (Click) ClickManager.f3871g.get(0);
                        Message obtain = Message.obtain();
                        obtain.what = click.f1526f ? 3 : 2;
                        obtain.obj = click;
                        long currentTimeMillis = System.currentTimeMillis() - click.f1523c;
                        if (currentTimeMillis < ((long) (ClickManager.f3874j.m1151b() * DateUtils.MILLIS_IN_SECOND))) {
                            sendMessageDelayed(obtain, ((long) (ClickManager.f3874j.m1151b() * DateUtils.MILLIS_IN_SECOND)) - currentTimeMillis);
                            return;
                        } else {
                            sendMessage(obtain);
                            return;
                        }
                    } else if (ClickManager.f3872h.m1692a()) {
                        ClickManager.f3873i.set(false);
                        return;
                    } else {
                        Message obtain2 = Message.obtain();
                        obtain2.what = 1;
                        sendMessageDelayed(obtain2, (long) (ClickManager.f3874j.m1151b() * DateUtils.MILLIS_IN_SECOND));
                        return;
                    }
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    if (NetworkUtils.m1479a()) {
                        click = (Click) message.obj;
                        if (click.f1524d == 0) {
                            m1698b(click);
                            return;
                        }
                        a = (ClickManager.f3874j.m1150a() - click.f1524d) + 1;
                        if (a == 0) {
                            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + click.f1521a + ") over HTTP");
                        } else {
                            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Retry attempt #" + a + " for click (" + click.f1521a + ") over HTTP");
                        }
                        new ClickManager(new ClickManager(this)).m1704a(click);
                        return;
                    }
                    ClickManager.f3873i.set(false);
                    this.f1532a.m4568c();
                    return;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    if (NetworkUtils.m1479a()) {
                        click = (Click) message.obj;
                        if (click.f1524d == 0) {
                            m1698b(click);
                            return;
                        }
                        a = (ClickManager.f3874j.m1150a() - click.f1524d) + 1;
                        if (a == 0) {
                            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + click.f1521a + ") in WebView");
                        } else {
                            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Retry attempt #" + a + " for click (" + click.f1521a + ") using WebView");
                        }
                        new ClickManager(new ClickManager(this)).m1703a(click);
                        return;
                    }
                    ClickManager.f3873i.set(false);
                    this.f1532a.m4568c();
                    return;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    break;
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    click = (Click) message.obj;
                    Map hashMap = new HashMap();
                    hashMap.put("pingUrl", click.f1522b);
                    hashMap.put("errorCode", "MaxRetryCountReached");
                    TelemetryComponent.m4448a().m4470a("ads", "PingDiscarded", hashMap);
                    break;
                default:
                    return;
            }
            click = (Click) message.obj;
            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Processing click (" + click.f1521a + ") completed");
            ClickManager.f3872h.m1694b(click);
            ClickManager.f3871g.remove(click);
            if (!ClickManager.f3871g.isEmpty()) {
                m1696a((Click) ClickManager.f3871g.get(0));
            } else if (ClickManager.f3872h.m1692a()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Done processing all clicks!");
                ClickManager.f3873i.set(false);
            } else {
                obtain2 = Message.obtain();
                obtain2.what = 1;
                sendMessage(obtain2);
            }
        }

        private void m1696a(Click click) {
            Message obtain = Message.obtain();
            obtain.what = click.f1526f ? 3 : 2;
            obtain.obj = click;
            sendMessage(obtain);
        }

        private void m1698b(Click click) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = click;
            sendMessage(obtain);
        }

        private void m1700c(Click click) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.obj = click;
            sendMessage(obtain);
        }

        private void m1701d(Click click) {
            int indexOf = ClickManager.f3871g.indexOf(click);
            Click click2 = (Click) ClickManager.f3871g.get(indexOf == ClickManager.f3871g.size() + -1 ? 0 : indexOf + 1);
            Message obtain = Message.obtain();
            obtain.what = click2.f1526f ? 3 : 2;
            obtain.obj = click2;
            if (System.currentTimeMillis() - click2.f1523c < ((long) (ClickManager.f3874j.m1151b() * DateUtils.MILLIS_IN_SECOND))) {
                sendMessageDelayed(obtain, (long) (ClickManager.f3874j.m1151b() * DateUtils.MILLIS_IN_SECOND));
            } else {
                sendMessage(obtain);
            }
        }
    }

    /* renamed from: com.inmobi.rendering.a.c.b */
    static final class ClickManager {
        private ClickManager f1542a;

        /* renamed from: com.inmobi.rendering.a.c.b.1 */
        class ClickManager implements Runnable {
            final /* synthetic */ Click f1539a;
            final /* synthetic */ Handler f1540b;
            final /* synthetic */ ClickManager f1541c;

            /* renamed from: com.inmobi.rendering.a.c.b.1.1 */
            class ClickManager extends WebViewClient {
                AtomicBoolean f1536a;
                boolean f1537b;
                final /* synthetic */ ClickManager f1538c;

                /* renamed from: com.inmobi.rendering.a.c.b.1.1.1 */
                class ClickManager implements Runnable {
                    final /* synthetic */ WebView f1534a;
                    final /* synthetic */ ClickManager f1535b;

                    /* renamed from: com.inmobi.rendering.a.c.b.1.1.1.1 */
                    class ClickManager implements Runnable {
                        final /* synthetic */ ClickManager f1533a;

                        ClickManager(ClickManager clickManager) {
                            this.f1533a = clickManager;
                        }

                        public void run() {
                            this.f1533a.f1534a.stopLoading();
                        }
                    }

                    ClickManager(ClickManager clickManager, WebView webView) {
                        this.f1535b = clickManager;
                        this.f1534a = webView;
                    }

                    public void run() {
                        try {
                            Thread.sleep((long) (ClickManager.f3874j.m1152c() * DateUtils.MILLIS_IN_SECOND));
                        } catch (InterruptedException e) {
                        }
                        if (!this.f1535b.f1536a.get()) {
                            Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + this.f1535b.f1538c.f1539a.f1521a + ") via WebView timed out!");
                            this.f1535b.f1538c.f1539a.f1525e.set(true);
                            this.f1535b.f1538c.f1540b.post(new ClickManager(this));
                            this.f1535b.f1538c.f1541c.f1542a.m1706a(this.f1535b.f1538c.f1539a, null);
                        }
                    }
                }

                ClickManager(ClickManager clickManager) {
                    this.f1538c = clickManager;
                }

                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                    this.f1536a = new AtomicBoolean(false);
                    this.f1537b = false;
                    new Thread(new ClickManager(this, webView)).start();
                }

                public void onPageFinished(WebView webView, String str) {
                    this.f1536a.set(true);
                    if (!this.f1537b && !this.f1538c.f1539a.f1525e.get()) {
                        this.f1538c.f1541c.f1542a.m1705a(this.f1538c.f1539a);
                    }
                }

                public void onReceivedError(WebView webView, int i, String str, String str2) {
                    this.f1537b = true;
                    this.f1538c.f1541c.f1542a.m1706a(this.f1538c.f1539a, null);
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (this.f1538c.f1539a.f1527g || str.equals(this.f1538c.f1539a.f1522b)) {
                        return false;
                    }
                    return true;
                }
            }

            ClickManager(ClickManager clickManager, Click click, Handler handler) {
                this.f1541c = clickManager;
                this.f1539a = click;
                this.f1540b = handler;
            }

            public void run() {
                new WebViewNetworkTask(new NetworkRequest(RequestType.GET, this.f1539a.f1522b, false, null), new ClickManager(this)).m1438a();
            }
        }

        public ClickManager(ClickManager clickManager) {
            this.f1542a = clickManager;
        }

        public void m1703a(Click click) {
            click.f1525e.set(false);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new ClickManager(this, click, handler));
        }
    }

    /* renamed from: com.inmobi.rendering.a.c.c */
    static final class ClickManager {
        private ClickManager f1543a;

        public ClickManager(ClickManager clickManager) {
            this.f1543a = clickManager;
        }

        public void m1704a(Click click) {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, click.f1522b, false, null);
            networkRequest.m1401a(false);
            networkRequest.m1403b(click.f1527g);
            networkRequest.m1402b(ClickManager.f3874j.m1152c() * DateUtils.MILLIS_IN_SECOND);
            networkRequest.m1404c(ClickManager.f3874j.m1152c() * DateUtils.MILLIS_IN_SECOND);
            NetworkResponse a = new SyncNetworkTask(networkRequest).m1436a();
            if (a.m1433a()) {
                ErrorCode a2 = a.m1435c().m1395a();
                if (click.f1527g || !(ErrorCode.HTTP_SEE_OTHER == a2 || ErrorCode.HTTP_MOVED_TEMP == a2)) {
                    this.f1543a.m1706a(click, a.m1435c().m1395a());
                    return;
                } else {
                    this.f1543a.m1705a(click);
                    return;
                }
            }
            this.f1543a.m1705a(click);
        }
    }

    /* renamed from: com.inmobi.rendering.a.c.d */
    interface ClickManager {
        void m1705a(Click click);

        void m1706a(Click click, ErrorCode errorCode);
    }

    /* renamed from: com.inmobi.rendering.a.c.2 */
    class ClickManager implements NetworkConnectivityChangeObserver {
        final /* synthetic */ ClickManager f3861a;

        ClickManager(ClickManager clickManager) {
            this.f3861a = clickManager;
        }

        public void m4546a(boolean z) {
            if (z) {
                this.f3861a.m4566b();
            }
        }
    }

    /* renamed from: com.inmobi.rendering.a.c.3 */
    class ClickManager implements ClickManager {
        final /* synthetic */ ClickManager f3862a;

        ClickManager(ClickManager clickManager) {
            this.f3862a = clickManager;
        }

        public void m4547a(Click click) {
            if (click != null) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Processing click (" + click.f1521a + ") completed");
                ClickManager.f3872h.m1694b(click);
            }
        }

        public void m4548a(Click click, ErrorCode errorCode) {
            if (click != null) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ClickManager.f3865a, "Pinging click (" + click.f1521a + ") failed! Updating retry counts and timestamps ...");
                this.f3862a.m4556a(click);
                this.f3862a.m4566b();
            }
        }
    }

    static {
        f3865a = ClickManager.class.getSimpleName();
        f3867c = new Object();
        f3871g = new ArrayList();
        f3873i = new AtomicBoolean(false);
        f3875k = new Object();
    }

    public static ClickManager m4554a() {
        ClickManager clickManager = f3866b;
        if (clickManager == null) {
            synchronized (f3867c) {
                clickManager = f3866b;
                if (clickManager == null) {
                    clickManager = new ClickManager();
                    f3866b = clickManager;
                }
            }
        }
        return clickManager;
    }

    public void m4564a(Config config) {
        f3874j = ((com.inmobi.ads.AdConfig) config).m4412i();
    }

    public void m4566b() {
        if (NetworkUtils.m1479a()) {
            synchronized (f3875k) {
                if (f3873i.compareAndSet(false, true)) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "Resume processing clicks ...");
                    if (f3870f == null) {
                        f3870f = new HandlerThread("pingHandlerThread");
                        f3870f.start();
                    }
                    if (f3869e == null) {
                        f3869e = new ClickManager(this, f3870f.getLooper());
                    }
                    if (f3872h.m1692a()) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "Done processing all clicks!");
                        f3873i.set(false);
                        m4568c();
                    } else {
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        f3869e.sendMessage(obtain);
                    }
                }
            }
        }
    }

    public void m4565a(String str, boolean z) {
        Click click = new Click(str, z, false, f3874j.m1150a() + 1);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "Received click (" + click.f1521a + ") for pinging over HTTP");
        m4558b(click);
    }

    public void m4567b(String str, boolean z) {
        Click click = new Click(str, z, true, f3874j.m1150a() + 1);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "Received click (" + click.f1521a + ") for pinging in WebView");
        m4558b(click);
    }

    public void m4568c() {
        f3873i.set(false);
        synchronized (f3875k) {
            if (!f3873i.get()) {
                if (f3870f != null) {
                    f3870f.getLooper().quit();
                    f3870f.interrupt();
                    f3870f = null;
                    f3869e = null;
                }
                f3871g.clear();
            }
        }
    }

    private void m4556a(Click click) {
        if (click.f1524d > 0) {
            click.f1524d--;
            click.f1523c = System.currentTimeMillis();
            f3872h.m1691a(click);
        }
    }

    private void m4558b(Click click) {
        f3872h.m1693a(click, f3874j.m1153d());
        if (NetworkUtils.m1479a()) {
            f3868d.submit(new ClickManager(this, click));
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "No network available. Saving click for later processing ...");
        f3873i.set(false);
        m4568c();
    }

    private ClickManager() {
        this.f3876l = new ClickManager(this);
        Logger.m1440a(InternalLogLevel.INTERNAL, f3865a, "Creating a new instance ...");
        f3868d = Executors.newFixedThreadPool(5);
        f3870f = new HandlerThread("pingHandlerThread");
        f3870f.start();
        f3869e = new ClickManager(this, f3870f.getLooper());
        Config adConfig = new com.inmobi.ads.AdConfig();
        com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(adConfig, (ConfigComponent) this);
        f3874j = adConfig.m4412i();
        f3872h = new ClickDao();
        com.inmobi.commons.core.utilities.NetworkConnectivityChangeObserver.m1471a().m1475a(new ClickManager(this));
    }
}
