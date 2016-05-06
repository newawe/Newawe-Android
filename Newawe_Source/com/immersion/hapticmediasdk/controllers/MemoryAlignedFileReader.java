package com.immersion.hapticmediasdk.controllers;

import com.immersion.content.HapticHeaderUtils;
import com.immersion.content.HeaderUtils;
import com.immersion.hapticmediasdk.models.HapticFileInformation;
import com.immersion.hapticmediasdk.models.NotEnoughHapticBytesAvailableException;
import com.immersion.hapticmediasdk.utils.FileManager;
import com.immersion.hapticmediasdk.utils.Profiler;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import rrrrrr.rcrcrr;

public class MemoryAlignedFileReader implements IHapticFileReader {
    private static final String f3714a = "MemoryAlignedFileReader";
    public static int f3715b04150415 = 10;
    public static int f3716b041504150415 = 1;
    public static int f3717b04150415 = 0;
    public static int f3718b04150415 = 2;
    private static int f3719h = 0;
    private static int f3720i = 0;
    private static final int f3721k = 1024;
    private static final int f3722l = 3072;
    private static final int f3723t = 16;
    private File f3724b;
    private FileChannel f3725c;
    private rcrcrr f3726d;
    private rcrcrr f3727e;
    private int f3728f;
    private int f3729g;
    private HapticFileInformation f3730j;
    private String f3731m;
    private FileManager f3732n;
    private HeaderUtils f3733o;
    private byte[] f3734p;
    private final Profiler f3735q;
    private int f3736r;
    private int f3737s;

    static {
        f3719h = 80;
        f3720i = 0;
    }

