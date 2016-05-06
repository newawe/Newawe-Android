package com.Newawe.ads.behavior.bannerBehaviors;

import com.Newawe.ads.BottomBannerLayout;

public class BannerHeightBehavior extends BannerLayoutBehavior {
    private int _height;

    public BannerHeightBehavior(int h) {
        this._height = h;
    }

    public void visit(BottomBannerLayout layout) {
        layout.setHeight(this._height);
    }
}
