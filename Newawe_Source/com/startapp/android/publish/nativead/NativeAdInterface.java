package com.startapp.android.publish.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK;

/* compiled from: StartAppSDK */
public interface NativeAdInterface {
    StartAppSDK getCampaignAction();

    String getCategory();

    String getDescription();

    Bitmap getImageBitmap();

    String getImageUrl();

    String getInstalls();

    String getPackacgeName();

    float getRating();

    String getTitle();

    void sendClick(Context context);

    void sendImpression(Context context);
}
