package com.startapp.android.publish.gson;

import com.startapp.android.publish.gson.internal.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: StartAppSDK */
public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> members;

    public JsonObject() {
        this.members = new LinkedTreeMap();
    }

    public void add(String property, JsonElement value) {
        if (value == null) {
            value = JsonNull.INSTANCE;
        }
        this.members.put(property, value);
    }

    public JsonElement remove(String property) {
        return (JsonElement) this.members.remove(property);
    }

    public Set<Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public boolean has(String memberName) {
        return this.members.containsKey(memberName);
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof JsonObject) && ((JsonObject) o).members.equals(this.members));
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
