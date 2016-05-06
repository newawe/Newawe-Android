package com.chartboost.sdk.impl;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0372g;

public final class al extends LinearLayout {
    private ai f406a;
    private LinearLayout f407b;
    private bk f408c;
    private TextView f409d;
    private bl f410e;
    private int f411f;

    /* renamed from: com.chartboost.sdk.impl.al.1 */
    class C10431 extends bl {
        final /* synthetic */ al f3482a;

        C10431(al alVar, Context context) {
            this.f3482a = alVar;
            super(context);
        }

        protected void m3882a(MotionEvent motionEvent) {
            this.f3482a.f410e.setEnabled(false);
            this.f3482a.f406a.m5097r().m5059i();
        }
    }

    public al(Context context, ai aiVar) {
        super(context);
        this.f411f = ExploreByTouchHelper.INVALID_ID;
        this.f406a = aiVar;
        m494a(context);
    }

    private void m494a(Context context) {
        Context context2 = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        setOrientation(1);
        setGravity(17);
        this.f407b = new LinearLayout(context2);
        this.f407b.setGravity(17);
        this.f407b.setOrientation(0);
        this.f407b.setPadding(round, round, round, round);
        this.f408c = new bk(context2);
        this.f408c.setScaleType(ScaleType.FIT_CENTER);
        this.f408c.setPadding(0, 0, round, 0);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.f406a.m3862a(layoutParams, this.f406a.f4210E, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.f409d = new TextView(getContext());
        this.f409d.setTextColor(-1);
        this.f409d.setTypeface(null, 1);
        this.f409d.setGravity(17);
        this.f409d.setTextSize(2, C0372g.m464a(context) ? 26.0f : 16.0f);
        this.f407b.addView(this.f408c, layoutParams);
        this.f407b.addView(this.f409d, new LinearLayout.LayoutParams(-2, -2));
        this.f410e = new C10431(this, getContext());
        this.f410e.setPadding(0, 0, 0, round);
        this.f410e.m715a(ScaleType.FIT_CENTER);
        this.f410e.setPadding(round, round, round, round);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        this.f406a.m3862a(layoutParams2, this.f406a.f4209D, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (this.f406a.f4210E.m3726e()) {
            this.f408c.m707a(this.f406a.f4210E);
        }
        if (this.f406a.f4209D.m3726e()) {
            this.f410e.m716a(this.f406a.f4209D);
        }
        addView(this.f407b, new LinearLayout.LayoutParams(-2, -2));
        addView(this.f410e, layoutParams2);
        m496a();
    }

    public void m498a(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : this.f411f);
    }

    public void m497a(String str, int i) {
        this.f409d.setText(str);
        this.f411f = i;
        m498a(this.f406a.m5099t());
    }

    public void m496a() {
        m498a(this.f406a.m5099t());
    }
}
