package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.configs.ConfigNetworkClient.ConfigNetworkClient;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.commons.p000a.SdkInfo;
import com.startapp.android.publish.model.MetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONException;

/* renamed from: com.inmobi.commons.core.configs.b */
public class ConfigComponent {
    private static final String f1202a;
    private static final Object f1203b;
    private static Map<Config, ArrayList<WeakReference<ConfigComponent>>> f1204c;
    private static RootConfig f1205d;
    private static volatile ConfigComponent f1206e;
    private static ConfigComponent f1207f;
    private HandlerThread f1208g;
    private ConfigComponent f1209h;
    private boolean f1210i;

    /* renamed from: com.inmobi.commons.core.configs.b.b */
    public interface ConfigComponent {
        void m1351a(Config config);
    }

    /* renamed from: com.inmobi.commons.core.configs.b.a */
    static final class ConfigComponent extends Handler implements ConfigNetworkClient {
        private List<Config> f3832a;
        private Map<String, Map<String, Config>> f3833b;
        private Map<String, Config> f3834c;
        private ExecutorService f3835d;

        ConfigComponent(Looper looper) {
            super(looper);
            this.f3832a = new ArrayList();
            this.f3833b = new HashMap();
            this.f3834c = new HashMap();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    Config config = (Config) message.obj;
                    Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Fetch requested for config:" + config.m1345a() + ". IsAlreadyScheduled:" + m4498a(config.m1345a()));
                    if (m4498a(config.m1345a())) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config fetching already in progress:" + config.m1345a());
                        return;
                    }
                    this.f3832a.add(config);
                    if (!hasMessages(2)) {
                        sendEmptyMessage(2);
                    }
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    sendEmptyMessageDelayed(3, (long) (ConfigComponent.f1205d.m4526g() * DateUtils.MILLIS_IN_SECOND));
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    m4497a(this.f3832a);
                    this.f3832a.clear();
                    if (this.f3835d == null || this.f3835d.isShutdown()) {
                        this.f3835d = Executors.newFixedThreadPool(1);
                        sendEmptyMessage(4);
                    }
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    Entry entry;
                    if (this.f3833b.isEmpty()) {
                        entry = null;
                    } else {
                        entry = (Entry) this.f3833b.entrySet().iterator().next();
                    }
                    if (entry != null) {
                        this.f3834c = (Map) entry.getValue();
                        this.f3833b.remove(entry.getKey());
                        m4496a((String) entry.getKey(), this.f3834c);
                        return;
                    }
                    Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config fetching stopping as no more configs left to fetch.");
                    sendEmptyMessage(5);
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    m4499a();
                default:
            }
        }

        public void m4499a() {
            if (this.f3835d != null && !this.f3835d.isShutdown()) {
                this.f3834c = null;
                this.f3833b.clear();
                removeMessages(3);
                this.f3835d.shutdownNow();
            }
        }

        private boolean m4498a(String str) {
            boolean z;
            if (this.f3833b.get(ConfigComponent.f1205d.m4520b(str)) == null || !((Map) this.f3833b.get(ConfigComponent.f1205d.m4520b(str))).containsKey(str)) {
                z = false;
            } else {
                z = true;
            }
            if (this.f3834c == null || !this.f3834c.containsKey(str)) {
                return z;
            }
            return true;
        }

        private void m4496a(String str, Map<String, Config> map) {
            int f = ConfigComponent.f1205d.m4525f();
            Map<String, Config> map2 = map;
            this.f3835d.execute(new ConfigNetworkClient(this, new ConfigNetworkRequest(map2, new UidMap(ConfigComponent.f1205d.m1350n().m1344a()), str, ConfigComponent.f1205d.m4524e(), f)));
        }

        private void m4497a(List<Config> list) {
            for (int i = 0; i < list.size(); i++) {
                Config config = (Config) list.get(i);
                HashMap hashMap = (HashMap) this.f3833b.get(ConfigComponent.f1205d.m4520b(config.m1345a()));
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(config.m1345a(), config);
                this.f3833b.put(ConfigComponent.f1205d.m4520b(config.m1345a()), hashMap);
            }
        }

        public void m4500a(ConfigResponse configResponse) {
            ConfigDao configDao = new ConfigDao();
            if (configResponse.m1336d()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config fetching failed:" + configResponse.m1332a().m1345a() + ", Error code:" + configResponse.m1335c().m1329a());
                return;
            }
            Config a = configResponse.m1332a();
            configDao.m1369a(a);
            if (configResponse.m1334b() == ConfigResponseStatus.NOT_MODIFIED) {
                Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config not modified status from server:" + configResponse.m1332a().m1345a());
                configDao.m1370a(configResponse.m1332a().m1345a(), System.currentTimeMillis());
                return;
            }
            try {
                Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config cached successfully:" + configResponse.m1332a().m1345a());
                Logger.m1440a(InternalLogLevel.INTERNAL, ConfigComponent.f1202a, "Config cached successfully:" + a.m1347b().toString());
                ConfigComponent.m1357b(a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void m4501b() {
            sendEmptyMessage(4);
        }
    }

    /* renamed from: com.inmobi.commons.core.configs.b.c */
    static class ConfigComponent implements ConfigComponent {
        ConfigComponent() {
        }

        public void m4502a(Config config) {
            ConfigComponent.f1205d = (RootConfig) config;
        }
    }

    static {
        f1202a = ConfigComponent.class.getSimpleName();
        f1203b = new Object();
    }

    public static ConfigComponent m1352a() {
        ConfigComponent configComponent = f1206e;
        if (configComponent == null) {
            synchronized (f1203b) {
                configComponent = f1206e;
                if (configComponent == null) {
                    configComponent = new ConfigComponent();
                    f1206e = configComponent;
                }
            }
        }
        return configComponent;
    }

    private ConfigComponent() {
        this.f1210i = false;
        f1204c = new HashMap();
        this.f1208g = new HandlerThread("ConfigBootstrapHandler");
        this.f1208g.start();
        this.f1209h = new ConfigComponent(this.f1208g.getLooper());
        f1205d = new RootConfig();
    }

    public synchronized void m1365b() {
        if (!this.f1210i) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Starting config component.");
            this.f1210i = true;
            TelemetryComponent.m4448a().m4471a("root", f1205d.m4528i());
            if (f1207f == null) {
                f1207f = new ConfigComponent();
            }
            m1364a(f1205d, f1207f);
            m1363g();
        }
    }

    public synchronized void m1366c() {
        if (this.f1210i) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Stopping config component.");
            this.f1210i = false;
            this.f1209h.sendEmptyMessage(5);
        }
    }

    private void m1358b(Config config, ConfigComponent configComponent) {
        ArrayList arrayList;
        ArrayList arrayList2 = (ArrayList) f1204c.get(config);
        if (arrayList2 == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = arrayList2;
        }
        arrayList.add(configComponent == null ? null : new WeakReference(configComponent));
        f1204c.put(config, arrayList);
    }

    private void m1363g() {
        for (Entry key : f1204c.entrySet()) {
            Config config = (Config) key.getKey();
            m1359c(config);
            ConfigComponent.m1357b(config);
        }
    }

    private static void m1357b(Config config) {
        ArrayList arrayList = (ArrayList) f1204c.get(config);
        if (arrayList != null) {
            int i = 0;
            while (i < arrayList.size()) {
                if (!(arrayList.get(i) == null || ((WeakReference) arrayList.get(i)).get() == null)) {
                    ((ConfigComponent) ((WeakReference) arrayList.get(i)).get()).m1351a(config);
                }
                i++;
            }
        }
    }

    public final synchronized void m1364a(Config config, ConfigComponent configComponent) {
        if (this.f1210i) {
            m1358b(config, configComponent);
            m1359c(config);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Config component not yet started, config can't be fetched. Requested type:" + config.m1345a());
        }
    }

    public final void m1367d() {
        String a = f1205d.m4527h().m1393a();
        String b = f1205d.m4527h().m1394b();
        if (a.trim().length() != 0 && ConfigComponent.m1356a(SdkInfo.m1268b(), a.trim())) {
            Logger.m1440a(InternalLogLevel.DEBUG, f1202a, "A newer version (version " + a + ") of the InMobi SDK is available! " + "You are currently on an older version (Version " + SdkInfo.m1268b() + "). Please download the latest InMobi SDK from " + b);
        }
    }

    private final synchronized void m1359c(Config config) {
        ConfigDao configDao = new ConfigDao();
        if (configDao.m1371a(f1205d.m4518a())) {
            configDao.m1373b(f1205d);
            if (m1355a(configDao.m1372b(f1205d.m4518a()), f1205d.m4517a(f1205d.m4518a()))) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "RootConfig expired. Fetching root.");
                m1360d(f1205d.m4523d());
            }
            if (configDao.m1371a(config.m1345a())) {
                configDao.m1373b(config);
                if (m1355a(configDao.m1372b(config.m1345a()), f1205d.m4517a(config.m1345a()))) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Requested config expired. Returning currently cached and fetching. Config type:" + config.m1345a());
                    m1360d(config.m1349d());
                } else {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Serving config from cache. Config:" + config.m1345a());
                }
            } else {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "Requested config not present. Returning default and fetching. Config type:" + config.m1345a());
                m1360d(config.m1349d());
            }
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1202a, "RootConfig not available. Fetching root and returning defaults for config type:" + config.m1345a());
            m1360d(f1205d.m4523d());
        }
    }

    private boolean m1355a(long j, long j2) {
        if (System.currentTimeMillis() - j > j2) {
            return true;
        }
        return false;
    }

    private void m1360d(Config config) {
        Message obtainMessage = this.f1209h.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = config;
        this.f1209h.sendMessage(obtainMessage);
    }

    public static boolean m1356a(String str, String str2) {
        boolean z = true;
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length) {
            try {
                if (Integer.valueOf(split[i]).intValue() < 0) {
                    return false;
                }
                i++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        for (String valueOf : split2) {
            if (Integer.valueOf(valueOf).intValue() < 0) {
                return false;
            }
        }
        i = split.length < split2.length ? split.length : split2.length;
        int i2 = 0;
        while (i2 < i) {
            if (split[i2].equals(split2[i2])) {
                i2++;
            } else {
                boolean z2;
                if (Integer.valueOf(split[i2]).intValue() < Integer.valueOf(split2[i2]).intValue()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                return z2;
            }
        }
        if (split.length >= split2.length) {
            z = false;
        }
        return z;
    }
}
