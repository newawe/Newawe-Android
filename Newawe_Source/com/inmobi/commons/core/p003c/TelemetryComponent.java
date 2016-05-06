package com.inmobi.commons.core.p003c;

import android.content.ContentValues;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent.ConfigComponent;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.p001a.CrashEvent;
import com.inmobi.commons.core.p003c.TelemetryComponentConfig.TelemetryComponentConfig;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkConnectivityChangeObserver.NetworkConnectivityChangeObserver;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.commons.p000a.SdkContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.c.a */
public class TelemetryComponent implements ConfigComponent {
    private static final String f3809a;
    private static final Object f3810b;
    private static volatile TelemetryComponent f3811c;
    private static final AtomicBoolean f3812d;
    private static final AtomicBoolean f3813e;
    private static Map<String, TelemetryComponentConfig> f3814f;
    private static TelemetryConfig f3815h;
    private static final Random f3816o;
    private List<TelemetryEvent> f3817g;
    private HandlerThread f3818i;
    private TelemetryComponent f3819j;
    private Map<String, Integer> f3820k;
    private final Object f3821l;
    private final Object f3822m;
    private final Object f3823n;

    /* renamed from: com.inmobi.commons.core.c.a.a */
    private final class TelemetryComponent extends Handler {
        final /* synthetic */ TelemetryComponent f1163a;
        private String f1164b;
        private String f1165c;
        private TelemetryDao f1166d;
        private int f1167e;
        private int f1168f;
        private int f1169g;
        private AtomicBoolean f1170h;
        private int f1171i;
        private int f1172j;
        private boolean f1173k;
        private List<TelemetryEvent> f1174l;

        public TelemetryComponent(TelemetryComponent telemetryComponent, Looper looper) {
            this.f1163a = telemetryComponent;
            super(looper);
            this.f1170h = new AtomicBoolean(false);
            this.f1171i = 0;
            this.f1174l = new ArrayList();
            this.f1165c = null;
            m1294a();
        }

