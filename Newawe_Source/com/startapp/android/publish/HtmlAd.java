package com.startapp.android.publish;

import android.content.Context;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.p028a.StartAppSDK;

@Deprecated
/* compiled from: StartAppSDK */
public class HtmlAd extends StartAppSDK {
    public HtmlAd(Context context) {
        super(context, null);
    }

    protected void loadAds(AdPreferences adPreferences, AdEventListener callback) {
        new com.startapp.android.publish.p011e.StartAppSDK(this.context, this, adPreferences, callback).m2743c();
    }
}
