package com.google.android.youtube.player.internal;

import android.content.Context;
import android.content.res.Resources;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.google.android.youtube.player.internal.m */
public final class C0662m {
    public final String f809a;
    public final String f810b;
    public final String f811c;
    public final String f812d;
    public final String f813e;
    public final String f814f;
    public final String f815g;
    public final String f816h;
    public final String f817i;
    public final String f818j;

    public C0662m(Context context) {
        Resources resources = context.getResources();
        Locale locale = (resources == null || resources.getConfiguration() == null || resources.getConfiguration().locale == null) ? Locale.getDefault() : resources.getConfiguration().locale;
        Map a = C0677x.m968a(locale);
        this.f809a = (String) a.get("error_initializing_player");
        this.f810b = (String) a.get("get_youtube_app_title");
        this.f811c = (String) a.get("get_youtube_app_text");
        this.f812d = (String) a.get("get_youtube_app_action");
        this.f813e = (String) a.get("enable_youtube_app_title");
        this.f814f = (String) a.get("enable_youtube_app_text");
        this.f815g = (String) a.get("enable_youtube_app_action");
        this.f816h = (String) a.get("update_youtube_app_title");
        this.f817i = (String) a.get("update_youtube_app_text");
        this.f818j = (String) a.get("update_youtube_app_action");
    }
}