        private void m1294a() {
            this.f1167e = TelemetryComponent.f3815h.m4491i();
            this.f1164b = TelemetryComponent.f3815h.m4488f();
            this.f1168f = TelemetryComponent.f3815h.m4493k() * DateUtils.MILLIS_IN_SECOND;
            this.f1169g = TelemetryComponent.f3815h.m4489g() * DateUtils.MILLIS_IN_SECOND;
            this.f1172j = TelemetryComponent.f3815h.m4492j();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    removeMessages(1);
                    if (!this.f1170h.compareAndSet(false, true)) {
                        return;
                    }
                    if (SdkContext.m1263e() && NetworkUtils.m1479a()) {
                        m1294a();
                        m1296b();
                        return;
                    }
                    Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "App not in foreground or No Network available ");
                    this.f1170h.set(false);
                    if (!SdkContext.m1263e()) {
                        sendEmptyMessage(4);
                    }
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    m1297c();
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    this.f1170h.set(false);
                    sendEmptyMessageDelayed(1, (long) this.f1168f);
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    TelemetryComponent.m4448a().m4465i();
                default:
            }
        }

        private void m1296b() {
            Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Begin reporting");
            this.f1166d = new TelemetryDao();
            List a = this.f1166d.m1315a();
            if (!a.isEmpty()) {
                this.f1173k = true;
                this.f1165c = m1295b(a);
            } else if (this.f1165c == null || this.f1165c.equals(StringUtils.EMPTY)) {
                this.f1173k = false;
                if (this.f1174l.isEmpty()) {
                    this.f1174l = this.f1166d.m1316a(this.f1167e);
                }
                if (this.f1174l.isEmpty()) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "No events to report");
                    sendEmptyMessage(3);
                    return;
                }
                this.f1165c = m1293a(this.f1174l);
            }
            sendEmptyMessage(2);
        }

        private void m1297c() {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.POST, this.f1164b, true, new UidMap(TelemetryComponent.f3815h.m1350n().m1344a()));
            Map hashMap = new HashMap();
            if (this.f1173k) {
                hashMap.put("metric", this.f1165c);
            } else {
                hashMap.put("telemetry", this.f1165c);
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Telemetry Payload: " + this.f1165c);
            networkRequest.m1405c(hashMap);
            NetworkResponse a = new SyncNetworkTask(networkRequest).m1436a();
            if (a.m1433a()) {
                this.f1171i++;
                if (this.f1171i > this.f1172j) {
                    this.f1171i = 0;
                    Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Unable to send telemetry events to server: " + a.m1434b() + " . And retry count exhausted. Will Discard Events");
                    this.f1174l.clear();
                    this.f1165c = null;
                    sendEmptyMessage(3);
                    return;
                }
                Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Unable to send telemetry events to server: " + a.m1434b() + ". Will retry");
                this.f1170h.set(false);
                sendEmptyMessageDelayed(1, (long) this.f1169g);
                return;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Successfully sent events to server: " + a.m1434b());
            this.f1165c = null;
            this.f1174l.clear();
            if (this.f1166d.m1321c() > this.f1167e) {
                this.f1170h.set(false);
                sendEmptyMessage(1);
                return;
            }
            sendEmptyMessage(3);
        }

        private String m1293a(List<TelemetryEvent> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("componentType", ((TelemetryEvent) list.get(i)).m1323a());
                    jSONObject.put("eventType", ((TelemetryEvent) list.get(i)).m1325b());
                    if (!((TelemetryEvent) list.get(i)).m1326c().trim().isEmpty()) {
                        jSONObject.put("payload", ((TelemetryEvent) list.get(i)).m1326c());
                    }
                    jSONObject.put("ts", ((TelemetryEvent) list.get(i)).m1327d());
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return StringUtils.EMPTY;
                }
            }
            return jSONArray.toString();
        }

        private String m1295b(List<ContentValues> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("componentType", ((ContentValues) list.get(i)).getAsString("componentType"));
                    jSONObject.put("eventType", ((ContentValues) list.get(i)).getAsString("eventType"));
                    jSONObject.put("payload", ((ContentValues) list.get(i)).getAsString("payload"));
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return StringUtils.EMPTY;
                }
            }
            return jSONArray.toString();
        }
    }

    /* renamed from: com.inmobi.commons.core.c.a.1 */
    class TelemetryComponent implements NetworkConnectivityChangeObserver {
        final /* synthetic */ TelemetryComponent f3808a;

        TelemetryComponent(TelemetryComponent telemetryComponent) {
            this.f3808a = telemetryComponent;
        }

        public void m4447a(boolean z) {
            Logger.m1440a(InternalLogLevel.INTERNAL, TelemetryComponent.f3809a, "Network status changed " + z);
            if (z && !TelemetryComponent.f3813e.get() && SdkContext.m1263e()) {
                TelemetryComponent.m4448a().m4450a(60);
            }
            TelemetryComponent.f3813e.set(z);
        }
    }

    static {
        f3809a = TelemetryComponent.class.getSimpleName();
        f3810b = new Object();
        f3812d = new AtomicBoolean(false);
        f3813e = new AtomicBoolean(false);
        f3816o = new Random(System.currentTimeMillis());
    }

    public static TelemetryComponent m4448a() {
        TelemetryComponent telemetryComponent = f3811c;
        if (telemetryComponent == null) {
            synchronized (f3810b) {
                telemetryComponent = f3811c;
                if (telemetryComponent == null) {
                    telemetryComponent = new TelemetryComponent();
                    f3811c = telemetryComponent;
                }
            }
        }
        return telemetryComponent;
    }

    private TelemetryComponent() {
        this.f3821l = new Object();
        this.f3822m = new Object();
        this.f3823n = new Object();
        this.f3817g = new ArrayList();
        f3814f = new HashMap();
        this.f3820k = new HashMap();
        f3815h = new TelemetryConfig();
        f3813e.set(NetworkUtils.m1479a());
        com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(f3815h, (ConfigComponent) this);
        m4453a(f3815h.m4482a(), f3815h.m4495m());
        com.inmobi.commons.core.utilities.NetworkConnectivityChangeObserver.m1471a().m1475a(new TelemetryComponent(this));
    }

    public void m4469a(Config config) {
        f3815h = (TelemetryConfig) config;
    }

    public final void m4471a(String str, JSONObject jSONObject) {
        m4453a(str, new TelemetryComponentConfig(str, jSONObject, f3815h.m4495m()));
    }

    private void m4453a(String str, TelemetryComponentConfig telemetryComponentConfig) {
        if (str == null || str.trim().equals(StringUtils.EMPTY)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Component type provided while registering is null or empty!");
        } else if (telemetryComponentConfig != null) {
            f3814f.put(str, telemetryComponentConfig);
        } else {
            f3814f.put(str, new TelemetryComponentConfig(str, null, f3815h.m4495m()));
        }
    }

    TelemetryComponentConfig m4467a(String str) {
        if (str != null && !str.trim().equals(StringUtils.EMPTY)) {
            return (TelemetryComponentConfig) f3814f.get(str);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Request null or empty Component type!");
        return null;
    }

    public void m4470a(String str, String str2, Map<String, Object> map) {
        TelemetryEvent telemetryEvent = new TelemetryEvent(str, str2);
        if (!(map == null || map.isEmpty())) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Entry entry : map.entrySet()) {
                    jSONObject.put(entry.getKey().toString(), entry.getValue());
                }
                telemetryEvent.m1324a(jSONObject.toString());
            } catch (JSONException e) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Error forming JSON payload for " + str2 + " Error: " + e);
            }
        }
        TelemetryComponent.m4448a().m4468a(telemetryEvent);
    }

    public void m4468a(TelemetryEvent telemetryEvent) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Event submitted to telemetry: " + telemetryEvent.m1325b() + " - " + telemetryEvent.m1323a());
        TelemetryComponentConfig f = m4460f(telemetryEvent);
        if (f != null && f.m1311b() && f3815h.m4487e()) {
            m4455c(telemetryEvent);
            m4457d(telemetryEvent);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Telemetry service is not enabled or registered for component: " + telemetryEvent.m1323a());
    }

    private void m4455c(TelemetryEvent telemetryEvent) {
        if (m4462g(telemetryEvent).m1303c()) {
            m4473b(telemetryEvent);
        }
    }

    private void m4457d(TelemetryEvent telemetryEvent) {
        int b = m4462g(telemetryEvent).m1302b();
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Event Sampling factor: " + b);
        if (b <= 0) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Sampling factor is <=0 for this event!");
        } else if (f3816o.nextInt(b) != 0) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Event " + telemetryEvent.m1325b() + " is not lucky enough to be processed further");
        } else {
            m4459e(telemetryEvent);
        }
    }

    private void m4459e(TelemetryEvent telemetryEvent) {
        if (telemetryEvent instanceof CrashEvent) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Got a crash event, will save it right away!");
            new TelemetryDao().m1317a(telemetryEvent);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Caching event " + telemetryEvent.m1325b() + " in memory");
        int h = f3815h.m4490h();
        synchronized (this.f3823n) {
            this.f3817g.add(telemetryEvent);
        }
        if (this.f3817g.size() >= h) {
            m4463g();
            h = new TelemetryDao().m1321c();
            int l = f3815h.m4494l();
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Current event count: " + h + " Upper cap: " + l);
            if (h > (l * 3) / 4) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Telemetry is more than 75% full. Begin reporting ");
                m4464h();
            }
        }
    }

    private void m4463g() {
        synchronized (this.f3823n) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Adding events " + this.f3817g.toString() + "to persistence");
            TelemetryDao telemetryDao = new TelemetryDao();
            int l = f3815h.m4494l();
            int c = telemetryDao.m1321c();
            if ((this.f3817g.size() + c) - l <= 0) {
                telemetryDao.m1319a(this.f3817g);
            } else {
                l -= c;
                if (l <= 0) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Persistence is full, won't add events");
                } else {
                    telemetryDao.m1319a(this.f3817g.subList(0, l));
                    Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Persistence will overflow, will add " + l + " events to persistence");
                }
            }
            this.f3817g.clear();
        }
    }

    private TelemetryComponentConfig m4460f(TelemetryEvent telemetryEvent) {
        return TelemetryComponent.m4448a().m4467a(telemetryEvent.m1323a());
    }

    private TelemetryComponentConfig m4462g(TelemetryEvent telemetryEvent) {
        return m4460f(telemetryEvent).m1305a(telemetryEvent.m1325b());
    }

    private void m4464h() {
        m4450a(0);
    }

    private void m4450a(int i) {
        if (SdkContext.m1263e() && NetworkUtils.m1479a()) {
            synchronized (this.f3822m) {
                if (f3812d.compareAndSet(false, true)) {
                    this.f3818i = new HandlerThread("telemetry");
                    this.f3818i.start();
                    this.f3819j = new TelemetryComponent(this, this.f3818i.getLooper());
                }
                if (i > 0) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Begin reporting after " + i + " seconds");
                    this.f3819j.sendEmptyMessageDelayed(1, (long) (i * DateUtils.MILLIS_IN_SECOND));
                } else {
                    this.f3819j.sendEmptyMessage(1);
                }
            }
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "App not in foreground or No Network available");
    }

    public synchronized void m4472b() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "start called");
        TelemetryComponent.m4448a().m4464h();
    }

    public synchronized void m4474c() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "stop called");
        TelemetryComponent.m4448a().m4466j();
        TelemetryComponent.m4448a().m4463g();
    }

    private void m4465i() {
        synchronized (this.f3822m) {
            if (this.f3818i != null) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Deiniting telemetry");
                this.f3818i.getLooper().quit();
                this.f3818i.interrupt();
                this.f3818i = null;
                this.f3819j = null;
                f3812d.set(false);
            }
        }
    }

    void m4473b(TelemetryEvent telemetryEvent) {
        String a = telemetryEvent.m1323a();
        String b = telemetryEvent.m1325b();
        Logger.m1440a(InternalLogLevel.INTERNAL, f3809a, "Metric collected: " + b + " - " + a);
        b = m4449a(a, b);
        synchronized (this.f3821l) {
            if (this.f3820k.containsKey(b)) {
                this.f3820k.put(b, Integer.valueOf(((Integer) this.f3820k.get(b)).intValue() + 1));
            } else {
                this.f3820k.put(b, Integer.valueOf(1));
            }
        }
    }

    private String m4449a(String str, String str2) {
        return str + "@$#$@" + str2;
    }

    private String[] m4454b(String str) {
        return str.split("\\@\\$\\#\\$\\@");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m4466j() {
        /*
        r7 = this;
        r2 = r7.f3821l;
        monitor-enter(r2);
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005b }
        r1 = f3809a;	 Catch:{ all -> 0x005b }
        r3 = "Saving metric to persistence";
        com.inmobi.commons.core.utilities.Logger.m1440a(r0, r1, r3);	 Catch:{ all -> 0x005b }
        r3 = new com.inmobi.commons.core.c.d;	 Catch:{ all -> 0x005b }
        r3.<init>();	 Catch:{ all -> 0x005b }
        r3.m1320b();	 Catch:{ all -> 0x005b }
        r0 = r7.f3820k;	 Catch:{ all -> 0x005b }
        r0 = r0.entrySet();	 Catch:{ all -> 0x005b }
        r4 = r0.iterator();	 Catch:{ all -> 0x005b }
    L_0x001e:
        r0 = r4.hasNext();	 Catch:{ all -> 0x005b }
        if (r0 == 0) goto L_0x005e;
    L_0x0024:
        r0 = r4.next();	 Catch:{ all -> 0x005b }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x005b }
        r1 = r0.getKey();	 Catch:{ all -> 0x005b }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x005b }
        r1 = r7.m4454b(r1);	 Catch:{ all -> 0x005b }
        r5 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0050 }
        r5.<init>();	 Catch:{ JSONException -> 0x0050 }
        r6 = "count";
        r0 = r0.getValue();	 Catch:{ JSONException -> 0x0050 }
        r5.put(r6, r0);	 Catch:{ JSONException -> 0x0050 }
        r0 = 0;
        r0 = r1[r0];	 Catch:{ JSONException -> 0x0050 }
        r6 = 1;
        r1 = r1[r6];	 Catch:{ JSONException -> 0x0050 }
        r5 = r5.toString();	 Catch:{ JSONException -> 0x0050 }
        r3.m1318a(r0, r1, r5);	 Catch:{ JSONException -> 0x0050 }
        goto L_0x001e;
    L_0x0050:
        r0 = move-exception;
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005b }
        r1 = f3809a;	 Catch:{ all -> 0x005b }
        r5 = "Error forming metric payload";
        com.inmobi.commons.core.utilities.Logger.m1440a(r0, r1, r5);	 Catch:{ all -> 0x005b }
        goto L_0x001e;
    L_0x005b:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x005b }
        throw r0;
    L_0x005e:
        r0 = r7.f3820k;	 Catch:{ all -> 0x005b }
        r0.clear();	 Catch:{ all -> 0x005b }
        monitor-exit(r2);	 Catch:{ all -> 0x005b }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.c.a.j():void");
    }
}
