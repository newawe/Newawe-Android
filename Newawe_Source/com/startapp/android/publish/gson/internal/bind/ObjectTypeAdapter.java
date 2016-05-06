package com.startapp.android.publish.gson.internal.bind;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.TypeAdapter;
import com.startapp.android.publish.gson.TypeAdapterFactory;
import com.startapp.android.publish.gson.internal.LinkedTreeMap;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonToken;
import com.startapp.android.publish.gson.stream.JsonWriter;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* compiled from: StartAppSDK */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;

    /* renamed from: com.startapp.android.publish.gson.internal.bind.ObjectTypeAdapter.2 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.bind.ObjectTypeAdapter.1 */
    static class StartAppSDK implements TypeAdapterFactory {
        StartAppSDK() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == Object.class) {
                return new ObjectTypeAdapter(null);
            }
            return null;
        }
    }

    static {
        FACTORY = new StartAppSDK();
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public Object read(JsonReader in) {
        switch (StartAppSDK.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                List arrayList = new ArrayList();
                in.beginArray();
                while (in.hasNext()) {
                    arrayList.add(read(in));
                }
                in.endArray();
                return arrayList;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                Object linkedTreeMap = new LinkedTreeMap();
                in.beginObject();
                while (in.hasNext()) {
                    linkedTreeMap.put(in.nextName(), read(in));
                }
                in.endObject();
                return linkedTreeMap;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return in.nextString();
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return Double.valueOf(in.nextDouble());
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                return Boolean.valueOf(in.nextBoolean());
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(JsonWriter out, Object value) {
        if (value == null) {
            out.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(value.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
            return;
        }
        adapter.write(out, value);
    }
}
