package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.http.HttpStatus;

@SuppressLint({"ClickableViewAccessibility"})
public class InMobiAdActivity extends Activity {
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, C0698b> f1345a;
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> f1346b;
    public static Integer f1347c;
    private static final String f1348d;
    private static Stack<RenderView> f1349e;
    private static RenderView f1350f;
    private RenderView f1351g;
    private RenderView f1352h;
    private CustomView f1353i;
    private CustomView f1354j;
    private int f1355k;
    private boolean f1356l;

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.1 */
    class C06911 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f1339a;

        C06911(InMobiAdActivity inMobiAdActivity) {
            this.f1339a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                this.f1339a.f1356l = true;
                this.f1339a.finish();
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.2 */
    class C06922 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f1340a;

        C06922(InMobiAdActivity inMobiAdActivity) {
            this.f1340a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                this.f1340a.f1352h.reload();
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.3 */
    class C06933 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f1341a;

        C06933(InMobiAdActivity inMobiAdActivity) {
            this.f1341a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                if (this.f1341a.f1352h.canGoBack()) {
                    this.f1341a.f1352h.goBack();
                } else {
                    this.f1341a.f1356l = true;
                    this.f1341a.finish();
                }
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.4 */
    class C06944 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f1342a;

        C06944(InMobiAdActivity inMobiAdActivity) {
            this.f1342a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                if (this.f1342a.f1352h.canGoForward()) {
                    this.f1342a.f1352h.goForward();
                }
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.5 */
    class C06955 implements OnClickListener {
        final /* synthetic */ InMobiAdActivity f1343a;

        C06955(InMobiAdActivity inMobiAdActivity) {
            this.f1343a = inMobiAdActivity;
        }

        public void onClick(View view) {
            this.f1343a.f1356l = true;
            this.f1343a.f1351g.m1677j();
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.6 */
    class C06966 implements OnClickListener {
        final /* synthetic */ InMobiAdActivity f1344a;

        C06966(InMobiAdActivity inMobiAdActivity) {
            this.f1344a = inMobiAdActivity;
        }

        public void onClick(View view) {
            this.f1344a.f1356l = true;
            this.f1344a.f1351g.m1677j();
        }
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.a */
    public interface C0697a {
        void m1578a();

        void m1579b();
    }

    /* renamed from: com.inmobi.rendering.InMobiAdActivity.b */
    public interface C0698b {
        void m1580a(int i, Intent intent);
    }

    public InMobiAdActivity() {
        this.f1356l = false;
    }

    static {
        f1348d = InMobiAdActivity.class.getSimpleName();
        f1349e = new Stack();
        f1345a = new HashMap();
        f1346b = new HashMap();
        f1347c = Integer.valueOf(0);
    }

    public static int m1582a(RenderView renderView) {
        f1349e.push(renderView);
        return f1349e.indexOf(renderView);
    }

    public static RenderView m1583a() {
        return (RenderView) f1349e.peek();
    }

    public static void m1589b(RenderView renderView) {
        f1350f = renderView;
    }

    public static int m1581a(Intent intent, C0698b c0698b) {
        Integer num = f1347c;
        f1347c = Integer.valueOf(f1347c.intValue() + 1);
        f1345a.put(f1347c, c0698b);
        f1346b.put(f1347c, intent);
        return f1347c.intValue();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1355k = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", HttpStatus.SC_PROCESSING);
        if (this.f1355k == 100) {
            String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
            this.f1352h = new RenderView(this, new RenderingProperties(PlacementType.FULL_SCREEN));
            this.f1352h.m1639a(f1350f.getListener(), f1350f.getRenderingConfig(), f1350f.getMraidConfig());
            m1588b();
            this.f1352h.loadUrl(stringExtra);
            this.f1352h.getListener().m1601e(this.f1352h);
            this.f1352h.setFullScreenActivity(this);
        } else if (this.f1355k == HttpStatus.SC_PROCESSING) {
            int intExtra = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", -1);
            if (intExtra != -1) {
                if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                    requestWindowFeature(1);
                    getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
                }
                this.f1351g = (RenderView) f1349e.get(intExtra);
                m1590c();
                this.f1351g.setFullScreenActivity(this);
                if (this.f1351g.getAdScreenEventsListener() != null) {
                    this.f1351g.getAdScreenEventsListener().m1578a();
                }
            }
        } else if (this.f1355k == C0302R.styleable.Theme_checkedTextViewStyle) {
            int intExtra2 = getIntent().getIntExtra("id", -1);
            if (intExtra2 == -1) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1348d, "Invalid Request Code Supplied");
            } else {
                startActivityForResult((Intent) f1346b.get(Integer.valueOf(intExtra2)), intExtra2);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f1352h != null) {
            this.f1352h.m1679k();
        }
    }

    private void m1588b() {
        ViewGroup relativeLayout = new RelativeLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        layoutParams.addRule(2, 65533);
        relativeLayout.setBackgroundColor(-1);
        relativeLayout.addView(this.f1352h, layoutParams);
        m1585a(relativeLayout);
        setContentView(relativeLayout);
    }

    private void m1585a(ViewGroup viewGroup) {
        float c = DisplayInfo.m1481a().m1497c();
        View linearLayout = new LinearLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (48.0f * c));
        linearLayout.setOrientation(0);
        linearLayout.setId(65533);
        linearLayout.setWeightSum(100.0f);
        linearLayout.setBackgroundResource(17301658);
        linearLayout.setBackgroundColor(-7829368);
        layoutParams.addRule(12);
        viewGroup.addView(linearLayout, layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = 25.0f;
        View customView = new CustomView(this, c, SwitchIconType.CLOSE_ICON);
        customView.setOnTouchListener(new C06911(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.REFRESH);
        customView.setOnTouchListener(new C06922(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.BACK);
        customView.setOnTouchListener(new C06933(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.FORWARD_INACTIVE);
        customView.setOnTouchListener(new C06944(this));
        linearLayout.addView(customView, layoutParams);
    }

    private void m1590c() {
        FrameLayout frameLayout = (FrameLayout) findViewById(16908290);
        View relativeLayout = new RelativeLayout(this);
        float c = DisplayInfo.m1481a().m1497c();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        if (this.f1351g.getParent() != null) {
            ((ViewGroup) this.f1351g.getParent()).removeView(this.f1351g);
        }
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (50.0f * c), (int) (50.0f * c));
        layoutParams2.addRule(11);
        this.f1353i = new CustomView(this, c, SwitchIconType.CLOSE_BUTTON);
        this.f1353i.setId(65532);
        this.f1353i.setOnClickListener(new C06955(this));
        this.f1354j = new CustomView(this, c, SwitchIconType.CLOSE_TRANSPARENT);
        this.f1354j.setId(65531);
        this.f1354j.setOnClickListener(new C06966(this));
        relativeLayout.setId(65534);
        relativeLayout.addView(this.f1351g, layoutParams);
        relativeLayout.addView(this.f1353i, layoutParams2);
        relativeLayout.addView(this.f1354j, layoutParams2);
        relativeLayout.setBackgroundColor(0);
        frameLayout.addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        this.f1351g.m1647a(this.f1351g.m1666e());
        this.f1351g.m1653b(this.f1351g.m1663d());
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!this.f1356l) {
            return;
        }
        if (this.f1355k == 100) {
            this.f1352h.getListener().m1602f(this.f1352h);
            this.f1352h.destroy();
        } else if (this.f1355k == HttpStatus.SC_PROCESSING) {
            if (this.f1351g.getAdScreenEventsListener() != null) {
                this.f1351g.getAdScreenEventsListener().m1579b();
            }
            f1349e.pop();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        C0698b c0698b = (C0698b) f1345a.get(Integer.valueOf(i));
        f1345a.remove(Integer.valueOf(i));
        f1346b.remove(Integer.valueOf(i));
        c0698b.m1580a(i2, intent);
        this.f1356l = true;
        finish();
    }

    void m1591a(boolean z) {
        this.f1356l = z;
    }

    public void onBackPressed() {
        if (this.f1355k == HttpStatus.SC_PROCESSING) {
            this.f1351g.m1673h();
            if (!this.f1351g.m1668f()) {
                this.f1356l = true;
                this.f1351g.m1677j();
            }
        } else if (this.f1355k == 100) {
            this.f1356l = true;
            finish();
        }
    }
}
