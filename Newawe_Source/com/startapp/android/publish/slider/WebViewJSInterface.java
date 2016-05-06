package com.startapp.android.publish.slider;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.p022h.StartAppSDK;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class WebViewJSInterface {
    private Context mContext;
    private String trackingUrl;

    public WebViewJSInterface(Context c) {
        this.mContext = c;
    }

    @JavascriptInterface
    public void processHTML(String html) {
        this.trackingUrl = substringBetween(html, "@tracking@", "@tracking@");
        if (this.trackingUrl != null) {
            StartAppSDK.m2912b(this.mContext, "trackingUrl", this.trackingUrl);
        }
    }

    @JavascriptInterface
    public void processServerEvent(String html) {
        if (StartAppSDK.m2903a(this.mContext, "slideEvent", Boolean.valueOf(false)).booleanValue() && !StartAppSDK.m2903a(this.mContext, "trackingEvent", Boolean.valueOf(false)).booleanValue()) {
            this.trackingUrl = substringBetween(html, "@tracking@", "@tracking@");
            if (this.trackingUrl != null) {
                StartAppSDK.m2912b(this.mContext, "trackingUrl", this.trackingUrl);
                new StartAppSDK(this.mContext).m3191a(StringUtils.EMPTY);
            }
        }
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    private String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int indexOf = str.indexOf(open);
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(close, open.length() + indexOf);
        if (indexOf2 != -1) {
            return str.substring(open.length() + indexOf, indexOf2);
        }
        return null;
    }
}
