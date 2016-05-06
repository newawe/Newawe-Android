package com.startapp.android.publish.gson;

import java.lang.reflect.Type;

/* compiled from: StartAppSDK */
public interface JsonSerializer<T> {
    JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext);
}
