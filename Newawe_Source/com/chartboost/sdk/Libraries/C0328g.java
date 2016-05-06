package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Libraries.C0323e.C0321a;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.g */
public final class C0328g {
    private static final String f121a;
    private static C1308p f122b;
    private static C1014n f123c;
    private static C1012j f124d;
    private static C1006d f125e;
    private static C1013l f126f;

    /* renamed from: com.chartboost.sdk.Libraries.g.a */
    public static abstract class C0326a {
        private String f118a;

        public abstract String m167a();

        public abstract boolean m168a(Object obj);

        public C0326a() {
            this.f118a = null;
        }

        public boolean m169a(Object obj, StringBuilder stringBuilder) {
            if (obj instanceof C0321a) {
                obj = ((C0321a) obj).m153n();
            }
            boolean a = m168a(obj);
            if (!a) {
                stringBuilder.append(this.f118a != null ? this.f118a : m167a());
            }
            return a;
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.k */
    public static class C0327k {
        private String f119a;
        private C0326a f120b;

        public C0327k(String str, C0326a c0326a) {
            this.f119a = str;
            this.f120b = c0326a;
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.b */
    private static class C1005b extends C0326a {
        private C1005b() {
        }

        public boolean m3695a(Object obj) {
            return (obj instanceof List) || (obj instanceof JSONArray);
        }

        public String m3694a() {
            return "object must be an array.";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.d */
    private static class C1006d extends C0326a {
        private C1006d() {
        }

        public boolean m3697a(Object obj) {
            return Boolean.class.isInstance(obj) || Boolean.TYPE.isInstance(obj);
        }

        public String m3696a() {
            return "object must be a boolean.";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.e */
    public static abstract class C1007e extends C0326a {
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.f */
    private static class C1008f extends C0326a {
        protected C0327k[] f3382a;
        protected String f3383b;

        public C1008f(C0327k[] c0327kArr) {
            this.f3383b = null;
            this.f3382a = c0327kArr;
        }

        public boolean m3699a(Object obj) {
            String a;
            C0326a b;
            int i;
            if (obj instanceof Map) {
                Map map = (Map) obj;
                for (Entry entry : map.entrySet()) {
                    if (!(entry.getKey() instanceof String)) {
                        this.f3383b = "key '" + entry.getKey().toString() + "' is not a string";
                        return false;
                    }
                }
                if (this.f3382a != null && this.f3382a.length >= 1) {
                    for (i = 0; i < this.f3382a.length; i++) {
                        a = this.f3382a[i].f119a;
                        b = this.f3382a[i].f120b;
                        if (map.containsKey(a)) {
                            if (!b.m168a(map.get(a))) {
                                this.f3383b = "key '" + a + "' fails to match: <" + b.m167a() + ">";
                                return false;
                            }
                        } else if (!b.m168a(null)) {
                            this.f3383b = "no key for required mapping '" + a + "' : <" + b.m167a() + ">";
                            return false;
                        }
                    }
                }
                return true;
            } else if (!(obj instanceof JSONObject)) {
                return false;
            } else {
                JSONObject jSONObject = (JSONObject) obj;
                if (this.f3382a != null && this.f3382a.length >= 1) {
                    i = 0;
                    while (i < this.f3382a.length) {
                        a = this.f3382a[i].f119a;
                        b = this.f3382a[i].f120b;
                        try {
                            if (b.m168a(jSONObject.get(a))) {
                                i++;
                            } else {
                                this.f3383b = "key '" + a + "' fails to match: <" + b.m167a() + ">";
                                return false;
                            }
                        } catch (JSONException e) {
                            if (!b.m168a(null)) {
                                this.f3383b = "no key for required mapping '" + a + "' : <" + b.m167a() + ">";
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }

        public String m3698a() {
            if (this.f3383b != null) {
                return this.f3383b;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("object must contain the following key-value schema: {\n");
            for (int i = 0; i < this.f3382a.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(this.f3382a[i].f119a);
                stringBuilder.append(": [");
                stringBuilder.append(this.f3382a[i].f120b.m167a());
                stringBuilder.append("]>");
                if (i < this.f3382a.length - 1) {
                    stringBuilder.append(",\n");
                }
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.g */
    private static class C1009g extends C0326a {
        private Set<String> f3384a;
        private C1008f f3385b;
        private String f3386c;

        public C1009g(C1008f c1008f) {
            this.f3386c = null;
            this.f3385b = c1008f;
            this.f3384a = new HashSet();
            for (C0327k a : this.f3385b.f3382a) {
                this.f3384a.add(a.f119a);
            }
        }

        public boolean m3701a(Object obj) {
            if (!this.f3385b.m3699a(obj)) {
                this.f3386c = this.f3385b.f3383b;
                return false;
            } else if (obj instanceof Map) {
                for (Object next : ((Map) obj).keySet()) {
                    if (!this.f3384a.contains(next)) {
                        this.f3386c = "key '" + next + "' is not allowed in this dictionary";
                        return false;
                    }
                }
                return true;
            } else if (obj instanceof JSONObject) {
                Iterator keys = ((JSONObject) obj).keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    if (!this.f3384a.contains(str)) {
                        this.f3386c = "key '" + str + "' is not allowed in this dictionary";
                        return false;
                    }
                }
                return true;
            } else {
                this.f3386c = "object must be a dictionary";
                return false;
            }
        }

        public String m3700a() {
            if (this.f3386c != null) {
                return this.f3386c;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("object must EXACTLY MATCH the following key-value schema: {\n");
            for (int i = 0; i < this.f3385b.f3382a.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(this.f3385b.f3382a[i].f119a);
                stringBuilder.append(": [");
                stringBuilder.append(this.f3385b.f3382a[i].f120b.m167a());
                stringBuilder.append("]>");
                if (i < this.f3385b.f3382a.length - 1) {
                    stringBuilder.append(",\n");
                }
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.h */
    private static class C1010h extends C0326a {
        private Object[] f3387a;

        public C1010h(Object[] objArr) {
            this.f3387a = objArr;
        }

        public boolean m3703a(Object obj) {
            for (Object obj2 : this.f3387a) {
                if (obj == null) {
                    if (obj2 == null) {
                        return true;
                    }
                } else if (obj.equals(obj2)) {
                    return true;
                }
            }
            return false;
        }

        public String m3702a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("object must equal one of the following: ");
            for (int i = 0; i < this.f3387a.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(this.f3387a[i].toString());
                stringBuilder.append(">");
                if (i < this.f3387a.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.i */
    private static class C1011i extends C0326a {
        private Class<?> f3388a;

        public C1011i(Class<?> cls) {
            this.f3388a = cls;
        }

        public boolean m3705a(Object obj) {
            return this.f3388a.isInstance(obj);
        }

        public String m3704a() {
            return "object must be an instance of " + this.f3388a.getName() + ".";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.j */
    private static class C1012j extends C0326a {
        private C1012j() {
        }

        public boolean m3707a(Object obj) {
            return Integer.class.isInstance(obj) || Long.class.isInstance(obj) || Short.class.isInstance(obj) || Byte.class.isInstance(obj) || BigInteger.class.isInstance(obj) || Integer.TYPE.isInstance(obj) || Long.TYPE.isInstance(obj) || Short.TYPE.isInstance(obj) || Byte.TYPE.isInstance(obj);
        }

        public String m3706a() {
            return "object must be a number w/o decimals (int, long, short, or byte).";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.l */
    private static class C1013l extends C0326a {
        private C1013l() {
        }

        public boolean m3709a(Object obj) {
            return obj == null || obj == JSONObject.NULL;
        }

        public String m3708a() {
            return "object must be null.";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.n */
    private static class C1014n extends C0326a {
        private C1014n() {
        }

        public boolean m3711a(Object obj) {
            return (obj instanceof Number) || Integer.TYPE.isInstance(obj) || Long.TYPE.isInstance(obj) || Short.TYPE.isInstance(obj) || Float.TYPE.isInstance(obj) || Double.TYPE.isInstance(obj) || Byte.TYPE.isInstance(obj);
        }

        public String m3710a() {
            return "object must be a number (primitive type or derived from Number).";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.o */
    private static class C1015o extends C0326a {
        private C0326a[] f3389a;

        public C1015o(C0326a[] c0326aArr) {
            this.f3389a = c0326aArr;
        }

        public boolean m3713a(Object obj) {
            for (C0326a a : this.f3389a) {
                if (a.m168a(obj)) {
                    return true;
                }
            }
            return false;
        }

        public String m3712a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("object must match one of the following: ");
            for (int i = 0; i < this.f3389a.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(this.f3389a[i].m167a());
                stringBuilder.append(">");
                if (i < this.f3389a.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.q */
    private static class C1016q extends C0326a {
        protected String f3390a;
        private C0326a[] f3391b;

        public C1016q(C0326a[] c0326aArr) {
            this.f3390a = null;
            this.f3391b = c0326aArr;
        }

        public boolean m3715a(Object obj) {
            int i = 0;
            while (i < this.f3391b.length) {
                if (this.f3391b[i].m168a(obj)) {
                    i++;
                } else {
                    this.f3390a = "object failed to match: <" + this.f3391b[i].m167a() + ">";
                    return false;
                }
            }
            return true;
        }

        public String m3714a() {
            if (this.f3390a != null) {
                return this.f3390a;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("object must match ALL of the following: ");
            for (int i = 0; i < this.f3391b.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(this.f3391b[i].m167a());
                stringBuilder.append(">");
                if (i < this.f3391b.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.c */
    private static class C1306c extends C1005b {
        private C0326a f4192a;

        public C1306c(C0326a c0326a) {
            super();
            this.f4192a = c0326a;
        }

        public boolean m5022a(Object obj) {
            int i;
            if (obj instanceof List) {
                List list = (List) obj;
                for (i = 0; i < list.size(); i++) {
                    if (!this.f4192a.m168a(list.get(i))) {
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof JSONArray)) {
                return false;
            } else {
                JSONArray jSONArray = (JSONArray) obj;
                for (i = 0; i < jSONArray.length(); i++) {
                    if (!this.f4192a.m168a(jSONArray.opt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }

        public String m5021a() {
            return "object must be an array of objects matching: <" + this.f4192a.m167a() + ">";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.m */
    private static class C1307m extends C1013l {
        private C0326a f4193a;

        public C1307m(C0326a c0326a) {
            super();
            this.f4193a = c0326a;
        }

        public boolean m5024a(Object obj) {
            if (super.m3709a(obj)) {
                return true;
            }
            return this.f4193a.m168a(obj);
        }

        public String m5023a() {
            return "object must be either null or " + this.f4193a.m167a();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.g.p */
    private static class C1308p extends C1011i {
        public C1308p() {
            super(String.class);
        }
    }

    static {
        f121a = C0328g.class.getSimpleName();
        f122b = new C1308p();
        f123c = new C1014n();
        f124d = new C1012j();
        f125e = new C1006d();
        f126f = new C1013l();
    }

    private C0328g() {
    }

    public static C0326a m174a(Class<?> cls) {
        return new C1011i(cls);
    }

    public static C0326a m172a() {
        return f122b;
    }

    public static C0326a m179b() {
        return f123c;
    }

    public static C0326a m183c() {
        return f125e;
    }

    public static C0326a m173a(C0326a c0326a) {
        return new C1307m(c0326a);
    }

    public static C0326a m180b(C0326a c0326a) {
        return new C1306c(c0326a);
    }

    public static C0326a m175a(C0326a... c0326aArr) {
        return new C1015o(c0326aArr);
    }

    public static C0326a m181b(C0326a... c0326aArr) {
        return new C1016q(c0326aArr);
    }

    public static C0326a m177a(Object... objArr) {
        return new C1010h(objArr);
    }

    public static C0327k m178a(String str, C0326a c0326a) {
        return new C0327k(str, c0326a);
    }

    public static C0326a m176a(C0327k... c0327kArr) {
        return new C1008f(c0327kArr);
    }

    public static C0326a m182b(C0327k... c0327kArr) {
        return new C1009g(new C1008f(c0327kArr));
    }

    public static boolean m184c(C0326a c0326a) {
        return (c0326a instanceof C1008f) || (c0326a instanceof C1009g) || (c0326a instanceof C1015o);
    }
}
