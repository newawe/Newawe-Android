package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import com.inmobi.commons.p000a.SdkContext;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class DisplayInfo {

    public enum ORIENTATION_VALUES {
        PORTRAIT(1),
        REVERSE_PORTRAIT(2),
        LANDSCAPE(3),
        REVERSE_LANDSCAPE(4);
        
        private int f1277a;

        private ORIENTATION_VALUES(int i) {
            this.f1277a = i;
        }

        public int getValue() {
            return this.f1277a;
        }
    }

    private static String m1484d() {
        DisplayProperties a = m1481a();
        return a.m1496b() + "X" + a.m1495a();
    }

    private static String m1485e() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) SdkContext.m1258b().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        return i + "x" + displayMetrics.heightPixels;
    }

    public static DisplayProperties m1481a() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) SdkContext.m1258b().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        return new DisplayProperties(Math.round(((float) displayMetrics.widthPixels) / f), Math.round(((float) displayMetrics.heightPixels) / f), f);
    }

    public static int m1480a(int i) {
        return Math.round(((float) i) / m1481a().m1497c());
    }

    public static int m1482b() {
        Context b = SdkContext.m1258b();
        int rotation = ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (b.getResources().getConfiguration().orientation) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (VERSION.SDK_INT < 8) {
                    return ORIENTATION_VALUES.PORTRAIT.getValue();
                }
                if (rotation == 1 || rotation == 2) {
                    return ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue();
                }
                return ORIENTATION_VALUES.PORTRAIT.getValue();
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (VERSION.SDK_INT < 8) {
                    return ORIENTATION_VALUES.LANDSCAPE.getValue();
                }
                if (rotation == 0 || rotation == 1) {
                    return ORIENTATION_VALUES.LANDSCAPE.getValue();
                }
                return ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue();
            default:
                return ORIENTATION_VALUES.PORTRAIT.getValue();
        }
    }

    private static float m1486f() {
        return new TextView(SdkContext.m1258b()).getTextSize();
    }

    public static Map<String, String> m1483c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("d-device-screen-density", String.valueOf(m1481a().m1497c()));
        hashMap.put("d-device-screen-size", m1484d());
        hashMap.put("d-density-dependent-screen-size", m1485e());
        hashMap.put("d-orientation", String.valueOf(m1482b()));
        hashMap.put("d-textsize", String.valueOf(m1486f()));
        return hashMap;
    }
}
