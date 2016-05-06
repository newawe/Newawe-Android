package com.chartboost.sdk.impl;

import com.Newawe.storage.DatabaseOpenHelper;
import com.chartboost.sdk.C0349b;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0363e.C0362a;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0344b;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.bd.C0416a;
import com.chartboost.sdk.impl.bm.C0441a;
import com.chartboost.sdk.impl.bm.C0442b;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.Method;

public class af extends ae {
    private static af f4196c;
    private static String f4197d;

    /* renamed from: com.chartboost.sdk.impl.af.1 */
    class C03751 implements Runnable {
        final /* synthetic */ C0321a f393a;
        final /* synthetic */ C0343a f394b;
        final /* synthetic */ af f395c;

        /* renamed from: com.chartboost.sdk.impl.af.1.1 */
        class C10331 extends C0442b {
            final /* synthetic */ C03751 f3438a;

            C10331(C03751 c03751) {
                this.f3438a = c03751;
            }

            public void m3834a(bm bmVar) {
                this.f3438a.f395c.m406a(this.f3438a.f394b, CBImpressionError.USER_CANCELLATION);
            }

            public void m3835a(bm bmVar, int i) {
                if (i == 1) {
                    super.m428i(this.f3438a.f394b);
                } else {
                    this.f3438a.f395c.m406a(this.f3438a.f394b, CBImpressionError.USER_CANCELLATION);
                }
            }
        }

        C03751(af afVar, C0321a c0321a, C0343a c0343a) {
            this.f395c = afVar;
            this.f393a = c0321a;
            this.f394b = c0343a;
        }

        public void run() {
            C0441a c0441a = new C0441a();
            c0441a.m720a(this.f393a.m138e(DatabaseOpenHelper.HISTORY_ROW_TITLE)).m722b(this.f393a.m138e(Method.TEXT)).m724d(this.f393a.m138e("confirm")).m723c(this.f393a.m138e("cancel"));
            c0441a.m721a(this.f395c.m417d(), new C10331(this));
        }
    }

    /* renamed from: com.chartboost.sdk.impl.af.2 */
    class C03762 implements Runnable {
        final /* synthetic */ C0321a f396a;
        final /* synthetic */ af f397b;

        /* renamed from: com.chartboost.sdk.impl.af.2.1 */
        class C10341 extends C0442b {
            final /* synthetic */ C03762 f3439a;

            C10341(C03762 c03762) {
                this.f3439a = c03762;
            }

            public void m3836a(bm bmVar, int i) {
                CBLogging.m79c(af.f4197d, "post-popup dismissed");
            }
        }

        C03762(af afVar, C0321a c0321a) {
            this.f397b = afVar;
            this.f396a = c0321a;
        }

        public void run() {
            C0441a c0441a = new C0441a();
            c0441a.m720a(this.f396a.m138e(DatabaseOpenHelper.HISTORY_ROW_TITLE)).m722b(this.f396a.m138e(Method.TEXT)).m723c(this.f396a.m138e("confirm"));
            c0441a.m721a(this.f397b.m417d(), new C10341(this));
        }
    }

    /* renamed from: com.chartboost.sdk.impl.af.3 */
    class C10353 implements C0362a {
        final /* synthetic */ af f3440a;

        C10353(af afVar) {
            this.f3440a = afVar;
        }

        public void m3837a(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didClickRewardedVideo(c0343a.f229e);
            }
        }

