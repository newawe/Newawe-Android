package com.startapp.android.publish.gson.internal.bind;

import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: StartAppSDK */
public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat enUsFormat;
    private final DateFormat iso8601Format;
    private final DateFormat localFormat;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.DateTypeAdapter.1 */
    static class StartAppSDK implements TypeAdapterFactory {
        StartAppSDK() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new DateTypeAdapter() : null;
        }
    }

    public DateTypeAdapter() {
        this.enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.localFormat = DateFormat.getDateTimeInstance(2, 2);
        this.iso8601Format = buildIso8601Format();
    }

    static {
        FACTORY = new StartAppSDK();
    }

    private static DateFormat buildIso8601Format() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public Date read(JsonReader in) {
        if (in.peek() != JsonToken.NULL) {
            return deserializeToDate(in.nextString());
        }
        in.nextNull();
        return null;
    }

    private synchronized Date deserializeToDate(String json) {
        Date parse;
        try {
            parse = this.localFormat.parse(json);
        } catch (ParseException e) {
            try {
                parse = this.enUsFormat.parse(json);
            } catch (ParseException e2) {
                try {
                    parse = this.iso8601Format.parse(json);
                } catch (Throwable e3) {
                    throw new JsonSyntaxException(json, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void write(JsonWriter out, Date value) {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(this.enUsFormat.format(value));
        }
    }
}
