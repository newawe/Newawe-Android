package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;

public final class ag extends am {
    private LinearLayout f3442b;
    private LinearLayout f3443c;
    private bk f3444d;
    private bl f3445e;
    private TextView f3446f;
    private TextView f3447g;

    /* renamed from: com.chartboost.sdk.impl.ag.1 */
    class C10361 extends bl {
        final /* synthetic */ ag f3441a;

        C10361(ag agVar, Context context) {
            this.f3441a = agVar;
            super(context);
        }

        protected void m3846a(MotionEvent motionEvent) {
            this.f3441a.a.m5097r().m5058h();
        }
    }

    public ag(Context context, ai aiVar) {
        super(context, aiVar);
    }

    protected View m3847a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 6.0f);
        this.f3442b = new LinearLayout(context);
        this.f3442b.setOrientation(0);
        this.f3442b.setGravity(17);
        this.f3443c = new LinearLayout(context);
        this.f3443c.setOrientation(1);
        this.f3443c.setGravity(19);
        this.f3444d = new bk(context);
        this.f3444d.setPadding(round, round, round, round);
        if (this.a.f4212G.m3726e()) {
            this.f3444d.m707a(this.a.f4212G);
        }
        this.f3445e = new C10361(this, context);
        this.f3445e.setPadding(round, round, round, round);
        if (this.a.f4213H.m3726e()) {
            this.f3445e.m716a(this.a.f4213H);
        }
        this.f3446f = new TextView(getContext());
        this.f3446f.setTextColor(-15264491);
        this.f3446f.setTypeface(null, 1);
        this.f3446f.setGravity(3);
        this.f3446f.setPadding(round, round, round, round / 2);
        this.f3447g = new TextView(getContext());
        this.f3447g.setTextColor(-15264491);
        this.f3447g.setTypeface(null, 1);
        this.f3447g.setGravity(3);
        this.f3447g.setPadding(round, 0, round, round);
        this.f3446f.setTextSize(2, 14.0f);
        this.f3447g.setTextSize(2, 11.0f);
        this.f3443c.addView(this.f3446f);
        this.f3443c.addView(this.f3447g);
        this.f3442b.addView(this.f3444d);
        this.f3442b.addView(this.f3443c, new LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.f3442b.addView(this.f3445e);
        return this.f3442b;
    }

    public void m3848a(String str, String str2) {
        this.f3446f.setText(str);
        this.f3447g.setText(str2);
    }

    protected int m3849b() {
        return 72;
    }
}
