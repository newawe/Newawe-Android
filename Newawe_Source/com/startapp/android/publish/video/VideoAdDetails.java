package com.startapp.android.publish.video;

import com.startapp.android.publish.video.tracking.StartAppSDK;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class VideoAdDetails implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean clickable;
    private boolean closeable;
    private String localVideoPath;
    private PostRollType postRoll;
    private boolean skippable;
    private int skippableAfter;
    private StartAppSDK videoTrackingDetails;
    private String videoUrl;

    /* compiled from: StartAppSDK */
    public enum PostRollType {
        IMAGE,
        LAST_FRAME,
        NONE
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public String getLocalVideoPath() {
        return this.localVideoPath;
    }

    public void setLocalVideoPath(String localVideoPath) {
        this.localVideoPath = localVideoPath;
    }

    public PostRollType getPostRollType() {
        return this.postRoll;
    }

    public boolean isCloseable() {
        return this.closeable;
    }

    public boolean isSkippable() {
        return this.skippable;
    }

    public int getSkippableAfter() {
        return this.skippableAfter;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public StartAppSDK getVideoTrackingDetails() {
        return this.videoTrackingDetails;
    }

    public String toString() {
        return "VideoAdDetails [videoUrl=" + this.videoUrl + ", localVideoPath=" + this.localVideoPath + ", postRoll=" + this.postRoll + ", closeable=" + this.closeable + ", skippable=" + this.skippable + ", skippableAfter=" + this.skippableAfter + ", videoTrackingDetails = " + this.videoTrackingDetails.toString() + "]";
    }
}
