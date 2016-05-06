package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.Newawe.storage.DatabaseOpenHelper;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C1018j;
import com.chartboost.sdk.Libraries.CBUtility;
import org.apache.commons.lang.StringUtils;

public class ar extends ap {
    private aw f3486a;
    private TextView f3487b;
    private TextView f3488c;
    private TextView f3489d;
    private LinearLayout f3490e;
    private au f3491f;
    private bl f3492g;
    private int f3493h;
    private Point f3494i;
    private C1018j f3495j;
    private OnClickListener f3496k;

    /* renamed from: com.chartboost.sdk.impl.ar.1 */
    class C10451 extends bl {
        final /* synthetic */ ar f3485a;

        C10451(ar arVar, Context context) {
            this.f3485a = arVar;
            super(context);
        }

        protected void m3886a(MotionEvent motionEvent) {
            this.f3485a.f3496k.onClick(this.f3485a.f3492g);
        }
    }

    public ar(aw awVar, Context context) {
        super(context);
        this.f3486a = awVar;
        this.f3490e = new LinearLayout(context);
        this.f3490e.setOrientation(1);
        setGravity(16);
        boolean a = C0372g.m464a(context);
        this.f3487b = new TextView(context);
        this.f3487b.setTypeface(null, 1);
        this.f3487b.setTextSize(2, a ? 21.0f : 16.0f);
        this.f3487b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f3487b.setSingleLine();
        this.f3487b.setEllipsize(TruncateAt.END);
        this.f3488c = new TextView(context);
        this.f3488c.setTextSize(2, a ? 16.0f : 10.0f);
        this.f3488c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f3488c.setSingleLine();
        this.f3488c.setEllipsize(TruncateAt.END);
        this.f3489d = new TextView(context);
        this.f3489d.setTextSize(2, a ? 18.0f : 11.0f);
        this.f3489d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f3489d.setMaxLines(2);
        this.f3489d.setEllipsize(TruncateAt.END);
        this.f3492g = new C10451(this, context);
        this.f3492g.m715a(ScaleType.FIT_CENTER);
        this.f3491f = new au(context);
        setFocusable(false);
        setGravity(16);
        addView(this.f3491f);
        addView(this.f3490e, new LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addView(this.f3492g);
        setBackgroundColor(0);
        this.f3490e.addView(this.f3487b, new LayoutParams(-1, -2));
        this.f3490e.addView(this.f3488c, new LayoutParams(-1, -2));
        this.f3490e.addView(this.f3489d, new LayoutParams(-1, -1));
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.f3496k = clickListener;
    }

    public void m3892a(C0321a c0321a, int i) {
        this.f3487b.setText(c0321a.m127a("name").m136d("Unknown App"));
        if (TextUtils.isEmpty(c0321a.m138e("publisher"))) {
            this.f3488c.setVisibility(8);
        } else {
            this.f3488c.setText(c0321a.m138e("publisher"));
        }
        if (TextUtils.isEmpty(c0321a.m138e("description"))) {
            this.f3489d.setVisibility(8);
        } else {
            this.f3489d.setText(c0321a.m138e("description"));
        }
        this.f3493h = c0321a.m132b("border-color") ? -4802890 : C0372g.m462a(c0321a.m138e("border-color"));
        if (c0321a.m135c("offset")) {
            this.f3494i = new Point(c0321a.m127a("offset").m140f("x"), c0321a.m127a("offset").m140f("y"));
        } else {
            this.f3494i = new Point(0, 0);
        }
        this.f3495j = null;
        if (c0321a.m135c("deep-link") && bb.m637a(c0321a.m138e("deep-link"))) {
            if (this.f3486a.f3521l.m3726e()) {
                this.f3495j = this.f3486a.f3521l;
            } else {
                this.f3492g.m718a("Play");
            }
        } else if (this.f3486a.f3520k.m3726e()) {
            this.f3495j = this.f3486a.f3520k;
        } else {
            this.f3492g.m718a("Install");
        }
        int a = CBUtility.m86a(C0372g.m464a(getContext()) ? 14 : 7, getContext());
        if (this.f3495j != null) {
            this.f3492g.m716a(this.f3495j);
            a = (a * 2) + Math.round((((float) this.f3495j.m3723b()) * ((float) m3890c())) / ((float) this.f3495j.m3724c()));
        } else {
            this.f3492g.m713a().setTextColor(-14571545);
            a = CBUtility.m86a(8, getContext());
            this.f3492g.m713a().setPadding(a, a, a, a);
            a = CBUtility.m86a(100, getContext());
        }
        this.f3492g.setLayoutParams(new LayoutParams(a, m3890c()));
        removeView(this.f3491f);
        this.f3491f = new au(getContext());
        addView(this.f3491f, 0);
        m3888a(this.f3491f, i, c0321a.m127a("assets").m127a("icon"));
        this.f3491f.m3899a(this.f3493h);
        this.f3491f.m3898a(0.16666667f);
        m3893b();
    }

    private void m3888a(bk bkVar, int i, C0321a c0321a) {
        if (!c0321a.m131b()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = StringUtils.EMPTY;
            if (!(c0321a.m138e("checksum") == null || c0321a.m138e("checksum").isEmpty())) {
                str = c0321a.m138e("checksum");
            }
            bc.m652a().m658a(c0321a.m138e(DatabaseOpenHelper.HISTORY_ROW_URL), str, null, bkVar, bundle);
        }
    }

    protected void m3893b() {
        int a = CBUtility.m86a(C0372g.m464a(getContext()) ? 14 : 7, getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(m3891a() - (a * 2), m3891a() - (a * 2));
        layoutParams.setMargins(a, a, a, a);
        this.f3490e.setPadding(0, a, 0, a);
        this.f3492g.setPadding((this.f3494i.x * 2) + a, this.f3494i.y * 2, a, 0);
        this.f3491f.setLayoutParams(layoutParams);
        this.f3491f.setScaleType(ScaleType.FIT_CENTER);
    }

    public int m3891a() {
        int i = 134;
        if (CBUtility.m93c().m166b()) {
            if (!C0372g.m464a(getContext())) {
                i = 75;
            }
        } else if (!C0372g.m464a(getContext())) {
            i = 77;
        }
        return CBUtility.m86a(i, getContext());
    }

    private int m3890c() {
        int i = 74;
        if (CBUtility.m93c().m166b()) {
            if (!C0372g.m464a(getContext())) {
                i = 41;
            }
        } else if (!C0372g.m464a(getContext())) {
            i = 41;
        }
        return CBUtility.m86a(i, getContext());
    }
}
