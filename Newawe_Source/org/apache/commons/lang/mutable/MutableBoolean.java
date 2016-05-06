package org.apache.commons.lang.mutable;

import java.io.Serializable;
import org.apache.commons.lang.BooleanUtils;

public class MutableBoolean implements Mutable, Serializable, Comparable {
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;

    public MutableBoolean(boolean value) {
        this.value = value;
    }

    public MutableBoolean(Boolean value) {
        this.value = value.booleanValue();
    }

    public Object getValue() {
        return BooleanUtils.toBooleanObject(this.value);
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void setValue(Object value) {
        setValue(((Boolean) value).booleanValue());
    }

    public boolean isTrue() {
        return this.value;
    }

    public boolean isFalse() {
        return !this.value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    public Boolean toBoolean() {
        return BooleanUtils.toBooleanObject(this.value);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableBoolean) && this.value == ((MutableBoolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
    }

    public int compareTo(Object obj) {
        if (this.value == ((MutableBoolean) obj).value) {
            return 0;
        }
        return this.value ? 1 : -1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
