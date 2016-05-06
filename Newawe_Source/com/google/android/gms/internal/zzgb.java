package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.protocol.HTTP;

@zzhb
public class zzgb implements zzfz {
    private final Context mContext;
    final Set<WebView> zzFr;

    /* renamed from: com.google.android.gms.internal.zzgb.1 */
    class C06001 implements Runnable {
        final /* synthetic */ String zzFs;
        final /* synthetic */ String zzFt;
        final /* synthetic */ zzgb zzFu;

        /* renamed from: com.google.android.gms.internal.zzgb.1.1 */
        class C05991 extends WebViewClient {
            final /* synthetic */ C06001 zzFv;
            final /* synthetic */ WebView zztj;

            C05991(C06001 c06001, WebView webView) {
                this.zzFv = c06001;
                this.zztj = webView;
            }

            public void onPageFinished(WebView view, String url) {
                zzb.zzaI("Loading assets have finished");
                this.zzFv.zzFu.zzFr.remove(this.zztj);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                zzb.zzaK("Loading assets have failed.");
                this.zzFv.zzFu.zzFr.remove(this.zztj);
            }
        }

        C06001(zzgb com_google_android_gms_internal_zzgb, String str, String str2) {
            this.zzFu = com_google_android_gms_internal_zzgb;
            this.zzFs = str;
            this.zzFt = str2;
        }

        public void run() {
            WebView zzfR = this.zzFu.zzfR();
            zzfR.setWebViewClient(new C05991(this, zzfR));
            this.zzFu.zzFr.add(zzfR);
            zzfR.loadDataWithBaseURL(this.zzFs, this.zzFt, "text/html", HTTP.UTF_8, null);
            zzb.zzaI("Fetching assets finished.");
        }
    }

    public zzgb(Context context) {
        this.zzFr = Collections.synchronizedSet(new HashSet());
        this.mContext = context;
    }

    public void zza(String str, String str2, String str3) {
        zzb.zzaI("Fetching assets for the given html");
        zzir.zzMc.post(new C06001(this, str2, str3));
    }

    public WebView zzfR() {
        WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}
