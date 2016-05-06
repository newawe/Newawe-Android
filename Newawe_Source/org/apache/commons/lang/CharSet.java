package org.apache.commons.lang;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharSet implements Serializable {
    public static final CharSet ASCII_ALPHA;
    public static final CharSet ASCII_ALPHA_LOWER;
    public static final CharSet ASCII_ALPHA_UPPER;
    public static final CharSet ASCII_NUMERIC;
    protected static final Map COMMON;
    public static final CharSet EMPTY;
    private static final long serialVersionUID = 5947847346149275958L;
    private final Set set;

    static {
        EMPTY = new CharSet((String) null);
        ASCII_ALPHA = new CharSet("a-zA-Z");
        ASCII_ALPHA_LOWER = new CharSet("a-z");
        ASCII_ALPHA_UPPER = new CharSet("A-Z");
        ASCII_NUMERIC = new CharSet("0-9");
        COMMON = Collections.synchronizedMap(new HashMap());
        COMMON.put(null, EMPTY);
        COMMON.put(StringUtils.EMPTY, EMPTY);
        COMMON.put("a-zA-Z", ASCII_ALPHA);
        COMMON.put("A-Za-z", ASCII_ALPHA);
        COMMON.put("a-z", ASCII_ALPHA_LOWER);
        COMMON.put("A-Z", ASCII_ALPHA_UPPER);
        COMMON.put("0-9", ASCII_NUMERIC);
    }

    public static CharSet getInstance(String setStr) {
        Object set = COMMON.get(setStr);
        if (set != null) {
            return (CharSet) set;
        }
        return new CharSet(setStr);
    }

    public static CharSet getInstance(String[] setStrs) {
        if (setStrs == null) {
            return null;
        }
        return new CharSet(setStrs);
    }

    protected CharSet(String setStr) {
        this.set = Collections.synchronizedSet(new HashSet());
        add(setStr);
    }

    protected CharSet(String[] set) {
        this.set = Collections.synchronizedSet(new HashSet());
        for (String add : set) {
            add(add);
        }
    }

    protected void add(String str) {
        if (str != null) {
            int len = str.length();
            int pos = 0;
            while (pos < len) {
                int remainder = len - pos;
                if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
                    this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
                    pos += 4;
                } else if (remainder >= 3 && str.charAt(pos + 1) == '-') {
                    this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
                    pos += 3;
                } else if (remainder < 2 || str.charAt(pos) != '^') {
                    this.set.add(CharRange.is(str.charAt(pos)));
                    pos++;
                } else {
                    this.set.add(CharRange.isNot(str.charAt(pos + 1)));
                    pos += 2;
                }
            }
        }
    }

    public CharRange[] getCharRanges() {
        return (CharRange[]) this.set.toArray(new CharRange[this.set.size()]);
    }

    public boolean contains(char ch) {
        for (CharRange range : this.set) {
            if (range.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharSet)) {
            return false;
        }
        return this.set.equals(((CharSet) obj).set);
    }

    public int hashCode() {
        return this.set.hashCode() + 89;
    }

    public String toString() {
        return this.set.toString();
    }
}
