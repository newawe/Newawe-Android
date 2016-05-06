package com.inmobi.rendering.mraid;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.inmobi.rendering.mraid.e */
public class ImageProcessing {
    private static final String f1607a;

    static {
        f1607a = ImageProcessing.class.getSimpleName();
    }

    public static String m1777a(Bitmap bitmap, Context context, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static String m1778a(Uri uri, Context context) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
        query.moveToFirst();
        String string = query.getString(columnIndexOrThrow);
        query.close();
        return string;
    }

    public static Bitmap m1776a(String str, Context context, int i, int i2) {
        int i3 = i * i2;
        try {
            Bitmap createScaledBitmap;
            InputStream fileInputStream = new FileInputStream(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            int i4 = 1;
            while (((double) (options.outWidth * options.outHeight)) * (1.0d / Math.pow((double) i4, 2.0d)) > ((double) i3)) {
                i4++;
            }
            InputStream fileInputStream2 = new FileInputStream(str);
            if (i4 > 1) {
                i4--;
                Options options2 = new Options();
                options2.inSampleSize = i4;
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream2, null, options2);
                i4 = decodeStream.getHeight();
                int width = decodeStream.getWidth();
                double sqrt = Math.sqrt(((double) i3) / (((double) width) / ((double) i4)));
                createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, (int) ((sqrt / ((double) i4)) * ((double) width)), (int) sqrt, true);
                decodeStream.recycle();
                System.gc();
            } else {
                createScaledBitmap = BitmapFactory.decodeStream(fileInputStream2);
            }
            fileInputStream2.close();
            Logger.m1440a(InternalLogLevel.INTERNAL, f1607a, "bitmap size - width: " + createScaledBitmap.getWidth() + ", height: " + createScaledBitmap.getHeight());
            return createScaledBitmap;
        } catch (IOException e) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1607a, e.getMessage());
            return null;
        }
    }
}
