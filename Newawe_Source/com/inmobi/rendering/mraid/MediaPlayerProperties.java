package com.inmobi.rendering.mraid;

import java.util.Locale;

/* renamed from: com.inmobi.rendering.mraid.f */
public class MediaPlayerProperties {
    public String f1608a;
    public String f1609b;
    public String f1610c;
    public boolean f1611d;
    public boolean f1612e;
    public boolean f1613f;
    public boolean f1614g;

    public MediaPlayerProperties() {
        this.f1608a = "anonymous";
        this.f1609b = "fullscreen";
        this.f1610c = "exit";
        this.f1611d = true;
        this.f1612e = true;
        this.f1613f = false;
        this.f1614g = false;
    }

    public boolean m1779a() {
        return "fullscreen".equals(this.f1609b.toLowerCase(Locale.ENGLISH));
    }

    public boolean m1780b() {
        return "exit".equals(this.f1610c.toLowerCase(Locale.ENGLISH));
    }
}
