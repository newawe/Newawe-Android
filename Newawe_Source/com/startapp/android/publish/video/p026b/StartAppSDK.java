package com.startapp.android.publish.video.p026b;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.widget.VideoView;
import com.android.volley.DefaultRetryPolicy;

/* renamed from: com.startapp.android.publish.video.b.b */
public class StartAppSDK extends StartAppSDK implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private MediaPlayer f4315g;
    private VideoView f4316h;

    /* renamed from: com.startapp.android.publish.video.b.b.a */
    public enum StartAppSDK {
        MEDIA_ERROR_IO,
        MEDIA_ERROR_MALFORMED,
        MEDIA_ERROR_UNSUPPORTED,
        MEDIA_ERROR_TIMED_OUT;

        public static StartAppSDK m3608a(int i) {
            switch (i) {
                case -1010:
                    return MEDIA_ERROR_UNSUPPORTED;
                case -1007:
                    return MEDIA_ERROR_MALFORMED;
                case -1004:
                    return MEDIA_ERROR_IO;
                case -110:
                    return MEDIA_ERROR_TIMED_OUT;
                default:
                    return MEDIA_ERROR_IO;
            }
        }
    }

    /* renamed from: com.startapp.android.publish.video.b.b.b */
    public enum StartAppSDK {
        MEDIA_ERROR_UNKNOWN,
        MEDIA_ERROR_SERVER_DIED;

        public static StartAppSDK m3609a(int i) {
            if (i == 100) {
                return MEDIA_ERROR_SERVER_DIED;
            }
            return MEDIA_ERROR_UNKNOWN;
        }
    }

    public StartAppSDK(VideoView videoView) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "Ctor");
        this.f4316h = videoView;
        this.f4316h.setOnPreparedListener(this);
        this.f4316h.setOnCompletionListener(this);
        this.f4316h.setOnErrorListener(this);
    }

    public void m5377a() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "start");
        this.f4316h.start();
    }

    public void m5378a(int i) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "seekTo(" + i + ")");
        this.f4316h.seekTo(i);
    }

    public void m5381b() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "pause");
        this.f4316h.pause();
    }

    public void m5382c() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "stop");
        this.f4316h.stopPlayback();
    }

    public void m5380a(boolean z) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "setMute(" + z + ")");
        if (this.f4315g == null) {
            return;
        }
        if (z) {
            this.f4315g.setVolume(0.0f, 0.0f);
        } else {
            this.f4315g.setVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    public void m5383d() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "replay");
        if (this.a != null && this.f4315g != null) {
            this.f4316h.setVideoPath(this.a);
        }
    }

    public int m5384e() {
        return this.f4316h.getCurrentPosition();
    }

    public int m5385f() {
        return this.f4316h.getDuration();
    }

    public boolean m5386g() {
        return this.f4315g != null;
    }

    public void m5379a(String str) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "setVideoLocation(" + str + ")");
        super.m5003a(str);
        this.f4316h.setVideoPath(this.a);
    }

    public void m5387h() {
        if (this.f4315g != null) {
            this.f4315g = null;
        }
    }

    public void onPrepared(MediaPlayer mp) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "onPrepared");
        this.f4315g = mp;
        if (this.b != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 3, "Dispatching onPrepared");
            this.b.m3612a();
        }
    }

    public void onCompletion(MediaPlayer mp) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 4, "onCompletion");
        if (this.d != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 3, "Dispatching onCompletion");
            this.d.m3610a();
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 6, "onError(" + what + ", " + extra + ")");
        if (this.c == null) {
            return false;
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("NativeVideoPlayer", 3, "Dispatching onError");
        return this.c.m3611a(m5376a(what, extra));
    }

    private StartAppSDK m5376a(int i, int i2) {
        return new StartAppSDK(StartAppSDK.m3609a(i) == StartAppSDK.MEDIA_ERROR_SERVER_DIED ? StartAppSDK.SERVER_DIED : StartAppSDK.UNKNOWN, StartAppSDK.m3608a(i2).toString());
    }
}
