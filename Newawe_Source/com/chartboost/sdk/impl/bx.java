package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.io.Writer;

public class bx extends Writer implements Serializable {
    private final StringBuilder f709a;

    public bx() {
        this.f709a = new StringBuilder();
    }

    public bx(int i) {
        this.f709a = new StringBuilder(i);
    }

    public Writer append(char value) {
        this.f709a.append(value);
        return this;
    }

    public Writer append(CharSequence value) {
        this.f709a.append(value);
        return this;
    }

    public Writer append(CharSequence value, int start, int end) {
        this.f709a.append(value, start, end);
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public void write(String value) {
        if (value != null) {
            this.f709a.append(value);
        }
    }

    public void write(char[] value, int offset, int length) {
        if (value != null) {
            this.f709a.append(value, offset, length);
        }
    }

    public String toString() {
        return this.f709a.toString();
    }
}
