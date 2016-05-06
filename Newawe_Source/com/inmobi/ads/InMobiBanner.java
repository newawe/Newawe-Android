package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C0680a;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.p000a.SdkContext;
import com.startapp.android.publish.model.MetaData;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

public final class InMobiBanner extends RelativeLayout {
    private static final String TAG;
    private long mAdLoadCalledTimestamp;
    private AnimationType mAnimationType;
    private BannerAdUnit mBackgroundBannerAdUnit;
    private final C0680a mBannerAdListener;
    private BannerAdUnit mBannerAdUnit1;
    private BannerAdUnit mBannerAdUnit2;
    private int mBannerHeightInDp;
    private int mBannerWidthInDp;
    private C0686b mClientCallbackHandler;
    private BannerAdListener mClientListener;
    private BannerAdUnit mForegroundBannerAdUnit;
    private boolean mIsAutoRefreshEnabled;
    private boolean mIsInitialized;
    private BannerRefreshHandler mRefreshHandler;
    private int mRefreshInterval;

    /* renamed from: com.inmobi.ads.InMobiBanner.1 */
    class C06821 implements Runnable {
        final /* synthetic */ boolean f1035a;
        final /* synthetic */ InMobiBanner f1036b;

        C06821(InMobiBanner inMobiBanner, boolean z) {
            this.f1036b = inMobiBanner;
            this.f1035a = z;
        }

