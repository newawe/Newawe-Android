package com.startapp.android.publish.p009c;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.google.android.gcm.GCMConstants;
import com.startapp.android.publish.Ad.AdType;
import com.startapp.android.publish.AdDisplayListener.NotDisplayedReason;
import com.startapp.android.publish.JsInterface;
import com.startapp.android.publish.VideoJsInterface;
import com.startapp.android.publish.c.h.AnonymousClass16;
import com.startapp.android.publish.c.h.AnonymousClass17;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.VideoConfig.BackMode;
import com.startapp.android.publish.p009c.StartAppSDK.StartAppSDK;
import com.startapp.android.publish.video.VideoAdDetails;
import com.startapp.android.publish.video.VideoAdDetails.PostRollType;
import com.startapp.android.publish.video.tracking.VideoTrackingLink;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.startapp.android.publish.c.h */
public class StartAppSDK extends StartAppSDK {
    private Handler f4280A;
    private Handler f4281B;
    private Handler f4282C;
    private Handler f4283D;
    private Map<Integer, List<com.startapp.android.publish.video.tracking.StartAppSDK>> f4284E;
    private Map<Integer, List<com.startapp.android.publish.video.tracking.StartAppSDK>> f4285F;
    private long f4286G;
    private com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK f4287H;
    private com.startapp.android.publish.video.p026b.StartAppSDK f4288e;
    private VideoView f4289f;
    private RelativeLayout f4290g;
    private RelativeLayout f4291h;
    private ProgressBar f4292i;
    private boolean f4293j;
    private int f4294k;
    private int f4295l;
    private int f4296m;
    private int f4297n;
    private boolean f4298o;
    private boolean f4299p;
    private boolean f4300q;
    private boolean f4301r;
    private boolean f4302s;
    private boolean f4303t;
    private boolean f4304u;
    private HashMap<Integer, Boolean> f4305v;
    private HashMap<Integer, Boolean> f4306w;
    private int f4307x;
    private boolean f4308y;
    private boolean f4309z;

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.android.publish.c.h.17 */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ Handler f2774a;
        final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f2775b;

        AnonymousClass17(com.startapp.android.publish.p009c.StartAppSDK startAppSDK, Handler handler) {
            this.f2775b = startAppSDK;
            this.f2774a = handler;
        }

