package com.inmobi.commons.core.p002b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.inmobi.commons.p000a.SdkContext;
import java.util.HashMap;

/* renamed from: com.inmobi.commons.core.b.c */
public final class KeyValueStore {
    private static HashMap<String, KeyValueStore> f1160a;
    private static final Object f1161b;
    private SharedPreferences f1162c;

    static {
        f1160a = new HashMap();
        f1161b = new Object();
    }

    private KeyValueStore(Context context, String str) {
        this.f1162c = context.getSharedPreferences(str, 0);
    }

    public static String m1285a(String str) {
        return "com.im.keyValueStore." + str;
    }

    public static KeyValueStore m1284a(Context context, String str) {
        String a = KeyValueStore.m1285a(str);
        if (f1160a.containsKey(a)) {
            return (KeyValueStore) f1160a.get(a);
        }
        synchronized (f1161b) {
            if (f1160a.containsKey(a)) {
                KeyValueStore keyValueStore = (KeyValueStore) f1160a.get(a);
                return keyValueStore;
            }
            keyValueStore = new KeyValueStore(context, a);
            f1160a.put(a, keyValueStore);
            return keyValueStore;
        }
    }

    public static KeyValueStore m1286b(String str) {
        return KeyValueStore.m1284a(SdkContext.m1258b(), str);
    }

    public void m1288a(String str, String str2) {
        Editor edit = this.f1162c.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String m1291b(String str, String str2) {
        return this.f1162c.getString(str, str2);
    }

    public void m1287a(String str, long j) {
        Editor edit = this.f1162c.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public long m1290b(String str, long j) {
        return this.f1162c.getLong(str, j);
    }

    public void m1289a(String str, boolean z) {
        Editor edit = this.f1162c.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public boolean m1292b(String str, boolean z) {
        return this.f1162c.getBoolean(str, z);
    }
}
