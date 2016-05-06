package com.inmobi.commons.core.network;

import java.util.List;
import java.util.Map;

/* renamed from: com.inmobi.commons.core.network.c */
public class NetworkResponse {
    private NetworkRequest f1246a;
    private String f1247b;
    private NetworkError f1248c;
    private Map<String, List<String>> f1249d;

    public NetworkResponse(NetworkRequest networkRequest) {
        this.f1246a = networkRequest;
    }

    public boolean m1433a() {
        return this.f1248c != null;
    }

    public String m1434b() {
        return this.f1247b;
    }

    public void m1431a(String str) {
        this.f1247b = str;
    }

    public void m1432a(Map<String, List<String>> map) {
        this.f1249d = map;
    }

    public NetworkError m1435c() {
        return this.f1248c;
    }

    public void m1430a(NetworkError networkError) {
        this.f1248c = networkError;
    }
}
