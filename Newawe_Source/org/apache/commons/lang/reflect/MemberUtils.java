package org.apache.commons.lang.reflect;

import com.android.volley.DefaultRetryPolicy;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.SystemUtils;

abstract class MemberUtils {
    private static final int ACCESS_TEST = 7;
    private static final Method IS_SYNTHETIC;
    private static final Class[] ORDERED_PRIMITIVE_TYPES;
    static Class class$java$lang$reflect$Member;

    MemberUtils() {
    }

    static {
        Method isSynthetic = null;
        if (SystemUtils.isJavaVersionAtLeast(1.5f)) {
            try {
                Class class$;
                if (class$java$lang$reflect$Member == null) {
                    class$ = class$("java.lang.reflect.Member");
                    class$java$lang$reflect$Member = class$;
                } else {
                    class$ = class$java$lang$reflect$Member;
                }
                isSynthetic = class$.getMethod("isSynthetic", ArrayUtils.EMPTY_CLASS_ARRAY);
            } catch (Exception e) {
            }
        }
        IS_SYNTHETIC = isSynthetic;
        Class[] clsArr = new Class[ACCESS_TEST];
        clsArr[0] = Byte.TYPE;
        clsArr[1] = Short.TYPE;
        clsArr[2] = Character.TYPE;
        clsArr[3] = Integer.TYPE;
        clsArr[4] = Long.TYPE;
        clsArr[5] = Float.TYPE;
        clsArr[6] = Double.TYPE;
        ORDERED_PRIMITIVE_TYPES = clsArr;
    }

    static Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    static void setAccessibleWorkaround(AccessibleObject o) {
        if (o != null && !o.isAccessible()) {
            Member m = (Member) o;
            if (Modifier.isPublic(m.getModifiers()) && isPackageAccess(m.getDeclaringClass().getModifiers())) {
                try {
                    o.setAccessible(true);
                } catch (SecurityException e) {
                }
            }
        }
    }

    static boolean isPackageAccess(int modifiers) {
        return (modifiers & ACCESS_TEST) == 0;
    }

    static boolean isAccessible(Member m) {
        return (m == null || !Modifier.isPublic(m.getModifiers()) || isSynthetic(m)) ? false : true;
    }

    static boolean isSynthetic(Member m) {
        if (IS_SYNTHETIC != null) {
            try {
                return ((Boolean) IS_SYNTHETIC.invoke(m, null)).booleanValue();
            } catch (Exception e) {
            }
        }
        return false;
    }

    static int compareParameterTypes(Class[] left, Class[] right, Class[] actual) {
        float leftCost = getTotalTransformationCost(actual, left);
        float rightCost = getTotalTransformationCost(actual, right);
        if (leftCost < rightCost) {
            return -1;
        }
        return rightCost < leftCost ? 1 : 0;
    }

    private static float getTotalTransformationCost(Class[] srcArgs, Class[] destArgs) {
        float totalCost = 0.0f;
        for (int i = 0; i < srcArgs.length; i++) {
            totalCost += getObjectTransformationCost(srcArgs[i], destArgs[i]);
        }
        return totalCost;
    }

    private static float getObjectTransformationCost(Class srcClass, Class destClass) {
        if (destClass.isPrimitive()) {
            return getPrimitivePromotionCost(srcClass, destClass);
        }
        float cost = 0.0f;
        while (srcClass != null && !destClass.equals(srcClass)) {
            if (destClass.isInterface() && ClassUtils.isAssignable(srcClass, destClass)) {
                cost += 0.25f;
                break;
            }
            cost += DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
            srcClass = srcClass.getSuperclass();
        }
        if (srcClass == null) {
            return cost + 1.5f;
        }
        return cost;
    }

    private static float getPrimitivePromotionCost(Class srcClass, Class destClass) {
        float cost = 0.0f;
        Class cls = srcClass;
        if (!cls.isPrimitive()) {
            cost = 0.0f + 0.1f;
            cls = ClassUtils.wrapperToPrimitive(cls);
        }
        int i = 0;
        while (cls != destClass && i < ORDERED_PRIMITIVE_TYPES.length) {
            if (cls == ORDERED_PRIMITIVE_TYPES[i]) {
                cost += 0.1f;
                if (i < ORDERED_PRIMITIVE_TYPES.length - 1) {
                    cls = ORDERED_PRIMITIVE_TYPES[i + 1];
                }
            }
            i++;
        }
        return cost;
    }
}
