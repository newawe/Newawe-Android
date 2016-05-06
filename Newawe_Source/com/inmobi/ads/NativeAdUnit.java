package com.inmobi.ads;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C0680a;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.p005a.ClickManager;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;

/* renamed from: com.inmobi.ads.n */
class NativeAdUnit extends AdUnit {
    private static final String f4268a;
    private Map<NativeAdUnit, WeakReference<View>> f4269b;
    private WeakHashMap<View, NativeAdUnit> f4270c;
    private String f4271d;
    private String f4272e;
    private ImpressionTracker f4273f;
    private URL f4274g;
    private String f4275h;
    private int f4276i;
    private long f4277j;

    static {
        f4268a = NativeAdUnit.class.getSimpleName();
    }

    public NativeAdUnit(long j, C0680a c0680a) {
        super(SdkContext.m1258b(), j, c0680a);
        this.f4269b = new HashMap();
        this.f4270c = new WeakHashMap();
        this.f4276i = 0;
        this.f4277j = 0;
    }

    public void m5191n() {
        if (this.f4277j != 0) {
            int f = m4365k().m4409f();
            if (SystemClock.elapsedRealtime() - this.f4277j < ((long) (f * DateUtils.MILLIS_IN_SECOND))) {
                m4337a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + f + " seconds"), false);
                Logger.m1440a(InternalLogLevel.ERROR, f4268a, "Ad cannot be refreshed before " + f + " seconds");
                return;
            }
        }
        this.f4277j = SystemClock.elapsedRealtime();
        super.m4368n();
        this.f4273f = new ImpressionTracker(m4365k().m4415l());
    }

    public void m5193v() {
        if (AdState.STATE_ATTACHED == m4360g()) {
            WeakReference weakReference = (WeakReference) this.f4269b.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f4273f != null && view != null) {
                    this.f4273f.m1235a(view, this);
                }
            }
        }
    }

    public void m5194w() {
        if (AdState.STATE_ATTACHED == m4360g()) {
            WeakReference weakReference = (WeakReference) this.f4269b.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f4273f != null && view != null) {
                    this.f4273f.m1234a(view);
                }
            }
        }
    }

    public boolean m5185b(Ad ad) {
        if (!super.m4349b(ad)) {
            return false;
        }
        String str = "contextCode";
        str = "namespace";
        try {
            JSONObject jSONObject = new JSONObject(ad.m1130b());
            this.f4271d = jSONObject.getString("contextCode");
            this.f4272e = jSONObject.getString("namespace");
            if (this.f4271d == null || this.f4271d.trim().length() == 0 || this.f4272e == null || this.f4272e.trim().length() == 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f4268a, "Exception while parsing ad.", e);
            return false;
        }
    }

    public void m5183a(Ad ad) {
        super.m4338a(ad);
        if (AdState.STATE_AVAILABLE == m4360g()) {
            m4348b(this.f4271d);
        }
    }

    public Object m5195x() {
        return m4362h();
    }

    public void m5182a(View view, URL url, String str) {
        View view2;
        boolean z = true;
        Map hashMap = new HashMap();
        hashMap.put("customScript", Boolean.valueOf(str != null));
        String str2 = "customUrl";
        if (url == null) {
            z = false;
        }
        hashMap.put(str2, Boolean.valueOf(z));
        TelemetryComponent.m4448a().m4470a("ads", "TrackImpression", hashMap);
        WeakReference weakReference = (WeakReference) this.f4269b.get(this);
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        } else {
            view2 = null;
        }
        if (!view.equals(view2)) {
            if (AdState.STATE_LOADED == m4360g() || AdState.STATE_ATTACHED == m4360g()) {
                m5181a(view2);
                m5181a(view);
                this.f4269b.put(this, new WeakReference(view));
                this.f4270c.put(view, this);
                this.f4274g = url;
                this.f4275h = str;
                this.f4273f.m1235a(view, this);
                m4335a(AdState.STATE_ATTACHED);
            } else if (m4360g() != AdState.STATE_RENDERED && m4360g() != AdState.STATE_ACTIVE) {
                Logger.m1440a(InternalLogLevel.ERROR, f4268a, "Please wait for the ad to finish loading before making a call to bind.");
            }
        }
    }

    public void m5181a(View view) {
        if (view != null && AdState.STATE_ATTACHED == m4360g()) {
            m4335a(AdState.STATE_LOADED);
            InMobiNative.sMappedAdUnits.remove(view);
            this.f4273f.m1234a(view);
            this.f4269b.remove(this);
            NativeAdUnit nativeAdUnit = (NativeAdUnit) this.f4270c.remove(view);
            if (nativeAdUnit != null) {
                nativeAdUnit.m4335a(AdState.STATE_LOADED);
                this.f4269b.remove(nativeAdUnit);
            }
        }
    }

    void m5196y() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f4268a, "Impression record requested for Ad unit (" + hashCode() + ")");
        if (AdState.STATE_ATTACHED == m4360g()) {
            m4335a(AdState.STATE_RENDERED);
            m4367m().m1649b(this.f4272e + "recordEvent(18)");
            if (this.f4275h != null) {
                m4367m().m1649b(this.f4275h);
            }
            if (this.f4274g != null) {
                ClickManager.m4554a().m4565a(this.f4274g.toExternalForm(), true);
            }
        }
    }

    void m5184a(Map<String, String> map, URL url, String str) {
        boolean z = false;
        Logger.m1440a(InternalLogLevel.INTERNAL, f4268a, "Click record requested");
        Map hashMap = new HashMap();
        hashMap.put("customScript", Boolean.valueOf(str != null));
        String str2 = "customUrl";
        if (url != null) {
            z = true;
        }
        hashMap.put(str2, Boolean.valueOf(z));
        TelemetryComponent.m4448a().m4470a("ads", "ReportClick", hashMap);
        if (AdState.STATE_ATTACHED == m4360g() || AdState.STATE_RENDERED == m4360g()) {
            m4367m().m1649b(m5179b((Map) map));
            if (str != null) {
                m4367m().m1649b(str);
            }
            if (url != null) {
                ClickManager.m4554a().m4565a(url.toExternalForm(), true);
                return;
            }
            return;
        }
        TelemetryComponent.m4448a().m4468a(new TelemetryEvent("ads", "InvalidClickReport"));
        Logger.m1440a(InternalLogLevel.ERROR, f4268a, "reportAdClick call made in wrong state");
    }

    private String m5179b(Map<String, String> map) {
        NetworkUtils.m1478a((Map) map);
        if (map == null || map.isEmpty()) {
            return this.f4272e + "recordEvent(8)";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f4272e + "recordEvent(8, ");
        stringBuilder.append(new JSONObject(map).toString());
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    void m5197z() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f4268a, "Open landing page requested");
        TelemetryComponent.m4448a().m4468a(new TelemetryEvent("ads", "OpenLandingPage"));
        if (AdState.STATE_RENDERED == m4360g() || AdState.STATE_ATTACHED == m4360g()) {
            m4367m().m1649b(this.f4272e + "openLandingPage()");
        }
    }

    protected void m5192r() {
        m4364j();
        this.f4269b.clear();
        this.f4270c.clear();
        if (this.f4273f != null) {
            this.f4273f.m1236b();
        }
        this.f4275h = null;
        this.f4274g = null;
        if (!(m4367m() == null || m4367m().getParent() == null)) {
            ((ViewGroup) m4367m().getParent()).removeView(m4367m());
        }
        super.m4372r();
    }

    protected String m5180a() {
        return "native";
    }

    protected String m5186c() {
        return null;
    }

    protected PlacementType m5188d() {
        return PlacementType.INLINE;
    }

    public void m5187c(RenderView renderView) {
        super.m4351c(renderView);
        if (AdState.STATE_AVAILABLE == m4360g()) {
            m4373s();
            m4335a(AdState.STATE_LOADED);
            m4375u();
            m4366l().m1120a();
            m4370p();
        }
    }

    public void m5189e(RenderView renderView) {
        super.m4357e(renderView);
        if (AdState.STATE_RENDERED == m4360g() || AdState.STATE_ATTACHED == m4360g()) {
            this.f4276i++;
            m4335a(AdState.STATE_ACTIVE);
            m4366l().m1123b();
        } else if (m4360g() == AdState.STATE_ACTIVE) {
            this.f4276i++;
        }
    }

    public void m5190f(RenderView renderView) {
        super.m4359f(renderView);
        if (AdState.STATE_ACTIVE == m4360g()) {
            this.f4276i--;
            if (this.f4276i == 0) {
                m4335a(AdState.STATE_RENDERED);
                Map hashMap = new HashMap();
                hashMap.put("type", m5180a());
                hashMap.put("impId", m4363i());
                TelemetryComponent.m4448a().m4470a("ads", "AdRendered", hashMap);
                m4366l().m1125c();
            }
        }
    }
}
