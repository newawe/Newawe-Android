package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Libraries.CBUtility;

public final class aj extends am {
    private LinearLayout f3471b;
    private bk f3472c;
    private TextView f3473d;

    public aj(Context context, ai aiVar) {
        super(context, aiVar);
    }

    protected View m3871a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        this.f3471b = new LinearLayout(context);
        this.f3471b.setOrientation(0);
        this.f3471b.setGravity(17);
        int a = CBUtility.m86a(36, context);
        this.f3472c = new bk(context);
        this.f3472c.setPadding(round, round, round, round);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        this.f3472c.setScaleType(ScaleType.FIT_CENTER);
        this.f3473d = new TextView(context);
        this.f3473d.setPadding(round / 2, round, round, round);
        this.f3473d.setTextColor(-15264491);
        this.f3473d.setTextSize(2, 16.0f);
        this.f3473d.setTypeface(null, 1);
        this.f3473d.setGravity(17);
        this.f3471b.addView(this.f3472c, layoutParams);
        this.f3471b.addView(this.f3473d, new LinearLayout.LayoutParams(-2, -1));
        return this.f3471b;
    }

    public void m3872a(C1018j c1018j) {
        this.f3472c.m707a(c1018j);
        this.f3472c.setScaleType(ScaleType.FIT_CENTER);
    }

    public void m3873a(String str) {
        this.f3473d.setText(str);
    }

    protected int m3874b() {
        return 48;
    }
}
