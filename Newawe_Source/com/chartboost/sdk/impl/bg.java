package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.bh.C0430a;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class bg extends TextureView implements SurfaceTextureListener, C0430a {
    OnVideoSizeChangedListener f3574a;
    OnPreparedListener f3575b;
    private String f3576c;
    private Uri f3577d;
    private Map<String, String> f3578e;
    private int f3579f;
    private int f3580g;
    private int f3581h;
    private Surface f3582i;
    private MediaPlayer f3583j;
    private int f3584k;
    private int f3585l;
    private OnCompletionListener f3586m;
    private OnPreparedListener f3587n;
    private int f3588o;
    private OnErrorListener f3589p;
    private int f3590q;
    private OnCompletionListener f3591r;
    private OnErrorListener f3592s;
    private OnBufferingUpdateListener f3593t;

    /* renamed from: com.chartboost.sdk.impl.bg.1 */
    class C04251 implements OnVideoSizeChangedListener {
        final /* synthetic */ bg f596a;

        C04251(bg bgVar) {
            this.f596a = bgVar;
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            this.f596a.f3584k = mp.getVideoWidth();
            this.f596a.f3585l = mp.getVideoHeight();
            if (this.f596a.f3584k != 0 && this.f596a.f3585l != 0) {
                this.f596a.m4030a(this.f596a.getWidth(), this.f596a.getHeight());
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bg.2 */
    class C04262 implements OnPreparedListener {
        final /* synthetic */ bg f597a;

        C04262(bg bgVar) {
            this.f597a = bgVar;
        }

        public void onPrepared(MediaPlayer mp) {
            this.f597a.f3580g = 2;
            this.f597a.f3584k = mp.getVideoWidth();
            this.f597a.f3585l = mp.getVideoHeight();
            if (this.f597a.f3587n != null) {
                this.f597a.f3587n.onPrepared(this.f597a.f3583j);
            }
            int e = this.f597a.f3590q;
            if (e != 0) {
                this.f597a.m4029a(e);
            }
            if (this.f597a.f3581h == 3) {
                this.f597a.m4028a();
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bg.3 */
    class C04273 implements OnCompletionListener {
        final /* synthetic */ bg f598a;

        C04273(bg bgVar) {
            this.f598a = bgVar;
        }

        public void onCompletion(MediaPlayer mp) {
            this.f598a.f3581h = 5;
            if (this.f598a.f3580g != 5) {
                this.f598a.f3580g = 5;
                if (this.f598a.f3586m != null) {
                    this.f598a.f3586m.onCompletion(this.f598a.f3583j);
                }
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bg.4 */
    class C04284 implements OnErrorListener {
        final /* synthetic */ bg f599a;

        C04284(bg bgVar) {
            this.f599a = bgVar;
        }

        public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
            CBLogging.m75a(this.f599a.f3576c, "Error: " + framework_err + "," + impl_err);
            if (framework_err == 100) {
                this.f599a.m4022g();
            } else {
                this.f599a.f3580g = -1;
                this.f599a.f3581h = -1;
                if (this.f599a.f3589p != null && this.f599a.f3589p.onError(this.f599a.f3583j, framework_err, impl_err)) {
                }
            }
            return true;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bg.5 */
    class C04295 implements OnBufferingUpdateListener {
        final /* synthetic */ bg f600a;

        C04295(bg bgVar) {
            this.f600a = bgVar;
        }

        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            this.f600a.f3588o = percent;
        }
    }

    public bg(Context context) {
        super(context);
        this.f3576c = "VideoTextureView";
        this.f3580g = 0;
        this.f3581h = 0;
        this.f3582i = null;
        this.f3583j = null;
        this.f3574a = new C04251(this);
        this.f3575b = new C04262(this);
        this.f3591r = new C04273(this);
        this.f3592s = new C04284(this);
        this.f3593t = new C04295(this);
        m4020f();
    }

    private void m4020f() {
        this.f3584k = 0;
        this.f3585l = 0;
        setSurfaceTextureListener(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f3580g = 0;
        this.f3581h = 0;
    }

    public void m4034a(Uri uri) {
        m4035a(uri, null);
    }

    public void m4030a(int i, int i2) {
        if (this.f3584k != 0 && this.f3585l != 0 && i != 0 && i2 != 0) {
            float min = Math.min(((float) i) / ((float) this.f3584k), ((float) i2) / ((float) this.f3585l));
            float f = ((float) this.f3584k) * min;
            min *= (float) this.f3585l;
            Matrix matrix = new Matrix();
            matrix.setScale(f / ((float) i), min / ((float) i2), ((float) i) / 2.0f, ((float) i2) / 2.0f);
            setTransform(matrix);
        }
    }

    public void m4035a(Uri uri, Map<String, String> map) {
        this.f3577d = uri;
        this.f3578e = map;
        this.f3590q = 0;
        m4022g();
        requestLayout();
        invalidate();
    }

    private void m4022g() {
        if (this.f3577d != null && this.f3582i != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra("command", "pause");
            getContext().sendBroadcast(intent);
            m4010a(false);
            try {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(this.f3577d.toString());
                String extractMetadata = mediaMetadataRetriever.extractMetadata(19);
                String extractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
                this.f3585l = (int) Float.parseFloat(extractMetadata);
                this.f3584k = (int) Float.parseFloat(extractMetadata2);
            } catch (Throwable e) {
                CBLogging.m82d("play video", "read size error", e);
            }
            try {
                this.f3583j = new MediaPlayer();
                this.f3583j.setOnPreparedListener(this.f3575b);
                this.f3583j.setOnVideoSizeChangedListener(this.f3574a);
                this.f3579f = -1;
                this.f3583j.setOnCompletionListener(this.f3591r);
                this.f3583j.setOnErrorListener(this.f3592s);
                this.f3583j.setOnBufferingUpdateListener(this.f3593t);
                this.f3588o = 0;
                FileInputStream fileInputStream = new FileInputStream(new File(this.f3577d.toString()));
                this.f3583j.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
                this.f3583j.setSurface(this.f3582i);
                this.f3583j.setAudioStreamType(3);
                this.f3583j.setScreenOnWhilePlaying(true);
                this.f3583j.prepareAsync();
                this.f3580g = 1;
            } catch (Throwable e2) {
                CBLogging.m82d(this.f3576c, "Unable to open content: " + this.f3577d, e2);
                this.f3580g = -1;
                this.f3581h = -1;
                this.f3592s.onError(this.f3583j, 1, 0);
            } catch (Throwable e22) {
                CBLogging.m82d(this.f3576c, "Unable to open content: " + this.f3577d, e22);
                this.f3580g = -1;
                this.f3581h = -1;
                this.f3592s.onError(this.f3583j, 1, 0);
            }
        }
    }

    public void m4033a(OnPreparedListener onPreparedListener) {
        this.f3587n = onPreparedListener;
    }

    public void m4031a(OnCompletionListener onCompletionListener) {
        this.f3586m = onCompletionListener;
    }

    public void m4032a(OnErrorListener onErrorListener) {
        this.f3589p = onErrorListener;
    }

    private void m4010a(boolean z) {
        if (this.f3583j != null) {
            this.f3583j.reset();
            this.f3583j.release();
            this.f3583j = null;
            this.f3580g = 0;
            if (z) {
                this.f3581h = 0;
            }
        }
    }

    public void m4028a() {
        if (m4024h()) {
            this.f3583j.start();
            this.f3580g = 3;
        }
        this.f3581h = 3;
    }

    public void m4036b() {
        if (m4024h() && this.f3583j.isPlaying()) {
            this.f3583j.pause();
            this.f3580g = 4;
        }
        this.f3581h = 4;
    }

    public int m4037c() {
        if (!m4024h()) {
            this.f3579f = -1;
            return this.f3579f;
        } else if (this.f3579f > 0) {
            return this.f3579f;
        } else {
            this.f3579f = this.f3583j.getDuration();
            return this.f3579f;
        }
    }

    public int m4038d() {
        if (m4024h()) {
            return this.f3583j.getCurrentPosition();
        }
        return 0;
    }

    public void m4029a(int i) {
        if (m4024h()) {
            this.f3583j.seekTo(i);
            this.f3590q = 0;
            return;
        }
        this.f3590q = i;
    }

    public boolean m4039e() {
        return m4024h() && this.f3583j.isPlaying();
    }

    private boolean m4024h() {
        return (this.f3583j == null || this.f3580g == -1 || this.f3580g == 0 || this.f3580g == 1) ? false : true;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int w, int h) {
        this.f3582i = new Surface(surface);
        m4022g();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        this.f3582i = null;
        m4010a(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int w, int h) {
        Object obj = this.f3581h == 3 ? 1 : null;
        if (this.f3583j != null && obj != null) {
            if (this.f3590q != 0) {
                m4029a(this.f3590q);
            }
            m4028a();
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
