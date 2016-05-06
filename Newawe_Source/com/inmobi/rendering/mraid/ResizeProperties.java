package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.mraid.m */
public class ResizeProperties {
    private static final String f1670g;
    String f1671a;
    int f1672b;
    int f1673c;
    int f1674d;
    int f1675e;
    boolean f1676f;

    static {
        f1670g = ResizeProperties.class.getSimpleName();
    }

    public ResizeProperties() {
        this.f1674d = 0;
        this.f1675e = 0;
        this.f1671a = "top-right";
        this.f1676f = true;
    }

    public static ResizeProperties m1833a(String str, ResizeProperties resizeProperties) {
        ResizeProperties resizeProperties2 = new ResizeProperties();
        try {
            JSONObject jSONObject = new JSONObject(str);
            resizeProperties2.f1672b = jSONObject.getInt("width");
            resizeProperties2.f1673c = jSONObject.getInt("height");
            resizeProperties2.f1674d = jSONObject.getInt("offsetX");
            resizeProperties2.f1675e = jSONObject.getInt("offsetY");
            if (resizeProperties == null) {
                return resizeProperties2;
            }
            resizeProperties2.f1671a = jSONObject.optString("customClosePosition", resizeProperties.f1671a);
            resizeProperties2.f1676f = jSONObject.optBoolean("allowOffscreen", resizeProperties.f1676f);
            return resizeProperties2;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1670g, "Invalid resize properties string passed.", e);
            return null;
        }
    }

    public String m1834a() {
        String str = StringUtils.EMPTY;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.f1672b);
            jSONObject.put("height", this.f1673c);
            jSONObject.put("customClosePosition", this.f1671a);
            jSONObject.put("offsetX", this.f1674d);
            jSONObject.put("offsetY", this.f1675e);
            jSONObject.put("allowOffscreen", this.f1676f);
            str = jSONObject.toString();
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1670g, "Invalid resize properties string passed.", e);
        }
        return str;
    }
}
