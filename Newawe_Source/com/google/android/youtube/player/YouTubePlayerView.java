package com.google.android.youtube.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.internal.C0663n;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import com.google.android.youtube.player.internal.C0678y;
import com.google.android.youtube.player.internal.C1199b;
import com.google.android.youtube.player.internal.C1222s;
import com.google.android.youtube.player.internal.aa;
import com.google.android.youtube.player.internal.ab;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class YouTubePlayerView extends ViewGroup implements Provider {
    private final C0650a f3655a;
    private final Set<View> f3656b;
    private final C0651b f3657c;
    private C1199b f3658d;
    private C1222s f3659e;
    private View f3660f;
    private C0663n f3661g;
    private Provider f3662h;
    private Bundle f3663i;
    private OnInitializedListener f3664j;
    private boolean f3665k;
    private boolean f3666l;

    /* renamed from: com.google.android.youtube.player.YouTubePlayerView.a */
    private final class C0650a implements OnGlobalFocusChangeListener {
        final /* synthetic */ YouTubePlayerView f804a;

        private C0650a(YouTubePlayerView youTubePlayerView) {
            this.f804a = youTubePlayerView;
        }

        public final void onGlobalFocusChanged(View view, View view2) {
            if (this.f804a.f3659e != null && this.f804a.f3656b.contains(view2) && !this.f804a.f3656b.contains(view)) {
                this.f804a.f3659e.m4283g();
            }
        }
    }

    /* renamed from: com.google.android.youtube.player.YouTubePlayerView.b */
    interface C0651b {
        void m867a(YouTubePlayerView youTubePlayerView);

        void m868a(YouTubePlayerView youTubePlayerView, String str, OnInitializedListener onInitializedListener);
    }

    /* renamed from: com.google.android.youtube.player.YouTubePlayerView.1 */
    class C11951 implements C0671a {
        final /* synthetic */ Activity f3652a;
        final /* synthetic */ YouTubePlayerView f3653b;

        C11951(YouTubePlayerView youTubePlayerView, Activity activity) {
            this.f3653b = youTubePlayerView;
            this.f3652a = activity;
        }

        public final void m4125a() {
            if (this.f3653b.f3658d != null) {
                YouTubePlayerView.m4131a(this.f3653b, this.f3652a);
            }
            this.f3653b.f3658d = null;
        }

        public final void m4126b() {
            if (!(this.f3653b.f3666l || this.f3653b.f3659e == null)) {
                this.f3653b.f3659e.m4282f();
            }
            this.f3653b.f3661g.m954a();
            if (this.f3653b.indexOfChild(this.f3653b.f3661g) < 0) {
                this.f3653b.addView(this.f3653b.f3661g);
                this.f3653b.removeView(this.f3653b.f3660f);
            }
            this.f3653b.f3660f = null;
            this.f3653b.f3659e = null;
            this.f3653b.f3658d = null;
        }
    }

    /* renamed from: com.google.android.youtube.player.YouTubePlayerView.2 */
    class C11962 implements C0672b {
        final /* synthetic */ YouTubePlayerView f3654a;

        C11962(YouTubePlayerView youTubePlayerView) {
            this.f3654a = youTubePlayerView;
        }

        public final void m4127a(YouTubeInitializationResult youTubeInitializationResult) {
            this.f3654a.m4130a(youTubeInitializationResult);
            this.f3654a.f3658d = null;
        }
    }

    public YouTubePlayerView(Context context) {
        this(context, null);
    }

    public YouTubePlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YouTubePlayerView(Context context, AttributeSet attributeSet, int i) {
        if (context instanceof YouTubeBaseActivity) {
            this(context, attributeSet, i, ((YouTubeBaseActivity) context).m860a());
            return;
        }
        throw new IllegalStateException("A YouTubePlayerView can only be created with an Activity  which extends YouTubeBaseActivity as its context.");
    }

    YouTubePlayerView(Context context, AttributeSet attributeSet, int i, C0651b c0651b) {
        super((Context) ab.m880a((Object) context, (Object) "context cannot be null"), attributeSet, i);
        this.f3657c = (C0651b) ab.m880a((Object) c0651b, (Object) "listener cannot be null");
        if (getBackground() == null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        setClipToPadding(false);
        this.f3661g = new C0663n(context);
        requestTransparentRegion(this.f3661g);
        addView(this.f3661g);
        this.f3656b = new HashSet();
        this.f3655a = new C0650a();
    }

    private void m4129a(View view) {
        Object obj = (view == this.f3661g || (this.f3659e != null && view == this.f3660f)) ? 1 : null;
        if (obj == null) {
            throw new UnsupportedOperationException("No views can be added on top of the player");
        }
    }

    private void m4130a(YouTubeInitializationResult youTubeInitializationResult) {
        this.f3659e = null;
        this.f3661g.m956c();
        if (this.f3664j != null) {
            this.f3664j.onInitializationFailure(this.f3662h, youTubeInitializationResult);
            this.f3664j = null;
        }
    }

    static /* synthetic */ void m4131a(YouTubePlayerView youTubePlayerView, Activity activity) {
        try {
            youTubePlayerView.f3659e = new C1222s(youTubePlayerView.f3658d, aa.m874a().m878a(activity, youTubePlayerView.f3658d, youTubePlayerView.f3665k));
            youTubePlayerView.f3660f = youTubePlayerView.f3659e.m4271a();
            youTubePlayerView.addView(youTubePlayerView.f3660f);
            youTubePlayerView.removeView(youTubePlayerView.f3661g);
            youTubePlayerView.f3657c.m867a(youTubePlayerView);
            if (youTubePlayerView.f3664j != null) {
                boolean z = false;
                if (youTubePlayerView.f3663i != null) {
                    z = youTubePlayerView.f3659e.m4275a(youTubePlayerView.f3663i);
                    youTubePlayerView.f3663i = null;
                }
                youTubePlayerView.f3664j.onInitializationSuccess(youTubePlayerView.f3662h, youTubePlayerView.f3659e, z);
                youTubePlayerView.f3664j = null;
            }
        } catch (Throwable e) {
            C0678y.m970a("Error creating YouTubePlayerView", e);
            youTubePlayerView.m4130a(YouTubeInitializationResult.INTERNAL_ERROR);
        }
    }

    final void m4141a() {
        if (this.f3659e != null) {
            this.f3659e.m4276b();
        }
    }

    final void m4142a(Activity activity, Provider provider, String str, OnInitializedListener onInitializedListener, Bundle bundle) {
        if (this.f3659e == null && this.f3664j == null) {
            ab.m880a((Object) activity, (Object) "activity cannot be null");
            this.f3662h = (Provider) ab.m880a((Object) provider, (Object) "provider cannot be null");
            this.f3664j = (OnInitializedListener) ab.m880a((Object) onInitializedListener, (Object) "listener cannot be null");
            this.f3663i = bundle;
            this.f3661g.m955b();
            this.f3658d = aa.m874a().m877a(getContext(), str, new C11951(this, activity), new C11962(this));
            this.f3658d.m964e();
        }
    }

    final void m4143a(boolean z) {
        if (!z || VERSION.SDK_INT >= 14) {
            this.f3665k = z;
            return;
        }
        C0678y.m971a("Could not enable TextureView because API level is lower than 14", new Object[0]);
        this.f3665k = false;
    }

    public final void addFocusables(ArrayList<View> arrayList, int i) {
        Collection arrayList2 = new ArrayList();
        super.addFocusables(arrayList2, i);
        arrayList.addAll(arrayList2);
        this.f3656b.clear();
        this.f3656b.addAll(arrayList2);
    }

    public final void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        Collection arrayList2 = new ArrayList();
        super.addFocusables(arrayList2, i, i2);
        arrayList.addAll(arrayList2);
        this.f3656b.clear();
        this.f3656b.addAll(arrayList2);
    }

    public final void addView(View view) {
        m4129a(view);
        super.addView(view);
    }

    public final void addView(View view, int i) {
        m4129a(view);
        super.addView(view, i);
    }

    public final void addView(View view, int i, int i2) {
        m4129a(view);
        super.addView(view, i, i2);
    }

    public final void addView(View view, int i, LayoutParams layoutParams) {
        m4129a(view);
        super.addView(view, i, layoutParams);
    }

    public final void addView(View view, LayoutParams layoutParams) {
        m4129a(view);
        super.addView(view, layoutParams);
    }

    final void m4144b() {
        if (this.f3659e != null) {
            this.f3659e.m4279c();
        }
    }

    final void m4145b(boolean z) {
        if (this.f3659e != null) {
            this.f3659e.m4277b(z);
            m4147c(z);
        }
    }

    final void m4146c() {
        if (this.f3659e != null) {
            this.f3659e.m4280d();
        }
    }

    final void m4147c(boolean z) {
        this.f3666l = true;
        if (this.f3659e != null) {
            this.f3659e.m4273a(z);
        }
    }

    public final void clearChildFocus(View view) {
        if (hasFocusable()) {
            requestFocus();
        } else {
            super.clearChildFocus(view);
        }
    }

    final void m4148d() {
        if (this.f3659e != null) {
            this.f3659e.m4281e();
        }
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.f3659e != null) {
            if (keyEvent.getAction() == 0) {
                return this.f3659e.m4274a(keyEvent.getKeyCode(), keyEvent) || super.dispatchKeyEvent(keyEvent);
            } else {
                if (keyEvent.getAction() == 1) {
                    return this.f3659e.m4278b(keyEvent.getKeyCode(), keyEvent) || super.dispatchKeyEvent(keyEvent);
                }
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    final Bundle m4149e() {
        return this.f3659e == null ? this.f3663i : this.f3659e.m4284h();
    }

    public final void focusableViewAvailable(View view) {
        super.focusableViewAvailable(view);
        this.f3656b.add(view);
    }

    public final void initialize(String str, OnInitializedListener onInitializedListener) {
        ab.m881a(str, (Object) "Developer key cannot be null or empty");
        this.f3657c.m868a(this, str, onInitializedListener);
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.f3655a);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f3659e != null) {
            this.f3659e.m4272a(configuration);
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.f3655a);
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            getChildAt(0).layout(0, 0, i3 - i, i4 - i2);
        }
    }

    protected final void onMeasure(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            childAt.measure(i, i2);
            setMeasuredDimension(childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            return;
        }
        setMeasuredDimension(0, 0);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public final void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        this.f3656b.add(view2);
    }

    public final void setClipToPadding(boolean z) {
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
    }
}
