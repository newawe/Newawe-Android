package com.Newawe;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.HttpAuthHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.Newawe.ads.AdsLoader;
import com.Newawe.ads.AdsLoader.AdsLoadingFinishedListener;
import com.Newawe.ads.AdsLoader.HeadersReceiver;
import com.Newawe.ads.BottomBannerLayout;
import com.Newawe.ads.FullscreenBanner.AdMobFSBannerController;
import com.Newawe.ads.FullscreenBanner.FullScreenBannerController;
import com.Newawe.ads.FullscreenBanner.IFullScreenBannerListener;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorFactory;
import com.Newawe.ads.behavior.BehaviorVisitor;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.configuration.WebWidgetConfiguration.ApplicationThemes;
import com.Newawe.configuration.WebWidgetConfiguration.TabsPositions;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStates;
import com.Newawe.configuration.WebWidgetConfigurationManager;
import com.Newawe.controllers.ITabsController;
import com.Newawe.controllers.SplashScreenController;
import com.Newawe.controllers.WebContentController;
import com.Newawe.controllers.WidgetsController;
import com.Newawe.deviceidparser.DeviceIdParameters;
import com.Newawe.deviceidparser.DeviceIdParser;
import com.Newawe.deviceidparser.IDeviceIdParserListener;
import com.Newawe.media.camera.AlbumStorageController;
import com.Newawe.model.WidgetEntity;
import com.Newawe.model.WidgetEntity.DefaultWidgetType;
import com.Newawe.notification.NotificationChecker;
import com.Newawe.pull.PullServerController;
import com.Newawe.server.AppsGeyserServerClient;
import com.Newawe.server.PushServerClient;
import com.Newawe.storage.BrowsingHistoryItem;
import com.Newawe.ui.dialog.SimpleDialogs;
import com.Newawe.ui.dialog.StartupConfirmationDialog;
import com.Newawe.ui.menu.MenuItemsHolder;
import com.Newawe.ui.navigationdrawer.NavigationDrawer;
import com.Newawe.ui.navigationdrawer.NavigationDrawerHolder;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import com.Newawe.ui.navigationwidget.NavigationWidget;
import com.Newawe.ui.navigationwidget.TopNavigationWidget;
import com.Newawe.ui.views.AboutDialog;
import com.Newawe.ui.views.BrowserWebView;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gcm.GCMRegistrar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

public class MainNavigationActivity extends AppCompatActivity implements Callback, OnCompletionListener, OnErrorListener, AdsLoadingFinishedListener, HeadersReceiver, BehaviorAcceptor, IDeviceIdParserListener, IFullScreenBannerListener {
    private static final int ACTION_TAKE_PHOTO = 2;
    public static final String ADS_SLEEP_PARAM = "adsSleep";
    public static final String APPMODE_PARAM = "applicationMode";
    public static final String BANNER_JS_PARAM = "bannerJs";
    static final LayoutParams COVER_SCREEN_GRAVITY_CENTER;
    private static final int FILECHOOSER_RESULTCODE = 1;
    public static final String PREFS_NAME = "AppsgeyserPrefs";
    private static boolean _active;
    private static volatile ApplicationState applicationState;
    MainNavigationActivity _activity;
    AdMobFSBannerController _adMobFSBannerController;
    WebWidgetConfiguration _config;
    Dialog _connectionErrorDialog;
    private ApplicationMode _currentMode;
    DeviceIdParameters _deviceIdParameters;
    FullScreenBannerController _fullScreenBannerController;
    private MenuItemsHolder _menuItemsHolder;
    private NavigationDrawer _navigationDrawer;
    PushServerClient _pushClient;
    AppsGeyserServerClient _serverClient;
    SplashScreenController _splashScreenController;
    ITabsController _tabsController;
    private AdsLoader adsLoader;
    private AlbumStorageController albumStorageController;
    StartupConfirmationDialog alertDialog;
    private BottomBannerLayout bannerLayout;
    private Handler loadUrlFromIntentHandler;
    private Runnable loadUrlFromIntentRunnable;
    AboutDialog mAboutDialog;
    private LinearLayout mContentView;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private FrameLayout mFullScreenBannerView;
    private ViewGroup mSplashScreenView;
    private ValueCallback<Uri> mUploadMessage;
    private VideoView mVideo;
    private View mVideoProgressView;
    private String urlFromIntentToLoad;

