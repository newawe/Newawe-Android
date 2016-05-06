package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.NonNull;

/* renamed from: com.inmobi.ads.p */
class TimestampWrapper<T> {
    @NonNull
    final T f1126a;
    long f1127b;

    TimestampWrapper(@NonNull T t) {
        this.f1126a = t;
        this.f1127b = SystemClock.uptimeMillis();
    }
}
