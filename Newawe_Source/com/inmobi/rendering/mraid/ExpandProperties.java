package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.mraid.d */
public class ExpandProperties {
    private static final String f1600c;
    public int f1601a;
    public int f1602b;
    private boolean f1603d;
    private boolean f1604e;
    private boolean f1605f;
    private String f1606g;

    static {
        f1600c = ExpandProperties.class.getSimpleName();
    }

    public ExpandProperties() {
        this.f1601a = DisplayInfo.m1481a().m1496b();
        this.f1602b = DisplayInfo.m1481a().m1495a();
        this.f1603d = false;
        this.f1605f = true;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.f1601a);
            jSONObject.put("height", this.f1602b);
            jSONObject.put("useCustomClose", this.f1603d);
            jSONObject.put("isModal", this.f1605f);
        } catch (JSONException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1600c, "Exception in composing ExpandProperties: " + e.getMessage());
        }
        this.f1606g = jSONObject.toString();
    }

    public boolean m1773a() {
        return this.f1603d;
    }

    public boolean m1774b() {
        return this.f1604e;
    }

    public static ExpandProperties m1772a(String str, ExpandProperties expandProperties, OrientationProperties orientationProperties) {
        ExpandProperties expandProperties2 = new ExpandProperties();
        expandProperties2.f1606g = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            expandProperties2.f1605f = true;
            if (jSONObject.has("useCustomClose")) {
                expandProperties2.f1604e = true;
            }
            expandProperties2.f1603d = jSONObject.optBoolean("useCustomClose", false);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1600c, "Invalid expand properties string passed.", e);
        }
        return expandProperties2;
    }

    public String m1775c() {
        return this.f1606g;
    }
}
