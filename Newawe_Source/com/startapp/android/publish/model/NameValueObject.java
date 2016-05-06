package com.startapp.android.publish.model;

import java.util.Set;

/* compiled from: StartAppSDK */
public class NameValueObject {
    private String name;
    private String value;
    private Set<String> valueSet;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<String> getValueSet() {
        return this.valueSet;
    }

    public void setValueSet(Set<String> valueSet) {
        this.valueSet = valueSet;
    }

    public String toString() {
        return "NameValueObject [name=" + this.name + ", value=" + this.value + ", valueSet=" + this.valueSet + "]";
    }
}
