package com.inmobi.ads;

import android.content.Context;
import android.os.SystemClock;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.inmobi.ads.AdStore.AdStore;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.Config;
import com.inmobi.commons.core.configs.ConfigComponent.ConfigComponent;
import com.inmobi.commons.core.configs.PkConfig;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.inmobi.commons.core.utilities.uid.UidHelper;
import com.inmobi.commons.core.utilities.uid.UidMap;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderView.C0710b;
import com.inmobi.rendering.RenderingProperties;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.signals.SignalsComponent;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;

abstract class AdUnit implements AdStore, ConfigComponent, C0710b {
    private static final String f3756a;
    private AdState f3757b;
    private Context f3758c;
    private long f3759d;
    private String f3760e;
    private Map<String, String> f3761f;
    private AdConfig f3762g;
    private String f3763h;
    private String f3764i;
    private C0680a f3765j;
    private RenderView f3766k;
    private RenderTimeoutHandler f3767l;
    private long f3768m;
    private long f3769n;

    public enum AdState {
        STATE_CREATED,
        STATE_LOADING,
        STATE_AVAILABLE,
        STATE_FAILED,
        STATE_LOADED,
        STATE_READY,
        STATE_ATTACHED,
        STATE_RENDERED,
        STATE_ACTIVE
    }

    /* renamed from: com.inmobi.ads.AdUnit.a */
    interface C0680a {
        void m1120a();

        void m1121a(InMobiAdRequestStatus inMobiAdRequestStatus);

        void m1122a(Map<Object, Object> map);

        void m1123b();

        void m1124b(Map<Object, Object> map);

        void m1125c();

        void m1126d();
    }

    protected abstract String m4334a();

    protected abstract String m4350c();

    protected abstract PlacementType m4353d();

    static {
        f3756a = AdUnit.class.getSimpleName();
    }

    public void m4340a(Config config) {
        this.f3762g = (AdConfig) config;
        TelemetryComponent.m4448a().m4471a(this.f3762g.m4403a(), this.f3762g.m4416m());
    }

    public AdUnit(Context context, long j, C0680a c0680a) {
        this.f3769n = 0;
        this.f3758c = context;
        this.f3759d = j;
        this.f3765j = c0680a;
        m4331v();
        m4335a(AdState.STATE_CREATED);
    }

    protected String m4345b() {
        return "json";
    }

    protected Map<String, String> m4356e() {
        return null;
    }

    protected Context m4358f() {
        return this.f3758c;
    }

    public AdState m4360g() {
        return this.f3757b;
    }

    protected String m4362h() {
        return this.f3763h;
    }

    protected String m4363i() {
        return this.f3764i;
    }

    protected void m4364j() {
        this.f3763h = null;
    }

    protected void m4335a(AdState adState) {
        this.f3757b = adState;
    }

    protected final AdConfig m4365k() {
        return this.f3762g;
    }

    protected final C0680a m4366l() {
        return this.f3765j;
    }

    protected final RenderView m4367m() {
        return this.f3766k;
    }

