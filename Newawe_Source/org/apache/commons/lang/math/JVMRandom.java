package org.apache.commons.lang.math;

import java.util.Random;
import mf.org.apache.xerces.dom3.as.ASContentModel;

public final class JVMRandom extends Random {
    private static final Random SHARED_RANDOM;
    private static final long serialVersionUID = 1;
    private boolean constructed;

    static {
        SHARED_RANDOM = new Random();
    }

    public JVMRandom() {
        this.constructed = false;
        this.constructed = true;
    }

    public synchronized void setSeed(long seed) {
        if (this.constructed) {
            throw new UnsupportedOperationException();
        }
    }

    public synchronized double nextGaussian() {
        throw new UnsupportedOperationException();
    }

    public void nextBytes(byte[] byteArray) {
        throw new UnsupportedOperationException();
    }

    public int nextInt() {
        return nextInt(ASContentModel.AS_UNBOUNDED);
    }

    public int nextInt(int n) {
        return SHARED_RANDOM.nextInt(n);
    }

    public long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    public static long nextLong(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Upper bound for nextInt must be positive");
        } else if (((-n) & n) == n) {
            return next63bits() >> (63 - bitsRequired(n - serialVersionUID));
        } else {
            long val;
            long bits;
            do {
                bits = next63bits();
                val = bits % n;
            } while ((bits - val) + (n - serialVersionUID) < 0);
            return val;
        }
    }

    public boolean nextBoolean() {
        return SHARED_RANDOM.nextBoolean();
    }

    public float nextFloat() {
        return SHARED_RANDOM.nextFloat();
    }

    public double nextDouble() {
        return SHARED_RANDOM.nextDouble();
    }

    private static long next63bits() {
        return SHARED_RANDOM.nextLong() & Long.MAX_VALUE;
    }

    private static int bitsRequired(long num) {
        long y = num;
        int n = 0;
        while (num >= 0) {
            if (y == 0) {
                return n;
            }
            n++;
            num <<= serialVersionUID;
            y >>= serialVersionUID;
        }
        return 64 - n;
    }
}
