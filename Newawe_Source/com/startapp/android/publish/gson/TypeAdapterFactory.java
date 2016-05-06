package com.startapp.android.publish.gson;

import com.startapp.android.publish.gson.reflect.TypeToken;

/* compiled from: StartAppSDK */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
