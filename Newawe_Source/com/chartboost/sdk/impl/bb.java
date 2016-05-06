package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0356d.C0355b;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.C0343a.C0342e;
import com.chartboost.sdk.Model.CBError.CBClickError;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;

public final class bb {
    private static bb f556c;
    private C0410a f557a;
    private C0343a f558b;

    /* renamed from: com.chartboost.sdk.impl.bb.1 */
    class C04091 implements Runnable {
        final /* synthetic */ String f552a;
        final /* synthetic */ Activity f553b;
        final /* synthetic */ C0355b f554c;
        final /* synthetic */ bb f555d;

        /* renamed from: com.chartboost.sdk.impl.bb.1.1 */
        class C04081 implements Runnable {
            final /* synthetic */ String f550a;
            final /* synthetic */ C04091 f551b;

            C04081(C04091 c04091, String str) {
                this.f551b = c04091;
                this.f550a = str;
            }

            public void run() {
                this.f551b.f555d.m636a(this.f550a, this.f551b.f553b, this.f551b.f554c);
            }
        }

        C04091(bb bbVar, String str, Activity activity, C0355b c0355b) {
            this.f555d = bbVar;
            this.f552a = str;
            this.f553b = activity;
            this.f554c = c0355b;
        }

        public void run() {
            String str;
            Throwable th;
            String str2 = this.f552a;
            if (ay.m543a().m551c()) {
                HttpURLConnection httpURLConnection = null;
                try {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f552a).openConnection();
                    try {
                        httpURLConnection2.setInstanceFollowRedirects(false);
                        httpURLConnection2.setConnectTimeout(30000);
                        httpURLConnection2.setReadTimeout(30000);
                        String headerField = httpURLConnection2.getHeaderField(HttpHeaders.LOCATION);
                        if (headerField != null) {
                            str2 = headerField;
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                            str = str2;
                            m632a(str);
                        }
                    } catch (Throwable e) {
                        Throwable th2 = e;
                        httpURLConnection = httpURLConnection2;
                        th = th2;
                        try {
                            CBLogging.m78b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                                str = str2;
                                m632a(str);
                            }
                            str = str2;
                            m632a(str);
                        } catch (Throwable th3) {
                            th = th3;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        httpURLConnection = httpURLConnection2;
                        th = th4;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    th = e2;
                    CBLogging.m78b("CBURLOpener", "Exception raised while opening a HTTP Conection", th);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        str = str2;
                        m632a(str);
                    }
                    str = str2;
                    m632a(str);
                }
            }
            str = str2;
            m632a(str);
        }

        public void m632a(String str) {
            Runnable c04081 = new C04081(this, str);
            if (this.f553b != null) {
                this.f553b.runOnUiThread(c04081);
            } else {
                CBUtility.m96e().post(c04081);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bb.a */
    public interface C0410a {
        void m633a(C0343a c0343a, boolean z, String str, CBClickError cBClickError, C0355b c0355b);
    }

    public static bb m634a(C0410a c0410a) {
        if (f556c == null) {
            f556c = new bb(c0410a);
        }
        return f556c;
    }

    private bb(C0410a c0410a) {
        this.f557a = c0410a;
    }

    public void m638a(C0343a c0343a, String str, Activity activity, C0355b c0355b) {
        this.f558b = c0343a;
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                if (this.f557a != null) {
                    this.f557a.m633a(c0343a, false, str, CBClickError.URI_INVALID, c0355b);
                }
            } else if (scheme.equals(HttpHost.DEFAULT_SCHEME_NAME) || scheme.equals("https")) {
                ax.m541a().execute(new C04091(this, str, activity, c0355b));
            } else {
                m636a(str, activity, c0355b);
            }
        } catch (URISyntaxException e) {
            if (this.f557a != null) {
                this.f557a.m633a(c0343a, false, str, CBClickError.URI_INVALID, c0355b);
            }
        }
    }

    private void m636a(String str, Context context, C0355b c0355b) {
        Intent intent;
        String str2;
        if (this.f558b != null && this.f558b.m265a()) {
            this.f558b.f227c = C0342e.NONE;
        }
        if (context == null) {
            context = C0351c.m378y();
        }
        if (context != null) {
            try {
                intent = new Intent("android.intent.action.VIEW");
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                intent.setData(Uri.parse(str));
                context.startActivity(intent);
                str2 = str;
            } catch (Exception e) {
                if (str.startsWith("market://")) {
                    try {
                        str = "http://market.android.com/" + str.substring(9);
                        intent = new Intent("android.intent.action.VIEW");
                        if (!(context instanceof Activity)) {
                            intent.addFlags(268435456);
                        }
                        intent.setData(Uri.parse(str));
                        context.startActivity(intent);
                        str2 = str;
                    } catch (Throwable e2) {
                        str2 = str;
                        CBLogging.m78b("CBURLOpener", "Exception raised openeing an inavld playstore URL", e2);
                        if (this.f557a != null) {
                            this.f557a.m633a(this.f558b, false, str2, CBClickError.URI_UNRECOGNIZED, c0355b);
                            return;
                        }
                        return;
                    }
                }
                if (this.f557a != null) {
                    this.f557a.m633a(this.f558b, false, str, CBClickError.URI_UNRECOGNIZED, c0355b);
                }
                str2 = str;
            }
            if (this.f557a != null) {
                this.f557a.m633a(this.f558b, true, str2, null, c0355b);
            }
        } else if (this.f557a != null) {
            this.f557a.m633a(this.f558b, false, str, CBClickError.NO_HOST_ACTIVITY, c0355b);
        }
    }

    public static boolean m637a(String str) {
        try {
            Context y = C0351c.m378y();
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(y instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            if (y.getPackageManager().queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            CBLogging.m78b("CBURLOpener", "Cannot open URL", e);
            return false;
        }
    }
}
