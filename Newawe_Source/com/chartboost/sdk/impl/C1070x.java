package com.chartboost.sdk.impl;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/* renamed from: com.chartboost.sdk.impl.x */
public class C1070x implements C0484z {
    protected final HttpClient f3636a;

    /* renamed from: com.chartboost.sdk.impl.x.a */
    public static final class C1345a extends HttpEntityEnclosingRequestBase {
        public C1345a(String str) {
            setURI(URI.create(str));
        }
    }

    public C1070x(HttpClient httpClient) {
        this.f3636a = httpClient;
    }

    private static void m4110a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    public HttpResponse m4112a(C0467l<?> c0467l, Map<String, String> map) throws IOException, C1031a {
        HttpUriRequest b = C1070x.m4111b(c0467l, map);
        C1070x.m4110a(b, (Map) map);
        C1070x.m4110a(b, c0467l.m799i());
        m4113a(b);
        HttpParams params = b.getParams();
        int t = c0467l.m810t();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, t);
        return this.f3636a.execute(b);
    }

    static HttpUriRequest m4111b(C0467l<?> c0467l, Map<String, String> map) throws C1031a {
        HttpEntityEnclosingRequestBase httpPost;
        switch (c0467l.m779a()) {
            case DTDGrammar.TOP_LEVEL_SCOPE /*-1*/:
                byte[] m = c0467l.m803m();
                if (m == null) {
                    return new HttpGet(c0467l.m794d());
                }
                HttpUriRequest httpPost2 = new HttpPost(c0467l.m794d());
                httpPost2.addHeader(HTTP.CONTENT_TYPE, c0467l.m802l());
                httpPost2.setEntity(new ByteArrayEntity(m));
                return httpPost2;
            case DurationDV.DURATION_TYPE /*0*/:
                return new HttpGet(c0467l.m794d());
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                httpPost = new HttpPost(c0467l.m794d());
                httpPost.addHeader(HTTP.CONTENT_TYPE, c0467l.m806p());
                C1070x.m4109a(httpPost, (C0467l) c0467l);
                return httpPost;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                httpPost = new HttpPut(c0467l.m794d());
                httpPost.addHeader(HTTP.CONTENT_TYPE, c0467l.m806p());
                C1070x.m4109a(httpPost, (C0467l) c0467l);
                return httpPost;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return new HttpDelete(c0467l.m794d());
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return new HttpHead(c0467l.m794d());
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                return new HttpOptions(c0467l.m794d());
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                return new HttpTrace(c0467l.m794d());
            case ConnectionResult.NETWORK_ERROR /*7*/:
                httpPost = new C1345a(c0467l.m794d());
                httpPost.addHeader(HTTP.CONTENT_TYPE, c0467l.m806p());
                C1070x.m4109a(httpPost, (C0467l) c0467l);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    private static void m4109a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, C0467l<?> c0467l) throws C1031a {
        byte[] q = c0467l.m807q();
        if (q != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(q));
        }
    }

    protected void m4113a(HttpUriRequest httpUriRequest) throws IOException {
    }
}
