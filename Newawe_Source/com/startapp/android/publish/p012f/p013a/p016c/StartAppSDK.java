package com.startapp.android.publish.p012f.p013a.p016c;

import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.w3c.dom.traversal.NodeFilter;

/* renamed from: com.startapp.android.publish.f.a.c.a */
public class StartAppSDK extends StartAppSDK {
    static final byte[] f4108a;
    private static final byte[] f4109d;
    private static final byte[] f4110e;
    private static final byte[] f4111f;
    private final byte[] f4112g;
    private final byte[] f4113h;
    private final byte[] f4114i;
    private final int f4115j;
    private final int f4116k;

    static {
        f4108a = new byte[]{(byte) 13, (byte) 10};
        f4109d = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        f4110e = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        f4111f = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) 62, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51};
    }

    public StartAppSDK() {
        this(0);
    }

    public StartAppSDK(boolean z) {
        this(76, f4108a, z);
    }

    public StartAppSDK(int i) {
        this(i, f4108a);
    }

    public StartAppSDK(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public StartAppSDK(int i, byte[] bArr, boolean z) {
        int i2;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = bArr.length;
        }
        super(3, 4, i, i2);
        this.f4113h = f4111f;
        if (bArr == null) {
            this.f4116k = 4;
            this.f4114i = null;
        } else if (m2775c(bArr)) {
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + StartAppSDK.m2779a(bArr) + "]");
        } else if (i > 0) {
            this.f4116k = bArr.length + 4;
            this.f4114i = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.f4114i, 0, bArr.length);
        } else {
            this.f4116k = 4;
            this.f4114i = null;
        }
        this.f4115j = this.f4116k - 1;
        this.f4112g = z ? f4110e : f4109d;
    }

    void m4833a(byte[] bArr, int i, int i2, StartAppSDK startAppSDK) {
        if (!startAppSDK.f2879f) {
            int i3;
            int i4;
            if (i2 < 0) {
                startAppSDK.f2879f = true;
                if (startAppSDK.f2881h != 0 || this.c != 0) {
                    Object a = m2772a(this.f4116k, startAppSDK);
                    i3 = startAppSDK.f2877d;
                    switch (startAppSDK.f2881h) {
                        case DurationDV.DURATION_TYPE /*0*/:
                            break;
                        case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                            i4 = startAppSDK.f2877d;
                            startAppSDK.f2877d = i4 + 1;
                            a[i4] = this.f4112g[(startAppSDK.f2874a >> 2) & 63];
                            i4 = startAppSDK.f2877d;
                            startAppSDK.f2877d = i4 + 1;
                            a[i4] = this.f4112g[(startAppSDK.f2874a << 4) & 63];
                            if (this.f4112g == f4109d) {
                                i4 = startAppSDK.f2877d;
                                startAppSDK.f2877d = i4 + 1;
                                a[i4] = 61;
                                i4 = startAppSDK.f2877d;
                                startAppSDK.f2877d = i4 + 1;
                                a[i4] = 61;
                                break;
                            }
                            break;
                        case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                            i4 = startAppSDK.f2877d;
                            startAppSDK.f2877d = i4 + 1;
                            a[i4] = this.f4112g[(startAppSDK.f2874a >> 10) & 63];
                            i4 = startAppSDK.f2877d;
                            startAppSDK.f2877d = i4 + 1;
                            a[i4] = this.f4112g[(startAppSDK.f2874a >> 4) & 63];
                            i4 = startAppSDK.f2877d;
                            startAppSDK.f2877d = i4 + 1;
                            a[i4] = this.f4112g[(startAppSDK.f2874a << 2) & 63];
                            if (this.f4112g == f4109d) {
                                i4 = startAppSDK.f2877d;
                                startAppSDK.f2877d = i4 + 1;
                                a[i4] = 61;
                                break;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Impossible modulus " + startAppSDK.f2881h);
                    }
                    startAppSDK.f2880g = (startAppSDK.f2877d - i3) + startAppSDK.f2880g;
                    if (this.c > 0 && startAppSDK.f2880g > 0) {
                        System.arraycopy(this.f4114i, 0, a, startAppSDK.f2877d, this.f4114i.length);
                        startAppSDK.f2877d += this.f4114i.length;
                        return;
                    }
                    return;
                }
                return;
            }
            i3 = 0;
            while (i3 < i2) {
                Object a2 = m2772a(this.f4116k, startAppSDK);
                startAppSDK.f2881h = (startAppSDK.f2881h + 1) % 3;
                i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += NodeFilter.SHOW_DOCUMENT;
                }
                startAppSDK.f2874a = i5 + (startAppSDK.f2874a << 8);
                if (startAppSDK.f2881h == 0) {
                    i5 = startAppSDK.f2877d;
                    startAppSDK.f2877d = i5 + 1;
                    a2[i5] = this.f4112g[(startAppSDK.f2874a >> 18) & 63];
                    i5 = startAppSDK.f2877d;
                    startAppSDK.f2877d = i5 + 1;
                    a2[i5] = this.f4112g[(startAppSDK.f2874a >> 12) & 63];
                    i5 = startAppSDK.f2877d;
                    startAppSDK.f2877d = i5 + 1;
                    a2[i5] = this.f4112g[(startAppSDK.f2874a >> 6) & 63];
                    i5 = startAppSDK.f2877d;
                    startAppSDK.f2877d = i5 + 1;
                    a2[i5] = this.f4112g[startAppSDK.f2874a & 63];
                    startAppSDK.f2880g += 4;
                    if (this.c > 0 && this.c <= startAppSDK.f2880g) {
                        System.arraycopy(this.f4114i, 0, a2, startAppSDK.f2877d, this.f4114i.length);
                        startAppSDK.f2877d += this.f4114i.length;
                        startAppSDK.f2880g = 0;
                    }
                }
                i3++;
                i = i4;
            }
        }
    }

    public static String m4829a(byte[] bArr) {
        return StartAppSDK.m2779a(StartAppSDK.m4830a(bArr, false));
    }

    public static byte[] m4830a(byte[] bArr, boolean z) {
        return StartAppSDK.m4831a(bArr, z, false);
    }

    public static byte[] m4831a(byte[] bArr, boolean z, boolean z2) {
        return StartAppSDK.m4832a(bArr, z, z2, (int) ASContentModel.AS_UNBOUNDED);
    }

    public static byte[] m4832a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        StartAppSDK startAppSDK = z ? new StartAppSDK(z2) : new StartAppSDK(0, f4108a, z2);
        long d = startAppSDK.m2776d(bArr);
        if (d <= ((long) i)) {
            return startAppSDK.m2774b(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + d + ") than the specified maximum size of " + i);
    }

    protected boolean m4834a(byte b) {
        return b >= null && b < this.f4113h.length && this.f4113h[b] != -1;
    }
}
