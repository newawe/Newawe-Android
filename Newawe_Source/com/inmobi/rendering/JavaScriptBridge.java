package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import com.Newawe.storage.DatabaseOpenHelper;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.inmobi.commons.core.network.AsyncNetworkTask.AsyncNetworkTask;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.NetworkResponse;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.commons.p000a.SdkInfo;
import com.inmobi.rendering.InMobiAdActivity.C0698b;
import com.inmobi.rendering.RenderView.RenderViewState;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.inmobi.rendering.a.AnonymousClass10;
import com.inmobi.rendering.a.AnonymousClass11;
import com.inmobi.rendering.a.AnonymousClass12;
import com.inmobi.rendering.a.AnonymousClass13;
import com.inmobi.rendering.a.AnonymousClass14;
import com.inmobi.rendering.a.AnonymousClass15;
import com.inmobi.rendering.a.AnonymousClass16;
import com.inmobi.rendering.a.AnonymousClass17;
import com.inmobi.rendering.a.AnonymousClass18;
import com.inmobi.rendering.a.AnonymousClass19;
import com.inmobi.rendering.a.AnonymousClass20;
import com.inmobi.rendering.a.AnonymousClass21;
import com.inmobi.rendering.a.AnonymousClass22;
import com.inmobi.rendering.a.AnonymousClass24;
import com.inmobi.rendering.a.AnonymousClass25;
import com.inmobi.rendering.a.AnonymousClass27;
import com.inmobi.rendering.a.AnonymousClass29;
import com.inmobi.rendering.a.AnonymousClass30;
import com.inmobi.rendering.mraid.Dimensions;
import com.inmobi.rendering.mraid.ExpandProperties;
import com.inmobi.rendering.mraid.ImageProcessing;
import com.inmobi.rendering.mraid.MediaPlayerProperties;
import com.inmobi.rendering.mraid.MraidMediaProcessor.MediaContentType;
import com.inmobi.rendering.mraid.OrientationProperties;
import com.inmobi.rendering.mraid.ResizeProperties;
import com.inmobi.rendering.p005a.ClickManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.a */
public class JavaScriptBridge {
    static final String[] f1544a;
    private static final String f1545b;
    private RenderView f1546c;
    private RenderingProperties f1547d;
    private OrientationProperties f1548e;
    private DownloadManager f1549f;
    private BroadcastReceiver f1550g;
    private Context f1551h;

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ String f1423a;
        final /* synthetic */ String f1424b;
        final /* synthetic */ JavaScriptBridge f1425c;

        AnonymousClass10(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1425c = javaScriptBridge;
            this.f1423a = str;
            this.f1424b = str2;
        }

