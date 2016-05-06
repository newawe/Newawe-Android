package com.startapp.android.publish.model;

import android.content.Context;
import com.startapp.android.publish.Ad.AdType;
import com.startapp.android.publish.SDKAdPreferences.Gender;
import com.startapp.android.publish.StartAppSDK;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* compiled from: StartAppSDK */
public class AdPreferences implements Serializable {
    public static final String TYPE_APP_WALL = "APP_WALL";
    public static final String TYPE_BANNER = "BANNER";
    public static final String TYPE_INAPP_EXIT = "INAPP_EXIT";
    public static final String TYPE_SCRINGO_TOOLBAR = "SCRINGO_TOOLBAR";
    public static final String TYPE_TEXT = "TEXT";
    private static final long serialVersionUID = 1;
    protected String advertiserId;
    private String age;
    private Set<String> categories;
    private Set<String> categoriesExclude;
    protected String country;
    protected boolean forceFullpage;
    protected boolean forceOfferWall2D;
    protected boolean forceOfferWall3D;
    protected boolean forceOverlay;
    private Gender gender;
    private boolean hardwareAccelerated;
    private String keywords;
    private Double latitude;
    private Double longitude;
    protected Set<String> packageInclude;
    private String productId;
    private String publisherId;
    protected String template;
    private boolean testMode;
    protected AdType type;

    /* compiled from: StartAppSDK */
    public enum Placement {
        INAPP_FULL_SCREEN(1),
        INAPP_BANNER(2),
        INAPP_OFFER_WALL(3),
        INAPP_SPLASH(4),
        INAPP_OVERLAY(5),
        INAPP_NATIVE(6),
        DEVICE_SIDEBAR(7),
        INAPP_RETURN(8),
        INAPP_BROWSER(9);
        
        private int index;

        private Placement(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public boolean isInterstitial() {
            return this == INAPP_FULL_SCREEN || this == INAPP_OFFER_WALL || this == INAPP_SPLASH || this == INAPP_OVERLAY;
        }

        public static Placement getByIndex(int index) {
            Placement placement = INAPP_FULL_SCREEN;
            Placement[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    placement = values[i];
                }
            }
            return placement;
        }
    }

    public AdPreferences() {
        this.country = null;
        this.advertiserId = null;
        this.template = null;
        this.type = null;
        this.packageInclude = null;
        this.forceOfferWall3D = false;
        this.forceOfferWall2D = false;
        this.forceFullpage = false;
        this.forceOverlay = false;
        this.publisherId = null;
        this.productId = null;
        this.testMode = false;
        this.longitude = null;
        this.latitude = null;
        this.keywords = null;
        this.gender = null;
        this.age = null;
        this.hardwareAccelerated = StartAppSDK.m2799a().m2822d();
        this.categories = null;
        this.categoriesExclude = null;
    }

    public AdPreferences(AdPreferences other) {
        this.country = null;
        this.advertiserId = null;
        this.template = null;
        this.type = null;
        this.packageInclude = null;
        this.forceOfferWall3D = false;
        this.forceOfferWall2D = false;
        this.forceFullpage = false;
        this.forceOverlay = false;
        this.publisherId = null;
        this.productId = null;
        this.testMode = false;
        this.longitude = null;
        this.latitude = null;
        this.keywords = null;
        this.gender = null;
        this.age = null;
        this.hardwareAccelerated = StartAppSDK.m2799a().m2822d();
        this.categories = null;
        this.categoriesExclude = null;
        this.country = other.country;
        this.advertiserId = other.advertiserId;
        this.template = other.template;
        this.type = other.type;
        if (other.packageInclude != null) {
            this.packageInclude = new HashSet(other.packageInclude);
        }
        this.forceOfferWall3D = other.forceOfferWall3D;
        this.forceOfferWall2D = other.forceOfferWall2D;
        this.forceFullpage = other.forceFullpage;
        this.forceOverlay = other.forceOverlay;
        this.publisherId = other.publisherId;
        this.productId = other.productId;
        this.testMode = other.testMode;
        this.longitude = other.longitude;
        this.latitude = other.latitude;
        this.keywords = other.keywords;
        this.gender = other.gender;
        this.age = other.age;
        this.hardwareAccelerated = other.hardwareAccelerated;
        if (other.categories != null) {
            this.categories = new HashSet(other.categories);
        }
        if (other.categoriesExclude != null) {
            this.categoriesExclude = new HashSet(other.categoriesExclude);
        }
    }

