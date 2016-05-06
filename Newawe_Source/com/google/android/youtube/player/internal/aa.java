package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import com.google.android.youtube.player.internal.C0676w.C0675a;

public abstract class aa {
    private static final aa f808a;

    static {
        f808a = m875b();
    }

    public static aa m874a() {
        return f808a;
    }

    private static aa m875b() {
        try {
            return (aa) Class.forName("com.google.android.youtube.api.locallylinked.LocallyLinkedFactory").asSubclass(aa.class).newInstance();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        } catch (Throwable e2) {
            throw new IllegalStateException(e2);
        } catch (ClassNotFoundException e3) {
            return new ac();
        }
    }

    public abstract C1198a m876a(C1199b c1199b, YouTubeThumbnailView youTubeThumbnailView);

    public abstract C1199b m877a(Context context, String str, C0671a c0671a, C0672b c0672b);

    public abstract C0653d m878a(Activity activity, C1199b c1199b, boolean z) throws C0675a;
}
