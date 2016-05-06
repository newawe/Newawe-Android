package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Handler;
import com.chartboost.sdk.Libraries.CBUtility;

public final class br extends bo {
    private static Handler f3595a;
    private float f3596b;
    private long f3597c;
    private Paint f3598d;
    private Paint f3599e;
    private Path f3600f;
    private Path f3601g;
    private RectF f3602h;
    private RectF f3603i;
    private Runnable f3604j;

    /* renamed from: com.chartboost.sdk.impl.br.1 */
    class C04431 implements Runnable {
        final /* synthetic */ br f657a;

        C04431(br brVar) {
            this.f657a = brVar;
        }

        public void run() {
            float f = this.f657a.getContext().getResources().getDisplayMetrics().density;
            br.m4043a(this.f657a, (60.0f * f) * 0.016666668f);
            f = ((float) this.f657a.getHeight()) - (f * 9.0f);
            if (this.f657a.f3596b > f) {
                br.m4045b(this.f657a, f * 2.0f);
            }
            if (this.f657a.getWindowVisibility() == 0) {
                this.f657a.invalidate();
            }
        }
    }

    static /* synthetic */ float m4043a(br brVar, float f) {
        float f2 = brVar.f3596b + f;
        brVar.f3596b = f2;
        return f2;
    }

    static /* synthetic */ float m4045b(br brVar, float f) {
        float f2 = brVar.f3596b - f;
        brVar.f3596b = f2;
        return f2;
    }

    static {
        f3595a = CBUtility.m96e();
    }

    public br(Context context) {
        super(context);
        this.f3604j = new C04431(this);
        m4044a(context);
    }

    private void m4044a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.f3596b = 0.0f;
        this.f3597c = (long) (((double) System.nanoTime()) / 1000000.0d);
        this.f3598d = new Paint();
        this.f3598d.setColor(-1);
        this.f3598d.setStyle(Style.STROKE);
        this.f3598d.setStrokeWidth(f * 3.0f);
        this.f3598d.setAntiAlias(true);
        this.f3599e = new Paint();
        this.f3599e.setColor(-1);
        this.f3599e.setStyle(Style.FILL);
        this.f3599e.setAntiAlias(true);
        this.f3600f = new Path();
        this.f3601g = new Path();
        this.f3603i = new RectF();
        this.f3602h = new RectF();
    }

    protected void m4046a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.f3602h.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f3602h.inset(1.5f * f, 1.5f * f);
        float height = ((float) getHeight()) / 2.0f;
        canvas.drawRoundRect(this.f3602h, height, height, this.f3598d);
        this.f3603i.set(this.f3602h);
        this.f3603i.inset(3.0f * f, f * 3.0f);
        f = this.f3603i.height() / 2.0f;
        this.f3600f.reset();
        this.f3600f.addRoundRect(this.f3603i, f, f, Direction.CW);
        height = this.f3603i.height();
        this.f3601g.reset();
        this.f3601g.moveTo(0.0f, height);
        this.f3601g.lineTo(height, height);
        this.f3601g.lineTo(height * 2.0f, 0.0f);
        this.f3601g.lineTo(height, 0.0f);
        this.f3601g.close();
        canvas.save();
        Object obj = 1;
        try {
            canvas.clipPath(this.f3600f);
        } catch (UnsupportedOperationException e) {
            obj = null;
        }
        if (obj != null) {
            for (f = (-height) + this.f3596b; f < this.f3603i.width() + height; f += 2.0f * height) {
                float f2 = this.f3603i.left + f;
                canvas.save();
                canvas.translate(f2, this.f3603i.top);
                canvas.drawPath(this.f3601g, this.f3599e);
                canvas.restore();
            }
        }
        canvas.restore();
        long max = Math.max(0, 16 - (((long) (((double) System.nanoTime()) / 1000000.0d)) - this.f3597c));
        f3595a.removeCallbacks(this.f3604j);
        f3595a.postDelayed(this.f3604j, max);
    }

    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        f3595a.removeCallbacks(this.f3604j);
        if (visibility == 0) {
            f3595a.post(this.f3604j);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        f3595a.removeCallbacks(this.f3604j);
        f3595a.post(this.f3604j);
    }

    protected void onDetachedFromWindow() {
        f3595a.removeCallbacks(this.f3604j);
        super.onDetachedFromWindow();
    }
}
