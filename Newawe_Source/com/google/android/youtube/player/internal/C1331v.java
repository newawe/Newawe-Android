package com.google.android.youtube.player.internal;

import android.os.IBinder;
import com.google.android.youtube.player.internal.C0674u.C1224a;
import java.lang.reflect.Field;

/* renamed from: com.google.android.youtube.player.internal.v */
public final class C1331v<T> extends C1224a {
    private final T f4257a;

    private C1331v(T t) {
        this.f4257a = t;
    }

    public static <T> C0674u m5145a(T t) {
        return new C1331v(t);
    }

    public static <T> T m5146a(C0674u c0674u) {
        if (c0674u instanceof C1331v) {
            return ((C1331v) c0674u).f4257a;
        }
        IBinder asBinder = c0674u.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        if (declaredFields.length == 1) {
            Field field = declaredFields[0];
            if (field.isAccessible()) {
                throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
            }
            field.setAccessible(true);
            try {
                return field.get(asBinder);
            } catch (Throwable e) {
                throw new IllegalArgumentException("Binder object is null.", e);
            } catch (Throwable e2) {
                throw new IllegalArgumentException("remoteBinder is the wrong class.", e2);
            } catch (Throwable e22) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", e22);
            }
        }
        throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
    }
}
