package com.startapp.android.publish.slider.sliding;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.android.volley.DefaultRetryPolicy;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.util.Arrays;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.startapp.android.publish.slider.sliding.c */
public class StartAppSDK {
    private static final Interpolator f3195v;
    private int f3196a;
    private int f3197b;
    private int f3198c;
    private float[] f3199d;
    private float[] f3200e;
    private float[] f3201f;
    private float[] f3202g;
    private int[] f3203h;
    private int[] f3204i;
    private int[] f3205j;
    private int f3206k;
    private VelocityTracker f3207l;
    private float f3208m;
    private float f3209n;
    private int f3210o;
    private int f3211p;
    private StartAppSDK f3212q;
    private final StartAppSDK f3213r;
    private View f3214s;
    private boolean f3215t;
    private final ViewGroup f3216u;
    private final Runnable f3217w;

    /* renamed from: com.startapp.android.publish.slider.sliding.c.1 */
    static class StartAppSDK implements Interpolator {
        StartAppSDK() {
        }

        public float getInterpolation(float t) {
            t -= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
            return ((((t * t) * t) * t) * t) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.c.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3194a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3194a = startAppSDK;
        }

        public void run() {
            this.f3194a.m3534c(0);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.c.a */
    public static abstract class StartAppSDK {
        public abstract boolean m3492a(View view, int i);

        public void m3488a(int i) {
        }

        public void m3491a(View view, int i, int i2, int i3, int i4) {
        }

        public void m3496b(View view, int i) {
        }

        public void m3490a(View view, float f, float f2) {
        }

        public void m3489a(int i, int i2) {
        }

        public boolean m3497b(int i) {
            return false;
        }

        public void m3495b(int i, int i2) {
        }

        public int m3498c(int i) {
            return i;
        }

        public int m3486a(View view) {
            return 0;
        }

        public int m3493b(View view) {
            return 0;
        }

        public int m3487a(View view, int i, int i2) {
            return 0;
        }

        public int m3494b(View view, int i, int i2) {
            return 0;
        }
    }

    static {
        f3195v = new StartAppSDK();
    }

    public static StartAppSDK m3503a(ViewGroup viewGroup, StartAppSDK startAppSDK) {
        return new StartAppSDK(viewGroup.getContext(), viewGroup, startAppSDK);
    }

    public static StartAppSDK m3502a(ViewGroup viewGroup, float f, StartAppSDK startAppSDK) {
        StartAppSDK a = StartAppSDK.m3503a(viewGroup, startAppSDK);
        a.f3197b = (int) (((float) a.f3197b) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / f));
        return a;
    }

    private StartAppSDK(Context context, ViewGroup viewGroup, StartAppSDK startAppSDK) {
        this.f3198c = -1;
        this.f3217w = new StartAppSDK(this);
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (startAppSDK == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.f3216u = viewGroup;
            this.f3213r = startAppSDK;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.f3210o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f3197b = viewConfiguration.getScaledTouchSlop();
            this.f3208m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.f3209n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.f3212q = StartAppSDK.m3374a(context, f3195v);
        }
    }

    public void m3520a(float f) {
        this.f3209n = f;
    }

    public int m3519a() {
        return this.f3196a;
    }

    public void m3521a(int i) {
        this.f3211p = i;
    }

    public int m3527b() {
        return this.f3210o;
    }

    public void m3522a(View view, int i) {
        if (view.getParent() != this.f3216u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f3216u + ")");
        }
        this.f3214s = view;
        this.f3198c = i;
        this.f3213r.m3496b(view, i);
        m3534c(1);
    }

    public View m3533c() {
        return this.f3214s;
    }

    public int m3536d() {
        return this.f3197b;
    }

    public void m3540e() {
        this.f3198c = -1;
        m3515f();
        if (this.f3207l != null) {
            this.f3207l.recycle();
            this.f3207l = null;
        }
    }

    public boolean m3525a(View view, int i, int i2) {
        this.f3214s = view;
        this.f3198c = -1;
        return m3507a(i, i2, 0, 0);
    }

