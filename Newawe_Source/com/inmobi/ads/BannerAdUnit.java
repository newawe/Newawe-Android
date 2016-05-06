package com.inmobi.ads;

import android.content.Context;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C0680a;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.inmobi.ads.j */
class BannerAdUnit extends AdUnit {
    private static final String f4258a;
    private InMobiBanner f4259b;
    private boolean f4260c;
    private boolean f4261d;
    private int f4262e;

    static {
        f4258a = BannerAdUnit.class.getSimpleName();
    }

    public BannerAdUnit(InMobiBanner inMobiBanner, Context context, long j, C0680a c0680a) {
        super(context, j, c0680a);
        this.f4260c = true;
        this.f4261d = false;
        this.f4262e = 0;
        this.f4259b = inMobiBanner;
    }

    void m5158v() {
        this.f4261d = true;
        if (m4367m() != null) {
            m4367m().m1683o();
        }
    }

    public void m5149a(boolean z) {
        this.f4260c = z;
        super.m4368n();
    }

    boolean m5159w() {
        return this.f4261d;
    }

    boolean m5160x() {
        return m4360g() == AdState.STATE_ACTIVE;
    }

    protected void m5157o() {
        super.m4369o();
        if (this.f4261d) {
            m4367m().m1683o();
        }
    }

    protected String m5147a() {
        return "banner";
    }

    protected String m5150c() {
        return this.f4259b.getFrameSizeString();
    }

    protected PlacementType m5152d() {
        return PlacementType.INLINE;
    }

    protected Map<String, String> m5154e() {
        Map hashMap = new HashMap();
        hashMap.put("u-rt", this.f4260c ? String.valueOf(1) : String.valueOf(0));
        hashMap.put("mk-ad-slot", this.f4259b.getFrameSizeString());
        return hashMap;
    }

    public void m5148a(Ad ad) {
        super.m4338a(ad);
        if (m4360g() == AdState.STATE_AVAILABLE) {
            m4348b(m4362h());
        }
    }

    public void m5151c(RenderView renderView) {
        super.m4351c(renderView);
        if (m4360g() == AdState.STATE_AVAILABLE) {
            m4373s();
            m4335a(AdState.STATE_LOADED);
            m4375u();
            m4366l().m1120a();
            m4370p();
        }
    }

    public void m5153d(RenderView renderView) {
        super.m4354d(renderView);
        if (m4360g() == AdState.STATE_LOADED) {
            m4335a(AdState.STATE_RENDERED);
            Map hashMap = new HashMap();
            hashMap.put("type", m5147a());
            hashMap.put("impId", m4363i());
            TelemetryComponent.m4448a().m4470a("ads", "AdRendered", hashMap);
        }
    }

    public synchronized void m5155e(RenderView renderView) {
        super.m4357e(renderView);
        if (m4360g() == AdState.STATE_RENDERED) {
            this.f4262e++;
            m4335a(AdState.STATE_ACTIVE);
            m4366l().m1123b();
        } else if (m4360g() == AdState.STATE_ACTIVE) {
            this.f4262e++;
        }
    }

    public synchronized void m5156f(RenderView renderView) {
        super.m4359f(renderView);
        if (m4360g() == AdState.STATE_ACTIVE) {
            this.f4262e--;
            if (this.f4262e == 0) {
                m4335a(AdState.STATE_RENDERED);
                m4366l().m1125c();
            }
        }
    }
}
