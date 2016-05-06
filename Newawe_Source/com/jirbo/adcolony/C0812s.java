package com.jirbo.adcolony;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.jirbo.adcolony.s */
class C0812s {
    char[] f2575a;
    int f2576b;
    int f2577c;

    C0812s(String str) {
        this.f2577c = str.length();
        StringBuilder stringBuilder = new StringBuilder(this.f2577c);
        int i = 0;
        while (i < this.f2577c) {
            char charAt = str.charAt(i);
            if ((charAt >= ' ' && charAt <= '~') || charAt == '\n') {
                stringBuilder.append(charAt);
            } else if ((charAt & TransportMediator.FLAG_KEY_MEDIA_NEXT) == 0) {
                stringBuilder.append(' ');
            } else if ((charAt & 224) == 192 && i + 1 < this.f2577c) {
                stringBuilder.append((char) (((charAt & 31) << 6) | (str.charAt(i + 1) & 63)));
                i++;
            } else if (i + 2 < this.f2577c) {
                stringBuilder.append((char) ((((charAt & 15) << 12) | ((str.charAt(i + 1) & 63) << 6)) | (str.charAt(i + 2) & 63)));
                i += 2;
            } else {
                stringBuilder.append('?');
            }
            i++;
        }
        this.f2577c = stringBuilder.length();
        this.f2575a = new char[this.f2577c];
        stringBuilder.getChars(0, this.f2577c, this.f2575a, 0);
    }

    C0812s(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(inputStream.available());
        int read = inputStream.read();
        while (read != -1) {
            if ((read >= 32 && read <= TransportMediator.KEYCODE_MEDIA_PLAY) || read == 10) {
                stringBuilder.append((char) read);
            } else if ((read & TransportMediator.FLAG_KEY_MEDIA_NEXT) == 0) {
                stringBuilder.append(' ');
            } else if ((read & 224) == 192) {
                stringBuilder.append((char) (((read & 31) << 6) | (inputStream.read() & 63)));
            } else {
                stringBuilder.append((char) ((((read & 15) << 12) | ((inputStream.read() & 63) << 6)) | (inputStream.read() & 63)));
            }
            read = inputStream.read();
        }
        inputStream.close();
        this.f2577c = stringBuilder.length();
        this.f2575a = new char[this.f2577c];
        stringBuilder.getChars(0, this.f2577c, this.f2575a, 0);
    }

    boolean m2493a() {
        return this.f2576b < this.f2577c;
    }

    char m2496b() {
        if (this.f2576b == this.f2577c) {
            return '\u0000';
        }
        return this.f2575a[this.f2576b];
    }

    char m2499c() {
        char[] cArr = this.f2575a;
        int i = this.f2576b;
        this.f2576b = i + 1;
        return cArr[i];
    }

    boolean m2494a(char c) {
        if (this.f2576b == this.f2577c || this.f2575a[this.f2576b] != c) {
            return false;
        }
        this.f2576b++;
        return true;
    }

    void m2497b(char c) {
        if (!m2494a(c)) {
            throw new AdColonyException("'" + c + "' expected.");
        }
    }

    boolean m2495a(String str) {
        int length = str.length();
        if (this.f2576b + length > this.f2577c) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != this.f2575a[this.f2576b + i]) {
                return false;
            }
        }
        this.f2576b += length;
        return true;
    }

    void m2498b(String str) {
        if (!m2495a(str)) {
            throw new AdColonyException("\"" + str + "\" expected.");
        }
    }

    void m2500d() {
        while (this.f2576b != this.f2577c) {
            char c = this.f2575a[this.f2576b];
            if (c == ' ' || c == '\n') {
                this.f2576b++;
            } else {
                return;
            }
        }
    }

    public static void m2492a(String[] strArr) {
    }
}
