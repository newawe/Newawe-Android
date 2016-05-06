package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0340c;
import com.chartboost.sdk.Model.C0343a.C0342e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.bi;
import com.chartboost.sdk.impl.bi.C0434a;
import com.chartboost.sdk.impl.bi.C0435b;
import com.chartboost.sdk.impl.bq;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.chartboost.sdk.f */
public final class C0367f {
    private static C0367f f363c;
    private bq f364a;
    private C0343a f365b;
    private int f366d;

    /* renamed from: com.chartboost.sdk.f.2 */
    class C03652 implements Runnable {
        final /* synthetic */ C0343a f359a;
        final /* synthetic */ Activity f360b;
        final /* synthetic */ C0367f f361c;

        /* renamed from: com.chartboost.sdk.f.2.1 */
        class C10301 implements C0434a {
            final /* synthetic */ C03652 f3426a;

            /* renamed from: com.chartboost.sdk.f.2.1.1 */
            class C03641 implements Runnable {
                final /* synthetic */ C0343a f357a;
                final /* synthetic */ C10301 f358b;

                C03641(C10301 c10301, C0343a c0343a) {
                    this.f358b = c10301;
                    this.f357a = c0343a;
                }

                public void run() {
                    this.f358b.f3426a.f361c.m449d(this.f357a);
                }
            }

            C10301(C03652 c03652) {
                this.f3426a = c03652;
            }

            public void m3800a(C0343a c0343a) {
                CBUtility.m96e().post(new C03641(this, c0343a));
                c0343a.m280o();
                if (c0343a.f225a == C0339b.WEB) {
                    CBUtility.m91b(this.f3426a.f360b);
                }
                if (this.f3426a.f361c.f366d != -1) {
                    this.f3426a.f360b.getWindow().getDecorView().setSystemUiVisibility(this.f3426a.f361c.f366d);
                    this.f3426a.f361c.f366d = -1;
                }
            }
        }

        C03652(C0367f c0367f, C0343a c0343a, Activity activity) {
            this.f361c = c0367f;
            this.f359a = c0343a;
            this.f360b = activity;
        }

        public void run() {
            this.f359a.f227c = C0342e.DISMISSING;
            C0435b c0435b = C0435b.CBAnimationTypePerspectiveRotate;
            if (this.f359a.f225a == C0339b.WEB) {
                c0435b = C0435b.CBAnimationTypeFade;
            }
            if (this.f359a.f228d == C0338a.MORE_APPS) {
                c0435b = C0435b.CBAnimationTypePerspectiveZoom;
            }
            C0435b a = C0435b.m697a(this.f359a.m259A().m140f("animation"));
            if (a != null) {
                c0435b = a;
            }
            if (C0351c.m362i()) {
                c0435b = C0435b.CBAnimationTypeNone;
            }
            bi.m702b(c0435b, this.f359a, new C10301(this));
        }
    }

