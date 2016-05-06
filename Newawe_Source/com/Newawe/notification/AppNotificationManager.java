package com.Newawe.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.MessageViewer;
import com.Newawe.storage.DatabaseOpenHelper;
import com.Newawe.utils.ImageReader;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class AppNotificationManager {
    public static final int NOTIFICATION_ID = 10001;

    public static Intent getLaunchIntent(Context context, String title, String url, String launchMain) {
        if (launchMain == null || !launchMain.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
            return getLaunchMessageIntent(context, title, url);
        }
        return getLaunchMainIntent(context, url);
    }

    public static Intent getLaunchMessageIntent(Context context, String title, String url) {
        Intent intent = new Intent(context, MessageViewer.class);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(AccessibilityNodeInfoCompat.ACTION_SET_TEXT);
        Bundle extras = new Bundle();
        extras.putString(DatabaseOpenHelper.HISTORY_ROW_URL, url);
        extras.putString(DatabaseOpenHelper.HISTORY_ROW_TITLE, title);
        intent.putExtras(extras);
        return intent;
    }

    public static Intent getLaunchMainIntent(Context context) {
        return getLaunchMainIntent(context, null);
    }

    public static Intent getLaunchMainIntent(Context context, String url) {
        Intent intent = new Intent(context, MainNavigationActivity.class);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(AccessibilityNodeInfoCompat.ACTION_SET_TEXT);
        if (url != null && url.length() > 0) {
            intent.setData(Uri.parse(url));
        }
        return intent;
    }

    public static void generateNotification(Context context, String msg, String title, Intent intent) {
        int notificatonID = (int) System.currentTimeMillis();
        ((NotificationManager) context.getSystemService("notification")).notify(notificatonID, new Builder(context).setContentTitle(title).setContentText(msg).setSmallIcon(C0186R.drawable.icon).setContentIntent(PendingIntent.getActivity(context, (int) (System.currentTimeMillis() & 268435455), intent, 0)).setAutoCancel(true).build());
        playNotificationSound(context);
    }

    public static void generateNotification(Context context, String msg, String title, Intent intent, String iconUrl, String imageUrl) {
        Builder builder = new Builder(context).setContentTitle(title).setContentText(msg).setSmallIcon(C0186R.drawable.icon).setContentIntent(PendingIntent.getActivity(context, (int) (System.currentTimeMillis() & 268435455), intent, 0)).setAutoCancel(true);
        if (!(iconUrl == null || iconUrl.length() == 0)) {
            Bitmap bigIcon = ImageReader.resizedBitmapFromUrl(iconUrl, 192);
            if (bigIcon != null) {
                builder.setLargeIcon(bigIcon);
            }
        }
        if (!(imageUrl == null || imageUrl.length() == 0)) {
            Bitmap bigPicture = ImageReader.resizedBitmapFromUrl(imageUrl, 720);
            if (bigPicture != null) {
                builder.setStyle(new BigPictureStyle().bigPicture(bigPicture));
            }
        }
        int notificatonID = (int) System.currentTimeMillis();
        ((NotificationManager) context.getSystemService("notification")).notify(notificatonID, builder.build());
        playNotificationSound(context);
    }

    public static void playNotificationSound(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(2);
        if (uri != null) {
            Ringtone rt = RingtoneManager.getRingtone(context, uri);
            if (rt != null) {
                rt.setStreamType(5);
                rt.play();
            }
        }
    }
}
