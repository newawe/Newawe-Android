package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.bh.C0430a;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class bf extends SurfaceView implements C0430a {
    OnVideoSizeChangedListener f3551a;
    OnPreparedListener f3552b;
    Callback f3553c;
    private String f3554d;
    private Uri f3555e;
    private Map<String, String> f3556f;
    private int f3557g;
    private int f3558h;
    private int f3559i;
    private SurfaceHolder f3560j;
    private MediaPlayer f3561k;
    private int f3562l;
    private int f3563m;
    private int f3564n;
    private int f3565o;
    private OnCompletionListener f3566p;
    private OnPreparedListener f3567q;
    private int f3568r;
    private OnErrorListener f3569s;
    private int f3570t;
    private OnCompletionListener f3571u;
    private OnErrorListener f3572v;
    private OnBufferingUpdateListener f3573w;

    /* renamed from: com.chartboost.sdk.impl.bf.1 */
    class C04191 implements OnVideoSizeChangedListener {
        final /* synthetic */ bf f590a;

        C04191(bf bfVar) {
            this.f590a = bfVar;
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            this.f590a.f3562l = mp.getVideoWidth();
            this.f590a.f3563m = mp.getVideoHeight();
            if (this.f590a.f3562l != 0 && this.f590a.f3563m != 0) {
                this.f590a.getHolder().setFixedSize(this.f590a.f3562l, this.f590a.f3563m);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf.2 */
    class C04202 implements OnPreparedListener {
        final /* synthetic */ bf f591a;

        C04202(bf bfVar) {
            this.f591a = bfVar;
        }

        public void onPrepared(MediaPlayer mp) {
            this.f591a.f3558h = 2;
            this.f591a.f3562l = mp.getVideoWidth();
            this.f591a.f3563m = mp.getVideoHeight();
            if (this.f591a.f3567q != null) {
                this.f591a.f3567q.onPrepared(this.f591a.f3561k);
            }
            int e = this.f591a.f3570t;
            if (e != 0) {
                this.f591a.m3997a(e);
            }
            if (this.f591a.f3562l != 0 && this.f591a.f3563m != 0) {
                this.f591a.getHolder().setFixedSize(this.f591a.f3562l, this.f591a.f3563m);
                if (this.f591a.f3564n == this.f591a.f3562l && this.f591a.f3565o == this.f591a.f3563m && this.f591a.f3559i == 3) {
                    this.f591a.m3996a();
                }
            } else if (this.f591a.f3559i == 3) {
                this.f591a.m3996a();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf.3 */
    class C04213 implements OnCompletionListener {
        final /* synthetic */ bf f592a;

        C04213(bf bfVar) {
            this.f592a = bfVar;
        }

        public void onCompletion(MediaPlayer mp) {
            this.f592a.f3559i = 5;
            if (this.f592a.f3558h != 5) {
                this.f592a.f3558h = 5;
                if (this.f592a.f3566p != null) {
                    this.f592a.f3566p.onCompletion(this.f592a.f3561k);
                }
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf.4 */
    class C04224 implements OnErrorListener {
        final /* synthetic */ bf f593a;

        C04224(bf bfVar) {
            this.f593a = bfVar;
        }

        public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
            CBLogging.m75a(this.f593a.f3554d, "Error: " + framework_err + "," + impl_err);
            this.f593a.f3558h = -1;
            this.f593a.f3559i = -1;
            return (this.f593a.f3569s == null || this.f593a.f3569s.onError(this.f593a.f3561k, framework_err, impl_err)) ? true : true;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf.5 */
    class C04235 implements OnBufferingUpdateListener {
        final /* synthetic */ bf f594a;

        C04235(bf bfVar) {
            this.f594a = bfVar;
        }

        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            this.f594a.f3568r = percent;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf.6 */
    class C04246 implements Callback {
        final /* synthetic */ bf f595a;

        C04246(bf bfVar) {
            this.f595a = bfVar;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            Object obj = 1;
            this.f595a.f3564n = w;
            this.f595a.f3565o = h;
            Object obj2 = this.f595a.f3559i == 3 ? 1 : null;
            if (!(this.f595a.f3562l == w && this.f595a.f3563m == h)) {
                obj = null;
            }
            if (this.f595a.f3561k != null && obj2 != null && r1 != null) {
                if (this.f595a.f3570t != 0) {
                    this.f595a.m3997a(this.f595a.f3570t);
                }
                this.f595a.m3996a();
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
            this.f595a.f3560j = holder;
            this.f595a.m3988g();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            this.f595a.f3560j = null;
            this.f595a.m3974a(true);
        }
    }

    public bf(Context context) {
        super(context);
        this.f3554d = "VideoSurfaceView";
        this.f3558h = 0;
        this.f3559i = 0;
        this.f3560j = null;
        this.f3561k = null;
        this.f3551a = new C04191(this);
        this.f3552b = new C04202(this);
        this.f3571u = new C04213(this);
        this.f3572v = new C04224(this);
        this.f3573w = new C04235(this);
        this.f3553c = new C04246(this);
        m3985f();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int defaultSize = getDefaultSize(0, widthMeasureSpec);
        int defaultSize2 = getDefaultSize(0, heightMeasureSpec);
        if (this.f3562l <= 0 || this.f3563m <= 0) {
            i = defaultSize2;
            defaultSize2 = defaultSize;
        } else {
            i = Math.min(defaultSize2, Math.round((((float) this.f3563m) / ((float) this.f3562l)) * ((float) defaultSize)));
            defaultSize2 = Math.min(defaultSize, Math.round(((float) defaultSize2) * (((float) this.f3562l) / ((float) this.f3563m))));
        }
        setMeasuredDimension(defaultSize2, i);
    }

    private void m3985f() {
        this.f3562l = 0;
        this.f3563m = 0;
        getHolder().addCallback(this.f3553c);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f3558h = 0;
        this.f3559i = 0;
    }

    public void m4002a(Uri uri) {
        m4003a(uri, null);
    }

    public void m4003a(Uri uri, Map<String, String> map) {
        this.f3555e = uri;
        this.f3556f = map;
        this.f3570t = 0;
        m3988g();
        requestLayout();
        invalidate();
    }

    private void m3988g() {
        if (this.f3555e != null && this.f3560j != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra("command", "pause");
            getContext().sendBroadcast(intent);
            m3974a(false);
            try {
                this.f3561k = new MediaPlayer();
                this.f3561k.setOnPreparedListener(this.f3552b);
                this.f3561k.setOnVideoSizeChangedListener(this.f3551a);
                this.f3557g = -1;
                this.f3561k.setOnCompletionListener(this.f3571u);
                this.f3561k.setOnErrorListener(this.f3572v);
                this.f3561k.setOnBufferingUpdateListener(this.f3573w);
                this.f3568r = 0;
                this.f3561k.setDisplay(this.f3560j);
                this.f3561k.setAudioStreamType(3);
                this.f3561k.setScreenOnWhilePlaying(true);
                FileInputStream fileInputStream = new FileInputStream(new File(this.f3555e.toString()));
                this.f3561k.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
                this.f3561k.prepareAsync();
                this.f3558h = 1;
            } catch (Throwable e) {
                CBLogging.m82d(this.f3554d, "Unable to open content: " + this.f3555e, e);
                this.f3558h = -1;
                this.f3559i = -1;
                this.f3572v.onError(this.f3561k, 1, 0);
            } catch (Throwable e2) {
                CBLogging.m82d(this.f3554d, "Unable to open content: " + this.f3555e, e2);
                this.f3558h = -1;
                this.f3559i = -1;
                this.f3572v.onError(this.f3561k, 1, 0);
            }
        }
    }

    public void m4001a(OnPreparedListener onPreparedListener) {
        this.f3567q = onPreparedListener;
    }

    public void m3999a(OnCompletionListener onCompletionListener) {
        this.f3566p = onCompletionListener;
    }

    public void m4000a(OnErrorListener onErrorListener) {
        this.f3569s = onErrorListener;
    }

    private void m3974a(boolean z) {
        if (this.f3561k != null) {
            this.f3561k.reset();
            this.f3561k.release();
            this.f3561k = null;
            this.f3558h = 0;
            if (z) {
                this.f3559i = 0;
            }
        }
    }

    public void m3996a() {
        if (m3990h()) {
            this.f3561k.start();
            this.f3558h = 3;
        }
        this.f3559i = 3;
    }

    public void m4004b() {
        if (m3990h() && this.f3561k.isPlaying()) {
            this.f3561k.pause();
            this.f3558h = 4;
        }
        this.f3559i = 4;
    }

    public int m4005c() {
        if (!m3990h()) {
            this.f3557g = -1;
            return this.f3557g;
        } else if (this.f3557g > 0) {
            return this.f3557g;
        } else {
            this.f3557g = this.f3561k.getDuration();
            return this.f3557g;
        }
    }

    public int m4006d() {
        if (m3990h()) {
            return this.f3561k.getCurrentPosition();
        }
        return 0;
    }

    public void m3997a(int i) {
        if (m3990h()) {
            this.f3561k.seekTo(i);
            this.f3570t = 0;
            return;
        }
        this.f3570t = i;
    }

    public boolean m4007e() {
        return m3990h() && this.f3561k.isPlaying();
    }

    private boolean m3990h() {
        return (this.f3561k == null || this.f3558h == -1 || this.f3558h == 0 || this.f3558h == 1) ? false : true;
    }

    public void m3998a(int i, int i2) {
    }
}
