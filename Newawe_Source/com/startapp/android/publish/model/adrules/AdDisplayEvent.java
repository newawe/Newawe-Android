package com.startapp.android.publish.model.adrules;

import com.startapp.android.publish.model.AdPreferences.Placement;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class AdDisplayEvent implements Comparable<AdDisplayEvent> {
    private String adTag;
    private long displayTime;
    private Placement placement;

    public AdDisplayEvent(Placement placement, String adTag) {
        this.displayTime = System.currentTimeMillis();
        this.placement = placement;
        if (adTag == null) {
            adTag = StringUtils.EMPTY;
        }
        this.adTag = adTag;
    }

    public long getDisplayTime() {
        return this.displayTime;
    }

    public Placement getPlacement() {
        return this.placement;
    }

    public String getAdTag() {
        return this.adTag;
    }

    public int compareTo(AdDisplayEvent other) {
        long j = this.displayTime - other.displayTime;
        if (j > 0) {
            return 1;
        }
        if (j == 0) {
            return 0;
        }
        return -1;
    }

    public String toString() {
        return "AdDisplayEvent [displayTime=" + this.displayTime + ", placement=" + this.placement + ", adTag=" + this.adTag + "]";
    }
}
