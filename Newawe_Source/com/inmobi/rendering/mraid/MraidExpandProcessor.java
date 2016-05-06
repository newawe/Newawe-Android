package com.inmobi.rendering.mraid;

import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import org.apache.http.HttpStatus;

/* renamed from: com.inmobi.rendering.mraid.h */
public final class MraidExpandProcessor {
    private static final String f1650a;
    private RenderView f1651b;
    private boolean f1652c;
    private ViewGroup f1653d;
    private int f1654e;

    static {
        f1650a = MraidExpandProcessor.class.getSimpleName();
    }

    public MraidExpandProcessor(RenderView renderView) {
        this.f1651b = renderView;
        this.f1652c = false;
    }

    public void m1813a(String str, String str2) {
        if (this.f1653d == null) {
            this.f1653d = (ViewGroup) this.f1651b.getParent();
            this.f1654e = this.f1653d.indexOfChild(this.f1651b);
        }
        if (this.f1651b == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1650a, "Please check if the MRAID processor was initialized correctly.");
            return;
        }
        int a;
        ExpandProperties expandProperties = this.f1651b.getExpandProperties();
        this.f1652c = URLUtil.isValidUrl(str2);
        if (this.f1652c) {
            RenderView renderView = new RenderView(this.f1651b.getContext(), new RenderingProperties(PlacementType.INLINE));
            renderView.m1639a(this.f1651b.getListener(), this.f1651b.getRenderingConfig(), this.f1651b.getMraidConfig());
            renderView.setOriginalRenderView(this.f1651b);
            renderView.loadUrl(str2);
            a = InMobiAdActivity.m1582a(renderView);
        } else {
            m1811b();
            a = InMobiAdActivity.m1582a(this.f1651b);
        }
        Intent intent = new Intent(this.f1651b.getContext(), InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", HttpStatus.SC_PROCESSING);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", a);
        this.f1651b.getContext().startActivity(intent);
        if (expandProperties != null) {
            InMobiAdActivity.m1583a().setUseCustomClose(this.f1651b.m1663d());
        }
    }

    private void m1811b() {
        View frameLayout = new FrameLayout(this.f1651b.getContext());
        LayoutParams layoutParams = new LayoutParams(this.f1651b.getWidth(), this.f1651b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.f1653d.addView(frameLayout, this.f1654e, layoutParams);
        this.f1653d.removeView(this.f1651b);
    }

    public void m1812a() {
        if (this.f1651b.getOriginalRenderView() == null) {
            View findViewById = this.f1653d.getRootView().findViewById(SupportMenu.USER_MASK);
            ((ViewGroup) this.f1651b.getParent()).removeView(this.f1651b);
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            this.f1653d.addView(this.f1651b, this.f1654e, new RelativeLayout.LayoutParams(this.f1653d.getWidth(), this.f1653d.getHeight()));
            this.f1651b.m1680l();
        }
    }
}
