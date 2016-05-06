package com.inmobi.ads;

import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.AdConfig.AdConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* renamed from: com.inmobi.ads.q */
class VisibilityTracker {
    private static final String f1134a;
    @NonNull
    private final ArrayList<View> f1135b;
    private long f1136c;
    @NonNull
    private final Map<View, VisibilityTracker> f1137d;
    @NonNull
    private final VisibilityTracker f1138e;
    @Nullable
    private VisibilityTracker f1139f;
    @NonNull
    private final VisibilityTracker f1140g;
    @NonNull
    private final Handler f1141h;
    private boolean f1142i;
    @NonNull
    private AdConfig f1143j;

    /* renamed from: com.inmobi.ads.q.a */
    static class VisibilityTracker {
        int f1128a;
        long f1129b;

        VisibilityTracker() {
        }
    }

    /* renamed from: com.inmobi.ads.q.b */
    static class VisibilityTracker {
        private final Rect f1130a;

        VisibilityTracker() {
            this.f1130a = new Rect();
        }

        boolean m1238a(long j, int i) {
            return SystemClock.uptimeMillis() - j >= ((long) i);
        }

        boolean m1239a(@Nullable View view, int i) {
            if (view == null || view.getVisibility() != 0 || view.getParent() == null || !view.getGlobalVisibleRect(this.f1130a)) {
                return false;
            }
            long height = ((long) this.f1130a.height()) * ((long) this.f1130a.width());
            long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
            if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.inmobi.ads.q.c */
    class VisibilityTracker implements Runnable {
        final /* synthetic */ VisibilityTracker f1131a;
        @NonNull
        private final ArrayList<View> f1132b;
        @NonNull
        private final ArrayList<View> f1133c;

        VisibilityTracker(VisibilityTracker visibilityTracker) {
            this.f1131a = visibilityTracker;
            this.f1133c = new ArrayList();
            this.f1132b = new ArrayList();
        }

        public void run() {
            this.f1131a.f1142i = false;
            for (Entry entry : this.f1131a.f1137d.entrySet()) {
                View view = (View) entry.getKey();
                if (this.f1131a.f1138e.m1239a(view, ((VisibilityTracker) entry.getValue()).f1128a)) {
                    this.f1132b.add(view);
                } else {
                    this.f1133c.add(view);
                }
            }
            if (this.f1131a.f1139f != null) {
                this.f1131a.f1139f.m1240a(this.f1132b, this.f1133c);
            }
            this.f1132b.clear();
            this.f1133c.clear();
            this.f1131a.m1251c();
        }
    }

    /* renamed from: com.inmobi.ads.q.d */
    interface VisibilityTracker {
        void m1240a(List<View> list, List<View> list2);
    }

    static {
        f1134a = VisibilityTracker.class.getSimpleName();
    }

    public VisibilityTracker(AdConfig adConfig) {
        this(new WeakHashMap(10), new VisibilityTracker(), new Handler(), adConfig);
    }

    VisibilityTracker(@NonNull Map<View, VisibilityTracker> map, @NonNull VisibilityTracker visibilityTracker, @NonNull Handler handler, @NonNull AdConfig adConfig) {
        this.f1136c = 0;
        this.f1137d = map;
        this.f1138e = visibilityTracker;
        this.f1141h = handler;
        this.f1140g = new VisibilityTracker(this);
        this.f1143j = adConfig;
        this.f1135b = new ArrayList(50);
    }

    void m1249a(@Nullable VisibilityTracker visibilityTracker) {
        this.f1139f = visibilityTracker;
    }

    void m1248a(@NonNull View view, int i) {
        VisibilityTracker visibilityTracker = (VisibilityTracker) this.f1137d.get(view);
        if (visibilityTracker == null) {
            visibilityTracker = new VisibilityTracker();
            this.f1137d.put(view, visibilityTracker);
            m1251c();
        }
        visibilityTracker.f1128a = i;
        visibilityTracker.f1129b = this.f1136c;
        this.f1136c++;
        if (this.f1136c % 50 == 0) {
            m1242a(this.f1136c - 50);
        }
    }

    private void m1242a(long j) {
        for (Entry entry : this.f1137d.entrySet()) {
            if (((VisibilityTracker) entry.getValue()).f1129b < j) {
                this.f1135b.add(entry.getKey());
            }
        }
        Iterator it = this.f1135b.iterator();
        while (it.hasNext()) {
            m1247a((View) it.next());
        }
        this.f1135b.clear();
    }

    void m1247a(@NonNull View view) {
        this.f1137d.remove(view);
    }

    void m1246a() {
        this.f1137d.clear();
        this.f1141h.removeMessages(0);
        this.f1142i = false;
    }

    void m1250b() {
        m1246a();
        this.f1139f = null;
    }

    void m1251c() {
        if (!this.f1142i) {
            this.f1142i = true;
            this.f1141h.postDelayed(this.f1140g, (long) this.f1143j.m1189c());
        }
    }
}
