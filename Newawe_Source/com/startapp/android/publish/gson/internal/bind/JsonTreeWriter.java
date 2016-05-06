package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.JsonArray;
import com.startapp.android.publish.gson.JsonElement;
import com.startapp.android.publish.gson.JsonNull;
import com.startapp.android.publish.gson.JsonObject;
import com.startapp.android.publish.gson.JsonPrimitive;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED;
    private static final Writer UNWRITABLE_WRITER;
    private String pendingName;
    private JsonElement product;
    private final List<JsonElement> stack;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.JsonTreeWriter.1 */
    static class StartAppSDK extends Writer {
        StartAppSDK() {
        }

        public void write(char[] buffer, int offset, int counter) {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    }

    static {
        UNWRITABLE_WRITER = new StartAppSDK();
        SENTINEL_CLOSED = new JsonPrimitive("closed");
    }

    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
        this.stack = new ArrayList();
        this.product = JsonNull.INSTANCE;
    }

    public JsonElement get() {
        if (this.stack.isEmpty()) {
            return this.product;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.stack);
    }

    private JsonElement peek() {
        return (JsonElement) this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement value) {
        if (this.pendingName != null) {
            if (!value.isJsonNull() || getSerializeNulls()) {
                ((JsonObject) peek()).add(this.pendingName, value);
            }
            this.pendingName = null;
        } else if (this.stack.isEmpty()) {
            this.product = value;
        } else {
            JsonElement peek = peek();
            if (peek instanceof JsonArray) {
                ((JsonArray) peek).add(value);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginArray() {
        JsonElement jsonArray = new JsonArray();
        put(jsonArray);
        this.stack.add(jsonArray);
        return this;
    }

    public JsonWriter endArray() {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonArray) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginObject() {
        JsonElement jsonObject = new JsonObject();
        put(jsonObject);
        this.stack.add(jsonObject);
        return this;
    }

    public JsonWriter endObject() {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter name(String name) {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.pendingName = name;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter value(String value) {
        if (value == null) {
            return nullValue();
        }
        put(new JsonPrimitive(value));
        return this;
    }

    public JsonWriter nullValue() {
        put(JsonNull.INSTANCE);
        return this;
    }

    public JsonWriter value(boolean value) {
        put(new JsonPrimitive(Boolean.valueOf(value)));
        return this;
    }

    public JsonWriter value(long value) {
        put(new JsonPrimitive(Long.valueOf(value)));
        return this;
    }

    public JsonWriter value(Number value) {
        if (value == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double doubleValue = value.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
            }
        }
        put(new JsonPrimitive(value));
        return this;
    }

    public void flush() {
    }

    public void close() {
        if (this.stack.isEmpty()) {
            this.stack.add(SENTINEL_CLOSED);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
