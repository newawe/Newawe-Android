package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.SessionInfo;
import com.inmobi.signals.activityrecognition.ActivityInfo;
import com.inmobi.signals.p006a.CellOperatorInfo;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.l */
public class IceSample {
    private static final String f1747a;
    private long f1748b;
    private CellOperatorInfo f1749c;
    private Map<String, Object> f1750d;
    private SessionInfo f1751e;
    private List<IceWifiSample> f1752f;
    private List<ActivityInfo> f1753g;

    static {
        f1747a = IceSample.class.getSimpleName();
    }

    public IceSample() {
        m1968a(Calendar.getInstance().getTimeInMillis());
    }

    public void m1968a(long j) {
        this.f1748b = j;
    }

    public void m1970a(CellOperatorInfo cellOperatorInfo) {
        this.f1749c = cellOperatorInfo;
    }

    public void m1972a(Map<String, Object> map) {
        this.f1750d = map;
    }

    public void m1969a(SessionInfo sessionInfo) {
        this.f1751e = sessionInfo;
    }

    public void m1971a(List<IceWifiSample> list) {
        this.f1752f = list;
    }

    public void m1973b(List<ActivityInfo> list) {
        this.f1753g = list;
    }

    public JSONObject m1967a() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.f1748b);
            if (this.f1749c != null) {
                if (this.f1749c.m1845a() != null) {
                    jSONObject.put("s-co", this.f1749c.m1845a());
                }
                if (this.f1749c.m1847b() != null) {
                    jSONObject.put("s-ho", this.f1749c.m1847b());
                }
            }
            if (!(this.f1750d == null || this.f1750d.isEmpty())) {
                jSONObject.put("l", new JSONObject(this.f1750d));
            }
            if (this.f1751e != null) {
                jSONObject.put("s", this.f1751e.m1530b());
            }
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.f1752f.size(); i2++) {
                jSONArray.put(((IceWifiSample) this.f1752f.get(i2)).m1978b());
            }
            if (jSONArray.length() > 0) {
                jSONObject.put("w", jSONArray);
            }
            if (this.f1753g != null) {
                JSONArray jSONArray2 = new JSONArray();
                while (i < this.f1753g.size()) {
                    jSONArray2.put(((ActivityInfo) this.f1753g.get(i)).m1881a());
                    i++;
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("ar", jSONArray2);
                }
            }
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1747a, "Error while converting IceSample to string.", e);
        }
        return jSONObject;
    }
}
