package com.startapp.android.publish.inappbrowser;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/* renamed from: com.startapp.android.publish.inappbrowser.a */
public class StartAppSDK extends ProgressBar {
    private static final Interpolator f3020a;
    private ValueAnimator f3021b;
    private boolean f3022c;

    /* renamed from: com.startapp.android.publish.inappbrowser.a.1 */
    class StartAppSDK implements AnimatorUpdateListener {
        Integer f3018a;
        final /* synthetic */ StartAppSDK f3019b;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3019b = startAppSDK;
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            this.f3018a = (Integer) animation.getAnimatedValue();
            super.setProgress(this.f3018a.intValue());
        }
    }

    static {
        f3020a = new AccelerateDecelerateInterpolator();
    }

    public StartAppSDK(Context context, AttributeSet attributeSet, int i) {
        boolean z = false;
        super(context, attributeSet, i);
        this.f3022c = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        this.f3022c = z;
    }

    public void setProgress(int progress) {
        if (this.f3022c) {
            if (this.f3021b != null) {
                this.f3021b.cancel();
                if (getProgress() >= progress) {
                    return;
                }
            }
            this.f3021b = ValueAnimator.ofInt(new int[]{getProgress(), progress});
            this.f3021b.setInterpolator(f3020a);
            this.f3021b.addUpdateListener(new StartAppSDK(this));
            this.f3021b.setIntValues(new int[]{getProgress(), progress});
            this.f3021b.start();
            return;
        }
        super.setProgress(progress);
    }

    protected ValueAnimator getAnimator() {
        return this.f3021b;
    }

    public void m3069a() {
        super.setProgress(0);
        if (this.f3021b != null) {
            this.f3021b.cancel();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f3021b != null) {
            this.f3021b.cancel();
        }
    }
}
