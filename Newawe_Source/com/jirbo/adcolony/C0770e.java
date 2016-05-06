package com.jirbo.adcolony;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileDescriptor;
import org.apache.http.HttpStatus;

/* renamed from: com.jirbo.adcolony.e */
class C0770e extends SurfaceView implements MediaPlayerControl {
    static final int f2161e = -1;
    static final int f2162f = 0;
    static final int f2163g = 1;
    static final int f2164h = 2;
    static final int f2165i = 3;
    static final int f2166j = 4;
    static final int f2167k = 5;
    static final int f2168l = 6;
    static final int f2169m = 7;
    static final int f2170n = 8;
    OnErrorListener f2171A;
    int f2172B;
    boolean f2173C;
    boolean f2174D;
    boolean f2175E;
    boolean f2176F;
    int f2177G;
    OnVideoSizeChangedListener f2178H;
    OnPreparedListener f2179I;
    Callback f2180J;
    private OnCompletionListener f2181K;
    private OnErrorListener f2182L;
    private OnBufferingUpdateListener f2183M;
    String f2184a;
    Uri f2185b;
    FileDescriptor f2186c;
    int f2187d;
    int f2188o;
    int f2189p;
    SurfaceHolder f2190q;
    MediaPlayer f2191r;
    int f2192s;
    int f2193t;
    int f2194u;
    int f2195v;
    MediaController f2196w;
    OnCompletionListener f2197x;
    OnPreparedListener f2198y;
    int f2199z;

    /* renamed from: com.jirbo.adcolony.e.1 */
    class C07631 implements OnVideoSizeChangedListener {
        final /* synthetic */ C0770e f2154a;

