package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.android.volley.DefaultRetryPolicy;
import com.inmobi.ads.InMobiBanner.AnimationType;

/* renamed from: com.inmobi.ads.i */
class AnimationController {

    /* renamed from: com.inmobi.ads.i.a */
    static class AnimationController extends Animation {
        private final float f1099a;
        private final float f1100b;
        private final float f1101c;
        private final float f1102d;
        private final float f1103e;
        private final boolean f1104f;
        private Camera f1105g;

        public AnimationController(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.f1099a = f;
            this.f1100b = f2;
            this.f1101c = f3;
            this.f1102d = f4;
            this.f1103e = f5;
            this.f1104f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.f1105g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.f1099a;
            f2 += (this.f1100b - f2) * f;
            float f3 = this.f1101c;
            float f4 = this.f1102d;
            Camera camera = this.f1105g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f1104f) {
                camera.translate(0.0f, 0.0f, this.f1103e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.f1103e * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f));
            }
            camera.rotateX(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    /* renamed from: com.inmobi.ads.i.b */
    static class AnimationController extends Animation {
        private final float f1106a;
        private final float f1107b;
        private final float f1108c;
        private final float f1109d;
        private final float f1110e;
        private final boolean f1111f;
        private Camera f1112g;

        public AnimationController(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.f1106a = f;
            this.f1107b = f2;
            this.f1108c = f3;
            this.f1109d = f4;
            this.f1110e = f5;
            this.f1111f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.f1112g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.f1106a;
            f2 += (this.f1107b - f2) * f;
            float f3 = this.f1108c;
            float f4 = this.f1109d;
            Camera camera = this.f1112g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f1111f) {
                camera.translate(0.0f, 0.0f, this.f1110e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.f1110e * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f));
            }
            camera.rotateY(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    static Animation m1227a(AnimationType animationType, float f, float f2) {
        Animation alphaAnimation;
        if (animationType == AnimationType.ANIMATION_ALPHA) {
            alphaAnimation = new AlphaAnimation(0.0f, 0.5f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            return alphaAnimation;
        } else if (animationType == AnimationType.ROTATE_HORIZONTAL_AXIS) {
            alphaAnimation = new AnimationController(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        } else if (animationType != AnimationType.ROTATE_VERTICAL_AXIS) {
            return null;
        } else {
            alphaAnimation = new AnimationController(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        }
    }
}
