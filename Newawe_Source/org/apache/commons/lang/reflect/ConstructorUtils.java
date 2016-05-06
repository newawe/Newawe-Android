package org.apache.commons.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;

public class ConstructorUtils {
    public static Object invokeConstructor(Class cls, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return invokeConstructor(cls, new Object[]{arg});
    }

    public static Object invokeConstructor(Class cls, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (args == null) {
            args = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeConstructor(cls, args, parameterTypes);
    }

    public static Object invokeConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (parameterTypes == null) {
            parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (args == null) {
            args = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Constructor ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
        if (ctor != null) {
            return ctor.newInstance(args);
        }
        throw new NoSuchMethodException(new StringBuffer().append("No such accessible constructor on object: ").append(cls.getName()).toString());
    }

    public static Object invokeExactConstructor(Class cls, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return invokeExactConstructor(cls, new Object[]{arg});
    }

    public static Object invokeExactConstructor(Class cls, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (args == null) {
            args = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        int arguments = args.length;
        Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeExactConstructor(cls, args, parameterTypes);
    }

    public static Object invokeExactConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (args == null) {
            args = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        if (parameterTypes == null) {
            parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Constructor ctor = getAccessibleConstructor(cls, parameterTypes);
        if (ctor != null) {
            return ctor.newInstance(args);
        }
        throw new NoSuchMethodException(new StringBuffer().append("No such accessible constructor on object: ").append(cls.getName()).toString());
    }

    public static Constructor getAccessibleConstructor(Class cls, Class parameterType) {
        return getAccessibleConstructor(cls, new Class[]{parameterType});
    }

    public static Constructor getAccessibleConstructor(Class cls, Class[] parameterTypes) {
        try {
            return getAccessibleConstructor(cls.getConstructor(parameterTypes));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Constructor getAccessibleConstructor(Constructor ctor) {
        return (MemberUtils.isAccessible(ctor) && Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) ? ctor : null;
    }

    public static Constructor getMatchingAccessibleConstructor(Class cls, Class[] parameterTypes) {
        Constructor ctor;
        try {
            ctor = cls.getConstructor(parameterTypes);
            MemberUtils.setAccessibleWorkaround(ctor);
            return ctor;
        } catch (NoSuchMethodException e) {
            Constructor result = null;
            Constructor[] ctors = cls.getConstructors();
            for (int i = 0; i < ctors.length; i++) {
                if (ClassUtils.isAssignable(parameterTypes, ctors[i].getParameterTypes(), true)) {
                    ctor = getAccessibleConstructor(ctors[i]);
                    if (ctor != null) {
                        MemberUtils.setAccessibleWorkaround(ctor);
                        if (result == null || MemberUtils.compareParameterTypes(ctor.getParameterTypes(), result.getParameterTypes(), parameterTypes) < 0) {
                            result = ctor;
                        }
                    }
                }
            }
            return result;
        }
    }
}
