package com.google.android.youtube.player.internal;

import android.util.Log;

/* renamed from: com.google.android.youtube.player.internal.y */
public final class C0678y {
    public static void m970a(String str, Throwable th) {
        Log.e("YouTubeAndroidPlayerAPI", str, th);
    }

    public static void m971a(String str, Object... objArr) {
        Log.w("YouTubeAndroidPlayerAPI", String.format(str, objArr));
    }
}
