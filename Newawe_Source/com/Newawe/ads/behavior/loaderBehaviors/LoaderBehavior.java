package com.Newawe.ads.behavior.loaderBehaviors;

import com.Newawe.ads.AdsLoader;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorVisitor;

public abstract class LoaderBehavior implements BehaviorVisitor {
    abstract void visit(AdsLoader adsLoader);

    public void visit(BehaviorAcceptor loader) {
        if (loader instanceof AdsLoader) {
            visit((AdsLoader) loader);
        }
    }
}
