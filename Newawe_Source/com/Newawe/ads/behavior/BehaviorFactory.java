package com.Newawe.ads.behavior;

import com.Newawe.ads.behavior.activityBehaviors.AdsSleepBehavior;
import com.Newawe.ads.behavior.bannerBehaviors.BannerFullScreenBehavior;
import com.Newawe.ads.behavior.bannerBehaviors.BannerHeightBehavior;
import com.Newawe.ads.behavior.bannerBehaviors.BannerWidthBehavior;
import com.Newawe.ads.behavior.loaderBehaviors.LoaderClickBehavior;
import com.Newawe.ads.behavior.loaderBehaviors.LoaderHideTimeoutBehavior;
import com.Newawe.ads.behavior.loaderBehaviors.LoaderRefreshTimeoutBehavior;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BehaviorFactory {
    public final String BANNER_HEIGHT_HEADER;
    public final String BANNER_WIDTH_HEADER;
    public final String CLICK_BEHAVIOR_HEADER;
    public final String HIDE_TIMEOUT_HEADER;
    public final String INFINITY_VALUE;
    public final String IS_FULLSCREEN_HEADER;
    private final String[] POSTLOAD_BEHAVIORS;
    private final String[] PRELOAD_BEHAVIORS;
    public final String REFRESH_TIMEOUT_HEADER;
    public final String REMAIN_ON_SCREEN_VALUE;
    public final String SLEEP_TIMEOUT_HEADER;

    public enum ClickBehavior {
        HIDE,
        REMAIN_ON_SCREEN
    }

    public BehaviorFactory() {
        this.SLEEP_TIMEOUT_HEADER = "APPAD-SleepTimeout";
        this.HIDE_TIMEOUT_HEADER = "APPAD-HideTimeout";
        this.BANNER_WIDTH_HEADER = "APPAD-BannerWidth";
        this.BANNER_HEIGHT_HEADER = "APPAD-BannerHeight";
        this.IS_FULLSCREEN_HEADER = "APPAD-IsFullScreen";
        this.CLICK_BEHAVIOR_HEADER = "APPAD-ClickBehaviour";
        this.REFRESH_TIMEOUT_HEADER = "APPAD-RefreshTimeout";
        this.INFINITY_VALUE = "infinity";
        this.REMAIN_ON_SCREEN_VALUE = "remainOnScreen";
        this.PRELOAD_BEHAVIORS = new String[]{"APPAD-SleepTimeout"};
        this.POSTLOAD_BEHAVIORS = new String[]{"APPAD-HideTimeout", "APPAD-BannerWidth", "APPAD-BannerHeight", "APPAD-IsFullScreen", "APPAD-ClickBehaviour", "APPAD-RefreshTimeout"};
    }

    public List<BehaviorVisitor> createPreloadBehaviors(Map<String, List<String>> responseHeaders) {
        return _createBehaviors(this.PRELOAD_BEHAVIORS, responseHeaders);
    }

    public List<BehaviorVisitor> createPostloadBehaviors(Map<String, List<String>> responseHeaders) {
        return _createBehaviors(this.POSTLOAD_BEHAVIORS, responseHeaders);
    }

    private List<BehaviorVisitor> _createBehaviors(String[] types, Map<String, List<String>> responseHeaders) {
        ArrayList<BehaviorVisitor> resultSet = new ArrayList();
        if (responseHeaders != null) {
            for (Entry<String, List<String>> element : responseHeaders.entrySet()) {
                String key = (String) element.getKey();
                if (key != null && _isInArray(types, key)) {
                    BehaviorVisitor visitor = _createVisitor(element);
                    if (visitor != null) {
                        resultSet.add(visitor);
                    }
                }
            }
        }
        return resultSet;
    }

    private boolean _isInArray(String[] haystack, String needle) {
        for (String s : haystack) {
            if (s.toLowerCase().equals(needle.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private BehaviorVisitor _createVisitor(Entry<String, List<String>> header) {
        try {
            String headerKey = ((String) header.getKey()).toLowerCase();
            String headerValue = ((String) ((List) header.getValue()).get(0)).toLowerCase();
            if (headerKey.equalsIgnoreCase("APPAD-SleepTimeout")) {
                float timeOut = Float.POSITIVE_INFINITY;
                if (!headerValue.equalsIgnoreCase("infinity")) {
                    timeOut = Float.parseFloat(headerValue);
                }
                return new AdsSleepBehavior(timeOut);
            } else if (headerKey.equalsIgnoreCase("APPAD-HideTimeout")) {
                return new LoaderHideTimeoutBehavior(Float.parseFloat(headerValue));
            } else {
                if (headerKey.equalsIgnoreCase("APPAD-BannerWidth")) {
                    return new BannerWidthBehavior(Integer.parseInt(headerValue));
                }
                if (headerKey.equalsIgnoreCase("APPAD-BannerHeight")) {
                    return new BannerHeightBehavior(Integer.parseInt(headerValue));
                }
                if (headerKey.equalsIgnoreCase("APPAD-IsFullScreen")) {
                    return new BannerFullScreenBehavior(true);
                }
                if (headerKey.equalsIgnoreCase("APPAD-ClickBehaviour")) {
                    ClickBehavior clickBehavior = ClickBehavior.HIDE;
                    if (headerValue.equalsIgnoreCase("remainOnScreen")) {
                        clickBehavior = ClickBehavior.REMAIN_ON_SCREEN;
                    }
                    return new LoaderClickBehavior(clickBehavior);
                } else if (headerKey.equalsIgnoreCase("APPAD-RefreshTimeout")) {
                    return new LoaderRefreshTimeoutBehavior(Float.parseFloat(headerValue));
                } else {
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
