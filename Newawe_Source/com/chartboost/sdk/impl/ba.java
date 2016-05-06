package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0318c;
import com.chartboost.sdk.Libraries.C0318c.C0317a;
import com.chartboost.sdk.Libraries.C0323e;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0323e.C0322b;
import com.chartboost.sdk.Libraries.C0328g.C0326a;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.C0336a;
import com.chartboost.sdk.Tracking.C1020a;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.az.C0399c;
import com.google.android.gcm.GCMConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ba implements Observer {
    private static ba f541b;
    private static ConcurrentHashMap<az, File> f542e;
    private static ConcurrentHashMap<az, File> f543f;
    private static List<Runnable> f544h;
    private ay f545a;
    private C0469m f546c;
    private C0330h f547d;
    private ConcurrentHashMap<String, C0405b> f548g;
    private CountDownTimer f549i;

    /* renamed from: com.chartboost.sdk.impl.ba.1 */
    class C04031 extends CountDownTimer {
        final /* synthetic */ ba f526a;

        C04031(ba baVar, long j, long j2) {
            this.f526a = baVar;
            super(j, j2);
        }

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            this.f526a.m626c();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ba.a */
    public enum C0404a {
        ARRAY_OF_DICTIONARY
    }

    /* renamed from: com.chartboost.sdk.impl.ba.b */
    private class C0405b {
        final /* synthetic */ ba f529a;
        private String f530b;
        private int f531c;
        private String f532d;
        private C0404a f533e;
        private boolean f534f;
        private JSONArray f535g;
        private az f536h;

        public C0405b(ba baVar) {
            this.f529a = baVar;
            this.f530b = null;
            this.f531c = 50;
            this.f534f = false;
            this.f535g = null;
            this.f532d = Long.toString(System.nanoTime());
            this.f533e = C0404a.ARRAY_OF_DICTIONARY;
            this.f535g = new JSONArray();
        }

        public String m599a() {
            return this.f532d;
        }

        public void m600a(String str) {
            this.f530b = str;
        }

        public String m602b() {
            return this.f530b;
        }

        public boolean m604c() {
            return this.f534f;
        }

        public void m601a(boolean z) {
            this.f534f = z;
        }

        public void m605d() {
            this.f535g = new JSONArray();
        }

        public synchronized az m598a(az azVar) {
            C0321a i = azVar.m580i();
            if (i.m134c()) {
                i = i.m127a(this.f530b);
                if (!i.m131b()) {
                    if (this.f533e == C0404a.ARRAY_OF_DICTIONARY) {
                        if (this.f529a.f545a.m551c() || (this.f536h != null && this.f536h.m588q())) {
                            this.f532d = Long.toString(System.nanoTime());
                            azVar.m565a(this.f530b, new JSONArray().put(i.m139e()));
                        } else {
                            if (this.f535g.length() == this.f531c) {
                                this.f532d = Long.toString(System.nanoTime());
                                this.f535g = new JSONArray();
                            }
                            this.f535g.put(i.m139e());
                            if (this.f536h != null) {
                                ba.f542e.remove(this.f536h);
                            }
                            azVar.m565a(this.f530b, this.f535g);
                            this.f536h = azVar;
                            azVar = this.f536h;
                        }
                    }
                }
            }
            return azVar;
        }

        public void m603b(az azVar) {
            this.f536h = azVar;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ba.d */
    public static class C0406d {
        private C0321a f537a;
        private C0464i f538b;

        public C0406d(C0321a c0321a, C0464i c0464i) {
            this.f537a = c0321a;
            this.f538b = c0464i;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ba.e */
    private class C0407e implements Runnable {
        final /* synthetic */ ba f539a;
        private az f540b;

        /* renamed from: com.chartboost.sdk.impl.ba.e.a */
        private class C1053a extends C0467l<C0406d> {
            final /* synthetic */ C0407e f3539a;
            private az f3540b;

            protected /* synthetic */ void m3950b(Object obj) {
                m3948a((C0406d) obj);
            }

            public C1053a(C0407e c0407e, int i, String str, az azVar) {
                this.f3539a = c0407e;
                super(i, str, null);
                this.f3540b = azVar;
            }

            public String m3952p() {
                String b = this.f3540b.m569b();
                if (b == null) {
                    return "application/json; charset=utf-8";
                }
                return b;
            }

            public byte[] m3953q() {
                return (this.f3540b.m580i() == null ? StringUtils.EMPTY : this.f3540b.m580i().toString()).getBytes();
            }

            public C0466a m3954s() {
                return this.f3540b.m585n();
            }

            public Map<String, String> m3951i() throws C1031a {
                Map<String, String> hashMap = new HashMap();
                for (Entry entry : this.f3540b.m581j().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return hashMap;
            }

            protected C0472n<C0406d> m3947a(C0464i c0464i) {
                CBError cBError;
                Exception exception;
                Object obj = C0321a.f99a;
                int i = c0464i.f729a;
                if (i <= HttpStatus.SC_MULTIPLE_CHOICES || i >= HttpStatus.SC_OK) {
                    try {
                        String str;
                        byte[] bArr = c0464i.f730b;
                        if (bArr != null) {
                            str = new String(bArr);
                        } else {
                            str = null;
                        }
                        if (str != null) {
                            C0321a a = C0321a.m122a(new JSONObject(new JSONTokener(str)));
                            try {
                                CBError cBError2;
                                C0326a l = this.f3540b.m583l();
                                CBLogging.m79c("CBRequestManager", "Request " + this.f3540b.m578g() + " succeeded. Response code: " + i + ", body: " + str);
                                if (a.m140f(NotificationCompatApi21.CATEGORY_STATUS) == HttpStatus.SC_NOT_FOUND) {
                                    cBError2 = new CBError(C0336a.HTTP_NOT_FOUND, "404 error from server");
                                } else {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    if (l == null || l.m169a(a, stringBuilder)) {
                                        cBError2 = null;
                                    } else {
                                        cBError2 = new CBError(C0336a.UNEXPECTED_RESPONSE, "Json response failed validation");
                                        CBLogging.m77b("CBRequestManager", "Json response failed validation: " + stringBuilder.toString());
                                    }
                                }
                                C0321a c0321a = a;
                                cBError = cBError2;
                                C0321a c0321a2 = c0321a;
                            } catch (Exception e) {
                                exception = e;
                                obj = a;
                                cBError = new CBError(C0336a.MISCELLANEOUS, exception.getLocalizedMessage());
                                if (obj.m134c()) {
                                }
                                return C0472n.m825a(new C1052c(cBError));
                            }
                        }
                        cBError = new CBError(C0336a.INVALID_RESPONSE, "Response is not a valid json object");
                    } catch (Exception e2) {
                        exception = e2;
                        cBError = new CBError(C0336a.MISCELLANEOUS, exception.getLocalizedMessage());
                        if (obj.m134c()) {
                        }
                        return C0472n.m825a(new C1052c(cBError));
                    }
                }
                CBLogging.m81d("CBRequestManager", "Request " + this.f3540b.m578g() + " failed. Response code: " + i);
                cBError = new CBError(C0336a.NETWORK_FAILURE, "Request failed. Response code: " + i + " is not valid ");
                if (obj.m134c() || cBError != null) {
                    return C0472n.m825a(new C1052c(cBError));
                }
                return C0472n.m826a(new C0406d(C0321a.m122a(obj), c0464i), null);
            }

            protected void m3948a(C0406d c0406d) {
                if (!(this.f3539a.f540b.m589r() == null || c0406d == null)) {
                    this.f3539a.f540b.m589r().m552a(c0406d.f537a, this.f3539a.f540b);
                }
                if (this.f3539a.f540b.m579h()) {
                    C1020a.m3733a().m3771l().m216e((File) ba.f543f.get(this.f3539a.f540b));
                    C1020a.m3733a().m3775p();
                    CBLogging.m75a("CBRequestManager", "### Removing track events sent to server...");
                    ba.f543f.remove(this.f3539a.f540b);
                    return;
                }
                this.f3539a.f539a.f547d.m216e((File) ba.f542e.get(this.f3539a.f540b));
                ba.f542e.remove(this.f3539a.f540b);
                C0405b c0405b = (C0405b) this.f3539a.f539a.f548g.get(this.f3539a.f540b.m578g());
                if (c0405b != null && !TextUtils.isEmpty(c0405b.m602b()) && c0405b.m604c() && c0405b.f536h == this.f3539a.f540b) {
                    c0405b.m605d();
                    c0405b.m603b(null);
                }
                this.f3539a.f540b.m575d(false);
                this.f3539a.f539a.m613a(this.f3539a.f540b, c0406d.f538b, null, true);
            }

            public void m3949b(C0475s c0475s) {
                if (!(this.f3539a.f540b == null || C0351c.m365l())) {
                    if (!this.f3539a.f540b.m579h() && ba.f542e.containsKey(this.f3539a.f540b)) {
                        this.f3539a.f539a.f547d.m216e((File) ba.f542e.get(this.f3539a.f540b));
                        ba.f542e.remove(this.f3539a.f540b);
                    } else if (!ba.f543f.isEmpty() && ba.f543f.containsKey(this.f3539a.f540b)) {
                        C1020a.m3733a().m3771l().m216e((File) ba.f543f.get(this.f3539a.f540b));
                        ba.f543f.remove(this.f3539a.f540b);
                    }
                }
                if (c0475s != null) {
                    CBError a;
                    if (c0475s instanceof C1052c) {
                        a = ((C1052c) c0475s).f3538b;
                    } else {
                        a = new CBError(C0336a.NETWORK_FAILURE, c0475s.getMessage());
                    }
                    C0321a c0321a = C0321a.f99a;
                    if (c0475s != null) {
                        try {
                            if (!(c0475s.f770a == null || c0475s.f770a.f730b == null || c0475s.f770a.f730b.length <= 0)) {
                                c0321a = C0321a.m123k(new String(c0475s.f770a.f730b));
                            }
                        } catch (Throwable e) {
                            CBLogging.m82d("CBRequestManager", "unable to read error json", e);
                        }
                    }
                    if (c0475s.f770a == null || c0475s.f770a.f729a != HttpStatus.SC_OK) {
                        if (this.f3539a.f540b.m589r() != null) {
                            this.f3539a.f540b.m589r().m553a(c0321a, this.f3539a.f540b, a);
                        }
                        if (this.f3539a.f540b.m579h()) {
                            ba.f543f.remove(this.f3539a.f540b);
                            return;
                        }
                        this.f3539a.f540b.m575d(false);
                        this.f3539a.f539a.m613a(this.f3539a.f540b, c0475s.f770a, a, false);
                        return;
                    }
                    m3948a(new C0406d(c0321a, c0475s.f770a));
                }
            }
        }

        public C0407e(ba baVar, az azVar) {
            this.f539a = baVar;
            this.f540b = azVar;
        }

        public void run() {
            this.f540b.m572c();
            this.f540b.m574d();
            String format = String.format("%s%s", new Object[]{"https://live.chartboost.com", this.f540b.m576e()});
            this.f540b.m557a();
            C0467l c1053a = new C1053a(this, 1, format, this.f540b);
            c1053a.m784a(new C1061d(30000, 0, 0.0f));
            this.f539a.f546c.m815a(c1053a);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ba.c */
    public static class C1052c extends C0475s {
        private CBError f3538b;

        public C1052c(CBError cBError) {
            this.f3538b = cBError;
        }
    }

    static {
        f544h = new ArrayList();
    }

    public static ba m610a(Context context) {
        if (f541b == null) {
            synchronized (ba.class) {
                if (f541b == null) {
                    f541b = new ba(context);
                }
            }
        }
        return f541b;
    }

    private ba(Context context) {
        this.f545a = null;
        this.f546c = ad.m491a(context.getApplicationContext());
        this.f545a = ay.m543a();
        this.f547d = new C0330h(false);
        f542e = new ConcurrentHashMap();
        f543f = new ConcurrentHashMap();
        this.f548g = new ConcurrentHashMap();
        m622l();
        this.f545a.addObserver(this);
    }

    public C0469m m623a() {
        return this.f546c;
    }

    private void m613a(az azVar, C0464i c0464i, CBError cBError, boolean z) {
        if (azVar != null) {
            String str;
            C0322b[] c0322bArr = new C0322b[5];
            c0322bArr[0] = C0323e.m158a("endpoint", azVar.m578g());
            c0322bArr[1] = C0323e.m158a("statuscode", c0464i == null ? "None" : Integer.valueOf(c0464i.f729a));
            c0322bArr[2] = C0323e.m158a(GCMConstants.EXTRA_ERROR, cBError == null ? "None" : cBError.m250a());
            c0322bArr[3] = C0323e.m158a("errorDescription", cBError == null ? "None" : cBError.m251b());
            c0322bArr[4] = C0323e.m158a("retryCount", Integer.valueOf(azVar.m586o()));
            C0321a a = C0323e.m157a(c0322bArr);
            String str2 = "request_manager";
            String str3 = "request";
            if (z) {
                str = "success";
            } else {
                str = "failure";
            }
            C1020a.m3740a(str2, str3, str, null, null, null, a.m139e());
        }
    }

    protected void m624a(az azVar, C0399c c0399c) {
        if (azVar != null) {
            if (this.f545a.m551c()) {
                if (!azVar.m579h() && azVar.m587p()) {
                    azVar.m573c(false);
                    m612a(azVar);
                }
                m625a(new C0407e(this, azVar));
                return;
            }
            CBError cBError = new CBError(C0336a.INTERNET_UNAVAILABLE, "Internet Unavailable");
            azVar.m575d(false);
            if (!azVar.m579h()) {
                if (azVar.m587p()) {
                    azVar.m573c(false);
                    m612a(azVar);
                }
                m613a(azVar, null, cBError, false);
                if (c0399c != null) {
                    CBLogging.m77b("Network failure", String.format("request %s failed with error : %s", new Object[]{azVar.m578g(), cBError.m251b()}));
                    c0399c.m553a(C0321a.f99a, azVar, cBError);
                }
            }
        }
    }

    public void m625a(Runnable runnable) {
        Object obj = null;
        synchronized (C0318c.class) {
            C0317a c = C0318c.m111c();
            if (c == C0317a.PRELOAD || c == C0317a.LOADING) {
                f544h.add(runnable);
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            ax.m541a().execute(runnable);
        }
    }

    public static void m616b() {
        List<Runnable> arrayList = new ArrayList();
        synchronized (C0318c.class) {
            arrayList.addAll(f544h);
            f544h.clear();
        }
        for (Runnable execute : arrayList) {
            ax.m541a().execute(execute);
        }
    }

    public synchronized void m626c() {
        synchronized (this) {
            if (f542e != null && !f542e.isEmpty()) {
                for (az azVar : f542e.keySet()) {
                    if (!(azVar == null || azVar.m588q())) {
                        azVar.m558a(azVar.m586o() + 1);
                        azVar.m560a(azVar.m589r());
                    }
                }
                m628f();
            } else if (this.f547d.m223n() != null) {
                String[] list = this.f547d.m223n().list();
                if (list != null) {
                    for (String str : list) {
                        az a = m609a(str);
                        if (a != null) {
                            f542e.put(a, this.f547d.m212c(this.f547d.m223n(), str));
                            a.m573c(false);
                            a.m558a(a.m586o() + 1);
                            a.m560a(a.m589r());
                        }
                    }
                }
                m628f();
            }
        }
    }

    public synchronized void m627d() {
        try {
            String[] c;
            if (this.f547d != null) {
                c = this.f547d.m214c(this.f547d.m223n());
            } else {
                c = null;
            }
            if (c != null && c.length > 0) {
                for (String str : c) {
                    C0321a a = this.f547d.m204a(this.f547d.m223n(), str);
                    if (a.m134c()) {
                        this.f547d.m209b(this.f547d.m223n(), str);
                        az a2 = az.m554a(a);
                        if (a2 != null) {
                            a2.m567a(true);
                            a2.m590s();
                        } else {
                            CBLogging.m77b("CBRequestManager", "Error processing video completion event");
                        }
                    }
                }
            }
        } catch (Throwable e) {
            CBLogging.m78b("CBRequestManager", "Error executing saved requests", e);
        }
    }

    public static void m619e() {
        C1020a a = C1020a.m3733a();
        if (!C0351c.m366m()) {
            if (!(f543f == null || f543f.isEmpty())) {
                f543f.clear();
            }
            C0330h l = a.m3771l();
            l.m217f(l.m225p());
        } else if (f543f.isEmpty()) {
            try {
                String[] c;
                C0330h l2 = a.m3771l();
                if (l2 != null) {
                    c = l2.m214c(l2.m225p());
                } else {
                    c = null;
                }
                if (c != null) {
                    for (String str : c) {
                        if (!a.m3764c(str)) {
                            C0321a a2 = l2.m204a(l2.m225p(), str);
                            if (a2.m134c()) {
                                CBLogging.m75a("CBRequestManager", "### Flushing out " + str + "track events from cache to server...");
                                az a3 = a.m3758a(a2);
                                f543f.put(a3, l2.m212c(l2.m225p(), str));
                                a3.m590s();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                CBLogging.m78b("CBRequestManager", "Error executing saved requests", e);
            }
        } else {
            for (az azVar : f543f.keySet()) {
                if (!(azVar == null || azVar.m588q())) {
                    azVar.m558a(azVar.m586o() + 1);
                    azVar.m560a(azVar.m589r());
                }
            }
        }
    }

    private void m612a(az azVar) {
        if (azVar != null) {
            Object a;
            if (azVar.m582k()) {
                C0405b c0405b = (C0405b) this.f548g.get(azVar.m578g());
                if (c0405b == null || TextUtils.isEmpty(c0405b.m602b()) || !c0405b.m604c()) {
                    a = this.f547d.m205a(this.f547d.m223n(), null, azVar.m591t());
                } else {
                    azVar = c0405b.m598a(azVar);
                    a = this.f547d.m206a(this.f547d.m223n(), c0405b.m599a(), azVar.m591t());
                }
            } else {
                a = null;
            }
            if ((azVar.m582k() || azVar.m584m()) && a != null) {
                f542e.put(azVar, a);
            }
        }
    }

    private az m609a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C0321a a = this.f547d.m204a(this.f547d.m223n(), str);
        if (a.m134c()) {
            return az.m554a(a);
        }
        return null;
    }

    public void m628f() {
        if (this.f549i == null) {
            this.f549i = new C04031(this, 240000, 1000).start();
        }
    }

    public void m629g() {
        CBLogging.m75a("CBRequestManager", "Timer stopped:");
        if (this.f549i != null) {
            this.f549i.cancel();
            this.f549i = null;
        }
    }

    public void update(Observable observable, Object data) {
        if (this.f549i != null) {
            m629g();
        }
        m626c();
    }

    public ConcurrentHashMap<az, File> m630h() {
        return f542e;
    }

    public C0330h m631i() {
        return this.f547d;
    }

    private void m622l() {
        C0405b c0405b = new C0405b(this);
        c0405b.m600a("track_info");
        c0405b.m601a(true);
        this.f548g.put("/post-install-event/".concat("tracking"), c0405b);
    }
}
