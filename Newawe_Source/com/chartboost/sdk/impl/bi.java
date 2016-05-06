package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0367f;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0340c;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class bi {

    /* renamed from: com.chartboost.sdk.impl.bi.1 */
    static class C04311 implements OnGlobalLayoutListener {
        final /* synthetic */ View f603a;
        final /* synthetic */ C0435b f604b;
        final /* synthetic */ C0343a f605c;
        final /* synthetic */ C0434a f606d;
        final /* synthetic */ boolean f607e;

        C04311(View view, C0435b c0435b, C0343a c0343a, C0434a c0434a, boolean z) {
            this.f603a = view;
            this.f604b = c0435b;
            this.f605c = c0343a;
            this.f606d = c0434a;
            this.f607e = z;
        }

        public void onGlobalLayout() {
            this.f603a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            bi.m704c(this.f604b, this.f605c, this.f606d, this.f607e);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bi.2 */
    static class C04322 implements Runnable {
        final /* synthetic */ C0434a f608a;
        final /* synthetic */ C0343a f609b;

        C04322(C0434a c0434a, C0343a c0343a) {
            this.f608a = c0434a;
            this.f609b = c0343a;
        }

        public void run() {
            this.f608a.m696a(this.f609b);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bi.3 */
    static /* synthetic */ class C04333 {
        static final /* synthetic */ int[] f610a;

        static {
            f610a = new int[C0435b.values().length];
            try {
                f610a[C0435b.CBAnimationTypeFade.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f610a[C0435b.CBAnimationTypePerspectiveZoom.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f610a[C0435b.CBAnimationTypePerspectiveRotate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f610a[C0435b.CBAnimationTypeSlideFromBottom.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f610a[C0435b.CBAnimationTypeSlideFromTop.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f610a[C0435b.CBAnimationTypeSlideFromLeft.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f610a[C0435b.CBAnimationTypeSlideFromRight.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f610a[C0435b.CBAnimationTypeBounce.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bi.a */
    public interface C0434a {
        void m696a(C0343a c0343a);
    }

    /* renamed from: com.chartboost.sdk.impl.bi.b */
    public enum C0435b {
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromTop,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeFade,
        CBAnimationTypeNone,
        CBAnimationTypeSlideFromLeft,
        CBAnimationTypeSlideFromRight;

        public static C0435b m697a(int i) {
            if (i != 0 && i > 0 && i <= C0435b.values().length) {
                return C0435b.values()[i - 1];
            }
            return null;
        }
    }

    public static void m698a(C0435b c0435b, C0343a c0343a, C0434a c0434a) {
        m703b(c0435b, c0343a, c0434a, true);
    }

    public static void m702b(C0435b c0435b, C0343a c0343a, C0434a c0434a) {
        m704c(c0435b, c0343a, c0434a, false);
    }

    private static void m703b(C0435b c0435b, C0343a c0343a, C0434a c0434a, boolean z) {
        if (c0435b == C0435b.CBAnimationTypeNone) {
            if (c0434a != null) {
                c0434a.m696a(c0343a);
            }
        } else if (c0343a == null || c0343a.f233i == null) {
            CBLogging.m75a("AnimationManager", "Transition of impression canceled due to lack of container");
        } else {
            View f = c0343a.f233i.m740f();
            if (f == null) {
                C0367f.m440a().m449d(c0343a);
                CBLogging.m75a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            ViewTreeObserver viewTreeObserver = f.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new C04311(f, c0435b, c0343a, c0434a, z));
            }
        }
    }

    private static void m704c(C0435b c0435b, C0343a c0343a, C0434a c0434a, boolean z) {
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        if (c0343a == null || c0343a.f233i == null) {
            CBLogging.m75a("AnimationManager", "Transition of impression canceled due to lack of container");
            return;
        }
        View f = c0343a.f233i.m740f();
        if (f == null) {
            CBLogging.m75a("AnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        View view;
        long j;
        Animation alphaAnimation;
        if (c0343a.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO || c0343a.f230f == C0340c.INTERSTITIAL_VIDEO) {
            view = c0343a.f233i;
        } else {
            view = f;
        }
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f2 = (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - 0.4f) / 2.0f;
        if (c0343a.f225a == C0339b.WEB) {
            j = 1000;
        } else {
            j = 500;
        }
        float f3;
        float f4;
        Animation translateAnimation;
        switch (C04333.f610a[c0435b.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (z) {
                    alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                Animation animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(alphaAnimation);
                alphaAnimation = animationSet2;
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (z) {
                    alphaAnimation = new bn(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                } else {
                    alphaAnimation = new bn(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation(width * f2, 0.0f, (-height) * 0.4f, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, height);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                if (z) {
                    alphaAnimation = new bn(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                } else {
                    alphaAnimation = new bn(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f2, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f2);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                f3 = z ? height : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = height;
                }
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, f3, f4);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, z ? -height : 0.0f, z ? 0.0f : -height);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                f3 = z ? width : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = width;
                }
                translateAnimation = new TranslateAnimation(f3, f4, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                translateAnimation = new TranslateAnimation(z ? -width : 0.0f, z ? 0.0f : -width, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                if (!z) {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 1, 0.5f, 1, 0.5f);
                    alphaAnimation.setDuration(j);
                    alphaAnimation.setStartOffset(0);
                    alphaAnimation.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation);
                    alphaAnimation = animationSet;
                    break;
                }
                alphaAnimation = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setStartOffset(0);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.19999999f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.099999964f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * 0.8f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            default:
                alphaAnimation = animationSet;
                break;
        }
        if (c0435b != C0435b.CBAnimationTypeNone) {
            if (c0434a != null) {
                CBUtility.m96e().postDelayed(new C04322(c0434a, c0343a), j);
            }
            view.startAnimation(alphaAnimation);
        } else if (c0434a != null) {
            c0434a.m696a(c0343a);
        }
    }

    public static void m700a(boolean z, View view) {
        long j = 500;
        if (C0351c.m328F().booleanValue()) {
            j = 1000;
        }
        m701a(z, view, j);
    }

    public static void m701a(boolean z, View view, long j) {
        float f;
        float f2 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        view.clearAnimation();
        if (z) {
            view.setVisibility(0);
        }
        if (z) {
            f = 0.0f;
        } else {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        if (!z) {
            f2 = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(j);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }
}
