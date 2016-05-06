package com.startapp.android.publish.list3d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import com.android.volley.DefaultRetryPolicy;
import java.util.LinkedList;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.startapp.android.publish.list3d.c */
public class StartAppSDK extends AdapterView<Adapter> {
    private boolean f3068A;
    private boolean f3069B;
    private String f3070C;
    private String f3071D;
    public BroadcastReceiver f3072a;
    private String f3073b;
    private Adapter f3074c;
    private int f3075d;
    private int f3076e;
    private int f3077f;
    private int f3078g;
    private int f3079h;
    private int f3080i;
    private int f3081j;
    private int f3082k;
    private int f3083l;
    private VelocityTracker f3084m;
    private Dynamics f3085n;
    private Runnable f3086o;
    private final LinkedList<View> f3087p;
    private Runnable f3088q;
    private Rect f3089r;
    private Camera f3090s;
    private Matrix f3091t;
    private Paint f3092u;
    private int f3093v;
    private float f3094w;
    private boolean f3095x;
    private boolean f3096y;
    private boolean f3097z;

    /* renamed from: com.startapp.android.publish.list3d.c.1 */
    class StartAppSDK extends BroadcastReceiver {
        final /* synthetic */ StartAppSDK f3064a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3064a = startAppSDK;
        }

        public void onReceive(Context context, Intent intent) {
            double height = ((double) this.f3064a.getHeight()) / ((double) intent.getIntExtra("getHeight", this.f3064a.getHeight()));
            com.startapp.android.publish.p022h.StartAppSDK.m2926a(3, this.f3064a.f3073b + "Updating Position with Ratio: [" + height + "]");
            this.f3064a.f3075d = intent.getIntExtra("mTouchState", this.f3064a.f3075d);
            this.f3064a.f3076e = intent.getIntExtra("mTouchStartX", this.f3064a.f3076e);
            this.f3064a.f3077f = intent.getIntExtra("mTouchStartY", this.f3064a.f3077f);
            this.f3064a.f3081j = intent.getIntExtra("mListRotation", this.f3064a.f3081j);
            this.f3064a.f3082k = (int) (((double) intent.getIntExtra("mFirstItemPosition", this.f3064a.f3082k)) * height);
            this.f3064a.f3082k = this.f3064a.f3082k - 1;
            this.f3064a.f3083l = (int) (((double) intent.getIntExtra("mLastItemPosition", this.f3064a.f3083l)) * height);
            this.f3064a.f3083l = this.f3064a.f3083l - 1;
            this.f3064a.f3079h = (int) (((double) intent.getIntExtra("mListTop", this.f3064a.f3079h)) * height);
            this.f3064a.f3078g = (int) (((double) intent.getIntExtra("mListTopStart", this.f3064a.f3078g)) * height);
            this.f3064a.f3080i = (int) (((double) intent.getIntExtra("mListTopOffset", this.f3064a.f3080i)) * height);
            this.f3064a.f3085n = (Dynamics) intent.getParcelableExtra("mDynamics");
            this.f3064a.f3094w = intent.getFloatExtra("mLastVelocity", this.f3064a.f3094w);
            this.f3064a.f3085n.m3071a(height);
            this.f3064a.setAdapter(new StartAppSDK(this.f3064a.getContext(), intent.getParcelableArrayListExtra(SchemaSymbols.ATTVAL_LIST), "home", this.f3064a.f3070C, this.f3064a.f3071D));
            this.f3064a.f3095x = true;
            this.f3064a.f3096y = true;
            this.f3064a.m3117a(this.f3064a.f3094w, true);
            com.startapp.android.publish.p022h.StartAppSDK.m2915a(context).m2918a((BroadcastReceiver) this);
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.c.2 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3065a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3065a = startAppSDK;
        }

