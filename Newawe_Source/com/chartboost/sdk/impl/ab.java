package com.chartboost.sdk.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.C0472n.C0470a;
import com.chartboost.sdk.impl.C0472n.C0471b;
import org.apache.commons.lang.time.DateUtils;

public class ab extends C0467l<Bitmap> {
    private static final Object f3430e;
    private final C0471b<Bitmap> f3431a;
    private final Config f3432b;
    private final int f3433c;
    private final int f3434d;

    protected /* synthetic */ void m3812b(Object obj) {
        m3811a((Bitmap) obj);
    }

    static {
        f3430e = new Object();
    }

    public ab(String str, C0471b<Bitmap> c0471b, int i, int i2, Config config, C0470a c0470a) {
        super(0, str, c0470a);
        m784a(new C1061d(DateUtils.MILLIS_IN_SECOND, 2, 2.0f));
        this.f3431a = c0471b;
        this.f3432b = config;
        this.f3433c = i;
        this.f3434d = i2;
    }

    public C0466a m3813s() {
        return C0466a.LOW;
    }

    private static int m3808b(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
        }
        if (i2 == 0) {
            return i;
        }
        double d = ((double) i4) / ((double) i3);
        if (((double) i) * d > ((double) i2)) {
            return (int) (((double) i2) / d);
        }
        return i;
    }

    protected C0472n<Bitmap> m3810a(C0464i c0464i) {
        C0472n<Bitmap> b;
        synchronized (f3430e) {
            try {
                b = m3809b(c0464i);
            } catch (Throwable e) {
                C0478t.m840c("Caught OOM for %d byte image, url=%s", Integer.valueOf(c0464i.f730b.length), m794d());
                b = C0472n.m825a(new C1064k(e));
            }
        }
        return b;
    }

    private C0472n<Bitmap> m3809b(C0464i c0464i) {
        Object decodeByteArray;
        byte[] bArr = c0464i.f730b;
        Options options = new Options();
        if (this.f3433c == 0 && this.f3434d == 0) {
            options.inPreferredConfig = this.f3432b;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int b = m3808b(this.f3433c, this.f3434d, i, i2);
            int b2 = m3808b(this.f3434d, this.f3433c, i2, i);
            options.inJustDecodeBounds = false;
            options.inSampleSize = m3807a(i, i2, b, b2);
            Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= b && decodeByteArray2.getHeight() <= b2)) {
                Bitmap bitmap = decodeByteArray2;
            } else {
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, b, b2, true);
                decodeByteArray2.recycle();
            }
        }
        if (decodeByteArray == null) {
            return C0472n.m825a(new C1064k(c0464i));
        }
        return C0472n.m826a(decodeByteArray, C0483y.m851a(c0464i));
    }

    protected void m3811a(Bitmap bitmap) {
        this.f3431a.m824a(bitmap);
    }

    static int m3807a(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        while (((double) (f * 2.0f)) <= min) {
            f *= 2.0f;
        }
        return (int) f;
    }
}