        public void run() {
            if (this.f2775b.f4289f == null) {
                return;
            }
            if (this.f2775b.f4288e.m3627e() > 0) {
                this.f2775b.m5277e(0);
                this.f2775b.m5281f(0);
                if (this.f2775b.f4294k == 0) {
                    this.f2775b.al();
                    com.startapp.android.publish.p022h.StartAppSDK.m2915a(this.f2775b.m2680b()).m2920a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                }
            } else if (!this.f2775b.f4302s) {
                this.f2774a.postDelayed(this, 100);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2776a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2776a = startAppSDK;
        }

        public void run() {
            int l = this.f2776a.m5231M();
            if (l >= DateUtils.MILLIS_IN_SECOND) {
                this.f2776a.f4281B.postDelayed(this, this.f2776a.m5245a(l));
            }
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.3 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2777a;
        private boolean f2778b;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2777a = startAppSDK;
        }

        public void run() {
            int d = this.f2777a.m5259b(this.f2777a.f4288e.m3627e() + 50);
            if (d >= 0 && !this.f2778b) {
                if (d == 0 || this.f2777a.f4295l >= this.f2777a.aa().getSkippableAfter() * DateUtils.MILLIS_IN_SECOND) {
                    this.f2778b = true;
                    this.f2777a.m4784a("videoApi.setSkipTimer", Integer.valueOf(0));
                } else {
                    this.f2777a.m4784a("videoApi.setSkipTimer", Integer.valueOf(d));
                }
            }
            this.f2777a.m4784a("videoApi.setVideoCurrentPosition", Integer.valueOf((this.f2777a.f4288e.m3627e() + 50) / DateUtils.MILLIS_IN_SECOND));
            if ((this.f2777a.f4288e.m3627e() + 50) / DateUtils.MILLIS_IN_SECOND < this.f2777a.f4288e.m3628f() / DateUtils.MILLIS_IN_SECOND) {
                this.f2777a.f4281B.postDelayed(this, this.f2777a.m5235Q());
            }
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2780a;
        final /* synthetic */ StartAppSDK f2781b;

        /* renamed from: com.startapp.android.publish.c.h.4.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f2779a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f2779a = startAppSDK;
            }

            public void run() {
                if (!this.f2779a.f2781b.f4303t) {
                    this.f2779a.f2781b.f4289f.setVisibility(4);
                }
            }
        }

        StartAppSDK(StartAppSDK startAppSDK, StartAppSDK startAppSDK2) {
            this.f2781b = startAppSDK;
            this.f2780a = startAppSDK2;
        }

        public void run() {
            if (this.f2780a == StartAppSDK.SKIPPED || this.f2780a == StartAppSDK.CLICKED) {
                this.f2781b.f4280A.removeCallbacksAndMessages(null);
                this.f2781b.f4282C.removeCallbacksAndMessages(null);
                this.f2781b.f4296m = this.f2781b.f4288e.m3627e();
                this.f2781b.f4288e.m3624b();
            } else {
                this.f2781b.f4296m = this.f2781b.f4297n;
                this.f2781b.m5243Y();
            }
            this.f2781b.f4281B.removeCallbacksAndMessages(null);
            this.f2781b.f4305v.clear();
            this.f2781b.f4306w.clear();
            if (this.f2780a == StartAppSDK.CLICKED) {
                this.f2781b.m5236R();
                return;
            }
            if (this.f2781b.aa().getPostRollType() != PostRollType.NONE) {
                this.f2781b.m5226H();
                this.f2781b.a.m2547a().setVisibility(0);
            }
            if (this.f2781b.aa().getPostRollType() == PostRollType.IMAGE) {
                new Handler().postDelayed(new StartAppSDK(this), 1000);
            } else if (this.f2781b.aa().getPostRollType() == PostRollType.NONE) {
                this.f2781b.m4788n();
            }
            this.f2781b.m5236R();
            if (this.f2781b.aa().getPostRollType() != PostRollType.NONE) {
                this.f2781b.am();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.5 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ int f2782a;
        final /* synthetic */ StartAppSDK f2783b;

        StartAppSDK(StartAppSDK startAppSDK, int i) {
            this.f2783b = startAppSDK;
            this.f2782a = i;
        }

        public void run() {
            this.f2783b.m5277e(this.f2782a);
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.6 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ int f2784a;
        final /* synthetic */ StartAppSDK f2785b;

        StartAppSDK(StartAppSDK startAppSDK, int i) {
            this.f2785b = startAppSDK;
            this.f2784a = i;
        }

        public void run() {
            this.f2785b.m5281f(this.f2784a);
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.7 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2786a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2786a = startAppSDK;
        }

        public void run() {
            this.f2786a.m5243Y();
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.8 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2787a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2787a = startAppSDK;
        }

        public void run() {
            this.f2787a.f4293j = !this.f2787a.f4293j;
            this.f2787a.af();
            this.f2787a.m5263b(this.f2787a.f4293j);
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.9 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f2788a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f2788a = startAppSDK;
        }

        public void run() {
            this.f2788a.ad();
        }
    }

    /* renamed from: com.startapp.android.publish.c.h.a */
    private enum StartAppSDK {
        PLAYER,
        POST_ROLL
    }

    /* renamed from: com.startapp.android.publish.c.h.b */
    private enum StartAppSDK {
        ON,
        OFF
    }

    /* renamed from: com.startapp.android.publish.c.h.c */
    private enum StartAppSDK {
        COMPLETE,
        SKIPPED,
        CLICKED
    }

    /* renamed from: com.startapp.android.publish.c.h.1 */
    class StartAppSDK implements com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK {
        final /* synthetic */ StartAppSDK f4095a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f4095a = startAppSDK;
        }

        public void m4805a() {
            if (this.f4095a.f4299p && this.f4095a.f4300q) {
                this.f4095a.m5215B();
            }
            if (this.f4095a.f4299p) {
                this.f4095a.m5220D();
            }
        }
    }

    public StartAppSDK() {
        this.f4293j = false;
        this.f4294k = 0;
        this.f4295l = 0;
        this.f4296m = 0;
        this.f4297n = 0;
        this.f4299p = false;
        this.f4300q = false;
        this.f4301r = false;
        this.f4302s = false;
        this.f4303t = false;
        this.f4304u = false;
        this.f4305v = new HashMap();
        this.f4306w = new HashMap();
        this.f4307x = 1;
        this.f4308y = false;
        this.f4309z = false;
        this.f4280A = new Handler();
        this.f4281B = new Handler();
        this.f4282C = new Handler();
        this.f4283D = new Handler();
        this.f4284E = new HashMap();
        this.f4285F = new HashMap();
    }

    public void m5303a(Bundle bundle) {
        super.m4782a(bundle);
        this.f4286G = System.currentTimeMillis();
        m5223F();
        ak();
        if (bundle != null && bundle.containsKey("currentPosition")) {
            this.f4295l = bundle.getInt("currentPosition");
            this.f4296m = bundle.getInt("latestPosition");
            this.f4305v = (HashMap) bundle.getSerializable("fractionProgressImpressionsSent");
            this.f4306w = (HashMap) bundle.getSerializable("absoluteProgressImpressionsSent");
            this.f4293j = bundle.getBoolean("isMuted");
            this.f4298o = bundle.getBoolean("shouldSetBg");
            this.f4294k = bundle.getInt("replayNum");
            this.f4304u = bundle.getBoolean("videoCompletedBroadcastSent", false);
            this.f4307x = bundle.getInt("pauseNum");
        }
    }

    public void m5304a(WebView webView) {
        super.m4783a(webView);
        webView.setBackgroundColor(33554431);
        com.startapp.android.publish.p022h.StartAppSDK.m2872a(webView, null);
    }

    protected void m5313x() {
        super.m4793x();
        this.f4299p = true;
        if (this.f4300q && ag()) {
            m5215B();
        } else if (m5238T()) {
            m5248a(this.c);
        }
        if (ag()) {
            m5220D();
        }
        if (m5238T()) {
            m5226H();
        }
    }

    private void m5215B() {
        if (this.f4301r) {
            m5248a(this.f4289f);
            if (!m5238T()) {
                m5227I();
            }
        }
    }

    public void m5310s() {
        super.m4790s();
        if (!m2680b().isFinishing()) {
            m5217C();
        }
    }

    private void m5217C() {
        if (this.f4289f == null) {
            m5246a(m2680b().getApplicationContext());
        }
        if (this.f4288e == null) {
            this.f4288e = new com.startapp.android.publish.video.p026b.StartAppSDK(this.f4289f);
        }
        this.f4300q = false;
        this.f4290g.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.f4288e.m3622a(aa().getLocalVideoPath());
        if (m5238T()) {
            this.a.m2547a().setVisibility(0);
            this.f4289f.setVisibility(4);
        } else if (this.f4295l != 0) {
            this.f4288e.m3616a(this.f4295l);
            m5262b(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK.EXTERNAL);
        }
        this.f4288e.m3621a(new StartAppSDK(this));
        this.f4288e.m3619a(new com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f4090a;

            {
                this.f4090a = r1;
            }

            public void m4802a() {
                if (!this.f4090a.m5238T()) {
                    this.f4090a.m5249a(StartAppSDK.COMPLETE);
                }
                this.f4090a.f4288e.m3625c();
            }
        });
        this.f4288e.m3620a(new com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f4091a;

            {
                this.f4091a = r1;
            }

            public boolean m4803a(com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK startAppSDK) {
                return this.f4091a.m5258a(startAppSDK);
            }
        });
        this.f4288e.m3618a(new com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f4092a;

            {
                this.f4092a = r1;
            }
        });
        this.f4288e.m3617a(new com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f4093a;

            {
                this.f4093a = r1;
            }
        });
        com.startapp.android.publish.p022h.StartAppSDK.m2868a(this.f4289f, new com.startapp.android.publish.p022h.StartAppSDK.StartAppSDK() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f4094a;

            {
                this.f4094a = r1;
            }

            public void m4804a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                this.f4094a.f4300q = true;
                if (this.f4094a.f4299p && this.f4094a.ag()) {
                    this.f4094a.m5215B();
                }
            }
        });
    }

    private void m5220D() {
        m5228J();
        if (m5238T()) {
            this.f4288e.m3624b();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f2773a;

            /* renamed from: com.startapp.android.publish.c.h.16.1 */
            class StartAppSDK implements Runnable {
                final /* synthetic */ AnonymousClass16 f2772a;

                StartAppSDK(AnonymousClass16 anonymousClass16) {
                    this.f2772a = anonymousClass16;
                }

                public void run() {
                    this.f2772a.f2773a.m5215B();
                }
            }

            {
                this.f2773a = r1;
            }

            public void run() {
                this.f2773a.f4288e.m3615a();
                this.f2773a.f4301r = true;
                new Handler().post(new com.startapp.android.publish.p009c.StartAppSDK.16.StartAppSDK(this));
            }
        }, m5225G());
        if (this.f4295l == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new AnonymousClass17(this, handler), 100);
        }
        m5239U();
        m5242X();
        m5230L();
        m5232N();
        this.a.m2547a().setVisibility(4);
        af();
    }

    private void m5221E() {
        this.f4283D.removeCallbacksAndMessages(null);
        if (this.f4292i.isShown()) {
            this.f4292i.setVisibility(8);
        }
    }

    private void m5223F() {
        if (!m2687g().equals("back")) {
            return;
        }
        if (MetaData.getInstance().getVideoConfig().getBackMode().equals(BackMode.BOTH)) {
            this.f4308y = true;
            this.f4309z = true;
        } else if (MetaData.getInstance().getVideoConfig().getBackMode().equals(BackMode.SKIP)) {
            this.f4308y = true;
            this.f4309z = false;
        } else if (MetaData.getInstance().getVideoConfig().getBackMode().equals(BackMode.CLOSE)) {
            this.f4308y = false;
            this.f4309z = true;
        } else if (MetaData.getInstance().getVideoConfig().getBackMode().equals(BackMode.DISABLED)) {
            this.f4308y = false;
            this.f4309z = false;
        } else {
            this.f4308y = false;
            this.f4309z = false;
        }
    }

    private long m5225G() {
        long currentTimeMillis = System.currentTimeMillis() - this.f4286G;
        if (this.f4295l == 0 && this.f4294k == 0 && currentTimeMillis < 500) {
            return Math.max(200, 500 - currentTimeMillis);
        }
        return 0;
    }

    private void m5226H() {
        m4784a("videoApi.setReplayEnabled", Boolean.valueOf(ag()));
        m4784a("videoApi.setMode", StartAppSDK.POST_ROLL + "_" + aa().getPostRollType());
        m4784a("videoApi.setCloseable", Boolean.valueOf(true));
    }

    private void m5227I() {
        m4784a("videoApi.setClickableVideo", Boolean.valueOf(aa().isClickable()));
        m4784a("videoApi.setMode", StartAppSDK.PLAYER.toString());
        String str = "videoApi.setCloseable";
        Object[] objArr = new Object[1];
        boolean z = aa().isCloseable() || this.f4309z;
        objArr[0] = Boolean.valueOf(z);
        m4784a(str, objArr);
        m4784a("videoApi.setSkippable", Boolean.valueOf(ai()));
    }

    private void m5228J() {
        m4784a("videoApi.setVideoDuration", Integer.valueOf(this.f4288e.m3628f() / DateUtils.MILLIS_IN_SECOND));
        m5231M();
        m5233O();
        m4784a("videoApi.setVideoCurrentPosition", Integer.valueOf(this.f4295l / DateUtils.MILLIS_IN_SECOND));
    }

    private void m5229K() {
        m4784a("videoApi.setVideoCurrentPosition", Integer.valueOf(0));
        m4784a("videoApi.setSkipTimer", Integer.valueOf(0));
    }

    private void m5248a(View view) {
        m4784a("videoApi.setVideoFrame", Integer.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2973b(m2680b(), view.getLeft())), Integer.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2973b(m2680b(), view.getTop())), Integer.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2973b(m2680b(), view.getWidth())), Integer.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2973b(m2680b(), view.getHeight())));
    }

    private void m5230L() {
        this.f4281B.post(new StartAppSDK(this));
    }

    private int m5231M() {
        int P = m5234P();
        int i = P / DateUtils.MILLIS_IN_SECOND;
        if (i > 0 && P % DateUtils.MILLIS_IN_SECOND < 100) {
            i--;
        }
        m4784a("videoApi.setVideoRemainingTimer", Integer.valueOf(i));
        return P;
    }

    private void m5232N() {
        m5233O();
        this.f4281B.post(new StartAppSDK(this));
    }

    private void m5233O() {
        m4784a("videoApi.setSkipTimer", Integer.valueOf(m5259b(this.f4295l + 50)));
    }

    private int m5234P() {
        if (this.f4288e.m3627e() != this.f4288e.m3628f() || m5238T()) {
            return this.f4288e.m3628f() - this.f4288e.m3627e();
        }
        return this.f4288e.m3628f();
    }

    private long m5245a(int i) {
        int i2 = i % DateUtils.MILLIS_IN_SECOND;
        if (i2 == 0) {
            i2 = DateUtils.MILLIS_IN_SECOND;
        }
        return (long) (i2 + 50);
    }

    private long m5235Q() {
        return (long) (1000 - (this.f4288e.m3627e() % DateUtils.MILLIS_IN_SECOND));
    }

    private int m5259b(int i) {
        if (this.f4308y || this.f4294k > 0) {
            return 0;
        }
        int skippableAfter = (aa().getSkippableAfter() * DateUtils.MILLIS_IN_SECOND) - i;
        if (skippableAfter > 0) {
            return (skippableAfter / DateUtils.MILLIS_IN_SECOND) + 1;
        }
        return 0;
    }

    private void m5249a(StartAppSDK startAppSDK) {
        new Handler().postDelayed(new StartAppSDK(this, startAppSDK), 0);
    }

    private void m5236R() {
        this.f4295l = -1;
    }

    private void m5237S() {
        this.f4295l = 0;
    }

    private boolean m5238T() {
        return this.f4295l == -1;
    }

    private void m5239U() {
        this.f4297n = this.f4288e.m3628f();
        m5240V();
        m5241W();
    }

    private void m5240V() {
        for (Integer intValue : this.f4284E.keySet()) {
            int intValue2 = intValue.intValue();
            m5247a(m5266c(intValue2), this.f4280A, new StartAppSDK(this, intValue2));
        }
    }

    private void m5241W() {
        for (Integer intValue : this.f4285F.keySet()) {
            int intValue2 = intValue.intValue();
            m5247a(intValue2, this.f4280A, new StartAppSDK(this, intValue2));
        }
    }

    private void m5242X() {
        m5247a(m5266c(MetaData.getInstance().getVideoConfig().getRewardGrantPercentage()), this.f4282C, new StartAppSDK(this));
    }

    private void m5247a(int i, Handler handler, Runnable runnable) {
        if (this.f4295l < i) {
            handler.postDelayed(runnable, (long) (i - this.f4295l));
        }
    }

    private int m5266c(int i) {
        return (this.f4297n * i) / 100;
    }

    private void m5243Y() {
        if (m5244Z() && !this.f4304u && this.f4294k == 0) {
            this.f4304u = true;
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending rewarded video completion broadcast.");
            if (com.startapp.android.publish.p022h.StartAppSDK.m2915a(m2680b()).m2920a(new Intent("com.startapp.android.OnVideoCompleted"))) {
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Rewarded video completion broadcast sent successfully.");
            }
            an();
        }
    }

    private boolean m5244Z() {
        return m2701u().getType() == AdType.REWARDED_VIDEO;
    }

    public void m5305b(Bundle bundle) {
        super.m4785b(bundle);
        bundle.putInt("currentPosition", this.f4295l);
        bundle.putInt("latestPosition", this.f4296m);
        bundle.putSerializable("fractionProgressImpressionsSent", this.f4305v);
        bundle.putSerializable("absoluteProgressImpressionsSent", this.f4306w);
        bundle.putBoolean("isMuted", this.f4293j);
        bundle.putBoolean("shouldSetBg", this.f4298o);
        bundle.putInt("replayNum", this.f4294k);
        bundle.putInt("pauseNum", this.f4307x);
        bundle.putBoolean("videoCompletedBroadcastSent", this.f4304u);
    }

    private VideoAdDetails aa() {
        return ((com.startapp.android.publish.p028a.StartAppSDK) m2701u()).getVideoAdDetails();
    }

    public void m5309q() {
        if (!(m5238T() || m2680b().isFinishing() || this.f4309z || this.f4308y)) {
            m5253a(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK.EXTERNAL);
        }
        ah();
        this.f4280A.removeCallbacksAndMessages(null);
        this.f4281B.removeCallbacksAndMessages(null);
        this.f4282C.removeCallbacksAndMessages(null);
        m5221E();
        this.f4298o = true;
        super.m4789q();
    }

    protected void m5312w() {
    }

    protected JsInterface m5311v() {
        return new VideoJsInterface(m2680b(), this.d, this.d, ae(), ac(), ab(), new com.startapp.android.publish.p022h.StartAppSDK(m2693m()));
    }

    private Runnable ab() {
        return new StartAppSDK(this);
    }

    private Runnable ac() {
        return new StartAppSDK(this);
    }

    private void ad() {
        m5249a(StartAppSDK.SKIPPED);
        ao();
    }

    private Runnable ae() {
        return new Runnable() {
            final /* synthetic */ com.startapp.android.publish.p009c.StartAppSDK f2771a;

            {
                this.f2771a = r1;
            }

            public void run() {
                this.f2771a.f4294k = this.f2771a.f4294k + 1;
                this.f2771a.f4289f.setVisibility(0);
                this.f2771a.f4298o = false;
                this.f2771a.m5237S();
                this.f2771a.m5229K();
                this.f2771a.f4288e.m3626d();
            }
        };
    }

    private RelativeLayout m5246a(Context context) {
        this.f4291h = (RelativeLayout) m2680b().findViewById(com.startapp.android.publish.StartAppSDK.STARTAPP_AD_MAIN_LAYOUT_ID);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f4289f = new VideoView(context);
        this.f4289f.setId(100);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        this.f4292i = new ProgressBar(context, null, 16843399);
        this.f4292i.setVisibility(4);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(15);
        this.f4290g = new RelativeLayout(context);
        this.f4290g.setId(1475346436);
        m2680b().setContentView(this.f4290g);
        this.f4290g.addView(this.f4289f, layoutParams2);
        this.f4290g.addView(this.f4291h, layoutParams);
        this.f4290g.addView(this.f4292i, layoutParams3);
        if (com.startapp.android.publish.StartAppSDK.m2734b().booleanValue()) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(14);
            this.f4290g.addView(m5260b(context), layoutParams);
        }
        this.a.m2547a().setVisibility(4);
        return this.f4290g;
    }

    private View m5260b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("url=" + aa().getVideoUrl());
        View textView = new TextView(context);
        textView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        com.startapp.android.publish.p022h.StartAppSDK.m2865a(textView, 0.5f);
        textView.setTextColor(-7829368);
        textView.setSingleLine(false);
        textView.setText(stringBuilder.toString());
        return textView;
    }

    private void af() {
        if (this.f4288e != null) {
            try {
                if (this.f4293j) {
                    this.f4288e.m3623a(true);
                } else {
                    this.f4288e.m3623a(false);
                }
            } catch (IllegalStateException e) {
            }
        }
        String str = "videoApi.setSound";
        Object[] objArr = new Object[1];
        objArr[0] = this.f4293j ? StartAppSDK.OFF.toString() : StartAppSDK.ON.toString();
        m4784a(str, objArr);
    }

    private void m5253a(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK startAppSDK) {
        if (this.f4288e != null) {
            int e = this.f4288e.m3627e();
            this.f4295l = e;
            this.f4296m = e;
            this.f4288e.m3624b();
        }
        m5269c(startAppSDK);
    }

    private void m5262b(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK startAppSDK) {
        m5275d(startAppSDK);
        this.f4307x++;
    }

    private boolean m5258a(com.startapp.android.publish.video.p026b.StartAppSDK.StartAppSDK startAppSDK) {
        boolean z = false;
        com.startapp.android.publish.p010d.StartAppSDK.m2729a(m2680b(), com.startapp.android.publish.p010d.StartAppSDK.StartAppSDK.VIDEO_MEDIA_PLAYER_ERROR, startAppSDK.m3613a().toString(), startAppSDK.m3614b(), com.startapp.android.publish.p022h.StartAppSDK.m2991a(aj(), null));
        ar();
        boolean z2 = this.f4295l > 0;
        if (aa().getPostRollType() == PostRollType.NONE) {
            z = true;
        }
        boolean Z = m5244Z();
        if (!z2) {
            com.startapp.android.publish.video.StartAppSDK.m3635b(m2680b());
        }
        if ((!Z || this.f4304u) && !r2) {
            m5249a(StartAppSDK.SKIPPED);
        } else {
            com.startapp.android.publish.p022h.StartAppSDK.m3004a(m2680b(), m2688h(), m2693m(), this.f4294k, NotDisplayedReason.VIDEO_ERROR.toString());
            Intent intent = new Intent("com.startapp.android.ShowFailedDisplayBroadcastListener");
            intent.putExtra("showFailedReason", NotDisplayedReason.VIDEO_ERROR);
            com.startapp.android.publish.p022h.StartAppSDK.m2915a(m2680b()).m2920a(intent);
            this.f4302s = true;
            m4788n();
        }
        return true;
    }

    private boolean ag() {
        return this.f4288e != null && this.f4288e.m3629g();
    }

    private void ah() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Releasing video player");
        if (this.f4288e != null) {
            this.f4288e.m3630h();
            this.f4288e = null;
        }
    }

    public boolean m5308p() {
        if (m5238T()) {
            return false;
        }
        int b = m5259b(this.f4288e.m3627e() + 50);
        if (ai() && b == 0) {
            ad();
            return true;
        } else if (aa().isCloseable() || this.f4309z) {
            return false;
        } else {
            return true;
        }
    }

    private boolean ai() {
        return this.f4294k > 0 || aa().isSkippable() || this.f4308y;
    }

    private int m5271d(int i) {
        if (this.f4297n > 0) {
            return (i * 100) / this.f4297n;
        }
        return 0;
    }

    private String aj() {
        String[] h = m2688h();
        if (h != null && h.length > 0) {
            return h[0];
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 5, "dParam is not available.");
        return StringUtils.EMPTY;
    }

    public void m5307o() {
        if (!this.f4302s) {
            super.m2695o();
        }
    }

    protected boolean m5306b(String str) {
        this.f4287H = m5238T() ? com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK.POSTROLL : com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK.VIDEO;
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Video clicked from: " + this.f4287H);
        if (this.f4287H == com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK.VIDEO) {
            m5249a(StartAppSDK.CLICKED);
        }
        m5252a(this.f4287H);
        return super.m4787b(str);
    }

    protected void m5314y() {
        if (this.f4302s) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Not sending close events due to media player error");
        } else if (m5238T() || this.f4289f == null) {
            ap();
        } else {
            aq();
        }
        super.m4794y();
    }

    protected com.startapp.android.publish.p022h.StartAppSDK m5302A() {
        return new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k, this.f4287H).m5008b(true).m5006a(true);
    }

    private void ak() {
        List list;
        int i = 0;
        com.startapp.android.publish.video.tracking.StartAppSDK[] a = aa().getVideoTrackingDetails().m3643a();
        if (a != null) {
            for (com.startapp.android.publish.video.tracking.StartAppSDK startAppSDK : a) {
                list = (List) this.f4284E.get(Integer.valueOf(startAppSDK.m5005a()));
                if (list == null) {
                    list = new ArrayList();
                    this.f4284E.put(Integer.valueOf(startAppSDK.m5005a()), list);
                }
                list.add(startAppSDK);
            }
        }
        com.startapp.android.publish.video.tracking.StartAppSDK[] b = aa().getVideoTrackingDetails().m3644b();
        if (b != null) {
            int length = b.length;
            while (i < length) {
                com.startapp.android.publish.video.tracking.StartAppSDK startAppSDK2 = b[i];
                list = (List) this.f4285F.get(Integer.valueOf(startAppSDK2.m5004a()));
                if (list == null) {
                    list = new ArrayList();
                    this.f4285F.put(Integer.valueOf(startAppSDK2.m5004a()), list);
                }
                list.add(startAppSDK2);
                i++;
            }
        }
    }

    private void al() {
        com.startapp.android.publish.p022h.StartAppSDK.m3003a(m2680b(), m2688h(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), 0, this.f4294k));
        m5254a(aa().getVideoTrackingDetails().m3645c(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), 0, this.f4294k), 0, "impression");
        m5254a(aa().getVideoTrackingDetails().m3647e(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), 0, this.f4294k), 0, "creativeView");
    }

    private void m5277e(int i) {
        if (this.f4305v.get(Integer.valueOf(i)) == null) {
            if (this.f4284E.containsKey(Integer.valueOf(i))) {
                List list = (List) this.f4284E.get(Integer.valueOf(i));
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending fraction progress event with fraction: " + i + ", total: " + list.size());
                m5254a((VideoTrackingLink[]) list.toArray(new com.startapp.android.publish.video.tracking.StartAppSDK[list.size()]), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), i, this.f4294k), this.f4288e.m3627e(), "fraction");
            }
            this.f4305v.put(Integer.valueOf(i), Boolean.valueOf(true));
            return;
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Fraction progress event already sent for fraction: " + i);
    }

    private void m5281f(int i) {
        if (this.f4306w.get(Integer.valueOf(i)) == null) {
            if (this.f4285F.containsKey(Integer.valueOf(i))) {
                List list = (List) this.f4285F.get(Integer.valueOf(i));
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending absolute progress event with video progress: " + i + ", total: " + list.size());
                m5254a((VideoTrackingLink[]) list.toArray(new com.startapp.android.publish.video.tracking.StartAppSDK[list.size()]), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), i, this.f4294k), i, "absolute");
            }
            this.f4306w.put(Integer.valueOf(i), Boolean.valueOf(true));
            return;
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Absolute progress event already sent for video progress: " + i);
    }

    private void am() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending postroll impression event");
        m5254a(aa().getVideoTrackingDetails().m3653k(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k), this.f4296m, "postrollImression");
    }

    private void an() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending rewarded event");
        m5254a(aa().getVideoTrackingDetails().m3655m(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), MetaData.getInstance().getVideoConfig().getRewardGrantPercentage(), this.f4294k), this.f4288e.m3627e(), "rewarded");
    }

    private void m5263b(boolean z) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending sound " + (z ? "muted " : "unmuted ") + NotificationCompatApi21.CATEGORY_EVENT);
        m5254a(z ? aa().getVideoTrackingDetails().m3648f() : aa().getVideoTrackingDetails().m3646d(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4288e.m3627e()), this.f4294k), this.f4288e.m3627e(), "sound");
    }

    private void ao() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending skip event");
        m5254a(aa().getVideoTrackingDetails().m3651i(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4288e.m3627e()), this.f4294k), this.f4288e.m3627e(), "skipped");
    }

    private void m5269c(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK startAppSDK) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending pause event with origin: " + startAppSDK);
        m5254a(aa().getVideoTrackingDetails().m3649g(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k, this.f4307x, startAppSDK), this.f4296m, "paused");
    }

    private void m5275d(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK startAppSDK) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending resume event with pause origin: " + startAppSDK);
        m5254a(aa().getVideoTrackingDetails().m3650h(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k, this.f4307x, startAppSDK), this.f4296m, "resumed");
    }

    private void ap() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending postroll closed event");
        m5254a(aa().getVideoTrackingDetails().m3654l(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k), this.f4296m, "postrollClosed");
    }

    private void aq() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending video closed event");
        m5254a(aa().getVideoTrackingDetails().m3652j(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4288e.m3627e()), this.f4294k), this.f4288e.m3627e(), "closed");
    }

    private void m5252a(com.startapp.android.publish.video.tracking.StartAppSDK.StartAppSDK startAppSDK) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending video clicked event with origin: " + startAppSDK.toString());
        m5254a(aa().getVideoTrackingDetails().m3656n(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4288e.m3627e()), this.f4294k, startAppSDK), this.f4288e.m3627e(), "clicked");
    }

    private void m5254a(VideoTrackingLink[] videoTrackingLinkArr, com.startapp.android.publish.video.tracking.StartAppSDK startAppSDK, int i, String str) {
        com.startapp.android.publish.video.StartAppSDK.m3634a(m2680b(), new com.startapp.android.publish.video.p025a.StartAppSDK(videoTrackingLinkArr, startAppSDK, aa().getVideoUrl(), i).m3601a(str).m3599a());
    }

    private void ar() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoMode", 3, "Sending internal video event");
        com.startapp.android.publish.video.StartAppSDK.m3634a(m2680b(), new com.startapp.android.publish.video.p025a.StartAppSDK(aa().getVideoTrackingDetails().m3657o(), new com.startapp.android.publish.video.tracking.StartAppSDK(m2693m(), m5271d(this.f4296m), this.f4294k), aa().getVideoUrl(), this.f4296m).m3600a(com.startapp.android.publish.video.p025a.StartAppSDK.StartAppSDK.GENERAL).m3601a(GCMConstants.EXTRA_ERROR).m3599a());
    }
}