    /* renamed from: com.Newawe.MainNavigationActivity.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ int val$viewVisililityCode;

        AnonymousClass10(int i) {
            this.val$viewVisililityCode = i;
        }

        public void run() {
            INavigationWidget navigationWIdget = Factory.getInstance().getNavigationWidget();
            if (navigationWIdget == null) {
                return;
            }
            if (this.val$viewVisililityCode == 0) {
                ((NavigationWidget) navigationWIdget).showAnimated();
            } else {
                ((NavigationWidget) navigationWIdget).hideAnimated();
            }
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.1 */
    class C01751 implements Runnable {
        C01751() {
        }

        public void run() {
            if (MainNavigationActivity.this.urlFromIntentToLoad != null && MainNavigationActivity.this.urlFromIntentToLoad.length() > 0) {
                WebContentController controller = MainNavigationActivity.this._tabsController.getSelectedTab();
                if (controller != null) {
                    WebView currentView = controller.getWebView();
                    currentView.stopLoading();
                    currentView.loadUrl(MainNavigationActivity.this.urlFromIntentToLoad);
                    return;
                }
                MainNavigationActivity.this.loadUrlFromIntentHandler.postDelayed(this, 500);
            }
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.2 */
    class C01762 implements OnClickListener {
        C01762() {
        }

        public void onClick(DialogInterface dialog, int which) {
            MainNavigationActivity.this._closeNavigationDrawer();
            MainNavigationActivity.applicationState = ApplicationState.EXITING;
            if (MainNavigationActivity.this._config.getOnExitFullscreenBannerEnabled()) {
                MainNavigationActivity.this._fullScreenBannerController.loadBanner();
            } else {
                MainNavigationActivity.this.finish();
            }
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.3 */
    class C01773 implements OnClickListener {
        C01773() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.4 */
    class C01784 implements View.OnClickListener {
        C01784() {
        }

        public void onClick(View view) {
            MainNavigationActivity.this._tabsController = Factory.getInstance().getTabsController();
            MainNavigationActivity.this._tabsController.initWithTabs(Factory.getInstance().getWidgetsController());
            MainNavigationActivity.this._connectionErrorDialog.dismiss();
            MainNavigationActivity.this._connectionErrorDialog = null;
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.5 */
    class C01795 implements View.OnClickListener {
        C01795() {
        }

        public void onClick(View view) {
            MainNavigationActivity.this._connectionErrorDialog.dismiss();
            MainNavigationActivity.this._connectionErrorDialog = null;
            MainNavigationActivity.this._activity.finish();
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.6 */
    class C01806 implements OnCancelListener {
        final /* synthetic */ HttpAuthHandler val$handler;

        C01806(HttpAuthHandler httpAuthHandler) {
            this.val$handler = httpAuthHandler;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$handler.cancel();
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.7 */
    class C01817 implements OnClickListener {
        final /* synthetic */ HttpAuthHandler val$handler;

        C01817(HttpAuthHandler httpAuthHandler) {
            this.val$handler = httpAuthHandler;
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            this.val$handler.cancel();
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.8 */
    class C01828 implements OnClickListener {
        final /* synthetic */ HttpAuthHandler val$handler;
        final /* synthetic */ String val$host;
        final /* synthetic */ String val$realm;
        final /* synthetic */ View val$v;
        final /* synthetic */ WebView val$webView;

        C01828(View view, WebView webView, String str, String str2, HttpAuthHandler httpAuthHandler) {
            this.val$v = view;
            this.val$webView = webView;
            this.val$host = str;
            this.val$realm = str2;
            this.val$handler = httpAuthHandler;
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            String nm = ((EditText) this.val$v.findViewById(C0186R.id.username_edit)).getText().toString();
            String pw = ((EditText) this.val$v.findViewById(C0186R.id.password_edit)).getText().toString();
            MainNavigationActivity.this.setHttpAuthUsernamePassword(this.val$webView, this.val$host, this.val$realm, nm, pw);
            this.val$handler.proceed(nm, pw);
        }
    }

    /* renamed from: com.Newawe.MainNavigationActivity.9 */
    class C01839 implements Runnable {
        C01839() {
        }

        public void run() {
            MainNavigationActivity.this._fullScreenBannerController.loadBanner();
        }
    }

    public enum ApplicationMode {
        UNKNOWN,
        COMMON,
        CUSTOM
    }

    public enum ApplicationState {
        STARTED,
        EXITING
    }

    public MainNavigationActivity() {
        this._config = null;
        this._deviceIdParameters = null;
        this._fullScreenBannerController = null;
        this._splashScreenController = null;
        this._pushClient = null;
        this._currentMode = ApplicationMode.COMMON;
        this.loadUrlFromIntentHandler = new Handler();
        this.loadUrlFromIntentRunnable = new C01751();
    }

    public FullScreenBannerController getFullScreenBannerController() {
        return this._fullScreenBannerController;
    }

    public PushServerClient getPushClient() {
        if (this._pushClient == null) {
            this._pushClient = new PushServerClient(this);
        }
        return this._pushClient;
    }

    public AdMobFSBannerController getAdMobFSBannerController() {
        return this._adMobFSBannerController;
    }

    static {
        COVER_SCREEN_GRAVITY_CENTER = new LayoutParams(-1, -1, 17);
        _active = false;
    }

    public StartupConfirmationDialog getAlertDialog() {
        return this.alertDialog;
    }

    public static ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        applicationState = applicationState;
    }

    public DeviceIdParameters getDeviceIdParameters() {
        return this._deviceIdParameters;
    }

    public void setDeviceIdParameters(DeviceIdParameters _deviceIdParameters) {
        this._deviceIdParameters = _deviceIdParameters;
    }

    public BottomBannerLayout getBannerLayout() {
        return this.bannerLayout;
    }

    public void setMStartupScreenViewContainer(FrameLayout mStartupScreenViewContainer) {
        this.mFullScreenBannerView = mStartupScreenViewContainer;
    }

    public void onCreate(Bundle savedInstanceState) {
        int tabsLayoutId;
        super.onCreate(null);
        this._activity = this;
        Factory.getInstance().Init(this._activity);
        applicationState = ApplicationState.STARTED;
        try {
            this._config = WebWidgetConfigurationManager.getInstance(this._activity).loadConfiguration(this._activity);
            this._serverClient = AppsGeyserServerClient.getInstance(this._activity);
            this._serverClient.SendAfterInstallInfo();
            this._serverClient.SendUsageInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPreviousApplicationMode();
        _applyAppTheme(this._config);
        setContentView((int) C0186R.layout.main);
        _postApplyAppTheme(this._config);
        this._serverClient.GetApplicationMode();
        this.mContentView = (LinearLayout) findViewById(C0186R.id.contentFrame);
        if (this._config.getTabsPosition() == TabsPositions.BOTTOM) {
            tabsLayoutId = C0186R.layout.tabs_panel_bottom;
        } else {
            tabsLayoutId = C0186R.layout.tabs_panel;
        }
        getLayoutInflater().inflate(tabsLayoutId, this.mContentView, true);
        this.mContentView.setKeepScreenOn(this._config.getPreventFromSleep());
        this.mCustomViewContainer = (FrameLayout) findViewById(C0186R.id.customFrame);
        this.mFullScreenBannerView = (FrameLayout) findViewById(C0186R.id.fullScreenBannerContainer);
        this.mSplashScreenView = (ViewGroup) findViewById(C0186R.id.splashScreenView);
        this._splashScreenController = new SplashScreenController(this.mSplashScreenView, this);
        if (this._config.isSplashScreenEnabled()) {
            this._splashScreenController.showSplashScreen(this._config.getSplashScreenImage());
        } else {
            showContentView();
        }
        this._adMobFSBannerController = new AdMobFSBannerController(this);
        if (!StartupConfirmationDialog.isAlreadyShown(this) && this._config.isStartupConfirmationDialogEnabled()) {
            this.alertDialog = new StartupConfirmationDialog(this);
            this.alertDialog.show();
        }
        this.albumStorageController = new AlbumStorageController(this._config.getWidgetName());
        _initAppContent();
        DeviceIdParser.getInstance().rescan(this, this);
        new PullServerController().reScheduleNotification(this);
        new NotificationChecker().rescheduleLaunch(this);
    }

    protected void onStart() {
        super.onStart();
    }

    private void _applyAppTheme(WebWidgetConfiguration config) {
        if (config.getApplicationTheme() == ApplicationThemes.ACTION_BAR) {
            try {
                setTheme(C0186R.style.AppThemeActionBar);
                ActionBar bar = getSupportActionBar();
                if (bar != null) {
                    bar.setElevation(0.0f);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void _postApplyAppTheme(WebWidgetConfiguration config) {
        this._navigationDrawer = NavigationDrawerHolder.getNavigationDrawer(this);
        if (config.getApplicationTheme() == ApplicationThemes.SLIDER) {
            _showNavigationDrawer();
        } else {
            _removeNavigationDrawer();
        }
    }

    private void _initAppContent() {
        this._tabsController = Factory.getInstance().getTabsController();
        this._tabsController.initWithTabs(Factory.getInstance().getWidgetsController());
        CookieSyncManager.createInstance(this._activity);
        CookieSyncManager.getInstance().startSync();
        this.mAboutDialog = new AboutDialog(this._activity, C0186R.style.FullHeightDialog);
    }

    public void showCloseAppDialog() {
        SimpleDialogs.createConfirmDialog(null, getResources().getString(C0186R.string.appExitCaption), this, new C01762(), null).show();
    }

    public void showMessage(String text) {
        Builder builder = new Builder(this._activity);
        builder.setMessage(text);
        builder.setPositiveButton("ok", new C01773());
        builder.create().show();
    }

    public void showPausedContentInfo() {
        if (this._tabsController == null) {
            this._tabsController = Factory.getInstance().getTabsController();
        }
        WidgetsController widgetCtrlr = Factory.getInstance().getWidgetsController();
        widgetCtrlr.removeAll();
        widgetCtrlr.addWidget(WidgetEntity.createDefaultWidget(DefaultWidgetType.PAUSED));
        this._tabsController.initWithTabs(widgetCtrlr);
    }

    public void setApplicationMode(ApplicationMode mode) {
        if (!(mode == ApplicationMode.UNKNOWN || this._currentMode == mode)) {
            getSharedPreferences(PREFS_NAME, 0).edit().putInt(APPMODE_PARAM, this._currentMode.ordinal()).apply();
            this._currentMode = mode;
        }
        if (this.mAboutDialog != null) {
            this.mAboutDialog.setApplicationMode(this._currentMode);
        }
    }

    public void loadPreviousApplicationMode() {
        int mode = getSharedPreferences(PREFS_NAME, 0).getInt(APPMODE_PARAM, ApplicationMode.COMMON.ordinal());
        if (ApplicationMode.COMMON.ordinal() == mode) {
            this._currentMode = ApplicationMode.COMMON;
        } else if (ApplicationMode.CUSTOM.ordinal() == mode) {
            this._currentMode = ApplicationMode.CUSTOM;
        }
        if (this.mAboutDialog != null) {
            this.mAboutDialog.setApplicationMode(this._currentMode);
        }
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        this.mUploadMessage = uploadMsg;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (acceptType.length() == 0) {
            acceptType = "*/*";
        }
        intent.setType(acceptType);
        startActivityForResult(Intent.createChooser(intent, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE && this.mUploadMessage != null) {
            Uri result = (intent == null || resultCode != -1) ? null : intent.getData();
            this.mUploadMessage.onReceiveValue(result);
            this.mUploadMessage = null;
        }
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        switch (actionCode) {
            case ACTION_TAKE_PHOTO /*2*/:
                try {
                    File f = this.albumStorageController.setUpPhotoFile();
                    this.albumStorageController.setCurrentPhotoPath(f.getAbsolutePath());
                    takePictureIntent.putExtra("output", Uri.fromFile(f));
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    this.albumStorageController.setCurrentPhotoPath(null);
                    break;
                }
        }
        startActivityForResult(takePictureIntent, actionCode);
    }

    public static boolean isIntentAvailable(Context context, String action) {
        return context.getPackageManager().queryIntentActivities(new Intent(action), AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0;
    }

    protected void onPause() {
        super.onPause();
        _active = false;
        boolean showing = ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        if (((TelephonyManager) getSystemService("phone")).getCallState() == FILECHOOSER_RESULTCODE) {
            pauseBrowser();
        }
        if (!showing) {
            pauseBrowser();
        }
    }

    public void pauseBrowser() {
        BrowserWebView bannerBrowser = (BrowserWebView) findViewById(C0186R.id.banner_webkit);
        if (bannerBrowser != null) {
            if (!this._adMobFSBannerController.isActive()) {
                bannerBrowser.pauseTimers();
            }
            this._adMobFSBannerController.setActive(false);
        }
        CookieSyncManager.getInstance().stopSync();
    }

    protected void onResume() {
        super.onResume();
        _active = true;
        Intent intent = getIntent();
        if (intent != null) {
            Uri url = intent.getData();
            if (url != null) {
                this.urlFromIntentToLoad = url.toString();
                this.loadUrlFromIntentHandler.post(this.loadUrlFromIntentRunnable);
            }
        }
        if (!this._config.getPreventFromSleep()) {
            if (this._config.getPushAccount().length() != 0) {
                String regId = GCMRegistrar.getRegistrationId(this);
                if (regId == null || StringUtils.EMPTY.equals(regId)) {
                    registerGCM(this._config.getPushAccount());
                } else {
                    if (this._pushClient == null) {
                        this._pushClient = new PushServerClient(this);
                    }
                    this._pushClient.sendRegisteredId(regId);
                }
            } else if (this._config.getAppGuid().length() > 0) {
                updatePushAccount();
            }
            BrowserWebView bannerBrowser = (BrowserWebView) findViewById(C0186R.id.banner_webkit);
            if (bannerBrowser != null) {
                bannerBrowser.resumeTimers();
            }
            CookieSyncManager.getInstance().startSync();
        }
    }

    public void updatePushAccount() {
        if (this._pushClient == null) {
            this._pushClient = new PushServerClient(this);
        }
        this._pushClient.loadPushAccount();
    }

    public void registerGCM(String pushAccount) {
        String[] strArr = new String[FILECHOOSER_RESULTCODE];
        strArr[0] = pushAccount;
        GCMRegistrar.register(this, strArr);
    }

    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            setIntent(intent);
            Uri url = intent.getData();
            if (url != null) {
                this.urlFromIntentToLoad = url.toString();
                this.loadUrlFromIntentHandler.post(this.loadUrlFromIntentRunnable);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (this._navigationDrawer != null && this._navigationDrawer.isOpened()) {
            _closeNavigationDrawer();
            return true;
        } else if (this.mCustomView != null) {
            onHideCustomView();
            return true;
        } else {
            INavigationWidget navigationWIdget = Factory.getInstance().getNavigationWidget();
            if (navigationWIdget != null && (navigationWIdget instanceof TopNavigationWidget) && ((TopNavigationWidget) navigationWIdget).isSuggestionsVisible()) {
                ((TopNavigationWidget) navigationWIdget).hideSuggestionsView();
                return true;
            }
            if (!this._tabsController.onBackKeyDown()) {
                if (getFullScreenBannerController().isOnScreen()) {
                    if (!getFullScreenBannerController().isBackKeyLocked()) {
                        getFullScreenBannerController().closeBanner();
                    }
                } else if (!getApplicationState().equals(ApplicationState.EXITING)) {
                    showCloseAppDialog();
                }
            }
            return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        System.err.println("menu__onCreateOptionsMenu");
        return super.onCreateOptionsMenu(new MenuItemsHolder(this._config, this._currentMode, this, menu).getMenu());
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        System.err.println("menu__onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        System.err.println("menu__onOptionsItemSelected");
        if (_isMenuItemId(item.getItemId())) {
            return onOptionsItemSelected(item.getItemId(), item);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean _isMenuItemId(int itemId) {
        return itemId == C0186R.id.webapp_exit || itemId == C0186R.id.webapp_refresh || itemId == C0186R.id.webapp_about || itemId == C0186R.id.webapp_rate || itemId == C0186R.id.webapp_share || itemId == C0186R.id.webapp_back || itemId == C0186R.id.webapp_forward || itemId == C0186R.id.webapp_request_desktop || itemId == C0186R.id.webapp_pin_to_desktop || itemId == C0186R.id.webapp_add_to_home || itemId == C0186R.id.webapp_home;
    }

    public boolean onOptionsItemSelected(int itemId, MenuItem item) {
        boolean z = false;
        NavigationWidget navigationWidget = (NavigationWidget) Factory.getInstance().getNavigationWidget();
        switch (itemId) {
            case C0186R.id.webapp_back /*2131493034*/:
                if (navigationWidget == null) {
                    return true;
                }
                navigationWidget.onClickBackButton();
                return true;
            case C0186R.id.webapp_forward /*2131493035*/:
                if (navigationWidget == null) {
                    return true;
                }
                navigationWidget.onClickForwardButton();
                return true;
            case C0186R.id.webapp_request_desktop /*2131493036*/:
                if (navigationWidget == null || item == null) {
                    return true;
                }
                boolean z2;
                if (item.isChecked()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                navigationWidget.reloadWithChangedUserAgent(z2);
                if (!item.isChecked()) {
                    z = true;
                }
                item.setChecked(z);
                return true;
            case C0186R.id.webapp_pin_to_desktop /*2131493037*/:
                if (navigationWidget == null) {
                    return true;
                }
                navigationWidget.onPinToHomeScreenButtonClick();
                return true;
            case C0186R.id.webapp_add_to_home /*2131493038*/:
                if (navigationWidget == null) {
                    return true;
                }
                navigationWidget.onAddToStartPageClick();
                return true;
            case C0186R.id.webapp_home /*2131493039*/:
                if (navigationWidget == null) {
                    return true;
                }
                navigationWidget.onHomeButtonClick();
                return true;
            case C0186R.id.webapp_refresh /*2131493040*/:
                this._tabsController.getSelectedTab().getWebView().reload();
                return true;
            case C0186R.id.webapp_share /*2131493041*/:
                String bodyText;
                String subjectText;
                Intent sharingIntent = new Intent("android.intent.action.SEND");
                sharingIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                if (this._config.getShareExtraLink() != null && !this._config.getShareExtraLink().equals(StringUtils.EMPTY)) {
                    bodyText = this._config.getShareExtraLink();
                    subjectText = getResources().getString(C0186R.string.shareContentSubject);
                } else if (this._config.getUrlOverlayState() == UrlBarStates.ENABLED) {
                    bodyText = Factory.getInstance().getTabsController().getSelectedTab().getWebView().getUrl();
                    subjectText = getResources().getString(C0186R.string.shareSiteSubject);
                } else {
                    bodyText = getResources().getString(C0186R.string.getWidgetUrl) + this._config.getApplicationId() + "?" + this._config.getAffiliateString();
                    subjectText = getResources().getString(C0186R.string.shareContentSubject);
                }
                sharingIntent.putExtra("android.intent.extra.TEXT", bodyText);
                sharingIntent.putExtra("android.intent.extra.SUBJECT", subjectText);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                return true;
            case C0186R.id.webapp_about /*2131493042*/:
                this.mAboutDialog.showDialog();
                return true;
            case C0186R.id.webapp_rate /*2131493043*/:
                this._activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this._activity.getPackageName())));
                return true;
            case C0186R.id.webapp_exit /*2131493044*/:
                showCloseAppDialog();
                return true;
            default:
                return false;
        }
    }

    public void showConnectionErrorDialog() {
        if (this._connectionErrorDialog == null) {
            this._connectionErrorDialog = new Dialog(this, C0186R.style.FullHeightDialog);
            this._connectionErrorDialog.setContentView(C0186R.layout.connection_error_dialog);
            ((TextView) this._connectionErrorDialog.findViewById(C0302R.id.text)).setText(getResources().getString(C0186R.string.noInternetConnectionMessage));
            Button btn = (Button) this._connectionErrorDialog.findViewById(C0186R.id.ok);
            btn.setText("Try Again");
            btn.setOnClickListener(new C01784());
            btn = (Button) this._connectionErrorDialog.findViewById(C0186R.id.cancel);
            btn.setText("Exit");
            btn.setOnClickListener(new C01795());
            this._connectionErrorDialog.setCancelable(true);
            this._connectionErrorDialog.show();
        }
    }

    public void invoke(String origin, boolean allow, boolean remember) {
    }

    public View getVideoLoadingProgressView() {
        if (this.mVideoProgressView == null) {
            this.mVideoProgressView = LayoutInflater.from(this).inflate(C0186R.layout.video_loading_progress, null);
        }
        return this.mVideoProgressView;
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (this.mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        if (view instanceof FrameLayout) {
            FrameLayout frame = (FrameLayout) view;
            if (frame.getFocusedChild() instanceof VideoView) {
                this.mVideo = (VideoView) frame.getFocusedChild();
                this.mVideo.setOnCompletionListener(this);
                this.mVideo.setOnErrorListener(this);
            }
        }
        this.mCustomViewContainer.addView(view, COVER_SCREEN_GRAVITY_CENTER);
        this.mCustomView = view;
        this.mCustomViewCallback = callback;
        this.mContentView.setVisibility(8);
        _hideNavigationDrawer();
        this.mCustomViewContainer.setVisibility(0);
        this.mCustomViewContainer.bringToFront();
    }

    public void onHideCustomView() {
        if (this.mCustomView != null) {
            if (this.mVideo != null) {
                this.mVideo.stopPlayback();
            }
            this.mCustomView.setVisibility(8);
            this.mCustomViewContainer.removeView(this.mCustomView);
            this.mCustomView = null;
            this.mCustomViewContainer.setVisibility(8);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mContentView.setVisibility(0);
            _showNavigationDrawer();
        }
    }

    public void showContentView() {
        this.mFullScreenBannerView.setVisibility(8);
        this.mContentView.setVisibility(0);
        this.mSplashScreenView.setVisibility(8);
        this.mContentView.bringToFront();
        _showNavigationDrawer();
    }

    public void showFullscreenBannerView() {
        this.mContentView.setVisibility(8);
        this.mFullScreenBannerView.setVisibility(0);
        this.mSplashScreenView.setVisibility(8);
        this.mFullScreenBannerView.bringToFront();
        _hideNavigationDrawer();
    }

    public void showSplashScreen() {
        this.mContentView.setVisibility(8);
        this.mFullScreenBannerView.setVisibility(8);
        this.mSplashScreenView.setVisibility(0);
        this.mSplashScreenView.bringToFront();
        _hideNavigationDrawer();
    }

    public void showVideoView() {
        this.mContentView.setVisibility(8);
        this.mFullScreenBannerView.setVisibility(8);
        this.mSplashScreenView.setVisibility(8);
        _hideNavigationDrawer();
    }

    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        onHideCustomView();
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (getIntent() != null) {
            Uri uri = getIntent().getData();
            if (uri != null) {
                intent.setData(uri);
                startActivity(intent);
            }
        }
        return false;
    }

    public WebWidgetConfiguration getConfig() {
        return this._config;
    }

    public void showHttpAuthentication(WebView webView, HttpAuthHandler handler, String host, String realm, String title, String name, String password, int focusId) {
        if (getResources().getBoolean(C0186R.bool.autoHttpAuthorization)) {
            String login = this._config.getHttpAccessLogin();
            String pass = this._config.getHttpAccessPassword();
            setHttpAuthUsernamePassword(webView, host, realm, login, pass);
            handler.proceed(login, pass);
            return;
        }
        View v = LayoutInflater.from(this).inflate(C0186R.layout.http_authentication, null);
        if (name != null) {
            ((EditText) v.findViewById(C0186R.id.username_edit)).setText(name);
        }
        if (password != null) {
            ((EditText) v.findViewById(C0186R.id.password_edit)).setText(password);
        }
        String titleText = title;
        if (titleText == null) {
            titleText = getText(C0186R.string.sign_in_to).toString().replace("%s1", host).replace("%s2", realm);
        }
        AlertDialog dialog = new Builder(this).setTitle(titleText).setView(v).setPositiveButton("Sign in", new C01828(v, webView, host, realm, handler)).setNegativeButton("Cancel", new C01817(handler)).setOnCancelListener(new C01806(handler)).create();
        dialog.getWindow().setSoftInputMode(4);
        dialog.show();
        if (focusId != 0) {
            dialog.findViewById(focusId).requestFocus();
        } else {
            v.findViewById(C0186R.id.username_edit).requestFocus();
        }
    }

    public void setHttpAuthUsernamePassword(WebView webView, String host, String realm, String username, String password) {
        if (webView != null) {
            webView.setHttpAuthUsernamePassword(host, realm, username, password);
        }
    }

    public boolean onAdHeadersReceived(Map<String, List<String>> headers) {
        _applyBehaviors(new BehaviorFactory().createPreloadBehaviors(headers));
        if (new Date().compareTo(new Date(getSharedPreferences(PREFS_NAME, 0).getLong(ADS_SLEEP_PARAM, 0))) >= 0) {
            return true;
        }
        return false;
    }

    public void onAdLoadFinished() {
        _applyBehaviors(new BehaviorFactory().createPostloadBehaviors(this.adsLoader.getLastResponseHeaders()));
    }

    private void _applyBehaviors(List<BehaviorVisitor> behaviors) {
        for (BehaviorVisitor visitor : behaviors) {
            acceptBehavior(visitor);
            this.adsLoader.acceptBehavior(visitor);
            this.bannerLayout.acceptBehavior(visitor);
        }
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isCurrentStartupAdView() {
        return this.mFullScreenBannerView.getVisibility() == 0;
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (this._menuItemsHolder == null) {
            this._menuItemsHolder = new MenuItemsHolder(this._config, this);
        }
        if (this._navigationDrawer != null) {
            this._navigationDrawer.setOptions(this._menuItemsHolder.getAllItems());
            this._navigationDrawer.show();
        }
    }

    public void onDeviceIdParametersObtained(DeviceIdParameters result) {
        this._deviceIdParameters = result;
        this._fullScreenBannerController = new FullScreenBannerController(this.mFullScreenBannerView, this);
        this._fullScreenBannerController.setFullScreenBannerListener(this);
        applicationState = ApplicationState.STARTED;
        if (this._config.isSplashScreenEnabled()) {
            new Handler().postDelayed(new C01839(), 1500);
        } else {
            this._fullScreenBannerController.loadBanner();
        }
        this.adsLoader = new AdsLoader();
        this.bannerLayout = new BottomBannerLayout();
        this.bannerLayout.init(this, this.adsLoader);
        this.bannerLayout.setPosition(this._config.getBannerPosition());
        this.adsLoader.init(this._serverClient.GetBannerUrl(), this);
        this.adsLoader.setAdsLoadingFinishedListener(this);
        this.adsLoader.setHeaderReceiver(this);
        this.adsLoader.setBottomBannerLayout(this.bannerLayout);
        this.adsLoader.reload();
    }

    private void _showNavigationDrawer() {
        if (this._navigationDrawer != null) {
            this._navigationDrawer.show();
        }
    }

    private void _hideNavigationDrawer() {
        if (this._navigationDrawer != null) {
            this._navigationDrawer.hide();
        }
    }

    private void _removeNavigationDrawer() {
        if (this._navigationDrawer != null) {
            this._navigationDrawer.remove();
        }
    }

    private void _closeNavigationDrawer() {
        if (this._navigationDrawer != null) {
            this._navigationDrawer.close();
        }
    }

    public static boolean isActive() {
        return _active;
    }

    public void onLoadStarted() {
    }

    public void onLoadFinished() {
    }

    public void onAdFailedToLoad() {
    }

    public void onAdClosed() {
        if (getApplicationState().equals(ApplicationState.EXITING)) {
            finish();
        }
    }

    public void setUrlBarVisibility(int viewVisililityCode) {
        runOnUiThread(new AnonymousClass10(viewVisililityCode));
    }

    public ArrayList<BrowsingHistoryItem> getWeeklyHistory() {
        ArrayList<BrowsingHistoryItem> historyItems = new ArrayList();
        INavigationWidget navigationWIdget = Factory.getInstance().getNavigationWidget();
        if (navigationWIdget == null || !(navigationWIdget instanceof NavigationWidget)) {
            return historyItems;
        }
        return ((NavigationWidget) navigationWIdget).getWeeklyHistory();
    }

    public int removeHistoryItem(long id) {
        INavigationWidget navigationWIdget = Factory.getInstance().getNavigationWidget();
        if (navigationWIdget == null || !(navigationWIdget instanceof NavigationWidget)) {
            return -1;
        }
        return ((NavigationWidget) navigationWIdget).removeHistoryItem(id);
    }
}
