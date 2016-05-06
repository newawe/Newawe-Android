package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.inmobi.ads.o */
final class RenderTimeoutHandler extends Handler {
    private AdUnit f1125a;

    public RenderTimeoutHandler(AdUnit adUnit) {
        this.f1125a = adUnit;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case DurationDV.DURATION_TYPE /*0*/:
                this.f1125a.m4367m().stopLoading();
                this.f1125a.m4374t();
            default:
        }
    }
}
