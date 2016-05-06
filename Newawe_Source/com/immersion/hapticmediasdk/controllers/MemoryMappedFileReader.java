package com.immersion.hapticmediasdk.controllers;

import com.immersion.hapticmediasdk.models.HapticFileInformation;
import com.immersion.hapticmediasdk.models.NotEnoughHapticBytesAvailableException;
import com.immersion.hapticmediasdk.utils.FileManager;
import com.immersion.hapticmediasdk.utils.Log;
import com.immersion.hapticmediasdk.utils.Profiler;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;
import rrrrrr.ccrrrr;

public class MemoryMappedFileReader implements IHapticFileReader {
    private static final String f3738a = "MemoryMappedFileReader";
    public static int f3739b044A044A = 1;
    public static int f3740b044A044A = 93;
    public static int f3741b044A = 0;
    public static int f3742b044A = 2;
    private static int f3743g = 0;
    private static int f3744h = 0;
    private static final int f3745j = 4096;
    private static final int f3746k = 12288;
    private File f3747b;
    private FileChannel f3748c;
    private ccrrrr f3749d;
    private ccrrrr f3750e;
    private int f3751f;
    private HapticFileInformation f3752i;
    private String f3753l;
    private final Profiler f3754m;
    private FileManager f3755n;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r2 = 0;
    L_0x0001:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0009;
            default: goto L_0x0005;
        };
    L_0x0005:
        switch(r2) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0001;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0005;
    L_0x0009:
        r0 = 40;
        f3743g = r0;
        r0 = f3740b044A044A;
        r1 = f3739b044A044A;
        r0 = r0 + r1;
        r1 = f3740b044A044A;
        r0 = r0 * r1;
        r1 = f3742b044A;
        r0 = r0 % r1;
        r1 = f3741b044A;
        if (r0 == r1) goto L_0x0024;
    L_0x001c:
        r0 = 55;
        f3740b044A044A = r0;
        r0 = 34;
        f3741b044A = r0;
    L_0x0024:
        f3744h = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.<clinit>():void");
    }

    public MemoryMappedFileReader(String str, FileManager fileManager) {
        try {
            if (((m4322b044A() + f3739b044A044A) * m4322b044A()) % f3742b044A != f3741b044A) {
                f3741b044A = m4322b044A();
            }
            this.f3754m = new Profiler();
            try {
                this.f3753l = str;
                this.f3755n = fileManager;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m4314a(rrrrrr.ccrrrr r4, int r5) {
        /*
        r3 = this;
        r1 = 1;
        r0 = r4.mHapticDataOffset;
    L_0x0003:
        switch(r1) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r1) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r0 = r5 - r0;
        r1 = f3740b044A044A;
        r2 = m4321b044A044A();
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f3742b044A;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0026;
            default: goto L_0x001a;
        };
    L_0x001a:
        r1 = m4322b044A();
        f3740b044A044A = r1;
        r1 = m4322b044A();
        f3741b044A = r1;
    L_0x0026:
        r1 = r4.mMappedByteBuffer;
        r1 = r1.capacity();
        r0 = r0 % r1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.a(rrrrrr.ccrrrr, int):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m4315a() {
        /*
        r5 = this;
        r0 = 0;
        r2 = 0;
        r1 = r5.f3752i;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0033;
    L_0x0006:
        r0 = 1;
    L_0x0007:
        return r0;
    L_0x0008:
        r1 = r5.f3747b;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 != 0) goto L_0x0016;
    L_0x000c:
        r1 = r5.f3755n;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r3 = r5.f3753l;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r1.getHapticStorageFile(r3);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r5.f3747b = r1;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
    L_0x0016:
        r1 = r5.f3748c;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 != 0) goto L_0x002a;
    L_0x001a:
        r3 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r5.f3747b;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r4 = "r";
        r3.<init>(r1, r4);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        r1 = r3.getChannel();	 Catch:{ FileNotFoundException -> 0x0058, Exception -> 0x0053 }
        r5.f3748c = r1;	 Catch:{ FileNotFoundException -> 0x0058, Exception -> 0x0053 }
        r2 = r3;
    L_0x002a:
        r1 = r5.f3748c;	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0007;
    L_0x002e:
        r0 = r5.m4318b();	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        goto L_0x0007;
    L_0x0033:
        r1 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
        r1 = r5.m4316a(r1);	 Catch:{ FileNotFoundException -> 0x003c, Exception -> 0x0053 }
        if (r1 == 0) goto L_0x0008;
    L_0x003b:
        goto L_0x0007;
    L_0x003c:
        r1 = move-exception;
    L_0x003d:
        r3 = "MemoryMappedFileReader";
        r1 = r1.getMessage();
        com.immersion.hapticmediasdk.utils.Log.m1114e(r3, r1);
        r1 = r5.f3755n;
        r1.closeCloseable(r2);
        r1 = r5.f3755n;
        r2 = r5.f3748c;
        r1.closeCloseable(r2);
        goto L_0x0007;
    L_0x0053:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0058:
        r1 = move-exception;
        r2 = r3;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.a():boolean");
    }

    private boolean m4316a(int i) {
        if (this.f3751f < i) {
            return false;
        }
        if (((m4322b044A() + f3739b044A044A) * m4322b044A()) % f3742b044A == f3741b044A) {
            return true;
        }
        f3740b044A044A = 58;
        f3741b044A = 75;
        return true;
    }

    private int m4317b(int i) {
        int sampleHertz = i / (DateUtils.MILLIS_IN_SECOND / this.f3752i.getSampleHertz());
        if (((f3740b044A044A + f3739b044A044A) * f3740b044A044A) % f3742b044A != f3741b044A) {
            f3740b044A044A = 77;
            f3741b044A = 64;
        }
        int bitsPerSample = this.f3752i.getBitsPerSample();
        int numberOfChannels = this.f3752i.getNumberOfChannels();
        float f = (float) ((bitsPerSample * numberOfChannels) / 8);
        bitsPerSample = (int) f;
        if (((float) (bitsPerSample * numberOfChannels)) / 8.0f > f) {
            bitsPerSample++;
        }
        return bitsPerSample * sampleHertz;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m4318b() {
        /*
        r8 = this;
        r2 = 1;
        r6 = 4;
        r1 = 0;
        r0 = 4;
        r0 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ IOException -> 0x0105 }
        r3 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0105 }
        r0.order(r3);	 Catch:{ IOException -> 0x0105 }
        r3 = 0;
        r0.position(r3);	 Catch:{ IOException -> 0x0105 }
        r3 = r8.f3748c;	 Catch:{ IOException -> 0x0105 }
        r4 = 16;
        r3 = r3.read(r0, r4);	 Catch:{ IOException -> 0x0105 }
        if (r3 == r6) goto L_0x001c;
    L_0x001b:
        return r1;
    L_0x001c:
        r0.flip();	 Catch:{ IOException -> 0x0105 }
        r0 = r0.getInt();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 28;
        r3 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ IOException -> 0x0105 }
        r4 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x0105 }
        r3.order(r4);	 Catch:{ IOException -> 0x0105 }
        r4 = r8.f3748c;	 Catch:{ IOException -> 0x0105 }
        r6 = 0;
        r4 = r4.read(r3, r6);	 Catch:{ IOException -> 0x0105 }
    L_0x0036:
        switch(r1) {
            case 0: goto L_0x003d;
            case 1: goto L_0x0036;
            default: goto L_0x0039;
        };	 Catch:{ IOException -> 0x0105 }
    L_0x0039:
        switch(r2) {
            case 0: goto L_0x0036;
            case 1: goto L_0x003d;
            default: goto L_0x003c;
        };	 Catch:{ IOException -> 0x0105 }
    L_0x003c:
        goto L_0x0039;
    L_0x003d:
        if (r4 != r0) goto L_0x001b;
    L_0x003f:
        r3.flip();	 Catch:{ IOException -> 0x0105 }
        r4 = new com.immersion.hapticmediasdk.models.HapticFileInformation$Builder;	 Catch:{ IOException -> 0x0105 }
        r4.<init>();	 Catch:{ IOException -> 0x0105 }
        r0 = r8.f3747b;	 Catch:{ IOException -> 0x0105 }
        r0 = r0.getAbsolutePath();	 Catch:{ IOException -> 0x0105 }
        r4.setFilePath(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 4;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 8;
        r4.setTotalFileLength(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 20;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.setMajorVersion(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.setMinorVersion(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.setEncoding(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = 24;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r4.setSampleHertz(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r5 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r5 = r5 << 8;
        r0 = r0 | r5;
        r4.setBitsPerSample(r0);	 Catch:{ IOException -> 0x0105 }
        r5 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.setNumberOfChannels(r5);	 Catch:{ IOException -> 0x0105 }
        r6 = new int[r5];	 Catch:{ IOException -> 0x0105 }
        r0 = r1;
    L_0x009b:
        if (r0 >= r5) goto L_0x00a6;
    L_0x009d:
        r7 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r6[r0] = r7;	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 1;
        goto L_0x009b;
    L_0x00a6:
        r4.setActuatorArray(r6);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.get();	 Catch:{ IOException -> 0x0105 }
        r4.setCompressionScheme(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.position();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 + 4;
        r3.position(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.getInt();	 Catch:{ IOException -> 0x0105 }
        r4.setHapticDataLength(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r3.position();	 Catch:{ IOException -> 0x0105 }
        r4.setHapticDataStartByteOffset(r0);	 Catch:{ IOException -> 0x0105 }
        r0 = r4.build();	 Catch:{ IOException -> 0x0105 }
        r8.f3752i = r0;	 Catch:{ IOException -> 0x0105 }
        r0 = f3743g;	 Catch:{ IOException -> 0x0105 }
        r3 = r8.f3752i;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.getSampleHertz();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r0 = r0 / 1000;
        r3 = f3740b044A044A;
        r4 = m4321b044A044A();
        r4 = r4 + r3;
        r3 = r3 * r4;
        r4 = f3742b044A;
        r3 = r3 % r4;
        switch(r3) {
            case 0: goto L_0x00f0;
            default: goto L_0x00e6;
        };
    L_0x00e6:
        r3 = m4322b044A();
        f3740b044A044A = r3;
        r3 = 51;
        f3741b044A = r3;
    L_0x00f0:
        r3 = r8.f3752i;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.getBitsPerSample();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r3 = r8.f3752i;	 Catch:{ IOException -> 0x0105 }
        r3 = r3.getNumberOfChannels();	 Catch:{ IOException -> 0x0105 }
        r0 = r0 * r3;
        r0 = r0 / 8;
        f3744h = r0;	 Catch:{ IOException -> 0x0105 }
        r1 = r2;
        goto L_0x001b;
    L_0x0105:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.b():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m4319b(rrrrrr.ccrrrr r3, int r4) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = r3.mHapticDataOffset;
        if (r4 >= r2) goto L_0x001f;
    L_0x0006:
        r1 = f3740b044A044A;
        r2 = f3739b044A044A;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = f3742b044A;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x001e;
            default: goto L_0x0012;
        };
    L_0x0012:
        r1 = m4322b044A();
        f3740b044A044A = r1;
        r1 = m4322b044A();
        f3741b044A = r1;
    L_0x001e:
        return r0;
    L_0x001f:
        switch(r1) {
            case 0: goto L_0x0026;
            case 1: goto L_0x001f;
            default: goto L_0x0022;
        };
    L_0x0022:
        switch(r0) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0026;
            default: goto L_0x0025;
        };
    L_0x0025:
        goto L_0x0022;
    L_0x0026:
        r0 = r1;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.b(rrrrrr.ccrrrr, int):boolean");
    }

    public static int m4320b044A044A044A() {
        return 0;
    }

    public static int m4321b044A044A() {
        return 1;
    }

    public static int m4322b044A() {
        return 73;
    }

    public static int m4323b044A044A044A() {
        return 2;
    }

    private int m4324c(int i) {
        try {
            HapticFileInformation hapticFileInformation = this.f3752i;
            if (((f3740b044A044A + f3739b044A044A) * f3740b044A044A) % f3742b044A != f3741b044A) {
                f3740b044A044A = 98;
                f3741b044A = 21;
            }
            try {
                return hapticFileInformation.getHapticDataStartByteOffset() + m4317b(i);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private void m4325c() throws NotEnoughHapticBytesAvailableException, IOException {
        try {
            if (this.f3750e != null) {
                int i = this.f3750e.mHapticDataOffset + f3745j;
                try {
                    this.f3749d = this.f3750e;
                    if (((m4322b044A() + f3739b044A044A) * m4322b044A()) % m4323b044A044A044A() != m4320b044A044A044A()) {
                        f3740b044A044A = 80;
                        f3741b044A = 68;
                    }
                    this.f3750e = m4327d(i);
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    private static boolean m4326c(ccrrrr rrrrrr_ccrrrr, int i) {
        if (i < rrrrrr_ccrrrr.mHapticDataOffset + rrrrrr_ccrrrr.mMappedByteBuffer.capacity()) {
            return false;
        }
        if (((f3740b044A044A + f3739b044A044A) * f3740b044A044A) % f3742b044A == f3741b044A) {
            return true;
        }
        f3740b044A044A = m4322b044A();
        f3741b044A = m4322b044A();
        return true;
    }

    private ccrrrr m4327d(int i) throws IOException, NotEnoughHapticBytesAvailableException {
        this.f3754m.startTiming();
        if (i < this.f3752i.getHapticDataLength()) {
            int hapticDataStartByteOffset = this.f3752i.getHapticDataStartByteOffset() + i;
            int hapticDataLength = i + f3745j <= this.f3752i.getHapticDataLength() ? f3745j : this.f3752i.getHapticDataLength() - i;
            if (i + hapticDataLength > this.f3751f) {
                throw new NotEnoughHapticBytesAvailableException("Not enough bytes available yet.");
            }
            MappedByteBuffer map = this.f3748c.map(MapMode.READ_ONLY, (long) hapticDataStartByteOffset, (long) hapticDataLength);
            if (map != null) {
                map.order(ByteOrder.LITTLE_ENDIAN);
                ccrrrr rrrrrr_ccrrrr = new ccrrrr();
                rrrrrr_ccrrrr.mMappedByteBuffer = map;
                rrrrrr_ccrrrr.mHapticDataOffset = i;
                return rrrrrr_ccrrrr;
            }
        }
        return null;
    }

    private void m4328d() {
        Log.m1113d(f3738a, "%%%%%%%%%%% logBufferState %%%%%%%%%%%");
        if (this.f3749d != null) {
            Log.m1113d(f3738a, "mCurrentMMW capacity = " + this.f3749d.mMappedByteBuffer.capacity());
            Log.m1113d(f3738a, "mCurrentMMW position = " + this.f3749d.mMappedByteBuffer.position());
            Log.m1113d(f3738a, "mCurrentMMW remaining = " + this.f3749d.mMappedByteBuffer.remaining());
            Log.m1113d(f3738a, "mCurrentMMW mHapticDataOffset = " + this.f3749d.mHapticDataOffset);
            String str = f3738a;
            StringBuilder append = new StringBuilder().append("mCurrentMMW mHapticDataOffset + position = ");
            ccrrrr rrrrrr_ccrrrr = this.f3749d;
            if (((m4322b044A() + f3739b044A044A) * m4322b044A()) % m4323b044A044A044A() != m4320b044A044A044A()) {
                f3740b044A044A = m4322b044A();
                f3741b044A = m4322b044A();
            }
            Log.m1113d(str, append.append(rrrrrr_ccrrrr.mHapticDataOffset + this.f3749d.mMappedByteBuffer.position()).toString());
        } else {
            Log.m1113d(f3738a, "mCurrentMMW is null");
        }
        Log.m1113d(f3738a, "--------------------------------------");
        if (this.f3750e != null) {
            Log.m1113d(f3738a, "mNextMMW capacity = " + this.f3750e.mMappedByteBuffer.capacity());
            Log.m1113d(f3738a, "mNextMMW position = " + this.f3750e.mMappedByteBuffer.position());
            Log.m1113d(f3738a, "mNextMMW remaining = " + this.f3750e.mMappedByteBuffer.remaining());
            Log.m1113d(f3738a, "mNextMMW mHapticDataOffset = " + this.f3750e.mHapticDataOffset);
            Log.m1113d(f3738a, "mNextMMW mHapticDataOffset + position = " + (this.f3750e.mHapticDataOffset + this.f3750e.mMappedByteBuffer.position()));
        } else {
            Log.m1113d(f3738a, "mNextMMW is null");
        }
        Log.m1113d(f3738a, "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    private static boolean m4329d(ccrrrr rrrrrr_ccrrrr, int i) {
        try {
            if (!m4319b(rrrrrr_ccrrrr, i)) {
                boolean c = m4326c(rrrrrr_ccrrrr, i);
                if (((f3740b044A044A + f3739b044A044A) * f3740b044A044A) % f3742b044A != f3741b044A) {
                    f3740b044A044A = 52;
                    f3741b044A = 7;
                }
                if (!c) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    private static boolean m4330e(ccrrrr rrrrrr_ccrrrr, int i) {
        int i2 = f3740b044A044A;
        switch ((i2 * (m4321b044A044A() + i2)) % f3742b044A) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f3740b044A044A = 57;
                f3741b044A = 27;
                break;
        }
        try {
            try {
                return m4326c(rrrrrr_ccrrrr, f3744h + i);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean bufferAtPlaybackPosition(int r6) {
        /*
        r5 = this;
        r1 = 1;
        r0 = 0;
        r2 = -1;
        r3 = r5.m4315a();	 Catch:{ Exception -> 0x0065 }
        if (r3 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = r5.m4317b(r6);	 Catch:{ Exception -> 0x0065 }
        r4 = r5.f3749d;	 Catch:{ Exception -> 0x0065 }
        if (r4 == 0) goto L_0x001a;
    L_0x0012:
        r4 = r5.f3749d;	 Catch:{ Exception -> 0x0065 }
        r4 = m4329d(r4, r3);	 Catch:{ Exception -> 0x0065 }
        if (r4 == 0) goto L_0x006b;
    L_0x001a:
        r4 = r5.f3750e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r4 == 0) goto L_0x002e;
    L_0x001e:
        r4 = r5.f3750e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r4 = m4329d(r4, r3);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r4 != 0) goto L_0x002e;
    L_0x0026:
        r4 = r5.f3750e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r4 = m4330e(r4, r3);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r4 == 0) goto L_0x0054;
    L_0x002e:
        r2 = r5.f3749d;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r2 == 0) goto L_0x0038;
    L_0x0032:
        r2 = r5.f3749d;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r2 = r2.mHapticDataOffset;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r2 == r3) goto L_0x003e;
    L_0x0038:
        r2 = r5.m4327d(r3);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r5.f3749d = r2;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
    L_0x003e:
        r2 = r5.f3750e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        if (r2 == 0) goto L_0x004a;
    L_0x0042:
        r2 = r5.f3750e;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r2 = r2.mHapticDataOffset;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r4 = r3 + 4096;
        if (r2 == r4) goto L_0x0052;
    L_0x004a:
        r2 = r3 + 4096;
        r2 = r5.m4327d(r2);	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
        r5.f3750e = r2;	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
    L_0x0052:
        r0 = r1;
        goto L_0x0009;
    L_0x0054:
        r5.m4325c();	 Catch:{ NotEnoughHapticBytesAvailableException -> 0x005a, IOException -> 0x007e }
    L_0x0057:
        r0 = new int[r2];	 Catch:{ Exception -> 0x0067 }
        goto L_0x0057;
    L_0x005a:
        r1 = move-exception;
        r2 = "MemoryMappedFileReader";
        r1 = r1.getMessage();	 Catch:{ Exception -> 0x0065 }
        com.immersion.hapticmediasdk.utils.Log.m1117w(r2, r1);	 Catch:{ Exception -> 0x0065 }
        goto L_0x0009;
    L_0x0065:
        r0 = move-exception;
        throw r0;
    L_0x0067:
        r0 = move-exception;
        r0 = 5;
        f3740b044A044A = r0;
    L_0x006b:
        r0 = r5.f3749d;	 Catch:{ Exception -> 0x0083 }
        if (r0 == 0) goto L_0x007c;
    L_0x006f:
        r0 = r5.f3749d;	 Catch:{ Exception -> 0x0065 }
        r0 = r0.mMappedByteBuffer;	 Catch:{ Exception -> 0x0065 }
        r2 = r5.f3749d;	 Catch:{ Exception -> 0x0083 }
        r2 = r5.m4314a(r2, r3);	 Catch:{ Exception -> 0x0083 }
        r0.position(r2);	 Catch:{ Exception -> 0x0083 }
    L_0x007c:
        r0 = r1;
        goto L_0x0009;
    L_0x007e:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x0083 }
        goto L_0x0009;
    L_0x0083:
        r0 = move-exception;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.bufferAtPlaybackPosition(int):boolean");
    }

    public void close() {
        int i = f3740b044A044A;
        switch ((i * (m4321b044A044A() + i)) % f3742b044A) {
            case DurationDV.DURATION_TYPE /*0*/:
                break;
            default:
                f3740b044A044A = m4322b044A();
                f3741b044A = 35;
                break;
        }
        try {
            this.f3755n.closeCloseable(this.f3748c);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getBlockOffset(long r3) {
        /*
        r2 = this;
    L_0x0000:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0000;
            default: goto L_0x0004;
        };
    L_0x0004:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0000;
            case 1: goto L_0x0009;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0004;
    L_0x0009:
        r0 = f3740b044A044A;
        r1 = f3739b044A044A;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f3742b044A;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001d;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = 89;
        f3740b044A044A = r0;
        r0 = 47;
        f3741b044A = r0;
    L_0x001d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.getBlockOffset(long):long");
    }

    public int getBlockSizeMS() {
        try {
            int i = f3743g;
            if (((f3740b044A044A + f3739b044A044A) * f3740b044A044A) % f3742b044A != f3741b044A) {
                f3740b044A044A = m4322b044A();
                f3741b044A = m4322b044A();
            }
            return i;
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBufferForPlaybackPosition(int r7) throws com.immersion.hapticmediasdk.models.NotEnoughHapticBytesAvailableException {
        /*
        r6 = this;
        r0 = 0;
        r4 = 0;
        r1 = r6.f3749d;
        if (r1 != 0) goto L_0x0057;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = r6.f3749d;
        r2 = r2.mMappedByteBuffer;
        r2 = r2.position();
        r1 = r1 + r2;
        r2 = r6.f3752i;
        r2 = r2.getHapticDataLength();
        if (r1 >= r2) goto L_0x0006;
    L_0x0018:
        r1 = f3744h;	 Catch:{ Exception -> 0x007c }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x007c }
        r2 = f3744h;	 Catch:{ Exception -> 0x007c }
        r3 = r6.f3749d;	 Catch:{ Exception -> 0x007c }
        r3 = r3.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r3 = r3.remaining();	 Catch:{ Exception -> 0x007c }
        if (r2 < r3) goto L_0x008a;
    L_0x0028:
        r2 = r6.f3749d;	 Catch:{ Exception -> 0x007c }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r3 = r2.remaining();	 Catch:{ Exception -> 0x007c }
        r2 = f3744h;	 Catch:{ Exception -> 0x007c }
        r2 = r2 - r3;
        r4 = r6.f3749d;	 Catch:{ Exception -> 0x007c }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r5 = 0;
        r4.get(r1, r5, r3);	 Catch:{ Exception -> 0x007c }
        if (r2 <= 0) goto L_0x0052;
    L_0x003d:
        r4 = r6.f3750e;	 Catch:{ Exception -> 0x007c }
        if (r4 == 0) goto L_0x0052;
    L_0x0041:
        r4 = r6.f3750e;	 Catch:{ Exception -> 0x007c }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r4 = r4.remaining();	 Catch:{ Exception -> 0x007c }
        if (r4 < r2) goto L_0x0081;
    L_0x004b:
        r4 = r6.f3750e;	 Catch:{ Exception -> 0x007c }
        r4 = r4.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r4.get(r1, r3, r2);	 Catch:{ Exception -> 0x007c }
    L_0x0052:
        r6.m4325c();	 Catch:{ Exception -> 0x007c }
    L_0x0055:
        r0 = r1;
        goto L_0x0006;
    L_0x0057:
        r1 = r6.f3749d;
        r2 = f3740b044A044A;
        r3 = f3739b044A044A;
        r2 = r2 + r3;
        r3 = f3740b044A044A;
        r2 = r2 * r3;
        r3 = f3742b044A;
        r2 = r2 % r3;
        r3 = f3741b044A;
        if (r2 == r3) goto L_0x0072;
    L_0x0068:
        r2 = m4322b044A();
        f3740b044A044A = r2;
        r2 = 47;
        f3741b044A = r2;
    L_0x0072:
        r1 = r1.mHapticDataOffset;
    L_0x0074:
        r2 = 1;
        switch(r2) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0007;
            default: goto L_0x0078;
        };
    L_0x0078:
        switch(r4) {
            case 0: goto L_0x0007;
            case 1: goto L_0x0074;
            default: goto L_0x007b;
        };
    L_0x007b:
        goto L_0x0078;
    L_0x007c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0006;
    L_0x0081:
        r2 = r6.f3750e;	 Catch:{ Exception -> 0x007c }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r2 = r2.remaining();	 Catch:{ Exception -> 0x007c }
        goto L_0x004b;
    L_0x008a:
        r2 = r6.f3749d;	 Catch:{ Exception -> 0x007c }
        r2 = r2.mMappedByteBuffer;	 Catch:{ Exception -> 0x007c }
        r3 = 0;
        r4 = f3744h;	 Catch:{ Exception -> 0x007c }
        r2.get(r1, r3, r4);	 Catch:{ Exception -> 0x007c }
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.getBufferForPlaybackPosition(int):byte[]");
    }

    public byte[] getEncryptedHapticHeader() {
        return new byte[0];
    }

    public int getHapticBlockIndex(long j) {
        return 0;
    }

    public HapticFileInformation getHapticFileInformation() {
        return this.f3752i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBlockSizeMS(int r3) {
        /*
        r2 = this;
        r0 = 1;
        f3743g = r3;
    L_0x0003:
        switch(r0) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0006;
        };
    L_0x0006:
        switch(r0) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0006;
    L_0x000a:
        r0 = m4322b044A();
        r1 = m4321b044A044A();
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = f3742b044A;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x001e;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = 15;
        f3741b044A = r0;
    L_0x001e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.MemoryMappedFileReader.setBlockSizeMS(int):void");
    }

    public void setBytesAvailable(int i) {
        this.f3751f = i;
        if (((f3740b044A044A + m4321b044A044A()) * f3740b044A044A) % f3742b044A != m4320b044A044A044A()) {
            f3740b044A044A = 62;
            f3741b044A = 4;
        }
        m4315a();
    }
}