        public void run() {
            this.f3065a.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 2, 0.0f, -20.0f, 0));
            this.f3065a.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, -20.0f, 0));
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.c.3 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3066a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3066a = startAppSDK;
        }

        public void run() {
            if (this.f3066a.f3085n != null) {
                View childAt = this.f3066a.getChildAt(0);
                if (childAt != null) {
                    this.f3066a.f3078g = this.f3066a.m3124b(childAt) - this.f3066a.f3080i;
                    this.f3066a.f3085n.m3075a(AnimationUtils.currentAnimationTimeMillis());
                    this.f3066a.m3118a(((int) this.f3066a.f3085n.m3070a()) - this.f3066a.f3078g);
                }
                if (!this.f3066a.f3085n.m3076a(0.5f, 0.4f)) {
                    this.f3066a.postDelayed(this, 16);
                }
            }
        }
    }

    /* renamed from: com.startapp.android.publish.list3d.c.4 */
    class StartAppSDK implements Runnable {
        final /* synthetic */ StartAppSDK f3067a;

        StartAppSDK(StartAppSDK startAppSDK) {
            this.f3067a = startAppSDK;
        }

        public void run() {
            if (this.f3067a.f3075d == 1) {
                int a = this.f3067a.m3109a(this.f3067a.f3076e, this.f3067a.f3077f);
                if (a != -1) {
                    this.f3067a.m3128b(a);
                }
            }
        }
    }

    public StartAppSDK(Context context, AttributeSet attributeSet, String str, String str2) {
        super(context, attributeSet);
        this.f3073b = "List3DView";
        this.f3075d = 0;
        this.f3087p = new LinkedList();
        this.f3093v = ExploreByTouchHelper.INVALID_ID;
        this.f3094w = 0.0f;
        this.f3095x = false;
        this.f3096y = false;
        this.f3097z = false;
        this.f3068A = false;
        this.f3069B = false;
        this.f3072a = new StartAppSDK(this);
        this.f3070C = str;
        this.f3071D = str2;
    }

    public void setTag(String TAG) {
        this.f3073b = TAG;
    }

    public void m3166a() {
        this.f3095x = true;
    }

    public void setHint(boolean hint) {
        this.f3068A = hint;
    }

    public boolean m3167b() {
        return this.f3068A;
    }

    public boolean m3168c() {
        return this.f3097z;
    }

    public void setFade(boolean fade) {
        this.f3097z = fade;
    }

    public void setAdapter(Adapter adapter) {
        if (m3142d() && m3168c()) {
            com.startapp.android.publish.p022h.StartAppSDK.m2865a((View) this, 0.0f);
        }
        this.f3074c = adapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    public Adapter getAdapter() {
        return this.f3074c;
    }

    public void setSelection(int position) {
        throw new UnsupportedOperationException("Not supported");
    }

    public View getSelectedView() {
        return null;
    }

    private boolean m3142d() {
        return com.startapp.android.publish.p022h.StartAppSDK.m2875a();
    }

    public void setDynamics(Dynamics dynamics) {
        if (this.f3085n != null) {
            dynamics.m3073a(this.f3085n.m3070a(), this.f3085n.m3077b(), AnimationUtils.currentAnimationTimeMillis());
        }
        this.f3085n = dynamics;
    }

    private void m3145e() {
        if (!this.f3069B) {
            this.f3069B = true;
            dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 0, 0.0f, 0.0f, 0));
            postDelayed(new StartAppSDK(this), 5);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float f = 0.0f;
        if (getChildCount() == 0) {
            return false;
        }
        switch (event.getAction()) {
            case DurationDV.DURATION_TYPE /*0*/:
                if (m3142d()) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2866a((View) this, 1500);
                }
                m3120a(event);
                break;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (this.f3075d == 1) {
                    m3129b((int) event.getX(), (int) event.getY());
                } else if (this.f3075d == 2) {
                    this.f3084m.addMovement(event);
                    this.f3084m.computeCurrentVelocity(DateUtils.MILLIS_IN_SECOND);
                    f = this.f3084m.getYVelocity();
                    this.f3094w = f;
                }
                m3127b(f);
                break;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                if (this.f3075d == 1) {
                    m3130b(event);
                }
                if (this.f3075d == 2) {
                    this.f3084m.addMovement(event);
                    m3118a(((int) event.getY()) - this.f3077f);
                    break;
                }
                break;
            default:
                m3127b(0.0f);
                break;
        }
        return true;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.f3095x && this.f3074c != null) {
            if (getChildCount() == 0) {
                if (m3167b()) {
                    this.f3079h = getHeight() / 3;
                }
                if (this.f3096y) {
                    this.f3083l = this.f3082k;
                    this.f3082k++;
                } else {
                    this.f3083l = -1;
                }
                m3136c(this.f3079h, 0);
            } else {
                int b = (this.f3079h + this.f3080i) - m3124b(getChildAt(0));
                m3135c(b);
                m3140d(b);
            }
            m3154h();
            if (m3167b()) {
                m3145e();
            }
            invalidate();
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Bitmap drawingCache = child.getDrawingCache();
        if (drawingCache == null) {
            return super.drawChild(canvas, child, drawingTime);
        }
        float f;
        int top = child.getTop();
        int left = child.getLeft();
        int width = child.getWidth() / 2;
        int height = child.getHeight() / 2;
        float height2 = (float) (getHeight() / 2);
        height2 = (((float) (top + height)) - height2) / height2;
        float cos = (float) (1.0d - (0.15000000596046448d * (1.0d - Math.cos((double) height2))));
        height2 = (((float) this.f3081j) - (height2 * 20.0f)) % 90.0f;
        if (height2 < 0.0f) {
            f = height2 + 90.0f;
        } else {
            f = height2;
        }
        if (f < 45.0f) {
            m3119a(canvas, drawingCache, top, left, width, height, cos, f - 90.0f);
            m3119a(canvas, drawingCache, top, left, width, height, cos, f);
        } else {
            m3119a(canvas, drawingCache, top, left, width, height, cos, f);
            m3119a(canvas, drawingCache, top, left, width, height, cos, f - 90.0f);
        }
        return false;
    }

    private void m3119a(Canvas canvas, Bitmap bitmap, int i, int i2, int i3, int i4, float f, float f2) {
        if (this.f3090s == null) {
            this.f3090s = new Camera();
        }
        this.f3090s.save();
        this.f3090s.translate(0.0f, 0.0f, (float) i4);
        this.f3090s.rotateX(f2);
        this.f3090s.translate(0.0f, 0.0f, (float) (-i4));
        if (this.f3091t == null) {
            this.f3091t = new Matrix();
        }
        this.f3090s.getMatrix(this.f3091t);
        this.f3090s.restore();
        this.f3091t.preTranslate((float) (-i3), (float) (-i4));
        this.f3091t.postScale(f, f);
        this.f3091t.postTranslate((float) (i2 + i3), (float) (i + i4));
        if (this.f3092u == null) {
            this.f3092u = new Paint();
            this.f3092u.setAntiAlias(true);
            this.f3092u.setFilterBitmap(true);
        }
        this.f3092u.setColorFilter(m3114a(f2));
        canvas.drawBitmap(bitmap, this.f3091t, this.f3092u);
    }

    private LightingColorFilter m3114a(float f) {
        int i = MotionEventCompat.ACTION_MASK;
        double cos = Math.cos((3.141592653589793d * ((double) f)) / 180.0d);
        int i2 = ((int) (200.0d * cos)) + 55;
        int pow = (int) (Math.pow(cos, 200.0d) * 70.0d);
        if (i2 > MotionEventCompat.ACTION_MASK) {
            i2 = MotionEventCompat.ACTION_MASK;
        }
        if (pow <= MotionEventCompat.ACTION_MASK) {
            i = pow;
        }
        return new LightingColorFilter(Color.rgb(i2, i2, i2), Color.rgb(i, i, i));
    }

    private void m3120a(MotionEvent motionEvent) {
        removeCallbacks(this.f3086o);
        this.f3076e = (int) motionEvent.getX();
        this.f3077f = (int) motionEvent.getY();
        this.f3078g = m3124b(getChildAt(0)) - this.f3080i;
        m3151g();
        this.f3084m = VelocityTracker.obtain();
        this.f3084m.addMovement(motionEvent);
        this.f3075d = 1;
    }

    private void m3127b(float f) {
        m3117a(f, false);
    }

    private void m3117a(float f, boolean z) {
        if (this.f3084m != null || z) {
            if (this.f3084m != null) {
                this.f3084m.recycle();
            }
            this.f3084m = null;
            removeCallbacks(this.f3088q);
            if (this.f3086o == null) {
                this.f3086o = new StartAppSDK(this);
            }
            if (this.f3085n != null) {
                if (!z) {
                    this.f3085n.m3073a((float) this.f3079h, f, AnimationUtils.currentAnimationTimeMillis());
                }
                post(this.f3086o);
            }
            this.f3075d = 0;
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f3086o);
    }

    private void m3118a(int i) {
        this.f3079h = this.f3078g + i;
        this.f3081j = (-(this.f3079h * 270)) / getHeight();
        m3148f();
        requestLayout();
    }

    private void m3148f() {
        int i = this.f3081j % 90;
        if (i < 45) {
            i = ((-(this.f3081j - i)) * getHeight()) / 270;
        } else {
            i = ((-((this.f3081j + 90) - i)) * getHeight()) / 270;
        }
        if (this.f3093v == ExploreByTouchHelper.INVALID_ID && this.f3083l == this.f3074c.getCount() - 1 && m3132c(getChildAt(getChildCount() - 1)) < getHeight()) {
            this.f3093v = i;
        }
        if (i > 0) {
            i = 0;
        } else if (i < this.f3093v) {
            i = this.f3093v;
        }
        this.f3085n.m3072a((float) i);
        this.f3085n.m3078b((float) i);
    }

    private void m3151g() {
        if (this.f3088q == null) {
            this.f3088q = new StartAppSDK(this);
        }
        postDelayed(this.f3088q, (long) ViewConfiguration.getLongPressTimeout());
    }

    private boolean m3130b(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x >= this.f3076e - 10 && x <= this.f3076e + 10 && y >= this.f3077f - 10 && y <= this.f3077f + 10) {
            return false;
        }
        removeCallbacks(this.f3088q);
        this.f3075d = 2;
        return true;
    }

    private int m3109a(int i, int i2) {
        if (this.f3089r == null) {
            this.f3089r = new Rect();
        }
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).getHitRect(this.f3089r);
            if (this.f3089r.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    private void m3129b(int i, int i2) {
        int a = m3109a(i, i2);
        if (a != -1) {
            View childAt = getChildAt(a);
            a += this.f3082k;
            performItemClick(childAt, a, this.f3074c.getItemId(a));
        }
    }

    private void m3128b(int i) {
        View childAt = getChildAt(i);
        int i2 = this.f3082k + i;
        long itemId = this.f3074c.getItemId(i2);
        OnItemLongClickListener onItemLongClickListener = getOnItemLongClickListener();
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(this, childAt, i2, itemId);
        }
    }

    private void m3135c(int i) {
        int childCount = getChildCount();
        if (this.f3083l != this.f3074c.getCount() - 1 && childCount > 1) {
            View childAt = getChildAt(0);
            while (childAt != null && m3132c(childAt) + i < 0) {
                removeViewInLayout(childAt);
                int i2 = childCount - 1;
                this.f3087p.addLast(childAt);
                this.f3082k++;
                this.f3080i += m3137d(childAt);
                if (i2 > 1) {
                    childAt = getChildAt(0);
                    childCount = i2;
                } else {
                    childAt = null;
                    childCount = i2;
                }
            }
        }
        if (this.f3082k != 0 && childCount > 1) {
            int i3 = childCount;
            View childAt2 = getChildAt(childCount - 1);
            while (childAt2 != null && m3124b(childAt2) + i > getHeight()) {
                removeViewInLayout(childAt2);
                i3--;
                this.f3087p.addLast(childAt2);
                this.f3083l--;
                childAt2 = i3 > 1 ? getChildAt(i3 - 1) : null;
            }
        }
    }

    private void m3140d(int i) {
        m3136c(m3132c(getChildAt(getChildCount() - 1)), i);
        m3141d(m3124b(getChildAt(0)), i);
    }

    private void m3136c(int i, int i2) {
        while (i + i2 < getHeight() && this.f3083l < this.f3074c.getCount() - 1) {
            this.f3083l++;
            View view = this.f3074c.getView(this.f3083l, getCachedView(), this);
            m3121a(view, 0);
            i += m3137d(view);
        }
    }

    private void m3141d(int i, int i2) {
        while (i + i2 > 0 && this.f3082k > 0) {
            this.f3082k--;
            View view = this.f3074c.getView(this.f3082k, getCachedView(), this);
            m3121a(view, 1);
            int d = m3137d(view);
            i -= d;
            this.f3080i -= d;
        }
    }

    private void m3121a(View view, int i) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new LayoutParams(-2, -2);
        } else {
            layoutParams = layoutParams2;
        }
        int i2 = i == 1 ? 0 : -1;
        view.setDrawingCacheEnabled(true);
        addViewInLayout(view, i2, layoutParams, true);
        view.measure(((int) (((float) getWidth()) * 0.85f)) | 1073741824, 0);
    }

    private void m3154h() {
        int i = this.f3080i + this.f3079h;
        float width = 0.0f * ((float) getWidth());
        float height = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / (((float) getHeight()) * 0.9f);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            int sin = (int) (((double) width) * Math.sin((6.283185307179586d * ((double) height)) * ((double) i)));
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            sin += (getWidth() - measuredWidth) / 2;
            int a = m3110a(childAt);
            int i3 = i + a;
            childAt.layout(sin, i3, measuredWidth + sin, i3 + measuredHeight);
            i += (a * 2) + measuredHeight;
        }
    }

    private View getCachedView() {
        if (this.f3087p.size() != 0) {
            return (View) this.f3087p.removeFirst();
        }
        return null;
    }

    private int m3110a(View view) {
        return (int) ((((float) view.getMeasuredHeight()) * 0.35000002f) / 2.0f);
    }

    private int m3124b(View view) {
        return view.getTop() - m3110a(view);
    }

    private int m3132c(View view) {
        return view.getBottom() + m3110a(view);
    }

    private int m3137d(View view) {
        return view.getMeasuredHeight() + (m3110a(view) * 2);
    }

    public int getFirstItemPosition() {
        return this.f3082k;
    }

    public int getLastItemPosition() {
        return this.f3083l;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return super.dispatchKeyShortcutEvent(event);
    }
}
