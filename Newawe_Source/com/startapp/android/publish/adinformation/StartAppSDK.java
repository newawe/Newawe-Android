package com.startapp.android.publish.adinformation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.startapp.android.publish.adinformation.AdInformationConfig.ImageResourceType;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.startapp.android.publish.adinformation.a */
public class StartAppSDK implements OnClickListener {
    private Context f2651a;
    private StartAppSDK f2652b;
    private RelativeLayout f2653c;
    private WebView f2654d;
    private Dialog f2655e;
    private Placement f2656f;
    private RelativeLayout f2657g;
    private Handler f2658h;
    private StartAppSDK f2659i;
    private boolean f2660j;
    private StartAppSDK f2661k;
    private AdInformationConfig f2662l;
    private StartAppSDK f2663m;
    private Runnable f2664n;
    private Runnable f2665o;

    /* renamed from: com.startapp.android.publish.adinformation.a.1 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2638a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2638a = startAppSDK;
        }

        public void run() {
            this.f2638a.m2550c();
            this.f2638a.f2663m.m2583a(this.f2638a.f2651a, true);
            this.f2638a.f2662l.m2521a(this.f2638a.f2651a, true);
            this.f2638a.m2538a(false);
        }
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2639a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2639a = startAppSDK;
        }

        public void run() {
            this.f2639a.m2550c();
            this.f2639a.f2663m.m2583a(this.f2639a.f2651a, false);
            this.f2639a.f2662l.m2521a(this.f2639a.f2651a, true);
            this.f2639a.m2538a(false);
        }
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.3 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2640a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2640a = startAppSDK;
        }

        public void run() {
            this.f2640a.f2653c.removeView(this.f2640a.f2657g);
        }
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ ViewGroup f2641a;
        final /* synthetic */ LayoutParams f2642b;
        final /* synthetic */ StartAppSDK f2643c;

        StartAppSDK(StartAppSDK startAppSDK, ViewGroup viewGroup, LayoutParams layoutParams) {
            this.f2643c = startAppSDK;
            this.f2641a = viewGroup;
            this.f2642b = layoutParams;
        }

        public void run() {
            this.f2643c.f2653c.addView(this.f2641a, this.f2642b);
        }
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.5 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f2644a;

