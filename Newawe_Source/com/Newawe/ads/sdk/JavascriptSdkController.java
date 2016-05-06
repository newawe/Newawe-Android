package com.Newawe.ads.sdk;

import android.webkit.JavascriptInterface;
import com.Newawe.Factory;
import com.Newawe.javascriptinterface.BaseSecureJsInterface;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class JavascriptSdkController extends BaseSecureJsInterface {
    private HashMap<String, String> jsonStringToMap(String json) {
        HashMap<String, String> map = new HashMap();
        try {
            JSONObject jObject = new JSONObject(json);
            Iterator<?> keys = jObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                map.put(key, jObject.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return map;
    }

    @JavascriptInterface
    public void startSessionForSdk(String sdkName, String jsonExtras) {
        SdkWrapper wrapper = SdkWrapperFactory.getInstance().getWrapperByKey(sdkName);
        wrapper.addExtras(jsonStringToMap(jsonExtras));
        wrapper.startSession(Factory.getInstance().getMainNavigationActivity());
    }

    @JavascriptInterface
    public void stopSessionForSdk(String sdkName) {
        SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).stopSession();
    }

    @JavascriptInterface
    public boolean isSdkActive(String sdkName) {
        return SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).isActive();
    }

    @JavascriptInterface
    public void showFSBannerForSdk(String sdkName) {
        SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).showFsBanner();
    }

    @JavascriptInterface
    public void setFSBannerCallbacksForSdk(String sdkName, String onAdReadyJSCallback, String onAdFailedJSCallback, String onAdClickedJSCallback, String onAdCLosedJSCallback) {
    }
}
