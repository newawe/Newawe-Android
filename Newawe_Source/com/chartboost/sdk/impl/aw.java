package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0370a;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0324f;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C0343a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class aw extends C0372g {
    protected C1018j f3520k;
    protected C1018j f3521l;
    private List<C0321a> f3522m;
    private C1018j f3523n;
    private C1018j f3524o;
    private C1018j f3525p;
    private C1018j f3526q;
    private C1018j f3527r;
    private C1018j f3528s;
    private C1018j f3529t;
    private Set<C1018j> f3530u;
    private int f3531v;
    private C0321a f3532w;
    private int f3533x;
    private int f3534y;
    private String f3535z;

    /* renamed from: com.chartboost.sdk.impl.aw.b */
    private enum C0395b {
        FEATURED("featured", aq.class),
        REGULAR("regular", ar.class),
        WEBVIEW("webview", at.class),
        VIDEO("video", as.class);
        
        private String f462e;
        private Class<? extends ap> f463f;

        private C0395b(String str, Class<? extends ap> cls) {
            this.f462e = str;
            this.f463f = cls;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.aw.a */
    public class C1048a extends C0370a {
        final /* synthetic */ aw f3513b;
        private bl f3514c;
        private bk f3515d;
        private TextView f3516e;
        private RelativeLayout f3517f;
        private ListView f3518g;
        private C0394a f3519h;

        /* renamed from: com.chartboost.sdk.impl.aw.a.a */
        public class C0394a extends ArrayAdapter<C0321a> {
            final /* synthetic */ C1048a f455a;
            private Context f456b;

            /* renamed from: com.chartboost.sdk.impl.aw.a.a.1 */
            class C03931 implements OnClickListener {
                final /* synthetic */ C0321a f453a;
                final /* synthetic */ C0394a f454b;

                C03931(C0394a c0394a, C0321a c0321a) {
                    this.f454b = c0394a;
                    this.f453a = c0321a;
                }

                public void onClick(View v) {
                    String e = this.f453a.m138e("deep-link");
                    if (TextUtils.isEmpty(e) || !bb.m637a(e)) {
                        e = this.f453a.m138e("link");
                    }
                    if (!this.f454b.f455a.f3513b.m473a(e, this.f453a)) {
                    }
                }
            }

            public /* synthetic */ Object getItem(int x0) {
                return m538a(x0);
            }

            public C0394a(C1048a c1048a, Context context) {
                this.f455a = c1048a;
                super(context, 0, c1048a.f3513b.f3522m);
                this.f456b = context;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                int i = 0;
                C0321a a = m538a(position);
                C0321a a2 = a.m127a("type");
                if (convertView == null) {
                    View view;
                    C0395b[] values = C0395b.values();
                    while (i < values.length) {
                        if (a2.equals(values[i].f462e)) {
                            try {
                                view = (ap) values[i].f463f.getConstructor(new Class[]{aw.class, Context.class}).newInstance(new Object[]{this.f455a.f3513b, this.f456b});
                                break;
                            } catch (Throwable e) {
                                CBLogging.m78b(this, "error in more apps list", e);
                                view = null;
                            }
                        } else {
                            i++;
                        }
                    }
                    view = null;
                    convertView = view;
                } else if (!(convertView instanceof ap)) {
                    return convertView;
                } else {
                    ap convertView2 = (ap) convertView;
                }
                if (convertView == null) {
                    return new View(getContext());
                }
                convertView.m537a(a, position);
                LayoutParams layoutParams = convertView.getLayoutParams();
                if (layoutParams == null || !(layoutParams instanceof AbsListView.LayoutParams)) {
                    layoutParams = new AbsListView.LayoutParams(-1, convertView.m536a());
                } else {
                    layoutParams = (AbsListView.LayoutParams) layoutParams;
                    layoutParams.width = -1;
                    layoutParams.height = convertView.m536a();
                }
                convertView.setLayoutParams(layoutParams);
                convertView.setOnClickListener(new C03931(this, a));
                return convertView;
            }

            public int getCount() {
                return this.f455a.f3513b.f3522m.size();
            }

            public C0321a m538a(int i) {
                return (C0321a) this.f455a.f3513b.f3522m.get(i);
            }

            public int getItemViewType(int position) {
                C0321a a = m538a(position).m127a("type");
                C0395b[] values = C0395b.values();
                for (int i = 0; i < values.length; i++) {
                    if (a.equals(values[i].f462e)) {
                        return i;
                    }
                }
                return 0;
            }

            public int getViewTypeCount() {
                return C0395b.values().length;
            }
        }

        /* renamed from: com.chartboost.sdk.impl.aw.a.1 */
        class C10471 extends bl {
            final /* synthetic */ aw f3511a;
            final /* synthetic */ C1048a f3512b;

            C10471(C1048a c1048a, Context context, aw awVar) {
                this.f3512b = c1048a;
                this.f3511a = awVar;
                super(context);
            }

            protected void m3922a(MotionEvent motionEvent) {
                this.f3512b.f3513b.m482h();
            }
        }

        private C1048a(aw awVar, Context context) {
            this.f3513b = awVar;
            super(awVar, context);
            setBackgroundColor(-1);
            this.f3515d = new bk(context);
            this.f3514c = new C10471(this, context, awVar);
            this.f3516e = new TextView(context);
            this.f3516e.setBackgroundColor(awVar.f3533x);
            this.f3516e.setText(awVar.f3535z);
            this.f3516e.setTextColor(awVar.f3534y);
            this.f3516e.setTextSize(2, m460c() ? 30.0f : 18.0f);
            this.f3516e.setGravity(17);
            this.f3518g = new ListView(context);
            this.f3518g.setBackgroundColor(-1);
            this.f3518g.setDividerHeight(0);
            m456a(this.f3518g);
            addView(this.f3518g);
            this.f3515d.setFocusable(false);
            this.f3514c.setFocusable(false);
            this.f3514c.setClickable(true);
            this.f3515d.setScaleType(ScaleType.CENTER_CROP);
            this.f3514c.m715a(ScaleType.FIT_CENTER);
            this.f3517f = new RelativeLayout(context);
            this.f3517f.addView(this.f3515d, new RelativeLayout.LayoutParams(-1, -1));
            this.f3517f.addView(this.f3516e, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.f3517f, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.f3514c, new RelativeLayout.LayoutParams(-1, -1));
            m456a(this.f3517f);
            this.f3519h = new C0394a(this, context);
        }

        protected void m3923a(int i, int i2) {
            C1018j e;
            int i3;
            Context context = getContext();
            C0324f c = CBUtility.m93c();
            if (c.m165a() && this.f3513b.f3526q.m3726e()) {
                e = this.f3513b.f3526q;
            } else if (c.m166b() && this.f3513b.f3527r.m3726e()) {
                e = this.f3513b.f3527r;
            } else if (this.f3513b.f3529t.m3726e()) {
                e = this.f3513b.f3529t;
            } else {
                e = null;
            }
            if (e != null) {
                this.f3513b.f3531v = e.m3730i();
                if (e.m3729h() < i) {
                    this.f3513b.f3531v = Math.round(((float) this.f3513b.f3531v) * (((float) i) / ((float) e.m3729h())));
                }
                this.f3516e.setVisibility(8);
                this.f3515d.m707a(e);
            } else {
                this.f3513b.f3531v = CBUtility.m86a(m460c() ? 80 : 40, context);
                this.f3516e.setVisibility(0);
            }
            if (this.f3513b.f3532w.m134c()) {
                this.f3513b.f3531v = CBUtility.m86a(this.f3513b.f3532w.m150k(), context);
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.f3513b.f3531v);
            if (this.f3513b.f3524o.m3726e() && c.m165a()) {
                e = this.f3513b.f3524o;
            } else if (this.f3513b.f3525p.m3726e() && c.m166b()) {
                e = this.f3513b.f3525p;
            } else if (this.f3513b.f3523n.m3726e()) {
                e = this.f3513b.f3523n;
            } else {
                e = null;
            }
            if (e != null) {
                this.f3514c.m717a(e, layoutParams2);
                if (m460c()) {
                    i3 = 14;
                } else {
                    i3 = 7;
                }
                i3 = CBUtility.m86a(i3, context);
            } else {
                this.f3514c.m718a("X");
                this.f3514c.m713a().setTextSize(2, m460c() ? 26.0f : 16.0f);
                this.f3514c.m713a().setTextColor(this.f3513b.f3534y);
                this.f3514c.m713a().setTypeface(Typeface.SANS_SERIF, 1);
                layoutParams2.width = this.f3513b.f3531v / 2;
                layoutParams2.height = this.f3513b.f3531v / 3;
                if (m460c()) {
                    i3 = 30;
                } else {
                    i3 = 20;
                }
                i3 = CBUtility.m86a(i3, context);
            }
            int round = Math.round(((float) (this.f3513b.f3531v - layoutParams2.height)) / 2.0f);
            layoutParams2.rightMargin = i3;
            layoutParams2.topMargin = round;
            layoutParams2.addRule(11);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.addRule(3, this.f3517f.getId());
            this.f3518g.setAdapter(this.f3519h);
            this.f3518g.setLayoutParams(layoutParams);
            this.f3514c.setLayoutParams(layoutParams2);
            this.f3517f.setLayoutParams(layoutParams3);
        }

        public void m3924b() {
            super.m459b();
            this.f3514c = null;
            this.f3515d = null;
            this.f3518g = null;
        }
    }

    public aw(C0343a c0343a) {
        super(c0343a);
        this.f3522m = new ArrayList();
        this.f3529t = new C1018j(this);
        this.f3527r = new C1018j(this);
        this.f3526q = new C1018j(this);
        this.f3528s = new C1018j(this);
        this.f3523n = new C1018j(this);
        this.f3525p = new C1018j(this);
        this.f3524o = new C1018j(this);
        this.f3521l = new C1018j(this);
        this.f3520k = new C1018j(this);
    }

    protected C0370a m3941b(Context context) {
        return new C1048a(context, null);
    }

    public boolean m3940a(C0321a c0321a) {
        int i = 0;
        if (!super.m472a(c0321a)) {
            return false;
        }
        C0321a a = c0321a.m127a("cells");
        if (a.m131b()) {
            m468a(CBImpressionError.INVALID_RESPONSE);
            return false;
        }
        this.f3530u = new HashSet();
        while (i < a.m154o()) {
            C0321a c = a.m133c(i);
            this.f3522m.add(c);
            C0321a a2 = c.m127a("type");
            if (a2.equals("regular")) {
                c = c.m127a("assets");
                if (c.m134c()) {
                    m3926a(c, "icon");
                }
            } else if (a2.equals("featured")) {
                c = c.m127a("assets");
                if (c.m134c()) {
                    m3926a(c, "portrait");
                    m3926a(c, "landscape");
                }
            } else if (a2.equals("webview")) {
            }
            i++;
        }
        this.f3523n.m3721a("close");
        this.f3525p.m3721a("close-landscape");
        this.f3524o.m3721a("close-portrait");
        this.f3529t.m3721a("header-center");
        this.f3526q.m3721a("header-portrait");
        this.f3527r.m3721a("header-landscape");
        this.f3528s.m3721a("header-tile");
        this.f3521l.m3721a("play-button");
        this.f3520k.m3721a("install-button");
        this.f3532w = this.e.m127a("header-height");
        if (this.f3532w.m134c()) {
            this.f3531v = this.f3532w.m150k();
        } else {
            this.f3531v = C0372g.m464a(C0351c.m378y()) ? 80 : 40;
        }
        this.f3533x = this.e.m135c("background-color") ? C0372g.m462a(this.e.m138e("background-color")) : -14571545;
        this.f3535z = this.e.m135c("header-text") ? this.e.m138e("header-text") : "More Free Games";
        this.f3534y = this.e.m135c("text-color") ? C0372g.m462a(this.e.m138e("text-color")) : -1;
        return true;
    }

    private void m3926a(C0321a c0321a, String str) {
        if (!c0321a.m132b(str)) {
            C1018j c1018j = new C1018j(this);
            this.f3530u.add(c1018j);
            c1018j.m3720a(c0321a, str, new Bundle());
        }
    }

    public void m3942d() {
        super.m478d();
        this.f3522m = null;
        for (C1018j d : this.f3530u) {
            d.m3725d();
        }
        this.f3530u.clear();
        this.f3523n.m3725d();
        this.f3525p.m3725d();
        this.f3524o.m3725d();
        this.f3529t.m3725d();
        this.f3528s.m3725d();
        this.f3526q.m3725d();
        this.f3527r.m3725d();
        this.f3521l.m3725d();
        this.f3520k.m3725d();
    }
}
