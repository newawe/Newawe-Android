package com.startapp.android.publish;

/* compiled from: StartAppSDK */
public interface AdDisplayListener {

    /* compiled from: StartAppSDK */
    public enum NotDisplayedReason {
        NETWORK_PROBLEM,
        AD_RULES,
        AD_NOT_READY,
        AD_EXPIRED,
        VIDEO_BACK,
        VIDEO_ERROR,
        INTERNAL_ERROR
    }

    void adClicked(Ad ad);

    void adDisplayed(Ad ad);

    void adHidden(Ad ad);

    void adNotDisplayed(Ad ad);
}
