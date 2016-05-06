package com.google.android.youtube.player.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.internal.C0652c.C1201a;
import com.google.android.youtube.player.internal.C0658i.C1213a;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import java.util.ArrayList;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.google.android.youtube.player.internal.r */
public abstract class C1221r<T extends IInterface> implements C0673t {
    final Handler f3686a;
    private final Context f3687b;
    private T f3688c;
    private ArrayList<C0671a> f3689d;
    private final ArrayList<C0671a> f3690e;
    private boolean f3691f;
    private ArrayList<C0672b> f3692g;
    private boolean f3693h;
    private final ArrayList<C0669b<?>> f3694i;
    private ServiceConnection f3695j;
    private boolean f3696k;

    /* renamed from: com.google.android.youtube.player.internal.r.1 */
    static /* synthetic */ class C06671 {
        static final /* synthetic */ int[] f830a;

        static {
            f830a = new int[YouTubeInitializationResult.values().length];
            try {
                f830a[YouTubeInitializationResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.r.a */
    final class C0668a extends Handler {
        final /* synthetic */ C1221r f831a;

        C0668a(C1221r c1221r) {
            this.f831a = c1221r;
        }

        public final void handleMessage(Message message) {
            if (message.what == 3) {
                this.f831a.m4259a((YouTubeInitializationResult) message.obj);
            } else if (message.what == 4) {
                synchronized (this.f831a.f3689d) {
                    if (this.f831a.f3696k && this.f831a.m4266f() && this.f831a.f3689d.contains(message.obj)) {
                        ((C0671a) message.obj).m960a();
                    }
                }
            } else if (message.what == 2 && !this.f831a.m4266f()) {
            } else {
                if (message.what == 2 || message.what == 1) {
                    ((C0669b) message.obj).m957a();
                }
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.r.b */
    protected abstract class C0669b<TListener> {
        final /* synthetic */ C1221r f832a;
        private TListener f833b;

        public C0669b(C1221r c1221r, TListener tListener) {
            this.f832a = c1221r;
            this.f833b = tListener;
            synchronized (c1221r.f3694i) {
                c1221r.f3694i.add(this);
            }
        }

        public final void m957a() {
            Object obj;
            synchronized (this) {
                obj = this.f833b;
            }
            m958a(obj);
        }

        protected abstract void m958a(TListener tListener);

        public final void m959b() {
            synchronized (this) {
                this.f833b = null;
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.r.e */
    final class C0670e implements ServiceConnection {
        final /* synthetic */ C1221r f834a;

        C0670e(C1221r c1221r) {
            this.f834a = c1221r;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f834a.m4262b(iBinder);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            this.f834a.f3688c = null;
            this.f834a.m4268h();
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.r.c */
    protected final class C1220c extends C0669b<Boolean> {
        public final YouTubeInitializationResult f3683b;
        public final IBinder f3684c;
        final /* synthetic */ C1221r f3685d;

        public C1220c(C1221r c1221r, String str, IBinder iBinder) {
            this.f3685d = c1221r;
            super(c1221r, Boolean.valueOf(true));
            this.f3683b = C1221r.m4253b(str);
            this.f3684c = iBinder;
        }

        protected final /* synthetic */ void m4248a(Object obj) {
            if (((Boolean) obj) != null) {
                switch (C06671.f830a[this.f3683b.ordinal()]) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        try {
                            if (this.f3685d.m4261b().equals(this.f3684c.getInterfaceDescriptor())) {
                                this.f3685d.f3688c = this.f3685d.m4258a(this.f3684c);
                                if (this.f3685d.f3688c != null) {
                                    this.f3685d.m4267g();
                                    return;
                                }
                            }
                        } catch (RemoteException e) {
                        }
                        this.f3685d.m4252a();
                        this.f3685d.m4259a(YouTubeInitializationResult.INTERNAL_ERROR);
                    default:
                        this.f3685d.m4259a(this.f3683b);
                }
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.r.d */
    protected final class C1326d extends C1201a {
        final /* synthetic */ C1221r f4248a;

        protected C1326d(C1221r c1221r) {
            this.f4248a = c1221r;
        }

        public final void m5129a(String str, IBinder iBinder) {
            this.f4248a.f3686a.sendMessage(this.f4248a.f3686a.obtainMessage(1, new C1220c(this.f4248a, str, iBinder)));
        }
    }

    protected C1221r(Context context, C0671a c0671a, C0672b c0672b) {
        this.f3690e = new ArrayList();
        this.f3691f = false;
        this.f3693h = false;
        this.f3694i = new ArrayList();
        this.f3696k = false;
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Clients must be created on the UI thread.");
        }
        this.f3687b = (Context) ab.m879a((Object) context);
        this.f3689d = new ArrayList();
        this.f3689d.add(ab.m879a((Object) c0671a));
        this.f3692g = new ArrayList();
        this.f3692g.add(ab.m879a((Object) c0672b));
        this.f3686a = new C0668a(this);
    }

    private void m4252a() {
        if (this.f3695j != null) {
            try {
                this.f3687b.unbindService(this.f3695j);
            } catch (Throwable e) {
                Log.w("YouTubeClient", "Unexpected error from unbindService()", e);
            }
        }
        this.f3688c = null;
        this.f3695j = null;
    }

    private static YouTubeInitializationResult m4253b(String str) {
        try {
            return YouTubeInitializationResult.valueOf(str);
        } catch (IllegalArgumentException e) {
            return YouTubeInitializationResult.UNKNOWN_ERROR;
        } catch (NullPointerException e2) {
            return YouTubeInitializationResult.UNKNOWN_ERROR;
        }
    }

    protected abstract T m4258a(IBinder iBinder);

    protected final void m4259a(YouTubeInitializationResult youTubeInitializationResult) {
        this.f3686a.removeMessages(4);
        synchronized (this.f3692g) {
            this.f3693h = true;
            ArrayList arrayList = this.f3692g;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                if (this.f3696k) {
                    if (this.f3692g.contains(arrayList.get(i))) {
                        ((C0672b) arrayList.get(i)).m962a(youTubeInitializationResult);
                    }
                    i++;
                } else {
                    return;
                }
            }
            this.f3693h = false;
        }
    }

    protected abstract void m4260a(C0658i c0658i, C1326d c1326d) throws RemoteException;

    protected abstract String m4261b();

    protected final void m4262b(IBinder iBinder) {
        try {
            m4260a(C1213a.m4234a(iBinder), new C1326d(this));
        } catch (RemoteException e) {
            Log.w("YouTubeClient", "service died");
        }
    }

    protected abstract String m4263c();

    public void m4264d() {
        m4268h();
        this.f3696k = false;
        synchronized (this.f3694i) {
            int size = this.f3694i.size();
            for (int i = 0; i < size; i++) {
                ((C0669b) this.f3694i.get(i)).m959b();
            }
            this.f3694i.clear();
        }
        m4252a();
    }

    public final void m4265e() {
        this.f3696k = true;
        YouTubeInitializationResult isYouTubeApiServiceAvailable = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this.f3687b);
        if (isYouTubeApiServiceAvailable != YouTubeInitializationResult.SUCCESS) {
            this.f3686a.sendMessage(this.f3686a.obtainMessage(3, isYouTubeApiServiceAvailable));
            return;
        }
        Intent intent = new Intent(m4263c()).setPackage(C0679z.m974a(this.f3687b));
        if (this.f3695j != null) {
            Log.e("YouTubeClient", "Calling connect() while still connected, missing disconnect().");
            m4252a();
        }
        this.f3695j = new C0670e(this);
        if (!this.f3687b.bindService(intent, this.f3695j, 129)) {
            this.f3686a.sendMessage(this.f3686a.obtainMessage(3, YouTubeInitializationResult.ERROR_CONNECTING_TO_SERVICE));
        }
    }

    public final boolean m4266f() {
        return this.f3688c != null;
    }

    protected final void m4267g() {
        boolean z = true;
        synchronized (this.f3689d) {
            ab.m882a(!this.f3691f);
            this.f3686a.removeMessages(4);
            this.f3691f = true;
            if (this.f3690e.size() != 0) {
                z = false;
            }
            ab.m882a(z);
            ArrayList arrayList = this.f3689d;
            int size = arrayList.size();
            for (int i = 0; i < size && this.f3696k && m4266f(); i++) {
                if (!this.f3690e.contains(arrayList.get(i))) {
                    ((C0671a) arrayList.get(i)).m960a();
                }
            }
            this.f3690e.clear();
            this.f3691f = false;
        }
    }

    protected final void m4268h() {
        this.f3686a.removeMessages(4);
        synchronized (this.f3689d) {
            this.f3691f = true;
            ArrayList arrayList = this.f3689d;
            int size = arrayList.size();
            for (int i = 0; i < size && this.f3696k; i++) {
                if (this.f3689d.contains(arrayList.get(i))) {
                    ((C0671a) arrayList.get(i)).m961b();
                }
            }
            this.f3691f = false;
        }
    }

    protected final void m4269i() {
        if (!m4266f()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    protected final T m4270j() {
        m4269i();
        return this.f3688c;
    }
}
