package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.telephony.PhoneStateListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.immersion.hapticmediasdk.HapticContentSDKFactory;
import com.jirbo.adcolony.ADCDownload.Listener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public abstract class ADCVideo extends Activity implements OnPreparedListener, Listener {
    static int f3928a;
    static int f3929b;
    static int f3930c;
    static boolean f3931d;
    static boolean f3932e;
    static boolean f3933f;
    static boolean f3934g;
    static boolean f3935h;
    static boolean f3936i;
    double f3937A;
    String f3938B;
    String f3939C;
    boolean f3940D;
    boolean f3941E;
    boolean f3942F;
    C0770e f3943G;
    ad f3944H;
    AdColonyAd f3945I;
    HapticContentSDK f3946J;
    String f3947K;
    boolean f3948L;
    boolean f3949M;
    String f3950N;
    VideoView f3951O;
    FrameLayout f3952P;
    FrameLayout f3953Q;
    FrameLayout f3954R;
    Rect f3955S;
    ADCImage f3956T;
    C0726a f3957U;
    FileInputStream f3958V;
    PhoneStateListener f3959W;
    boolean f3960X;
    boolean f3961Y;
    boolean f3962j;
    boolean f3963k;
    boolean f3964l;
    boolean f3965m;
    boolean f3966n;
    boolean f3967o;
    double f3968p;
    double f3969q;
    long f3970r;
    long f3971s;
    int f3972t;
    int f3973u;
    int f3974v;
    int f3975w;
    int f3976x;
    int f3977y;
    int f3978z;

    /* renamed from: com.jirbo.adcolony.ADCVideo.1 */
    class C07221 implements Runnable {
        final /* synthetic */ ADCVideo f1818a;

        C07221(ADCVideo aDCVideo) {
            this.f1818a = aDCVideo;
        }

        public void run() {
            this.f1818a.f3943G.setBackgroundColor(0);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCVideo.2 */
    class C07232 implements Runnable {
        final /* synthetic */ ADCVideo f1819a;

        C07232(ADCVideo aDCVideo) {
            this.f1819a = aDCVideo;
        }

        public void run() {
            this.f1819a.f3944H.m2195c();
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCVideo.3 */
    class C07243 implements OnCompletionListener {
        final /* synthetic */ ADCVideo f1820a;

        C07243(ADCVideo aDCVideo) {
            this.f1820a = aDCVideo;
        }

        public void onCompletion(MediaPlayer media_player) {
            this.f1820a.setContentView(this.f1820a.f3952P);
            this.f1820a.f3954R.removeAllViews();
            ADCVideo.f3934g = false;
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCVideo.4 */
    class C07254 implements OnPreparedListener {
        final /* synthetic */ ADCVideo f1821a;

        C07254(ADCVideo aDCVideo) {
            this.f1821a = aDCVideo;
        }

        public void onPrepared(MediaPlayer media_player) {
            this.f1821a.f3954R.removeViewAt(1);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCVideo.a */
    class C0726a extends View {
        Rect f1822a;
        final /* synthetic */ ADCVideo f1823b;

        public C0726a(ADCVideo aDCVideo, Activity activity) {
            this.f1823b = aDCVideo;
            super(activity);
            this.f1822a = new Rect();
        }

        public void onDraw(Canvas canvas) {
            canvas.drawARGB(MotionEventCompat.ACTION_MASK, 0, 0, 0);
            getDrawingRect(this.f1822a);
            this.f1823b.f3956T.m2101a(canvas, (this.f1822a.width() - this.f1823b.f3956T.f1805f) / 2, (this.f1822a.height() - this.f1823b.f3956T.f1806g) / 2);
            invalidate();
        }
    }

    public ADCVideo() {
        this.f3962j = true;
        this.f3938B = StringUtils.EMPTY;
        this.f3939C = StringUtils.EMPTY;
        this.f3940D = true;
        this.f3941E = true;
        this.f3949M = true;
        this.f3950N = "Your purchase will begin shortly!";
        this.f3955S = new Rect();
        this.f3960X = false;
        this.f3961Y = false;
    }

    static void m4677a() {
        f3928a = 0;
        f3931d = false;
        f3932e = false;
        f3934g = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        int i = 1;
        C0745a.ak = false;
        super.onCreate(savedInstanceState);
        this.f3945I = C0745a.f1983T;
        if (this.f3945I == null) {
            finish();
            return;
        }
        String j;
        boolean z;
        int i2;
        this.f3948L = C0745a.m2164i("haptics_enabled");
        this.f3947K = C0745a.m2165j("haptics_filepath");
        this.f3950N = C0745a.m2165j("in_progress");
        if (this.f3945I.f1857x == null) {
            j = C0745a.m2165j("video_filepath");
        } else {
            j = this.f3945I.f1857x.f1937f;
        }
        this.f3939C = j;
        this.f3937A = (double) C0745a.m2162h("video_duration");
        if (this.f3948L) {
            try {
                this.f3946J = HapticContentSDKFactory.GetNewSDKInstance(0, this);
                this.f3946J.openHaptics(this.f3947K);
            } catch (Exception e) {
                e.printStackTrace();
                this.f3948L = false;
            }
            if (this.f3946J == null) {
                this.f3948L = false;
            }
        }
        if (C0745a.m2164i("video_enabled")) {
            z = false;
        } else {
            z = true;
        }
        C0745a.aa = z;
        if (C0745a.m2164i("end_card_enabled")) {
            z = false;
        } else {
            z = true;
        }
        C0745a.f1989Z = z;
        C0745a.ab = C0745a.m2164i("load_timeout_enabled");
        C0745a.ac = C0745a.m2162h("load_timeout");
        for (i2 = 0; i2 < C0745a.aq.size(); i2++) {
            if (C0745a.aq.get(i2) != null) {
                AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) C0745a.aq.get(i2);
                if (adColonyNativeAdView.ag != null) {
                    adColonyNativeAdView.f1929U.setVisibility(4);
                }
                if (adColonyNativeAdView.f1927S != null) {
                    adColonyNativeAdView.f1927S.setVisibility(4);
                }
            }
        }
        if (C0745a.m2164i("v4iap_enabled")) {
            this.f3945I.f1859z = AdColonyIAPEngagement.AUTOMATIC;
            this.f3945I.f1855v = true;
            this.f3945I.f1847n = C0745a.m2165j("product_id");
        }
        f3932e = this.f3945I.f1854u;
        requestWindowFeature(1);
        getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        if (C0745a.f2002m) {
            i2 = getResources().getConfiguration().orientation;
            int i3 = (i2 == 0 || i2 == 6 || i2 == 2) ? 6 : 7;
            C0745a.f1969F = i3;
            if (VERSION.SDK_INT < 10 || Build.MODEL.equals("Kindle Fire")) {
                if (Build.MODEL.equals("Kindle Fire")) {
                    getRequestedOrientation();
                    switch (((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation()) {
                        case DurationDV.DURATION_TYPE /*0*/:
                            break;
                        case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                            i = 0;
                            break;
                        case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                            i = 9;
                            break;
                        default:
                            i = 8;
                            break;
                    }
                }
                i = i2;
                C0745a.f1969F = i;
                setRequestedOrientation(i);
            } else {
                setRequestedOrientation(C0745a.f1969F);
            }
        } else if (VERSION.SDK_INT >= 10) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(0);
        }
        setVolumeControlStream(3);
        this.f3943G = new C0770e(this);
        this.f3943G.m2286a((OnPreparedListener) this);
        this.f3952P = new FrameLayout(this);
        this.f3944H = new ad(this);
        this.f3954R = new FrameLayout(this);
        this.f3957U = new C0726a(this, this);
        this.f3956T = new ADCImage(C0745a.m2165j("browser_icon"));
        AdColonyBrowser.f1871A = false;
        C0745a.f1984U = this;
        C0745a.f1985V = this;
    }

    public void onPrepared(MediaPlayer mp) {
        if (this.f3962j) {
            int duration = this.f3943G.getDuration() / DateUtils.MILLIS_IN_SECOND;
            C0777l.f2239a.m2349a("duration, actual_duration = ").m2346a(this.f3937A).m2349a(", ").m2352b(duration);
            boolean z = this.f3937A / ((double) duration) > 0.9d && this.f3937A / ((double) duration) < 1.1d;
            if (z) {
                C0745a.f2001l.m2246a(this.f3945I);
                this.f3962j = false;
                return;
            }
            finish();
        }
    }

    public void onResume() {
        f3935h = true;
        super.onResume();
        AdColony.resume(this);
        if (C0745a.m2147a()) {
            finish();
        }
        m4679b();
        if (this.f3940D) {
            this.f3940D = false;
            if (!f3931d) {
                if (this.f3944H.f2052Q) {
                    this.f3953Q.addView(this.f3944H.f2059a);
                }
                if (this.f3944H.f2052Q) {
                    this.f3953Q.setVisibility(4);
                }
                if (Build.MODEL.equals("Kindle Fire")) {
                    this.f3944H.f2071m = 20;
                }
                if (Build.MODEL.equals("SCH-I800")) {
                    this.f3944H.f2071m = 25;
                }
                this.f3952P.addView(this.f3943G, new LayoutParams(this.f3976x, this.f3977y, 17));
                if (this.f3944H.f2052Q) {
                    this.f3952P.addView(this.f3953Q, new LayoutParams(this.f3972t, this.f3973u - this.f3944H.f2071m, 17));
                }
                this.f3952P.addView(this.f3944H, new LayoutParams(this.f3972t, this.f3973u, 17));
            }
        }
        if (f3934g) {
            this.f3954R.removeView(this.f3957U);
            this.f3954R.addView(this.f3957U);
            setContentView(this.f3954R);
        } else {
            setContentView(this.f3952P);
            if (f3931d) {
                this.f3970r = System.currentTimeMillis();
            }
        }
        this.f3943G.m2284a(this.f3944H);
        this.f3943G.m2285a(this.f3944H);
        try {
            this.f3958V = new FileInputStream(this.f3939C);
            this.f3943G.m2289a(this.f3958V.getFD());
            if (!f3936i) {
                onWindowFocusChanged(true);
            }
            if (C0745a.aa) {
                this.f3944H.m2186a();
                this.f3944H.m2197d();
            }
        } catch (IOException e) {
            C0745a.m2157e("Unable to play video: " + C0745a.m2165j("video_filepath"));
            this.f3944H.m2196c(true);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (C0745a.ak) {
            C1257v.f4037H = false;
            C1257v.f4038I = null;
        } else {
            C1257v.f4037H = false;
            C1257v.f4038I = null;
        }
        if (this.f3945I != null && this.f3945I.f1858y != null && !this.f3945I.f1856w) {
            this.f3945I.f1839f = 1;
            this.f3945I.m2115a();
        }
    }

    boolean m4679b() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f3972t = displayMetrics.widthPixels;
        this.f3973u = displayMetrics.heightPixels;
        this.f3972t = displayMetrics.widthPixels;
        this.f3973u = displayMetrics.heightPixels;
        this.f3978z = ViewCompat.MEASURED_STATE_MASK;
        getWindow().setBackgroundDrawable(new ColorDrawable(this.f3978z));
        int i = this.f3972t;
        int i2 = this.f3973u;
        this.f3976x = i;
        this.f3977y = i2;
        if (!C0745a.f2002m && this.f3976x < this.f3977y) {
            this.f3972t = i2;
            this.f3973u = i;
            this.f3976x = i2;
            this.f3977y = i;
        }
        if (!C0745a.f1974K) {
            return false;
        }
        C0745a.f1974K = false;
        return true;
    }

    public void onWindowFocusChanged(boolean has_focus) {
        if (has_focus) {
            f3936i = false;
            if (f3931d || !f3935h) {
                if (f3934g) {
                    if (this.f3951O != null) {
                        this.f3951O.seekTo(f3929b);
                        this.f3951O.start();
                        return;
                    }
                    if (this.f3954R != null) {
                        this.f3954R.removeAllViews();
                    }
                    setContentView(this.f3952P);
                    return;
                } else if (f3931d) {
                    this.f3944H.invalidate();
                    return;
                } else {
                    return;
                }
            } else if (this.f3943G != null) {
                if (f3930c != 0) {
                    f3928a = f3930c;
                }
                f3930c = 0;
                this.f3943G.seekTo(f3928a);
                if (C0745a.f2002m) {
                    Handler handler = new Handler();
                    Runnable c07221 = new C07221(this);
                    this.f3943G.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    handler.postDelayed(c07221, 900);
                } else {
                    this.f3943G.setBackgroundColor(0);
                }
                if (!(C1257v.f4037H || this.f3961Y)) {
                    this.f3944H.f2054S = false;
                    this.f3943G.start();
                    this.f3942F = true;
                    if (this.f3949M) {
                        if (this.f3948L) {
                            this.f3946J.play();
                        }
                        new ADCDownload(C0745a.f2001l, this.f3945I.f1857x == null ? this.f3945I.f1848o : this.f3945I.f1857x.aq, this).m4676b();
                        this.f3949M = false;
                    } else if (this.f3948L) {
                        this.f3946J.resume();
                    }
                }
                this.f3944H.requestFocus();
                this.f3944H.invalidate();
                return;
            } else {
                return;
            }
        }
        if (f3935h && !this.f3961Y) {
            if (this.f3948L) {
                this.f3946J.pause();
            }
            f3928a = this.f3943G.getCurrentPosition();
            this.f3943G.pause();
            this.f3942F = false;
        }
        f3936i = true;
    }

    public void on_download_finished(ADCDownload download) {
        try {
            if (this.f3944H.f2052Q) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<script type=\"text/javascript\">");
                stringBuilder.append(aa.m2174a(C0745a.ae, StringUtils.EMPTY));
                stringBuilder.append("</script>");
                if (download.f3927n == null) {
                    this.f3944H.af = StringUtils.EMPTY;
                    return;
                }
                String replaceAll = download.f3927n.replaceAll("<script (type=\"text/javascript\")?((\\s)*src=\"mraid.js\"){1}></script>", stringBuilder.toString());
                if (this.f3944H != null) {
                    this.f3944H.af = replaceAll;
                    runOnUiThread(new C07232(this));
                }
            }
        } catch (OutOfMemoryError e) {
            C0777l.f2242d.m2353b((Object) "OutOfMemoryError - disabling AdColony.");
            this.f3944H.m2196c(true);
            AdColony.disable();
        }
    }

    public void onPause() {
        f3935h = false;
        if (!f3934g) {
            f3929b = 0;
        } else if (this.f3951O != null) {
            f3929b = this.f3951O.getCurrentPosition();
            this.f3951O.stopPlayback();
        }
        if (f3931d) {
            View view = new View(this);
            view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            setContentView(view);
            this.f3971s = System.currentTimeMillis();
            if (!isFinishing()) {
                this.f3969q += ((double) (this.f3971s - this.f3970r)) / 1000.0d;
            }
        }
        if (this.f3943G == null || this.f3961Y) {
            f3928a = 0;
        } else {
            if (this.f3943G.getCurrentPosition() != 0) {
                f3928a = this.f3943G.getCurrentPosition();
            }
            this.f3943G.m2283a();
            this.f3942F = false;
            this.f3943G.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            if (this.f3948L) {
                this.f3946J.pause();
            }
        }
        this.f3944H.f2036A = true;
        this.f3944H.f2044I = false;
        this.f3944H.f2043H = false;
        this.f3944H.f2045J = false;
        this.f3944H.f2079u = 0;
        this.f3944H.f2078t = 0;
        this.f3944H.invalidate();
        super.onPause();
        AdColony.pause();
    }

    public boolean onKeyUp(int keycode, KeyEvent event) {
        if (C1257v.f4038I != null && C1257v.f4038I.onKeyDown(keycode, event)) {
            return true;
        }
        if (keycode == 4) {
            if (f3931d) {
                if (f3934g) {
                    this.f3951O.stopPlayback();
                    f3934g = false;
                    this.f3954R.removeAllViews();
                    setContentView(this.f3952P);
                } else if (this.f3944H != null && this.f3944H.f2078t == 0) {
                    C0745a.ak = true;
                    this.f3944H.m2200g();
                }
            } else if (this.f3944H != null && C1257v.f4038I != null) {
                Iterator it = C1257v.f4038I.o.iterator();
                while (it.hasNext()) {
                    ((ADCImage) it.next()).m2096a();
                }
                C1257v.f4038I = null;
                C1257v.f4037H = false;
                this.f3943G.start();
                this.f3942F = true;
            } else if (this.f3944H != null && this.f3944H.f2048M && this.f3944H.f2050O) {
                C0745a.ak = true;
                this.f3944H.m2201h();
            }
            return true;
        } else if (keycode == 82) {
            return true;
        } else {
            return super.onKeyUp(keycode, event);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == 4) {
            return true;
        }
        return super.onKeyDown(keycode, event);
    }

    void m4678a(String str) {
        this.f3938B = str;
        f3934g = true;
        this.f3951O = new VideoView(this);
        this.f3951O.setVideoURI(Uri.parse(str));
        new MediaController(this).setMediaPlayer(this.f3951O);
        this.f3951O.setLayoutParams(new LayoutParams(this.f3972t, this.f3973u, 17));
        this.f3954R.addView(this.f3951O);
        this.f3954R.addView(this.f3957U);
        setContentView(this.f3954R);
        this.f3951O.setOnCompletionListener(new C07243(this));
        this.f3951O.setOnPreparedListener(new C07254(this));
        this.f3951O.start();
    }
}
