package com.chartboost.sdk.impl;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import org.apache.http.protocol.HTTP;

public class bv {
    public static final BigInteger f697a;
    public static final BigInteger f698b;
    public static final BigInteger f699c;
    public static final BigInteger f700d;
    public static final BigInteger f701e;
    public static final BigInteger f702f;
    public static final BigInteger f703g;
    public static final BigInteger f704h;
    public static final File[] f705i;
    private static final Charset f706j;

    static {
        f697a = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        f698b = f697a.multiply(f697a);
        f699c = f697a.multiply(f698b);
        f700d = f697a.multiply(f699c);
        f701e = f697a.multiply(f700d);
        f702f = f697a.multiply(f701e);
        f703g = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).multiply(BigInteger.valueOf(1152921504606846976L));
        f704h = f697a.multiply(f703g);
        f705i = new File[0];
        f706j = Charset.forName(HTTP.UTF_8);
    }

    public static FileInputStream m760a(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    public static FileOutputStream m761a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.mkdirs() || parentFile.isDirectory())) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file, z);
    }

    public static byte[] m764b(File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = m760a(file);
            byte[] a = bw.m769a(inputStream, file.length());
            return a;
        } finally {
            bw.m766a(inputStream);
        }
    }

    public static void m762a(File file, byte[] bArr) throws IOException {
        m763a(file, bArr, false);
    }

    public static void m763a(File file, byte[] bArr, boolean z) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = m761a(file, z);
            outputStream.write(bArr);
            outputStream.close();
        } finally {
            bw.m767a(outputStream);
        }
    }
}
