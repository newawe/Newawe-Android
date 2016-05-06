package com.startapp.android.publish.model.adrules;

import java.io.Serializable;
import java.util.List;

/* compiled from: StartAppSDK */
public class ProbabilityRule extends AdRule implements Serializable {
    private static final long serialVersionUID = 1;
    private double probability;

    public ProbabilityRule() {
        super(false);
    }

    public ProbabilityRule(double probability) {
        this();
        this.probability = probability;
    }

    public boolean shouldDisplayAd(List<AdDisplayEvent> list) {
        return Math.random() < this.probability;
    }

    public String toString() {
        return "ProbabilityRule [probability=" + this.probability + "]";
    }
}
