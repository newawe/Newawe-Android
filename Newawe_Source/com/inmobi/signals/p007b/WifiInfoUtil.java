package com.inmobi.signals.p007b;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.SignalsComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.inmobi.signals.b.b */
public class WifiInfoUtil {
    public static WifiInfo m1900a() {
        if (!WifiInfoUtil.m1911e() || !SignalsComponent.m4580a().m4585e().m2072l()) {
            return null;
        }
        int j = SignalsComponent.m4580a().m4585e().m2070j();
        return WifiInfoUtil.m1902a(WifiInfoUtil.m1904a(j), WifiInfoUtil.m1908b(j));
    }

    public static Map<String, String> m1907b() {
        WifiInfo a = WifiInfoUtil.m1900a();
        Map hashMap = new HashMap();
        if (a != null) {
            hashMap.put("c-ap-bssid", String.valueOf(a.m1891a()));
        }
        return hashMap;
    }

    private static boolean m1904a(int i) {
        return !WifiInfoUtil.m1905a(i, 2);
    }

    private static boolean m1908b(int i) {
        return WifiInfoUtil.m1905a(i, 1);
    }

    private static boolean m1911e() {
        String str = "android.permission.ACCESS_WIFI_STATE";
        if (SdkContext.m1258b().checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
            return false;
        }
        return true;
    }

    private static WifiInfo m1902a(boolean z, boolean z2) {
        WifiInfo connectionInfo = ((WifiManager) SdkContext.m1258b().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String bssid = connectionInfo.getBSSID();
        String ssid = connectionInfo.getSSID();
        if (bssid == null || WifiInfoUtil.m1906a(z, ssid)) {
            return null;
        }
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.m1893a(WifiInfoUtil.m1898a(bssid));
        if (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        if (z2) {
            ssid = null;
        }
        wifiInfo.m1894a(ssid);
        wifiInfo.m1892a(connectionInfo.getRssi());
        wifiInfo.m1896b(connectionInfo.getIpAddress());
        return wifiInfo;
    }

    private static boolean m1906a(boolean z, String str) {
        return z && str != null && str.endsWith("_nomap");
    }

    private static long m1898a(String str) {
        String str2 = ":";
        str2 = "\\:";
        String[] split = str.split("\\:");
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return WifiInfoUtil.m1899a(bArr);
    }

    private static long m1899a(byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            return 0;
        }
        return ((((WifiInfoUtil.m1897a(bArr[5]) | (WifiInfoUtil.m1897a(bArr[4]) << 8)) | (WifiInfoUtil.m1897a(bArr[3]) << 16)) | (WifiInfoUtil.m1897a(bArr[2]) << 24)) | (WifiInfoUtil.m1897a(bArr[1]) << 32)) | (WifiInfoUtil.m1897a(bArr[0]) << 40);
    }

    private static long m1897a(byte b) {
        return ((long) b) & 255;
    }

    private static boolean m1905a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean m1909c() {
        Context b = SdkContext.m1258b();
        for (String checkCallingOrSelfPermission : new String[]{"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"}) {
            if (b.checkCallingOrSelfPermission(checkCallingOrSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<WifiInfo> m1903a(List<ScanResult> list) {
        int j = SignalsComponent.m4580a().m4585e().m2070j();
        boolean a = WifiInfoUtil.m1904a(j);
        boolean b = WifiInfoUtil.m1908b(j);
        List arrayList = new ArrayList();
        if (list != null) {
            for (ScanResult scanResult : list) {
                if (!WifiInfoUtil.m1906a(a, scanResult.SSID)) {
                    arrayList.add(WifiInfoUtil.m1901a(scanResult, b));
                }
            }
        }
        return arrayList;
    }

    private static WifiInfo m1901a(ScanResult scanResult, boolean z) {
        String str = null;
        if (scanResult == null) {
            return null;
        }
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.m1893a(WifiInfoUtil.m1898a(scanResult.BSSID));
        if (!z) {
            str = scanResult.SSID;
        }
        wifiInfo.m1894a(str);
        wifiInfo.m1892a(scanResult.level);
        return wifiInfo;
    }

    public static Map<String, String> m1910d() {
        ArrayList arrayList = (ArrayList) WifiScanner.m1914a();
        Map hashMap = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            hashMap.put("v-ap-bssid", String.valueOf(((WifiInfo) arrayList.get(arrayList.size() - 1)).m1891a()));
        }
        return hashMap;
    }
}
