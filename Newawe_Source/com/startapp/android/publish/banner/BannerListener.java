package com.startapp.android.publish.banner;

import android.view.View;

/* compiled from: StartAppSDK */
public interface BannerListener {
    void onClick(View view);

    void onFailedToReceiveAd(View view);

    void onReceiveAd(View view);
}
