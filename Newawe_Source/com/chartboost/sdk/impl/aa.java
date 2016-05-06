package com.chartboost.sdk.impl;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HTTP;

public class aa implements C0484z {
    private final C0373a f3428a;
    private final SSLSocketFactory f3429b;

    /* renamed from: com.chartboost.sdk.impl.aa.a */
    public interface C0373a {
        String m489a(String str);
    }

    public aa() {
        this(null);
    }

    public aa(C0373a c0373a) {
        this(c0373a, null);
    }

    public aa(C0373a c0373a, SSLSocketFactory sSLSocketFactory) {
        this.f3428a = c0373a;
        this.f3429b = sSLSocketFactory;
    }

    public HttpResponse m3806a(C0467l<?> c0467l, Map<String, String> map) throws IOException, C1031a {
        String a;
        String d = c0467l.m794d();
        HashMap hashMap = new HashMap();
        hashMap.putAll(c0467l.m799i());
        hashMap.putAll(map);
        if (this.f3428a != null) {
            a = this.f3428a.m489a(d);
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + d);
            }
        }
        a = d;
        HttpURLConnection a2 = m3801a(new URL(a), (C0467l) c0467l);
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        m3803a(a2, (C0467l) c0467l);
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
        basicHttpResponse.setEntity(m3802a(a2));
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity m3802a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection m3805a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private HttpURLConnection m3801a(URL url, C0467l<?> c0467l) throws IOException {
        HttpURLConnection a = m3805a(url);
        int t = c0467l.m810t();
        a.setConnectTimeout(t);
        a.setReadTimeout(t);
        a.setUseCaches(false);
        a.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.f3429b != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.f3429b);
        }
        return a;
    }

    static void m3803a(HttpURLConnection httpURLConnection, C0467l<?> c0467l) throws IOException, C1031a {
        switch (c0467l.m779a()) {
            case DTDGrammar.TOP_LEVEL_SCOPE /*-1*/:
                byte[] m = c0467l.m803m();
                if (m != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                    httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, c0467l.m802l());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(m);
                    dataOutputStream.close();
                }
            case DurationDV.DURATION_TYPE /*0*/:
                httpURLConnection.setRequestMethod(HttpGet.METHOD_NAME);
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                m3804b(httpURLConnection, c0467l);
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                httpURLConnection.setRequestMethod(HttpPut.METHOD_NAME);
                m3804b(httpURLConnection, c0467l);
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                httpURLConnection.setRequestMethod(HttpDelete.METHOD_NAME);
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                httpURLConnection.setRequestMethod(HttpHead.METHOD_NAME);
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                httpURLConnection.setRequestMethod(HttpOptions.METHOD_NAME);
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                httpURLConnection.setRequestMethod(HttpTrace.METHOD_NAME);
            case ConnectionResult.NETWORK_ERROR /*7*/:
                httpURLConnection.setRequestMethod(HttpPatch.METHOD_NAME);
                m3804b(httpURLConnection, c0467l);
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void m3804b(HttpURLConnection httpURLConnection, C0467l<?> c0467l) throws IOException, C1031a {
        byte[] q = c0467l.m807q();
        if (q != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HTTP.CONTENT_TYPE, c0467l.m806p());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(q);
            dataOutputStream.close();
        }
    }
}
