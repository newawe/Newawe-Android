package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.internal.C$Gson$Types;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.ArrayTypeAdapter.1 */
    static class StartAppSDK implements TypeAdapterFactory {
        StartAppSDK() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Type type = typeToken.getType();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            type = C$Gson$Types.getArrayComponentType(type);
            return new ArrayTypeAdapter(gson, gson.getAdapter(TypeToken.get(type)), C$Gson$Types.getRawType(type));
        }
    }

    static {
        FACTORY = new StartAppSDK();
    }

    public ArrayTypeAdapter(Gson context, TypeAdapter<E> componentTypeAdapter, Class<E> componentType) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(context, componentTypeAdapter, componentType);
        this.componentType = componentType;
    }

    public Object read(JsonReader in) {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        List arrayList = new ArrayList();
        in.beginArray();
        while (in.hasNext()) {
            arrayList.add(this.componentTypeAdapter.read(in));
        }
        in.endArray();
        Object newInstance = Array.newInstance(this.componentType, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void write(JsonWriter out, Object array) {
        if (array == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            this.componentTypeAdapter.write(out, Array.get(array, i));
        }
        out.endArray();
    }
}
