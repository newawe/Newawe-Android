package com.startapp.android.publish.model.adrules;

import java.util.List;

/* compiled from: StartAppSDK */
public abstract class AdRule {
    private transient boolean shouldProcessEntireHierarchy;

    public abstract boolean shouldDisplayAd(List<AdDisplayEvent> list);

    public AdRule(boolean shouldProcessEntireHierarchy) {
        this.shouldProcessEntireHierarchy = shouldProcessEntireHierarchy;
    }

    public boolean shouldProcessEntireHierarchy() {
        return this.shouldProcessEntireHierarchy;
    }
}
