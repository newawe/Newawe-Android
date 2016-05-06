package com.startapp.android.publish.slider.sliding;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import com.android.volley.DefaultRetryPolicy;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* compiled from: StartAppSDK */
public class DrawerLayout extends ViewGroup {
    private static final int[] f3155a;
    private int f3156b;
    private int f3157c;
    private float f3158d;
    private Paint f3159e;
    private final StartAppSDK f3160f;
    private final StartAppSDK f3161g;
    private final StartAppSDK f3162h;
    private final StartAppSDK f3163i;
    private int f3164j;
    private boolean f3165k;
    private boolean f3166l;
    private int f3167m;
    private int f3168n;
    private boolean f3169o;
    private boolean f3170p;
    private StartAppSDK f3171q;
    private float f3172r;
    private float f3173s;
    private Drawable f3174t;
    private Drawable f3175u;

    /* compiled from: StartAppSDK */
    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f3147a;
        int f3148b;
        int f3149c;

        /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.SavedState.1 */
        static class StartAppSDK implements Creator<SavedState> {
            StartAppSDK() {
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return m3207a(x0);
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return m3208a(x0);
            }

            public SavedState m3207a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m3208a(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel in) {
            super(in);
            this.f3147a = 0;
            this.f3148b = 0;
            this.f3149c = 0;
            this.f3147a = in.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
            this.f3147a = 0;
            this.f3148b = 0;
            this.f3149c = 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.f3147a);
        }

