package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.widget.LinearLayout;
import com.android.volley.DefaultRetryPolicy;

public class an extends LinearLayout {
    private Paint f424a;
    private float f425b;
    private int f426c;

    public an(Context context) {
        super(context);
        this.f425b = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f426c = 0;
        int round = Math.round(context.getResources().getDisplayMetrics().density * 5.0f);
        setPadding(round, round, round, round);
        setBaselineAligned(false);
        this.f424a = new Paint();
        this.f424a.setStyle(Style.FILL);
    }

    public void m505a(int i) {
        this.f424a.setColor(i);
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = getContext().getResources().getDisplayMetrics().density;
        if ((this.f426c & 1) > 0) {
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), this.f425b * f, this.f424a);
        }
        if ((this.f426c & 2) > 0) {
            canvas.drawRect(((float) canvas.getWidth()) - (this.f425b * f), 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.f424a);
        }
        if ((this.f426c & 4) > 0) {
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - (this.f425b * f), (float) canvas.getWidth(), (float) canvas.getHeight(), this.f424a);
        }
        if ((this.f426c & 8) > 0) {
            canvas.drawRect(0.0f, 0.0f, this.f425b * f, (float) canvas.getHeight(), this.f424a);
        }
    }

    public void m506b(int i) {
        this.f426c = i;
    }
}
