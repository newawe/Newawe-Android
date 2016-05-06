package com.chartboost.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import com.chartboost.sdk.C0351c.C0350a;
import com.chartboost.sdk.InPlay.C0313a;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0318c;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0334k;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0342e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1020a;
import com.chartboost.sdk.impl.C0469m;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.be;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.w3c.dom.traversal.NodeFilter;

public final class Chartboost {
    protected static volatile Handler f53a;
    protected static C0334k f54b;
    private static volatile Chartboost f55c;
    private static CBImpressionActivity f56d;
    private static C0343a f57e;
    private static ay f58f;
    private static ba f59g;
    private static C0469m f60h;
    private static C1020a f61i;
    public static boolean isFirstHardBootup;
    private static boolean f62j;
    private static SparseBooleanArray f63k;
    private static C0367f f64l;
    private static C0356d f65m;
    private static boolean f66n;
    private static Runnable f67o;
    private static boolean f68p;
    private static boolean f69q;
    private static Runnable f70r;

    /* renamed from: com.chartboost.sdk.Chartboost.11 */
    static class AnonymousClass11 implements Runnable {
        final /* synthetic */ String f7a;

        AnonymousClass11(String str) {
            this.f7a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f7a)) {
                CBLogging.m77b("Chartboost", "cacheMoreApps location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadMoreApps(this.f7a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            av.m3909h().m411b(this.f7a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.12 */
    static class AnonymousClass12 implements Runnable {
        final /* synthetic */ Activity f8a;
        final /* synthetic */ String f9b;
        final /* synthetic */ String f10c;

        AnonymousClass12(Activity activity, String str, String str2) {
            this.f8a = activity;
            this.f9b = str;
            this.f10c = str2;
        }

        public void run() {
            Chartboost.f55c = new Chartboost(this.f9b, this.f10c, null);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.13 */
    static class AnonymousClass13 implements Runnable {
        final /* synthetic */ String f11a;

        AnonymousClass13(String str) {
            this.f11a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f11a)) {
                CBLogging.m77b("Chartboost", "showMoreApps location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadMoreApps(this.f11a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            av.m3909h().m3913a(this.f11a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.14 */
    static class AnonymousClass14 implements Runnable {
        final /* synthetic */ CBMediation f12a;
        final /* synthetic */ String f13b;

        AnonymousClass14(CBMediation cBMediation, String str) {
            this.f12a = cBMediation;
            this.f13b = str;
        }

        public void run() {
            C0351c.m334a(this.f12a, this.f13b);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.15 */
    static class AnonymousClass15 implements Runnable {
        final /* synthetic */ CBFramework f14a;

        AnonymousClass15(CBFramework cBFramework) {
            this.f14a = cBFramework;
        }

        public void run() {
            C0351c.m332a(this.f14a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.16 */
    static class AnonymousClass16 implements Runnable {
        final /* synthetic */ CBFramework f15a;
        final /* synthetic */ String f16b;

        AnonymousClass16(CBFramework cBFramework, String str) {
            this.f15a = cBFramework;
            this.f16b = str;
        }

        public void run() {
            C0351c.m333a(this.f15a, this.f16b);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.17 */
    static class AnonymousClass17 implements Runnable {
        final /* synthetic */ String f17a;

        AnonymousClass17(String str) {
            this.f17a = str;
        }

        public void run() {
            C0351c.m339a(this.f17a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.18 */
    static class AnonymousClass18 implements Runnable {
        final /* synthetic */ String f18a;

        AnonymousClass18(String str) {
            this.f18a = str;
        }

        public void run() {
            C0351c.m352d(this.f18a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.19 */
    static class AnonymousClass19 implements Runnable {
        final /* synthetic */ Level f19a;

        AnonymousClass19(Level level) {
            this.f19a = level;
        }

        public void run() {
            C0351c.m335a(this.f19a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.20 */
    static class AnonymousClass20 implements Runnable {
        final /* synthetic */ ChartboostDelegate f20a;

        AnonymousClass20(ChartboostDelegate chartboostDelegate) {
            this.f20a = chartboostDelegate;
        }

        public void run() {
            C0351c.m337a(this.f20a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.21 */
    static class AnonymousClass21 implements Runnable {
        final /* synthetic */ boolean f21a;

        AnonymousClass21(boolean z) {
            this.f21a = z;
        }

        public void run() {
            C0351c.m340a(this.f21a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.22 */
    static class AnonymousClass22 implements Runnable {
        final /* synthetic */ boolean f22a;

        AnonymousClass22(boolean z) {
            this.f22a = z;
        }

        public void run() {
            C0351c.m356e(this.f22a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.23 */
    static class AnonymousClass23 implements Runnable {
        final /* synthetic */ Activity f23a;

        AnonymousClass23(Activity activity) {
            this.f23a = activity;
        }

        public void run() {
            Chartboost.m38e(this.f23a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.24 */
    static class AnonymousClass24 implements Runnable {
        final /* synthetic */ boolean f24a;

        AnonymousClass24(boolean z) {
            this.f24a = z;
        }

        public void run() {
            C0351c.m358f(this.f24a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.25 */
    static class AnonymousClass25 implements Runnable {
        final /* synthetic */ boolean f25a;

        AnonymousClass25(boolean z) {
            this.f25a = z;
        }

        public void run() {
            C0351c.m360g(this.f25a);
            String C = C0351c.m325C();
            if (this.f25a) {
                if (C.equals("/webview/v1/prefetch")) {
                    C0349b.m306b();
                } else {
                    be.m668b();
                }
            } else if (C.equals("/webview/v1/prefetch")) {
                C0349b.m311e();
            } else {
                be.m672d();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.26 */
    static class AnonymousClass26 implements Runnable {
        final /* synthetic */ Activity f26a;

        AnonymousClass26(Activity activity) {
            this.f26a = activity;
        }

        public void run() {
            CBLogging.m83e("VideoInit", "preparing activity for video surface");
            this.f26a.addContentView(new SurfaceView(this.f26a), new LayoutParams(0, 0));
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.27 */
    static class AnonymousClass27 implements Runnable {
        final /* synthetic */ String f27a;
        final /* synthetic */ boolean f28b;

        AnonymousClass27(String str, boolean z) {
            this.f27a = str;
            this.f28b = z;
        }

        public void run() {
            ae.m3823h().m412b(this.f27a, this.f28b);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.28 */
    static class AnonymousClass28 implements Runnable {
        final /* synthetic */ String f29a;
        final /* synthetic */ boolean f30b;

        AnonymousClass28(String str, boolean z) {
            this.f29a = str;
            this.f30b = z;
        }

        public void run() {
            av.m3909h().m412b(this.f29a, this.f30b);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.29 */
    static class AnonymousClass29 implements Runnable {
        final /* synthetic */ String f31a;
        final /* synthetic */ boolean f32b;

        AnonymousClass29(String str, boolean z) {
            this.f31a = str;
            this.f32b = z;
        }

        public void run() {
            af.m5031j().m412b(this.f31a, this.f32b);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.2 */
    static class C03042 implements Runnable {
        final /* synthetic */ Activity f33a;

        C03042(Activity activity) {
            this.f33a = activity;
        }

        public void run() {
            C0334k a = C0334k.m245a(this.f33a);
            if (Chartboost.m37d(a)) {
                Chartboost.m42f(a);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.30 */
    static class AnonymousClass30 implements Runnable {
        final /* synthetic */ boolean f34a;

        AnonymousClass30(boolean z) {
            this.f34a = z;
        }

        public void run() {
            if (Chartboost.f56d == null) {
                return;
            }
            if (this.f34a) {
                Chartboost.f56d.forwardTouchEvents(Chartboost.getHostActivity());
            } else {
                Chartboost.f56d.forwardTouchEvents(null);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.31 */
    static class AnonymousClass31 implements Runnable {
        final /* synthetic */ Activity f35a;

        AnonymousClass31(Activity activity) {
            this.f35a = activity;
        }

        public void run() {
            Chartboost.f53a.removeCallbacks(Chartboost.f67o);
            if (!(Chartboost.f54b == null || Chartboost.f54b.m249b(this.f35a) || !Chartboost.m54p())) {
                Chartboost.m42f(Chartboost.f54b);
                Chartboost.m34c(Chartboost.f54b, false);
            }
            Chartboost.m28b(this.f35a, true);
            Chartboost.f54b = C0334k.m245a(this.f35a);
            Chartboost.m15a();
            Chartboost.m17a(this.f35a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.35 */
    static class AnonymousClass35 implements Runnable {
        final /* synthetic */ Activity f36a;

        AnonymousClass35(Activity activity) {
            this.f36a = activity;
        }

        public void run() {
            C0334k a = C0334k.m245a(this.f36a);
            if (Chartboost.m37d(a)) {
                Chartboost.m20a(a);
            } else if (C0351c.m343b() != null && C0351c.m343b().ordinal() == CBFramework.CBFrameworkUnity.ordinal()) {
                Chartboost.m15a();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.36 */
    static class AnonymousClass36 implements Runnable {
        final /* synthetic */ Activity f37a;

        AnonymousClass36(Activity activity) {
            this.f37a = activity;
        }

        public void run() {
            C0334k a = C0334k.m245a(this.f37a);
            if (Chartboost.m37d(a)) {
                Chartboost.m29b(a);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.3 */
    static class C03053 implements Runnable {
        final /* synthetic */ C0356d f38a;

        C03053(C0356d c0356d) {
            this.f38a = c0356d;
        }

        public void run() {
            this.f38a.m388b();
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.4 */
    static class C03064 implements Runnable {
        final /* synthetic */ C0367f f39a;
        final /* synthetic */ C0356d f40b;

        C03064(C0367f c0367f, C0356d c0356d) {
            this.f39a = c0367f;
            this.f40b = c0356d;
        }

        public void run() {
            this.f39a.m444a(this.f40b.m389c(), true);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.5 */
    static class C03075 implements Runnable {
        final /* synthetic */ Activity f41a;

        C03075(Activity activity) {
            this.f41a = activity;
        }

        public void run() {
            if (Chartboost.f54b == null || Chartboost.f54b.m249b(this.f41a)) {
                Chartboost.f67o = new C0312a();
                Chartboost.f67o.run();
            }
            Chartboost.m27b(this.f41a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.6 */
    static class C03086 implements Runnable {
        final /* synthetic */ String f42a;

        C03086(String str) {
            this.f42a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f42a)) {
                CBLogging.m77b("Chartboost", "cacheRewardedVideo location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadRewardedVideo(this.f42a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            af.m5031j().m411b(this.f42a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.7 */
    static class C03097 implements Runnable {
        final /* synthetic */ String f43a;

        C03097(String str) {
            this.f43a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f43a)) {
                CBLogging.m77b("Chartboost", "showRewardedVideo location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadRewardedVideo(this.f43a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            af.m5031j().m409a(this.f43a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.8 */
    static class C03108 implements Runnable {
        final /* synthetic */ String f44a;

        C03108(String str) {
            this.f44a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f44a)) {
                CBLogging.m77b("Chartboost", "cacheInterstitial location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadInterstitial(this.f44a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ae.m3823h().m411b(this.f44a);
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.9 */
    static class C03119 implements Runnable {
        final /* synthetic */ String f45a;

        C03119(String str) {
            this.f45a = str;
        }

        public void run() {
            if (!C0351c.m370q()) {
                return;
            }
            if (TextUtils.isEmpty(this.f45a)) {
                CBLogging.m77b("Chartboost", "showInterstitial location cannot be empty");
                if (C0351c.m359g() != null) {
                    C0351c.m359g().didFailToLoadInterstitial(this.f45a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ae.m3823h().m409a(this.f45a);
        }
    }

    public enum CBFramework {
        CBFrameworkUnity("Unity"),
        CBFrameworkCorona("Corona"),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS"),
        CBFrameworkCocos2dx("Cocos2dx"),
        CBFrameworkPrime31Unreal("Prime31Unreal"),
        CBFrameworkWeeby("Weeby"),
        CBFrameworkOther("Other");
        
        private final String f47a;

        private CBFramework(String s) {
            this.f47a = s;
        }

        public String toString() {
            return this.f47a;
        }

        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFrameworkAir || this == CBFrameworkCocos2dx;
        }

        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFrameworkAir;
        }
    }

    public enum CBMediation {
        CBMediationAdMarvel("AdMarvel"),
        CBMediationFuse("Fuse"),
        CBMediationFyber("Fyber"),
        CBMediationHeyZap("HeyZap"),
        CBMediationMoPub("MoPub"),
        CBMediationSupersonic("Supersonic"),
        CBMediationOther("Other");
        
        private final String f49a;

        private CBMediation(String s) {
            this.f49a = s;
        }

        public String toString() {
            return this.f49a;
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.a */
    private static class C0312a implements Runnable {
        private int f50a;
        private int f51b;
        private int f52c;

        private C0345a m13a() {
            return C0351c.m359g();
        }

        private C0312a() {
            int i = -1;
            C0345a a = m13a();
            this.f50a = Chartboost.f56d == null ? -1 : Chartboost.f56d.hashCode();
            this.f51b = Chartboost.f54b == null ? -1 : Chartboost.f54b.hashCode();
            if (a != null) {
                i = a.hashCode();
            }
            this.f52c = i;
        }

        public void run() {
            C0345a a = m13a();
            if (Chartboost.f54b != null && Chartboost.f54b.hashCode() == this.f51b) {
                Chartboost.f54b = null;
            }
            if (a != null && a.hashCode() == this.f52c) {
                C0351c.m337a(null);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Chartboost.1 */
    class C10001 implements C0350a {
        final /* synthetic */ Chartboost f3370a;

        C10001(Chartboost chartboost) {
            this.f3370a = chartboost;
        }

        public void m3686a() {
            az azVar = new az("api/install");
            azVar.m567a(true);
            azVar.m568a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a));
            azVar.m590s();
            Chartboost.m53o();
            Chartboost.isFirstHardBootup = false;
        }
    }

    static {
        f55c = null;
        f56d = null;
        f57e = null;
        f58f = null;
        f59g = null;
        f60h = null;
        f61i = null;
        f62j = false;
        f63k = new SparseBooleanArray();
        f64l = null;
        f65m = null;
        isFirstHardBootup = true;
        f66n = false;
        f53a = new Handler(Looper.getMainLooper());
        f54b = null;
        f68p = false;
        f69q = false;
        f70r = new Runnable() {
            public void run() {
                if (!Chartboost.f69q) {
                    Chartboost.m31c();
                }
                Chartboost.f69q = false;
            }
        };
    }

    private Chartboost(Activity app, String appId, String appSignature) {
        f55c = this;
        CBUtility.m90a(f53a);
        C0351c.m330a(app.getApplication());
        C0351c.m331a(app.getApplicationContext());
        C0351c.m344b(appId);
        C0351c.m349c(appSignature);
        f58f = ay.m543a();
        f64l = C0367f.m440a();
        f59g = ba.m610a(C0351c.m378y());
        f60h = f59g.m623a();
        f65m = C0356d.m382a();
        f61i = C1020a.m3733a();
        f58f.m545a(C0351c.m378y());
        C0349b.m298a();
        be.m661a();
        f67o = new C0312a();
        C0318c.m106a();
        synchronized (f55c) {
            C0351c.m338a(new C10001(this));
        }
    }

    public static void startWithAppId(Activity activity, String appId, String appSignature) {
        if (f55c == null) {
            synchronized (Chartboost.class) {
                if (f55c == null) {
                    if (activity == null && !(activity instanceof Activity)) {
                        CBLogging.m77b("Chartboost", "Activity object is null. Please pass a valid activity object");
                        return;
                    } else if (!C0351c.m346b(activity)) {
                        CBLogging.m77b("Chartboost", "Permissions not set correctly");
                        return;
                    } else if (!C0351c.m347b((Context) activity)) {
                        CBLogging.m77b("Chartboost", "CBImpression Activity not added in your manifest.xml");
                        return;
                    } else if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(appSignature)) {
                        CBLogging.m77b("Chartboost", "AppId or AppSignature is null. Please pass a valid id's");
                        return;
                    } else {
                        m23a(new AnonymousClass12(activity, appId, appSignature));
                    }
                }
            }
        }
    }

    public static void onCreate(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new AnonymousClass23(activity));
        }
    }

    private static void m38e(Activity activity) {
        if (!(f54b == null || f54b.m249b(activity) || !m54p())) {
            m42f(f54b);
            m34c(f54b, false);
        }
        f53a.removeCallbacks(f67o);
        f54b = C0334k.m245a(activity);
        if (C0351c.m328F().booleanValue()) {
            m32c(activity);
        }
        ba.m610a((Context) activity).m627d();
    }

    public static void onStart(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new AnonymousClass31(activity));
        }
    }

    protected static void m17a(Activity activity) {
        boolean z;
        if (VERSION.SDK_INT >= 23) {
            C0351c.m346b(activity);
        }
        f58f.m548b(C0351c.m378y());
        if (!(activity instanceof CBImpressionActivity)) {
            f60h.m816a();
            f59g.m628f();
        }
        C0351c.m331a(activity.getApplicationContext());
        if (activity instanceof CBImpressionActivity) {
            m19a((CBImpressionActivity) activity);
        } else {
            f54b = C0334k.m245a(activity);
            m34c(f54b, true);
        }
        f53a.removeCallbacks(f67o);
        if (C0351c.m343b() == null || !C0351c.m343b().doesWrapperUseCustomBackgroundingBehavior()) {
            z = false;
        } else {
            z = true;
        }
        if (activity == null) {
            return;
        }
        if (z || m43f(activity)) {
            m30b(C0334k.m245a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                f66n = false;
            }
            if (f65m.m386a(activity, f57e)) {
                f57e = null;
            }
            C0343a c = f65m.m389c();
            if (c != null) {
                c.m290y();
            }
        }
    }

    protected static void m15a() {
        if (C0351c.m378y() == null) {
            CBLogging.m77b("Chartboost", "The context must be set through the Chartboost method onCreate() before calling startSession().");
        } else if (!C0351c.m361h() || !C0351c.m369p()) {
            m52n();
        } else if (f68p) {
            f69q = true;
        } else {
            f69q = false;
            m52n();
        }
    }

    protected static void m26b() {
        if (C0351c.m361h()) {
            f53a.postDelayed(f70r, 500);
        } else {
            m31c();
        }
    }

    private static void m52n() {
        f68p = true;
        C0351c.m345b(true);
        if (f61i == null) {
            f61i = C1020a.m3733a();
        }
        f61i.m3767h();
        C1020a.m3745b();
        synchronized (f55c) {
            if (!isFirstHardBootup) {
                C0351c.m338a(new C0350a() {
                    public void m3687a() {
                        az azVar = new az("api/install");
                        azVar.m567a(true);
                        azVar.m568a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a));
                        azVar.m590s();
                        Chartboost.m53o();
                    }
                });
            }
        }
    }

    private static void m53o() {
        ax.m541a().execute(new Runnable() {
            public void run() {
                try {
                    if (C0351c.m325C().equals("/webview/v1/prefetch")) {
                        C0349b.m306b();
                    } else {
                        be.m668b();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected static void m31c() {
        f68p = false;
        if (f61i == null) {
            f61i = C1020a.m3733a();
        }
        f61i.m3763c();
    }

    public static void onResume(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new AnonymousClass35(activity));
        }
    }

    protected static void m20a(C0334k c0334k) {
        C0343a c = C0356d.m382a().m389c();
        if (C0351c.m343b() != null && C0351c.m343b().ordinal() == CBFramework.CBFrameworkUnity.ordinal()) {
            m15a();
        }
        if (c != null) {
            c.m289x();
        }
    }

    public static void onPause(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new AnonymousClass36(activity));
        }
    }

    protected static void m29b(C0334k c0334k) {
        C0343a c = C0356d.m382a().m389c();
        if (c != null) {
            c.m291z();
        }
    }

    public static void onStop(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new C03042(activity));
        }
    }

    private static void m42f(C0334k c0334k) {
        if (!C0351c.m361h()) {
            m33c(c0334k);
        }
        if (!(c0334k.get() instanceof CBImpressionActivity)) {
            m34c(c0334k, false);
        }
        m26b();
    }

    protected static void m33c(C0334k c0334k) {
        C0343a c = C0356d.m382a().m389c();
        if (c != null && c.f225a == C0339b.NATIVE) {
            C0367f h = m46h();
            if (m45g(c0334k) && h != null) {
                if (c != null) {
                    h.m447c(c);
                    f57e = c;
                }
                m30b(c0334k, false);
                if (c0334k.get() instanceof CBImpressionActivity) {
                    m44g();
                }
            }
            if (!(c0334k.get() instanceof CBImpressionActivity)) {
                m34c(c0334k, false);
            }
        }
        f58f.m550c(C0351c.m378y());
        if (!(c0334k.get() instanceof CBImpressionActivity)) {
            f60h.m820b();
            f59g.m629g();
        }
    }

    public static boolean onBackPressed() {
        if (!C0351c.m373t()) {
            return false;
        }
        if (f54b == null) {
            CBLogging.m77b("Chartboost", "The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
            return false;
        } else if (!C0351c.m361h()) {
            return m36d();
        } else {
            if (!f66n) {
                return false;
            }
            f66n = false;
            m36d();
            return true;
        }
    }

    protected static boolean m36d() {
        return m40e();
    }

    protected static boolean m40e() {
        C0356d a = C0356d.m382a();
        C0343a c = a.m389c();
        if (c == null || c.f227c != C0342e.DISPLAYED) {
            C0367f h = m46h();
            if (h == null || !h.m448c()) {
                return false;
            }
            m23a(new C03064(h, a));
            return true;
        } else if (c.m288w()) {
            return true;
        } else {
            m23a(new C03053(a));
            return true;
        }
    }

    public static void onDestroy(Activity activity) {
        if (C0351c.m373t() && C0351c.m342a(activity)) {
            m23a(new C03075(activity));
        }
    }

    protected static void m27b(Activity activity) {
        m30b(C0334k.m245a(activity), false);
        f57e = null;
    }

    public static void didPassAgeGate(boolean pass) {
        C0351c.m350c(pass);
    }

    public static void setShouldPauseClickForConfirmation(boolean shouldPause) {
        C0351c.m353d(shouldPause);
    }

    public static void clearCache() {
        if (C0351c.m370q()) {
            bc.m652a().m659b();
            af.m5031j().m403a();
            ae.m3823h().m403a();
            av.m3909h().m3911a();
            C0313a.m65b();
        }
    }

    public static boolean hasRewardedVideo(String location) {
        if (C0351c.m370q()) {
            return af.m5031j().m416c(location);
        }
        return false;
    }

    public static void cacheRewardedVideo(String location) {
        m23a(new C03086(location));
    }

    public static void showRewardedVideo(String location) {
        m23a(new C03097(location));
    }

    public static boolean hasInterstitial(String location) {
        if (C0351c.m370q()) {
            return ae.m3823h().m416c(location);
        }
        return false;
    }

    public static void cacheInterstitial(String location) {
        m23a(new C03108(location));
    }

    public static void showInterstitial(String location) {
        m23a(new C03119(location));
    }

    public static void closeImpression() {
        m23a(new Runnable() {
            public void run() {
                if (C0351c.m370q()) {
                    Chartboost.m40e();
                }
            }
        });
    }

    public static boolean hasMoreApps(String location) {
        if (C0351c.m370q()) {
            return av.m3909h().m416c(location);
        }
        return false;
    }

    public static void cacheMoreApps(String location) {
        m23a(new AnonymousClass11(location));
    }

    public static void showMoreApps(String location) {
        m23a(new AnonymousClass13(location));
    }

    public static boolean isAnyViewVisible() {
        C0367f h = m46h();
        if (h == null) {
            return false;
        }
        return h.m450d();
    }

    public static void setMediation(CBMediation mediation, String libraryVersion) {
        m23a(new AnonymousClass14(mediation, libraryVersion));
    }

    public static void setFramework(CBFramework framework) {
        m23a(new AnonymousClass15(framework));
    }

    public static void setFramework(CBFramework framework, String version) {
        m23a(new AnonymousClass16(framework, version));
    }

    public static void setFrameworkVersion(String version) {
        m23a(new AnonymousClass17(version));
    }

    public static String getCustomId() {
        return C0351c.m368o();
    }

    public static void setCustomId(String customID) {
        m23a(new AnonymousClass18(customID));
    }

    public static void setLoggingLevel(Level lvl) {
        m23a(new AnonymousClass19(lvl));
    }

    public static Level getLoggingLevel() {
        return C0351c.m367n();
    }

    public static C0345a getDelegate() {
        return C0351c.m359g();
    }

    public static void setDelegate(ChartboostDelegate delegate) {
        m23a(new AnonymousClass20(delegate));
    }

    public static boolean getAutoCacheAds() {
        return C0351c.m363j();
    }

    public static void setAutoCacheAds(boolean autoCacheAds) {
        m23a(new AnonymousClass21(autoCacheAds));
    }

    public static void setShouldRequestInterstitialsInFirstSession(boolean shouldRequest) {
        m23a(new AnonymousClass22(shouldRequest));
    }

    public static void setShouldDisplayLoadingViewForMoreApps(boolean shouldDisplay) {
        m23a(new AnonymousClass24(shouldDisplay));
    }

    public static void setShouldPrefetchVideoContent(boolean shouldPrefetch) {
        m23a(new AnonymousClass25(shouldPrefetch));
    }

    protected static Activity m41f() {
        if (C0351c.m361h()) {
            return f56d;
        }
        return getHostActivity();
    }

    private static boolean m43f(Activity activity) {
        if (C0351c.m361h()) {
            if (f56d == activity) {
                return true;
            }
            return false;
        } else if (f54b != null) {
            return f54b.m249b(activity);
        } else {
            if (activity != null) {
                return false;
            }
            return true;
        }
    }

    private static boolean m45g(C0334k c0334k) {
        if (C0351c.m361h()) {
            if (c0334k != null) {
                return c0334k.m249b(f56d);
            }
            if (f56d == null) {
                return true;
            }
            return false;
        } else if (f54b != null) {
            return f54b.m247a(c0334k);
        } else {
            if (c0334k != null) {
                return false;
            }
            return true;
        }
    }

    protected static void m19a(CBImpressionActivity cBImpressionActivity) {
        if (!f62j) {
            C0351c.m331a(cBImpressionActivity.getApplicationContext());
            f56d = cBImpressionActivity;
            f62j = true;
        }
        f53a.removeCallbacks(f67o);
    }

    protected static void m44g() {
        if (f62j) {
            f56d = null;
            f62j = false;
        }
    }

    protected static void m22a(C0343a c0343a) {
        boolean z = true;
        C0367f h = m46h();
        if (h != null && h.m450d() && h.m451e().m742h() != c0343a) {
            c0343a.m263a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        } else if (!C0351c.m361h()) {
            h = m46h();
            if (h == null || !m54p()) {
                c0343a.m263a(CBImpressionError.NO_HOST_ACTIVITY);
            } else {
                h.m443a(c0343a);
            }
        } else if (f62j) {
            if (m41f() == null || h == null) {
                if (m41f() == null) {
                    CBLogging.m77b("Chartboost", "Activity not found. Cannot display the view");
                    c0343a.m263a(CBImpressionError.NO_HOST_ACTIVITY);
                    return;
                }
                CBLogging.m77b("Chartboost", "Missing view controller to manage the impression activity");
                c0343a.m263a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            } else if (c0343a.f225a == C0339b.WEB) {
                C0372g B = c0343a.m260B();
                if (B != null) {
                    B.m477c();
                }
            } else {
                h.m443a(c0343a);
            }
        } else if (m54p()) {
            Context hostActivity = getHostActivity();
            if (hostActivity == null) {
                CBLogging.m77b("Chartboost", "Failed to display impression as the host activity reference has been lost!");
                c0343a.m263a(CBImpressionError.NO_HOST_ACTIVITY);
            } else if (f57e == null || f57e == c0343a) {
                f57e = c0343a;
                Intent intent = new Intent(hostActivity, CBImpressionActivity.class);
                boolean z2 = (hostActivity.getWindow().getAttributes().flags & NodeFilter.SHOW_DOCUMENT_FRAGMENT) != 0;
                boolean z3;
                if ((hostActivity.getWindow().getAttributes().flags & XMLEntityManager.DEFAULT_BUFFER_SIZE) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                String str = "paramFullscreen";
                if (!z2 || r3) {
                    z = false;
                }
                intent.putExtra(str, z);
                try {
                    hostActivity.startActivity(intent);
                    f66n = true;
                } catch (ActivityNotFoundException e) {
                    CBLogging.m77b("Chartboost", "Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                }
            } else {
                c0343a.m263a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        } else {
            c0343a.m263a(CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    protected static Activity getHostActivity() {
        return f54b != null ? (Activity) f54b.get() : null;
    }

    protected static void m23a(Runnable runnable) {
        if (CBUtility.m92b()) {
            runnable.run();
        } else {
            f53a.post(runnable);
        }
    }

    protected static Context getValidContext() {
        return f54b != null ? f54b.m248b() : C0351c.m378y();
    }

    private static void m30b(C0334k c0334k, boolean z) {
    }

    private static boolean m54p() {
        return m37d(f54b);
    }

    protected static boolean m37d(C0334k c0334k) {
        if (c0334k == null) {
            return false;
        }
        Boolean valueOf = Boolean.valueOf(f63k.get(c0334k.m246a()));
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        return false;
    }

    private static void m28b(Activity activity, boolean z) {
        if (activity != null) {
            m16a(activity.hashCode(), z);
        }
    }

    private static void m34c(C0334k c0334k, boolean z) {
        if (c0334k != null) {
            m16a(c0334k.m246a(), z);
        }
    }

    private static void m16a(int i, boolean z) {
        f63k.put(i, z);
    }

    protected static C0367f m46h() {
        if (m41f() == null) {
            return null;
        }
        return f64l;
    }

    public static boolean getImpressionsUseActivities() {
        return C0351c.m361h();
    }

    @Deprecated
    public static void setImpressionsUseActivities(boolean impressionsUseActivities) {
    }

    protected static void m32c(Activity activity) {
        if (VERSION.SDK_INT < 14) {
            f53a.post(new AnonymousClass26(activity));
        }
    }

    private static void showInterstitialAIR(String location, boolean show) {
        m23a(new AnonymousClass27(location, show));
    }

    private static void showMoreAppsAIR(String location, boolean show) {
        m23a(new AnonymousClass28(location, show));
    }

    private static void showRewardedVideoAIR(String location, boolean show) {
        m23a(new AnonymousClass29(location, show));
    }

    private static void forwardTouchEventsAIR(boolean forward) {
        m23a(new AnonymousClass30(forward));
    }
}
