package com.Newawe.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.Base64;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;

public class WebViewScreenShooter {
    public static String takeScreenShotInBase64(WebView view) {
        Picture screenShot = view.capturePicture();
        Bitmap b = Bitmap.createBitmap(screenShot.getWidth(), screenShot.getHeight(), Config.ARGB_8888);
        screenShot.draw(new Canvas(b));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
