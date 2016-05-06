package com.chartboost.sdk;

import android.app.Activity;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0341d;
import com.chartboost.sdk.Model.C0343a.C0342e;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1020a;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.bb.C0410a;
import com.chartboost.sdk.impl.bq;
import com.chartboost.sdk.impl.bu;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.chartboost.sdk.d */
public final class C0356d {
    private static final String f333c;
    private static C0356d f334d;
    public C0341d f335a;
    public C0410a f336b;
    private bb f337e;

    /* renamed from: com.chartboost.sdk.d.4 */
    static /* synthetic */ class C03534 {
        static final /* synthetic */ int[] f332a;

        static {
            f332a = new int[C0342e.values().length];
            try {
                f332a[C0342e.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f332a[C0342e.CACHED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f332a[C0342e.LOADED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f332a[C0342e.DISPLAYED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.d.a */
    public interface C0354a {
        void m380a(boolean z);
    }

    /* renamed from: com.chartboost.sdk.d.b */
    public interface C0355b {
        void m381a();
    }

    /* renamed from: com.chartboost.sdk.d.1 */
    class C10251 implements C0354a {
        final /* synthetic */ C0343a f3417a;
        final /* synthetic */ String f3418b;
        final /* synthetic */ C0355b f3419c;
        final /* synthetic */ C0356d f3420d;

        /* renamed from: com.chartboost.sdk.d.1.1 */
        class C03521 implements Runnable {
            final /* synthetic */ boolean f330a;
            final /* synthetic */ C10251 f331b;

            C03521(C10251 c10251, boolean z) {
                this.f331b = c10251;
                this.f330a = z;
            }

            public void run() {
                if (this.f331b.f3417a != null) {
                    if (this.f331b.f3417a.m267b()) {
                        this.f331b.f3417a.m281p();
                    }
                    if (!this.f330a) {
                        this.f331b.f3417a.m289x();
                    }
                    if (this.f331b.f3417a.m265a() && this.f331b.f3417a.f227c == C0342e.DISPLAYED) {
                        C0367f h = Chartboost.m46h();
                        if (h != null) {
                            h.m446b(this.f331b.f3417a);
                        }
                    }
                }
                if (this.f330a) {
                    this.f331b.f3420d.m385a(this.f331b.f3417a, this.f331b.f3418b, this.f331b.f3419c);
                } else {
                    this.f331b.f3420d.f336b.m633a(this.f331b.f3417a, false, this.f331b.f3418b, CBClickError.AGE_GATE_FAILURE, this.f331b.f3419c);
                }
            }
        }

        C10251(C0356d c0356d, C0343a c0343a, String str, C0355b c0355b) {
            this.f3420d = c0356d;
            this.f3417a = c0343a;
            this.f3418b = str;
            this.f3419c = c0355b;
        }

        public void m3789a(boolean z) {
            Chartboost.m23a(new C03521(this, z));
        }
    }

    /* renamed from: com.chartboost.sdk.d.2 */
    class C10262 implements C0341d {
        final /* synthetic */ C0356d f3421a;

        C10262(C0356d c0356d) {
            this.f3421a = c0356d;
        }

        public void m3790a(C0343a c0343a) {
            synchronized (this.f3421a) {
                boolean z = c0343a.f231g;
            }
            if (c0343a.f227c == C0342e.LOADING) {
                c0343a.f227c = C0342e.LOADED;
                if (z) {
                    c0343a.m286u().m404a(c0343a);
                } else {
                    c0343a.m286u().m437r(c0343a);
                }
            }
            if (!z || c0343a.f227c == C0342e.DISPLAYED) {
                c0343a.m286u().m427h(c0343a);
            }
            c0343a.m286u().m435p(c0343a);
        }

        public void m3793b(C0343a c0343a) {
            if (c0343a.f227c == C0342e.DISPLAYED) {
                C0367f h = Chartboost.m46h();
                if (h != null) {
                    h.m446b(c0343a);
                }
            }
            C1020a.m3751c(c0343a.m286u().m420e(), c0343a.f229e, c0343a.m285t());
        }

        public void m3792a(C0343a c0343a, String str, C0321a c0321a) {
            c0343a.m286u().m410b().m391a(c0343a);
            if (!C0351c.m374u() && c0343a.m265a() && c0343a.f227c == C0342e.DISPLAYED) {
                C0367f h = Chartboost.m46h();
                if (h != null) {
                    h.m446b(c0343a);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                C0321a A = c0343a.m259A();
                az d = this.f3421a.m390d();
                d.m564a("ad_id", A);
                d.m564a("to", A);
                d.m564a("cgn", A);
                d.m564a("creative", A);
                d.m564a("cgn", c0321a);
                d.m564a("creative", c0321a);
                d.m564a("type", c0321a);
                d.m564a("more_type", c0321a);
                d.m565a("location", c0343a.f229e);
                if (c0343a.m270e()) {
                    d.m565a("retarget_reinstall", Boolean.valueOf(c0343a.m271f()));
                }
                c0343a.f239o = d;
                this.f3421a.m387b(c0343a, str, null);
            } else {
                this.f3421a.f336b.m633a(c0343a, false, str, CBClickError.URI_INVALID, null);
            }
            C1020a.m3748b(c0343a.m286u().m420e(), c0343a.f229e, c0343a.m285t());
        }

        public void m3791a(C0343a c0343a, CBImpressionError cBImpressionError) {
            C0363e u = c0343a.m286u();
            C1020a.m3738a(u.m420e(), c0343a.f229e, c0343a.m285t(), cBImpressionError);
            u.m406a(c0343a, cBImpressionError);
        }

        public void m3794c(C0343a c0343a) {
            c0343a.f237m = true;
            if (c0343a.f228d == C0338a.REWARDED_VIDEO && C0351c.m359g() != null) {
                C0351c.m359g().didCompleteRewardedVideo(c0343a.f229e, c0343a.m259A().m140f("reward"));
            }
            C0356d.m384b(c0343a);
        }

        public void m3795d(C0343a c0343a) {
            c0343a.f238n = true;
        }
    }

    /* renamed from: com.chartboost.sdk.d.3 */
    class C10273 implements C0410a {
        final /* synthetic */ C0356d f3422a;

        C10273(C0356d c0356d) {
            this.f3422a = c0356d;
        }

        public void m3796a(C0343a c0343a, boolean z, String str, CBClickError cBClickError, C0355b c0355b) {
            if (c0343a != null) {
                c0343a.f240p = false;
                if (c0343a.m265a()) {
                    c0343a.f227c = C0342e.DISMISSING;
                }
            }
            if (z) {
                if (c0343a != null && c0343a.f239o != null) {
                    c0343a.f239o.m567a(true);
                    c0343a.f239o.m590s();
                } else if (c0355b != null) {
                    c0355b.m381a();
                }
            } else if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToRecordClick(str, cBClickError);
            }
        }
    }

    static {
        f333c = C0356d.class.getSimpleName();
    }

    private C0356d() {
        this.f335a = new C10262(this);
        this.f336b = new C10273(this);
        this.f337e = bb.m634a(this.f336b);
    }

    public static C0356d m382a() {
        if (f334d == null) {
            f334d = new C0356d();
        }
        return f334d;
    }

    public final void m385a(C0343a c0343a, String str, C0355b c0355b) {
        this.f337e.m638a(c0343a, str, Chartboost.getHostActivity(), c0355b);
    }

    public final void m387b(C0343a c0343a, String str, C0355b c0355b) {
        C0351c.f304a = new C10251(this, c0343a, str, c0355b);
        if (!C0351c.m374u()) {
            m385a(c0343a, str, c0355b);
        } else if (C0351c.m359g() != null) {
            if (c0343a != null) {
                if (c0343a.m267b()) {
                    c0343a.m282q();
                }
                c0343a.m291z();
                c0343a.f240p = false;
            }
            if (c0343a == null) {
                C0351c.m359g().didPauseClickForConfirmation(Chartboost.getHostActivity());
            } else {
                C0351c.m359g().didPauseClickForConfirmation(Chartboost.m41f());
            }
        }
    }

    protected final boolean m388b() {
        C0343a c = m389c();
        if (c == null) {
            return false;
        }
        c.f242r = true;
        this.f335a.m256b(c);
        return true;
    }

    private static synchronized void m384b(C0343a c0343a) {
        synchronized (C0356d.class) {
            az azVar = new az("/api/video-complete");
            azVar.m565a("location", c0343a.f229e);
            azVar.m565a("reward", c0343a.m259A().m138e("reward"));
            azVar.m565a("currency-name", c0343a.m259A().m138e("currency-name"));
            azVar.m565a("ad_id", c0343a.m285t());
            azVar.m565a("force_close", Boolean.valueOf(false));
            C0372g c0372g = null;
            if (c0343a.f225a == C0339b.NATIVE && c0343a.m278m() != null) {
                c0372g = (ai) c0343a.m260B();
            } else if (c0343a.f225a == C0339b.WEB && c0343a.m278m() != null) {
                bu buVar = (bu) c0343a.m260B();
            }
            if (c0372g != null) {
                float k = c0372g.m485k();
                float j = c0372g.m484j();
                CBLogging.m75a(c0343a.m286u().getClass().getSimpleName(), String.format("TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                azVar.m565a("total_time", Float.valueOf(j / 1000.0f));
                if (k <= 0.0f) {
                    azVar.m565a("playback_time", Float.valueOf(j / 1000.0f));
                } else {
                    azVar.m565a("playback_time", Float.valueOf(k / 1000.0f));
                }
            }
            azVar.m567a(true);
            azVar.m590s();
        }
    }

    protected final C0343a m389c() {
        C0367f h = Chartboost.m46h();
        bq e = h == null ? null : h.m451e();
        if (e == null) {
            return null;
        }
        return e.m742h();
    }

    public az m390d() {
        az azVar = new az("/api/click");
        if (Chartboost.m41f() == null) {
            Chartboost.getValidContext();
        }
        return azVar;
    }

    public final boolean m386a(Activity activity, C0343a c0343a) {
        if (c0343a != null) {
            switch (C03534.f332a[c0343a.f227c.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (c0343a.f234j) {
                        Chartboost.m22a(c0343a);
                        break;
                    }
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    Chartboost.m22a(c0343a);
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    if (!c0343a.m274i()) {
                        if (C0351c.m343b() == null || !C0351c.m343b().doesWrapperUseCustomBackgroundingBehavior() || (activity instanceof CBImpressionActivity)) {
                            C0367f h = Chartboost.m46h();
                            if (h != null) {
                                CBLogging.m77b(f333c, "Error onActivityStart " + c0343a.f227c.name());
                                h.m449d(c0343a);
                                break;
                            }
                        }
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
