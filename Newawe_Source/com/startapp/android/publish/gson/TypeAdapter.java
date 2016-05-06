package com.startapp.android.publish.gson;

import com.startapp.android.publish.gson.internal.bind.JsonTreeReader;
import com.startapp.android.publish.gson.internal.bind.JsonTreeWriter;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonWriter;

/* compiled from: StartAppSDK */
public abstract class TypeAdapter<T> {
    public abstract T read(JsonReader jsonReader);

    public abstract void write(JsonWriter jsonWriter, T t);

    public final JsonElement toJsonTree(T value) {
        try {
            JsonWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, value);
            return jsonTreeWriter.get();
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public final T fromJsonTree(JsonElement jsonTree) {
        try {
            return read(new JsonTreeReader(jsonTree));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }
}
