package com.jirbo.adcolony;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

class ai {
    private static String f2094a = null;
    private static final String f2095b = "INSTALLATION";

    ai() {
    }

    static {
        f2094a = null;
    }

    public static synchronized String m2232a(Context context) {
        String str;
        synchronized (ai.class) {
            if (f2094a == null) {
                File file = new File(context.getFilesDir(), f2095b);
                try {
                    if (!file.exists()) {
                        m2234b(file);
                    }
                    f2094a = m2233a(file);
                } catch (Exception e) {
                    throw new AdColonyException(e.toString());
                }
            }
            str = f2094a;
        }
        return str;
    }

    private static String m2233a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void m2234b(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
