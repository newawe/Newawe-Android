package rrrrrr;

import com.immersion.hapticmediasdk.HapticContentSDK;
import com.immersion.hapticmediasdk.MediaPlaybackSDK;
import com.immersion.hapticmediasdk.utils.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpHead;

public class rrccrr implements Runnable {
    private static final String f3350a = "ValidateURL";
    public static int f3351b044A044A044A044A044A = 2;
    public static int f3352b044A044A044A044A = 24;
    public static int f3353b044A044A044A044A = 1;
    private URL f3354b;
    public final /* synthetic */ MediaPlaybackSDK f3355b042504250425;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public rrccrr(com.immersion.hapticmediasdk.MediaPlaybackSDK r5, java.lang.String r6) throws java.net.MalformedURLException {
        /*
        r4 = this;
        r3 = 1;
        r0 = 0;
        r1 = -1;
        r4.f3355b042504250425 = r5;
    L_0x0005:
        r2 = new int[r1];	 Catch:{ Exception -> 0x0024 }
        goto L_0x0005;
    L_0x0008:
        r0 = move-exception;
        r4.<init>();
        r0 = new java.net.URL;
    L_0x000e:
        switch(r3) {
            case 0: goto L_0x000e;
            case 1: goto L_0x0015;
            default: goto L_0x0011;
        };
    L_0x0011:
        switch(r3) {
            case 0: goto L_0x000e;
            case 1: goto L_0x0015;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0011;
    L_0x0015:
        r0.<init>(r6);
        r4.f3354b = r0;
        return;
    L_0x001b:
        r0.length();	 Catch:{ Exception -> 0x001f }
        goto L_0x001b;
    L_0x001f:
        r1 = move-exception;
    L_0x0020:
        r0.length();	 Catch:{ Exception -> 0x0008 }
        goto L_0x0020;
    L_0x0024:
        r1 = move-exception;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: rrrrrr.rrccrr.<init>(com.immersion.hapticmediasdk.MediaPlaybackSDK, java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3676a(int r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.f3355b042504250425;	 Catch:{ all -> 0x0014 }
        com.immersion.hapticmediasdk.MediaPlaybackSDK.m4293b043B043B(r0, r2);	 Catch:{ all -> 0x0014 }
        r1.notifyAll();	 Catch:{ all -> 0x0014 }
    L_0x0009:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0012;
            case 1: goto L_0x0009;
            default: goto L_0x000d;
        };	 Catch:{ all -> 0x0014 }
    L_0x000d:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0009;
            case 1: goto L_0x0012;
            default: goto L_0x0011;
        };	 Catch:{ all -> 0x0014 }
    L_0x0011:
        goto L_0x000d;
    L_0x0012:
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        return;
    L_0x0014:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rrrrrr.rrccrr.a(int):void");
    }

    public static int m3677b044A044A044A044A() {
        return 6;
    }

    public void run() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.f3354b.openConnection();
            httpURLConnection.setConnectTimeout(HapticContentSDK.f857b04440444044404440444);
            httpURLConnection.setReadTimeout(HapticContentSDK.f857b04440444044404440444);
            httpURLConnection.setUseCaches(false);
            String str = HttpHead.METHOD_NAME;
            int i = f3352b044A044A044A044A;
            switch ((i * (f3353b044A044A044A044A + i)) % f3351b044A044A044A044A044A) {
                case DurationDV.DURATION_TYPE /*0*/:
                    break;
                default:
                    f3352b044A044A044A044A = 0;
                    f3353b044A044A044A044A = m3677b044A044A044A044A();
                    break;
            }
            httpURLConnection.setRequestMethod(str);
            int responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            Log.m1114e(f3350a, e.getMessage());
            try {
            } catch (Exception e2) {
                throw e2;
            }
        } finally {
            m3676a(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