    /* renamed from: com.chartboost.sdk.f.3 */
    static /* synthetic */ class C03663 {
        static final /* synthetic */ int[] f362a;

        static {
            f362a = new int[C0342e.values().length];
            try {
                f362a[C0342e.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.f.1 */
    class C10291 implements C0434a {
        final /* synthetic */ C0367f f3425a;

        C10291(C0367f c0367f) {
            this.f3425a = c0367f;
        }

        public void m3799a(C0343a c0343a) {
            c0343a.m284s();
        }
    }

    private C0367f() {
        this.f364a = null;
        this.f366d = -1;
    }

    public static C0367f m440a() {
        if (f363c == null) {
            f363c = new C0367f();
        }
        return f363c;
    }

    public void m443a(C0343a c0343a) {
        switch (C03663.f362a[c0343a.f227c.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (c0343a.f234j && C0351c.m376w()) {
                    m442f(c0343a);
                }
            default:
                m441e(c0343a);
        }
    }

    private void m441e(C0343a c0343a) {
        if (this.f364a == null || this.f364a.m742h() == c0343a) {
            Object obj = c0343a.f227c != C0342e.DISPLAYED ? 1 : null;
            c0343a.f227c = C0342e.DISPLAYED;
            Activity f = Chartboost.m41f();
            if (c0343a.f225a == C0339b.NATIVE) {
                CBImpressionError cBImpressionError = f == null ? CBImpressionError.NO_HOST_ACTIVITY : null;
                if (cBImpressionError == null) {
                    cBImpressionError = c0343a.m277l();
                }
                if (cBImpressionError != null) {
                    CBLogging.m77b("CBViewController", "Unable to create the view while trying th display the impression");
                    c0343a.m263a(cBImpressionError);
                    return;
                }
            }
            if (this.f364a == null) {
                this.f364a = new bq(f, c0343a);
                f.addContentView(this.f364a, new LayoutParams(-1, -1));
            }
            if (c0343a.f225a == C0339b.WEB) {
                CBUtility.m89a(f);
            }
            if (this.f366d == -1) {
                this.f366d = f.getWindow().getDecorView().getSystemUiVisibility();
                CBUtility.m94c(f);
            }
            this.f364a.m735a();
            CBLogging.m77b("CBViewController", "Displaying the impression");
            c0343a.f233i = this.f364a;
            if (obj != null) {
                if (c0343a.f225a == C0339b.NATIVE) {
                    this.f364a.m739e().m705a();
                }
                C0435b c0435b = C0435b.CBAnimationTypePerspectiveRotate;
                if (c0343a.f225a == C0339b.WEB) {
                    c0435b = C0435b.CBAnimationTypeFade;
                }
                if (c0343a.f228d == C0338a.MORE_APPS) {
                    c0435b = C0435b.CBAnimationTypePerspectiveZoom;
                }
                C0435b a = C0435b.m697a(c0343a.m259A().m140f("animation"));
                if (a != null) {
                    c0435b = a;
                }
                if (C0351c.m362i()) {
                    c0435b = C0435b.CBAnimationTypeNone;
                }
                c0343a.m283r();
                bi.m698a(c0435b, c0343a, new C10291(this));
                if (C0351c.m359g() != null && (c0343a.f230f == C0340c.INTERSTITIAL_VIDEO || c0343a.f230f == C0340c.INTERSTITIAL_REWARD_VIDEO)) {
                    C0351c.m359g().willDisplayVideo(c0343a.f229e);
                }
                if (c0343a.m286u().m410b() != null) {
                    c0343a.m286u().m410b().m396e(c0343a);
                    return;
                }
                return;
            }
            return;
        }
        CBLogging.m77b("CBViewController", "Impression already visible");
        c0343a.m263a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
    }

    public void m446b(C0343a c0343a) {
        CBLogging.m77b("CBViewController", "Dismissing impression");
        Runnable c03652 = new C03652(this, c0343a, Chartboost.m41f());
        if (c0343a.f236l) {
            c0343a.m264a(c03652);
        } else {
            c03652.run();
        }
    }

    private void m442f(C0343a c0343a) {
        Context f = Chartboost.m41f();
        if (f == null) {
            CBLogging.m81d(this, "No host activity to display loading view");
            return;
        }
        if (this.f364a == null) {
            this.f364a = new bq(f, c0343a);
            f.addContentView(this.f364a, new LayoutParams(-1, -1));
        }
        this.f364a.m736b();
        this.f365b = c0343a;
    }

    public void m444a(C0343a c0343a, boolean z) {
        if (c0343a == null) {
            return;
        }
        if (c0343a == this.f365b || c0343a == C0356d.m382a().m389c()) {
            this.f365b = null;
            CBLogging.m77b("CBViewController", "Dismissing loading view");
            if (m448c()) {
                this.f364a.m737c();
                if (z && this.f364a != null && this.f364a.m742h() != null) {
                    m449d(this.f364a.m742h());
                }
            }
        }
    }

    public void m447c(C0343a c0343a) {
        CBLogging.m77b("CBViewController", "Removing impression silently");
        if (m448c()) {
            m444a(c0343a, false);
        }
        c0343a.m276k();
        try {
            ((ViewGroup) this.f364a.getParent()).removeView(this.f364a);
        } catch (Throwable e) {
            CBLogging.m78b("CBViewController", "Exception removing impression silently", e);
        }
        this.f364a = null;
    }

    public void m449d(C0343a c0343a) {
        CBLogging.m77b("CBViewController", "Removing impression");
        c0343a.f227c = C0342e.NONE;
        if (this.f364a != null) {
            try {
                ((ViewGroup) this.f364a.getParent()).removeView(this.f364a);
            } catch (Throwable e) {
                CBLogging.m78b("CBViewController", "Exception removing impression ", e);
            }
            c0343a.m275j();
            this.f364a = null;
            if (C0351c.m361h()) {
                m445b();
            }
            c0343a.m286u().m410b().m394c(c0343a);
            if (c0343a.m261C()) {
                c0343a.m286u().m410b().m393b(c0343a);
            }
        } else if (C0351c.m361h()) {
            m445b();
        }
    }

    public void m445b() {
        CBLogging.m77b("CBViewController", " Closing impression activity");
        Activity f = Chartboost.m41f();
        if (f != null && (f instanceof CBImpressionActivity)) {
            CBLogging.m77b("CBViewController", " Closing impression activity #######");
            Chartboost.m44g();
            f.finish();
        }
    }

    public boolean m448c() {
        return this.f364a != null && this.f364a.m741g();
    }

    public boolean m450d() {
        return this.f364a != null;
    }

    public bq m451e() {
        return this.f364a;
    }
}
