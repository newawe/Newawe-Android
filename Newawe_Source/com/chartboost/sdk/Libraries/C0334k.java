package com.chartboost.sdk.Libraries;

import android.app.Activity;
import android.content.Context;
import com.chartboost.sdk.C0351c;
import java.lang.ref.WeakReference;

/* renamed from: com.chartboost.sdk.Libraries.k */
public final class C0334k extends WeakReference<Activity> {
    private static C0334k f188b;
    private int f189a;

    private C0334k(Activity activity) {
        super(activity);
        this.f189a = activity.hashCode();
    }

    public static C0334k m245a(Activity activity) {
        if (f188b == null || f188b.f189a != activity.hashCode()) {
            f188b = new C0334k(activity);
        }
        return f188b;
    }

    public boolean m249b(Activity activity) {
        if (activity != null && activity.hashCode() == this.f189a) {
            return true;
        }
        return false;
    }

    public boolean m247a(C0334k c0334k) {
        if (c0334k != null && c0334k.m246a() == this.f189a) {
            return true;
        }
        return false;
    }

    public int m246a() {
        return this.f189a;
    }

    public int hashCode() {
        return m246a();
    }

    public Context m248b() {
        Context context = (Context) get();
        if (context == null) {
            return C0351c.m378y();
        }
        return context;
    }
}
