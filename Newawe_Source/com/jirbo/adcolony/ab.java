package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

class ab extends C0773h {
    boolean f3988H;
    boolean f3989I;
    boolean f3990J;

    public ab(String str, AdColonyV4VCAd adColonyV4VCAd) {
        this.f3990J = true;
        this.F = str;
        this.G = adColonyV4VCAd;
        if (m2316a()) {
            AdColony.activity().addContentView(this, new LayoutParams(-1, -1, 17));
            if (this.n) {
                this.D += 20;
            }
        }
    }

    protected void onDetachedFromWindow() {
        if (this.f3990J) {
            C0745a.f1982S = null;
            this.f3990J = false;
            this.G.m4691c(false);
            C0745a.f1968E = true;
            for (int i = 0; i < C0745a.an.size(); i++) {
                ((Bitmap) C0745a.an.get(i)).recycle();
            }
            C0745a.an.clear();
        }
    }

    public void onDraw(Canvas canvas) {
        m2322d();
        int currentTimeMillis = (((int) (System.currentTimeMillis() - this.w)) * MotionEventCompat.ACTION_MASK) / DateUtils.MILLIS_IN_SECOND;
        if (currentTimeMillis > TransportMediator.FLAG_KEY_MEDIA_NEXT) {
            currentTimeMillis = TransportMediator.FLAG_KEY_MEDIA_NEXT;
        }
        canvas.drawARGB(currentTimeMillis, 0, 0, 0);
        this.a.m2101a(canvas, this.x, this.y);
        int b = (m2318b() * 3) / 2;
        int remainingViewsUntilReward = this.G.getRemainingViewsUntilReward();
        if (this.G.getViewsPerReward() == 1 || this.G.getViewsPerReward() == 0) {
            m2315a(this.F, StringUtils.EMPTY);
            if (s) {
                m2314a("Watch a video to earn", this.z, (int) (((double) this.A) - (((double) b) * this.l)), canvas);
                m2314a(q + ".", this.z, (int) (((double) this.A) - (((double) b) * this.m)), canvas);
            } else {
                m2314a("Watch a video to earn", this.z, (int) (((double) this.A) - (((double) b) * this.i)), canvas);
                m2314a(q, this.z, (int) (((double) this.A) - (((double) b) * this.j)), canvas);
                m2314a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * this.k)), canvas);
            }
        } else {
            String str = remainingViewsUntilReward == 1 ? "video" : "videos";
            m2315a(this.F, StringUtils.EMPTY + remainingViewsUntilReward + " more " + str + " to earn )?");
            if (s) {
                m2314a("Watch a sponsored video now (Only", this.z, (int) (((double) this.A) - (((double) b) * this.l)), canvas);
                m2314a(StringUtils.EMPTY + remainingViewsUntilReward + " more " + str + " to earn " + q + ")?", this.z, (int) (((double) this.A) - (((double) b) * this.m)), canvas);
            } else {
                m2314a("Watch a sponsored video now (Only", this.z, (int) (((double) this.A) - (((double) b) * this.i)), canvas);
                m2314a(StringUtils.EMPTY + remainingViewsUntilReward + " more " + str + " to earn " + q, this.z, (int) (((double) this.A) - (((double) b) * this.j)), canvas);
                m2314a(r + ")?", this.z, (int) (((double) this.A) - (((double) b) * this.k)), canvas);
            }
        }
        this.b.m2101a(canvas, this.z - (this.b.f1805f / 2), this.A - (this.b.f1806g / 2));
        if (this.f3989I) {
            this.d.m2101a(canvas, this.B, this.D);
        } else {
            this.c.m2101a(canvas, this.B, this.D);
        }
        if (this.f3988H) {
            this.f.m2101a(canvas, this.C, this.D);
        } else {
            this.e.m2101a(canvas, this.C, this.D);
        }
        m2321c("Yes", this.B, this.D, canvas);
        m2321c("No", this.C, this.D, canvas);
        if (currentTimeMillis != TransportMediator.FLAG_KEY_MEDIA_NEXT) {
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == 1) {
            if (m2317a(x, y, this.B, this.D) && this.f3989I) {
                C0745a.f1982S = null;
                this.f3990J = false;
                ((ViewGroup) getParent()).removeView(this);
                this.G.m4691c(true);
            } else if (m2317a(x, y, this.C, this.D) && this.f3988H) {
                C0745a.f1982S = null;
                this.f3990J = false;
                ((ViewGroup) getParent()).removeView(this);
                this.G.m4691c(false);
                C0745a.f1968E = true;
                for (int i = 0; i < C0745a.an.size(); i++) {
                    ((Bitmap) C0745a.an.get(i)).recycle();
                }
                C0745a.an.clear();
            }
            this.f3988H = false;
            this.f3989I = false;
            invalidate();
        }
        if (event.getAction() == 0) {
            if (m2317a(x, y, this.B, this.D)) {
                this.f3989I = true;
                invalidate();
            } else if (m2317a(x, y, this.C, this.D)) {
                this.f3988H = true;
                invalidate();
            }
        }
        return true;
    }
}
