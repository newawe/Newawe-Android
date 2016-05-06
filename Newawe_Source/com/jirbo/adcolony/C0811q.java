package com.jirbo.adcolony;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: com.jirbo.adcolony.q */
class C0811q {
    public static final int f2571a = 30;
    public static String f2572b;
    public static String f2573c;
    public static String f2574d;

    C0811q() {
    }

    static {
        f2572b = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  x          xxxxxxx                          xxxx x                          xxxxx";
        f2573c = "0123456789ABCDEF";
        f2574d = "0123456789abcdef";
    }

    static boolean m2484a() {
        return C0811q.m2485a(null);
    }

    static boolean m2485a(Activity activity) {
        if (activity == null) {
            activity = C0745a.f1979P;
        }
        if (activity == null) {
            C0777l.f2239a.m2353b((Object) "Null Activity");
            return false;
        } else if (C0745a.f1977N) {
            return true;
        } else {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.getType() == 1;
        }
    }

    static boolean m2487b() {
        return C0811q.m2488b(null);
    }

    static boolean m2488b(Activity activity) {
        if (activity == null) {
            activity = C0745a.f1979P;
        }
        if (activity == null || C0745a.f1977N) {
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        boolean z = type == 0 || type >= 2;
        return z;
    }

    static boolean m2489c() {
        return C0811q.m2490c(null);
    }

    static boolean m2490c(Activity activity) {
        return C0811q.m2485a(activity) || C0811q.m2488b(activity);
    }

    public static String m2491d() {
        if (C0811q.m2484a()) {
            return "wifi";
        }
        if (C0811q.m2487b()) {
            return "cell";
        }
        return "offline";
    }

    public static String m2483a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= '\u0080' || f2572b.charAt(charAt) != ' ') {
                stringBuilder.append('%');
                int i2 = (charAt >> 4) & 15;
                int i3 = charAt & 15;
                if (i2 < 10) {
                    stringBuilder.append((char) (i2 + 48));
                } else {
                    stringBuilder.append((char) ((i2 + 65) - 10));
                }
                if (i3 < 10) {
                    stringBuilder.append((char) (i3 + 48));
                } else {
                    stringBuilder.append((char) ((i3 + 65) - 10));
                }
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }

    public static int m2482a(char c) {
        int indexOf = f2573c.indexOf(c);
        if (indexOf >= 0) {
            return indexOf;
        }
        indexOf = f2574d.indexOf(c);
        return indexOf < 0 ? 0 : indexOf;
    }

    public static String m2486b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = str.charAt(i);
            if (charAt == '%') {
                char charAt2;
                if (i + 1 < length) {
                    charAt2 = str.charAt(i + 1);
                } else {
                    charAt2 = '0';
                }
                if (i + 2 < length) {
                    charAt = str.charAt(i + 2);
                } else {
                    charAt = '0';
                }
                i += 2;
                stringBuilder.append((char) (C0811q.m2482a(charAt) | (C0811q.m2482a(charAt2) << 8)));
                i2 = i;
            } else {
                stringBuilder.append(charAt);
                i2 = i;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }
}
