package com.inmobi.rendering.mraid;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderView;

/* renamed from: com.inmobi.rendering.mraid.k */
public final class MraidResizeProcessor {
    private static final String f1663a;
    private RenderView f1664b;
    private ViewGroup f1665c;
    private int f1666d;

    /* renamed from: com.inmobi.rendering.mraid.k.1 */
    class MraidResizeProcessor implements OnClickListener {
        final /* synthetic */ MraidResizeProcessor f1662a;

        MraidResizeProcessor(MraidResizeProcessor mraidResizeProcessor) {
            this.f1662a = mraidResizeProcessor;
        }

        public void onClick(View view) {
            this.f1662a.f1664b.m1677j();
        }
    }

    static {
        f1663a = MraidResizeProcessor.class.getSimpleName();
    }

    public MraidResizeProcessor(RenderView renderView) {
        this.f1664b = renderView;
    }

    public void m1830a() {
        if (this.f1665c == null) {
            this.f1665c = (ViewGroup) this.f1664b.getParent();
            this.f1666d = this.f1665c.indexOfChild(this.f1664b);
        }
        ResizeProperties resizeProperties = this.f1664b.getResizeProperties();
        m1829c();
        m1828a(resizeProperties);
    }

    private void m1829c() {
        View frameLayout = new FrameLayout(this.f1664b.getContext());
        LayoutParams layoutParams = new LayoutParams(this.f1664b.getWidth(), this.f1664b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.f1665c.addView(frameLayout, this.f1666d, layoutParams);
        this.f1665c.removeView(this.f1664b);
    }

    private void m1828a(ResizeProperties resizeProperties) {
        float c = DisplayInfo.m1481a().m1497c();
        int i = (int) ((((float) resizeProperties.f1672b) * c) + 0.5f);
        int i2 = (int) ((c * ((float) resizeProperties.f1673c)) + 0.5f);
        FrameLayout frameLayout = (FrameLayout) this.f1665c.getRootView().findViewById(16908290);
        View frameLayout2 = new FrameLayout(this.f1664b.getContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ViewGroup relativeLayout = new RelativeLayout(this.f1664b.getContext());
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i, i2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i, i2);
        frameLayout2.setId(65534);
        if (this.f1664b.getParent() != null) {
            ((ViewGroup) this.f1664b.getParent()).removeAllViews();
        }
        relativeLayout.addView(this.f1664b, layoutParams3);
        m1826a(relativeLayout, resizeProperties.f1671a);
        frameLayout2.addView(relativeLayout, layoutParams2);
        frameLayout.addView(frameLayout2, layoutParams);
        m1827a(frameLayout, frameLayout2, resizeProperties);
        frameLayout2.setBackgroundColor(0);
    }

    private void m1826a(ViewGroup viewGroup, String str) {
        float c = DisplayInfo.m1481a().m1497c();
        View customView = new CustomView(this.f1664b.getContext(), c, SwitchIconType.CLOSE_TRANSPARENT);
        customView.setId(65531);
        customView.setOnClickListener(new MraidResizeProcessor(this));
        viewGroup.addView(customView, m1823a(str, c));
    }

    private RelativeLayout.LayoutParams m1823a(String str, float f) {
        String a = m1825a(str);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (50.0f * f), (int) (50.0f * f));
        if (a.equals("top-right") || a.equals("bottom-right")) {
            layoutParams.addRule(11);
        }
        if (a.equals("bottom-right") || a.equals("bottom-left") || a.equals("bottom-center")) {
            layoutParams.addRule(12);
            layoutParams.addRule(4);
        }
        if (a.equals("bottom-center") || a.equals("top-center") || a.equals("center")) {
            layoutParams.addRule(13);
        }
        if (a.equals("top-center")) {
            layoutParams.addRule(10);
        }
        return layoutParams;
    }

    private String m1825a(String str) {
        if (str == null || str.length() == 0) {
            return "top-right";
        }
        if (str.equals("top-left") || str.equals("top-right") || str.equals("bottom-left") || str.equals("bottom-right") || str.equals("top-center") || str.equals("bottom-center") || str.equals("center")) {
            return str;
        }
        return "top-right";
    }

    private void m1827a(FrameLayout frameLayout, FrameLayout frameLayout2, ResizeProperties resizeProperties) {
        float c = DisplayInfo.m1481a().m1497c();
        int i = (int) ((((float) resizeProperties.f1672b) * c) + 0.5f);
        int i2 = (int) ((((float) resizeProperties.f1673c) * c) + 0.5f);
        int i3 = (int) ((((float) resizeProperties.f1674d) * c) + 0.5f);
        int i4 = (int) ((c * ((float) resizeProperties.f1675e)) + 0.5f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.f1665c.getLocationOnScreen(iArr);
        frameLayout.getLocationOnScreen(iArr2);
        iArr[1] = iArr[1] - iArr2[1];
        iArr[0] = iArr[0] - iArr2[0];
        iArr[0] = i3 + iArr[0];
        iArr[1] = i4 + iArr[1];
        if (!resizeProperties.f1676f) {
            if (i > frameLayout.getWidth() - iArr[0]) {
                iArr[0] = frameLayout.getWidth() - i;
            }
            if (i2 > frameLayout.getHeight() - iArr[1]) {
                iArr[1] = frameLayout.getHeight() - i2;
            }
            if (iArr[0] < 0) {
                iArr[0] = 0;
            }
            if (iArr[1] < 0) {
                iArr[1] = 0;
            }
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        layoutParams.gravity = GravityCompat.START;
        frameLayout2.setLayoutParams(layoutParams);
    }

    public void m1831b() {
        ViewGroup viewGroup = (ViewGroup) this.f1664b.getParent();
        View findViewById = viewGroup.getRootView().findViewById(65534);
        View findViewById2 = this.f1665c.getRootView().findViewById(SupportMenu.USER_MASK);
        ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        ((ViewGroup) findViewById2.getParent()).removeView(findViewById2);
        viewGroup.removeView(this.f1664b);
        this.f1665c.addView(this.f1664b, this.f1666d, new RelativeLayout.LayoutParams(this.f1665c.getWidth(), this.f1665c.getHeight()));
        this.f1664b.m1680l();
    }
}