        public void run() {
            if (this.f1036b.hasValidSize()) {
                this.f1036b.cancelScheduledRefresh();
                if (this.f1036b.checkForRefreshRate()) {
                    this.f1036b.mBackgroundBannerAdUnit.m5149a(this.f1035a);
                    return;
                }
                return;
            }
            Logger.m1440a(InternalLogLevel.ERROR, InMobiBanner.TAG, "The height or width of the banner can not be determined");
            this.f1036b.mBannerAdListener.m1121a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    /* renamed from: com.inmobi.ads.InMobiBanner.2 */
    class C06832 implements OnGlobalLayoutListener {
        final /* synthetic */ InMobiBanner f1037a;

        C06832(InMobiBanner inMobiBanner) {
            this.f1037a = inMobiBanner;
        }

        public void onGlobalLayout() {
            this.f1037a.mBannerWidthInDp = DisplayInfo.m1480a(this.f1037a.getMeasuredWidth());
            this.f1037a.mBannerHeightInDp = DisplayInfo.m1480a(this.f1037a.getMeasuredHeight());
            if (!this.f1037a.hasValidSize()) {
                return;
            }
            if (VERSION.SDK_INT >= 16) {
                this.f1037a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f1037a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }

    /* renamed from: com.inmobi.ads.InMobiBanner.3 */
    class C06843 implements AnimationListener {
        final /* synthetic */ C0685a f1038a;
        final /* synthetic */ InMobiBanner f1039b;

        C06843(InMobiBanner inMobiBanner, C0685a c0685a) {
            this.f1039b = inMobiBanner;
            this.f1038a = c0685a;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f1039b.displayAd();
            this.f1038a.m1127a();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    /* renamed from: com.inmobi.ads.InMobiBanner.a */
    private interface C0685a {
        void m1127a();
    }

    /* renamed from: com.inmobi.ads.InMobiBanner.b */
    private static final class C0686b extends Handler {
        private WeakReference<BannerAdListener> f1040a;
        private WeakReference<InMobiBanner> f1041b;

        public C0686b(InMobiBanner inMobiBanner, BannerAdListener bannerAdListener) {
            super(Looper.getMainLooper());
            this.f1041b = new WeakReference(inMobiBanner);
            this.f1040a = new WeakReference(bannerAdListener);
        }

        public void m1128a(BannerAdListener bannerAdListener) {
            this.f1040a = new WeakReference(bannerAdListener);
        }

        public void handleMessage(Message message) {
            Map map = null;
            InMobiBanner inMobiBanner = (InMobiBanner) this.f1041b.get();
            BannerAdListener bannerAdListener = (BannerAdListener) this.f1040a.get();
            if (inMobiBanner != null && bannerAdListener != null) {
                switch (message.what) {
                    case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                        bannerAdListener.onAdLoadSucceeded(inMobiBanner);
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        bannerAdListener.onAdLoadFailed(inMobiBanner, (InMobiAdRequestStatus) message.obj);
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        bannerAdListener.onAdDisplayed(inMobiBanner);
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        bannerAdListener.onAdDismissed(inMobiBanner);
                    case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        bannerAdListener.onAdInteraction(inMobiBanner, map);
                    case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                        bannerAdListener.onUserLeftApplication(inMobiBanner);
                    case ConnectionResult.NETWORK_ERROR /*7*/:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        bannerAdListener.onAdRewardActionCompleted(inMobiBanner, map);
                    default:
                        Logger.m1440a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                }
            }
        }
    }

    /* renamed from: com.inmobi.ads.InMobiBanner.4 */
    class C12264 implements C0680a {
        final /* synthetic */ InMobiBanner f3771a;

        /* renamed from: com.inmobi.ads.InMobiBanner.4.1 */
        class C12251 implements C0685a {
            final /* synthetic */ C12264 f3770a;

            C12251(C12264 c12264) {
                this.f3770a = c12264;
            }

            public void m4376a() {
                this.f3770a.f3771a.mClientCallbackHandler.sendEmptyMessage(1);
                this.f3770a.f3771a.scheduleRefresh();
            }
        }

        C12264(InMobiBanner inMobiBanner) {
            this.f3771a = inMobiBanner;
        }

        public void m4377a() {
            if (this.f3771a.mForegroundBannerAdUnit == null || !this.f3771a.mForegroundBannerAdUnit.m5160x()) {
                this.f3771a.swapAdUnitsAndDisplayAd(new C12251(this));
            }
        }

        public void m4378a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = inMobiAdRequestStatus;
            this.f3771a.mClientCallbackHandler.sendMessage(obtain);
            this.f3771a.scheduleRefresh();
        }

        public void m4380b() {
            this.f3771a.mClientCallbackHandler.sendEmptyMessage(3);
        }

        public void m4382c() {
            this.f3771a.scheduleRefresh();
            this.f3771a.mClientCallbackHandler.sendEmptyMessage(4);
        }

        public void m4379a(Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = map;
            this.f3771a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void m4383d() {
            this.f3771a.mClientCallbackHandler.sendEmptyMessage(6);
        }

        public void m4381b(Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = map;
            this.f3771a.mClientCallbackHandler.sendMessage(obtain);
        }
    }

    static {
        TAG = InMobiBanner.class.getSimpleName();
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsInitialized = false;
        this.mIsAutoRefreshEnabled = true;
        this.mBannerWidthInDp = 0;
        this.mBannerHeightInDp = 0;
        this.mAnimationType = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.mAdLoadCalledTimestamp = 0;
        this.mBannerAdListener = new C12264(this);
        if (SdkContext.m1257a()) {
            String str = "http://schemas.android.com/apk/lib/com.inmobi.ads";
            this.mClientCallbackHandler = new C0686b(this, this.mClientListener);
            str = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "placementId");
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
            if (str != null) {
                try {
                    initializeAdUnit(context, Long.parseLong(str.trim()));
                } catch (Throwable e) {
                    Map hashMap = new HashMap();
                    hashMap.put("errorCode", "InvalidPlacement");
                    hashMap.put("type", "banner");
                    TelemetryComponent.m4448a().m4470a("ads", "AdLoadFailed", hashMap);
                    Logger.m1441a(InternalLogLevel.ERROR, TAG, "Placement id value supplied in XML layout is not valid. Banner creation failed.", e);
                }
            } else {
                Logger.m1440a(InternalLogLevel.ERROR, TAG, "Placement id value is not supplied in XML layout. Banner creation failed.");
            }
            if (attributeValue != null) {
                try {
                    setRefreshInterval(Integer.parseInt(attributeValue.trim()));
                    return;
                } catch (Throwable e2) {
                    Logger.m1441a(InternalLogLevel.ERROR, TAG, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.", e2);
                    return;
                }
            }
            return;
        }
        Logger.m1440a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
    }

    public InMobiBanner(Context context, long j) {
        super(context);
        this.mIsInitialized = false;
        this.mIsAutoRefreshEnabled = true;
        this.mBannerWidthInDp = 0;
        this.mBannerHeightInDp = 0;
        this.mAnimationType = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.mAdLoadCalledTimestamp = 0;
        this.mBannerAdListener = new C12264(this);
        if (context == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Context supplied as null, the ad unit can't be created.");
        } else if (SdkContext.m1257a()) {
            this.mClientCallbackHandler = new C0686b(this, this.mClientListener);
            initializeAdUnit(context, j);
        } else {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
        }
    }

    public void load() {
        load(false);
    }

    void load(boolean z) {
        if (!this.mIsInitialized) {
            return;
        }
        if (getLayoutParams() == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "The layout params of the banner must be set before calling load");
            this.mBannerAdListener.m1121a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
        } else if (getLayoutParams().width == -2 || getLayoutParams().height == -2) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "The height or width of a Banner ad can't be WRAP_CONTENT");
            this.mBannerAdListener.m1121a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
        } else if (this.mForegroundBannerAdUnit == null || !this.mForegroundBannerAdUnit.m5160x()) {
            if (!hasValidSize()) {
                setSizeFromLayoutParams();
            }
            if (hasValidSize()) {
                cancelScheduledRefresh();
                if (checkForRefreshRate()) {
                    this.mBackgroundBannerAdUnit.m5149a(z);
                    return;
                }
                return;
            }
            new Handler().postDelayed(new C06821(this, z), 200);
        } else {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
            this.mClientCallbackHandler.sendMessage(obtain);
            this.mForegroundBannerAdUnit.m4352c("AdActive");
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
        }
    }

    private final boolean checkForRefreshRate() {
        if (this.mAdLoadCalledTimestamp != 0) {
            int f = this.mBackgroundBannerAdUnit.m4365k().m4409f();
            if (SystemClock.elapsedRealtime() - this.mAdLoadCalledTimestamp < ((long) (f * DateUtils.MILLIS_IN_SECOND))) {
                this.mBackgroundBannerAdUnit.m4337a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + f + " seconds"), false);
                Logger.m1440a(InternalLogLevel.ERROR, TAG, "Ad cannot be refreshed before " + f + " seconds");
                return false;
            }
        }
        this.mAdLoadCalledTimestamp = SystemClock.elapsedRealtime();
        return true;
    }

