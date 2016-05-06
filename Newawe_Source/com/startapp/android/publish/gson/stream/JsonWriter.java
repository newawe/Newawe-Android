package com.startapp.android.publish.gson.stream;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.LineSeparator;

/* compiled from: StartAppSDK */
public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int[] stack;
    private int stackSize;

    static {
        REPLACEMENT_CHARS = new String[TransportMediator.FLAG_KEY_MEDIA_NEXT];
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        REPLACEMENT_CHARS[34] = "\\\"";
        REPLACEMENT_CHARS[92] = "\\\\";
        REPLACEMENT_CHARS[9] = "\\t";
        REPLACEMENT_CHARS[8] = "\\b";
        REPLACEMENT_CHARS[10] = "\\n";
        REPLACEMENT_CHARS[13] = "\\r";
        REPLACEMENT_CHARS[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = (String[]) REPLACEMENT_CHARS.clone();
        HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
        HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }

    public JsonWriter(Writer out) {
        this.stack = new int[32];
        this.stackSize = 0;
        push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }

    public final void setIndent(String indent) {
        if (indent.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = indent;
        this.separator = ": ";
    }

    public final void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final void setHtmlSafe(boolean htmlSafe) {
        this.htmlSafe = htmlSafe;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public final void setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() {
        writeDeferredName();
        return open(1, "[");
    }

    public JsonWriter endArray() {
        return close(1, 2, "]");
    }

    public JsonWriter beginObject() {
        writeDeferredName();
        return open(3, "{");
    }

    public JsonWriter endObject() {
        return close(3, 5, "}");
    }

    private JsonWriter open(int empty, String openBracket) {
        beforeValue(true);
        push(empty);
        this.out.write(openBracket);
        return this;
    }

    private JsonWriter close(int empty, int nonempty, String closeBracket) {
        int peek = peek();
        if (peek != nonempty && peek != empty) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else {
            this.stackSize--;
            if (peek == nonempty) {
                newline();
            }
            this.out.write(closeBracket);
            return this;
        }
    }

    private void push(int newTop) {
        if (this.stackSize == this.stack.length) {
            Object obj = new int[(this.stackSize * 2)];
            System.arraycopy(this.stack, 0, obj, 0, this.stackSize);
            this.stack = obj;
        }
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    private int peek() {
        if (this.stackSize != 0) {
            return this.stack[this.stackSize - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void replaceTop(int topOfStack) {
        this.stack[this.stackSize - 1] = topOfStack;
    }

    public JsonWriter name(String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (this.deferredName != null) {
            throw new IllegalStateException();
        } else if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.deferredName = name;
            return this;
        }
    }

    private void writeDeferredName() {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String value) {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue(false);
        string(value);
        return this;
    }

    public JsonWriter nullValue() {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue(false);
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean value) {
        writeDeferredName();
        beforeValue(false);
        this.out.write(value ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE);
        return this;
    }

    public JsonWriter value(long value) {
        writeDeferredName();
        beforeValue(false);
        this.out.write(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number value) {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        CharSequence obj = value.toString();
        if (this.lenient || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            beforeValue(false);
            this.out.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
    }

    public void flush() {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public void close() {
        this.out.close();
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.stack[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    private void string(String value) {
        int i = 0;
        String[] strArr = this.htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        this.out.write("\"");
        int length = value.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = value.charAt(i2);
            String str;
            if (charAt < '\u0080') {
                str = strArr[charAt];
                if (str == null) {
                }
                if (i < i2) {
                    this.out.write(value, i, i2 - i);
                }
                this.out.write(str);
                i = i2 + 1;
            } else {
                if (charAt == '\u2028') {
                    str = "\\u2028";
                } else if (charAt == '\u2029') {
                    str = "\\u2029";
                }
                if (i < i2) {
                    this.out.write(value, i, i2 - i);
                }
                this.out.write(str);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.out.write(value, i, length - i);
        }
        this.out.write("\"");
    }

    private void newline() {
        if (this.indent != null) {
            this.out.write(LineSeparator.Web);
            int i = this.stackSize;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.indent);
            }
        }
    }

    private void beforeName() {
        int peek = peek();
        if (peek == 5) {
            this.out.write(44);
        } else if (peek != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue(boolean root) {
        switch (peek()) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                replaceTop(2);
                newline();
                return;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.out.append(',');
                newline();
                return;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                this.out.append(this.separator);
                replaceTop(5);
                return;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                break;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        if (this.lenient || root) {
            replaceTop(7);
            return;
        }
        throw new IllegalStateException("JSON must start with an array or an object.");
    }
}
