package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.Libraries.CBUtility;

public final class au extends bk {
    private RectF f3499b;
    private Paint f3500c;
    private Paint f3501d;
    private BitmapShader f3502e;
    private float f3503f;

    public au(Context context) {
        super(context);
        this.f3503f = 0.0f;
        m3897a(context);
    }

    private void m3897a(Context context) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f3499b = new RectF();
        this.f3500c = new Paint();
        this.f3500c.setStyle(Style.STROKE);
        this.f3500c.setStrokeWidth(Math.max(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, f * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.f3500c.setAntiAlias(true);
    }

    public void m3899a(int i) {
        this.f3500c.setColor(i);
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        float a = CBUtility.m84a((float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, getContext());
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (this.f3502e == null) {
                if (bitmap != null) {
                    this.f3502e = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
                    this.f3501d = new Paint();
                    this.f3501d.setAntiAlias(true);
                    this.f3501d.setShader(this.f3502e);
                } else {
                    postInvalidate();
                    return;
                }
            }
            float max = Math.max(((float) getWidth()) / ((float) bitmap.getWidth()), ((float) getHeight()) / ((float) bitmap.getHeight()));
            canvas.save();
            canvas.scale(max, max);
            this.f3499b.set(0.0f, 0.0f, ((float) getWidth()) / max, ((float) getHeight()) / max);
            this.f3499b.inset(a / (max * 2.0f), a / (max * 2.0f));
            canvas.drawRoundRect(this.f3499b, this.f3499b.width() * this.f3503f, this.f3499b.height() * this.f3503f, this.f3501d);
            canvas.restore();
        } else {
            super.onDraw(canvas);
        }
        this.f3499b.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f3499b.inset(a / 2.0f, a / 2.0f);
        canvas.drawRoundRect(this.f3499b, this.f3499b.width() * this.f3503f, this.f3499b.height() * this.f3503f, this.f3500c);
    }

    public void m3898a(float f) {
        this.f3503f = f;
        invalidate();
    }
}
