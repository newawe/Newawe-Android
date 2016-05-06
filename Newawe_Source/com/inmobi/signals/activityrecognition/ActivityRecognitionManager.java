package com.inmobi.signals.activityrecognition;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.GoogleApiClientWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.lang.time.DateUtils;

public class ActivityRecognitionManager extends IntentService {
    private static final String f1698a;
    private static Object f1699b;
    private static Object f1700c;

    /* renamed from: com.inmobi.signals.activityrecognition.ActivityRecognitionManager.a */
    private static class C0720a implements InvocationHandler {
        private C0720a() {
        }

        public void m1872a(Bundle bundle) {
            PendingIntent service = PendingIntent.getService(SdkContext.m1258b(), 0, new Intent(SdkContext.m1258b(), ActivityRecognitionManager.class), 134217728);
            try {
                Field declaredField = Class.forName("com.google.android.gms.location.ActivityRecognition").getDeclaredField("ActivityRecognitionApi");
                Class cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                Class.forName("com.google.android.gms.location.ActivityRecognitionApi").getMethod("requestActivityUpdates", new Class[]{cls, Long.TYPE, PendingIntent.class}).invoke(declaredField.get(null), new Object[]{ActivityRecognitionManager.f1700c, Integer.valueOf(DateUtils.MILLIS_IN_SECOND), service});
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f1698a, "Unable to request activity updates from ActivityRecognition client", e);
            } catch (Throwable e2) {
                Logger.m1441a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f1698a, "Unable to request activity updates from ActivityRecognition client", e2);
            } catch (Throwable e22) {
                Logger.m1441a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f1698a, "Unable to request activity updates from ActivityRecognition client", e22);
            } catch (Throwable e222) {
                Logger.m1441a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f1698a, "Unable to request activity updates from ActivityRecognition client", e222);
            } catch (Throwable e2222) {
                Logger.m1441a(InternalLogLevel.INTERNAL, ActivityRecognitionManager.f1698a, "Unable to request activity updates from ActivityRecognition client", e2222);
            }
        }

        public void m1871a(int i) {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str = "onConnected";
            str = "onConnectionSuspended";
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    m1872a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    m1871a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    static {
        f1698a = ActivityRecognitionManager.class.getSimpleName();
        f1699b = null;
        f1700c = null;
    }

    public ActivityRecognitionManager() {
        super("Activity service");
    }

    static void m1873a() {
        if (GoogleApiClientWrapper.m1949a() && f1700c == null) {
            m1874a(SdkContext.m1258b());
        }
    }

    static void m1876b() {
        if (GoogleApiClientWrapper.m1949a() && f1700c != null) {
            m1880f();
        }
    }

    private static void m1874a(Context context) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1698a, "Connecting activity recognition manager.");
        f1700c = GoogleApiClientWrapper.m1947a(context, new C0720a(), new C0720a(), "com.google.android.gms.location.ActivityRecognition");
        GoogleApiClientWrapper.m1948a(f1700c);
    }

    private static void m1880f() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1698a, "Disconnecting activity recognition manager.");
        GoogleApiClientWrapper.m1950b(f1700c);
        f1699b = null;
        f1700c = null;
    }

    protected void onHandleIntent(Intent intent) {
        if (f1700c != null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1698a, "Got activity recognition intent.");
            m1875a(intent);
        }
    }

    private static void m1875a(Intent intent) {
        try {
            Class cls = Class.forName("com.google.android.gms.location.ActivityRecognitionResult");
            if (((Boolean) cls.getMethod("hasResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent})).booleanValue()) {
                f1699b = cls.getMethod("getMostProbableActivity", (Class[]) null).invoke(cls.getMethod("extractResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent}), (Object[]) null);
            }
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "HandleIntent: Google play services not included. Cannot get current activity.", e);
        } catch (Throwable e2) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "HandleIntent: Google play services not included. Cannot get current activity.", e2);
        } catch (Throwable e22) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "HandleIntent: Google play services not included. Cannot get current activity.", e22);
        } catch (Throwable e222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "HandleIntent: Google play services not included. Cannot get current activity.", e222);
        }
    }

    public static int m1877c() {
        int intValue;
        Throwable e;
        Throwable th;
        String str = "getType";
        if (f1699b == null) {
            return -1;
        }
        try {
            intValue = ((Integer) Class.forName("com.google.android.gms.location.DetectedActivity").getMethod("getType", (Class[]) null).invoke(f1699b, (Object[]) null)).intValue();
            try {
                f1699b = null;
                Logger.m1440a(InternalLogLevel.INTERNAL, f1698a, "Getting detected activity:" + intValue);
                return intValue;
            } catch (ClassNotFoundException e2) {
                e = e2;
            } catch (NoSuchMethodException e3) {
                e = e3;
                Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            } catch (InvocationTargetException e4) {
                e = e4;
                Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            } catch (IllegalAccessException e5) {
                e = e5;
                Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
                return intValue;
            }
        } catch (Throwable e6) {
            th = e6;
            intValue = -1;
            e = th;
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e62) {
            th = e62;
            intValue = -1;
            e = th;
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e622) {
            th = e622;
            intValue = -1;
            e = th;
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        } catch (Throwable e6222) {
            th = e6222;
            intValue = -1;
            e = th;
            Logger.m1441a(InternalLogLevel.INTERNAL, f1698a, "getDetectedActivity: Google play services not included. Returning null.", e);
            return intValue;
        }
    }
}
