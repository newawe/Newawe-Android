package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import com.google.android.youtube.player.internal.C0676w.C0675a;

public final class ac extends aa {
    public final C1198a m4167a(C1199b c1199b, YouTubeThumbnailView youTubeThumbnailView) {
        return new C1325p(c1199b, youTubeThumbnailView);
    }

    public final C1199b m4168a(Context context, String str, C0671a c0671a, C0672b c0672b) {
        return new C1323o(context, str, context.getPackageName(), C0679z.m981d(context), c0671a, c0672b);
    }

    public final C0653d m4169a(Activity activity, C1199b c1199b, boolean z) throws C0675a {
        return C0676w.m967a(activity, c1199b.m4170a(), z);
    }
}
