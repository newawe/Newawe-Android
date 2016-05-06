package com.startapp.android.publish.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.startapp.android.publish.banner.BannerOptions;
import com.startapp.android.publish.model.AdDetails;
import com.startapp.android.publish.model.MetaData;

/* compiled from: StartAppSDK */
public class Banner3DFace implements Parcelable, com.startapp.android.publish.p022h.StartAppSDK.StartAppSDK {
    public static final Creator<Banner3DFace> CREATOR;
    private AdDetails f4070a;
    private Point f4071b;
    private Bitmap f4072c;
    private Bitmap f4073d;
    private Boolean f4074e;
    private com.startapp.android.publish.p022h.StartAppSDK f4075f;
    private StartAppSDK f4076g;

    /* renamed from: com.startapp.android.publish.banner.banner3d.Banner3DFace.1 */
    static class StartAppSDK implements Creator<Banner3DFace> {
        StartAppSDK() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m2650a(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m2651a(x0);
        }

        public Banner3DFace m2650a(Parcel parcel) {
            return new Banner3DFace(parcel);
        }

        public Banner3DFace[] m2651a(int i) {
            return new Banner3DFace[i];
        }
    }

    public Banner3DFace(Context context, ViewGroup parent, AdDetails adDetails, BannerOptions options, com.startapp.android.publish.p022h.StartAppSDK params) {
        this.f4072c = null;
        this.f4073d = null;
        this.f4074e = Boolean.valueOf(false);
        this.f4076g = null;
        this.f4070a = adDetails;
        this.f4075f = params;
        m4761a(context, options, parent);
    }

    public AdDetails m4759a() {
        return this.f4070a;
    }

    public Bitmap m4763b() {
        return this.f4073d;
    }

    public void m4761a(Context context, BannerOptions bannerOptions, ViewGroup viewGroup) {
        int a = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, bannerOptions.m2632e() - 5);
        this.f4071b = new Point((int) (((float) com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, bannerOptions.m2631d())) * bannerOptions.m2638k()), (int) (((float) com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, bannerOptions.m2632e())) * bannerOptions.m2639l()));
        this.f4076g = new StartAppSDK(context, new Point(bannerOptions.m2631d(), bannerOptions.m2632e()));
        this.f4076g.setText(m4759a().getTitle());
        this.f4076g.setRating(m4759a().getRating());
        this.f4076g.setDescription(m4759a().getDescription());
        this.f4076g.setButtonText(this.f4070a.isCPE());
        if (this.f4072c != null) {
            this.f4076g.m2660a(this.f4072c, a, a);
        } else {
            this.f4076g.m2659a(17301651, a, a);
            new com.startapp.android.publish.p022h.StartAppSDK(m4759a().getImageUrl(), this, 0).m2900a();
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DFace", 3, " Banner Face Image Async Request: [" + m4759a().getTitle() + "]");
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f4071b.x, this.f4071b.y);
        layoutParams.addRule(13);
        viewGroup.addView(this.f4076g, layoutParams);
        this.f4076g.setVisibility(8);
        m4758c();
    }

    private void m4758c() {
        this.f4073d = m4757a(this.f4076g);
        if (this.f4071b.x > 0 && this.f4071b.y > 0) {
            this.f4073d = Bitmap.createScaledBitmap(this.f4073d, this.f4071b.x, this.f4071b.y, false);
        }
    }

    private Bitmap m4757a(View view) {
        view.measure(view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return createBitmap;
    }

    public void m4762a(Bitmap bitmap, int i) {
        if (bitmap != null && this.f4076g != null) {
            this.f4072c = bitmap;
            this.f4076g.setImage(bitmap);
            m4758c();
        }
    }

    public void m4760a(Context context) {
        if (!this.f4074e.booleanValue()) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("Banner3DFace", 3, "Sending Impression: [" + m4759a().getTitle() + "]");
            com.startapp.android.publish.p022h.StartAppSDK.m2997a(context, m4759a().getTrackingUrl(), this.f4075f);
            this.f4074e = Boolean.valueOf(true);
        }
    }

    public void m4764b(Context context) {
        String intentPackageName = m4759a().getIntentPackageName();
        if (intentPackageName != null && !"null".equals(intentPackageName) && !TextUtils.isEmpty(intentPackageName)) {
            com.startapp.android.publish.p022h.StartAppSDK.m3012a(intentPackageName, m4759a().getIntentDetails(), m4759a().getClickUrl(), context, this.f4075f);
        } else if (m4759a().isSmartRedirect()) {
            com.startapp.android.publish.p022h.StartAppSDK.m3001a(context, m4759a().getClickUrl(), m4759a().getTrackingClickUrl(), m4759a().getPackageName(), this.f4075f, MetaData.getInstance().getSmartRedirectTimeout(), m4759a().isStartappBrowserEnabled());
        } else {
            com.startapp.android.publish.p022h.StartAppSDK.m2999a(context, m4759a().getClickUrl(), m4759a().getTrackingClickUrl(), this.f4075f, m4759a().isStartappBrowserEnabled());
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(m4759a(), flags);
        dest.writeInt(this.f4071b.x);
        dest.writeInt(this.f4071b.y);
        dest.writeParcelable(this.f4072c, flags);
        dest.writeBooleanArray(new boolean[]{this.f4074e.booleanValue()});
        dest.writeSerializable(this.f4075f);
    }

    public Banner3DFace(Parcel in) {
        this.f4072c = null;
        this.f4073d = null;
        this.f4074e = Boolean.valueOf(false);
        this.f4076g = null;
        this.f4070a = (AdDetails) in.readParcelable(AdDetails.class.getClassLoader());
        this.f4071b = new Point(1, 1);
        this.f4071b.x = in.readInt();
        this.f4071b.y = in.readInt();
        this.f4072c = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        boolean[] zArr = new boolean[1];
        in.readBooleanArray(zArr);
        this.f4074e = Boolean.valueOf(zArr[0]);
        this.f4075f = (com.startapp.android.publish.p022h.StartAppSDK) in.readSerializable();
    }

    static {
        CREATOR = new StartAppSDK();
    }
}