        static {
            CREATOR = new StartAppSDK();
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.b */
    public interface StartAppSDK {
        void m3209a(int i);

        void m3210a(View view);

        void m3211a(View view, float f);

        void m3212b(View view);
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.c */
    public static class StartAppSDK extends MarginLayoutParams {
        public int f3150a;
        float f3151b;
        boolean f3152c;
        boolean f3153d;

        public StartAppSDK(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3150a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f3155a);
            this.f3150a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public StartAppSDK(int i, int i2) {
            super(i, i2);
            this.f3150a = 0;
        }

        public StartAppSDK(StartAppSDK startAppSDK) {
            super(startAppSDK);
            this.f3150a = 0;
            this.f3150a = startAppSDK.f3150a;
        }

        public StartAppSDK(LayoutParams layoutParams) {
            super(layoutParams);
            this.f3150a = 0;
        }

        public StartAppSDK(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3150a = 0;
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.a */
    class StartAppSDK extends com.startapp.android.publish.slider.sliding.p024b.StartAppSDK {
        final /* synthetic */ DrawerLayout f4168a;
        private final Rect f4169c;

        StartAppSDK(DrawerLayout drawerLayout) {
            this.f4168a = drawerLayout;
            this.f4169c = new Rect();
        }

        public void m4869a(View view, com.startapp.android.publish.slider.sliding.p023a.StartAppSDK startAppSDK) {
            com.startapp.android.publish.slider.sliding.p023a.StartAppSDK a = com.startapp.android.publish.slider.sliding.p023a.StartAppSDK.m3286a(startAppSDK);
            super.m3397a(view, a);
            startAppSDK.m3292a(view);
            ViewParent c = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3468c(view);
            if (c instanceof View) {
                startAppSDK.m3301c((View) c);
            }
            m4868a(startAppSDK, a);
            a.m3326s();
            int childCount = this.f4168a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f4168a.getChildAt(i);
                if (!m4870a(childAt)) {
                    startAppSDK.m3297b(childAt);
                }
            }
        }

        public boolean m4871a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (m4870a(view)) {
                return false;
            }
            return super.m3399a(viewGroup, view, accessibilityEvent);
        }

        public boolean m4870a(View view) {
            View a = this.f4168a.m3220a();
            return (a == null || a == view) ? false : true;
        }

        private void m4868a(com.startapp.android.publish.slider.sliding.p023a.StartAppSDK startAppSDK, com.startapp.android.publish.slider.sliding.p023a.StartAppSDK startAppSDK2) {
            Rect rect = this.f4169c;
            startAppSDK2.m3291a(rect);
            startAppSDK.m3296b(rect);
            startAppSDK2.m3300c(rect);
            startAppSDK.m3305d(rect);
            startAppSDK.m3303c(startAppSDK2.m3313g());
            startAppSDK.m3293a(startAppSDK2.m3322o());
            startAppSDK.m3298b(startAppSDK2.m3323p());
            startAppSDK.m3302c(startAppSDK2.m3325r());
            startAppSDK.m3314h(startAppSDK2.m3319l());
            startAppSDK.m3310f(startAppSDK2.m3317j());
            startAppSDK.m3294a(startAppSDK2.m3309e());
            startAppSDK.m3299b(startAppSDK2.m3311f());
            startAppSDK.m3306d(startAppSDK2.m3315h());
            startAppSDK.m3308e(startAppSDK2.m3316i());
            startAppSDK.m3312g(startAppSDK2.m3318k());
            startAppSDK.m3290a(startAppSDK2.m3295b());
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.d */
    private class StartAppSDK extends com.startapp.android.publish.slider.sliding.StartAppSDK.StartAppSDK {
        final /* synthetic */ DrawerLayout f4170a;
        private final int f4171b;
        private StartAppSDK f4172c;
        private final Runnable f4173d;

        /* renamed from: com.startapp.android.publish.slider.sliding.DrawerLayout.d.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f3154a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f3154a = startAppSDK;
            }

            public void run() {
                this.f3154a.m4874c();
            }
        }

        public StartAppSDK(DrawerLayout drawerLayout, int i) {
            this.f4170a = drawerLayout;
            this.f4173d = new StartAppSDK(this);
            this.f4171b = i;
        }

        public void m4882a(StartAppSDK startAppSDK) {
            this.f4172c = startAppSDK;
        }

        public void m4877a() {
            this.f4170a.removeCallbacks(this.f4173d);
        }

        public boolean m4883a(View view, int i) {
            return this.f4170a.m3235g(view) && this.f4170a.m3226a(view, this.f4171b) && this.f4170a.m3219a(view) == 0;
        }

        public void m4878a(int i) {
            this.f4170a.m3223a(this.f4171b, i, this.f4172c.m3533c());
        }

        public void m4881a(View view, int i, int i2, int i3, int i4) {
            float f;
            int width = view.getWidth();
            if (this.f4170a.m3226a(view, 3)) {
                f = ((float) (width + i)) / ((float) width);
            } else {
                f = ((float) (this.f4170a.getWidth() - i)) / ((float) width);
            }
            this.f4170a.m3229b(view, f);
            view.setVisibility(f == 0.0f ? 4 : 0);
            this.f4170a.invalidate();
        }

        public void m4886b(View view, int i) {
            ((StartAppSDK) view.getLayoutParams()).f3152c = false;
            m4873b();
        }

        private void m4873b() {
            int i = 3;
            if (this.f4171b == 3) {
                i = 5;
            }
            View a = this.f4170a.m3221a(i);
            if (a != null) {
                this.f4170a.m3237i(a);
            }
        }

        public void m4880a(View view, float f, float f2) {
            int i;
            float d = this.f4170a.m3232d(view);
            int width = view.getWidth();
            if (this.f4170a.m3226a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && d > 0.5f)) ? 0 : -width;
            } else {
                i = this.f4170a.getWidth();
                if (f < 0.0f || (f == 0.0f && d < 0.5f)) {
                    i -= width;
                }
            }
            this.f4172c.m3523a(i, view.getTop());
            this.f4170a.invalidate();
        }

        public void m4879a(int i, int i2) {
            this.f4170a.postDelayed(this.f4173d, 160);
        }

        private void m4874c() {
            View view;
            int i;
            int i2 = 0;
            int b = this.f4172c.m3527b();
            boolean z = this.f4171b == 3;
            if (z) {
                View a = this.f4170a.m3221a(3);
                if (a != null) {
                    i2 = -a.getWidth();
                }
                i2 += b;
                view = a;
                i = i2;
            } else {
                i2 = this.f4170a.getWidth() - b;
                view = this.f4170a.m3221a(5);
                i = i2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && this.f4170a.m3219a(view) == 0) {
                StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
                this.f4172c.m3525a(view, i, view.getTop());
                startAppSDK.f3152c = true;
                this.f4170a.invalidate();
                m4873b();
                this.f4170a.m3230c();
            }
        }

        public boolean m4887b(int i) {
            return false;
        }

        public void m4885b(int i, int i2) {
            View a;
            if ((i & 1) == 1) {
                a = this.f4170a.m3221a(3);
            } else {
                a = this.f4170a.m3221a(5);
            }
            if (a != null && this.f4170a.m3219a(a) == 0) {
                this.f4172c.m3522a(a, i2);
            }
        }

        public int m4875a(View view) {
            return view.getWidth();
        }

        public int m4876a(View view, int i, int i2) {
            if (this.f4170a.m3226a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = this.f4170a.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public int m4884b(View view, int i, int i2) {
            return view.getTop();
        }
    }

    static {
        f3155a = new int[]{16842931};
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f3157c = -1728053248;
        this.f3159e = new Paint();
        this.f3166l = true;
        float f = getResources().getDisplayMetrics().density;
        this.f3156b = (int) ((64.0f * f) + 0.5f);
        f *= 400.0f;
        this.f3162h = new StartAppSDK(this, 3);
        this.f3163i = new StartAppSDK(this, 5);
        this.f3160f = StartAppSDK.m3502a((ViewGroup) this, 0.5f, this.f3162h);
        this.f3160f.m3521a(1);
        this.f3160f.m3520a(f);
        this.f3162h.m4882a(this.f3160f);
        this.f3161g = StartAppSDK.m3502a((ViewGroup) this, 0.5f, this.f3163i);
        this.f3161g.m3521a(2);
        this.f3161g.m3520a(f);
        this.f3163i.m4882a(this.f3161g);
        setFocusableInTouchMode(true);
        com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3466a(this, new StartAppSDK(this));
        com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3475a(this, false);
    }

    public void setScrimColor(int color) {
        this.f3157c = color;
        invalidate();
    }

    public void setDrawerListener(StartAppSDK listener) {
        this.f3171q = listener;
    }

    public void setDrawerLockMode(int lockMode) {
        m3222a(lockMode, 3);
        m3222a(lockMode, 5);
    }

    public void m3222a(int i, int i2) {
        int a = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3433a(i2, com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3467b(this));
        if (a == 3) {
            this.f3167m = i;
        } else if (a == 5) {
            this.f3168n = i;
        }
        if (i != 0) {
            (a == 3 ? this.f3160f : this.f3161g).m3540e();
        }
        View a2;
        switch (i) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                a2 = m3221a(a);
                if (a2 != null) {
                    m3237i(a2);
                }
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                a2 = m3221a(a);
                if (a2 != null) {
                    m3236h(a2);
                }
            default:
        }
    }

    public int m3219a(View view) {
        int e = m3233e(view);
        if (e == 3) {
            return this.f3167m;
        }
        if (e == 5) {
            return this.f3168n;
        }
        return 0;
    }

    void m3223a(int i, int i2, View view) {
        int i3 = 1;
        int a = this.f3160f.m3519a();
        int a2 = this.f3161g.m3519a();
        if (!(a == 1 || a2 == 1)) {
            i3 = (a == 2 || a2 == 2) ? 2 : 0;
        }
        if (view != null && i2 == 0) {
            StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
            if (startAppSDK.f3151b == 0.0f) {
                m3228b(view);
            } else if (startAppSDK.f3151b == DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                m3231c(view);
            }
        }
        if (i3 != this.f3164j) {
            this.f3164j = i3;
            if (this.f3171q != null) {
                this.f3171q.m3209a(i3);
            }
        }
    }

    void m3228b(View view) {
        StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
        if (startAppSDK.f3153d) {
            startAppSDK.f3153d = false;
            if (this.f3171q != null) {
                this.f3171q.m3212b(view);
            }
            sendAccessibilityEvent(32);
        }
    }

    void m3231c(View view) {
        StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
        if (!startAppSDK.f3153d) {
            startAppSDK.f3153d = true;
            if (this.f3171q != null) {
                this.f3171q.m3210a(view);
            }
            view.sendAccessibilityEvent(32);
        }
    }

    void m3224a(View view, float f) {
        if (this.f3171q != null) {
            this.f3171q.m3211a(view, f);
        }
    }

    void m3229b(View view, float f) {
        StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
        if (f != startAppSDK.f3151b) {
            startAppSDK.f3151b = f;
            m3224a(view, f);
        }
    }

    float m3232d(View view) {
        return ((StartAppSDK) view.getLayoutParams()).f3151b;
    }

    int m3233e(View view) {
        return com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3433a(((StartAppSDK) view.getLayoutParams()).f3150a, com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3467b(view));
    }

    boolean m3226a(View view, int i) {
        return (m3233e(view) & i) == i;
    }

    View m3220a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (((StartAppSDK) childAt.getLayoutParams()).f3153d) {
                return childAt;
            }
        }
        return null;
    }

    View m3221a(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((m3233e(childAt) & 7) == (i & 7)) {
                return childAt;
            }
        }
        return null;
    }

    static String m3213b(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        if ((i & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(i);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3166l = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3166l = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r12, int r13) {
        /*
        r11 = this;
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r4 = 0;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r10 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = android.view.View.MeasureSpec.getMode(r12);
        r5 = android.view.View.MeasureSpec.getMode(r13);
        r2 = android.view.View.MeasureSpec.getSize(r12);
        r0 = android.view.View.MeasureSpec.getSize(r13);
        if (r3 != r10) goto L_0x001b;
    L_0x0019:
        if (r5 == r10) goto L_0x0046;
    L_0x001b:
        r6 = r11.isInEditMode();
        if (r6 == 0) goto L_0x0048;
    L_0x0021:
        if (r3 != r7) goto L_0x0040;
    L_0x0023:
        if (r5 != r7) goto L_0x0044;
    L_0x0025:
        r1 = r0;
    L_0x0026:
        r11.setMeasuredDimension(r2, r1);
        r5 = r11.getChildCount();
        r3 = r4;
    L_0x002e:
        if (r3 >= r5) goto L_0x0109;
    L_0x0030:
        r6 = r11.getChildAt(r3);
        r0 = r6.getVisibility();
        r7 = 8;
        if (r0 != r7) goto L_0x0050;
    L_0x003c:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x002e;
    L_0x0040:
        if (r3 != 0) goto L_0x0023;
    L_0x0042:
        r2 = r1;
        goto L_0x0023;
    L_0x0044:
        if (r5 == 0) goto L_0x0026;
    L_0x0046:
        r1 = r0;
        goto L_0x0026;
    L_0x0048:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "DrawerLayout must be measured with MeasureSpec.EXACTLY.";
        r0.<init>(r1);
        throw r0;
    L_0x0050:
        r0 = r6.getLayoutParams();
        r0 = (com.startapp.android.publish.slider.sliding.DrawerLayout.StartAppSDK) r0;
        r7 = r11.m3234f(r6);
        if (r7 == 0) goto L_0x0077;
    L_0x005c:
        r7 = r0.leftMargin;
        r7 = r2 - r7;
        r8 = r0.rightMargin;
        r7 = r7 - r8;
        r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r10);
        r8 = r0.topMargin;
        r8 = r1 - r8;
        r0 = r0.bottomMargin;
        r0 = r8 - r0;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r10);
        r6.measure(r7, r0);
        goto L_0x003c;
    L_0x0077:
        r7 = r11.m3235g(r6);
        if (r7 == 0) goto L_0x00da;
    L_0x007d:
        r7 = r11.m3233e(r6);
        r7 = r7 & 7;
        r8 = r4 & r7;
        if (r8 == 0) goto L_0x00bc;
    L_0x0087:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child drawer has absolute gravity ";
        r1 = r1.append(r2);
        r2 = m3213b(r7);
        r1 = r1.append(r2);
        r2 = " but this ";
        r1 = r1.append(r2);
        r2 = "DrawerLayout";
        r1 = r1.append(r2);
        r2 = " already has a ";
        r1 = r1.append(r2);
        r2 = "drawer view along that edge";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00bc:
        r7 = r11.f3156b;
        r8 = r0.leftMargin;
        r7 = r7 + r8;
        r8 = r0.rightMargin;
        r7 = r7 + r8;
        r8 = r0.width;
        r7 = getChildMeasureSpec(r12, r7, r8);
        r8 = r0.topMargin;
        r9 = r0.bottomMargin;
        r8 = r8 + r9;
        r0 = r0.height;
        r0 = getChildMeasureSpec(r13, r8, r0);
        r6.measure(r7, r0);
        goto L_0x003c;
    L_0x00da:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child ";
        r1 = r1.append(r2);
        r1 = r1.append(r6);
        r2 = " at index ";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = " does not have a valid layout_gravity - must be Gravity.LEFT, ";
        r1 = r1.append(r2);
        r2 = "Gravity.RIGHT or Gravity.NO_GRAVITY";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0109:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.slider.sliding.DrawerLayout.onMeasure(int, int):void");
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.f3165k = true;
        int i = r - l;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                StartAppSDK startAppSDK = (StartAppSDK) childAt.getLayoutParams();
                if (m3234f(childAt)) {
                    childAt.layout(startAppSDK.leftMargin, startAppSDK.topMargin, startAppSDK.leftMargin + childAt.getMeasuredWidth(), startAppSDK.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i3;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (m3226a(childAt, 3)) {
                        i3 = ((int) (((float) measuredWidth) * startAppSDK.f3151b)) + (-measuredWidth);
                        f = ((float) (measuredWidth + i3)) / ((float) measuredWidth);
                    } else {
                        i3 = i - ((int) (((float) measuredWidth) * startAppSDK.f3151b));
                        f = ((float) (i - i3)) / ((float) measuredWidth);
                    }
                    Object obj = f != startAppSDK.f3151b ? 1 : null;
                    int i4;
                    switch (startAppSDK.f3150a & 112) {
                        case ConnectionResult.API_UNAVAILABLE /*16*/:
                            int i5 = b - t;
                            i4 = (i5 - measuredHeight) / 2;
                            if (i4 < startAppSDK.topMargin) {
                                i4 = startAppSDK.topMargin;
                            } else if (i4 + measuredHeight > i5 - startAppSDK.bottomMargin) {
                                i4 = (i5 - startAppSDK.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i3, i4, measuredWidth + i3, measuredHeight + i4);
                            break;
                        case MetaData.DEFAULT_PROBABILITY_3D /*80*/:
                            i4 = b - t;
                            childAt.layout(i3, (i4 - startAppSDK.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i3, i4 - startAppSDK.bottomMargin);
                            break;
                        default:
                            childAt.layout(i3, startAppSDK.topMargin, measuredWidth + i3, measuredHeight);
                            break;
                    }
                    if (obj != null) {
                        m3229b(childAt, f);
                    }
                    int i6 = startAppSDK.f3151b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i6) {
                        childAt.setVisibility(i6);
                    }
                }
            }
        }
        this.f3165k = false;
        this.f3166l = false;
    }

    public void requestLayout() {
        if (!this.f3165k) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((StartAppSDK) getChildAt(i).getLayoutParams()).f3151b);
        }
        this.f3158d = f;
        if ((this.f3160f.m3526a(true) | this.f3161g.m3526a(true)) != 0) {
            com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3465a(this);
        }
    }

    private static boolean m3218k(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        int i;
        int height = getHeight();
        boolean f = m3234f(child);
        int i2 = 0;
        int width = getWidth();
        int save = canvas.save();
        if (f) {
            int childCount = getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != child && childAt.getVisibility() == 0 && m3218k(childAt) && m3235g(childAt)) {
                    if (childAt.getHeight() < height) {
                        i = width;
                    } else if (m3226a(childAt, 3)) {
                        i = childAt.getRight();
                        if (i <= i2) {
                            i = i2;
                        }
                        i2 = i;
                        i = width;
                    } else {
                        i = childAt.getLeft();
                        if (i < width) {
                        }
                    }
                    i3++;
                    width = i;
                }
                i = width;
                i3++;
                width = i;
            }
            canvas.clipRect(i2, 0, width, getHeight());
        }
        i = width;
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(save);
        if (this.f3158d > 0.0f && f) {
            this.f3159e.setColor((((int) (((float) ((this.f3157c & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.f3158d)) << 24) | (this.f3157c & ViewCompat.MEASURED_SIZE_MASK));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), this.f3159e);
        } else if (this.f3174t != null && m3226a(child, 3)) {
            i = this.f3174t.getIntrinsicWidth();
            i2 = child.getRight();
            r2 = Math.max(0.0f, Math.min(((float) i2) / ((float) this.f3160f.m3527b()), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            this.f3174t.setBounds(i2, child.getTop(), i + i2, child.getBottom());
            this.f3174t.setAlpha((int) (255.0f * r2));
            this.f3174t.draw(canvas);
        } else if (this.f3175u != null && m3226a(child, 5)) {
            i = this.f3175u.getIntrinsicWidth();
            i2 = child.getLeft();
            r2 = Math.max(0.0f, Math.min(((float) (getWidth() - i2)) / ((float) this.f3161g.m3527b()), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            this.f3175u.setBounds(i2 - i, child.getTop(), i2, child.getBottom());
            this.f3175u.setAlpha((int) (255.0f * r2));
            this.f3175u.draw(canvas);
        }
        return drawChild;
    }

    boolean m3234f(View view) {
        return ((StartAppSDK) view.getLayoutParams()).f3150a == 0;
    }

    boolean m3235g(View view) {
        return (com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3433a(((StartAppSDK) view.getLayoutParams()).f3150a, com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3467b(view)) & 7) != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3443a(r8);
        r3 = r7.f3160f;
        r3 = r3.m3524a(r8);
        r4 = r7.f3161g;
        r4 = r4.m3524a(r8);
        r3 = r3 | r4;
        switch(r0) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0063;
            case 2: goto L_0x004e;
            case 3: goto L_0x0063;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r2;
    L_0x0017:
        if (r3 != 0) goto L_0x0025;
    L_0x0019:
        if (r0 != 0) goto L_0x0025;
    L_0x001b:
        r0 = r7.m3215e();
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r0 = r7.f3170p;
        if (r0 == 0) goto L_0x0026;
    L_0x0025:
        r2 = r1;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r8.getX();
        r4 = r8.getY();
        r7.f3172r = r0;
        r7.f3173s = r4;
        r5 = r7.f3158d;
        r6 = 0;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x006b;
    L_0x003a:
        r5 = r7.f3160f;
        r0 = (int) r0;
        r4 = (int) r4;
        r0 = r5.m3539e(r0, r4);
        r0 = r7.m3234f(r0);
        if (r0 == 0) goto L_0x006b;
    L_0x0048:
        r0 = r1;
    L_0x0049:
        r7.f3169o = r2;
        r7.f3170p = r2;
        goto L_0x0017;
    L_0x004e:
        r0 = r7.f3160f;
        r4 = 3;
        r0 = r0.m3537d(r4);
        if (r0 == 0) goto L_0x0016;
    L_0x0057:
        r0 = r7.f3162h;
        r0.m4877a();
        r0 = r7.f3163i;
        r0.m4877a();
        r0 = r2;
        goto L_0x0017;
    L_0x0063:
        r7.m3225a(r1);
        r7.f3169o = r2;
        r7.f3170p = r2;
        goto L_0x0016;
    L_0x006b:
        r0 = r2;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.slider.sliding.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent ev) {
        this.f3160f.m3528b(ev);
        this.f3161g.m3528b(ev);
        float x;
        float y;
        switch (ev.getAction() & MotionEventCompat.ACTION_MASK) {
            case DurationDV.DURATION_TYPE /*0*/:
                x = ev.getX();
                y = ev.getY();
                this.f3172r = x;
                this.f3173s = y;
                this.f3169o = false;
                this.f3170p = false;
                break;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                boolean z;
                x = ev.getX();
                y = ev.getY();
                View e = this.f3160f.m3539e((int) x, (int) y);
                if (e != null && m3234f(e)) {
                    x -= this.f3172r;
                    y -= this.f3173s;
                    int d = this.f3160f.m3536d();
                    if ((x * x) + (y * y) < ((float) (d * d))) {
                        View a = m3220a();
                        if (a != null) {
                            z = m3219a(a) == 2;
                            m3225a(z);
                            this.f3169o = false;
                            break;
                        }
                    }
                }
                z = true;
                m3225a(z);
                this.f3169o = false;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                m3225a(true);
                this.f3169o = false;
                this.f3170p = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (!(this.f3160f.m3541e(1) || this.f3161g.m3541e(2))) {
            super.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
        this.f3169o = disallowIntercept;
        if (disallowIntercept) {
            m3225a(true);
        }
    }

    public void m3227b() {
        m3225a(false);
    }

    void m3225a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            StartAppSDK startAppSDK = (StartAppSDK) childAt.getLayoutParams();
            if (m3235g(childAt) && (!z || startAppSDK.f3152c)) {
                int width = childAt.getWidth();
                if (m3226a(childAt, 3)) {
                    i |= this.f3160f.m3525a(childAt, -width, childAt.getTop());
                } else {
                    i |= this.f3161g.m3525a(childAt, getWidth(), childAt.getTop());
                }
                startAppSDK.f3152c = false;
            }
        }
        this.f3162h.m4877a();
        this.f3163i.m4877a();
        if (i != 0) {
            invalidate();
        }
    }

    public void m3236h(View view) {
        if (m3235g(view)) {
            if (this.f3166l) {
                StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
                startAppSDK.f3151b = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
                startAppSDK.f3153d = true;
            } else if (m3226a(view, 3)) {
                this.f3160f.m3525a(view, 0, view.getTop());
            } else {
                this.f3161g.m3525a(view, getWidth() - view.getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void m3237i(View view) {
        if (m3235g(view)) {
            if (this.f3166l) {
                StartAppSDK startAppSDK = (StartAppSDK) view.getLayoutParams();
                startAppSDK.f3151b = 0.0f;
                startAppSDK.f3153d = false;
            } else if (m3226a(view, 3)) {
                this.f3160f.m3525a(view, -view.getWidth(), view.getTop());
            } else {
                this.f3161g.m3525a(view, getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public boolean m3238j(View view) {
        if (m3235g(view)) {
            return ((StartAppSDK) view.getLayoutParams()).f3151b > 0.0f;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    private boolean m3215e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((StartAppSDK) getChildAt(i).getLayoutParams()).f3152c) {
                return true;
            }
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new StartAppSDK(-1, -1);
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        if (p instanceof StartAppSDK) {
            return new StartAppSDK((StartAppSDK) p);
        }
        return p instanceof MarginLayoutParams ? new StartAppSDK((MarginLayoutParams) p) : new StartAppSDK(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof StartAppSDK) && super.checkLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new StartAppSDK(getContext(), attrs);
    }

    private boolean m3216f() {
        return m3217g() != null;
    }

    private View m3217g() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m3235g(childAt) && m3238j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void m3230c() {
        int i = 0;
        if (!this.f3170p) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            while (i < childCount) {
                getChildAt(i).dispatchTouchEvent(obtain);
                i++;
            }
            obtain.recycle();
            this.f3170p = true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !m3216f()) {
            return super.onKeyDown(keyCode, event);
        }
        com.startapp.android.publish.slider.sliding.p024b.StartAppSDK.m3436a(event);
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyUp(keyCode, event);
        }
        View g = m3217g();
        if (g != null && m3219a(g) == 0) {
            m3227b();
        }
        return g != null;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f3147a != 0) {
            View a = m3221a(savedState.f3147a);
            if (a != null) {
                m3236h(a);
            }
        }
        m3222a(savedState.f3148b, 3);
        m3222a(savedState.f3149c, 5);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m3235g(childAt)) {
                StartAppSDK startAppSDK = (StartAppSDK) childAt.getLayoutParams();
                if (startAppSDK.f3153d) {
                    savedState.f3147a = startAppSDK.f3150a;
                    break;
                }
            }
        }
        savedState.f3148b = this.f3167m;
        savedState.f3149c = this.f3168n;
        return savedState;
    }
}
