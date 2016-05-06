package com.startapp.android.publish.list3d;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: StartAppSDK */
class SimpleDynamics extends Dynamics implements Parcelable {
    public static final Creator<SimpleDynamics> CREATOR;
    private float f4156f;
    private float f4157g;

    /* renamed from: com.startapp.android.publish.list3d.SimpleDynamics.1 */
    static class StartAppSDK implements Creator<SimpleDynamics> {
        StartAppSDK() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m3099a(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m3100a(x0);
        }

        public SimpleDynamics m3099a(Parcel parcel) {
            return new SimpleDynamics(parcel);
        }

        public SimpleDynamics[] m3100a(int i) {
            return new SimpleDynamics[i];
        }
    }

    public SimpleDynamics(float frictionFactor, float snapToFactor) {
        this.f4156f = frictionFactor;
        this.f4157g = snapToFactor;
    }

    protected void m4855a(int i) {
        this.b += m3079c() * this.f4157g;
        this.a += (this.b * ((float) i)) / 1000.0f;
        this.b *= this.f4156f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(this.f4156f);
        dest.writeFloat(this.f4157g);
    }

    public SimpleDynamics(Parcel in) {
        super(in);
        this.f4156f = in.readFloat();
        this.f4157g = in.readFloat();
    }

    static {
        CREATOR = new StartAppSDK();
    }

    public void m4854a(double d) {
        super.m3071a(d);
    }

    public String toString() {
        return super.toString() + ", Friction: [" + this.f4156f + "], Snap:[" + this.f4157g + "]";
    }
}
