package com.Newawe.utils;

import android.app.Activity;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class DeviceIdParser {
    public static String getDeviceId(Activity activity) {
        try {
            if (activity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                return ((TelephonyManager) activity.getSystemService("phone")).getDeviceId();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getAndroidId(Activity activity) {
        try {
            return Secure.getString(activity.getContentResolver(), "android_id");
        } catch (Exception e) {
            return null;
        }
    }
}
