package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.chartboost.sdk.C0345a;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0356d;
import com.chartboost.sdk.C0356d.C0355b;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C0344b;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.C0472n.C0470a;
import com.chartboost.sdk.impl.C0472n.C0471b;
import com.chartboost.sdk.impl.C0475s;
import com.chartboost.sdk.impl.ab;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.az.C0399c;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bb;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* renamed from: com.chartboost.sdk.InPlay.a */
public final class C0313a {
    private static final String f77a;
    private static ArrayList<CBInPlay> f78b;
    private static int f79c;
    private static C0313a f80d;
    private static LinkedHashMap<String, Bitmap> f81e;
    private static volatile boolean f82f;

    /* renamed from: com.chartboost.sdk.InPlay.a.1 */
    class C10011 implements C0399c {
        final /* synthetic */ String f3371a;
        final /* synthetic */ boolean f3372b;
        final /* synthetic */ C0313a f3373c;

        C10011(C0313a c0313a, String str, boolean z) {
            this.f3373c = c0313a;
            this.f3371a = str;
            this.f3372b = z;
        }

        public void m3688a(C0321a c0321a, az azVar) {
            C0313a.f82f = false;
            if (c0321a.m134c()) {
                CBInPlay cBInPlay = new CBInPlay();
                cBInPlay.m57a(c0321a);
                cBInPlay.m59b(c0321a.m138e("name"));
                if (!TextUtils.isEmpty(this.f3371a)) {
                    cBInPlay.m58a(this.f3371a);
                }
                C0321a a = c0321a.m127a("icons");
                if (a.m134c() && !TextUtils.isEmpty(a.m138e("lg"))) {
                    String e = a.m138e("lg");
                    if (C0313a.f81e.get(e) == null) {
                        C0471b c1004b = new C1004b(null);
                        C0470a c1003a = new C1003a(null);
                        c1004b.f3380c = cBInPlay;
                        c1004b.f3379b = e;
                        c1004b.f3378a = this.f3372b;
                        ba.m610a(C0351c.m378y()).m623a().m815a(new ab(e, c1004b, 0, 0, null, c1003a));
                        return;
                    }
                    this.f3373c.m61a(cBInPlay, e, true);
                }
            }
        }

