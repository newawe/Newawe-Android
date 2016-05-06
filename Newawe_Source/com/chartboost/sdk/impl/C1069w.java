package com.chartboost.sdk.impl;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.chartboost.sdk.impl.C0402b.C0401a;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.chartboost.sdk.impl.w */
public class C1069w implements C0402b {
    private final Map<String, C0481a> f3632a;
    private long f3633b;
    private final File f3634c;
    private final int f3635d;

    /* renamed from: com.chartboost.sdk.impl.w.a */
    static class C0481a {
        public long f784a;
        public String f785b;
        public String f786c;
        public long f787d;
        public long f788e;
        public long f789f;
        public Map<String, String> f790g;

        private C0481a() {
        }

        public C0481a(String str, C0401a c0401a) {
            this.f785b = str;
            this.f784a = (long) c0401a.f520a.length;
            this.f786c = c0401a.f521b;
            this.f787d = c0401a.f522c;
            this.f788e = c0401a.f523d;
            this.f789f = c0401a.f524e;
            this.f790g = c0401a.f525f;
        }

        public static C0481a m846a(InputStream inputStream) throws IOException {
            C0481a c0481a = new C0481a();
            if (C1069w.m4090a(inputStream) != 538183203) {
                throw new IOException();
            }
            c0481a.f785b = C1069w.m4099c(inputStream);
            c0481a.f786c = C1069w.m4099c(inputStream);
            if (c0481a.f786c.equals(StringUtils.EMPTY)) {
                c0481a.f786c = null;
            }
            c0481a.f787d = C1069w.m4098b(inputStream);
            c0481a.f788e = C1069w.m4098b(inputStream);
            c0481a.f789f = C1069w.m4098b(inputStream);
            c0481a.f790g = C1069w.m4101d(inputStream);
            return c0481a;
        }

        public C0401a m847a(byte[] bArr) {
            C0401a c0401a = new C0401a();
            c0401a.f520a = bArr;
            c0401a.f521b = this.f786c;
            c0401a.f522c = this.f787d;
            c0401a.f523d = this.f788e;
            c0401a.f524e = this.f789f;
            c0401a.f525f = this.f790g;
            return c0401a;
        }

