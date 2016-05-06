package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0339b;

public final class bq extends RelativeLayout {
    private C0370a f652a;
    private bj f653b;
    private bj f654c;
    private bp f655d;
    private C0343a f656e;

    public bq(Context context, C0343a c0343a) {
        super(context);
        this.f656e = null;
        this.f656e = c0343a;
        if (c0343a.f225a == C0339b.NATIVE) {
            this.f653b = new bj(context);
            addView(this.f653b, new LayoutParams(-1, -1));
            this.f654c = new bj(context);
            addView(this.f654c, new LayoutParams(-1, -1));
            this.f654c.setVisibility(8);
        }
    }

    public void m735a() {
        if (this.f652a == null) {
            this.f652a = this.f656e.m278m();
            if (this.f652a != null) {
                addView(this.f652a, new LayoutParams(-1, -1));
                this.f652a.m454a();
            }
        }
        m737c();
    }

    public void m736b() {
        boolean z = !this.f656e.f235k;
        this.f656e.f235k = true;
        if (this.f655d == null) {
            this.f655d = new bp(getContext());
            this.f655d.setVisibility(8);
            addView(this.f655d, new LayoutParams(-1, -1));
        } else {
            if (!(this.f654c == null || this.f653b == null)) {
                this.f654c.bringToFront();
                this.f654c.setVisibility(0);
                this.f654c.m705a();
                bi.m700a(false, this.f653b);
            }
            this.f655d.bringToFront();
            this.f655d.m734a();
        }
        if (!m741g()) {
            this.f655d.setVisibility(0);
            if (z) {
                if (!(this.f654c == null || this.f653b == null)) {
                    m739e().m705a();
                }
                bi.m700a(true, this.f655d);
            }
        }
    }

    public void m737c() {
        if (this.f655d != null) {
            this.f655d.clearAnimation();
            this.f655d.setVisibility(8);
        }
    }

    public void m738d() {
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public bj m739e() {
        return this.f653b;
    }

    public View m740f() {
        return this.f652a;
    }

    public boolean m741g() {
        return this.f655d != null && this.f655d.getVisibility() == 0;
    }

    public C0343a m742h() {
        return this.f656e;
    }
}
