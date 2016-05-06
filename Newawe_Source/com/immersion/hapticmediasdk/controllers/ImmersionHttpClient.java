package com.immersion.hapticmediasdk.controllers;

import com.immersion.hapticmediasdk.utils.Log;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

public class ImmersionHttpClient {
    private static final String f949a = "ImmersionHttpClient";
    public static int f950b04460446044604460446 = 0;
    public static int f951b0446044604460446 = 1;
    public static int f952b0446044604460446 = 2;
    public static int f953b044604460446 = 3;
    private DefaultHttpClient f954b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ImmersionHttpClient() {
        /*
        r2 = this;
        r2.<init>();
        r0 = m1068b044604460446();
        r1 = f951b0446044604460446;
        r0 = r0 + r1;
        r1 = m1068b044604460446();
        r0 = r0 * r1;
        r1 = f952b0446044604460446;
        r0 = r0 % r1;
        r1 = f950b04460446044604460446;
        if (r0 == r1) goto L_0x001c;
    L_0x0016:
        r0 = m1068b044604460446();
        f950b04460446044604460446 = r0;
    L_0x001c:
        r0 = 0;
    L_0x001d:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x001d;
            case 1: goto L_0x0026;
            default: goto L_0x0021;
        };
    L_0x0021:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0026;
            case 1: goto L_0x001d;
            default: goto L_0x0025;
        };
    L_0x0025:
        goto L_0x0021;
    L_0x0026:
        r2.f954b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.ImmersionHttpClient.<init>():void");
    }

    private HttpResponse m1065a(HttpUriRequest httpUriRequest, Map map, int i) throws Exception {
        URI uri = httpUriRequest.getURI();
        String trim = uri.getHost() != null ? uri.getHost().trim() : StringUtils.EMPTY;
        if (trim.length() > 0) {
            httpUriRequest.setHeader(HTTP.TARGET_HOST, trim);
            if (((f953b044604460446 + f951b0446044604460446) * f953b044604460446) % f952b0446044604460446 != f950b04460446044604460446) {
                f953b044604460446 = 43;
                f950b04460446044604460446 = 98;
            }
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                httpUriRequest.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Header[] allHeaders = httpUriRequest.getAllHeaders();
        Log.m1113d(f949a, "request URI [" + httpUriRequest.getURI() + "]");
        for (Object obj : allHeaders) {
            Log.m1113d(f949a, "request header [" + obj.toString() + "]");
        }
        HttpConnectionParams.setSoTimeout(this.f954b.getParams(), i);
        HttpResponse execute = this.f954b.execute(httpUriRequest);
        if (execute != null) {
            return execute;
        }
        throw new RuntimeException("Null response returned.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1066a() {
        /*
        r6 = this;
        r2 = 0;
        r0 = r6.f954b;
        if (r0 != 0) goto L_0x005f;
    L_0x0005:
        r0 = f953b044604460446;
        r1 = f951b0446044604460446;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f952b0446044604460446;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0019;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = 66;
        f953b044604460446 = r0;
        r0 = 39;
        f950b04460446044604460446 = r0;
    L_0x0019:
        r0 = new org.apache.http.params.BasicHttpParams;
        r0.<init>();
    L_0x001e:
        switch(r2) {
            case 0: goto L_0x0025;
            case 1: goto L_0x001e;
            default: goto L_0x0021;
        };
    L_0x0021:
        switch(r2) {
            case 0: goto L_0x0025;
            case 1: goto L_0x001e;
            default: goto L_0x0024;
        };
    L_0x0024:
        goto L_0x0021;
    L_0x0025:
        r1 = 5;
        org.apache.http.conn.params.ConnManagerParams.setMaxTotalConnections(r0, r1);
        r1 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r0, r1);
        r1 = new org.apache.http.conn.scheme.SchemeRegistry;
        r1.<init>();
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "http";
        r4 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();
        r5 = 80;
        r2.<init>(r3, r4, r5);
        r1.register(r2);
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "https";
        r4 = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
        r5 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
        r2.<init>(r3, r4, r5);
        r1.register(r2);
        r2 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
        r2.<init>(r0, r1);
        r1 = new org.apache.http.impl.client.DefaultHttpClient;
        r1.<init>(r2, r0);
        r6.f954b = r1;
    L_0x005f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.ImmersionHttpClient.a():void");
    }

    public static int m1067b0446044604460446() {
        return 1;
    }

    public static int m1068b044604460446() {
        return 26;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.immersion.hapticmediasdk.controllers.ImmersionHttpClient getHttpClient() {
        /*
        r0 = new com.immersion.hapticmediasdk.controllers.ImmersionHttpClient;
        r0.<init>();
        r0.m1066a();
    L_0x0008:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0011;
            case 1: goto L_0x0008;
            default: goto L_0x000c;
        };
    L_0x000c:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0011;
            default: goto L_0x0010;
        };
    L_0x0010:
        goto L_0x000c;
    L_0x0011:
        r1 = m1068b044604460446();
        r2 = f951b0446044604460446;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f952b0446044604460446;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0023;
            default: goto L_0x001f;
        };
    L_0x001f:
        r1 = 31;
        f951b0446044604460446 = r1;
    L_0x0023:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.ImmersionHttpClient.getHttpClient():com.immersion.hapticmediasdk.controllers.ImmersionHttpClient");
    }

    public HttpResponse executeDelete(String str, Map map, int i) throws Exception {
        HttpUriRequest httpDelete = new HttpDelete(str);
        if (((f953b044604460446 + f951b0446044604460446) * f953b044604460446) % f952b0446044604460446 != f950b04460446044604460446) {
            f953b044604460446 = 82;
            f950b04460446044604460446 = m1068b044604460446();
        }
        return m1065a(httpDelete, map, i);
    }

    public HttpResponse executeGet(String str, Map map, int i) throws Exception {
        int i2 = f953b044604460446;
        switch ((i2 * (m1067b0446044604460446() + i2)) % f952b0446044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f953b044604460446 = m1068b044604460446();
                f950b04460446044604460446 = m1068b044604460446();
                break;
        }
        try {
            try {
                return m1065a(new HttpGet(str), map, i);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public HttpResponse executePost(String str, Map map, int i) throws Exception {
        return m1065a(new HttpPost(str), map, i);
    }

    public HttpResponse executePostWithBody(String str, String str2, Map map, int i) throws Exception {
        try {
            HttpUriRequest httpPost = new HttpPost(str);
            try {
                HttpEntity stringEntity = new StringEntity(str2, HTTP.UTF_8);
                int i2 = f953b044604460446;
                switch ((i2 * (f951b0446044604460446 + i2)) % f952b0446044604460446) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    default:
                        f953b044604460446 = m1068b044604460446();
                        f950b04460446044604460446 = 81;
                        break;
                }
                httpPost.setEntity(stringEntity);
                return m1065a(httpPost, map, i);
            } catch (UnsupportedEncodingException e) {
                throw e;
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e22) {
            throw e22;
        }
    }

    public HttpParams getParams() {
        int i = f953b044604460446;
        switch ((i * (f951b0446044604460446 + i)) % f952b0446044604460446) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f953b044604460446 = 18;
                f950b04460446044604460446 = m1068b044604460446();
                break;
        }
        try {
            try {
                return this.f954b.getParams();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
