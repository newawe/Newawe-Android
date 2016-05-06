package com.startapp.android.publish.banner;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.startapp.android.publish.model.AdPreferences;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import com.startapp.android.publish.model.adrules.AdDisplayEvent;
import com.startapp.android.publish.model.adrules.AdRulesResult;
import com.startapp.android.publish.model.adrules.SessionManager;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.startapp.android.publish.banner.a */
public abstract class StartAppSDK extends RelativeLayout {
    private static final String TAG = "BannerLayout";
    protected AdPreferences adPreferences;
    protected AdRulesResult adRulesResult;
    private boolean attachedToWindow;
    private boolean clicked;
    protected boolean drawn;
    private boolean firstLoad;
    protected int offset;
    private boolean shouldReloadBanner;
    private StartAppSDK task;
    private Timer timer;

    /* renamed from: com.startapp.android.publish.banner.a.a */
    private class StartAppSDK extends TimerTask {
        final /* synthetic */ StartAppSDK f2721a;

        /* renamed from: com.startapp.android.publish.banner.a.a.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f2720a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f2720a = startAppSDK;
            }

            public void run() {
                if (this.f2720a.f2721a.isShown() || !(this.f2720a.f2721a.adRulesResult == null || this.f2720a.f2721a.adRulesResult.shouldDisplayAd())) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a(StartAppSDK.TAG, 3, "REFRESH");
                    this.f2720a.f2721a.load();
                }
            }
        }

        private StartAppSDK(StartAppSDK startAppSDK) {
            this.f2721a = startAppSDK;
        }

        public void run() {
            this.f2721a.post(new StartAppSDK(this));
        }
    }

    protected abstract int getOffset();

    protected abstract int getRefreshRate();

    protected abstract void reload();

    public StartAppSDK(Context context) {
        super(context);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new StartAppSDK();
    }

    public StartAppSDK(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new StartAppSDK();
    }

    public StartAppSDK(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new StartAppSDK();
    }

    protected String getAdTag() {
        return getTag() != null ? getTag().toString() : null;
    }

    protected void loadBanner() {
        scheduleReloadTask();
        load();
    }

    protected void load() {
        if (this.adRulesResult == null || MetaData.getInstance().getAdRules().isApplyOnBannerRefresh()) {
            this.adRulesResult = MetaData.getInstance().getAdRules().shouldDisplayAd(Placement.INAPP_BANNER, getAdTag());
            if (this.adRulesResult.shouldDisplayAd()) {
                reload();
                return;
            }
            setVisibility(4);
            if (com.startapp.android.publish.StartAppSDK.m2732a().booleanValue()) {
                com.startapp.android.publish.p022h.StartAppSDK.m2954a().m2955a(getContext(), this.adRulesResult.getReason());
            }
        } else if (this.adRulesResult.shouldDisplayAd()) {
            reload();
        }
    }

    private void scheduleReloadTask() {
        if (this.attachedToWindow && !isInEditMode()) {
            if (this.task != null) {
                this.task.cancel();
            }
            if (this.timer != null) {
                this.timer.cancel();
            }
            this.task = new StartAppSDK();
            this.timer = new Timer();
            this.timer.scheduleAtFixedRate(this.task, (long) getRefreshRate(), (long) getRefreshRate());
        }
    }

    private void cancelReloadTask() {
        if (!isInEditMode()) {
            if (this.task != null) {
                this.task.cancel();
            }
            if (this.timer != null) {
                this.timer.cancel();
            }
            this.task = null;
            this.timer = null;
        }
    }

    protected Parcelable onSaveInstanceState() {
        if (isClicked()) {
            setClicked(false);
            this.shouldReloadBanner = true;
        }
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        bundle.putParcelable("upperState", onSaveInstanceState);
        bundle.putSerializable("adRulesResult", this.adRulesResult);
        bundle.putSerializable("adPreferences", this.adPreferences);
        bundle.putInt("offset", this.offset);
        bundle.putBoolean("firstLoad", this.firstLoad);
        bundle.putBoolean("shouldReloadBanner", this.shouldReloadBanner);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.adRulesResult = (AdRulesResult) bundle.getSerializable("adRulesResult");
            this.adPreferences = (AdPreferences) bundle.getSerializable("adPreferences");
            this.offset = bundle.getInt("offset");
            this.firstLoad = bundle.getBoolean("firstLoad");
            this.shouldReloadBanner = bundle.getBoolean("shouldReloadBanner");
            super.onRestoreInstanceState(bundle.getParcelable("upperState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "onAttachedToWindow");
        this.attachedToWindow = true;
        scheduleReloadTask();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "onDetachedFromWindow");
        this.attachedToWindow = false;
        cancelReloadTask();
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        com.startapp.android.publish.p022h.StartAppSDK.m2928a(TAG, 3, "onWindowFocusChanged");
        if (hasWindowFocus) {
            if (this.shouldReloadBanner) {
                this.shouldReloadBanner = false;
                load();
            }
            this.attachedToWindow = true;
            scheduleReloadTask();
            return;
        }
        this.attachedToWindow = false;
        cancelReloadTask();
    }

    public boolean isFirstLoad() {
        return this.firstLoad;
    }

    public void setFirstLoad(boolean firstLoad) {
        this.firstLoad = firstLoad;
    }

    protected void addDisplayEventOnLoad() {
        if (isFirstLoad() || MetaData.getInstance().getAdRules().isApplyOnBannerRefresh()) {
            setFirstLoad(false);
            SessionManager.getInstance().addAdDisplayEvent(new AdDisplayEvent(Placement.INAPP_BANNER, getAdTag()));
        }
    }

    protected void setHardwareAcceleration(AdPreferences adPreferences) {
        com.startapp.android.publish.p022h.StartAppSDK.m3010a(adPreferences, "hardwareAccelerated", com.startapp.android.publish.p022h.StartAppSDK.m2878a((View) this, this.attachedToWindow));
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
