package com.chartboost.sdk.Libraries;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.e */
public class C0323e {
    private static Map<Integer, C0321a> f107a;
    private static Runnable f108b;

    /* renamed from: com.chartboost.sdk.Libraries.e.1 */
    static class C03201 implements Runnable {
        C03201() {
        }

        public void run() {
            C0323e.f107a.clear();
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.e.a */
    public static class C0321a {
        public static C0321a f99a;
        private static JSONObject f100c;
        private static Map<String, Object> f101d;
        private static JSONArray f102e;
        private static List<?> f103f;
        private Object f104b;

        static {
            f99a = new C0321a(null);
            f100c = null;
            f101d = null;
            f102e = null;
            f103f = null;
        }

        private C0321a(Object obj) {
            this.f104b = obj;
        }

        public static C0321a m121a() {
            return C0321a.m122a(new JSONObject());
        }

        public C0321a m127a(String str) {
            if (this.f104b instanceof JSONObject) {
                return C0321a.m122a(((JSONObject) this.f104b).opt(str));
            }
            if (this.f104b instanceof Map) {
                return C0321a.m122a(((Map) this.f104b).get(str));
            }
            return f99a;
        }

        public boolean m131b() {
            return this.f104b == null || this.f104b == JSONObject.NULL;
        }

        public boolean m134c() {
            return !m131b();
        }

        public boolean m137d() {
            return !TextUtils.isEmpty(m145h());
        }

        public boolean m132b(String str) {
            return m127a(str).m131b();
        }

        public boolean m135c(String str) {
            return m127a(str).m134c();
        }

        public JSONObject m139e() {
            if (this.f104b instanceof JSONObject) {
                return (JSONObject) this.f104b;
            }
            if (!(this.f104b instanceof Map)) {
                return null;
            }
            if (f100c == null) {
                f100c = C0323e.m163a((Map) this.f104b);
            }
            return f100c;
        }

        public Map<String, Object> m141f() {
            if (this.f104b instanceof JSONObject) {
                if (f101d == null) {
                    f101d = C0323e.m161a((JSONObject) this.f104b);
                }
                return f101d;
            } else if (this.f104b instanceof Map) {
                return (Map) this.f104b;
            } else {
                return null;
            }
        }

        public JSONArray m143g() {
            if (this.f104b instanceof JSONArray) {
                return (JSONArray) this.f104b;
            }
            if (!(this.f104b instanceof List)) {
                return null;
            }
            if (f102e == null) {
                f102e = C0323e.m162a((List) this.f104b);
            }
            return f102e;
        }

        public String m145h() {
            if (m131b()) {
                return null;
            }
            return this.f104b instanceof String ? (String) this.f104b : this.f104b.toString();
        }

        public String m136d(String str) {
            return this.f104b instanceof String ? (String) this.f104b : str;
        }

        public double m146i() {
            return m124a(0.0d);
        }

        public double m124a(double d) {
            if (!(this.f104b instanceof String)) {
                return this.f104b instanceof Number ? ((Number) this.f104b).doubleValue() : d;
            } else {
                try {
                    return Double.parseDouble((String) this.f104b);
                } catch (NumberFormatException e) {
                    return d;
                }
            }
        }

        public float m148j() {
            return m125a(0.0f);
        }

        public float m125a(float f) {
            if (!(this.f104b instanceof String)) {
                return this.f104b instanceof Number ? ((Number) this.f104b).floatValue() : f;
            } else {
                try {
                    return Float.parseFloat((String) this.f104b);
                } catch (NumberFormatException e) {
                    return f;
                }
            }
        }

        public int m150k() {
            return m126a(0);
        }

        public int m126a(int i) {
            if (!(this.f104b instanceof String)) {
                return this.f104b instanceof Number ? ((Number) this.f104b).intValue() : i;
            } else {
                try {
                    return Integer.parseInt((String) this.f104b);
                } catch (NumberFormatException e) {
                    return i;
                }
            }
        }

        public long m151l() {
            return m130b(0);
        }

        public long m130b(int i) {
            if (!(this.f104b instanceof String)) {
                return this.f104b instanceof Number ? ((Number) this.f104b).longValue() : (long) i;
            } else {
                try {
                    return Long.parseLong((String) this.f104b);
                } catch (NumberFormatException e) {
                    return (long) i;
                }
            }
        }

        public boolean m152m() {
            return m129a(false);
        }

        public boolean m129a(boolean z) {
            return this.f104b instanceof Boolean ? ((Boolean) this.f104b).booleanValue() : z;
        }

        public boolean equals(Object other) {
            C0321a a = C0321a.m122a(other);
            if (m131b()) {
                return a.m131b();
            }
            if (m139e() != null && a.m139e() != null) {
                return C0331i.m236a(m139e(), a.m139e());
            }
            if (m143g() != null && a.m143g() != null) {
                return C0331i.m235a(m143g(), a.m143g());
            }
            if (this.f104b instanceof String) {
                return this.f104b.equals(a.m145h());
            }
            if (a.f104b instanceof String) {
                return a.f104b.equals(m145h());
            }
            return m153n().equals(a.m153n());
        }

        public Object m153n() {
            return this.f104b;
        }

        public static C0321a m122a(Object obj) {
            if (obj instanceof C0321a) {
                return (C0321a) obj;
            }
            if (obj == null || obj == JSONObject.NULL) {
                return f99a;
            }
            C0321a c0321a = (C0321a) C0323e.f107a.get(Integer.valueOf(obj.hashCode()));
            if (c0321a != null) {
                return c0321a;
            }
            CBUtility.m96e().removeCallbacks(C0323e.f108b);
            CBUtility.m96e().postDelayed(C0323e.f108b, 1000);
            c0321a = new C0321a(obj);
            C0323e.f107a.put(Integer.valueOf(obj.hashCode()), c0321a);
            return c0321a;
        }

        public int m154o() {
            if (this.f104b instanceof JSONArray) {
                return ((JSONArray) this.f104b).length();
            }
            if (this.f104b instanceof List) {
                return ((List) this.f104b).size();
            }
            return 1;
        }

        public C0321a m133c(int i) {
            if (this.f104b instanceof JSONArray) {
                return C0321a.m122a(((JSONArray) this.f104b).opt(i));
            }
            if (!(this.f104b instanceof List)) {
                return i != 0 ? f99a : this;
            } else {
                try {
                    return C0321a.m122a(((List) this.f104b).get(i));
                } catch (IndexOutOfBoundsException e) {
                    return f99a;
                }
            }
        }

        public String m138e(String str) {
            return m127a(str).m145h();
        }

        public int m140f(String str) {
            return m127a(str).m150k();
        }

        public float m142g(String str) {
            return m127a(str).m148j();
        }

        public double m144h(String str) {
            return m127a(str).m146i();
        }

        public long m147i(String str) {
            return m127a(str).m151l();
        }

        public boolean m149j(String str) {
            return m127a(str).m152m();
        }

        public void m128a(String str, Object obj) {
            f100c = null;
            f102e = null;
            f101d = null;
            f103f = null;
            if (obj instanceof C0321a) {
                obj = ((C0321a) obj).m153n();
            }
            if (this.f104b instanceof JSONObject) {
                try {
                    ((JSONObject) this.f104b).put(str, obj);
                } catch (Throwable e) {
                    CBLogging.m78b(this, "Error updating balances dictionary.", e);
                }
            } else if (this.f104b instanceof Map) {
                try {
                    ((Map) this.f104b).put(str, obj);
                } catch (Throwable e2) {
                    CBLogging.m78b(this, "Error updating balances dictionary.", e2);
                }
            }
        }

        public static C0321a m123k(String str) {
            if (str == null) {
                CBLogging.m81d("CBJSON", "null passed when creating new JSON object");
                return f99a;
            }
            if (str != null) {
                try {
                    if (str.trim().startsWith("[")) {
                        return C0321a.m122a(new JSONArray(str));
                    }
                } catch (Throwable e) {
                    CBLogging.m78b("CBJSON", "error creating new json object", e);
                    return f99a;
                }
            }
            return C0321a.m122a(new JSONObject(str));
        }

        public String toString() {
            if (m139e() != null) {
                return m139e().toString();
            }
            if (m143g() != null) {
                return m143g().toString();
            }
            if (this.f104b != null) {
                return this.f104b.toString();
            }
            return "null";
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.e.b */
    public static class C0322b {
        private String f105a;
        private Object f106b;

        public C0322b(String str, Object obj) {
            this.f105a = str;
            if (obj instanceof C0321a) {
                this.f106b = ((C0321a) obj).m153n();
            } else {
                this.f106b = obj;
            }
        }
    }

    static {
        f107a = Collections.synchronizedMap(new HashMap());
        f108b = new C03201();
    }

    public static C0321a m157a(C0322b... c0322bArr) {
        C0321a a = C0321a.m121a();
        for (int i = 0; i < c0322bArr.length; i++) {
            a.m128a(c0322bArr[i].f105a, c0322bArr[i].f106b);
        }
        return a;
    }

    public static C0322b m158a(String str, Object obj) {
        return new C0322b(str, obj);
    }

    public static List<?> m159a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<?> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONObject) {
                    obj = C0323e.m161a((JSONObject) obj);
                } else if (obj instanceof JSONArray) {
                    obj = C0323e.m159a((JSONArray) obj);
                } else if (obj.equals(JSONObject.NULL)) {
                    obj = null;
                }
                arrayList.add(obj);
            } catch (Throwable e) {
                CBLogging.m78b("CBJSON", "error converting json", e);
            }
        }
        return arrayList;
    }

