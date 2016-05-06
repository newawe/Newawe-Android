package com.Newawe.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang.StringUtils;

public class ImageReader {
    public static Bitmap decodeFile(InputStream f, int requiredSize) {
        if (requiredSize == 0) {
            return null;
        }
        Options o = new Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(f, null, o);
        int scale = 1;
        while ((o.outWidth / scale) / 2 >= requiredSize && (o.outHeight / scale) / 2 >= requiredSize) {
            scale *= 2;
        }
        Options o2 = new Options();
        o2.inSampleSize = scale;
        try {
            f.reset();
            return BitmapFactory.decodeStream(f, null, o2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeFile(String f, int requiredSize) {
        try {
            return decodeFile(new FileInputStream(f), requiredSize);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Bitmap createBitmapFromBase64(String base64) {
        if (base64.equals(StringUtils.EMPTY)) {
            return null;
        }
        byte[] decodedString = Base64.decode(base64.replace("data:image/png;base64,", StringUtils.EMPTY), 0);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap resizedBitmapFromUrl(String src, int requiredSize) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(src);
            Log.d("ImageReader", "URL: " + src);
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            if (requiredSize != 0) {
                Options o = new Options();
                Bitmap bitmap2 = BitmapFactory.decodeStream(input, null, o);
                int scale = 1;
                while ((o.outWidth / scale) / 2 >= requiredSize && (o.outHeight / scale) / 2 >= requiredSize) {
                    scale *= 2;
                }
                bitmap = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth() / scale, bitmap2.getHeight() / scale, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap bitmapFromUrl(String src) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(src).openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
