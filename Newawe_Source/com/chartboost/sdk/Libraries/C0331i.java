package com.chartboost.sdk.Libraries;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.i */
public final class C0331i {
    private static final String f176a;

    static {
        f176a = C0331i.class.getSimpleName();
    }

    private C0331i() {
    }

    public static boolean m235a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray.length() != jSONArray2.length() && !C0331i.m237b(jSONArray.toString(), jSONArray2.toString())) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            Object opt = jSONArray.opt(i);
            Object opt2 = jSONArray2.opt(i);
            if (!C0331i.m234a(opt.getClass(), opt2.getClass()) && (!Number.class.isInstance(opt) || !Number.class.isInstance(opt2))) {
                return false;
            }
            if (opt instanceof JSONObject) {
                if (!C0331i.m236a((JSONObject) opt, (JSONObject) opt2)) {
                    return false;
                }
            } else if (opt instanceof JSONArray) {
                if (!C0331i.m235a((JSONArray) opt, (JSONArray) opt2)) {
                    return false;
                }
            } else if (!C0331i.m237b(opt, opt2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean m234a(Object obj, Object obj2) {
        return obj == obj2;
    }

    public static boolean m236a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject.length() != jSONObject2.length()) {
            try {
                if (!C0331i.m237b(jSONObject.toString(2), jSONObject2.toString(2))) {
                    return false;
                }
            } catch (JSONException e) {
                return false;
            }
        }
        JSONArray names = jSONObject.names();
        if (names == null && jSONObject2.names() == null) {
            return true;
        }
        for (int i = 0; i < names.length(); i++) {
            String optString = names.optString(i);
            Object opt = jSONObject.opt(optString);
            Object opt2 = jSONObject2.opt(optString);
            if (C0331i.m233a(opt) && !C0331i.m233a(opt2)) {
                return false;
            }
            if (!C0331i.m234a(opt.getClass(), opt2.getClass()) && (!Number.class.isInstance(opt) || !Number.class.isInstance(opt2))) {
                return false;
            }
            if (opt instanceof JSONObject) {
                if (!C0331i.m236a((JSONObject) opt, (JSONObject) opt2)) {
                    return false;
                }
            } else if (opt instanceof JSONArray) {
                if (!C0331i.m235a((JSONArray) opt, (JSONArray) opt2)) {
                    return false;
                }
            } else if (!C0331i.m237b(opt, opt2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean m233a(Object obj) {
        return (obj == null || obj == JSONObject.NULL) ? false : true;
    }

    private static BigDecimal m232a(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            return new BigDecimal(number.longValue());
        }
        if ((number instanceof Float) || (number instanceof Double)) {
            return new BigDecimal(number.doubleValue());
        }
        try {
            return new BigDecimal(number.toString());
        } catch (Throwable e) {
            CBLogging.m78b(f176a, "The given number (\"" + number + "\" of class " + number.getClass().getName() + ") does not have a parsable string representation", e);
            return null;
        }
    }

    public static boolean m237b(Object obj, Object obj2) {
        if (obj == null || obj == JSONObject.NULL) {
            boolean z = obj2 == null || obj2 == JSONObject.NULL;
            return z;
        }
        if (Number.class.isInstance(obj) && Number.class.isInstance(obj2)) {
            try {
                if (C0331i.m232a((Number) obj).compareTo(C0331i.m232a((Number) obj2)) != 0) {
                    return false;
                }
                return true;
            } catch (RuntimeException e) {
                CBLogging.m77b(f176a, "Error comparing big decimal values");
            }
        }
        return obj.equals(obj2);
    }
}
