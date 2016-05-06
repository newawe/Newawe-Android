package com.immersion.hapticmediasdk.utils;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class FileManager {
    public static final String HAPTIC_STORAGE_FILENAME = "dat.hapt";
    public static final String TAG = "FileManager";
    public static int f1014b04270427042704270427 = 75;
    public static int f1015b0427042704270427 = 1;
    public static int f1016b0427042704270427 = 2;
    public static int f1017b04270427;
    Context f1018a;
    private int f1019b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileManager(android.content.Context r4) {
        /*
        r3 = this;
        r2 = 0;
        r0 = 3;
        r1 = 0;
    L_0x0003:
        switch(r2) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r2) {
            case 0: goto L_0x000a;
            case 1: goto L_0x0003;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r3.<init>();
    L_0x000d:
        r0 = r0 / r1;
        goto L_0x000d;
    L_0x000f:
        r0 = move-exception;
        r3.f1019b = r2;
        r3.f1018a = r4;
        r0 = android.os.Process.myTid();
        r3.f1019b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.utils.FileManager.<init>(android.content.Context):void");
    }

    public static int m1107b042704270427() {
        return 0;
    }

    public static int m1108b042704270427() {
        return 2;
    }

    public static int m1109b042704270427() {
        return 28;
    }

    public void closeCloseable(Closeable closeable) {
        if (((m1109b042704270427() + f1015b0427042704270427) * m1109b042704270427()) % m1108b042704270427() != f1017b04270427) {
            f1014b04270427042704270427 = m1109b042704270427();
            f1017b04270427 = m1109b042704270427();
        }
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                try {
                    e.printStackTrace();
                } catch (Exception e2) {
                    throw e2;
                }
            } catch (Exception e22) {
                throw e22;
            }
        }
    }

    public void deleteHapticStorage() {
        try {
            File[] listFiles = new File(getInternalHapticPath()).listFiles();
            if (listFiles != null) {
                int bЧЧ04270427Ч0427 = m1109b042704270427();
                switch ((bЧЧ04270427Ч0427 * (f1015b0427042704270427 + bЧЧ04270427Ч0427)) % f1016b0427042704270427) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    default:
                        f1015b0427042704270427 = 63;
                        break;
                }
                for (File file : listFiles) {
                    if (file.getName().endsWith(this.f1019b + HAPTIC_STORAGE_FILENAME)) {
                        try {
                            file.delete();
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public File getHapticStorageFile(String str) {
        try {
            try {
                String path = this.f1018a.getFilesDir().getPath();
                StringBuilder append = new StringBuilder().append(getUniqueFileName(str));
                String str2 = HAPTIC_STORAGE_FILENAME;
                if (((f1014b04270427042704270427 + f1015b0427042704270427) * f1014b04270427042704270427) % f1016b0427042704270427 != m1107b042704270427()) {
                    f1014b04270427042704270427 = m1109b042704270427();
                    f1017b04270427 = m1109b042704270427();
                }
                return new File(path, append.append(str2).toString());
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public String getInternalHapticPath() {
        File filesDir = this.f1018a.getFilesDir();
        int bЧЧ04270427Ч0427 = m1109b042704270427();
        switch ((bЧЧ04270427Ч0427 * (f1015b0427042704270427 + bЧЧ04270427Ч0427)) % f1016b0427042704270427) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f1015b0427042704270427 = 65;
                break;
        }
        return filesDir.getAbsolutePath();
    }

    public String getUniqueFileName(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes(), 0, str.length());
            String str2 = "%032x_%d";
            Object[] objArr = new Object[2];
            objArr[0] = new BigInteger(1, instance.digest());
            if (((f1014b04270427042704270427 + f1015b0427042704270427) * f1014b04270427042704270427) % f1016b0427042704270427 != f1017b04270427) {
                f1014b04270427042704270427 = m1109b042704270427();
                f1017b04270427 = 59;
            }
            objArr[1] = Integer.valueOf(this.f1019b);
            return String.format(str2, objArr);
        } catch (Exception e) {
            try {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                throw e2;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.BufferedOutputStream makeOutputStream(java.lang.String r9) {
        /*
        r8 = this;
        r0 = 0;
        r7 = 0;
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = new byte[r1];
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x006a }
        r4.<init>(r9);	 Catch:{ Exception -> 0x006a }
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x006a }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x006a }
        r2.<init>();	 Catch:{ Exception -> 0x006a }
        r5 = f1014b04270427042704270427;
        r6 = f1015b0427042704270427;
        r6 = r6 + r5;
        r5 = r5 * r6;
        r6 = f1016b0427042704270427;
        r5 = r5 % r6;
        switch(r5) {
            case 0: goto L_0x0028;
            default: goto L_0x001e;
        };
    L_0x001e:
        r5 = 73;
        f1014b04270427042704270427 = r5;
        r5 = m1109b042704270427();
        f1017b04270427 = r5;
    L_0x0028:
        r5 = r8.getUniqueFileName(r9);	 Catch:{ Exception -> 0x006a }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x006a }
        r5 = "dat.hapt";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x006a }
        r2 = r2.toString();	 Catch:{ Exception -> 0x006a }
        r1.<init>(r2);	 Catch:{ Exception -> 0x006a }
        r2 = r8.f1018a;	 Catch:{ Exception -> 0x006a }
        r5 = 0;
        r2 = r2.openFileOutput(r1, r5);	 Catch:{ Exception -> 0x006a }
        r1 = r4.available();	 Catch:{ Exception -> 0x0070 }
    L_0x0048:
        if (r1 <= 0) goto L_0x0057;
    L_0x004a:
        r1 = r4.read(r3);	 Catch:{ Exception -> 0x0070 }
        r5 = 0;
        r2.write(r3, r5, r1);	 Catch:{ Exception -> 0x0070 }
        r1 = r4.available();	 Catch:{ Exception -> 0x0070 }
        goto L_0x0048;
    L_0x0057:
        r4.close();	 Catch:{ Exception -> 0x0070 }
    L_0x005a:
        if (r2 == 0) goto L_0x0069;
    L_0x005c:
        r0 = new java.io.BufferedOutputStream;
    L_0x005e:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x005e;
            case 1: goto L_0x0066;
            default: goto L_0x0062;
        };
    L_0x0062:
        switch(r7) {
            case 0: goto L_0x0066;
            case 1: goto L_0x005e;
            default: goto L_0x0065;
        };
    L_0x0065:
        goto L_0x0062;
    L_0x0066:
        r0.<init>(r2);
    L_0x0069:
        return r0;
    L_0x006a:
        r1 = move-exception;
        r2 = r0;
    L_0x006c:
        r1.printStackTrace();
        goto L_0x005a;
    L_0x0070:
        r1 = move-exception;
        goto L_0x006c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.utils.FileManager.makeOutputStream(java.lang.String):java.io.BufferedOutputStream");
    }

    public BufferedOutputStream makeOutputStreamForStreaming(String str) {
        try {
            try {
                OutputStream openFileOutput = this.f1018a.openFileOutput(getUniqueFileName(str) + HAPTIC_STORAGE_FILENAME, 0);
                if (((f1014b04270427042704270427 + f1015b0427042704270427) * f1014b04270427042704270427) % f1016b0427042704270427 != f1017b04270427) {
                    f1014b04270427042704270427 = 23;
                    f1017b04270427 = m1109b042704270427();
                }
                return new BufferedOutputStream(openFileOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Exception e22) {
            throw e22;
        }
    }
}
