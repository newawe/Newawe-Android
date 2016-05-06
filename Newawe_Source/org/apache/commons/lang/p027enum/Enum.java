package org.apache.commons.lang.p027enum;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

/* renamed from: org.apache.commons.lang.enum.Enum */
public abstract class Enum implements Comparable, Serializable {
    private static final Map EMPTY_MAP;
    private static Map cEnumClasses = null;
    static Class class$org$apache$commons$lang$enum$Enum = null;
    static Class class$org$apache$commons$lang$enum$ValuedEnum = null;
    private static final long serialVersionUID = -487045951170455942L;
    private final transient int iHashCode;
    private final String iName;
    protected transient String iToString;

    /* renamed from: org.apache.commons.lang.enum.Enum.Entry */
    private static class Entry {
        final List list;
        final Map map;
        final List unmodifiableList;
        final Map unmodifiableMap;

        protected Entry() {
            this.map = new HashMap();
            this.unmodifiableMap = Collections.unmodifiableMap(this.map);
            this.list = new ArrayList(25);
            this.unmodifiableList = Collections.unmodifiableList(this.list);
        }
    }

    static {
        EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));
        cEnumClasses = new WeakHashMap();
    }

    protected Enum(String name) {
        this.iToString = null;
        init(name);
        this.iName = name;
        this.iHashCode = (getEnumClass().hashCode() + 7) + (name.hashCode() * 3);
    }

    private void init(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("The Enum name must not be empty or null");
        }
        Class enumClass = getEnumClass();
        if (enumClass == null) {
            throw new IllegalArgumentException("getEnumClass() must not be null");
        }
        Class cls = getClass();
        boolean ok = false;
        while (cls != null) {
            Class class$;
            if (class$org$apache$commons$lang$enum$Enum == null) {
                class$ = Enum.class$("org.apache.commons.lang.enum.Enum");
                class$org$apache$commons$lang$enum$Enum = class$;
            } else {
                class$ = class$org$apache$commons$lang$enum$Enum;
            }
            if (cls == class$) {
                break;
            }
            if (class$org$apache$commons$lang$enum$ValuedEnum == null) {
                class$ = Enum.class$("org.apache.commons.lang.enum.ValuedEnum");
                class$org$apache$commons$lang$enum$ValuedEnum = class$;
            } else {
                class$ = class$org$apache$commons$lang$enum$ValuedEnum;
            }
            if (cls == class$) {
                break;
            } else if (cls == enumClass) {
                ok = true;
                break;
            } else {
                cls = cls.getSuperclass();
            }
        }
        if (ok) {
            Entry entry;
            if (class$org$apache$commons$lang$enum$Enum == null) {
                class$ = Enum.class$("org.apache.commons.lang.enum.Enum");
                class$org$apache$commons$lang$enum$Enum = class$;
            } else {
                class$ = class$org$apache$commons$lang$enum$Enum;
            }
            synchronized (r5) {
                entry = (Entry) cEnumClasses.get(enumClass);
                if (entry == null) {
                    entry = Enum.createEntry(enumClass);
                    Map myMap = new WeakHashMap();
                    myMap.putAll(cEnumClasses);
                    myMap.put(enumClass, entry);
                    cEnumClasses = myMap;
                }
            }
            if (entry.map.containsKey(name)) {
                throw new IllegalArgumentException(new StringBuffer().append("The Enum name must be unique, '").append(name).append("' has already been added").toString());
            }
            entry.map.put(name, this);
            entry.list.add(this);
            return;
        }
        throw new IllegalArgumentException("getEnumClass() must return a superclass of this class");
    }

    static Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected Object readResolve() {
        Entry entry = (Entry) cEnumClasses.get(getEnumClass());
        if (entry == null) {
            return null;
        }
        return entry.map.get(getName());
    }

    protected static Enum getEnum(Class enumClass, String name) {
        Entry entry = Enum.getEntry(enumClass);
        if (entry == null) {
            return null;
        }
        return (Enum) entry.map.get(name);
    }

    protected static Map getEnumMap(Class enumClass) {
        Entry entry = Enum.getEntry(enumClass);
        if (entry == null) {
            return EMPTY_MAP;
        }
        return entry.unmodifiableMap;
    }

    protected static List getEnumList(Class enumClass) {
        Entry entry = Enum.getEntry(enumClass);
        if (entry == null) {
            return Collections.EMPTY_LIST;
        }
        return entry.unmodifiableList;
    }

    protected static Iterator iterator(Class enumClass) {
        return Enum.getEnumList(enumClass).iterator();
    }

    private static Entry getEntry(Class enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        }
        Class class$;
        if (class$org$apache$commons$lang$enum$Enum == null) {
            class$ = Enum.class$("org.apache.commons.lang.enum.Enum");
            class$org$apache$commons$lang$enum$Enum = class$;
        } else {
            class$ = class$org$apache$commons$lang$enum$Enum;
        }
        if (class$.isAssignableFrom(enumClass)) {
            Entry entry = (Entry) cEnumClasses.get(enumClass);
            if (entry != null) {
                return entry;
            }
            try {
                Class.forName(enumClass.getName(), true, enumClass.getClassLoader());
                return (Entry) cEnumClasses.get(enumClass);
            } catch (Exception e) {
                return entry;
            }
        }
        throw new IllegalArgumentException("The Class must be a subclass of Enum");
    }

    private static Entry createEntry(Class enumClass) {
        Entry entry = new Entry();
        Class cls = enumClass.getSuperclass();
        while (cls != null) {
            Class class$;
            if (class$org$apache$commons$lang$enum$Enum == null) {
                class$ = Enum.class$("org.apache.commons.lang.enum.Enum");
                class$org$apache$commons$lang$enum$Enum = class$;
            } else {
                class$ = class$org$apache$commons$lang$enum$Enum;
            }
            if (cls != class$) {
                if (class$org$apache$commons$lang$enum$ValuedEnum == null) {
                    class$ = Enum.class$("org.apache.commons.lang.enum.ValuedEnum");
                    class$org$apache$commons$lang$enum$ValuedEnum = class$;
                } else {
                    class$ = class$org$apache$commons$lang$enum$ValuedEnum;
                }
                if (cls == class$) {
                    break;
                }
                Entry loopEntry = (Entry) cEnumClasses.get(cls);
                if (loopEntry != null) {
                    entry.list.addAll(loopEntry.list);
                    entry.map.putAll(loopEntry.map);
                    break;
                }
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return entry;
    }

    public final String getName() {
        return this.iName;
    }

    public Class getEnumClass() {
        return getClass();
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() == getClass()) {
            return this.iName.equals(((Enum) other).iName);
        }
        if (other.getClass().getName().equals(getClass().getName())) {
            return this.iName.equals(getNameInOtherClassLoader(other));
        }
        return false;
    }

    public final int hashCode() {
        return this.iHashCode;
    }

    public int compareTo(Object other) {
        if (other == this) {
            return 0;
        }
        if (other.getClass() == getClass()) {
            return this.iName.compareTo(((Enum) other).iName);
        }
        if (other.getClass().getName().equals(getClass().getName())) {
            return this.iName.compareTo(getNameInOtherClassLoader(other));
        }
        throw new ClassCastException(new StringBuffer().append("Different enum class '").append(ClassUtils.getShortClassName(other.getClass())).append("'").toString());
    }

    private String getNameInOtherClassLoader(Object other) {
        try {
            return (String) other.getClass().getMethod("getName", null).invoke(other, null);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("This should not happen");
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("This should not happen");
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException("This should not happen");
        }
    }

    public String toString() {
        if (this.iToString == null) {
            this.iToString = new StringBuffer().append(ClassUtils.getShortClassName(getEnumClass())).append("[").append(getName()).append("]").toString();
        }
        return this.iToString;
    }
}
