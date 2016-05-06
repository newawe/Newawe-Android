package com.Newawe.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.Newawe.MainNavigationActivity;

public class VersionManager {
    private static final String APP_VERSION_ALIAS = "app_version_prefix";
    public static final int DEFAULT_VERSION = -1;

    public static int getPreviousVersion(Context context) {
        int previousVersion = DEFAULT_VERSION;
        try {
            previousVersion = context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getInt(APP_VERSION_ALIAS, DEFAULT_VERSION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return previousVersion;
    }

    public static int getCurrentVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return DEFAULT_VERSION;
        }
    }

    public static boolean updateVersion(Context context, int newVersion) {
        try {
            context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit().putInt(APP_VERSION_ALIAS, newVersion).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
