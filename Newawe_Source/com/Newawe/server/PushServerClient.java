package com.Newawe.server;

import android.content.Context;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.server.BaseServerClient.OnRequestDoneListener;
import com.google.android.gcm.GCMRegistrar;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class PushServerClient extends BaseServerClient implements OnRequestDoneListener {

    enum RequestType {
        REGISTER_ID,
        UNREGISTER_ID,
        GET_PUSH_ACCOUNT
    }

    public PushServerClient(MainNavigationActivity activity) {
        super(activity);
    }

    public PushServerClient(Context context, WebWidgetConfiguration config) {
        super(context, config);
    }

    public void sendRegisteredId(String regId) {
        sendRequestAsync(this._context.getResources().getString(C0186R.string.pushDomainUrl) + "add_register_id.php" + "?id=" + String.valueOf(this._config.getApplicationId()) + "&guid=" + this._config.getAppGuid() + "&regId=" + regId, RequestType.REGISTER_ID.ordinal(), this);
    }

    public void sendUnregisteredId(String regId) {
        sendRequestAsync(this._context.getResources().getString(C0186R.string.pushDomainUrl) + "remove_register_id.php" + "?id=" + String.valueOf(this._config.getApplicationId()) + "&guid=" + this._config.getAppGuid() + "&regId=" + regId, RequestType.UNREGISTER_ID.ordinal(), this);
    }

    public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return;
        }
        if (tag == RequestType.REGISTER_ID.ordinal()) {
            GCMRegistrar.clearRegistrationId(this._context);
        } else if (tag == RequestType.GET_PUSH_ACCOUNT.ordinal()) {
            _savePushAccount(response);
        }
    }

    private void _savePushAccount(HttpResponse response) {
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                this._config.saveNewPushAccount(new JSONObject(EntityUtils.toString(entity)).getString("accountName"), this._context);
                GCMRegistrar.register(this._context, pushAccount);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void loadPushAccount() {
        sendRequestAsync(this._context.getResources().getString(C0186R.string.pushDomainUrl) + "register.php" + "?id=" + String.valueOf(this._config.getApplicationId()) + "&guid=" + this._config.getAppGuid(), RequestType.GET_PUSH_ACCOUNT.ordinal(), this);
    }
}
