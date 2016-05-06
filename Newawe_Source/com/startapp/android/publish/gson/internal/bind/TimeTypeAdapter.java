package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: StartAppSDK */
public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.TimeTypeAdapter.1 */
    static class StartAppSDK implements TypeAdapterFactory {
        StartAppSDK() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Time.class ? new TimeTypeAdapter() : null;
        }
    }

    public TimeTypeAdapter() {
        this.format = new SimpleDateFormat("hh:mm:ss a");
    }

    static {
        FACTORY = new StartAppSDK();
    }

    public synchronized Time read(JsonReader in) {
        Time time;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.format.parse(in.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return time;
    }

    public synchronized void write(JsonWriter out, Time value) {
        out.value(value == null ? null : this.format.format(value));
    }
}
