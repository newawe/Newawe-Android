package com.Newawe.configuration;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.Newawe.MainNavigationActivity;
import com.Newawe.MainNavigationActivity.ApplicationMode;
import com.Newawe.ads.BottomBannerLayout.BannerPosition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;

public class WebWidgetConfiguration implements Serializable {
    private static final String GUID_KEY_NAME = "AppGuidString";
    public static final String PUSH_ACCOUNT_NAME = "PushAccountName";
    private boolean acceptCookie;
    private String addUsageUrl;
    private String affiliateGetString;
    private String appGuid;
    private ApplicationMode appMode;
    private ApplicationThemes appTheme;
    private int applicationId;
    private BannerPosition bannerPosition;
    private int contentHeight;
    private int contentWidth;
    private DownloadActions downloadAction;
    private boolean fullScreenBannerEnabled;
    private String fullScreenModeUrl;
    private String httpAccessLogin;
    private String httpAccessPassword;
    private boolean isAboutScreenEnabled;
    private RedirectionTypes isRedirectEnabled;
    private boolean isSplashScreenEnabled;
    private String locationUrl;
    private boolean onExitFullScreenBannerEnabled;
    private boolean preventFromSleep;
    private String publisherName;
    private String pushAccount;
    private boolean rateItemVisibility;
    private String registeredUrl;
    private String shareExtraLink;
    private boolean showAboutMenuItem;
    private boolean showExitMenuItem;
    private boolean showRefreshMenuItem;
    private boolean showShareMenuItem;
    private boolean showStartupConfirmationDialog;
    private String splashScreenImage;
    private TabsPositions tabsPosition;
    private long updatePeriodOfFullScreenModeInMs;
    private long updatePeriodOfWidgetInMS;
    private ArrayList<UrlBarMenuButton> urlBarMenuButtons;
    private UrlBarStyles urlBarStyle;
    private UrlBarStates urlOverlayEnabled;
    private String widgetName;

    public enum ApplicationThemes {
        ACTION_BAR,
        SLIDER,
        NO_MENU
    }

    public enum DownloadActions {
        OPEN,
        SAVE,
        DIALOG
    }

    public enum RedirectionTypes {
        REDIRECT_ALL,
        REDIRECT_EXTERNAL,
        NO_REDIRECT
    }

    public enum TabsPositions {
        TOP,
        BOTTOM
    }

    public enum UrlBarStates {
        ENABLED,
        ENABLED_ON_EXTERNAL_URLS,
        DISABLED
    }

    public enum UrlBarStyles {
        TOP,
        BOTTOM
    }

    public WebWidgetConfiguration() {
        this.appMode = ApplicationMode.UNKNOWN;
        this.appTheme = ApplicationThemes.SLIDER;
        this.tabsPosition = TabsPositions.TOP;
        this.bannerPosition = BannerPosition.TOP;
        this.rateItemVisibility = false;
        this.acceptCookie = true;
        this.affiliateGetString = StringUtils.EMPTY;
        this.fullScreenBannerEnabled = false;
        this.onExitFullScreenBannerEnabled = false;
        this.publisherName = StringUtils.EMPTY;
        this.appGuid = StringUtils.EMPTY;
        this.pushAccount = StringUtils.EMPTY;
        this.isRedirectEnabled = RedirectionTypes.REDIRECT_ALL;
        this.isAboutScreenEnabled = true;
        this.shareExtraLink = StringUtils.EMPTY;
        this.showStartupConfirmationDialog = false;
        this.isSplashScreenEnabled = false;
        this.downloadAction = DownloadActions.OPEN;
        this.splashScreenImage = StringUtils.EMPTY;
        this.urlBarMenuButtons = new ArrayList();
        this.urlOverlayEnabled = UrlBarStates.DISABLED;
        this.urlBarStyle = UrlBarStyles.BOTTOM;
    }

    public void addUrlBarMenuButton(UrlBarMenuButton button) {
        this.urlBarMenuButtons.add(button);
    }

    public ArrayList<UrlBarMenuButton> getUrlBarMenuButtons() {
        return this.urlBarMenuButtons;
    }

