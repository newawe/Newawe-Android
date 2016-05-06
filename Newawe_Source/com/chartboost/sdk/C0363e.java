package com.chartboost.sdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0338a;
import com.chartboost.sdk.Model.C0343a.C0339b;
import com.chartboost.sdk.Model.C0343a.C0342e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1020a;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.az.C0399c;
import com.chartboost.sdk.impl.az.C1051d;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

/* renamed from: com.chartboost.sdk.e */
public abstract class C0363e {
    protected static Handler f351a;
    public C0339b f352b;
    private Map<String, C0343a> f353c;
    private Map<String, C0343a> f354d;
    private Map<String, C0343a> f355e;
    private C0362a f356f;

    /* renamed from: com.chartboost.sdk.e.1 */
    class C03571 implements Runnable {
        final /* synthetic */ String f338a;
        final /* synthetic */ C0343a f339b;
        final /* synthetic */ C0363e f340c;

        C03571(C0363e c0363e, String str, C0343a c0343a) {
            this.f340c = c0363e;
            this.f338a = str;
            this.f339b = c0343a;
        }

        public void run() {
            if (this.f340c.m416c(this.f338a)) {
                C0343a d = this.f340c.m418d(this.f338a);
                if (d.f227c == C0342e.NONE) {
                    d.f227c = C0342e.CACHED;
                }
                this.f340c.m427h(d);
                return;
            }
            this.f340c.m415c(this.f339b);
        }
    }

    /* renamed from: com.chartboost.sdk.e.2 */
    class C03582 implements Runnable {
        final /* synthetic */ C0343a f341a;
        final /* synthetic */ C0363e f342b;

        C03582(C0363e c0363e, C0343a c0343a) {
            this.f342b = c0363e;
            this.f341a = c0343a;
        }

