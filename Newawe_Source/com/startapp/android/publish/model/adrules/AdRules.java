package com.startapp.android.publish.model.adrules;

import com.startapp.android.publish.model.AdPreferences.Placement;
import com.startapp.android.publish.p022h.StartAppSDK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class AdRules implements Serializable {
    private static final String TAG = "AdRules";
    private static final long serialVersionUID = 1;
    private boolean applyOnBannerRefresh;
    private Map<Placement, List<AdRule>> placements;
    private transient Set<Class<? extends AdRule>> processedRuleTypes;
    private List<AdRule> session;
    private Map<String, List<AdRule>> tags;

    public AdRules() {
        this.session = new ArrayList();
        this.placements = new HashMap();
        this.tags = new HashMap();
        this.applyOnBannerRefresh = true;
        this.processedRuleTypes = new HashSet();
    }

    public List<AdRule> getSessionRules() {
        return this.session;
    }

    public Map<Placement, List<AdRule>> getPlacementRules() {
        return this.placements;
    }

    public Map<String, List<AdRule>> getTagRules() {
        return this.tags;
    }

    public boolean isApplyOnBannerRefresh() {
        return this.applyOnBannerRefresh;
    }

    public synchronized AdRulesResult shouldDisplayAd(Placement placement, String adTag) {
        AdRulesResult processRuleList;
        String str;
        StringBuilder append;
        String str2;
        this.processedRuleTypes.clear();
        AdRulesResult processRuleList2 = processRuleList((List) this.tags.get(adTag), SessionManager.getInstance().getAdDisplayEvents(adTag), AdRuleLevel.TAG, adTag);
        if (processRuleList2.shouldDisplayAd()) {
            processRuleList2 = processRuleList((List) this.placements.get(placement), SessionManager.getInstance().getAdDisplayEvents(placement), AdRuleLevel.PLACEMENT, placement.toString());
            if (processRuleList2.shouldDisplayAd()) {
                processRuleList = processRuleList(this.session, SessionManager.getInstance().getAdDisplayEvents(), AdRuleLevel.SESSION, "session");
                str = TAG;
                append = new StringBuilder().append("shouldDisplayAd result: ").append(processRuleList.shouldDisplayAd());
                if (processRuleList.shouldDisplayAd()) {
                    str2 = " because of rule " + processRuleList.getReason();
                } else {
                    str2 = StringUtils.EMPTY;
                }
                StartAppSDK.m2928a(str, 3, append.append(str2).toString());
            }
        }
        processRuleList = processRuleList2;
        str = TAG;
        append = new StringBuilder().append("shouldDisplayAd result: ").append(processRuleList.shouldDisplayAd());
        if (processRuleList.shouldDisplayAd()) {
            str2 = " because of rule " + processRuleList.getReason();
        } else {
            str2 = StringUtils.EMPTY;
        }
        StartAppSDK.m2928a(str, 3, append.append(str2).toString());
        return processRuleList;
    }

    private AdRulesResult processRuleList(List<AdRule> adRules, List<AdDisplayEvent> adDisplayEvents, AdRuleLevel adRuleLevel, String debugInfo) {
        if (adRules == null) {
            return new AdRulesResult(true);
        }
        for (AdRule adRule : adRules) {
            if (adRule.shouldProcessEntireHierarchy() || !this.processedRuleTypes.contains(adRule.getClass())) {
                if (adRule.shouldDisplayAd(adDisplayEvents)) {
                    this.processedRuleTypes.add(adRule.getClass());
                } else {
                    return new AdRulesResult(false, adRule.getClass().getSimpleName() + "_" + adRuleLevel + (com.startapp.android.publish.StartAppSDK.DEBUG.booleanValue() ? " " + debugInfo + ":" + adRule : StringUtils.EMPTY));
                }
            }
        }
        return new AdRulesResult(true);
    }
}
