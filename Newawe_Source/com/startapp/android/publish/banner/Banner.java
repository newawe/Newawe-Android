package com.startapp.android.publish.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.startapp.android.publish.BannerInterface;
import com.startapp.android.publish.banner.banner3d.Banner3D;
import com.startapp.android.publish.banner.bannerstandard.BannerStandard;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.adrules.SessionManager;
import java.util.Random;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class Banner extends FrameLayout implements BannerInterface {
    private static final String TAG = "Banner";
    private boolean bFirstTime;
    private boolean bVisible;
    private Banner3D banner3D;
    private BannerStandard bannerHtml;
    private boolean initBannerCalled;
    private int innerBanner3dId;
    private int innerBannerstandardId;
    private BannerOptions options;
    private BType type;

    /* renamed from: com.startapp.android.publish.banner.Banner.1 */
    class StartAppSDK implements OnGlobalLayoutListener {
        StartAppSDK() {
        }

        public void onGlobalLayout() {
            com.startapp.android.publish.p022h.StartAppSDK.m2869a(Banner.this.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            Banner.this.initBanner();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.Banner.4 */
    class StartAppSDK implements Runnable {
        StartAppSDK() {
        }

        public void run() {
            Banner.this.initBanner();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.Banner.5 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] $SwitchMap$com$startapp$android$publish$banner$Banner$BType;

        static {
            $SwitchMap$com$startapp$android$publish$banner$Banner$BType = new int[BType.values().length];
            try {
                $SwitchMap$com$startapp$android$publish$banner$Banner$BType[BType.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$startapp$android$publish$banner$Banner$BType[BType.THREED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    private enum BType {
        THREED,
        REGULAR
    }

    /* compiled from: StartAppSDK */
    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public boolean bFirstTime;
        public int banner3dId;
        public int bannerstandardId;
        public BType type;

        /* renamed from: com.startapp.android.publish.banner.Banner.SavedState.1 */
        static class StartAppSDK implements Creator<SavedState> {
            StartAppSDK() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            int readInt = in.readInt();
            this.type = BType.REGULAR;
            if (readInt == 2) {
                this.type = BType.THREED;
            }
            readInt = in.readInt();
            this.bFirstTime = false;
            if (readInt == 1) {
                this.bFirstTime = true;
            }
            this.banner3dId = in.readInt();
            this.bannerstandardId = in.readInt();
        }

        public void writeToParcel(Parcel out, int flags) {
            int i = 1;
            super.writeToParcel(out, flags);
            int i2 = 0;
            if (this.bFirstTime) {
                i2 = 1;
            }
            if (this.type == BType.THREED) {
                i = 2;
            }
            out.writeInt(i);
            out.writeInt(i2);
            out.writeInt(this.banner3dId);
            out.writeInt(this.bannerstandardId);
        }

        static {
            CREATOR = new StartAppSDK();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.Banner.2 */
    class StartAppSDK implements com.startapp.android.publish.p011e.StartAppSDK {
        StartAppSDK() {
        }

        public void onFinishLoadingMeta() {
            Banner.this.init_step2();
        }

        public void onFailedLoadingMeta() {
            Banner.this.init_step2();
        }
    }

    /* renamed from: com.startapp.android.publish.banner.Banner.3 */
    class StartAppSDK implements BannerListener {
        final /* synthetic */ BannerListener val$listener;

        StartAppSDK(BannerListener bannerListener) {
            this.val$listener = bannerListener;
        }

        public void onReceiveAd(View banner) {
            this.val$listener.onReceiveAd(Banner.this);
        }

        public void onFailedToReceiveAd(View banner) {
            this.val$listener.onFailedToReceiveAd(Banner.this);
        }

        public void onClick(View banner) {
            this.val$listener.onClick(Banner.this);
        }
    }

    public Banner(Context context) {
        this(context, null, null);
    }

    public Banner(Context context, AdPreferences adPreferences) {
        this(context, adPreferences, null);
    }

    public Banner(Context context, BannerListener listener) {
        this(context, null, listener);
    }

    public Banner(Context context, AdPreferences adPreferences, BannerListener listener) {
        super(context);
        this.innerBanner3dId = 159868227 + new Random().nextInt(100000);
        this.innerBannerstandardId = this.innerBanner3dId + 1;
        this.bFirstTime = true;
        this.type = null;
        this.bVisible = true;
        this.initBannerCalled = false;
        init(adPreferences);
        setBannerListener(listener);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.innerBanner3dId = 159868227 + new Random().nextInt(100000);
        this.innerBannerstandardId = this.innerBanner3dId + 1;
        this.bFirstTime = true;
        this.type = null;
        this.bVisible = true;
        this.initBannerCalled = false;
        init(null);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.innerBanner3dId = 159868227 + new Random().nextInt(100000);
        this.innerBannerstandardId = this.innerBanner3dId + 1;
        this.bFirstTime = true;
        this.type = null;
        this.bVisible = true;
        this.initBannerCalled = false;
        init(null);
    }

    private void init(AdPreferences adPreferences) {
        if (isInEditMode()) {
            setMinimumWidth(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) HttpStatus.SC_MULTIPLE_CHOICES));
            setMinimumHeight(com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), 50));
            setBackgroundColor(Color.rgb(169, 169, 169));
            View textView = new TextView(getContext());
            textView.setText("StartApp Banner");
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            addView(textView, layoutParams);
            return;
        }
        LayoutParams layoutParams2;
        if (adPreferences == null) {
            this.banner3D = new Banner3D(getContext(), false);
            this.bannerHtml = new BannerStandard(getContext(), false);
        } else {
            this.banner3D = new Banner3D(getContext(), false, adPreferences);
            this.bannerHtml = new BannerStandard(getContext(), false, adPreferences);
        }
        if (getLayoutParams() != null) {
            layoutParams2 = new FrameLayout.LayoutParams(getLayoutParams().width, getLayoutParams().height);
        } else {
            layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        }
        layoutParams2.gravity = 17;
        addView(this.bannerHtml, layoutParams2);
        addView(this.banner3D, layoutParams2);
        this.banner3D.setId(this.innerBanner3dId);
        this.bannerHtml.setId(this.innerBannerstandardId);
        this.banner3D.setTag(getTag());
        this.bannerHtml.setTag(getTag());
        setVisibility(8);
        getViewTreeObserver().addOnGlobalLayoutListener(new StartAppSDK());
    }

    private void initBanner() {
        if (!this.initBannerCalled) {
            this.initBannerCalled = true;
            com.startapp.android.publish.p011e.StartAppSDK startAppSDK = new StartAppSDK();
            AdPreferences adPreferences = new AdPreferences();
            com.startapp.android.publish.p022h.StartAppSDK.m2996a(getContext(), adPreferences);
            MetaData.getInstance().loadFromServer(getContext(), adPreferences, SessionManager.getInstance().getSessionRequestReason(), true, startAppSDK);
        }
    }

    private void init_step2() {
        this.options = MetaData.getInstance().getBannerOptionsCopy();
        if (this.bFirstTime) {
            int j = this.options.m2637j();
            int nextInt = new Random().nextInt(100);
            this.type = BType.REGULAR;
            if (nextInt < j) {
                this.type = BType.THREED;
            }
            com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "BannerProbability [" + nextInt + "\\" + j + "]");
        }
        switch (StartAppSDK.$SwitchMap$com$startapp$android$publish$banner$Banner$BType[this.type.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "BannerDisplaying REGULAR");
                this.banner3D.hideBanner();
                this.bannerHtml.loadBanner();
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "BannerDisplaying 3D");
                this.bannerHtml.hideBanner();
                if (!this.bFirstTime) {
                    this.banner3D.showBanner();
                    break;
                } else {
                    this.banner3D.loadBanner();
                    break;
                }
        }
        this.bFirstTime = false;
        if (this.bVisible) {
            showBanner();
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.bFirstTime = this.bFirstTime;
        savedState.type = this.type;
        savedState.banner3dId = this.innerBanner3dId;
        savedState.bannerstandardId = this.innerBannerstandardId;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            this.type = savedState.type;
            this.bFirstTime = savedState.bFirstTime;
            this.innerBanner3dId = savedState.banner3dId;
            this.innerBannerstandardId = savedState.bannerstandardId;
            this.banner3D.setId(this.innerBanner3dId);
            this.bannerHtml.setId(this.innerBannerstandardId);
            super.onRestoreInstanceState(savedState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void showBanner() {
        setVisibility(0);
        this.bVisible = true;
    }

    public void hideBanner() {
        setVisibility(8);
        this.bVisible = false;
    }

    public void setBannerListener(BannerListener listener) {
        BannerListener bannerListener = null;
        if (listener != null) {
            bannerListener = new StartAppSDK(listener);
        }
        if (this.banner3D != null) {
            this.banner3D.setBannerListener(bannerListener);
        }
        if (this.bannerHtml != null) {
            this.bannerHtml.setBannerListener(bannerListener);
        }
    }

    public void setLayoutParams(LayoutParams params) {
        super.setLayoutParams(params);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(getLayoutParams().width, getLayoutParams().height);
        layoutParams.gravity = 17;
        if (this.banner3D != null) {
            this.banner3D.setLayoutParams(layoutParams);
        }
        if (this.bannerHtml != null) {
            this.bannerHtml.setLayoutParams(layoutParams);
        }
        if (params.width > 0 && params.height > 0) {
            new Handler().post(new StartAppSDK());
        }
    }

    public void setTag(Object tag) {
        super.setTag(tag);
        if (this.banner3D != null) {
            this.banner3D.setTag(tag);
        }
        if (this.bannerHtml != null) {
            this.bannerHtml.setTag(tag);
        }
    }
}