        public void m3689a(C0321a c0321a, az azVar, CBError cBError) {
            CBLogging.m77b(C0313a.f77a, "InPlay cache call failed" + cBError);
            C0313a.f82f = false;
            if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToLoadInPlay(this.f3371a, cBError != null ? cBError.m252c() : null);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.InPlay.a.2 */
    class C10022 implements C0355b {
        final /* synthetic */ CBInPlay f3374a;
        final /* synthetic */ C0321a f3375b;
        final /* synthetic */ C0313a f3376c;

        C10022(C0313a c0313a, CBInPlay cBInPlay, C0321a c0321a) {
            this.f3376c = c0313a;
            this.f3374a = cBInPlay;
            this.f3375b = c0321a;
        }

        public void m3690a() {
            az d = C0356d.m382a().m390d();
            d.m565a("location", this.f3374a.getLocation());
            d.m564a("to", this.f3375b);
            d.m564a("cgn", this.f3375b);
            d.m564a("creative", this.f3375b);
            d.m564a("ad_id", this.f3375b);
            d.m564a("type", this.f3375b);
            d.m564a("more_type", this.f3375b);
            d.m567a(true);
            d.m590s();
        }
    }

    /* renamed from: com.chartboost.sdk.InPlay.a.a */
    private class C1003a implements C0470a {
        final /* synthetic */ C0313a f3377a;

        private C1003a(C0313a c0313a) {
            this.f3377a = c0313a;
        }

        public void m3691a(C0475s c0475s) {
            CBLogging.m77b(C0313a.f77a, "Bitmap download failed " + c0475s.getMessage());
        }
    }

    /* renamed from: com.chartboost.sdk.InPlay.a.b */
    private class C1004b implements C0471b<Bitmap> {
        protected boolean f3378a;
        protected String f3379b;
        protected CBInPlay f3380c;
        final /* synthetic */ C0313a f3381d;

        private C1004b(C0313a c0313a) {
            this.f3381d = c0313a;
        }

        public void m3692a(Bitmap bitmap) {
            C0313a.f81e.put(this.f3379b, bitmap);
            this.f3381d.m61a(this.f3380c, this.f3379b, this.f3378a);
        }
    }

    static {
        f77a = C0313a.class.getSimpleName();
        f79c = 4;
        f82f = false;
    }

    private C0313a() {
        f78b = new ArrayList();
        f81e = new LinkedHashMap(f79c);
    }

    public static C0313a m60a() {
        if (f80d == null) {
            synchronized (C0313a.class) {
                if (f80d == null) {
                    f80d = new C0313a();
                }
            }
        }
        return f80d;
    }

    public synchronized void m70a(String str) {
        if (!(C0313a.m68e() || f82f)) {
            m63a(str, true);
        }
    }

    private static boolean m68e() {
        return f78b.size() == f79c;
    }

    public synchronized boolean m72b(String str) {
        boolean z;
        if (f78b.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized CBInPlay m73c(String str) {
        CBInPlay cBInPlay;
        cBInPlay = null;
        if (f78b.size() > 0) {
            cBInPlay = (CBInPlay) f78b.get(0);
            f78b.remove(0);
        }
        if (!(C0313a.m68e() || f82f)) {
            m63a(str, true);
        }
        if (cBInPlay == null) {
            CBLogging.m75a(f77a, "InPlay Object not available returning null :(");
        }
        return cBInPlay;
    }

    private void m63a(String str, boolean z) {
        f82f = true;
        az azVar = new az("/inplay/get");
        azVar.m565a("raw", Boolean.valueOf(true));
        azVar.m565a("cache", Boolean.valueOf(true));
        azVar.m562a(C0466a.HIGH);
        azVar.m571b(true);
        azVar.m565a("location", (Object) str);
        azVar.m559a(C0344b.f254e);
        azVar.m560a(new C10011(this, str, z));
    }

    private synchronized void m61a(CBInPlay cBInPlay, String str, boolean z) {
        cBInPlay.m56a((Bitmap) f81e.get(str));
        f78b.add(cBInPlay);
        C0345a g = C0351c.m359g();
        if (g != null && z) {
            g.didCacheInPlay(cBInPlay.getLocation());
        }
        if (!(C0313a.m68e() || f82f)) {
            m63a(cBInPlay.getLocation(), false);
        }
    }

    protected void m69a(CBInPlay cBInPlay) {
        Object a = cBInPlay.m55a();
        az azVar = new az("/inplay/show");
        azVar.m565a("inplay-dictionary", a);
        azVar.m565a("location", cBInPlay.getLocation());
        azVar.m567a(true);
        azVar.m590s();
        if (cBInPlay.m55a().m134c()) {
            a.m138e("ad_id");
        }
    }

    protected void m71b(CBInPlay cBInPlay) {
        String str;
        C0321a a = cBInPlay.m55a();
        if (a != null) {
            String e = a.m138e("link");
            String e2 = a.m138e("deep-link");
            if (!TextUtils.isEmpty(e2)) {
                try {
                    if (!bb.m637a(e2)) {
                        e2 = e;
                    }
                    str = e2;
                } catch (Exception e3) {
                    CBLogging.m77b(f77a, "Cannot open a url");
                }
            }
            str = e;
        } else {
            str = null;
        }
        C0355b c10022 = new C10022(this, cBInPlay, a);
        C0356d a2 = C0356d.m382a();
        if (TextUtils.isEmpty(str)) {
            a2.f336b.m633a(null, false, str, CBClickError.URI_INVALID, c10022);
        } else {
            a2.m387b(null, str, c10022);
        }
    }

    public static void m65b() {
        if (f81e != null) {
            f81e.clear();
        }
        if (f78b != null) {
            f78b.clear();
        }
    }
}
