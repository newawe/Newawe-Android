package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.mraid.MediaRenderView.MediaRenderView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpHost;

@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    private static final String f1572a;
    private RenderView f1573b;
    private MediaRenderView f1574c;
    private C0716a f1575d;
    private RingerModeChangeReceiver f1576e;
    private C0717b f1577f;
    private HeadphonesPluggedChangeReceiver f1578g;
    private MediaPlayerProperties f1579h;
    private Dimensions f1580i;
    private boolean f1581j;
    private Hashtable<String, MediaRenderView> f1582k;

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.1 */
    class C07111 implements OnTouchListener {
        final /* synthetic */ MraidMediaProcessor f1552a;

        C07111(MraidMediaProcessor mraidMediaProcessor) {
            this.f1552a = mraidMediaProcessor;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.2 */
    class C07122 implements OnKeyListener {
        final /* synthetic */ MraidMediaProcessor f1553a;

        C07122(MraidMediaProcessor mraidMediaProcessor) {
            this.f1553a = mraidMediaProcessor;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (4 != i || keyEvent.getAction() != 0) {
                return false;
            }
            this.f1553a.f1574c.m1800a(true);
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.4 */
    static /* synthetic */ class C07134 {
        static final /* synthetic */ int[] f1554a;

        static {
            f1554a = new int[MediaRenderView.values().length];
            try {
                f1554a[MediaRenderView.PAUSED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1554a[MediaRenderView.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1554a[MediaRenderView.COMPLETED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1554a[MediaRenderView.PLAYING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        final /* synthetic */ MraidMediaProcessor f1555a;
        private String f1556b;

        public HeadphonesPluggedChangeReceiver(MraidMediaProcessor mraidMediaProcessor, String str) {
            this.f1555a = mraidMediaProcessor;
            this.f1556b = str;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (intent != null && "android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("state", 0);
                Logger.m1440a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f1572a, "Headphone plugged state changed: " + intExtra);
                MraidMediaProcessor mraidMediaProcessor = this.f1555a;
                String str = this.f1556b;
                if (1 != intExtra) {
                    z = false;
                }
                mraidMediaProcessor.m1731b(str, z);
            }
        }
    }

    public enum MediaContentType {
        MEDIA_CONTENT_TYPE_AUDIO,
        MEDIA_CONTENT_TYPE_AUDIO_VIDEO
    }

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        final /* synthetic */ MraidMediaProcessor f1557a;
        private String f1558b;

        public RingerModeChangeReceiver(MraidMediaProcessor mraidMediaProcessor, String str) {
            this.f1557a = mraidMediaProcessor;
            this.f1558b = str;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
                Logger.m1440a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f1572a, "Ringer mode action changed: " + intExtra);
                this.f1557a.m1727a(this.f1558b, 2 != intExtra);
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.a */
    public static final class C0716a {
        private static final String f1560a;
        private static final int[] f1561b;
        private static double f1562f;
        private HandlerThread f1563c;
        private C0715b f1564d;
        private AudioRecord f1565e;
        private List<C0714a> f1566g;
        private boolean f1567h;

        /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.a.a */
        public interface C0714a {
            void m1713a(double d);
        }

        /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.a.b */
        static final class C0715b extends Handler {
            private WeakReference<C0716a> f1559a;

            public C0715b(Looper looper, C0716a c0716a) {
                super(looper);
                this.f1559a = new WeakReference(c0716a);
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        C0716a c0716a = (C0716a) this.f1559a.get();
                        if (c0716a != null) {
                            c0716a.m1719e();
                        }
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        sendMessageDelayed(obtain, 1000);
                    default:
                        super.handleMessage(message);
                }
            }
        }

        public C0716a() {
            this.f1566g = new ArrayList();
        }

        static {
            f1560a = C0716a.class.getSimpleName();
            f1561b = new int[]{8000, 11025, 22050, 44100};
            f1562f = Double.MIN_VALUE;
        }

        public static double m1714a() {
            return f1562f;
        }

        public void m1720a(C0714a c0714a) {
            this.f1566g.add(c0714a);
            if (1 == this.f1566g.size()) {
                m1716b();
            }
        }

        public void m1721b(C0714a c0714a) {
            this.f1566g.remove(c0714a);
            if (this.f1566g.size() == 0) {
                m1717c();
            }
        }

        private void m1716b() {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1560a, "Start sampling audio levels ...");
            this.f1563c = new HandlerThread("audioSampler");
            this.f1563c.start();
            this.f1564d = new C0715b(this.f1563c.getLooper(), this);
            this.f1565e = m1718d();
            Message obtain = Message.obtain();
            obtain.what = 1;
            this.f1564d.sendMessage(obtain);
        }

        private void m1717c() {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1560a, "Stop sampling audio levels ...");
            if (this.f1565e != null) {
                if (this.f1567h) {
                    this.f1567h = false;
                }
                this.f1564d.removeMessages(1);
                try {
                    this.f1565e.stop();
                    this.f1565e.release();
                } catch (IllegalStateException e) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1560a, "Invalid recorder state: " + e.getMessage());
                }
                this.f1563c.getLooper().quit();
                this.f1563c.interrupt();
                this.f1563c = null;
            }
        }

        private AudioRecord m1718d() {
            for (int i : f1561b) {
                for (short s : new short[]{(short) 3, (short) 2}) {
                    for (short s2 : new short[]{(short) 16, (short) 12}) {
                        int minBufferSize = AudioRecord.getMinBufferSize(i, s2, s);
                        if (minBufferSize != -2) {
                            AudioRecord audioRecord = new AudioRecord(0, i, s2, s, minBufferSize);
                            if (audioRecord.getState() == 1) {
                                return audioRecord;
                            }
                        }
                    }
                }
            }
            return null;
        }

        private void m1719e() {
            if (this.f1565e != null && 1 == this.f1565e.getState()) {
                short[] sArr = new short[XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE];
                float[] fArr = new float[3];
                this.f1567h = true;
                this.f1565e.startRecording();
                int read = this.f1565e.read(sArr, 0, sArr.length);
                float f = 0.0f;
                for (int i = 0; i < read; i += 2) {
                    short s = (short) (sArr[i] | sArr[i + 1]);
                    if (s != (short) 0) {
                        f += (float) (Math.abs(s) / read);
                    }
                }
                fArr[0] = f;
                float f2 = 0.0f;
                for (int i2 = 0; i2 < 3; i2++) {
                    f2 += fArr[i2];
                }
                f1562f = (double) ((f2 / ((float) read)) / 32.0f);
                for (C0714a c0714a : this.f1566g) {
                    if (c0714a != null) {
                        c0714a.m1713a(f1562f);
                    }
                }
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.b */
    public final class C0717b extends ContentObserver {
        final /* synthetic */ MraidMediaProcessor f1568a;
        private Context f1569b;
        private int f1570c;
        private String f1571d;

        public C0717b(MraidMediaProcessor mraidMediaProcessor, String str, Context context, Handler handler) {
            this.f1568a = mraidMediaProcessor;
            super(handler);
            this.f1571d = str;
            this.f1569b = context;
            this.f1570c = -1;
        }

        public void onChange(boolean z) {
            super.onChange(z);
            if (this.f1569b != null) {
                int streamVolume = ((AudioManager) this.f1569b.getSystemService("audio")).getStreamVolume(3);
                if (streamVolume != this.f1570c) {
                    this.f1570c = streamVolume;
                    this.f1568a.m1726a(this.f1571d, streamVolume);
                }
            }
        }
    }

    /* renamed from: com.inmobi.rendering.mraid.MraidMediaProcessor.3 */
    class C12323 implements MediaRenderView {
        final /* synthetic */ MraidMediaProcessor f3877a;

        C12323(MraidMediaProcessor mraidMediaProcessor) {
            this.f3877a = mraidMediaProcessor;
        }

        public void m4569a(MediaRenderView mediaRenderView) {
            Logger.m1440a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f1572a, ">>> onPlayerCompleted");
            this.f3877a.f1573b.setAdActiveFlag(false);
            View f = mediaRenderView.m1808f();
            if (f != null) {
                ((ViewGroup) f.getParent()).removeView(f);
            }
            mediaRenderView.m1796a(null);
            synchronized (this) {
                if (this.f3877a.f1574c != null && mediaRenderView.f1634f.equalsIgnoreCase(this.f3877a.f1574c.f1634f)) {
                    this.f3877a.f1582k.remove(this.f3877a.f1574c.f1634f);
                    this.f3877a.f1574c = null;
                }
            }
        }

        public void m4570b(MediaRenderView mediaRenderView) {
            Logger.m1440a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f1572a, ">>> onPlayerPrepared");
        }
    }

    static {
        f1572a = MraidMediaProcessor.class.getSimpleName();
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.f1582k = new Hashtable();
        this.f1573b = renderView;
        this.f1575d = new C0716a();
        this.f1579h = new MediaPlayerProperties();
        this.f1580i = new Dimensions();
        this.f1581j = false;
    }

    public boolean m1744a() {
        return this.f1581j;
    }

    public void m1738a(MediaPlayerProperties mediaPlayerProperties) {
        this.f1579h = mediaPlayerProperties;
        this.f1581j = true;
    }

    public void m1737a(Dimensions dimensions) {
        this.f1580i = dimensions;
    }

    public void m1742a(String str, String str2, MediaContentType mediaContentType, Activity activity) {
        if (m1732b(str, str2, mediaContentType, activity)) {
            MediaPlayerProperties mediaPlayerProperties = this.f1579h;
            Dimensions dimensions = this.f1580i;
            this.f1573b.setAdActiveFlag(true);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + this.f1574c.f1631c);
            if (str2.length() != 0) {
                this.f1574c.m1799a(str, str2, mediaPlayerProperties, dimensions);
            } else {
                this.f1574c.m1799a(str, this.f1574c.f1635g, mediaPlayerProperties, dimensions);
            }
            if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType && str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME) && !str2.endsWith("mp4") && !str2.endsWith("avi") && !str2.endsWith("m4v")) {
                this.f1574c.m1805c(3);
            } else if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO == mediaContentType && str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME) && !str2.endsWith("mp3")) {
                this.f1574c.m1805c(3);
            } else {
                this.f1582k.put(this.f1579h.f1608a, this.f1574c);
                if (MediaRenderView.HIDDEN == this.f1574c.f1631c) {
                    this.f1574c.m1802b();
                    return;
                }
                ViewGroup viewGroup;
                LayoutParams layoutParams;
                if (mediaPlayerProperties.m1779a()) {
                    viewGroup = (ViewGroup) activity.findViewById(16908290);
                    layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(13);
                    this.f1574c.setLayoutParams(layoutParams);
                    ViewGroup relativeLayout = new RelativeLayout(activity);
                    relativeLayout.setOnTouchListener(new C07111(this));
                    relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    relativeLayout.addView(this.f1574c);
                    viewGroup.addView(relativeLayout, new LayoutParams(-1, -1));
                    this.f1574c.m1796a(relativeLayout);
                    this.f1574c.requestFocus();
                    this.f1574c.setOnKeyListener(new C07122(this));
                } else {
                    viewGroup = (ViewGroup) activity.findViewById(16908290);
                    ViewGroup relativeLayout2 = new RelativeLayout(activity);
                    LayoutParams layoutParams2 = new FrameLayout.LayoutParams(dimensions.f1587c, dimensions.f1588d);
                    layoutParams2.leftMargin = dimensions.f1585a;
                    layoutParams2.topMargin = dimensions.f1586b;
                    layoutParams2.width = dimensions.f1587c;
                    layoutParams2.height = dimensions.f1588d;
                    layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(10);
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    layoutParams.addRule(11);
                    this.f1574c.setLayoutParams(layoutParams);
                    this.f1574c.m1796a(relativeLayout2);
                    relativeLayout2.addView(this.f1574c);
                    viewGroup.addView(relativeLayout2, layoutParams2);
                    this.f1574c.clearFocus();
                }
                this.f1574c.m1797a(new C12323(this));
                this.f1574c.m1793a();
            }
        }
    }

    public void m1740a(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "pauseMedia");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (d.f1631c == MediaRenderView.PLAYING || !(MediaRenderView.INITIALIZED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c)) {
            d.pause();
        } else if (d.f1636h) {
            this.f1573b.m1645a(str, "Invalid player state", "pauseMedia");
        } else {
            this.f1579h.f1611d = false;
            d.f1633e = this.f1579h;
        }
    }

    public void m1741a(String str, String str2, int i) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "seekMedia");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c || MediaRenderView.INITIALIZED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "seekMedia");
        } else {
            d.m1794a(i * DateUtils.MILLIS_IN_SECOND);
        }
    }

    public void m1747b(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "muteMedia");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c || MediaRenderView.INITIALIZED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "muteMedia");
        } else {
            d.m1806d();
        }
    }

    public void m1751c(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "unMuteMedia");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c || MediaRenderView.INITIALIZED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "unMuteMedia");
        } else {
            d.m1807e();
        }
    }

    public boolean m1753d(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "isMediaMuted");
            return false;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED != d.f1631c && MediaRenderView.INITIALIZED != d.f1631c && MediaRenderView.HIDDEN != d.f1631c) {
            return d.f1630b;
        }
        this.f1573b.m1645a(str, "Invalid player state", "isMediaMuted");
        return false;
    }

    public void m1748b(String str, String str2, int i) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "setMediaVolume");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "setMediaVolume");
        } else {
            d.m1803b(i);
        }
    }

    public int m1754e(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "getMediaVolume");
            return 0;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "getMediaVolume");
            return 0;
        } else if (d.f1630b) {
            return 0;
        } else {
            return d.f1629a;
        }
    }

    public void m1743a(String str, String str2, boolean z) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "closeMedia");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player state: " + d.f1631c);
        if (MediaRenderView.RELEASED == d.f1631c || MediaRenderView.HIDDEN == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "closeMedia");
        } else {
            d.m1800a(z);
        }
    }

    public void m1757f(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "hideMedia");
        } else if (MediaRenderView.RELEASED == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "hideMedia");
        } else if (MediaRenderView.HIDDEN == d.f1631c) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player is already hidden");
        } else {
            d.m1804c();
        }
    }

    public void m1759g(String str, String str2) {
        MediaRenderView d = m1734d(str2);
        if (d == null) {
            this.f1573b.m1645a(str, "Invalid property ID", "showMedia");
        } else if (MediaRenderView.RELEASED == d.f1631c) {
            this.f1573b.m1645a(str, "Invalid player state", "showMedia");
        } else if (!this.f1579h.f1608a.equalsIgnoreCase(str2)) {
            this.f1573b.m1645a(str, "Show failed. There is already a video playing", "showMedia");
        } else if (MediaRenderView.SHOWING == d.f1631c) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Media player is already showing");
        } else {
            this.f1582k.remove(str2);
            this.f1574c = d;
            d.m1802b();
        }
    }

    public void m1745b() {
        if (this.f1574c != null) {
            this.f1582k.put(this.f1574c.f1634f, this.f1574c);
        }
        for (MediaRenderView a : this.f1582k.values()) {
            a.m1800a(true);
        }
        this.f1582k.clear();
        this.f1574c = null;
    }

    public void m1749c() {
        if (this.f1574c != null && MediaRenderView.RELEASED != this.f1574c.f1631c) {
            this.f1582k.put(this.f1574c.f1634f, this.f1574c);
            this.f1574c.m1804c();
        }
    }

    public C0716a m1752d() {
        return this.f1575d;
    }

    public boolean m1755e() {
        return 2 != ((AudioManager) SdkContext.m1258b().getSystemService("audio")).getRingerMode();
    }

    public void m1739a(String str) {
        if (this.f1576e == null) {
            this.f1576e = new RingerModeChangeReceiver(this, str);
            SdkContext.m1258b().registerReceiver(this.f1576e, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        }
    }

    public void m1756f() {
        if (this.f1576e != null) {
            SdkContext.m1258b().unregisterReceiver(this.f1576e);
            this.f1576e = null;
        }
    }

    public void m1746b(String str) {
        if (this.f1577f == null) {
            Context b = SdkContext.m1258b();
            this.f1577f = new C0717b(this, str, b, new Handler());
            b.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.f1577f);
        }
    }

    public void m1758g() {
        if (this.f1577f != null) {
            SdkContext.m1258b().getContentResolver().unregisterContentObserver(this.f1577f);
            this.f1577f = null;
        }
    }

    public boolean m1760h() {
        return ((AudioManager) SdkContext.m1258b().getSystemService("audio")).isWiredHeadsetOn();
    }

    public void m1750c(String str) {
        if (this.f1578g == null) {
            this.f1578g = new HeadphonesPluggedChangeReceiver(this, str);
            SdkContext.m1258b().registerReceiver(this.f1578g, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        }
    }

    public void m1761i() {
        if (this.f1578g != null) {
            SdkContext.m1258b().unregisterReceiver(this.f1578g);
            this.f1578g = null;
        }
    }

    private boolean m1732b(String str, String str2, MediaContentType mediaContentType, Activity activity) {
        if (this.f1574c == null || !this.f1574c.f1634f.equalsIgnoreCase(this.f1579h.f1608a)) {
            return m1728a(str, str2, this.f1579h.f1608a, mediaContentType, activity);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Reusing media player (" + this.f1574c.f1634f + ") from the pool");
        if (!this.f1574c.f1634f.equalsIgnoreCase(this.f1579h.f1608a)) {
            return false;
        }
        if (str2.length() == 0 || this.f1574c.f1635g.equalsIgnoreCase(str2)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Player state: " + this.f1574c.f1631c);
            switch (C07134.f1554a[this.f1574c.f1631c.ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    this.f1574c.start();
                    m1736k();
                    return false;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    if (this.f1574c.f1636h) {
                        this.f1574c.start();
                    } else {
                        this.f1579h.f1611d = true;
                        this.f1574c.f1633e = this.f1579h;
                    }
                    m1736k();
                    return false;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    if (!this.f1579h.f1613f) {
                        return false;
                    }
                    this.f1574c.start();
                    m1736k();
                    return false;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    m1736k();
                    return false;
                default:
                    return false;
            }
        }
        this.f1574c.m1799a(str, str2, this.f1579h, this.f1580i);
        this.f1574c.m1809g();
        return false;
    }

    private void m1736k() {
        if (!this.f1579h.m1779a()) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f1574c.m1808f();
            if (relativeLayout != null) {
                relativeLayout.setOnTouchListener(null);
                relativeLayout.setBackgroundColor(0);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f1580i.f1587c, this.f1580i.f1588d);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
                if (-99999 == this.f1580i.f1585a || -99999 == this.f1580i.f1586b) {
                    layoutParams.leftMargin = layoutParams2.leftMargin;
                    layoutParams.topMargin = layoutParams2.topMargin;
                } else {
                    layoutParams.leftMargin = this.f1580i.f1585a;
                    layoutParams.topMargin = this.f1580i.f1586b;
                }
                relativeLayout.setLayoutParams(layoutParams);
            }
        }
    }

    private boolean m1728a(String str, String str2, String str3, MediaContentType mediaContentType, Activity activity) {
        if ((str2.length() != 0 && !URLUtil.isValidUrl(str2)) || (str2.length() == 0 && !this.f1582k.containsKey(str3))) {
            String str4;
            RenderView renderView = this.f1573b;
            String str5 = "Invalid ID (" + str3 + "); no playback URL for this ID";
            if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType) {
                str4 = "playVideo";
            } else {
                str4 = "playAudio";
            }
            renderView.m1645a(str, str5, str4);
            return false;
        } else if (this.f1582k.size() == 5) {
            this.f1573b.m1645a(str, "Cannot create media player - limit on number of media players reached", MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType ? "playVideo" : "playAudio");
            return false;
        } else {
            MediaRenderView mediaRenderView = (MediaRenderView) this.f1582k.remove(str3);
            if (mediaRenderView == null) {
                if (this.f1574c != null && this.f1579h.m1779a()) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Only a single instance of full-screen media playback is allowed. Releasing the current active player ...");
                    this.f1582k.remove(this.f1574c.f1634f);
                    this.f1574c.m1800a(false);
                }
                Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Creating a new media player instance!");
                this.f1574c = new MediaRenderView(activity, this.f1573b);
            } else {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Reusing media player (" + str3 + ") from the pool");
                this.f1574c = mediaRenderView;
            }
            if (str2.length() == 0 && mediaRenderView != null) {
                this.f1574c.m1799a(str, mediaRenderView.f1635g, mediaRenderView.f1633e, mediaRenderView.f1632d);
                this.f1574c.f1632d = mediaRenderView.f1632d;
            }
            return true;
        }
    }

    private MediaRenderView m1734d(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Checking for media player with ID: " + str);
        if (this.f1574c == null || !(str == null || str.length() == 0)) {
            MediaRenderView mediaRenderView = (MediaRenderView) this.f1582k.get(str);
            if (mediaRenderView != null) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Returning media render view with ID: " + str + " (state: " + mediaRenderView.f1631c + ")");
                return mediaRenderView;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "No media render view found!");
            return mediaRenderView;
        } else if ("anonymous".equalsIgnoreCase(this.f1579h.f1608a)) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Returning media render view with ID: " + this.f1579h.f1608a + " (state: " + this.f1574c.f1631c + ")");
            return this.f1574c;
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1572a, "Cannot find ID to look up the media render view");
            return null;
        }
    }

    private void m1727a(String str, boolean z) {
        if (this.f1573b != null) {
            this.f1573b.m1642a(str, "fireDeviceMuteChangeEvent(" + z + ");");
        }
    }

    private void m1726a(String str, int i) {
        if (this.f1573b != null) {
            this.f1573b.m1642a(str, "fireDeviceVolumeChangeEvent(" + i + ");");
        }
    }

    private void m1731b(String str, boolean z) {
        if (this.f1573b != null) {
            this.f1573b.m1642a(str, "fireHeadphonePluggedEvent(" + z + ");");
        }
    }
}
