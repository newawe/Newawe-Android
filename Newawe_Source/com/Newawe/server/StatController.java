package com.Newawe.server;

import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

public class StatController {
    public static final String KEY_ADCOLONY_INTERSTITIAL_CLICK_URL = "adcolony_interstitial_click_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_IMPRESSION_URL = "adcolony_interstitial_impression_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_NO_FILL_URL = "adcolony_interstitial_no_fill_url";
    public static final String KEY_ADCOLONY_INTERSTITIAL_REQUEST_URL = "adcolony_interstitial_request_url";
    public static final String KEY_ADTAPSY_INTERSTITIAL_CLICK_URL = "adtapsy_interstitial_click_url";
    public static final String KEY_ADTAPSY_INTERSTITIAL_IMPRESSION_URL = "adtapsy_interstitial_impression_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_CLICK_URL = "chartboost_interstitial_click_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_IMPRESSION_URL = "chartboost_interstitial_impression_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_NO_FILL_URL = "chartboost_interstitial_no_fill_url";
    public static final String KEY_CHARTBOOST_INTERSTITIAL_REQUEST_URL = "chartboost_interstitial_request_url";
    public static final String KEY_CLICK_APP_ALREADY_INSTALLED = "click_app_already_installed";
    public static final String KEY_CLICK_BANNER_BACK_KEY_PRESSED = "click_banner_back_key_pressed";
    public static final String KEY_CLICK_BROWSER_BACK_KEY_PRESSED = "click_browser_back_key_pressed";
    public static final String KEY_CLICK_CAN_NOT_START_DOWNLOAD = "click_can_not_start_download";
    public static final String KEY_CLICK_CROSS_BANNER = "click_cross_banner";
    public static final String KEY_CLICK_CROSS_MINI_BROWSER = "click_cross_mini_browser";
    public static final String KEY_CLICK_EXTERNAL_BROWSER = "click_external_browser";
    public static final String KEY_CLICK_FINISH_DOWNLOAD = "click_finish_download";
    public static final String KEY_CLICK_FINISH_EMPTY_HTML = "click_finish_epmty_html";
    public static final String KEY_CLICK_FINISH_HTML = "click_finish_html";
    public static final String KEY_CLICK_FINISH_MARKET = "click_finish_market";
    public static final String KEY_CLICK_HTML_TAP_START = "click_html_tap_start";
    public static final String KEY_CLICK_LOADING_ERROR = "click_loading_error";
    public static final String KEY_CLICK_NO_MARKET_ON_DEVICE = "click_no_market_on_device";
    public static final String KEY_CLICK_REDIRECT_START = "click_redirect_start";
    public static final String KEY_CLICK_TIMER_BANNER = "click_timer_banner";
    public static final String KEY_CLICK_WEBWIEW_TAP = "click_webview_tap";
    public static final String KEY_GET_PARAM_DETAILS = "details";
    public static final String KEY_GET_PARAM_URL = "url";
    public static final String KEY_INIT_ERROR = "init_error";
    public static final String KEY_INMOBI_INTERSTITIAL_CLICK_URL = "inmobi_interstitial_click_url";
    public static final String KEY_INMOBI_INTERSTITIAL_FAIL_URL = "inmobi_interstitial_fail_url";
    public static final String KEY_INMOBI_INTERSTITIAL_IMPRESSION_URL = "inmobi_interstitial_impression_url";
    public static final String KEY_INMOBI_INTERSTITIAL_NO_FILL_URL = "inmobi_interstitial_no_fill_url";
    public static final String KEY_INMOBI_INTERSTITIAL_REQUEST_URL = "inmobi_interstitial_request_url";
    public static final String KEY_INMOBI_INTERSTITIAL_SUCCEEDED_URL = "inmobi_interstitial_succeeded_url";
    public static final String KEY_NATIVE_INTERSTITIAL_CLICK_URL = "native_interstitial_click_url";
    public static final String KEY_NATIVE_INTERSTITIAL_IMPRESSION_URL = "native_interstitial_impression_url";
    private static StatController _instance;
    private HashMap<String, String> items;

    /* renamed from: com.Newawe.server.StatController.1 */
    class C02561 extends Thread {
        final /* synthetic */ HashMap val$details;
        final /* synthetic */ String val$url;

        C02561(String str, HashMap hashMap) {
            this.val$url = str;
            this.val$details = hashMap;
        }

        public void run() {
            HttpClient client = new DefaultHttpClient(new BasicHttpParams());
            String newUrl = this.val$url;
            if (this.val$details != null) {
                Builder builder = Uri.parse(this.val$url).buildUpon();
                for (Entry<String, String> detail : this.val$details.entrySet()) {
                    builder.appendQueryParameter((String) detail.getKey(), (String) detail.getValue());
                }
                newUrl = builder.build().toString();
            }
            HttpGet httpGet = new HttpGet(newUrl);
            httpGet.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache,no-store");
            try {
                client.execute(httpGet);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public StatController() {
        this.items = null;
    }

    static {
        _instance = null;
    }

    public static synchronized StatController getInstance() {
        StatController statController;
        synchronized (StatController.class) {
            if (_instance == null) {
                _instance = new StatController();
            }
            statController = _instance;
        }
        return statController;
    }

    public void init(HashMap<String, String> values) {
        clear();
        this.items = values;
    }

    public void clear() {
        this.items = null;
    }

    public String get(String key) {
        return (String) this.items.get(key);
    }

    public void sendRequestAsyncByKey(String key, HashMap<String, String> details) {
        if (isInitialized()) {
            String url = get(key);
            if (url == null) {
                Log.d("StatController", "Stat url not set, skipping stat request on: " + key);
                return;
            } else {
                new C02561(url, details).start();
                return;
            }
        }
        Log.d("StatController", "StatController not initialized, skipping stat request on: " + key);
    }

    public void sendRequestAsyncByKey(String key) {
        sendRequestAsyncByKey(key, null);
    }

    public boolean isInitialized() {
        return this.items != null;
    }
}
