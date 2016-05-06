package com.inmobi.ads;

import android.os.SystemClock;
import com.inmobi.ads.AdNetworkClient.AdNetworkClient;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.inmobi.ads.g */
final class AdStore implements AdNetworkClient {
    private static final String f3798a;
    private AdStoreRequest f3799b;
    private AdStore f3800c;
    private AdDao f3801d;
    private boolean f3802e;
    private long f3803f;

    /* renamed from: com.inmobi.ads.g.a */
    public interface AdStore {
        void m1203a(InMobiAdRequestStatus inMobiAdRequestStatus);

        void m1204a(Ad ad);
    }

    static {
        f3798a = AdStore.class.getSimpleName();
    }

    public AdStore(AdStoreRequest adStoreRequest, AdStore adStore) {
        this.f3802e = false;
        this.f3803f = 0;
        this.f3799b = adStoreRequest;
        this.f3800c = adStore;
        this.f3801d = AdDao.m1191a();
    }

    public void m4435a() {
        this.f3802e = false;
        this.f3801d.m1193a(this.f3799b.m1223h(), this.f3799b.m1221f().m1144e());
        int a = this.f3801d.m1192a(this.f3799b.m1215c(), this.f3799b.m1226k());
        if (a == 0) {
            m4433a(this.f3799b);
        } else if (a <= this.f3799b.m1221f().m1143d()) {
            m4432a(this.f3799b.m1215c(), this.f3799b.m1226k());
            m4433a(this.f3799b);
        } else {
            m4432a(this.f3799b.m1215c(), this.f3799b.m1226k());
        }
    }

    private void m4432a(long j, String str) {
        this.f3802e = true;
        this.f3800c.m1204a(this.f3801d.m1195b(j, str));
    }

    private void m4433a(AdStoreRequest adStoreRequest) {
        AdNetworkRequest adNetworkRequest = new AdNetworkRequest(adStoreRequest.m1205a(), adStoreRequest.m1215c(), adStoreRequest.m1222g());
        adNetworkRequest.m4430d(adStoreRequest.m1217d());
        adNetworkRequest.m4423a(adStoreRequest.m1219e());
        adNetworkRequest.m4422a(adStoreRequest.m1223h());
        adNetworkRequest.m4425b(adStoreRequest.m1224i());
        adNetworkRequest.m4421a(adStoreRequest.m1221f().m1142c());
        adNetworkRequest.m4426b(adStoreRequest.m1225j());
        adNetworkRequest.m4425b(adStoreRequest.m1224i());
        adNetworkRequest.m4428c(adStoreRequest.m1226k());
        adNetworkRequest.m1402b(adStoreRequest.m1212b() * DateUtils.MILLIS_IN_SECOND);
        adNetworkRequest.m1404c(adStoreRequest.m1212b() * DateUtils.MILLIS_IN_SECOND);
        this.f3803f = SystemClock.elapsedRealtime();
        new AdNetworkClient(adNetworkRequest, this).m4417a();
    }

    public void m4436a(AdNetworkResponse adNetworkResponse) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        List c = m4434c(adNetworkResponse);
        if (c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3798a, "Could not parse ad response:" + adNetworkResponse.m1201c());
            if (!this.f3802e) {
                this.f3800c.m1203a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c.size() == 0) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f3798a, "Ad response received but no ad available:" + adNetworkResponse.m1201c());
            Map hashMap = new HashMap();
            hashMap.put("type", this.f3799b.m1223h());
            hashMap.put("loadLatency", Long.valueOf(elapsedRealtime - this.f3803f));
            TelemetryComponent.m4448a().m4470a("ads", "ServerNoFill", hashMap);
            if (!this.f3802e) {
                this.f3800c.m1203a(new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            Map hashMap2 = new HashMap();
            hashMap2.put("type", this.f3799b.m1223h());
            hashMap2.put("count", Integer.valueOf(c.size()));
            hashMap2.put("loadLatency", Long.valueOf(elapsedRealtime - this.f3803f));
            TelemetryComponent.m4448a().m4470a("ads", "ServerFill", hashMap2);
            if (!this.f3802e) {
                this.f3800c.m1204a((Ad) c.get(0));
                c.remove(0);
            }
            this.f3801d.m1194a(c, this.f3799b.m1221f().m1141b(), this.f3799b.m1223h());
        }
    }

    private List<Ad> m4434c(AdNetworkResponse adNetworkResponse) {
        List<Ad> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(adNetworkResponse.m1201c());
            String trim = jSONObject.getString("requestId").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray == null) {
                return arrayList;
            }
            int min = Math.min(adNetworkResponse.m1200b().m4429d(), jSONArray.length());
            for (int i = 0; i < min; i++) {
                arrayList.add(new Ad(adNetworkResponse, jSONArray.getJSONObject(i).toString(), trim + "_" + i));
            }
            return arrayList;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f3798a, "Error while parsing ad response.", e);
            Map hashMap = new HashMap();
            hashMap.put("type", this.f3799b.m1223h());
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e.getLocalizedMessage());
            TelemetryComponent.m4448a().m4470a("ads", "ServerError", hashMap);
            return null;
        }
    }

    public void m4437b(AdNetworkResponse adNetworkResponse) {
        if (!this.f3802e) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Map hashMap = new HashMap();
            hashMap.put("type", this.f3799b.m1223h());
            hashMap.put("errorCode", String.valueOf(adNetworkResponse.m1202d().m1395a().getValue()));
            hashMap.put("reason", adNetworkResponse.m1202d().m1396b());
            hashMap.put("loadLatency", Long.valueOf(elapsedRealtime - this.f3803f));
            TelemetryComponent.m4448a().m4470a("ads", "ServerError", hashMap);
            this.f3800c.m1203a(adNetworkResponse.m1199a());
        }
    }
}
