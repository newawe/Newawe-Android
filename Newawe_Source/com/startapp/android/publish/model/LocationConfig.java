package com.startapp.android.publish.model;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class LocationConfig implements Serializable {
    private static final boolean DEFAULT_CO_ENABLED = true;
    private static final boolean DEFAULT_FI_ENABLED = true;
    private static final long serialVersionUID = 1;
    private Boolean coEnabled;
    private Boolean fiEnabled;

    public LocationConfig() {
        this.fiEnabled = Boolean.valueOf(DEFAULT_FI_ENABLED);
        this.coEnabled = Boolean.valueOf(DEFAULT_FI_ENABLED);
    }

    public Boolean isFiEnabled() {
        return this.fiEnabled;
    }

    public Boolean isCoEnabled() {
        return this.coEnabled;
    }
}
