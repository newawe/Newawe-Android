package com.chartboost.sdk.Libraries;

/* renamed from: com.chartboost.sdk.Libraries.f */
public enum C0324f {
    PORTRAIT,
    LANDSCAPE,
    PORTRAIT_REVERSE,
    LANDSCAPE_REVERSE;
    
    public static final C0324f f113e;
    public static final C0324f f114f;
    public static final C0324f f115g;
    public static final C0324f f116h;

    static {
        f113e = PORTRAIT_REVERSE;
        f114f = PORTRAIT;
        f115g = LANDSCAPE;
        f116h = LANDSCAPE_REVERSE;
    }

    public boolean m165a() {
        return this == PORTRAIT || this == PORTRAIT_REVERSE;
    }

    public boolean m166b() {
        return this == LANDSCAPE || this == LANDSCAPE_REVERSE;
    }
}