        public void m3839b(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCloseRewardedVideo(c0343a.f229e);
            }
        }

        public void m3840c(C0343a c0343a) {
            this.f3440a.m5041s(c0343a);
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDismissRewardedVideo(c0343a.f229e);
            }
        }

        public void m3841d(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didCacheRewardedVideo(c0343a.f229e);
            }
        }

        public void m3838a(C0343a c0343a, CBImpressionError cBImpressionError) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToLoadRewardedVideo(c0343a.f229e, cBImpressionError);
            }
        }

        public void m3842e(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                C0351c.m359g().didDisplayRewardedVideo(c0343a.f229e);
            }
        }

        public boolean m3843f(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m359g().shouldDisplayRewardedVideo(c0343a.f229e);
            }
            return true;
        }

        public boolean m3844g(C0343a c0343a) {
            return true;
        }

        public boolean m3845h(C0343a c0343a) {
            if (C0351c.m359g() != null) {
                return C0351c.m375v();
            }
            return true;
        }
    }

    static {
        f4197d = "CBRewardedVideo";
    }

    private af() {
    }

    public static af m5031j() {
        if (f4196c == null) {
            f4196c = new af();
        }
        return f4196c;
    }

    protected boolean m5034b(C0343a c0343a, C0321a c0321a) {
        return true;
    }

    protected C0343a m5033a(String str, boolean z) {
        return new C0343a(C0338a.REWARDED_VIDEO, z, str, false, m423f());
    }

    protected az m5037f(C0343a c0343a) {
        az azVar = null;
        if (C0351c.m324B() == "/reward/get") {
            c0343a.f225a = C0339b.NATIVE;
        } else {
            c0343a.f225a = C0339b.WEB;
        }
        if (c0343a.f225a == C0339b.NATIVE) {
            Object i = m3831i();
            if (i == null || i.length() == 0) {
                CBLogging.m77b(f4197d, "Local video list is empty so cannot make any video request as adserver will not return anything");
                m406a(c0343a, CBImpressionError.EMPTY_LOCAL_VIDEO_LIST);
                be.m668b();
            } else {
                azVar = new az(C0351c.m324B());
                azVar.m565a("local-videos", i);
                azVar.m562a(C0466a.HIGH);
                azVar.m565a("location", c0343a.f229e);
                if (c0343a.f231g) {
                    azVar.m565a("cache", SchemaSymbols.ATTVAL_TRUE_1);
                    azVar.m571b(true);
                }
                azVar.m559a(C0344b.f251b);
            }
        } else {
            C0321a a = C0349b.m296a(true);
            if (a.m154o() == 0 && (C0349b.m312f() || Chartboost.isFirstHardBootup)) {
                CBLogging.m77b(f4197d, "Asset Download is in progress, so wait and retry request until its complete");
                C0349b.f277a.add(c0343a);
            } else {
                azVar = new bd(C0351c.m324B());
                azVar.m3959a("ad_units", a, C0416a.AD);
                azVar.m562a(C0466a.HIGH);
                azVar.m559a(C0344b.f255f);
                azVar.m3959a("location", c0343a.f229e, C0416a.AD);
                if (c0343a.f231g) {
                    azVar.m3959a("cache", Boolean.valueOf(true), C0416a.AD);
                    azVar.m571b(true);
                } else {
                    azVar.m3959a("cache", Boolean.valueOf(false), C0416a.AD);
                }
            }
        }
        return azVar;
    }

    public az m5040m(C0343a c0343a) {
        az m = super.m3833m(c0343a);
        m.m563a("/reward/show");
        return m;
    }

    protected void m5039j(C0343a c0343a) {
    }

    protected void m5038i(C0343a c0343a) {
        C0321a a = c0343a.m259A().m127a("ux").m127a("pre-popup");
        if (a.m134c() && a.m127a(DatabaseOpenHelper.HISTORY_ROW_TITLE).m137d() && a.m127a(Method.TEXT).m137d() && a.m127a("confirm").m137d() && a.m127a("cancel").m137d() && m417d() != null) {
            a.post(new C03751(this, a, c0343a));
        } else {
            super.m428i(c0343a);
        }
    }

    protected void m5041s(C0343a c0343a) {
        C0321a a = c0343a.m259A().m127a("ux").m127a("post-popup");
        if (a.m134c() && a.m127a(DatabaseOpenHelper.HISTORY_ROW_TITLE).m137d() && a.m127a(Method.TEXT).m137d() && a.m127a("confirm").m137d() && m417d() != null && c0343a.f237m) {
            a.post(new C03762(this, a));
        }
    }

    public C0362a m5035c() {
        return new C10353(this);
    }

    public String m5036e() {
        return String.format("%s-%s", new Object[]{"rewarded-video", m425g()});
    }
}
