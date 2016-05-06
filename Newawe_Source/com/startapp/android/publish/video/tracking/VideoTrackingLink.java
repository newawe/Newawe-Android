package com.startapp.android.publish.video.tracking;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public abstract class VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean appendReplayParameter;
    private String replayParameter;
    private TrackingSource trackingSource;
    private String trackingUrl;

    /* compiled from: StartAppSDK */
    public enum TrackingSource {
        STARTAPP,
        EXTERNAL
    }

    public String m3638b() {
        return this.trackingUrl;
    }

    public boolean m3639c() {
        return this.appendReplayParameter;
    }

    public String m3640d() {
        return this.replayParameter;
    }

    public TrackingSource m3641e() {
        return this.trackingSource;
    }

    public String toString() {
        return "trackingSource=" + this.trackingSource + ", trackingUrl=" + this.trackingUrl + ", replayParameter=" + this.replayParameter + ", appendReplayParameter=" + this.appendReplayParameter;
    }
}
