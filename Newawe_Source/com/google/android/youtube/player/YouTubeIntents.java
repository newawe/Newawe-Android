package com.google.android.youtube.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.youtube.player.internal.C0679z;
import java.util.List;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public final class YouTubeIntents {
    private YouTubeIntents() {
    }

    static Intent m861a(Intent intent, Context context) {
        intent.putExtra("app_package", context.getPackageName()).putExtra("app_version", C0679z.m981d(context)).putExtra("client_library_version", C0679z.m973a());
        return intent;
    }

    private static Uri m862a(String str) {
        String valueOf = String.valueOf("https://www.youtube.com/playlist?list=");
        String valueOf2 = String.valueOf(str);
        return Uri.parse(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private static String m863a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return C0679z.m979b(packageManager) ? "com.google.android.youtube.tv" : C0679z.m976a(packageManager) ? "com.google.android.youtube.googletv" : "com.google.android.youtube";
    }

    private static boolean m864a(Context context, Intent intent) {
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT);
        return (queryIntentActivities == null || queryIntentActivities.isEmpty()) ? false : true;
    }

    private static boolean m865a(Context context, Uri uri) {
        return m864a(context, new Intent("android.intent.action.VIEW", uri).setPackage(m863a(context)));
    }

    private static Intent m866b(Context context, Uri uri) {
        return m861a(new Intent("android.intent.action.VIEW", uri).setPackage(m863a(context)), context);
    }

    public static boolean canResolveChannelIntent(Context context) {
        return m865a(context, Uri.parse("https://www.youtube.com/channel/"));
    }

    public static boolean canResolveOpenPlaylistIntent(Context context) {
        return m865a(context, Uri.parse("https://www.youtube.com/playlist?list="));
    }

    public static boolean canResolvePlayPlaylistIntent(Context context) {
        int installedYouTubeVersionCode = getInstalledYouTubeVersionCode(context);
        boolean z = C0679z.m976a(context.getPackageManager()) ? installedYouTubeVersionCode >= 4700 : installedYouTubeVersionCode >= 4000;
        return z && canResolveOpenPlaylistIntent(context);
    }

    public static boolean canResolvePlayVideoIntent(Context context) {
        return m865a(context, Uri.parse("https://www.youtube.com/watch?v="));
    }

    public static boolean canResolvePlayVideoIntentWithOptions(Context context) {
        int installedYouTubeVersionCode = getInstalledYouTubeVersionCode(context);
        PackageManager packageManager = context.getPackageManager();
        boolean z = C0679z.m979b(packageManager) ? true : C0679z.m976a(packageManager) ? installedYouTubeVersionCode >= ASContentModel.AS_UNBOUNDED : installedYouTubeVersionCode >= 3300;
        return z && canResolvePlayVideoIntent(context);
    }

    public static boolean canResolveSearchIntent(Context context) {
        return (!C0679z.m976a(context.getPackageManager()) || getInstalledYouTubeVersionCode(context) >= 4700) ? m864a(context, new Intent("android.intent.action.SEARCH").setPackage(m863a(context))) : false;
    }

    public static boolean canResolveUploadIntent(Context context) {
        return m864a(context, new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(m863a(context)).setType("video/*"));
    }

    public static boolean canResolveUserIntent(Context context) {
        return m865a(context, Uri.parse("https://www.youtube.com/user/"));
    }

    public static Intent createChannelIntent(Context context, String str) {
        String valueOf = String.valueOf("https://www.youtube.com/channel/");
        String valueOf2 = String.valueOf(str);
        return m866b(context, Uri.parse(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
    }

    public static Intent createOpenPlaylistIntent(Context context, String str) {
        return m861a(m866b(context, m862a(str)), context);
    }

    public static Intent createPlayPlaylistIntent(Context context, String str) {
        return m861a(m866b(context, m862a(str).buildUpon().appendQueryParameter("playnext", SchemaSymbols.ATTVAL_TRUE_1).build()), context);
    }

    public static Intent createPlayVideoIntent(Context context, String str) {
        return createPlayVideoIntentWithOptions(context, str, false, false);
    }

    public static Intent createPlayVideoIntentWithOptions(Context context, String str, boolean z, boolean z2) {
        String valueOf = String.valueOf("https://www.youtube.com/watch?v=");
        String valueOf2 = String.valueOf(str);
        return m866b(context, Uri.parse(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))).putExtra("force_fullscreen", z).putExtra("finish_on_ended", z2);
    }

    public static Intent createSearchIntent(Context context, String str) {
        return m861a(new Intent("android.intent.action.SEARCH").setPackage(m863a(context)).putExtra("query", str), context);
    }

    public static Intent createUploadIntent(Context context, Uri uri) throws IllegalArgumentException {
        if (uri == null) {
            throw new IllegalArgumentException("videoUri parameter cannot be null.");
        } else if (uri.toString().startsWith("content://media/")) {
            return m861a(new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(m863a(context)).setDataAndType(uri, "video/*"), context);
        } else {
            throw new IllegalArgumentException("videoUri parameter must be a URI pointing to a valid video file. It must begin with \"content://media/\"");
        }
    }

    public static Intent createUserIntent(Context context, String str) {
        String valueOf = String.valueOf("https://www.youtube.com/user/");
        String valueOf2 = String.valueOf(str);
        return m866b(context, Uri.parse(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
    }

    public static int getInstalledYouTubeVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m863a(context), 0).versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    public static String getInstalledYouTubeVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m863a(context), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean isYouTubeInstalled(Context context) {
        try {
            context.getPackageManager().getApplicationInfo(m863a(context), 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
