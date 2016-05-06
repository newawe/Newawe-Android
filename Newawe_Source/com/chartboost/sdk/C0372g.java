package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0324f;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.bi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.chartboost.sdk.g */
public abstract class C0372g {
    public static Handler f378a;
    public boolean f379b;
    protected List<C0371b> f380c;
    protected List<C0371b> f381d;
    protected C0321a f382e;
    protected C0343a f383f;
    protected C0324f f384g;
    public Map<Integer, Runnable> f385h;
    protected boolean f386i;
    protected boolean f387j;
    private boolean f388k;
    private C0370a f389l;

    /* renamed from: com.chartboost.sdk.g.1 */
    class C03681 implements Runnable {
        final /* synthetic */ boolean f367a;
        final /* synthetic */ View f368b;
        final /* synthetic */ C0372g f369c;

        C03681(C0372g c0372g, boolean z, View view) {
            this.f369c = c0372g;
            this.f367a = z;
            this.f368b = view;
        }

        public void run() {
            if (!this.f367a) {
                this.f368b.setVisibility(8);
                this.f368b.setClickable(false);
            }
            this.f369c.f385h.remove(Integer.valueOf(this.f368b.hashCode()));
        }
    }

    /* renamed from: com.chartboost.sdk.g.a */
    public abstract class C0370a extends RelativeLayout {
        final /* synthetic */ C0372g f371a;
        private boolean f372b;
        private int f373c;
        private int f374d;
        private int f375e;
        private int f376f;
        private C0324f f377g;

        /* renamed from: com.chartboost.sdk.g.a.1 */
        class C03691 implements Runnable {
            final /* synthetic */ C0370a f370a;

            C03691(C0370a c0370a) {
                this.f370a = c0370a;
            }

            public void run() {
                this.f370a.requestLayout();
            }
        }

        protected abstract void m455a(int i, int i2);

        public C0370a(C0372g c0372g, Context context) {
            this.f371a = c0372g;
            super(context);
            this.f372b = false;
            this.f373c = -1;
            this.f374d = -1;
            this.f375e = -1;
            this.f376f = -1;
            this.f377g = null;
            c0372g.f389l = this;
            c0372g.f388k = false;
            setFocusableInTouchMode(true);
            requestFocus();
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.f375e = w;
            this.f376f = h;
            if (this.f373c != -1 && this.f374d != -1 && this.f371a.f383f != null && this.f371a.f383f.f225a == C0339b.NATIVE) {
                m454a();
            }
        }

        private boolean m453b(int i, int i2) {
            boolean z = true;
            if (this.f371a.f383f != null && this.f371a.f383f.f225a == C0339b.WEB) {
                return true;
            }
            if (this.f372b) {
                return false;
            }
            C0324f c = CBUtility.m93c();
            if (this.f373c == i && this.f374d == i2 && this.f377g == c) {
                return true;
            }
            this.f372b = true;
            try {
                if (this.f371a.f386i && c.m165a()) {
                    this.f371a.f384g = c;
                } else if (this.f371a.f387j && c.m166b()) {
                    this.f371a.f384g = c;
                }
                m455a(i, i2);
                post(new C03691(this));
                this.f373c = i;
                this.f374d = i2;
                this.f377g = c;
            } catch (Throwable e) {
                CBLogging.m78b("CBViewProtocol", "Exception raised while layouting Subviews", e);
                z = false;
            }
            this.f372b = false;
            return z;
        }

        public final void m454a() {
            m457a(false);
        }

        public final void m457a(boolean z) {
            if (z) {
                this.f377g = null;
            }
            m458a((Activity) getContext());
        }

        public void m459b() {
        }

        public boolean m458a(Activity activity) {
            if (this.f375e == -1 || this.f376f == -1) {
                int width;
                int height;
                try {
                    width = getWidth();
                    height = getHeight();
                    if (width == 0 || height == 0) {
                        View findViewById = activity.getWindow().findViewById(16908290);
                        if (findViewById == null) {
                            findViewById = activity.getWindow().getDecorView();
                        }
                        width = findViewById.getWidth();
                        height = findViewById.getHeight();
                    }
                } catch (Exception e) {
                    height = 0;
                    width = 0;
                }
                if (width == 0 || r0 == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    width = displayMetrics.widthPixels;
                    height = displayMetrics.heightPixels;
                }
                this.f375e = width;
                this.f376f = height;
            }
            return m453b(this.f375e, this.f376f);
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            for (int i = 0; i < this.f371a.f385h.size(); i++) {
                C0372g.f378a.removeCallbacks((Runnable) this.f371a.f385h.get(Integer.valueOf(i)));
            }
            this.f371a.f385h.clear();
        }

        public final void m456a(View view) {
            int i = HttpStatus.SC_OK;
            if (HttpStatus.SC_OK == getId()) {
                i = HttpStatus.SC_CREATED;
            }
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        protected boolean m460c() {
            return C0372g.m464a(getContext());
        }
    }

    /* renamed from: com.chartboost.sdk.g.b */
    public interface C0371b {
        boolean m461a();
    }

    protected abstract C0370a m474b(Context context);

    static {
        f378a = CBUtility.m96e();
    }