        static {
            f2644a = new int[StartAppSDK.values().length];
            try {
                f2644a[StartAppSDK.LAYOUT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2644a[StartAppSDK.REGULAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.a */
    public enum StartAppSDK {
        REGULAR,
        LAYOUT
    }

    /* renamed from: com.startapp.android.publish.adinformation.a.b */
    public enum StartAppSDK {
        SMALL(ImageResourceType.INFO_S, ImageResourceType.INFO_EX_S),
        LARGE(ImageResourceType.INFO_L, ImageResourceType.INFO_EX_L);
        
        private ImageResourceType infoExtendedType;
        private ImageResourceType infoType;

        private StartAppSDK(ImageResourceType imageResourceType, ImageResourceType imageResourceType2) {
            this.infoType = imageResourceType;
            this.infoExtendedType = imageResourceType2;
        }

        public ImageResourceType m2531a() {
            return this.infoType;
        }

        public ImageResourceType m2532b() {
            return this.infoExtendedType;
        }
    }

    public StartAppSDK(Context context, StartAppSDK startAppSDK, Placement placement, StartAppSDK startAppSDK2) {
        this.f2655e = null;
        this.f2658h = new Handler();
        this.f2659i = StartAppSDK.REGULAR;
        this.f2660j = false;
        this.f2664n = new StartAppSDK(this);
        this.f2665o = new StartAppSDK(this);
        this.f2651a = context;
        this.f2656f = placement;
        this.f2661k = startAppSDK2;
        this.f2662l = m2543d();
        this.f2663m = this.f2662l.m2527e();
        this.f2652b = new StartAppSDK(context, startAppSDK, placement, startAppSDK2);
        this.f2652b.setOnInfoClickListener(this);
    }

    public void m2548a(RelativeLayout relativeLayout) {
        boolean a;
        if (m2545e() == null || !m2545e().m2557e()) {
            a = m2543d().m2523a(this.f2651a);
        } else {
            a = m2545e().m2554b();
        }
        if (a) {
            this.f2653c = relativeLayout;
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            if (m2545e() == null || !m2545e().m2556d()) {
                m2543d().m2519a(this.f2656f).addRules(layoutParams);
            } else {
                m2545e().m2555c().addRules(layoutParams);
            }
            this.f2653c.addView(this.f2652b, layoutParams);
        }
    }

    public View m2547a() {
        return this.f2652b;
    }

    public boolean m2549b() {
        return this.f2660j;
    }

    public static AdInformationConfig m2534a(Context context) {
        return MetaData.getInstance().getAdInformationConfig();
    }

    private AdInformationConfig m2543d() {
        return MetaData.getInstance().getAdInformationConfig();
    }

    private StartAppSDK m2545e() {
        return this.f2661k;
    }

    private void m2538a(boolean z) {
        if (!this.f2656f.isInterstitial() && (this.f2651a instanceof Activity)) {
            com.startapp.android.publish.p022h.StartAppSDK.m2994a((Activity) this.f2651a, z);
        }
    }

    public void onClick(View view) {
        if (this.f2663m.m2585a(this.f2651a)) {
            m2538a(true);
            this.f2657g = new RelativeLayout(this.f2651a);
            this.f2654d = new WebView(this.f2651a);
            this.f2654d.setWebViewClient(new WebViewClient());
            this.f2654d.setWebChromeClient(new WebChromeClient());
            this.f2654d.getSettings().setJavaScriptEnabled(true);
            this.f2654d.setHorizontalScrollBarEnabled(false);
            this.f2654d.setVerticalScrollBarEnabled(false);
            this.f2654d.loadUrl(m2535a(this.f2662l.m2526d()));
            this.f2654d.addJavascriptInterface(new AdInformationJsInterface(this.f2664n, this.f2665o), "startappwall");
            WindowManager windowManager = (WindowManager) this.f2651a.getSystemService("window");
            Point point = new Point(1, 1);
            com.startapp.android.publish.p022h.StartAppSDK.m2870a(windowManager, point);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.f2654d.setPadding(0, 0, 0, 0);
            layoutParams.setMargins(0, 0, 0, 0);
            this.f2657g.addView(this.f2654d, layoutParams);
            m2546f();
            switch (StartAppSDK.f2644a[this.f2659i.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    m2540b(this.f2657g, point);
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    m2536a(this.f2657g, point);
                default:
            }
        }
    }

    public void m2550c() {
        this.f2660j = false;
        switch (StartAppSDK.f2644a[this.f2659i.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.f2658h.post(new StartAppSDK(this));
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.f2655e.dismiss();
            default:
        }
    }

    private void m2546f() {
        String a = com.startapp.android.publish.p022h.StartAppSDK.m2990a(this.f2651a, null);
        if (a != null) {
            this.f2654d.loadUrl("javascript:window.onload=function(){document.getElementById('titlePlacement').innerText='" + a + "';}");
        }
    }

    private void m2536a(ViewGroup viewGroup, Point point) {
        this.f2660j = true;
        this.f2655e = new Dialog(this.f2651a);
        this.f2655e.requestWindowFeature(1);
        this.f2655e.setContentView(viewGroup);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.f2655e.getWindow().getAttributes());
        layoutParams.width = (int) (((float) point.x) * 0.9f);
        layoutParams.height = (int) (((float) point.y) * 0.85f);
        this.f2655e.show();
        this.f2655e.getWindow().setAttributes(layoutParams);
    }

    private void m2540b(ViewGroup viewGroup, Point point) {
        this.f2660j = true;
        LayoutParams layoutParams = new LayoutParams((int) (((float) point.x) * 0.9f), (int) (((float) point.y) * 0.85f));
        layoutParams.addRule(13);
        this.f2658h.post(new StartAppSDK(this, viewGroup, layoutParams));
    }

    private String m2535a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (com.startapp.android.publish.p022h.StartAppSDK.m2924b(this.f2651a)) {
            stringBuilder.append("?le=true");
        }
        return stringBuilder.toString();
    }
}
