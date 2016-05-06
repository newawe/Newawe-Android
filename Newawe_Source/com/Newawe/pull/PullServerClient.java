package com.Newawe.pull;

import android.content.Context;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.configuration.WebWidgetConfigurationManager;
import com.Newawe.storage.DatabaseOpenHelper;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PullServerClient {
    private final String DOMAIN_URL_KEY;
    private final String LAST_REQUEST_TIME_KEY;
    private final int OK_RESPONSE;
    private Context _context;
    private onMessageReceivedListener _listener;

    /* renamed from: com.Newawe.pull.PullServerClient.1 */
    class C02491 extends Thread {
        final /* synthetic */ onMessageReceivedListener val$onResponseListener;
        final /* synthetic */ String val$requestUrl;

        C02491(String str, onMessageReceivedListener com_Newawe_pull_PullServerClient_onMessageReceivedListener) {
            this.val$requestUrl = str;
            this.val$onResponseListener = com_Newawe_pull_PullServerClient_onMessageReceivedListener;
        }

        public void run() {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(this.val$requestUrl);
                httpGet.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache,no-store");
                HttpResponse response = client.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        JSONObject json = new JSONObject(EntityUtils.toString(entity));
                        PullServerClient.this._setLastRequestTime(PullServerClient.this._context, json.getLong("timestamp"));
                        JSONArray jsonViewUrls = json.getJSONArray("data");
                        if (jsonViewUrls.length() > 0) {
                            Response[] responses = new Response[jsonViewUrls.length()];
                            for (int i = 0; i < jsonViewUrls.length(); i++) {
                                String string;
                                JSONObject row = jsonViewUrls.getJSONObject(i);
                                responses[i] = new Response();
                                responses[i].url = row.getString("data");
                                responses[i].message = row.getString("message");
                                responses[i].title = row.getString(DatabaseOpenHelper.HISTORY_ROW_TITLE);
                                Response response2 = responses[i];
                                if (row.has("launchMain")) {
                                    string = row.getString("launchMain");
                                } else {
                                    string = null;
                                }
                                response2.launchMain = string;
                            }
                            this.val$onResponseListener.onMessage(responses);
                            return;
                        }
                        return;
                    }
                    return;
                }
                PullServerClient.this._rotateDomainUrls(PullServerClient.this._context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class Response {
        String launchMain;
        String message;
        String title;
        String url;

        public Response() {
            this.url = StringUtils.EMPTY;
            this.title = StringUtils.EMPTY;
            this.message = StringUtils.EMPTY;
            this.launchMain = StringUtils.EMPTY;
        }
    }

    public interface onMessageReceivedListener {
        void onMessage(Response[] responseArr);
    }

    public PullServerClient(Context context, onMessageReceivedListener listener) {
        this.OK_RESPONSE = HttpStatus.SC_OK;
        this.LAST_REQUEST_TIME_KEY = "last_pull_request_time";
        this.DOMAIN_URL_KEY = "domain_url";
        this._context = null;
        this._context = context;
        this._listener = listener;
    }

    public void tryLoadMessageAsync() {
        try {
            WebWidgetConfiguration config = WebWidgetConfigurationManager.getInstance(this._context).loadConfiguration(this._context);
            String pullServerUrl = _getDomainUrl(this._context);
            String version = this._context.getString(C0186R.string.platformVersion);
            _sendRequestAsync(pullServerUrl + "get_message.php?app_id=" + config.getApplicationId() + "&name=" + URLEncoder.encode(config.getWidgetName(), "utf-8") + "&last_request_timestamp=" + _getLastRequestTime(this._context) + "&guid=" + URLEncoder.encode(config.getAppGuid()) + "&v=" + URLEncoder.encode(version), this._listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _sendRequestAsync(String requestUrl, onMessageReceivedListener onResponseListener) {
        new C02491(requestUrl, onResponseListener).start();
    }

    private long _getLastRequestTime(Context context) {
        return context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getLong("last_pull_request_time", System.currentTimeMillis() / 1000);
    }

    private void _setLastRequestTime(Context context, long newTime) {
        context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit().putLong("last_pull_request_time", newTime).commit();
    }

    private void _rotateDomainUrls(Context context) {
        String currentUrl = _getDomainUrl(context);
        String url1 = this._context.getResources().getString(C0186R.string.pullDomainUrl1);
        String url2 = this._context.getResources().getString(C0186R.string.pullDomainUrl2);
        String url3 = this._context.getResources().getString(C0186R.string.pullDomainUrl3);
        if (currentUrl.equalsIgnoreCase(url1)) {
            _setDomainUrl(context, url2);
        } else if (currentUrl.equalsIgnoreCase(url2)) {
            _setDomainUrl(context, url3);
        } else if (currentUrl.equalsIgnoreCase(url3)) {
            _setDomainUrl(context, url1);
        } else {
            _setDomainUrl(context, url1);
        }
    }

    private String _getDomainUrl(Context context) {
        return context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getString("domain_url", this._context.getResources().getString(C0186R.string.pullDomainUrl1));
    }

    private void _setDomainUrl(Context context, String newUrl) {
        context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit().putString("domain_url", newUrl).commit();
    }
}
