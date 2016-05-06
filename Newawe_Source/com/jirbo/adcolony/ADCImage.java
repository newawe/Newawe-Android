package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Iterator;

public class ADCImage {
    static int f1798k;
    static int f1799l;
    Bitmap f1800a;
    Bitmap f1801b;
    Paint f1802c;
    Rect f1803d;
    Rect f1804e;
    int f1805f;
    int f1806g;
    boolean f1807h;
    int f1808i;
    int f1809j;
    boolean f1810m;
    ArrayList<Bitmap> f1811n;

    ADCImage(String filepath) {
        this(filepath, 1.0d);
    }

    ADCImage(String filepath, int width, int height) {
        this(filepath, 1.0d);
        m2102b(width, height);
    }

    ADCImage(String filepath, boolean is_native, boolean down_state) {
        this(filepath, 1.0d, down_state, is_native);
    }

    ADCImage(String filepath, boolean down_state) {
        this(filepath, 1.0d, down_state);
    }

    ADCImage(String filepath, double scale) {
        this(filepath, scale, false);
    }

    ADCImage(String filepath, double scale, boolean down_state) {
        this(filepath, scale, down_state, false);
    }

    ADCImage(String filepath, double scale, boolean down_state, boolean is_native) {
        this.f1802c = new Paint();
        this.f1803d = new Rect();
        this.f1804e = new Rect();
        this.f1811n = new ArrayList();
        try {
            InputStream fileInputStream = new FileInputStream(filepath);
            this.f1800a = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
            this.f1801b = this.f1800a;
            this.f1805f = this.f1800a.getWidth();
            this.f1806g = this.f1800a.getHeight();
            this.f1808i = this.f1800a.getWidth();
            this.f1809j = this.f1800a.getHeight();
            f1798k = this.f1808i;
            f1799l = this.f1809j;
            m2097a(scale);
            this.f1807h = true;
            if (down_state) {
                this.f1800a = convertToMutable(this.f1800a);
                int[] iArr = new int[(this.f1800a.getWidth() * this.f1800a.getHeight())];
                this.f1800a.getPixels(iArr, 0, this.f1800a.getWidth(), 0, 0, this.f1800a.getWidth(), this.f1800a.getHeight());
                int i = 0;
                while (i < iArr.length) {
                    if (iArr[i] < 255 && iArr[i] != 0) {
                        iArr[i] = ((iArr[i] >> 1) & 8355711) | ViewCompat.MEASURED_STATE_MASK;
                    }
                    i++;
                }
                this.f1800a.setPixels(iArr, 0, this.f1800a.getWidth(), 0, 0, this.f1800a.getWidth(), this.f1800a.getHeight());
                this.f1801b = this.f1800a;
            }
            if (!is_native) {
                C0745a.an.add(this.f1800a);
                this.f1811n.add(this.f1800a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            C0745a.m2157e("Failed to load image " + filepath);
            this.f1810m = true;
        }
    }

    void m2099a(int i, int i2) {
        if (this.f1800a != null) {
            Bitmap createBitmap = Bitmap.createBitmap(this.f1801b, 0, 0, this.f1808i / 3, this.f1809j);
            Bitmap createBitmap2 = Bitmap.createBitmap(this.f1801b, (this.f1808i * 2) / 3, 0, this.f1808i / 3, this.f1809j);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(Bitmap.createBitmap(this.f1801b, this.f1808i / 3, 0, this.f1808i / 3, this.f1809j), i, this.f1809j, false);
            int[] iArr = new int[((this.f1808i / 3) * this.f1809j)];
            int[] iArr2 = new int[((this.f1808i / 3) * this.f1809j)];
            createBitmap.getPixels(iArr, 0, this.f1808i / 3, 0, 0, this.f1808i / 3, this.f1809j);
            createBitmap2.getPixels(iArr2, 0, this.f1808i / 3, 0, 0, this.f1808i / 3, this.f1809j);
            createScaledBitmap.getPixels(new int[(createScaledBitmap.getWidth() * createScaledBitmap.getHeight())], 0, createScaledBitmap.getWidth(), 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight());
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i6 < createScaledBitmap.getHeight()) {
                if (i5 < this.f1808i / 3) {
                    if (i4 < iArr.length) {
                        createScaledBitmap.setPixel(i5, i6, iArr[i4]);
                    }
                    i4++;
                } else if (i5 >= createScaledBitmap.getWidth() - (this.f1808i / 3)) {
                    if (i3 < iArr2.length) {
                        createScaledBitmap.setPixel(i5, i6, iArr2[i3]);
                    }
                    i3++;
                }
                i5++;
                if (i5 == createScaledBitmap.getWidth()) {
                    i6++;
                    i5 = 0;
                }
            }
            this.f1800a = createScaledBitmap;
            this.f1801b = this.f1800a;
            this.f1805f = this.f1800a.getWidth();
            this.f1806g = this.f1800a.getHeight();
            this.f1808i = this.f1805f;
            this.f1809j = this.f1806g;
            this.f1803d.right = this.f1805f;
            this.f1803d.bottom = this.f1806g;
        }
    }

    void m2102b(int i, int i2) {
        if (this.f1800a != null && !this.f1800a.isRecycled()) {
            if (i != this.f1805f || i2 != this.f1806g) {
                this.f1800a = Bitmap.createScaledBitmap(this.f1801b, i, i2, true);
                this.f1805f = i;
                this.f1806g = i2;
                this.f1803d.right = i;
                this.f1803d.bottom = i2;
                C0745a.an.add(this.f1800a);
                this.f1811n.add(this.f1800a);
            }
        }
    }

    void m2097a(double d) {
        m2098a(d, false);
    }

    void m2098a(double d, boolean z) {
        if (this.f1800a != null && !this.f1800a.isRecycled()) {
            if (d != 1.0d) {
                int width = (int) (((double) this.f1801b.getWidth()) * d);
                int height = (int) (((double) this.f1801b.getHeight()) * d);
                if (!(width == this.f1805f && height == this.f1806g)) {
                    this.f1805f = width;
                    this.f1806g = height;
                    this.f1800a = Bitmap.createScaledBitmap(this.f1801b, this.f1805f, this.f1806g, true);
                    if (!z) {
                        C0745a.an.add(this.f1800a);
                        this.f1811n.add(this.f1800a);
                    }
                }
                if (z) {
                    this.f1801b.recycle();
                    this.f1801b = null;
                }
            }
            this.f1803d.right = this.f1805f;
            this.f1803d.bottom = this.f1806g;
        }
    }

    void m2096a() {
        Iterator it = this.f1811n.iterator();
        while (it.hasNext()) {
            ((Bitmap) it.next()).recycle();
        }
        this.f1811n.clear();
    }

    void m2105c(int i, int i2) {
        this.f1804e.left = i;
        this.f1804e.top = i2;
        this.f1804e.right = this.f1805f + i;
        this.f1804e.bottom = this.f1806g + i2;
    }

    int[] m2103b() {
        return new int[]{this.f1804e.left, this.f1804e.top};
    }

    int m2104c() {
        return this.f1804e.left;
    }

    int m2106d() {
        return this.f1804e.top;
    }

    void m2107d(int i, int i2) {
        m2105c((i - this.f1805f) / 2, (i2 - this.f1806g) / 2);
    }

    void m2101a(Canvas canvas, int i, int i2) {
        m2105c(i, i2);
        m2100a(canvas);
    }

    void m2100a(Canvas canvas) {
        if (this.f1800a != null && !this.f1800a.isRecycled()) {
            canvas.drawBitmap(this.f1800a, this.f1803d, this.f1804e, this.f1802c);
        }
    }

    public static Bitmap convertToMutable(Bitmap img_in) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            int i = f1798k;
            int i2 = f1799l;
            Config config = img_in.getConfig();
            FileChannel channel = randomAccessFile.getChannel();
            Buffer map = channel.map(MapMode.READ_WRITE, 0, (long) (img_in.getRowBytes() * i2));
            img_in.copyPixelsToBuffer(map);
            img_in = Bitmap.createBitmap(i, i2, config);
            map.position(0);
            img_in.copyPixelsFromBuffer(map);
            channel.close();
            randomAccessFile.close();
            file.delete();
            return img_in;
        } catch (Exception e) {
            e.printStackTrace();
            return img_in;
        }
    }
}
