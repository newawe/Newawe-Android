package com.startapp.android.publish;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.p022h.StartAppSDK;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class JsInterface {
    private Runnable clickCallback;
    private Runnable closeCallback;
    protected Context mContext;
    private StartAppSDK params;
    private boolean processed;

    public JsInterface(Context context, Runnable closeCallback, StartAppSDK params) {
        this.processed = false;
        this.closeCallback = null;
        this.clickCallback = null;
        this.closeCallback = closeCallback;
        this.mContext = context;
        this.params = params;
    }

    public JsInterface(Context context, Runnable closeCallback, Runnable clickCallback, StartAppSDK params) {
        this(context, closeCallback, params);
        this.clickCallback = clickCallback;
    }

    @JavascriptInterface
    public void closeAd() {
        if (!this.processed) {
            this.processed = true;
            this.closeCallback.run();
        }
    }

    @JavascriptInterface
    public void openApp(String clickUrl, String intentPackageName, String intentDetails) {
        if (!(clickUrl == null || TextUtils.isEmpty(clickUrl))) {
            StartAppSDK.m3029b(this.mContext, clickUrl, this.params);
        }
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(intentPackageName);
        if (intentDetails != null) {
            try {
                JSONObject jSONObject = new JSONObject(intentDetails);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    launchIntentForPackage.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (Throwable e) {
                StartAppSDK.m2927a(6, "Couldn't parse intent details json!", e);
            }
        }
        try {
            this.mContext.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            com.startapp.android.publish.p010d.StartAppSDK.m2729a(this.mContext, com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.EXCEPTION, "JsInterface.openApp(): Couldn't start activity", e2.getMessage(), StartAppSDK.m2991a(clickUrl, null));
            StartAppSDK.m2926a(6, "Cannot find activity to handle url: [" + clickUrl + "]");
        }
        if (this.clickCallback != null) {
            this.clickCallback.run();
        }
    }
}
