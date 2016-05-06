package com.google.android.gms.internal;

import java.io.IOException;

public final class zzsx {
    public static final boolean[] zzbuA;
    public static final String[] zzbuB;
    public static final byte[][] zzbuC;
    public static final byte[] zzbuD;
    public static final int[] zzbuw;
    public static final long[] zzbux;
    public static final float[] zzbuy;
    public static final double[] zzbuz;

    static {
        zzbuw = new int[0];
        zzbux = new long[0];
        zzbuy = new float[0];
        zzbuz = new double[0];
        zzbuA = new boolean[0];
        zzbuB = new String[0];
        zzbuC = new byte[0][];
        zzbuD = new byte[0];
    }

    static int zzF(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean zzb(zzsm com_google_android_gms_internal_zzsm, int i) throws IOException {
        return com_google_android_gms_internal_zzsm.zzmo(i);
    }

    public static final int zzc(zzsm com_google_android_gms_internal_zzsm, int i) throws IOException {
        int i2 = 1;
        int position = com_google_android_gms_internal_zzsm.getPosition();
        com_google_android_gms_internal_zzsm.zzmo(i);
        while (com_google_android_gms_internal_zzsm.zzIX() == i) {
            com_google_android_gms_internal_zzsm.zzmo(i);
            i2++;
        }
        com_google_android_gms_internal_zzsm.zzms(position);
        return i2;
    }

    static int zzmI(int i) {
        return i & 7;
    }

    public static int zzmJ(int i) {
        return i >>> 3;
    }
}
