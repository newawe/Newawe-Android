package com.inmobi.rendering.mraid;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.android.volley.DefaultRetryPolicy;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.rendering.RenderView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.inmobi.rendering.mraid.g */
final class MediaRenderView extends VideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final String f1628k;
    int f1629a;
    boolean f1630b;
    MediaRenderView f1631c;
    Dimensions f1632d;
    MediaPlayerProperties f1633e;
    String f1634f;
    String f1635g;
    boolean f1636h;
    int f1637i;
    int f1638j;
    private MediaPlayer f1639l;
    private MediaRenderView f1640m;
    private RenderView f1641n;
    private Bitmap f1642o;
    private ViewGroup f1643p;
    private MediaRenderView f1644q;
    private MediaRenderView f1645r;
    private MediaRenderView f1646s;
    private String f1647t;
    private boolean f1648u;
    private boolean f1649v;

    /* renamed from: com.inmobi.rendering.mraid.g.1 */
    class MediaRenderView implements OnVideoSizeChangedListener {
        final /* synthetic */ MediaRenderView f1615a;

        MediaRenderView(MediaRenderView mediaRenderView) {
            this.f1615a = mediaRenderView;
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            Logger.m1440a(InternalLogLevel.INTERNAL, MediaRenderView.f1628k, ">>> onVideoSizeChanged");
            if (this.f1615a.f1640m == null && this.f1615a.f1633e.f1612e) {
                this.f1615a.f1640m = new MediaRenderView(this.f1615a.getContext());
                this.f1615a.f1640m.setAnchorView(this.f1615a);
                this.f1615a.setMediaController(this.f1615a.f1640m);
                this.f1615a.requestLayout();
                this.f1615a.requestFocus();
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.g.a */
    static class MediaRenderView extends MediaController {
        Context f1616a;

        public MediaRenderView(Context context) {
            super(context);
            this.f1616a = context;
        }

        public void show(int i) {
            super.show(i);
            if (VERSION.SDK_INT < 19) {
                try {
                    Field declaredField = MediaController.class.getDeclaredField("mAnchor");
                    declaredField.setAccessible(true);
                    View view = (View) declaredField.get(this);
                    Field declaredField2 = MediaController.class.getDeclaredField("mDecor");
                    declaredField2.setAccessible(true);
                    View view2 = (View) declaredField2.get(this);
                    Field declaredField3 = MediaController.class.getDeclaredField("mDecorLayoutParams");
                    declaredField3.setAccessible(true);
                    LayoutParams layoutParams = (LayoutParams) declaredField3.get(this);
                    Field declaredField4 = MediaController.class.getDeclaredField("mWindowManager");
                    declaredField4.setAccessible(true);
                    WindowManager windowManager = (WindowManager) declaredField4.get(this);
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    view2.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(view.getHeight(), ExploreByTouchHelper.INVALID_ID));
                    view2.setPadding(0, 0, 0, 0);
                    layoutParams.verticalMargin = 0.0f;
                    layoutParams.horizontalMargin = 0.0f;
                    layoutParams.width = view.getWidth();
                    layoutParams.gravity = 8388659;
                    layoutParams.x = iArr[0];
                    layoutParams.y = (view.getHeight() + iArr[1]) - view2.getMeasuredHeight();
                    windowManager.updateViewLayout(view2, layoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.g.b */
    static final class MediaRenderView extends Handler {
        private final WeakReference<MediaRenderView> f1617a;

        public MediaRenderView(MediaRenderView mediaRenderView) {
            this.f1617a = new WeakReference(mediaRenderView);
        }

        public void handleMessage(Message message) {
            MediaRenderView mediaRenderView = (MediaRenderView) this.f1617a.get();
            if (mediaRenderView != null) {
                switch (message.what) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        if (MediaRenderView.PLAYING == mediaRenderView.f1631c) {
                            int round = Math.round(((float) mediaRenderView.getCurrentPosition()) / 1000.0f);
                            int round2 = Math.round(((float) mediaRenderView.getDuration()) / 1000.0f);
                            if (mediaRenderView.f1637i != round) {
                                mediaRenderView.m1795a(round, round2);
                                mediaRenderView.f1637i = round;
                                mediaRenderView.f1638j = round;
                            }
                            sendEmptyMessageDelayed(1, 1000);
                            break;
                        }
                        return;
                }
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.g.c */
    interface MediaRenderView {
        void m1781a(MediaRenderView mediaRenderView);

        void m1782b(MediaRenderView mediaRenderView);
    }

    /* renamed from: com.inmobi.rendering.mraid.g.d */
    enum MediaRenderView {
        INITIALIZED,
        PLAYING,
        PAUSED,
        HIDDEN,
        SHOWING,
        COMPLETED,
        RELEASED
    }

    /* renamed from: com.inmobi.rendering.mraid.g.e */
    final class MediaRenderView extends BroadcastReceiver {
        final /* synthetic */ MediaRenderView f1626a;
        private final String f1627b;

        MediaRenderView(MediaRenderView mediaRenderView) {
            this.f1626a = mediaRenderView;
            this.f1627b = MediaRenderView.class.getSimpleName();
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                Logger.m1440a(InternalLogLevel.INTERNAL, this.f1627b, "Screen OFF");
                if (MediaRenderView.PLAYING == this.f1626a.f1631c) {
                    this.f1626a.f1649v = true;
                    this.f1626a.pause();
                }
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                Logger.m1440a(InternalLogLevel.INTERNAL, this.f1627b, "Screen ON");
                if (this.f1626a.f1649v && MediaRenderView.PAUSED == this.f1626a.f1631c) {
                    this.f1626a.f1649v = false;
                    this.f1626a.m1793a();
                }
            }
        }
    }

    static {
        f1628k = MediaRenderView.class.getSimpleName();
    }

    public MediaRenderView(Context context, RenderView renderView) {
        super(context);
        this.f1641n = renderView;
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDrawingCacheEnabled(true);
        this.f1629a = 100;
        this.f1637i = -1;
        this.f1638j = 0;
        this.f1631c = MediaRenderView.INITIALIZED;
        this.f1648u = false;
        this.f1630b = false;
        this.f1649v = false;
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, ">>> onWindowVisibilityChanged (" + i + ")");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getHolder().setSizeFromLayout();
    }

    @TargetApi(16)
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, ">>> onVisibilityChanged (" + i + ")");
        if (i != 0) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            setBackground(new BitmapDrawable(SdkContext.m1258b().getResources(), this.f1642o));
        } else {
            setBackgroundDrawable(new BitmapDrawable(this.f1642o));
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, ">>> onCompletion");
        this.f1631c = MediaRenderView.COMPLETED;
        this.f1648u = true;
        m1798a("ended");
        this.f1645r.removeMessages(1);
        if (this.f1633e.f1613f) {
            synchronized (this) {
                if (!m1792k()) {
                    this.f1638j = 0;
                    start();
                }
            }
        } else if (this.f1633e.m1780b()) {
            m1800a(false);
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, ">>> onError (" + i + ", " + i2 + ")");
        m1800a(false);
        int i3 = -1;
        if (100 == i) {
            i3 = 2;
        }
        m1805c(i3);
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, ">>> onPrepared");
        mediaPlayer.setOnVideoSizeChangedListener(new MediaRenderView(this));
        this.f1639l = mediaPlayer;
        m1794a(this.f1638j * DateUtils.MILLIS_IN_SECOND);
        this.f1636h = true;
        this.f1644q.m1782b(this);
        m1809g();
    }

    public void m1799a(String str, String str2, MediaPlayerProperties mediaPlayerProperties, Dimensions dimensions) {
        this.f1647t = str;
        this.f1645r = new MediaRenderView(this);
        this.f1635g = m1788b(str2.trim());
        this.f1633e = mediaPlayerProperties;
        this.f1634f = mediaPlayerProperties.f1608a;
        this.f1632d = dimensions;
        if (this.f1642o == null) {
            this.f1642o = Bitmap.createBitmap(24, 24, Config.ARGB_8888);
            this.f1642o = m1789c(this.f1635g);
        }
    }

    public void start() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Media render view state: " + this.f1631c);
        if (MediaRenderView.PLAYING != this.f1631c) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Start media playback");
            m1794a(this.f1638j * DateUtils.MILLIS_IN_SECOND);
            this.f1631c = MediaRenderView.PLAYING;
            super.start();
            if (this.f1636h) {
                m1798a("play");
            }
            this.f1645r.sendEmptyMessage(1);
        }
    }

    public void pause() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Media render view state: " + this.f1631c);
        if (MediaRenderView.PAUSED != this.f1631c) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Pause media playback");
            this.f1645r.removeMessages(1);
            super.pause();
            this.f1631c = MediaRenderView.PAUSED;
            m1798a("pause");
        }
    }

    public void m1793a() {
        setVideoPath(this.f1635g);
        setOnCompletionListener(this);
        setOnPreparedListener(this);
        setOnErrorListener(this);
        if (this.f1640m == null && this.f1633e.f1612e && VERSION.SDK_INT >= 19) {
            this.f1640m = new MediaRenderView(getContext());
            this.f1640m.setAnchorView(this);
            setMediaController(this.f1640m);
        }
        if (this.f1646s == null) {
            this.f1646s = new MediaRenderView(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            SdkContext.m1258b().registerReceiver(this.f1646s, intentFilter);
        }
    }

    public void m1794a(int i) {
        if (i < getDuration()) {
            this.f1638j = i;
            seekTo(i);
        }
    }

    public void m1802b() {
        if (MediaRenderView.SHOWING != this.f1631c) {
            this.f1643p.setVisibility(0);
            setVisibility(0);
            requestFocus();
            this.f1631c = MediaRenderView.SHOWING;
            m1798a("showing");
        }
    }

    public void m1804c() {
        if (MediaRenderView.HIDDEN != this.f1631c) {
            setVisibility(4);
            this.f1643p.setVisibility(4);
            this.f1631c = MediaRenderView.HIDDEN;
            m1798a("hidden");
        }
    }

    public void m1806d() {
        if (this.f1639l != null && !this.f1630b) {
            this.f1630b = true;
            this.f1639l.setVolume(0.0f, 0.0f);
            m1810h();
        }
    }

    public void m1807e() {
        if (this.f1639l != null && this.f1630b) {
            m1803b(this.f1629a);
        }
    }

    public void m1803b(int i) {
        boolean z = false;
        if (this.f1639l == null) {
            return;
        }
        if (!this.f1630b && i == this.f1629a) {
            return;
        }
        if (this.f1630b && i == 0) {
            this.f1629a = 0;
            return;
        }
        if (i == 0) {
            z = true;
        }
        this.f1630b = z;
        this.f1629a = i;
        float log = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - ((float) (Math.log((double) (101 - i)) / Math.log(101.0d)));
        this.f1639l.setVolume(log, log);
        m1810h();
    }

    public void m1800a(boolean z) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Media render view state: " + this.f1631c);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1628k, "Release the media render view");
        if (this.f1646s != null) {
            SdkContext.m1258b().unregisterReceiver(this.f1646s);
            this.f1646s = null;
        }
        this.f1631c = MediaRenderView.RELEASED;
        m1801a(z, this.f1637i != -1 ? this.f1637i : Math.round((float) (getCurrentPosition() / DateUtils.MILLIS_IN_SECOND)));
        stopPlayback();
        this.f1645r.removeMessages(1);
        m1791j();
        super.setMediaController(null);
        this.f1640m = null;
        if (this.f1644q != null) {
            this.f1644q.m1781a(this);
        }
    }

    public ViewGroup m1808f() {
        return this.f1643p;
    }

    public void m1796a(ViewGroup viewGroup) {
        this.f1643p = viewGroup;
    }

    public void m1797a(MediaRenderView mediaRenderView) {
        this.f1644q = mediaRenderView;
    }

    void m1809g() {
        if (MediaRenderView.SHOWING == this.f1631c) {
            this.f1631c = this.f1648u ? MediaRenderView.COMPLETED : MediaRenderView.PAUSED;
            if (!this.f1636h) {
                return;
            }
            if (VERSION.SDK_INT < 21) {
                super.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                super.pause();
                return;
            }
            super.start();
            super.pause();
        } else if (MediaRenderView.INITIALIZED == this.f1631c) {
            if (this.f1633e.f1614g) {
                m1806d();
            }
            if (this.f1633e.f1611d) {
                start();
            } else if (!this.f1636h) {
            } else {
                if (VERSION.SDK_INT < 21) {
                    super.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                    }
                    super.pause();
                    return;
                }
                super.start();
                super.pause();
            }
        }
    }

    private void m1791j() {
        if (this.f1643p != null) {
            ViewGroup viewGroup = (ViewGroup) this.f1643p.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.f1643p);
            }
            viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
            setBackgroundColor(0);
            this.f1643p = null;
        }
    }

    private boolean m1792k() {
        return MediaRenderView.PAUSED == this.f1631c || MediaRenderView.HIDDEN == this.f1631c;
    }

    void m1801a(boolean z, int i) {
        if (this.f1641n != null) {
            this.f1641n.m1642a(this.f1647t, "fireMediaCloseEvent('" + this.f1633e.f1608a + "'," + z + "," + i + ");");
        }
    }

    void m1798a(String str) {
        if (this.f1641n != null) {
            this.f1641n.m1642a(this.f1647t, "fireMediaTrackingEvent('" + str + "','" + this.f1633e.f1608a + "');");
        }
    }

    void m1810h() {
        int i = this.f1630b ? 0 : this.f1629a;
        if (this.f1641n != null) {
            this.f1641n.m1642a(this.f1647t, "fireMediaVolumeChangeEvent('" + this.f1633e.f1608a + "'," + i + "," + this.f1630b + ");");
        }
    }

    void m1805c(int i) {
        if (this.f1641n != null) {
            this.f1641n.m1642a(this.f1647t, "fireMediaErrorEvent('" + this.f1633e.f1608a + "'," + i + ");");
        }
    }

    void m1795a(int i, int i2) {
        if (this.f1641n != null) {
            this.f1641n.m1642a(this.f1647t, "fireMediaTimeUpdateEvent('" + this.f1633e.f1608a + "'," + i + "," + i2 + ");");
        }
    }

    private String m1788b(String str) {
        String str2 = StringUtils.EMPTY;
        byte[] bytes = str.getBytes();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            if ((b & TransportMediator.FLAG_KEY_MEDIA_NEXT) > 0) {
                stringBuilder.append("%").append(m1784a(b));
            } else {
                stringBuilder.append((char) b);
            }
        }
        try {
            return new String(stringBuilder.toString().getBytes(), HTTP.ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        }
    }

    private Bitmap m1789c(String str) {
        try {
            return (Bitmap) Class.forName("android.media.ThumbnailUtils").getDeclaredMethod("createVideoThumbnail", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(1)});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private String m1784a(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }
}
