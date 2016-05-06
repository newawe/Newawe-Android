package com.Newawe.ads.behavior.loaderBehaviors;

import com.Newawe.ads.AdsLoader;

public class LoaderHideTimeoutBehavior extends LoaderBehavior {
    float _timeOut;

    public LoaderHideTimeoutBehavior(float timeout) {
        this._timeOut = timeout;
    }

    public void visit(AdsLoader loader) {
        loader.setHideTimeout(this._timeOut);
    }
}
