package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout;
import com.Newawe.storage.DatabaseOpenHelper;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.ads.AdConfig.AdConfig;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.rendering.InMobiAdActivity.C0697a;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.mraid.DownloadTask;
import com.inmobi.rendering.mraid.ExpandProperties;
import com.inmobi.rendering.mraid.MraidExpandProcessor;
import com.inmobi.rendering.mraid.MraidJsDao;
import com.inmobi.rendering.mraid.MraidJsFetcher;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.MraidMediaProcessor.C0716a;
import com.inmobi.rendering.mraid.MraidMediaProcessor.C0716a.C0714a;
import com.inmobi.rendering.mraid.MraidMediaProcessor.MediaContentType;
import com.inmobi.rendering.mraid.MraidResizeProcessor;
import com.inmobi.rendering.mraid.OrientationProperties;
import com.inmobi.rendering.mraid.ResizeProperties;
import com.inmobi.rendering.mraid.SystemTasksProcessor;
import com.startapp.android.publish.model.MetaData;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "ViewConstructor", "ClickableViewAccessibility"})
public final class RenderView extends WebView {
    private static final String f1377a;
    private static List<DownloadTask> f1378c;
    private boolean f1379A;
    private boolean f1380B;
    private boolean f1381C;
    private boolean f1382D;
    private boolean f1383E;
    private boolean f1384F;
    private String f1385G;
    private boolean f1386H;
    private boolean f1387I;
    private final Object f1388J;
    private final Object f1389K;
    private boolean f1390L;
    private boolean f1391M;
    private View f1392N;
    private CustomViewCallback f1393O;
    private int f1394P;
    private final C0697a f1395Q;
    private final WebViewClient f1396R;
    private final WebChromeClient f1397S;
    private RenderView f1398b;
    private Context f1399d;
    private Activity f1400e;
    private ViewGroup f1401f;
    private C0710b f1402g;
    private RenderViewState f1403h;
    private RenderingProperties f1404i;
    private MraidExpandProcessor f1405j;
    private MraidResizeProcessor f1406k;
    private MraidMediaProcessor f1407l;
    private SystemTasksProcessor f1408m;
    private JavaScriptBridge f1409n;
    private AdConfig f1410o;
    private AdConfig f1411p;
    private List<String> f1412q;
    private C1231a f1413r;
    private boolean f1414s;
    private boolean f1415t;
    private ExpandProperties f1416u;
    private ResizeProperties f1417v;
    private OrientationProperties f1418w;
    private JSONObject f1419x;
    private JSONObject f1420y;
    private boolean f1421z;

    /* renamed from: com.inmobi.rendering.RenderView.1 */
    class C06991 implements Runnable {
        final /* synthetic */ String f1357a;
        final /* synthetic */ RenderView f1358b;

        C06991(RenderView renderView, String str) {
            this.f1358b = renderView;
            this.f1357a = str;
        }

        public void run() {
            this.f1358b.loadDataWithBaseURL(StringUtils.EMPTY, this.f1357a, "text/html", HTTP.UTF_8, null);
        }
    }

    /* renamed from: com.inmobi.rendering.RenderView.2 */
    class C07002 implements Runnable {
        final /* synthetic */ String f1359a;
        final /* synthetic */ RenderView f1360b;

        C07002(RenderView renderView, String str) {
            this.f1360b = renderView;
            this.f1359a = str;
        }

        public void run() {
            if (!this.f1360b.f1387I) {
                String str = "javascript:try{" + this.f1359a + "}catch(e){}";
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Injecting javascript:" + str);
                if (VERSION.SDK_INT < 19) {
                    this.f1360b.m1624i(str);
                } else {
                    this.f1360b.m1626j(str);
                }
            }
        }
    }

    /* renamed from: com.inmobi.rendering.RenderView.5 */
    class C07015 extends WebViewClient {
        final /* synthetic */ RenderView f1361a;

