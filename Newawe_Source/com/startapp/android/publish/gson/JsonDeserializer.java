package com.startapp.android.publish.gson;

import java.lang.reflect.Type;

/* compiled from: StartAppSDK */
public interface JsonDeserializer<T> {
    T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext);
}
