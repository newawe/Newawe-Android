package com.chartboost.sdk.Model;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0363e;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.bq;
import com.chartboost.sdk.impl.bu;
import com.google.android.gms.common.ConnectionResult;
import java.util.Date;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.chartboost.sdk.Model.a */
public final class C0343a {
    public C0339b f225a;
    public Date f226b;
    public C0342e f227c;
    public C0338a f228d;
    public String f229e;
    public C0340c f230f;
    public boolean f231g;
    public boolean f232h;
    public bq f233i;
    public boolean f234j;
    public boolean f235k;
    public boolean f236l;
    public boolean f237m;
    public boolean f238n;
    public az f239o;
    public boolean f240p;
    public boolean f241q;
    public boolean f242r;
    public boolean f243s;
    private C0321a f244t;
    private boolean f245u;
    private Boolean f246v;
    private C0372g f247w;
    private C0341d f248x;
    private Runnable f249y;

    /* renamed from: com.chartboost.sdk.Model.a.1 */
    static /* synthetic */ class C03371 {
        static final /* synthetic */ int[] f204a;

        static {
            f204a = new int[C0338a.values().length];
            try {
                f204a[C0338a.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f204a[C0338a.REWARDED_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f204a[C0338a.MORE_APPS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f204a[C0338a.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Model.a.a */
    public enum C0338a {
        INTERSTITIAL,
        MORE_APPS,
        REWARDED_VIDEO,
        NONE
    }

    /* renamed from: com.chartboost.sdk.Model.a.b */
    public enum C0339b {
        NATIVE,
        WEB
    }

    /* renamed from: com.chartboost.sdk.Model.a.c */
    public enum C0340c {
        INTERSTITIAL,
        INTERSTITIAL_VIDEO,
        INTERSTITIAL_REWARD_VIDEO,
        NONE
    }

    /* renamed from: com.chartboost.sdk.Model.a.d */
    public interface C0341d {
        void m253a(C0343a c0343a);

        void m254a(C0343a c0343a, CBImpressionError cBImpressionError);

        void m255a(C0343a c0343a, String str, C0321a c0321a);

        void m256b(C0343a c0343a);

        void m257c(C0343a c0343a);

        void m258d(C0343a c0343a);
    }

    /* renamed from: com.chartboost.sdk.Model.a.e */
    public enum C0342e {
        LOADING,
        LOADED,
        DISPLAYED,
        CACHED,
        DISMISSING,
        NONE
    }

    public C0343a(C0338a c0338a, boolean z, String str, boolean z2, C0339b c0339b) {
        this.f225a = C0339b.NATIVE;
        this.f246v = null;
        this.f235k = false;
        this.f236l = false;
        this.f237m = false;
        this.f238n = false;
        this.f241q = false;
        this.f242r = false;
        this.f243s = false;
        this.f227c = C0342e.LOADING;
        this.f231g = z;
        this.f232h = false;
        this.f240p = false;
        this.f242r = true;
        this.f228d = c0338a;
        this.f234j = z2;
        this.f244t = C0321a.f99a;
        this.f230f = C0340c.NONE;
        this.f229e = str;
        this.f245u = true;
        this.f225a = c0339b;
        if (this.f229e == null) {
            this.f229e = CBLocation.LOCATION_DEFAULT;
        }
    }

    public void m262a(C0321a c0321a, C0341d c0341d) {
        boolean z;
        if (c0321a == null) {
            c0321a = C0321a.m121a();
        }
        this.f244t = c0321a;
        this.f226b = new Date();
        this.f227c = C0342e.LOADING;
        this.f248x = c0341d;
        Object e = this.f244t.m138e("type");
        if (TextUtils.isEmpty(e) || !e.equals("native")) {
            this.f225a = C0339b.WEB;
        } else {
            this.f225a = C0339b.NATIVE;
        }
        if (this.f225a == C0339b.NATIVE) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            switch (C03371.f204a[this.f228d.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (!c0321a.m127a(OutputKeys.MEDIA_TYPE).equals("video")) {
                        this.f230f = C0340c.INTERSTITIAL;
                        this.f247w = new ah(this);
                        break;
                    }
                    this.f230f = C0340c.INTERSTITIAL_VIDEO;
                    this.f247w = new ai(this);
                    this.f245u = false;
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    this.f230f = C0340c.INTERSTITIAL_REWARD_VIDEO;
                    this.f247w = new ai(this);
                    this.f245u = false;
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    this.f247w = new aw(this);
                    this.f245u = false;
                    break;
            }
        }
        switch (C03371.f204a[this.f228d.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.f230f = C0340c.INTERSTITIAL_VIDEO;
                this.f245u = false;
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.f230f = C0340c.INTERSTITIAL_REWARD_VIDEO;
                this.f245u = false;
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.f245u = false;
                break;
        }
        this.f247w = new bu(this);
        this.f247w.m472a(c0321a);
    }

    public boolean m265a() {
        return this.f245u;
    }

    public boolean m267b() {
        if (C0351c.m343b() == null || this.f245u) {
            return false;
        }
        return true;
    }

    public void m268c() {
        if (this.f248x != null) {
            this.f242r = true;
            this.f248x.m256b(this);
        }
    }

    public void m269d() {
        if (this.f248x != null) {
            this.f248x.m253a(this);
        }
    }

    public boolean m266a(String str, C0321a c0321a) {
        if (this.f227c != C0342e.DISPLAYED || this.f236l) {
            return false;
        }
        if (str == null) {
            str = this.f244t.m138e("link");
        }
        String e = this.f244t.m138e("deep-link");
        if (!TextUtils.isEmpty(e)) {
            try {
                if (bb.m637a(e)) {
                    try {
                        this.f246v = new Boolean(true);
                        str = e;
                    } catch (Exception e2) {
                        str = e;
                    }
                } else {
                    this.f246v = new Boolean(false);
                }
            } catch (Exception e3) {
            }
        }
        if (this.f240p) {
            return false;
        }
        this.f240p = true;
        this.f242r = false;
        this.f248x.m255a(this, str, c0321a);
        return true;
    }

    public boolean m270e() {
        return this.f246v != null;
    }

    public boolean m271f() {
        return this.f246v.booleanValue();
    }

    public void m263a(CBImpressionError cBImpressionError) {
        if (this.f248x != null) {
            this.f248x.m254a(this, cBImpressionError);
        }
    }

    public void m272g() {
        if (this.f248x != null) {
            this.f248x.m257c(this);
        }
    }

    public void m273h() {
        if (this.f248x != null) {
            this.f248x.m258d(this);
        }
    }

    public boolean m274i() {
        if (this.f247w != null) {
            this.f247w.m476b();
            if (this.f247w.m479e() != null) {
                return true;
            }
        }
        CBLogging.m77b("CBImpression", "reinitializing -- no view protocol exists!!");
        CBLogging.m83e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    public void m275j() {
        m276k();
        if (this.f232h) {
            if (this.f247w != null) {
                this.f247w.m478d();
            }
            this.f247w = null;
            CBLogging.m77b("CBImpression", "Destroying the view and view data");
        }
    }

    public void m276k() {
        if (this.f233i != null) {
            this.f233i.m738d();
            try {
                if (!(this.f247w == null || this.f247w.m479e() == null || this.f247w.m479e().getParent() == null)) {
                    this.f233i.removeView(this.f247w.m479e());
                }
            } catch (Throwable e) {
                CBLogging.m78b("CBImpression", "Exception raised while cleaning up views", e);
            }
            this.f233i = null;
        }
        if (this.f247w != null) {
            this.f247w.m480f();
        }
        CBLogging.m77b("CBImpression", "Destroying the view");
    }

    public CBImpressionError m277l() {
        if (this.f247w != null) {
            return this.f247w.m477c();
        }
        return CBImpressionError.ERROR_CREATING_VIEW;
    }

    public C0370a m278m() {
        if (this.f247w != null) {
            return this.f247w.m479e();
        }
        return null;
    }

    public void m279n() {
        if (this.f247w != null && this.f247w.m479e() != null) {
            this.f247w.m479e().setVisibility(0);
        }
    }

    public void m280o() {
        if (this.f247w != null && this.f247w.m479e() != null) {
            this.f247w.m479e().setVisibility(8);
        }
    }

    public void m281p() {
        if (this.f247w != null && this.f247w.m479e() != null) {
            ViewParent parent = this.f247w.m479e().getParent();
            if (parent == null || !(parent instanceof View)) {
                m280o();
            } else {
                ((View) parent).setVisibility(0);
            }
        }
    }

    public void m282q() {
        if (this.f247w != null && this.f247w.m479e() != null) {
            ViewParent parent = this.f247w.m479e().getParent();
            if (parent == null || !(parent instanceof View)) {
                m279n();
            } else {
                ((View) parent).setVisibility(8);
            }
        }
    }

    public void m264a(Runnable runnable) {
        this.f249y = runnable;
    }

    public void m283r() {
        this.f236l = true;
    }

    public void m284s() {
        if (this.f249y != null) {
            this.f249y.run();
            this.f249y = null;
        }
        this.f236l = false;
        this.f235k = false;
    }

    public String m285t() {
        return this.f244t.m138e("ad_id");
    }

    public C0363e m286u() {
        switch (C03371.f204a[this.f228d.ordinal()]) {
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return af.m5031j();
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return av.m3909h();
            default:
                return ae.m3823h();
        }
    }

    public void m287v() {
        m286u().m430k(this);
    }

    public boolean m288w() {
        if (this.f247w != null) {
            return this.f247w.m486l();
        }
        return false;
    }

    public void m289x() {
        this.f240p = false;
        if (this.f247w != null && this.f241q) {
            this.f241q = false;
            this.f247w.m487m();
        }
    }

    public void m290y() {
        this.f240p = false;
    }

    public void m291z() {
        if (this.f247w != null && !this.f241q) {
            this.f241q = true;
            this.f247w.m488n();
        }
    }

    public C0321a m259A() {
        return this.f244t == null ? C0321a.f99a : this.f244t;
    }

    public C0372g m260B() {
        return this.f247w;
    }

    public boolean m261C() {
        return this.f242r;
    }
}
