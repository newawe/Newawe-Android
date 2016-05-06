package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.FieldNamingStrategy;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.annotations.SerializedName;
import com.startapp.android.publish.gson.internal.C$Gson$Types;
import com.startapp.android.publish.gson.internal.ConstructorConstructor;
import com.startapp.android.publish.gson.internal.Excluder;
import com.startapp.android.publish.gson.internal.ObjectConstructor;
import com.startapp.android.publish.gson.internal.Primitives;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;

    /* compiled from: StartAppSDK */
    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        abstract void read(JsonReader jsonReader, Object obj);

        abstract void write(JsonWriter jsonWriter, Object obj);

        protected BoundField(String name, boolean serialized, boolean deserialized) {
            this.name = name;
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.ReflectiveTypeAdapterFactory.1 */
    class StartAppSDK extends BoundField {
        final TypeAdapter<?> typeAdapter;
        final /* synthetic */ Gson val$context;
        final /* synthetic */ Field val$field;
        final /* synthetic */ TypeToken val$fieldType;
        final /* synthetic */ boolean val$isPrimitive;

        StartAppSDK(String x0, boolean x1, boolean x2, Gson gson, TypeToken typeToken, Field field, boolean z) {
            this.val$context = gson;
            this.val$fieldType = typeToken;
            this.val$field = field;
            this.val$isPrimitive = z;
            super(x0, x1, x2);
            this.typeAdapter = this.val$context.getAdapter(this.val$fieldType);
        }

        void write(JsonWriter writer, Object value) {
            new TypeAdapterRuntimeTypeWrapper(this.val$context, this.typeAdapter, this.val$fieldType.getType()).write(writer, this.val$field.get(value));
        }

        void read(JsonReader reader, Object value) {
            Object read = this.typeAdapter.read(reader);
            if (read != null || (!this.val$isPrimitive && !this.val$fieldType.getRawType().isEnum())) {
                this.val$field.set(value, read);
            }
        }
    }

    /* compiled from: StartAppSDK */
    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        private Adapter(ObjectConstructor<T> constructor, Map<String, BoundField> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }

        public T read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            T construct = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    BoundField boundField = (BoundField) this.boundFields.get(in.nextName());
                    if (boundField == null || !boundField.deserialized) {
                        in.skipValue();
                    } else {
                        boundField.read(in, construct);
                    }
                }
                in.endObject();
                return construct;
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(JsonWriter out, T value) {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.serialized) {
                        out.name(boundField.name);
                        boundField.write(out, value);
                    }
                }
                out.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
    }

    public boolean excludeField(Field f, boolean serialize) {
        return (this.excluder.excludeClass(f.getType(), serialize) || this.excluder.excludeField(f, serialize)) ? false : true;
    }

    private String getFieldName(Field f) {
        SerializedName serializedName = (SerializedName) f.getAnnotation(SerializedName.class);
        return serializedName == null ? this.fieldNamingPolicy.translateName(f) : serializedName.value();
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class rawType = type.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new Adapter(getBoundFields(gson, type, rawType), null);
        }
        return null;
    }

    private BoundField createBoundField(Gson context, Field field, String name, TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
        return new StartAppSDK(name, serialize, deserialize, context, fieldType, field, Primitives.isPrimitive(fieldType.getRawType()));
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        Map<String, BoundField> linkedHashMap = new LinkedHashMap();
        if (raw.isInterface()) {
            return linkedHashMap;
        }
        Type type2 = type.getType();
        while (raw != Object.class) {
            for (Field field : raw.getDeclaredFields()) {
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    BoundField createBoundField = createBoundField(context, field, getFieldName(field), TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, field.getGenericType())), excludeField, excludeField2);
                    createBoundField = (BoundField) linkedHashMap.put(createBoundField.name, createBoundField);
                    if (createBoundField != null) {
                        throw new IllegalArgumentException(type2 + " declares multiple JSON fields named " + createBoundField.name);
                    }
                }
            }
            type = TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        }
        return linkedHashMap;
    }
}