    public static boolean m464a(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    public C0372g(C0343a c0343a) {
        this.f379b = false;
        this.f380c = new ArrayList();
        this.f381d = new ArrayList();
        this.f385h = Collections.synchronizedMap(new HashMap());
        this.f386i = true;
        this.f387j = true;
        this.f383f = c0343a;
        this.f389l = null;
        this.f384g = CBUtility.m93c();
        this.f388k = false;
    }

    public C0324f m466a() {
        return this.f384g;
    }

    public boolean m472a(C0321a c0321a) {
        this.f382e = c0321a.m127a("assets");
        if (!this.f382e.m131b()) {
            return true;
        }
        CBLogging.m77b("CBViewProtocol", "Media got from the response is null or empty");
        m468a(CBImpressionError.INVALID_RESPONSE);
        return false;
    }

    public void m469a(C0371b c0371b) {
        if (c0371b.m461a()) {
            this.f381d.remove(c0371b);
        }
        this.f380c.remove(c0371b);
        if (this.f380c.isEmpty() && !m476b()) {
            CBLogging.m77b("CBViewProtocol", "Error while downloading the assets");
            m468a(CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        }
    }

    public boolean m476b() {
        if (this.f381d.isEmpty()) {
            m483i();
            return true;
        }
        CBLogging.m81d("CBViewProtocol", "not completed loading assets for impression");
        return false;
    }

    public CBImpressionError m477c() {
        Activity f = Chartboost.m41f();
        if (f == null) {
            this.f389l = null;
            return CBImpressionError.NO_HOST_ACTIVITY;
        } else if (!this.f387j && !this.f386i) {
            return CBImpressionError.WRONG_ORIENTATION;
        } else {
            this.f384g = CBUtility.m93c();
            if ((this.f384g.m166b() && !this.f387j) || (this.f384g.m165a() && !this.f386i)) {
                return CBImpressionError.ERROR_CREATING_VIEW;
            }
            if (this.f389l == null) {
                this.f389l = m474b((Context) f);
            }
            if (this.f383f.f225a != C0339b.NATIVE || this.f389l.m458a(f)) {
                return null;
            }
            this.f389l = null;
            return CBImpressionError.ERROR_CREATING_VIEW;
        }
    }

    public void m478d() {
        m480f();
        for (int i = 0; i < this.f385h.size(); i++) {
            f378a.removeCallbacks((Runnable) this.f385h.get(Integer.valueOf(i)));
        }
        this.f385h.clear();
    }

    public C0370a m479e() {
        return this.f389l;
    }

    public void m480f() {
        if (this.f389l != null) {
            this.f389l.m459b();
        }
        this.f389l = null;
    }

    public C0321a m481g() {
        return this.f382e;
    }

    public void m475b(C0371b c0371b) {
        this.f380c.add(c0371b);
        this.f381d.add(c0371b);
    }

    protected void m468a(CBImpressionError cBImpressionError) {
        this.f383f.m263a(cBImpressionError);
    }

    protected void m482h() {
        if (!this.f388k) {
            this.f388k = true;
            this.f383f.m268c();
        }
    }

    protected void m483i() {
        this.f383f.m269d();
    }

    public boolean m473a(String str, C0321a c0321a) {
        return this.f383f.m266a(str, c0321a);
    }

    public void m470a(boolean z, View view) {
        m471a(z, view, true);
    }

    public void m471a(boolean z, View view, boolean z2) {
        int i = 8;
        if (((z && view.getVisibility() == 0) || (!z && view.getVisibility() == 8)) && this.f385h.get(Integer.valueOf(view.hashCode())) == null) {
            return;
        }
        if (z2) {
            Runnable c03681 = new C03681(this, z, view);
            long j = 500;
            if (this.f383f.f225a == C0339b.WEB) {
                j = 1000;
            }
            bi.m701a(z, view, j);
            m467a(view, c03681, j);
            return;
        }
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        view.setClickable(z);
    }

    protected void m467a(View view, Runnable runnable, long j) {
        Runnable runnable2 = (Runnable) this.f385h.get(Integer.valueOf(view.hashCode()));
        if (runnable2 != null) {
            f378a.removeCallbacks(runnable2);
        }
        this.f385h.put(Integer.valueOf(view.hashCode()), runnable);
        f378a.postDelayed(runnable, j);
    }

    public static int m462a(String str) {
        int i = 0;
        if (str != null) {
            if (!str.startsWith("#")) {
                try {
                    i = Color.parseColor(str);
                } catch (IllegalArgumentException e) {
                    str = "#" + str;
                }
            }
            if (str.length() == 4 || str.length() == 5) {
                StringBuilder stringBuilder = new StringBuilder((str.length() * 2) + 1);
                stringBuilder.append("#");
                for (int i2 = i; i2 < str.length() - 1; i2++) {
                    stringBuilder.append(str.charAt(i2 + 1));
                    stringBuilder.append(str.charAt(i2 + 1));
                }
                str = stringBuilder.toString();
            }
            try {
                i = Color.parseColor(str);
            } catch (Throwable e2) {
                CBLogging.m82d("CBViewProtocol", "error parsing color " + str, e2);
            }
        }
        return i;
    }

    public float m484j() {
        return 0.0f;
    }

    public float m485k() {
        return 0.0f;
    }

    public boolean m486l() {
        return false;
    }

    public void m487m() {
        if (this.f379b) {
            this.f379b = false;
        }
        if (m479e() != null && CBUtility.m93c() != m479e().f377g) {
            m479e().m457a(false);
        }
    }

    public void m488n() {
        this.f379b = true;
    }
}
