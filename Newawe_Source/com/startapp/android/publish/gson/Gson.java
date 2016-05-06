package com.startapp.android.publish.gson;

import com.startapp.android.publish.gson.internal.ConstructorConstructor;
import com.startapp.android.publish.gson.internal.Excluder;
import com.startapp.android.publish.gson.internal.Primitives;
import com.startapp.android.publish.gson.internal.Streams;
import com.startapp.android.publish.gson.internal.bind.ArrayTypeAdapter;
import com.startapp.android.publish.gson.internal.bind.CollectionTypeAdapterFactory;
import com.startapp.android.publish.gson.internal.bind.DateTypeAdapter;
import com.startapp.android.publish.gson.internal.bind.MapTypeAdapterFactory;
import com.startapp.android.publish.gson.internal.bind.ObjectTypeAdapter;
import com.startapp.android.publish.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.startapp.android.publish.gson.internal.bind.SqlDateTypeAdapter;
import com.startapp.android.publish.gson.internal.bind.TimeTypeAdapter;
import com.startapp.android.publish.gson.internal.bind.TypeAdapters;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
public final class Gson {
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    final JsonDeserializationContext deserializationContext;
    private final List<TypeAdapterFactory> factories;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private final boolean prettyPrinting;
    final JsonSerializationContext serializationContext;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    /* renamed from: com.startapp.android.publish.gson.Gson.1 */
    class StartAppSDK implements JsonDeserializationContext {
        StartAppSDK() {
        }
    }

    /* renamed from: com.startapp.android.publish.gson.Gson.2 */
    class StartAppSDK implements JsonSerializationContext {
        StartAppSDK() {
        }
    }

