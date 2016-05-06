package com.startapp.android.publish.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class AdDetails implements Parcelable, Serializable {
    public static final Creator<AdDetails> CREATOR;
    private static final long serialVersionUID = 1;
    private String adId;
    private String appPresencePackage;
    private String category;
    private String clickUrl;
    private String description;
    private String imageUrl;
    private String installs;
    private String intentDetails;
    private String intentPackageName;
    private int minAppVersion;
    private String packageName;
    private float rating;
    private boolean smartRedirect;
    private boolean startappBrowserEnabled;
    private String template;
    private String title;
    private String trackingClickUrl;
    private String trackingUrl;

    /* renamed from: com.startapp.android.publish.model.AdDetails.1 */
    static class StartAppSDK implements Creator<AdDetails> {
        StartAppSDK() {
        }

        public AdDetails createFromParcel(Parcel in) {
            return new AdDetails(in);
        }

        public AdDetails[] newArray(int size) {
            return new AdDetails[size];
        }
    }

    public AdDetails() {
        this.rating = 5.0f;
    }

    public String getAdId() {
        return this.adId;
    }

    public String getClickUrl() {
        return this.clickUrl;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public String getTrackingClickUrl() {
        return this.trackingClickUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public float getRating() {
        return this.rating;
    }

    public boolean isSmartRedirect() {
        return this.smartRedirect;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getAppPresencePackage() {
        return this.appPresencePackage;
    }

    public String getIntentDetails() {
        return this.intentDetails;
    }

    public String getIntentPackageName() {
        return this.intentPackageName;
    }

    public boolean isCPE() {
        return this.intentPackageName != null;
    }

    public String getInstalls() {
        return this.installs;
    }

    public String getCategory() {
        return this.category;
    }

    public String toString() {
        return "AdDetails [adId=" + this.adId + ", clickUrl=" + this.clickUrl + ", trackingUrl=" + this.trackingUrl + ", trackingClickUrl=" + this.trackingClickUrl + ", title=" + this.title + ", description=" + this.description + ", imageUrl=" + this.imageUrl + ", rating=" + this.rating + ", smartRedirect=" + this.smartRedirect + ", template=" + this.template + ", packageName=" + this.packageName + ", appPresencePackage=" + this.appPresencePackage + ", intentDetails=" + this.intentDetails + ", intentPackageName=" + this.intentPackageName + ", minAppVersion=" + this.minAppVersion + ", installs=" + this.installs + ", startappBrowserEnabled=" + this.startappBrowserEnabled + ", category=" + this.category + "]";
    }

    public int describeContents() {
        return 0;
    }

    public AdDetails(Parcel in) {
        this.rating = 5.0f;
        this.adId = in.readString();
        this.clickUrl = in.readString();
        this.trackingUrl = in.readString();
        this.trackingClickUrl = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.imageUrl = in.readString();
        this.rating = in.readFloat();
        int readInt = in.readInt();
        int readInt2 = in.readInt();
        this.smartRedirect = false;
        if (readInt == 1) {
            this.smartRedirect = true;
        }
        this.startappBrowserEnabled = true;
        if (readInt2 == 0) {
            this.startappBrowserEnabled = false;
        }
        this.template = in.readString();
        this.packageName = in.readString();
        this.appPresencePackage = in.readString();
        this.intentPackageName = in.readString();
        this.intentDetails = in.readString();
        this.minAppVersion = in.readInt();
        this.installs = in.readString();
        this.category = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 0;
        dest.writeString(this.adId);
        dest.writeString(this.clickUrl);
        dest.writeString(this.trackingUrl);
        dest.writeString(this.trackingClickUrl);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
        dest.writeFloat(this.rating);
        if (this.smartRedirect) {
            i = 1;
        } else {
            i = 0;
        }
        if (this.startappBrowserEnabled) {
            i2 = 1;
        }
        dest.writeInt(i);
        dest.writeInt(i2);
        dest.writeString(this.template);
        dest.writeString(this.packageName);
        dest.writeString(this.appPresencePackage);
        dest.writeString(this.intentPackageName);
        dest.writeString(this.intentDetails);
        dest.writeInt(this.minAppVersion);
        dest.writeString(this.installs);
        dest.writeString(this.category);
    }

    static {
        CREATOR = new StartAppSDK();
    }

    public int getMinAppVersion() {
        return this.minAppVersion;
    }

    public void setMinAppVersion(int minAppVersion) {
        this.minAppVersion = minAppVersion;
    }

    public boolean isStartappBrowserEnabled() {
        return this.startappBrowserEnabled;
    }

    public void setStartappBrowserEnabled(boolean startappBrowserEnabled) {
        this.startappBrowserEnabled = startappBrowserEnabled;
    }
}
