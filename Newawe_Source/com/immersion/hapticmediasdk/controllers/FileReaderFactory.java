package com.immersion.hapticmediasdk.controllers;

import com.google.android.gms.common.ConnectionResult;
import com.immersion.hapticmediasdk.utils.FileManager;
import com.immersion.hapticmediasdk.utils.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class FileReaderFactory {
    private static final String f890a = "FileReaderFactory";
    public static int f891b0446044604460446 = 2;
    public static int f892b0446044604460446 = 0;
    public static int f893b044604460446 = 72;
    public static int f894b044604460446 = 1;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileReaderFactory() {
        /*
        r2 = this;
        r0 = f893b044604460446;
        r1 = m1017b044604460446();
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f891b0446044604460446;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001a;
            default: goto L_0x000e;
        };
    L_0x000e:
        r0 = m1018b04460446();
        f893b044604460446 = r0;
        r0 = m1018b04460446();
        f891b0446044604460446 = r0;
    L_0x001a:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0023;
            case 1: goto L_0x001a;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x001a;
            case 1: goto L_0x0023;
            default: goto L_0x0022;
        };
    L_0x0022:
        goto L_0x001e;
    L_0x0023:
        r2.<init>();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.FileReaderFactory.<init>():void");
    }

    private static int m1013a(String str, FileManager fileManager) {
        File hapticStorageFile;
        FileChannel fileChannel = null;
        String str2 = null;
        if (fileManager != null) {
            try {
                hapticStorageFile = fileManager.getHapticStorageFile(str);
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    return 0;
                } catch (Exception e2) {
                    throw e2;
                }
            }
        } else if (str == null) {
            return 0;
        } else {
            hapticStorageFile = new File(str);
        }
        if (hapticStorageFile.length() == 0) {
            return -1;
        }
        if (null == null) {
            fileChannel = new RandomAccessFile(hapticStorageFile, "r").getChannel();
        }
        if (fileChannel == null) {
            return 0;
        }
        int a = m1014a(fileChannel);
        fileChannel.close();
        while (true) {
            try {
                str2.length();
            } catch (Exception e3) {
                f893b044604460446 = m1018b04460446();
                return a;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m1014a(java.nio.channels.FileChannel r6) {
        /*
        r4 = 4;
        r0 = 0;
        r1 = 4;
        r1 = java.nio.ByteBuffer.allocate(r1);	 Catch:{ IOException -> 0x005d }
        r2 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x005d }
        r1.order(r2);	 Catch:{ IOException -> 0x005d }
        r2 = m1018b04460446();
        r3 = m1017b044604460446();
        r2 = r2 + r3;
        r3 = m1018b04460446();
        r2 = r2 * r3;
        r3 = m1016b0446044604460446();
        r2 = r2 % r3;
        r3 = f892b0446044604460446;
        if (r2 == r3) goto L_0x002d;
    L_0x0023:
        r2 = m1018b04460446();
        f893b044604460446 = r2;
        r2 = 93;
        f892b0446044604460446 = r2;
    L_0x002d:
        r2 = 0;
        r1.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = 16;
        r2 = r6.read(r1, r2);	 Catch:{ IOException -> 0x005d }
        if (r2 == r4) goto L_0x0062;
    L_0x0039:
        return r0;
    L_0x003a:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x003a;
            default: goto L_0x003d;
        };	 Catch:{ IOException -> 0x005d }
    L_0x003d:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x003a;
            default: goto L_0x0040;
        };	 Catch:{ IOException -> 0x005d }
    L_0x0040:
        goto L_0x003d;
    L_0x0041:
        r2 = 4;
        r3.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = r3.getInt();	 Catch:{ IOException -> 0x005d }
        r2 = r2 + 8;
        r2 = 20;
        r3.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = new com.immersion.content.HapticHeaderUtils;	 Catch:{ IOException -> 0x005d }
        r2.<init>();	 Catch:{ IOException -> 0x005d }
        r2.setEncryptedHSI(r3, r1);	 Catch:{ IOException -> 0x005d }
        r0 = r2.getMajorVersionNumber();	 Catch:{ IOException -> 0x005d }
        goto L_0x0039;
    L_0x005d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0039;
    L_0x0062:
        r1.flip();	 Catch:{ IOException -> 0x005d }
        r1 = r1.getInt();	 Catch:{ IOException -> 0x005d }
        r2 = r1 + 28;
        r3 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x005d }
        r4 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x005d }
        r3.order(r4);	 Catch:{ IOException -> 0x005d }
        r4 = 0;
        r4 = r6.read(r3, r4);	 Catch:{ IOException -> 0x005d }
        if (r4 == r2) goto L_0x003a;
    L_0x007c:
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.FileReaderFactory.a(java.nio.channels.FileChannel):int");
    }

    public static int m1015b044604460446() {
        return 0;
    }

    public static int m1016b0446044604460446() {
        return 2;
    }

    public static int m1017b044604460446() {
        return 1;
    }

    public static int m1018b04460446() {
        return 47;
    }

    public static IHapticFileReader getHapticFileReaderInstance(String str, FileManager fileManager) {
        try {
            switch (m1013a(str, fileManager)) {
                case DTDGrammar.TOP_LEVEL_SCOPE /*-1*/:
                    Log.m1115i(f890a, "Can't retrieve Major version! Not enough bytes available yet.");
                    return null;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    return new MemoryMappedFileReader(str, fileManager);
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    return new MemoryAlignedFileReader(str, fileManager, 2);
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    return new MemoryAlignedFileReader(str, fileManager, 3);
                default:
                    Log.m1114e(f890a, "Unsupported HAPT file version");
                    while (true) {
                        switch (null) {
                            case DurationDV.DURATION_TYPE /*0*/:
                                return null;
                            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                                break;
                            default:
                                while (true) {
                                    switch (null) {
                                        case DurationDV.DURATION_TYPE /*0*/:
                                            return null;
                                        case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                                            break;
                                        default:
                                    }
                                }
                        }
                    }
            }
        } catch (Error e) {
            e.printStackTrace();
            if (((f893b044604460446 + f894b044604460446) * f893b044604460446) % f891b0446044604460446 != m1015b044604460446()) {
                return null;
            }
            f893b044604460446 = m1018b04460446();
            f894b044604460446 = m1018b04460446();
            return null;
        }
        e.printStackTrace();
        if (((f893b044604460446 + f894b044604460446) * f893b044604460446) % f891b0446044604460446 != m1015b044604460446()) {
            return null;
        }
        f893b044604460446 = m1018b04460446();
        f894b044604460446 = m1018b04460446();
        return null;
    }
}
