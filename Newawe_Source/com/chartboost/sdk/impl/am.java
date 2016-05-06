package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public abstract class am extends RelativeLayout {
    private static final String f420b;
    protected ai f421a;
    private an f422c;
    private C0384a f423d;

    /* renamed from: com.chartboost.sdk.impl.am.1 */
    class C03821 implements Runnable {
        final /* synthetic */ boolean f412a;
        final /* synthetic */ am f413b;

        C03821(am amVar, boolean z) {
            this.f413b = amVar;
            this.f412a = z;
        }

        public void run() {
            if (!this.f412a) {
                this.f413b.setVisibility(8);
                this.f413b.clearAnimation();
            }
            this.f413b.f421a.h.remove(Integer.valueOf(hashCode()));
        }
    }

    /* renamed from: com.chartboost.sdk.impl.am.2 */
    static /* synthetic */ class C03832 {
        static final /* synthetic */ int[] f414a;

        static {
            f414a = new int[C0384a.values().length];
            try {
                f414a[C0384a.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f414a[C0384a.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f414a[C0384a.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f414a[C0384a.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.am.a */
    public enum C0384a {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    protected abstract View m501a();

    protected abstract int m504b();

    static {
        f420b = am.class.getSimpleName();
    }

    public am(Context context, ai aiVar) {
        super(context);
        this.f421a = aiVar;
        this.f423d = C0384a.BOTTOM;
        m499a(context);
    }

    public void m502a(C0384a c0384a) {
        if (c0384a == null) {
            CBLogging.m77b(f420b, "Side object cannot be null");
            return;
        }
        this.f423d = c0384a;
        LayoutParams layoutParams = null;
        setClickable(false);
        int b = m504b();
        switch (C03832.f414a[this.f423d.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.m86a(b, getContext()));
                layoutParams.addRule(10);
                this.f422c.m506b(1);
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.m86a(b, getContext()));
                layoutParams.addRule(12);
                this.f422c.m506b(4);
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.m86a(b, getContext()), -1);
                layoutParams.addRule(9);
                this.f422c.m506b(8);
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.m86a(b, getContext()), -1);
                layoutParams.addRule(11);
                this.f422c.m506b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private void m499a(Context context) {
        Context context2 = getContext();
        setGravity(17);
        this.f422c = new an(context2);
        this.f422c.m505a(-1);
        this.f422c.setBackgroundColor(-855638017);
        addView(this.f422c, new RelativeLayout.LayoutParams(-1, -1));
        addView(m501a(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public void m503a(boolean z) {
        m500a(z, 500);
    }

    private void m500a(boolean z, long j) {
        this.f421a.f4233z = z;
        if (!z || getVisibility() != 0) {
            if (z || getVisibility() != 8) {
                Animation translateAnimation;
                Runnable c03821 = new C03821(this, z);
                if (z) {
                    setVisibility(0);
                }
                float a = CBUtility.m84a((float) m504b(), getContext());
                float f;
                switch (C03832.f414a[this.f423d.ordinal()]) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, z ? 0.0f : -a);
                        break;
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        if (z) {
                            f = a;
                        } else {
                            f = 0.0f;
                        }
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, a);
                        break;
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, z ? 0.0f : -a, 0.0f, 0.0f);
                        break;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        f = z ? a : 0.0f;
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, a, 0.0f, 0.0f);
                        break;
                    default:
                        translateAnimation = null;
                        break;
                }
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(!z);
                startAnimation(translateAnimation);
                this.f421a.h.put(Integer.valueOf(hashCode()), c03821);
                C0372g.f378a.postDelayed(c03821, j);
            }
        }
    }
}
