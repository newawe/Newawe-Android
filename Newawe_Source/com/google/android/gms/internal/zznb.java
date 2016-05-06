package com.google.android.gms.internal;

import android.text.TextUtils;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zznb {
    private static final Pattern zzaoi;
    private static final Pattern zzaoj;

    static {
        zzaoi = Pattern.compile("\\\\.");
        zzaoj = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static String zzcU(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zzaoj.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case ConnectionResult.INTERNAL_ERROR /*8*/:
                    matcher.appendReplacement(stringBuffer, "\\\\b");
                    break;
                case ConnectionResult.SERVICE_INVALID /*9*/:
                    matcher.appendReplacement(stringBuffer, "\\\\t");
                    break;
                case MetaData.DEFAULT_MAX_ADS /*10*/:
                    matcher.appendReplacement(stringBuffer, "\\\\n");
                    break;
                case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                    break;
                case ConnectionResult.CANCELED /*13*/:
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                    break;
                case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                    break;
                case Tokens.EXPRTOKEN_NUMBER /*47*/:
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                    break;
                case C0302R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                    break;
                default:
                    break;
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static boolean zze(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) obj;
            JSONObject jSONObject2 = (JSONObject) obj2;
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!jSONObject2.has(str)) {
                    return false;
                }
                try {
                    if (!zze(jSONObject.get(str), jSONObject2.get(str))) {
                        return false;
                    }
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } else if (!(obj instanceof JSONArray) || !(obj2 instanceof JSONArray)) {
            return obj.equals(obj2);
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            JSONArray jSONArray2 = (JSONArray) obj2;
            if (jSONArray.length() != jSONArray2.length()) {
                return false;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    if (!zze(jSONArray.get(i), jSONArray2.get(i))) {
                        return false;
                    }
                    i++;
                } catch (JSONException e2) {
                    return false;
                }
            }
            return true;
        }
    }
}
