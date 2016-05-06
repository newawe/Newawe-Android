package com.jirbo.adcolony;

import android.graphics.Bitmap;
import org.apache.commons.lang.StringUtils;

public final class AdColonyV4VCAd extends AdColonyAd {
    boolean f3985C;
    boolean f3986D;
    boolean f3987E;

    /* renamed from: com.jirbo.adcolony.AdColonyV4VCAd.1 */
    class C12441 extends C0775j {
        final /* synthetic */ AdColonyV4VCAd f3983a;

        C12441(AdColonyV4VCAd adColonyV4VCAd, C0762d c0762d) {
            this.f3983a = adColonyV4VCAd;
            super(c0762d);
        }

        void m4685a() {
            if (this.f3983a.h != null) {
                this.o.f2144d.m4732a(this.f3983a.h, this.f3983a);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyV4VCAd.2 */
    class C12452 extends C0775j {
        final /* synthetic */ AdColonyV4VCAd f3984a;

        C12452(AdColonyV4VCAd adColonyV4VCAd, C0762d c0762d) {
            this.f3984a = adColonyV4VCAd;
            super(c0762d);
        }

        void m4686a() {
            this.o.f2144d.m4732a(this.f3984a.h, this.f3984a);
        }
    }

    public AdColonyV4VCAd() {
        this.f3985C = false;
        this.f3986D = false;
        C0745a.f1967D = false;
        C0745a.m2156e();
        this.k = "v4vc";
        this.f3987E = false;
        this.l = "fullscreen";
        this.m = aa.m2177b();
    }

    public AdColonyV4VCAd(String zone_id) {
        this.f3985C = false;
        this.f3986D = false;
        C0745a.m2156e();
        this.h = zone_id;
        this.k = "v4vc";
        this.f3987E = false;
        this.l = "fullscreen";
        this.m = aa.m2177b();
    }

    public AdColonyV4VCAd withListener(AdColonyAdListener listener) {
        this.y = listener;
        return this;
    }

    public AdColonyV4VCAd withConfirmationDialog(boolean setting) {
        this.f3985C = setting;
        return this;
    }

    public AdColonyV4VCAd withResultsDialog(boolean setting) {
        this.f3986D = setting;
        C0745a.f1967D = this.f3986D;
        return this;
    }

    public AdColonyV4VCAd withConfirmationDialog() {
        return withConfirmationDialog(true);
    }

    public AdColonyV4VCAd withResultsDialog() {
        return withResultsDialog(true);
    }

    boolean m4690b() {
        return true;
    }

    boolean m4689a(boolean z) {
        return false;
    }

    public boolean isReady() {
        if (this.h == null) {
            this.h = C0745a.f2001l.m2270f();
            if (this.h == null) {
                return false;
            }
        }
        return C0745a.f2001l.m2271f(this.h);
    }

    public String getRewardName() {
        if (m2119c()) {
            return this.i.f2317n.f2327d;
        }
        return StringUtils.EMPTY;
    }

    public int getRewardAmount() {
        if (m2119c()) {
            return this.i.f2317n.f2326c;
        }
        return 0;
    }

    public int getViewsPerReward() {
        if (m2119c()) {
            return this.i.f2317n.f2329f;
        }
        return 0;
    }

    public int getRemainingViewsUntilReward() {
        if (m2119c()) {
            return this.i.f2317n.f2329f - this.i.f2322s;
        }
        return 0;
    }

    public void show() {
        if (this.f3987E) {
            C0777l.f2242d.m2353b((Object) "Show attempt on out of date ad object. Please instantiate a new ad object for each ad attempt.");
            return;
        }
        C0745a.am = 0;
        if (isReady()) {
            this.g = C0745a.am;
            if (C0745a.f1968E) {
                C12452 c12452 = new C12452(this, C0745a.f2001l);
                C0745a.f1968E = false;
                m2119c();
                C0745a.f1983T = this;
                C0745a.f2001l.m2248a(this);
                if (this.f3985C) {
                    m4688a("Confirmation");
                    return;
                }
                this.f3987E = true;
                m4691c(true);
                return;
            }
            return;
        }
        this.g = C0745a.am;
        C12441 c12441 = new C12441(this, C0745a.f2001l);
        this.f = 2;
        if (this.y != null) {
            this.y.onAdColonyAdAttemptFinished(this);
        }
        this.f3987E = true;
    }

    void m4691c(boolean z) {
        if (!z) {
            this.f = 1;
        } else if (C0745a.f2001l.m2261b(this)) {
            C0745a.f2004o = false;
            if (this.y != null) {
                this.y.onAdColonyAdStarted(this);
            }
            this.f = 4;
        } else {
            this.f = 3;
        }
        if (this.f != 4) {
            C0745a.f1968E = true;
            if (this.y != null) {
                this.y.onAdColonyAdAttemptFinished(this);
            }
        }
    }

    void m4687a() {
        if (this.f == 4 && this.f3986D) {
            m4688a("Result");
        }
        if (!(this.y == null || this.w)) {
            this.y.onAdColonyAdAttemptFinished(this);
        }
        if (!(C0745a.f1967D || AdColonyBrowser.f1872B)) {
            for (int i = 0; i < C0745a.an.size(); i++) {
                ((Bitmap) C0745a.an.get(i)).recycle();
            }
            C0745a.an.clear();
        }
        C0745a.f1984U = null;
        this.w = true;
        if (!this.f3986D) {
            C0745a.f1968E = true;
        }
        System.gc();
    }

    void m4688a(String str) {
        String rewardName = getRewardName();
        String str2 = StringUtils.EMPTY;
        rewardName = (StringUtils.EMPTY + getRewardAmount()) + " " + rewardName;
        if (str.equals("Confirmation")) {
            C0745a.f1982S = new ab(rewardName, this);
        } else {
            C0745a.f1982S = new ac(rewardName, this);
        }
    }
}
