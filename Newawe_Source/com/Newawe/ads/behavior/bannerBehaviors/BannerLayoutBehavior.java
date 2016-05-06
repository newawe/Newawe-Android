package com.Newawe.ads.behavior.bannerBehaviors;

import com.Newawe.ads.BottomBannerLayout;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorVisitor;

public abstract class BannerLayoutBehavior implements BehaviorVisitor {
    abstract void visit(BottomBannerLayout bottomBannerLayout);

    public void visit(BehaviorAcceptor acceptor) {
        if (acceptor instanceof BottomBannerLayout) {
            visit((BottomBannerLayout) acceptor);
        }
    }
}
