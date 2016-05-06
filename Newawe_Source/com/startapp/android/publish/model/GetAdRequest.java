package com.startapp.android.publish.model;

import android.content.Context;
import android.location.Location;
import com.startapp.android.publish.Ad.AdType;
import com.startapp.android.publish.SDKAdPreferences.Gender;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.adrules.SessionManager;
import com.startapp.android.publish.p022h.StartAppSDK;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class GetAdRequest extends BaseRequest {
    private int adsDisplayed;
    private int adsNumber;
    private String advertiserId;
    private String age;
    private Set<String> categories;
    private Set<String> categoriesExclude;
    private String country;
    private boolean engInclude;
    private Gender gender;
    private Boolean isAutoDateTimeEnabled;
    private boolean isHardwareAccelerated;
    private String keywords;
    private String latitude;
    private String locAcc;
    private String locSrc;
    private String locTs;
    private String longitude;
    private int offset;
    private Set<String> packageExclude;
    private Set<String> packageInclude;
    private Placement placement;
    private String profileId;
    private String simpleToken;
    private String template;
    private boolean testMode;
    private long timeSinceSessionStart;
    private AdType type;
    private VideoRequestMode videoRequestMode;
    private VideoRequestType videoRequestType;

    /* compiled from: StartAppSDK */
    private enum VideoRequestMode {
        INTERSTITIAL,
        REWARDED
    }

    /* compiled from: StartAppSDK */
    private enum VideoRequestType {
        ENABLED,
        DISABLED,
        FORCED
    }

    public GetAdRequest() {
        this.adsNumber = 1;
        this.videoRequestMode = VideoRequestMode.INTERSTITIAL;
        this.isHardwareAccelerated = true;
        this.offset = 0;
        this.categories = null;
        this.categoriesExclude = null;
        this.packageExclude = null;
        this.packageInclude = null;
        this.engInclude = true;
        this.country = null;
        this.advertiserId = null;
        this.type = null;
        this.timeSinceSessionStart = System.currentTimeMillis() - SessionManager.getInstance().getSessionStartTime();
        this.adsDisplayed = SessionManager.getInstance().getNumOfAdsDisplayed();
        this.profileId = MetaData.getInstance().getProfileId();
    }

    public Placement getPlacement() {
        return this.placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public VideoRequestMode getVideoRequestMode() {
        return this.videoRequestMode;
    }

    public void setVideoRequestMode(VideoRequestMode videoRequestMode) {
        this.videoRequestMode = videoRequestMode;
    }

    public boolean isTestMode() {
        return this.testMode;
    }

    public String getLocationSource() {
        return this.locSrc;
    }

    public void setLocationSource(String locationSource) {
        this.locSrc = locationSource;
    }

    public String getLocationAccuracy() {
        return this.locAcc;
    }

    public void setLocationAccuracy(String locationAccuracy) {
        this.locAcc = locationAccuracy;
    }

    public String getLocationTimestamp() {
        return this.locTs;
    }

    public void setLocationTimestamp(String locationTimestamp) {
        this.locTs = locationTimestamp;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getAdsNumber() {
        return this.adsNumber;
    }

    public void setAdsNumber(int adsNumber) {
        this.adsNumber = adsNumber;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        if (this.categories == null) {
            this.categories = new HashSet();
        }
        this.categories.add(category);
    }

    public Set<String> getCategoriesExclude() {
        return this.categoriesExclude;
    }

    public void setCategoriesExclude(Set<String> categoriesExclude) {
        this.categoriesExclude = categoriesExclude;
    }

    public void addCategoryExclude(String categoryExclude) {
        if (this.categoriesExclude == null) {
            this.categoriesExclude = new HashSet();
        }
        this.categoriesExclude.add(categoryExclude);
    }

    public Set<String> getPackageExclude() {
        return this.packageExclude;
    }

    public void setPackageExclude(Set<String> packageExclude) {
        this.packageExclude = packageExclude;
    }

    public Set<String> getPackageInclude() {
        return this.packageInclude;
    }

    public void setPackageInclude(Set<String> packageInclude) {
        this.packageInclude = packageInclude;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSimpleToken() {
        return this.simpleToken;
    }

    public void setSimpleToken(String simpleToken) {
        this.simpleToken = simpleToken;
    }

    public boolean isEngInclude() {
        return this.engInclude;
    }

    public void setEngInclude(boolean engInclude) {
        this.engInclude = engInclude;
    }

    public long getTineSinceSessionStart() {
        return this.timeSinceSessionStart;
    }

    public void setTimeSinceSessionStart(long timeSinceSessionStart) {
        this.timeSinceSessionStart = timeSinceSessionStart;
    }

    public int getAdsDisplayed() {
        return this.adsDisplayed;
    }

    public void setAdsDisplayed(int adsDisplayed) {
        this.adsDisplayed = adsDisplayed;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GetAdRequest [");
        stringBuilder.append("placement=" + this.placement);
        stringBuilder.append(", testMode=" + this.testMode);
        stringBuilder.append(", longitude=" + this.longitude);
        stringBuilder.append(", latitude=" + this.latitude);
        stringBuilder.append(", gender=" + this.gender);
        stringBuilder.append(", age=" + this.age);
        stringBuilder.append(", keywords=" + this.keywords);
        stringBuilder.append(", template=" + this.template);
        stringBuilder.append(", adsNumber=" + this.adsNumber);
        stringBuilder.append(", offset=" + this.offset);
        stringBuilder.append(", categories=" + this.categories);
        stringBuilder.append(", categoriesExclude=" + this.categoriesExclude);
        stringBuilder.append(", packageExclude=" + this.packageExclude);
        stringBuilder.append(", packageInclude=" + this.packageInclude);
        stringBuilder.append(", simpleToken=" + this.simpleToken);
        stringBuilder.append(", engInclude=" + this.engInclude);
        stringBuilder.append(", country=" + this.country);
        stringBuilder.append(", advertiserId=" + this.advertiserId);
        stringBuilder.append(", type=" + this.type);
        stringBuilder.append(", sessionStartTime=" + this.timeSinceSessionStart);
        stringBuilder.append(", adsDisplayed=" + this.adsDisplayed);
        stringBuilder.append(", profileId=" + this.profileId);
        stringBuilder.append(", hardwareAccelerated=" + this.isHardwareAccelerated);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void fillAdPreferences(Context context, AdPreferences adPreferences, Placement placement, String simpleToken) {
        this.placement = placement;
        if (MetaData.getInstance().getAdInformationConfig().m2527e().m2585a(context) && adPreferences.isSimpleToken()) {
            this.simpleToken = simpleToken;
        } else {
            this.simpleToken = StringUtils.EMPTY;
        }
        this.age = adPreferences.getAge(context);
        this.gender = adPreferences.getGender(context);
        this.keywords = adPreferences.getKeywords();
        this.testMode = adPreferences.isTestMode();
        this.categories = adPreferences.getCategories();
        this.categoriesExclude = adPreferences.getCategoriesExclude();
        this.isHardwareAccelerated = adPreferences.isHardwareAccelerated();
        this.isAutoDateTimeEnabled = Boolean.valueOf(StartAppSDK.m2887e(context));
        fillLocationDetails(adPreferences, context);
        setCountry(adPreferences.country);
        setAdvertiser(adPreferences.advertiserId);
        setTemplate(adPreferences.template);
        setType(adPreferences.type);
        setVideoRequestType(context);
        setVideoRequestMode();
        setPackageInclude(adPreferences.packageInclude);
    }

    private void setVideoRequestMode() {
        if (getType() == AdType.REWARDED_VIDEO) {
            this.videoRequestMode = VideoRequestMode.REWARDED;
        }
    }

    private void setVideoRequestType(Context context) {
        if (getType() != null) {
            if (getType() == AdType.NON_VIDEO) {
                this.videoRequestType = VideoRequestType.DISABLED;
            } else if (isAdTypeVideo()) {
                this.videoRequestType = VideoRequestType.FORCED;
            }
        } else if (com.startapp.android.publish.video.StartAppSDK.m3631a(context) == com.startapp.android.publish.video.StartAppSDK.StartAppSDK.ELIGIBLE) {
            this.videoRequestType = VideoRequestType.ENABLED;
        } else {
            this.videoRequestType = VideoRequestType.DISABLED;
        }
    }

    public boolean isAdTypeVideo() {
        return getType() == AdType.VIDEO || getType() == AdType.REWARDED_VIDEO;
    }

    private void fillLocationDetails(AdPreferences adPreferences, Context context) {
        boolean z = true;
        if (adPreferences.getLatitude() == null || adPreferences.getLongitude() == null) {
            Location a = StartAppSDK.m2921a(context);
            if (a != null) {
                setLongitude(StartAppSDK.m3033c(String.valueOf(a.getLongitude())));
                setLatitude(StartAppSDK.m3033c(String.valueOf(a.getLatitude())));
                setLocationAccuracy(StartAppSDK.m3033c(String.valueOf(a.getAccuracy())));
                setLocationSource(StartAppSDK.m3033c(StartAppSDK.m2922a(a)));
            } else {
                z = false;
            }
        } else {
            setLongitude(StartAppSDK.m3033c(String.valueOf(adPreferences.getLongitude())));
            setLatitude(StartAppSDK.m3033c(String.valueOf(adPreferences.getLatitude())));
            setLocationSource(StartAppSDK.m3033c(MetaData.DEFAULT_LOCATION_SOURCE));
        }
        StartAppSDK.m2923a(context, z);
    }

    public List<NameValueObject> getNameValueMap() {
        List nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new ArrayList();
        }
        StartAppSDK.m3013a(nameValueMap, "placement", this.placement.name(), true);
        StartAppSDK.m3013a(nameValueMap, "testMode", Boolean.toString(this.testMode), false);
        StartAppSDK.m3013a(nameValueMap, "longitude", this.longitude, false);
        StartAppSDK.m3013a(nameValueMap, "latitude", this.latitude, false);
        StartAppSDK.m3013a(nameValueMap, "locSrc", this.locSrc, false);
        StartAppSDK.m3013a(nameValueMap, "locAcc", this.locAcc, false);
        StartAppSDK.m3013a(nameValueMap, "locTs", this.locTs, false);
        StartAppSDK.m3013a(nameValueMap, "gender", this.gender, false);
        StartAppSDK.m3013a(nameValueMap, "age", this.age, false);
        StartAppSDK.m3013a(nameValueMap, "keywords", this.keywords, false);
        StartAppSDK.m3013a(nameValueMap, "template", this.template, false);
        StartAppSDK.m3013a(nameValueMap, "adsNumber", Integer.toString(this.adsNumber), false);
        StartAppSDK.m3015a(nameValueMap, "category", this.categories, false);
        StartAppSDK.m3015a(nameValueMap, "categoryExclude", this.categoriesExclude, false);
        StartAppSDK.m3015a(nameValueMap, "packageExclude", this.packageExclude, false);
        StartAppSDK.m3013a(nameValueMap, "offset", Integer.toString(this.offset), false);
        StartAppSDK.m3013a(nameValueMap, SchemaSymbols.ATTVAL_TOKEN, this.simpleToken, false);
        StartAppSDK.m3013a(nameValueMap, "engInclude", Boolean.toString(this.engInclude), false);
        if (!MetaData.getInstance().isDisableTwoClicks()) {
            StartAppSDK.m3013a(nameValueMap, "twoClicks", Boolean.toString(true), false);
        }
        StartAppSDK.m3013a(nameValueMap, "video", this.videoRequestType, false);
        if (getType() == AdType.INTERSTITIAL || getType() == AdType.RICH_TEXT) {
            StartAppSDK.m3013a(nameValueMap, "type", this.type, false);
        }
        StartAppSDK.m3013a(nameValueMap, "timeSinceSessionStart", Long.valueOf(this.timeSinceSessionStart), true);
        StartAppSDK.m3013a(nameValueMap, "adsDisplayed", Integer.valueOf(this.adsDisplayed), true);
        StartAppSDK.m3013a(nameValueMap, "profileId", this.profileId, false);
        StartAppSDK.m3013a(nameValueMap, "hardwareAccelerated", Boolean.valueOf(this.isHardwareAccelerated), false);
        StartAppSDK.m3013a(nameValueMap, "dts", this.isAutoDateTimeEnabled, false);
        StartAppSDK.m3013a(nameValueMap, "videoMode", this.videoRequestMode, false);
        StartAppSDK.m3013a(nameValueMap, "downloadingMode", (Object) "CACHE", false);
        Object a = StartAppSDK.m2888a();
        StartAppSDK.m3013a(nameValueMap, StartAppSDK.f2938b, a, true);
        StartAppSDK.m3014a(nameValueMap, StartAppSDK.f2940d, StartAppSDK.m2890b(getProductId() + this.placement.name() + getSessionId() + getSdkVersion() + a), true, false);
        if (getCountry() != null) {
            StartAppSDK.m3013a(nameValueMap, "country", getCountry(), false);
        }
        if (getAdvertiserId() != null) {
            StartAppSDK.m3013a(nameValueMap, "advertiserId", getAdvertiserId(), false);
        }
        if (getPackageInclude() != null) {
            StartAppSDK.m3015a(nameValueMap, "packageInclude", getPackageInclude(), false);
        }
        return nameValueMap;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiserId = advertiser;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAdvertiserId() {
        return this.advertiserId;
    }

    public AdType getType() {
        return this.type;
    }

    public void setType(AdType type) {
        this.type = type;
    }
}
