package com.jirbo.adcolony;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.jirbo.adcolony.h */
class C0773h extends View {
    static double f2204p;
    static String f2205q;
    static String f2206r;
    static boolean f2207s;
    static Paint f2208t;
    static float[] f2209u;
    int f2210A;
    int f2211B;
    int f2212C;
    int f2213D;
    int f2214E;
    String f2215F;
    AdColonyV4VCAd f2216G;
    ADCImage f2217a;
    ADCImage f2218b;
    ADCImage f2219c;
    ADCImage f2220d;
    ADCImage f2221e;
    ADCImage f2222f;
    ADCImage f2223g;
    ADCImage f2224h;
    double f2225i;
    double f2226j;
    double f2227k;
    double f2228l;
    double f2229m;
    boolean f2230n;
    ArrayList<ADCImage> f2231o;
    AdColonyInterstitialAd f2232v;
    long f2233w;
    int f2234x;
    int f2235y;
    int f2236z;

    C0773h() {
        super(C0745a.m2148b());
        this.f2225i = 2.8d;
        this.f2226j = 2.05d;
        this.f2227k = 1.3d;
        this.f2228l = 2.5d;
        this.f2229m = 1.5d;
        this.f2231o = new ArrayList();
        this.f2233w = System.currentTimeMillis();
    }

    static {
        f2205q = StringUtils.EMPTY;
        f2206r = StringUtils.EMPTY;
        f2207s = true;
        f2208t = new Paint(1);
        f2209u = new float[80];
    }

    public boolean m2316a() {
        double d = 2.5d;
        double d2 = 0.8d;
        if (this.f2217a == null) {
            this.f2217a = new ADCImage(C0745a.m2165j("pre_popup_bg"));
            this.f2218b = new ADCImage(C0745a.m2165j("v4vc_logo"));
            this.f2219c = new ADCImage(C0745a.m2165j("yes_button_normal"));
            this.f2220d = new ADCImage(C0745a.m2165j("yes_button_down"));
            this.f2221e = new ADCImage(C0745a.m2165j("no_button_normal"));
            this.f2222f = new ADCImage(C0745a.m2165j("no_button_down"));
            this.f2224h = new ADCImage(C0745a.m2165j("done_button_normal"));
            this.f2223g = new ADCImage(C0745a.m2165j("done_button_down"));
            this.f2231o.add(this.f2217a);
            this.f2231o.add(this.f2218b);
            this.f2231o.add(this.f2219c);
            this.f2231o.add(this.f2220d);
            this.f2231o.add(this.f2221e);
            this.f2231o.add(this.f2222f);
            this.f2231o.add(this.f2224h);
            this.f2231o.add(this.f2223g);
            Display defaultDisplay = C0745a.m2148b().getWindowManager().getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            double d3 = height > width ? ((double) (height - width)) / 360.0d : ((double) (width - height)) / 360.0d;
            if (d3 < 0.8d && !C0745a.f2002m) {
                this.f2230n = true;
            }
            if (d3 <= 2.5d) {
                d = d3;
            }
            if (d >= 0.8d) {
                d2 = d;
            } else if (!C0745a.f2002m) {
                d2 = 1.7d;
            }
            f2204p = d2;
            if (this.f2230n) {
                this.f2225i = 2.6d;
                this.f2226j = 1.8d;
                this.f2227k = 1.0d;
                this.f2228l = 2.2d;
                this.f2229m = 1.2d;
            }
            this.f2217a.m2097a(d2 / 1.8d);
            this.f2218b.m2097a(d2 / 1.8d);
            this.f2220d.m2097a(d2 / 1.8d);
            this.f2222f.m2097a(d2 / 1.8d);
            this.f2219c.m2097a(d2 / 1.8d);
            this.f2221e.m2097a(d2 / 1.8d);
            this.f2223g.m2097a(d2 / 1.8d);
            this.f2224h.m2097a(d2 / 1.8d);
            f2208t.setTextSize((float) (18.0d * d2));
            if (this.f2230n) {
                f2208t.setTextSize((float) (d2 * 9.0d));
            }
            f2208t.setFakeBoldText(true);
        }
        return true;
    }

