package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.mraid.l */
public class OrientationProperties {
    private static String f1667c;
    public boolean f1668a;
    public String f1669b;

    static {
        f1667c = OrientationProperties.class.getSimpleName();
    }

    public OrientationProperties() {
        this.f1668a = true;
        this.f1669b = StringUtils.EMPTY;
    }

    public static OrientationProperties m1832a(String str, OrientationProperties orientationProperties) {
        OrientationProperties orientationProperties2 = new OrientationProperties();
        try {
            JSONObject jSONObject = new JSONObject(str);
            orientationProperties2.f1669b = jSONObject.optString("forceOrientation", orientationProperties.f1669b);
            orientationProperties2.f1668a = jSONObject.optBoolean("allowOrientationChange", orientationProperties.f1668a);
            return orientationProperties2;
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1667c, "Invalid orientation properties string passed.", e);
            return null;
        }
    }
}
