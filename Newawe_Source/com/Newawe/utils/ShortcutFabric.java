package com.Newawe.utils;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;

public class ShortcutFabric {
    public static void createShortcut(String url, String title, Context context) {
        Intent shortcutIntent = new Intent();
        shortcutIntent.setClassName(MainNavigationActivity.class.getPackage().getName(), MainNavigationActivity.class.getName());
        shortcutIntent.addFlags(268435456);
        shortcutIntent.addFlags(67108864);
        if (url.length() > 0) {
            shortcutIntent.setData(Uri.parse(url));
        }
        Intent addIntent = new Intent();
        addIntent.putExtra("android.intent.extra.shortcut.INTENT", shortcutIntent);
        addIntent.putExtra("android.intent.extra.shortcut.NAME", title);
        addIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context, C0186R.drawable.pin_icon));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(addIntent);
    }
}
