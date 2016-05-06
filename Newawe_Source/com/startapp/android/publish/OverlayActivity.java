package com.startapp.android.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.p009c.StartAppSDK;
import mf.org.w3c.dom.traversal.NodeFilter;

/* compiled from: StartAppSDK */
public class OverlayActivity extends Activity {
    private StartAppSDK f2616a;
    private boolean f2617b;
    private int f2618c;
    private boolean f2619d;
    private Bundle f2620e;
    private boolean f2621f;
    private int f2622g;

    public OverlayActivity() {
        this.f2621f = false;
        this.f2622g = -1;
    }

    protected void onCreate(Bundle savedInstanceState) {
        boolean z = true;
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        boolean booleanExtra = getIntent().getBooleanExtra("videoAd", false);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("fullscreen", false) || booleanExtra) {
            getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onCreate");
        this.f2619d = getIntent().getBooleanExtra("activityShouldLockOrientation", true);
        if (savedInstanceState == null && !booleanExtra) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2920a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
        }
        if (savedInstanceState != null) {
            this.f2622g = savedInstanceState.getInt("activityLockedOrientation", -1);
            this.f2619d = savedInstanceState.getBoolean("activityShouldLockOrientation", true);
        }
        this.f2618c = getIntent().getIntExtra("orientation", getResources().getConfiguration().orientation);
        if (getResources().getConfiguration().orientation == this.f2618c) {
            z = false;
        }
        this.f2617b = z;
        if (this.f2617b) {
            this.f2620e = savedInstanceState;
            return;
        }
        m2515a();
        this.f2616a.m2673a(savedInstanceState);
    }

    private void m2515a() {
        this.f2616a = StartAppSDK.m2661a(this, getIntent(), Placement.getByIndex(getIntent().getIntExtra("placement", 0)));
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.f2617b) {
            m2515a();
            this.f2616a.m2673a(this.f2620e);
            this.f2616a.m2699s();
            this.f2617b = false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.f2616a == null || this.f2616a.m2679a(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onPause() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onPause");
        super.onPause();
        if (!this.f2617b) {
            this.f2616a.m2697q();
            com.startapp.android.publish.p022h.StartAppSDK.m3034c();
        }
        overridePendingTransition(0, 0);
    }

    protected void onSaveInstanceState(Bundle outState) {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onSaveInstanceState");
        super.onSaveInstanceState(outState);
        if (!this.f2617b) {
            this.f2616a.m2681b(outState);
            outState.putInt("activityLockedOrientation", this.f2622g);
            outState.putBoolean("activityShouldLockOrientation", this.f2619d);
        }
    }

    protected void onResume() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onResume");
        super.onResume();
        if (this.f2621f) {
            this.f2616a.m2682c();
        }
        if (this.f2622g == -1) {
            this.f2622g = com.startapp.android.publish.p022h.StartAppSDK.m2984a((Activity) this, this.f2618c, this.f2619d);
        } else {
            setRequestedOrientation(this.f2622g);
        }
        if (!this.f2617b) {
            this.f2616a.m2699s();
        }
    }

    protected void onStop() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onStop");
        super.onStop();
        if (!this.f2617b) {
            this.f2616a.m2698r();
        }
    }

    protected void onDestroy() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("AppWallActivity", 2, "AppWallActivity::onDestroy");
        if (!this.f2617b) {
            this.f2616a.m2700t();
            this.f2616a = null;
            com.startapp.android.publish.p022h.StartAppSDK.m2994a((Activity) this, false);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (!this.f2616a.m2696p()) {
            super.onBackPressed();
        }
    }

    public void finish() {
        if (this.f2616a != null) {
            this.f2616a.m2695o();
        }
        super.finish();
    }
}
