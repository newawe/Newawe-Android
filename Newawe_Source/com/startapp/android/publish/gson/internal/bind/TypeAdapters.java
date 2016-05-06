package com.startapp.android.publish.gson.internal.bind;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonArray;
import com.startapp.android.publish.gson.JsonElement;
import com.startapp.android.publish.gson.JsonIOException;
import com.startapp.android.publish.gson.JsonNull;
import com.startapp.android.publish.gson.JsonObject;
import com.startapp.android.publish.gson.JsonPrimitive;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.annotations.SerializedName;
import com.startapp.android.publish.gson.internal.LazilyParsedNumber;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import com.startapp.android.publish.model.MetaData;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* compiled from: StartAppSDK */
public final class TypeAdapters {
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
    public static final TypeAdapter<BigInteger> BIG_INTEGER;
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY;
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.32 */
    static /* synthetic */ class AnonymousClass32 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.1 */
    static class StartAppSDK extends TypeAdapter<Class> {
        StartAppSDK() {
        }

        public void write(JsonWriter out, Class value) {
            if (value == null) {
                out.nullValue();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + value.getName() + ". Forgot to register a type adapter?");
        }

        public Class read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.28 */
    static class AnonymousClass28 implements TypeAdapterFactory {
        final /* synthetic */ Class val$type;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass28(Class cls, TypeAdapter typeAdapter) {
            this.val$type = cls;
            this.val$typeAdapter = typeAdapter;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == this.val$type ? this.val$typeAdapter : null;
        }