        C07631(C0770e c0770e) {
            this.f2154a = c0770e;
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            this.f2154a.f2192s = mp.getVideoWidth();
            this.f2154a.f2193t = mp.getVideoHeight();
            if (this.f2154a.f2192s != 0 && this.f2154a.f2193t != 0) {
                this.f2154a.getHolder().setFixedSize(this.f2154a.f2192s, this.f2154a.f2193t);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.e.2 */
    class C07642 implements OnPreparedListener {
        final /* synthetic */ C0770e f2155a;

        C07642(C0770e c0770e) {
            this.f2155a = c0770e;
        }

        public void onPrepared(MediaPlayer mp) {
            this.f2155a.f2188o = C0770e.f2164h;
            C0770e c0770e = this.f2155a;
            C0770e c0770e2 = this.f2155a;
            this.f2155a.f2175E = true;
            c0770e2.f2174D = true;
            c0770e.f2173C = true;
            if (this.f2155a.f2198y != null) {
                this.f2155a.f2198y.onPrepared(this.f2155a.f2191r);
            }
            if (this.f2155a.f2196w != null) {
                this.f2155a.f2196w.setEnabled(true);
            }
            this.f2155a.f2192s = mp.getVideoWidth();
            this.f2155a.f2193t = mp.getVideoHeight();
            int i = this.f2155a.f2172B;
            if (i != 0) {
                this.f2155a.seekTo(i);
            }
            if (this.f2155a.f2192s != 0 && this.f2155a.f2193t != 0) {
                this.f2155a.getHolder().setFixedSize(this.f2155a.f2192s, this.f2155a.f2193t);
                if (this.f2155a.f2194u != this.f2155a.f2192s || this.f2155a.f2195v != this.f2155a.f2193t) {
                    return;
                }
                if (this.f2155a.f2189p == C0770e.f2165i) {
                    this.f2155a.start();
                    if (this.f2155a.f2196w != null) {
                        this.f2155a.f2196w.show();
                    }
                } else if (!this.f2155a.isPlaying()) {
                    if ((i != 0 || this.f2155a.getCurrentPosition() > 0) && this.f2155a.f2196w != null) {
                        this.f2155a.f2196w.show(C0770e.f2162f);
                    }
                }
            } else if (this.f2155a.f2189p == C0770e.f2165i) {
                this.f2155a.start();
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.e.3 */
    class C07653 implements OnCompletionListener {
        final /* synthetic */ C0770e f2156a;

        C07653(C0770e c0770e) {
            this.f2156a = c0770e;
        }

        public void onCompletion(MediaPlayer mp) {
            this.f2156a.f2188o = C0770e.f2167k;
            this.f2156a.f2189p = C0770e.f2167k;
            if (this.f2156a.f2196w != null) {
                this.f2156a.f2196w.hide();
            }
            if (this.f2156a.f2197x != null) {
                this.f2156a.f2197x.onCompletion(this.f2156a.f2191r);
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.e.4 */
    class C07674 implements OnErrorListener {
        final /* synthetic */ C0770e f2158a;

        /* renamed from: com.jirbo.adcolony.e.4.1 */
        class C07661 implements OnClickListener {
            final /* synthetic */ C07674 f2157a;

            C07661(C07674 c07674) {
                this.f2157a = c07674;
            }

            public void onClick(DialogInterface dialog, int whichButton) {
                if (this.f2157a.f2158a.f2197x != null) {
                    this.f2157a.f2158a.f2197x.onCompletion(this.f2157a.f2158a.f2191r);
                }
            }
        }

        C07674(C0770e c0770e) {
            this.f2158a = c0770e;
        }

        public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
            Log.d(this.f2158a.f2184a, "Error: " + framework_err + "," + impl_err);
            this.f2158a.f2188o = C0770e.f2161e;
            this.f2158a.f2189p = C0770e.f2161e;
            if (this.f2158a.f2196w != null) {
                this.f2158a.f2196w.hide();
            }
            if ((this.f2158a.f2171A == null || !this.f2158a.f2171A.onError(this.f2158a.f2191r, framework_err, impl_err)) && this.f2158a.getWindowToken() != null) {
                CharSequence charSequence;
                this.f2158a.m2291b().getResources();
                if (framework_err == HttpStatus.SC_OK) {
                    charSequence = "Invalid progressive playback";
                } else {
                    charSequence = "Unknown error";
                }
                new Builder(this.f2158a.m2291b()).setTitle("ERROR").setMessage(charSequence).setPositiveButton("OKAY", new C07661(this)).setCancelable(false).show();
            }
            return true;
        }
    }

    /* renamed from: com.jirbo.adcolony.e.5 */
    class C07685 implements OnBufferingUpdateListener {
        final /* synthetic */ C0770e f2159a;

        C07685(C0770e c0770e) {
            this.f2159a = c0770e;
        }

        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            this.f2159a.f2199z = percent;
        }
    }

    /* renamed from: com.jirbo.adcolony.e.6 */
    class C07696 implements Callback {
        final /* synthetic */ C0770e f2160a;

        C07696(C0770e c0770e) {
            this.f2160a = c0770e;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            Object obj = C0770e.f2163g;
            this.f2160a.f2194u = w;
            this.f2160a.f2195v = h;
            Object obj2 = this.f2160a.f2189p == C0770e.f2165i ? C0770e.f2163g : C0770e.f2162f;
            if (!(this.f2160a.f2192s == w && this.f2160a.f2193t == h)) {
                obj = C0770e.f2162f;
            }
            if (this.f2160a.f2191r != null && obj2 != null && r1 != null) {
                if (this.f2160a.f2172B != 0) {
                    this.f2160a.seekTo(this.f2160a.f2172B);
                }
                this.f2160a.start();
                if (this.f2160a.f2196w != null) {
                    this.f2160a.f2196w.show();
                }
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
            this.f2160a.f2190q = holder;
            if (this.f2160a.f2191r != null && this.f2160a.f2188o == C0770e.f2168l && this.f2160a.f2189p == C0770e.f2169m) {
                this.f2160a.f2191r.setDisplay(this.f2160a.f2190q);
                this.f2160a.m2293d();
                return;
            }
            this.f2160a.m2278f();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            this.f2160a.f2190q = null;
            if (this.f2160a.f2196w != null) {
                this.f2160a.f2196w.hide();
            }
            if (this.f2160a.f2188o != C0770e.f2168l) {
                this.f2160a.m2276a(true);
            }
        }
    }

    C0770e(Context context) {
        super(context);
        this.f2184a = "ADCCustomVideoView";
        this.f2188o = f2162f;
        this.f2189p = f2162f;
        this.f2190q = null;
        this.f2191r = null;
        this.f2178H = new C07631(this);
        this.f2179I = new C07642(this);
        this.f2181K = new C07653(this);
        this.f2182L = new C07674(this);
        this.f2183M = new C07685(this);
        this.f2180J = new C07696(this);
        m2277e();
    }

    C0770e(Context context, boolean z) {
        super(context);
        this.f2184a = "ADCCustomVideoView";
        this.f2188o = f2162f;
        this.f2189p = f2162f;
        this.f2190q = null;
        this.f2191r = null;
        this.f2178H = new C07631(this);
        this.f2179I = new C07642(this);
        this.f2181K = new C07653(this);
        this.f2182L = new C07674(this);
        this.f2183M = new C07685(this);
        this.f2180J = new C07696(this);
        this.f2176F = z;
        m2277e();
    }

    public C0770e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f2162f);
        m2277e();
    }

    public C0770e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2184a = "ADCCustomVideoView";
        this.f2188o = f2162f;
        this.f2189p = f2162f;
        this.f2190q = null;
        this.f2191r = null;
        this.f2178H = new C07631(this);
        this.f2179I = new C07642(this);
        this.f2181K = new C07653(this);
        this.f2182L = new C07674(this);
        this.f2183M = new C07685(this);
        this.f2180J = new C07696(this);
        m2277e();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = C0770e.getDefaultSize(this.f2192s, widthMeasureSpec);
        int defaultSize2 = C0770e.getDefaultSize(this.f2193t, heightMeasureSpec);
        if (this.f2192s > 0 && this.f2193t > 0) {
            if (this.f2192s * defaultSize2 > this.f2193t * defaultSize) {
                defaultSize2 = (this.f2193t * defaultSize) / this.f2192s;
            } else if (this.f2192s * defaultSize2 < this.f2193t * defaultSize) {
                defaultSize = (this.f2192s * defaultSize2) / this.f2193t;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public int m2282a(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                return Math.min(i, size);
            case 1073741824:
                return size;
            default:
                return i;
        }
    }

    private void m2277e() {
        this.f2192s = f2162f;
        this.f2193t = f2162f;
        getHolder().addCallback(this.f2180J);
        getHolder().setType(f2165i);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (this.f2176F) {
            requestFocus();
        }
        this.f2188o = f2162f;
        this.f2189p = f2162f;
    }

    public int getAudioSessionId() {
        return f2162f;
    }

    public void m2290a(String str) {
        m2287a(Uri.parse(str));
    }

    public void m2289a(FileDescriptor fileDescriptor) {
        this.f2186c = fileDescriptor;
        this.f2172B = f2162f;
        m2278f();
        requestLayout();
        invalidate();
    }

    public void m2287a(Uri uri) {
        this.f2185b = uri;
        this.f2172B = f2162f;
        m2278f();
        requestLayout();
        invalidate();
    }

    public void m2283a() {
        if (this.f2191r != null) {
            this.f2191r.stop();
            this.f2191r.release();
            this.f2191r = null;
            this.f2188o = f2162f;
            this.f2189p = f2162f;
        }
    }

    Activity m2291b() {
        return AdColony.activity();
    }

    private void m2278f() {
        if ((this.f2185b != null || this.f2186c != null) && this.f2190q != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra("command", "pause");
            m2291b().sendBroadcast(intent);
            m2276a(false);
            try {
                this.f2191r = new MediaPlayer();
                this.f2191r.setOnPreparedListener(this.f2179I);
                this.f2191r.setOnVideoSizeChangedListener(this.f2178H);
                this.f2187d = f2161e;
                this.f2191r.setOnCompletionListener(this.f2181K);
                this.f2191r.setOnErrorListener(this.f2182L);
                this.f2191r.setOnBufferingUpdateListener(this.f2183M);
                this.f2199z = f2162f;
                if (this.f2185b != null) {
                    this.f2191r.setDataSource(m2291b(), this.f2185b);
                } else {
                    this.f2191r.setDataSource(this.f2186c);
                }
                this.f2191r.setDisplay(this.f2190q);
                this.f2191r.setAudioStreamType(f2165i);
                this.f2191r.setScreenOnWhilePlaying(true);
                this.f2191r.prepare();
                this.f2188o = f2163g;
                m2279g();
            } catch (Throwable e) {
                if (this.f2185b != null) {
                    Log.w(this.f2184a, "Unable to open content: " + this.f2185b, e);
                } else {
                    Log.w(this.f2184a, "Unable to open content");
                }
                this.f2188o = f2161e;
                this.f2189p = f2161e;
                this.f2182L.onError(this.f2191r, f2163g, f2162f);
                e.printStackTrace();
            } catch (Throwable e2) {
                if (this.f2185b != null) {
                    Log.w(this.f2184a, "Unable to open content: " + this.f2185b, e2);
                } else {
                    Log.w(this.f2184a, "Unable to open content");
                }
                this.f2188o = f2161e;
                this.f2189p = f2161e;
                this.f2182L.onError(this.f2191r, f2163g, f2162f);
                e2.printStackTrace();
            }
        }
    }

    public void m2288a(MediaController mediaController) {
        if (this.f2196w != null) {
            this.f2196w.hide();
        }
        this.f2196w = mediaController;
        m2279g();
    }

    private void m2279g() {
        if (this.f2191r != null && this.f2196w != null) {
            View view;
            this.f2196w.setMediaPlayer(this);
            if (getParent() instanceof View) {
                view = (View) getParent();
            } else {
                view = this;
            }
            this.f2196w.setAnchorView(view);
            this.f2196w.setEnabled(m2281i());
        }
    }

    public void m2286a(OnPreparedListener onPreparedListener) {
        this.f2198y = onPreparedListener;
    }

    public void m2284a(OnCompletionListener onCompletionListener) {
        this.f2197x = onCompletionListener;
    }

    public void m2285a(OnErrorListener onErrorListener) {
        this.f2171A = onErrorListener;
    }

    private void m2276a(boolean z) {
        if (this.f2191r != null) {
            this.f2191r.reset();
            this.f2191r.release();
            this.f2191r = null;
            this.f2188o = f2162f;
            if (z) {
                this.f2189p = f2162f;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (m2281i() && this.f2196w != null) {
            m2280h();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent ev) {
        if (m2281i() && this.f2196w != null) {
            m2280h();
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean z = (keyCode == f2166j || keyCode == 24 || keyCode == 25 || keyCode == 82 || keyCode == f2167k || keyCode == f2168l) ? false : true;
        if (m2281i() && z && this.f2196w != null) {
            if (keyCode == 79 || keyCode == 85) {
                if (this.f2191r.isPlaying()) {
                    pause();
                    this.f2196w.show();
                    return true;
                }
                start();
                this.f2196w.hide();
                return true;
            } else if (keyCode == 86 && this.f2191r.isPlaying()) {
                pause();
                this.f2196w.show();
            } else {
                m2280h();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void m2280h() {
        if (this.f2196w.isShowing()) {
            this.f2196w.hide();
        } else {
            this.f2196w.show();
        }
    }

    public void start() {
        if (m2281i()) {
            this.f2191r.start();
            this.f2188o = f2165i;
        }
        this.f2189p = f2165i;
    }

    public void pause() {
        if (m2281i() && this.f2191r.isPlaying()) {
            this.f2191r.pause();
            this.f2188o = f2166j;
        }
        this.f2189p = f2166j;
    }

    public void m2292c() {
        if (m2281i()) {
            this.f2191r.stop();
            this.f2177G = this.f2188o;
            this.f2188o = f2168l;
            this.f2189p = f2168l;
        }
    }

    public void m2293d() {
        if (this.f2190q == null && this.f2188o == f2168l) {
            this.f2189p = f2169m;
        } else if (this.f2191r != null && this.f2188o == f2168l) {
            this.f2191r.start();
            this.f2188o = this.f2177G;
            this.f2189p = this.f2177G;
        } else if (this.f2188o == f2170n) {
            m2278f();
        }
    }

    public int getDuration() {
        if (!m2281i()) {
            this.f2187d = f2161e;
            return this.f2187d;
        } else if (this.f2187d > 0) {
            return this.f2187d;
        } else {
            this.f2187d = this.f2191r.getDuration();
            return this.f2187d;
        }
    }

    public int getCurrentPosition() {
        if (m2281i()) {
            return this.f2191r.getCurrentPosition();
        }
        return f2162f;
    }

    public void seekTo(int msec) {
        if (m2281i()) {
            this.f2191r.seekTo(msec);
            this.f2172B = f2162f;
            return;
        }
        this.f2172B = msec;
    }

    public boolean isPlaying() {
        return m2281i() && this.f2191r.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.f2191r != null) {
            return this.f2199z;
        }
        return f2162f;
    }

    private boolean m2281i() {
        return (this.f2191r == null || this.f2188o == f2161e || this.f2188o == 0 || this.f2188o == f2163g) ? false : true;
    }

    public boolean canPause() {
        return this.f2173C;
    }

    public boolean canSeekBackward() {
        return this.f2174D;
    }

    public boolean canSeekForward() {
        return this.f2175E;
    }
}
