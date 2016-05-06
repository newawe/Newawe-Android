package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.widget.LinearLayout;
import com.chartboost.sdk.Libraries.C0323e.C0321a;

public abstract class ap extends LinearLayout {
    private Rect f447a;
    private Paint f448b;
    private Paint f449c;

    public abstract int m536a();

    public abstract void m537a(C0321a c0321a, int i);

    public ap(Context context) {
        super(context);
        this.f447a = new Rect();
        this.f448b = null;
        this.f449c = null;
    }

    protected void onDraw(Canvas canvas) {
        this.f447a.set(0, 0, canvas.getWidth(), canvas.getHeight());
        m534a(canvas, this.f447a);
        m535b(canvas, this.f447a);
    }

    private void m534a(Canvas canvas, Rect rect) {
        if (this.f449c == null) {
            this.f449c = new Paint();
            this.f449c.setStyle(Style.FILL);
            this.f449c.setColor(-1);
        }
        canvas.drawRect(rect, this.f449c);
    }

    private void m535b(Canvas canvas, Rect rect) {
        if (this.f448b == null) {
            this.f448b = new Paint();
            this.f448b.setStyle(Style.FILL);
            this.f448b.setAntiAlias(true);
        }
        this.f448b.setColor(-2631721);
        canvas.drawRect((float) rect.left, (float) (rect.bottom - 1), (float) rect.right, (float) rect.bottom, this.f448b);
    }
}
