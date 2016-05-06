package com.startapp.android.publish.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class VideoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private BackMode backMode;
    private int maxCachedVideos;
    private int maxTimeForCachingIndicator;
    private long minAvailableStorageRequired;
    private int minTimeForCachingIndicator;
    private int nativePlayerProbability;
    private int rewardGrantPercentage;
    private int videoErrorsThreshold;

    /* compiled from: StartAppSDK */
    public enum BackMode {
        DISABLED,
        SKIP,
        CLOSE,
        BOTH
    }

    public VideoConfig() {
        this.maxCachedVideos = 3;
        this.minAvailableStorageRequired = 20;
        this.rewardGrantPercentage = 100;
        this.videoErrorsThreshold = 2;
        this.backMode = BackMode.DISABLED;
        this.nativePlayerProbability = 100;
        this.minTimeForCachingIndicator = 1;
        this.maxTimeForCachingIndicator = 10;
    }

    public BackMode getBackMode() {
        return this.backMode;
    }

    public int getMaxCachedVideos() {
        return this.maxCachedVideos;
    }

    public long getMinAvailableStorageRequired() {
        return this.minAvailableStorageRequired;
    }

    public int getRewardGrantPercentage() {
        return this.rewardGrantPercentage;
    }

    public int getVideoErrorsThreshold() {
        return this.videoErrorsThreshold;
    }

    public int getNativePlayerProbability() {
        return this.nativePlayerProbability;
    }

    public long getMinTimeForCachingIndicator() {
        return TimeUnit.SECONDS.toMillis((long) this.minTimeForCachingIndicator);
    }

    public long getMaxTimeForCachingIndicator() {
        return TimeUnit.SECONDS.toMillis((long) this.maxTimeForCachingIndicator);
    }
}