        public void run() {
            this.f1425c.f1546c.m1674h(this.f1423a, this.f1424b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.11 */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ String f1426a;
        final /* synthetic */ String f1427b;
        final /* synthetic */ JavaScriptBridge f1428c;

        AnonymousClass11(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1428c = javaScriptBridge;
            this.f1426a = str;
            this.f1427b = str2;
        }

        public void run() {
            this.f1428c.f1546c.m1676i(this.f1426a, this.f1427b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String f1429a;
        final /* synthetic */ String f1430b;
        final /* synthetic */ JavaScriptBridge f1431c;

        AnonymousClass12(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1431c = javaScriptBridge;
            this.f1429a = str;
            this.f1430b = str2;
        }

        public void run() {
            this.f1431c.f1546c.m1658c("openEmbedded", this.f1429a, this.f1430b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.13 */
    class AnonymousClass13 implements Runnable {
        final /* synthetic */ String f1432a;
        final /* synthetic */ String f1433b;
        final /* synthetic */ JavaScriptBridge f1434c;

        AnonymousClass13(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1434c = javaScriptBridge;
            this.f1432a = str;
            this.f1433b = str2;
        }

        public void run() {
            this.f1434c.f1546c.m1656c(this.f1432a, this.f1433b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.14 */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ String f1435a;
        final /* synthetic */ String f1436b;
        final /* synthetic */ JavaScriptBridge f1437c;

        AnonymousClass14(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1437c = javaScriptBridge;
            this.f1435a = str;
            this.f1436b = str2;
        }

        public void run() {
            this.f1437c.f1546c.m1661d(this.f1435a, this.f1436b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.15 */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ String f1438a;
        final /* synthetic */ String f1439b;
        final /* synthetic */ int f1440c;
        final /* synthetic */ JavaScriptBridge f1441d;

        AnonymousClass15(JavaScriptBridge javaScriptBridge, String str, String str2, int i) {
            this.f1441d = javaScriptBridge;
            this.f1438a = str;
            this.f1439b = str2;
            this.f1440c = i;
        }

        public void run() {
            this.f1441d.f1546c.m1651b(this.f1438a, this.f1439b, this.f1440c);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.16 */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ String f1442a;
        final /* synthetic */ String f1443b;
        final /* synthetic */ String f1444c;
        final /* synthetic */ boolean f1445d;
        final /* synthetic */ boolean f1446e;
        final /* synthetic */ String f1447f;
        final /* synthetic */ String f1448g;
        final /* synthetic */ boolean f1449h;
        final /* synthetic */ JavaScriptBridge f1450i;

        AnonymousClass16(JavaScriptBridge javaScriptBridge, String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, boolean z3) {
            this.f1450i = javaScriptBridge;
            this.f1442a = str;
            this.f1443b = str2;
            this.f1444c = str3;
            this.f1445d = z;
            this.f1446e = z2;
            this.f1447f = str4;
            this.f1448g = str5;
            this.f1449h = z3;
        }

        public void run() {
            if ((this.f1442a == null || this.f1442a.trim().length() == 0) && (this.f1443b == null || this.f1443b.trim().length() == 0 || !this.f1443b.startsWith(HttpHost.DEFAULT_SCHEME_NAME))) {
                this.f1450i.f1546c.m1645a(this.f1444c, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Media player properties");
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "shouldAutoPlay: " + this.f1445d + "; shouldLoopPlayback: " + this.f1446e + "; startStyle: " + this.f1447f + "; stopStyle: " + this.f1448g);
            Dimensions dimensions = new Dimensions();
            dimensions.f1585a = 0;
            dimensions.f1586b = 0;
            dimensions.f1587c = 24;
            dimensions.f1588d = 24;
            MediaPlayerProperties mediaPlayerProperties = new MediaPlayerProperties();
            if (this.f1442a == null || this.f1442a.length() != 0) {
                mediaPlayerProperties.f1608a = this.f1442a;
            }
            if (!this.f1450i.f1546c.getMediaProcessor().m1744a()) {
                mediaPlayerProperties.f1611d = this.f1445d;
                mediaPlayerProperties.f1613f = this.f1446e;
                mediaPlayerProperties.f1609b = this.f1447f;
                mediaPlayerProperties.f1610c = this.f1448g;
                mediaPlayerProperties.f1612e = this.f1449h;
            }
            this.f1450i.f1546c.getMediaProcessor().m1737a(dimensions);
            this.f1450i.f1546c.getMediaProcessor().m1738a(mediaPlayerProperties);
            this.f1450i.f1546c.m1644a(this.f1444c, this.f1443b, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.17 */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ String f1451a;
        final /* synthetic */ String f1452b;
        final /* synthetic */ int f1453c;
        final /* synthetic */ JavaScriptBridge f1454d;

        AnonymousClass17(JavaScriptBridge javaScriptBridge, String str, String str2, int i) {
            this.f1454d = javaScriptBridge;
            this.f1451a = str;
            this.f1452b = str2;
            this.f1453c = i;
        }

        public void run() {
            this.f1454d.f1546c.m1643a(this.f1451a, this.f1452b, this.f1453c);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.18 */
    class AnonymousClass18 implements Runnable {
        final /* synthetic */ String f1455a;
        final /* synthetic */ String f1456b;
        final /* synthetic */ JavaScriptBridge f1457c;

        AnonymousClass18(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1457c = javaScriptBridge;
            this.f1455a = str;
            this.f1456b = str2;
        }

        public void run() {
            this.f1457c.f1546c.m1650b(this.f1455a, this.f1456b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.19 */
    class AnonymousClass19 implements Runnable {
        final /* synthetic */ String f1458a;
        final /* synthetic */ String f1459b;
        final /* synthetic */ JavaScriptBridge f1460c;

        AnonymousClass19(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1460c = javaScriptBridge;
            this.f1458a = str;
            this.f1459b = str2;
        }

        public void run() {
            this.f1460c.f1546c.m1656c(this.f1458a, this.f1459b);
        }
    }

    /* renamed from: com.inmobi.rendering.a.1 */
    class JavaScriptBridge implements Runnable {
        final /* synthetic */ String f1461a;
        final /* synthetic */ String f1462b;
        final /* synthetic */ JavaScriptBridge f1463c;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1463c = javaScriptBridge;
            this.f1461a = str;
            this.f1462b = str2;
        }

        public void run() {
            this.f1463c.f1546c.m1658c("open", this.f1461a, this.f1462b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.20 */
    class AnonymousClass20 implements Runnable {
        final /* synthetic */ String f1464a;
        final /* synthetic */ String f1465b;
        final /* synthetic */ JavaScriptBridge f1466c;

        AnonymousClass20(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1466c = javaScriptBridge;
            this.f1464a = str;
            this.f1465b = str2;
        }

        public void run() {
            this.f1466c.f1546c.m1661d(this.f1464a, this.f1465b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.21 */
    class AnonymousClass21 implements Runnable {
        final /* synthetic */ String f1467a;
        final /* synthetic */ String f1468b;
        final /* synthetic */ int f1469c;
        final /* synthetic */ JavaScriptBridge f1470d;

        AnonymousClass21(JavaScriptBridge javaScriptBridge, String str, String str2, int i) {
            this.f1470d = javaScriptBridge;
            this.f1467a = str;
            this.f1468b = str2;
            this.f1469c = i;
        }

        public void run() {
            this.f1470d.f1546c.m1651b(this.f1467a, this.f1468b, this.f1469c);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.22 */
    class AnonymousClass22 implements Runnable {
        final /* synthetic */ boolean f1471a;
        final /* synthetic */ JavaScriptBridge f1472b;

        AnonymousClass22(JavaScriptBridge javaScriptBridge, boolean z) {
            this.f1472b = javaScriptBridge;
            this.f1471a = z;
        }

        public void run() {
            this.f1472b.f1546c.m1647a(this.f1471a);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.24 */
    class AnonymousClass24 implements Runnable {
        final /* synthetic */ String f1474a;
        final /* synthetic */ JavaScriptBridge f1475b;

        AnonymousClass24(JavaScriptBridge javaScriptBridge, String str) {
            this.f1475b = javaScriptBridge;
            this.f1474a = str;
        }

        public void run() {
            this.f1475b.f1546c.m1665e(this.f1474a);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.25 */
    class AnonymousClass25 implements Runnable {
        final /* synthetic */ String f1476a;
        final /* synthetic */ String f1477b;
        final /* synthetic */ int f1478c;
        final /* synthetic */ JavaScriptBridge f1479d;

        AnonymousClass25(JavaScriptBridge javaScriptBridge, String str, String str2, int i) {
            this.f1479d = javaScriptBridge;
            this.f1476a = str;
            this.f1477b = str2;
            this.f1478c = i;
        }

        public void run() {
            this.f1479d.f1546c.m1657c(this.f1476a, this.f1477b, this.f1478c);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.27 */
    class AnonymousClass27 implements Runnable {
        final /* synthetic */ String f1481a;
        final /* synthetic */ String f1482b;
        final /* synthetic */ JavaScriptBridge f1483c;

        AnonymousClass27(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1483c = javaScriptBridge;
            this.f1481a = str;
            this.f1482b = str2;
        }

        public void run() {
            this.f1483c.f1546c.m1678j(this.f1481a, this.f1482b);
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.29 */
    class AnonymousClass29 implements Runnable {
        final /* synthetic */ boolean f1485a;
        final /* synthetic */ JavaScriptBridge f1486b;

        AnonymousClass29(JavaScriptBridge javaScriptBridge, boolean z) {
            this.f1486b = javaScriptBridge;
            this.f1485a = z;
        }

        public void run() {
            this.f1486b.f1546c.m1653b(this.f1485a);
        }
    }

    /* renamed from: com.inmobi.rendering.a.2 */
    class JavaScriptBridge extends BroadcastReceiver {
        final /* synthetic */ String f1487a;
        final /* synthetic */ JavaScriptBridge f1488b;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str) {
            this.f1488b = javaScriptBridge;
            this.f1487a = str;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                long longExtra = intent.getLongExtra("extra_download_id", 0);
                Query query = new Query();
                query.setFilterById(new long[]{longExtra});
                Cursor query2 = this.f1488b.f1549f.query(query);
                if (query2.moveToFirst()) {
                    int columnIndex = query2.getColumnIndex(NotificationCompatApi21.CATEGORY_STATUS);
                    if (16 == query2.getInt(columnIndex)) {
                        this.f1488b.f1546c.m1645a(this.f1487a, "File failed to download", "storePicture");
                    } else if (8 == query2.getInt(columnIndex)) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Download completed");
                    } else if (1 == query2.getInt(columnIndex)) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Download queued");
                    } else if (2 == query2.getInt(columnIndex)) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Download ongoing");
                    }
                }
            }
        }
    }

    /* compiled from: JavaScriptBridge */
    /* renamed from: com.inmobi.rendering.a.30 */
    class AnonymousClass30 implements Runnable {
        final /* synthetic */ String f1489a;
        final /* synthetic */ String f1490b;
        final /* synthetic */ JavaScriptBridge f1491c;

        AnonymousClass30(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1491c = javaScriptBridge;
            this.f1489a = str;
            this.f1490b = str2;
        }

        public void run() {
            this.f1491c.f1546c.m1644a(this.f1489a, this.f1490b, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO);
        }
    }

    /* renamed from: com.inmobi.rendering.a.6 */
    class JavaScriptBridge implements Runnable {
        final /* synthetic */ String f1493a;
        final /* synthetic */ String f1494b;
        final /* synthetic */ String f1495c;
        final /* synthetic */ String f1496d;
        final /* synthetic */ String f1497e;
        final /* synthetic */ boolean f1498f;
        final /* synthetic */ boolean f1499g;
        final /* synthetic */ String f1500h;
        final /* synthetic */ String f1501i;
        final /* synthetic */ String f1502j;
        final /* synthetic */ String f1503k;
        final /* synthetic */ boolean f1504l;
        final /* synthetic */ boolean f1505m;
        final /* synthetic */ JavaScriptBridge f1506n;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, String str6, String str7, String str8, String str9, boolean z3, boolean z4) {
            this.f1506n = javaScriptBridge;
            this.f1493a = str;
            this.f1494b = str2;
            this.f1495c = str3;
            this.f1496d = str4;
            this.f1497e = str5;
            this.f1498f = z;
            this.f1499g = z2;
            this.f1500h = str6;
            this.f1501i = str7;
            this.f1502j = str8;
            this.f1503k = str9;
            this.f1504l = z3;
            this.f1505m = z4;
        }

        public void run() {
            int i = 0;
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "JavaScript called: playVideo (" + this.f1493a + ", " + this.f1494b + ")");
            if ((this.f1494b == null || this.f1494b.trim().length() == 0) && (this.f1493a == null || this.f1493a.trim().length() == 0 || !this.f1493a.startsWith(HttpHost.DEFAULT_SCHEME_NAME))) {
                this.f1506n.f1546c.m1645a(this.f1495c, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Video dimensions: (" + this.f1496d + ", " + this.f1497e + ")");
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "Media player properties");
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "shouldAutoPlay: " + this.f1498f + "; shouldLoopPlayback: " + this.f1499g + "; startStyle: " + this.f1500h + "; stopStyle: " + this.f1501i);
            Dimensions dimensions = new Dimensions();
            MediaPlayerProperties mediaPlayerProperties = new MediaPlayerProperties();
            if (this.f1494b == null || this.f1494b.length() != 0) {
                mediaPlayerProperties.f1608a = this.f1494b;
            }
            int a = JavaScriptBridge.m1710b(this.f1496d);
            int a2 = JavaScriptBridge.m1710b(this.f1497e);
            int a3 = JavaScriptBridge.m1710b(this.f1502j);
            int a4 = JavaScriptBridge.m1710b(this.f1503k);
            if (!(-99999 == a && -99999 == a2) && a > 0 && a2 > 0) {
                float c = DisplayInfo.m1481a().m1497c();
                if (a3 == -99999) {
                    a3 = 0;
                }
                if (a4 != -99999) {
                    i = a4;
                }
                dimensions.f1585a = (int) ((((float) a3) * c) + 0.5f);
                dimensions.f1586b = (int) ((((float) i) * c) + 0.5f);
                dimensions.f1587c = (int) ((((float) a) * c) + 0.5f);
                dimensions.f1588d = (int) ((((float) a2) * c) + 0.5f);
                mediaPlayerProperties.f1609b = this.f1500h;
            } else {
                mediaPlayerProperties.f1609b = "fullscreen";
            }
            if (!this.f1506n.f1546c.getMediaProcessor().m1744a()) {
                mediaPlayerProperties.f1614g = this.f1504l;
                mediaPlayerProperties.f1611d = this.f1498f;
                mediaPlayerProperties.f1613f = this.f1499g;
                mediaPlayerProperties.f1610c = this.f1501i;
                mediaPlayerProperties.f1612e = this.f1505m;
            }
            this.f1506n.f1546c.getMediaProcessor().m1737a(dimensions);
            this.f1506n.f1546c.getMediaProcessor().m1738a(mediaPlayerProperties);
            this.f1506n.f1546c.m1644a(this.f1495c, this.f1493a, MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO);
        }
    }

    /* renamed from: com.inmobi.rendering.a.7 */
    class JavaScriptBridge implements Runnable {
        final /* synthetic */ String f1507a;
        final /* synthetic */ String f1508b;
        final /* synthetic */ int f1509c;
        final /* synthetic */ JavaScriptBridge f1510d;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str, String str2, int i) {
            this.f1510d = javaScriptBridge;
            this.f1507a = str;
            this.f1508b = str2;
            this.f1509c = i;
        }

        public void run() {
            this.f1510d.f1546c.m1643a(this.f1507a, this.f1508b, this.f1509c);
        }
    }

    /* renamed from: com.inmobi.rendering.a.8 */
    class JavaScriptBridge implements Runnable {
        final /* synthetic */ String f1511a;
        final /* synthetic */ String f1512b;
        final /* synthetic */ JavaScriptBridge f1513c;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1513c = javaScriptBridge;
            this.f1511a = str;
            this.f1512b = str2;
        }

        public void run() {
            this.f1513c.f1546c.m1650b(this.f1511a, this.f1512b);
        }
    }

    /* renamed from: com.inmobi.rendering.a.9 */
    class JavaScriptBridge implements Runnable {
        final /* synthetic */ String f1514a;
        final /* synthetic */ String f1515b;
        final /* synthetic */ JavaScriptBridge f1516c;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str, String str2) {
            this.f1516c = javaScriptBridge;
            this.f1514a = str;
            this.f1515b = str2;
        }

        public void run() {
            this.f1516c.f1546c.m1671g(this.f1514a, this.f1515b);
        }
    }

    @TargetApi(16)
    /* renamed from: com.inmobi.rendering.a.a */
    private static class JavaScriptBridge implements OnGlobalLayoutListener {
        private int f1517a;
        private int f1518b;
        private View f1519c;
        private final Boolean f1520d;

        JavaScriptBridge(View view) {
            this.f1520d = Boolean.valueOf(false);
            this.f1519c = view;
        }

        public void onGlobalLayout() {
            this.f1517a = DisplayInfo.m1480a(this.f1519c.getWidth());
            this.f1518b = DisplayInfo.m1480a(this.f1519c.getHeight());
            if (VERSION.SDK_INT >= 16) {
                this.f1519c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f1519c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            synchronized (this.f1520d) {
                this.f1520d.notify();
            }
        }
    }

    /* renamed from: com.inmobi.rendering.a.3 */
    class JavaScriptBridge implements C0698b {
        final /* synthetic */ Uri f3855a;
        final /* synthetic */ String f3856b;
        final /* synthetic */ JavaScriptBridge f3857c;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, Uri uri, String str) {
            this.f3857c = javaScriptBridge;
            this.f3855a = uri;
            this.f3856b = str;
        }

        public void m4542a(int i, Intent intent) {
            if (i == -1) {
                String a;
                if (intent == null || intent.getData() == null) {
                    a = ImageProcessing.m1778a(this.f3855a, this.f3857c.f1551h);
                } else {
                    a = ImageProcessing.m1778a(intent.getData(), this.f3857c.f1551h);
                }
                Bitmap a2 = ImageProcessing.m1776a(a, this.f3857c.f1551h, this.f3857c.f1546c.getRenderingConfig().m1174a(), this.f3857c.f1546c.getRenderingConfig().m1175b());
                int width = a2.getWidth();
                this.f3857c.f1546c.m1642a(this.f3856b, "fireCameraPictureCatpturedEvent('" + ImageProcessing.m1777a(a2, this.f3857c.f1551h, this.f3857c.f1546c.getRenderingConfig().m1176c()) + "'" + "," + "'" + width + "','" + a2.getHeight() + "')");
                return;
            }
            this.f3857c.f1546c.m1645a(this.f3856b, "User did not take a picture", "takeCameraPicture");
        }
    }

    /* renamed from: com.inmobi.rendering.a.4 */
    class JavaScriptBridge implements C0698b {
        final /* synthetic */ String f3858a;
        final /* synthetic */ JavaScriptBridge f3859b;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge, String str) {
            this.f3859b = javaScriptBridge;
            this.f3858a = str;
        }

        public void m4543a(int i, Intent intent) {
            if (i == -1) {
                Bitmap a = ImageProcessing.m1776a(ImageProcessing.m1778a(intent.getData(), this.f3859b.f1551h), this.f3859b.f1551h, this.f3859b.f1546c.getRenderingConfig().m1174a(), this.f3859b.f1546c.getRenderingConfig().m1175b());
                int width = a.getWidth();
                this.f3859b.f1546c.m1642a(this.f3858a, "fireGalleryImageSelectedEvent('" + ImageProcessing.m1777a(a, this.f3859b.f1551h, this.f3859b.f1546c.getRenderingConfig().m1176c()) + "'" + "," + "'" + width + "','" + a.getHeight() + "')");
                return;
            }
            this.f3859b.f1546c.m1645a(this.f3858a, "User did not select an image from gallery", "getGalleryImage");
        }
    }

    /* renamed from: com.inmobi.rendering.a.5 */
    class JavaScriptBridge implements AsyncNetworkTask {
        final /* synthetic */ JavaScriptBridge f3860a;

        JavaScriptBridge(JavaScriptBridge javaScriptBridge) {
            this.f3860a = javaScriptBridge;
        }

        public void m4544a(NetworkResponse networkResponse) {
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "asyncPing Successful");
        }

        public void m4545b(NetworkResponse networkResponse) {
            Logger.m1440a(InternalLogLevel.INTERNAL, JavaScriptBridge.f1545b, "asyncPing Failed");
        }
    }

    static {
        f1545b = JavaScriptBridge.class.getSimpleName();
        f1544a = new String[]{"tel", "sms", "calendar", "storePicture", "inlineVideo"};
    }

    public JavaScriptBridge(RenderView renderView, RenderingProperties renderingProperties) {
        this.f1551h = renderView.getContext();
        this.f1546c = renderView;
        this.f1547d = renderingProperties;
    }

    @JavascriptInterface
    public void open(String str, String str2) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            new Handler(this.f1546c.getContext().getMainLooper()).post(new JavaScriptBridge(this, str, str2));
        }
    }

    @JavascriptInterface
    public void openEmbedded(String str, String str2) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            new Handler(this.f1546c.getContext().getMainLooper()).post(new AnonymousClass12(this, str, str2));
        }
    }

    @JavascriptInterface
    public void ping(String str, String str2, boolean z) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.f1546c.m1645a(str, "Invalid URL:" + str2, "ping");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called ping() URL: >>> " + str2 + " <<<");
            ClickManager.m4554a().m4565a(str2, z);
        }
    }

    @JavascriptInterface
    public void pingInWebView(String str, String str2, boolean z) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.f1546c.m1645a(str, "Invalid URL:" + str2, "pingInWebView");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called pingInWebView() URL: >>> " + str2 + " <<<");
            ClickManager.m4554a().m4567b(str2, z);
        }
    }

    @JavascriptInterface
    public void log(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Log called. Message:" + str2);
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        String num = Integer.toString(VERSION.SDK_INT);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getPlatformVersion. Version:" + num);
        return num;
    }

    @JavascriptInterface
    public String getPlatform(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getPlatform. Platform:" + SdkInfo.m1270d());
        return SdkInfo.m1270d();
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "fireAdReady called.");
        this.f1546c.getListener().m1595a(this.f1546c);
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "fireAdFailed called.");
        this.f1546c.getListener().m1597b(this.f1546c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.webkit.JavascriptInterface
    public java.lang.String getDefaultPosition(java.lang.String r4) {
        /*
        r3 = this;
        r0 = r3.f1546c;
        if (r0 != 0) goto L_0x0017;
    L_0x0004:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
        r1 = f1545b;
        r2 = "Found a null instance of render view!";
        com.inmobi.commons.core.utilities.Logger.m1440a(r0, r1, r2);
        r0 = new org.json.JSONObject;
        r0.<init>();
        r0 = r0.toString();
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = r3.f1546c;
        r1 = r0.getDefaultPositionMonitor();
        monitor-enter(r1);
        r0 = r3.f1546c;	 Catch:{ all -> 0x004a }
        r0.setDefaultPositionLock();	 Catch:{ all -> 0x004a }
        r0 = r3.f1546c;	 Catch:{ all -> 0x004a }
        r0 = r0.getContext();	 Catch:{ all -> 0x004a }
        r0 = (android.app.Activity) r0;	 Catch:{ all -> 0x004a }
        r2 = new com.inmobi.rendering.a$23;	 Catch:{ all -> 0x004a }
        r2.<init>(r3);	 Catch:{ all -> 0x004a }
        r0.runOnUiThread(r2);	 Catch:{ all -> 0x004a }
    L_0x0033:
        r0 = r3.f1546c;	 Catch:{ all -> 0x004a }
        r0 = r0.m1654b();	 Catch:{ all -> 0x004a }
        if (r0 == 0) goto L_0x004d;
    L_0x003b:
        r0 = r3.f1546c;	 Catch:{ InterruptedException -> 0x0045 }
        r0 = r0.getDefaultPositionMonitor();	 Catch:{ InterruptedException -> 0x0045 }
        r0.wait();	 Catch:{ InterruptedException -> 0x0045 }
        goto L_0x0033;
    L_0x0045:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x004a }
        goto L_0x0033;
    L_0x004a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004a }
        throw r0;
    L_0x004d:
        monitor-exit(r1);	 Catch:{ all -> 0x004a }
        r0 = r3.f1546c;
        r0 = r0.getDefaultPosition();
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.a.getDefaultPosition(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.webkit.JavascriptInterface
    public java.lang.String getCurrentPosition(java.lang.String r4) {
        /*
        r3 = this;
        r0 = r3.f1546c;
        if (r0 != 0) goto L_0x0010;
    L_0x0004:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
        r1 = f1545b;
        r2 = "Found a null instance of render view!";
        com.inmobi.commons.core.utilities.Logger.m1440a(r0, r1, r2);
        r0 = "";
    L_0x000f:
        return r0;
    L_0x0010:
        r0 = r3.f1546c;
        r1 = r0.getCurrentPositionMonitor();
        monitor-enter(r1);
        r0 = r3.f1546c;	 Catch:{ all -> 0x0043 }
        r0.setCurrentPositionLock();	 Catch:{ all -> 0x0043 }
        r0 = r3.f1546c;	 Catch:{ all -> 0x0043 }
        r0 = r0.getContext();	 Catch:{ all -> 0x0043 }
        r0 = (android.app.Activity) r0;	 Catch:{ all -> 0x0043 }
        r2 = new com.inmobi.rendering.a$26;	 Catch:{ all -> 0x0043 }
        r2.<init>(r3);	 Catch:{ all -> 0x0043 }
        r0.runOnUiThread(r2);	 Catch:{ all -> 0x0043 }
    L_0x002c:
        r0 = r3.f1546c;	 Catch:{ all -> 0x0043 }
        r0 = r0.m1659c();	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0046;
    L_0x0034:
        r0 = r3.f1546c;	 Catch:{ InterruptedException -> 0x003e }
        r0 = r0.getCurrentPositionMonitor();	 Catch:{ InterruptedException -> 0x003e }
        r0.wait();	 Catch:{ InterruptedException -> 0x003e }
        goto L_0x002c;
    L_0x003e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0043 }
        goto L_0x002c;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        r0 = r3.f1546c;
        r0 = r0.getCurrentPosition();
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.a.getCurrentPosition(java.lang.String):java.lang.String");
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "setExpandProperties called. Params:" + str2);
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (this.f1546c.getState() == RenderViewState.EXPANDED) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "setExpandProperties can't be called on an already expanded ad.");
        } else {
            this.f1546c.setExpandProperties(ExpandProperties.m1772a(str2, this.f1546c.getExpandProperties(), this.f1546c.getOrientationProperties()));
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        if (this.f1546c != null) {
            return this.f1546c.getExpandProperties().m1775c();
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return StringUtils.EMPTY;
    }

    @JavascriptInterface
    public void expand(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "expand called. Url:" + str2);
        if (this.f1547d.m1685a() == PlacementType.FULL_SCREEN) {
            return;
        }
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (!this.f1546c.m1648a()) {
            this.f1546c.m1645a(str, "Creative is not visible. Ignoring request.", "expand");
        } else if (str2 == null || str2.length() == 0 || str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass27(this, str, str2));
        } else {
            this.f1546c.m1645a(str, "Invalid URL", "expand");
        }
    }

    @JavascriptInterface
    public String getVersion(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getVersion called. Version:" + SdkInfo.m1269c());
        return SdkInfo.m1269c();
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "setResizeProperties called. Properties:" + str2);
        ResizeProperties a = ResizeProperties.m1833a(str2, this.f1546c.getResizeProperties());
        if (a == null) {
            this.f1546c.m1645a(str, "setResizeProperties", "All mandatory fields are not present");
        }
        this.f1546c.setResizeProperties(a);
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return StringUtils.EMPTY;
        }
        ResizeProperties resizeProperties = this.f1546c.getResizeProperties();
        return resizeProperties == null ? StringUtils.EMPTY : resizeProperties.m1834a();
    }

    @JavascriptInterface
    public void resize(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "resize called");
        if (this.f1547d.m1685a() == PlacementType.FULL_SCREEN) {
            return;
        }
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (this.f1546c.m1648a()) {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new Runnable() {
                final /* synthetic */ JavaScriptBridge f1484a;

                {
                    this.f1484a = r1;
                }

                public void run() {
                    this.f1484a.f1546c.m1675i();
                }
            });
        } else {
            this.f1546c.m1645a(str, "Creative is not visible. Ignoring request.", "resize");
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        this.f1548e = OrientationProperties.m1832a(str2, this.f1546c.getOrientationProperties());
        this.f1546c.setOrientationProperties(this.f1548e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        String obj = this.f1548e.toString();
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getOrientationProperties called: " + obj);
        return obj;
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, ">>> onOrientationChange() >>> This API is deprecated!");
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        if (this.f1546c != null) {
            return this.f1546c.m1648a();
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void useCustomClose(String str, boolean z) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "useCustomClose called:" + z);
        ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass29(this, z));
    }

    @JavascriptInterface
    public void playVideo(String str, String str2) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            this.f1546c.m1645a(str, "Null or empty or invalid media playback URL supplied", "playVideo");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: playVideo (" + str2 + ")");
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass30(this, str, str2));
        }
    }

    @JavascriptInterface
    public String getState(String str) {
        String toLowerCase = this.f1546c.getState().toString().toLowerCase(Locale.ENGLISH);
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getState called:" + toLowerCase);
        return toLowerCase;
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", DisplayInfo.m1481a().m1496b());
            jSONObject.put("height", DisplayInfo.m1481a().m1495a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getScreenSize called:" + jSONObject2);
        return jSONObject2;
    }

    @JavascriptInterface
    public String getMaxSize(String str) {
        Activity activity;
        int i;
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getMaxSize called");
        if (this.f1546c.getFullScreenActivity() == null) {
            activity = (Activity) this.f1546c.getContext();
        } else {
            activity = this.f1546c.getFullScreenActivity();
        }
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(16908290);
        int a = DisplayInfo.m1480a(frameLayout.getWidth());
        int a2 = DisplayInfo.m1480a(frameLayout.getHeight());
        if (this.f1546c.getFullScreenActivity() == null || !(a == 0 || a2 == 0)) {
            i = a2;
            a2 = a;
        } else {
            JavaScriptBridge javaScriptBridge = new JavaScriptBridge(frameLayout);
            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(javaScriptBridge);
            synchronized (javaScriptBridge.f1520d) {
                try {
                    javaScriptBridge.f1520d.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a2 = javaScriptBridge.f1517a;
                i = javaScriptBridge.f1518b;
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", a2);
            jSONObject.put("height", i);
        } catch (Throwable e2) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1545b, "Error while creating max size Json.", e2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getMaxSize called:" + jSONObject.toString());
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void close(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "close called");
        ((Activity) this.f1546c.getContext()).runOnUiThread(new Runnable() {
            final /* synthetic */ JavaScriptBridge f1492a;

            {
                this.f1492a = r1;
            }

            public void run() {
                this.f1492a.f1546c.m1677j();
            }
        });
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getPlacementType called");
        if (this.f1547d.m1685a() == PlacementType.FULL_SCREEN) {
            return "interstitial";
        }
        return "inline";
    }

    @JavascriptInterface
    @SuppressLint({"NewApi"})
    public void storePicture(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "storePicture called with URL: " + str2);
        if (!this.f1546c.m1672g("storePicture")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "storePicture called despite the fact that it is not supported");
        } else if (str2 == null || str2.length() == 0) {
            this.f1546c.m1645a(str, "Null or empty URL supplied", "storePicture");
        } else if (str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str2.startsWith(HttpVersion.HTTP)) {
            if (this.f1549f == null) {
                this.f1549f = (DownloadManager) SdkContext.m1258b().getSystemService("download");
            }
            try {
                Uri parse = Uri.parse(str2);
                Request request = new Request(parse);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, parse.getLastPathSegment());
                registerBroadcastListener(str);
                Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Download enqueued with ID: " + this.f1549f.enqueue(request));
            } catch (ParseException e) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Invalid URL provided to storePicture " + e.getMessage());
                this.f1546c.m1645a(str, "Invalid URL", "storePicture");
            }
        } else {
            this.f1546c.m1645a(str, "Invalid URL scheme - only HTTP(S) is supported", "storePicture");
        }
    }

    @JavascriptInterface
    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (str3 == null || str3.trim().length() == 0 || str4 == null || str4.trim().length() == 0) {
            this.f1546c.m1645a(str, "Mandatory parameter(s) start and/or end date not supplied", "createCalendarEvent");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "createCalendarEvent called with parameters: \nevent ID: " + str2 + "; startDate: " + str3 + "; endDate: " + str4 + "; location: " + str5 + "; description: " + str6 + "; summary: " + str7 + "; status: " + str8 + "; transparency: " + str9 + "; recurrence: " + str10 + "; reminder: " + str11);
            this.f1546c.m1646a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
        }
    }

    @SuppressLint({"NewApi"})
    public void registerBroadcastListener(String str) {
        if (this.f1550g == null) {
            this.f1550g = new JavaScriptBridge(this, str);
            SdkContext.m1258b().registerReceiver(this.f1550g, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        }
    }

    @SuppressLint({"NewApi"})
    public void unRegisterBroadcastListener() {
        if (this.f1550g != null) {
            SdkContext.m1258b().unregisterReceiver(this.f1550g);
            this.f1550g = null;
        }
    }

    @JavascriptInterface
    public void makeCall(String str, String str2) {
        if (this.f1546c.m1672g("tel")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "makeCall: " + str2);
            if (TextUtils.isEmpty(str2)) {
                str2 = null;
            } else if (!str2.startsWith("tel:")) {
                str2 = "tel:" + str2;
            }
            if (str2 != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
                intent.addFlags(268435456);
                this.f1551h.startActivity(intent);
                this.f1546c.getListener().m1603g(this.f1546c);
                return;
            }
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "makeCall Unsuccesful: invalid number provided");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "makeCall called even when it is not supported");
    }

    @JavascriptInterface
    public void sendMail(String str, String str2, String str3, String str4) {
        if (this.f1546c.m1672g("sendMail")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "sendMail recipient: " + str2 + "subject: " + str3 + " message: " + str4);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("plain/text");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str2});
            intent.putExtra("android.intent.extra.SUBJECT", str3);
            intent.putExtra("android.intent.extra.TEXT", str4);
            intent.addFlags(268435456);
            this.f1551h.startActivity(Intent.createChooser(intent, "Choose the Email Client."));
            this.f1546c.getListener().m1603g(this.f1546c);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "sendMail called even when it is not supported");
    }

    @JavascriptInterface
    public void sendSMS(String str, String str2, String str3) {
        if (this.f1546c.m1672g("sms")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "sendSMS recipient: " + str2 + " message: " + str3);
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + Uri.encode(str2)));
            intent.putExtra("sms_body", str3);
            intent.addFlags(268435456);
            this.f1551h.startActivity(intent);
            this.f1546c.getListener().m1603g(this.f1546c);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "sendSMS called even when it is not supported");
    }

    @JavascriptInterface
    public void takeCameraPicture(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "takeCameraPicture called ");
        if (this.f1546c.m1672g("takeCameraPicture")) {
            Object insert = this.f1551h.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", insert);
            int a = InMobiAdActivity.m1581a(intent, new JavaScriptBridge(this, insert, str));
            intent = new Intent(this.f1551h, InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", C0302R.styleable.Theme_checkedTextViewStyle);
            intent.putExtra("id", a);
            this.f1551h.startActivity(intent);
            this.f1546c.getListener().m1603g(this.f1546c);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "takeCameraPicture called even when it is not supported");
    }

    @JavascriptInterface
    public void getGalleryImage(String str) {
        if (this.f1546c.m1672g("getGalleryImage")) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getGalleryImage called ");
            int a = InMobiAdActivity.m1581a(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), new JavaScriptBridge(this, str));
            Intent intent = new Intent(this.f1551h, InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", C0302R.styleable.Theme_checkedTextViewStyle);
            intent.putExtra("id", a);
            this.f1551h.startActivity(intent);
            this.f1546c.getListener().m1603g(this.f1546c);
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getGalleryImage called even when it is not supported");
    }

    @JavascriptInterface
    public void postToSocial(String str, int i, String str2, String str3, String str4) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "postToSocial called with parameters: socialType: " + i + "; text: " + str2 + "; link: " + str3 + "; image URL: " + str4);
        this.f1546c.m1641a(str, i, str2, str3, str4);
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getSdkVersion called. Version:" + SdkInfo.m1268b());
        return SdkInfo.m1268b();
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Checking support for: " + str2);
        if (Arrays.asList(f1544a).contains(str2) || this.f1546c.m1672g(str2)) {
            return String.valueOf(this.f1546c.m1672g(str2));
        }
        return SchemaSymbols.ATTVAL_FALSE;
    }

    @JavascriptInterface
    public void openExternal(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "openExternal called with url: " + str2);
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else if (!str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || URLUtil.isValidUrl(str2)) {
            this.f1546c.m1652b("openExternal", str, str2);
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "openExternal called with invalid url (" + str2 + ")");
            this.f1546c.m1645a(str, "Invalid URL", "openExternal");
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "asyncPing called: " + str2);
        if (URLUtil.isValidUrl(str2)) {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, str2, false, null);
            networkRequest.m1401a(false);
            new com.inmobi.commons.core.network.AsyncNetworkTask(networkRequest, new JavaScriptBridge(this)).m1422a();
            return;
        }
        this.f1546c.m1645a(str, "Invalid url", "asyncPing");
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "showAlert: " + str2);
    }

    @JavascriptInterface
    public void playVideo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Activity activity = (Activity) this.f1546c.getContext();
        r16.runOnUiThread(new JavaScriptBridge(this, str2, str9, str, str5, str6, z2, z4, str7, str8, str4, str3, z, z3));
    }

    @JavascriptInterface
    public void seekVideo(String str, String str2, int i) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: seekVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new JavaScriptBridge(this, str, str2, i));
        }
    }

    @JavascriptInterface
    public void pauseVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: pauseVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new JavaScriptBridge(this, str, str2));
        }
    }

    @JavascriptInterface
    public void closeVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: closeVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new JavaScriptBridge(this, str, str2));
        }
    }

    @JavascriptInterface
    public void hideVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: hideVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass10(this, str, str2));
        }
    }

    @JavascriptInterface
    public void showVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: showVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass11(this, str, str2));
        }
    }

    @JavascriptInterface
    public void muteVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: muteVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass13(this, str, str2));
        }
    }

    @JavascriptInterface
    public void unMuteVideo(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: unMuteVideo (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass14(this, str, str2));
        }
    }

    @JavascriptInterface
    public boolean isVideoMuted(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: isVideoMuted (" + str2 + ")");
        if (this.f1546c != null) {
            return this.f1546c.m1669f(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void setVideoVolume(String str, String str2, int i) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: setVideoVolume (" + str2 + ", " + i + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass15(this, str, str2, i));
        }
    }

    @JavascriptInterface
    public int getVideoVolume(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: getVideoVolume (" + str2 + ")");
        if (this.f1546c != null) {
            return this.f1546c.m1664e(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return 0;
    }

    @JavascriptInterface
    public void playAudio(String str, String str2, boolean z, boolean z2, boolean z3, String str3, String str4, String str5) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: playAudio (" + str2 + ", " + str5 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass16(this, str5, str2, str, z, z3, str3, str4, z2));
    }

    @JavascriptInterface
    public void seekAudio(String str, String str2, int i) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: seekAudio (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass17(this, str, str2, i));
        }
    }

    @JavascriptInterface
    public void pauseAudio(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: pauseAudio (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass18(this, str, str2));
        }
    }

    @JavascriptInterface
    public void muteAudio(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: muteAudio (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass19(this, str, str2));
        }
    }

    @JavascriptInterface
    public void unMuteAudio(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: unMuteAudio (" + str2 + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass20(this, str, str2));
        }
    }

    @JavascriptInterface
    public boolean isAudioMuted(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: isAudioMuted (" + str2 + ")");
        if (this.f1546c != null) {
            return this.f1546c.m1669f(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void setAudioVolume(String str, String str2, int i) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: setAudioVolume (" + str2 + ", " + i + ")");
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass21(this, str, str2, i));
        }
    }

    @JavascriptInterface
    public int getAudioVolume(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: getAudioVolume (" + str2 + ")");
        if (this.f1546c != null) {
            return this.f1546c.m1664e(str, str2);
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return 0;
    }

    @JavascriptInterface
    public double getMicIntensity(String str) {
        if (this.f1546c != null) {
            return this.f1546c.m1681m();
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return Double.MIN_VALUE;
    }

    @JavascriptInterface
    public void disableCloseRegion(String str, boolean z) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass22(this, z));
        }
    }

    @JavascriptInterface
    public void onUserInteraction(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "onUserInteraction called. Params:" + str2);
        if (str2 == null) {
            this.f1546c.getListener().m1598b(this.f1546c, null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            this.f1546c.getListener().m1598b(this.f1546c, hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
            this.f1546c.getListener().m1598b(this.f1546c, null);
        }
    }

    @JavascriptInterface
    public void incentCompleted(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "incentCompleted called. IncentData:" + str2);
        if (str2 == null) {
            this.f1546c.getListener().m1596a(this.f1546c, null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            this.f1546c.getListener().m1596a(this.f1546c, hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
            this.f1546c.getListener().m1596a(this.f1546c, null);
        }
    }

    @JavascriptInterface
    public void vibrate(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Vibrate called");
        ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass24(this, str));
    }

    @JavascriptInterface
    public void vibrate(String str, String str2, int i) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Vibrate called with pattern " + str2);
        ((Activity) this.f1546c.getContext()).runOnUiThread(new AnonymousClass25(this, str, str2, i));
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "getOrientation called");
        int b = DisplayInfo.m1482b();
        if (b == ORIENTATION_VALUES.PORTRAIT.getValue()) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        if (b == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
            return "90";
        }
        if (b == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
            return "180";
        }
        if (b == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
            return "270";
        }
        return "-1";
    }

    @JavascriptInterface
    public void saveContent(String str, String str2, String str3) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) {
            String str4;
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "saveContent called with invalid parameters");
            JSONObject jSONObject = new JSONObject();
            try {
                str4 = DatabaseOpenHelper.HISTORY_ROW_URL;
                if (str3 == null) {
                    str3 = StringUtils.EMPTY;
                }
                jSONObject.put(str4, str3);
                jSONObject.put("reason", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            str4 = jSONObject.toString().replace("\"", "\\\"");
            StringBuilder append = new StringBuilder().append("sendSaveContentResult(\"saveContent_");
            if (str2 == null) {
                str2 = StringUtils.EMPTY;
            }
            this.f1546c.m1642a(str, append.append(str2).append("\", 'failure', \"").append(str4).append("\");").toString());
            return;
        }
        this.f1546c.m1662d(str, str2, str3);
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "cancelSaveContent called. mediaId:" + str2);
        this.f1546c.m1667f(str2);
    }

    @JavascriptInterface
    public void registerMicListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Register microphone intensity listener ...");
        this.f1546c.m1660d(str);
    }

    @JavascriptInterface
    public void unregisterMicListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Unregister microphone intensity listener ...");
        this.f1546c.m1682n();
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return SchemaSymbols.ATTVAL_FALSE;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: isDeviceMuted()");
        return String.valueOf(this.f1546c.getMediaProcessor().m1755e());
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return SchemaSymbols.ATTVAL_FALSE;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "JavaScript called: isHeadphonePlugged()");
        return String.valueOf(this.f1546c.getMediaProcessor().m1760h());
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.getMediaProcessor().m1739a(str);
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Unregister device mute event listener ...");
        this.f1546c.getMediaProcessor().m1756f();
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.getMediaProcessor().m1746b(str);
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Unregister device volume change listener ...");
        this.f1546c.getMediaProcessor().m1758g();
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.getMediaProcessor().m1750c(str);
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
            return;
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Unregister headphone plugged event listener ...");
        this.f1546c.getMediaProcessor().m1761i();
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.setDisableBackButton(z);
        }
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        if (this.f1546c != null) {
            return this.f1546c.m1668f();
        }
        Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.m1655c(str);
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        if (this.f1546c == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1545b, "Found a null instance of render view!");
        } else {
            this.f1546c.m1670g();
        }
    }

    private static int m1710b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -99999;
        }
    }
}
