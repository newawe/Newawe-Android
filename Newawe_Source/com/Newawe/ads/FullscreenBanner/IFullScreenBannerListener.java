package com.Newawe.ads.FullscreenBanner;

public interface IFullScreenBannerListener {
    void onAdClosed();

    void onAdFailedToLoad();

    void onLoadFinished();

    void onLoadStarted();
}
