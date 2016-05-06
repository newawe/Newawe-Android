package org.apache.commons.lang;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.exception.CloneFailedException;
import org.apache.commons.lang.reflect.MethodUtils;

public class ObjectUtils {
    public static final Null NULL;

    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }

    static {
        NULL = new Null();
    }

    public static Object defaultIfNull(Object object, Object defaultValue) {
        return object != null ? object : defaultValue;
    }

    public static boolean equals(Object object1, Object object2) {
        if (object1 == object2) {
            return true;
        }
        if (object1 == null || object2 == null) {
            return false;
        }
        return object1.equals(object2);
    }

    public static boolean notEqual(Object object1, Object object2) {
        return !equals(object1, object2);
    }

    public static int hashCode(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }

    public static String identityToString(Object object) {
        if (object == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        identityToString(buffer, object);
        return buffer.toString();
    }

    public static void identityToString(StringBuffer buffer, Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        }
        buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }

    public static StringBuffer appendIdentityToString(StringBuffer buffer, Object object) {
        if (object == null) {
            return null;
        }
        if (buffer == null) {
            buffer = new StringBuffer();
        }
        return buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }

    public static String toString(Object obj) {
        return obj == null ? StringUtils.EMPTY : obj.toString();
    }

    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    public static Object min(Comparable c1, Comparable c2) {
        return compare(c1, c2, true) <= 0 ? c1 : c2;
    }

    public static Object max(Comparable c1, Comparable c2) {
        return compare(c1, c2, false) >= 0 ? c1 : c2;
    }

    public static int compare(Comparable c1, Comparable c2) {
        return compare(c1, c2, false);
    }

    public static int compare(Comparable c1, Comparable c2, boolean nullGreater) {
        int i = -1;
        if (c1 == c2) {
            return 0;
        }
        if (c1 == null) {
            if (nullGreater) {
                return 1;
            }
            return -1;
        } else if (c2 != null) {
            return c1.compareTo(c2);
        } else {
            if (!nullGreater) {
                i = 1;
            }
            return i;
        }
    }

    public static Object clone(Object o) {
        if (!(o instanceof Cloneable)) {
            return null;
        }
        if (o.getClass().isArray()) {
            Class componentType = o.getClass().getComponentType();
            if (!componentType.isPrimitive()) {
                return ((Object[]) o).clone();
            }
            int length = Array.getLength(o);
            Object result = Array.newInstance(componentType, length);
            int length2 = length;
            while (true) {
                length = length2 - 1;
                if (length2 <= 0) {
                    return result;
                }
                Array.set(result, length, Array.get(o, length));
                length2 = length;
            }
        } else {
            try {
                return MethodUtils.invokeMethod(o, "clone", null);
            } catch (NoSuchMethodException e) {
                throw new CloneFailedException(new StringBuffer().append("Cloneable type ").append(o.getClass().getName()).append(" has no clone method").toString(), e);
            } catch (IllegalAccessException e2) {
                throw new CloneFailedException(new StringBuffer().append("Cannot clone Cloneable type ").append(o.getClass().getName()).toString(), e2);
            } catch (InvocationTargetException e3) {
                throw new CloneFailedException(new StringBuffer().append("Exception cloning Cloneable type ").append(o.getClass().getName()).toString(), e3.getTargetException());
            }
        }
    }

    public static Object cloneIfPossible(Object o) {
        Object clone = clone(o);
        return clone == null ? o : clone;
    }
}
