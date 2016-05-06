package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;

public final class bj extends View {
    private boolean f621a;

    public bj(Context context) {
        super(context);
        this.f621a = false;
        setFocusable(false);
        setBackgroundColor(-1442840576);
    }

    public void m705a() {
        if (!this.f621a) {
            bi.m700a(true, this);
            this.f621a = true;
        }
    }
}
