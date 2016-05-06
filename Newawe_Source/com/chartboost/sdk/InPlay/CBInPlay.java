package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

public final class CBInPlay {
    private static final String f71a;
    private static C0313a f72f;
    private String f73b;
    private Bitmap f74c;
    private String f75d;
    private C0321a f76e;

    static {
        f71a = CBInPlay.class.getSimpleName();
        f72f = null;
    }

    CBInPlay() {
    }

    public void show() {
        C0313a.m60a().m69a(this);
    }

    public void click() {
        C0313a.m60a().m71b(this);
    }

    public String getLocation() {
        return this.f73b;
    }

    protected void m58a(String str) {
        this.f73b = str;
    }

    public Bitmap getAppIcon() {
        return this.f74c;
    }

    protected void m56a(Bitmap bitmap) {
        this.f74c = bitmap;
    }

    public String getAppName() {
        return this.f75d;
    }

    protected void m59b(String str) {
        this.f75d = str;
    }

    protected C0321a m55a() {
        return this.f76e;
    }

    protected void m57a(C0321a c0321a) {
        this.f76e = c0321a;
    }

    public static void cacheInPlay(String location) {
        if (!C0351c.m370q()) {
            return;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.m77b(f71a, "Inplay location cannot be empty");
            if (C0351c.m359g() != null) {
                C0351c.m359g().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        if (f72f == null) {
            f72f = C0313a.m60a();
        }
        f72f.m70a(location);
    }

    public static boolean hasInPlay(String location) {
        if (!C0351c.m370q()) {
            return false;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.m77b(f71a, "Inplay location cannot be empty");
            if (C0351c.m359g() == null) {
                return false;
            }
            C0351c.m359g().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
            return false;
        }
        if (f72f == null) {
            f72f = C0313a.m60a();
        }
        return f72f.m72b(location);
    }

    public static CBInPlay getInPlay(String location) {
        if (!C0351c.m370q()) {
            return null;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.m77b(f71a, "Inplay location cannot be empty");
            if (C0351c.m359g() == null) {
                return null;
            }
            C0351c.m359g().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
            return null;
        }
        if (f72f == null) {
            f72f = C0313a.m60a();
        }
        return f72f.m73c(location);
    }
}