        public boolean m848a(OutputStream outputStream) {
            try {
                C1069w.m4092a(outputStream, 538183203);
                C1069w.m4094a(outputStream, this.f785b);
                C1069w.m4094a(outputStream, this.f786c == null ? StringUtils.EMPTY : this.f786c);
                C1069w.m4093a(outputStream, this.f787d);
                C1069w.m4093a(outputStream, this.f788e);
                C1069w.m4093a(outputStream, this.f789f);
                C1069w.m4096a(this.f790g, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                C0478t.m839b("%s", e.toString());
                return false;
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.w.b */
    private static class C0482b extends FilterInputStream {
        private int f791a;

        private C0482b(InputStream inputStream) {
            super(inputStream);
            this.f791a = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.f791a++;
            }
            return read;
        }

        public int read(byte[] buffer, int offset, int count) throws IOException {
            int read = super.read(buffer, offset, count);
            if (read != -1) {
                this.f791a += read;
            }
            return read;
        }
    }

    public C1069w(File file, int i) {
        this.f3632a = new LinkedHashMap(16, 0.75f, true);
        this.f3633b = 0;
        this.f3634c = file;
        this.f3635d = i;
    }

    public C1069w(File file) {
        this(file, 5242880);
    }

    public synchronized C0401a m4104a(String str) {
        C0401a c0401a;
        C0482b c0482b;
        IOException e;
        Throwable th;
        C0481a c0481a = (C0481a) this.f3632a.get(str);
        if (c0481a == null) {
            c0401a = null;
        } else {
            File c = m4108c(str);
            try {
                c0482b = new C0482b(null);
                try {
                    C0481a.m846a((InputStream) c0482b);
                    c0401a = c0481a.m847a(C1069w.m4097a((InputStream) c0482b, (int) (c.length() - ((long) c0482b.f791a))));
                    if (c0482b != null) {
                        try {
                            c0482b.close();
                        } catch (IOException e2) {
                            c0401a = null;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        C0478t.m839b("%s: %s", c.getAbsolutePath(), e.toString());
                        m4107b(str);
                        if (c0482b != null) {
                            try {
                                c0482b.close();
                            } catch (IOException e4) {
                                c0401a = null;
                            }
                        }
                        c0401a = null;
                        return c0401a;
                    } catch (Throwable th2) {
                        th = th2;
                        if (c0482b != null) {
                            try {
                                c0482b.close();
                            } catch (IOException e5) {
                                c0401a = null;
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                c0482b = null;
                C0478t.m839b("%s: %s", c.getAbsolutePath(), e.toString());
                m4107b(str);
                if (c0482b != null) {
                    c0482b.close();
                }
                c0401a = null;
                return c0401a;
            } catch (Throwable th3) {
                th = th3;
                c0482b = null;
                if (c0482b != null) {
                    c0482b.close();
                }
                throw th;
            }
        }
        return c0401a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m4105a() {
        /*
        r9 = this;
        r0 = 0;
        monitor-enter(r9);
        r1 = r9.f3634c;	 Catch:{ all -> 0x0067 }
        r1 = r1.exists();	 Catch:{ all -> 0x0067 }
        if (r1 != 0) goto L_0x0025;
    L_0x000a:
        r0 = r9.f3634c;	 Catch:{ all -> 0x0067 }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x0067 }
        if (r0 != 0) goto L_0x0023;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0067 }
        r2 = 0;
        r3 = r9.f3634c;	 Catch:{ all -> 0x0067 }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x0067 }
        r1[r2] = r3;	 Catch:{ all -> 0x0067 }
        com.chartboost.sdk.impl.C0478t.m840c(r0, r1);	 Catch:{ all -> 0x0067 }
    L_0x0023:
        monitor-exit(r9);
        return;
    L_0x0025:
        r1 = r9.f3634c;	 Catch:{ all -> 0x0067 }
        r3 = r1.listFiles();	 Catch:{ all -> 0x0067 }
        if (r3 == 0) goto L_0x0023;
    L_0x002d:
        r4 = r3.length;	 Catch:{ all -> 0x0067 }
        r2 = r0;
    L_0x002f:
        if (r2 >= r4) goto L_0x0023;
    L_0x0031:
        r5 = r3[r2];	 Catch:{ all -> 0x0067 }
        r1 = 0;
        r0 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0051, all -> 0x0060 }
        r0.<init>(r5);	 Catch:{ IOException -> 0x0051, all -> 0x0060 }
        r1 = com.chartboost.sdk.impl.C1069w.C0481a.m846a(r0);	 Catch:{ IOException -> 0x0073 }
        r6 = r5.length();	 Catch:{ IOException -> 0x0073 }
        r1.f784a = r6;	 Catch:{ IOException -> 0x0073 }
        r6 = r1.f785b;	 Catch:{ IOException -> 0x0073 }
        r9.m4095a(r6, r1);	 Catch:{ IOException -> 0x0073 }
        if (r0 == 0) goto L_0x004d;
    L_0x004a:
        r0.close();	 Catch:{ IOException -> 0x006c }
    L_0x004d:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x002f;
    L_0x0051:
        r0 = move-exception;
        r0 = r1;
    L_0x0053:
        if (r5 == 0) goto L_0x0058;
    L_0x0055:
        r5.delete();	 Catch:{ all -> 0x006e }
    L_0x0058:
        if (r0 == 0) goto L_0x004d;
    L_0x005a:
        r0.close();	 Catch:{ IOException -> 0x005e }
        goto L_0x004d;
    L_0x005e:
        r0 = move-exception;
        goto L_0x004d;
    L_0x0060:
        r0 = move-exception;
    L_0x0061:
        if (r1 == 0) goto L_0x0066;
    L_0x0063:
        r1.close();	 Catch:{ IOException -> 0x006a }
    L_0x0066:
        throw r0;	 Catch:{ all -> 0x0067 }
    L_0x0067:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x006a:
        r1 = move-exception;
        goto L_0x0066;
    L_0x006c:
        r0 = move-exception;
        goto L_0x004d;
    L_0x006e:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0061;
    L_0x0073:
        r1 = move-exception;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.w.a():void");
    }

    public synchronized void m4106a(String str, C0401a c0401a) {
        m4091a(c0401a.f520a.length);
        File c = m4108c(str);
        try {
            OutputStream fileOutputStream = new FileOutputStream(c);
            C0481a c0481a = new C0481a(str, c0401a);
            if (c0481a.m848a(fileOutputStream)) {
                fileOutputStream.write(c0401a.f520a);
                fileOutputStream.close();
                m4095a(str, c0481a);
            } else {
                fileOutputStream.close();
                C0478t.m839b("Failed to write header for %s", c.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e) {
            if (!c.delete()) {
                C0478t.m839b("Could not clean up file %s", c.getAbsolutePath());
            }
        }
    }

    public synchronized void m4107b(String str) {
        boolean delete = m4108c(str).delete();
        m4103e(str);
        if (!delete) {
            C0478t.m839b("Could not delete cache entry for key=%s, filename=%s", str, m4100d(str));
        }
    }

    private String m4100d(String str) {
        int length = str.length() / 2;
        return new StringBuilder(String.valueOf(String.valueOf(str.substring(0, length).hashCode()))).append(String.valueOf(str.substring(length).hashCode())).toString();
    }

    public File m4108c(String str) {
        return new File(this.f3634c, m4100d(str));
    }

    private void m4091a(int i) {
        if (this.f3633b + ((long) i) >= ((long) this.f3635d)) {
            int i2;
            if (C0478t.f778b) {
                C0478t.m837a("Pruning old cache entries.", new Object[0]);
            }
            long j = this.f3633b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = this.f3632a.entrySet().iterator();
            int i3 = 0;
            while (it.hasNext()) {
                C0481a c0481a = (C0481a) ((Entry) it.next()).getValue();
                if (m4108c(c0481a.f785b).delete()) {
                    this.f3633b -= c0481a.f784a;
                } else {
                    C0478t.m839b("Could not delete cache entry for key=%s, filename=%s", c0481a.f785b, m4100d(c0481a.f785b));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.f3633b + ((long) i))) < ((float) this.f3635d) * 0.9f) {
                    break;
                }
                i3 = i2;
            }
            i2 = i3;
            if (C0478t.f778b) {
                C0478t.m837a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.f3633b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void m4095a(String str, C0481a c0481a) {
        if (this.f3632a.containsKey(str)) {
            C0481a c0481a2 = (C0481a) this.f3632a.get(str);
            this.f3633b = (c0481a.f784a - c0481a2.f784a) + this.f3633b;
        } else {
            this.f3633b += c0481a.f784a;
        }
        this.f3632a.put(str, c0481a);
    }

    private void m4103e(String str) {
        C0481a c0481a = (C0481a) this.f3632a.get(str);
        if (c0481a != null) {
            this.f3633b -= c0481a.f784a;
            this.f3632a.remove(str);
        }
    }

    private static byte[] m4097a(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    private static int m4102e(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void m4092a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 8) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 16) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 24) & MotionEventCompat.ACTION_MASK);
    }

    static int m4090a(InputStream inputStream) throws IOException {
        return (((0 | (C1069w.m4102e(inputStream) << 0)) | (C1069w.m4102e(inputStream) << 8)) | (C1069w.m4102e(inputStream) << 16)) | (C1069w.m4102e(inputStream) << 24);
    }

    static void m4093a(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long m4098b(InputStream inputStream) throws IOException {
        return (((((((0 | ((((long) C1069w.m4102e(inputStream)) & 255) << null)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 8)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 16)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 24)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 32)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 40)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 48)) | ((((long) C1069w.m4102e(inputStream)) & 255) << 56);
    }

    static void m4094a(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes(HTTP.UTF_8);
        C1069w.m4093a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String m4099c(InputStream inputStream) throws IOException {
        return new String(C1069w.m4097a(inputStream, (int) C1069w.m4098b(inputStream)), HTTP.UTF_8);
    }

    static void m4096a(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            C1069w.m4092a(outputStream, map.size());
            for (Entry entry : map.entrySet()) {
                C1069w.m4094a(outputStream, (String) entry.getKey());
                C1069w.m4094a(outputStream, (String) entry.getValue());
            }
            return;
        }
        C1069w.m4092a(outputStream, 0);
    }

    static Map<String, String> m4101d(InputStream inputStream) throws IOException {
        Map<String, String> emptyMap;
        int a = C1069w.m4090a(inputStream);
        if (a == 0) {
            emptyMap = Collections.emptyMap();
        } else {
            emptyMap = new HashMap(a);
        }
        for (int i = 0; i < a; i++) {
            emptyMap.put(C1069w.m4099c(inputStream).intern(), C1069w.m4099c(inputStream).intern());
        }
        return emptyMap;
    }
}
