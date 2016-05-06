package com.startapp.android.publish.gson.internal;

import com.startapp.android.publish.gson.JsonElement;
import com.startapp.android.publish.gson.JsonIOException;
import com.startapp.android.publish.gson.JsonNull;
import com.startapp.android.publish.gson.JsonSyntaxException;
import com.startapp.android.publish.gson.internal.bind.TypeAdapters;
import com.startapp.android.publish.gson.stream.JsonReader;
import com.startapp.android.publish.gson.stream.JsonWriter;
import java.io.Writer;

/* compiled from: StartAppSDK */
public final class Streams {

    /* compiled from: StartAppSDK */
    private static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        /* compiled from: StartAppSDK */
        static class CurrentWrite implements CharSequence {
            char[] chars;

            CurrentWrite() {
            }

            public int length() {
                return this.chars.length;
            }

            public char charAt(int i) {
                return this.chars[i];
            }

            public CharSequence subSequence(int start, int end) {
                return new String(this.chars, start, end - start);
            }
        }

        private AppendableWriter(Appendable appendable) {
            this.currentWrite = new CurrentWrite();
            this.appendable = appendable;
        }

        public void write(char[] chars, int offset, int length) {
            this.currentWrite.chars = chars;
            this.appendable.append(this.currentWrite, offset, offset + length);
        }

        public void write(int i) {
            this.appendable.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static JsonElement parse(JsonReader reader) {
        Object obj = 1;
        try {
            reader.peek();
            obj = null;
            return (JsonElement) TypeAdapters.JSON_ELEMENT.read(reader);
        } catch (Throwable e) {
            if (obj != null) {
                return JsonNull.INSTANCE;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonIOException(e22);
        } catch (Throwable e222) {
            throw new JsonSyntaxException(e222);
        }
    }

    public static void write(JsonElement element, JsonWriter writer) {
        TypeAdapters.JSON_ELEMENT.write(writer, element);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(null);
    }
}
