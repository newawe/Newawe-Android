package com.chartboost.sdk.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.chartboost.sdk.impl.C0402b.C0401a;
import com.chartboost.sdk.impl.C0472n.C0470a;
import com.chartboost.sdk.impl.C0478t.C0477a;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;

/* renamed from: com.chartboost.sdk.impl.l */
public abstract class C0467l<T> implements Comparable<C0467l<T>> {
    private final C0477a f741a;
    private final int f742b;
    private final String f743c;
    private final int f744d;
    private final C0470a f745e;
    private Integer f746f;
    private C0469m f747g;
    private boolean f748h;
    private boolean f749i;
    private boolean f750j;
    private long f751k;
    private C0474p f752l;
    private C0401a f753m;
    private Object f754n;

    /* renamed from: com.chartboost.sdk.impl.l.1 */
    class C04651 implements Runnable {
        final /* synthetic */ C0467l f733a;
        private final /* synthetic */ String f734b;
        private final /* synthetic */ long f735c;

        C04651(C0467l c0467l, String str, long j) {
            this.f733a = c0467l;
            this.f734b = str;
            this.f735c = j;
        }

        public void run() {
            this.f733a.f741a.m836a(this.f734b, this.f735c);
            this.f733a.f741a.m835a(toString());
        }
    }

    /* renamed from: com.chartboost.sdk.impl.l.a */
    public enum C0466a {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract C0472n<T> m786a(C0464i c0464i);

    protected abstract void m791b(T t);

    public /* synthetic */ int compareTo(Object obj) {
        return m780a((C0467l) obj);
    }

    public C0467l(int i, String str, C0470a c0470a) {
        C0477a c0477a;
        if (C0477a.f774a) {
            c0477a = new C0477a();
        } else {
            c0477a = null;
        }
        this.f741a = c0477a;
        this.f748h = true;
        this.f749i = false;
        this.f750j = false;
        this.f751k = 0;
        this.f753m = null;
        this.f742b = i;
        this.f743c = str;
        this.f745e = c0470a;
        m784a(new C1061d());
        this.f744d = C0467l.m778c(str);
    }

    public int m779a() {
        return this.f742b;
    }

    public C0467l<?> m785a(Object obj) {
        this.f754n = obj;
        return this;
    }

    public Object m789b() {
        return this.f754n;
    }

    public int m793c() {
        return this.f744d;
    }

    private static int m778c(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public C0467l<?> m784a(C0474p c0474p) {
        this.f752l = c0474p;
        return this;
    }

    public void m788a(String str) {
        if (C0477a.f774a) {
            this.f741a.m836a(str, Thread.currentThread().getId());
        } else if (this.f751k == 0) {
            this.f751k = SystemClock.elapsedRealtime();
        }
    }

    void m792b(String str) {
        if (this.f747g != null) {
            this.f747g.m821b(this);
        }
        if (C0477a.f774a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new C04651(this, str, id));
                return;
            }
            this.f741a.m836a(str, id);
            this.f741a.m835a(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.f751k >= 3000) {
            C0478t.m839b("%d ms: %s", Long.valueOf(SystemClock.elapsedRealtime() - this.f751k), toString());
        }
    }

    public C0467l<?> m783a(C0469m c0469m) {
        this.f747g = c0469m;
        return this;
    }

    public final C0467l<?> m781a(int i) {
        this.f746f = Integer.valueOf(i);
        return this;
    }

    public String m794d() {
        return this.f743c;
    }

    public String m795e() {
        return m794d();
    }

    public C0467l<?> m782a(C0401a c0401a) {
        this.f753m = c0401a;
        return this;
    }

    public C0401a m796f() {
        return this.f753m;
    }

    public void m797g() {
        this.f749i = true;
    }

    public boolean m798h() {
        return this.f749i;
    }

    public Map<String, String> m799i() throws C1031a {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> m800j() throws C1031a {
        return m804n();
    }

    @Deprecated
    protected String m801k() {
        return m805o();
    }

    @Deprecated
    public String m802l() {
        return m806p();
    }

    @Deprecated
    public byte[] m803m() throws C1031a {
        Map j = m800j();
        if (j == null || j.size() <= 0) {
            return null;
        }
        return m776a(j, m801k());
    }

    protected Map<String, String> m804n() throws C1031a {
        return null;
    }

    protected String m805o() {
        return HTTP.UTF_8;
    }

    public String m806p() {
        return "application/x-www-form-urlencoded; charset=" + m805o();
    }

    public byte[] m807q() throws C1031a {
        Map n = m804n();
        if (n == null || n.size() <= 0) {
            return null;
        }
        return m776a(n, m805o());
    }

    private byte[] m776a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final boolean m808r() {
        return this.f748h;
    }

    public C0466a m809s() {
        return C0466a.NORMAL;
    }

    public final int m810t() {
        return this.f752l.m831a();
    }

    public C0474p m811u() {
        return this.f752l;
    }

    public void m812v() {
        this.f750j = true;
    }

    public boolean m813w() {
        return this.f750j;
    }

    protected C0475s m787a(C0475s c0475s) {
        return c0475s;
    }

    public void m790b(C0475s c0475s) {
        if (this.f745e != null) {
            this.f745e.m823a(c0475s);
        }
    }

    public int m780a(C0467l<T> c0467l) {
        C0466a s = m809s();
        C0466a s2 = c0467l.m809s();
        if (s == s2) {
            return this.f746f.intValue() - c0467l.f746f.intValue();
        }
        return s2.ordinal() - s.ordinal();
    }

    public String toString() {
        return new StringBuilder(String.valueOf(this.f749i ? "[X] " : "[ ] ")).append(m794d()).append(" ").append("0x" + Integer.toHexString(m793c())).append(" ").append(m809s()).append(" ").append(this.f746f).toString();
    }
}
