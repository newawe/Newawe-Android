package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public abstract class bo extends View {
    private Bitmap f648a;
    private Canvas f649b;

    protected abstract void m732a(Canvas canvas);

    public bo(Context context) {
        super(context);
        this.f648a = null;
        this.f649b = null;
        m730a(context);
    }

    private void m730a(Context context) {
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (Exception e) {
        }
    }

    private boolean m731b(Canvas canvas) {
        try {
            return ((Boolean) Canvas.class.getMethod("isHardwareAccelerated", new Class[0]).invoke(canvas, new Object[0])).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    protected final void onDraw(Canvas canvas) {
        Canvas canvas2;
        boolean b = m731b(canvas);
        if (b) {
            if (!(this.f648a != null && this.f648a.getWidth() == canvas.getWidth() && this.f648a.getHeight() == canvas.getHeight())) {
                if (!(this.f648a == null || this.f648a.isRecycled())) {
                    this.f648a.recycle();
                }
                try {
                    this.f648a = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.ARGB_8888);
                    this.f649b = new Canvas(this.f648a);
                } catch (Throwable th) {
                    return;
                }
            }
            this.f648a.eraseColor(0);
            Canvas canvas3 = canvas;
            canvas = this.f649b;
            canvas2 = canvas3;
        } else {
            canvas2 = null;
        }
        m732a(canvas);
        if (b && canvas2 != null) {
            canvas2.drawBitmap(this.f648a, 0.0f, 0.0f, null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.f648a == null || this.f648a.isRecycled())) {
            this.f648a.recycle();
        }
        this.f648a = null;
    }
}
