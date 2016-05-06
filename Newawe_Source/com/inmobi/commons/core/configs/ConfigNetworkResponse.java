package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.configs.ConfigError.ErrorCode;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

final class ConfigNetworkResponse {
    private static final String f1195a;
    private Map<String, Config> f1196b;
    private Map<String, ConfigResponse> f1197c;
    private NetworkResponse f1198d;
    private ConfigError f1199e;

    public static class ConfigResponse {
        private ConfigResponseStatus f1192a;
        private Config f1193b;
        private ConfigError f1194c;

        public enum ConfigResponseStatus {
            SUCCESS(HttpStatus.SC_OK),
            NOT_MODIFIED(HttpStatus.SC_NOT_MODIFIED),
            PRODUCT_NOT_FOUND(HttpStatus.SC_NOT_FOUND),
            INTERNAL_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
            UNKNOWN(-1);
            
            private int f1191a;

            private ConfigResponseStatus(int i) {
                this.f1191a = i;
            }

            public int getValue() {
                return this.f1191a;
            }

            public static ConfigResponseStatus fromValue(int i) {
                for (ConfigResponseStatus configResponseStatus : values()) {
                    if (configResponseStatus.f1191a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, Config config) {
            this.f1193b = config;
            if (jSONObject != null) {
                m1331a(jSONObject);
            }
        }

        private void m1331a(JSONObject jSONObject) {
            try {
                this.f1192a = ConfigResponseStatus.fromValue(jSONObject.getInt(NotificationCompatApi21.CATEGORY_STATUS));
                if (this.f1192a == ConfigResponseStatus.SUCCESS) {
                    this.f1193b.m1346a(jSONObject.getJSONObject("content"));
                    if (!this.f1193b.m1348c()) {
                        m1333a(new ConfigError(ErrorCode.PARSING_ERROR, "The received config has failed validation."));
                        Logger.m1440a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f1195a, "Config type:" + this.f1193b.m1345a() + " Error code:" + m1335c().m1329a() + " Error message:" + m1335c().m1330b());
                    }
                } else if (this.f1192a == ConfigResponseStatus.NOT_MODIFIED) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f1195a, "Config type:" + this.f1193b.m1345a() + " Config not modified");
                } else {
                    m1333a(new ConfigError(ErrorCode.CONFIG_SERVER_INTERNAL_ERROR, this.f1192a.toString()));
                    Logger.m1440a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f1195a, "Config type:" + this.f1193b.m1345a() + " Error code:" + m1335c().m1329a() + " Error message:" + m1335c().m1330b());
                }
            } catch (Throwable e) {
                m1333a(new ConfigError(ErrorCode.PARSING_ERROR, e.getLocalizedMessage()));
                Logger.m1441a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f1195a, "Config type:" + this.f1193b.m1345a() + " Error code:" + m1335c().m1329a() + " Error message:" + m1335c().m1330b(), e);
                Map hashMap = new HashMap();
                hashMap.put("name", this.f1193b.m1345a());
                hashMap.put("errorCode", "ParsingError");
                hashMap.put("reason", e.getLocalizedMessage());
                TelemetryComponent.m4448a().m4470a("root", "InvalidConfig", hashMap);
            }
        }

        public Config m1332a() {
            return this.f1193b;
        }

        public ConfigResponseStatus m1334b() {
            return this.f1192a;
        }

        public ConfigError m1335c() {
            return this.f1194c;
        }

        public void m1333a(ConfigError configError) {
            this.f1194c = configError;
        }

        public boolean m1336d() {
            return this.f1194c != null;
        }
    }

    static {
        f1195a = ConfigNetworkResponse.class.getName();
    }

    ConfigNetworkResponse(Map<String, Config> map, NetworkResponse networkResponse) {
        this.f1196b = map;
        this.f1198d = networkResponse;
        this.f1197c = new HashMap();
        m1340d();
    }

    private void m1340d() {
        if (this.f1198d.m1433a()) {
            for (Entry entry : this.f1196b.entrySet()) {
                ConfigResponse configResponse = new ConfigResponse(null, (Config) entry.getValue());
                configResponse.m1333a(new ConfigError(ErrorCode.NETWORK_ERROR, "Network error in fetching config."));
                this.f1197c.put(entry.getKey(), configResponse);
            }
            m1338a(new ConfigError(ErrorCode.NETWORK_ERROR, this.f1198d.m1435c().m1396b()));
            Logger.m1440a(InternalLogLevel.INTERNAL, f1195a, "Error code:" + m1342b().m1329a() + " Error message:" + m1342b().m1330b());
            Map hashMap = new HashMap();
            hashMap.put("name", m1337a(this.f1196b));
            hashMap.put("errorCode", String.valueOf(this.f1198d.m1435c().m1395a().getValue()));
            hashMap.put("reason", this.f1198d.m1435c().m1396b());
            TelemetryComponent.m4448a().m4470a("root", "InvalidConfig", hashMap);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f1198d.m1434b());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (this.f1196b.get(str) != null) {
                    this.f1197c.put(str, new ConfigResponse(jSONObject2, (Config) this.f1196b.get(str)));
                }
            }
        } catch (Throwable e) {
            m1338a(new ConfigError(ErrorCode.PARSING_ERROR, e.getLocalizedMessage()));
            Logger.m1441a(InternalLogLevel.INTERNAL, f1195a, "Error code:" + m1342b().m1329a() + " Error message:" + m1342b().m1330b(), e);
            Map hashMap2 = new HashMap();
            hashMap2.put("name", m1337a(this.f1196b));
            hashMap2.put("errorCode", "ParsingError");
            hashMap2.put("reason", e.getLocalizedMessage());
            TelemetryComponent.m4448a().m4470a("root", "InvalidConfig", hashMap2);
        }
    }

    public Map<String, ConfigResponse> m1341a() {
        return this.f1197c;
    }

    public ConfigError m1342b() {
        return this.f1199e;
    }

    private void m1338a(ConfigError configError) {
        this.f1199e = configError;
    }

    private static String m1337a(Map<String, Config> map) {
        String str = StringUtils.EMPTY;
        String str2 = str;
        for (String str3 : map.keySet()) {
            str2 = str2 + str3 + ",";
        }
        return str2.substring(0, str2.length() - 1);
    }
}
