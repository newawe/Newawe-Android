package com.google.android.youtube.player.internal;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

/* renamed from: com.google.android.youtube.player.internal.n */
public final class C0663n extends FrameLayout {
    private final ProgressBar f819a;
    private final TextView f820b;

    public C0663n(Context context) {
        super(context, null, C0679z.m980c(context));
        C0662m c0662m = new C0662m(context);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.f819a = new ProgressBar(context);
        this.f819a.setVisibility(8);
        addView(this.f819a, new LayoutParams(-2, -2, 17));
        int i = (int) ((10.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
        this.f820b = new TextView(context);
        this.f820b.setTextAppearance(context, 16973894);
        this.f820b.setTextColor(-1);
        this.f820b.setVisibility(8);
        this.f820b.setPadding(i, i, i, i);
        this.f820b.setGravity(17);
        this.f820b.setText(c0662m.f809a);
        addView(this.f820b, new LayoutParams(-2, -2, 17));
    }

    public final void m954a() {
        this.f819a.setVisibility(8);
        this.f820b.setVisibility(8);
    }

    public final void m955b() {
        this.f819a.setVisibility(0);
        this.f820b.setVisibility(8);
    }

    public final void m956c() {
        this.f819a.setVisibility(8);
        this.f820b.setVisibility(0);
    }

    protected final void onMeasure(int i, int i2) {
        int i3 = 0;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            i3 = size;
        } else if (mode == 1073741824 || (mode == ExploreByTouchHelper.INVALID_ID && mode2 == 0)) {
            size2 = (int) (((float) size) / 1.777f);
            i3 = size;
        } else if (mode2 == 1073741824 || (mode2 == ExploreByTouchHelper.INVALID_ID && mode == 0)) {
            i3 = (int) (((float) size2) * 1.777f);
        } else if (mode != ExploreByTouchHelper.INVALID_ID || mode2 != ExploreByTouchHelper.INVALID_ID) {
            size2 = 0;
        } else if (((float) size2) < ((float) size) / 1.777f) {
            i3 = (int) (((float) size2) * 1.777f);
        } else {
            size2 = (int) (((float) size) / 1.777f);
            i3 = size;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(C0663n.resolveSize(i3, i), 1073741824), MeasureSpec.makeMeasureSpec(C0663n.resolveSize(size2, i2), 1073741824));
    }
}
