package com.startapp.android.publish.banner.banner3d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import com.startapp.android.publish.banner.Banner;
import com.startapp.android.publish.banner.BannerOptions;
import com.startapp.android.publish.banner.StartAppSDK;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class Banner3DSize {

    /* compiled from: StartAppSDK */
    public enum Size {
        XXSMALL(new StartAppSDK(280, 50)),
        XSMALL(new StartAppSDK(HttpStatus.SC_MULTIPLE_CHOICES, 50)),
        SMALL(new StartAppSDK(320, 50)),
        MEDIUM(new StartAppSDK(468, 60)),
        LARGE(new StartAppSDK(728, 90)),
        XLARGE(new StartAppSDK(NodeFilter.SHOW_DOCUMENT_FRAGMENT, 90));
        
        private StartAppSDK size;

        private Size(StartAppSDK size) {
            this.size = size;
        }

        public StartAppSDK getSize() {
            return this.size;
        }
    }

    public static boolean m2654a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D, StartAppSDK startAppSDK) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "============== Optimize Size ==========");
        StartAppSDK a = m2652a(context, viewParent, bannerOptions, banner3D);
        startAppSDK.m2647a(a.m2645a(), a.m2648b());
        boolean z = false;
        for (Size size : Size.values()) {
            if (size.getSize().m2645a() <= a.m2645a() && size.getSize().m2648b() <= a.m2648b()) {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "BannerSize [" + size.getSize().m2645a() + "," + size.getSize().m2648b() + "]");
                bannerOptions.m2628a(size.getSize().m2645a(), size.getSize().m2648b());
                z = true;
            }
        }
        if (!z) {
            bannerOptions.m2628a(0, 0);
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "============== Optimize Size [" + z + "] ==========");
        return z;
    }

    private static StartAppSDK m2652a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D) {
        Object obj = null;
        Point point = new Point();
        point.x = bannerOptions.m2631d();
        point.y = bannerOptions.m2632e();
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "=============== set Application Size ===========");
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().width > 0) {
            point.x = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, banner3D.getLayoutParams().width + 1);
        }
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().height > 0) {
            point.y = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, banner3D.getLayoutParams().height + 1);
        }
        if (banner3D.getLayoutParams() == null || banner3D.getLayoutParams().width <= 0 || banner3D.getLayoutParams().height <= 0) {
            if (context instanceof Activity) {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "Context is Activity");
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view;
                    View view2 = (View) viewParent;
                    if (view2 instanceof Banner) {
                        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "Parent is instance of Wrapper Banner");
                        view = (View) view2.getParent();
                    } else {
                        view = view2;
                    }
                    View view3 = view;
                    Object obj2 = null;
                    while (view3 != null && (view3.getMeasuredWidth() <= 0 || view3.getMeasuredHeight() <= 0)) {
                        Object obj3;
                        if (view3.getMeasuredWidth() <= 0 || obj != null) {
                            obj3 = obj;
                        } else {
                            m2655b(context, point, view3);
                            obj3 = 1;
                        }
                        if (view3.getMeasuredHeight() <= 0 || obj2 != null) {
                            obj = obj2;
                        } else {
                            m2653a(context, point, view3);
                            obj = 1;
                        }
                        view3 = (View) view3.getParent();
                        obj2 = obj;
                        obj = obj3;
                    }
                    if (view3 == null) {
                        m2656c(context, point, decorView);
                    } else {
                        if (obj == null) {
                            m2655b(context, point, view3);
                        }
                        if (obj2 == null) {
                            m2653a(context, point, view3);
                        }
                    }
                } catch (Exception e) {
                    m2656c(context, point, decorView);
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "Exception occoured");
                }
            } else {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "Context not Activity, get max win size");
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                if (windowManager != null) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2863a(context, windowManager, point);
                }
            }
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DSize", 3, "============ exit Application Size [" + point.x + "," + point.y + "] =========");
        return new StartAppSDK(point.x, point.y);
    }

    private static void m2653a(Context context, Point point, View view) {
        point.y = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop());
    }

    private static void m2655b(Context context, Point point, View view) {
        point.x = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight());
    }

    private static void m2656c(Context context, Point point, View view) {
        point.x = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, view.getMeasuredWidth());
        point.y = com.startapp.android.publish.p022h.StartAppSDK.m2973b(context, view.getMeasuredHeight());
    }
}
