package com.startapp.android.publish;

import com.startapp.android.publish.banner.BannerListener;

/* compiled from: StartAppSDK */
public interface BannerInterface {
    void hideBanner();

    void setBannerListener(BannerListener bannerListener);

    void showBanner();
}