    /* renamed from: com.startapp.android.publish.gson.Gson.3 */
    class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Double read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return Double.valueOf(in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) {
            if (value == null) {
                out.nullValue();
                return;
            }
            Gson.this.checkValidFloatingPoint(value.doubleValue());
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.Gson.4 */
    class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Float read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return Float.valueOf((float) in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) {
            if (value == null) {
                out.nullValue();
                return;
            }
            Gson.this.checkValidFloatingPoint((double) value.floatValue());
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.Gson.5 */
    class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return Long.valueOf(in.nextLong());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.toString());
            }
        }
    }

    /* compiled from: StartAppSDK */
    static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate != null) {
                throw new AssertionError();
            }
            this.delegate = typeAdapter;
        }

        public T read(JsonReader in) {
            if (this.delegate != null) {
                return this.delegate.read(in);
            }
            throw new IllegalStateException();
        }

        public void write(JsonWriter out, T value) {
            if (this.delegate == null) {
                throw new IllegalStateException();
            }
            this.delegate.write(out, value);
        }
    }

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldNamingPolicy, Map<Type, InstanceCreator<?>> instanceCreators, boolean serializeNulls, boolean complexMapKeySerialization, boolean generateNonExecutableGson, boolean htmlSafe, boolean prettyPrinting, boolean serializeSpecialFloatingPointValues, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> typeAdapterFactories) {
        this.calls = new ThreadLocal();
        this.typeTokenCache = Collections.synchronizedMap(new HashMap());
        this.deserializationContext = new StartAppSDK();
        this.serializationContext = new StartAppSDK();
        this.constructorConstructor = new ConstructorConstructor(instanceCreators);
        this.serializeNulls = serializeNulls;
        this.generateNonExecutableJson = generateNonExecutableGson;
        this.htmlSafe = htmlSafe;
        this.prettyPrinting = prettyPrinting;
        List arrayList = new ArrayList();
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList.add(ObjectTypeAdapter.FACTORY);
        arrayList.add(excluder);
        arrayList.addAll(typeAdapterFactories);
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        arrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter(longSerializationPolicy)));
        arrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(serializeSpecialFloatingPointValues)));
        arrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(serializeSpecialFloatingPointValues)));
        arrayList.add(TypeAdapters.NUMBER_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        arrayList.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        arrayList.add(TimeTypeAdapter.FACTORY);
        arrayList.add(SqlDateTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.TIMESTAMP_FACTORY);
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        arrayList.add(new MapTypeAdapterFactory(this.constructorConstructor, complexMapKeySerialization));
        arrayList.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingPolicy, excluder));
        this.factories = Collections.unmodifiableList(arrayList);
    }

    private TypeAdapter<Number> doubleAdapter(boolean serializeSpecialFloatingPointValues) {
        if (serializeSpecialFloatingPointValues) {
            return TypeAdapters.DOUBLE;
        }
        return new StartAppSDK();
    }

    private TypeAdapter<Number> floatAdapter(boolean serializeSpecialFloatingPointValues) {
        if (serializeSpecialFloatingPointValues) {
            return TypeAdapters.FLOAT;
        }
        return new StartAppSDK();
    }

    private void checkValidFloatingPoint(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(value + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new StartAppSDK();
    }

    public <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
        Map map;
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.typeTokenCache.get(type);
        if (typeAdapter == null) {
            Map map2 = (Map) this.calls.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.calls.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            FutureTypeAdapter futureTypeAdapter = (FutureTypeAdapter) map.get(type);
            if (futureTypeAdapter == null) {
                try {
                    FutureTypeAdapter futureTypeAdapter2 = new FutureTypeAdapter();
                    map.put(type, futureTypeAdapter2);
                    for (TypeAdapterFactory create : this.factories) {
                        typeAdapter = create.create(this, type);
                        if (typeAdapter != null) {
                            futureTypeAdapter2.setDelegate(typeAdapter);
                            this.typeTokenCache.put(type, typeAdapter);
                            map.remove(type);
                            if (obj != null) {
                                this.calls.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + type);
                } catch (Throwable th) {
                    map.remove(type);
                    if (obj != null) {
                        this.calls.remove();
                    }
                }
            }
        }
        return typeAdapter;
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        Object obj = null;
        for (TypeAdapterFactory typeAdapterFactory : this.factories) {
            if (obj != null) {
                TypeAdapter<T> create = typeAdapterFactory.create(this, type);
                if (create != null) {
                    return create;
                }
            } else if (typeAdapterFactory == skipPast) {
                obj = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + type);
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return getAdapter(TypeToken.get((Class) type));
    }

    public String toJson(Object src) {
        if (src == null) {
            return toJson(JsonNull.INSTANCE);
        }
        return toJson(src, src.getClass());
    }

    public String toJson(Object src, Type typeOfSrc) {
        Appendable stringWriter = new StringWriter();
        toJson(src, typeOfSrc, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(Object src, Type typeOfSrc, Appendable writer) {
        try {
            toJson(src, typeOfSrc, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(Object src, Type typeOfSrc, JsonWriter writer) {
        TypeAdapter adapter = getAdapter(TypeToken.get(typeOfSrc));
        boolean isLenient = writer.isLenient();
        writer.setLenient(true);
        boolean isHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(writer, src);
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        }
    }

    public String toJson(JsonElement jsonElement) {
        Appendable stringWriter = new StringWriter();
        toJson(jsonElement, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable writer) {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private JsonWriter newJsonWriter(Writer writer) {
        if (this.generateNonExecutableJson) {
            writer.write(")]}'\n");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public void toJson(JsonElement jsonElement, JsonWriter writer) {
        boolean isLenient = writer.isLenient();
        writer.setLenient(true);
        boolean isHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean serializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement, writer);
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            writer.setLenient(isLenient);
            writer.setHtmlSafe(isHtmlSafe);
            writer.setSerializeNulls(serializeNulls);
        }
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public <T> T fromJson(String json, Type typeOfT) {
        if (json == null) {
            return null;
        }
        return fromJson(new StringReader(json), typeOfT);
    }

    public <T> T fromJson(Reader json, Type typeOfT) {
        JsonReader jsonReader = new JsonReader(json);
        T fromJson = fromJson(jsonReader, typeOfT);
        assertFullConsumption(fromJson, jsonReader);
        return fromJson;
    }

    private static void assertFullConsumption(Object obj, JsonReader reader) {
        if (obj != null) {
            try {
                if (reader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (Throwable e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromJson(JsonReader reader, Type typeOfT) {
        boolean z = true;
        boolean isLenient = reader.isLenient();
        reader.setLenient(true);
        try {
            reader.peek();
            z = false;
            T read = getAdapter(TypeToken.get(typeOfT)).read(reader);
            reader.setLenient(isLenient);
            return read;
        } catch (Throwable e) {
            if (z) {
                reader.setLenient(isLenient);
                return null;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonSyntaxException(e22);
        } catch (Throwable th) {
            reader.setLenient(isLenient);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
