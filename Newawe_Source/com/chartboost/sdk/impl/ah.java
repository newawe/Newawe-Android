package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Model.C0343a;

public class ah extends C0372g {
    protected C1018j f3457k;
    protected C1018j f3458l;
    protected C0321a f3459m;
    protected String f3460n;
    protected float f3461o;
    private C1018j f3462p;
    private C1018j f3463q;
    private C1018j f3464r;
    private C1018j f3465s;

    /* renamed from: com.chartboost.sdk.impl.ah.a */
    public class C1039a extends C0370a {
        protected bk f3451b;
        protected bl f3452c;
        protected bl f3453d;
        protected ImageView f3454e;
        final /* synthetic */ ah f3455f;
        private boolean f3456g;

        /* renamed from: com.chartboost.sdk.impl.ah.a.1 */
        class C10371 extends bl {
            final /* synthetic */ ah f3448a;
            final /* synthetic */ C1039a f3449b;

            C10371(C1039a c1039a, Context context, ah ahVar) {
                this.f3449b = c1039a;
                this.f3448a = ahVar;
                super(context);
            }

            protected void m3850a(MotionEvent motionEvent) {
                this.f3449b.m3852a(motionEvent.getX(), motionEvent.getY());
            }
        }

        /* renamed from: com.chartboost.sdk.impl.ah.a.2 */
        class C10382 extends bl {
            final /* synthetic */ C1039a f3450a;

            C10382(C1039a c1039a, Context context) {
                this.f3450a = c1039a;
                super(context);
            }

            protected void m3851a(MotionEvent motionEvent) {
                this.f3450a.m3856e();
            }
        }

        protected C1039a(ah ahVar, Context context) {
            this.f3455f = ahVar;
            super(ahVar, context);
            this.f3456g = false;
            setBackgroundColor(0);
            setLayoutParams(new LayoutParams(-1, -1));
            this.f3451b = new bk(context);
            addView(this.f3451b, new LayoutParams(-1, -1));
            this.f3453d = new C10371(this, context, ahVar);
            m456a(this.f3453d);
            this.f3454e = new ImageView(context);
            this.f3454e.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            addView(this.f3454e);
            addView(this.f3453d);
        }

        protected void m3855d() {
            this.f3452c = new C10382(this, getContext());
            addView(this.f3452c);
        }

        protected void m3852a(float f, float f2) {
            this.f3455f.m473a(null, null);
        }

