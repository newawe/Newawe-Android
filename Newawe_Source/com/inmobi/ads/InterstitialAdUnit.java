package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C0680a;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

/* renamed from: com.inmobi.ads.m */
class InterstitialAdUnit extends AdUnit {
    private static final String f4263a;
    private int f4264b;
    private boolean f4265c;
    private int f4266d;
    private long f4267e;

    static {
        f4263a = InterstitialAdUnit.class.getSimpleName();
    }

    public InterstitialAdUnit(Context context, long j, C0680a c0680a) {
        super(context, j, c0680a);
        this.f4264b = 0;
        this.f4265c = false;
        this.f4266d = -1;
        this.f4267e = 0;
    }

    protected void m5172o() {
        super.m4369o();
        if (this.f4265c) {
            m4367m().m1683o();
        }
    }

    public void m5171n() {
        if (this.f4267e != 0) {
            int f = m4365k().m4409f();
            if (SystemClock.elapsedRealtime() - this.f4267e < ((long) (f * DateUtils.MILLIS_IN_SECOND))) {
                m4337a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + f + " seconds"), false);
                Logger.m1440a(InternalLogLevel.ERROR, f4263a, "Ad cannot be refreshed before " + f + " seconds");
                return;
            }
        }
        if (m4360g() == AdState.STATE_RENDERED) {
            m4337a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.m1440a(InternalLogLevel.ERROR, f4263a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
            return;
        }
        this.f4267e = SystemClock.elapsedRealtime();
        super.m4368n();
    }

    protected void m5173r() {
        super.m4372r();
        this.f4266d = -1;
    }

    public void m5175v() {
        Map hashMap = new HashMap();
        String i = m4363i();
        if (i != null) {
            hashMap.put("impId", i);
        }
        TelemetryComponent.m4448a().m4470a("ads", "ShowInt", hashMap);
        if (m5177x()) {
            hashMap.put("type", m5161a());
            TelemetryComponent.m4448a().m4470a("ads", "AdRendered", hashMap);
            m4335a(AdState.STATE_RENDERED);
            m5176w();
            return;
        }
        Logger.m1440a(InternalLogLevel.ERROR, f4263a, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
        m4355d("ShowIntBeforeReady");
    }

    public void m5162a(int i, int i2) {
        try {
            m4358f().getResources().getAnimation(i);
            m4358f().getResources().getAnimation(i2);
            this.f4266d = i;
            m4367m().setFullScreenExitAnimation(i2);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.ERROR, f4263a, "The supplied resource id with show for animations is invalid", e);
        }
        m5175v();
    }

    void m5176w() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f4263a, ">>> Starting " + InMobiAdActivity.class.getSimpleName() + " to display interstitial ad ...");
        int a = InMobiAdActivity.m1582a(m4367m());
        Intent intent = new Intent(m4358f(), InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", a);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", HttpStatus.SC_PROCESSING);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
        m4358f().startActivity(intent);
        if ((m4358f() instanceof Activity) && this.f4266d != -1) {
            ((Activity) m4358f()).overridePendingTransition(this.f4266d, 0);
        }
    }

    public boolean m5177x() {
        return m4360g() == AdState.STATE_READY;
    }

    void m5178y() {
        this.f4265c = true;
        if (m4367m() != null) {
            m4367m().m1683o();
        }
    }

    protected String m5161a() {
        return SchemaSymbols.ATTVAL_INT;
    }

    protected String m5166c() {
        return null;
    }

    protected PlacementType m5168d() {
        return PlacementType.FULL_SCREEN;
    }

    public void m5163a(Ad ad) {
        super.m4338a(ad);
        if (m4360g() == AdState.STATE_AVAILABLE) {
            m4348b(m4362h());
        }
    }

    public void m5164a(RenderView renderView) {
        super.m4341a(renderView);
        if (m4360g() == AdState.STATE_LOADED) {
            m4373s();
            m4335a(AdState.STATE_READY);
            m4375u();
            m4366l().m1120a();
            m4370p();
        }
    }

    public void m5167c(RenderView renderView) {
        super.m4351c(renderView);
        if (m4360g() == AdState.STATE_AVAILABLE) {
            m4335a(AdState.STATE_LOADED);
        }
    }

    public void m5165b(RenderView renderView) {
        super.m4346b(renderView);
        if (m4360g() == AdState.STATE_LOADED) {
            m4373s();
            m4335a(AdState.STATE_FAILED);
            m4366l().m1121a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    public synchronized void m5169e(RenderView renderView) {
        super.m4357e(renderView);
        if (m4360g() == AdState.STATE_RENDERED) {
            this.f4264b++;
            if (this.f4264b == 1) {
                m4366l().m1123b();
            } else {
                m4335a(AdState.STATE_ACTIVE);
            }
        } else if (m4360g() == AdState.STATE_ACTIVE) {
            this.f4264b++;
        }
    }

    public synchronized void m5170f(RenderView renderView) {
        super.m4359f(renderView);
        if (m4360g() == AdState.STATE_ACTIVE) {
            this.f4264b--;
            if (this.f4264b == 1) {
                m4335a(AdState.STATE_RENDERED);
            }
        } else if (m4360g() == AdState.STATE_RENDERED) {
            this.f4264b--;
            m4335a(AdState.STATE_CREATED);
            Map hashMap = new HashMap();
            hashMap.put("impId", m4363i());
            TelemetryComponent.m4448a().m4470a("ads", "IntClosed", hashMap);
            m4366l().m1125c();
            m5173r();
        }
    }

    protected void m5174t() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f4263a, "Renderview timed out.");
        m4352c("RenderTimeOut");
        if (m4360g() == AdState.STATE_LOADED) {
            m4335a(AdState.STATE_FAILED);
            m4366l().m1121a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }
}