    public void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.m4344a((Map) map);
            this.mBannerAdUnit2.m4344a((Map) map);
        }
    }

    public void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.m4343a(str);
            this.mBannerAdUnit2.m4343a(str);
        }
    }

    public void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Please pass a non-null listener to the banner.");
            return;
        }
        this.mClientListener = bannerAdListener;
        if (this.mClientCallbackHandler != null) {
            this.mClientCallbackHandler.m1128a(bannerAdListener);
        }
    }

    public void setEnableAutoRefresh(boolean z) {
        if (this.mIsInitialized && this.mIsAutoRefreshEnabled != z) {
            this.mIsAutoRefreshEnabled = z;
            if (this.mIsAutoRefreshEnabled) {
                scheduleRefresh();
            } else {
                cancelScheduledRefresh();
            }
        }
    }

    public void setRefreshInterval(int i) {
        if (this.mIsInitialized) {
            if (i < this.mBackgroundBannerAdUnit.m4365k().m4409f()) {
                i = this.mBackgroundBannerAdUnit.m4365k().m4409f();
            }
            this.mRefreshInterval = i;
        }
    }

    public void setAnimationType(AnimationType animationType) {
        if (this.mIsInitialized) {
            this.mAnimationType = animationType;
        }
    }

    public void disableHardwareAcceleration() {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.m5158v();
            this.mBannerAdUnit2.m5158v();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsInitialized) {
            setSizeFromLayoutParams();
            if (!hasValidSize()) {
                setupBannerSizeObserver();
            }
            scheduleRefresh();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsInitialized) {
            cancelScheduledRefresh();
        }
    }

    private void setSizeFromLayoutParams() {
        if (getLayoutParams() != null) {
            this.mBannerWidthInDp = DisplayInfo.m1480a(getLayoutParams().width);
            this.mBannerHeightInDp = DisplayInfo.m1480a(getLayoutParams().height);
        }
    }

    @TargetApi(16)
    void setupBannerSizeObserver() {
        getViewTreeObserver().addOnGlobalLayoutListener(new C06832(this));
    }

    boolean hasValidSize() {
        return this.mBannerWidthInDp > 0 && this.mBannerHeightInDp > 0;
    }

    String getFrameSizeString() {
        return this.mBannerWidthInDp + "x" + this.mBannerHeightInDp;
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!this.mIsInitialized) {
            return;
        }
        if (i == 0) {
            scheduleRefresh();
        } else {
            cancelScheduledRefresh();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.mIsInitialized) {
            return;
        }
        if (z) {
            scheduleRefresh();
        } else {
            cancelScheduledRefresh();
        }
    }

    private void initializeAdUnit(Context context, long j) {
        this.mBannerAdUnit1 = new BannerAdUnit(this, context, j, this.mBannerAdListener);
        this.mBannerAdUnit2 = new BannerAdUnit(this, context, j, this.mBannerAdListener);
        this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        this.mRefreshInterval = this.mBackgroundBannerAdUnit.m4365k().m4410g();
        this.mRefreshHandler = new BannerRefreshHandler(this);
        this.mIsInitialized = true;
    }

    private void scheduleRefresh() {
        if (isShown() && hasWindowFocus()) {
            this.mRefreshHandler.removeMessages(1);
            if (this.mBackgroundBannerAdUnit.m4360g() == AdState.STATE_LOADING || this.mBackgroundBannerAdUnit.m4360g() == AdState.STATE_AVAILABLE || (this.mForegroundBannerAdUnit != null && this.mForegroundBannerAdUnit.m4360g() == AdState.STATE_ACTIVE)) {
                Logger.m1440a(InternalLogLevel.INTERNAL, TAG, "Ignoring an attempt to schedule refresh when an ad is already loading or active.");
            } else if (this.mIsAutoRefreshEnabled) {
                this.mRefreshHandler.sendEmptyMessageDelayed(1, (long) (this.mRefreshInterval * DateUtils.MILLIS_IN_SECOND));
            }
        }
    }

    private void cancelScheduledRefresh() {
        this.mRefreshHandler.removeMessages(1);
    }

    private void displayAd() {
        if (this.mForegroundBannerAdUnit.m5159w()) {
            this.mForegroundBannerAdUnit.m4367m().m1683o();
        }
        ViewGroup viewGroup = (ViewGroup) this.mForegroundBannerAdUnit.m4367m().getParent();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (viewGroup == null) {
            addView(this.mForegroundBannerAdUnit.m4367m(), layoutParams);
        } else {
            viewGroup.removeAllViews();
            viewGroup.addView(this.mForegroundBannerAdUnit.m4367m(), layoutParams);
        }
        this.mBackgroundBannerAdUnit.m4372r();
    }

    private void swapAdUnitsAndDisplayAd(C0685a c0685a) {
        if (this.mForegroundBannerAdUnit == null) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit1)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit2;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit2)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        }
        Animation a = AnimationController.m1227a(this.mAnimationType, (float) getWidth(), (float) getHeight());
        if (a == null) {
            displayAd();
            c0685a.m1127a();
            return;
        }
        a.setAnimationListener(new C06843(this, c0685a));
        startAnimation(a);
    }
}
