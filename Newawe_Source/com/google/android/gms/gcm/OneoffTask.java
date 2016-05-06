package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR;
    private final long zzaLW;
    private final long zzaLX;

    /* renamed from: com.google.android.gms.gcm.OneoffTask.1 */
    static class C05411 implements Creator<OneoffTask> {
        C05411() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzeI(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return zzhf(i);
        }

        public OneoffTask zzeI(Parcel parcel) {
            return new OneoffTask(null);
        }

        public OneoffTask[] zzhf(int i) {
            return new OneoffTask[i];
        }
    }

    public static class Builder extends com.google.android.gms.gcm.Task.Builder {
        private long zzaLY;
        private long zzaLZ;

        public Builder() {
            this.zzaLY = -1;
            this.zzaLZ = -1;
            this.isPersisted = false;
        }

        public OneoffTask build() {
            checkConditions();
            return new OneoffTask();
        }

        protected void checkConditions() {
            super.checkConditions();
            if (this.zzaLY == -1 || this.zzaLZ == -1) {
                throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
            } else if (this.zzaLY >= this.zzaLZ) {
                throw new IllegalArgumentException("Window start must be shorter than window end.");
            }
        }

        public Builder setExecutionWindow(long windowStartDelaySeconds, long windowEndDelaySeconds) {
            this.zzaLY = windowStartDelaySeconds;
            this.zzaLZ = windowEndDelaySeconds;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.extras = extras;
            return this;
        }

        public Builder setPersisted(boolean isPersisted) {
            this.isPersisted = isPersisted;
            return this;
        }

        public Builder setRequiredNetwork(int requiredNetworkState) {
            this.requiredNetworkState = requiredNetworkState;
            return this;
        }

        public Builder setRequiresCharging(boolean requiresCharging) {
            this.requiresCharging = requiresCharging;
            return this;
        }

        public Builder setService(Class<? extends GcmTaskService> gcmTaskService) {
            this.gcmTaskService = gcmTaskService.getName();
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setUpdateCurrent(boolean updateCurrent) {
            this.updateCurrent = updateCurrent;
            return this;
        }
    }

    static {
        CREATOR = new C05411();
    }

    @Deprecated
    private OneoffTask(Parcel in) {
        super(in);
        this.zzaLW = in.readLong();
        this.zzaLX = in.readLong();
    }

    private OneoffTask(Builder builder) {
        super((com.google.android.gms.gcm.Task.Builder) builder);
        this.zzaLW = builder.zzaLY;
        this.zzaLX = builder.zzaLZ;
    }

    public long getWindowEnd() {
        return this.zzaLX;
    }

    public long getWindowStart() {
        return this.zzaLW;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaLW);
        bundle.putLong("window_end", this.zzaLX);
    }

    public String toString() {
        return super.toString() + " " + "windowStart=" + getWindowStart() + " " + "windowEnd=" + getWindowEnd();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeLong(this.zzaLW);
        parcel.writeLong(this.zzaLX);
    }
}
