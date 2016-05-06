package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.ConnectionResult;
import com.immersion.hapticmediasdk.HapticContentSDKFactory;
import com.jirbo.adcolony.aa.C0747b;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

class ad extends View implements OnCompletionListener, OnErrorListener {
    static float[] aB;
    boolean f2036A;
    boolean f2037B;
    boolean f2038C;
    boolean f2039D;
    boolean f2040E;
    boolean f2041F;
    boolean f2042G;
    boolean f2043H;
    boolean f2044I;
    boolean f2045J;
    boolean f2046K;
    boolean f2047L;
    boolean f2048M;
    boolean f2049N;
    boolean f2050O;
    boolean f2051P;
    boolean f2052Q;
    boolean f2053R;
    boolean f2054S;
    boolean f2055T;
    boolean f2056U;
    boolean f2057V;
    Canvas f2058W;
    WebView f2059a;
    String[] aA;
    float aC;
    float aD;
    float aE;
    float aF;
    float aG;
    float aH;
    float aI;
    Paint aJ;
    RectF aK;
    C0758b aL;
    Handler aM;
    String aa;
    String ab;
    String ac;
    String ad;
    String ae;
    String af;
    C0747b ag;
    Paint ah;
    Paint ai;
    Paint aj;
    Paint ak;
    Rect al;
    ADCImage am;
    ADCImage an;
    ADCImage ao;
    ADCImage ap;
    ADCImage aq;
    ADCImage ar;
    ADCImage as;
    ADCImage at;
    ADCImage au;
    ADCImage av;
    ADCImage aw;
    ADCImage[] ax;
    ADCImage[] ay;
    C0780m az;
    WebView f2060b;
    View f2061c;
    ADCVideo f2062d;
    double f2063e;
    double f2064f;
    int f2065g;
    int f2066h;
    int f2067i;
    int f2068j;
    int f2069k;
    int f2070l;
    int f2071m;
    int f2072n;
    int f2073o;
    int f2074p;
    int f2075q;
    int f2076r;
    int f2077s;
    int f2078t;
    int f2079u;
    long f2080v;
    long f2081w;
    float f2082x;
    boolean f2083y;
    boolean f2084z;

    /* renamed from: com.jirbo.adcolony.ad.1 */
    class C07481 implements Runnable {
        final /* synthetic */ ad f2020a;

        C07481(ad adVar) {
            this.f2020a = adVar;
        }

