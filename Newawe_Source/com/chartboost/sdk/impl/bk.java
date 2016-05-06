package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1018j;

public class bk extends ImageView {
    protected TextView f622a;
    private C1018j f623b;

    public bk(Context context) {
        super(context);
        this.f623b = null;
        this.f622a = null;
    }

    public void m707a(C1018j c1018j) {
        if (this.f623b != c1018j) {
            this.f623b = c1018j;
            setImageDrawable(new BitmapDrawable(c1018j.m3727f()));
        }
    }

    public void setImageBitmap(Bitmap adImage) {
        this.f623b = null;
        setImageDrawable(new BitmapDrawable(adImage));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m706a(canvas);
    }

    protected void m706a(Canvas canvas) {
        if (this.f622a != null) {
            this.f622a.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.f622a.setEnabled(isEnabled());
            this.f622a.setSelected(isSelected());
            if (isFocused()) {
                this.f622a.requestFocus();
            } else {
                this.f622a.clearFocus();
            }
            this.f622a.setPressed(isPressed());
            this.f622a.draw(canvas);
        }
    }
}