    public MemoryAlignedFileReader(String str, HeaderUtils headerUtils) {
        try {
            this.f3728f = 0;
            this.f3731m = null;
            this.f3732n = null;
            this.f3734p = null;
            try {
                this.f3735q = new Profiler();
                this.f3731m = str;
                int i = f3715b04150415;
                switch ((i * (f3716b041504150415 + i)) % f3718b04150415) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    default:
                        f3715b04150415 = m4302b041504150415();
                        f3717b04150415 = 92;
                        break;
                }
                this.f3733o = headerUtils;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public MemoryAlignedFileReader(String str, FileManager fileManager, int i) {
        try {
            if (((f3715b04150415 + m4301b041504150415()) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
                f3715b04150415 = m4302b041504150415();
                f3717b04150415 = m4302b041504150415();
            }
            this.f3728f = 0;
            this.f3731m = null;
            this.f3732n = null;
            this.f3734p = null;
            this.f3735q = new Profiler();
            try {
                this.f3731m = str;
                this.f3732n = fileManager;
                this.f3733o = new HapticHeaderUtils();
                this.f3728f = i;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private int m4295a(rcrcrr rrrrrr_rcrcrr, int i) {
        if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
            f3715b04150415 = 0;
            f3717b04150415 = m4302b041504150415();
        }
        try {
            try {
                return (i - rrrrrr_rcrcrr.mHapticDataOffset) % rrrrrr_rcrcrr.mMappedByteBuffer.capacity();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m4296a() {
        /*
        r5 = this;
        r0 = 0;
        r1 = 0;
        r2 = r5.f3730j;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x0008;
    L_0x0006:
        r0 = 1;
    L_0x0007:
        return r0;
    L_0x0008:
        r2 = r5.f3724b;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x001a;
    L_0x000c:
        r2 = r5.f3732n;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x006d;
    L_0x0010:
        r2 = r5.f3732n;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.f3731m;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r2 = r2.getHapticStorageFile(r3);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r5.f3724b = r2;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
    L_0x001a:
        r2 = r5.f3725c;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x002e;
    L_0x001e:
        r2 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.f3724b;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r4 = "r";
        r2.<init>(r3, r4);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r1 = r2.getChannel();	 Catch:{ FileNotFoundException -> 0x007d, Exception -> 0x0063 }
        r5.f3725c = r1;	 Catch:{ FileNotFoundException -> 0x007d, Exception -> 0x0063 }
        r1 = r2;
    L_0x002e:
        r2 = r5.f3725c;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 != 0) goto L_0x0068;
    L_0x0032:
        r1 = f3715b04150415;
        r2 = f3716b041504150415;
        r1 = r1 + r2;
        r2 = f3715b04150415;
        r1 = r1 * r2;
        r2 = f3718b04150415;
        r1 = r1 % r2;
        r2 = f3717b04150415;
        if (r1 == r2) goto L_0x0007;
    L_0x0041:
        r1 = m4302b041504150415();
        f3715b04150415 = r1;
        r1 = 96;
        f3717b04150415 = r1;
        goto L_0x0007;
    L_0x004c:
        r2 = move-exception;
    L_0x004d:
        r2 = "MemoryAlignedFileReader";
        r3 = "FileNotFoundException";
        com.immersion.hapticmediasdk.utils.Log.m1114e(r2, r3);	 Catch:{ Exception -> 0x007b }
        r2 = r5.f3732n;	 Catch:{ Exception -> 0x007b }
        r2.closeCloseable(r1);	 Catch:{ Exception -> 0x007b }
        r1 = r5.f3732n;	 Catch:{ Exception -> 0x0061 }
        r2 = r5.f3725c;	 Catch:{ Exception -> 0x0061 }
        r1.closeCloseable(r2);	 Catch:{ Exception -> 0x0061 }
        goto L_0x0007;
    L_0x0061:
        r0 = move-exception;
        throw r0;
    L_0x0063:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x0061 }
        goto L_0x0007;
    L_0x0068:
        r0 = r5.m4299b();	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        goto L_0x0007;
    L_0x006d:
        r2 = r5.f3731m;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        if (r2 == 0) goto L_0x0007;
    L_0x0071:
        r2 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r3 = r5.f3731m;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        r5.f3724b = r2;	 Catch:{ FileNotFoundException -> 0x004c, Exception -> 0x0063 }
        goto L_0x001a;
    L_0x007b:
        r0 = move-exception;
        throw r0;
    L_0x007d:
        r1 = move-exception;
        r1 = r2;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.a():boolean");
    }

    private boolean m4297a(int i) {
        if (((f3715b04150415 + m4301b041504150415()) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
            f3715b04150415 = 31;
            f3717b04150415 = 17;
        }
        try {
            return this.f3729g >= i;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m4298b(int r3) {
        /*
        r2 = this;
        r0 = 0;
        r1 = r2.f3733o;
        if (r1 == 0) goto L_0x0026;
    L_0x0005:
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0005;
            default: goto L_0x0008;
        };
    L_0x0008:
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0005;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0008;
    L_0x000c:
        r0 = f3715b04150415;
        r1 = f3716b041504150415;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f3718b04150415;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0020;
            default: goto L_0x0018;
        };
    L_0x0018:
        r0 = 53;
        f3715b04150415 = r0;
        r0 = 85;
        f3717b04150415 = r0;
    L_0x0020:
        r0 = r2.f3733o;
        r0 = r0.calculateByteOffsetIntoHapticData(r3);
    L_0x0026:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.b(int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m4299b() {
        /*
        r8 = this;
        r6 = 4;
        r0 = 0;
        r1 = -1;
        r2 = 4;
        r2 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x0080 }
        r3 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0080 }
        r2.order(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = 0;
        r2.position(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = r8.f3725c;	 Catch:{ IOException -> 0x0080 }
        r4 = 16;
        r3 = r3.read(r2, r4);	 Catch:{ IOException -> 0x0080 }
        if (r3 == r6) goto L_0x005c;
    L_0x001b:
        return r0;
    L_0x001c:
        r5 = 4;
        r4.position(r5);	 Catch:{ IOException -> 0x0080 }
        r5 = r4.getInt();	 Catch:{ IOException -> 0x0080 }
        r5 = r5 + 8;
        r5 = r5 - r3;
        r8.f3736r = r5;	 Catch:{ IOException -> 0x0080 }
        r8.f3737s = r3;	 Catch:{ IOException -> 0x0080 }
        r3 = 20;
        r4.position(r3);	 Catch:{ IOException -> 0x0080 }
        r3 = new byte[r2];	 Catch:{ IOException -> 0x0080 }
        r8.f3734p = r3;	 Catch:{ IOException -> 0x0080 }
        r3 = r4.duplicate();	 Catch:{ IOException -> 0x0080 }
        r5 = r8.f3734p;	 Catch:{ IOException -> 0x0080 }
        r6 = 0;
        r3.get(r5, r6, r2);	 Catch:{ IOException -> 0x0080 }
        r3 = r8.f3733o;	 Catch:{ IOException -> 0x0080 }
        r3.setEncryptedHSI(r4, r2);	 Catch:{ IOException -> 0x0080 }
        r2 = r8.f3733o;	 Catch:{ IOException -> 0x0080 }
        r2 = r2.calculateBlockSize();	 Catch:{ IOException -> 0x0080 }
        if (r2 <= 0) goto L_0x001b;
    L_0x004b:
        r2 = r2 * 2;
        f3720i = r2;	 Catch:{ IOException -> 0x0080 }
        r2 = r8.f3733o;	 Catch:{ IOException -> 0x0080 }
        r2 = r2.calculateBlockRate();	 Catch:{ IOException -> 0x0080 }
        if (r2 <= 0) goto L_0x001b;
    L_0x0057:
        f3719h = r2;	 Catch:{ IOException -> 0x0080 }
    L_0x0059:
        r0 = new int[r1];	 Catch:{ Exception -> 0x0085 }
        goto L_0x0059;
    L_0x005c:
        r2.flip();	 Catch:{ IOException -> 0x0080 }
        r2 = r2.getInt();	 Catch:{ IOException -> 0x0080 }
    L_0x0063:
        switch(r0) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0063;
            default: goto L_0x0066;
        };	 Catch:{ IOException -> 0x0080 }
    L_0x0066:
        switch(r0) {
            case 0: goto L_0x006a;
            case 1: goto L_0x0063;
            default: goto L_0x0069;
        };	 Catch:{ IOException -> 0x0080 }
    L_0x0069:
        goto L_0x0066;
    L_0x006a:
        r3 = r2 + 28;
        r4 = java.nio.ByteBuffer.allocate(r3);	 Catch:{ IOException -> 0x0080 }
        r5 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0080 }
        r4.order(r5);	 Catch:{ IOException -> 0x0080 }
        r5 = r8.f3725c;	 Catch:{ IOException -> 0x0080 }
        r6 = 0;
        r5 = r5.read(r4, r6);	 Catch:{ IOException -> 0x0080 }
        if (r5 == r3) goto L_0x001c;
    L_0x007f:
        goto L_0x001b;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001b;
    L_0x0085:
        r0 = move-exception;
        r0 = m4302b041504150415();
        f3715b04150415 = r0;
        r0 = 1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.b():boolean");
    }

    private static boolean m4300b(rcrcrr rrrrrr_rcrcrr, int i) {
        if (i >= rrrrrr_rcrcrr.mHapticDataOffset) {
            return false;
        }
        if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % m4303b041504150415() == f3717b04150415) {
            return true;
        }
        f3715b04150415 = 22;
        f3717b04150415 = m4302b041504150415();
        return true;
    }

    public static int m4301b041504150415() {
        return 1;
    }

    public static int m4302b041504150415() {
        return 23;
    }

    public static int m4303b041504150415() {
        return 2;
    }

    public static int m4304b041504150415() {
        return 0;
    }

    private int m4305c(int i) {
        return this.f3737s + m4298b(i);
    }

    private void m4306c() throws NotEnoughHapticBytesAvailableException, IOException {
        String str = null;
        try {
            if (this.f3727e == null) {
                while (true) {
                    try {
                        int[] iArr = new int[-1];
                    } catch (Exception e) {
                        f3715b04150415 = m4302b041504150415();
                        while (true) {
                            try {
                                str.length();
                            } catch (Exception e2) {
                                f3715b04150415 = 39;
                                while (true) {
                                    try {
                                        int[] iArr2 = new int[-1];
                                    } catch (Exception e3) {
                                        f3715b04150415 = 45;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int i = this.f3727e.mHapticDataOffset + f3721k;
            this.f3726d = this.f3727e;
            try {
                this.f3727e = m4309d(i - (f3720i / 2));
            } catch (Exception e4) {
                throw e4;
            }
        } catch (Exception e42) {
            throw e42;
        }
    }

    private static boolean m4307c(rcrcrr rrrrrr_rcrcrr, int i) {
        try {
            if (i < rrrrrr_rcrcrr.mHapticDataOffset + rrrrrr_rcrcrr.mMappedByteBuffer.capacity()) {
                return false;
            }
            if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % f3718b04150415 == m4304b041504150415()) {
                return true;
            }
            f3715b04150415 = m4302b041504150415();
            f3717b04150415 = m4302b041504150415();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    private int m4308d() {
        try {
            if (this.f3733o == null) {
                return 0;
            }
            try {
                int numChannels = this.f3733o.getNumChannels();
                if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % f3718b04150415 == f3717b04150415) {
                    return numChannels;
                }
                f3715b04150415 = m4302b041504150415();
                f3717b04150415 = m4302b041504150415();
                return numChannels;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private rcrcrr m4309d(int i) throws IOException, NotEnoughHapticBytesAvailableException {
        try {
            this.f3735q.startTiming();
            if (i < this.f3736r) {
                int i2 = this.f3737s + i;
                try {
                    int i3;
                    int f = m4313f();
                    if ((i + f3721k) + f <= this.f3736r) {
                        f += f3721k;
                        if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
                            f3715b04150415 = 31;
                            f3717b04150415 = 69;
                        }
                        i3 = f;
                    } else {
                        i3 = this.f3736r - i;
                    }
                    if (i + i3 > this.f3729g) {
                        throw new NotEnoughHapticBytesAvailableException("Not enough bytes available yet.");
                    }
                    MappedByteBuffer map = this.f3725c.map(MapMode.READ_ONLY, (long) i2, (long) i3);
                    if (map != null) {
                        map.order(ByteOrder.BIG_ENDIAN);
                        rcrcrr rrrrrr_rcrcrr = new rcrcrr();
                        rrrrrr_rcrcrr.mMappedByteBuffer = map;
                        rrrrrr_rcrcrr.mHapticDataOffset = i;
                        return rrrrrr_rcrcrr;
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            return null;
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m4310d(rrrrrr.rcrcrr r3, int r4) {
        /*
        r0 = 1;
        r1 = m4300b(r3, r4);
        if (r1 != 0) goto L_0x002c;
    L_0x0007:
        switch(r0) {
            case 0: goto L_0x0007;
            case 1: goto L_0x000e;
            default: goto L_0x000a;
        };
    L_0x000a:
        switch(r0) {
            case 0: goto L_0x0007;
            case 1: goto L_0x000e;
            default: goto L_0x000d;
        };
    L_0x000d:
        goto L_0x000a;
    L_0x000e:
        r1 = m4307c(r3, r4);
        if (r1 == 0) goto L_0x002d;
    L_0x0014:
        r1 = m4302b041504150415();
        r2 = f3716b041504150415;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f3718b04150415;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x002c;
            default: goto L_0x0022;
        };
    L_0x0022:
        r1 = m4302b041504150415();
        f3715b04150415 = r1;
        r1 = 24;
        f3717b04150415 = r1;
    L_0x002c:
        return r0;
    L_0x002d:
        r0 = 0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.d(rrrrrr.rcrcrr, int):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m4311e() {
        /*
        r4 = this;
        r3 = 1;
        r0 = "MemoryAlignedFileReader";
        r1 = "%%%%%%%%%%% logBufferState %%%%%%%%%%%";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = r4.f3726d;
        if (r0 == 0) goto L_0x0185;
    L_0x000c:
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mCurrentMMW capacity = ";
        r1 = r1.append(r2);
        r2 = r4.f3726d;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.capacity();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
    L_0x0033:
        switch(r3) {
            case 0: goto L_0x0033;
            case 1: goto L_0x003a;
            default: goto L_0x0036;
        };
    L_0x0036:
        switch(r3) {
            case 0: goto L_0x0033;
            case 1: goto L_0x003a;
            default: goto L_0x0039;
        };
    L_0x0039:
        goto L_0x0036;
    L_0x003a:
        r2 = "mCurrentMMW position = ";
        r1 = r1.append(r2);
        r2 = r4.f3726d;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.position();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mCurrentMMW remaining = ";
        r1 = r1.append(r2);
        r2 = r4.f3726d;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.remaining();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mCurrentMMW mHapticDataOffset = ";
        r1 = r1.append(r2);
        r2 = r4.f3726d;
        r2 = r2.mHapticDataOffset;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mCurrentMMW mHapticDataOffset + position = ";
        r1 = r1.append(r2);
        r2 = r4.f3726d;
        r2 = r2.mHapticDataOffset;
        r3 = r4.f3726d;
        r3 = r3.mMappedByteBuffer;
        r3 = r3.position();
        r2 = r2 + r3;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
    L_0x00b4:
        r0 = "MemoryAlignedFileReader";
        r1 = "--------------------------------------";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = r4.f3727e;
        if (r0 == 0) goto L_0x018e;
    L_0x00bf:
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mNextMMW capacity = ";
        r1 = r1.append(r2);
        r2 = r4.f3727e;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.capacity();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mNextMMW position = ";
        r1 = r1.append(r2);
        r2 = r4.f3727e;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.position();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mNextMMW remaining = ";
        r1 = r1.append(r2);
        r2 = r4.f3727e;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.remaining();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mNextMMW mHapticDataOffset = ";
        r1 = r1.append(r2);
        r2 = r4.f3727e;
        r2 = r2.mHapticDataOffset;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        r0 = "MemoryAlignedFileReader";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "mNextMMW mHapticDataOffset + position = ";
        r1 = r1.append(r2);
        r2 = f3715b04150415;
        r3 = m4301b041504150415();
        r2 = r2 + r3;
        r3 = f3715b04150415;
        r2 = r2 * r3;
        r3 = f3718b04150415;
        r2 = r2 % r3;
        r3 = f3717b04150415;
        if (r2 == r3) goto L_0x0165;
    L_0x0159:
        r2 = m4302b041504150415();
        f3715b04150415 = r2;
        r2 = m4302b041504150415();
        f3717b04150415 = r2;
    L_0x0165:
        r2 = r4.f3727e;
        r2 = r2.mHapticDataOffset;
        r3 = r4.f3727e;
        r3 = r3.mMappedByteBuffer;
        r3 = r3.position();
        r2 = r2 + r3;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
    L_0x017d:
        r0 = "MemoryAlignedFileReader";
        r1 = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        return;
    L_0x0185:
        r0 = "MemoryAlignedFileReader";
        r1 = "mCurrentMMW is null";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        goto L_0x00b4;
    L_0x018e:
        r0 = "MemoryAlignedFileReader";
        r1 = "mNextMMW is null";
        com.immersion.hapticmediasdk.utils.Log.m1113d(r0, r1);
        goto L_0x017d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.e():void");
    }

    private static boolean m4312e(rcrcrr rrrrrr_rcrcrr, int i) {
        try {
            int i2 = f3720i;
            int i3 = f3715b04150415;
            switch ((i3 * (f3716b041504150415 + i3)) % f3718b04150415) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f3715b04150415 = 4;
                    f3717b04150415 = 62;
                    break;
            }
            return m4307c(rrrrrr_rcrcrr, i2 + i);
        } catch (Exception e) {
            throw e;
        }
    }

    private int m4313f() {
        int i = 0;
        while ((i + f3721k) % (f3720i / 2) != 0) {
            i += f3723t;
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean bufferAtPlaybackPosition(int r8) {
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r7.m4296a();
        if (r2 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r7.m4298b(r8);
        r3 = r7.f3736r;
        if (r2 >= r3) goto L_0x0008;
    L_0x0011:
        r3 = r7.f3726d;
        if (r3 == 0) goto L_0x001d;
    L_0x0015:
        r3 = r7.f3726d;
        r3 = m4310d(r3, r2);
        if (r3 == 0) goto L_0x007f;
    L_0x001d:
        r3 = r7.f3727e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x0031;
    L_0x0021:
        r3 = r7.f3727e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r3 = m4310d(r3, r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 != 0) goto L_0x0031;
    L_0x0029:
        r3 = r7.f3727e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r3 = m4312e(r3, r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x007c;
    L_0x0031:
        r3 = r7.f3726d;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x003b;
    L_0x0035:
        r3 = r7.f3726d;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r3 = r3.mHapticDataOffset;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 == r2) goto L_0x0041;
    L_0x003b:
        r3 = r7.m4309d(r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r7.f3726d = r3;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
    L_0x0041:
        r3 = r7.f3727e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        if (r3 == 0) goto L_0x006d;
    L_0x0045:
        r3 = r7.f3727e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r3 = r3.mHapticDataOffset;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r4 = r2 + 1024;
        r5 = f3720i;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r5 = r5 / 2;
        r4 = r4 - r5;
        r5 = m4302b041504150415();
        r6 = f3716b041504150415;
        r5 = r5 + r6;
        r6 = m4302b041504150415();
        r5 = r5 * r6;
        r6 = f3718b04150415;
        r5 = r5 % r6;
        r6 = f3717b04150415;
        if (r5 == r6) goto L_0x006b;
    L_0x0063:
        r5 = 98;
        f3715b04150415 = r5;
        r5 = 73;
        f3717b04150415 = r5;
    L_0x006b:
        if (r3 == r4) goto L_0x007a;
    L_0x006d:
        r2 = r2 + 1024;
        r3 = f3720i;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r3 = r3 / 2;
        r2 = r2 - r3;
        r2 = r7.m4309d(r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
        r7.f3727e = r2;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
    L_0x007a:
        r0 = r1;
        goto L_0x0008;
    L_0x007c:
        r7.m4306c();	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x0096, IOException -> 0x0093 }
    L_0x007f:
        r0 = r7.f3726d;
        if (r0 == 0) goto L_0x0090;
    L_0x0083:
        r0 = r7.f3726d;
        r0 = r0.mMappedByteBuffer;
        r3 = r7.f3726d;
        r2 = r7.m4295a(r3, r2);
        r0.position(r2);
    L_0x0090:
        r0 = r1;
        goto L_0x0008;
    L_0x0093:
        r1 = move-exception;
        goto L_0x0008;
    L_0x0096:
        r1 = move-exception;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.bufferAtPlaybackPosition(int):boolean");
    }

    public void close() {
        this.f3732n.closeCloseable(this.f3725c);
        this.f3733o.dispose();
    }

    public long getBlockOffset(long j) {
        long j2 = j % ((long) f3719h);
        int i = f3715b04150415;
        switch ((i * (f3716b041504150415 + i)) % f3718b04150415) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f3715b04150415 = m4302b041504150415();
                f3717b04150415 = 40;
                break;
        }
        return (j2 * 16) / ((long) f3719h);
    }

    public int getBlockSizeMS() {
        int i = f3719h;
        if (((f3715b04150415 + f3716b041504150415) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
            f3715b04150415 = 57;
            f3717b04150415 = 94;
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBufferForPlaybackPosition(int r8) throws com.immersion.hapticmediasdk.models.NotEnoughHapticBytesAvailableException {
        /*
        r7 = this;
        r0 = 0;
        r3 = 0;
        r1 = r7.f3726d;
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = r7.m4298b(r8);
        r1 = r7.f3736r;
        r4 = f3720i;
        r1 = r1 - r4;
        if (r2 >= r1) goto L_0x0006;
    L_0x0012:
        r1 = f3720i;	 Catch:{ Exception -> 0x00b0 }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x00b0 }
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.remaining();	 Catch:{ Exception -> 0x00b0 }
        r5 = f3720i;	 Catch:{ Exception -> 0x00b0 }
        if (r4 >= r5) goto L_0x0025;
    L_0x0022:
        r7.m4306c();	 Catch:{ Exception -> 0x00b0 }
    L_0x0025:
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
        r5 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r5 = r5.mHapticDataOffset;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4 + r5;
        if (r4 < r2) goto L_0x0036;
    L_0x0034:
        if (r4 <= r2) goto L_0x004a;
    L_0x0036:
        r2 = r2 - r4;
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
        r2 = r2 + r4;
        if (r2 >= 0) goto L_0x009b;
    L_0x0042:
        r2 = r3;
    L_0x0043:
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4.position(r2);	 Catch:{ Exception -> 0x00b0 }
    L_0x004a:
        r2 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.remaining();	 Catch:{ Exception -> 0x00b0 }
        r4 = f3715b04150415;
        r5 = f3716b041504150415;
        r5 = r5 + r4;
        r4 = r4 * r5;
        r5 = m4303b041504150415();
        r4 = r4 % r5;
        switch(r4) {
            case 0: goto L_0x006c;
            default: goto L_0x0060;
        };
    L_0x0060:
        r4 = m4302b041504150415();
        f3715b04150415 = r4;
        r4 = m4302b041504150415();
        f3717b04150415 = r4;
    L_0x006c:
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r5 = 0;
        r6 = f3720i;	 Catch:{ Exception -> 0x00b0 }
        if (r2 >= r6) goto L_0x0098;
    L_0x0075:
        r4.get(r1, r5, r2);	 Catch:{ Exception -> 0x00b0 }
        r2 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.position();	 Catch:{ Exception -> 0x00b0 }
    L_0x0084:
        switch(r3) {
            case 0: goto L_0x008c;
            case 1: goto L_0x0084;
            default: goto L_0x0087;
        };	 Catch:{ Exception -> 0x00b0 }
    L_0x0087:
        r5 = 1;
        switch(r5) {
            case 0: goto L_0x0084;
            case 1: goto L_0x008c;
            default: goto L_0x008b;
        };	 Catch:{ Exception -> 0x00b0 }
    L_0x008b:
        goto L_0x0087;
    L_0x008c:
        r3 = f3720i;	 Catch:{ Exception -> 0x00b0 }
        r3 = r3 / 2;
        r3 = r4 - r3;
        r2.position(r3);	 Catch:{ Exception -> 0x00b0 }
        r0 = r1;
        goto L_0x0006;
    L_0x0098:
        r2 = f3720i;	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0075;
    L_0x009b:
        r4 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r4 = r4.limit();	 Catch:{ Exception -> 0x00b0 }
        if (r4 >= r2) goto L_0x0043;
    L_0x00a5:
        r2 = r7.f3726d;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x00b0 }
        r2 = r2.limit();	 Catch:{ Exception -> 0x00b0 }
        r2 = r2 + -1;
        goto L_0x0043;
    L_0x00b0:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryAlignedFileReader.getBufferForPlaybackPosition(int):byte[]");
    }

    public byte[] getEncryptedHapticHeader() {
        return this.f3734p;
    }

    public int getHapticBlockIndex(long j) {
        try {
            int b = m4298b((int) j);
            int i = this.f3728f;
            if (((f3715b04150415 + m4301b041504150415()) * f3715b04150415) % f3718b04150415 != f3717b04150415) {
                f3715b04150415 = 2;
                f3717b04150415 = m4302b041504150415();
            }
            if (i == 2) {
                return b / f3723t;
            }
            if (this.f3728f < 3) {
                return 0;
            }
            try {
                return b / (m4308d() * f3723t);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public HapticFileInformation getHapticFileInformation() {
        return this.f3730j;
    }

    public void setBlockSizeMS(int i) {
        int i2 = f3715b04150415;
        switch ((i2 * (m4301b041504150415() + i2)) % f3718b04150415) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f3715b04150415 = m4302b041504150415();
                f3717b04150415 = m4302b041504150415();
                break;
        }
        try {
            f3719h = i;
        } catch (Exception e) {
            throw e;
        }
    }

    public void setBytesAvailable(int i) {
        this.f3729g = i;
        if (this.f3729g <= 0) {
            this.f3729g = i;
            m4296a();
        }
    }
}