        public void run() {
            this.f2020a.f2038C = true;
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.2 */
    class C07492 extends Handler {
        final /* synthetic */ ad f2021a;

        C07492(ad adVar) {
            this.f2021a = adVar;
        }

        public void handleMessage(Message m) {
            if (!this.f2021a.f2062d.isFinishing() && this.f2021a.f2062d.f3943G != null) {
                this.f2021a.m2187a(m.what);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.3 */
    class C07503 extends WebChromeClient {
        final /* synthetic */ ad f2022a;

        C07503(ad adVar) {
            this.f2022a = adVar;
        }

        public boolean onConsoleMessage(ConsoleMessage cm) {
            String sourceId = cm.sourceId();
            if (sourceId == null) {
                sourceId = "Internal";
            } else {
                int lastIndexOf = sourceId.lastIndexOf(47);
                if (lastIndexOf != -1) {
                    sourceId = sourceId.substring(lastIndexOf + 1);
                }
            }
            C0777l.f2240b.m2349a(cm.message()).m2349a(" [").m2349a(sourceId).m2349a(" line ").m2347a(cm.lineNumber()).m2353b((Object) "]");
            return true;
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.4 */
    class C07514 extends WebViewClient {
        String f2023a;
        final /* synthetic */ ad f2024b;

        C07514(ad adVar) {
            this.f2024b = adVar;
            this.f2023a = C0745a.ad;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            C0777l.f2239a.m2349a("DEC request: ").m2353b((Object) url);
            if (url.contains("mraid:")) {
                this.f2024b.az.m2358a(url);
                return true;
            } else if (url.contains("youtube")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + url));
                intent.putExtra("VIDEO_ID", url);
                this.f2024b.f2062d.startActivity(intent);
                return true;
            } else if (url.contains("mraid.js")) {
                return true;
            } else {
                return false;
            }
        }

        public void onLoadResource(WebView view, String url) {
            C0777l.f2239a.m2349a("DEC onLoad: ").m2353b((Object) url);
            if (url.equals(this.f2023a)) {
                C0777l.f2239a.m2353b((Object) "DEC disabling mouse events");
                this.f2024b.m2188a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (url.equals(this.f2023a)) {
                this.f2024b.f2062d.f3963k = true;
                this.f2024b.f2080v = System.currentTimeMillis();
            }
        }

        public void onPageFinished(WebView view, String url) {
            C0777l.f2239a.m2349a("onPageFinished - ").m2353b((Object) url);
            C0777l.f2239a.m2349a("END CARD URL = ").m2353b(this.f2023a);
            C0777l.f2239a.m2349a("equals? ").m2354b(url.equals(this.f2023a));
            if (url.equals(this.f2023a) || C0745a.ad.startsWith("<")) {
                C0777l.f2239a.m2353b((Object) "DEC FINISHED LOADING");
                this.f2024b.f2039D = false;
                this.f2024b.f2062d.f3964l = true;
                this.f2024b.f2081w = System.currentTimeMillis();
                this.f2024b.f2062d.f3968p = ((double) (this.f2024b.f2081w - this.f2024b.f2080v)) / 1000.0d;
            }
            this.f2024b.f2062d.f3952P.removeView(this.f2024b.f2061c);
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.5 */
    class C07525 implements Runnable {
        final /* synthetic */ ad f2025a;

        C07525(ad adVar) {
            this.f2025a = adVar;
        }

        public void run() {
            if (this.f2025a.f2039D && this.f2025a.f2062d != null && this.f2025a.f2052Q && this.f2025a.f2059a != null) {
                this.f2025a.f2062d.f3965m = true;
                this.f2025a.m2200g();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.6 */
    class C07536 implements Runnable {
        final /* synthetic */ View f2026a;
        final /* synthetic */ ad f2027b;

        C07536(ad adVar, View view) {
            this.f2027b = adVar;
            this.f2026a = view;
        }

        public void run() {
            this.f2027b.f2062d.f3952P.removeView(this.f2026a);
            this.f2027b.m2189a(true);
            this.f2027b.f2062d.f3970r = System.currentTimeMillis();
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.7 */
    class C07547 implements Runnable {
        final /* synthetic */ ad f2028a;

        C07547(ad adVar) {
            this.f2028a = adVar;
        }

        public void run() {
            if (this.f2028a.f2062d.f3943G != null) {
                this.f2028a.f2062d.f3943G.setVisibility(8);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.8 */
    class C07558 implements Runnable {
        final /* synthetic */ View f2029a;
        final /* synthetic */ ad f2030b;

        C07558(ad adVar, View view) {
            this.f2030b = adVar;
            this.f2029a = view;
        }

        public void run() {
            if (this.f2030b.f2052Q) {
                this.f2030b.f2062d.f3953Q.setVisibility(4);
            }
            this.f2030b.f2062d.f3952P.removeView(this.f2029a);
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.9 */
    class C07569 implements OnGlobalLayoutListener {
        final /* synthetic */ View f2031a;
        final /* synthetic */ ad f2032b;

        C07569(ad adVar, View view) {
            this.f2032b = adVar;
            this.f2031a = view;
        }

        public void onGlobalLayout() {
            Rect rect = new Rect();
            this.f2031a.getWindowVisibleDisplayFrame(rect);
            if (this.f2032b.f2059a != null) {
                this.f2032b.m2193b((this.f2031a.getRootView().getHeight() - (rect.bottom - rect.top)) - ((this.f2032b.f2062d.f3973u - this.f2032b.f2059a.getHeight()) / 2));
            }
            this.f2032b.m2204k();
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.a */
    class C0757a extends View {
        Rect f2033a;
        final /* synthetic */ ad f2034b;

        public C0757a(ad adVar, Activity activity) {
            this.f2034b = adVar;
            super(activity);
            this.f2033a = new Rect();
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
            getDrawingRect(this.f2033a);
            this.f2034b.av.m2101a(canvas, (this.f2033a.width() - this.f2034b.av.f1805f) / 2, (this.f2033a.height() - this.f2034b.av.f1806g) / 2);
            invalidate();
        }
    }

    /* renamed from: com.jirbo.adcolony.ad.b */
    class C0758b extends Handler {
        final /* synthetic */ ad f2035a;

        C0758b(ad adVar) {
            this.f2035a = adVar;
            m2184a();
        }

        void m2184a() {
            sendMessageDelayed(obtainMessage(), 500);
        }

        public void handleMessage(Message m) {
            m2184a();
            if (!this.f2035a.f2062d.isFinishing() && this.f2035a.f2062d.f3943G != null) {
                synchronized (this) {
                    if (!(this.f2035a.ag == null || !this.f2035a.ag.m2170a() || this.f2035a.f2062d.f3943G.isPlaying())) {
                        this.f2035a.ag = null;
                        this.f2035a.f2078t = 0;
                        if (this.f2035a.f2062d.f3943G != null) {
                            this.f2035a.f2062d.f3943G.m2283a();
                        }
                        this.f2035a.f2062d.f3966n = true;
                        this.f2035a.m2200g();
                    }
                }
            }
        }
    }

    static {
        aB = new float[80];
    }

    ad(ADCVideo aDCVideo) {
        super(aDCVideo);
        this.f2063e = 1.0d;
        this.f2064f = 1.0d;
        this.f2065g = 99;
        this.f2066h = 0;
        this.f2083y = true;
        this.f2084z = true;
        this.f2036A = true;
        this.f2037B = true;
        this.f2038C = true;
        this.f2039D = true;
        this.aa = C0745a.f2001l.f2141a.f2115b;
        this.ah = new Paint();
        this.ai = new Paint(1);
        this.aj = new Paint(1);
        this.ak = new Paint(1);
        this.al = new Rect();
        this.ax = new ADCImage[4];
        this.ay = new ADCImage[4];
        this.aA = new String[4];
        this.aJ = new Paint(1);
        this.aK = new RectF();
        this.aL = new C0758b(this);
        this.aM = new C07492(this);
        this.f2062d = aDCVideo;
        this.f2048M = C0745a.f2001l.f2141a.f2132t;
        if (C0745a.f1983T != null) {
            this.f2048M |= C0745a.f1983T.f1843j.f2282z.f2300l.f2388a;
            C0745a.f1983T.f1849p = C0745a.f1983T.f1850q;
        }
        this.f2082x = aDCVideo.getResources().getDisplayMetrics().density;
        this.f2052Q = C0745a.f1988Y;
        if (!(C0745a.ad == null || C0745a.f1994e == null)) {
            int indexOf = C0745a.ad.indexOf("#");
            C0745a.ad = C0745a.f1994e + (indexOf >= 0 ? C0745a.ad.substring(indexOf) : StringUtils.EMPTY);
        }
        aDCVideo.f3945I.f1848o = C0745a.ad;
        C0777l.f2239a.m2349a("DEC URL = ").m2353b(aDCVideo.f3945I.f1848o);
        if (C0745a.f1983T != null && C0745a.f1983T.f1843j.f2281y.f2407d) {
            boolean z;
            if (this.f2052Q) {
                z = false;
            } else {
                z = true;
            }
            this.f2049N = z;
        }
        if (this.f2049N) {
            this.am = new ADCImage(C0745a.m2165j("end_card_filepath"));
            this.f2072n = this.am.f1805f;
            this.f2073o = this.am.f1806g;
            if (this.f2072n == 0) {
                this.f2072n = 480;
            }
            if (this.f2073o == 0) {
                this.f2073o = 320;
            }
            this.ax[0] = new ADCImage(C0745a.m2165j("info_image_normal"));
            this.ax[1] = new ADCImage(C0745a.m2165j("download_image_normal"));
            this.ax[2] = new ADCImage(C0745a.m2165j("replay_image_normal"));
            this.ax[3] = new ADCImage(C0745a.m2165j("continue_image_normal"));
            this.ay[0] = new ADCImage(C0745a.m2165j("info_image_down"), true);
            this.ay[1] = new ADCImage(C0745a.m2165j("download_image_down"), true);
            this.ay[2] = new ADCImage(C0745a.m2165j("replay_image_down"), true);
            this.ay[3] = new ADCImage(C0745a.m2165j("continue_image_down"), true);
            this.aA[0] = "Info";
            this.aA[1] = "Download";
            this.aA[2] = "Replay";
            this.aA[3] = "Continue";
        } else if (this.f2052Q) {
            this.ar = new ADCImage(C0745a.m2165j("reload_image_normal"));
            this.ap = new ADCImage(C0745a.m2165j("close_image_normal"));
            this.aq = new ADCImage(C0745a.m2165j("close_image_down"));
            this.as = new ADCImage(C0745a.m2165j("reload_image_down"));
            this.av = new ADCImage(C0745a.m2165j("browser_icon"));
            this.f2061c = new C0757a(this, aDCVideo);
            m2192b();
        }
        if (this.f2048M) {
            this.an = new ADCImage(C0745a.m2165j("skip_video_image_normal"));
            this.ao = new ADCImage(C0745a.m2165j("skip_video_image_down"));
            this.f2074p = C0745a.m2162h("skip_delay") * DateUtils.MILLIS_IN_SECOND;
        }
        this.aJ.setStyle(Style.STROKE);
        float f = 2.0f * aDCVideo.getResources().getDisplayMetrics().density;
        if (f > 6.0f) {
            f = 6.0f;
        }
        if (f < 4.0f) {
            this.aJ.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aJ.setColor(-3355444);
            this.f2054S = false;
            this.f2047L = false;
            this.f2055T = false;
        } else {
            this.aJ.setStrokeWidth(2.0f * aDCVideo.getResources().getDisplayMetrics().density);
            this.aJ.setColor(-3355444);
            this.f2054S = false;
            this.f2047L = false;
            this.f2055T = false;
        }
        if (C0745a.f1983T != null) {
            this.f2047L = C0745a.f1983T.f1843j.f2282z.f2301m.f2388a;
            this.f2055T = C0745a.m2164i("image_overlay_enabled");
        }
        if (this.f2047L) {
            this.at = new ADCImage(C0745a.m2165j("engagement_image_normal"));
            this.au = new ADCImage(C0745a.m2165j("engagement_image_down"));
            this.ad = C0745a.f1983T.f1843j.f2282z.f2301m.f2397j;
            this.ab = C0745a.f1983T.f1843j.f2282z.f2301m.f2399l;
            this.ac = C0745a.f1983T.f1843j.f2282z.f2301m.f2402o;
            this.f2076r = C0745a.f1983T.f1843j.f2282z.f2301m.f2390c;
            this.f2075q = C0745a.m2162h("engagement_delay") * DateUtils.MILLIS_IN_SECOND;
            if (this.ab.equals(StringUtils.EMPTY)) {
                this.ab = "Learn More";
            }
            if (!this.ac.equals(StringUtils.EMPTY)) {
                this.f2042G = true;
            }
            if (this.f2042G) {
                this.f2060b = new WebView(aDCVideo);
                this.f2060b.setBackgroundColor(0);
            }
            if (this.at == null || this.au == null) {
                this.f2047L = false;
            }
        }
        if (this.f2055T) {
            double d;
            this.aw = new ADCImage(C0745a.m2165j("image_overlay_filepath"));
            if (AdColony.isTablet()) {
                d = (((double) this.f2076r) * (((double) this.f2082x) / 1.0d)) / ((double) this.aw.f1806g);
            } else {
                d = (((double) this.f2076r) * (((double) this.f2082x) / 0.75d)) / ((double) this.aw.f1806g);
            }
            this.aw.m2097a(d);
        }
        if (ADCVideo.f3931d) {
            m2199f();
        }
        this.ah.setColor(-1);
        this.aj.setTextSize(24.0f);
        this.aj.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.ai.setColor(-3355444);
        this.ai.setTextSize(20.0f);
        this.ai.setTextAlign(Align.CENTER);
        this.ak.setTextSize(20.0f);
        this.ak.setColor(-1);
        try {
            getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
        } catch (Exception e) {
        }
    }

    public void onDraw(Canvas canvas) {
        if (!this.f2041F) {
            m2186a();
            this.f2058W = canvas;
            if (!this.f2050O && this.f2048M) {
                this.f2050O = this.f2062d.f3943G.getCurrentPosition() > this.f2074p;
            }
            if (!this.f2051P && this.f2047L) {
                this.f2051P = this.f2062d.f3943G.getCurrentPosition() > this.f2075q;
            }
            ADCVideo aDCVideo = this.f2062d;
            int c;
            int i;
            if (ADCVideo.f3931d && this.f2049N) {
                canvas.drawARGB((this.f2062d.f3978z >> 24) & MotionEventCompat.ACTION_MASK, 0, 0, 0);
                this.am.m2101a(canvas, (this.f2062d.f3972t - this.am.f1805f) / 2, (this.f2062d.f3973u - this.am.f1806g) / 2);
                c = this.am.m2104c() + ((int) (186.0d * this.f2063e));
                int d = this.am.m2106d() + ((int) (470.0d * this.f2063e));
                i = 0;
                while (i < this.ax.length) {
                    if (this.f2078t == i + 1 || !(this.f2079u != i + 1 || this.f2036A || this.f2079u == 0)) {
                        this.ay[i].m2097a(this.f2063e);
                        this.ay[i].m2101a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.f2063e));
                    } else if (this.f2036A || i + 1 != this.f2079u) {
                        this.ax[i].m2097a(this.f2063e);
                        this.ax[i].m2101a(canvas, c, d);
                        c = (int) (((double) c) + (((double) 1125974016) * this.f2063e));
                    }
                    this.ai.setColor(-1);
                    this.ai.clearShadowLayer();
                    canvas.drawText(this.aA[i], ((float) this.ax[i].m2104c()) + ((float) (this.ax[i].f1805f / 2)), (float) (this.ax[i].m2106d() + this.ax[i].f1806g), this.ai);
                    i++;
                }
                return;
            }
            aDCVideo = this.f2062d;
            if (ADCVideo.f3931d && this.f2052Q) {
                this.ap.m2097a(this.f2064f);
                this.aq.m2097a(this.f2064f);
                this.ar.m2097a(this.f2064f);
                this.as.m2097a(this.f2064f);
                i = (C0745a.f2002m || this.f2067i == 0) ? this.f2062d.f3972t - this.ap.f1805f : this.f2067i;
                this.f2067i = i;
                this.f2068j = 0;
                this.f2069k = 0;
                this.f2070l = 0;
                if (this.f2043H) {
                    this.aq.m2101a(canvas, this.f2067i, this.f2068j);
                } else {
                    this.ap.m2101a(canvas, this.f2067i, this.f2068j);
                }
                if (this.f2044I) {
                    this.as.m2101a(canvas, this.f2069k, this.f2070l);
                } else {
                    this.ar.m2101a(canvas, this.f2069k, this.f2070l);
                }
                m2203j();
                return;
            }
            if (this.f2062d.f3943G != null) {
                int i2;
                C0745a.f2001l.m2244a(((double) this.f2062d.f3943G.getCurrentPosition()) / ((double) this.f2062d.f3943G.getDuration()), this.f2062d.f3945I);
                if (this.f2062d.f3948L) {
                    this.f2062d.f3946J.update((long) this.f2062d.f3943G.getCurrentPosition());
                }
                c = this.f2062d.f3943G.getCurrentPosition();
                i = ((this.f2077s - c) + 999) / DateUtils.MILLIS_IN_SECOND;
                if (this.f2054S && i == 1) {
                    i2 = 0;
                } else {
                    i2 = i;
                }
                if (i2 == 0) {
                    this.f2054S = true;
                }
                if (c >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    if (this.f2037B) {
                        this.aD = (float) (360.0d / (((double) this.f2077s) / 1000.0d));
                        this.f2037B = false;
                        Rect rect = new Rect();
                        this.ai.getTextBounds("0123456789", 0, 9, rect);
                        this.aG = (float) rect.height();
                    }
                    this.aE = (float) getWidth();
                    this.aF = (float) getHeight();
                    this.aH = this.aG;
                    this.aI = (((float) this.f2062d.f3973u) - this.aG) - ((float) this.f2071m);
                    this.aK.set(this.aH - (this.aG / 2.0f), this.aI - (2.0f * this.aG), this.aH + (2.0f * this.aG), this.aI + (this.aG / 2.0f));
                    this.aJ.setShadowLayer((float) ((int) (4.0d * this.f2063e)), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
                    this.aC = (float) (((((double) this.f2077s) / 1000.0d) - (((double) c) / 1000.0d)) * ((double) this.aD));
                    canvas.drawArc(this.aK, 270.0f, this.aC, false, this.aJ);
                    aDCVideo = this.f2062d;
                    if (!ADCVideo.f3931d) {
                        this.ai.setColor(-3355444);
                        this.ai.setShadowLayer((float) ((int) (2.0d * this.f2063e)), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
                        this.ai.setTextAlign(Align.CENTER);
                        this.ai.setLinearText(true);
                        canvas.drawText(StringUtils.EMPTY + i2, this.aK.centerX(), (float) (((double) this.aK.centerY()) + (((double) this.ai.getFontMetrics().bottom) * 1.35d)), this.ai);
                    }
                    if (this.f2048M) {
                        aDCVideo = this.f2062d;
                        if (!ADCVideo.f3931d && this.f2050O) {
                            if (this.f2078t == 10) {
                                this.ao.m2101a(canvas, this.f2062d.f3972t - this.ao.f1805f, (int) (this.f2063e * 4.0d));
                            } else {
                                this.an.m2101a(canvas, this.f2062d.f3972t - this.an.f1805f, (int) (this.f2063e * 4.0d));
                            }
                        }
                    }
                    if (this.f2047L && this.f2051P) {
                        if (!this.f2042G && !this.f2055T) {
                            if (this.f2045J) {
                                this.au.m2105c((int) (((float) (this.f2062d.f3972t - this.au.f1805f)) - (this.aG / 2.0f)), ((this.f2062d.f3973u - this.au.f1806g) - this.f2071m) - ((int) (this.aG / 2.0f)));
                                this.au.m2100a(canvas);
                            } else {
                                this.at.m2105c((int) (((float) (this.f2062d.f3972t - this.at.f1805f)) - (this.aG / 2.0f)), ((this.f2062d.f3973u - this.at.f1806g) - this.f2071m) - ((int) (this.aG / 2.0f)));
                                this.at.m2100a(canvas);
                            }
                            this.aj.setTextAlign(Align.CENTER);
                            canvas.drawText(this.ab, (float) this.at.f1804e.centerX(), (float) (((double) this.at.f1804e.centerY()) + (((double) this.aj.getFontMetrics().bottom) * 1.35d)), this.aj);
                        } else if (!this.f2042G && this.f2055T) {
                            this.aw.m2105c((int) (((float) (this.f2062d.f3972t - this.aw.f1805f)) - (this.aG / 2.0f)), ((this.f2062d.f3973u - this.aw.f1806g) - this.f2071m) - ((int) (this.aG / 2.0f)));
                            this.aw.m2100a(canvas);
                        }
                    }
                }
                if (C1257v.f4038I != null) {
                    C1257v.f4038I.onDraw(canvas);
                }
            }
            aDCVideo = this.f2062d;
            if (ADCVideo.f3935h) {
                invalidate();
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.f2071m = this.f2062d.f3973u - h;
        if (Build.MODEL.equals("Kindle Fire")) {
            this.f2071m = 20;
        }
        if (Build.MODEL.equals("SCH-I800")) {
            this.f2071m = 25;
        }
        if (Build.MODEL.equals("SHW-M380K") || Build.MODEL.equals("SHW-M380S") || Build.MODEL.equals("SHW-M380W")) {
            this.f2071m = 40;
        }
    }

    void m2187a(int i) {
        try {
            if (this.f2038C || i == 10) {
                this.f2038C = false;
                Object j;
                switch (i) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        this.f2078t = 0;
                        C0745a.m2146a("info", "{\"ad_slot\":" + C0745a.f1983T.f1842i.f2321r.f2089c + "}", this.f2062d.f3945I);
                        j = C0745a.m2165j("info_url");
                        C0777l.f2240b.m2349a("INFO ").m2353b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.f2062d.startActivity(new Intent(this.f2062d, AdColonyBrowser.class));
                            break;
                        }
                        this.f2062d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                        break;
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        this.f2078t = 0;
                        C0745a.m2146a("download", "{\"ad_slot\":" + C0745a.f1983T.f1842i.f2321r.f2089c + "}", this.f2062d.f3945I);
                        j = C0745a.m2165j("download_url");
                        C0777l.f2240b.m2349a("DOWNLOAD ").m2353b(j);
                        if (!j.startsWith("market:") && !j.startsWith("amzn:")) {
                            AdColonyBrowser.url = j;
                            this.f2062d.startActivity(new Intent(this.f2062d, AdColonyBrowser.class));
                            break;
                        }
                        this.f2062d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                        break;
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        this.f2078t = 0;
                        m2202i();
                        invalidate();
                        break;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        this.f2078t = 0;
                        this.f2062d.f3943G.m2283a();
                        m2200g();
                        break;
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                        this.f2078t = 0;
                        m2201h();
                        break;
                    default:
                        this.f2078t = 0;
                        break;
                }
                new Handler().postDelayed(new C07481(this), 1500);
            }
        } catch (RuntimeException e) {
            this.f2038C = true;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (C1257v.f4038I != null) {
            C1257v.f4038I.onTouchEvent(event);
            return true;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        ADCVideo aDCVideo;
        if (action == 0) {
            aDCVideo = this.f2062d;
            if (!ADCVideo.f3931d || !this.f2052Q) {
                aDCVideo = this.f2062d;
                if (ADCVideo.f3931d && this.f2049N) {
                    x = (int) (((double) (event.getX() - ((float) this.am.m2104c()))) / (this.f2063e * 2.0d));
                    y = (int) (((double) (event.getY() - ((float) this.am.m2106d()))) / (this.f2063e * 2.0d));
                    if (this.f2078t == 0 && y >= 235 && y < HttpStatus.SC_USE_PROXY) {
                        action = m2185a(x, y);
                        this.f2078t = action;
                        this.f2079u = action;
                        this.f2036A = false;
                        invalidate();
                    }
                }
                if (this.f2048M && this.f2050O && this.f2062d.f3943G != null && m2190a(this.an, r1, r0)) {
                    this.f2078t = 10;
                    this.f2079u = this.f2078t;
                    this.f2036A = false;
                    invalidate();
                    return true;
                } else if (this.f2047L && this.f2051P && (m2190a(this.at, r1, r0) || m2190a(this.aw, r1, r0))) {
                    this.f2045J = true;
                    invalidate();
                    return true;
                }
            } else if (m2190a(this.ap, x, y)) {
                this.f2043H = true;
                invalidate();
                return true;
            } else if (!m2190a(this.ar, x, y)) {
                return false;
            } else {
                this.f2044I = true;
                invalidate();
                return true;
            }
        } else if (action == 1) {
            aDCVideo = this.f2062d;
            if (ADCVideo.f3931d && this.f2052Q) {
                if (m2190a(this.ap, x, y) && this.f2043H) {
                    this.f2078t = 4;
                    if (this.f2059a != null) {
                        this.f2059a.clearCache(true);
                    }
                    this.aM.sendMessageDelayed(this.aM.obtainMessage(this.f2078t), 250);
                    return true;
                } else if (m2190a(this.ar, x, y) && this.f2044I) {
                    this.f2078t = 3;
                    if (this.f2059a != null) {
                        this.f2059a.clearCache(true);
                    }
                    this.aM.sendMessageDelayed(this.aM.obtainMessage(this.f2078t), 250);
                    return true;
                }
            }
            aDCVideo = this.f2062d;
            if (ADCVideo.f3931d && this.f2049N) {
                x = (int) (((double) (event.getX() - ((float) this.am.m2104c()))) / (this.f2063e * 2.0d));
                y = (int) (((double) (event.getY() - ((float) this.am.m2106d()))) / (this.f2063e * 2.0d));
                if (!this.f2036A && y >= 235 && y < HttpStatus.SC_USE_PROXY) {
                    action = m2185a(x, y);
                    if (action > 0 && action == this.f2079u) {
                        this.aM.sendMessageDelayed(this.aM.obtainMessage(action), 250);
                    }
                }
            }
            if (this.f2048M && this.f2050O && this.f2062d.f3943G != null && m2190a(this.an, r1, r0)) {
                this.f2078t = 10;
                this.f2036A = true;
                this.f2079u = this.f2078t;
                this.aM.sendMessageDelayed(this.aM.obtainMessage(this.f2078t), 250);
                return true;
            } else if (this.f2047L && this.f2051P && (m2190a(this.at, r1, r0) || m2190a(this.aw, r1, r0))) {
                this.f2045J = false;
                if (this.ad.startsWith("market:") || this.ad.startsWith("amzn:")) {
                    this.f2062d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.ad)));
                } else if (this.f2062d.f3945I.f1843j.f2256B == null || !this.f2062d.f3945I.f1843j.f2256B.f2285c) {
                    AdColonyBrowser.url = this.ad;
                    this.f2062d.startActivity(new Intent(this.f2062d, AdColonyBrowser.class));
                } else {
                    this.ae = this.f2062d.f3945I.f1847n;
                    this.f2062d.f3945I.f1859z = AdColonyIAPEngagement.OVERLAY;
                    this.f2047L = false;
                    this.f2056U = true;
                    this.f2055T = false;
                    m2201h();
                }
                C0745a.m2146a("in_video_engagement", "{\"ad_slot\":" + C0745a.f1983T.f1842i.f2321r.f2089c + "}", this.f2062d.f3945I);
                return true;
            } else {
                this.f2043H = false;
                this.f2044I = false;
                this.f2045J = false;
                this.f2036A = true;
                this.f2078t = 0;
                invalidate();
                return true;
            }
        } else if (action == 3) {
            this.f2043H = false;
            this.f2044I = false;
            this.f2045J = false;
            this.f2036A = true;
            this.f2078t = 0;
            invalidate();
            return true;
        }
        return true;
    }

    int m2185a(int i, int i2) {
        if (i >= this.f2065g && i < this.f2065g + 62) {
            return 1;
        }
        if (i >= this.f2065g + 78 && i < (this.f2065g + 78) + 62) {
            return 2;
        }
        if (i >= (this.f2065g + 78) + 78 && i < ((this.f2065g + 78) + 78) + 62) {
            return 3;
        }
        if (i >= ((this.f2065g + 78) + 78) + 78 && i < (((this.f2065g + 78) + 78) + 78) + 62) {
            return 4;
        }
        if (this.f2062d.f3943G == null || !this.f2048M || i < this.f2062d.f3943G.getWidth() - this.an.f1805f || i2 > this.an.f1806g) {
            return 0;
        }
        return 10;
    }

    public boolean m2190a(ADCImage aDCImage, int i, int i2) {
        if (aDCImage != null && i < (aDCImage.m2104c() + aDCImage.f1805f) + 8 && i > aDCImage.m2104c() - 8 && i2 < (aDCImage.m2106d() + aDCImage.f1806g) + 8 && i2 > aDCImage.m2106d() - 8) {
            return true;
        }
        return false;
    }

    public void m2186a() {
        boolean b = this.f2062d.m4679b();
        this.f2083y |= b;
        if (this.f2062d.f3943G != null) {
            if (this.f2077s <= 0) {
                this.f2077s = this.f2062d.f3943G.getDuration();
            }
            if (b) {
                setLayoutParams(new LayoutParams(this.f2062d.f3972t, this.f2062d.f3973u, 17));
                this.f2062d.f3943G.setLayoutParams(new LayoutParams(this.f2062d.f3976x, this.f2062d.f3977y, 17));
                this.f2083y = true;
            }
        }
        if (this.f2083y) {
            double sqrt;
            this.f2083y = false;
            if (this.f2084z) {
                DisplayMetrics displayMetrics = AdColony.activity().getResources().getDisplayMetrics();
                float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
                float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
                sqrt = Math.sqrt((double) ((displayMetrics.heightPixels * displayMetrics.heightPixels) + (displayMetrics.widthPixels * displayMetrics.widthPixels))) / Math.sqrt((double) ((f * f) + (f2 * f2)));
                this.f2064f = sqrt / 280.0d < 0.7d ? 0.7d : sqrt / 280.0d;
                if (!AdColony.isTablet() && this.f2064f == 0.7d) {
                    this.f2064f = 1.0d;
                }
                float f3 = this.f2064f * 20.0d < 18.0d ? 18.0f : (float) (this.f2064f * 20.0d);
                if (this.f2064f * 20.0d < 18.0d) {
                    f = 18.0f;
                } else {
                    f = (float) (this.f2064f * 20.0d);
                }
                this.ai.setTextSize(f3);
                this.ak.setTextSize(f3);
                this.aj.setTextSize(f);
                if (!(!this.f2047L || this.at == null || this.au == null)) {
                    this.at.m2099a(m2191b(this.ab + (this.at.f1805f * 2)), this.at.f1806g);
                    this.au.m2099a(m2191b(this.ab + (this.au.f1805f * 2)), this.au.f1806g);
                }
                int i;
                if (this.f2062d.f3972t > this.f2062d.f3973u) {
                    i = this.f2062d.f3973u;
                } else {
                    i = this.f2062d.f3972t;
                }
                this.f2084z = false;
            }
            if (this.f2052Q) {
                if (b && this.f2059a != null) {
                    this.f2059a.setLayoutParams(new LayoutParams(this.f2062d.f3972t, this.f2062d.f3973u - this.f2071m, 17));
                }
                this.f2063e = ((double) this.f2062d.f3977y) / 640.0d < 0.9d ? 0.9d : ((double) this.f2062d.f3977y) / 640.0d;
                if (!AdColony.isTablet() && this.f2063e == 0.9d) {
                    this.f2063e = 1.2d;
                }
            }
            if (this.f2049N) {
                double d = (double) (this.f2072n / this.f2073o);
                sqrt = ((double) this.f2062d.f3972t) / d > ((double) this.f2062d.f3973u) / 1.0d ? ((double) this.f2062d.f3973u) / 1.0d : ((double) this.f2062d.f3972t) / d;
                this.f2062d.f3976x = (int) (d * sqrt);
                this.f2062d.f3977y = (int) (sqrt * 1.0d);
                this.f2063e = this.f2062d.f3972t > this.f2062d.f3973u ? ((double) this.f2062d.f3977y) / 640.0d : ((double) this.f2062d.f3977y) / 960.0d;
                if (((double) this.f2062d.f3972t) / ((double) this.f2072n) > ((double) this.f2062d.f3973u) / ((double) this.f2073o)) {
                    sqrt = ((double) this.f2062d.f3973u) / ((double) this.f2073o);
                } else {
                    sqrt = ((double) this.f2062d.f3972t) / ((double) this.f2072n);
                }
                this.am.m2097a(sqrt);
                this.am.m2107d(this.f2062d.f3972t, this.f2062d.f3973u);
            }
            if (!(!this.f2047L || this.at == null || this.au == null)) {
                if (this.at == null || this.au == null || this.at.f1801b == null || this.au.f1801b == null) {
                    this.f2047L = false;
                } else {
                    int height = (int) (((double) this.au.f1801b.getHeight()) * this.f2064f);
                    this.at.m2102b(this.at.f1805f, (int) (((double) this.at.f1801b.getHeight()) * this.f2064f));
                    this.au.m2102b(this.au.f1805f, height);
                }
            }
            if (this.f2048M) {
                this.an.m2097a(this.f2064f);
                this.ao.m2097a(this.f2064f);
            }
        }
    }

    void m2192b() {
        this.f2059a = new WebView(this.f2062d);
        this.f2059a.setFocusable(true);
        this.f2059a.setHorizontalScrollBarEnabled(false);
        this.f2059a.setVerticalScrollBarEnabled(false);
        WebSettings settings = this.f2059a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(PluginState.ON_DEMAND);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        this.f2059a.setWebChromeClient(new C07503(this));
        this.f2062d.f3953Q = new FrameLayout(this.f2062d);
        if (C0745a.m2164i("hardware_acceleration_disabled")) {
            try {
                this.f2062d.f3953Q.getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this.f2059a, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
        }
        this.az = new C0780m(this.f2062d, this.f2059a, this.f2062d);
        this.f2059a.setWebViewClient(new C07514(this));
    }

    public void m2195c() {
        if (this.f2059a != null) {
            if (this.f2062d.f3945I.f1848o.startsWith("<")) {
                this.f2059a.loadData(this.f2062d.f3945I.f1848o, "text/html", null);
            } else {
                this.f2059a.loadDataWithBaseURL(this.f2062d.f3945I.f1848o, this.af, "text/html", null, null);
            }
            new C0771f("htmltest").m2295a(this.af);
            m2188a("var is_tablet=" + (C0745a.f2002m ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE) + ";");
            String str = C0745a.f2002m ? "tablet" : "phone";
            m2188a("adc_bridge.adc_version='" + C0745a.ag + "'");
            m2188a("adc_bridge.os_version='" + C0745a.af + "'");
            m2188a("adc_bridge.os_name='android'");
            m2188a("adc_bridge.device_type='" + str + "'");
            m2188a("adc_bridge.fireChangeEvent({state:'default'});");
            m2188a("adc_bridge.fireReadyEvent()");
        }
    }

    public void onCompletion(MediaPlayer player) {
        m2197d();
    }

    public void m2197d() {
        C0762d c0762d = C0745a.f2001l;
        ADCVideo aDCVideo = this.f2062d;
        c0762d.m2254a(ADCVideo.f3932e, this.f2062d.f3945I);
        if (this.f2052Q && this.f2039D && C0745a.ab) {
            this.f2062d.f3952P.addView(this.f2061c);
            new Handler().postDelayed(new C07525(this), (long) (C0745a.ac * DateUtils.MILLIS_IN_SECOND));
        }
        if (C0745a.f1989Z) {
            m2200g();
        }
        C0745a.m2144a("card_shown", this.f2062d.f3945I);
        synchronized (this.aL) {
            this.ag = null;
            if (C0745a.f1983T.f1843j.f2281y.f2408e) {
                this.ag = new C0747b(C0745a.f1983T.f1843j.f2281y.f2410g);
            }
        }
        if (this.f2052Q) {
            Handler handler = new Handler();
            View view = new View(this.f2062d);
            Runnable c07536 = new C07536(this, view);
            view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.f2062d.f3952P.addView(view);
            handler.postDelayed(c07536, 500);
            this.f2062d.f3953Q.setVisibility(0);
        }
        this.f2062d.f3970r = System.currentTimeMillis();
        m2199f();
    }

    void m2198e() {
        this.f2059a.loadUrl(C0745a.ad);
        C0777l.f2239a.m2349a("Loading - end card url = ").m2353b(C0745a.ad);
    }

    void m2188a(String str) {
        if (!this.f2049N && this.f2059a != null) {
            if (VERSION.SDK_INT >= 19) {
                this.f2059a.evaluateJavascript(str, null);
            } else {
                this.f2059a.loadUrl("javascript:" + str);
            }
        }
    }

    void m2189a(boolean z) {
        if (!this.f2049N) {
            if (z) {
                m2188a("adc_bridge.fireChangeEvent({viewable:true});");
            } else {
                m2188a("adc_bridge.fireChangeEvent({viewable:false});");
            }
        }
    }

    void m2194b(boolean z) {
        if (!this.f2049N) {
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        m2196c(true);
        return true;
    }

    void m2199f() {
        new Handler().postDelayed(new C07547(this), 300);
        if (this.f2062d.f3948L) {
            this.f2062d.f3946J.stop();
        }
        ADCVideo aDCVideo = this.f2062d;
        ADCVideo.f3931d = true;
        if (this.f2062d.f3943G != null) {
            this.f2062d.f3943G.m2283a();
        }
        C1257v.f4038I = null;
        invalidate();
        this.f2044I = false;
        invalidate();
    }

    void m2200g() {
        if (this.f2062d == null) {
            return;
        }
        if (!this.f2052Q || (this.f2059a != null && this.f2062d.f3953Q != null && this.f2062d.f3952P != null)) {
            C0745a.f1976M = true;
            this.f2062d.f3971s = System.currentTimeMillis();
            ADCVideo aDCVideo = this.f2062d;
            aDCVideo.f3969q += ((double) (this.f2062d.f3971s - this.f2062d.f3970r)) / 1000.0d;
            C0745a.ak = true;
            for (int i = 0; i < C0745a.aq.size(); i++) {
                if (C0745a.aq.get(i) != null) {
                    ((AdColonyNativeAdView) C0745a.aq.get(i)).m2123a();
                }
            }
            try {
                this.f2062d.f3958V.close();
            } catch (Exception e) {
            }
            this.f2062d.finish();
            this.ag = null;
            if (this.f2052Q) {
                this.f2062d.f3952P.removeView(this.f2062d.f3953Q);
                this.f2059a.destroy();
                this.f2059a = null;
            }
            C0745a.m2139a(this.f2062d.f3945I);
            AdColonyBrowser.f1871A = true;
            C0745a.f1968E = true;
        }
    }

    void m2201h() {
        m2196c(false);
    }

    void m2196c(boolean z) {
        C0745a.f1976M = true;
        if (!C0745a.f1983T.m2117b() || z) {
            for (int i = 0; i < C0745a.aq.size(); i++) {
                if (C0745a.aq.get(i) != null) {
                    ((AdColonyNativeAdView) C0745a.aq.get(i)).m2123a();
                }
            }
            this.f2062d.finish();
            C0745a.f1986W.m2134b(this.f2062d.f3945I);
            C0745a.ak = true;
            C0745a.f1968E = true;
            AdColonyBrowser.f1871A = true;
            return;
        }
        ADCVideo aDCVideo = this.f2062d;
        ADCVideo.f3928a = this.f2062d.f3943G.getCurrentPosition();
        C1257v.f4038I = new C1257v(this.f2062d, (AdColonyV4VCAd) C0745a.f1983T);
    }

    void m2202i() {
        C0745a.m2144a("replay", this.f2062d.f3945I);
        ADCVideo aDCVideo = this.f2062d;
        ADCVideo.f3932e = true;
        aDCVideo = this.f2062d;
        ADCVideo.f3931d = false;
        aDCVideo = this.f2062d;
        ADCVideo.f3928a = 0;
        this.f2062d.f3945I.f1854u = true;
        this.f2062d.f3945I.f1849p = 0.0d;
        this.f2054S = false;
        View view = new View(this.f2062d);
        view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.f2062d.f3952P.addView(view, new LayoutParams(this.f2062d.f3972t, this.f2062d.f3973u, 17));
        new Handler().postDelayed(new C07558(this, view), 900);
        this.f2062d.f3943G.start();
        if (this.f2062d.f3948L) {
            try {
                this.f2062d.f3946J = HapticContentSDKFactory.GetNewSDKInstance(0, this.f2062d);
                this.f2062d.f3946J.openHaptics(this.f2062d.f3947K);
            } catch (Exception e) {
                this.f2062d.f3948L = false;
            }
            if (this.f2062d.f3946J == null) {
                this.f2062d.f3948L = false;
            }
            if (this.f2062d.f3948L) {
                this.f2062d.f3946J.play();
            }
        }
        C0745a.f2001l.m2246a(this.f2062d.f3945I);
        this.f2062d.f3943G.requestFocus();
        this.f2062d.f3943G.setBackgroundColor(0);
        this.f2062d.f3943G.setVisibility(0);
        m2189a(false);
    }

    int m2191b(String str) {
        this.aj.getTextWidths(str, aB);
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += aB[i];
        }
        return (int) f;
    }

    void m2203j() {
        getViewTreeObserver().addOnGlobalLayoutListener(new C07569(this, this));
    }

    void m2204k() {
        if (this.f2066h >= 70 && !this.f2040E) {
            this.f2040E = true;
            m2194b(true);
        } else if (this.f2040E && this.f2066h == 0) {
            this.f2040E = false;
            m2194b(false);
        }
    }

    void m2193b(int i) {
        this.f2066h = i;
        if (i < 0) {
            this.f2066h = 0;
        }
    }
}
