package com.startapp.android.publish.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.banner.banner3d.Banner3DSize.Size;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.startapp.android.publish.banner.banner3d.a */
public class StartAppSDK extends RelativeLayout {
    private TextView f2731a;
    private TextView f2732b;
    private ImageView f2733c;
    private com.startapp.android.publish.p022h.StartAppSDK f2734d;
    private TextView f2735e;
    private Point f2736f;

    /* renamed from: com.startapp.android.publish.banner.banner3d.a.1 */
    class StartAppSDK extends ShapeDrawable {
        final /* synthetic */ StartAppSDK f2723a;

        StartAppSDK(StartAppSDK startAppSDK, Shape shape) {
            this.f2723a = startAppSDK;
            super(shape);
        }

        protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, shape.getHeight(), -4466580, -11363070, TileMode.REPEAT));
            paint.setMaskFilter(new EmbossMaskFilter(new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}, 0.4f, 5.0f, 3.0f));
            super.onDraw(shape, canvas, paint);
        }
    }

    /* renamed from: com.startapp.android.publish.banner.banner3d.a.2 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f2724a;

        static {
            f2724a = new int[StartAppSDK.values().length];
            try {
                f2724a[StartAppSDK.XS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2724a[StartAppSDK.S.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2724a[StartAppSDK.M.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2724a[StartAppSDK.L.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2724a[StartAppSDK.XL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: com.startapp.android.publish.banner.banner3d.a.a */
    private enum StartAppSDK {
        XS,
        S,
        M,
        L,
        XL
    }

    public StartAppSDK(Context context, Point point) {
        super(context);
        this.f2736f = point;
        m2657a();
    }

    private void m2657a() {
        Context context = getContext();
        StartAppSDK templateBySize = getTemplateBySize();
        setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{MetaData.getInstance().getItemGradientTop(), MetaData.getInstance().getItemGradientBottom()}));
        setLayoutParams(new LayoutParams(-2, -2));
        int a = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 2);
        int a2 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 3);
        com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 4);
        int a3 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 5);
        int a4 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 6);
        int a5 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 8);
        com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 10);
        int a6 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 20);
        com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 84);
        int a7 = com.startapp.android.publish.p022h.StartAppSDK.m2966a(context, 90);
        setPadding(a3, 0, a3, 0);
        setTag(this);
        this.f2733c = new ImageView(context);
        this.f2733c.setId(1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a7, a7);
        layoutParams.addRule(15);
        this.f2733c.setLayoutParams(layoutParams);
        this.f2731a = new TextView(context);
        this.f2731a.setId(2);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, 1);
        layoutParams2.addRule(14);
        this.f2731a.setLayoutParams(layoutParams2);
        this.f2731a.setTextColor(MetaData.getInstance().getItemTitleTextColor().intValue());
        this.f2731a.setGravity(3);
        this.f2731a.setBackgroundColor(0);
        switch (StartAppSDK.f2724a[templateBySize.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.f2731a.setTextSize(17.0f);
                this.f2731a.setPadding(a2, 0, 0, a);
                layoutParams2.width = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) (((double) this.f2736f.x) * 0.55d));
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.f2731a.setTextSize(17.0f);
                this.f2731a.setPadding(a2, 0, 0, a);
                layoutParams2.width = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) (((double) this.f2736f.x) * 0.65d));
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                this.f2731a.setTextSize(22.0f);
                this.f2731a.setPadding(a2, 0, 0, a3);
                break;
        }
        this.f2731a.setSingleLine(true);
        this.f2731a.setEllipsize(TruncateAt.END);
        com.startapp.android.publish.p022h.StartAppSDK.m2972a(this.f2731a, MetaData.getInstance().getItemTitleTextDecoration());
        this.f2732b = new TextView(context);
        this.f2732b.setId(3);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(1, 1);
        layoutParams3.addRule(3, 2);
        layoutParams3.setMargins(0, 0, 0, a3);
        this.f2732b.setLayoutParams(layoutParams3);
        this.f2732b.setTextColor(MetaData.getInstance().getItemDescriptionTextColor().intValue());
        this.f2732b.setTextSize(18.0f);
        this.f2732b.setMaxLines(2);
        this.f2732b.setLines(2);
        this.f2732b.setSingleLine(false);
        this.f2732b.setEllipsize(TruncateAt.MARQUEE);
        this.f2732b.setHorizontallyScrolling(true);
        this.f2732b.setPadding(a2, 0, 0, 0);
        this.f2734d = new com.startapp.android.publish.p022h.StartAppSDK(getContext());
        this.f2734d.setId(5);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        switch (StartAppSDK.f2724a[templateBySize.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                layoutParams4.addRule(1, 1);
                layoutParams4.addRule(8, 1);
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                layoutParams4.addRule(1, 2);
                layoutParams3.width = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), (int) (((double) this.f2736f.x) * 0.6d));
                break;
        }
        layoutParams4.setMargins(a2, a5, a2, 0);
        this.f2734d.setLayoutParams(layoutParams4);
        this.f2735e = new TextView(context);
        LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        switch (StartAppSDK.f2724a[templateBySize.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.f2735e.setTextSize(13.0f);
                layoutParams5.addRule(1, 2);
                layoutParams5.addRule(15);
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                layoutParams5.addRule(1, 3);
                layoutParams5.addRule(15);
                layoutParams5.setMargins(a6, 0, 0, 0);
                this.f2735e.setTextSize(26.0f);
                break;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                layoutParams5.addRule(1, 3);
                layoutParams5.addRule(15);
                layoutParams5.setMargins(a6 * 7, 0, 0, 0);
                this.f2735e.setTextSize(26.0f);
                break;
        }
        this.f2735e.setPadding(a4, a4, a4, a4);
        this.f2735e.setLayoutParams(layoutParams5);
        setButtonText(false);
        this.f2735e.setTextColor(-1);
        this.f2735e.setTypeface(null, 1);
        this.f2735e.setId(4);
        this.f2735e.setShadowLayer(2.5f, -3.0f, 3.0f, -9013642);
        this.f2735e.setBackgroundDrawable(new StartAppSDK(this, new RoundRectShape(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}, null, null)));
        addView(this.f2733c);
        addView(this.f2731a);
        switch (StartAppSDK.f2724a[templateBySize.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                addView(this.f2735e);
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                addView(this.f2735e);
                addView(this.f2732b);
                break;
        }
        addView(this.f2734d);
    }

    public void setText(String text) {
        this.f2731a.setText(text);
    }

    public void setImage(Bitmap img) {
        this.f2733c.setImageBitmap(img);
    }

    public void m2659a(int i, int i2, int i3) {
        this.f2733c.setImageResource(i);
        LayoutParams layoutParams = this.f2733c.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.f2733c.setLayoutParams(layoutParams);
    }

    public void setRating(float rating) {
        this.f2734d.setRating(rating);
    }

    public void m2660a(Bitmap bitmap, int i, int i2) {
        this.f2733c.setImageBitmap(bitmap);
        LayoutParams layoutParams = this.f2733c.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.f2733c.setLayoutParams(layoutParams);
    }

    public void setDescription(String description) {
        if (description != null && description.compareTo(StringUtils.EMPTY) != 0) {
            String[] a = m2658a(description);
            String str = a[0];
            String str2 = StringUtils.EMPTY;
            if (a[1] != null) {
                str2 = m2658a(a[1])[0];
            }
            if (description.length() >= 110) {
                str2 = str2 + "...";
            }
            this.f2732b.setText(str + LineSeparator.Web + str2);
        }
    }

    private String[] m2658a(String str) {
        int i = 55;
        String[] strArr = new String[2];
        if (str.length() > 55) {
            char[] toCharArray = str.substring(0, 55).toCharArray();
            int length = toCharArray.length - 1;
            int i2 = length - 1;
            while (i2 > 0) {
                if (toCharArray[i2] == ' ') {
                    length = 1;
                    break;
                }
                i2--;
            }
            i2 = length;
            length = 0;
            if (length != 0) {
                i = i2;
            }
            strArr[0] = str.substring(0, i);
            strArr[1] = str.substring(i + 1, str.length());
        } else {
            strArr[0] = str;
            strArr[1] = null;
        }
        return strArr;
    }

    private StartAppSDK getTemplateBySize() {
        StartAppSDK startAppSDK = StartAppSDK.S;
        if (this.f2736f.x > Size.SMALL.getSize().m2645a() || this.f2736f.y > Size.SMALL.getSize().m2648b()) {
            startAppSDK = StartAppSDK.M;
        }
        if (this.f2736f.x > Size.MEDIUM.getSize().m2645a() || this.f2736f.y > Size.MEDIUM.getSize().m2648b()) {
            startAppSDK = StartAppSDK.L;
        }
        if (this.f2736f.x > Size.LARGE.getSize().m2645a() || this.f2736f.y > Size.LARGE.getSize().m2648b()) {
            return StartAppSDK.XL;
        }
        return startAppSDK;
    }

    public void setButtonText(boolean isCPE) {
        if (isCPE) {
            this.f2735e.setText("OPEN");
        } else {
            this.f2735e.setText("DOWNLOAD");
        }
    }
}
