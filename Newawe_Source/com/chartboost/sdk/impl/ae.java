package com.chartboost.sdk.impl;

import com.chartboost.sdk.C0349b;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0363e;
import com.chartboost.sdk.C0363e.C0362a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0340c;
import com.chartboost.sdk.Model.C0344b;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.bd.C0416a;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;

public class ae extends C0363e {
    private static final String f3436c;
    private static ae f3437d;

    /* renamed from: com.chartboost.sdk.impl.ae.1 */
    class C03741 implements Runnable {
        final /* synthetic */ C0321a f391a;
        final /* synthetic */ ae f392b;

        C03741(ae aeVar, C0321a c0321a) {
            this.f392b = aeVar;
            this.f391a = c0321a;
        }

        public void run() {
            C0349b.m309c();
            if (this.f391a.m134c() && this.f391a.m127a("ad_units").m134c()) {
                C0349b.m300a(this.f391a.m127a("ad_units"), false);
            } else {
                C0349b.m300a(null, false);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ae.2 */
    class C10322 implements C0362a {
        final /* synthetic */ ae f3435a;

        C10322(ae aeVar) {
            this.f3435a = aeVar;
        }

        public void m3814a(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didClickInterstitial(c0343a.f229e);
            }
        }

        public void m3816b(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCloseInterstitial(c0343a.f229e);
            }
        }

        public void m3817c(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDismissInterstitial(c0343a.f229e);
            }
        }

        public void m3818d(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCacheInterstitial(c0343a.f229e);
            }
        }

        public void m3815a(C0343a c0343a, CBImpressionError cBImpressionError) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToLoadInterstitial(c0343a.f229e, cBImpressionError);
            }
        }

        public void m3819e(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDisplayInterstitial(c0343a.f229e);
            }
        }

        public boolean m3820f(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m359g().shouldDisplayInterstitial(c0343a.f229e);
            }
            return true;
        }

        public boolean m3821g(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m359g().shouldRequestInterstitial(c0343a.f229e);
            }
            return true;
        }

        public boolean m3822h(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m375v();
            }
            return true;
        }
    }

    static {
        f3436c = ae.class.getSimpleName();
    }

    protected ae() {
    }

    public static ae m3823h() {
        if (f3437d == null) {
            f3437d = new ae();
        }
        return f3437d;
    }

    protected boolean m3826b(C0343a c0343a, C0321a c0321a) {
        return c0321a.m127a(OutputKeys.MEDIA_TYPE) != null && c0321a.m127a(OutputKeys.MEDIA_TYPE).equals("video");
    }

    protected void m3830h(C0343a c0343a) {
        super.m427h(c0343a);
    }

    protected void m3825a(C0343a c0343a, C0321a c0321a) {
        if (c0343a.f225a == C0339b.NATIVE) {
            if (m3826b(c0343a, c0321a) && !be.m670c(c0321a)) {
                CBLogging.m77b(f3436c, "Video Media unavailable for the cached impression");
                m406a(c0343a, CBImpressionError.VIDEO_UNAVAILABLE);
                return;
            }
        } else if (C0349b.m302a(c0321a)) {
            ax.m541a().execute(new C03741(this, c0321a));
        } else {
            CBLogging.m77b(f3436c, "WebView ad id for the html unavailable");
            m406a(c0343a, CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return;
        }
        super.m405a(c0343a, c0321a);
    }

    protected C0343a m3824a(String str, boolean z) {
        return new C0343a(C0338a.INTERSTITIAL, z, str, false, m423f());
    }

    protected az m3829f(C0343a c0343a) {
        az azVar;
        if (C0351c.m323A() == "/interstitial/get") {
            c0343a.f225a = C0339b.NATIVE;
        } else {
            c0343a.f225a = C0339b.WEB;
        }
        if (c0343a.f225a == C0339b.NATIVE) {
            azVar = new az(C0351c.m323A());
            azVar.m565a("local-videos", m3831i());
            azVar.m562a(C0466a.HIGH);
            azVar.m559a(C0344b.f255f);
            azVar.m565a("location", c0343a.f229e);
            if (c0343a.f231g) {
                azVar.m565a("cache", SchemaSymbols.ATTVAL_TRUE_1);
                azVar.m571b(true);
            }
        } else {
            C0321a a = C0349b.m296a(false);
            azVar = new bd(C0351c.m323A());
            azVar.m3959a("ad_units", a, C0416a.AD);
            azVar.m562a(C0466a.HIGH);
            azVar.m3959a("location", c0343a.f229e, C0416a.AD);
            if (c0343a.f231g) {
                azVar.m3959a("cache", Boolean.valueOf(true), C0416a.AD);
                azVar.m571b(true);
            } else {
                azVar.m3959a("cache", Boolean.valueOf(false), C0416a.AD);
            }
            azVar.m559a(C0344b.f255f);
        }
        return azVar;
    }

    protected void m3832j(C0343a c0343a) {
        if (c0343a.f230f != C0340c.INTERSTITIAL_VIDEO) {
            super.m429j(c0343a);
        }
    }

    protected C0362a m3827c() {
        return new C10322(this);
    }

    protected az m3833m(C0343a c0343a) {
        return new az("/interstitial/show");
    }

    public JSONArray m3831i() {
        JSONArray jSONArray = new JSONArray();
        String[] d = C0330h.m194d();
        if (d != null) {
            for (String str : d) {
                if (!str.contains("nomedia")) {
                    jSONArray.put(str);
                }
            }
        }
        return jSONArray;
    }

    public String m3828e() {
        return String.format("%s-%s", new Object[]{"interstitial", m425g()});
    }
}
