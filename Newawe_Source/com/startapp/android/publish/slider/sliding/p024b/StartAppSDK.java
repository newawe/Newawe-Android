package com.startapp.android.publish.slider.sliding.p024b;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: com.startapp.android.publish.slider.sliding.b.b */
class StartAppSDK {

    /* renamed from: com.startapp.android.publish.slider.sliding.b.b.1 */
    static class StartAppSDK extends AccessibilityDelegate {
        final /* synthetic */ StartAppSDK f3186a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3186a = startAppSDK;
        }

        public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            return this.f3186a.m3406a(host, event);
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            this.f3186a.m3408b(host, event);
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            this.f3186a.m3405a(host, (Object) info);
        }

        public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            this.f3186a.m3409c(host, event);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            return this.f3186a.m3407a(host, child, event);
        }

        public void sendAccessibilityEvent(View host, int eventType) {
            this.f3186a.m3404a(host, eventType);
        }

        public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
            this.f3186a.m3410d(host, event);
        }
    }

    /* renamed from: com.startapp.android.publish.slider.sliding.b.b.a */
    public interface StartAppSDK {
        void m3404a(View view, int i);

        void m3405a(View view, Object obj);

        boolean m3406a(View view, AccessibilityEvent accessibilityEvent);

        boolean m3407a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m3408b(View view, AccessibilityEvent accessibilityEvent);

        void m3409c(View view, AccessibilityEvent accessibilityEvent);

        void m3410d(View view, AccessibilityEvent accessibilityEvent);
    }

    public static Object m3411a() {
        return new AccessibilityDelegate();
    }

    public static Object m3412a(StartAppSDK startAppSDK) {
        return new StartAppSDK(startAppSDK);
    }

    public static boolean m3415a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m3417b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public static void m3414a(Object obj, View view, Object obj2) {
        ((AccessibilityDelegate) obj).onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo) obj2);
    }

    public static void m3418c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean m3416a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return ((AccessibilityDelegate) obj).onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public static void m3413a(Object obj, View view, int i) {
        ((AccessibilityDelegate) obj).sendAccessibilityEvent(view, i);
    }

    public static void m3419d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        ((AccessibilityDelegate) obj).sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
