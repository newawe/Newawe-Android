package com.startapp.android.publish.gson;

import java.lang.reflect.Type;

/* compiled from: StartAppSDK */
public interface InstanceCreator<T> {
    T createInstance(Type type);
}
