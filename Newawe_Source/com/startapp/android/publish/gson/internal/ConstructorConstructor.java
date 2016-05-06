package com.startapp.android.publish.gson.internal;

import com.startapp.android.publish.gson.InstanceCreator;
import com.startapp.android.publish.gson.JsonIOException;
import com.startapp.android.publish.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: StartAppSDK */
public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.12 */
    class AnonymousClass12 implements ObjectConstructor<T> {
        private final UnsafeAllocator unsafeAllocator;
        final /* synthetic */ Class val$rawType;
        final /* synthetic */ Type val$type;

        AnonymousClass12(Class cls, Type type) {
            this.val$rawType = cls;
            this.val$type = type;
            this.unsafeAllocator = UnsafeAllocator.create();
        }

        public T construct() {
            try {
                return this.unsafeAllocator.newInstance(this.val$rawType);
            } catch (Throwable e) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", e);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.1 */
    class StartAppSDK implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;
        final /* synthetic */ InstanceCreator val$typeCreator;

        StartAppSDK(InstanceCreator instanceCreator, Type type) {
            this.val$typeCreator = instanceCreator;
            this.val$type = type;
        }

        public T construct() {
            return this.val$typeCreator.createInstance(this.val$type);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.2 */
    class StartAppSDK implements ObjectConstructor<T> {
        final /* synthetic */ InstanceCreator val$rawTypeCreator;
        final /* synthetic */ Type val$type;

        StartAppSDK(InstanceCreator instanceCreator, Type type) {
            this.val$rawTypeCreator = instanceCreator;
            this.val$type = type;
        }

        public T construct() {
            return this.val$rawTypeCreator.createInstance(this.val$type);
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.3 */
    class StartAppSDK implements ObjectConstructor<T> {
        final /* synthetic */ Constructor val$constructor;

        StartAppSDK(Constructor constructor) {
            this.val$constructor = constructor;
        }

        public T construct() {
            try {
                return this.val$constructor.newInstance(null);
            } catch (Throwable e) {
                throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e2.getTargetException());
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.4 */
    class StartAppSDK implements ObjectConstructor<T> {
        StartAppSDK() {
        }

        public T construct() {
            return new TreeSet();
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.5 */
    class StartAppSDK implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;

        StartAppSDK(Type type) {
            this.val$type = type;
        }

        public T construct() {
            if (this.val$type instanceof ParameterizedType) {
                Type type = ((ParameterizedType) this.val$type).getActualTypeArguments()[0];
                if (type instanceof Class) {
                    return EnumSet.noneOf((Class) type);
                }
                throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.6 */
    class StartAppSDK implements ObjectConstructor<T> {
        StartAppSDK() {
        }

        public T construct() {
            return new LinkedHashSet();
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.7 */
    class StartAppSDK implements ObjectConstructor<T> {
        StartAppSDK() {
        }

        public T construct() {
            return new LinkedList();
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.8 */
    class StartAppSDK implements ObjectConstructor<T> {
        StartAppSDK() {
        }

        public T construct() {
            return new ArrayList();
        }
    }

    /* renamed from: com.startapp.android.publish.gson.internal.ConstructorConstructor.9 */
    class StartAppSDK implements ObjectConstructor<T> {
        StartAppSDK() {
        }

        public T construct() {
            return new TreeMap();
        }
    }

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class rawType = typeToken.getRawType();
        InstanceCreator instanceCreator = (InstanceCreator) this.instanceCreators.get(type);
        if (instanceCreator != null) {
            return new StartAppSDK(instanceCreator, type);
        }
        instanceCreator = (InstanceCreator) this.instanceCreators.get(rawType);
        if (instanceCreator != null) {
            return new StartAppSDK(instanceCreator, type);
        }
        ObjectConstructor<T> newDefaultConstructor = newDefaultConstructor(rawType);
        if (newDefaultConstructor != null) {
            return newDefaultConstructor;
        }
        newDefaultConstructor = newDefaultImplementationConstructor(type, rawType);
        return newDefaultConstructor == null ? newUnsafeAllocator(type, rawType) : newDefaultConstructor;
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> rawType) {
        try {
            Constructor declaredConstructor = rawType.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new StartAppSDK(declaredConstructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> rawType) {
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                return new StartAppSDK();
            }
            if (EnumSet.class.isAssignableFrom(rawType)) {
                return new StartAppSDK(type);
            }
            if (Set.class.isAssignableFrom(rawType)) {
                return new StartAppSDK();
            }
            if (Queue.class.isAssignableFrom(rawType)) {
                return new StartAppSDK();
            }
            return new StartAppSDK();
        } else if (!Map.class.isAssignableFrom(rawType)) {
            return null;
        } else {
            if (SortedMap.class.isAssignableFrom(rawType)) {
                return new StartAppSDK();
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new ObjectConstructor<T>() {
                    public T construct() {
                        return new LinkedTreeMap();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                public T construct() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Type type, Class<? super T> rawType) {
        return new AnonymousClass12(rawType, type);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}
