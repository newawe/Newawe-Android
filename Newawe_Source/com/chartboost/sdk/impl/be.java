package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0328g.C0327k;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.C0467l.C0466a;
import com.chartboost.sdk.impl.C0472n.C0470a;
import com.chartboost.sdk.impl.az.C0399c;
import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

public class be {
    public static be f577a;
    private static final String f578b;
    private static C0330h f579c;
    private static C0469m f580d;
    private static ConcurrentHashMap<Integer, C1057b> f581e;
    private static C0418a f582f;
    private static C0418a f583g;
    private static AtomicInteger f584h;
    private static AtomicInteger f585i;
    private static boolean f586j;
    private static boolean f587k;
    private static Observer f588l;
    private static C0399c f589m;

    /* renamed from: com.chartboost.sdk.impl.be.1 */
    static class C04171 implements Observer {
        C04171() {
        }

        public void update(Observable observable, Object data) {
            be.m680l();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.be.a */
    public enum C0418a {
        kCBIntial,
        kCBInProgress
    }

    /* renamed from: com.chartboost.sdk.impl.be.2 */
    static class C10562 implements C0399c {
        C10562() {
        }

        public void m3961a(C0321a c0321a, az azVar) {
            try {
                synchronized (be.class) {
                    be.f582f = C0418a.kCBIntial;
                    if (c0321a.m134c()) {
                        CBLogging.m75a(be.f578b, "Got Video list from server :)" + c0321a);
                        be.m663a(c0321a.m127a("videos"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void m3962a(C0321a c0321a, az azVar, CBError cBError) {
            be.f582f = C0418a.kCBIntial;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.be.b */
    private static class C1057b extends C0467l<Object> {
        private String f3547a;
        private long f3548b;
        private String f3549c;

        public C1057b(int i, String str, C1058c c1058c, String str2) {
            super(i, str, c1058c);
            this.f3547a = str2;
            this.f3549c = str;
            this.f3548b = System.currentTimeMillis();
        }

        protected void m3966b(Object obj) {
        }

        protected C0472n<Object> m3965a(C0464i c0464i) {
            if (c0464i != null) {
                Long.valueOf((System.currentTimeMillis() - this.f3548b) / 1000);
                CBLogging.m75a(be.f578b, "Video download Success. Storing video in cache" + this.f3547a);
                be.f579c.m208a(be.f579c.m219j(), this.f3547a, c0464i.f730b);
            }
            synchronized (be.m661a()) {
                if (be.f584h.get() == be.f585i.get()) {
                    be.f584h.set(0);
                    be.f585i.set(0);
                    be.f583g = C0418a.kCBIntial;
                    be.f581e.clear();
                }
            }
            return C0472n.m826a(null, null);
        }

        public C0466a m3967s() {
            return C0466a.LOW;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.be.c */
    private static class C1058c implements C0470a {
        private C1057b f3550a;

        private C1058c() {
        }

        public void m3969a(C0475s c0475s) {
            if ((c0475s instanceof C1067r) || (c0475s instanceof C1066q) || (c0475s instanceof C1063h)) {
                if (this.f3550a != null) {
                    Long.valueOf((System.currentTimeMillis() - this.f3550a.f3548b) / 1000);
                }
                be.f581e.put(Integer.valueOf(this.f3550a.hashCode()), this.f3550a);
                CBLogging.m77b(be.f578b, "Error downloading video " + c0475s.getMessage() + this.f3550a.f3547a);
            }
        }
    }

    static {
        f578b = be.class.getSimpleName();
        f584h = new AtomicInteger();
        f585i = new AtomicInteger();
        f586j = true;
        f587k = false;
        f588l = new C04171();
        f589m = new C10562();
    }

    private be() {
    }

    public static synchronized be m661a() {
        be beVar;
        synchronized (be.class) {
            if (f577a == null) {
                f577a = new be();
                m679k();
            }
            beVar = f577a;
        }
        return beVar;
    }

    private static synchronized void m679k() {
        synchronized (be.class) {
            if (!f587k) {
                f587k = true;
                f579c = new C0330h(true);
                f581e = new ConcurrentHashMap();
                f582f = C0418a.kCBIntial;
                f583g = C0418a.kCBIntial;
                f580d = ba.m610a(C0351c.m378y()).m623a();
                ay.m543a().addObserver(f588l);
            }
        }
    }

    public static synchronized void m668b() {
        synchronized (be.class) {
            if (!f587k) {
                m679k();
            }
            if (C0351c.m377x()) {
                CBLogging.m75a(f578b, "Prefetching the Video list");
                if (!(C0418a.kCBInProgress == f582f || C0418a.kCBInProgress == f583g)) {
                    if (!(f581e == null || f581e.isEmpty())) {
                        f581e.clear();
                        f580d.m818a(Integer.valueOf(f588l.hashCode()));
                        f583g = C0418a.kCBIntial;
                        CBLogging.m75a(f578b, "prefetchVideo: Clearing all volley request for new start");
                    }
                    f582f = C0418a.kCBInProgress;
                    Object jSONArray = new JSONArray();
                    if (m671c() != null) {
                        for (Object put : m671c()) {
                            jSONArray.put(put);
                        }
                    }
                    f585i.set(0);
                    f584h.set(0);
                    az azVar = new az("/api/video-prefetch");
                    azVar.m565a("local-videos", jSONArray);
                    C0327k[] c0327kArr = new C0327k[2];
                    c0327kArr[0] = C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a);
                    c0327kArr[1] = C0328g.m178a("videos", C0328g.m180b(C0328g.m176a(C0328g.m178a("video", C0328g.m173a(C0328g.m172a())), C0328g.m178a("id", C0328g.m172a()))));
                    azVar.m559a(C0328g.m176a(c0327kArr));
                    azVar.m571b(true);
                    azVar.m560a(f589m);
                }
            }
        }
    }

    public static synchronized void m663a(C0321a c0321a) {
        synchronized (be.class) {
            if (C0351c.m377x()) {
                if (c0321a.m134c()) {
                    HashMap hashMap = new HashMap();
                    HashMap hashMap2 = new HashMap();
                    String[] c = m671c();
                    for (int i = 0; i < c0321a.m154o(); i++) {
                        C0321a c2 = c0321a.m133c(i);
                        if (!(c2.m132b("id") || c2.m132b("video"))) {
                            String e = c2.m138e("id");
                            CharSequence e2 = c2.m138e("video");
                            if (!(f579c.m210b(e) || TextUtils.isEmpty(e) || TextUtils.isEmpty(e2) || hashMap2.get(e) != null)) {
                                hashMap2.put(e, e2);
                                f585i.incrementAndGet();
                            }
                            hashMap.put(e, e2);
                        }
                    }
                    if (f586j) {
                        f586j = false;
                    }
                    CBLogging.m75a(f578b, "Synchronizing videos with the list got from the server");
                    m665a(hashMap, c);
                    if (!hashMap2.isEmpty()) {
                        m664a(hashMap2);
                        f583g = C0418a.kCBInProgress;
                    }
                }
            }
        }
    }

    private static synchronized void m665a(HashMap<String, String> hashMap, String[] strArr) {
        synchronized (be.class) {
            if (!(hashMap == null || strArr == null)) {
                for (String str : strArr) {
                    if (!hashMap.containsKey(str)) {
                        File c = f579c.m212c(f579c.m219j(), str);
                        if (!(c == null || str.equals(".nomedia"))) {
                            CBLogging.m83e(f578b, "Deleting video: " + c.getAbsolutePath());
                            f579c.m216e(c);
                        }
                    }
                }
            }
        }
    }

    private static synchronized void m664a(HashMap<String, String> hashMap) {
        synchronized (be.class) {
            for (String str : hashMap.keySet()) {
                C1058c c1058c = new C1058c();
                C0467l c1057b = new C1057b(0, (String) hashMap.get(str), c1058c, str);
                c1057b.m784a(new C1061d(30000, 0, 0.0f));
                c1058c.f3550a = c1057b;
                c1057b.m785a((Object) Integer.valueOf(f588l.hashCode()));
                f580d.m815a(c1057b);
                CBLogging.m75a(f578b, "Downloading video:" + ((String) hashMap.get(str)));
            }
        }
    }

    public static String[] m671c() {
        if (f579c == null) {
            return null;
        }
        return f579c.m214c(f579c.m219j());
    }

    public static synchronized void m672d() {
        synchronized (be.class) {
            f580d.m818a(Integer.valueOf(f588l.hashCode()));
        }
    }

    public static String m662a(String str) {
        if (f579c.m215d(str)) {
            return f579c.m212c(f579c.m219j(), str).getPath();
        }
        return null;
    }

    public static String m667b(C0321a c0321a) {
        if (c0321a == null) {
            return null;
        }
        C0321a a = c0321a.m127a("assets");
        if (a.m131b()) {
            return null;
        }
        C0321a a2 = a.m127a(CBUtility.m93c().m165a() ? "video-portrait" : "video-landscape");
        if (a2.m131b()) {
            return null;
        }
        String e = a2.m138e("id");
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        return m662a(e);
    }

    public static void m669b(String str) {
        if (f579c.m210b(str)) {
            f579c.m209b(f579c.m219j(), str);
        }
    }

    public static boolean m670c(C0321a c0321a) {
        return !TextUtils.isEmpty(m667b(c0321a));
    }

    private static synchronized void m680l() {
        synchronized (be.class) {
            CBLogging.m75a(f578b, "Process Request called");
            if (!(f582f == C0418a.kCBInProgress || f583g == C0418a.kCBInProgress)) {
                if ((f583g == C0418a.kCBIntial && f581e != null) || f581e.size() > 0) {
                    for (Integer num : f581e.keySet()) {
                        f583g = C0418a.kCBInProgress;
                        f580d.m815a((C0467l) f581e.get(num));
                        f581e.remove(num);
                    }
                }
            }
        }
    }
}
