package com.startapp.android.publish.gson.typeadapters;

import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonElement;
import com.startapp.android.publish.gson.JsonObject;
import com.startapp.android.publish.gson.JsonParseException;
import com.startapp.android.publish.gson.JsonPrimitive;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.internal.Streams;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: StartAppSDK */
public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final Map<String, Class<?>> labelToSubtype;
    private final Map<Class<?>, String> subtypeToLabel;
    private final String typeFieldName;

    /* renamed from: com.startapp.android.publish.gson.typeadapters.RuntimeTypeAdapterFactory.1 */
    class StartAppSDK extends TypeAdapter<R> {
        final /* synthetic */ Map val$labelToDelegate;
        final /* synthetic */ Map val$subtypeToDelegate;

        StartAppSDK(Map map, Map map2) {
            this.val$labelToDelegate = map;
            this.val$subtypeToDelegate = map2;
        }

        public R read(JsonReader in) {
            JsonElement parse = Streams.parse(in);
            JsonElement remove = parse.getAsJsonObject().remove(RuntimeTypeAdapterFactory.this.typeFieldName);
            if (remove == null) {
                throw new JsonParseException("cannot deserialize " + RuntimeTypeAdapterFactory.this.baseType + " because it does not define a field named " + RuntimeTypeAdapterFactory.this.typeFieldName);
            }
            String asString = remove.getAsString();
            TypeAdapter typeAdapter = (TypeAdapter) this.val$labelToDelegate.get(asString);
            if (typeAdapter != null) {
                return typeAdapter.fromJsonTree(parse);
            }
            throw new JsonParseException("cannot deserialize " + RuntimeTypeAdapterFactory.this.baseType + " subtype named " + asString + "; did you forget to register a subtype?");
        }

        public void write(JsonWriter out, R value) {
            Class cls = value.getClass();
            String str = (String) RuntimeTypeAdapterFactory.this.subtypeToLabel.get(cls);
            TypeAdapter typeAdapter = (TypeAdapter) this.val$subtypeToDelegate.get(cls);
            if (typeAdapter == null) {
                throw new JsonParseException("cannot serialize " + cls.getName() + "; did you forget to register a subtype?");
            }
            JsonObject asJsonObject = typeAdapter.toJsonTree(value).getAsJsonObject();
            if (asJsonObject.has(RuntimeTypeAdapterFactory.this.typeFieldName)) {
                throw new JsonParseException("cannot serialize " + cls.getName() + " because it already defines a field named " + RuntimeTypeAdapterFactory.this.typeFieldName);
            }
            JsonElement jsonObject = new JsonObject();
            jsonObject.add(RuntimeTypeAdapterFactory.this.typeFieldName, new JsonPrimitive(str));
            for (Entry entry : asJsonObject.entrySet()) {
                jsonObject.add((String) entry.getKey(), (JsonElement) entry.getValue());
            }
            Streams.write(jsonObject, out);
        }
    }

    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName) {
        this.labelToSubtype = new LinkedHashMap();
        this.subtypeToLabel = new LinkedHashMap();
        if (typeFieldName == null || baseType == null) {
            throw new NullPointerException();
        }
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory(baseType, typeFieldName);
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        if (type == null || label == null) {
            throw new NullPointerException();
        } else if (this.subtypeToLabel.containsKey(type) || this.labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("types and labels must be unique");
        } else {
            this.labelToSubtype.put(label, type);
            this.subtypeToLabel.put(type, label);
            return this;
        }
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type) {
        return registerSubtype(type, type.getSimpleName());
    }

    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (type.getRawType() != this.baseType) {
            return null;
        }
        Map linkedHashMap = new LinkedHashMap();
        Map linkedHashMap2 = new LinkedHashMap();
        for (Entry entry : this.labelToSubtype.entrySet()) {
            TypeAdapter delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get((Class) entry.getValue()));
            linkedHashMap.put(entry.getKey(), delegateAdapter);
            linkedHashMap2.put(entry.getValue(), delegateAdapter);
        }
        return new StartAppSDK(linkedHashMap, linkedHashMap2);
    }
}
