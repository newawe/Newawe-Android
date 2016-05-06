package com.chartboost.sdk.impl;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.bu.C0457b;
import com.google.android.gcm.GCMConstants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class bs extends WebChromeClient {
    private static final String f676a;
    private View f677b;
    private ViewGroup f678c;
    private View f679d;
    private bt f680e;
    private boolean f681f;
    private FrameLayout f682g;
    private CustomViewCallback f683h;
    private C0453a f684i;
    private bu f685j;
    private Handler f686k;

    /* renamed from: com.chartboost.sdk.impl.bs.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ JSONObject f658a;
        final /* synthetic */ bs f659b;

        AnonymousClass10(bs bsVar, JSONObject jSONObject) {
            this.f659b = bsVar;
            this.f658a = jSONObject;
        }

        public void run() {
            try {
                this.f659b.f685j.m4056a(((float) this.f658a.getDouble(SchemaSymbols.ATTVAL_DURATION)) * 1000.0f);
            } catch (Exception e) {
                this.f659b.f685j.m4063c("Parsing exception unknown field for current player duration");
                CBLogging.m77b(bs.f676a, "Cannot find duration parameter for the video");
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.11 */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ JSONObject f660a;
        final /* synthetic */ bs f661b;

        AnonymousClass11(bs bsVar, JSONObject jSONObject) {
            this.f661b = bsVar;
            this.f660a = jSONObject;
        }

        public void run() {
            try {
                this.f661b.f685j.m4061b(((float) this.f660a.getDouble(SchemaSymbols.ATTVAL_DURATION)) * 1000.0f);
            } catch (Exception e) {
                this.f661b.f685j.m4063c("Parsing exception unknown field for total player duration");
                CBLogging.m77b(bs.f676a, "Cannot find duration parameter for the video");
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.1 */
    class C04441 implements Runnable {
        final /* synthetic */ bs f662a;

        C04441(bs bsVar) {
            this.f662a = bsVar;
        }

        public void run() {
            if (this.f662a != null) {
                this.f662a.onHideCustomView();
            }
            this.f662a.f685j.m4057a(C0457b.IDLE);
            this.f662a.f685j.m4072o();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.2 */
    class C04452 implements Runnable {
        final /* synthetic */ bs f663a;

        C04452(bs bsVar) {
            this.f663a = bsVar;
        }

        public void run() {
            this.f663a.f685j.m473a(null, null);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.3 */
    class C04463 implements Runnable {
        final /* synthetic */ bs f664a;

        C04463(bs bsVar) {
            this.f664a = bsVar;
        }

        public void run() {
            this.f664a.f685j.m4066h();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.4 */
    class C04474 implements Runnable {
        final /* synthetic */ JSONObject f665a;
        final /* synthetic */ bs f666b;

        C04474(bs bsVar, JSONObject jSONObject) {
            this.f666b = bsVar;
            this.f665a = jSONObject;
        }

        public void run() {
            try {
                this.f666b.f685j.m4062b(this.f665a.getString("message"));
            } catch (Exception e) {
                CBLogging.m77b(bs.f676a, "Error message is empty");
                this.f666b.f685j.m4062b(StringUtils.EMPTY);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.5 */
    class C04485 implements Runnable {
        final /* synthetic */ JSONObject f667a;
        final /* synthetic */ bs f668b;

        C04485(bs bsVar, JSONObject jSONObject) {
            this.f668b = bsVar;
            this.f667a = jSONObject;
        }

        public void run() {
            try {
                this.f668b.f685j.m4063c(this.f667a.getString("message"));
            } catch (Exception e) {
                CBLogging.m77b(bs.f676a, "Warning message is empty");
                this.f668b.f685j.m4063c(StringUtils.EMPTY);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.6 */
    class C04496 implements Runnable {
        final /* synthetic */ bs f669a;

        C04496(bs bsVar) {
            this.f669a = bsVar;
        }

        public void run() {
            this.f669a.f685j.m4075r();
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.7 */
    class C04507 implements Runnable {
        final /* synthetic */ JSONObject f670a;
        final /* synthetic */ bs f671b;

        C04507(bs bsVar, JSONObject jSONObject) {
            this.f671b = bsVar;
            this.f670a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.f670a.getString("name");
                if (!TextUtils.isEmpty(string)) {
                    this.f671b.f685j.f3610k = string;
                }
                this.f671b.f685j.m4073p();
            } catch (Exception e) {
                CBLogging.m77b(bs.f676a, "Cannot find video file name");
                this.f671b.f685j.m4063c("Parsing exception unknown field for video replay");
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.8 */
    class C04518 implements Runnable {
        final /* synthetic */ JSONObject f672a;
        final /* synthetic */ bs f673b;

        C04518(bs bsVar, JSONObject jSONObject) {
            this.f673b = bsVar;
            this.f672a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.f672a.getString("name");
                if (!TextUtils.isEmpty(string)) {
                    this.f673b.f685j.f3610k = string;
                }
            } catch (Exception e) {
                CBLogging.m77b(bs.f676a, "Cannot find video file name");
                this.f673b.f685j.m4063c("Parsing exception unknown field for video pause");
            }
            this.f673b.f685j.m4057a(C0457b.PAUSED);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.9 */
    class C04529 implements Runnable {
        final /* synthetic */ JSONObject f674a;
        final /* synthetic */ bs f675b;

        C04529(bs bsVar, JSONObject jSONObject) {
            this.f675b = bsVar;
            this.f674a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.f674a.getString("name");
                if (!TextUtils.isEmpty(string)) {
                    this.f675b.f685j.f3610k = string;
                }
            } catch (Exception e) {
                CBLogging.m77b(bs.f676a, "Cannot find video file name");
                this.f675b.f685j.m4063c("Parsing exception unknown field for video play");
            }
            this.f675b.f685j.m4057a(C0457b.PLAYING);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bs.a */
    public interface C0453a {
        void m743a(boolean z);
    }

    static {
        f676a = bs.class.getSimpleName();
    }

    public bs() {
        this.f686k = CBUtility.m96e();
    }

    public bs(View view, ViewGroup viewGroup, View view2, bt btVar, bu buVar) {
        this.f686k = CBUtility.m96e();
        this.f677b = view;
        this.f678c = viewGroup;
        this.f679d = view2;
        this.f680e = btVar;
        this.f681f = false;
        this.f685j = buVar;
    }

    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d(bs.class.getSimpleName(), "Chartboost Webview:" + cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        try {
            JSONObject jSONObject = new JSONObject(message);
            result.confirm(m746a(jSONObject.getJSONObject("eventArgs"), jSONObject.getString("eventType")));
        } catch (JSONException e) {
            CBLogging.m77b(f676a, "Exception caught parsing the function name from js to native");
        }
        return true;
    }

    public String m746a(JSONObject jSONObject, String str) {
        if (str.equals("click")) {
            m757k(jSONObject);
        } else if (str.equals("close")) {
            Log.d(f676a, "JavaScript to native close callback triggered");
            m758l(jSONObject);
        } else if (str.equals("videoCompleted")) {
            Log.d(f676a, "JavaScript to native video complete callback triggered");
            m748b(jSONObject);
        } else if (str.equals("videoPlaying")) {
            Log.d(f676a, "JavaScript to native video playing callback triggered");
            m754h(jSONObject);
        } else if (str.equals("videoPaused")) {
            Log.d(f676a, "JavaScript to native video pause callback triggered");
            m753g(jSONObject);
        } else if (str.equals("videoReplay")) {
            Log.d(f676a, "JavaScript to native video replay callback triggered");
            m752f(jSONObject);
        } else if (str.equals("currentDuration")) {
            m755i(jSONObject);
        } else if (str.equals("totalDuration")) {
            Log.d(f676a, "JavaScript to native total duration callback triggered");
            m756j(jSONObject);
        } else if (str.equals("show")) {
            Log.d(f676a, "JavaScript to native show callback triggered");
            m751e(jSONObject);
        } else if (str.equals(GCMConstants.EXTRA_ERROR)) {
            Log.d(f676a, "JavaScript to native error callback triggered");
            m749c(jSONObject);
        } else if (str.equals("warning")) {
            Log.d(f676a, "JavaScript to native warning callback triggered");
            m750d(jSONObject);
        } else if (!str.equals("webview")) {
            return "Function name not recognized.";
        } else {
            Log.d(f676a, "JavaScript to native webview track event callback triggered");
            m747a(jSONObject);
        }
        return "Native function successfully called.";
    }

    public void m747a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("meta");
            String string = jSONObject.getString("trackingLevel");
            if (!string.isEmpty()) {
                this.f685j.m4058a(jSONObject2, string);
            }
        } catch (Exception e) {
            CBLogging.m77b(f676a, "Parsing exception for tracking webiew events");
            this.f685j.m4063c("Parsing exception unknown field for webview track");
        }
    }

    public void m748b(JSONObject jSONObject) {
        Log.d(bt.class.getName(), "Video is Completed");
        this.f686k.post(new C04441(this));
    }

    public void m749c(JSONObject jSONObject) {
        Log.d(bt.class.getName(), "Javascript Error occured");
        this.f686k.post(new C04474(this, jSONObject));
    }

    public void m750d(JSONObject jSONObject) {
        Log.d(bt.class.getName(), "Javascript warning occurred");
        this.f686k.post(new C04485(this, jSONObject));
    }

    public void m751e(JSONObject jSONObject) {
        this.f686k.post(new C04496(this));
    }

    public void m752f(JSONObject jSONObject) {
        this.f686k.post(new C04507(this, jSONObject));
    }

    public void m753g(JSONObject jSONObject) {
        this.f686k.post(new C04518(this, jSONObject));
    }

    public void m754h(JSONObject jSONObject) {
        this.f686k.post(new C04529(this, jSONObject));
    }

    public void m755i(JSONObject jSONObject) {
        this.f686k.post(new AnonymousClass10(this, jSONObject));
    }

    public void m756j(JSONObject jSONObject) {
        this.f686k.post(new AnonymousClass11(this, jSONObject));
    }

    public void m757k(JSONObject jSONObject) {
        this.f686k.post(new C04452(this));
    }

    public void m758l(JSONObject jSONObject) {
        this.f686k.post(new C04463(this));
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            frameLayout.getFocusedChild();
            this.f681f = true;
            this.f682g = frameLayout;
            this.f683h = callback;
            this.f677b.setVisibility(4);
            this.f678c.addView(this.f682g, new LayoutParams(-1, -1));
            this.f678c.setVisibility(0);
            if (this.f684i != null) {
                this.f684i.m743a(true);
            }
        }
    }

    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        onShowCustomView(view, callback);
    }

    public void onHideCustomView() {
        if (this.f681f) {
            this.f678c.setVisibility(4);
            this.f678c.removeView(this.f682g);
            this.f677b.setVisibility(0);
            if (!(this.f683h == null || this.f683h.getClass().getName().contains(".chromium."))) {
                this.f683h.onCustomViewHidden();
            }
            this.f681f = false;
            this.f682g = null;
            this.f683h = null;
            if (this.f684i != null) {
                this.f684i.m743a(false);
            }
        }
    }
}
