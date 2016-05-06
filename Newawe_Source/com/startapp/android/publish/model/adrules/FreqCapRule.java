package com.startapp.android.publish.model.adrules;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class FreqCapRule extends AdRule implements Serializable {
    private static final long serialVersionUID = 1;
    private int cap;
    private int time;

    public FreqCapRule() {
        super(true);
    }

    public FreqCapRule(int time, int cap) {
        this();
        this.time = time;
        this.cap = cap;
    }

    public boolean shouldDisplayAd(List<AdDisplayEvent> adDisplayEvents) {
        boolean z = false;
        if (adDisplayEvents == null && this.cap > 0) {
            return true;
        }
        if (this.cap <= 0) {
            return false;
        }
        if (this.time == 0) {
            return true;
        }
        int size;
        if (this.time < 0) {
            size = adDisplayEvents.size();
        } else {
            size = 0;
            for (AdDisplayEvent displayTime : adDisplayEvents) {
                int i;
                if (System.currentTimeMillis() - displayTime.getDisplayTime() <= TimeUnit.SECONDS.toMillis((long) this.time)) {
                    i = size + 1;
                } else {
                    i = size;
                }
                size = i;
            }
        }
        if (size < this.cap) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return "FreqCapRule [time=" + this.time + ", cap=" + this.cap + "]";
    }
}
