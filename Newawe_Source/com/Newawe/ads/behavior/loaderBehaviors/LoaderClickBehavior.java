package com.Newawe.ads.behavior.loaderBehaviors;

import com.Newawe.ads.AdsLoader;
import com.Newawe.ads.behavior.BehaviorFactory.ClickBehavior;

public class LoaderClickBehavior extends LoaderBehavior {
    ClickBehavior _clickBehavior;

    public LoaderClickBehavior(ClickBehavior behavior) {
        this._clickBehavior = behavior;
    }

    public void visit(AdsLoader loader) {
        loader.changeClickBehavior(this._clickBehavior);
    }
}
