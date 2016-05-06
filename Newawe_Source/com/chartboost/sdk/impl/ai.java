package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.Newawe.storage.DatabaseOpenHelper;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0340c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.ah.C1039a;
import com.chartboost.sdk.impl.am.C0384a;
import com.chartboost.sdk.impl.bm.C0441a;
import com.chartboost.sdk.impl.bm.C0442b;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xml.serialize.Method;

public class ai extends ah {
    protected int f4206A;
    protected C1018j f4207B;
    protected C1018j f4208C;
    protected C1018j f4209D;
    protected C1018j f4210E;
    protected C1018j f4211F;
    protected C1018j f4212G;
    protected C1018j f4213H;
    protected C1018j f4214I;
    protected boolean f4215J;
    protected boolean f4216K;
    protected boolean f4217L;
    private boolean f4218M;
    private boolean f4219N;
    private boolean f4220O;
    private boolean f4221P;
    private boolean f4222Q;
    protected C0381b f4223p;
    protected int f4224q;
    protected String f4225r;
    protected String f4226s;
    protected int f4227t;
    protected int f4228u;
    protected boolean f4229v;
    protected boolean f4230w;
    protected boolean f4231x;
    protected boolean f4232y;
    protected boolean f4233z;

    /* renamed from: com.chartboost.sdk.impl.ai.2 */
    static /* synthetic */ class C03772 {
        static final /* synthetic */ int[] f398a;

        static {
            f398a = new int[C0381b.values().length];
            try {
                f398a[C0381b.REWARD_OFFER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f398a[C0381b.VIDEO_PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f398a[C0381b.POST_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ai.b */
    protected enum C0381b {
        REWARD_OFFER,
        VIDEO_PLAYING,
        POST_VIDEO
    }

    /* renamed from: com.chartboost.sdk.impl.ai.1 */
    class C10401 extends C0442b {
        final /* synthetic */ ai f3466a;

        C10401(ai aiVar) {
            this.f3466a = aiVar;
        }

        public void m3867a(bm bmVar) {
            C1310a r = this.f3466a.m5097r();
            if (r != null) {
                r.f4200i.m529e();
            }
        }

        public void m3868a(bm bmVar, int i) {
            C1310a r = this.f3466a.m5097r();
            if (i != 1) {
                if (r != null) {
                    r.m5048d(false);
                    r.f4200i.m532h();
                }
                this.f3466a.m482h();
            } else if (r != null) {
                r.f4200i.m529e();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ai.a */
    public class C1310a extends C1039a {
        final /* synthetic */ ai f4198g;
        private bl f4199h;
        private ao f4200i;
        private al f4201j;
        private View f4202k;
        private ag f4203l;
        private aj f4204m;
        private bl f4205n;

        /* renamed from: com.chartboost.sdk.impl.ai.a.3 */
        class C03783 implements Runnable {
            final /* synthetic */ C1310a f399a;

            C03783(C1310a c1310a) {
                this.f399a = c1310a;
            }

            public void run() {
                boolean z;
                String str = "InterstitialVideoViewProtocol";
                String str2 = "controls %s automatically from timer";
                Object[] objArr = new Object[1];
                objArr[0] = this.f399a.f4198g.f4229v ? "hidden" : "shown";
                CBLogging.m79c(str, String.format(str2, objArr));
                ao b = this.f399a.f4200i;
                if (this.f399a.f4198g.f4229v) {
                    z = false;
                } else {
                    z = true;
                }
                b.m523a(z, true);
                this.f399a.f4198g.h.remove(Integer.valueOf(this.f399a.f4200i.hashCode()));
            }
        }

        /* renamed from: com.chartboost.sdk.impl.ai.a.4 */
        class C03794 implements Runnable {
            final /* synthetic */ C1310a f400a;

            C03794(C1310a c1310a) {
                this.f400a = c1310a;
            }

            public void run() {
                this.f400a.f4204m.m503a(false);
            }
        }

        /* renamed from: com.chartboost.sdk.impl.ai.a.5 */
        class C03805 implements Runnable {
            final /* synthetic */ C1310a f401a;

            C03805(C1310a c1310a) {
                this.f401a = c1310a;
            }

            public void run() {
                this.f401a.f4198g.m482h();
            }
        }

        /* renamed from: com.chartboost.sdk.impl.ai.a.1 */
        class C10411 extends bl {
            final /* synthetic */ ai f3467a;
            final /* synthetic */ C1310a f3468b;

            C10411(C1310a c1310a, Context context, ai aiVar) {
                this.f3468b = c1310a;
                this.f3467a = aiVar;
                super(context);
            }

            protected void m3869a(MotionEvent motionEvent) {
                if (this.f3468b.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                    this.f3468b.f4204m.m503a(false);
                }
                if (this.f3468b.f4198g.f4223p == C0381b.VIDEO_PLAYING) {
                    this.f3468b.m5048d(false);
                }
                this.f3468b.m5047c(true);
            }
        }

        /* renamed from: com.chartboost.sdk.impl.ai.a.2 */
        class C10422 extends bl {
            final /* synthetic */ ai f3469a;
            final /* synthetic */ C1310a f3470b;

            C10422(C1310a c1310a, Context context, ai aiVar) {
                this.f3470b = c1310a;
                this.f3469a = aiVar;
                super(context);
            }

            protected void m3870a(MotionEvent motionEvent) {
                this.f3470b.m5055e();
            }
        }

        private C1310a(ai aiVar, Context context) {
            this.f4198g = aiVar;
            super(aiVar, context);
            if (aiVar.f4216K) {
                this.f4202k = new View(context);
                this.f4202k.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                this.f4202k.setVisibility(8);
                addView(this.f4202k);
            }
            if (aiVar.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                this.f4201j = new al(context, aiVar);
                this.f4201j.setVisibility(8);
                addView(this.f4201j);
            }
            this.f4200i = new ao(context, aiVar);
            this.f4200i.setVisibility(8);
            addView(this.f4200i);
            this.f4203l = new ag(context, aiVar);
            this.f4203l.setVisibility(8);
            addView(this.f4203l);
            if (aiVar.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                this.f4204m = new aj(context, aiVar);
                this.f4204m.setVisibility(8);
                addView(this.f4204m);
            }
            this.f4199h = new C10411(this, getContext(), aiVar);
            this.f4199h.setVisibility(8);
            addView(this.f4199h);
            this.f4205n = new C10422(this, getContext(), aiVar);
            this.f4205n.setVisibility(8);
            addView(this.f4205n);
            if (aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m135c("background-color") && aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m135c("border-color") && aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m135c("progress-color") && aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m135c("radius")) {
                aiVar.f4215J = true;
                ak c = this.f4200i.m526c();
                c.m3877a(C0372g.m462a(aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m138e("background-color")));
                c.m3880b(C0372g.m462a(aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m138e("border-color")));
                c.m3881c(C0372g.m462a(aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m138e("progress-color")));
                c.m3879b(aiVar.m.m127a(NotificationCompatApi21.CATEGORY_PROGRESS).m127a("radius").m148j());
            }
            if (aiVar.m.m127a("video-controls-background").m135c("color")) {
                this.f4200i.m520a(C0372g.m462a(aiVar.m.m127a("video-controls-background").m138e("color")));
            }
            if (aiVar.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && aiVar.f4231x) {
                this.f4203l.m3848a(aiVar.m.m127a("post-video-toaster").m138e(DatabaseOpenHelper.HISTORY_ROW_TITLE), aiVar.m.m127a("post-video-toaster").m138e("tagline"));
            }
            if (aiVar.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && aiVar.f4230w) {
                this.f4201j.m497a(aiVar.m.m127a("confirmation").m138e(Method.TEXT), C0372g.m462a(aiVar.m.m127a("confirmation").m138e("color")));
            }
            if (aiVar.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && aiVar.f4232y) {
                this.f4204m.m502a(aiVar.m.m127a("post-video-reward-toaster").m127a("position").equals("inside-top") ? C0384a.TOP : C0384a.BOTTOM);
                this.f4204m.m3873a(aiVar.m.m127a("post-video-reward-toaster").m138e(Method.TEXT));
                if (aiVar.f4212G.m3726e()) {
                    this.f4204m.m3872a(aiVar.f4214I);
                }
            }
            if (aiVar.e.m127a("video-click-button").m131b()) {
                this.f4200i.m528d();
            }
            this.f4200i.m527c(aiVar.m.m149j("video-progress-timer-enabled"));
            if (aiVar.f4217L || aiVar.f4216K) {
                this.e.setVisibility(4);
            }
            aiVar.f4226s = aiVar.e.m127a(aiVar.m466a().m165a() ? "video-portrait" : "video-landscape").m138e("id");
            if (TextUtils.isEmpty(aiVar.f4226s)) {
                aiVar.m468a(CBImpressionError.VIDEO_ID_MISSING);
                return;
            }
            if (aiVar.f4225r == null) {
                aiVar.f4225r = C0330h.m188a(aiVar.f4226s);
            }
            if (aiVar.f4225r == null) {
                aiVar.m468a(CBImpressionError.VIDEO_UNAVAILABLE);
            } else {
                this.f4200i.m521a(aiVar.f4225r);
            }
        }

        protected void m5054d() {
            super.m3855d();
            if (this.f4198g.f4223p != C0381b.REWARD_OFFER || (this.f4198g.f4230w && !this.f4198g.m5094o())) {
                m5044a(this.f4198g.f4223p, false);
            } else {
                m5047c(false);
            }
        }

        public void m5056f() {
            m5048d(true);
            this.f4200i.m532h();
            ai aiVar = this.f4198g;
            aiVar.f4224q++;
            if (this.f4198g.f4224q <= 1 && !this.f4198g.f4222Q && this.f4198g.f4227t >= 1) {
                this.f4198g.f.m272g();
            }
        }

        protected void m5051a(int i, int i2) {
            super.m3853a(i, i2);
            m5044a(this.f4198g.f4223p, false);
            boolean a = this.f4198g.m466a().m165a();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            this.f4198g.m3862a(layoutParams2, a ? this.f4198g.f4208C : this.f4198g.f4207B, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            Point b = this.f4198g.m3864b(a ? "replay-portrait" : "replay-landscape");
            int round = Math.round(((((float) layoutParams6.leftMargin) + (((float) layoutParams6.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f));
            int round2 = Math.round((((((float) layoutParams6.height) / 2.0f) + ((float) layoutParams6.topMargin)) + ((float) b.y)) - (((float) layoutParams2.height) / 2.0f));
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.f4199h.bringToFront();
            if (a) {
                this.f4199h.m716a(this.f4198g.f4208C);
            } else {
                this.f4199h.m716a(this.f4198g.f4207B);
            }
            layoutParams6 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
            if (this.f4198g.m5099t()) {
                LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
                C1018j c1018j = a ? this.f4198g.k : this.f4198g.l;
                this.f4198g.m3862a(layoutParams7, c1018j, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                layoutParams7.leftMargin = 0;
                layoutParams7.topMargin = 0;
                layoutParams7.addRule(11);
                this.f4205n.setLayoutParams(layoutParams7);
                this.f4205n.m716a(c1018j);
            } else {
                layoutParams3.width = layoutParams6.width;
                layoutParams3.height = layoutParams6.height;
                layoutParams3.leftMargin = layoutParams6.leftMargin;
                layoutParams3.topMargin = layoutParams6.topMargin;
                layoutParams4.width = layoutParams6.width;
                layoutParams4.height = layoutParams6.height;
                layoutParams4.leftMargin = layoutParams6.leftMargin;
                layoutParams4.topMargin = layoutParams6.topMargin;
            }
            layoutParams5.width = layoutParams6.width;
            layoutParams5.height = 72;
            layoutParams5.leftMargin = layoutParams6.leftMargin;
            layoutParams5.topMargin = (layoutParams6.height + layoutParams6.topMargin) - 72;
            if (this.f4198g.f4216K) {
                this.f4202k.setLayoutParams(layoutParams);
            }
            if (this.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                this.f4201j.setLayoutParams(layoutParams3);
            }
            this.f4200i.setLayoutParams(layoutParams4);
            this.f4203l.setLayoutParams(layoutParams5);
            this.f4199h.setLayoutParams(layoutParams2);
            if (this.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                this.f4201j.m496a();
            }
            this.f4200i.m519a();
        }

        private void m5047c(boolean z) {
            if (this.f4198g.f4223p != C0381b.VIDEO_PLAYING) {
                if (this.f4198g.f4230w) {
                    m5044a(C0381b.REWARD_OFFER, z);
                    return;
                }
                m5044a(C0381b.VIDEO_PLAYING, z);
                if (this.f4198g.f4224q >= 1 || !this.f4198g.m.m127a("timer").m135c("delay")) {
                    this.f4200i.m522a(!this.f4198g.f4229v);
                } else {
                    String str = "InterstitialVideoViewProtocol";
                    String str2 = "controls starting %s, setting timer";
                    Object[] objArr = new Object[1];
                    objArr[0] = this.f4198g.f4229v ? "visible" : "hidden";
                    CBLogging.m79c(str, String.format(str2, objArr));
                    this.f4200i.m522a(this.f4198g.f4229v);
                    this.f4198g.m467a(this.f4200i, new C03783(this), Math.round(1000.0d * this.f4198g.m.m127a("timer").m144h("delay")));
                }
                this.f4200i.m529e();
                if (this.f4198g.f4224q <= 1) {
                    this.f4198g.f.m273h();
                }
            }
        }

        private void m5048d(boolean z) {
            this.f4200i.m530f();
            if (this.f4198g.f4223p == C0381b.VIDEO_PLAYING && z) {
                if (this.f4198g.f4224q < 1 && this.f4198g.m.m135c("post-video-reward-toaster") && this.f4198g.f4232y && this.f4198g.f4212G.m3726e() && this.f4198g.f4213H.m3726e()) {
                    m5049e(true);
                }
                m5044a(C0381b.POST_VIDEO, true);
                if (CBUtility.m93c().m165a()) {
                    requestLayout();
                }
            }
        }

        private void m5049e(boolean z) {
            if (z) {
                this.f4204m.m503a(true);
            } else {
                this.f4204m.setVisibility(0);
            }
            C0372g.f378a.postDelayed(new C03794(this), 2500);
        }

        private void m5044a(C0381b c0381b, boolean z) {
            boolean z2;
            boolean z3 = true;
            this.f4198g.f4223p = c0381b;
            switch (C03772.f398a[c0381b.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    this.f4198g.m471a(!this.f4198g.m5099t(), this.d, z);
                    if (this.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                        this.f4198g.m471a(true, this.f4201j, z);
                    }
                    if (this.f4198g.f4216K) {
                        this.f4198g.m471a(false, this.f4202k, z);
                    }
                    this.f4198g.m471a(false, this.f4200i, z);
                    this.f4198g.m471a(false, this.f4199h, z);
                    this.f4198g.m471a(false, this.f4203l, z);
                    this.d.setEnabled(false);
                    this.f4199h.setEnabled(false);
                    this.f4200i.setEnabled(false);
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    this.f4198g.m471a(false, this.d, z);
                    if (this.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                        this.f4198g.m471a(false, this.f4201j, z);
                    }
                    if (this.f4198g.f4216K) {
                        this.f4198g.m471a(true, this.f4202k, z);
                    }
                    this.f4198g.m471a(true, this.f4200i, z);
                    this.f4198g.m471a(false, this.f4199h, z);
                    this.f4198g.m471a(false, this.f4203l, z);
                    this.d.setEnabled(true);
                    this.f4199h.setEnabled(false);
                    this.f4200i.setEnabled(true);
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    this.f4198g.m471a(true, this.d, z);
                    if (this.f4198g.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO) {
                        this.f4198g.m471a(false, this.f4201j, z);
                    }
                    if (this.f4198g.f4216K) {
                        this.f4198g.m471a(false, this.f4202k, z);
                    }
                    this.f4198g.m471a(false, this.f4200i, z);
                    this.f4198g.m471a(true, this.f4199h, z);
                    z2 = this.f4198g.f4213H.m3726e() && this.f4198g.f4212G.m3726e() && this.f4198g.f4231x;
                    this.f4198g.m471a(z2, this.f4203l, z);
                    this.f4199h.setEnabled(true);
                    this.d.setEnabled(true);
                    this.f4200i.setEnabled(false);
                    if (this.f4198g.f4233z) {
                        m5049e(false);
                        break;
                    }
                    break;
            }
            z2 = m5057g();
            View b = m5052b(true);
            b.setEnabled(z2);
            this.f4198g.m471a(z2, b, z);
            View b2 = m5052b(false);
            b2.setEnabled(false);
            this.f4198g.m471a(false, b2, z);
            if (this.f4198g.f4217L || this.f4198g.f4216K) {
                this.f4198g.m471a(!this.f4198g.m5099t(), this.e, z);
            }
            ai aiVar = this.f4198g;
            if (this.f4198g.m5099t()) {
                z2 = false;
            } else {
                z2 = true;
            }
            aiVar.m471a(z2, this.b, z);
            if (c0381b == C0381b.REWARD_OFFER) {
                z3 = false;
            }
            m457a(z3);
        }

        protected boolean m5057g() {
            if (this.f4198g.f4223p == C0381b.VIDEO_PLAYING && this.f4198g.f4224q < 1) {
                float a = this.f4198g.e.m127a("close-" + (this.f4198g.m466a().m165a() ? "portrait" : "landscape")).m127a("delay").m125a(-1.0f);
                int round = a >= 0.0f ? Math.round(a * 1000.0f) : -1;
                this.f4198g.f4206A = round;
                if (round < 0) {
                    return false;
                }
                if (round > this.f4200i.m524b().m690d()) {
                    return false;
                }
            }
            return true;
        }

        public void m5053b() {
            this.f4198g.m5093n();
            super.m3854b();
        }

        protected void m5055e() {
            if (this.f4198g.f4223p == C0381b.VIDEO_PLAYING && this.f4198g.m.m127a("cancel-popup").m135c(DatabaseOpenHelper.HISTORY_ROW_TITLE) && this.f4198g.m.m127a("cancel-popup").m135c(Method.TEXT) && this.f4198g.m.m127a("cancel-popup").m135c("cancel") && this.f4198g.m.m127a("cancel-popup").m135c("confirm")) {
                this.f4200i.m531g();
                if (this.f4198g.f4224q < 1) {
                    this.f4198g.m5095p();
                    return;
                }
            }
            if (this.f4198g.f4223p == C0381b.VIDEO_PLAYING) {
                m5048d(false);
                this.f4200i.m532h();
                if (this.f4198g.f4224q < 1) {
                    ai aiVar = this.f4198g;
                    aiVar.f4224q++;
                    this.f4198g.f.m272g();
                }
            }
            C0372g.f378a.post(new C03805(this));
        }

        protected void m5050a(float f, float f2) {
            if ((!this.f4198g.f4229v || this.f4198g.f4223p != C0381b.VIDEO_PLAYING) && this.f4198g.f4223p != C0381b.REWARD_OFFER) {
                m5058h();
            }
        }

        protected void m5058h() {
            if (this.f4198g.f4223p == C0381b.VIDEO_PLAYING) {
                m5048d(false);
            }
            this.f4198g.m473a(null, null);
        }

        protected void m5059i() {
            this.f4198g.f4230w = false;
            m5047c(true);
        }

        public bl m5052b(boolean z) {
            return (!(this.f4198g.m5099t() && z) && (this.f4198g.m5099t() || z)) ? this.c : this.f4205n;
        }
    }

    public /* synthetic */ C0370a m5087e() {
        return m5097r();
    }

    public ai(C0343a c0343a) {
        super(c0343a);
        this.f4223p = C0381b.REWARD_OFFER;
        this.f4218M = true;
        this.f4219N = false;
        this.f4220O = false;
        this.f4227t = 0;
        this.f4228u = 0;
        this.f4221P = false;
        this.f4222Q = false;
        this.f4233z = false;
        this.f4206A = 0;
        this.f4215J = false;
        this.f4216K = false;
        this.f4217L = false;
        this.f4223p = C0381b.REWARD_OFFER;
        this.f4207B = new C1018j(this);
        this.f4208C = new C1018j(this);
        this.f4209D = new C1018j(this);
        this.f4210E = new C1018j(this);
        this.f4211F = new C1018j(this);
        this.f4212G = new C1018j(this);
        this.f4213H = new C1018j(this);
        this.f4214I = new C1018j(this);
        this.f4224q = 0;
    }

    public boolean m5094o() {
        return this.f.f230f == C0340c.INTERSTITIAL_VIDEO;
    }

    public void m5095p() {
        C0441a c0441a = new C0441a();
        c0441a.m720a(this.m.m127a("cancel-popup").m138e(DatabaseOpenHelper.HISTORY_ROW_TITLE)).m722b(this.m.m127a("cancel-popup").m138e(Method.TEXT)).m724d(this.m.m127a("cancel-popup").m138e("confirm")).m723c(this.m.m127a("cancel-popup").m138e("cancel"));
        c0441a.m721a(m5097r().getContext(), new C10401(this));
    }

    protected C0370a m5085b(Context context) {
        return new C1310a(context, null);
    }

    public boolean m5091l() {
        if (!(m5097r().m5052b(true).getVisibility() == 4 || m5097r().m5052b(true).getVisibility() == 8)) {
            m5097r().m5055e();
        }
        return true;
    }

    public void m5092m() {
        super.m487m();
        if (this.f4223p == C0381b.VIDEO_PLAYING && this.f4219N) {
            m5097r().f4200i.m524b().m682a(this.f4227t);
            if (!this.f4220O) {
                m5097r().f4200i.m529e();
            }
        }
        this.f4220O = false;
        this.f4219N = false;
    }

    public void m5093n() {
        super.m488n();
        if (this.f4223p == C0381b.VIDEO_PLAYING && !this.f4219N) {
            if (!m5097r().f4200i.m533i()) {
                this.f4220O = true;
            }
            this.f4219N = true;
            m5097r().f4200i.m531g();
        }
    }

    public boolean m5084a(C0321a c0321a) {
        boolean z = false;
        if (!super.m3863a(c0321a)) {
            return false;
        }
        if (this.e.m132b("video-landscape") || this.e.m132b("replay-landscape")) {
            this.j = false;
        }
        this.f4207B.m3721a("replay-landscape");
        this.f4208C.m3721a("replay-portrait");
        this.f4211F.m3721a("video-click-button");
        this.f4212G.m3721a("post-video-reward-icon");
        this.f4213H.m3721a("post-video-button");
        this.f4209D.m3721a("video-confirmation-button");
        this.f4210E.m3721a("video-confirmation-icon");
        this.f4214I.m3721a("post-video-reward-icon");
        this.f4229v = c0321a.m127a("ux").m149j("video-controls-togglable");
        this.f4216K = c0321a.m127a("fullscreen").m131b() ? false : c0321a.m127a("fullscreen").m152m();
        if (!c0321a.m127a("preroll_popup_fullscreen").m131b()) {
            z = c0321a.m127a("preroll_popup_fullscreen").m152m();
        }
        this.f4217L = z;
        if (this.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && this.m.m127a("post-video-toaster").m135c(DatabaseOpenHelper.HISTORY_ROW_TITLE) && this.m.m127a("post-video-toaster").m135c("tagline")) {
            this.f4231x = true;
        }
        if (this.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && this.m.m127a("confirmation").m135c(Method.TEXT) && this.m.m127a("confirmation").m135c("color")) {
            this.f4230w = true;
        }
        if (this.f.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO && this.m.m135c("post-video-reward-toaster")) {
            this.f4232y = true;
        }
        return true;
    }

    protected void m5088i() {
        if (this.f4230w && !(this.f4209D.m3726e() && this.f4210E.m3726e())) {
            this.f4230w = false;
        }
        if (this.f4218M) {
            super.m483i();
        } else {
            m468a(CBImpressionError.ERROR_DISPLAYING_VIEW);
        }
    }

    public void m5086d() {
        super.m3866d();
        this.f4207B.m3725d();
        this.f4208C.m3725d();
        this.f4211F.m3725d();
        this.f4212G.m3725d();
        this.f4213H.m3725d();
        this.f4209D.m3725d();
        this.f4210E.m3725d();
        this.f4214I.m3725d();
        this.f4207B = null;
        this.f4208C = null;
        this.f4211F = null;
        this.f4212G = null;
        this.f4213H = null;
        this.f4209D = null;
        this.f4210E = null;
        this.f4214I = null;
    }

    public boolean m5096q() {
        return this.f4223p == C0381b.VIDEO_PLAYING;
    }

    public C1310a m5097r() {
        return (C1310a) super.m479e();
    }

    protected void m5098s() {
        this.f.m287v();
    }

    protected boolean m5099t() {
        boolean z = true;
        if (this.f4223p == C0381b.POST_VIDEO) {
            return false;
        }
        boolean a = CBUtility.m93c().m165a();
        if (this.f4223p == C0381b.REWARD_OFFER) {
            if (this.f4217L || a) {
                return true;
            }
            return false;
        } else if (this.f4223p != C0381b.VIDEO_PLAYING) {
            if (!a || this.f4223p == C0381b.POST_VIDEO) {
                z = false;
            }
            return z;
        } else if (this.f4216K || a) {
            return true;
        } else {
            return false;
        }
    }

    public boolean m5100u() {
        return this.f4221P;
    }

    public void m5083a(boolean z) {
        this.f4221P = z;
    }

    public void m5101v() {
        this.f4222Q = true;
        be.m669b(this.f4226s);
        m468a(CBImpressionError.ERROR_PLAYING_VIDEO);
    }

    public float m5089j() {
        return (float) this.f4228u;
    }

    public float m5090k() {
        return (float) this.f4227t;
    }
}
