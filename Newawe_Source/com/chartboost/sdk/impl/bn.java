package com.chartboost.sdk.impl;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public final class bn extends Animation {
    private final float f642a;
    private final float f643b;
    private final float f644c;
    private final float f645d;
    private boolean f646e;
    private Camera f647f;

    public bn(float f, float f2, float f3, float f4, boolean z) {
        this.f646e = true;
        this.f642a = f;
        this.f643b = f2;
        this.f644c = f3;
        this.f645d = f4;
        this.f646e = z;
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.f647f = new Camera();
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float f = this.f642a + ((this.f643b - this.f642a) * interpolatedTime);
        Camera camera = this.f647f;
        Matrix matrix = t.getMatrix();
        camera.save();
        if (this.f646e) {
            camera.rotateY(f);
        } else {
            camera.rotateX(f);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-this.f644c, -this.f645d);
        matrix.postTranslate(this.f644c, this.f645d);
    }
}
