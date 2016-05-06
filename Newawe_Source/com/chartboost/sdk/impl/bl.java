package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1018j;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public abstract class bl extends RelativeLayout {
    private static Rect f626b;
    private C1059a f627a;
    protected boolean f628c;
    protected Button f629d;
    private boolean f630e;

    /* renamed from: com.chartboost.sdk.impl.bl.1 */
    class C04361 implements OnTouchListener {
        final /* synthetic */ bl f624a;

        C04361(bl blVar) {
            this.f624a = blVar;
        }

        public boolean onTouch(View v, MotionEvent event) {
            boolean a = bl.m711b(v, event);
            switch (event.getActionMasked()) {
                case DurationDV.DURATION_TYPE /*0*/:
                    this.f624a.f627a.m4041a(a);
                    return a;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (this.f624a.getVisibility() == 0 && this.f624a.isEnabled() && a) {
                        this.f624a.m714a(event);
                    }
                    this.f624a.f627a.m4041a(false);
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    this.f624a.f627a.m4041a(a);
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    this.f624a.f627a.m4041a(false);
                    break;
            }
            return true;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bl.2 */
    class C04372 implements OnClickListener {
        final /* synthetic */ bl f625a;

        C04372(bl blVar) {
            this.f625a = blVar;
        }

        public void onClick(View v) {
            this.f625a.m714a(null);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bl.a */
    public class C1059a extends bk {
        final /* synthetic */ bl f3594b;

        public C1059a(bl blVar, Context context) {
            this.f3594b = blVar;
            super(context);
            blVar.f628c = false;
        }

        protected void m4041a(boolean z) {
            if (this.f3594b.f630e && z) {
                if (!this.f3594b.f628c) {
                    if (getDrawable() != null) {
                        getDrawable().setColorFilter(1996488704, Mode.SRC_ATOP);
                    } else if (getBackground() != null) {
                        getBackground().setColorFilter(1996488704, Mode.SRC_ATOP);
                    }
                    invalidate();
                    this.f3594b.f628c = true;
                }
            } else if (this.f3594b.f628c) {
                if (getDrawable() != null) {
                    getDrawable().clearColorFilter();
                } else if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                invalidate();
                this.f3594b.f628c = false;
            }
        }

        public void m4040a(C1018j c1018j, LayoutParams layoutParams) {
            m707a(c1018j);
            layoutParams.width = c1018j.m3729h();
            layoutParams.height = c1018j.m3730i();
        }
    }

    protected abstract void m714a(MotionEvent motionEvent);

    static {
        f626b = new Rect();
    }

    public bl(Context context) {
        super(context);
        this.f628c = false;
        this.f629d = null;
        this.f630e = true;
        m710b();
    }

    private void m710b() {
        this.f627a = new C1059a(this, getContext());
        this.f627a.setOnTouchListener(new C04361(this));
        addView(this.f627a, new RelativeLayout.LayoutParams(-1, -1));
    }

    private static boolean m711b(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(f626b);
        Rect rect = f626b;
        rect.left += view.getPaddingLeft();
        rect = f626b;
        rect.top += view.getPaddingTop();
        rect = f626b;
        rect.right -= view.getPaddingRight();
        rect = f626b;
        rect.bottom -= view.getPaddingBottom();
        return f626b.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    public void m718a(String str) {
        if (str != null) {
            m713a().setText(str);
            addView(m713a(), new RelativeLayout.LayoutParams(-1, -1));
            this.f627a.setVisibility(8);
            m719a(false);
            this.f629d.setOnClickListener(new C04372(this));
        } else if (this.f629d != null) {
            removeView(m713a());
            this.f629d = null;
            this.f627a.setVisibility(0);
            m719a(true);
        }
    }

    public TextView m713a() {
        if (this.f629d == null) {
            this.f629d = new Button(getContext());
            this.f629d.setGravity(17);
        }
        this.f629d.postInvalidate();
        return this.f629d;
    }

    public void m716a(C1018j c1018j) {
        this.f627a.m707a(c1018j);
        m718a(null);
    }

    public void m717a(C1018j c1018j, RelativeLayout.LayoutParams layoutParams) {
        this.f627a.m4040a(c1018j, layoutParams);
        m718a(null);
    }

    public void m715a(ScaleType scaleType) {
        this.f627a.setScaleType(scaleType);
    }

    public void m719a(boolean z) {
        this.f630e = z;
    }
}