    public C0773h(String str, int i, AdColonyInterstitialAd adColonyInterstitialAd) {
        super(AdColony.activity());
        this.f2225i = 2.8d;
        this.f2226j = 2.05d;
        this.f2227k = 1.3d;
        this.f2228l = 2.5d;
        this.f2229m = 1.5d;
        this.f2231o = new ArrayList();
        this.f2233w = System.currentTimeMillis();
        this.f2215F = str;
        this.f2214E = i;
        this.f2232v = adColonyInterstitialAd;
        if (m2316a()) {
            AdColony.activity().addContentView(this, new LayoutParams(-1, -1, 17));
        }
    }

    int m2313a(String str) {
        f2208t.getTextWidths(str, f2209u);
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += f2209u[i];
        }
        return (int) f;
    }

    int m2318b() {
        return (int) f2208t.getTextSize();
    }

    ViewGroup m2320c() {
        return (ViewGroup) getParent();
    }

    void m2314a(String str, int i, int i2, Canvas canvas) {
        int a = i - (m2313a(str) / 2);
        f2208t.setColor(-986896);
        canvas.drawText(str, (float) (a + 1), (float) (i2 + 1), f2208t);
        f2208t.setColor(-8355712);
        canvas.drawText(str, (float) a, (float) i2, f2208t);
    }

    void m2319b(String str, int i, int i2, Canvas canvas) {
        int a = i - (m2313a(str) / 2);
        f2208t.setColor(-8355712);
        canvas.drawText(str, (float) (a + 2), (float) (i2 + 2), f2208t);
        f2208t.setColor(-1);
        canvas.drawText(str, (float) a, (float) i2, f2208t);
    }

    void m2321c(String str, int i, int i2, Canvas canvas) {
        m2319b(str, (this.f2219c.f1805f / 2) + i, ((this.f2219c.f1806g / 2) + i2) + ((m2318b() * 4) / 10), canvas);
    }

    boolean m2317a(int i, int i2, int i3, int i4) {
        if (i >= i3 && i2 >= i4 && i < this.f2219c.f1805f + i3 && i2 < this.f2219c.f1806g + i4) {
            return true;
        }
        return false;
    }

    void m2315a(String str, String str2) {
        int a = m2313a(str);
        f2205q = StringUtils.EMPTY;
        f2206r = StringUtils.EMPTY;
        String str3 = StringUtils.EMPTY;
        if (a > (this.f2217a.f1805f - m2313a("WW")) - m2313a(str2)) {
            f2207s = false;
            a = 0;
            String str4 = str3;
            int i = 0;
            while (i < (this.f2217a.f1805f - m2313a("WW")) - m2313a(str2)) {
                str4 = str4 + str.charAt(a);
                a++;
                i = m2313a(str4);
            }
            int i2 = 0;
            int i3 = 0;
            while (i2 < a) {
                if (str4.charAt(i2) != ' ' || i2 < 5) {
                    f2205q = i3 < 5 ? str.substring(0, a) : f2205q;
                    i = i3;
                } else {
                    f2205q = str.substring(0, i2);
                    i = i2;
                }
                i2++;
                i3 = i;
            }
            f2206r = i3 < 5 ? str.substring(a) : str.substring(i3);
            return;
        }
        f2207s = true;
        f2205q = str;
        f2206r = StringUtils.EMPTY;
    }

    void m2322d() {
        double d = this.f2230n ? 12.0d : 16.0d;
        Display defaultDisplay = C0745a.m2148b().getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        this.f2234x = (width - this.f2217a.f1805f) / 2;
        this.f2235y = ((height - this.f2217a.f1806g) / 2) - 80;
        this.f2236z = this.f2234x + (this.f2217a.f1805f / 2);
        this.f2210A = this.f2235y + (this.f2217a.f1806g / 2);
        this.f2213D = this.f2235y + ((int) (((double) this.f2217a.f1806g) - (((double) this.f2219c.f1806g) + (f2204p * d))));
        this.f2211B = this.f2234x + ((int) (f2204p * d));
        this.f2212C = ((int) (((double) this.f2217a.f1805f) - ((d * f2204p) + ((double) this.f2219c.f1805f)))) + this.f2234x;
    }
}
