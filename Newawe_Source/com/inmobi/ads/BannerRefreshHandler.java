package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.inmobi.ads.k */
final class BannerRefreshHandler extends Handler {
    private InMobiBanner f1113a;

    public BannerRefreshHandler(InMobiBanner inMobiBanner) {
        this.f1113a = inMobiBanner;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.f1113a.load(true);
            default:
        }
    }
}
