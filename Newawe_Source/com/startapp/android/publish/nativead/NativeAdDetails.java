package com.startapp.android.publish.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class NativeAdDetails implements Parcelable, com.startapp.android.publish.p022h.StartAppSDK.StartAppSDK, NativeAdInterface {
    public static final Creator<NativeAdDetails> CREATOR;
    private AdDetails f4158a;
    private int f4159b;
    private Bitmap f4160c;
    private boolean f4161d;
    private StartAppSDK f4162e;
    private String f4163f;

    /* renamed from: com.startapp.android.publish.nativead.NativeAdDetails.1 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ NativeAdDetails f3111a;

        StartAppSDK(NativeAdDetails nativeAdDetails) {
            this.f3111a = nativeAdDetails;
        }

        public void run() {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("StartAppNativeAd", 3, "SingleAd [" + this.f3111a.f4159b + "] Loaded");
            if (this.f3111a.f4162e != null) {
                this.f3111a.f4162e.onNativeAdDetailsLoaded(this.f3111a.f4159b);
            }
        }
    }

    /* renamed from: com.startapp.android.publish.nativead.NativeAdDetails.2 */
    static class StartAppSDK implements Creator<NativeAdDetails> {
        StartAppSDK() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m3187a(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m3188a(x0);
        }

        public NativeAdDetails m3187a(Parcel parcel) {
            return new NativeAdDetails(parcel);
        }

        public NativeAdDetails[] m3188a(int i) {
            return new NativeAdDetails[i];
        }
    }

    /* renamed from: com.startapp.android.publish.nativead.NativeAdDetails.3 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f3112a;

        static {
            f3112a = new int[com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK.values().length];
            try {
                f3112a[com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK.OPEN_MARKET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3112a[com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK.LAUNCH_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.nativead.NativeAdDetails.a */
    protected interface StartAppSDK {
        void onNativeAdDetailsLoaded(int i);
    }

    public NativeAdDetails(AdDetails adDetails, NativeAdPreferences config, int identifier, StartAppSDK singleAdLoadedListener) {
        this.f4161d = false;
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("StartAppNativeAd", 3, "Initializiang SingleAd [" + identifier + "]");
        this.f4158a = adDetails;
        this.f4159b = identifier;
        this.f4162e = singleAdLoadedListener;
        if (config.isAutoBitmapDownload()) {
            new com.startapp.android.publish.p022h.StartAppSDK(getImageUrl(), this, identifier).m2900a();
        } else {
            m4859b();
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("         Title: [" + getTitle() + "]\n");
        stringBuffer.append("         Description: [" + getDescription().substring(0, 30) + "]...\n");
        stringBuffer.append("         Rating: [" + getRating() + "]\n");
        stringBuffer.append("         Installs: [" + getInstalls() + "]\n");
        stringBuffer.append("         Category: [" + getCategory() + "]\n");
        stringBuffer.append("         PackageName: [" + getPackacgeName() + "]\n");
        stringBuffer.append("         CampaginAction: [" + getCampaignAction() + "]\n");
        return stringBuffer.toString();
    }

    private void m4857a(Bitmap bitmap) {
        this.f4160c = bitmap;
    }

    public void m4861a(Bitmap bitmap, int i) {
        m4857a(bitmap);
        m4859b();
    }

    private void m4859b() {
        new Handler().post(new StartAppSDK(this));
    }

    protected void m4862a(String str) {
        this.f4163f = str;
    }

    public String getTitle() {
        String str = StringUtils.EMPTY;
        if (this.f4158a != null) {
            return this.f4158a.getTitle();
        }
        return str;
    }

    public String getDescription() {
        String str = StringUtils.EMPTY;
        if (this.f4158a != null) {
            return this.f4158a.getDescription();
        }
        return str;
    }

    public float getRating() {
        if (this.f4158a != null) {
            return this.f4158a.getRating();
        }
        return 5.0f;
    }

    public String getImageUrl() {
        String str = "http://www.dummy.com";
        if (this.f4158a != null) {
            return this.f4158a.getImageUrl();
        }
        return str;
    }

    public Bitmap getImageBitmap() {
        return this.f4160c;
    }

    public String getInstalls() {
        String str = StringUtils.EMPTY;
        if (this.f4158a != null) {
            return this.f4158a.getInstalls();
        }
        return str;
    }

    public String getCategory() {
        String str = StringUtils.EMPTY;
        if (this.f4158a != null) {
            return this.f4158a.getCategory();
        }
        return str;
    }

    public String getPackacgeName() {
        String str = StringUtils.EMPTY;
        if (this.f4158a != null) {
            return this.f4158a.getPackageName();
        }
        return str;
    }

    public com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK getCampaignAction() {
        com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK startAppSDK = com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK.OPEN_MARKET;
        if (this.f4158a == null || !this.f4158a.isCPE()) {
            return startAppSDK;
        }
        return com.startapp.android.publish.nativead.StartAppNativeAd.StartAppSDK.LAUNCH_APP;
    }

    protected AdDetails m4860a() {
        return this.f4158a;
    }

    public void sendClick(Context context) {
        if (this.f4158a != null) {
            switch (StartAppSDK.f3112a[getCampaignAction().ordinal()]) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (this.f4158a.isSmartRedirect()) {
                        com.startapp.android.publish.p022h.StartAppSDK.m3001a(context, this.f4158a.getClickUrl(), this.f4158a.getTrackingClickUrl(), this.f4158a.getPackageName(), new com.startapp.android.publish.p022h.StartAppSDK(this.f4163f), MetaData.getInstance().getSmartRedirectTimeout(), this.f4158a.isStartappBrowserEnabled());
                        return;
                    }
                    com.startapp.android.publish.p022h.StartAppSDK.m2999a(context, this.f4158a.getClickUrl(), this.f4158a.getTrackingClickUrl(), new com.startapp.android.publish.p022h.StartAppSDK(this.f4163f), this.f4158a.isStartappBrowserEnabled());
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    com.startapp.android.publish.p022h.StartAppSDK.m3012a(getPackacgeName(), this.f4158a.getIntentDetails(), this.f4158a.getClickUrl(), context, new com.startapp.android.publish.p022h.StartAppSDK(this.f4163f));
                default:
            }
        }
    }

    public void sendImpression(Context context) {
        if (this.f4161d) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("StartAppNativeAd", 3, "Already sent impression for [" + this.f4159b + "]");
            return;
        }
        this.f4161d = true;
        if (this.f4158a != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("StartAppNativeAd", 3, "Sending Impression for [" + this.f4159b + "]");
            com.startapp.android.publish.p022h.StartAppSDK.m2997a(context, this.f4158a.getTrackingUrl(), new com.startapp.android.publish.p022h.StartAppSDK(this.f4163f));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3 = 0;
        if (this.f4158a != null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getImageBitmap() != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (this.f4161d) {
            i3 = 1;
        }
        dest.writeInt(i);
        if (i == 1) {
            dest.writeParcelable(this.f4158a, flags);
        }
        dest.writeInt(i2);
        if (i2 == 1) {
            dest.writeParcelable(getImageBitmap(), flags);
        }
        dest.writeInt(i3);
        dest.writeInt(this.f4159b);
        dest.writeString(this.f4163f);
    }

    public NativeAdDetails(Parcel in) {
        this.f4161d = false;
        if (in.readInt() == 1) {
            this.f4158a = (AdDetails) in.readParcelable(AdDetails.class.getClassLoader());
        }
        if (in.readInt() == 1) {
            m4857a((Bitmap) in.readParcelable(Bitmap.class.getClassLoader()));
        }
        int readInt = in.readInt();
        this.f4161d = false;
        if (readInt == 1) {
            this.f4161d = true;
        }
        this.f4159b = in.readInt();
        this.f4163f = in.readString();
    }

    static {
        CREATOR = new StartAppSDK();
    }
}
