package com.startapp.android.publish.gson;

/* compiled from: StartAppSDK */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
