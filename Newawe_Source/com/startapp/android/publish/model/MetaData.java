package com.startapp.android.publish.model;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.ViewCompat;
import com.startapp.android.publish.adinformation.AdInformationConfig;
import com.startapp.android.publish.banner.BannerOptions;
import com.startapp.android.publish.gson.Gson;
import com.startapp.android.publish.gson.GsonBuilder;
import com.startapp.android.publish.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.startapp.android.publish.model.MetaDataRequest.RequestReason;
import com.startapp.android.publish.model.adrules.AdRule;
import com.startapp.android.publish.model.adrules.AdRules;
import com.startapp.android.publish.model.adrules.FreqCapRule;
import com.startapp.android.publish.model.adrules.ProbabilityRule;
import com.startapp.android.publish.p011e.StartAppSDK;
import com.startapp.android.publish.splash.SplashConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class MetaData extends BaseResponse {
    public static final String DEFAULT_AD_CLICK_URL;
    public static final String DEFAULT_AD_PLATFORM_HOST;
    public static final boolean DEFAULT_APP_PRESENCE = true;
    public static final int DEFAULT_BG_BOTTOM = -14606047;
    public static final int DEFAULT_BG_TOP = -14606047;
    public static final boolean DEFAULT_DISABLE_INAPP_STORE = false;
    public static final boolean DEFAULT_DISABLE_RETURN_AD = false;
    public static final boolean DEFAULT_DISABLE_TWO_CLICKS = false;
    public static final int DEFAULT_FULLSCREEN_OVERLAY_PROBABILITY = 0;
    public static final int DEFAULT_HOME_PROBABILITY_3D = 80;
    public static final int DEFAULT_HTML_3D_PROBABILITY_3D = 50;
    public static final boolean DEFAULT_INAPPBROWSER = true;
    public static final long DEFAULT_LAST_KNOWN_LOCATION_THRESHOLD = 30;
    public static final String DEFAULT_LOCATION_SOURCE = "API";
    public static final int DEFAULT_MAX_ADS = 10;
    public static final String DEFAULT_METADATA_HOST;
    public static final Integer DEFAULT_POWERED_BY_BG;
    public static final Integer DEFAULT_POWERED_BY_TEXT_COLOR;
    public static final int DEFAULT_PROBABILITY_3D = 80;
    public static final String DEFAULT_PROFILE_ID;
    public static final long DEFAULT_RETURN_AD_MIN_BACKGROUND_TIME = 300;
    public static final int DEFAULT_SESSION_MAX_BACKGROUND_TIME = 1800;
    public static final int DEFAULT_SMART_REDIRECT_TIMEOUT = 5;
    public static final int DEFAULT_TITLE_BG = -14803426;
    public static final String DEFAULT_TITLE_CONTENT = "Free Apps of the day";
    public static final Integer DEFAULT_TITLE_LINE_COLOR;
    public static final Integer DEFAULT_TITLE_TEXT_COLOR;
    public static final Set<String> DEFAULT_TITLE_TEXT_DECORATION;
    public static final Integer DEFAULT_TITLE_TEXT_SIZE;
    public static final boolean DEFAULT_WF_SCAN_ENABLED = true;
    public static final String KEY_METADATA = "metaData";
    public static final String TEXT_DECORATION_BOLD = "BOLD";
    public static final String TEXT_DECORATION_ITALIC = "ITALIC";
    public static final String TEXT_DECORATION_UNDERLINE = "UNDERLINE";
    private static transient RuntimeTypeAdapterFactory<AdRule> adRuleTypeFactory = null;
    private static transient MetaData instance = null;
    private static transient Object lock = null;
    private static final long serialVersionUID = 1;
    private static transient StartAppSDK task;
    private ACMConfig ACM;
    private AdInformationConfig AdInformation;
    private BannerOptions BannerOptions;
    private SplashConfig SplashConfig;
    private String adClickURL;
    private String adPlatformHostGeneric;
    private AdRules adRules;
    private com.startapp.android.publish.p010d.StartAppSDK analytics;
    private boolean appPresence;
    private Integer backgroundGradientBottom;
    private Integer backgroundGradientTop;
    private boolean disableInAppStore;
    private boolean disableReturnAd;
    private boolean disableTwoClicks;
    private Integer fullpageOfferWallProbability;
    private Integer fullpageOverlayProbability;
    private Integer homeProbability3D;
    private boolean inAppBrowser;
    private inAppBrowserPreLoad inAppBrowserPreLoad;
    private Integer itemDescriptionTextColor;
    private Set<String> itemDescriptionTextDecoration;
    private Integer itemDescriptionTextSize;
    private Integer itemGradientBottom;
    private Integer itemGradientTop;
    private Integer itemTitleTextColor;
    private Set<String> itemTitleTextDecoration;
    private Integer itemTitleTextSize;
    private transient boolean loading;
    private LocationConfig location;
    private Integer maxAds;
    private String metaDataHostGeneric;
    private transient List<StartAppSDK> metaDataListeners;
    private Integer poweredByBackgroundColor;
    private Integer poweredByTextColor;
    private Integer probability3D;
    private String profileId;
    private transient boolean ready;
    private long returnAdMinBackgroundTime;
    private int sessionMaxBackgroundTime;
    private int smartRedirectTimeout;
    private HashMap<String, MetaDataStyle> templates;
    private Integer titleBackgroundColor;
    private String titleContent;
    private Integer titleLineColor;
    private Integer titleTextColor;
    private Set<String> titleTextDecoration;
    private Integer titleTextSize;
    private VideoConfig video;
    private boolean wfScanEnabled;

    /* compiled from: StartAppSDK */
    private enum inAppBrowserPreLoad {
        DISABLED,
        CONTENT,
        FULL
    }

    static {
        instance = new MetaData();
        lock = new Object();
        adRuleTypeFactory = RuntimeTypeAdapterFactory.of(AdRule.class, "type").registerSubtype(ProbabilityRule.class).registerSubtype(FreqCapRule.class);
        DEFAULT_TITLE_TEXT_SIZE = Integer.valueOf(18);
        DEFAULT_TITLE_TEXT_COLOR = Integer.valueOf(-1);
        DEFAULT_TITLE_TEXT_DECORATION = new HashSet(Arrays.asList(new String[]{TEXT_DECORATION_BOLD}));
        DEFAULT_TITLE_LINE_COLOR = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        DEFAULT_POWERED_BY_BG = Integer.valueOf(DEFAULT_TITLE_BG);
        DEFAULT_POWERED_BY_TEXT_COLOR = Integer.valueOf(-1);
        DEFAULT_AD_CLICK_URL = new String(new byte[]{(byte) 104, (byte) 116, (byte) 116, (byte) 112, (byte) 58, (byte) 47, (byte) 47, (byte) 119, (byte) 119, (byte) 119, (byte) 46, (byte) 115, (byte) 116, (byte) 97, (byte) 114, (byte) 116, (byte) 97, (byte) 112, (byte) 112, (byte) 101, (byte) 120, (byte) 99, (byte) 104, (byte) 97, (byte) 110, (byte) 103, (byte) 101, (byte) 46, (byte) 99, (byte) 111, (byte) 109, (byte) 47, (byte) 116, (byte) 114, (byte) 97, (byte) 99, (byte) 107, (byte) 105, (byte) 110, (byte) 103, (byte) 47, (byte) 97, (byte) 100, (byte) 67, (byte) 108, (byte) 105, (byte) 99, (byte) 107});
        DEFAULT_METADATA_HOST = new String(new byte[]{(byte) 104, (byte) 116, (byte) 116, (byte) 112, (byte) 58, (byte) 47, (byte) 47, (byte) 105, (byte) 110, (byte) 105, (byte) 116, (byte) 46, (byte) 115, (byte) 116, (byte) 97, (byte) 114, (byte) 116, (byte) 97, (byte) 112, (byte) 112, (byte) 101, (byte) 120, (byte) 99, (byte) 104, (byte) 97, (byte) 110, (byte) 103, (byte) 101, (byte) 46, (byte) 99, (byte) 111, (byte) 109, (byte) 47, (byte) 49, (byte) 46, (byte) 52, (byte) 47});
        DEFAULT_AD_PLATFORM_HOST = new String(new byte[]{(byte) 104, (byte) 116, (byte) 116, (byte) 112, (byte) 58, (byte) 47, (byte) 47, (byte) 119, (byte) 119, (byte) 119, (byte) 46, (byte) 115, (byte) 116, (byte) 97, (byte) 114, (byte) 116, (byte) 97, (byte) 112, (byte) 112, (byte) 101, (byte) 120, (byte) 99, (byte) 104, (byte) 97, (byte) 110, (byte) 103, (byte) 101, (byte) 46, (byte) 99, (byte) 111, (byte) 109, (byte) 47, (byte) 49, (byte) 46, (byte) 52, (byte) 47});
        DEFAULT_PROFILE_ID = null;
    }

    private MetaData() {
        this.probability3D = Integer.valueOf(DEFAULT_PROBABILITY_3D);
        this.homeProbability3D = Integer.valueOf(DEFAULT_PROBABILITY_3D);
        this.fullpageOfferWallProbability = Integer.valueOf(DEFAULT_HTML_3D_PROBABILITY_3D);
        this.fullpageOverlayProbability = Integer.valueOf(DEFAULT_FULLSCREEN_OVERLAY_PROBABILITY);
        this.backgroundGradientTop = Integer.valueOf(DEFAULT_BG_TOP);
        this.backgroundGradientBottom = Integer.valueOf(DEFAULT_BG_TOP);
        this.maxAds = Integer.valueOf(DEFAULT_MAX_ADS);
        this.titleBackgroundColor = Integer.valueOf(DEFAULT_TITLE_BG);
        this.titleContent = DEFAULT_TITLE_CONTENT;
        this.titleTextSize = DEFAULT_TITLE_TEXT_SIZE;
        this.titleTextColor = DEFAULT_TITLE_TEXT_COLOR;
        this.titleTextDecoration = DEFAULT_TITLE_TEXT_DECORATION;
        this.titleLineColor = DEFAULT_TITLE_LINE_COLOR;
        this.itemGradientTop = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_TOP);
        this.itemGradientBottom = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_BOTTOM);
        this.itemTitleTextSize = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_SIZE;
        this.itemTitleTextColor = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_COLOR;
        this.itemTitleTextDecoration = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_DECORATION;
        this.itemDescriptionTextSize = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_SIZE;
        this.itemDescriptionTextColor = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_COLOR;
        this.itemDescriptionTextDecoration = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_DECORATION;
        this.BannerOptions = new BannerOptions();
        this.templates = new HashMap();
        this.poweredByBackgroundColor = DEFAULT_POWERED_BY_BG;
        this.poweredByTextColor = DEFAULT_POWERED_BY_TEXT_COLOR;
        this.SplashConfig = new SplashConfig();
        this.AdInformation = AdInformationConfig.m2517a();
        this.adClickURL = DEFAULT_AD_CLICK_URL;
        this.metaDataHostGeneric = DEFAULT_METADATA_HOST;
        this.adPlatformHostGeneric = DEFAULT_AD_PLATFORM_HOST;
        this.sessionMaxBackgroundTime = DEFAULT_SESSION_MAX_BACKGROUND_TIME;
        this.profileId = DEFAULT_PROFILE_ID;
        this.returnAdMinBackgroundTime = DEFAULT_RETURN_AD_MIN_BACKGROUND_TIME;
        this.disableReturnAd = DEFAULT_DISABLE_TWO_CLICKS;
        this.smartRedirectTimeout = DEFAULT_SMART_REDIRECT_TIMEOUT;
        this.inAppBrowser = DEFAULT_WF_SCAN_ENABLED;
        this.disableTwoClicks = DEFAULT_DISABLE_TWO_CLICKS;
        this.appPresence = DEFAULT_WF_SCAN_ENABLED;
        this.adRules = new AdRules();
        this.disableInAppStore = DEFAULT_DISABLE_TWO_CLICKS;
        this.analytics = new com.startapp.android.publish.p010d.StartAppSDK();
        this.video = new VideoConfig();
        this.location = new LocationConfig();
        this.ACM = new ACMConfig();
        this.wfScanEnabled = DEFAULT_WF_SCAN_ENABLED;
        this.loading = DEFAULT_DISABLE_TWO_CLICKS;
        this.ready = DEFAULT_DISABLE_TWO_CLICKS;
        this.metaDataListeners = new ArrayList();
        getAdInformationConfig().m2528f();
    }

    public static void init(Context context) {
        MetaData metaData = (MetaData) getGson().fromJson(context.getSharedPreferences("com.startapp.android.publish", DEFAULT_FULLSCREEN_OVERLAY_PROBABILITY).getString(KEY_METADATA, StringUtils.EMPTY), MetaData.class);
        if (metaData != null) {
            instance = metaData;
        } else {
            instance = new MetaData();
        }
        getInstance().getAdInformationConfig().m2528f();
        getInstance().applyAdPlatformProtocolToHosts();
    }

    public static void update(Context context, MetaData metaData) {
        synchronized (lock) {
            metaData.metaDataListeners = getInstance().metaDataListeners;
            instance = metaData;
            if (com.startapp.android.publish.StartAppSDK.m2732a().booleanValue()) {
                com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, "MetaData received:");
                com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, getGson().toJson((Object) metaData));
            }
            AdInformationConfig.m2518a(getInstance().AdInformation);
            Editor edit = context.getSharedPreferences("com.startapp.android.publish", DEFAULT_FULLSCREEN_OVERLAY_PROBABILITY).edit();
            String toJson = getGson().toJson(getInstance());
            edit.putString(KEY_METADATA, toJson);
            edit.commit();
            com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, "MetaData saved:");
            com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, toJson);
            getInstance().applyAdPlatformProtocolToHosts();
            getInstance().getAdInformationConfig().m2528f();
            getInstance().loading = DEFAULT_DISABLE_TWO_CLICKS;
            getInstance().ready = DEFAULT_WF_SCAN_ENABLED;
            if (getInstance().metaDataListeners != null) {
                List<StartAppSDK> arrayList = new ArrayList(getInstance().metaDataListeners);
                getInstance().metaDataListeners.clear();
                for (StartAppSDK onFinishLoadingMeta : arrayList) {
                    onFinishLoadingMeta.onFinishLoadingMeta();
                }
            }
            com.startapp.android.publish.p022h.StartAppSDK.m2910b(context, "totalSessions", Integer.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2905a(context, "totalSessions", Integer.valueOf(DEFAULT_FULLSCREEN_OVERLAY_PROBABILITY)).intValue() + 1));
            task = null;
        }
    }

    public static void failedLoading() {
        List list = null;
        synchronized (lock) {
            if (getInstance().metaDataListeners != null) {
                list = new ArrayList(getInstance().metaDataListeners);
                getInstance().metaDataListeners.clear();
            }
            getInstance().loading = DEFAULT_DISABLE_TWO_CLICKS;
        }
        if (r0 != null) {
            for (StartAppSDK onFailedLoadingMeta : r0) {
                onFailedLoadingMeta.onFailedLoadingMeta();
            }
        }
    }

    public void loadFromServer(Context context, AdPreferences adPreferences, RequestReason reason, boolean waitForMetadata, StartAppSDK listener) {
        loadFromServer(context, adPreferences, reason, waitForMetadata, listener, DEFAULT_DISABLE_TWO_CLICKS);
    }

    public void loadFromServer(Context context, AdPreferences adPreferences, RequestReason reason, boolean waitForMetadata, StartAppSDK listener, boolean force) {
        if (!(waitForMetadata || listener == null)) {
            listener.onFinishLoadingMeta();
        }
        synchronized (lock) {
            if (!getInstance().isReady() || force) {
                if (!getInstance().isLoading() || force) {
                    this.loading = DEFAULT_WF_SCAN_ENABLED;
                    this.ready = DEFAULT_DISABLE_TWO_CLICKS;
                    if (task != null) {
                        task.m2748b();
                    }
                    task = new StartAppSDK(context, adPreferences, reason);
                    task.m2746a();
                }
                if (waitForMetadata && listener != null) {
                    getInstance().addMetaDataListener(listener);
                }
                return;
            }
            if (waitForMetadata && listener != null) {
                listener.onFinishLoadingMeta();
            }
        }
    }

    public void addMetaDataListener(StartAppSDK metaDataListener) {
        synchronized (lock) {
            this.metaDataListeners.add(metaDataListener);
        }
    }

    public static Object getLock() {
        return lock;
    }

    public boolean isLoading() {
        return this.loading;
    }

    public boolean isReady() {
        return this.ready;
    }

    public int getFullpageOfferwallProbability() {
        return this.fullpageOfferWallProbability.intValue();
    }

    public int getFullscreenOverlayProbability() {
        return this.fullpageOverlayProbability.intValue();
    }

    public int getProbability3D() {
        return this.probability3D.intValue();
    }

    public int getHomeProbability3D() {
        return this.homeProbability3D.intValue();
    }

    public int getBackgroundGradientTop() {
        return this.backgroundGradientTop.intValue();
    }

    public SplashConfig getSplashConfig() {
        return this.SplashConfig;
    }

    public MetaDataStyle getTemplate(String templateName) {
        return (MetaDataStyle) this.templates.get(templateName);
    }

    public BannerOptions getBannerOptions() {
        return this.BannerOptions;
    }

    public BannerOptions getBannerOptionsCopy() {
        return new BannerOptions(this.BannerOptions);
    }

    public int getBackgroundGradientBottom() {
        return this.backgroundGradientBottom.intValue();
    }

    public int getMaxAds() {
        return this.maxAds.intValue();
    }

    public Integer getTitleBackgroundColor() {
        return this.titleBackgroundColor;
    }

    public String getTitleContent() {
        return this.titleContent;
    }

    public Integer getTitleTextSize() {
        return this.titleTextSize;
    }

    public Integer getTitleTextColor() {
        return this.titleTextColor;
    }

    public Set<String> getTitleTextDecoration() {
        return this.titleTextDecoration;
    }

    public Integer getTitleLineColor() {
        return this.titleLineColor;
    }

    public int getItemGradientTop() {
        return this.itemGradientTop.intValue();
    }

    public int getItemGradientBottom() {
        return this.itemGradientBottom.intValue();
    }

    public Integer getItemTitleTextSize() {
        return this.itemTitleTextSize;
    }

    public Integer getItemTitleTextColor() {
        return this.itemTitleTextColor;
    }

    public Set<String> getItemTitleTextDecoration() {
        return this.itemTitleTextDecoration;
    }

    public Integer getItemDescriptionTextSize() {
        return this.itemDescriptionTextSize;
    }

    public Integer getItemDescriptionTextColor() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> getItemDescriptionTextDecoration() {
        return this.itemDescriptionTextDecoration;
    }

    public Integer getPoweredByBackgroundColor() {
        return this.poweredByBackgroundColor;
    }

    public Integer getPoweredByTextColor() {
        return this.poweredByTextColor;
    }

    public AdInformationConfig getAdInformationConfig() {
        return this.AdInformation;
    }

    public String getAdClickUrl() {
        return this.adClickURL;
    }

    public String getMetaDataHost() {
        if (com.startapp.android.publish.StartAppSDK.OVERRIDE_HOST != null) {
            return com.startapp.android.publish.StartAppSDK.OVERRIDE_HOST;
        }
        return this.metaDataHostGeneric;
    }

    public String getAdPlatformHost() {
        if (com.startapp.android.publish.StartAppSDK.OVERRIDE_HOST != null) {
            return com.startapp.android.publish.StartAppSDK.OVERRIDE_HOST;
        }
        return this.adPlatformHostGeneric;
    }

    public long getSessionMaxBackgroundTime() {
        return TimeUnit.SECONDS.toMillis((long) this.sessionMaxBackgroundTime);
    }

    public long getReturnAdMinBackgroundTime() {
        return TimeUnit.SECONDS.toMillis(this.returnAdMinBackgroundTime);
    }

    public AdRules getAdRules() {
        return this.adRules;
    }

    public boolean isDisableReturnAd() {
        return this.disableReturnAd;
    }

    public long getSmartRedirectTimeout() {
        return TimeUnit.SECONDS.toMillis((long) this.smartRedirectTimeout);
    }

    public boolean isInAppBrowser() {
        return this.inAppBrowser;
    }

    public void setInAppBrowser(boolean inAppBrowser) {
        this.inAppBrowser = inAppBrowser;
    }

    public inAppBrowserPreLoad getInAppBrowserPreLoad() {
        return this.inAppBrowserPreLoad;
    }

    public void setInAppBrowserPreLoad(inAppBrowserPreLoad inAppBrowserPreLoad) {
        this.inAppBrowserPreLoad = inAppBrowserPreLoad;
    }

    public boolean isDisableTwoClicks() {
        return this.disableTwoClicks;
    }

    public boolean isAppPresenceEnabled() {
        return this.appPresence;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public boolean isDisableInAppStore() {
        return this.disableInAppStore;
    }

    public com.startapp.android.publish.p010d.StartAppSDK getAnalyticsConfig() {
        return this.analytics;
    }

    public VideoConfig getVideoConfig() {
        return this.video;
    }

    public LocationConfig getLocationConfig() {
        return this.location;
    }

    public ACMConfig getACMConfig() {
        return this.ACM;
    }

    public boolean isWfScanEnabled() {
        return this.wfScanEnabled;
    }

    public static MetaData getInstance() {
        return instance;
    }

    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapterFactory(adRuleTypeFactory).create();
    }

    private void applyAdPlatformProtocolToHosts() {
        this.adPlatformHostGeneric = this.adPlatformHostGeneric.replace("%AdPlatformProtocol%", "1.4");
        this.metaDataHostGeneric = this.metaDataHostGeneric.replace("%AdPlatformProtocol%", "1.4");
    }
}
