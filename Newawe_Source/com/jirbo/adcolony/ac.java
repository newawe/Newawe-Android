package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import org.apache.commons.lang.time.DateUtils;

class ac extends C0773h {
    boolean f3991H;

    public ac(String str, AdColonyV4VCAd adColonyV4VCAd) {
        this.F = str;
        this.G = adColonyV4VCAd;
        if (m2316a()) {
            AdColony.activity().addContentView(this, new LayoutParams(-1, -1, 17));
        }
    }

    public void onDraw(Canvas canvas) {
        m4692d();
        int currentTimeMillis = (((int) (System.currentTimeMillis() - this.w)) * MotionEventCompat.ACTION_MASK) / DateUtils.MILLIS_IN_SECOND;
        if (currentTimeMillis > TransportMediator.FLAG_KEY_MEDIA_NEXT) {
            currentTimeMillis = TransportMediator.FLAG_KEY_MEDIA_NEXT;
        }
        canvas.drawARGB(currentTimeMillis, 0, 0, 0);
        this.a.m2101a(canvas, this.x, this.y);
        int b = (m2318b() * 3) / 2;
        int remainingViewsUntilReward = this.G.getRemainingViewsUntilReward();
        if (remainingViewsUntilReward == this.G.getViewsPerReward() || remainingViewsUntilReward == 0) {
            m2315a(this.F, "video. You earned");
            if (s) {
                m2314a("Thanks for watching the sponsored", this.z, (int) (((double) this.A) - (((double) b) * 2.5d)), canvas);
                m2314a("video. You earned " + q + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.5d)), canvas);
            } else {
                m2314a("Thanks for watching the sponsored", this.z, (int) (((double) this.A) - (((double) b) * 2.8d)), canvas);
                m2314a("video. You earned " + q, this.z, (int) (((double) this.A) - (((double) b) * 2.05d)), canvas);
                m2314a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.3d)), canvas);
            }
        } else {
            m2315a(this.F, "to earn ");
            String str = remainingViewsUntilReward == 1 ? "video" : "videos";
            if (s) {
                m2314a("Thank you. Watch " + remainingViewsUntilReward + " more " + str, this.z, (int) (((double) this.A) - (((double) b) * 2.5d)), canvas);
                m2314a("to earn " + q + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.5d)), canvas);
            } else {
                m2314a("Thank you. Watch " + remainingViewsUntilReward + " more " + str, this.z, (int) (((double) this.A) - (((double) b) * 2.8d)), canvas);
                m2314a("to earn " + q, this.z, (int) (((double) this.A) - (((double) b) * 2.05d)), canvas);
                m2314a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.3d)), canvas);
            }
        }
        this.b.m2101a(canvas, this.z - (this.b.f1805f / 2), this.A - (this.b.f1806g / 2));
        if (this.f3991H) {
            this.g.m2101a(canvas, this.B, this.D);
        } else {
            this.h.m2101a(canvas, this.B, this.D);
        }
        m2321c("Ok", this.B, this.D, canvas);
        if (currentTimeMillis != TransportMediator.FLAG_KEY_MEDIA_NEXT) {
            invalidate();
        }
    }

    void m4692d() {
        Display defaultDisplay = C0745a.m2148b().getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        double d = this.n ? 12.0d : 16.0d;
        this.x = (width - this.a.f1805f) / 2;
        this.y = ((height - this.a.f1806g) / 2) - 80;
        this.z = this.x + (this.a.f1805f / 2);
        this.A = this.y + (this.a.f1806g / 2);
        this.D = ((int) (((double) this.a.f1806g) - ((d * p) + ((double) this.h.f1806g)))) + this.y;
        this.B = this.z - (this.h.f1805f / 2);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == 1) {
            if (m2317a(x, y, this.B, this.D) && this.f3991H) {
                C0745a.f1982S = null;
                ((ViewGroup) getParent()).removeView(this);
                for (int i = 0; i < C0745a.an.size(); i++) {
                    ((Bitmap) C0745a.an.get(i)).recycle();
                }
                C0745a.an.clear();
                C0745a.f1968E = true;
            }
            this.f3991H = false;
            invalidate();
        }
        if (event.getAction() == 0 && m2317a(x, y, this.B, this.D)) {
            this.f3991H = true;
            invalidate();
        }
        return true;
    }
}
