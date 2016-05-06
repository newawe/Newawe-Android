package com.Newawe.utils;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.Newawe.Factory;
import com.Newawe.VideoPlayerActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.StringUtils;

public class FileManager {
    private static final ArrayList<String> list;

    /* renamed from: com.Newawe.utils.FileManager.1 */
    static class C02911 extends ArrayList<String> {
        C02911() {
            add("video/mpeg4");
            add("video/mp4");
            add("video/3gp");
            add("video/3gpp");
            add("video/3gpp2");
            add("video/webm");
            add("video/avi");
            add("application/sdp");
            add("application/vnd.apple.mpegurl");
            add("application/x-mpegurl");
        }
    }

    static {
        list = new C02911();
    }

    public static String getStringFromAssetsFileWithFileName(String fileName, Context context) {
        if (fileName == null || fileName.length() == 0) {
            return StringUtils.EMPTY;
        }
        StringBuilder contents = new StringBuilder();
        BufferedReader input;
        try {
            AssetManager assMngr = context.getAssets();
            if (assMngr != null) {
                InputStream inputStream = assMngr.open(fileName);
                if (inputStream != null) {
                    input = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        String line = input.readLine();
                        if (line == null) {
                            break;
                        }
                        contents.append(line).append(LineSeparator.Web);
                    }
                    input.close();
                    inputStream.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Throwable th) {
            input.close();
        }
        return contents.toString();
    }

    public static void fireOpenFileIntent(String url, String mimetype, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url), context, VideoPlayerActivity.class);
        if (mimetype == null || mimetype.length() <= 0) {
            intent.setData(Uri.parse(url));
        } else {
            intent.setDataAndType(Uri.parse(url), mimetype);
        }
        if (context.getPackageManager().resolveActivity(intent, 0) != null) {
            context.startActivity(intent);
            return;
        }
        Intent intentView;
        if (list.contains(mimetype)) {
            intentView = new Intent("android.intent.action.VIEW", Uri.parse(url), context, VideoPlayerActivity.class);
        } else {
            intentView = new Intent("android.intent.action.VIEW");
        }
        intentView.setData(Uri.parse(url));
        context.startActivity(intentView);
    }

    public static void fireOpenFileIntent(String url, Context context) {
        fireOpenFileIntent(url, null, context);
    }

    public static void downloadFile(String url, String mimetype, String contentDisposition, Context context) {
        if (VERSION.SDK_INT < 9) {
            fireOpenFileIntent(url, mimetype, context);
        }
        if (context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            fireOpenFileIntent(url, mimetype, context);
        }
        Toast.makeText(context, "Download starting...", 0).show();
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        Request request = new Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
        request.setMimeType(mimetype);
        if (VERSION.SDK_INT >= 11) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
        }
        downloadManager.enqueue(request);
    }

    public static void downloadFile(String url, String contentDisposition, Context context) {
        downloadFile(url, null, contentDisposition, context);
    }

    public static File saveBitmapToGallery(String prefix, Bitmap bmp) {
        try {
            File folder;
            Calendar c = Calendar.getInstance();
            String date = StringUtils.EMPTY + c.get(5) + c.get(2) + c.get(1) + c.get(11) + c.get(12) + c.get(13);
            if (VERSION.RELEASE.compareTo("2.3.3") >= 1) {
                folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            } else {
                folder = Environment.getExternalStorageDirectory();
            }
            File imageFile = new File(folder, prefix + date + ".png");
            FileOutputStream out = new FileOutputStream(imageFile);
            bmp.compress(CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            scanPhoto(imageFile);
            return imageFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void scanPhoto(File imageFile) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(Uri.fromFile(imageFile));
        Factory.getInstance().getMainNavigationActivity().sendBroadcast(mediaScanIntent);
    }
}
