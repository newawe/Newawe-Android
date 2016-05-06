package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public class ad {
    public static C0469m m492a(Context context, C0484z c0484z) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = new StringBuilder(String.valueOf(packageName)).append("/").append(context.getPackageManager().getPackageInfo(packageName, 0).versionCode).toString();
        } catch (NameNotFoundException e) {
        }
        if (c0484z == null) {
            if (VERSION.SDK_INT >= 9) {
                c0484z = new aa();
            } else {
                c0484z = new C1070x(AndroidHttpClient.newInstance(str));
            }
        }
        C0469m c0469m = new C0469m(new C1069w(file), new C1068u(c0484z));
        c0469m.m816a();
        return c0469m;
    }

    public static C0469m m491a(Context context) {
        return m492a(context, null);
    }
}