    public void setUrlBarMenuButtons(ArrayList<UrlBarMenuButton> urlBarMenuButtons) {
        this.urlBarMenuButtons = urlBarMenuButtons;
    }

    public UrlBarStyles getUrlBarStyle() {
        return this.urlBarStyle;
    }

    public void setUrlBarStyle(UrlBarStyles urlBarStyle) {
        this.urlBarStyle = urlBarStyle;
    }

    public RedirectionTypes getIsRedirectEnabled() {
        return this.isRedirectEnabled;
    }

    public void setIsRedirectEnabled(RedirectionTypes isEnabled) {
        this.isRedirectEnabled = isEnabled;
    }

    public String getAppGuid() {
        return this.appGuid;
    }

    public String loadGuid(Context context) {
        this.appGuid = context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getString(GUID_KEY_NAME, StringUtils.EMPTY);
        if (this.appGuid.length() <= 0) {
            this.appGuid = UUID.randomUUID().toString();
            saveNewAppGuid(this.appGuid, context);
        }
        return this.appGuid;
    }

    public void saveNewAppGuid(String newAppGuid, Context context) {
        this.appGuid = newAppGuid;
        Editor editor = context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit();
        editor.putString(GUID_KEY_NAME, newAppGuid);
        editor.commit();
    }

    public String getPushAccount() {
        return this.pushAccount;
    }

