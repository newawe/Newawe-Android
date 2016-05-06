package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.network.SyncNetworkTask;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.commons.core.configs.d */
class ConfigNetworkClient implements Runnable {
    private static final String f1212a;
    private ConfigNetworkRequest f1213b;
    private int f1214c;
    private ConfigNetworkClient f1215d;

    /* renamed from: com.inmobi.commons.core.configs.d.a */
    public interface ConfigNetworkClient {
        void m1374a(ConfigResponse configResponse);

        void m1375b();
    }

    static {
        f1212a = ConfigNetworkClient.class.getName();
    }

    public ConfigNetworkClient(ConfigNetworkClient configNetworkClient, ConfigNetworkRequest configNetworkRequest) {
        this.f1215d = configNetworkClient;
        this.f1213b = configNetworkRequest;
        this.f1214c = 0;
    }

    private void m1376a() throws InterruptedException {
        while (this.f1214c <= this.f1213b.m4507c()) {
            Map a = new ConfigNetworkResponse(this.f1213b.m4506b(), new SyncNetworkTask(this.f1213b).m1436a()).m1341a();
            for (Entry entry : a.entrySet()) {
                ConfigResponse configResponse = (ConfigResponse) entry.getValue();
                String str = (String) entry.getKey();
                if (!configResponse.m1336d()) {
                    this.f1215d.m1374a(configResponse);
                    this.f1213b.m4505a(str);
                }
            }
            if (this.f1213b.m4506b().isEmpty()) {
                break;
            }
            this.f1214c++;
            if (this.f1214c > this.f1213b.m4507c()) {
                for (Entry entry2 : this.f1213b.m4506b().entrySet()) {
                    str = (String) entry2.getKey();
                    if (a.containsKey(str)) {
                        this.f1215d.m1374a((ConfigResponse) a.get(str));
                    }
                }
            } else {
                Thread.sleep((long) (this.f1213b.m4508d() * DateUtils.MILLIS_IN_SECOND));
            }
        }
        this.f1215d.m1375b();
    }

    public void run() {
        try {
            m1376a();
        } catch (InterruptedException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1212a, "Fetching config interrupted by the component de-initialization.");
        }
    }
}
