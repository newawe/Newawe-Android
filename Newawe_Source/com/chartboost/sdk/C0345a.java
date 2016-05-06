package com.chartboost.sdk;

import android.app.Activity;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

/* renamed from: com.chartboost.sdk.a */
public interface C0345a {
    void didCacheInPlay(String str);

    void didCacheInterstitial(String str);

    void didCacheMoreApps(String str);

    void didCacheRewardedVideo(String str);

    void didClickInterstitial(String str);

    void didClickMoreApps(String str);

    void didClickRewardedVideo(String str);

    void didCloseInterstitial(String str);

    void didCloseMoreApps(String str);

    void didCloseRewardedVideo(String str);

    void didCompleteRewardedVideo(String str, int i);

    void didDismissInterstitial(String str);

    void didDismissMoreApps(String str);

    void didDismissRewardedVideo(String str);

    void didDisplayInterstitial(String str);

    void didDisplayMoreApps(String str);

    void didDisplayRewardedVideo(String str);

    void didFailToLoadInPlay(String str, CBImpressionError cBImpressionError);

    void didFailToLoadInterstitial(String str, CBImpressionError cBImpressionError);

    void didFailToLoadMoreApps(String str, CBImpressionError cBImpressionError);

    void didFailToLoadRewardedVideo(String str, CBImpressionError cBImpressionError);

    void didFailToRecordClick(String str, CBClickError cBClickError);

    void didPauseClickForConfirmation(Activity activity);

    boolean shouldDisplayInterstitial(String str);

    boolean shouldDisplayMoreApps(String str);

    boolean shouldDisplayRewardedVideo(String str);

    boolean shouldRequestInterstitial(String str);

    boolean shouldRequestMoreApps(String str);

    void willDisplayVideo(String str);
}
