package com.startapp.android.publish.inappbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.SparseArray;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import org.apache.http.HttpStatus;

/* compiled from: StartAppSDK */
public class NavigationBarLayout extends RelativeLayout {
    private static final int f3004m;
    private static final int f3005n;
    private RelativeLayout f3006a;
    private ImageView f3007b;
    private ImageView f3008c;
    private ImageView f3009d;
    private ImageView f3010e;
    private Bitmap f3011f;
    private Bitmap f3012g;
    private Bitmap f3013h;
    private Bitmap f3014i;
    private TextView f3015j;
    private TextView f3016k;
    private Boolean f3017l;

    /* compiled from: StartAppSDK */
    static class SavedState extends BaseSavedState {
        public static final ClassLoaderCreator<SavedState> f3002b;
        SparseArray f3003a;

        /* renamed from: com.startapp.android.publish.inappbrowser.NavigationBarLayout.SavedState.1 */
        static class StartAppSDK implements ClassLoaderCreator<SavedState> {
            StartAppSDK() {
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return m3058a(x0);
            }

            public /* synthetic */ Object createFromParcel(Parcel x0, ClassLoader x1) {
                return m3059a(x0, x1);
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return m3060a(x0);
            }

            public SavedState m3059a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(classLoader, null);
            }

            public SavedState m3058a(Parcel parcel) {
                return m3058a(null);
            }

            public SavedState[] m3060a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in, ClassLoader classLoader) {
            super(in);
            this.f3003a = in.readSparseArray(classLoader);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(this.f3003a);
        }

        static {
            f3002b = new StartAppSDK();
        }
    }

    static {
        f3004m = Color.rgb(78, 86, HttpStatus.SC_SWITCHING_PROTOCOLS);
        f3005n = Color.rgb(148, 155, 166);
    }

    public NavigationBarLayout(Context context) {
        super(context);
        this.f3017l = Boolean.valueOf(false);
    }

    public void m3062a() {
        setDescendantFocusability(AccessibilityNodeInfoCompat.ACTION_EXPAND);
        setBackgroundColor(Color.parseColor("#e9e9e9"));
        setLayoutParams(new LayoutParams(-1, com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), 60)));
        setId(2101);
    }

    public void m3064b() {
        Typeface typeface = Typeface.DEFAULT;
        this.f3015j = com.startapp.android.publish.p022h.StartAppSDK.m2971a(getContext(), this.f3015j, typeface, 1, 16.46f, f3004m, 2102);
        this.f3016k = com.startapp.android.publish.p022h.StartAppSDK.m2971a(getContext(), this.f3015j, typeface, 1, 12.12f, f3005n, 2107);
        this.f3015j.setText("Loading...");
        this.f3006a = new RelativeLayout(getContext());
        this.f3006a.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.f3006a.addView(this.f3015j, com.startapp.android.publish.p022h.StartAppSDK.m2969a(getContext(), new int[]{0, 0, 0, 0}, new int[0]));
        this.f3006a.addView(this.f3016k, com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{0, 0, 0, 0}, new int[0], 3, 2102));
        this.f3011f = com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 14, 22, "back_.png");
        this.f3013h = com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 14, 22, "back_dark.png");
        this.f3012g = com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 14, 22, "forward_.png");
        this.f3014i = com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 14, 22, "forward_dark.png");
        this.f3007b = com.startapp.android.publish.p022h.StartAppSDK.m2968a(getContext(), this.f3007b, com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 23, 23, "x_dark.png"), 2103);
        this.f3009d = com.startapp.android.publish.p022h.StartAppSDK.m2968a(getContext(), this.f3009d, com.startapp.android.publish.p022h.StartAppSDK.m2967a(getContext(), 28, 28, "browser_icon_dark.png"), 2104);
        this.f3010e = com.startapp.android.publish.p022h.StartAppSDK.m2968a(getContext(), this.f3010e, this.f3011f, 2105);
        this.f3008c = com.startapp.android.publish.p022h.StartAppSDK.m2968a(getContext(), this.f3008c, this.f3012g, 2106);
        int a = com.startapp.android.publish.p022h.StartAppSDK.m2966a(getContext(), 10);
        this.f3008c.setPadding(a, a, a, a);
        this.f3010e.setPadding(a, a, a, a);
        addView(this.f3007b, com.startapp.android.publish.p022h.StartAppSDK.m2969a(getContext(), new int[]{0, 0, 16, 0}, new int[]{15, 11}));
        addView(this.f3009d, com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{0, 0, 17, 0}, new int[]{15}, 0, 2103));
        addView(this.f3006a, com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{16, 6, 16, 0}, new int[]{9}, 0, 2104));
    }

    public void m3063a(WebView webView) {
        if (this.f3017l.booleanValue()) {
            m3065b(webView);
        } else if (webView.canGoBack()) {
            m3061d();
            this.f3017l = Boolean.valueOf(true);
        }
    }

    void m3065b(WebView webView) {
        if (webView.canGoBack()) {
            this.f3010e.setImageBitmap(this.f3013h);
        } else {
            this.f3010e.setImageBitmap(this.f3011f);
        }
        if (webView.canGoForward()) {
            this.f3008c.setImageBitmap(this.f3014i);
        } else {
            this.f3008c.setImageBitmap(this.f3012g);
        }
        if (webView.getTitle() != null) {
            this.f3015j.setText(webView.getTitle());
        }
    }

    public TextView getUrlTxt() {
        return this.f3016k;
    }

    public TextView getTitleTxt() {
        return this.f3015j;
    }

    public void setButtonsListener(OnClickListener listener) {
        this.f3007b.setOnClickListener(listener);
        this.f3010e.setOnClickListener(listener);
        this.f3008c.setOnClickListener(listener);
        this.f3009d.setOnClickListener(listener);
    }

    private void m3061d() {
        this.f3010e.setImageBitmap(this.f3013h);
        addView(this.f3010e, com.startapp.android.publish.p022h.StartAppSDK.m2969a(getContext(), new int[]{6, 0, 0, 0}, new int[]{15, 9}));
        addView(this.f3008c, com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{9, 0, 0, 0}, new int[]{15}, 1, 2105));
        removeView(this.f3006a);
        this.f3006a.removeView(this.f3016k);
        this.f3006a.removeView(this.f3015j);
        this.f3006a.addView(this.f3015j, com.startapp.android.publish.p022h.StartAppSDK.m2969a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}));
        this.f3006a.addView(this.f3016k, com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}, 3, 2102));
        ViewGroup.LayoutParams a = com.startapp.android.publish.p022h.StartAppSDK.m2970a(getContext(), new int[]{16, 0, 16, 0}, new int[]{15}, 1, 2106);
        a.addRule(0, 2104);
        addView(this.f3006a, a);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3003a = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(savedState.f3003a);
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(savedState.f3003a);
        }
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    public void m3066c() {
        if (VERSION.SDK_INT < 11) {
            ((BitmapDrawable) this.f3007b.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f3009d.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f3010e.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f3008c.getDrawable()).getBitmap().recycle();
        }
        this.f3011f = null;
        this.f3013h = null;
        this.f3012g = null;
        this.f3014i = null;
    }
}
