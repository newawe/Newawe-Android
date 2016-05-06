package com.startapp.android.publish.gson;

import com.startapp.android.publish.gson.internal.C$Gson$Preconditions;
import java.lang.reflect.Field;

/* compiled from: StartAppSDK */
public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field f) {
        C$Gson$Preconditions.checkNotNull(f);
        this.field = f;
    }
}
