package com.startapp.android.publish.banner;

import com.android.volley.DefaultRetryPolicy;
import java.io.Serializable;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class BannerOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private int adsNumber;
    private int delayFaceTime;
    private Effect effect;
    private int height;
    private float heightRatio;
    private int htmlAdsNumber;
    private float minScale;
    private int probability3D;
    private int refreshRate;
    private int refreshRate3D;
    private boolean rotateThroughOnStart;
    private int rotateThroughSpeedMult;
    private int scalePower;
    private int stepSize;
    private int timeBetweenFrames;
    private int width;
    private float widthRatio;

    /* compiled from: StartAppSDK */
    public enum Effect {
        ROTATE_3D(1),
        EXCHANGE(2),
        FLY_IN(3);
        
        private int index;

        private Effect(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public int getRotationMultiplier() {
            return (int) Math.pow(2.0d, (double) (this.index - 1));
        }

        public static Effect getByIndex(int index) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    effect = values[i];
                }
            }
            return effect;
        }

        public static Effect getByName(String name) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    effect = values[i];
                }
            }
            return effect;
        }
    }

    public BannerOptions() {
        this.width = HttpStatus.SC_MULTIPLE_CHOICES;
        this.height = 50;
        this.probability3D = 90;
        this.timeBetweenFrames = 25;
        this.stepSize = 5;
        this.delayFaceTime = 5000;
        this.adsNumber = 4;
        this.htmlAdsNumber = 10;
        this.refreshRate3D = DateUtils.MILLIS_IN_MINUTE;
        this.widthRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.heightRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.minScale = 0.88f;
        this.scalePower = 4;
        this.effect = Effect.ROTATE_3D;
        this.rotateThroughOnStart = true;
        this.rotateThroughSpeedMult = 2;
        this.refreshRate = DateUtils.MILLIS_IN_MINUTE;
    }

    public BannerOptions(BannerOptions other) {
        this.width = HttpStatus.SC_MULTIPLE_CHOICES;
        this.height = 50;
        this.probability3D = 90;
        this.timeBetweenFrames = 25;
        this.stepSize = 5;
        this.delayFaceTime = 5000;
        this.adsNumber = 4;
        this.htmlAdsNumber = 10;
        this.refreshRate3D = DateUtils.MILLIS_IN_MINUTE;
        this.widthRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.heightRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.minScale = 0.88f;
        this.scalePower = 4;
        this.effect = Effect.ROTATE_3D;
        this.rotateThroughOnStart = true;
        this.rotateThroughSpeedMult = 2;
        this.refreshRate = DateUtils.MILLIS_IN_MINUTE;
        this.width = other.width;
        this.height = other.height;
        this.probability3D = other.probability3D;
        this.timeBetweenFrames = other.timeBetweenFrames;
        this.stepSize = other.stepSize;
        this.delayFaceTime = other.delayFaceTime;
        this.adsNumber = other.adsNumber;
        this.htmlAdsNumber = other.htmlAdsNumber;
        this.refreshRate3D = other.refreshRate3D;
        this.widthRatio = other.widthRatio;
        this.heightRatio = other.heightRatio;
        this.minScale = other.minScale;
        this.scalePower = other.scalePower;
        this.effect = other.effect;
        this.rotateThroughOnStart = other.rotateThroughOnStart;
        this.rotateThroughSpeedMult = other.rotateThroughSpeedMult;
        this.refreshRate = other.refreshRate;
    }

    public void m2628a(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int m2627a() {
        return this.timeBetweenFrames;
    }

    public int m2629b() {
        return this.stepSize;
    }

    public int m2630c() {
        return this.delayFaceTime;
    }

    public int m2631d() {
        return this.width;
    }

    public int m2632e() {
        return this.height;
    }

    public int m2633f() {
        return this.adsNumber;
    }

    public int m2634g() {
        return this.htmlAdsNumber;
    }

    public int m2635h() {
        return this.refreshRate3D;
    }

    public int m2636i() {
        return this.refreshRate;
    }

    public int m2637j() {
        return this.probability3D;
    }

    public float m2638k() {
        return this.widthRatio;
    }

    public float m2639l() {
        return this.heightRatio;
    }

    public float m2640m() {
        return this.minScale;
    }

    public int m2641n() {
        return this.scalePower;
    }

    public Effect m2642o() {
        return this.effect;
    }

    public boolean m2643p() {
        return this.rotateThroughOnStart;
    }

    public int m2644q() {
        return this.rotateThroughSpeedMult;
    }

    public boolean equals(Object option) {
        BannerOptions bannerOptions = (BannerOptions) option;
        if (bannerOptions.m2633f() == m2633f() && bannerOptions.m2634g() == m2634g() && bannerOptions.m2635h() == m2635h() && bannerOptions.m2630c() == m2630c() && bannerOptions.m2632e() == m2632e() && bannerOptions.m2629b() == m2629b() && bannerOptions.m2627a() == m2627a() && bannerOptions.m2631d() == m2631d() && bannerOptions.m2636i() == m2636i() && bannerOptions.m2637j() == m2637j()) {
            return true;
        }
        return false;
    }
}
