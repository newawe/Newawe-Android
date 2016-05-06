package com.startapp.android.publish.list3d;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.model.MetaData;
import java.util.List;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class List3DActivity extends Activity implements StartAppSDK {
    private StartAppSDK f4145a;
    private ProgressDialog f4146b;
    private WebView f4147c;
    private List<ListItem> f4148d;
    private int f4149e;
    private String f4150f;
    private com.startapp.android.publish.adinformation.StartAppSDK f4151g;
    private Long f4152h;
    private String f4153i;
    private String f4154j;
    private BroadcastReceiver f4155k;

    /* renamed from: com.startapp.android.publish.list3d.List3DActivity.1 */
    class StartAppSDK extends BroadcastReceiver {
        final /* synthetic */ List3DActivity f3028a;

        StartAppSDK(List3DActivity list3DActivity) {
            this.f3028a = list3DActivity;
        }

        public void onReceive(Context context, Intent intent) {
            this.f3028a.finish();
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.List3DActivity.2 */
    class StartAppSDK implements OnItemClickListener {
        final /* synthetic */ List3DActivity f3030a;

        /* renamed from: com.startapp.android.publish.list3d.List3DActivity.2.1 */
        class StartAppSDK implements Runnable {
            final /* synthetic */ StartAppSDK f3029a;

            StartAppSDK(StartAppSDK startAppSDK) {
                this.f3029a = startAppSDK;
            }

            public void run() {
                this.f3029a.f3030a.finish();
            }
        }

        StartAppSDK(List3DActivity list3DActivity) {
            this.f3030a = list3DActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String b = ((ListItem) this.f3030a.f4148d.get(position)).m3084b();
            String d = ((ListItem) this.f3030a.f4148d.get(position)).m3086d();
            String e = ((ListItem) this.f3030a.f4148d.get(position)).m3087e();
            boolean k = ((ListItem) this.f3030a.f4148d.get(position)).m3093k();
            boolean l = ((ListItem) this.f3030a.f4148d.get(position)).m3094l();
            Object o = ((ListItem) this.f3030a.f4148d.get(position)).m3097o();
            String n = ((ListItem) this.f3030a.f4148d.get(position)).m3096n();
            if (o != null && !TextUtils.isEmpty(o)) {
                m3080a(o, n, b, d);
            } else if (!TextUtils.isEmpty(b)) {
                if (k) {
                    com.startapp.android.publish.p022h.StartAppSDK.m3002a(this.f3030a, b, d, e, new com.startapp.android.publish.p022h.StartAppSDK(this.f3030a.f4150f), MetaData.getInstance().getSmartRedirectTimeout(), l, new StartAppSDK(this));
                    return;
                }
                com.startapp.android.publish.p022h.StartAppSDK.m2999a(this.f3030a, b, d, new com.startapp.android.publish.p022h.StartAppSDK(this.f3030a.f4150f), l);
                this.f3030a.finish();
            }
        }

        private void m3080a(String str, String str2, String str3, String str4) {
            com.startapp.android.publish.p022h.StartAppSDK.m3012a(str, str2, str3, this.f3030a, new com.startapp.android.publish.p022h.StartAppSDK(this.f3030a.f4150f));
            this.f3030a.finish();
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.List3DActivity.3 */
    class StartAppSDK implements OnClickListener {
        final /* synthetic */ List3DActivity f3031a;

        StartAppSDK(List3DActivity list3DActivity) {
            this.f3031a = list3DActivity;
        }

        public void onClick(View v) {
            this.f3031a.finish();
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.List3DActivity.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ List3DActivity f3032a;

        StartAppSDK(List3DActivity list3DActivity) {
            this.f3032a = list3DActivity;
        }

        public void run() {
            this.f3032a.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    public List3DActivity() {
        this.f4146b = null;
        this.f4147c = null;
        this.f4155k = new StartAppSDK(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("fullscreen", false)) {
            requestWindowFeature(1);
            getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
        if (savedInstanceState == null) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2920a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
            this.f4152h = (Long) getIntent().getSerializableExtra("lastLoadTime");
        } else if (savedInstanceState.containsKey("lastLoadTime")) {
            this.f4152h = (Long) savedInstanceState.getSerializable("lastLoadTime");
        }
        this.f4153i = getIntent().getStringExtra("position");
        this.f4154j = getIntent().getStringExtra("listModelUuid");
        com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2919a(this.f4155k, new IntentFilter("com.startapp.android.CloseAdActivity"));
        this.f4149e = getResources().getConfiguration().orientation;
        com.startapp.android.publish.p022h.StartAppSDK.m2994a((Activity) this, true);
        boolean booleanExtra = getIntent().getBooleanExtra("overlay", false);
        requestWindowFeature(1);
        this.f4150f = getIntent().getStringExtra("adTag");
        int backgroundGradientTop = MetaData.getInstance().getBackgroundGradientTop();
        int backgroundGradientBottom = MetaData.getInstance().getBackgroundGradientBottom();
        this.f4145a = new StartAppSDK(this, null, this.f4150f, this.f4154j);
        this.f4145a.setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{backgroundGradientTop, backgroundGradientBottom}));
        this.f4148d = StartAppSDK.m3183a().m3184a(this.f4154j).m3182b();
        if (this.f4148d == null) {
            finish();
            return;
        }
        View imageButton;
        String str = StringUtils.EMPTY;
        if (booleanExtra) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2919a(this.f4145a.f3072a, new IntentFilter("com.startapp.android.Activity3DGetValues"));
        } else {
            this.f4145a.m3166a();
            this.f4145a.setHint(true);
            this.f4145a.setFade(true);
            str = "back";
        }
        Adapter startAppSDK = new StartAppSDK(this, this.f4148d, str, this.f4150f, this.f4154j);
        StartAppSDK.m3183a().m3184a(this.f4154j).m3179a(this, !booleanExtra);
        this.f4145a.setAdapter(startAppSDK);
        this.f4145a.setDynamics(new SimpleDynamics(0.9f, 0.6f));
        this.f4145a.setOnItemClickListener(new StartAppSDK(this));
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setContentDescription("StartApp Ad");
        relativeLayout.setId(com.startapp.android.publish.StartAppSDK.STARTAPP_AD_MAIN_LAYOUT_ID);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        relativeLayout.addView(linearLayout, layoutParams2);
        View relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        relativeLayout2.setBackgroundColor(MetaData.getInstance().getTitleBackgroundColor().intValue());
        linearLayout.addView(relativeLayout2);
        TextView textView = new TextView(this);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        textView.setLayoutParams(layoutParams3);
        textView.setPadding(0, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 2), 0, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 5));
        textView.setTextColor(MetaData.getInstance().getTitleTextColor().intValue());
        textView.setTextSize((float) MetaData.getInstance().getTitleTextSize().intValue());
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.END);
        textView.setText(MetaData.getInstance().getTitleContent());
        textView.setShadowLayer(2.5f, -2.0f, 2.0f, -11513776);
        com.startapp.android.publish.p022h.StartAppSDK.m2972a(textView, MetaData.getInstance().getTitleTextDecoration());
        relativeLayout2.addView(textView);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        Bitmap a = com.startapp.android.publish.p022h.StartAppSDK.m2894a(this, "close_button.png");
        if (a != null) {
            imageButton = new ImageButton(this, null, 16973839);
            ((ImageButton) imageButton).setImageBitmap(Bitmap.createScaledBitmap(a, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 36), com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 36), true));
        } else {
            imageButton = new TextView(this);
            ((TextView) imageButton).setText("   x   ");
            ((TextView) imageButton).setTextSize(20.0f);
        }
        imageButton.setLayoutParams(layoutParams4);
        imageButton.setOnClickListener(new StartAppSDK(this));
        imageButton.setContentDescription("x");
        imageButton.setId(com.startapp.android.publish.StartAppSDK.LIST_3D_CLOSE_BUTTON_ID);
        relativeLayout2.addView(imageButton);
        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 2)));
        view.setBackgroundColor(MetaData.getInstance().getTitleLineColor().intValue());
        linearLayout.addView(view);
        layoutParams2 = new LinearLayout.LayoutParams(-1, 0);
        layoutParams2.weight = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f4145a.setLayoutParams(layoutParams2);
        linearLayout.addView(this.f4145a);
        view = new LinearLayout(this);
        layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 80;
        view.setLayoutParams(layoutParams3);
        view.setBackgroundColor(MetaData.getInstance().getPoweredByBackgroundColor().intValue());
        view.setGravity(17);
        linearLayout.addView(view);
        imageButton = new TextView(this);
        imageButton.setTextColor(MetaData.getInstance().getPoweredByTextColor().intValue());
        imageButton.setPadding(0, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 2), 0, com.startapp.android.publish.p022h.StartAppSDK.m2966a((Context) this, 3));
        imageButton.setText("Powered By ");
        imageButton.setTextSize(16.0f);
        view.addView(imageButton);
        imageButton = new ImageView(this);
        imageButton.setImageBitmap(com.startapp.android.publish.p022h.StartAppSDK.m2894a(this, "logo.png"));
        view.addView(imageButton);
        this.f4151g = new com.startapp.android.publish.adinformation.StartAppSDK(this, com.startapp.android.publish.adinformation.StartAppSDK.StartAppSDK.LARGE, Placement.INAPP_OFFER_WALL, (com.startapp.android.publish.adinformation.StartAppSDK) getIntent().getSerializableExtra("adInfoOverride"));
        this.f4151g.m2548a(relativeLayout);
        setContentView(relativeLayout, layoutParams);
        new Handler().postDelayed(new StartAppSDK(this), 500);
    }

    protected void onResume() {
        super.onResume();
        if (m4852b()) {
            com.startapp.android.publish.p022h.StartAppSDK.m2928a("List3DActivity", 3, "Cache TTL passed, finishing");
            finish();
            return;
        }
        com.startapp.android.publish.StartAppSDK.m2799a().m2813a(true);
    }

    protected void onDestroy() {
        if (this.f4146b != null) {
            synchronized (this.f4146b) {
                if (this.f4146b != null) {
                    this.f4146b.dismiss();
                    this.f4146b = null;
                }
            }
        }
        if (this.f4147c != null) {
            this.f4147c.stopLoading();
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2994a((Activity) this, false);
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        if (this.f4151g != null && this.f4151g.m2549b()) {
            this.f4151g.m2550c();
        }
        overridePendingTransition(0, 0);
        if (this.f4153i != null && this.f4153i.equals("back")) {
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.f4152h != null) {
            outState.putSerializable("lastLoadTime", this.f4152h);
        }
    }

    public void m4853a(int i) {
        View childAt = this.f4145a.getChildAt(i - this.f4145a.getFirstItemPosition());
        if (childAt != null) {
            StartAppSDK startAppSDK = (StartAppSDK) childAt.getTag();
            StartAppSDK a = StartAppSDK.m3183a().m3184a(this.f4154j);
            if (a != null && a.m3182b() != null && i < a.m3182b().size()) {
                ListItem listItem = (ListItem) a.m3182b().get(i);
                startAppSDK.m3172b().setImageBitmap(a.m3176a(i, listItem.m3083a(), listItem.m3090h()));
                startAppSDK.m3172b().requestLayout();
                startAppSDK.m3171a(listItem.m3098p());
            }
        }
    }

    public void finish() {
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("List3DActivity", 2, "Finishing activity.");
        com.startapp.android.publish.StartAppSDK.m2799a().m2813a(false);
        m4850a();
        synchronized (this) {
            if (this.f4155k != null) {
                com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2918a(this.f4155k);
                this.f4155k = null;
            }
        }
        if (!com.startapp.android.publish.StartAppSDK.OVERRIDE_NETWORK.booleanValue()) {
            StartAppSDK.m3183a().m3185b(this.f4154j);
        }
        super.finish();
    }

    private void m4850a() {
        if (this.f4149e == getResources().getConfiguration().orientation) {
            com.startapp.android.publish.p022h.StartAppSDK.m2915a((Context) this).m2920a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
        }
    }

    private boolean m4852b() {
        if (this.f4152h == null || System.currentTimeMillis() - this.f4152h.longValue() <= MetaData.getInstance().getACMConfig().getAdCacheTtl()) {
            return false;
        }
        return true;
    }
}
