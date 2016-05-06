package com.jirbo.adcolony;

public class AdColonyVideoAd extends AdColonyInterstitialAd {
    public AdColonyVideoAd(String zone_id) {
        super(zone_id);
    }

    public AdColonyVideoAd withListener(AdColonyAdListener listener) {
        this.y = listener;
        return this;
    }
}
