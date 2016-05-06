package com.startapp.android.publish.p008b;

import android.content.Context;
import android.util.Log;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.reflect.TypeToken;
import com.startapp.android.publish.model.MetaData;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.startapp.android.publish.b.e */
public class StartAppSDK {
    private static StartAppSDK f2718a;
    private Queue<StartAppSDK> f2719b;

    /* renamed from: com.startapp.android.publish.b.e.1 */
    class StartAppSDK implements com.startapp.android.publish.video.StartAppSDK.StartAppSDK {
        final /* synthetic */ com.startapp.android.publish.video.StartAppSDK.StartAppSDK f4064a;
        final /* synthetic */ StartAppSDK f4065b;
        final /* synthetic */ Context f4066c;
        final /* synthetic */ StartAppSDK f4067d;

        StartAppSDK(StartAppSDK startAppSDK, com.startapp.android.publish.video.StartAppSDK.StartAppSDK startAppSDK2, StartAppSDK startAppSDK3, Context context) {
            this.f4067d = startAppSDK;
            this.f4064a = startAppSDK2;
            this.f4065b = startAppSDK3;
            this.f4066c = context;
        }

        public void m4754a(String str) {
            if (this.f4064a != null) {
                this.f4064a.m3591a(str);
            }
            if (str != null) {
                this.f4065b.m2616a(System.currentTimeMillis());
                this.f4065b.m2617a(str);
                this.f4067d.f2719b.add(this.f4065b);
                this.f4067d.m2623b(this.f4066c);
                com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoAdCacheManager", 3, "Added " + this.f4065b.m2615a() + " to cachedVideoAds. Size = " + this.f4067d.f2719b.size());
            }
        }
    }

    /* renamed from: com.startapp.android.publish.b.e.2 */
    class StartAppSDK extends TypeToken<LinkedList<StartAppSDK>> {
        final /* synthetic */ StartAppSDK f4068a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f4068a = startAppSDK;
        }
    }

    static {
        f2718a = new StartAppSDK();
    }

    private StartAppSDK() {
    }

    public void m2625a(Context context, String str, com.startapp.android.publish.video.StartAppSDK.StartAppSDK startAppSDK) {
        m2624b(context, str, startAppSDK);
    }

    private void m2624b(Context context, String str, com.startapp.android.publish.video.StartAppSDK.StartAppSDK startAppSDK) {
        Throwable e;
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoAdCacheManager", 3, "Full cache: " + str);
        m2621a(context);
        String str2 = StringUtils.EMPTY;
        Object url;
        try {
            url = new URL(str);
            try {
                String str3 = url.getHost() + url.getPath().replace("/", "_");
                try {
                    str2 = str3.substring(0, str3.lastIndexOf(46));
                    str2 = new String(com.startapp.android.publish.p022h.StartAppSDK.m2891a(MessageDigest.getInstance("MD5").digest(str2.getBytes()), 0)).replaceAll("[^a-zA-Z0-9]+", "_") + str3.substring(str3.lastIndexOf(46));
                } catch (Throwable e2) {
                    Log.e("VideoAdCacheManager", "Error applying MD5 digest to filename " + str3, e2);
                    str2 = str3;
                }
                StartAppSDK startAppSDK2 = new StartAppSDK(str2);
                com.startapp.android.publish.video.StartAppSDK.StartAppSDK startAppSDK3 = new StartAppSDK(this, startAppSDK, startAppSDK2, context);
                if (this.f2719b.contains(startAppSDK2)) {
                    this.f2719b.remove(startAppSDK2);
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoAdCacheManager", 3, "cachedVideoAds already contained " + startAppSDK2.m2615a() + " - removed. Size = " + this.f2719b.size());
                }
                m2626a(MetaData.getInstance().getVideoConfig().getMaxCachedVideos() - 1);
                new com.startapp.android.publish.video.StartAppSDK(context, url, str2, startAppSDK3).m3606a();
            } catch (MalformedURLException e3) {
                e2 = e3;
                Log.e("VideoAdCacheManager", "Malformed url " + url, e2);
                if (startAppSDK != null) {
                    startAppSDK.m3591a(null);
                }
            }
        } catch (MalformedURLException e4) {
            e2 = e4;
            url = null;
            Log.e("VideoAdCacheManager", "Malformed url " + url, e2);
            if (startAppSDK != null) {
                startAppSDK.m3591a(null);
            }
        }
    }

    protected boolean m2626a(int i) {
        Iterator it = this.f2719b.iterator();
        boolean z = false;
        while (it.hasNext() && this.f2719b.size() > i) {
            StartAppSDK startAppSDK = (StartAppSDK) it.next();
            if (!StartAppSDK.m2586a().m2597a(startAppSDK.m2618b())) {
                z = true;
                it.remove();
                if (startAppSDK.m2618b() != null) {
                    new File(startAppSDK.m2618b()).delete();
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoAdCacheManager", 3, "cachedVideoAds reached the maximum of " + i + " videos - removed " + startAppSDK.m2615a() + " Size = " + this.f2719b.size());
                }
            }
            z = z;
        }
        return z;
    }

    private void m2621a(Context context) {
        if (this.f2719b == null) {
            this.f2719b = (Queue) new Gson().fromJson(com.startapp.android.publish.p022h.StartAppSDK.m2907a(context, "cachedVideoAds", StringUtils.EMPTY), new StartAppSDK(this).getType());
            if (this.f2719b == null) {
                this.f2719b = new LinkedList();
            }
            if (m2626a(MetaData.getInstance().getVideoConfig().getMaxCachedVideos())) {
                m2623b(context);
            }
        }
    }

    private void m2623b(Context context) {
        com.startapp.android.publish.p022h.StartAppSDK.m2912b(context, "cachedVideoAds", new Gson().toJson(this.f2719b));
    }

    public static StartAppSDK m2619a() {
        return f2718a;
    }
}
