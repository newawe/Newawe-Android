package com.Newawe.server;

import android.content.Context;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.WebWidgetConfiguration;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class BaseServerClient {
    protected static final int CONNECTION_TIMEOUT = 30000;
    protected static final int FORBIDDEN_RESPONSE = 403;
    protected static final int OK_RESPONSE = 200;
    protected static final int SOCKET_TIMEOUT = 30000;
    protected MainNavigationActivity _activity;
    protected WebWidgetConfiguration _config;
    protected Context _context;
    protected HttpParams _httpParameters;

    /* renamed from: com.Newawe.server.BaseServerClient.1 */
    class C02551 extends Thread {
        final /* synthetic */ OnRequestDoneListener val$onResponseListener;
        final /* synthetic */ String val$requestUrl;
        final /* synthetic */ int val$tag;

        C02551(String str, OnRequestDoneListener onRequestDoneListener, int i) {
            this.val$requestUrl = str;
            this.val$onResponseListener = onRequestDoneListener;
            this.val$tag = i;
        }

        public void run() {
            try {
                HttpClient client = new DefaultHttpClient(BaseServerClient.this._httpParameters);
                HttpGet httpGet = new HttpGet(this.val$requestUrl);
                httpGet.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache,no-store");
                this.val$onResponseListener.onRequestDone(this.val$requestUrl, this.val$tag, client.execute(httpGet));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnRequestDoneListener {
        void onRequestDone(String str, int i, HttpResponse httpResponse);
    }

    public BaseServerClient(MainNavigationActivity activity) {
        this._activity = activity;
        this._context = activity;
        this._config = activity.getConfig();
        this._httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(this._httpParameters, SOCKET_TIMEOUT);
        HttpConnectionParams.setSoTimeout(this._httpParameters, SOCKET_TIMEOUT);
    }

    public BaseServerClient(Context context, WebWidgetConfiguration config) {
        this._activity = null;
        this._context = context;
        this._config = config;
        this._httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(this._httpParameters, SOCKET_TIMEOUT);
        HttpConnectionParams.setSoTimeout(this._httpParameters, SOCKET_TIMEOUT);
    }

    public void sendRequestAsync(String requestUrl, int tag, OnRequestDoneListener onResponseListener) {
        new C02551(requestUrl, onResponseListener, tag).start();
    }

    public String sendRequestSync(String requestURL) {
        try {
            HttpClient client = new DefaultHttpClient(this._httpParameters);
            HttpUriRequest httpGet = new HttpGet(requestURL);
            httpGet.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache,no-store");
            return (String) client.execute(httpGet, new BasicResponseHandler());
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public boolean getAvailabilityStatus(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url + "&test=1").openConnection();
            con.setRequestMethod(HttpHead.METHOD_NAME);
            if (con.getResponseCode() != OK_RESPONSE) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, List<String>> loadHeaders(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url + "&test=1").openConnection();
            con.setRequestMethod(HttpHead.METHOD_NAME);
            int iResp = con.getResponseCode();
            Map<String, List<String>> resp = con.getHeaderFields();
            if (iResp != OK_RESPONSE) {
                return null;
            }
            return resp;
        } catch (Exception e) {
            return null;
        }
    }
}
