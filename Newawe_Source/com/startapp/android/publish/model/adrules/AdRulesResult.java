package com.startapp.android.publish.model.adrules;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class AdRulesResult implements Serializable {
    private static final long serialVersionUID = 1;
    private String reason;
    private boolean shouldDisplayAd;

    public AdRulesResult(boolean shouldDisplayAd, String reason) {
        this.shouldDisplayAd = shouldDisplayAd;
        this.reason = reason;
    }

    public AdRulesResult(boolean shouldDisplayAd) {
        this(shouldDisplayAd, StringUtils.EMPTY);
    }

    public boolean shouldDisplayAd() {
        return this.shouldDisplayAd;
    }

    public void setShouldDisplayAd(boolean shouldDisplayAd) {
        this.shouldDisplayAd = shouldDisplayAd;
    }

    public String getReason() {
        return this.reason;
    }

    public String getSimpleReason() {
        if (this.reason != null) {
            return this.reason.split(" ")[0];
        }
        return StringUtils.EMPTY;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