        public void run() {
            az f = this.f342b.m424f(this.f341a);
            if (f != null) {
                this.f342b.m408a(f, this.f341a);
                C1020a.m3742a(this.f342b.m420e(), this.f341a.f229e, this.f341a.m285t(), this.f341a.f231g);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.e.3 */
    class C03593 implements Runnable {
        final /* synthetic */ C0343a f343a;
        final /* synthetic */ CBImpressionError f344b;
        final /* synthetic */ C0363e f345c;

        C03593(C0363e c0363e, C0343a c0343a, CBImpressionError cBImpressionError) {
            this.f345c = c0363e;
            this.f343a = c0343a;
            this.f344b = cBImpressionError;
        }

        public void run() {
            this.f345c.m435p(this.f343a);
            C0367f h = Chartboost.m46h();
            if (h != null && h.m448c()) {
                h.m444a(this.f343a, true);
            } else if (h != null && h.m450d()) {
                h.m446b(this.f343a);
            }
            this.f345c.m410b().m392a(this.f343a, this.f344b);
        }
    }

    /* renamed from: com.chartboost.sdk.e.a */
    protected interface C0362a {
        void m391a(C0343a c0343a);

        void m392a(C0343a c0343a, CBImpressionError cBImpressionError);

        void m393b(C0343a c0343a);

        void m394c(C0343a c0343a);

        void m395d(C0343a c0343a);

        void m396e(C0343a c0343a);

        boolean m397f(C0343a c0343a);

        boolean m398g(C0343a c0343a);

        boolean m399h(C0343a c0343a);
    }

    /* renamed from: com.chartboost.sdk.e.5 */
    class C10285 implements C0399c {
        final /* synthetic */ C0343a f3423a;
        final /* synthetic */ C0363e f3424b;

        /* renamed from: com.chartboost.sdk.e.5.1 */
        class C03601 implements Runnable {
            final /* synthetic */ C0321a f346a;
            final /* synthetic */ C10285 f347b;

            C03601(C10285 c10285, C0321a c0321a) {
                this.f347b = c10285;
                this.f346a = c0321a;
            }

            public void run() {
                try {
                    if (this.f346a.m134c()) {
                        this.f347b.f3423a.f243s = false;
                        Object e = this.f346a.m138e("type");
                        if (TextUtils.isEmpty(e) || !e.equals("native")) {
                            this.f347b.f3424b.m407a(this.f347b.f3423a, C0339b.WEB);
                        } else {
                            this.f347b.f3424b.m407a(this.f347b.f3423a, C0339b.NATIVE);
                        }
                        this.f347b.f3424b.m405a(this.f347b.f3423a, this.f346a);
                        return;
                    }
                    this.f347b.f3424b.m406a(this.f347b.f3423a, CBImpressionError.INVALID_RESPONSE);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        /* renamed from: com.chartboost.sdk.e.5.2 */
        class C03612 implements Runnable {
            final /* synthetic */ az f348a;
            final /* synthetic */ CBError f349b;
            final /* synthetic */ C10285 f350c;

            C03612(C10285 c10285, az azVar, CBError cBError) {
                this.f350c = c10285;
                this.f348a = azVar;
                this.f349b = cBError;
            }

            public void run() {
                this.f350c.f3423a.f243s = false;
                String str = "network failure";
                String str2 = "request %s failed with error %s: %s";
                Object[] objArr = new Object[3];
                objArr[0] = this.f348a.m578g();
                objArr[1] = this.f349b.m250a().name();
                objArr[2] = this.f349b.m251b() != null ? this.f349b.m251b() : StringUtils.EMPTY;
                CBLogging.m81d(str, String.format(str2, objArr));
                this.f350c.f3424b.m406a(this.f350c.f3423a, this.f349b.m252c());
            }
        }

        C10285(C0363e c0363e, C0343a c0343a) {
            this.f3424b = c0363e;
            this.f3423a = c0343a;
        }

        public void m3797a(C0321a c0321a, az azVar) {
            Chartboost.m23a(new C03601(this, c0321a));
        }

        public void m3798a(C0321a c0321a, az azVar, CBError cBError) {
            Chartboost.m23a(new C03612(this, azVar, cBError));
        }
    }

    /* renamed from: com.chartboost.sdk.e.4 */
    class C13094 extends C1051d {
        final /* synthetic */ C0343a f4194a;
        final /* synthetic */ C0363e f4195b;

        C13094(C0363e c0363e, C0343a c0343a) {
            this.f4195b = c0363e;
            this.f4194a = c0343a;
        }

        public void m5025a(C0321a c0321a, az azVar) {
            if (C0351c.m363j() && !this.f4195b.m416c(this.f4194a.f229e)) {
                this.f4195b.m411b(this.f4194a.f229e);
            }
        }
    }

    protected abstract C0343a m402a(String str, boolean z);

    protected abstract C0362a m414c();

    public abstract String m420e();

    protected abstract az m424f(C0343a c0343a);

    protected abstract az m432m(C0343a c0343a);

    static {
        f351a = CBUtility.m96e();
    }

    public C0363e() {
        this.f352b = C0339b.NATIVE;
        this.f356f = null;
        this.f354d = new HashMap();
        this.f353c = new HashMap();
        this.f355e = new HashMap();
    }

    public void m409a(String str) {
        C0343a a = m402a(str, false);
        C0367f h = Chartboost.m46h();
        if (h != null && h.m450d()) {
            m406a(a, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        } else if (!m413b(a)) {
            f351a.post(new C03571(this, str, a));
        }
    }

    public void m411b(String str) {
        C0343a d;
        if (m416c(str)) {
            d = m418d(str);
            if (d != null) {
                m410b().m395d(d);
                return;
            }
            return;
        }
        d = m402a(str, true);
        if (!m413b(d)) {
            m415c(d);
        }
    }

    protected void m404a(C0343a c0343a) {
        m437r(c0343a);
        m410b().m395d(c0343a);
        c0343a.f227c = C0342e.CACHED;
    }

    protected final boolean m413b(C0343a c0343a) {
        if (m410b().m399h(c0343a) || CBUtility.m87a().getInt("cbPrefSessionCount", 0) != 1) {
            return false;
        }
        m406a(c0343a, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED);
        return true;
    }

    protected void m415c(C0343a c0343a) {
        if (m426g(c0343a) && m410b().m398g(c0343a) && !m401s(c0343a)) {
            if (!c0343a.f231g && c0343a.f228d == C0338a.MORE_APPS && C0351c.m376w()) {
                c0343a.f234j = true;
                Chartboost.m22a(c0343a);
            }
            if (m422e(c0343a)) {
                m419d(c0343a);
            }
        }
    }

    protected void m419d(C0343a c0343a) {
        ax.m541a().execute(new C03582(this, c0343a));
    }

    protected boolean m422e(C0343a c0343a) {
        return true;
    }

    private final synchronized boolean m401s(C0343a c0343a) {
        boolean z = true;
        synchronized (this) {
            if (m434o(c0343a) != null) {
                CBLogging.m77b(getClass().getSimpleName(), String.format("%s %s", new Object[]{"Request already in process for impression with location", c0343a.f229e}));
            } else {
                m436q(c0343a);
                z = false;
            }
        }
        return z;
    }

    protected void m406a(C0343a c0343a, CBImpressionError cBImpressionError) {
        Chartboost.m23a(new C03593(this, c0343a, cBImpressionError));
    }

    protected final boolean m426g(C0343a c0343a) {
        if (C0351c.m369p()) {
            C0367f h = Chartboost.m46h();
            if (!c0343a.f231g && h != null && h.m450d()) {
                m406a(c0343a, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
                return false;
            } else if (ay.m543a().m551c()) {
                return true;
            } else {
                m406a(c0343a, CBImpressionError.INTERNET_UNAVAILABLE);
                return false;
            }
        }
        m406a(c0343a, CBImpressionError.SESSION_NOT_STARTED);
        return false;
    }

    protected void m427h(C0343a c0343a) {
        boolean z = c0343a.f227c != C0342e.DISPLAYED;
        if (z) {
            if (C0351c.m343b() != null && C0351c.m343b().doesWrapperUseCustomShouldDisplayBehavior()) {
                this.f355e.put(c0343a.f229e == null ? StringUtils.EMPTY : c0343a.f229e, c0343a);
            }
            if (!m410b().m397f(c0343a)) {
                return;
            }
        }
        m400a(c0343a, z);
    }

    protected void m412b(String str, boolean z) {
        if (str == null) {
            str = StringUtils.EMPTY;
        }
        C0343a c0343a = (C0343a) this.f355e.get(str);
        if (c0343a != null) {
            this.f355e.remove(str);
            if (z) {
                m400a(c0343a, true);
            }
        }
    }

    private void m400a(C0343a c0343a, boolean z) {
        boolean z2 = c0343a.f227c == C0342e.CACHED;
        m429j(c0343a);
        C0367f h = Chartboost.m46h();
        if (h != null) {
            if (h.m448c()) {
                h.m444a(c0343a, false);
            } else if (!(!c0343a.f234j || z2 || c0343a.f227c == C0342e.DISPLAYED)) {
                return;
            }
        }
        if (z) {
            m428i(c0343a);
        } else {
            Chartboost.m22a(c0343a);
        }
    }

    protected void m428i(C0343a c0343a) {
        Chartboost.m22a(c0343a);
    }

    protected void m429j(C0343a c0343a) {
        m430k(c0343a);
    }

    public void m430k(C0343a c0343a) {
        if (!c0343a.f232h) {
            c0343a.f232h = true;
            c0343a.f231g = false;
            m431l(c0343a);
            if (m418d(c0343a.f229e) == c0343a) {
                m421e(c0343a.f229e);
            }
        }
    }

    protected void m431l(C0343a c0343a) {
        az m = m432m(c0343a);
        m.m567a(true);
        if (c0343a.f231g) {
            m.m565a("cached", SchemaSymbols.ATTVAL_TRUE_1);
        } else {
            m.m565a("cached", SchemaSymbols.ATTVAL_FALSE_0);
        }
        Object e = c0343a.m259A().m138e("ad_id");
        if (e != null) {
            m.m565a("ad_id", e);
        }
        m.m565a("location", c0343a.f229e);
        m.m560a(new C13094(this, c0343a));
        C1020a.m3737a(m420e(), c0343a.f229e, c0343a.m285t());
    }

    protected final boolean m433n(C0343a c0343a) {
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - c0343a.f226b.getTime()) >= 86400;
    }

    protected void m405a(C0343a c0343a, C0321a c0321a) {
        if (c0321a.m140f(NotificationCompatApi21.CATEGORY_STATUS) == HttpStatus.SC_NOT_FOUND) {
            CBLogging.m77b(c0343a.f228d, "Invalid status code" + c0321a.m127a(NotificationCompatApi21.CATEGORY_STATUS));
            m406a(c0343a, CBImpressionError.NO_AD_FOUND);
        } else if (c0321a.m140f(NotificationCompatApi21.CATEGORY_STATUS) != HttpStatus.SC_OK) {
            CBLogging.m77b(c0343a.f228d, "Invalid status code" + c0321a.m127a(NotificationCompatApi21.CATEGORY_STATUS));
            m406a(c0343a, CBImpressionError.INVALID_RESPONSE);
        } else {
            c0343a.m262a(c0321a, C0356d.m382a().f335a);
        }
    }

    protected final void m408a(az azVar, C0343a c0343a) {
        c0343a.f243s = true;
        azVar.m560a(new C10285(this, c0343a));
    }

    protected synchronized C0343a m434o(C0343a c0343a) {
        C0343a c0343a2;
        if (c0343a != null) {
            c0343a2 = (C0343a) this.f353c.get(c0343a.f229e);
        } else {
            c0343a2 = null;
        }
        return c0343a2;
    }

    protected synchronized void m435p(C0343a c0343a) {
        if (c0343a != null) {
            this.f353c.remove(c0343a.f229e);
        }
    }

    protected synchronized void m436q(C0343a c0343a) {
        if (c0343a != null) {
            this.f353c.put(c0343a.f229e, c0343a);
        }
    }

    public boolean m416c(String str) {
        return m418d(str) != null;
    }

    protected C0343a m418d(String str) {
        C0343a c0343a = (C0343a) this.f354d.get(str);
        return (c0343a == null || m433n(c0343a)) ? null : c0343a;
    }

    protected void m421e(String str) {
        this.f354d.remove(str);
    }

    protected void m403a() {
        this.f354d.clear();
    }

    protected void m437r(C0343a c0343a) {
        this.f354d.put(c0343a.f229e, c0343a);
    }

    protected final C0362a m410b() {
        if (this.f356f == null) {
            this.f356f = m414c();
        }
        return this.f356f;
    }

    protected Context m417d() {
        try {
            Method declaredMethod = Chartboost.class.getDeclaredMethod("getValidContext", new Class[0]);
            declaredMethod.setAccessible(true);
            return (Context) declaredMethod.invoke(null, new Object[0]);
        } catch (Throwable e) {
            CBLogging.m78b(this, "Error encountered getting valid context", e);
            CBUtility.throwProguardError(e);
            return C0351c.m378y();
        }
    }

    public C0339b m423f() {
        return this.f352b;
    }

    public void m407a(C0343a c0343a, C0339b c0339b) {
        if (c0343a != null) {
            c0343a.f225a = c0339b;
        }
        this.f352b = c0339b;
    }

    public String m425g() {
        if (this.f352b == C0339b.NATIVE) {
            return "native";
        }
        return "web";
    }
}
