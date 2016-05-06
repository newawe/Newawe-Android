package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: StartAppSDK */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.SqlDateTypeAdapter.1 */
    static class StartAppSDK implements TypeAdapterFactory {
        StartAppSDK() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new SqlDateTypeAdapter() : null;
        }
    }

    public SqlDateTypeAdapter() {
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }

    static {
        FACTORY = new StartAppSDK();
    }

    public synchronized Date read(JsonReader in) {
        Date date;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.format.parse(in.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return date;
    }

    public synchronized void write(JsonWriter out, Date value) {
        out.value(value == null ? null : this.format.format(value));
    }
}
