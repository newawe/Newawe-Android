package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

@Deprecated
public abstract class ChartboostActivity extends Activity implements C0345a {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        C0351c.m337a((C0345a) this);
        Chartboost.onCreate(this);
    }

    protected void onStart() {
        super.onStart();
        Chartboost.onStart(this);
    }

    protected void onStop() {
        super.onStop();
        Chartboost.onStop(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            Chartboost.onDestroy(this);
        }
    }

    protected void onPause() {
        super.onPause();
        Chartboost.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        Chartboost.onResume(this);
    }

    public void onBackPressed() {
        if (!Chartboost.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public boolean shouldRequestInterstitial(String location) {
        return true;
    }

    public boolean shouldDisplayInterstitial(String location) {
        return true;
    }

    public void didCacheInterstitial(String location) {
    }

    public void didFailToLoadInterstitial(String location, CBImpressionError error) {
    }

    public void didDismissInterstitial(String location) {
    }

    public void didCloseInterstitial(String location) {
    }

    public void didClickInterstitial(String location) {
    }

    public void didDisplayInterstitial(String location) {
    }

    public boolean shouldRequestMoreApps(String location) {
        return true;
    }

    public boolean shouldDisplayMoreApps(String location) {
        return true;
    }

    public void didFailToLoadMoreApps(String location, CBImpressionError error) {
    }

    public void didCacheMoreApps(String location) {
    }

    public void didDismissMoreApps(String location) {
    }

    public void didCloseMoreApps(String location) {
    }

    public void didClickMoreApps(String location) {
    }

    public void didDisplayMoreApps(String location) {
    }

    public void didFailToRecordClick(String uri, CBClickError error) {
    }

    public void didPauseClickForConfirmation() {
    }

    public boolean shouldDisplayRewardedVideo(String location) {
        return true;
    }

    public void didCacheRewardedVideo(String location) {
    }

    public void didFailToLoadRewardedVideo(String location, CBImpressionError error) {
    }

    public void didDismissRewardedVideo(String location) {
    }

    public void didCloseRewardedVideo(String location) {
    }

    public void didClickRewardedVideo(String location) {
    }

    public void didCompleteRewardedVideo(String location, int reward) {
    }

    public void didDisplayRewardedVideo(String location) {
    }

    public void willDisplayVideo(String location) {
    }

    public void didCacheInPlay(String location) {
    }

    public void didFailToLoadInPlay(String location, CBImpressionError error) {
    }
}
