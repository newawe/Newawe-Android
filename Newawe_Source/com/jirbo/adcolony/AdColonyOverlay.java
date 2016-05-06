package com.jirbo.adcolony;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;

public class AdColonyOverlay extends ADCVideo {
    Rect f4278Z;
    int aa;

    /* renamed from: com.jirbo.adcolony.AdColonyOverlay.1 */
    class C07411 implements Runnable {
        final /* synthetic */ View f1958a;
        final /* synthetic */ AdColonyOverlay f1959b;

        C07411(AdColonyOverlay adColonyOverlay, View view) {
            this.f1959b = adColonyOverlay;
            this.f1958a = view;
        }

        public void run() {
            this.f1959b.P.removeView(this.f1958a);
        }
    }

    public AdColonyOverlay() {
        this.f4278Z = new Rect();
        this.aa = 0;
    }

    public void onConfigurationChanged(Configuration new_config) {
        super.onConfigurationChanged(new_config);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        this.t = defaultDisplay.getWidth();
        this.u = defaultDisplay.getHeight();
        C0745a.f1974K = true;
        View view = new View(this);
        view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (d && this.H.f2052Q) {
            this.Q.setLayoutParams(new LayoutParams(this.t, this.u - this.H.f2071m, 17));
            this.P.addView(view, new LayoutParams(this.t, this.u, 17));
            new Handler().postDelayed(new C07411(this, view), 1500);
        }
        this.H.m2186a();
    }
}