    public boolean m4349b(Ad ad) {
        String str = "pubContent";
        try {
            JSONObject jSONObject = new JSONObject(ad.m1130b());
            this.f3764i = ad.m1131c();
            this.f3763h = new String(Base64.decode(jSONObject.getString("pubContent"), 0)).trim();
            if (this.f3763h == null || this.f3763h.trim().length() == 0) {
                return false;
            }
            this.f3763h = this.f3763h.replace("@__imm_aft@", String.valueOf(System.currentTimeMillis() - this.f3768m));
            return true;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3756a, "Exception while parsing received ad.", e);
            return false;
        } catch (Throwable e2) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3756a, "Invalid Base64 encoding in received ad.", e2);
            return false;
        }
    }

    public void m4338a(Ad ad) {
        if (m4360g() != AdState.STATE_LOADING) {
            return;
        }
        if (m4349b(ad)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad fetch successful");
            m4335a(AdState.STATE_AVAILABLE);
            return;
        }
        m4352c("ParsingFailed");
        m4337a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
    }

    public void m4336a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad fetch failed. Status:" + inMobiAdRequestStatus.getStatusCode());
        m4337a(inMobiAdRequestStatus, true);
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.INTERNAL_ERROR) {
            m4352c("InternalError");
        }
    }

    protected void m4337a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (m4360g() == AdState.STATE_LOADING && z) {
            m4335a(AdState.STATE_FAILED);
        }
        m4366l().m1121a(inMobiAdRequestStatus);
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
            m4352c("NoFill");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.SERVER_ERROR) {
            m4352c("ServerError");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NETWORK_UNREACHABLE) {
            m4352c("NetworkUnreachable");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.AD_ACTIVE) {
            m4352c("AdActive");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_PENDING) {
            m4352c("RequestPending");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_INVALID) {
            m4352c("RequestInvalid");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_TIMED_OUT) {
            m4352c("RequestTimedOut");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.EARLY_REFRESH_REQUEST) {
            m4352c("EarlyRefreshRequest");
        }
    }

    public void m4343a(String str) {
        this.f3760e = str;
    }

    public void m4344a(Map<String, String> map) {
        this.f3761f = map;
    }

    public void m4368n() {
        Map hashMap = new HashMap();
        hashMap.put("type", m4334a());
        TelemetryComponent.m4448a().m4470a("ads", "AdLoadRequested", hashMap);
        if (!NetworkUtils.m1479a()) {
            m4337a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
        } else if (this.f3757b == AdState.STATE_LOADING || this.f3757b == AdState.STATE_AVAILABLE) {
            m4337a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            Logger.m1440a(InternalLogLevel.ERROR, f3756a, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
        } else if (m4360g() == AdState.STATE_ACTIVE) {
            m4337a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.m1440a(InternalLogLevel.ERROR, f3756a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad");
        } else {
            m4372r();
            this.f3757b = AdState.STATE_LOADING;
            SignalsComponent.m4580a().m4589i();
            m4369o();
            m4371q();
            m4339a(m4332w());
        }
    }

    protected void m4369o() {
        this.f3766k = new RenderView(m4358f(), new RenderingProperties(m4353d()));
        this.f3766k.m1639a((C0710b) this, m4365k().m4413j(), m4365k().m4414k());
    }

    protected void m4348b(String str) {
        this.f3769n = SystemClock.elapsedRealtime();
        m4367m().m1640a(str);
        m4333x();
    }

    protected void m4370p() {
        this.f3766k.m1649b("inmobi.recordEvent(120,null);");
    }

    private void m4331v() {
        this.f3762g = new AdConfig();
        com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(new PkConfig(), null);
        com.inmobi.commons.core.configs.ConfigComponent.m1352a().m1364a(this.f3762g, (ConfigComponent) this);
        this.f3767l = new RenderTimeoutHandler(this);
        TelemetryComponent.m4448a().m4471a(this.f3762g.m4403a(), this.f3762g.m4416m());
    }

    void m4371q() {
        UidHelper.m1550a().m1563e();
    }

    private AdStoreRequest m4332w() {
        AdStoreRequest adStoreRequest = new AdStoreRequest();
        adStoreRequest.m1213b(this.f3760e);
        adStoreRequest.m1211a(this.f3761f);
        adStoreRequest.m1207a(this.f3759d);
        adStoreRequest.m1216c(m4334a());
        adStoreRequest.m1208a(m4365k().m4402a(m4334a()));
        adStoreRequest.m1214b(m4356e());
        adStoreRequest.m1218d(m4345b());
        adStoreRequest.m1210a(this.f3762g.m4408e());
        adStoreRequest.m1206a(this.f3762g.m4411h());
        adStoreRequest.m1220e(m4350c());
        adStoreRequest.m1209a(new UidMap(this.f3762g.m1350n().m1344a()));
        return adStoreRequest;
    }

    void m4339a(AdStoreRequest adStoreRequest) {
        this.f3768m = System.currentTimeMillis();
        new AdStore(adStoreRequest, this).m4435a();
    }

    protected void m4372r() {
        this.f3764i = null;
        View m = m4367m();
        if (m != null) {
            ViewParent parent = m.getParent();
            m.removeAllViews();
            if (parent != null) {
                ((ViewGroup) parent).removeView(m);
            }
            m.destroy();
        }
    }

    public void m4341a(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Render view signaled ad ready");
    }

    public void m4346b(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Render view signaled ad failed");
        m4352c("RenderFailed");
    }

    public void m4351c(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "RenderView completed loading ad content");
    }

    public void m4354d(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Renderview visible");
    }

    public void m4357e(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad displayed");
    }

    public void m4359f(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad dismissed");
    }

    public void m4342a(RenderView renderView, HashMap<Object, Object> hashMap) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad reward action completed. Params:" + (hashMap == null ? null : hashMap.toString()));
        m4366l().m1124b(hashMap);
    }

    public void m4347b(RenderView renderView, HashMap<Object, Object> hashMap) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Ad interaction. Params:" + (hashMap == null ? null : hashMap.toString()));
        m4366l().m1122a((Map) hashMap);
    }

    public void m4361g(RenderView renderView) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "User left application");
        m4366l().m1126d();
    }

    private void m4333x() {
        m4373s();
        this.f3767l.sendEmptyMessageDelayed(0, (long) (m4365k().m4413j().m1182i() * DateUtils.MILLIS_IN_SECOND));
    }

    protected void m4373s() {
        this.f3767l.removeMessages(0);
    }

    protected void m4374t() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f3756a, "Renderview timed out.");
        m4352c("RenderTimeOut");
        if (m4360g() == AdState.STATE_AVAILABLE) {
            m4335a(AdState.STATE_FAILED);
            m4366l().m1121a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    protected void m4375u() {
        Map hashMap = new HashMap();
        hashMap.put("type", m4334a());
        hashMap.put("renderLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f3769n));
        TelemetryComponent.m4448a().m4470a("ads", "AdLoadSuccessful", hashMap);
    }

    protected void m4352c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("impId", m4363i());
        hashMap.put("errorCode", str);
        hashMap.put("type", m4334a());
        if (str != null && (str.trim().equalsIgnoreCase("RenderFailed") || str.trim().equalsIgnoreCase("RenderTimeOut"))) {
            hashMap.put("renderLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f3769n));
        }
        TelemetryComponent.m4448a().m4470a("ads", "AdLoadFailed", hashMap);
    }

    protected void m4355d(String str) {
        Map hashMap = new HashMap();
        hashMap.put("impId", m4363i());
        hashMap.put("errorCode", str);
        hashMap.put("type", m4334a());
        TelemetryComponent.m4448a().m4470a("ads", "AdShowFailed", hashMap);
    }
}
