package com.inmobi.signals.activityrecognition;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.SignalsComponent;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.signals.activityrecognition.b */
public class ActivityRecognitionSampler {
    private static final String f1704a;
    private static final Object f1705b;
    private static volatile ActivityRecognitionSampler f1706c;
    private static List<ActivityInfo> f1707d;
    private HandlerThread f1708e;
    private Handler f1709f;

    /* renamed from: com.inmobi.signals.activityrecognition.b.a */
    static class ActivityRecognitionSampler extends Handler {
        ActivityRecognitionSampler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case DurationDV.DURATION_TYPE /*0*/:
                    int c = ActivityRecognitionManager.m1877c();
                    Logger.m1440a(InternalLogLevel.INTERNAL, ActivityRecognitionSampler.f1704a, "Polling for ActivityRecognition. Detected activity:" + c);
                    if (c != -1) {
                        ActivityRecognitionSampler.f1707d.add(new ActivityInfo(c, System.currentTimeMillis()));
                    }
                    if (ActivityRecognitionSampler.f1707d.size() < SignalsComponent.m4580a().m4585e().m2079s()) {
                        sendEmptyMessageDelayed(0, (long) (SignalsComponent.m4580a().m4585e().m2078r() * DateUtils.MILLIS_IN_SECOND));
                    }
                default:
            }
        }
    }

    static {
        f1704a = ActivityRecognitionSampler.class.getSimpleName();
        f1705b = new Object();
    }

    public static ActivityRecognitionSampler m1882a() {
        ActivityRecognitionSampler activityRecognitionSampler = f1706c;
        if (activityRecognitionSampler == null) {
            synchronized (f1705b) {
                activityRecognitionSampler = f1706c;
                if (activityRecognitionSampler == null) {
                    activityRecognitionSampler = new ActivityRecognitionSampler();
                    f1706c = activityRecognitionSampler;
                }
            }
        }
        return activityRecognitionSampler;
    }

    private ActivityRecognitionSampler() {
        f1707d = new ArrayList();
        this.f1708e = new HandlerThread("ActivityRecognitionSampler");
        this.f1708e.start();
        this.f1709f = new ActivityRecognitionSampler(this.f1708e.getLooper());
    }

    public void m1887b() {
        if (ActivityRecognitionSampler.m1885h() && ActivityRecognitionSampler.m1886i() && !this.f1709f.hasMessages(0)) {
            ActivityRecognitionManager.m1873a();
            this.f1709f.sendEmptyMessage(0);
        }
    }

    public void m1888c() {
        if (ActivityRecognitionSampler.m1885h() && ActivityRecognitionSampler.m1886i() && this.f1709f.hasMessages(0)) {
            ActivityRecognitionManager.m1876b();
            this.f1709f.removeMessages(0);
        }
    }

    public List<ActivityInfo> m1889d() {
        return f1707d;
    }

    public void m1890e() {
        f1707d = new ArrayList();
    }

    private static boolean m1885h() {
        String str = "com.google.android.gms.permission.ACTIVITY_RECOGNITION";
        if (SdkContext.m1258b().checkCallingOrSelfPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION") == 0) {
            return true;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1704a, "Activity recognition sampling did not work due to missing permission.");
        return false;
    }

    private static boolean m1886i() {
        if (SdkContext.m1258b().getPackageManager().queryIntentServices(new Intent(SdkContext.m1258b(), ActivityRecognitionManager.class), AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
            return true;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1704a, "Activity recognition sampling did not work due to missing service in manifest.");
        return false;
    }
}
