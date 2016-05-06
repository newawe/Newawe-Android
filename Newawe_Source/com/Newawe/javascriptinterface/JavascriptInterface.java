package com.Newawe.javascriptinterface;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.ads.BottomBannerLayout;
import com.Newawe.ads.sdk.JavascriptSdkController;
import com.Newawe.controllers.WebContentController;
import com.Newawe.model.WidgetEntity;
import com.Newawe.notification.NotificationChecker;
import com.Newawe.server.AppsGeyserServerClient;
import com.Newawe.server.BaseServerClient.OnRequestDoneListener;
import com.Newawe.storage.BrowsingHistoryItem;
import com.Newawe.storage.DatabaseOpenHelper;
import com.Newawe.ui.navigationwidget.NavigationWidgetCustomIcon;
import com.Newawe.utils.FileManager;
import com.Newawe.utils.ImageReader;
import com.Newawe.utils.UrlConverter;
import com.Newawe.utils.WebViewScreenShooter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavascriptInterface extends JavascriptSdkController {
    public static final String JS_INTERFACE_NAME = "AppsgeyserJSInterface";
    public static final String JS_PREFERENCE_NAME = "JS-PREFERENCE";
    public static final String JS_PREFERENCE_PREFIX = "JS-Preference-";
    private ProgressDialog _currentProgressDialog;
    private MainNavigationActivity _mainActivity;
    private WebContentController _webController;
    private int mFinalHeight;
    private int mFinalWidth;

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ MainNavigationActivity val$activity;

        AnonymousClass10(MainNavigationActivity mainNavigationActivity) {
            this.val$activity = mainNavigationActivity;
        }

        public void run() {
            this.val$activity.showFullscreenBannerView();
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.11 */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ String val$resultCallback;

        AnonymousClass11(String str) {
            this.val$resultCallback = str;
        }

        public void run() {
            ArrayList<BrowsingHistoryItem> history = JavascriptInterface.this._mainActivity.getWeeklyHistory();
            JSONObject jResult = new JSONObject();
            JSONArray jArray = new JSONArray();
            try {
                Iterator i$ = history.iterator();
                while (i$.hasNext()) {
                    BrowsingHistoryItem browsingHistoryItem = (BrowsingHistoryItem) i$.next();
                    JSONObject jGroup = new JSONObject();
                    jGroup.put("_id", browsingHistoryItem.getId());
                    jGroup.put(SchemaSymbols.ATTVAL_DATE, browsingHistoryItem.getDate());
                    jGroup.put(DatabaseOpenHelper.HISTORY_ROW_TITLE, browsingHistoryItem.getTitle());
                    jGroup.put(DatabaseOpenHelper.HISTORY_ROW_URL, browsingHistoryItem.getUrl());
                    jArray.put(jGroup);
                }
            } catch (Exception e) {
                jArray = new JSONArray();
            }
            try {
                jResult.put(DatabaseOpenHelper.HISTORY_TABLE_NAME, jArray);
            } catch (JSONException e2) {
            }
            JavascriptInterface.this._webController.getWebView().loadUrl("javascript:" + this.val$resultCallback + "('" + jResult.toString() + "')");
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ long val$id;
        final /* synthetic */ String val$resultCallback;

        AnonymousClass12(long j, String str) {
            this.val$id = j;
            this.val$resultCallback = str;
        }

        public void run() {
            JavascriptInterface.this._webController.getWebView().loadUrl("javascript:" + this.val$resultCallback + "('" + JavascriptInterface.this._mainActivity.removeHistoryItem(this.val$id) + "')");
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.14 */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ int val$intervalMillis;
        final /* synthetic */ String val$url;

        AnonymousClass14(String str, int i) {
            this.val$url = str;
            this.val$intervalMillis = i;
        }

        public void run() {
            new NotificationChecker().addChecker(new UrlConverter(JavascriptInterface.this._webController.getWebView()).toAbsolute(this.val$url), this.val$intervalMillis, JavascriptInterface.this._mainActivity);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.15 */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ String val$url;

        AnonymousClass15(String str) {
            this.val$url = str;
        }

        public void run() {
            new NotificationChecker().removeChecker(new UrlConverter(JavascriptInterface.this._webController.getWebView()).toAbsolute(this.val$url), JavascriptInterface.this._mainActivity);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.2 */
    class C02332 implements Runnable {
        final /* synthetic */ String val$buttonBadge;
        final /* synthetic */ String val$buttonName;
        final /* synthetic */ HashMap val$icons;

        C02332(HashMap hashMap, String str, String str2) {
            this.val$icons = hashMap;
            this.val$buttonName = str;
            this.val$buttonBadge = str2;
        }

        public void run() {
            ((NavigationWidgetCustomIcon) this.val$icons.get(this.val$buttonName)).updateBadge(this.val$buttonBadge);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.3 */
    class C02343 implements Runnable {
        final /* synthetic */ String val$buttonName;
        final /* synthetic */ String val$iconPath;
        final /* synthetic */ HashMap val$icons;

        C02343(HashMap hashMap, String str, String str2) {
            this.val$icons = hashMap;
            this.val$buttonName = str;
            this.val$iconPath = str2;
        }

        public void run() {
            ((NavigationWidgetCustomIcon) this.val$icons.get(this.val$buttonName)).updateIcon(this.val$iconPath);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.4 */
    class C02354 implements Runnable {
        C02354() {
        }

        public void run() {
            JavascriptInterface.this._webController.setScaleForPageWithSize(JavascriptInterface.this.mFinalHeight, JavascriptInterface.this.mFinalWidth);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.5 */
    class C02365 implements Runnable {
        C02365() {
        }

        public void run() {
            JavascriptInterface.this._webController.zoomIn();
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.6 */
    class C02376 implements Runnable {
        final /* synthetic */ String val$text;

        C02376(String str) {
            this.val$text = str;
        }

        public void run() {
            JavascriptInterface.this._currentProgressDialog = ProgressDialog.show(JavascriptInterface.this._mainActivity, StringUtils.EMPTY, this.val$text, true);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.7 */
    class C02387 implements Runnable {
        C02387() {
        }

        public void run() {
            if (JavascriptInterface.this._currentProgressDialog != null) {
                JavascriptInterface.this._currentProgressDialog.hide();
                JavascriptInterface.this._currentProgressDialog = null;
            }
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.8 */
    class C02398 implements Runnable {
        C02398() {
        }

        public void run() {
            JavascriptInterface.this._mainActivity.findViewById(C0186R.id.tabtags_panel).setVisibility(8);
            BottomBannerLayout bannerLayout = JavascriptInterface.this._mainActivity.getBannerLayout();
            if (bannerLayout != null) {
                bannerLayout.setDefaultTagsPanelVisibility(8);
            }
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.9 */
    class C02409 implements Runnable {
        C02409() {
        }

        public void run() {
            JavascriptInterface.this._mainActivity.findViewById(C0186R.id.tabtags_panel).setVisibility(0);
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.13 */
    class AnonymousClass13 implements OnRequestDoneListener {
        final /* synthetic */ String val$resultCallback;

        /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.13.1 */
        class C02321 implements Runnable {
            final /* synthetic */ String val$result;

            C02321(String str) {
                this.val$result = str;
            }

            public void run() {
                JavascriptInterface.this._webController.getWebView().loadUrl("javascript:" + AnonymousClass13.this.val$resultCallback + "('" + this.val$result + "');");
            }
        }

        AnonymousClass13(String str) {
            this.val$resultCallback = str;
        }

        public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
            String stringResponse;
            try {
                InputStream is = response.getEntity().getContent();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while (true) {
                    int c = is.read();
                    if (c == -1) {
                        break;
                    }
                    out.write(c);
                }
                out.flush();
                byte[] imageRaw = out.toByteArray();
                is.close();
                out.close();
                stringResponse = "data:image/png;base64," + Base64.encodeToString(imageRaw, 0);
            } catch (IOException e) {
                e.printStackTrace();
                stringResponse = StringUtils.EMPTY;
            }
            JavascriptInterface.this._mainActivity.runOnUiThread(new C02321(stringResponse.replace(LineSeparator.Web, StringUtils.EMPTY).replace("\\", "\\\\").replace("'", "\\'")));
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.1 */
    class C09941 implements OnRequestDoneListener {
        final /* synthetic */ String val$resultCallback;

        /* renamed from: com.Newawe.javascriptinterface.JavascriptInterface.1.1 */
        class C02311 implements Runnable {
            final /* synthetic */ String val$callback;

            C02311(String str) {
                this.val$callback = str;
            }

            public void run() {
                JavascriptInterface.this._webController.getWebView().loadUrl(this.val$callback);
            }
        }

        C09941(String str) {
            this.val$resultCallback = str;
        }

        public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
            String stringResponse;
            try {
                InputStream is = response.getEntity().getContent();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringResponse = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                stringResponse = StringUtils.EMPTY;
            }
            JavascriptInterface.this._mainActivity.runOnUiThread(new C02311("javascript:window." + this.val$resultCallback + "('" + StringEscapeUtils.escapeJavaScript(stringResponse) + "');"));
        }
    }

    public JavascriptInterface(WebContentController webController) {
        if (webController != null) {
            this._mainActivity = webController.getMainNavigationActivity();
            this._webController = webController;
        }
    }

    @android.webkit.JavascriptInterface
    public String sendXMLHTTPRequestSync(String url) {
        String strRes = StringUtils.EMPTY;
        if (this._mainActivity != null) {
            return AppsGeyserServerClient.getInstance(this._mainActivity).sendRequestSync(url);
        }
        return strRes;
    }

    @android.webkit.JavascriptInterface
    public void sendXMLHTTPRequest(String url, String resultCallback) {
        AppsGeyserServerClient.getInstance(this._mainActivity).sendRequestAsync(url, 0, new C09941(resultCallback));
    }

    @android.webkit.JavascriptInterface
    public String getAppId() {
        return Integer.toString(this._mainActivity.getConfig().getApplicationId());
    }

    @android.webkit.JavascriptInterface
    public String getInstallationGuid() {
        return this._mainActivity.getConfig().getAppGuid();
    }

    @android.webkit.JavascriptInterface
    public void updateIconBadge(String buttonName, String buttonBadge) {
        HashMap<String, NavigationWidgetCustomIcon> icons = Factory.getInstance().getNavigationWidget().getCustomIcons();
        if (icons.containsKey(buttonName)) {
            this._mainActivity.runOnUiThread(new C02332(icons, buttonName, buttonBadge));
        }
    }

    @android.webkit.JavascriptInterface
    public void updateIcon(String buttonName, String iconPath) {
        HashMap<String, NavigationWidgetCustomIcon> icons = Factory.getInstance().getNavigationWidget().getCustomIcons();
        if (icons.containsKey(buttonName)) {
            this._mainActivity.runOnUiThread(new C02343(icons, buttonName, iconPath));
        }
    }

    @android.webkit.JavascriptInterface
    public boolean playYouTubeVideo(String videoId, String apiKey, int startMillis, boolean autoPlay, boolean lightBox) {
        try {
            this._mainActivity.startActivity(YouTubeStandalonePlayer.createVideoIntent(this._mainActivity, apiKey, videoId, startMillis, autoPlay, lightBox));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @android.webkit.JavascriptInterface
    public void showInfo(String message) {
        if (this._mainActivity != null) {
            Toast.makeText(this._mainActivity, message, 0).show();
        }
    }

    @android.webkit.JavascriptInterface
    public void setScaleForPageWithSize(int iHeight, int iWidth) {
        this.mFinalHeight = iHeight;
        this.mFinalWidth = iWidth;
        if (this._mainActivity != null) {
            this._mainActivity.runOnUiThread(new C02354());
        }
    }

    @android.webkit.JavascriptInterface
    public void downloadFile(String url) {
        FileManager.downloadFile(new UrlConverter(this._webController.getWebView()).toAbsolute(url), StringUtils.EMPTY, this._mainActivity);
    }

    @android.webkit.JavascriptInterface
    public String saveImageFromBase64(String base64, String prefix) {
        if (prefix == null) {
            prefix = "IMG";
        }
        Bitmap image = ImageReader.createBitmapFromBase64(base64);
        if (image != null) {
            File imageFile = FileManager.saveBitmapToGallery(prefix, image);
            if (imageFile != null) {
                showInfo("Image saved to gallery...");
                return imageFile.toString();
            }
        }
        return null;
    }

    @android.webkit.JavascriptInterface
    public void setWallpaper(String url) {
        InputStream wallpaperStream = new UrlConverter(this._webController.getWebView()).toStream(url);
        if (wallpaperStream != null) {
            Bitmap wallpaperBitmap = BitmapFactory.decodeStream(wallpaperStream);
            try {
                _scaleBitmapAndSetWallpaper(wallpaperBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            wallpaperBitmap.recycle();
        }
    }

    @android.webkit.JavascriptInterface
    private void _scaleBitmapAndSetWallpaper(Bitmap wallpaperBitmap) throws IOException {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this._mainActivity);
        new Options().inScaled = false;
        wallpaperManager.setBitmap(wallpaperBitmap);
    }

    @android.webkit.JavascriptInterface
    public void zoomIn() {
        if (this._mainActivity != null) {
            this._mainActivity.runOnUiThread(new C02365());
        }
    }

    @android.webkit.JavascriptInterface
    public String getFileContents(String fileName) {
        String strRes = StringUtils.EMPTY;
        return FileManager.getStringFromAssetsFileWithFileName(fileName, this._mainActivity);
    }

    @android.webkit.JavascriptInterface
    public void showLoadingDialog(String text) {
        this._mainActivity.runOnUiThread(new C02376(text));
    }

    @android.webkit.JavascriptInterface
    public void hideLoadingDialog() {
        this._mainActivity.runOnUiThread(new C02387());
    }

    @android.webkit.JavascriptInterface
    public String getTabUrl(String tabId) {
        WidgetEntity widget = Factory.getInstance().getWidgetsController().getWidgetByTabId(tabId);
        if (widget != null) {
            return widget.getLink();
        }
        return StringUtils.EMPTY;
    }

    @android.webkit.JavascriptInterface
    public void hideTabs() {
        this._mainActivity.runOnUiThread(new C02398());
    }

    @android.webkit.JavascriptInterface
    public void showTabs() {
        this._mainActivity.runOnUiThread(new C02409());
    }

    @android.webkit.JavascriptInterface
    public String getItem(String key) {
        return this._mainActivity.getSharedPreferences(JS_PREFERENCE_NAME, 0).getString(JS_PREFERENCE_PREFIX + key, null);
    }

    @android.webkit.JavascriptInterface
    public void setItem(String key, String value) {
        this._mainActivity.getSharedPreferences(JS_PREFERENCE_NAME, 0).edit().putString(JS_PREFERENCE_PREFIX + key, value).commit();
    }

    @android.webkit.JavascriptInterface
    public void addToHomePage(String name, String url) {
        Factory.getInstance().getHomePageManager().addBookmark(name, url);
    }

    @android.webkit.JavascriptInterface
    public void addBookmark(String name, String url, String pageName) {
        Factory.getInstance().getBookmarkManager(pageName).addBookmark(name, url);
    }

    @android.webkit.JavascriptInterface
    public void removeFromHomepage(String index) {
        Factory.getInstance().getHomePageManager().removeBookmark(Integer.valueOf(Integer.parseInt(index)));
    }

    @android.webkit.JavascriptInterface
    public void removeBookmark(String index, String pageName) {
        Factory.getInstance().getBookmarkManager(pageName).removeBookmark(Integer.valueOf(Integer.parseInt(index)));
    }

    private String getBookmarksFromCursor(Cursor c) {
        JSONArray bookmarksArray = new JSONArray();
        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                JSONObject bookmark = new JSONObject();
                int i = 0;
                while (i < c.getColumnCount()) {
                    try {
                        Long longRes = Long.valueOf(c.getLong(i));
                        String sRes = c.getString(i);
                        if (sRes == null || sRes.length() == 0) {
                            bookmark.put(c.getColumnName(i), longRes);
                            i++;
                        } else {
                            bookmark.put(c.getColumnName(i), sRes);
                            i++;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                bookmarksArray.put(bookmark);
                c.moveToNext();
            }
        }
        return bookmarksArray.toString();
    }

    @android.webkit.JavascriptInterface
    public String getHomePageItems() {
        return getBookmarksFromCursor(Factory.getInstance().getHomePageManager().getBookmarks());
    }

    @android.webkit.JavascriptInterface
    public String getBookmarks(String pageName) {
        return getBookmarksFromCursor(Factory.getInstance().getBookmarkManager(pageName).getBookmarks());
    }

    @android.webkit.JavascriptInterface
    public String getHomePageItems(String offset, String limit) {
        return getBookmarksFromCursor(Factory.getInstance().getHomePageManager().getBookmarks(Integer.valueOf(Integer.parseInt(offset)), Integer.valueOf(Integer.parseInt(limit))));
    }

    @android.webkit.JavascriptInterface
    public String getBookmarks(String offset, String limit, String pageName) {
        return getBookmarksFromCursor(Factory.getInstance().getBookmarkManager(pageName).getBookmarks(Integer.valueOf(Integer.parseInt(offset)), Integer.valueOf(Integer.parseInt(limit))));
    }

    @android.webkit.JavascriptInterface
    public String getAppName() {
        try {
            return Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName();
        } catch (Exception e) {
            return null;
        }
    }

    @android.webkit.JavascriptInterface
    public String getAppPackageName() {
        try {
            return this._mainActivity.getApplicationContext().getPackageName();
        } catch (Exception e) {
            return null;
        }
    }

    @android.webkit.JavascriptInterface
    public void redirect(String url) {
        try {
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().loadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @android.webkit.JavascriptInterface
    public void showPopup(String url) {
        MainNavigationActivity activity = Factory.getInstance().getMainNavigationActivity();
        activity.runOnUiThread(new AnonymousClass10(activity));
        activity.getFullScreenBannerController().showUrlInPopup(url);
    }

    @android.webkit.JavascriptInterface
    public void shareText(String subject, String body) {
        Intent sharingIntent = new Intent("android.intent.action.SEND");
        sharingIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        sharingIntent.putExtra("android.intent.extra.TEXT", body);
        sharingIntent.putExtra("android.intent.extra.SUBJECT", subject);
        Factory.getInstance().getMainNavigationActivity().startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    @android.webkit.JavascriptInterface
    public String takeScreenShot() {
        return WebViewScreenShooter.takeScreenShotInBase64(Factory.getInstance().getTabsController().getSelectedTab().getWebView());
    }

    @android.webkit.JavascriptInterface
    public void getWeeklyHistory(String resultCallback) {
        this._mainActivity.runOnUiThread(new AnonymousClass11(resultCallback));
    }

    @android.webkit.JavascriptInterface
    public void removeHistoryItem(long id, String resultCallback) {
        this._mainActivity.runOnUiThread(new AnonymousClass12(id, resultCallback));
    }

    @android.webkit.JavascriptInterface
    public void getBase64FromImageUrl(String url, String resultCallback) {
        AppsGeyserServerClient.getInstance(this._mainActivity).sendRequestAsync(url, 0, new AnonymousClass13(resultCallback));
    }

    @android.webkit.JavascriptInterface
    public void sharePicture(String fileName, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction("android.intent.action.SEND");
        sendIntent.setType("image/png");
        File file = new File(fileName);
        if (file.exists()) {
            sendIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
            this._mainActivity.startActivity(Intent.createChooser(sendIntent, text));
            return;
        }
        showInfo("Error! Can't find " + fileName + "!");
    }

    @android.webkit.JavascriptInterface
    public void registerUpdateChecker(String url, int intervalMillis) {
        this._mainActivity.runOnUiThread(new AnonymousClass14(url, intervalMillis));
    }

    @android.webkit.JavascriptInterface
    public void removeUpdateChecker(String url) {
        this._mainActivity.runOnUiThread(new AnonymousClass15(url));
    }

    @android.webkit.JavascriptInterface
    public void clearUpdateCheckers() {
        new NotificationChecker().clearCheckers(this._mainActivity);
    }

    @android.webkit.JavascriptInterface
    public void setUrlBarVisibility(boolean isVisible) {
        this._mainActivity.setUrlBarVisibility(isVisible ? 0 : 8);
    }
}