    public boolean m3523a(int i, int i2) {
        if (this.f3215t) {
            return m3507a(i, i2, (int) com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3457a(this.f3207l, this.f3198c), (int) com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3458b(this.f3207l, this.f3198c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean m3507a(int i, int i2, int i3, int i4) {
        int left = this.f3214s.getLeft();
        int top = this.f3214s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.f3212q.m3382g();
            m3534c(0);
            return false;
        }
        this.f3212q.m3375a(left, top, i5, i6, m3501a(this.f3214s, i5, i6, i3, i4));
        m3534c(2);
        return true;
    }

    private int m3501a(View view, int i, int i2, int i3, int i4) {
        int b = m3510b(i3, (int) this.f3209n, (int) this.f3208m);
        int b2 = m3510b(i4, (int) this.f3209n, (int) this.f3208m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) m3500a(i2, b2, this.f3213r.m3493b(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) m3500a(i, b, this.f3213r.m3486a(view)))));
    }

    private int m3500a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.f3216u.getWidth();
        int i4 = width / 2;
        float b = (m3509b(Math.min(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(b / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) * 256.0f);
        }
        return Math.min(width, 600);
    }

    private int m3510b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    private float m3499a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    private float m3509b(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean m3526a(boolean z) {
        if (this.f3196a == 2) {
            boolean a;
            boolean f = this.f3212q.m3381f();
            int b = this.f3212q.m3377b();
            int c = this.f3212q.m3378c();
            int left = b - this.f3214s.getLeft();
            int top = c - this.f3214s.getTop();
            if (left != 0) {
                this.f3214s.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.f3214s.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.f3213r.m3491a(this.f3214s, b, c, left, top);
            }
            if (f && b == this.f3212q.m3379d() && c == this.f3212q.m3380e()) {
                this.f3212q.m3382g();
                a = this.f3212q.m3376a();
            } else {
                a = f;
            }
            if (!a) {
                if (z) {
                    this.f3216u.post(this.f3217w);
                } else {
                    m3534c(0);
                }
            }
        }
        return this.f3196a == 2;
    }

    private void m3504a(float f, float f2) {
        this.f3215t = true;
        this.f3213r.m3490a(this.f3214s, f, f2);
        this.f3215t = false;
        if (this.f3196a == 1) {
            m3534c(0);
        }
    }

    private void m3515f() {
        if (this.f3199d != null) {
            Arrays.fill(this.f3199d, 0.0f);
            Arrays.fill(this.f3200e, 0.0f);
            Arrays.fill(this.f3201f, 0.0f);
            Arrays.fill(this.f3202g, 0.0f);
            Arrays.fill(this.f3203h, 0);
            Arrays.fill(this.f3204i, 0);
            Arrays.fill(this.f3205j, 0);
            this.f3206k = 0;
        }
    }

    private void m3516f(int i) {
        if (this.f3199d != null) {
            this.f3199d[i] = 0.0f;
            this.f3200e[i] = 0.0f;
            this.f3201f[i] = 0.0f;
            this.f3202g[i] = 0.0f;
            this.f3203h[i] = 0;
            this.f3204i[i] = 0;
            this.f3205j[i] = 0;
            this.f3206k &= (1 << i) ^ -1;
        }
    }

    private void m3518g(int i) {
        if (this.f3199d == null || this.f3199d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.f3199d != null) {
                System.arraycopy(this.f3199d, 0, obj, 0, this.f3199d.length);
                System.arraycopy(this.f3200e, 0, obj2, 0, this.f3200e.length);
                System.arraycopy(this.f3201f, 0, obj3, 0, this.f3201f.length);
                System.arraycopy(this.f3202g, 0, obj4, 0, this.f3202g.length);
                System.arraycopy(this.f3203h, 0, obj5, 0, this.f3203h.length);
                System.arraycopy(this.f3204i, 0, obj6, 0, this.f3204i.length);
                System.arraycopy(this.f3205j, 0, obj7, 0, this.f3205j.length);
            }
            this.f3199d = obj;
            this.f3200e = obj2;
            this.f3201f = obj3;
            this.f3202g = obj4;
            this.f3203h = obj5;
            this.f3204i = obj6;
            this.f3205j = obj7;
        }
    }

    private void m3505a(float f, float f2, int i) {
        m3518g(i);
        float[] fArr = this.f3199d;
        this.f3201f[i] = f;
        fArr[i] = f;
        fArr = this.f3200e;
        this.f3202g[i] = f2;
        fArr[i] = f2;
        this.f3203h[i] = m3514f((int) f, (int) f2);
        this.f3206k |= 1 << i;
    }

    private void m3513c(MotionEvent motionEvent) {
        int c = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3448c(motionEvent);
        for (int i = 0; i < c; i++) {
            int b = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, i);
            float c2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, i);
            float d = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, i);
            this.f3201f[b] = c2;
            this.f3202g[b] = d;
        }
    }

    public boolean m3529b(int i) {
        return (this.f3206k & (1 << i)) != 0;
    }

    void m3534c(int i) {
        if (this.f3196a != i) {
            this.f3196a = i;
            this.f3213r.m3488a(i);
            if (i == 0) {
                this.f3214s = null;
            }
        }
    }

    boolean m3531b(View view, int i) {
        if (view == this.f3214s && this.f3198c == i) {
            return true;
        }
        if (view == null || !this.f3213r.m3492a(view, i)) {
            return false;
        }
        this.f3198c = i;
        m3522a(view, i);
        return true;
    }

    public boolean m3524a(MotionEvent motionEvent) {
        int a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3443a(motionEvent);
        int b = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3445b(motionEvent);
        if (a == 0) {
            m3540e();
        }
        if (this.f3207l == null) {
            this.f3207l = VelocityTracker.obtain();
        }
        this.f3207l.addMovement(motionEvent);
        float y;
        int b2;
        switch (a) {
            case DurationDV.DURATION_TYPE /*0*/:
                float x = motionEvent.getX();
                y = motionEvent.getY();
                b2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, 0);
                m3505a(x, y, b2);
                View e = m3539e((int) x, (int) y);
                if (e == this.f3214s && this.f3196a == 2) {
                    m3531b(e, b2);
                }
                a = this.f3203h[b2];
                if ((this.f3211p & a) != 0) {
                    this.f3213r.m3489a(a & this.f3211p, b2);
                    break;
                }
                break;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                m3540e();
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                b = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3448c(motionEvent);
                a = 0;
                while (a < b) {
                    b2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, a);
                    float c = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, a);
                    float d = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, a);
                    float f = c - this.f3199d[b2];
                    float f2 = d - this.f3200e[b2];
                    m3511b(f, f2, b2);
                    if (this.f3196a != 1) {
                        View e2 = m3539e((int) c, (int) d);
                        if (e2 == null || !m3508a(e2, f, f2) || !m3531b(e2, b2)) {
                            a++;
                        }
                    }
                    m3513c(motionEvent);
                    break;
                }
                m3513c(motionEvent);
                break;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, b);
                float c2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, b);
                y = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, b);
                m3505a(c2, y, a);
                if (this.f3196a != 0) {
                    if (this.f3196a == 2) {
                        View e3 = m3539e((int) c2, (int) y);
                        if (e3 == this.f3214s) {
                            m3531b(e3, a);
                            break;
                        }
                    }
                }
                b = this.f3203h[a];
                if ((this.f3211p & b) != 0) {
                    this.f3213r.m3489a(b & this.f3211p, a);
                    break;
                }
                break;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                m3516f(com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, b));
                break;
        }
        if (this.f3196a == 1) {
            return true;
        }
        return false;
    }

    public void m3528b(MotionEvent motionEvent) {
        int i = 0;
        int a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3443a(motionEvent);
        int b = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3445b(motionEvent);
        if (a == 0) {
            m3540e();
        }
        if (this.f3207l == null) {
            this.f3207l = VelocityTracker.obtain();
        }
        this.f3207l.addMovement(motionEvent);
        float x;
        float y;
        View e;
        int i2;
        switch (a) {
            case DurationDV.DURATION_TYPE /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, 0);
                e = m3539e((int) x, (int) y);
                m3505a(x, y, i);
                m3531b(e, i);
                i2 = this.f3203h[i];
                if ((this.f3211p & i2) != 0) {
                    this.f3213r.m3489a(i2 & this.f3211p, i);
                }
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (this.f3196a == 1) {
                    m3517g();
                }
                m3540e();
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (this.f3196a == 1) {
                    i = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3444a(motionEvent, this.f3198c);
                    x = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, i);
                    i2 = (int) (x - this.f3201f[this.f3198c]);
                    i = (int) (com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, i) - this.f3202g[this.f3198c]);
                    m3512b(this.f3214s.getLeft() + i2, this.f3214s.getTop() + i, i2, i);
                    m3513c(motionEvent);
                    return;
                }
                i2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3448c(motionEvent);
                while (i < i2) {
                    a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, i);
                    float c = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, i);
                    float d = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, i);
                    float f = c - this.f3199d[a];
                    float f2 = d - this.f3200e[a];
                    m3511b(f, f2, a);
                    if (this.f3196a != 1) {
                        e = m3539e((int) c, (int) d);
                        if (!m3508a(e, f, f2) || !m3531b(e, a)) {
                            i++;
                        }
                    }
                    m3513c(motionEvent);
                }
                m3513c(motionEvent);
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                if (this.f3196a == 1) {
                    m3504a(0.0f, 0.0f);
                }
                m3540e();
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                i = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, b);
                x = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, b);
                y = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, b);
                m3505a(x, y, i);
                if (this.f3196a == 0) {
                    m3531b(m3539e((int) x, (int) y), i);
                    i2 = this.f3203h[i];
                    if ((this.f3211p & i2) != 0) {
                        this.f3213r.m3489a(i2 & this.f3211p, i);
                    }
                } else if (m3538d((int) x, (int) y)) {
                    m3531b(this.f3214s, i);
                }
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, b);
                if (this.f3196a == 1 && a == this.f3198c) {
                    b = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3448c(motionEvent);
                    while (i < b) {
                        int b2 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3446b(motionEvent, i);
                        if (b2 != this.f3198c) {
                            if (m3539e((int) com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3447c(motionEvent, i), (int) com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3449d(motionEvent, i)) == this.f3214s && m3531b(this.f3214s, b2)) {
                                i = this.f3198c;
                                if (i == -1) {
                                    m3517g();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        m3517g();
                    }
                }
                m3516f(a);
            default:
        }
    }

    private void m3511b(float f, float f2, int i) {
        int i2 = 1;
        if (!m3506a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (m3506a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (m3506a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (m3506a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.f3204i;
            iArr[i] = iArr[i] | i2;
            this.f3213r.m3495b(i2, i);
        }
    }

    private boolean m3506a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.f3203h[i] & i2) != i2 || (this.f3211p & i2) == 0 || (this.f3205j[i] & i2) == i2 || (this.f3204i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.f3197b) && abs2 <= ((float) this.f3197b)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.f3213r.m3497b(i2)) {
            int[] iArr = this.f3205j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.f3204i[i] & i2) != 0 || abs <= ((float) this.f3197b)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean m3508a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.f3213r.m3486a(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.f3213r.m3493b(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            if ((f * f) + (f2 * f2) <= ((float) (this.f3197b * this.f3197b))) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= ((float) this.f3197b)) {
                return false;
            }
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.f3197b)) {
                return false;
            }
            return true;
        }
    }

    public boolean m3537d(int i) {
        int length = this.f3199d.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (m3530b(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean m3530b(int i, int i2) {
        if (!m3529b(i2)) {
            return false;
        }
        boolean z;
        boolean z2 = (i & 1) == 1;
        if ((i & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        float f = this.f3201f[i2] - this.f3199d[i2];
        float f2 = this.f3202g[i2] - this.f3200e[i2];
        if (z2 && z) {
            if ((f * f) + (f2 * f2) <= ((float) (this.f3197b * this.f3197b))) {
                return false;
            }
            return true;
        } else if (z2) {
            if (Math.abs(f) <= ((float) this.f3197b)) {
                return false;
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.f3197b)) {
                return false;
            }
            return true;
        }
    }

    public boolean m3541e(int i) {
        int length = this.f3203h.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (m3535c(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean m3535c(int i, int i2) {
        return m3529b(i2) && (this.f3203h[i2] & i) != 0;
    }

    private void m3517g() {
        this.f3207l.computeCurrentVelocity(DateUtils.MILLIS_IN_SECOND, this.f3208m);
        m3504a(m3499a(com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3457a(this.f3207l, this.f3198c), this.f3209n, this.f3208m), m3499a(com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3458b(this.f3207l, this.f3198c), this.f3209n, this.f3208m));
    }

    private void m3512b(int i, int i2, int i3, int i4) {
        int a;
        int b;
        int left = this.f3214s.getLeft();
        int top = this.f3214s.getTop();
        if (i3 != 0) {
            a = this.f3213r.m3487a(this.f3214s, i, i3);
            this.f3214s.offsetLeftAndRight(a - left);
        } else {
            a = i;
        }
        if (i4 != 0) {
            b = this.f3213r.m3494b(this.f3214s, i2, i4);
            this.f3214s.offsetTopAndBottom(b - top);
        } else {
            b = i2;
        }
        if (i3 != 0 || i4 != 0) {
            this.f3213r.m3491a(this.f3214s, a, b, a - left, b - top);
        }
    }

    public boolean m3538d(int i, int i2) {
        return m3532b(this.f3214s, i, i2);
    }

    public boolean m3532b(View view, int i, int i2) {
        if (view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View m3539e(int i, int i2) {
        for (int childCount = this.f3216u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f3216u.getChildAt(this.f3213r.m3498c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int m3514f(int i, int i2) {
        int i3 = 0;
        if (i < this.f3216u.getLeft() + this.f3210o) {
            i3 = 1;
        }
        if (i2 < this.f3216u.getTop() + this.f3210o) {
            i3 |= 4;
        }
        if (i > this.f3216u.getRight() - this.f3210o) {
            i3 |= 2;
        }
        if (i2 > this.f3216u.getBottom() - this.f3210o) {
            return i3 | 8;
        }
        return i3;
    }
}
