package com.startapp.android.publish.gson;

/* compiled from: StartAppSDK */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE;

    static {
        INSTANCE = new JsonNull();
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof JsonNull);
    }
}
