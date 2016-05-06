package com.inmobi.commons.core.utilities;

import java.io.File;

/* renamed from: com.inmobi.commons.core.utilities.b */
public class FileUtils {
    public static void m1469a(File file) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        FileUtils.m1469a(file2);
                    } else {
                        file2.delete();
                    }
                }
            }
            file.delete();
        }
    }
}
