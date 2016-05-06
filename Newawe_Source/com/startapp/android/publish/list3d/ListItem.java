package com.startapp.android.publish.list3d;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.startapp.android.publish.model.AdDetails;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class ListItem implements Parcelable {
    public static final Creator<ListItem> CREATOR;
    private String f3033a;
    private String f3034b;
    private String f3035c;
    private String f3036d;
    private String f3037e;
    private String f3038f;
    private String f3039g;
    private String f3040h;
    private float f3041i;
    private boolean f3042j;
    private boolean f3043k;
    private Drawable f3044l;
    private String f3045m;
    private String f3046n;
    private String f3047o;

    /* renamed from: com.startapp.android.publish.list3d.ListItem.1 */
    static class StartAppSDK implements Creator<ListItem> {
        StartAppSDK() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m3081a(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m3082a(x0);
        }

        public ListItem m3081a(Parcel parcel) {
            return new ListItem(parcel);
        }

        public ListItem[] m3082a(int i) {
            return new ListItem[i];
        }
    }

    public ListItem(AdDetails adDetails) {
        this.f3033a = StringUtils.EMPTY;
        this.f3034b = StringUtils.EMPTY;
        this.f3035c = StringUtils.EMPTY;
        this.f3036d = StringUtils.EMPTY;
        this.f3037e = StringUtils.EMPTY;
        this.f3038f = StringUtils.EMPTY;
        this.f3039g = StringUtils.EMPTY;
        this.f3040h = StringUtils.EMPTY;
        this.f3041i = 0.0f;
        this.f3042j = false;
        this.f3043k = true;
        this.f3044l = null;
        this.f3047o = StringUtils.EMPTY;
        this.f3033a = adDetails.getAdId();
        this.f3034b = adDetails.getClickUrl();
        this.f3035c = adDetails.getTrackingUrl();
        this.f3036d = adDetails.getTrackingClickUrl();
        this.f3037e = adDetails.getPackageName();
        this.f3038f = adDetails.getTitle();
        this.f3039g = adDetails.getDescription();
        this.f3040h = adDetails.getImageUrl();
        this.f3041i = adDetails.getRating();
        this.f3042j = adDetails.isSmartRedirect();
        this.f3043k = adDetails.isStartappBrowserEnabled();
        this.f3044l = null;
        this.f3047o = adDetails.getTemplate();
        this.f3045m = adDetails.getIntentDetails();
        this.f3046n = adDetails.getIntentPackageName();
    }

    public ListItem(Parcel in) {
        this.f3033a = StringUtils.EMPTY;
        this.f3034b = StringUtils.EMPTY;
        this.f3035c = StringUtils.EMPTY;
        this.f3036d = StringUtils.EMPTY;
        this.f3037e = StringUtils.EMPTY;
        this.f3038f = StringUtils.EMPTY;
        this.f3039g = StringUtils.EMPTY;
        this.f3040h = StringUtils.EMPTY;
        this.f3041i = 0.0f;
        this.f3042j = false;
        this.f3043k = true;
        this.f3044l = null;
        this.f3047o = StringUtils.EMPTY;
        if (in.readInt() == 1) {
            this.f3044l = new BitmapDrawable((Bitmap) Bitmap.CREATOR.createFromParcel(in));
        } else {
            this.f3044l = null;
        }
        this.f3033a = in.readString();
        this.f3034b = in.readString();
        this.f3035c = in.readString();
        this.f3036d = in.readString();
        this.f3037e = in.readString();
        this.f3038f = in.readString();
        this.f3039g = in.readString();
        this.f3040h = in.readString();
        this.f3041i = in.readFloat();
        if (in.readInt() == 1) {
            this.f3042j = true;
        } else {
            this.f3042j = false;
        }
        if (in.readInt() == 0) {
            this.f3043k = false;
        } else {
            this.f3043k = true;
        }
        this.f3047o = in.readString();
        this.f3046n = in.readString();
        this.f3045m = in.readString();
    }

    public String m3083a() {
        return this.f3033a;
    }

    public String m3084b() {
        return this.f3034b;
    }

    public String m3085c() {
        return this.f3035c;
    }

    public String m3086d() {
        return this.f3036d;
    }

    public String m3087e() {
        return this.f3037e;
    }

    public String m3088f() {
        return this.f3038f;
    }

    public String m3089g() {
        return this.f3039g;
    }

    public String m3090h() {
        return this.f3040h;
    }

    public Drawable m3091i() {
        return this.f3044l;
    }

    public float m3092j() {
        return this.f3041i;
    }

    public boolean m3093k() {
        return this.f3042j;
    }

    public boolean m3094l() {
        return this.f3043k;
    }

    public String m3095m() {
        return this.f3047o;
    }

    public String m3096n() {
        return this.f3045m;
    }

    public String m3097o() {
        return this.f3046n;
    }

    public boolean m3098p() {
        return this.f3046n != null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 0;
        if (m3091i() != null) {
            dest.writeParcelable(((BitmapDrawable) m3091i()).getBitmap(), flags);
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
        }
        dest.writeString(this.f3033a);
        dest.writeString(this.f3034b);
        dest.writeString(this.f3035c);
        dest.writeString(this.f3036d);
        dest.writeString(this.f3037e);
        dest.writeString(this.f3038f);
        dest.writeString(this.f3039g);
        dest.writeString(this.f3040h);
        dest.writeFloat(this.f3041i);
        if (this.f3042j) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (this.f3043k) {
            i2 = 1;
        }
        dest.writeInt(i2);
        dest.writeString(this.f3047o);
        dest.writeString(this.f3046n);
        dest.writeString(this.f3045m);
    }

    static {
        CREATOR = new StartAppSDK();
    }
}
