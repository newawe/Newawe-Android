package com.jirbo.adcolony;

import java.io.File;

/* renamed from: com.jirbo.adcolony.i */
class C0774i {
    C0774i() {
    }

    public static boolean m2323a(File file) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    C0774i.m2323a(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }
}
