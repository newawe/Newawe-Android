package com.startapp.android.publish.p011e;

import android.content.Context;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;

/* renamed from: com.startapp.android.publish.e.o */
public class StartAppSDK extends StartAppSDK {
    public StartAppSDK(Context context, com.startapp.android.publish.p028a.StartAppSDK startAppSDK, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, startAppSDK, adPreferences, adEventListener, Placement.INAPP_SPLASH, true);
    }
}
