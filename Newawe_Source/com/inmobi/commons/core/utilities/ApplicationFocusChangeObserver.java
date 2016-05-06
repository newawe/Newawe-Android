package com.inmobi.commons.core.utilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.commons.core.utilities.a */
public class ApplicationFocusChangeObserver {
    private static final String f1265a;
    private static List<ApplicationFocusChangeObserver> f1266b;
    private static HandlerThread f1267c;
    private static Application f1268d;
    private static final Object f1269e;
    private static volatile ApplicationFocusChangeObserver f1270f;

    /* renamed from: com.inmobi.commons.core.utilities.a.1 */
    class ApplicationFocusChangeObserver implements InvocationHandler {
        final /* synthetic */ ApplicationFocusChangeObserver f1256a;
        private final Handler f1257b;

        ApplicationFocusChangeObserver(ApplicationFocusChangeObserver applicationFocusChangeObserver) {
            this.f1256a = applicationFocusChangeObserver;
            this.f1257b = new ApplicationFocusChangeObserver(ApplicationFocusChangeObserver.f1267c.getLooper());
        }

        public void m1442a(Activity activity) {
            this.f1257b.sendEmptyMessageDelayed(DateUtils.SEMI_MONTH, 3000);
        }

        public void m1443b(Activity activity) {
            this.f1257b.removeMessages(DateUtils.SEMI_MONTH);
            this.f1257b.sendEmptyMessage(1002);
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr != null) {
                if (method.getName().equals("onActivityPaused")) {
                    m1442a((Activity) objArr[0]);
                } else if (method.getName().equals("onActivityResumed")) {
                    m1443b((Activity) objArr[0]);
                }
            }
            return null;
        }
    }

    /* renamed from: com.inmobi.commons.core.utilities.a.a */
    static class ApplicationFocusChangeObserver extends Handler {
        boolean f1258a;

        public ApplicationFocusChangeObserver(Looper looper) {
            super(looper);
            this.f1258a = true;
        }

        public void handleMessage(Message message) {
            if (message.what == DateUtils.SEMI_MONTH && this.f1258a) {
                this.f1258a = false;
                ApplicationFocusChangeObserver.m1465b(Boolean.valueOf(false));
                Logger.m1440a(InternalLogLevel.INTERNAL, ApplicationFocusChangeObserver.f1265a, "App has gone to background.");
            } else if (message.what == 1002 && !this.f1258a) {
                this.f1258a = true;
                ApplicationFocusChangeObserver.m1465b(Boolean.valueOf(true));
                Logger.m1440a(InternalLogLevel.INTERNAL, ApplicationFocusChangeObserver.f1265a, "App has come to foreground.");
            }
        }
    }

    /* renamed from: com.inmobi.commons.core.utilities.a.b */
    public interface ApplicationFocusChangeObserver {
        void m1444a(boolean z);
    }

    static {
        f1265a = ApplicationFocusChangeObserver.class.getSimpleName();
        f1266b = new ArrayList();
        f1267c = null;
        f1269e = new Object();
    }

    public static ApplicationFocusChangeObserver m1462a() {
        ApplicationFocusChangeObserver applicationFocusChangeObserver = f1270f;
        if (applicationFocusChangeObserver == null) {
            synchronized (f1269e) {
                applicationFocusChangeObserver = f1270f;
                if (applicationFocusChangeObserver == null) {
                    applicationFocusChangeObserver = new ApplicationFocusChangeObserver();
                    f1270f = applicationFocusChangeObserver;
                }
            }
        }
        return applicationFocusChangeObserver;
    }

    private ApplicationFocusChangeObserver() {
        f1268d = (Application) SdkContext.m1258b();
    }

    @TargetApi(14)
    private void m1467d() {
        String str = "registerActivityLifecycleCallbacks";
        str = "ActivityLifecycleCallbacks";
        str = "onActivityPaused";
        str = "onActivityResumed";
        f1267c = new HandlerThread("ApplicationFocusChangeObserverHandler");
        f1267c.start();
        Class[] declaredClasses = Application.class.getDeclaredClasses();
        Class cls = null;
        int length = declaredClasses.length;
        int i = 0;
        while (i < length) {
            Class cls2 = declaredClasses[i];
            if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                new Class[1][0] = cls2;
            } else {
                cls2 = cls;
            }
            i++;
            cls = cls2;
        }
        if (Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ApplicationFocusChangeObserver(this)) != null) {
            try {
                Application.class.getMethod("registerActivityLifecycleCallbacks", new Class[]{cls}).invoke(f1268d, new Object[]{r0});
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1265a, "Error while registering activity life cycle listener.", e);
            } catch (Throwable e2) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1265a, "Error while registering activity life cycle listener.", e2);
            } catch (Throwable e22) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1265a, "Error while registering activity life cycle listener.", e22);
            }
        }
    }

    public void m1468a(ApplicationFocusChangeObserver applicationFocusChangeObserver) {
        if (VERSION.SDK_INT >= 14) {
            f1266b.add(applicationFocusChangeObserver);
            if (f1266b.size() == 1) {
                m1467d();
            }
        }
    }

    private static void m1465b(Boolean bool) {
        for (ApplicationFocusChangeObserver a : f1266b) {
            a.m1444a(bool.booleanValue());
        }
    }
}
