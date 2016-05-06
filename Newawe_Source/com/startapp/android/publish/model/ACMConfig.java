package com.startapp.android.publish.model;

import com.startapp.android.publish.StartAppAd.AdMode;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class ACMConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private long adCacheTTL;
    private Set<AdMode> autoLoad;
    private boolean localCache;

    public ACMConfig() {
        this.adCacheTTL = 172800;
        this.autoLoad = EnumSet.of(AdMode.FULLPAGE);
        this.localCache = true;
    }

    public long getAdCacheTtl() {
        return TimeUnit.SECONDS.toMillis(this.adCacheTTL);
    }

    public Set<AdMode> getAutoLoad() {
        return this.autoLoad;
    }

    public boolean isLocalCache() {
        return this.localCache;
    }
}
