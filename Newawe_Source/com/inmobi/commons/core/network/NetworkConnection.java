package com.inmobi.commons.core.network;

import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.inmobi.commons.core.network.b */
class NetworkConnection {
    private static final String f1243a;
    private NetworkRequest f1244b;
    private HttpURLConnection f1245c;

    static {
        f1243a = NetworkConnection.class.getName();
    }

    public NetworkConnection(NetworkRequest networkRequest) {
        this.f1244b = networkRequest;
    }

    public NetworkResponse m1429a() {
        this.f1244b.m1399a();
        NetworkResponse networkResponse;
        if (NetworkUtils.m1479a()) {
            try {
                String b = m1426b();
                Logger.m1440a(InternalLogLevel.INTERNAL, f1243a, "Url: " + b);
                this.f1245c = m1423a(b);
                if (!this.f1244b.m1413l()) {
                    this.f1245c.setInstanceFollowRedirects(false);
                }
                if (this.f1244b.m1414m() == RequestType.POST) {
                    m1427b(this.f1244b.m1412k());
                }
                return m1428c();
            } catch (IOException e) {
                IOException iOException = e;
                networkResponse = new NetworkResponse(this.f1244b);
                networkResponse.m1430a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, iOException.getLocalizedMessage()));
                iOException.printStackTrace();
                return networkResponse;
            } catch (IllegalArgumentException e2) {
                IllegalArgumentException illegalArgumentException = e2;
                networkResponse = new NetworkResponse(this.f1244b);
                networkResponse.m1430a(new NetworkError(ErrorCode.HTTP_BAD_REQUEST, "The URL is malformed:" + ErrorCode.HTTP_BAD_REQUEST.toString()));
                illegalArgumentException.printStackTrace();
                return networkResponse;
            }
        }
        networkResponse = new NetworkResponse(this.f1244b);
        networkResponse.m1430a(new NetworkError(ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network unavailable."));
        return networkResponse;
    }

    private String m1426b() {
        String h = this.f1244b.m1409h();
        if (!h.contains("?")) {
            h = h + "?";
        }
        return h + this.f1244b.m1411j();
    }

    private HttpURLConnection m1423a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        m1425a(httpURLConnection);
        return httpURLConnection;
    }

    private void m1425a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.f1244b.m1415n());
        httpURLConnection.setReadTimeout(this.f1244b.m1416o());
        httpURLConnection.setUseCaches(false);
        if (this.f1244b.m1410i() != null) {
            for (String str : this.f1244b.m1410i().keySet()) {
                httpURLConnection.setRequestProperty(str, (String) this.f1244b.m1410i().get(str));
            }
        }
        RequestType m = this.f1244b.m1414m();
        httpURLConnection.setRequestMethod(m.toString());
        if (m != RequestType.GET) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    private void m1427b(String str) throws IOException {
        Throwable th;
        this.f1245c.setRequestProperty(HTTP.CONTENT_LEN, Integer.toString(str.length()));
        this.f1245c.setRequestProperty(HTTP.CONTENT_TYPE, URLEncodedUtils.CONTENT_TYPE);
        Closeable bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.f1245c.getOutputStream()));
            try {
                bufferedWriter.write(str);
                m1424a(bufferedWriter);
            } catch (Throwable th2) {
                th = th2;
                m1424a(bufferedWriter);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            m1424a(bufferedWriter);
            throw th;
        }
    }

    private NetworkResponse m1428c() {
        Throwable th;
        NetworkResponse networkResponse = new NetworkResponse(this.f1244b);
        Closeable closeable = null;
        try {
            int responseCode = this.f1245c.getResponseCode();
            Logger.m1440a(InternalLogLevel.INTERNAL, f1243a, this.f1244b.m1409h() + "Response code: " + responseCode);
            if (responseCode == HttpStatus.SC_OK) {
                try {
                    if (!this.f1244b.m1408g() || ((long) this.f1245c.getContentLength()) <= this.f1244b.m1407f()) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f1245c.getInputStream(), HTTP.UTF_8));
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuilder.append(readLine).append(LineSeparator.Web);
                            }
                            networkResponse.m1431a(stringBuilder.toString());
                            networkResponse.m1432a(this.f1245c.getHeaderFields());
                            Object obj = bufferedReader;
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = bufferedReader;
                            this.f1245c.disconnect();
                            m1424a(closeable);
                            throw th;
                        }
                    }
                    networkResponse.m1430a(new NetworkError(ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size"));
                } catch (Throwable th3) {
                    th = th3;
                    this.f1245c.disconnect();
                    m1424a(closeable);
                    throw th;
                }
            }
            ErrorCode fromValue = ErrorCode.fromValue(responseCode);
            if (fromValue == null) {
                fromValue = ErrorCode.UNKNOWN_ERROR;
            }
            networkResponse.m1430a(new NetworkError(fromValue, "HTTP:" + responseCode));
            networkResponse.m1432a(this.f1245c.getHeaderFields());
            this.f1245c.disconnect();
            m1424a(closeable);
        } catch (SocketTimeoutException e) {
            networkResponse.m1430a(new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString()));
            e.printStackTrace();
        } catch (IOException e2) {
            networkResponse.m1430a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString()));
            e2.printStackTrace();
        } catch (OutOfMemoryError e3) {
            networkResponse.m1430a(new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString()));
            e3.printStackTrace();
        }
        try {
            if (this.f1244b.m1417p()) {
                String e4 = this.f1244b.m1406e(networkResponse.m1434b());
                if (e4 == null) {
                    networkResponse.m1430a(new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response."));
                } else {
                    networkResponse.m1431a(e4);
                }
            }
        } catch (IllegalArgumentException e5) {
            e5.printStackTrace();
            networkResponse.m1430a(new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response."));
        }
        return networkResponse;
    }

    private void m1424a(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }
}
