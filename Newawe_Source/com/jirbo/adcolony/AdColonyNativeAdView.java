package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.Newawe.storage.DatabaseOpenHelper;
import com.android.volley.DefaultRetryPolicy;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.C0807n.C0781a;
import com.jirbo.adcolony.C0807n.ad;
import java.io.FileInputStream;
import org.apache.commons.lang.StringUtils;

public class AdColonyNativeAdView extends FrameLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    boolean f1909A;
    boolean f1910B;
    boolean f1911C;
    boolean f1912D;
    boolean f1913E;
    boolean f1914F;
    boolean f1915G;
    boolean f1916H;
    boolean f1917I;
    boolean f1918J;
    boolean f1919K;
    String f1920L;
    AdColonyInterstitialAd f1921M;
    AdColonyNativeAdListener f1922N;
    AdColonyNativeAdMutedListener f1923O;
    ADCImage f1924P;
    ADCImage f1925Q;
    ADCImage f1926R;
    ImageView f1927S;
    C0740b f1928T;
    View f1929U;
    Bitmap f1930V;
    ADCImage f1931W;
    TextView f1932a;
    ad aA;
    C0781a aB;
    float aC;
    float aD;
    float aE;
    boolean aF;
    boolean aG;
    boolean aH;
    LayoutParams aI;
    LayoutParams aJ;
    FileInputStream aK;
    ImageView aa;
    boolean ab;
    Button ac;
    String ad;
    String ae;
    String af;
    MediaPlayer ag;
    Surface ah;
    String ai;
    String aj;
    String ak;
    String al;
    String am;
    String an;
    String ao;
    String ap;
    String aq;
    AdColonyIAPEngagement ar;
    int as;
    int at;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    int az;
    TextView f1933b;
    TextView f1934c;
    Activity f1935d;
    String f1936e;
    String f1937f;
    ViewGroup f1938g;
    SurfaceTexture f1939h;
    int f1940i;
    int f1941j;
    int f1942k;
    int f1943l;
    boolean f1944m;
    boolean f1945n;
    boolean f1946o;
    boolean f1947p;
    boolean f1948q;
    boolean f1949r;
    boolean f1950s;
    boolean f1951t;
    boolean f1952u;
    boolean f1953v;
    boolean f1954w;
    boolean f1955x;
    boolean f1956y;
    boolean f1957z;

    /* renamed from: com.jirbo.adcolony.AdColonyNativeAdView.1 */
    class C07361 implements OnTouchListener {
        final /* synthetic */ AdColonyNativeAdView f1901a;

        C07361(AdColonyNativeAdView adColonyNativeAdView) {
            this.f1901a = adColonyNativeAdView;
        }

        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == 0) {
                float[] fArr = new float[3];
                Color.colorToHSV(this.f1901a.ax, fArr);
                fArr[2] = fArr[2] * 0.8f;
                this.f1901a.ac.setBackgroundColor(Color.HSVToColor(fArr));
            } else if (action == 3) {
                this.f1901a.ac.setBackgroundColor(this.f1901a.ax);
            } else if (action == 1) {
                if (this.f1901a.f1918J) {
                    this.f1901a.ar = AdColonyIAPEngagement.OVERLAY;
                    this.f1901a.f1952u = true;
                } else {
                    if (this.f1901a.af.equals("install") || this.f1901a.af.equals(DatabaseOpenHelper.HISTORY_ROW_URL)) {
                        C0745a.f2001l.f2144d.m4737b("native_overlay_click", this.f1901a.f1921M);
                        try {
                            C0745a.m2148b().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f1901a.ae)));
                        } catch (Exception e) {
                            Toast.makeText(C0745a.m2148b(), "Unable to open store.", 0).show();
                        }
                    }
                    this.f1901a.ac.setBackgroundColor(this.f1901a.ax);
                }
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyNativeAdView.2 */
    class C07372 implements OnClickListener {
        final /* synthetic */ AdColonyNativeAdView f1902a;

        C07372(AdColonyNativeAdView adColonyNativeAdView) {
            this.f1902a = adColonyNativeAdView;
        }

        public void onClick(View v) {
            if (this.f1902a.f1949r) {
                if (this.f1902a.f1923O != null) {
                    this.f1902a.f1923O.onAdColonyNativeAdMuted(this.f1902a, true);
                }
                this.f1902a.m2128a(true, true);
                this.f1902a.f1955x = true;
            } else if (this.f1902a.f1930V == this.f1902a.f1926R.f1800a) {
                if (this.f1902a.f1923O != null) {
                    this.f1902a.f1923O.onAdColonyNativeAdMuted(this.f1902a, false);
                }
                this.f1902a.f1955x = false;
                this.f1902a.m2128a(false, true);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyNativeAdView.a */
    class C0739a extends TextureView implements SurfaceTextureListener {
        boolean f1904a;
        boolean f1905b;
        final /* synthetic */ AdColonyNativeAdView f1906c;

        /* renamed from: com.jirbo.adcolony.AdColonyNativeAdView.a.1 */
        class C07381 implements Runnable {
            final /* synthetic */ C0739a f1903a;

            C07381(C0739a c0739a) {
                this.f1903a = c0739a;
            }

            public void run() {
                if (!this.f1903a.f1906c.f1957z && !this.f1903a.f1906c.f1909A) {
                    this.f1903a.f1905b = false;
                    this.f1903a.f1906c.f1952u = true;
                    this.f1903a.f1906c.f1927S.setVisibility(8);
                }
            }
        }

        C0739a(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this(adColonyNativeAdView, context, false);
        }

        C0739a(AdColonyNativeAdView adColonyNativeAdView, Context context, boolean z) {
            this.f1906c = adColonyNativeAdView;
            super(context);
            this.f1904a = false;
            this.f1905b = false;
            setSurfaceTextureListener(this);
            setWillNotDraw(false);
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.f1904a = z;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture texture, int w, int h) {
            if (texture == null) {
                this.f1906c.f1952u = true;
                this.f1906c.f1927S.setVisibility(8);
                return;
            }
            this.f1906c.f1928T.setVisibility(0);
            this.f1906c.f1939h = texture;
            if (!this.f1906c.f1952u && !this.f1904a) {
                this.f1906c.ah = new Surface(texture);
                if (this.f1906c.ag != null) {
                    this.f1906c.ag.release();
                }
                this.f1906c.f1940i = w;
                this.f1906c.f1941j = h;
                this.f1906c.ag = new MediaPlayer();
                try {
                    this.f1906c.aK = new FileInputStream(this.f1906c.f1937f);
                    this.f1906c.ag.setDataSource(this.f1906c.aK.getFD());
                    this.f1906c.ag.setSurface(this.f1906c.ah);
                    this.f1906c.ag.setOnCompletionListener(this.f1906c);
                    this.f1906c.ag.setOnPreparedListener(this.f1906c);
                    this.f1906c.ag.setOnErrorListener(this.f1906c);
                    this.f1906c.ag.prepareAsync();
                    C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Prepare called.");
                    this.f1905b = true;
                    Handler handler = new Handler();
                    Runnable c07381 = new C07381(this);
                    if (!this.f1905b) {
                        handler.postDelayed(c07381, 1800);
                    }
                } catch (Exception e) {
                    this.f1906c.f1952u = true;
                    this.f1906c.f1927S.setVisibility(8);
                }
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture texture, int w, int h) {
            C0777l.f2241c.m2353b((Object) "[ADC] onSurfaceTextureSizeChanged");
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture texture) {
            C0777l.f2241c.m2353b((Object) "[ADC] Native surface destroyed");
            this.f1906c.f1957z = false;
            this.f1906c.f1927S.setVisibility(4);
            this.f1906c.f1928T.setVisibility(0);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture texture) {
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            if (action == 1 && C0745a.f1968E && C0811q.m2489c() && (x <= ((float) ((this.f1906c.au - this.f1906c.f1925Q.f1805f) + 8)) || y >= ((float) (this.f1906c.f1925Q.f1806g + 8)) || this.f1906c.f1952u || this.f1906c.ag == null || !this.f1906c.ag.isPlaying())) {
                C0745a.f1983T = this.f1906c.f1921M;
                C0745a.f2001l.f2141a.m2238a(this.f1906c.f1936e, this.f1906c.f1921M.j);
                ADCVideo.m4677a();
                if (this.f1906c.f1919K) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f1906c.f1920L));
                        if (C0745a.f1979P != null) {
                            C0745a.m2148b().startActivity(intent);
                        }
                    } catch (Exception e) {
                        if (C0745a.f1979P != null) {
                            Toast.makeText(C0745a.m2148b(), "Unable to open store.", 0).show();
                        }
                    }
                } else {
                    this.f1906c.f1921M.k = "native";
                    this.f1906c.f1921M.l = "fullscreen";
                    this.f1906c.f1921M.t = true;
                    this.f1906c.f1921M.u = this.f1906c.f1911C;
                    if ((this.f1906c.f1957z || this.f1906c.f1952u) && C0811q.m2489c()) {
                        if (this.f1906c.f1922N != null) {
                            this.f1906c.f1922N.onAdColonyNativeAdStarted(true, this.f1906c);
                        }
                        if (this.f1906c.ag == null || !this.f1906c.ag.isPlaying()) {
                            this.f1906c.f1921M.q = 0.0d;
                            ADCVideo.f3930c = 0;
                        } else {
                            ADCVideo.f3930c = this.f1906c.ag.getCurrentPosition();
                            this.f1906c.f1921M.q = this.f1906c.f1921M.p;
                            this.f1906c.ag.pause();
                        }
                        C0745a.f1968E = false;
                        C0745a.f2001l.f2144d.m4737b("video_expanded", this.f1906c.f1921M);
                        if (C0745a.f2002m) {
                            C0777l.f2239a.m2353b((Object) "Launching AdColonyOverlay");
                            C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyOverlay.class));
                        } else {
                            C0777l.f2239a.m2353b((Object) "Launching AdColonyFullscreen");
                            C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyFullscreen.class));
                        }
                        if (this.f1906c.f1952u) {
                            af afVar = this.f1906c.f1921M.i.f2321r;
                            afVar.f2090d++;
                        }
                        this.f1906c.f1952u = true;
                        this.f1906c.f1911C = true;
                    }
                }
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColonyNativeAdView.b */
    class C0740b extends View {
        boolean f1907a;
        final /* synthetic */ AdColonyNativeAdView f1908b;

        public C0740b(AdColonyNativeAdView adColonyNativeAdView, Context context) {
            this.f1908b = adColonyNativeAdView;
            super(context);
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }

        public void onDraw(Canvas canvas) {
            this.f1908b.f1938g = (ViewGroup) getParent().getParent();
            Rect rect = new Rect();
            if (!(this.f1908b.ag == null || this.f1908b.ag.isPlaying() || !this.f1908b.f1945n)) {
                this.f1907a = false;
            }
            if (getLocalVisibleRect(rect) && VERSION.SDK_INT >= 14 && this.f1908b.f1957z) {
                if ((!this.f1908b.f1945n || (this.f1908b.f1945n && (rect.top == 0 || rect.bottom - rect.top > this.f1908b.getNativeAdHeight()))) && rect.bottom - rect.top > this.f1908b.getNativeAdHeight() / 2 && rect.right - rect.left > this.f1908b.getNativeAdWidth() / 2) {
                    if (this.f1907a || this.f1908b.f1952u || this.f1908b.ag == null || this.f1908b.ag.isPlaying() || this.f1908b.f1909A || this.f1908b.f1921M.m4683a(true) || !this.f1908b.f1951t) {
                    }
                    if (!this.f1908b.f1951t) {
                        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Starting");
                        this.f1908b.m2129b();
                        this.f1908b.f1951t = true;
                        this.f1908b.f1921M.k = "native";
                        this.f1908b.f1921M.l = "native";
                    } else if (!this.f1908b.f1953v && this.f1908b.ag != null && C0811q.m2489c() && !this.f1908b.ag.isPlaying() && !C0745a.f1965B) {
                        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Resuming");
                        C0745a.f2001l.f2144d.m4737b("video_resumed", this.f1908b.f1921M);
                        if (!this.f1908b.f1949r) {
                            this.f1908b.m2132c(true);
                        }
                        this.f1908b.setVolume(this.f1908b.aD);
                        this.f1908b.ag.seekTo(this.f1908b.f1921M.r);
                        this.f1908b.ag.start();
                    } else if (!(this.f1908b.f1952u || this.f1908b.f1951t || (C0745a.f2001l.m2256a(this.f1908b.f1921M.h, true, false) && this.f1908b.aH))) {
                        this.f1908b.f1952u = true;
                        setVisibility(0);
                        this.f1908b.f1927S.setVisibility(8);
                    }
                }
                this.f1907a = true;
            } else {
                this.f1907a = false;
            }
            if (!(this.f1908b.f1952u || C0811q.m2489c() || this.f1908b.ag == null || this.f1908b.ag.isPlaying())) {
                setVisibility(0);
                this.f1908b.f1927S.setVisibility(8);
                this.f1908b.f1952u = true;
            }
            if (!this.f1908b.f1952u && this.f1908b.ag != null && this.f1908b.ag.isPlaying()) {
                setVisibility(4);
                this.f1908b.f1927S.setVisibility(0);
            } else if (this.f1908b.f1952u || this.f1908b.f1953v) {
                canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
                this.f1908b.f1927S.setVisibility(8);
                this.f1908b.f1924P.m2101a(canvas, (this.f1908b.au - this.f1908b.f1924P.f1805f) / 2, (this.f1908b.av - this.f1908b.f1924P.f1806g) / 2);
            }
            if (!this.f1908b.f1909A && !this.f1908b.f1952u) {
                invalidate();
            }
        }
    }

    public AdColonyNativeAdView(Activity context, String zone_id, int width) {
        super(context);
        this.f1910B = true;
        this.f1912D = true;
        this.ab = false;
        this.ad = StringUtils.EMPTY;
        this.ae = StringUtils.EMPTY;
        this.af = StringUtils.EMPTY;
        this.ap = StringUtils.EMPTY;
        this.aq = StringUtils.EMPTY;
        this.ar = AdColonyIAPEngagement.NONE;
        this.av = -1;
        this.ax = -3355444;
        this.ay = ViewCompat.MEASURED_STATE_MASK;
        this.aC = 0.25f;
        this.aD = 0.25f;
        m2125a(context, zone_id, width);
        m2123a();
    }

    public AdColonyNativeAdView(Activity context, String zone_id, int width, int height) {
        super(context);
        this.f1910B = true;
        this.f1912D = true;
        this.ab = false;
        this.ad = StringUtils.EMPTY;
        this.ae = StringUtils.EMPTY;
        this.af = StringUtils.EMPTY;
        this.ap = StringUtils.EMPTY;
        this.aq = StringUtils.EMPTY;
        this.ar = AdColonyIAPEngagement.NONE;
        this.av = -1;
        this.ax = -3355444;
        this.ay = ViewCompat.MEASURED_STATE_MASK;
        this.aC = 0.25f;
        this.aD = 0.25f;
        m2126a(context, zone_id, width, height);
        m2127a(false);
    }

    AdColonyNativeAdView(Activity context, String zone_id, int width, boolean is_private) {
        super(context);
        this.f1910B = true;
        this.f1912D = true;
        this.ab = false;
        this.ad = StringUtils.EMPTY;
        this.ae = StringUtils.EMPTY;
        this.af = StringUtils.EMPTY;
        this.ap = StringUtils.EMPTY;
        this.aq = StringUtils.EMPTY;
        this.ar = AdColonyIAPEngagement.NONE;
        this.av = -1;
        this.ax = -3355444;
        this.ay = ViewCompat.MEASURED_STATE_MASK;
        this.aC = 0.25f;
        this.aD = 0.25f;
        this.f1915G = is_private;
        m2125a(context, zone_id, width);
        m2123a();
    }

    void m2125a(Activity activity, String str, int i) {
        m2126a(activity, str, i, 0);
    }

    void m2126a(Activity activity, String str, int i, int i2) {
        int i3;
        int i4;
        C0745a.m2156e();
        C0745a.am = 0;
        this.f1935d = activity;
        this.f1936e = str;
        this.au = i;
        this.f1942k = i;
        if (i2 != 0) {
            this.f1943l = i2;
            this.av = i2;
            this.f1946o = true;
        }
        this.f1949r = true;
        this.aE = C0745a.m2148b().getResources().getDisplayMetrics().density;
        Display defaultDisplay = C0745a.m2148b().getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT >= 14) {
            Point point = new Point();
            defaultDisplay.getSize(point);
            i3 = point.x;
            i4 = point.y;
        } else {
            i3 = defaultDisplay.getWidth();
            i4 = defaultDisplay.getHeight();
        }
        if (i3 >= i4) {
            i3 = i4;
        }
        this.az = i3;
        this.f1921M = new AdColonyInterstitialAd(str);
        this.f1921M.k = "native";
        this.f1921M.l = "native";
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (!this.f1910B) {
            return;
        }
        if (!this.f1921M.m2118b(true) || this.f1921M.j == null || this.f1921M.j.f2255A == null) {
            if (!this.f1915G) {
                C0777l.f2242d.m2353b((Object) "AdColonyNativeAdView created while no ads are available, returning blank view.");
                this.f1921M.g = 5;
                C0745a.f2001l.f2144d.m4732a(str, this.f1921M);
            }
            this.aH = true;
            return;
        }
        this.aA = this.f1921M.i;
        if (!this.f1915G) {
            if (!this.f1915G) {
                C0745a.aq.add(this);
            }
            this.f1921M.i.m2396c();
            if (!this.f1921M.m4683a(true)) {
                this.aH = true;
            }
            this.f1921M.j.f2255A.f2466i = true;
            C0745a.f2001l.f2144d.m4732a(str, this.f1921M);
        }
    }

    public boolean isEngagementEnabled() {
        return this.ab;
    }

    public String getEngagementLabel() {
        if (this.ad == null) {
            return StringUtils.EMPTY;
        }
        return this.ad;
    }

    public String getEngagementCommand() {
        if (this.ae == null) {
            return StringUtils.EMPTY;
        }
        return this.ae;
    }

    public String getEngagementType() {
        if (this.af == null) {
            return StringUtils.EMPTY;
        }
        return this.af;
    }

    void m2123a() {
        m2127a(true);
    }

    void m2127a(boolean z) {
        this.f1957z = false;
        this.f1948q = false;
        setWillNotDraw(false);
        this.f1921M.x = this;
        if (this.f1910B) {
            if (C0745a.f2001l == null || C0745a.f2001l.f2141a == null || this.f1921M == null || this.f1921M.h == null || (!C0745a.f2001l.m2256a(this.f1921M.h, true, false) && this.aH)) {
                this.f1952u = true;
            } else {
                C0745a.f2001l.f2141a.m2240b(this.f1936e);
            }
            this.f1937f = C0745a.m2165j("video_filepath");
            this.ai = C0745a.m2165j("advertiser_name");
            this.aj = C0745a.m2165j("description");
            this.ak = C0745a.m2165j(DatabaseOpenHelper.HISTORY_ROW_TITLE);
            this.al = C0745a.m2165j("poster_image");
            this.am = C0745a.m2165j("unmute");
            this.an = C0745a.m2165j("mute");
            this.ao = C0745a.m2165j("thumb_image");
            this.ab = C0745a.m2164i("native_engagement_enabled");
            this.ad = C0745a.m2165j("native_engagement_label");
            this.ae = C0745a.m2165j("native_engagement_command");
            this.af = C0745a.m2165j("native_engagement_type");
            this.f1918J = C0745a.m2164i("v4iap_enabled");
            this.f1919K = C0745a.m2164i("click_to_install");
            this.f1920L = C0745a.m2165j("store_url");
            this.aq = C0745a.ad;
            if (this.f1918J) {
                this.ar = AdColonyIAPEngagement.AUTOMATIC;
            }
            this.ap = C0745a.m2165j("product_id");
            if (this.f1921M.j == null || this.f1921M.j.f2255A == null) {
                this.f1956y = true;
            } else {
                this.f1956y = this.f1921M.j.f2255A.f2459b;
            }
            if (this.aA != null) {
                this.aA.m2407m();
            }
            if (this.f1921M.j == null || this.f1921M.j.f2255A == null || !this.f1921M.j.f2255A.f2458a || this.f1921M.i == null) {
                C0745a.am = 13;
                return;
            }
            this.f1950s = true;
            if (this.f1915G) {
                return;
            }
        } else if (VERSION.SDK_INT < 14) {
            return;
        }
        if (this.f1910B) {
            float f;
            this.as = this.f1921M.j.f2282z.f2290b;
            this.at = this.f1921M.j.f2282z.f2291c;
            C0745a.m2163h();
            if (this.av == -1) {
                this.av = (int) (((double) this.at) * (((double) this.au) / ((double) this.as)));
                this.f1943l = this.av;
            }
            if (!z && this.ab) {
                this.av -= this.av / 6;
            }
            float f2 = ((float) this.as) / ((float) this.at);
            if (((float) this.au) / ((float) this.as) > ((float) this.av) / ((float) this.at)) {
                this.aG = true;
                this.au = (int) (((float) this.av) * f2);
            } else {
                this.aF = true;
                this.av = (int) (((float) this.au) / f2);
            }
            this.aJ = new LayoutParams(this.au, this.av, 48);
            this.aI = new LayoutParams(this.f1942k, this.f1943l, 48);
            if (this.ab && !z && this.aF) {
                this.aJ.setMargins(0, ((this.f1943l - this.av) / 2) - (this.av / 10), 0, 0);
                this.aI.setMargins(0, ((this.f1943l - this.av) / 2) - (this.av / 10), 0, (((this.f1943l - this.av) / 2) - (this.av / 10)) * -1);
            } else if (!z && this.aF) {
                this.aJ.setMargins(0, (this.f1943l - this.av) / 2, 0, 0);
                this.aI.setMargins(0, (this.f1943l - this.av) / 2, 0, ((this.f1943l - this.av) / 2) * -1);
            } else if (this.ab && !z && this.aG) {
                this.aJ.setMargins((this.f1942k - this.au) / 2, 0, 0, 0);
                this.aI.setMargins((this.f1942k - this.au) / 2, 0, ((this.f1942k - this.au) / 2) * -1, 0);
            } else if (!z && this.aG) {
                this.aJ.setMargins((this.f1942k - this.au) / 2, 0, 0, 0);
                this.aI.setMargins((this.f1942k - this.au) / 2, 0, ((this.f1942k - this.au) / 2) * -1, 0);
            }
            this.f1924P = new ADCImage(this.al, true, false);
            if (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) this.f1924P.f1805f) / ((float) this.au)) > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) this.f1924P.f1806g) / ((float) this.av))) {
                f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) this.f1924P.f1806g) / ((float) this.av));
            } else {
                f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) this.f1924P.f1805f) / ((float) this.au));
            }
            this.f1924P.m2098a((double) f, true);
            this.f1910B = false;
        }
        if (this.ab) {
            this.ac = new Button(C0745a.m2148b());
            this.ac.setText(this.ad);
            this.ac.setGravity(17);
            this.ac.setTextSize((float) ((int) (18.0d * (((double) this.au) / ((double) this.az)))));
            this.ac.setPadding(0, 0, 0, 0);
            this.ac.setBackgroundColor(this.ax);
            this.ac.setTextColor(this.ay);
            this.ac.setOnTouchListener(new C07361(this));
        }
        this.f1926R = new ADCImage(this.am, true, false);
        this.f1925Q = new ADCImage(this.an, true, false);
        this.f1931W = new ADCImage(this.ao, true, false);
        this.f1931W.m2098a((double) (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / ((float) (((double) (((float) this.f1931W.f1805f) / ((float) this.au))) / ((((double) this.au) / 5.5d) / ((double) ((float) this.au)))))), true);
        this.f1925Q.m2098a((double) (this.aE / 2.0f), true);
        this.f1926R.m2098a((double) (this.aE / 2.0f), true);
        this.f1928T = new C0740b(this, C0745a.m2148b());
        this.aa = new ImageView(C0745a.m2148b());
        this.f1927S = new ImageView(C0745a.m2148b());
        this.aa.setImageBitmap(this.f1931W.f1800a);
        if (this.f1949r) {
            this.f1927S.setImageBitmap(this.f1925Q.f1800a);
        } else {
            this.f1927S.setImageBitmap(this.f1926R.f1800a);
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(this.f1925Q.f1805f, this.f1925Q.f1806g, 48);
        layoutParams.setMargins(this.f1942k - this.f1925Q.f1805f, 0, 0, 0);
        this.f1927S.setOnClickListener(new C07372(this));
        this.f1930V = this.f1925Q.f1800a;
        if (this.f1952u) {
            this.f1927S.setVisibility(8);
        }
        if (this.f1953v) {
            this.f1927S.setVisibility(4);
        }
        if (VERSION.SDK_INT >= 14) {
            this.f1929U = new C0739a(this, C0745a.m2148b(), this.f1952u);
        }
        if (VERSION.SDK_INT >= 14) {
            addView(this.f1929U, this.aJ);
        }
        if (VERSION.SDK_INT < 14) {
            this.f1952u = true;
        }
        addView(this.f1928T, this.aI);
        if (this.f1956y && VERSION.SDK_INT >= 14 && this.f1912D) {
            addView(this.f1927S, layoutParams);
        }
        if (this.ab) {
            if (z) {
                layoutParams = new LayoutParams(this.f1942k, this.f1943l / 5, 80);
            } else {
                layoutParams = new LayoutParams(this.f1942k, this.av / 5, 80);
            }
            addView(this.ac, layoutParams);
        }
    }

    public boolean isReady() {
        if ((this.f1921M.m4683a(true) || !this.aH) && this.f1950s && !this.f1914F) {
            return true;
        }
        return false;
    }

    boolean m2130b(boolean z) {
        if ((this.f1921M.m4683a(true) || !this.aH) && AdColony.isZoneNative(this.f1936e)) {
            return true;
        }
        return false;
    }

    public int getNativeAdWidth() {
        return this.f1942k;
    }

    public int getNativeAdHeight() {
        return (this.f1946o || !this.ab) ? this.f1943l : this.f1943l + (this.f1943l / 5);
    }

    public void setOverlayButtonColor(int color) {
        if (this.ab) {
            this.ac.setBackgroundColor(color);
        }
        this.ax = color;
    }

    public void setOverlayButtonTextColor(int color) {
        if (this.ab) {
            this.ac.setTextColor(color);
        }
        this.ay = color;
    }

    public void setOverlayButtonTypeface(Typeface tf, int style) {
        if (this.ab) {
            this.ac.setTypeface(tf, style);
        }
    }

    void m2128a(boolean z, boolean z2) {
        if (z) {
            this.f1927S.setImageBitmap(this.f1926R.f1800a);
            this.f1949r = false;
            m2124a(0.0f, z2);
            this.f1930V = this.f1926R.f1800a;
        } else if (!this.f1955x && this.f1930V == this.f1926R.f1800a) {
            this.f1927S.setImageBitmap(this.f1925Q.f1800a);
            this.f1949r = true;
            if (this.ag != null) {
                if (((double) this.aD) != 0.0d) {
                    m2124a(this.aD, z2);
                } else {
                    m2124a(0.25f, z2);
                }
            }
            this.f1930V = this.f1925Q.f1800a;
        }
    }

    public void setMuted(boolean mute) {
        m2128a(mute, false);
    }

    public void destroy() {
        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Destroy called.");
        if (this.ah != null) {
            this.ah.release();
        }
        if (this.ag != null) {
            this.ag.release();
        }
        this.ag = null;
        this.f1921M.j.f2255A.f2466i = false;
        C0745a.aq.remove(this);
    }

    public ImageView getAdvertiserImage() {
        if (this.f1931W == null) {
            this.f1931W = new ADCImage(this.ao, true, false);
            this.f1931W.m2098a((double) (this.aE / 2.0f), true);
        }
        if (this.aa == null) {
            this.aa = new ImageView(C0745a.m2148b());
            this.aa.setImageBitmap(this.f1931W.f1800a);
        }
        return this.aa;
    }

    public String getTitle() {
        return this.ak;
    }

    public String getAdvertiserName() {
        return this.ai;
    }

    public String getDescription() {
        return this.aj;
    }

    public boolean canceled() {
        return this.f1917I;
    }

    public boolean iapEnabled() {
        return this.f1918J;
    }

    public String iapProductID() {
        return this.ap;
    }

    public AdColonyIAPEngagement iapEngagementType() {
        if (this.f1921M == null || this.f1921M.z != AdColonyIAPEngagement.END_CARD) {
            return this.ar;
        }
        return AdColonyIAPEngagement.END_CARD;
    }

    public AdColonyNativeAdView withListener(AdColonyNativeAdListener listener) {
        this.f1922N = listener;
        this.f1921M.f3981C = listener;
        return this;
    }

    public AdColonyNativeAdView withMutedListener(AdColonyNativeAdMutedListener mute_listener) {
        this.f1923O = mute_listener;
        return this;
    }

    public void pause() {
        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Pause called.");
        if (this.ag != null && !this.f1952u && this.ag.isPlaying() && VERSION.SDK_INT >= 14) {
            C0745a.f2001l.f2144d.m4737b("video_paused", this.f1921M);
            this.f1953v = true;
            this.ag.pause();
            this.f1928T.setVisibility(0);
            this.f1927S.setVisibility(4);
        }
    }

    public void resume() {
        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad Resume called.");
        if (this.ag != null && this.f1953v && !this.f1952u && VERSION.SDK_INT >= 14) {
            C0745a.f2001l.f2144d.m4737b("video_resumed", this.f1921M);
            this.f1953v = false;
            this.ag.seekTo(this.f1921M.r);
            this.ag.start();
            this.f1928T.setVisibility(4);
            this.f1927S.setVisibility(0);
        }
    }

    void m2132c(boolean z) {
        if (this.ag != null && this.f1927S != null) {
            if (z) {
                this.ag.setVolume(0.0f, 0.0f);
                this.f1927S.setImageBitmap(this.f1926R.f1800a);
                this.f1930V = this.f1926R.f1800a;
                return;
            }
            this.ag.setVolume(this.aD, this.aD);
            this.f1927S.setImageBitmap(this.f1925Q.f1800a);
            this.f1930V = this.f1925Q.f1800a;
        }
    }

    void m2124a(float f, boolean z) {
        if (VERSION.SDK_INT >= 14) {
            this.aD = f;
            if (this.ag != null && ((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                if (!this.f1955x) {
                    this.ag.setVolume(f, f);
                }
                if (!this.f1957z) {
                    return;
                }
                C1240g c1240g;
                if (this.f1930V == this.f1926R.f1800a && ((double) f) > 0.0d && !this.f1955x) {
                    c1240g = new C1240g();
                    c1240g.m4656b("user_action", z);
                    this.f1927S.setImageBitmap(this.f1925Q.f1800a);
                    this.f1930V = this.f1925Q.f1800a;
                    C0745a.f2001l.f2144d.m4731a("sound_unmute", c1240g, this.f1921M);
                    this.f1949r = true;
                } else if (this.f1930V == this.f1925Q.f1800a && ((double) f) == 0.0d) {
                    c1240g = new C1240g();
                    c1240g.m4656b("user_action", z);
                    this.f1927S.setImageBitmap(this.f1926R.f1800a);
                    this.f1930V = this.f1926R.f1800a;
                    C0745a.f2001l.f2144d.m4731a("sound_mute", c1240g, this.f1921M);
                    this.f1949r = false;
                }
            } else if (((double) f) >= 0.0d && ((double) f) <= 1.0d) {
                this.aC = f;
            }
        }
    }

    public void setVolume(float v) {
        m2124a(v, false);
    }

    synchronized void m2129b() {
        if ((this.f1952u || this.ag == null || !this.ag.isPlaying()) && this.ag != null) {
            setVolume(this.aD);
            this.ag.start();
            C0745a.f2001l.m2246a(this.f1921M);
            this.f1921M.s = true;
            if (this.f1922N != null) {
                this.f1922N.onAdColonyNativeAdStarted(false, this);
            }
        }
    }

    void m2131c() {
        if (!this.f1952u && this.ag != null && this.ag.isPlaying() && !this.f1953v) {
            C0745a.f2001l.f2144d.m4737b("video_paused", this.f1921M);
            this.ag.pause();
        }
    }

    public void onPrepared(MediaPlayer player) {
        C0777l.f2241c.m2353b((Object) "[ADC] Native Ad onPrepared called.");
        this.f1957z = true;
        if (this.f1930V == null || this.f1925Q.f1800a == null) {
            this.f1928T.setVisibility(0);
            this.f1927S.setVisibility(8);
            this.f1952u = true;
            this.ag = null;
            this.f1921M.r = 0;
        } else if (this.f1949r || !this.f1930V.equals(this.f1925Q.f1800a)) {
            setVolume(this.aD);
        } else {
            m2132c(true);
        }
    }

    public void onCompletion(MediaPlayer player) {
        try {
            this.aK.close();
        } catch (Exception e) {
        }
        this.f1928T.setVisibility(0);
        this.f1927S.setVisibility(8);
        this.f1921M.k = "native";
        this.f1921M.l = "native";
        this.f1921M.s = true;
        this.f1952u = true;
        if (this.ag != null) {
            this.ag.release();
        }
        this.ag = null;
        this.f1921M.r = 0;
        C1240g c1240g = new C1240g();
        c1240g.m4654b("ad_slot", C0745a.f2001l.f2145e.f2599j);
        c1240g.m4656b("replay", false);
        C0745a.f2001l.f2144d.m4731a("native_complete", c1240g, this.f1921M);
        this.f1921M.j.f2274r = false;
        if (this.f1922N != null) {
            this.f1922N.onAdColonyNativeAdFinished(false, this);
        }
        this.f1911C = true;
    }

    public boolean onError(MediaPlayer player, int what, int extra) {
        this.f1928T.setVisibility(0);
        this.f1927S.setVisibility(8);
        this.f1952u = true;
        this.f1957z = true;
        this.ag = null;
        this.f1921M.r = 0;
        return true;
    }

    public void onDraw(Canvas canvas) {
        if (this.f1938g != null) {
            Rect rect = new Rect();
            if (!this.f1938g.hasFocus()) {
                this.f1938g.requestFocus();
            }
            if (!(this.f1952u || this.ag == null)) {
                this.aw = this.ag.getCurrentPosition();
            }
            if (this.aw != 0) {
                this.f1921M.r = this.aw;
            }
            getLocalVisibleRect(rect);
            boolean z = rect.bottom - rect.top > getNativeAdHeight() / 2 && rect.right - rect.left > getNativeAdWidth() / 2;
            if ((z || this.f1945n) && (!this.f1945n || (z && (rect.bottom - rect.top >= getNativeAdHeight() || rect.top == 0)))) {
                if (this.f1952u || this.ag == null || !this.ag.isPlaying()) {
                    if (!this.f1928T.f1907a) {
                        canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
                    }
                } else if (this.f1957z) {
                    this.f1921M.k = "native";
                    this.f1921M.l = "native";
                    C0745a.f2001l.m2244a(((double) this.ag.getCurrentPosition()) / ((double) this.ag.getDuration()), this.f1921M);
                    if (!this.f1916H) {
                        this.f1916H = true;
                        C0745a.f2001l.m2252a("native_start", "{\"ad_slot\":" + (C0745a.f2001l.f2145e.f2599j + 1) + ", \"replay\":false}", this.f1921M);
                        this.f1921M.j.f2274r = true;
                        this.f1921M.j.f2273q = true;
                        C0745a.m2163h();
                        this.f1921M.i.f2313j.m4613a(this.f1921M.j.f2257a);
                    }
                } else {
                    canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
                }
            } else if (!(this.f1952u || this.ag == null || !this.ag.isPlaying() || this.f1953v)) {
                C0777l.f2241c.m2353b((Object) "[ADC] Scroll Pause");
                C0745a.f2001l.f2144d.m4737b("video_paused", this.f1921M);
                this.ag.pause();
                this.f1928T.setVisibility(0);
            }
            if (!this.f1909A && !this.f1952u) {
                invalidate();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (VERSION.SDK_INT >= 14) {
            return false;
        }
        if (event.getAction() == 1 && C0745a.f1968E && C0811q.m2489c()) {
            if (this.f1919K) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f1920L));
                    if (C0745a.f1979P != null) {
                        C0745a.m2148b().startActivity(intent);
                    }
                } catch (Exception e) {
                    if (C0745a.f1979P != null) {
                        Toast.makeText(C0745a.m2148b(), "Unable to open store.", 0).show();
                    }
                }
            } else {
                C0745a.f1983T = this.f1921M;
                C0745a.f2001l.f2141a.m2238a(this.f1936e, this.f1921M.j);
                ADCVideo.m4677a();
                this.f1921M.u = this.f1911C;
                this.f1921M.t = true;
                this.f1921M.k = "native";
                this.f1921M.l = "fullscreen";
                C0745a.f1968E = false;
                C0745a.f2001l.f2144d.m4737b("video_expanded", this.f1921M);
                if (this.f1922N != null) {
                    this.f1922N.onAdColonyNativeAdStarted(true, this);
                }
                if (C0745a.f2002m) {
                    C0777l.f2239a.m2353b((Object) "Launching AdColonyOverlay");
                    C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyOverlay.class));
                } else {
                    C0777l.f2239a.m2353b((Object) "Launching AdColonyFullscreen");
                    C0745a.m2148b().startActivity(new Intent(C0745a.m2148b(), AdColonyFullscreen.class));
                }
                if (this.f1952u) {
                    this.f1921M.f = -1;
                    af afVar = this.f1921M.i.f2321r;
                    afVar.f2090d++;
                    this.f1921M.j.f2274r = true;
                }
                this.f1952u = true;
                this.f1911C = true;
            }
        }
        return true;
    }

    public void notifyAddedToListView() {
        if (this.f1944m) {
            ((C0739a) this.f1929U).onSurfaceTextureAvailable(this.f1939h, this.f1940i, this.f1941j);
        } else {
            this.f1944m = true;
        }
    }

    public void prepareForListView() {
        this.f1945n = true;
    }
}