        public String toString() {
            return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.29 */
    static class AnonymousClass29 implements TypeAdapterFactory {
        final /* synthetic */ Class val$boxed;
        final /* synthetic */ TypeAdapter val$typeAdapter;
        final /* synthetic */ Class val$unboxed;

        AnonymousClass29(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.val$unboxed = cls;
            this.val$boxed = cls2;
            this.val$typeAdapter = typeAdapter;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class rawType = typeToken.getRawType();
            return (rawType == this.val$unboxed || rawType == this.val$boxed) ? this.val$typeAdapter : null;
        }

        public String toString() {
            return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.2 */
    static class StartAppSDK extends TypeAdapter<BitSet> {
        StartAppSDK() {
        }

        public BitSet read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            BitSet bitSet = new BitSet();
            in.beginArray();
            JsonToken peek = in.peek();
            int i = 0;
            while (peek != JsonToken.END_ARRAY) {
                boolean z;
                switch (AnonymousClass32.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()]) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        if (in.nextInt() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        z = in.nextBoolean();
                        break;
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        String nextString = in.nextString();
                        try {
                            if (Integer.parseInt(nextString) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                        }
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + peek);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                peek = in.peek();
            }
            in.endArray();
            return bitSet;
        }

        public void write(JsonWriter out, BitSet src) {
            if (src == null) {
                out.nullValue();
                return;
            }
            out.beginArray();
            for (int i = 0; i < src.length(); i++) {
                int i2;
                if (src.get(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                out.value((long) i2);
            }
            out.endArray();
        }
    }

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.30 */
    static class AnonymousClass30 implements TypeAdapterFactory {
        final /* synthetic */ Class val$base;
        final /* synthetic */ Class val$sub;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass30(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.val$base = cls;
            this.val$sub = cls2;
            this.val$typeAdapter = typeAdapter;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class rawType = typeToken.getRawType();
            return (rawType == this.val$base || rawType == this.val$sub) ? this.val$typeAdapter : null;
        }

        public String toString() {
            return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.31 */
    static class AnonymousClass31 implements TypeAdapterFactory {
        final /* synthetic */ Class val$clazz;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass31(Class cls, TypeAdapter typeAdapter) {
            this.val$clazz = cls;
            this.val$typeAdapter = typeAdapter;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return this.val$clazz.isAssignableFrom(typeToken.getRawType()) ? this.val$typeAdapter : null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.3 */
    static class StartAppSDK extends TypeAdapter<Boolean> {
        StartAppSDK() {
        }

        public Boolean read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else if (in.peek() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
            } else {
                return Boolean.valueOf(in.nextBoolean());
            }
        }

        public void write(JsonWriter out, Boolean value) {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.booleanValue());
            }
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.4 */
    static class StartAppSDK extends TypeAdapter<Boolean> {
        StartAppSDK() {
        }

        public Boolean read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return Boolean.valueOf(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Boolean value) {
            out.value(value == null ? "null" : value.toString());
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.5 */
    static class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) in.nextInt());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) {
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.6 */
    static class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) in.nextInt());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) {
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.7 */
    static class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(in.nextInt());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) {
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.8 */
    static class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return Long.valueOf(in.nextLong());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void write(JsonWriter out, Number value) {
            out.value(value);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.9 */
    static class StartAppSDK extends TypeAdapter<Number> {
        StartAppSDK() {
        }

        public Number read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return Float.valueOf((float) in.nextDouble());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, Number value) {
            out.value(value);
        }
    }

    /* compiled from: StartAppSDK */
    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<T, String> constantToName;
        private final Map<String, T> nameToConstant;

        public EnumTypeAdapter(Class<T> classOfT) {
            this.nameToConstant = new HashMap();
            this.constantToName = new HashMap();
            try {
                for (Enum enumR : (Enum[]) classOfT.getEnumConstants()) {
                    Object value;
                    String name = enumR.name();
                    SerializedName serializedName = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        value = serializedName.value();
                    } else {
                        String str = name;
                    }
                    this.nameToConstant.put(value, enumR);
                    this.constantToName.put(enumR, value);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public T read(JsonReader in) {
            if (in.peek() != JsonToken.NULL) {
                return (Enum) this.nameToConstant.get(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, T value) {
            out.value(value == null ? null : (String) this.constantToName.get(value));
        }
    }

    static {
        CLASS = new StartAppSDK();
        CLASS_FACTORY = newFactory(Class.class, CLASS);
        BIT_SET = new StartAppSDK();
        BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
        BOOLEAN = new StartAppSDK();
        BOOLEAN_AS_STRING = new StartAppSDK();
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
        BYTE = new StartAppSDK();
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
        SHORT = new StartAppSDK();
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
        INTEGER = new StartAppSDK();
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
        LONG = new StartAppSDK();
        FLOAT = new StartAppSDK();
        DOUBLE = new TypeAdapter<Number>() {
            public Number read(JsonReader in) {
                if (in.peek() != JsonToken.NULL) {
                    return Double.valueOf(in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) {
                out.value(value);
            }
        };
        NUMBER = new TypeAdapter<Number>() {
            public Number read(JsonReader in) {
                JsonToken peek = in.peek();
                switch (AnonymousClass32.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()]) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        return new LazilyParsedNumber(in.nextString());
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        in.nextNull();
                        return null;
                    default:
                        throw new JsonSyntaxException("Expecting number, got: " + peek);
                }
            }

            public void write(JsonWriter out, Number value) {
                out.value(value);
            }
        };
        NUMBER_FACTORY = newFactory(Number.class, NUMBER);
        CHARACTER = new TypeAdapter<Character>() {
            public Character read(JsonReader in) {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String nextString = in.nextString();
                if (nextString.length() == 1) {
                    return Character.valueOf(nextString.charAt(0));
                }
                throw new JsonSyntaxException("Expecting character, got: " + nextString);
            }

            public void write(JsonWriter out, Character value) {
                out.value(value == null ? null : String.valueOf(value));
            }
        };
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
        STRING = new TypeAdapter<String>() {
            public String read(JsonReader in) {
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(in.nextBoolean());
                } else {
                    return in.nextString();
                }
            }

            public void write(JsonWriter out, String value) {
                out.value(value);
            }
        };
        BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
            public BigDecimal read(JsonReader in) {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return new BigDecimal(in.nextString());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, BigDecimal value) {
                out.value((Number) value);
            }
        };
        BIG_INTEGER = new TypeAdapter<BigInteger>() {
            public BigInteger read(JsonReader in) {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return new BigInteger(in.nextString());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, BigInteger value) {
                out.value((Number) value);
            }
        };
        STRING_FACTORY = newFactory(String.class, STRING);
        STRING_BUILDER = new TypeAdapter<StringBuilder>() {
            public StringBuilder read(JsonReader in) {
                if (in.peek() != JsonToken.NULL) {
                    return new StringBuilder(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, StringBuilder value) {
                out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
        STRING_BUFFER = new TypeAdapter<StringBuffer>() {
            public StringBuffer read(JsonReader in) {
                if (in.peek() != JsonToken.NULL) {
                    return new StringBuffer(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, StringBuffer value) {
                out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
        URL = new TypeAdapter<URL>() {
            public URL read(JsonReader in) {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String nextString = in.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URL(nextString);
            }

            public void write(JsonWriter out, URL value) {
                out.value(value == null ? null : value.toExternalForm());
            }
        };
        URL_FACTORY = newFactory(URL.class, URL);
        URI = new TypeAdapter<URI>() {
            public URI read(JsonReader in) {
                URI uri = null;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                } else {
                    try {
                        String nextString = in.nextString();
                        if (!"null".equals(nextString)) {
                            uri = new URI(nextString);
                        }
                    } catch (Throwable e) {
                        throw new JsonIOException(e);
                    }
                }
                return uri;
            }

            public void write(JsonWriter out, URI value) {
                out.value(value == null ? null : value.toASCIIString());
            }
        };
        URI_FACTORY = newFactory(URI.class, URI);
        INET_ADDRESS = new TypeAdapter<InetAddress>() {
            public InetAddress read(JsonReader in) {
                if (in.peek() != JsonToken.NULL) {
                    return InetAddress.getByName(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, InetAddress value) {
                out.value(value == null ? null : value.getHostAddress());
            }
        };
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
        UUID = new TypeAdapter<UUID>() {
            public UUID read(JsonReader in) {
                if (in.peek() != JsonToken.NULL) {
                    return UUID.fromString(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, UUID value) {
                out.value(value == null ? null : value.toString());
            }
        };
        UUID_FACTORY = newFactory(UUID.class, UUID);
        TIMESTAMP_FACTORY = new TypeAdapterFactory() {

            /* renamed from: com.startapp.android.publish.gson.internal.bind.TypeAdapters.22.1 */
            class StartAppSDK extends TypeAdapter<Timestamp> {
                final /* synthetic */ TypeAdapter val$dateTypeAdapter;

                StartAppSDK(TypeAdapter typeAdapter) {
                    this.val$dateTypeAdapter = typeAdapter;
                }

                public Timestamp read(JsonReader in) {
                    Date date = (Date) this.val$dateTypeAdapter.read(in);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void write(JsonWriter out, Timestamp value) {
                    this.val$dateTypeAdapter.write(out, value);
                }
            }

            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() != Timestamp.class) {
                    return null;
                }
                return new StartAppSDK(gson.getAdapter(Date.class));
            }
        };
        CALENDAR = new TypeAdapter<Calendar>() {
            public Calendar read(JsonReader in) {
                int i = 0;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                in.beginObject();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (in.peek() != JsonToken.END_OBJECT) {
                    String nextName = in.nextName();
                    int nextInt = in.nextInt();
                    if ("year".equals(nextName)) {
                        i6 = nextInt;
                    } else if ("month".equals(nextName)) {
                        i5 = nextInt;
                    } else if ("dayOfMonth".equals(nextName)) {
                        i4 = nextInt;
                    } else if ("hourOfDay".equals(nextName)) {
                        i3 = nextInt;
                    } else if ("minute".equals(nextName)) {
                        i2 = nextInt;
                    } else if ("second".equals(nextName)) {
                        i = nextInt;
                    }
                }
                in.endObject();
                return new GregorianCalendar(i6, i5, i4, i3, i2, i);
            }

            public void write(JsonWriter out, Calendar value) {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.beginObject();
                out.name("year");
                out.value((long) value.get(1));
                out.name("month");
                out.value((long) value.get(2));
                out.name("dayOfMonth");
                out.value((long) value.get(5));
                out.name("hourOfDay");
                out.value((long) value.get(11));
                out.name("minute");
                out.value((long) value.get(12));
                out.name("second");
                out.value((long) value.get(13));
                out.endObject();
            }
        };
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
        LOCALE = new TypeAdapter<Locale>() {
            public Locale read(JsonReader in) {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String nextToken;
                String nextToken2;
                String nextToken3;
                StringTokenizer stringTokenizer = new StringTokenizer(in.nextString(), "_");
                if (stringTokenizer.hasMoreElements()) {
                    nextToken = stringTokenizer.nextToken();
                } else {
                    nextToken = null;
                }
                if (stringTokenizer.hasMoreElements()) {
                    nextToken2 = stringTokenizer.nextToken();
                } else {
                    nextToken2 = null;
                }
                if (stringTokenizer.hasMoreElements()) {
                    nextToken3 = stringTokenizer.nextToken();
                } else {
                    nextToken3 = null;
                }
                if (nextToken2 == null && nextToken3 == null) {
                    return new Locale(nextToken);
                }
                if (nextToken3 == null) {
                    return new Locale(nextToken, nextToken2);
                }
                return new Locale(nextToken, nextToken2, nextToken3);
            }

            public void write(JsonWriter out, Locale value) {
                out.value(value == null ? null : value.toString());
            }
        };
        LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
        JSON_ELEMENT = new TypeAdapter<JsonElement>() {
            public JsonElement read(JsonReader in) {
                JsonElement jsonArray;
                switch (AnonymousClass32.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        return new JsonPrimitive(new LazilyParsedNumber(in.nextString()));
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        return new JsonPrimitive(in.nextString());
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        in.nextNull();
                        return JsonNull.INSTANCE;
                    case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                        jsonArray = new JsonArray();
                        in.beginArray();
                        while (in.hasNext()) {
                            jsonArray.add(read(in));
                        }
                        in.endArray();
                        return jsonArray;
                    case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                        jsonArray = new JsonObject();
                        in.beginObject();
                        while (in.hasNext()) {
                            jsonArray.add(in.nextName(), read(in));
                        }
                        in.endObject();
                        return jsonArray;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            public void write(JsonWriter out, JsonElement value) {
                if (value == null || value.isJsonNull()) {
                    out.nullValue();
                } else if (value.isJsonPrimitive()) {
                    JsonPrimitive asJsonPrimitive = value.getAsJsonPrimitive();
                    if (asJsonPrimitive.isNumber()) {
                        out.value(asJsonPrimitive.getAsNumber());
                    } else if (asJsonPrimitive.isBoolean()) {
                        out.value(asJsonPrimitive.getAsBoolean());
                    } else {
                        out.value(asJsonPrimitive.getAsString());
                    }
                } else if (value.isJsonArray()) {
                    out.beginArray();
                    Iterator it = value.getAsJsonArray().iterator();
                    while (it.hasNext()) {
                        write(out, (JsonElement) it.next());
                    }
                    out.endArray();
                } else if (value.isJsonObject()) {
                    out.beginObject();
                    for (Entry entry : value.getAsJsonObject().entrySet()) {
                        out.name((String) entry.getKey());
                        write(out, (JsonElement) entry.getValue());
                    }
                    out.endObject();
                } else {
                    throw new IllegalArgumentException("Couldn't write " + value.getClass());
                }
            }
        };
        JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
        ENUM_FACTORY = newEnumTypeHierarchyFactory();
    }

    public static TypeAdapterFactory newEnumTypeHierarchyFactory() {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = rawType.getSuperclass();
                }
                return new EnumTypeAdapter(rawType);
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> type, TypeAdapter<TT> typeAdapter) {
        return new AnonymousClass28(type, typeAdapter);
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> unboxed, Class<TT> boxed, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass29(unboxed, boxed, typeAdapter);
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> base, Class<? extends TT> sub, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass30(base, sub, typeAdapter);
    }

    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(Class<TT> clazz, TypeAdapter<TT> typeAdapter) {
        return new AnonymousClass31(clazz, typeAdapter);
    }
}
