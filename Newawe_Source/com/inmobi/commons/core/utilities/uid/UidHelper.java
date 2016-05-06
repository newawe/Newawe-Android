package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.PublisherProvidedUserInfo;
import com.inmobi.commons.p000a.SdkContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.utilities.uid.c */
public class UidHelper {
    private static final String f1322a;
    private static final Object f1323b;
    private static UidHelper f1324c;
    private static AdvertisingIdInfo f1325d;
    private static String f1326e;

    /* renamed from: com.inmobi.commons.core.utilities.uid.c.1 */
    class UidHelper implements Runnable {
        final /* synthetic */ UidDao f1320a;
        final /* synthetic */ UidHelper f1321b;

        UidHelper(UidHelper uidHelper, UidDao uidDao) {
            this.f1321b = uidHelper;
            this.f1320a = uidDao;
        }

        public void run() {
            try {
                Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                Class cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                Object invoke = cls.getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{SdkContext.m1258b()});
                String str = (String) cls2.getDeclaredMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                UidHelper.f1325d.m1534a(str);
                this.f1320a.m1540a(str);
                Boolean bool = (Boolean) cls2.getDeclaredMethod("isLimitAdTrackingEnabled", (Class[]) null).invoke(invoke, (Object[]) null);
                UidHelper.f1325d.m1535a(bool.booleanValue());
                this.f1320a.m1541a(bool.booleanValue());
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, UidHelper.f1322a, "Failed to fetch advertising id.", e);
            } catch (Throwable e2) {
                Logger.m1441a(InternalLogLevel.INTERNAL, UidHelper.f1322a, "Failed to fetch advertising id.", e2);
            } catch (Throwable e22) {
                Logger.m1441a(InternalLogLevel.INTERNAL, UidHelper.f1322a, "Failed to fetch advertising id.", e22);
            } catch (Throwable e222) {
                Logger.m1441a(InternalLogLevel.INTERNAL, UidHelper.f1322a, "Failed to fetch advertising id.", e222);
            }
        }
    }

    static {
        f1322a = UidHelper.class.getSimpleName();
        f1323b = new Object();
    }

    public static UidHelper m1550a() {
        UidHelper uidHelper = f1324c;
        if (uidHelper == null) {
            synchronized (f1323b) {
                uidHelper = f1324c;
                if (uidHelper == null) {
                    uidHelper = new UidHelper();
                    f1324c = uidHelper;
                }
            }
        }
        return uidHelper;
    }

    private UidHelper() {
    }

    public void m1560b() {
        m1563e();
        m1562d();
        m1554r();
        m1553q();
        m1572n();
    }

    private void m1553q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.inmobi.share.id");
        SdkContext.m1258b().registerReceiver(new ImIdShareBroadCastReceiver(), intentFilter);
    }

    public String m1561c() {
        return f1326e;
    }

    private void m1554r() {
        UidDao uidDao = new UidDao();
        String c = uidDao.m1544c();
        if (c == null) {
            c = UUID.randomUUID().toString();
            uidDao.m1542b(c);
        }
        f1326e = c;
    }

    public void m1562d() {
        m1569k();
    }

    public void m1563e() {
        if (m1570l()) {
            AdvertisingIdInfo j = m1568j();
            if (j != null) {
                String b = j.m1537b();
                if (b != null) {
                    Logger.m1440a(InternalLogLevel.DEBUG, f1322a, "Publisher device Id is " + b);
                    return;
                }
                return;
            }
            return;
        }
        Logger.m1440a(InternalLogLevel.DEBUG, f1322a, "Publisher device Id is " + m1556a(m1567i()));
    }

    String m1556a(String str) {
        String str2 = "SHA-1";
        return m1557a(str, "SHA-1");
    }

    String m1564f() {
        return SchemaSymbols.ATTVAL_TRUE_1;
    }

    String m1557a(String str, String str2) {
        if (str != null) {
            try {
                if (!StringUtils.EMPTY.equals(str.trim())) {
                    MessageDigest instance = MessageDigest.getInstance(str2);
                    instance.update(str.getBytes());
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b : digest) {
                        stringBuffer.append(Integer.toString((b & MotionEventCompat.ACTION_MASK) + NodeFilter.SHOW_DOCUMENT, 16).substring(1));
                    }
                    return stringBuffer.toString();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
        return "TEST_EMULATOR";
    }

    String m1559b(String str) {
        String str2 = "MD5";
        return m1557a(str, "MD5");
    }

    String m1565g() {
        return PublisherProvidedUserInfo.m1506b();
    }

    String m1566h() {
        return PublisherProvidedUserInfo.m1509c();
    }

    String m1567i() {
        String string = Secure.getString(SdkContext.m1258b().getContentResolver(), "android_id");
        if (string == null) {
            return System.getString(SdkContext.m1258b().getContentResolver(), "android_id");
        }
        return string;
    }

    AdvertisingIdInfo m1568j() {
        return f1325d;
    }

    void m1569k() {
        String str = "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info";
        str = "getId";
        str = "isLimitAdTrackingEnabled";
        str = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
        str = "getAdvertisingIdInfo";
        UidDao uidDao = new UidDao();
        f1325d = new AdvertisingIdInfo();
        f1325d.m1534a(uidDao.m1538a());
        f1325d.m1535a(uidDao.m1543b());
        new Thread(new UidHelper(this, uidDao)).start();
    }

    boolean m1570l() {
        try {
            return GooglePlayServicesUtil.isGooglePlayServicesAvailable(SdkContext.m1258b()) == 0;
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean m1571m() {
        AdvertisingIdInfo j = UidHelper.m1550a().m1568j();
        if (j != null) {
            return j.m1536a();
        }
        return false;
    }

    protected void m1572n() {
        UidDao uidDao = new UidDao();
        String d = uidDao.m1546d();
        long e = uidDao.m1548e();
        if (d == null) {
            d = UUID.randomUUID().toString();
            uidDao.m1545c(d);
            uidDao.m1542b(m1561c());
            uidDao.m1547d(m1561c());
        }
        if (e == 0) {
            uidDao.m1539a(System.currentTimeMillis());
        }
        Intent intent = new Intent();
        intent.setAction("com.inmobi.share.id");
        intent.putExtra("imid", d);
        intent.putExtra("appendedid", uidDao.m1549f());
        intent.putExtra("imidts", uidDao.m1548e());
        intent.putExtra("appid", uidDao.m1544c());
        SdkContext.m1258b().sendBroadcast(intent);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1322a, "Generating and broadcasting IDs. ID:" + d + " AID:" + uidDao.m1549f());
    }

    String m1555a(Context context) {
        return new UidDao().m1546d();
    }

    String m1558b(Context context) {
        UidDao uidDao = new UidDao();
        try {
            JSONObject jSONObject = new JSONObject();
            CharSequence c = uidDao.m1544c();
            if (c != null) {
                jSONObject.put("p", c);
            }
            Object f = uidDao.m1549f();
            if (f != null && f.contains(c)) {
                f = f.replace(c, StringUtils.EMPTY);
            }
            if (!(f == null || f.trim().length() == 0)) {
                if (f.charAt(0) == ',') {
                    f = f.substring(1);
                }
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(f);
                jSONObject.put("s", jSONArray);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
