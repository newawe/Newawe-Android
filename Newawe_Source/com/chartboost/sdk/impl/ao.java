package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.Libraries.C0323e;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.ai.C1310a;
import com.chartboost.sdk.impl.bh.C0430a;
import org.apache.commons.lang.time.DateUtils;

public final class ao extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final CharSequence f432a;
    private static Handler f433l;
    private RelativeLayout f434b;
    private an f435c;
    private an f436d;
    private bl f437e;
    private TextView f438f;
    private ak f439g;
    private bh f440h;
    private ai f441i;
    private boolean f442j;
    private boolean f443k;
    private Runnable f444m;
    private Runnable f445n;
    private Runnable f446o;

    /* renamed from: com.chartboost.sdk.impl.ao.2 */
    class C03852 implements Runnable {
        final /* synthetic */ ao f427a;

        C03852(ao aoVar) {
            this.f427a = aoVar;
        }

        public void run() {
            this.f427a.m513d(false);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ao.3 */
    class C03863 implements Runnable {
        final /* synthetic */ ao f428a;

        C03863(ao aoVar) {
            this.f428a = aoVar;
        }

        public void run() {
            if (this.f428a.f435c != null) {
                this.f428a.f435c.setVisibility(8);
            }
            if (this.f428a.f441i.f4215J) {
                this.f428a.f439g.setVisibility(8);
            }
            this.f428a.f436d.setVisibility(8);
            if (this.f428a.f437e != null) {
                this.f428a.f437e.setEnabled(false);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ao.4 */
    class C03874 implements Runnable {
        final /* synthetic */ ao f429a;
        private int f430b;

        C03874(ao aoVar) {
            this.f429a = aoVar;
            this.f430b = 0;
        }

        public void run() {
            if (this.f429a.f440h.m695a().m691e()) {
                float f;
                int d = this.f429a.f440h.m695a().m690d();
                if (d > 0) {
                    this.f429a.f441i.f4227t = d;
                    f = (float) this.f429a.f441i.f4227t;
                    if (this.f429a.f440h.m695a().m691e() && f / 1000.0f > 0.0f && !this.f429a.f441i.m5100u()) {
                        this.f429a.f441i.m5098s();
                        this.f429a.f441i.m5083a(true);
                    }
                }
                f = ((float) d) / ((float) this.f429a.f440h.m695a().m689c());
                if (this.f429a.f441i.f4215J) {
                    this.f429a.f439g.m3876a(f);
                }
                d /= DateUtils.MILLIS_IN_SECOND;
                if (this.f430b != d) {
                    this.f430b = d;
                    int i = d / 60;
                    d %= 60;
                    this.f429a.f438f.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(d)}));
                }
            }
            C1310a r = this.f429a.f441i.m5097r();
            if (r.m5057g()) {
                View b = r.m5052b(true);
                if (b.getVisibility() == 8) {
                    this.f429a.f441i.m470a(true, b);
                    b.setEnabled(true);
                }
            }
            ao.f433l.removeCallbacks(this.f429a.f446o);
            ao.f433l.postDelayed(this.f429a.f446o, 16);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ao.5 */
    class C03885 implements Runnable {
        final /* synthetic */ ao f431a;

        C03885(ao aoVar) {
            this.f431a = aoVar;
        }

        public void run() {
            this.f431a.f440h.setVisibility(0);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ao.1 */
    class C10441 extends bl {
        final /* synthetic */ ao f3483a;

        C10441(ao aoVar, Context context) {
            this.f3483a = aoVar;
            super(context);
        }

        protected void m3883a(MotionEvent motionEvent) {
            this.f3483a.f441i.m473a(null, C0323e.m157a(C0323e.m158a("paused", Integer.valueOf(1))));
        }
    }

    static {
        f432a = "00:00";
        f433l = CBUtility.m96e();
    }

    public ao(Context context, ai aiVar) {
        super(context);
        this.f442j = false;
        this.f443k = false;
        this.f444m = new C03852(this);
        this.f445n = new C03863(this);
        this.f446o = new C03874(this);
        this.f441i = aiVar;
        m508a(context);
    }

    private void m508a(Context context) {
        LayoutParams layoutParams;
        Context context2 = getContext();
        C0321a g = this.f441i.m481g();
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(f * 10.0f);
        this.f440h = new bh(context2);
        this.f441i.m5097r().m456a(this.f440h);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(13);
        addView(this.f440h, layoutParams2);
        this.f434b = new RelativeLayout(context2);
        if (g.m134c() && g.m127a("video-click-button").m134c()) {
            this.f435c = new an(context2);
            this.f435c.setVisibility(8);
            this.f437e = new C10441(this, context2);
            this.f437e.m715a(ScaleType.FIT_CENTER);
            C1018j c1018j = this.f441i.f4211F;
            Point b = this.f441i.m3864b("video-click-button");
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.leftMargin = Math.round(((float) b.x) / c1018j.m3728g());
            layoutParams3.topMargin = Math.round(((float) b.y) / c1018j.m3728g());
            this.f441i.m3862a(layoutParams3, c1018j, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f437e.m716a(c1018j);
            this.f435c.addView(this.f437e, layoutParams3);
            layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(((float) layoutParams3.height) + (10.0f * f)));
            layoutParams.addRule(10);
            this.f434b.addView(this.f435c, layoutParams);
        }
        this.f436d = new an(context2);
        this.f436d.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams.addRule(12);
        this.f434b.addView(this.f436d, layoutParams);
        this.f436d.setGravity(16);
        this.f436d.setPadding(round, round, round, round);
        this.f438f = new TextView(context2);
        this.f438f.setTextColor(-1);
        this.f438f.setTextSize(2, 11.0f);
        this.f438f.setText(f432a);
        this.f438f.setPadding(0, 0, round, 0);
        this.f438f.setSingleLine();
        this.f438f.measure(0, 0);
        int measuredWidth = this.f438f.getMeasuredWidth();
        this.f438f.setGravity(17);
        this.f436d.addView(this.f438f, new LinearLayout.LayoutParams(measuredWidth, -1));
        this.f439g = new ak(context2);
        this.f439g.setVisibility(8);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, Math.round(10.0f * f));
        layoutParams4.setMargins(0, CBUtility.m86a(1, getContext()), 0, 0);
        this.f436d.addView(this.f439g, layoutParams4);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(6, this.f440h.getId());
        layoutParams4.addRule(8, this.f440h.getId());
        layoutParams4.addRule(5, this.f440h.getId());
        layoutParams4.addRule(7, this.f440h.getId());
        addView(this.f434b, layoutParams4);
        m519a();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.f437e != null) {
            this.f437e.setEnabled(enabled);
        }
        if (enabled) {
            m522a(false);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f433l.removeCallbacks(this.f446o);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent e) {
        if (!this.f440h.m695a().m691e() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.f441i == null) {
            return true;
        }
        m513d(true);
        return true;
    }

    public void onCompletion(MediaPlayer arg0) {
        this.f441i.f4227t = this.f440h.m695a().m689c();
        if (this.f441i.m5097r() != null) {
            this.f441i.m5097r().m5056f();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.f441i.f4228u = this.f440h.m695a().m689c();
        this.f441i.m5097r().m457a(true);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.f441i.m5101v();
        return false;
    }

    private void m513d(boolean z) {
        m523a(!this.f442j, z);
    }

    protected void m523a(boolean z, boolean z2) {
        f433l.removeCallbacks(this.f444m);
        f433l.removeCallbacks(this.f445n);
        if (this.f441i.f4229v && this.f441i.m5096q() && z != this.f442j) {
            this.f442j = z;
            Animation alphaAnimation = this.f442j ? new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) : new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setDuration(z2 ? 100 : 200);
            alphaAnimation.setFillAfter(true);
            if (!(this.f443k || this.f435c == null)) {
                this.f435c.setVisibility(0);
                this.f435c.startAnimation(alphaAnimation);
                if (this.f437e != null) {
                    this.f437e.setEnabled(true);
                }
            }
            if (this.f441i.f4215J) {
                this.f439g.setVisibility(0);
            }
            this.f436d.setVisibility(0);
            this.f436d.startAnimation(alphaAnimation);
            if (this.f442j) {
                f433l.postDelayed(this.f444m, 3000);
            } else {
                f433l.postDelayed(this.f445n, alphaAnimation.getDuration());
            }
        }
    }

    public void m522a(boolean z) {
        f433l.removeCallbacks(this.f444m);
        f433l.removeCallbacks(this.f445n);
        if (z) {
            if (!(this.f443k || this.f435c == null)) {
                this.f435c.setVisibility(0);
            }
            if (this.f441i.f4215J) {
                this.f439g.setVisibility(0);
            }
            this.f436d.setVisibility(0);
            if (this.f437e != null) {
                this.f437e.setEnabled(true);
            }
        } else {
            if (this.f435c != null) {
                this.f435c.clearAnimation();
                this.f435c.setVisibility(8);
            }
            this.f436d.clearAnimation();
            if (this.f441i.f4215J) {
                this.f439g.setVisibility(8);
            }
            this.f436d.setVisibility(8);
            if (this.f437e != null) {
                this.f437e.setEnabled(false);
            }
        }
        this.f442j = z;
    }

    public void m525b(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : 0);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.f440h.getId());
            layoutParams.addRule(8, this.f440h.getId());
            layoutParams.addRule(5, this.f440h.getId());
            layoutParams.addRule(7, this.f440h.getId());
        }
        this.f434b.setLayoutParams(layoutParams);
        if (this.f435c != null) {
            this.f435c.setGravity(19);
            this.f435c.requestLayout();
        }
    }

    public void m519a() {
        m525b(CBUtility.m93c().m165a());
    }

    public C0430a m524b() {
        return this.f440h.m695a();
    }

    public ak m526c() {
        return this.f439g;
    }

    public void m520a(int i) {
        if (this.f435c != null) {
            this.f435c.setBackgroundColor(i);
        }
        this.f436d.setBackgroundColor(i);
    }

    public void m528d() {
        if (this.f435c != null) {
            this.f435c.setVisibility(8);
        }
        this.f443k = true;
        if (this.f437e != null) {
            this.f437e.setEnabled(false);
        }
    }

    public void m527c(boolean z) {
        this.f438f.setVisibility(z ? 0 : 8);
    }

    public void m521a(String str) {
        this.f440h.m695a().m684a((OnCompletionListener) this);
        this.f440h.m695a().m685a((OnErrorListener) this);
        this.f440h.m695a().m686a((OnPreparedListener) this);
        this.f440h.m695a().m687a(Uri.parse(str));
    }

    public void m529e() {
        f433l.postDelayed(new C03885(this), 500);
        this.f440h.m695a().m681a();
        f433l.removeCallbacks(this.f446o);
        f433l.postDelayed(this.f446o, 16);
    }

    public void m530f() {
        if (this.f440h.m695a().m691e()) {
            this.f441i.f4227t = this.f440h.m695a().m690d();
            this.f440h.m695a().m688b();
        }
        if (this.f441i.m5097r().d.getVisibility() == 0) {
            this.f441i.m5097r().d.postInvalidate();
        }
        f433l.removeCallbacks(this.f446o);
    }

    public void m531g() {
        if (this.f440h.m695a().m691e()) {
            this.f441i.f4227t = this.f440h.m695a().m690d();
        }
        this.f440h.m695a().m688b();
        f433l.removeCallbacks(this.f446o);
    }

    public void m532h() {
        this.f440h.setVisibility(8);
        invalidate();
    }

    public boolean m533i() {
        return this.f440h.m695a().m691e();
    }
}
