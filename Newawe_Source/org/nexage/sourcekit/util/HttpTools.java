package org.nexage.sourcekit.util;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;

public class HttpTools {
    private static final String TAG;

    /* renamed from: org.nexage.sourcekit.util.HttpTools.1 */
    static class C09341 extends Thread {
        final /* synthetic */ String val$url;

        C09341(String str) {
            this.val$url = str;
        }

        public void run() {
            HttpURLConnection conn = null;
            try {
                VASTLog.m3662v(HttpTools.TAG, "connection to URL:" + this.val$url);
                URL httpUrl = new URL(this.val$url);
                HttpURLConnection.setFollowRedirects(true);
                conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestProperty(HTTP.CONN_DIRECTIVE, "close");
                conn.setRequestMethod(HttpGet.METHOD_NAME);
                VASTLog.m3662v(HttpTools.TAG, "response code:" + conn.getResponseCode() + ", for URL:" + this.val$url);
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                VASTLog.m3663w(HttpTools.TAG, this.val$url + ": " + e2.getMessage() + ":" + e2.toString());
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (Exception e4) {
                    }
                }
            }
        }
    }

    static {
        TAG = HttpTools.class.getName();
    }

    public static void httpGetURL(String url) {
        if (TextUtils.isEmpty(url)) {
            VASTLog.m3663w(TAG, "url is null or empty");
        } else {
            new C09341(url).start();
        }
    }
}
