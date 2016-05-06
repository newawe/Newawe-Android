package com.Newawe.ads.behavior.loaderBehaviors;

import com.Newawe.ads.AdsLoader;

public class LoaderRefreshTimeoutBehavior extends LoaderBehavior {
    float _timeOut;

    public LoaderRefreshTimeoutBehavior(float timeout) {
        this._timeOut = timeout;
    }

    public void visit(AdsLoader loader) {
        loader.setRefreshTimeout(this._timeOut);
    }
}