        protected void m3853a(int i, int i2) {
            C1018j c1018j;
            int round;
            int round2;
            if (!this.f3456g) {
                m3855d();
                this.f3456g = true;
            }
            boolean a = this.f3455f.m466a().m165a();
            C1018j a2 = a ? this.f3455f.f3462p : this.f3455f.f3463q;
            if (a) {
                c1018j = this.f3455f.f3457k;
            } else {
                c1018j = this.f3455f.f3458l;
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            this.f3455f.m3862a(layoutParams, a2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f3455f.f3461o = Math.min(Math.min(((float) i) / ((float) layoutParams.width), ((float) i2) / ((float) layoutParams.height)), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            layoutParams.width = (int) (((float) layoutParams.width) * this.f3455f.f3461o);
            layoutParams.height = (int) (((float) layoutParams.height) * this.f3455f.f3461o);
            Point b = this.f3455f.m3864b(a ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b.x) / a2.m3728g()) * this.f3455f.f3461o));
            layoutParams.topMargin = Math.round(((((float) b.y) / a2.m3728g()) * this.f3455f.f3461o) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.f3455f.m3862a(layoutParams2, c1018j, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            b = this.f3455f.m3864b(a ? "close-portrait" : "close-landscape");
            if (b.x == 0 && b.y == 0) {
                round = Math.round(((float) (-layoutParams2.width)) / 2.0f) + (layoutParams.leftMargin + layoutParams.width);
                round2 = layoutParams.topMargin + Math.round(((float) (-layoutParams2.height)) / 2.0f);
            } else {
                round = Math.round(((((float) layoutParams.leftMargin) + (((float) layoutParams.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f));
                round2 = Math.round((((float) b.y) + (((float) layoutParams.topMargin) + (((float) layoutParams.height) / 2.0f))) - (((float) layoutParams2.height) / 2.0f));
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.f3451b.setLayoutParams(layoutParams);
            this.f3452c.setLayoutParams(layoutParams2);
            this.f3451b.setScaleType(ScaleType.FIT_CENTER);
            this.f3451b.m707a(a2);
            this.f3452c.m716a(c1018j);
            c1018j = a ? this.f3455f.f3464r : this.f3455f.f3465s;
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            this.f3455f.m3862a(layoutParams3, c1018j, this.f3455f.f3461o);
            b = this.f3455f.m3864b(a ? "ad-portrait" : "ad-landscape");
            layoutParams3.leftMargin = Math.round((((float) (i - layoutParams3.width)) / 2.0f) + ((((float) b.x) / c1018j.m3728g()) * this.f3455f.f3461o));
            layoutParams3.topMargin = Math.round(((((float) b.y) / c1018j.m3728g()) * this.f3455f.f3461o) + (((float) (i2 - layoutParams3.height)) / 2.0f));
            this.f3454e.setLayoutParams(layoutParams3);
            this.f3453d.setLayoutParams(layoutParams3);
            this.f3453d.m715a(ScaleType.FIT_CENTER);
            this.f3453d.m716a(c1018j);
        }

        protected void m3856e() {
            this.f3455f.m482h();
        }

        public void m3854b() {
            super.m459b();
            this.f3451b = null;
            this.f3452c = null;
            this.f3453d = null;
            this.f3454e = null;
        }
    }

    public ah(C0343a c0343a) {
        super(c0343a);
        this.f3461o = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f3462p = new C1018j(this);
        this.f3463q = new C1018j(this);
        this.f3457k = new C1018j(this);
        this.f3458l = new C1018j(this);
        this.f3464r = new C1018j(this);
        this.f3465s = new C1018j(this);
    }

    protected C0370a m3865b(Context context) {
        return new C1039a(this, context);
    }

    public boolean m3863a(C0321a c0321a) {
        if (!super.m472a(c0321a)) {
            return false;
        }
        this.f3460n = c0321a.m138e("ad_id");
        this.f3459m = c0321a.m127a("ux");
        if (this.e.m132b("frame-portrait") || this.e.m132b("close-portrait")) {
            this.i = false;
        }
        if (this.e.m132b("frame-landscape") || this.e.m132b("close-landscape")) {
            this.j = false;
        }
        this.f3463q.m3721a("frame-landscape");
        this.f3462p.m3721a("frame-portrait");
        this.f3458l.m3721a("close-landscape");
        this.f3457k.m3721a("close-portrait");
        if (this.e.m132b("ad-portrait")) {
            this.i = false;
        }
        if (this.e.m132b("ad-landscape")) {
            this.j = false;
        }
        this.f3465s.m3721a("ad-landscape");
        this.f3464r.m3721a("ad-portrait");
        return true;
    }

    protected Point m3864b(String str) {
        C0321a a = this.e.m127a(str).m127a("offset");
        if (a.m134c()) {
            return new Point(a.m140f("x"), a.m140f("y"));
        }
        return new Point(0, 0);
    }

    public void m3862a(ViewGroup.LayoutParams layoutParams, C1018j c1018j, float f) {
        layoutParams.width = (int) ((((float) c1018j.m3723b()) / c1018j.m3728g()) * f);
        layoutParams.height = (int) ((((float) c1018j.m3724c()) / c1018j.m3728g()) * f);
    }

    public void m3866d() {
        super.m478d();
        this.f3463q.m3725d();
        this.f3462p.m3725d();
        this.f3458l.m3725d();
        this.f3457k.m3725d();
        this.f3465s.m3725d();
        this.f3464r.m3725d();
        this.f3463q = null;
        this.f3462p = null;
        this.f3458l = null;
        this.f3457k = null;
        this.f3465s = null;
        this.f3464r = null;
    }
}
