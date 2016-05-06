package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;

public class bh extends FrameLayout {
    private View f601a;
    private boolean f602b;

    /* renamed from: com.chartboost.sdk.impl.bh.a */
    public interface C0430a {
        void m681a();

        void m682a(int i);

        void m683a(int i, int i2);

        void m684a(OnCompletionListener onCompletionListener);

        void m685a(OnErrorListener onErrorListener);

        void m686a(OnPreparedListener onPreparedListener);

        void m687a(Uri uri);

        void m688b();

        int m689c();

        int m690d();

        boolean m691e();
    }

    public bh(Context context) {
        super(context);
        m694c();
    }

    private void m694c() {
        this.f602b = m693b();
        if (!Chartboost.getImpressionsUseActivities() && (getContext() instanceof Activity)) {
            this.f602b = m692a((Activity) getContext());
        }
        CBLogging.m83e("VideoInit", "Choosing " + (this.f602b ? "texture" : "surface") + " solution for video playback");
        if (this.f602b) {
            this.f601a = new bg(getContext());
        } else {
            this.f601a = new bf(getContext());
        }
        addView(this.f601a, new LayoutParams(-1, -1));
        if (!this.f602b) {
            ((SurfaceView) this.f601a).setZOrderMediaOverlay(true);
        }
    }

    public C0430a m695a() {
        return (C0430a) this.f601a;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m695a().m683a(w, h);
    }

    public static boolean m692a(Activity activity) {
        if (m693b()) {
            if (Chartboost.getImpressionsUseActivities()) {
                return true;
            }
            try {
                return activity.getWindow().getDecorView().isHardwareAccelerated();
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static boolean m693b() {
        return VERSION.SDK_INT >= 14;
    }
}