    public Boolean loadPushAccount(Context context) {
        boolean z = false;
        this.pushAccount = context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getString(PUSH_ACCOUNT_NAME, StringUtils.EMPTY);
        if (this.pushAccount.length() > 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public void saveNewPushAccount(String newPushAccount, Context context) {
        this.pushAccount = newPushAccount;
        Editor editor = context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit();
        editor.putString(PUSH_ACCOUNT_NAME, this.pushAccount);
        editor.commit();
    }

    public String getAffiliateString() {
        return this.affiliateGetString;
    }

    public void setAffiliateString(String str) {
        this.affiliateGetString = str;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public void setPublisherName(String str) {
        this.publisherName = str;
    }

    public String getWidgetName() {
        return this.widgetName;
    }

    public void setWidgetName(String name) {
        this.widgetName = name;
    }

    public String getRegisteredUrl() {
        return this.registeredUrl;
    }

    public void setRegisteredUrl(String registeredUrl) {
        this.registeredUrl = registeredUrl;
    }

    public String getLocationUrl() {
        return this.locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public int getApplicationId() {
        return this.applicationId;
    }

    public boolean getPreventFromSleep() {
        return this.preventFromSleep;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getFullScreenModeUrl() {
        return this.fullScreenModeUrl;
    }

    public void setFullScreenModeUrl(String fullScreenModeUrl) {
        this.fullScreenModeUrl = fullScreenModeUrl;
    }

    public boolean getShowRefreshMenuItem() {
        return this.showRefreshMenuItem;
    }

    public long getUpdatePeriodOfWidgetInMS() {
        return this.updatePeriodOfWidgetInMS;
    }

    public void setShowRefreshMenuItem(boolean val) {
        this.showRefreshMenuItem = val;
    }

    public void setUpdatePeriodOfWidgetInMS(long updatePeriodOfWidgetInMS) {
        this.updatePeriodOfWidgetInMS = updatePeriodOfWidgetInMS;
    }

    public long getUpdatePeriodOfFullScreenModeInMs() {
        return this.updatePeriodOfFullScreenModeInMs;
    }

    public void setIsAboutScreenEnabled(boolean isEnabled) {
        this.isAboutScreenEnabled = isEnabled;
    }

    public boolean getIsAboutScreenEnabled() {
        return this.isAboutScreenEnabled;
    }

    public void setUpdatePeriodOfFullScreenModeInMs(long updatePeriodOfFullScreenModeInMs) {
        this.updatePeriodOfFullScreenModeInMs = updatePeriodOfFullScreenModeInMs;
    }

    public String getAddUsageUrl() {
        return this.addUsageUrl;
    }

    public void setAddUsageUrl(String addUsageUrl) {
        this.addUsageUrl = addUsageUrl;
    }

    public int getContentWidth() {
        return this.contentWidth;
    }

    public void setContentWidth(int contentWidth) {
        this.contentWidth = contentWidth;
    }

    public int getContentHeight() {
        return this.contentHeight;
    }

    public void setContentHeight(int contentHeight) {
        this.contentHeight = contentHeight;
    }

    public String getHttpAccessLogin() {
        return this.httpAccessLogin;
    }

    public void setHttpAccessLogin(String login) {
        this.httpAccessLogin = login;
    }

    public String getHttpAccessPassword() {
        return this.httpAccessPassword;
    }

    public void setHttpAccessPassword(String pass) {
        this.httpAccessPassword = pass;
    }

    public void setPreventFromSleep(boolean isPreventFromSleep) {
        this.preventFromSleep = isPreventFromSleep;
    }

    public ApplicationMode getApplicationMode() {
        return this.appMode;
    }

    public void setApplicationMode(ApplicationMode mode) {
        this.appMode = mode;
    }

    public ApplicationThemes getApplicationTheme() {
        return this.appTheme;
    }

    public void setApplicationTheme(ApplicationThemes theme) {
        this.appTheme = theme;
    }

    public BannerPosition getBannerPosition() {
        return this.bannerPosition;
    }

    public void setBannerPosition(BannerPosition bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public boolean getRateItemVisibility() {
        return this.rateItemVisibility;
    }

    public void setRateItemVisibility(boolean rateItem) {
        this.rateItemVisibility = rateItem;
    }

    public boolean getAcceptCookie() {
        return this.acceptCookie;
    }

    public void setAcceptCookie(boolean acceptCookie) {
        this.acceptCookie = acceptCookie;
    }

    public boolean getFullscreenBannerEnabled() {
        return this.fullScreenBannerEnabled;
    }

    public void setFullscreenBannerEnabled(boolean isEnabled) {
        this.fullScreenBannerEnabled = isEnabled;
    }

    public boolean getOnExitFullscreenBannerEnabled() {
        return this.onExitFullScreenBannerEnabled;
    }

    public void setOnExitFullscreenBannerEnabled(boolean isEnabled) {
        this.onExitFullScreenBannerEnabled = isEnabled;
    }

    public UrlBarStates getUrlOverlayState() {
        return this.urlOverlayEnabled;
    }

    public void setUrlOverlayState(UrlBarStates state) {
        this.urlOverlayEnabled = state;
    }

    public boolean getShowShareMenuItem() {
        return this.showShareMenuItem;
    }

    public void setShowShareMenuItem(boolean showShareMenuItem) {
        this.showShareMenuItem = showShareMenuItem;
    }

    public boolean getShowAboutMenuItem() {
        return this.showAboutMenuItem;
    }

    public void setShowAboutMenuItem(boolean showAboutMenuItem) {
        this.showAboutMenuItem = showAboutMenuItem;
    }

    public boolean getShowExitMenuItem() {
        return this.showExitMenuItem;
    }

    public void setShowExitMenuItem(boolean showExitMenuItem) {
        this.showExitMenuItem = showExitMenuItem;
    }

    public void setSplashScreen(String image) {
        if (image != null) {
            this.isSplashScreenEnabled = true;
            this.splashScreenImage = image;
        }
    }

    public boolean isSplashScreenEnabled() {
        return this.isSplashScreenEnabled;
    }

    public String getSplashScreenImage() {
        return this.splashScreenImage;
    }

    public DownloadActions getDownloadAction() {
        return this.downloadAction;
    }

    public void setDownloadAction(DownloadActions downloadAction) {
        this.downloadAction = downloadAction;
    }

    public void setShowStartupConfirmationDialog(boolean showDialog) {
        this.showStartupConfirmationDialog = showDialog;
    }

    public boolean isStartupConfirmationDialogEnabled() {
        return this.showStartupConfirmationDialog;
    }

    public String getShareExtraLink() {
        return this.shareExtraLink;
    }

    public void setShareExtraLink(String shareExtraLink) {
        this.shareExtraLink = shareExtraLink;
    }

    public TabsPositions getTabsPosition() {
        return this.tabsPosition;
    }

    public void setTabsPosition(TabsPositions tabsPosition) {
        this.tabsPosition = tabsPosition;
    }
}
