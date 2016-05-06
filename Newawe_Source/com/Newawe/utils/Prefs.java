package com.Newawe.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class Prefs {
    public static SharedPreferences get(Context context) {
        return context.getSharedPreferences("CTP_PREFS", 0);
    }
}
