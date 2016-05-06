package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class bp extends LinearLayout {
    private TextView f650a;
    private br f651b;

    public bp(Context context) {
        super(context);
        m733a(context);
    }

    private void m733a(Context context) {
        setGravity(17);
        this.f650a = new TextView(getContext());
        this.f650a.setTextColor(-1);
        this.f650a.setTextSize(2, 16.0f);
        this.f650a.setTypeface(null, 1);
        this.f650a.setText("Loading...");
        this.f650a.setGravity(17);
        this.f651b = new br(getContext());
        addView(this.f650a);
        addView(this.f651b);
        m734a();
    }

    public void m734a() {
        removeView(this.f650a);
        removeView(this.f651b);
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(20.0f * f);
        setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(round, round, round, 0);
        addView(this.f650a, layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
        layoutParams.setMargins(round, round, round, round);
        addView(this.f651b, layoutParams);
    }
}
