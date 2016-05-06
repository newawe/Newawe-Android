package com.chartboost.sdk.impl;

import android.content.Intent;

/* renamed from: com.chartboost.sdk.impl.a */
public class C1031a extends C0475s {
    private Intent f3427b;

    public C1031a(C0464i c0464i) {
        super(c0464i);
    }

    public String getMessage() {
        if (this.f3427b != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
}
