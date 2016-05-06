package com.Newawe.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.Newawe.C0186R;
import com.Newawe.configuration.ConfigResolver;
import com.Newawe.configuration.WebWidgetConfiguration;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;

public class CampaignReceiver extends BroadcastReceiver {
    static final String TAG;

    enum RequestType {
        SOURCE,
        REFERRER
    }

    static {
        TAG = CampaignReceiver.class.getSimpleName();
    }

    public void onReceive(Context context, Intent intent) {
        WebWidgetConfiguration config = ConfigResolver.resolveConfig(context);
        BaseServerClient client = new BaseServerClient(context, config);
        String statDomain = context.getResources().getString(C0186R.string.statDomainUrl);
        String statSubUrl = _getStatParametersUrl(config, context);
        client.sendRequestAsync(statDomain + "source.php?source_id=1&" + statSubUrl, RequestType.SOURCE.ordinal(), null);
        String referrer = intent.getStringExtra("referrer");
        if (referrer != null) {
            try {
                referrer = URLEncoder.encode(referrer, HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            client.sendRequestAsync(statDomain + "referrer.php?referrer=" + referrer + "&" + statSubUrl, RequestType.REFERRER.ordinal(), null);
            return;
        }
        Log.i(TAG, "referrer is null");
    }

    private String _getStatParametersUrl(WebWidgetConfiguration config, Context context) {
        int wid = config.getApplicationId();
        String guid = config.getAppGuid();
        String v = context.getString(C0186R.string.platformVersion);
        try {
            guid = URLEncoder.encode(guid, HTTP.UTF_8);
            v = URLEncoder.encode(v, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "wid=" + wid + "&v=" + v + "&guid=" + guid;
    }
}
