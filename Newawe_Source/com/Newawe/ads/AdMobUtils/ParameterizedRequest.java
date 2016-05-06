package com.Newawe.ads.AdMobUtils;

import android.location.Location;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;

public class ParameterizedRequest {
    private static final String LOCATION_PROVIDER = "Appsgeyser";
    private static final String TAG;
    private AdRequest _request;

    static {
        TAG = ParameterizedRequest.class.getSimpleName();
    }

    public ParameterizedRequest(AdMobParameters adMobParameters) {
        Builder requestBuilder = new Builder();
        if (adMobParameters == null) {
            adMobParameters = new AdMobParameters();
        }
        if (1 == adMobParameters.getGender() || 2 == adMobParameters.getGender() || adMobParameters.getGender() == 0) {
            requestBuilder.setGender(adMobParameters.getGender());
        }
        if (adMobParameters.getBirthday() != null) {
            requestBuilder.setBirthday(adMobParameters.getBirthday());
        }
        if (adMobParameters.getKeywords() != null) {
            for (String keyword : adMobParameters.getKeywords()) {
                requestBuilder.addKeyword(keyword);
            }
        }
        if (!(adMobParameters.getLatitude() == null || adMobParameters.getLongtitude() == null)) {
            Location location = new Location(LOCATION_PROVIDER);
            location.setLatitude(adMobParameters.getLatitude().doubleValue());
            location.setLongitude(adMobParameters.getLongtitude().doubleValue());
            requestBuilder.setLocation(location);
        }
        this._request = requestBuilder.build();
    }

    public AdRequest getRequest() {
        return this._request;
    }

    public void setRequest(AdRequest reques) {
        this._request = reques;
    }
}
