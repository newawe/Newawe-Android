package com.inmobi.ads;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.AdConfig.AdConfig;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.VisibilityTracker.VisibilityTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* renamed from: com.inmobi.ads.l */
class ImpressionTracker {
    private static final String f1116a;
    @NonNull
    private final VisibilityTracker f1117b;
    @NonNull
    private final Map<View, NativeAdUnit> f1118c;
    @NonNull
    private final Map<View, TimestampWrapper<NativeAdUnit>> f1119d;
    @NonNull
    private final Handler f1120e;
    @NonNull
    private final ImpressionTracker f1121f;
    @NonNull
    private final VisibilityTracker f1122g;
    @Nullable
    private VisibilityTracker f1123h;
    @NonNull
    private AdConfig f1124i;

    /* renamed from: com.inmobi.ads.l.a */
    class ImpressionTracker implements Runnable {
        final /* synthetic */ ImpressionTracker f1114a;
        @NonNull
        private final ArrayList<View> f1115b;

        ImpressionTracker(ImpressionTracker impressionTracker) {
            this.f1114a = impressionTracker;
            this.f1115b = new ArrayList();
        }

        public void run() {
            for (Entry entry : this.f1114a.f1119d.entrySet()) {
                View view = (View) entry.getKey();
                TimestampWrapper timestampWrapper = (TimestampWrapper) entry.getValue();
                if (this.f1114a.f1122g.m1238a(timestampWrapper.f1127b, this.f1114a.f1124i.m1188b())) {
                    ((NativeAdUnit) timestampWrapper.f1126a).m5196y();
                    this.f1115b.add(view);
                }
            }
            Iterator it = this.f1115b.iterator();
            while (it.hasNext()) {
                this.f1114a.m1234a((View) it.next());
            }
            this.f1115b.clear();
            if (!this.f1114a.f1119d.isEmpty()) {
                this.f1114a.m1237c();
            }
        }
    }

    /* renamed from: com.inmobi.ads.l.1 */
    class ImpressionTracker implements VisibilityTracker {
        final /* synthetic */ ImpressionTracker f3804a;

        ImpressionTracker(ImpressionTracker impressionTracker) {
            this.f3804a = impressionTracker;
        }

        public void m4438a(@NonNull List<View> list, @NonNull List<View> list2) {
            for (View view : list) {
                NativeAdUnit nativeAdUnit = (NativeAdUnit) this.f3804a.f1118c.get(view);
                if (nativeAdUnit == null) {
                    this.f3804a.m1234a(view);
                } else {
                    TimestampWrapper timestampWrapper = (TimestampWrapper) this.f3804a.f1119d.get(view);
                    if (timestampWrapper == null || !nativeAdUnit.equals(timestampWrapper.f1126a)) {
                        this.f3804a.f1119d.put(view, new TimestampWrapper(nativeAdUnit));
                    }
                }
            }
            for (View view2 : list2) {
                this.f3804a.f1119d.remove(view2);
            }
            this.f3804a.m1237c();
        }
    }

    static {
        f1116a = ImpressionTracker.class.getSimpleName();
    }

    ImpressionTracker(AdConfig adConfig) {
        this(new WeakHashMap(), new WeakHashMap(), new VisibilityTracker(), new VisibilityTracker(adConfig), new Handler(), adConfig);
    }

    ImpressionTracker(@NonNull Map<View, NativeAdUnit> map, @NonNull Map<View, TimestampWrapper<NativeAdUnit>> map2, @NonNull VisibilityTracker visibilityTracker, @NonNull VisibilityTracker visibilityTracker2, @NonNull Handler handler, @NonNull AdConfig adConfig) {
        this.f1118c = map;
        this.f1119d = map2;
        this.f1122g = visibilityTracker;
        this.f1117b = visibilityTracker2;
        this.f1124i = adConfig;
        this.f1123h = new ImpressionTracker(this);
        this.f1117b.m1249a(this.f1123h);
        this.f1120e = handler;
        this.f1121f = new ImpressionTracker(this);
    }

    void m1235a(View view, @NonNull NativeAdUnit nativeAdUnit) {
        if (this.f1118c.get(view) != nativeAdUnit) {
            m1234a(view);
            if (AdState.STATE_RENDERED != nativeAdUnit.m4360g()) {
                this.f1118c.put(view, nativeAdUnit);
                this.f1117b.m1248a(view, this.f1124i.m1187a());
            }
        }
    }

    void m1234a(View view) {
        this.f1118c.remove(view);
        m1230b(view);
        this.f1117b.m1247a(view);
    }

    void m1233a() {
        this.f1118c.clear();
        this.f1119d.clear();
        this.f1117b.m1246a();
        this.f1120e.removeMessages(0);
    }

    void m1236b() {
        m1233a();
        this.f1117b.m1250b();
        this.f1123h = null;
    }

    void m1237c() {
        if (!this.f1120e.hasMessages(0)) {
            this.f1120e.postDelayed(this.f1121f, (long) this.f1124i.m1190d());
        }
    }

    private void m1230b(View view) {
        this.f1119d.remove(view);
    }
}
