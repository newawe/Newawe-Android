package com.startapp.android.publish.list3d;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.AutoScrollHelper;
import android.view.animation.AnimationUtils;

/* compiled from: StartAppSDK */
public abstract class Dynamics implements Parcelable {
    protected float f3023a;
    protected float f3024b;
    protected float f3025c;
    protected float f3026d;
    protected long f3027e;

    protected abstract void m3074a(int i);

    public Dynamics() {
        this.f3025c = AutoScrollHelper.NO_MAX;
        this.f3026d = -3.4028235E38f;
        this.f3027e = 0;
    }

    public Dynamics(Parcel in) {
        this.f3025c = AutoScrollHelper.NO_MAX;
        this.f3026d = -3.4028235E38f;
        this.f3027e = 0;
        this.f3023a = in.readFloat();
        this.f3024b = in.readFloat();
        this.f3025c = in.readFloat();
        this.f3026d = in.readFloat();
        this.f3027e = AnimationUtils.currentAnimationTimeMillis();
    }

    public void m3073a(float f, float f2, long j) {
        this.f3024b = f2;
        this.f3023a = f;
        this.f3027e = j;
    }

    public float m3070a() {
        return this.f3023a;
    }

    public float m3077b() {
        return this.f3024b;
    }

    public boolean m3076a(float f, float f2) {
        boolean z;
        if (Math.abs(this.f3024b) < f) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (this.f3023a - f2 >= this.f3025c || this.f3023a + f2 <= this.f3026d) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z && r3) {
            return true;
        }
        return false;
    }

    public void m3072a(float f) {
        this.f3025c = f;
    }

    public void m3078b(float f) {
        this.f3026d = f;
    }

    public void m3075a(long j) {
        int i = 50;
        if (this.f3027e != 0) {
            int i2 = (int) (j - this.f3027e);
            if (i2 <= 50) {
                i = i2;
            }
            m3074a(i);
        }
        this.f3027e = j;
    }

    protected float m3079c() {
        if (this.f3023a > this.f3025c) {
            return this.f3025c - this.f3023a;
        }
        if (this.f3023a < this.f3026d) {
            return this.f3026d - this.f3023a;
        }
        return 0.0f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.f3023a);
        dest.writeFloat(this.f3024b);
        dest.writeFloat(this.f3025c);
        dest.writeFloat(this.f3026d);
    }

    public void m3071a(double d) {
        this.f3023a = (float) (((double) this.f3023a) * d);
    }

    public String toString() {
        return "Position: [" + this.f3023a + "], Velocity:[" + this.f3024b + "], MaxPos: [" + this.f3025c + "], mMinPos: [" + this.f3026d + "] LastTime:[" + this.f3027e + "]";
    }
}