        C07015(RenderView renderView) {
            this.f1361a = renderView;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Placement type: " + this.f1361a.f1404i.m1685a());
            Intent parseUri;
            if (PlacementType.FULL_SCREEN != this.f1361a.f1404i.m1685a()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Override URL loading (returned true): " + str);
                try {
                    parseUri = Intent.parseUri(str, 0);
                    parseUri.setFlags(268435456);
                    this.f1361a.getContext().startActivity(parseUri);
                    this.f1361a.getListener().m1603g(this.f1361a);
                    return true;
                } catch (URISyntaxException e) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, e.getMessage());
                    return true;
                } catch (ActivityNotFoundException e2) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "No app can handle the URI (" + str + ")");
                    return true;
                }
            } else if (!str.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str.contains("play.google.com") || str.contains("market.android.com") || str.contains("market%3A%2F%2F")) {
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Override URL loading (returned true): " + str);
                try {
                    parseUri = Intent.parseUri(str, 0);
                    parseUri.setFlags(268435456);
                    this.f1361a.getContext().startActivity(parseUri);
                    this.f1361a.getListener().m1603g(this.f1361a);
                    return true;
                } catch (URISyntaxException e3) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, e3.getMessage());
                    return true;
                } catch (ActivityNotFoundException e4) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "No app can handle the URI (" + str + ")");
                    return true;
                }
            } else {
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Override URL loading (returned false): " + str);
                if (this.f1361a.f1407l == null) {
                    return false;
                }
                this.f1361a.f1407l.m1749c();
                return false;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Page load started:" + str);
            this.f1361a.f1386H = false;
            this.f1361a.setAndUpdateViewState(RenderViewState.LOADING);
        }

        public void onPageFinished(WebView webView, String str) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Page load finished:" + str);
            if (this.f1361a.f1412q.contains(str) && !this.f1361a.f1386H) {
                this.f1361a.f1386H = true;
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Injecting MRAID javascript for two piece creatives.");
                this.f1361a.m1649b(this.f1361a.getMraidJsString());
            }
            if (RenderViewState.LOADING == this.f1361a.f1403h) {
                this.f1361a.f1402g.m1599c(this.f1361a);
                this.f1361a.m1632t();
                if (this.f1361a.f1398b != null) {
                    this.f1361a.setAndUpdateViewState(RenderViewState.EXPANDED);
                } else {
                    this.f1361a.setAndUpdateViewState(RenderViewState.DEFAULT);
                }
            }
        }

        public void onLoadResource(WebView webView, String str) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Resource loading:" + str);
            if (str != null) {
                String url = this.f1361a.getUrl();
                if (str.contains("/mraid.js") && !url.equals("about:blank") && !url.startsWith("file:")) {
                    if (!this.f1361a.f1412q.contains(url)) {
                        this.f1361a.f1412q.add(url);
                    }
                    if (!this.f1361a.f1386H) {
                        this.f1361a.f1386H = true;
                        Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Injecting MRAID javascript for two piece creatives.");
                        this.f1361a.m1649b(this.f1361a.getMraidJsString());
                    }
                }
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Loading error. Error code:" + i + " Error msg:" + str + " Failing url:" + str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "SSL error received. Error code:" + sslError.getPrimaryError());
            sslErrorHandler.cancel();
        }
    }

    /* renamed from: com.inmobi.rendering.RenderView.6 */
    class C07096 extends WebChromeClient {
        final /* synthetic */ RenderView f1376a;

        /* renamed from: com.inmobi.rendering.RenderView.6.1 */
        class C07021 implements OnClickListener {
            final /* synthetic */ JsResult f1362a;
            final /* synthetic */ C07096 f1363b;

            C07021(C07096 c07096, JsResult jsResult) {
                this.f1363b = c07096;
                this.f1362a = jsResult;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f1362a.confirm();
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.2 */
        class C07032 implements OnClickListener {
            final /* synthetic */ JsResult f1364a;
            final /* synthetic */ C07096 f1365b;

            C07032(C07096 c07096, JsResult jsResult) {
                this.f1365b = c07096;
                this.f1364a = jsResult;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f1364a.cancel();
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.3 */
        class C07043 implements OnClickListener {
            final /* synthetic */ JsResult f1366a;
            final /* synthetic */ C07096 f1367b;

            C07043(C07096 c07096, JsResult jsResult) {
                this.f1367b = c07096;
                this.f1366a = jsResult;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f1366a.confirm();
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.4 */
        class C07054 implements OnTouchListener {
            final /* synthetic */ C07096 f1368a;

            C07054(C07096 c07096) {
                this.f1368a = c07096;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.5 */
        class C07065 implements OnKeyListener {
            final /* synthetic */ C07096 f1369a;

            C07065(C07096 c07096) {
                this.f1369a = c07096;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (4 != keyEvent.getKeyCode() || keyEvent.getAction() != 0) {
                    return false;
                }
                Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Back pressed when HTML5 video is playing.");
                this.f1369a.m1592a();
                return true;
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.6 */
        class C07076 implements OnClickListener {
            final /* synthetic */ Callback f1370a;
            final /* synthetic */ String f1371b;
            final /* synthetic */ C07096 f1372c;

            C07076(C07096 c07096, Callback callback, String str) {
                this.f1372c = c07096;
                this.f1370a = callback;
                this.f1371b = str;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f1370a.invoke(this.f1371b, false, false);
            }
        }

        /* renamed from: com.inmobi.rendering.RenderView.6.7 */
        class C07087 implements OnClickListener {
            final /* synthetic */ Callback f1373a;
            final /* synthetic */ String f1374b;
            final /* synthetic */ C07096 f1375c;

            C07087(C07096 c07096, Callback callback, String str) {
                this.f1375c = c07096;
                this.f1373a = callback;
                this.f1374b = str;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f1373a.invoke(this.f1374b, true, false);
            }
        }

        C07096(RenderView renderView) {
            this.f1376a = renderView;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "jsAlert called with: " + str2 + str);
            Context j = this.f1376a.f1400e;
            if (j == null) {
                j = this.f1376a.getContext();
            }
            new Builder(j).setMessage(str2).setTitle(str).setPositiveButton(17039370, new C07021(this, jsResult)).setCancelable(false).create().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            Context j = this.f1376a.f1400e;
            if (j == null) {
                j = this.f1376a.getContext();
            }
            new Builder(j).setMessage(str2).setPositiveButton(17039370, new C07043(this, jsResult)).setNegativeButton(17039360, new C07032(this, jsResult)).create().show();
            return true;
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            this.f1376a.f1392N = view;
            this.f1376a.f1393O = customViewCallback;
            this.f1376a.f1392N.setOnTouchListener(new C07054(this));
            Activity j = this.f1376a.f1400e;
            if (j == null) {
                j = (Activity) this.f1376a.getContext();
            }
            FrameLayout frameLayout = (FrameLayout) j.findViewById(16908290);
            this.f1376a.f1392N.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            frameLayout.addView(this.f1376a.f1392N, new LayoutParams(-1, -1, 0, 0));
            this.f1376a.f1392N.requestFocus();
            m1593a(this.f1376a.f1392N, new C07065(this));
        }

        private void m1593a(View view, OnKeyListener onKeyListener) {
            view.setOnKeyListener(onKeyListener);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }

        public void onHideCustomView() {
            m1592a();
            super.onHideCustomView();
        }

        private void m1592a() {
            if (this.f1376a.f1392N != null) {
                if (this.f1376a.f1393O != null) {
                    this.f1376a.f1393O.onCustomViewHidden();
                    this.f1376a.f1393O = null;
                }
                if (this.f1376a.f1392N != null && this.f1376a.f1392N.getParent() != null) {
                    ((ViewGroup) this.f1376a.f1392N.getParent()).removeView(this.f1376a.f1392N);
                    this.f1376a.f1392N = null;
                }
            }
        }

        public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
            Context j = this.f1376a.f1400e;
            if (j == null) {
                j = this.f1376a.getContext();
            }
            String str2 = "Location Permission";
            str2 = "Allow location access";
            new Builder(j).setTitle("Location Permission").setMessage("Allow location access").setPositiveButton(17039370, new C07087(this, callback, str)).setNegativeButton(17039360, new C07076(this, callback, str)).create().show();
            super.onGeolocationPermissionsShowPrompt(str, callback);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "Console message:" + (consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId()));
            return true;
        }
    }

    public enum RenderViewState {
        LOADING,
        DEFAULT,
        RESIZED,
        EXPANDED,
        EXPANDING,
        HIDDEN,
        RESIZING
    }

    /* renamed from: com.inmobi.rendering.RenderView.b */
    public interface C0710b {
        void m1595a(RenderView renderView);

        void m1596a(RenderView renderView, HashMap<Object, Object> hashMap);

        void m1597b(RenderView renderView);

        void m1598b(RenderView renderView, HashMap<Object, Object> hashMap);

        void m1599c(RenderView renderView);

        void m1600d(RenderView renderView);

        void m1601e(RenderView renderView);

        void m1602f(RenderView renderView);

        void m1603g(RenderView renderView);
    }

    /* renamed from: com.inmobi.rendering.RenderView.3 */
    class C12293 implements C0710b {
        final /* synthetic */ RenderView f3851a;

        C12293(RenderView renderView) {
            this.f3851a = renderView;
        }

        public void m4529a(RenderView renderView) {
        }

        public void m4531b(RenderView renderView) {
        }

        public void m4533c(RenderView renderView) {
        }

        public void m4534d(RenderView renderView) {
        }

        public void m4535e(RenderView renderView) {
        }

        public void m4536f(RenderView renderView) {
        }

        public void m4530a(RenderView renderView, HashMap<Object, Object> hashMap) {
        }

        public void m4532b(RenderView renderView, HashMap<Object, Object> hashMap) {
        }

        public void m4537g(RenderView renderView) {
        }
    }

    /* renamed from: com.inmobi.rendering.RenderView.4 */
    class C12304 implements C0697a {
        final /* synthetic */ RenderView f3852a;

        C12304(RenderView renderView) {
            this.f3852a = renderView;
        }

        public void m4538a() {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "onAdScreenDisplayed");
            if (PlacementType.INLINE == this.f3852a.f1404i.m1685a()) {
                if (this.f3852a.f1398b != null) {
                    this.f3852a.f1398b.setAndUpdateViewState(RenderViewState.EXPANDED);
                } else {
                    this.f3852a.setAndUpdateViewState(RenderViewState.EXPANDED);
                }
                this.f3852a.f1390L = false;
            }
            if (this.f3852a.f1402g != null) {
                this.f3852a.f1402g.m1601e(this.f3852a);
            }
        }

        public void m4539b() {
            Logger.m1440a(InternalLogLevel.INTERNAL, RenderView.f1377a, "onAdScreenDismissed");
            if (PlacementType.INLINE == this.f3852a.f1404i.m1685a()) {
                this.f3852a.setAndUpdateViewState(RenderViewState.DEFAULT);
                if (this.f3852a.f1398b != null) {
                    this.f3852a.f1398b.setAndUpdateViewState(RenderViewState.DEFAULT);
                }
            } else if (RenderViewState.DEFAULT == this.f3852a.f1403h) {
                this.f3852a.setAndUpdateViewState(RenderViewState.HIDDEN);
            }
            if (this.f3852a.f1402g != null) {
                this.f3852a.f1402g.m1602f(this.f3852a);
            }
        }
    }

    /* renamed from: com.inmobi.rendering.RenderView.a */
    private final class C1231a implements C0714a {
        String f3853a;
        final /* synthetic */ RenderView f3854b;

        private C1231a(RenderView renderView) {
            this.f3854b = renderView;
        }

        public void m4541a(String str) {
            this.f3853a = str;
        }

        public void m4540a(double d) {
            this.f3854b.m1642a(this.f3853a, "broadcastEvent('micIntensityChange'," + d + ")");
        }
    }

    static {
        f1377a = RenderView.class.getSimpleName();
        f1378c = new ArrayList();
    }

    public RenderView(Context context, RenderingProperties renderingProperties) {
        super(context);
        this.f1403h = RenderViewState.DEFAULT;
        this.f1412q = new ArrayList();
        this.f1413r = new C1231a();
        this.f1421z = true;
        this.f1379A = true;
        this.f1380B = false;
        this.f1381C = false;
        this.f1382D = false;
        this.f1383E = false;
        this.f1384F = false;
        this.f1385G = null;
        this.f1386H = false;
        this.f1387I = false;
        this.f1388J = new Object();
        this.f1389K = new Object();
        this.f1391M = true;
        this.f1394P = -1;
        this.f1395Q = new C12304(this);
        this.f1396R = new C07015(this);
        this.f1397S = new C07096(this);
        this.f1399d = context;
        this.f1398b = null;
        this.f1404i = renderingProperties;
        this.f1390L = false;
    }

    public void setOriginalRenderView(RenderView renderView) {
        this.f1398b = renderView;
    }

    public RenderView getOriginalRenderView() {
        return this.f1398b;
    }

    public C0697a getAdScreenEventsListener() {
        return this.f1395Q;
    }

    public RenderViewState getState() {
        return this.f1403h;
    }

    public boolean m1648a() {
        return this.f1382D;
    }

    public Object getDefaultPositionMonitor() {
        return this.f1388J;
    }

    public Object getCurrentPositionMonitor() {
        return this.f1389K;
    }

    public boolean m1654b() {
        return this.f1421z;
    }

    public boolean m1659c() {
        return this.f1379A;
    }

    public void setDefaultPositionLock() {
        this.f1421z = true;
    }

    public void setCurrentPositionLock() {
        this.f1379A = true;
    }

    public void setDefaultPosition() {
        int[] iArr = new int[2];
        this.f1419x = new JSONObject();
        if (this.f1401f == null) {
            this.f1401f = (ViewGroup) getParent();
        }
        if (this.f1401f != null) {
            this.f1401f.getLocationOnScreen(iArr);
            try {
                this.f1419x.put("x", DisplayInfo.m1480a(iArr[0]));
                this.f1419x.put("y", DisplayInfo.m1480a(iArr[1]));
                int a = DisplayInfo.m1480a(this.f1401f.getWidth());
                int a2 = DisplayInfo.m1480a(this.f1401f.getHeight());
                this.f1419x.put("width", a);
                this.f1419x.put("height", a2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.f1419x.put("x", 0);
                this.f1419x.put("y", 0);
                this.f1419x.put("width", 0);
                this.f1419x.put("height", 0);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        synchronized (this.f1388J) {
            this.f1421z = false;
            this.f1388J.notifyAll();
        }
    }

    public String getDefaultPosition() {
        return this.f1419x == null ? StringUtils.EMPTY : this.f1419x.toString();
    }

    public void setCurrentPosition() {
        this.f1420y = new JSONObject();
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        try {
            this.f1420y.put("x", DisplayInfo.m1480a(iArr[0]));
            this.f1420y.put("y", DisplayInfo.m1480a(iArr[1]));
            int a = DisplayInfo.m1480a(getWidth());
            int a2 = DisplayInfo.m1480a(getHeight());
            this.f1420y.put("width", a);
            this.f1420y.put("height", a2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        synchronized (this.f1389K) {
            this.f1379A = false;
            this.f1389K.notifyAll();
        }
    }

    public String getCurrentPosition() {
        return this.f1420y == null ? StringUtils.EMPTY : this.f1420y.toString();
    }

    public void setFullScreenActivity(Activity activity) {
        this.f1400e = activity;
        if (this.f1418w != null) {
            setOrientationProperties(this.f1418w);
        }
    }

    public Activity getFullScreenActivity() {
        return this.f1400e;
    }

    public AdConfig getRenderingConfig() {
        return this.f1410o;
    }

    public AdConfig getMraidConfig() {
        return this.f1411p;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "onSizeChanged (" + i + ", " + i2 + ")");
        if (i != 0 && i2 != 0) {
            m1606a(DisplayInfo.m1480a(i), DisplayInfo.m1480a(i2));
        }
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        boolean z = i == 0;
        if (this.f1382D != z) {
            m1615c(z);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f1382D != z) {
            m1615c(z);
        }
    }

    private void m1615c(boolean z) {
        if (!this.f1390L) {
            this.f1382D = z;
            if (z) {
                this.f1402g.m1600d(this);
            } else {
                this.f1408m.m1840a(this.f1399d);
            }
            m1617d(this.f1382D);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        super.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        m1630r();
        if (this.f1401f == null) {
            this.f1401f = (ViewGroup) getParent();
        }
    }

    @TargetApi(11)
    private void m1630r() {
        if (VERSION.SDK_INT >= 11) {
            this.f1415t = isHardwareAccelerated();
        }
    }

    public void onDetachedFromWindow() {
        this.f1412q.clear();
        if (this.f1409n != null) {
            this.f1409n.unRegisterBroadcastListener();
        }
        m1682n();
        getMediaProcessor().m1756f();
        getMediaProcessor().m1758g();
        getMediaProcessor().m1761i();
        this.f1408m.m1840a(this.f1399d);
        super.onDetachedFromWindow();
    }

    @SuppressLint({"AddJavascriptInterface"})
    @TargetApi(19)
    public void m1639a(C0710b c0710b, AdConfig adConfig, AdConfig adConfig2) {
        this.f1410o = adConfig;
        this.f1411p = adConfig2;
        this.f1402g = c0710b;
        this.f1401f = (ViewGroup) getParent();
        if ("production".equals("staging") && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (getRenderingConfig() != null) {
            setBackgroundColor(getRenderingConfig().m1177d());
        }
        if (getMraidConfig() != null && m1631s()) {
            new MraidJsFetcher(getMraidConfig().m1162d(), getMraidConfig().m1160b(), getMraidConfig().m1161c()).m1822a();
        }
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setGeolocationEnabled(true);
        setWebViewClient(this.f1396R);
        setWebChromeClient(this.f1397S);
        this.f1409n = new JavaScriptBridge(this, this.f1404i);
        addJavascriptInterface(this.f1409n, "sdkController");
        this.f1405j = new MraidExpandProcessor(this);
        this.f1406k = new MraidResizeProcessor(this);
        this.f1407l = new MraidMediaProcessor(this);
        this.f1408m = new SystemTasksProcessor(this);
        this.f1416u = new ExpandProperties();
        this.f1417v = new ResizeProperties();
        this.f1418w = new OrientationProperties();
    }

    @TargetApi(11)
    public void destroy() {
        this.f1387I = true;
        this.f1390L = true;
        this.f1394P = -1;
        removeJavascriptInterface("sdkController");
        m1638z();
        super.destroy();
    }

    private boolean m1631s() {
        return (System.currentTimeMillis() / 1000) - new MraidJsDao().m1817c() > getMraidConfig().m1159a();
    }

    public void m1640a(String str) {
        this.f1390L = false;
        new Handler(this.f1399d.getMainLooper()).post(new C06991(this, str));
    }

    private void m1632t() {
        m1649b("window.imaiview.broadcastEvent('ready');");
        m1649b("window.mraidview.broadcastEvent('ready');");
    }

    private void m1606a(int i, int i2) {
        m1649b("window.mraidview.broadcastEvent('sizeChange'," + i + "," + i2 + ");");
    }

    private void m1617d(boolean z) {
        m1649b("window.mraidview.broadcastEvent('viewableChange'," + z + ");");
    }

    private void m1621h(String str) {
        m1649b("window.mraidview.broadcastEvent('stateChange','" + str + "');");
    }

    public void m1645a(String str, String str2, String str3) {
        m1642a(str, "broadcastEvent('error',\"" + str2 + "\", \"" + str3 + "\")");
    }

    public void m1642a(String str, String str2) {
        m1649b(str + "." + str2);
    }

    public void m1649b(String str) {
        new Handler(this.f1399d.getMainLooper()).post(new C07002(this, str));
    }

    public void m1644a(String str, String str2, MediaContentType mediaContentType) {
        if (PlacementType.FULL_SCREEN != this.f1404i.m1685a() && RenderViewState.EXPANDED != getViewState()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback is only supported on full screen ads! Ignoring request ...");
        } else if (this.f1400e == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback is  not allowed before it is visible! Ignoring request ...");
            m1645a(str, "Media playback is  not allowed before it is visible! Ignoring request ...", "playVideo");
        } else {
            this.f1407l.m1742a(str, str2, mediaContentType, this.f1400e);
        }
    }

    public void m1650b(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1740a(str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1643a(String str, String str2, int i) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1741a(str, str2, i);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1656c(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1747b(str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1661d(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1751c(str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1651b(String str, String str2, int i) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1748b(str, str2, i);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public int m1664e(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            return this.f1407l.m1754e(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        return 0;
    }

    public boolean m1669f(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            return this.f1407l.m1753d(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        return false;
    }

    public void m1671g(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1743a(str, str2, false);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1674h(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1757f(str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1676i(String str, String str2) {
        if (PlacementType.FULL_SCREEN == this.f1404i.m1685a() || RenderViewState.EXPANDED == getViewState()) {
            this.f1407l.m1759g(str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Media playback controls are only supported on full screen ads! Ignoring request ...");
        }
    }

    public void m1678j(String str, String str2) {
        if (RenderViewState.DEFAULT == this.f1403h || RenderViewState.RESIZED == this.f1403h) {
            this.f1390L = true;
            this.f1405j.m1813a(str, str2);
            requestLayout();
            invalidate();
            this.f1381C = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:" + this.f1403h);
    }

    public boolean m1663d() {
        return this.f1380B;
    }

    public void setUseCustomClose(boolean z) {
        this.f1380B = z;
    }

    public boolean m1666e() {
        return this.f1383E;
    }

    public void setCloseRegionDisabled(boolean z) {
        this.f1383E = z;
    }

    public void setDisableBackButton(boolean z) {
        this.f1384F = z;
    }

    public boolean m1668f() {
        return this.f1384F;
    }

    public void m1655c(String str) {
        this.f1385G = str;
    }

    public void m1670g() {
        this.f1385G = null;
    }

    private boolean m1633u() {
        return this.f1385G != null;
    }

    public void m1673h() {
        if (m1633u()) {
            String str = "broadcastEvent('backButtonPressed')";
            m1642a(this.f1385G, "broadcastEvent('backButtonPressed')");
        }
    }

    public void m1647a(boolean z) {
        setCloseRegionDisabled(z);
        View rootView = getRootView();
        if (rootView != null) {
            CustomView customView = (CustomView) rootView.findViewById(65531);
            if (customView != null) {
                customView.setVisibility(this.f1383E ? 8 : 0);
            }
        }
    }

    public void m1653b(boolean z) {
        setUseCustomClose(z);
        if (getRootView() != null) {
            CustomView customView = (CustomView) getRootView().findViewById(65532);
            if (customView != null) {
                customView.setVisibility(this.f1380B ? 8 : 0);
            }
        }
    }

    public void m1675i() {
        if (RenderViewState.DEFAULT != this.f1403h && RenderViewState.RESIZED != this.f1403h) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Render view state must be either DEFAULT or RESIZED to admit the resize request");
        } else if (getResizeProperties() == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Render view state can not resize with invalid resize properties");
        } else {
            this.f1390L = true;
            this.f1406k.m1830a();
            requestLayout();
            invalidate();
            this.f1381C = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            setAndUpdateViewState(RenderViewState.RESIZED);
            this.f1402g.m1601e(this);
            this.f1390L = false;
        }
    }

    public void m1677j() {
        m1638z();
        this.f1407l.m1745b();
        if (RenderViewState.EXPANDED == this.f1403h) {
            m1636x();
        } else if (RenderViewState.RESIZED == this.f1403h) {
            m1637y();
        } else if (RenderViewState.DEFAULT == this.f1403h) {
            setAndUpdateViewState(RenderViewState.HIDDEN);
            if (this.f1404i.m1685a() == PlacementType.FULL_SCREEN) {
                m1634v();
            } else {
                ((ViewGroup) getParent()).removeAllViews();
            }
        }
        this.f1412q.clear();
        this.f1381C = false;
    }

    private void m1634v() {
        ((InMobiAdActivity) InMobiAdActivity.m1583a().getFullScreenActivity()).m1591a(true);
        InMobiAdActivity.m1583a().getFullScreenActivity().finish();
        if (this.f1394P != -1) {
            InMobiAdActivity.m1583a().getFullScreenActivity().overridePendingTransition(0, this.f1394P);
        }
    }

    public void setFullScreenExitAnimation(int i) {
        this.f1394P = i;
    }

    public void m1679k() {
        if (this.f1403h == RenderViewState.RESIZED && getResizeProperties() != null) {
            this.f1406k.m1830a();
        }
    }

    public void m1652b(String str, String str2, String str3) {
        try {
            Intent parseUri = Intent.parseUri(str3, 0);
            parseUri.setFlags(268435456);
            getContext().startActivity(parseUri);
            getListener().m1603g(this);
            m1642a(str2, "broadcastEvent('" + str + "Successful','" + str3 + "');");
        } catch (URISyntaxException e) {
            m1645a(str2, "Cannot resolve URI (" + str3 + ")", str);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Error message in processing openExternal: " + e.getMessage());
        } catch (ActivityNotFoundException e2) {
            m1645a(str2, "No app can handle the URI (" + str3 + ")", str);
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Error message in processing openExternal: " + e2.getMessage());
        }
    }

    public void m1658c(String str, String str2, String str3) {
        if (str3 == null || (str3.startsWith(HttpHost.DEFAULT_SCHEME_NAME) && !URLUtil.isValidUrl(str3))) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, str + " called with invalid url (" + str3 + ")");
            m1645a(str2, "Invalid URL", str);
        } else if (!str3.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str3.contains("play.google.com") || str3.contains("market.android.com") || str3.contains("market%3A%2F%2F")) {
            m1652b(str, str2, str3);
        } else {
            m1635w();
            InMobiAdActivity.m1589b(this);
            Intent intent = new Intent(getContext(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
            if (!(getContext() instanceof Activity)) {
                intent.setFlags(268435456);
            }
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str3);
            getContext().startActivity(intent);
            m1642a(str2, "broadcastEvent('" + str + "Successful','" + str3 + "');");
        }
    }

    public C0710b getListener() {
        if (this.f1402g != null) {
            return this.f1402g;
        }
        C0710b c12293 = new C12293(this);
        this.f1402g = c12293;
        return c12293;
    }

    public RenderViewState getViewState() {
        return this.f1403h;
    }

    public MraidMediaProcessor getMediaProcessor() {
        return this.f1407l;
    }

    public ExpandProperties getExpandProperties() {
        return this.f1416u;
    }

    public void setExpandProperties(ExpandProperties expandProperties) {
        if (expandProperties.m1774b()) {
            setUseCustomClose(expandProperties.m1773a());
        }
        this.f1416u = expandProperties;
    }

    public ResizeProperties getResizeProperties() {
        return this.f1417v;
    }

    public void setResizeProperties(ResizeProperties resizeProperties) {
        this.f1417v = resizeProperties;
    }

    public void setAndUpdateViewState(RenderViewState renderViewState) {
        this.f1403h = renderViewState;
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "set state:" + this.f1403h);
        m1621h(this.f1403h.toString().toLowerCase(Locale.ENGLISH));
    }

    public void m1680l() {
        setVisibility(0);
        requestLayout();
    }

    public void setAdActiveFlag(boolean z) {
        this.f1381C = z;
    }

    public OrientationProperties getOrientationProperties() {
        return this.f1418w;
    }

    public void setOrientationProperties(OrientationProperties orientationProperties) {
        this.f1418w = orientationProperties;
        if (this.f1400e != null && !orientationProperties.f1668a) {
            String str = orientationProperties.f1669b;
            int i = -1;
            switch (str.hashCode()) {
                case 729267099:
                    if (str.equals("portrait")) {
                        i = 1;
                        break;
                    }
                    break;
                case 1430647483:
                    if (str.equals("landscape")) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case DurationDV.DURATION_TYPE /*0*/:
                    if (VERSION.SDK_INT <= 8 || DisplayInfo.m1482b() != ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
                        this.f1400e.setRequestedOrientation(0);
                    } else {
                        this.f1400e.setRequestedOrientation(8);
                    }
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (VERSION.SDK_INT <= 8 || DisplayInfo.m1482b() != ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        this.f1400e.setRequestedOrientation(1);
                    } else {
                        this.f1400e.setRequestedOrientation(9);
                    }
                default:
                    if (VERSION.SDK_INT > 8 && DisplayInfo.m1482b() == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        this.f1400e.setRequestedOrientation(9);
                    } else if (VERSION.SDK_INT > 8 && DisplayInfo.m1482b() == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
                        this.f1400e.setRequestedOrientation(8);
                    } else if (DisplayInfo.m1482b() == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
                        this.f1400e.setRequestedOrientation(0);
                    } else {
                        this.f1400e.setRequestedOrientation(1);
                    }
            }
        }
    }

    public double m1681m() {
        return C0716a.m1714a();
    }

    public void m1660d(String str) {
        if (!this.f1414s) {
            this.f1414s = true;
            this.f1413r.m4541a(str);
            this.f1407l.m1752d().m1720a(this.f1413r);
        }
    }

    public void m1682n() {
        if (this.f1414s) {
            this.f1414s = false;
            this.f1407l.m1752d().m1721b(this.f1413r);
        }
    }

    public String getMraidJsString() {
        String str = "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var e=String(a);b[e]||(b[e]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var e in b)b[e].apply({},a)};this.toString=function(){var c=[a,\":\"],e;for(e in b)c.push(\"|\",e,\"|\");return c.join(\"\")}},\nInmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&window.imraidview.startListeningHeadphonePluggedEvents();\n\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent()}}catch(e){this.log(e)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\n\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(e){}}};this.sendSaveContentResult=\nfunction(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var e=JSON.parse(arguments[c]);b[c]=e}else b[c]=arguments[c];window.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),\nwindow.location=a)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){for(var b=this.urlScheme+\"://\"+a,c,e=!0,f=1;f<arguments.length;f+=2)c=arguments[f+1],null!=c&&(e?(b+=\"?\",e=!1):b+=\"&\",b+=arguments[f]+\"=\"+escape(c));__im__iosNativeCall.executeNativeCall(b);return\"OK\"};this.nativeCallComplete=function(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={},b={allowOrientationChange:!0,forceOrientation:\"none\"},c=[],e=!1;a.zeroPad=function(f){var a=\"\";10>f&&(a+=\"0\");return a+f};a.supports=function(f){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof f)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",f)};a.useCustomClose=function(f){try{sdkController.useCustomClose(\"window.mraidview\",f)}catch(a){imraidview.showAlert(\"use CustomClose: \"+\na)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(f){imraidview.showAlert(\"close: \"+f)}};a.stackCommands=function(f,a){e?c.push(f):(eval(f),a&&(e=!0))};a.expand=function(f){try{\"undefined\"==typeof f&&(f=null),sdkController.expand(\"window.mraidview\",f)}catch(a){imraidview.showAlert(\"executeNativeExpand: \"+a+\", URL = \"+f)}};a.setExpandProperties=function(f){try{f?this.props=f:f=null;if(\"undefined\"!=typeof f.lockOrientation&&null!=f.lockOrientation&&\"undefined\"!=typeof f.orientation&&\nnull!=f.orientation){var d={};d.allowOrientationChange=!f.lockOrientation;d.forceOrientation=f.orientation;a.setOrientationProperties(d)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(f))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+f)}};a.getExpandProperties=function(){try{return eval(\"(\"+sdkController.getExpandProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(f){try{f?\nb=f:f=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(f))}catch(d){imraidview.showAlert(\"executeNativesetOrientationProperties: \"+d+\", props = \"+f)}};a.getOrientationProperties=function(){return b};a.resizeProps=null;a.setResizeProperties=function(f){var d,b;try{d=parseInt(f.width);b=parseInt(f.height);if(isNaN(d)||isNaN(b)||1>d||1>b)throw\"Invalid\";f.width=d;f.height=b;a.resizeProps=f;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(f))}catch(c){window.mraid.broadcastEvent(\"error\",\n\"Invalid properties.\",\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(d){imraidview.showAlert(\"open: \"+d)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+\na)}};a.getMaxSize=function(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var d=\"\";null!=a&&(d=a);try{sdkController.playVideo(\"window.mraidview\",\nd)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(f){if(\"undefined\"===typeof JSON){var d=\"\",b;if(\"undefined\"==typeof f.length)return a.stringifyArg(f);for(b=0;b<f.length;b++)0<b&&(d+=\",\"),d+=a.stringifyArg(f[b]);return d+\"]\"}return JSON.stringify(f)};a.stringifyArg=function(a){var d,b,c;b=typeof a;d=\"\";if(\"number\"===b||\"boolean\"===b)d+=args;else if(a instanceof Array)d=d+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;d+=\"{\";for(c in a)null!==a[c]&&(b||(d+=\",\"),d=d+'\"'+c+'\":',b=\ntypeof a[c],d=\"number\"===b||\"boolean\"===b?d+a[c]:\"function\"===typeof a[c]?d+'\"\"':a[c]instanceof Object?d+this.stringify(args[i][c]):d+'\"'+a[c]+'\"',b=!1);d+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),d=d+'\"'+a+'\"';imraidview.showAlert(\"json:\"+d);return d};getPID=function(a){var d=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(d=a.id);return d};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(f){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var d={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)d={};else{\"string\"==typeof a.summary&&(d.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(d.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(d.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(d.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?d.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}d.daysInMonth=a.recurrence.daysInMonth;d.daysInYear=a.recurrence.daysInYear;d.weeksInMonth=a.recurrence.weeksInMonth;d.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(d),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var d=0;d<a.length;d++)switch(a[d]){case 0:a[d]=\"SU\";break;case 1:a[d]=\"MO\";break;case 2:a[d]=\"TU\";break;case 3:a[d]=\"WE\";break;case 4:a[d]=\"TH\";\nbreak;case 5:a[d]=\"FR\";break;case 6:a[d]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(f){console.log(\"bridge: storePicture\");if(\"string\"!=typeof f)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+f+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nf);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+f+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,d){};a.fireMediaErrorEvent=function(a,d){};a.fireMediaTimeUpdateEvent=function(a,d,b){};a.fireMediaCloseEvent=function(a,d,b){};a.fireMediaVolumeChangeEvent=function(a,d,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview;a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;\na.setOrientationProperties=b.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=b.createCalendarEvent;a.open=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c)};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};a.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=\nb.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var e=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=function(){b=a.getWindowOrientation();\nwindow.addEventListener(\"resize\",e,!1);window.addEventListener(\"orientationchange\",e,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",e,!1);window.removeEventListener(\"orientationchange\",e,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,d){var b={};b.name=a;var c=\"inmobi_media_\"+a;\"undefined\"!=typeof d&&(null!=d&&\"\"!=d)&&(c=c+\"_\"+d);window.imraid.broadcastEvent(c,\nb)};a.fireMediaErrorEvent=function(a,d){var b={name:\"error\"};b.code=d;var c=\"inmobi_media_\"+b.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(c=c+\"_\"+a);window.imraid.broadcastEvent(c,b)};a.fireMediaTimeUpdateEvent=function(a,d,b){var c={name:\"timeupdate\",target:{}};c.target.currentTime=d;c.target.duration=b;d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.saveContent=function(a,d,b){window.imraid.addEventListener(\"saveContent_\"+a,b);\nsdkController.saveContent(\"window.imraidview\",a,d)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,d,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+a;c.width=d;c.height=b;window.imraid.broadcastEvent(\"galleryImageSelected\",c)};a.fireCameraPictureCatpturedEvent=function(a,d,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+\na;c.width=d;c.height=b;window.imraid.broadcastEvent(\"cameraPictureCaptured\",c)};a.fireMediaCloseEvent=function(a,d,b){var c={name:\"close\"};c.viaUserInteraction=d;c.target={};c.target.currentTime=b;d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.fireMediaVolumeChangeEvent=function(a,b,c){var e={name:\"volumechange\",target:{}};e.target.volume=b;e.target.muted=c;b=\"inmobi_media_\"+e.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+\na);window.imraid.broadcastEvent(b,e)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b){try{sdkController.openExternal(\"window.imraidview\",b)}catch(d){a.showAlert(\"openExternal: \"+d)}};\na.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(d){a.showAlert(\"log: \"+d)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(d){a.showAlert(\"asyncPing: \"+d)}};a.makeCall=function(b){try{b.startsWith(\"tel:\")?sdkController.openExternal(\"window.imraidview\",b):sdkController.openExternal(\"window.imraidview\",\"tel:\"+b)}catch(d){a.showAlert(\"makeCall: \"+d)}};a.sendMail=function(b,d,c){try{null==d&&(d=\"\"),null==\nc&&(c=\"\"),sdkController.sendMail(\"window.imraidview\",b,d,c)}catch(e){a.showAlert(\"sendMail: \"+e)}};a.sendSMS=function(b,d){try{null==d&&(d=\"\"),sdkController.sendSMS(\"window.imraidview\",b,d)}catch(c){a.showAlert(\"sendSMS: \"+c)}};a.pauseAudio=function(b){try{var d=getPID(b);sdkController.pauseAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"pauseAudio: \"+c)}};a.muteAudio=function(b){try{var d=getPID(b);sdkController.muteAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"muteAudio: \"+c)}};a.unMuteAudio=\nfunction(b){try{var d=getPID(b);sdkController.unMuteAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"unMuteAudio: \"+c)}};a.isAudioMuted=function(b){try{var d=getPID(b);return sdkController.isAudioMuted(\"window.imraidview\",d)}catch(c){a.showAlert(\"isAudioMuted: \"+c)}};a.setAudioVolume=function(b,d){try{var c=getPID(b);d=parseInt(d);sdkController.setAudioVolume(\"window.imraidview\",c,d)}catch(e){a.showAlert(\"setAudioVolume: \"+e)}};a.getAudioVolume=function(b){try{var d=getPID(b);return sdkController.getAudioVolume(\"window.imraidview\",\nd)}catch(c){a.showAlert(\"getAudioVolume: \"+c)}};a.seekAudio=function(b,d){try{var c=getPID(b);d=parseInt(d);isNaN(d)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekAudio\"):sdkController.seekAudio(\"window.imraidview\",c,d)}catch(e){a.showAlert(\"seekAudio: \"+e)}};a.playVideo=function(b,d){var c=!1,e=!0,h=!0,p=!1,l=-99999,k=-99999,m=-99999,q=-99999,r=\"normal\",s=\"exit\",t=\"\",u=getPID(d);null!=b&&(t=b);null!=d&&(\"undefined\"!=typeof d.audio&&\"muted\"==d.audio&&(c=!0),\"undefined\"!=\ntypeof d.autoplay&&!1===d.autoplay&&(e=!1),\"undefined\"!=typeof d.controls&&!1===d.controls&&(h=!1),\"undefined\"!=typeof d.loop&&!0===d.loop&&(p=!0),\"undefined\"!=typeof d.inline&&null!=d.inline&&(l=d.inline.top,k=d.inline.left),\"undefined\"!=typeof d.width&&null!=d.width&&(m=d.width),\"undefined\"!=typeof d.height&&null!=d.height&&(q=d.height),\"undefined\"!=typeof d.startStyle&&null!=d.startStyle&&(r=d.startStyle),\"undefined\"!=typeof d.stopStyle&&null!=d.stopStyle&&(s=d.stopStyle),l=parseInt(l).toString(),\nk=parseInt(k).toString(),m=parseInt(m).toString(),q=parseInt(q).toString());try{sdkController.playVideo(\"window.imraidview\",t,c,e,h,p,l,k,m,q,r,s,u)}catch(v){a.showAlert(\"playVideo: \"+v)}};a.playAudio=function(b,d){var c=!0,e=!1,h=\"normal\",p=\"normal\",l=!0,k=\"\",m=getPID(d);null!=b&&(k=b);null!=d&&(\"undefined\"!=typeof d.autoplay&&!1===d.autoplay&&(c=!1),\"undefined\"!=typeof d.loop&&!0===d.loop&&(e=!0),\"undefined\"!=typeof d.startStyle&&null!=d.startStyle&&(h=d.startStyle),\"undefined\"!=typeof d.stopStyle&&\nnull!=d.stopStyle&&(p=d.stopStyle),\"fullscreen\"==h&&(l=!0));try{sdkController.playAudio(\"window.imraidview\",k,c,l,e,h,p,m)}catch(q){a.showAlert(\"playAudio: \"+q)}};a.pauseVideo=function(b){try{var d=getPID(b);sdkController.pauseVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"pauseVideo: \"+c)}};a.closeVideo=function(b){try{var d=getPID(b);sdkController.closeVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"closeVideo: \"+c)}};a.hideVideo=function(b){try{var d=getPID(b);sdkController.hideVideo(\"window.imraidview\",\nd)}catch(c){a.showAlert(\"hideVideo: \"+c)}};a.showVideo=function(b){try{var d=getPID(b);sdkController.showVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"showVideo: \"+c)}};a.muteVideo=function(b){try{var c=getPID(b);sdkController.muteVideo(\"window.imraidview\",c)}catch(e){a.showAlert(\"muteVideo: \"+e)}};a.unMuteVideo=function(b){try{var c=getPID(b);sdkController.unMuteVideo(\"window.imraidview\",c)}catch(e){a.showAlert(\"unMuteVideo: \"+e)}};a.seekVideo=function(b,c){try{var e=getPID(b);c=parseInt(c);\nisNaN(c)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekVideo\"):sdkController.seekVideo(\"window.imraidview\",e,c)}catch(g){a.showAlert(\"seekVideo: \"+g)}};a.isVideoMuted=function(b){try{var c=getPID(b);return sdkController.isVideoMuted(\"window.imraidview\",c)}catch(e){a.showAlert(\"isVideoMuted: \"+e)}};a.setVideoVolume=function(b,c){try{var e=getPID(b);c=parseInt(c);sdkController.setVideoVolume(\"window.imraidview\",e,c)}catch(g){a.showAlert(\"setVideoVolume: \"+g)}};a.getVideoVolume=\nfunction(b){try{var c=getPID(b);return sdkController.getVideoVolume(\"window.imraidview\",c)}catch(e){a.showAlert(\"getVideoVolume: \"+e)}};a.startListeningMicIntensity=function(){sdkController.registerMicListener(\"window.imraidview\")};a.stopListeningMicIntensity=function(){sdkController.unRegisterMicListener(\"window.imraidview\")};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=function(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};\na.startListeningVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};getSdkVersionInt=function(){for(var b=\na.getSdkVersion().split(\".\"),c=b.length,e=\"\",g=0;g<c;g++)e+=b[g];return parseInt(e)};a.vibrate=function(a){if(null==a||420>getSdkVersionInt())sdkController.vibrate(\"window.imraidview\");else{if(0===arguments.length)return sdkController.vibrate(\"window.imraidview\"),null;if(1==arguments.length)0===a||\"length\"in a&&0===a.length?sdkController.vibrate(\"window.imraidview\",\"[]\",-1):sdkController.vibrate(\"window.imraidview\",\"[0,\"+a+\"]\",-1);else{var b=Array.prototype.slice.call(arguments);sdkController.vibrate(\"window.imraidview\",\n\"[0,\"+String(b)+\"]\",-1)}}};a.takeCameraPicture=function(){sdkController.takeCameraPicture(\"window.imraidview\")};a.getGalleryImage=function(){return sdkController.getGalleryImage(\"window.imraidview\")};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.imraidview\",\na)};a.postToSocial=function(a,b,c,e){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof e&&(e=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,e))};a.getMicIntensity=function(){return sdkController.getMicIntensity(\"window.imraidview\")};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",null);else try{sdkController.incentCompleted(\"window.imraidview\",\nJSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+b)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+\nc+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=sdkController.isDeviceMuted(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,arguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,\narguments)};a.disableBackButton=function(a){void 0==a||\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};a.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.saveContent=function(a,e,f){var d=arguments.length;3>d?\"function\"===typeof arguments[d-1]&&(d=arguments[d-1],window.imraid.addEventListener(\"saveContent\",d),window.imraid.sendSaveContentResult(\"saveContent\",\"fail\",JSON.stringify({reason:1}))):b.saveContent(a,e,f)};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\n\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.makeCall=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must provide a number to call.\",\"makeCall\"):b.makeCall(c)};a.sendMail=function(c,e,f){\"string\"!=typeof c?a.broadcastEvent(\"error\",\n\"Request must specify a recipient.\",\"sendMail\"):b.sendMail(c,e,f)};a.sendSMS=function(c,e){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendSMS\"):b.sendSMS(c,e)};a.playAudio=function(a,e){\"object\"!=typeof e?\"string\"==typeof a?b.playAudio(a,null):\"object\"==typeof a?b.playAudio(null,a):b.playAudio(null,null):b.playAudio(a,e)};a.getGalleryImage=b.getGalleryImage;a.pauseAudio=b.pauseAudio;a.muteAudio=b.muteAudio;a.unMuteAudio=b.unMuteAudio;a.isAudioMuted=b.isAudioMuted;\na.setAudioVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var e=c.volume;isNaN(e)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setAudioVolume\"):(0>e?e=0:100<e&&(e=100),b.setAudioVolume(c,e))}};a.getAudioVolume=b.getAudioVolume;a.pauseVideo=b.pauseVideo;a.closeVideo=b.closeVideo;a.hideVideo=b.hideVideo;a.showVideo=b.showVideo;a.muteVideo=b.muteVideo;a.unMuteVideo=b.unMuteVideo;\na.isVideoMuted=b.isVideoMuted;a.setVideoVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var e=c.volume;isNaN(e)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setVideoVolume\"):(0>e?e=0:100<e&&(e=100),b.setVideoVolume(c,e))}};a.getVideoVolume=b.getVideoVolume;a.seekAudio=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\n\"seekAudio\");else{var e=c.time;imIsObjValid(e)?b.seekAudio(c,e):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekAudio\")}};a.seekVideo=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\");else{var e=c.time;imIsObjValid(e)?b.seekVideo(c,e):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\")}};a.openExternal=b.openExternal;a.updateToPassbook=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\n\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c)};a.vibrate=b.vibrate;a.takeCameraPicture=b.takeCameraPicture;a.getMicIntensity=function(){return!imIsObjValid(a.listeners.micIntensityChange)?-1:b.getMicIntensity()};a.postToSocial=b.postToSocial;a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=b.showSKStore;a.playVideo=function(a,e){\"object\"!=typeof e?\"string\"==typeof a?b.playVideo(a,null):\"object\"==typeof a?b.playVideo(null,\na):b.playVideo(null,null):b.playVideo(a,e)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=function(){return b.getDeviceVolume()};a.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=\nb.isBackButtonDisabled})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(e){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a){sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||null==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",\nJSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var f=-1;try{f=a.indexOf(b)}catch(d){}return f};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,e,f){if(!imIsObjValid(e)||!imIsObjValid(f))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+e+\",\"+f):a+(\"&u-tap-o=\"+e+\",\"+f));return a};b.performAdClick=function(a,e){e=e||event;if(imIsObjValid(a)){var f=a.clickConfig,d=a.landingConfig;if(!imIsObjValid(f)&&!imIsObjValid(d))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var n=null,g=null,h=null,p=null,l=null,k=null,m=null;if(imIsObjValid(e))try{p=e.changedTouches[0].pageX,l=e.changedTouches[0].pageY}catch(q){l=\np=0}imIsObjValid(d)?imIsObjValid(f)?(k=d.url,m=d.urlType,n=f.url,g=f.pingWV,h=f.fr):(k=d.url,m=d.urlType):(k=f.url,m=f.urlType);f=b.getPlatform();try{if(\"boolean\"!=typeof h&&\"number\"!=typeof h||null==h)h=!0;if(0>h||1<h)h=!0;if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"number\"!=typeof m||null==m)m=0;n=b.appendTapParams(n,p,l);imIsObjValid(n)?!0==g?b.pingInWebView(n):b.ping(n,h):b.log(\"clickurl provided is null.\");if(imIsObjValid(k))switch(imIsObjValid(n)||(k=b.appendTapParams(k,\np,l)),m){case 1:b.openEmbedded(k);break;case 2:\"ios\"==f?b.ios.openItunesProductView(k):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+f);break;default:b.openExternal(k)}else b.log(\"Landing url provided is null.\")}catch(r){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,e){e=e||event;if(imIsObjValid(a)){var f=a.clickConfig,d=a.landingConfig;if(!imIsObjValid(f)&&!imIsObjValid(d))b.log(\"click/landing config are invalid, Nothing to process .\"),\nthis.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var n=null,g=null,h=null,p=null,l=null;if(imIsObjValid(e))try{p=e.changedTouches[0].pageX,l=e.changedTouches[0].pageY}catch(k){l=p=0}imIsObjValid(f)&&(n=f.url,g=f.pingWV,h=f.fr);try{if(\"boolean\"!=typeof h&&\"number\"!=typeof h||null==h)h=!0;if(0>h||1<h)h=!0;if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;n=b.appendTapParams(n,p,l);imIsObjValid(n)?!0==g?b.pingInWebView(n):b.ping(n,h):\nb.log(\"clickurl provided is null.\");b.onUserInteraction(d)}catch(m){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=a.openEmbedded;b.openExternal=a.openExternal;b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.ios.openItunesProductView=\na.ios.openItunesProductView;b.fireAdReady=a.fireAdReady;b.fireAdFailed=a.fireAdFailed})();";
        str = new MraidJsDao().m1816b();
        if (str == null) {
            str = "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var e=String(a);b[e]||(b[e]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var e in b)b[e].apply({},a)};this.toString=function(){var c=[a,\":\"],e;for(e in b)c.push(\"|\",e,\"|\");return c.join(\"\")}},\nInmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&window.imraidview.startListeningHeadphonePluggedEvents();\n\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent()}}catch(e){this.log(e)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\n\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(e){}}};this.sendSaveContentResult=\nfunction(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var e=JSON.parse(arguments[c]);b[c]=e}else b[c]=arguments[c];window.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),\nwindow.location=a)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){for(var b=this.urlScheme+\"://\"+a,c,e=!0,f=1;f<arguments.length;f+=2)c=arguments[f+1],null!=c&&(e?(b+=\"?\",e=!1):b+=\"&\",b+=arguments[f]+\"=\"+escape(c));__im__iosNativeCall.executeNativeCall(b);return\"OK\"};this.nativeCallComplete=function(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={},b={allowOrientationChange:!0,forceOrientation:\"none\"},c=[],e=!1;a.zeroPad=function(f){var a=\"\";10>f&&(a+=\"0\");return a+f};a.supports=function(f){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof f)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",f)};a.useCustomClose=function(f){try{sdkController.useCustomClose(\"window.mraidview\",f)}catch(a){imraidview.showAlert(\"use CustomClose: \"+\na)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(f){imraidview.showAlert(\"close: \"+f)}};a.stackCommands=function(f,a){e?c.push(f):(eval(f),a&&(e=!0))};a.expand=function(f){try{\"undefined\"==typeof f&&(f=null),sdkController.expand(\"window.mraidview\",f)}catch(a){imraidview.showAlert(\"executeNativeExpand: \"+a+\", URL = \"+f)}};a.setExpandProperties=function(f){try{f?this.props=f:f=null;if(\"undefined\"!=typeof f.lockOrientation&&null!=f.lockOrientation&&\"undefined\"!=typeof f.orientation&&\nnull!=f.orientation){var d={};d.allowOrientationChange=!f.lockOrientation;d.forceOrientation=f.orientation;a.setOrientationProperties(d)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(f))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+f)}};a.getExpandProperties=function(){try{return eval(\"(\"+sdkController.getExpandProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(f){try{f?\nb=f:f=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(f))}catch(d){imraidview.showAlert(\"executeNativesetOrientationProperties: \"+d+\", props = \"+f)}};a.getOrientationProperties=function(){return b};a.resizeProps=null;a.setResizeProperties=function(f){var d,b;try{d=parseInt(f.width);b=parseInt(f.height);if(isNaN(d)||isNaN(b)||1>d||1>b)throw\"Invalid\";f.width=d;f.height=b;a.resizeProps=f;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(f))}catch(c){window.mraid.broadcastEvent(\"error\",\n\"Invalid properties.\",\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(d){imraidview.showAlert(\"open: \"+d)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+\na)}};a.getMaxSize=function(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var d=\"\";null!=a&&(d=a);try{sdkController.playVideo(\"window.mraidview\",\nd)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(f){if(\"undefined\"===typeof JSON){var d=\"\",b;if(\"undefined\"==typeof f.length)return a.stringifyArg(f);for(b=0;b<f.length;b++)0<b&&(d+=\",\"),d+=a.stringifyArg(f[b]);return d+\"]\"}return JSON.stringify(f)};a.stringifyArg=function(a){var d,b,c;b=typeof a;d=\"\";if(\"number\"===b||\"boolean\"===b)d+=args;else if(a instanceof Array)d=d+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;d+=\"{\";for(c in a)null!==a[c]&&(b||(d+=\",\"),d=d+'\"'+c+'\":',b=\ntypeof a[c],d=\"number\"===b||\"boolean\"===b?d+a[c]:\"function\"===typeof a[c]?d+'\"\"':a[c]instanceof Object?d+this.stringify(args[i][c]):d+'\"'+a[c]+'\"',b=!1);d+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),d=d+'\"'+a+'\"';imraidview.showAlert(\"json:\"+d);return d};getPID=function(a){var d=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(d=a.id);return d};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(f){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var d={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)d={};else{\"string\"==typeof a.summary&&(d.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(d.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(d.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(d.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?d.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}d.daysInMonth=a.recurrence.daysInMonth;d.daysInYear=a.recurrence.daysInYear;d.weeksInMonth=a.recurrence.weeksInMonth;d.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(d),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var d=0;d<a.length;d++)switch(a[d]){case 0:a[d]=\"SU\";break;case 1:a[d]=\"MO\";break;case 2:a[d]=\"TU\";break;case 3:a[d]=\"WE\";break;case 4:a[d]=\"TH\";\nbreak;case 5:a[d]=\"FR\";break;case 6:a[d]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(f){console.log(\"bridge: storePicture\");if(\"string\"!=typeof f)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+f+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nf);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+f+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,d){};a.fireMediaErrorEvent=function(a,d){};a.fireMediaTimeUpdateEvent=function(a,d,b){};a.fireMediaCloseEvent=function(a,d,b){};a.fireMediaVolumeChangeEvent=function(a,d,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview;a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;\na.setOrientationProperties=b.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=b.createCalendarEvent;a.open=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c)};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};a.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=\nb.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var e=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=function(){b=a.getWindowOrientation();\nwindow.addEventListener(\"resize\",e,!1);window.addEventListener(\"orientationchange\",e,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",e,!1);window.removeEventListener(\"orientationchange\",e,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,d){var b={};b.name=a;var c=\"inmobi_media_\"+a;\"undefined\"!=typeof d&&(null!=d&&\"\"!=d)&&(c=c+\"_\"+d);window.imraid.broadcastEvent(c,\nb)};a.fireMediaErrorEvent=function(a,d){var b={name:\"error\"};b.code=d;var c=\"inmobi_media_\"+b.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(c=c+\"_\"+a);window.imraid.broadcastEvent(c,b)};a.fireMediaTimeUpdateEvent=function(a,d,b){var c={name:\"timeupdate\",target:{}};c.target.currentTime=d;c.target.duration=b;d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.saveContent=function(a,d,b){window.imraid.addEventListener(\"saveContent_\"+a,b);\nsdkController.saveContent(\"window.imraidview\",a,d)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,d,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+a;c.width=d;c.height=b;window.imraid.broadcastEvent(\"galleryImageSelected\",c)};a.fireCameraPictureCatpturedEvent=function(a,d,b){var c=new Image;c.src=\"data:image/jpeg;base64,\"+\na;c.width=d;c.height=b;window.imraid.broadcastEvent(\"cameraPictureCaptured\",c)};a.fireMediaCloseEvent=function(a,d,b){var c={name:\"close\"};c.viaUserInteraction=d;c.target={};c.target.currentTime=b;d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.fireMediaVolumeChangeEvent=function(a,b,c){var e={name:\"volumechange\",target:{}};e.target.volume=b;e.target.muted=c;b=\"inmobi_media_\"+e.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+\na);window.imraid.broadcastEvent(b,e)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b){try{sdkController.openExternal(\"window.imraidview\",b)}catch(d){a.showAlert(\"openExternal: \"+d)}};\na.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(d){a.showAlert(\"log: \"+d)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(d){a.showAlert(\"asyncPing: \"+d)}};a.makeCall=function(b){try{b.startsWith(\"tel:\")?sdkController.openExternal(\"window.imraidview\",b):sdkController.openExternal(\"window.imraidview\",\"tel:\"+b)}catch(d){a.showAlert(\"makeCall: \"+d)}};a.sendMail=function(b,d,c){try{null==d&&(d=\"\"),null==\nc&&(c=\"\"),sdkController.sendMail(\"window.imraidview\",b,d,c)}catch(e){a.showAlert(\"sendMail: \"+e)}};a.sendSMS=function(b,d){try{null==d&&(d=\"\"),sdkController.sendSMS(\"window.imraidview\",b,d)}catch(c){a.showAlert(\"sendSMS: \"+c)}};a.pauseAudio=function(b){try{var d=getPID(b);sdkController.pauseAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"pauseAudio: \"+c)}};a.muteAudio=function(b){try{var d=getPID(b);sdkController.muteAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"muteAudio: \"+c)}};a.unMuteAudio=\nfunction(b){try{var d=getPID(b);sdkController.unMuteAudio(\"window.imraidview\",d)}catch(c){a.showAlert(\"unMuteAudio: \"+c)}};a.isAudioMuted=function(b){try{var d=getPID(b);return sdkController.isAudioMuted(\"window.imraidview\",d)}catch(c){a.showAlert(\"isAudioMuted: \"+c)}};a.setAudioVolume=function(b,d){try{var c=getPID(b);d=parseInt(d);sdkController.setAudioVolume(\"window.imraidview\",c,d)}catch(e){a.showAlert(\"setAudioVolume: \"+e)}};a.getAudioVolume=function(b){try{var d=getPID(b);return sdkController.getAudioVolume(\"window.imraidview\",\nd)}catch(c){a.showAlert(\"getAudioVolume: \"+c)}};a.seekAudio=function(b,d){try{var c=getPID(b);d=parseInt(d);isNaN(d)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekAudio\"):sdkController.seekAudio(\"window.imraidview\",c,d)}catch(e){a.showAlert(\"seekAudio: \"+e)}};a.playVideo=function(b,d){var c=!1,e=!0,h=!0,p=!1,l=-99999,k=-99999,m=-99999,q=-99999,r=\"normal\",s=\"exit\",t=\"\",u=getPID(d);null!=b&&(t=b);null!=d&&(\"undefined\"!=typeof d.audio&&\"muted\"==d.audio&&(c=!0),\"undefined\"!=\ntypeof d.autoplay&&!1===d.autoplay&&(e=!1),\"undefined\"!=typeof d.controls&&!1===d.controls&&(h=!1),\"undefined\"!=typeof d.loop&&!0===d.loop&&(p=!0),\"undefined\"!=typeof d.inline&&null!=d.inline&&(l=d.inline.top,k=d.inline.left),\"undefined\"!=typeof d.width&&null!=d.width&&(m=d.width),\"undefined\"!=typeof d.height&&null!=d.height&&(q=d.height),\"undefined\"!=typeof d.startStyle&&null!=d.startStyle&&(r=d.startStyle),\"undefined\"!=typeof d.stopStyle&&null!=d.stopStyle&&(s=d.stopStyle),l=parseInt(l).toString(),\nk=parseInt(k).toString(),m=parseInt(m).toString(),q=parseInt(q).toString());try{sdkController.playVideo(\"window.imraidview\",t,c,e,h,p,l,k,m,q,r,s,u)}catch(v){a.showAlert(\"playVideo: \"+v)}};a.playAudio=function(b,d){var c=!0,e=!1,h=\"normal\",p=\"normal\",l=!0,k=\"\",m=getPID(d);null!=b&&(k=b);null!=d&&(\"undefined\"!=typeof d.autoplay&&!1===d.autoplay&&(c=!1),\"undefined\"!=typeof d.loop&&!0===d.loop&&(e=!0),\"undefined\"!=typeof d.startStyle&&null!=d.startStyle&&(h=d.startStyle),\"undefined\"!=typeof d.stopStyle&&\nnull!=d.stopStyle&&(p=d.stopStyle),\"fullscreen\"==h&&(l=!0));try{sdkController.playAudio(\"window.imraidview\",k,c,l,e,h,p,m)}catch(q){a.showAlert(\"playAudio: \"+q)}};a.pauseVideo=function(b){try{var d=getPID(b);sdkController.pauseVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"pauseVideo: \"+c)}};a.closeVideo=function(b){try{var d=getPID(b);sdkController.closeVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"closeVideo: \"+c)}};a.hideVideo=function(b){try{var d=getPID(b);sdkController.hideVideo(\"window.imraidview\",\nd)}catch(c){a.showAlert(\"hideVideo: \"+c)}};a.showVideo=function(b){try{var d=getPID(b);sdkController.showVideo(\"window.imraidview\",d)}catch(c){a.showAlert(\"showVideo: \"+c)}};a.muteVideo=function(b){try{var c=getPID(b);sdkController.muteVideo(\"window.imraidview\",c)}catch(e){a.showAlert(\"muteVideo: \"+e)}};a.unMuteVideo=function(b){try{var c=getPID(b);sdkController.unMuteVideo(\"window.imraidview\",c)}catch(e){a.showAlert(\"unMuteVideo: \"+e)}};a.seekVideo=function(b,c){try{var e=getPID(b);c=parseInt(c);\nisNaN(c)?window.imraid.broadcastEvent(\"error\",\"seek position must be a number\",\"seekVideo\"):sdkController.seekVideo(\"window.imraidview\",e,c)}catch(g){a.showAlert(\"seekVideo: \"+g)}};a.isVideoMuted=function(b){try{var c=getPID(b);return sdkController.isVideoMuted(\"window.imraidview\",c)}catch(e){a.showAlert(\"isVideoMuted: \"+e)}};a.setVideoVolume=function(b,c){try{var e=getPID(b);c=parseInt(c);sdkController.setVideoVolume(\"window.imraidview\",e,c)}catch(g){a.showAlert(\"setVideoVolume: \"+g)}};a.getVideoVolume=\nfunction(b){try{var c=getPID(b);return sdkController.getVideoVolume(\"window.imraidview\",c)}catch(e){a.showAlert(\"getVideoVolume: \"+e)}};a.startListeningMicIntensity=function(){sdkController.registerMicListener(\"window.imraidview\")};a.stopListeningMicIntensity=function(){sdkController.unRegisterMicListener(\"window.imraidview\")};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=function(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};\na.startListeningVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};getSdkVersionInt=function(){for(var b=\na.getSdkVersion().split(\".\"),c=b.length,e=\"\",g=0;g<c;g++)e+=b[g];return parseInt(e)};a.vibrate=function(a){if(null==a||420>getSdkVersionInt())sdkController.vibrate(\"window.imraidview\");else{if(0===arguments.length)return sdkController.vibrate(\"window.imraidview\"),null;if(1==arguments.length)0===a||\"length\"in a&&0===a.length?sdkController.vibrate(\"window.imraidview\",\"[]\",-1):sdkController.vibrate(\"window.imraidview\",\"[0,\"+a+\"]\",-1);else{var b=Array.prototype.slice.call(arguments);sdkController.vibrate(\"window.imraidview\",\n\"[0,\"+String(b)+\"]\",-1)}}};a.takeCameraPicture=function(){sdkController.takeCameraPicture(\"window.imraidview\")};a.getGalleryImage=function(){return sdkController.getGalleryImage(\"window.imraidview\")};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.imraidview\",\na)};a.postToSocial=function(a,b,c,e){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof e&&(e=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,e))};a.getMicIntensity=function(){return sdkController.getMicIntensity(\"window.imraidview\")};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",null);else try{sdkController.incentCompleted(\"window.imraidview\",\nJSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+b)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+\nc+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=sdkController.isDeviceMuted(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,arguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,\narguments)};a.disableBackButton=function(a){void 0==a||\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};a.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.saveContent=function(a,e,f){var d=arguments.length;3>d?\"function\"===typeof arguments[d-1]&&(d=arguments[d-1],window.imraid.addEventListener(\"saveContent\",d),window.imraid.sendSaveContentResult(\"saveContent\",\"fail\",JSON.stringify({reason:1}))):b.saveContent(a,e,f)};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\n\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.makeCall=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must provide a number to call.\",\"makeCall\"):b.makeCall(c)};a.sendMail=function(c,e,f){\"string\"!=typeof c?a.broadcastEvent(\"error\",\n\"Request must specify a recipient.\",\"sendMail\"):b.sendMail(c,e,f)};a.sendSMS=function(c,e){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a recipient.\",\"sendSMS\"):b.sendSMS(c,e)};a.playAudio=function(a,e){\"object\"!=typeof e?\"string\"==typeof a?b.playAudio(a,null):\"object\"==typeof a?b.playAudio(null,a):b.playAudio(null,null):b.playAudio(a,e)};a.getGalleryImage=b.getGalleryImage;a.pauseAudio=b.pauseAudio;a.muteAudio=b.muteAudio;a.unMuteAudio=b.unMuteAudio;a.isAudioMuted=b.isAudioMuted;\na.setAudioVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var e=c.volume;isNaN(e)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setAudioVolume\"):(0>e?e=0:100<e&&(e=100),b.setAudioVolume(c,e))}};a.getAudioVolume=b.getAudioVolume;a.pauseVideo=b.pauseVideo;a.closeVideo=b.closeVideo;a.hideVideo=b.hideVideo;a.showVideo=b.showVideo;a.muteVideo=b.muteVideo;a.unMuteVideo=b.unMuteVideo;\na.isVideoMuted=b.isVideoMuted;a.setVideoVolume=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\"setAudioVolume\");else{var e=c.volume;isNaN(e)?a.broadcastEvent(\"error\",\"Request must specify a valid volume in the range [0,100]\",\"setVideoVolume\"):(0>e?e=0:100<e&&(e=100),b.setVideoVolume(c,e))}};a.getVideoVolume=b.getVideoVolume;a.seekAudio=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid properties\",\n\"seekAudio\");else{var e=c.time;imIsObjValid(e)?b.seekAudio(c,e):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekAudio\")}};a.seekVideo=function(c){if(\"object\"!=typeof c&&null!=c)a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\");else{var e=c.time;imIsObjValid(e)?b.seekVideo(c,e):a.broadcastEvent(\"error\",\"Request must specify a valid time\",\"seekVideo\")}};a.openExternal=b.openExternal;a.updateToPassbook=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\n\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c)};a.vibrate=b.vibrate;a.takeCameraPicture=b.takeCameraPicture;a.getMicIntensity=function(){return!imIsObjValid(a.listeners.micIntensityChange)?-1:b.getMicIntensity()};a.postToSocial=b.postToSocial;a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=b.showSKStore;a.playVideo=function(a,e){\"object\"!=typeof e?\"string\"==typeof a?b.playVideo(a,null):\"object\"==typeof a?b.playVideo(null,\na):b.playVideo(null,null):b.playVideo(a,e)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=function(){return b.getDeviceVolume()};a.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=\nb.isBackButtonDisabled})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(e){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a){sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||null==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",\nJSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var f=-1;try{f=a.indexOf(b)}catch(d){}return f};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,e,f){if(!imIsObjValid(e)||!imIsObjValid(f))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+e+\",\"+f):a+(\"&u-tap-o=\"+e+\",\"+f));return a};b.performAdClick=function(a,e){e=e||event;if(imIsObjValid(a)){var f=a.clickConfig,d=a.landingConfig;if(!imIsObjValid(f)&&!imIsObjValid(d))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var n=null,g=null,h=null,p=null,l=null,k=null,m=null;if(imIsObjValid(e))try{p=e.changedTouches[0].pageX,l=e.changedTouches[0].pageY}catch(q){l=\np=0}imIsObjValid(d)?imIsObjValid(f)?(k=d.url,m=d.urlType,n=f.url,g=f.pingWV,h=f.fr):(k=d.url,m=d.urlType):(k=f.url,m=f.urlType);f=b.getPlatform();try{if(\"boolean\"!=typeof h&&\"number\"!=typeof h||null==h)h=!0;if(0>h||1<h)h=!0;if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"number\"!=typeof m||null==m)m=0;n=b.appendTapParams(n,p,l);imIsObjValid(n)?!0==g?b.pingInWebView(n):b.ping(n,h):b.log(\"clickurl provided is null.\");if(imIsObjValid(k))switch(imIsObjValid(n)||(k=b.appendTapParams(k,\np,l)),m){case 1:b.openEmbedded(k);break;case 2:\"ios\"==f?b.ios.openItunesProductView(k):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+f);break;default:b.openExternal(k)}else b.log(\"Landing url provided is null.\")}catch(r){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,e){e=e||event;if(imIsObjValid(a)){var f=a.clickConfig,d=a.landingConfig;if(!imIsObjValid(f)&&!imIsObjValid(d))b.log(\"click/landing config are invalid, Nothing to process .\"),\nthis.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var n=null,g=null,h=null,p=null,l=null;if(imIsObjValid(e))try{p=e.changedTouches[0].pageX,l=e.changedTouches[0].pageY}catch(k){l=p=0}imIsObjValid(f)&&(n=f.url,g=f.pingWV,h=f.fr);try{if(\"boolean\"!=typeof h&&\"number\"!=typeof h||null==h)h=!0;if(0>h||1<h)h=!0;if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;n=b.appendTapParams(n,p,l);imIsObjValid(n)?!0==g?b.pingInWebView(n):b.ping(n,h):\nb.log(\"clickurl provided is null.\");b.onUserInteraction(d)}catch(m){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=a.openEmbedded;b.openExternal=a.openExternal;b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.ios.openItunesProductView=\na.ios.openItunesProductView;b.fireAdReady=a.fireAdReady;b.fireAdFailed=a.fireAdFailed})();";
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Returning default Mraid Js string.");
            return str;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Returning fetched Mraid Js string.");
        return str;
    }

    public void m1646a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        if (m1672g("calendar")) {
            this.f1408m.m1844a(str, this.f1399d, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "createCalendarEvent called even when it is not supported");
    }

    public void m1641a(String str, int i, String str2, String str3, String str4) {
        if (m1672g("postToSocial")) {
            this.f1408m.m1842a(str, this.f1399d, i, str2, str3, str4);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "postToSocial called even when it is not supported");
    }

    public void m1665e(String str) {
        if (!m1672g("vibrate")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "vibrate called despite the fact that it is not supported");
        } else if (m1648a()) {
            this.f1408m.m1841a(str, this.f1399d);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Creative not visible. Will not vibrate.");
            m1645a(str, "Creative not visible. Will not vibrate.", "vibrate");
        }
    }

    public void m1657c(String str, String str2, int i) {
        if (!m1672g("vibrate")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "vibrate called despite the fact that it is not supported");
        } else if (m1648a()) {
            this.f1408m.m1843a(str, this.f1399d, str2, i);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Creative not visible. Will not vibrate.");
            m1645a(str, "Creative not visible. Will not vibrate.", "vibrate");
        }
    }

    private void m1635w() {
        this.f1407l.m1749c();
    }

    private void m1636x() {
        if (RenderViewState.DEFAULT != this.f1403h) {
            this.f1390L = true;
            this.f1405j.m1812a();
            m1634v();
            this.f1390L = false;
        }
    }

    private void m1637y() {
        if (RenderViewState.DEFAULT != this.f1403h) {
            this.f1390L = true;
            this.f1406k.m1831b();
            setAndUpdateViewState(RenderViewState.DEFAULT);
            this.f1402g.m1602f(this);
            this.f1390L = false;
        }
    }

    public void m1662d(String str, String str2, String str3) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "saveContent called: content ID: " + str2 + "; URL: " + str3);
        JSONObject jSONObject;
        if (m1672g("saveContent")) {
            File file = new File(SdkContext.m1252a(this.f1399d), String.valueOf(hashCode()));
            if (file.mkdirs() || file.isDirectory()) {
                DownloadTask downloadTask = new DownloadTask(str, new File(file, UUID.randomUUID().toString()), str3, str2, this);
                f1378c.add(downloadTask);
                downloadTask.execute(new Void[0]);
                return;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "Cannot create temp directory to save ");
            jSONObject = new JSONObject();
            try {
                jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, str3);
                jSONObject.put("reason", 9);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            m1642a(str, "sendSaveContentResult(\"saveContent_" + str2 + "\", 'failure', \"" + jSONObject.toString().replace("\"", "\\\"") + "\");");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "saveContent called despite the fact that it is not supported");
        jSONObject = new JSONObject();
        try {
            jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, str3);
            jSONObject.put("reason", 5);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        m1642a(str, "sendSaveContentResult(\"saveContent_" + str2 + "\", 'failure', \"" + jSONObject.toString().replace("\"", "\\\"") + "\");");
    }

    public void m1667f(String str) {
        for (DownloadTask downloadTask : f1378c) {
            if (str != null && str.trim().length() != 0 && str.equals(downloadTask.m1769a())) {
                downloadTask.cancel(true);
                return;
            }
        }
    }

    private void m1638z() {
        for (DownloadTask cancel : f1378c) {
            cancel.cancel(true);
        }
        f1378c.clear();
        SdkContext.m1255a(SdkContext.m1252a(this.f1399d), String.valueOf(hashCode()));
    }

    private void m1624i(String str) {
        loadUrl(str);
    }

    @TargetApi(19)
    private void m1626j(String str) {
        evaluateJavascript(str, null);
    }

    public void m1683o() {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "disableHardwareAcceleration called.");
        this.f1391M = false;
        String str = "setLayerType";
        if (VERSION.SDK_INT >= 14) {
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1377a, "disableHardwareAcceleration failed.", e);
            } catch (Throwable e2) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1377a, "disableHardwareAcceleration failed.", e2);
            } catch (Throwable e22) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1377a, "disableHardwareAcceleration failed.", e22);
            } catch (Throwable e222) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1377a, "disableHardwareAcceleration failed.", e222);
            }
        }
    }

    public boolean m1684p() {
        return this.f1391M;
    }

    public boolean m1672g(String str) {
        boolean z = true;
        PackageManager packageManager = this.f1399d.getPackageManager();
        boolean z2 = true;
        switch (str.hashCode()) {
            case -1886160473:
                if (str.equals("playVideo")) {
                    z2 = true;
                    break;
                }
                break;
            case -1647691422:
                if (str.equals("inlineVideo")) {
                    z2 = true;
                    break;
                }
                break;
            case -587360353:
                if (str.equals("getGalleryImage")) {
                    z2 = true;
                    break;
                }
                break;
            case -178324674:
                if (str.equals("calendar")) {
                    z2 = true;
                    break;
                }
                break;
            case 114009:
                if (str.equals("sms")) {
                    z2 = true;
                    break;
                }
                break;
            case 114715:
                if (str.equals("tel")) {
                    z2 = false;
                    break;
                }
                break;
            case 451310959:
                if (str.equals("vibrate")) {
                    z2 = true;
                    break;
                }
                break;
            case 459238621:
                if (str.equals("storePicture")) {
                    z2 = true;
                    break;
                }
                break;
            case 1247233375:
                if (str.equals("sendMail")) {
                    z2 = true;
                    break;
                }
                break;
            case 1370921258:
                if (str.equals("microphone")) {
                    z2 = true;
                    break;
                }
                break;
            case 1509574865:
                if (str.equals("html5video")) {
                    z2 = true;
                    break;
                }
                break;
            case 1642189884:
                if (str.equals("saveContent")) {
                    z2 = true;
                    break;
                }
                break;
            case 1895570642:
                if (str.equals("takeCameraPicture")) {
                    z2 = true;
                    break;
                }
                break;
            case 1921345160:
                if (str.equals("postToSocial")) {
                    z2 = true;
                    break;
                }
                break;
        }
        Intent intent;
        ResolveInfo resolveActivity;
        switch (z2) {
            case DurationDV.DURATION_TYPE /*0*/:
                intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:123456789"));
                if (this.f1399d.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                    return false;
                }
                return true;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                return true;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                z2 = packageManager.hasSystemFeature("android.hardware.microphone") && packageManager.checkPermission("android.permission.RECORD_AUDIO", packageManager.getNameForUid(Binder.getCallingUid())) == 0;
                return z2;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                if (VERSION.SDK_INT >= 11 && !(this.f1415t && m1684p())) {
                    z = false;
                }
                Logger.m1440a(InternalLogLevel.INTERNAL, f1377a, "HTML5 video supported:" + z);
                return z;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                if (this.f1399d.getPackageManager().resolveActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:123456789")), AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                    return false;
                }
                return true;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                if (VERSION.SDK_INT >= 19 || packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
                    return true;
                }
                return false;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                if (this.f1399d.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                    return false;
                }
                return true;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                intent = new Intent("android.intent.action.VIEW");
                intent.setType("vnd.android.cursor.item/event");
                ResolveInfo resolveActivity2 = this.f1399d.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT);
                z2 = packageManager.checkPermission("android.permission.WRITE_CALENDAR", packageManager.getNameForUid(Binder.getCallingUid())) == 0;
                boolean z3;
                if (packageManager.checkPermission("android.permission.READ_CALENDAR", packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (resolveActivity2 != null && z2 && r3) {
                    return true;
                }
                return false;
            case MetaData.DEFAULT_MAX_ADS /*10*/:
                resolveActivity = this.f1399d.getPackageManager().resolveActivity(new Intent("android.media.action.IMAGE_CAPTURE"), AccessibilityNodeInfoCompat.ACTION_CUT);
                z2 = packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageManager.getNameForUid(Binder.getCallingUid())) == 0;
                if (resolveActivity == null || !z2) {
                    return false;
                }
                return true;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                resolveActivity = this.f1399d.getPackageManager().resolveActivity(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), AccessibilityNodeInfoCompat.ACTION_CUT);
                z2 = packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageManager.getNameForUid(Binder.getCallingUid())) == 0;
                if (resolveActivity == null || !z2) {
                    return false;
                }
                return true;
            case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                if (VERSION.SDK_INT < 9 || packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageManager.getNameForUid(Binder.getCallingUid())) != 0) {
                    return false;
                }
                return true;
            case ConnectionResult.CANCELED /*13*/:
                Vibrator vibrator = (Vibrator) this.f1399d.getSystemService("vibrator");
                if (!(packageManager.checkPermission("android.permission.VIBRATE", packageManager.getNameForUid(Binder.getCallingUid())) == 0) || vibrator == null || VERSION.SDK_INT < 11 || !m1608a(vibrator)) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    @TargetApi(11)
    private boolean m1608a(Vibrator vibrator) {
        return vibrator.hasVibrator();
    }
}
