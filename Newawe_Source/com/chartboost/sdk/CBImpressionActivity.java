package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.C0334k;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.ba;

public class CBImpressionActivity extends Activity {
    protected static final String f4a;
    private Activity f5b;
    private PhoneStateListener f6c;

    /* renamed from: com.chartboost.sdk.CBImpressionActivity.1 */
    class C03031 extends PhoneStateListener {
        final /* synthetic */ CBImpressionActivity f3a;

        C03031(CBImpressionActivity cBImpressionActivity) {
            this.f3a = cBImpressionActivity;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Phone call State: Ringing");
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Pausing the impression");
                this.f3a.onPause();
            } else if (state == 0) {
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Phone call State: Idle");
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Resuming the impression");
                this.f3a.onResume();
            } else if (state == 2) {
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Phone call State: OffHook");
                CBLogging.m75a(CBImpressionActivity.f4a, "##### Pausing the impression");
                this.f3a.onPause();
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    public CBImpressionActivity() {
        this.f5b = null;
        this.f6c = new C03031(this);
    }

    static {
        f4a = CBImpressionActivity.class.getSimpleName();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.f5b != null) {
            return this.f5b.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    public void forwardTouchEvents(Activity host) {
        this.f5b = host;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        requestWindowFeature(1);
        getWindow().setWindowAnimations(0);
        setContentView(new RelativeLayout(this));
        Chartboost.m19a(this);
        Chartboost.m32c((Activity) this);
        ba.m610a((Context) this).m627d();
        CBLogging.m75a(CBImpressionActivity.class.getName(), "Impression Activity onCreate() called");
    }

    protected void onStart() {
        super.onStart();
        Chartboost.m15a();
        Chartboost.m17a((Activity) this);
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(this.f6c, 32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        Chartboost.m20a(C0334k.m245a((Activity) this));
        CBUtility.m94c(this);
    }

    protected void onPause() {
        super.onPause();
        Chartboost.m29b(C0334k.m245a((Activity) this));
    }

    protected void onStop() {
        super.onStop();
        Chartboost.m33c(C0334k.m245a((Activity) this));
        try {
            ((TelephonyManager) getSystemService("phone")).listen(this.f6c, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        Chartboost.m27b((Activity) this);
    }

    public void onBackPressed() {
        if (!Chartboost.m36d()) {
            super.onBackPressed();
        }
    }
}
