package com.Newawe.pull;

import android.content.Context;
import com.Newawe.C0186R;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.configuration.WebWidgetConfigurationManager;
import java.net.URLEncoder;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class StatServerClient {
    private WebWidgetConfiguration _config;
    private Context _context;

    /* renamed from: com.Newawe.pull.StatServerClient.1 */
    class C02501 extends Thread {
        final /* synthetic */ String val$requestUrl;

        C02501(String str) {
            this.val$requestUrl = str;
        }

        public void run() {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(this.val$requestUrl);
                httpGet.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache,no-store");
                client.execute(httpGet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public StatServerClient(Context context) {
        this._context = null;
        this._context = context;
        try {
            this._config = WebWidgetConfigurationManager.getInstance(this._context).loadConfiguration(this._context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageAcceptedAsync(String messageUrl) {
        try {
            _sendRequestAsync(_createStatUrl("request", messageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPushReceivedAsync(String messageUrl) {
        try {
            _sendRequestAsync(_createStatPushUrl("request", messageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void senMessageClickedAsync(String messageUrl) {
        try {
            _sendRequestAsync(_createStatUrl("click", messageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String _createStatUrl(String action, String messageUrl) {
        return this._context.getResources().getString(C0186R.string.statDomainUrl) + "pull.php?" + "a=" + action + "&url=" + URLEncoder.encode(messageUrl) + "&app=" + this._config.getApplicationId() + "&v=" + URLEncoder.encode(this._context.getString(C0186R.string.platformVersion)) + "&guid=" + URLEncoder.encode(this._config.getAppGuid());
    }

    private String _createStatPushUrl(String action, String messageUrl) {
        return this._context.getResources().getString(C0186R.string.statDomainUrl) + "push.php?" + "a=" + action + "&url=" + URLEncoder.encode(messageUrl) + "&app=" + this._config.getApplicationId() + "&v=" + URLEncoder.encode(this._context.getString(C0186R.string.platformVersion)) + "&guid=" + URLEncoder.encode(this._config.getAppGuid());
    }

    private void _sendRequestAsync(String requestUrl) {
        new C02501(requestUrl).start();
    }
}