    @Deprecated
    public AdPreferences(String publisherId, String productId) {
        this.country = null;
        this.advertiserId = null;
        this.template = null;
        this.type = null;
        this.packageInclude = null;
        this.forceOfferWall3D = false;
        this.forceOfferWall2D = false;
        this.forceFullpage = false;
        this.forceOverlay = false;
        this.publisherId = null;
        this.productId = null;
        this.testMode = false;
        this.longitude = null;
        this.latitude = null;
        this.keywords = null;
        this.gender = null;
        this.age = null;
        this.hardwareAccelerated = StartAppSDK.m2799a().m2822d();
        this.categories = null;
        this.categoriesExclude = null;
        this.publisherId = publisherId;
        this.productId = productId;
    }

    @Deprecated
    public AdPreferences(String publisherId, String productId, String type) {
        this.country = null;
        this.advertiserId = null;
        this.template = null;
        this.type = null;
        this.packageInclude = null;
        this.forceOfferWall3D = false;
        this.forceOfferWall2D = false;
        this.forceFullpage = false;
        this.forceOverlay = false;
        this.publisherId = null;
        this.productId = null;
        this.testMode = false;
        this.longitude = null;
        this.latitude = null;
        this.keywords = null;
        this.gender = null;
        this.age = null;
        this.hardwareAccelerated = StartAppSDK.m2799a().m2822d();
        this.categories = null;
        this.categoriesExclude = null;
        this.publisherId = publisherId;
        this.productId = productId;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    @Deprecated
    public AdPreferences setPublisherId(String publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getProductId() {
        return this.productId;
    }

    @Deprecated
    public AdPreferences setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public boolean isTestMode() {
        return this.testMode;
    }

    public AdPreferences setTestMode(boolean testMode) {
        this.testMode = testMode;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public AdPreferences setLongitude(double longitude) {
        this.longitude = Double.valueOf(longitude);
        return this;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public AdPreferences setLatitude(double latitude) {
        this.latitude = Double.valueOf(latitude);
        return this;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public AdPreferences setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public Gender getGender(Context context) {
        if (this.gender == null) {
            return StartAppSDK.m2799a().m2814b(context).getGender();
        }
        return this.gender;
    }

    public AdPreferences setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getAge(Context context) {
        if (this.age == null) {
            return StartAppSDK.m2799a().m2814b(context).getAge();
        }
        return this.age;
    }

    public AdPreferences setAge(Integer age) {
        this.age = Integer.toString(age.intValue());
        return this;
    }

    public AdPreferences setAge(String age) {
        this.age = age;
        return this;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public AdPreferences addCategory(String category) {
        if (this.categories == null) {
            this.categories = new HashSet();
        }
        this.categories.add(category);
        return this;
    }

    public Set<String> getCategoriesExclude() {
        return this.categoriesExclude;
    }

    public AdPreferences addCategoryExclude(String CategoryExclude) {
        if (this.categoriesExclude == null) {
            this.categoriesExclude = new HashSet();
        }
        this.categoriesExclude.add(CategoryExclude);
        return this;
    }

    protected boolean isHardwareAccelerated() {
        return this.hardwareAccelerated;
    }

    public boolean isSimpleToken() {
        return true;
    }

    public AdType getType() {
        return this.type;
    }

    public String toString() {
        return "AdPreferences [publisherId=" + this.publisherId + ", productId=" + this.productId + ", testMode=" + this.testMode + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", keywords=" + this.keywords + ", categories=" + this.categories + ", categoriesExclude=" + this.categoriesExclude + "]";
    }
}
