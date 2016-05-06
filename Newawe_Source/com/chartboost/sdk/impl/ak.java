package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import com.android.volley.DefaultRetryPolicy;

public class ak extends bo {
    private Paint f3474a;
    private Paint f3475b;
    private Path f3476c;
    private RectF f3477d;
    private RectF f3478e;
    private int f3479f;
    private float f3480g;
    private float f3481h;

    public ak(Context context) {
        super(context);
        this.f3479f = 0;
        m3875a(context);
    }

    private void m3875a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.f3480g = 4.5f * f;
        this.f3474a = new Paint();
        this.f3474a.setColor(-1);
        this.f3474a.setStyle(Style.STROKE);
        this.f3474a.setStrokeWidth(f * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.f3474a.setAntiAlias(true);
        this.f3475b = new Paint();
        this.f3475b.setColor(-855638017);
        this.f3475b.setStyle(Style.FILL);
        this.f3475b.setAntiAlias(true);
        this.f3476c = new Path();
        this.f3478e = new RectF();
        this.f3477d = new RectF();
    }

    protected void m3878a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f3477d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        int min = Math.min(1, Math.round(f * 0.5f));
        this.f3477d.inset((float) min, (float) min);
        this.f3476c.reset();
        this.f3476c.addRoundRect(this.f3477d, this.f3480g, this.f3480g, Direction.CW);
        canvas.save();
        canvas.clipPath(this.f3476c);
        canvas.drawColor(this.f3479f);
        this.f3478e.set(this.f3477d);
        this.f3478e.right = ((this.f3478e.right - this.f3478e.left) * this.f3481h) + this.f3478e.left;
        canvas.drawRect(this.f3478e, this.f3475b);
        canvas.restore();
        canvas.drawRoundRect(this.f3477d, this.f3480g, this.f3480g, this.f3474a);
    }

    public void m3877a(int i) {
        this.f3479f = i;
        invalidate();
    }

    public void m3880b(int i) {
        this.f3474a.setColor(i);
        invalidate();
    }

    public void m3881c(int i) {
        this.f3475b.setColor(i);
        invalidate();
    }

    public void m3876a(float f) {
        this.f3481h = f;
        if (getVisibility() != 8) {
            invalidate();
        }
    }

    public void m3879b(float f) {
        this.f3480g = f;
    }
}
