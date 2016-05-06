package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class CustomView extends View {
    private float f1329a;
    private float f1330b;
    private float f1331c;
    private float f1332d;
    private float f1333e;
    private SwitchIconType f1334f;
    private int f1335g;
    private Paint f1336h;
    private Path f1337i;
    private RectF f1338j;

    /* renamed from: com.inmobi.rendering.CustomView.1 */
    static /* synthetic */ class C06901 {
        static final /* synthetic */ int[] f1328a;

        static {
            f1328a = new int[SwitchIconType.values().length];
            try {
                f1328a[SwitchIconType.CLOSE_BUTTON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1328a[SwitchIconType.CLOSE_TRANSPARENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1328a[SwitchIconType.FORWARD_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1328a[SwitchIconType.FORWARD_INACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1328a[SwitchIconType.BACK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1328a[SwitchIconType.REFRESH.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1328a[SwitchIconType.CLOSE_ICON.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum SwitchIconType {
        CLOSE_BUTTON,
        CLOSE_TRANSPARENT,
        CLOSE_ICON,
        REFRESH,
        BACK,
        FORWARD_ACTIVE,
        FORWARD_INACTIVE
    }

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f, SwitchIconType switchIconType) {
        this(context);
        this.f1334f = switchIconType;
        this.f1329a = f;
        this.f1335g = 15;
        this.f1330b = (50.0f * this.f1329a) / 2.0f;
        this.f1331c = (30.0f * this.f1329a) / 2.0f;
        this.f1332d = this.f1330b - (this.f1331c / 3.0f);
        this.f1333e = this.f1330b + (this.f1331c / 3.0f);
        this.f1336h = new Paint(1);
        this.f1338j = new RectF();
        this.f1337i = new Path();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1336h.reset();
        switch (C06901.f1328a[this.f1334f.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1336h.setStrokeWidth(3.0f);
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(this.f1330b, this.f1330b, this.f1331c, this.f1336h);
                this.f1336h.setColor(-1);
                this.f1336h.setStyle(Style.STROKE);
                canvas.drawLine(this.f1332d, this.f1332d, this.f1333e, this.f1333e, this.f1336h);
                canvas.drawLine(this.f1332d, this.f1333e, this.f1333e, this.f1332d, this.f1336h);
                canvas.drawCircle(this.f1330b, this.f1330b, this.f1331c, this.f1336h);
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(0);
                this.f1336h.setStrokeWidth(3.0f);
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawCircle(this.f1330b, this.f1330b, this.f1330b, this.f1336h);
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.f1337i.reset();
                this.f1337i.setFillType(FillType.EVEN_ODD);
                this.f1337i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), (float) (getHeight() / 2));
                this.f1337i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.close();
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1336h.setStrokeWidth(3.0f);
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f1337i, this.f1336h);
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                this.f1337i.reset();
                this.f1337i.setFillType(FillType.EVEN_ODD);
                this.f1337i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), (float) (getHeight() / 2));
                this.f1337i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.close();
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(-12303292);
                this.f1336h.setStrokeWidth(3.0f);
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f1337i, this.f1336h);
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                this.f1337i.reset();
                this.f1337i.setFillType(FillType.EVEN_ODD);
                this.f1337i.moveTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), (float) (getHeight() / 2));
                this.f1337i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f));
                this.f1337i.lineTo(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), (float) (getHeight() / 2));
                this.f1337i.close();
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1336h.setStrokeWidth(3.0f);
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f1337i, this.f1336h);
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                this.f1337i.reset();
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1336h.setStrokeWidth(5.0f);
                this.f1336h.setStyle(Style.STROKE);
                this.f1338j.set(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f));
                canvas.drawArc(this.f1338j, 0.0f, 270.0f, false, this.f1336h);
                this.f1337i.setFillType(FillType.EVEN_ODD);
                this.f1337i.moveTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - (this.f1329a * 2.0f));
                this.f1337i.lineTo((((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f)) - (this.f1329a * 2.0f), (float) (getHeight() / 2));
                this.f1337i.lineTo((((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f)) + (this.f1329a * 2.0f), (float) (getHeight() / 2));
                this.f1337i.lineTo(((float) (getWidth() / 2)) + ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - (this.f1329a * 2.0f));
                this.f1337i.close();
                this.f1336h.setStyle(Style.FILL_AND_STROKE);
                canvas.drawPath(this.f1337i, this.f1336h);
            case ConnectionResult.NETWORK_ERROR /*7*/:
                this.f1336h.setAntiAlias(true);
                this.f1336h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.f1336h.setStrokeWidth(5.0f);
                this.f1336h.setStyle(Style.STROKE);
                Canvas canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((((float) this.f1335g) * this.f1329a) / 2.0f) + ((float) (getWidth() / 2)), ((((float) this.f1335g) * this.f1329a) / 2.0f) + ((float) (getHeight() / 2)), this.f1336h);
                canvas2 = canvas;
                canvas2.drawLine(((float) (getWidth() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), ((((float) this.f1335g) * this.f1329a) / 2.0f) + ((float) (getHeight() / 2)), ((((float) this.f1335g) * this.f1329a) / 2.0f) + ((float) (getWidth() / 2)), ((float) (getHeight() / 2)) - ((((float) this.f1335g) * this.f1329a) / 2.0f), this.f1336h);
            default:
        }
    }

    public void setSwitchInt(SwitchIconType switchIconType) {
        this.f1334f = switchIconType;
    }
}
