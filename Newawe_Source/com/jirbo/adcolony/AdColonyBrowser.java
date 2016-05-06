package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.android.volley.DefaultRetryPolicy;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class AdColonyBrowser extends Activity {
    static boolean f1871A;
    static boolean f1872B;
    static boolean f1873C;
    static boolean f1874a;
    public static String url;
    static boolean f1875v;
    static boolean f1876w;
    static boolean f1877x;
    static boolean f1878y;
    static boolean f1879z;
    WebView f1880b;
    ADCImage f1881c;
    ADCImage f1882d;
    ADCImage f1883e;
    ADCImage f1884f;
    ADCImage f1885g;
    ADCImage f1886h;
    ADCImage f1887i;
    ADCImage f1888j;
    ADCImage f1889k;
    RelativeLayout f1890l;
    RelativeLayout f1891m;
    boolean f1892n;
    boolean f1893o;
    boolean f1894p;
    boolean f1895q;
    ProgressBar f1896r;
    DisplayMetrics f1897s;
    C0733a f1898t;
    C0735c f1899u;

    /* renamed from: com.jirbo.adcolony.AdColonyBrowser.1 */
    class C07311 extends WebChromeClient {
        final /* synthetic */ AdColonyBrowser f1860a;

        C07311(AdColonyBrowser adColonyBrowser) {
            this.f1860a = adColonyBrowser;
        }

        public void onProgressChanged(WebView view, int progress) {
            this.f1860a.setProgress(progress * DateUtils.MILLIS_IN_SECOND);
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyBrowser.2 */
    class C07322 extends WebViewClient {
        final /* synthetic */ AdColonyBrowser f1861a;

        C07322(AdColonyBrowser adColonyBrowser) {
            this.f1861a = adColonyBrowser;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.startsWith("market://") && !url.startsWith("amzn://")) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            if (C0745a.f1984U != null) {
                C0745a.f1984U.startActivity(intent);
            }
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (!AdColonyBrowser.f1873C) {
                AdColonyBrowser.f1877x = true;
                AdColonyBrowser.f1878y = false;
                this.f1861a.f1896r.setVisibility(0);
            }
            this.f1861a.f1898t.invalidate();
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failing_url) {
            C0777l.f2242d.m2349a("Error viewing URL: ").m2353b((Object) description);
            this.f1861a.finish();
        }

        public void onPageFinished(WebView view, String url) {
            if (!AdColonyBrowser.f1873C) {
                AdColonyBrowser.f1878y = true;
                AdColonyBrowser.f1877x = false;
                this.f1861a.f1896r.setVisibility(4);
                AdColonyBrowser.f1875v = this.f1861a.f1880b.canGoBack();
                AdColonyBrowser.f1876w = this.f1861a.f1880b.canGoForward();
            }
            this.f1861a.f1898t.invalidate();
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyBrowser.a */
    class C0733a extends View {
        Rect f1862a;
        Paint f1863b;
        final /* synthetic */ AdColonyBrowser f1864c;

        public C0733a(AdColonyBrowser adColonyBrowser, Activity activity) {
            this.f1864c = adColonyBrowser;
            super(activity);
            this.f1862a = new Rect();
            this.f1863b = new Paint();
        }

        public void onDraw(Canvas canvas) {
            getDrawingRect(this.f1862a);
            int height = (this.f1864c.f1890l.getHeight() - this.f1864c.f1881c.f1806g) / 2;
            if (AdColonyBrowser.f1875v) {
                this.f1864c.f1888j.m2101a(canvas, this.f1864c.f1881c.f1805f, height);
            } else {
                this.f1864c.f1881c.m2101a(canvas, this.f1864c.f1881c.f1805f, height);
            }
            if (AdColonyBrowser.f1876w) {
                this.f1864c.f1889k.m2101a(canvas, (this.f1864c.f1881c.m2104c() + (this.f1864c.f1890l.getWidth() / 10)) + this.f1864c.f1881c.f1805f, height);
            } else {
                this.f1864c.f1884f.m2101a(canvas, (this.f1864c.f1881c.m2104c() + (this.f1864c.f1890l.getWidth() / 10)) + this.f1864c.f1881c.f1805f, height);
            }
            if (AdColonyBrowser.f1877x) {
                this.f1864c.f1882d.m2101a(canvas, (this.f1864c.f1884f.m2104c() + this.f1864c.f1884f.f1805f) + (this.f1864c.f1890l.getWidth() / 10), height);
            } else {
                this.f1864c.f1883e.m2101a(canvas, (this.f1864c.f1884f.m2104c() + this.f1864c.f1884f.f1805f) + (this.f1864c.f1890l.getWidth() / 10), height);
            }
            this.f1864c.f1885g.m2101a(canvas, this.f1864c.f1890l.getWidth() - (this.f1864c.f1885g.f1805f * 2), height);
            if (this.f1864c.f1892n) {
                this.f1864c.f1886h.m2105c((this.f1864c.f1881c.m2104c() - (this.f1864c.f1886h.f1805f / 2)) + (this.f1864c.f1881c.f1805f / 2), (this.f1864c.f1881c.m2106d() - (this.f1864c.f1886h.f1806g / 2)) + (this.f1864c.f1881c.f1806g / 2));
                this.f1864c.f1886h.m2100a(canvas);
            }
            if (this.f1864c.f1893o) {
                this.f1864c.f1886h.m2105c((this.f1864c.f1884f.m2104c() - (this.f1864c.f1886h.f1805f / 2)) + (this.f1864c.f1884f.f1805f / 2), (this.f1864c.f1884f.m2106d() - (this.f1864c.f1886h.f1806g / 2)) + (this.f1864c.f1884f.f1806g / 2));
                this.f1864c.f1886h.m2100a(canvas);
            }
            if (this.f1864c.f1894p) {
                this.f1864c.f1886h.m2105c((this.f1864c.f1883e.m2104c() - (this.f1864c.f1886h.f1805f / 2)) + (this.f1864c.f1883e.f1805f / 2), (this.f1864c.f1883e.m2106d() - (this.f1864c.f1886h.f1806g / 2)) + (this.f1864c.f1883e.f1806g / 2));
                this.f1864c.f1886h.m2100a(canvas);
            }
            if (this.f1864c.f1895q) {
                this.f1864c.f1886h.m2105c((this.f1864c.f1885g.m2104c() - (this.f1864c.f1886h.f1805f / 2)) + (this.f1864c.f1885g.f1805f / 2), (this.f1864c.f1885g.m2106d() - (this.f1864c.f1886h.f1806g / 2)) + (this.f1864c.f1885g.f1806g / 2));
                this.f1864c.f1886h.m2100a(canvas);
            }
            m2120a();
        }

        public void m2120a() {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f1864c.f1896r.getWidth(), this.f1864c.f1896r.getHeight());
            layoutParams.topMargin = (this.f1864c.f1890l.getHeight() - this.f1864c.f1882d.f1806g) / 2;
            layoutParams.leftMargin = ((this.f1864c.f1890l.getWidth() / 10) + this.f1864c.f1882d.m2104c()) + this.f1864c.f1882d.f1805f;
            if (AdColonyBrowser.f1879z && this.f1864c.f1882d.m2104c() != 0) {
                this.f1864c.f1891m.removeView(this.f1864c.f1896r);
                this.f1864c.f1891m.addView(this.f1864c.f1896r, layoutParams);
                AdColonyBrowser.f1879z = false;
            }
            if (this.f1864c.f1896r.getLayoutParams() != null) {
                this.f1864c.f1896r.getLayoutParams().height = this.f1864c.f1882d.f1806g;
                this.f1864c.f1896r.getLayoutParams().width = this.f1864c.f1882d.f1805f;
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (action == 0) {
                if (m2121a(this.f1864c.f1881c, x, y) && AdColonyBrowser.f1875v) {
                    this.f1864c.f1892n = true;
                    invalidate();
                    return true;
                } else if (m2121a(this.f1864c.f1884f, x, y) && AdColonyBrowser.f1876w) {
                    this.f1864c.f1893o = true;
                    invalidate();
                    return true;
                } else if (m2121a(this.f1864c.f1883e, x, y)) {
                    this.f1864c.f1894p = true;
                    invalidate();
                    return true;
                } else if (m2121a(this.f1864c.f1885g, x, y)) {
                    this.f1864c.f1895q = true;
                    invalidate();
                    return true;
                }
            }
            if (action == 1) {
                if (m2121a(this.f1864c.f1881c, x, y) && AdColonyBrowser.f1875v) {
                    this.f1864c.f1880b.goBack();
                    m2122b();
                    return true;
                } else if (m2121a(this.f1864c.f1884f, x, y) && AdColonyBrowser.f1876w) {
                    this.f1864c.f1880b.goForward();
                    m2122b();
                    return true;
                } else if (m2121a(this.f1864c.f1883e, x, y) && AdColonyBrowser.f1877x) {
                    this.f1864c.f1880b.stopLoading();
                    m2122b();
                    return true;
                } else if (m2121a(this.f1864c.f1883e, x, y) && !AdColonyBrowser.f1877x) {
                    this.f1864c.f1880b.reload();
                    m2122b();
                    return true;
                } else if (m2121a(this.f1864c.f1885g, x, y)) {
                    AdColonyBrowser.f1873C = true;
                    this.f1864c.f1880b.loadData(StringUtils.EMPTY, "text/html", "utf-8");
                    AdColonyBrowser.f1876w = false;
                    AdColonyBrowser.f1875v = false;
                    AdColonyBrowser.f1877x = false;
                    m2122b();
                    this.f1864c.finish();
                    return true;
                } else {
                    m2122b();
                }
            }
            return false;
        }

        public void m2122b() {
            this.f1864c.f1892n = false;
            this.f1864c.f1893o = false;
            this.f1864c.f1894p = false;
            this.f1864c.f1895q = false;
            invalidate();
        }

        public boolean m2121a(ADCImage aDCImage, int i, int i2) {
            return i < (aDCImage.m2104c() + aDCImage.f1805f) + 16 && i > aDCImage.m2104c() - 16 && i2 < (aDCImage.m2106d() + aDCImage.f1806g) + 16 && i2 > aDCImage.m2106d() - 16;
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyBrowser.b */
    class C0734b extends View {
        Rect f1865a;
        final /* synthetic */ AdColonyBrowser f1866b;

        public C0734b(AdColonyBrowser adColonyBrowser, Activity activity) {
            this.f1866b = adColonyBrowser;
            super(activity);
            this.f1865a = new Rect();
        }

        public void onDraw(Canvas canvas) {
            if (!AdColonyBrowser.f1878y) {
                canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
                getDrawingRect(this.f1865a);
                this.f1866b.f1887i.m2101a(canvas, (this.f1865a.width() - this.f1866b.f1887i.f1805f) / 2, (this.f1865a.height() - this.f1866b.f1887i.f1806g) / 2);
                invalidate();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyBrowser.c */
    class C0735c extends View {
        Paint f1867a;
        ADCImage f1868b;
        ADCImage f1869c;
        final /* synthetic */ AdColonyBrowser f1870d;

        public C0735c(AdColonyBrowser adColonyBrowser, Activity activity) {
            this.f1870d = adColonyBrowser;
            super(activity);
            this.f1867a = new Paint();
            this.f1868b = new ADCImage(C0745a.m2165j("close_image_normal"));
            this.f1869c = new ADCImage(C0745a.m2165j("close_image_down"));
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
            this.f1867a.setColor(-3355444);
            this.f1867a.setStrokeWidth(10.0f);
            this.f1867a.setStyle(Style.STROKE);
            this.f1867a.setShadowLayer(3.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, ViewCompat.MEASURED_STATE_MASK);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawRect(0.0f, 0.0f, (float) this.f1870d.f1890l.getWidth(), 10.0f, this.f1867a);
        }
    }

    public AdColonyBrowser() {
        this.f1892n = false;
        this.f1893o = false;
        this.f1894p = false;
        this.f1895q = false;
    }

    static {
        f1874a = true;
        f1875v = false;
        f1876w = false;
        f1877x = false;
        f1878y = false;
        f1879z = true;
        f1871A = false;
        f1872B = false;
        f1873C = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f1872B = true;
        this.f1881c = new ADCImage(C0745a.m2165j("browser_back_image_normal"));
        this.f1882d = new ADCImage(C0745a.m2165j("browser_stop_image_normal"));
        this.f1883e = new ADCImage(C0745a.m2165j("browser_reload_image_normal"));
        this.f1884f = new ADCImage(C0745a.m2165j("browser_forward_image_normal"));
        this.f1885g = new ADCImage(C0745a.m2165j("browser_close_image_normal"));
        this.f1886h = new ADCImage(C0745a.m2165j("browser_glow_button"));
        this.f1887i = new ADCImage(C0745a.m2165j("browser_icon"));
        this.f1888j = new ADCImage(C0745a.m2165j("browser_back_image_normal"), true);
        this.f1889k = new ADCImage(C0745a.m2165j("browser_forward_image_normal"), true);
        this.f1897s = AdColony.activity().getResources().getDisplayMetrics();
        float f = ((float) this.f1897s.widthPixels) / this.f1897s.xdpi;
        float f2 = ((float) this.f1897s.heightPixels) / this.f1897s.ydpi;
        double sqrt = (Math.sqrt((double) ((this.f1897s.widthPixels * this.f1897s.widthPixels) + (this.f1897s.heightPixels * this.f1897s.heightPixels))) / Math.sqrt((double) ((f * f) + (f2 * f2)))) / 220.0d;
        if (sqrt > 1.8d) {
            sqrt = 1.8d;
        }
        f1879z = true;
        f1875v = false;
        f1876w = false;
        f1873C = false;
        this.f1881c.m2097a(sqrt);
        this.f1882d.m2097a(sqrt);
        this.f1883e.m2097a(sqrt);
        this.f1884f.m2097a(sqrt);
        this.f1885g.m2097a(sqrt);
        this.f1886h.m2097a(sqrt);
        this.f1888j.m2097a(sqrt);
        this.f1889k.m2097a(sqrt);
        this.f1896r = new ProgressBar(this);
        this.f1896r.setVisibility(4);
        this.f1891m = new RelativeLayout(this);
        this.f1890l = new RelativeLayout(this);
        this.f1890l.setBackgroundColor(-3355444);
        if (C0745a.f2002m) {
            this.f1890l.setLayoutParams(new RelativeLayout.LayoutParams(-1, (int) (((double) this.f1881c.f1806g) * 1.5d)));
        } else {
            this.f1890l.setLayoutParams(new RelativeLayout.LayoutParams(-1, (int) (((double) this.f1881c.f1806g) * 1.5d)));
        }
        requestWindowFeature(1);
        getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        getWindow().requestFeature(2);
        setVolumeControlStream(3);
        this.f1880b = new WebView(this);
        this.f1880b.getSettings().setJavaScriptEnabled(true);
        this.f1880b.getSettings().setBuiltInZoomControls(true);
        this.f1880b.getSettings().setUseWideViewPort(true);
        this.f1880b.getSettings().setLoadWithOverviewMode(true);
        this.f1880b.getSettings().setGeolocationEnabled(true);
        if (f1874a) {
            if (C0745a.f2002m) {
                setRequestedOrientation(C0745a.f1969F);
            } else if (VERSION.SDK_INT >= 10) {
                setRequestedOrientation(6);
            } else {
                setRequestedOrientation(0);
            }
        }
        f1874a = true;
        this.f1880b.setWebChromeClient(new C07311(this));
        this.f1880b.setWebViewClient(new C07322(this));
        this.f1898t = new C0733a(this, this);
        this.f1899u = new C0735c(this, this);
        this.f1891m.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        this.f1891m.addView(this.f1890l);
        this.f1890l.setId(12345);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.f1897s.heightPixels - ((int) (((double) this.f1885g.f1806g) * 1.5d)));
        layoutParams.addRule(3, this.f1890l.getId());
        this.f1891m.addView(this.f1880b, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, 20);
        layoutParams.addRule(3, this.f1890l.getId());
        layoutParams.setMargins(0, -10, 0, 0);
        this.f1891m.addView(this.f1899u, layoutParams);
        int i = this.f1897s.widthPixels > this.f1897s.heightPixels ? this.f1897s.widthPixels : this.f1897s.heightPixels;
        this.f1891m.addView(this.f1898t, new RelativeLayout.LayoutParams(i * 2, i * 2));
        layoutParams = new RelativeLayout.LayoutParams(-2, this.f1897s.heightPixels - ((int) (((double) this.f1885g.f1806g) * 1.5d)));
        layoutParams.addRule(3, this.f1890l.getId());
        this.f1891m.addView(new C0734b(this, this), layoutParams);
        setContentView(this.f1891m);
        this.f1880b.loadUrl(url);
        C0777l.f2241c.m2349a("Viewing ").m2353b(url);
    }

    public void onWindowFocusChanged(boolean has_focus) {
        super.onWindowFocusChanged(has_focus);
    }

    public void onPause() {
        super.onPause();
        this.f1898t.m2122b();
    }

    public void onResume() {
        super.onResume();
        f1879z = true;
        this.f1898t.invalidate();
    }

    public void onConfigurationChanged(Configuration new_config) {
        super.onConfigurationChanged(new_config);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.f1897s.heightPixels - ((int) (1.5d * ((double) this.f1885g.f1806g))));
        layoutParams.addRule(3, this.f1890l.getId());
        this.f1880b.setLayoutParams(layoutParams);
        f1879z = true;
        this.f1898t.invalidate();
    }

    public void onDestroy() {
        if (!C0745a.f1967D && f1871A) {
            for (int i = 0; i < C0745a.an.size(); i++) {
                ((Bitmap) C0745a.an.get(i)).recycle();
            }
            C0745a.an.clear();
        }
        f1871A = false;
        f1872B = false;
        super.onDestroy();
    }
}
