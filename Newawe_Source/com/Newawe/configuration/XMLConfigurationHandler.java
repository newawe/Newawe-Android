package com.Newawe.configuration;

import com.Newawe.MainNavigationActivity.ApplicationMode;
import com.Newawe.ads.BottomBannerLayout.BannerPosition;
import com.Newawe.configuration.UrlBarMenuButton.UrlBarMenuButtonTypes;
import com.Newawe.configuration.WebWidgetConfiguration.ApplicationThemes;
import com.Newawe.configuration.WebWidgetConfiguration.DownloadActions;
import com.Newawe.configuration.WebWidgetConfiguration.RedirectionTypes;
import com.Newawe.configuration.WebWidgetConfiguration.TabsPositions;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStates;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStyles;
import com.Newawe.controllers.WidgetsController;
import com.Newawe.model.WidgetEntity;
import com.Newawe.model.WidgetEntity.LoadingCurtainType;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.CookieSpecs;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLConfigurationHandler extends DefaultHandler {
    private static final String PROP_FILE = "file";
    private static final String PROP_ICON = "icon";
    private static final String PROP_IMAGE = "image";
    private static final String PROP_NAME = "name";
    private static final String PROP_PATTERN = "pattern";
    private static final String PROP_REGEXP = "regexp";
    private static final String PROP_TITLE = "title";
    private static final String PROP_URL = "url";
    private static final String SHARE_EXTRA_LINK = "shareExtraLink";
    private static final String TAG_ACCEPT_COOKIE = "acceptCookie";
    private static final String TAG_AFFILIATE = "affiliate";
    private static final String TAG_BANNER_POSITION = "bannerPosition";
    private static final String TAG_BLOCK_APP_FROM_SLEEP = "preventFromSleep";
    private static final String TAG_BUTTON = "button";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_CONTENT_HEIGHT = "height";
    private static final String TAG_CONTENT_PROTECTION = "contentProtection";
    private static final String TAG_CONTENT_WIDTH = "width";
    private static final String TAG_DOWNLOAD_ACTION = "downloadAction";
    private static final String TAG_ENABLE_FULLSCREEN_BANNER = "enableFullScreenBanner";
    private static final String TAG_ENABLE_ON_EXIT_FULLSCREEN_BANNER = "enableOnExitFullScreenBanner";
    private static final String TAG_ENABLE_URL_BAR = "enableUrlBar";
    private static final String TAG_FULL_SCREEN_MODE = "fullScreenMode";
    private static final String TAG_GET_STRING = "getString";
    private static final String TAG_ID = "id";
    private static final String TAG_INJECT_JS = "injectJS";
    private static final String TAG_IS_ABOUT_SCREEN_ENABLED = "enableAboutScreen";
    private static final String TAG_IS_REDIRECT_ENABLED = "enableRedirection";
    private static final String TAG_LINK = "link";
    private static final String TAG_LOADING_CURTAIN = "loadingCurtain";
    private static final String TAG_LOCATON_URL = "locationUrl";
    private static final String TAG_LOGIN = "login";
    private static final String TAG_NAME = "name";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_PUBLISHER_NAME = "publisher";
    private static final String TAG_RATE_ITEM_VISIBILITY = "rateItemVisibility";
    private static final String TAG_REGISTERED_URL = "registeredUrl";
    private static final String TAG_SCRIPT = "script";
    private static final String TAG_SHOW_ABOUT_MENU_ITEM = "showAboutMenuItem";
    private static final String TAG_SHOW_AS_TAB = "showAsTab";
    private static final String TAG_SHOW_EXIT_MENU_ITEM = "showExitMenuItem";
    private static final String TAG_SHOW_REFRESH_MENU_ITEM = "showRefreshMenuItem";
    private static final String TAG_SHOW_SHARE_MENU_ITEM = "showShareMenuItem";
    private static final String TAG_SHOW_STARTUP_CONFIRMATION_DIALOG = "showStartupConfirmationDialog";
    private static final String TAG_SPLASH_SCREEN = "splashScreen";
    private static final String TAG_TABS_POSITION = "tabsPosition";
    private static final String TAG_TAB_ICON = "tabIcon";
    private static final String TAG_TAB_ID = "tabId";
    private static final String TAG_TAB_NAME = "tabName";
    private static final String TAG_THEME = "theme";
    private static final String TAG_UPDATE = "update";
    private static final String TAG_URL_BAR_MENU_ITEMS = "urlBarMenuItems";
    private static final String TAG_URL_BAR_STYLE = "urlBarStyle";
    private static final String TAG_USAGE = "usage";
    private static final String TAG_USER_AGENT = "userAgent";
    private static final String TAG_USER_INTERFACE = "userInterface";
    private static final String TAG_WIDGET_NAME = "widgetName";
    private WidgetEntity _currentWidgetEntity;
    private StringBuilder builder;
    private String current2ndParentTag;
    private String currentParentTag;
    private Boolean inInjectJsTag;
    private Boolean scriptTagsFound;
    private WebWidgetConfiguration webWidgetConfiguration;
    private WidgetsController widgetsController;

    XMLConfigurationHandler(WebWidgetConfiguration webWidgetConfiguration, WidgetsController widgetsController) {
        this.current2ndParentTag = StringUtils.EMPTY;
        this.currentParentTag = StringUtils.EMPTY;
        this.inInjectJsTag = Boolean.valueOf(false);
        this.scriptTagsFound = Boolean.valueOf(false);
        this._currentWidgetEntity = null;
        this.webWidgetConfiguration = webWidgetConfiguration;
        this.widgetsController = widgetsController;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String str = String.copyValueOf(ch, start, length).trim();
        if (str.length() != 0) {
            this.builder.append(str);
        }
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.builder = new StringBuilder();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equalsIgnoreCase(TAG_FULL_SCREEN_MODE)) {
            this.current2ndParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_REGISTERED_URL)) {
            this.currentParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_USAGE)) {
            this.currentParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_LOCATON_URL)) {
            this.currentParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_CONTENT)) {
            this.currentParentTag = localName;
            this._currentWidgetEntity = new WidgetEntity();
        } else if (localName.equalsIgnoreCase(TAG_CONTENT_PROTECTION)) {
            this.currentParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_AFFILIATE)) {
            this.currentParentTag = localName;
        } else if (localName.equalsIgnoreCase(TAG_INJECT_JS)) {
            this.inInjectJsTag = Boolean.valueOf(true);
        } else if (localName.equalsIgnoreCase(TAG_SCRIPT)) {
            String pattern = attributes.getValue(PROP_PATTERN);
            String regex = attributes.getValue(PROP_REGEXP);
            String file = attributes.getValue(PROP_FILE);
            if (file != null && this._currentWidgetEntity != null && this.inInjectJsTag.booleanValue()) {
                this._currentWidgetEntity.addScript(new IncludeScriptConfigEntity(pattern, regex, file));
                this.scriptTagsFound = Boolean.valueOf(true);
            }
        } else if (localName.equalsIgnoreCase(TAG_URL_BAR_MENU_ITEMS)) {
            this.currentParentTag = localName;
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_URL_BAR_MENU_ITEMS) && localName.equalsIgnoreCase(TAG_BUTTON)) {
            String button = attributes.getValue(TAG_NAME);
            if (button.equalsIgnoreCase("back")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.BACK));
            } else if (button.equalsIgnoreCase("forward")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.FORWARD));
            } else if (button.equalsIgnoreCase("refresh")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.REFRESH));
            } else if (button.equalsIgnoreCase("request_desktop")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.REQUEST_DESKTOP));
            } else if (button.equalsIgnoreCase("pin_to_desktop")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.PIN_TO_DESKTOP));
            } else if (button.equalsIgnoreCase("add_to_home")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.ADD_TO_HOME));
            } else if (button.equalsIgnoreCase("home")) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuButton(UrlBarMenuButtonTypes.HOME));
            } else if (button.equalsIgnoreCase(TAG_LINK)) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarMenuLinkButton(attributes.getValue(PROP_TITLE), attributes.getValue(PROP_URL)));
            } else if (button.equalsIgnoreCase(PROP_ICON)) {
                this.webWidgetConfiguration.addUrlBarMenuButton(new UrlBarIcon(attributes.getValue(PROP_TITLE), attributes.getValue(PROP_URL), attributes.getValue(PROP_ICON)));
            }
        } else if (localName.equalsIgnoreCase(TAG_SPLASH_SCREEN)) {
            String image = attributes.getValue(PROP_IMAGE);
            if (image != null) {
                this.webWidgetConfiguration.setSplashScreen(image);
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equalsIgnoreCase(TAG_FULL_SCREEN_MODE)) {
            this.current2ndParentTag = StringUtils.EMPTY;
        } else if (localName.equalsIgnoreCase(TAG_REGISTERED_URL) || localName.equalsIgnoreCase(TAG_USAGE) || localName.equalsIgnoreCase(TAG_LOCATON_URL) || localName.equalsIgnoreCase(TAG_CONTENT) || localName.equalsIgnoreCase(TAG_CONTENT_PROTECTION) || localName.equalsIgnoreCase(TAG_AFFILIATE) || localName.equalsIgnoreCase(TAG_URL_BAR_MENU_ITEMS)) {
            this.currentParentTag = StringUtils.EMPTY;
            if (localName.equalsIgnoreCase(TAG_CONTENT)) {
                this.widgetsController.addWidget(this._currentWidgetEntity);
                this._currentWidgetEntity = null;
            }
        } else if (this.current2ndParentTag.equalsIgnoreCase(TAG_FULL_SCREEN_MODE)) {
            if (this.currentParentTag.equalsIgnoreCase(TAG_CONTENT)) {
                if (localName.equalsIgnoreCase(TAG_ID)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setId(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_NAME)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setName(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_LINK)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setLink(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_UPDATE)) {
                    if (this._currentWidgetEntity != null) {
                        if (this.builder.toString().length() > 0) {
                            this._currentWidgetEntity.setUpdateTime(Integer.parseInt(this.builder.toString()));
                        } else {
                            this._currentWidgetEntity.setUpdateTime(0);
                        }
                    }
                } else if (localName.equalsIgnoreCase(TAG_CONTENT_WIDTH)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setWidth(Integer.parseInt(this.builder.toString()));
                    }
                } else if (localName.equalsIgnoreCase(TAG_CONTENT_HEIGHT)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setHeight(Integer.parseInt(this.builder.toString()));
                    }
                } else if (localName.equalsIgnoreCase(TAG_TAB_NAME)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setTabName(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_TAB_ICON)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setTabIcon(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_INJECT_JS)) {
                    if (!(this._currentWidgetEntity == null || this.scriptTagsFound.booleanValue())) {
                        this._currentWidgetEntity.setInjectJS(this.builder.toString());
                    }
                    this.scriptTagsFound = Boolean.valueOf(false);
                    this.inInjectJsTag = Boolean.valueOf(false);
                } else if (localName.equalsIgnoreCase(TAG_LOADING_CURTAIN)) {
                    if (this._currentWidgetEntity != null) {
                        String typeName = this.builder.toString();
                        LoadingCurtainType type = LoadingCurtainType.NONE;
                        if (typeName.compareToIgnoreCase("none") == 0) {
                            type = LoadingCurtainType.NONE;
                        } else if (typeName.compareToIgnoreCase(CookieSpecs.DEFAULT) == 0) {
                            type = LoadingCurtainType.DEFAULT;
                        } else if (typeName.compareToIgnoreCase("banner") == 0) {
                            type = LoadingCurtainType.BANNER;
                        } else if (typeName.compareToIgnoreCase("custom") == 0) {
                            type = LoadingCurtainType.CUSTOM;
                        }
                        this._currentWidgetEntity.setLoadingCurtainType(type);
                    }
                } else if (localName.equalsIgnoreCase(TAG_USER_AGENT)) {
                    if (this._currentWidgetEntity != null) {
                        this._currentWidgetEntity.setUserAgent(this.builder.toString());
                    }
                } else if (localName.equalsIgnoreCase(TAG_TAB_ID)) {
                    this._currentWidgetEntity.setTabId(this.builder.toString());
                } else if (localName.equalsIgnoreCase(TAG_SHOW_AS_TAB)) {
                    this._currentWidgetEntity.setShowAsTab(this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE));
                }
            }
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_REGISTERED_URL)) {
            if (localName.equalsIgnoreCase(TAG_LINK)) {
                this.webWidgetConfiguration.setRegisteredUrl(this.builder.toString());
            }
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_USAGE)) {
            if (localName.equalsIgnoreCase(TAG_LINK)) {
                this.webWidgetConfiguration.setAddUsageUrl(this.builder.toString());
            }
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_LOCATON_URL)) {
            if (localName.equalsIgnoreCase(TAG_LINK)) {
                this.webWidgetConfiguration.setLocationUrl(this.builder.toString());
            }
        } else if (localName.equalsIgnoreCase(TAG_ID)) {
            this.webWidgetConfiguration.setApplicationId(Integer.parseInt(this.builder.toString().replaceAll(LineSeparator.Web, StringUtils.EMPTY).replaceAll("\t", StringUtils.EMPTY)));
        } else if (localName.equalsIgnoreCase(TAG_WIDGET_NAME)) {
            this.webWidgetConfiguration.setWidgetName(this.builder.toString());
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_CONTENT_PROTECTION)) {
            if (localName.equalsIgnoreCase(TAG_LOGIN)) {
                this.webWidgetConfiguration.setHttpAccessLogin(this.builder.toString());
            } else if (localName.equalsIgnoreCase(TAG_PASSWORD)) {
                this.webWidgetConfiguration.setHttpAccessPassword(this.builder.toString());
            }
        } else if (localName.equalsIgnoreCase(TAG_USER_INTERFACE)) {
            value = this.builder.toString();
            if (value.equals(CookieSpecs.STANDARD)) {
                this.webWidgetConfiguration.setApplicationMode(ApplicationMode.COMMON);
            } else if (value.equals("custom")) {
                this.webWidgetConfiguration.setApplicationMode(ApplicationMode.CUSTOM);
            }
        } else if (localName.equalsIgnoreCase(TAG_BANNER_POSITION)) {
            value = this.builder.toString();
            if (value.equals("top")) {
                this.webWidgetConfiguration.setBannerPosition(BannerPosition.TOP);
            } else if (value.equals("bottom")) {
                this.webWidgetConfiguration.setBannerPosition(BannerPosition.BOTTOM);
            }
        } else if (localName.equalsIgnoreCase(TAG_RATE_ITEM_VISIBILITY)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setRateItemVisibility(true);
            } else {
                this.webWidgetConfiguration.setRateItemVisibility(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_ACCEPT_COOKIE)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setAcceptCookie(true);
            } else {
                this.webWidgetConfiguration.setAcceptCookie(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_BLOCK_APP_FROM_SLEEP)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setPreventFromSleep(true);
            } else {
                this.webWidgetConfiguration.setPreventFromSleep(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_SHOW_REFRESH_MENU_ITEM)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setShowRefreshMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowRefreshMenuItem(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_SHOW_SHARE_MENU_ITEM)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setShowShareMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowShareMenuItem(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_SHOW_ABOUT_MENU_ITEM)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setShowAboutMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowAboutMenuItem(false);
            }
        } else if (localName.equalsIgnoreCase(TAG_SHOW_EXIT_MENU_ITEM)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setShowExitMenuItem(true);
            } else {
                this.webWidgetConfiguration.setShowExitMenuItem(false);
            }
        } else if (localName.equalsIgnoreCase(SHARE_EXTRA_LINK)) {
            this.webWidgetConfiguration.setShareExtraLink(this.builder.toString());
        } else if (localName.equalsIgnoreCase(TAG_ENABLE_FULLSCREEN_BANNER)) {
            this.webWidgetConfiguration.setFullscreenBannerEnabled(this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE));
        } else if (localName.equalsIgnoreCase(TAG_ENABLE_ON_EXIT_FULLSCREEN_BANNER)) {
            this.webWidgetConfiguration.setOnExitFullscreenBannerEnabled(this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE));
        } else if (localName.equalsIgnoreCase(TAG_SHOW_STARTUP_CONFIRMATION_DIALOG)) {
            this.webWidgetConfiguration.setShowStartupConfirmationDialog(this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE));
        } else if (localName.equalsIgnoreCase(TAG_IS_ABOUT_SCREEN_ENABLED)) {
            this.webWidgetConfiguration.setIsAboutScreenEnabled(this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE));
        } else if (localName.equalsIgnoreCase(TAG_THEME)) {
            value = this.builder.toString();
            if (value.equalsIgnoreCase("ACTION_BAR")) {
                this.webWidgetConfiguration.setApplicationTheme(ApplicationThemes.ACTION_BAR);
            } else if (value.equalsIgnoreCase("NO_MENU")) {
                this.webWidgetConfiguration.setApplicationTheme(ApplicationThemes.NO_MENU);
            } else {
                this.webWidgetConfiguration.setApplicationTheme(ApplicationThemes.SLIDER);
            }
        } else if (localName.equalsIgnoreCase(TAG_ENABLE_URL_BAR)) {
            UrlBarStates urlBarState = UrlBarStates.DISABLED;
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                urlBarState = UrlBarStates.ENABLED;
            } else if (this.builder.toString().equalsIgnoreCase("on_external_urls")) {
                urlBarState = UrlBarStates.ENABLED_ON_EXTERNAL_URLS;
            }
            this.webWidgetConfiguration.setUrlOverlayState(urlBarState);
        } else if (localName.equalsIgnoreCase(TAG_URL_BAR_STYLE)) {
            UrlBarStyles style = UrlBarStyles.BOTTOM;
            if (this.builder.toString().equalsIgnoreCase("top")) {
                style = UrlBarStyles.TOP;
            }
            this.webWidgetConfiguration.setUrlBarStyle(style);
        } else if (localName.equalsIgnoreCase(TAG_TABS_POSITION)) {
            TabsPositions position = TabsPositions.TOP;
            if (this.builder.toString().equalsIgnoreCase("bottom")) {
                position = TabsPositions.BOTTOM;
            }
            this.webWidgetConfiguration.setTabsPosition(position);
        } else if (localName.equalsIgnoreCase(TAG_PUBLISHER_NAME)) {
            this.webWidgetConfiguration.setPublisherName(this.builder.toString());
        } else if (this.currentParentTag.equalsIgnoreCase(TAG_AFFILIATE)) {
            if (localName.equalsIgnoreCase(TAG_GET_STRING)) {
                this.webWidgetConfiguration.setAffiliateString(this.builder.toString());
            }
        } else if (localName.equalsIgnoreCase(TAG_IS_REDIRECT_ENABLED)) {
            if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE)) {
                this.webWidgetConfiguration.setIsRedirectEnabled(RedirectionTypes.NO_REDIRECT);
            } else if (this.builder.toString().equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                this.webWidgetConfiguration.setIsRedirectEnabled(RedirectionTypes.REDIRECT_ALL);
            } else {
                this.webWidgetConfiguration.setIsRedirectEnabled(RedirectionTypes.REDIRECT_EXTERNAL);
            }
        } else if (localName.equalsIgnoreCase(TAG_DOWNLOAD_ACTION)) {
            String actionString = this.builder.toString().toLowerCase();
            DownloadActions action = DownloadActions.DIALOG;
            if (actionString.equals("open")) {
                action = DownloadActions.OPEN;
            } else if (actionString.equals("save")) {
                action = DownloadActions.SAVE;
            } else if (actionString.equals("dialog")) {
                action = DownloadActions.DIALOG;
            }
            this.webWidgetConfiguration.setDownloadAction(action);
        }
        this.builder.setLength(0);
    }

    public WebWidgetConfiguration getWebWidgetConfiguration() {
        return this.webWidgetConfiguration;
    }

    public WidgetsController getWidgetsController() {
        return this.widgetsController;
    }
}