    public static Map<String, Object> m161a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Map<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                Object obj = jSONObject.get(str);
                if (obj instanceof JSONObject) {
                    obj = C0323e.m161a((JSONObject) obj);
                } else if (obj instanceof JSONArray) {
                    obj = C0323e.m159a((JSONArray) obj);
                } else if (obj.equals(JSONObject.NULL)) {
                    obj = null;
                }
                hashMap.put(str, obj);
            } catch (Throwable e) {
                CBLogging.m78b("CBJSON", "error converting json", e);
            }
        }
        return hashMap;
    }

    public static JSONArray m162a(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                Object obj = list.get(i);
                if (obj instanceof Map) {
                    obj = C0323e.m163a((Map) obj);
                } else if (obj instanceof List) {
                    obj = C0323e.m162a((List) obj);
                } else if (obj == null) {
                    obj = JSONObject.NULL;
                }
                jSONArray.put(obj);
            } catch (Throwable e) {
                CBLogging.m78b("CBJSON", "error converting json", e);
            }
        }
        return jSONArray;
    }

    public static JSONObject m163a(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            String obj = entry.getKey().toString();
            Object value = entry.getValue();
            try {
                if (value instanceof Map) {
                    value = C0323e.m163a((Map) value);
                } else if (value instanceof List) {
                    value = C0323e.m162a((List) value);
                } else if (value == null) {
                    value = JSONObject.NULL;
                }
                jSONObject.put(obj, value);
            } catch (Throwable e) {
                CBLogging.m78b("CBJSON", "error converting json", e);
            }
        }
        return jSONObject;
    }
}
