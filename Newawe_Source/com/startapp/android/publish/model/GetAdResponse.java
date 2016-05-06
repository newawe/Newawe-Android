package com.startapp.android.publish.model;

import com.startapp.android.publish.adinformation.StartAppSDK;
import java.util.List;

/* compiled from: StartAppSDK */
public class GetAdResponse extends BaseResponse {
    private static final long serialVersionUID = 1;
    private StartAppSDK adInfoOverrides;
    private List<AdDetails> adsDetails;
    private boolean inAppBrowser;
    private inAppBrowserPreLoad inAppBrowserPreLoad;
    private String productId;
    private String publisherId;

    /* compiled from: StartAppSDK */
    public enum ResponseType {
        HTML,
        JSON
    }

    /* compiled from: StartAppSDK */
    private enum inAppBrowserPreLoad {
        DISABLED,
        CONTENT,
        FULL
    }

    public GetAdResponse() {
        this.adInfoOverrides = StartAppSDK.m2551a();
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<AdDetails> getAdsDetails() {
        return this.adsDetails;
    }

    public void setAdsDetails(List<AdDetails> adsDetails) {
        this.adsDetails = adsDetails;
    }

    public StartAppSDK getAdInfoOverride() {
        return this.adInfoOverrides;
    }

    public boolean isInAppBrowser() {
        return this.inAppBrowser;
    }

    public void setInAppBrowser(boolean inAppBrowser) {
        this.inAppBrowser = inAppBrowser;
    }

    public inAppBrowserPreLoad getInAppBrowserPreLoad() {
        return this.inAppBrowserPreLoad;
    }

    public void setInAppBrowserPreLoad(inAppBrowserPreLoad inAppBrowserPreLoad) {
        this.inAppBrowserPreLoad = inAppBrowserPreLoad;
    }
}
