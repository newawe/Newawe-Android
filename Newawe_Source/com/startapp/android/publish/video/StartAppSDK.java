package com.startapp.android.publish.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.net.URL;

/* renamed from: com.startapp.android.publish.video.a */
public class StartAppSDK {
    private Context f3268a;
    private URL f3269b;
    private String f3270c;
    private StartAppSDK f3271d;

    /* renamed from: com.startapp.android.publish.video.a.1 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3256a;

        /* renamed from: com.startapp.android.publish.video.a.1.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ String f3254a;
            final /* synthetic */ StartAppSDK f3255b;

            StartAppSDK(StartAppSDK startAppSDK, String str) {
                this.f3255b = startAppSDK;
                this.f3254a = str;
            }

            public void run() {
                if (this.f3255b.f3256a.f3271d != null) {
                    this.f3255b.f3256a.f3271d.m3591a(this.f3254a);
                }
            }
        }

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3256a = startAppSDK;
        }

        public void run() {
            Process.setThreadPriority(10);
            new Handler(Looper.getMainLooper()).post(new StartAppSDK(this, StartAppSDK.m3633a(this.f3256a.f3268a, this.f3256a.f3269b, this.f3256a.f3270c)));
        }
    }

    /* renamed from: com.startapp.android.publish.video.a.a */
    public interface StartAppSDK {
        void m3591a(String str);
    }

    public StartAppSDK(Context context, URL url, String str, StartAppSDK startAppSDK) {
        this.f3268a = context;
        this.f3269b = url;
        this.f3270c = str;
        this.f3271d = startAppSDK;
    }

    public void m3606a() {
        new Thread(new StartAppSDK(this)).start();
    }
}
