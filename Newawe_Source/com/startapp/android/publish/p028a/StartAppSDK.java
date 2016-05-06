package com.startapp.android.publish.p028a;

import android.content.Context;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;

/* renamed from: com.startapp.android.publish.a.l */
public class StartAppSDK extends StartAppSDK {
    private static final long serialVersionUID = 1;

    public StartAppSDK(Context context) {
        super(context, Placement.INAPP_OVERLAY);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener callback) {
        return super.load(adPreferences, callback, false);
    }

    protected void loadAds(AdPreferences adPreferences, AdEventListener callback) {
        new com.startapp.android.publish.p011e.StartAppSDK(this.context, this, adPreferences, callback).m2743c();
    }
}
