package com.chartboost.sdk.impl;

import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0363e;
import com.chartboost.sdk.C0363e.C0362a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0344b;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C0467l.C0466a;

public class av extends C0363e {
    private static final String f3505d;
    private static av f3506e;
    protected int f3507c;
    private C0343a f3508f;
    private boolean f3509g;
    private boolean f3510h;

    /* renamed from: com.chartboost.sdk.impl.av.1 */
    class C10461 implements C0362a {
        final /* synthetic */ av f3504a;

        C10461(av avVar) {
            this.f3504a = avVar;
        }

        public void m3900a(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didClickMoreApps(c0343a.f229e);
            }
        }

        public void m3902b(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCloseMoreApps(c0343a.f229e);
            }
        }

        public void m3903c(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDismissMoreApps(c0343a.f229e);
            }
        }

        public void m3904d(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCacheMoreApps(c0343a.f229e);
            }
        }

        public void m3901a(C0343a c0343a, CBImpressionError cBImpressionError) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToLoadMoreApps(c0343a.f229e, cBImpressionError);
            }
        }

        public void m3905e(C0343a c0343a) {
            this.f3504a.f3507c = 0;
            this.f3504a.m3919i();
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDisplayMoreApps(c0343a.f229e);
            }
        }

        public boolean m3906f(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m359g().shouldDisplayMoreApps(c0343a.f229e);
            }
            return true;
        }

        public boolean m3907g(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m359g().shouldRequestMoreApps(c0343a.f229e);
            }
            return true;
        }

        public boolean m3908h(C0343a c0343a) {
            return true;
        }
    }

    static {
        f3505d = av.class.getSimpleName();
    }

    private av() {
        this.f3508f = null;
    }

    public static av m3909h() {
        if (f3506e == null) {
            synchronized (av.class) {
                if (f3506e == null) {
                    f3506e = new av();
                }
            }
        }
        return f3506e;
    }

    public void m3913a(String str) {
        this.f3507c = 0;
        m3919i();
        super.m409a(str);
    }

    protected void m3912a(C0343a c0343a, C0321a c0321a) {
        if (!this.f3509g && this.f3510h) {
            this.f3510h = false;
            this.f3507c = c0321a.m127a("cells").m154o();
        }
        super.m405a(c0343a, c0321a);
    }

    protected C0343a m3910a(String str, boolean z) {
        return new C0343a(C0338a.MORE_APPS, z, str, false, m423f());
    }

    protected az m3918f(C0343a c0343a) {
        az azVar = new az("/more/get");
        azVar.m562a(C0466a.HIGH);
        azVar.m559a(C0344b.f253d);
        return azVar;
    }

    protected az m3920m(C0343a c0343a) {
        az azVar = new az("/more/show");
        if (c0343a.f229e != null) {
            azVar.m565a("location", c0343a.f229e);
        }
        if (c0343a.m259A().m135c("cells")) {
            azVar.m565a("cells", c0343a.m259A().m127a("cells"));
        }
        return azVar;
    }

    public void m3911a() {
        this.f3508f = null;
    }

    protected C0343a m3915d(String str) {
        return this.f3508f;
    }

    protected void m3917e(String str) {
        this.f3508f = null;
    }

    protected void m3921r(C0343a c0343a) {
        this.f3508f = c0343a;
    }

    protected C0362a m3914c() {
        return new C10461(this);
    }

    protected void m3919i() {
    }

    public String m3916e() {
        return "more-apps";
    }
}
