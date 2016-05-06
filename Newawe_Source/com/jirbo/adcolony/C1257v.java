package com.jirbo.adcolony;

import android.graphics.Canvas;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.jirbo.adcolony.v */
class C1257v extends C0773h {
    static boolean f4037H;
    static C1257v f4038I;
    boolean f4039J;
    boolean f4040K;
    ADCVideo f4041L;

    public C1257v(ADCVideo aDCVideo, AdColonyV4VCAd adColonyV4VCAd) {
        this.f4041L = aDCVideo;
        this.G = adColonyV4VCAd;
        aDCVideo.f3943G.pause();
        f4038I = this;
        if (!m2316a()) {
        }
    }

    public void onDraw(Canvas canvas) {
        int i = TransportMediator.FLAG_KEY_MEDIA_NEXT;
        if (this.f4041L.f3943G != null) {
            f4037H = true;
            m4741d();
            int currentTimeMillis = (((int) (System.currentTimeMillis() - this.w)) * MotionEventCompat.ACTION_MASK) / DateUtils.MILLIS_IN_SECOND;
            if (currentTimeMillis <= TransportMediator.FLAG_KEY_MEDIA_NEXT) {
                i = currentTimeMillis;
            }
            canvas.drawARGB(i, 0, 0, 0);
            this.a.m2101a(canvas, this.x, this.y);
            i = (m2318b() * 3) / 2;
            m2314a("Completion is required to receive", this.z, (int) (((double) this.A) - (((double) i) * 2.75d)), canvas);
            m2314a("your reward.", this.z, this.A - (i * 2), canvas);
            m2314a("Are you sure you want to skip?", this.z, (int) (((double) this.A) - (((double) i) * 1.25d)), canvas);
            this.b.m2101a(canvas, this.z - (this.b.f1805f / 2), this.A - (this.b.f1806g / 2));
            if (this.f4039J) {
                this.d.m2101a(canvas, this.B, this.D);
            } else {
                this.c.m2101a(canvas, this.B, this.D);
            }
            if (this.f4040K) {
                this.f.m2101a(canvas, this.C, this.D);
            } else {
                this.e.m2101a(canvas, this.C, this.D);
            }
            m2321c("Yes", this.B, this.D, canvas);
            m2321c("No", this.C, this.D, canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        ADCVideo aDCVideo = this.f4041L;
        if (ADCVideo.f3931d) {
            f4038I = null;
            return this.f4041L.f3944H.onTouchEvent(event);
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == 1) {
            if (m2317a(x, y, this.B, this.D) && this.f4039J) {
                f4038I = null;
                f4037H = false;
                C0745a.f1967D = false;
                C0745a.ak = true;
                C0745a.f1986W.m2134b(this.G);
                AdColonyBrowser.f1871A = true;
                this.f4041L.finish();
                C0745a.f1968E = true;
            } else if (m2317a(x, y, this.C, this.D) && this.f4040K) {
                f4038I = null;
                f4037H = false;
                this.f4041L.f3943G.start();
            }
            this.f4039J = false;
            this.f4040K = false;
            invalidate();
        }
        if (event.getAction() != 0) {
            return true;
        }
        if (m2317a(x, y, this.B, this.D)) {
            this.f4039J = true;
            invalidate();
            return true;
        } else if (!m2317a(x, y, this.C, this.D)) {
            return true;
        } else {
            this.f4040K = true;
            invalidate();
            return true;
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (this.f4041L.f3943G != null && keycode == 4) {
            return super.onKeyDown(keycode, event);
        }
        return false;
    }

    public boolean onKeyUp(int keycode, KeyEvent event) {
        if (keycode != 4) {
            return false;
        }
        f4038I = null;
        this.f4041L.f3943G.start();
        return true;
    }

    void m4741d() {
        int i = this.f4041L.f3972t;
        int i2 = this.f4041L.f3973u;
        this.x = (i - this.a.f1805f) / 2;
        this.y = (i2 - this.a.f1806g) / 2;
        this.z = this.x + (this.a.f1805f / 2);
        this.A = this.y + (this.a.f1806g / 2);
        this.D = this.y + ((int) (((double) this.a.f1806g) - (((double) this.c.f1806g) + (p * 16.0d))));
        this.B = this.x + ((int) (p * 16.0d));
        this.C = this.x + ((int) (((double) this.a.f1805f) - (((double) this.c.f1805f) + (p * 16.0d))));
    }
}
