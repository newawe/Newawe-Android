package com.startapp.android.publish.p012f.p013a.p017d;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.startapp.android.publish.f.a.d.a */
public class StartAppSDK implements StartAppSDK {
    private final StartAppSDK f4117a;

    public StartAppSDK(StartAppSDK startAppSDK) {
        this.f4117a = startAppSDK;
    }

    public String m4835a(String str) {
        OutputStream gZIPOutputStream;
        String a;
        Throwable th;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(str.getBytes());
                com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
                a = this.f4117a.m2781a(com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m4829a(byteArrayOutputStream.toByteArray()));
                com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
            } catch (Exception e) {
                try {
                    a = StringUtils.EMPTY;
                    com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            gZIPOutputStream = null;
            a = StringUtils.EMPTY;
            com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
            return a;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            gZIPOutputStream = null;
            th = th4;
            com.startapp.android.publish.p012f.p013a.p016c.StartAppSDK.m2778a(gZIPOutputStream);
            throw th;
        }
        return a;
    }
}
