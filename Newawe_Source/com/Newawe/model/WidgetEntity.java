package com.Newawe.model;

import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.configuration.IncludeScriptConfigEntity;
import java.io.Serializable;
import java.util.ArrayList;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

public class WidgetEntity implements Serializable {
    private int _height;
    private String _id;
    private String _injectJS;
    private ArrayList<IncludeScriptConfigEntity> _injectScripts;
    private String _link;
    private LoadingCurtainType _loadingCurtainType;
    private String _name;
    private boolean _showAsTab;
    private String _tabIcon;
    private String _tabId;
    private String _tabName;
    private int _updateTime;
    private String _userAgent;
    private int _width;

    /* renamed from: com.Newawe.model.WidgetEntity.1 */
    static /* synthetic */ class C02451 {
        static final /* synthetic */ int[] $SwitchMap$com$Newawe$model$WidgetEntity$DefaultWidgetType;

        static {
            $SwitchMap$com$Newawe$model$WidgetEntity$DefaultWidgetType = new int[DefaultWidgetType.values().length];
            try {
                $SwitchMap$com$Newawe$model$WidgetEntity$DefaultWidgetType[DefaultWidgetType.PAUSED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public enum DefaultWidgetType {
        PAUSED
    }

    public enum LoadingCurtainType {
        NONE,
        DEFAULT,
        BANNER,
        CUSTOM
    }

    public boolean isShowAsTab() {
        return this._showAsTab;
    }

    public void setShowAsTab(boolean _showAsTab) {
        this._showAsTab = _showAsTab;
    }

    public WidgetEntity() {
        this._width = 0;
        this._height = 0;
        this._updateTime = 0;
        this._showAsTab = true;
    }

    public WidgetEntity(WidgetEntity wdgt) {
        this._width = 0;
        this._height = 0;
        this._updateTime = 0;
        this._showAsTab = true;
        this._id = wdgt.getId();
        this._name = wdgt.getName();
        this._link = wdgt.getLink();
        this._width = wdgt.getWidth();
        this._height = wdgt.getHeight();
        this._updateTime = wdgt.getUpdateTime();
        this._tabName = wdgt.getTabName();
        this._tabIcon = wdgt.getTabIcon();
        this._injectJS = wdgt.getInjectJS();
        this._loadingCurtainType = wdgt.getLoadingCurtainType();
        this._userAgent = wdgt.getUserAgent();
        this._showAsTab = wdgt.isShowAsTab();
    }

    public static WidgetEntity newInstance(WidgetEntity wdgt) {
        WidgetEntity tmpEntity = new WidgetEntity();
        tmpEntity._id = wdgt.getId();
        tmpEntity._name = wdgt.getName();
        tmpEntity._link = wdgt.getLink();
        tmpEntity._width = wdgt.getWidth();
        tmpEntity._height = wdgt.getHeight();
        tmpEntity._updateTime = wdgt.getUpdateTime();
        tmpEntity._tabName = wdgt.getTabName();
        tmpEntity._tabIcon = wdgt.getTabIcon();
        tmpEntity._injectJS = wdgt.getInjectJS();
        tmpEntity._loadingCurtainType = wdgt.getLoadingCurtainType();
        tmpEntity._userAgent = wdgt.getUserAgent();
        return tmpEntity;
    }

    public static WidgetEntity createDefaultWidget(DefaultWidgetType type) {
        WidgetEntity tmpEntity = new WidgetEntity();
        switch (C02451.$SwitchMap$com$Newawe$model$WidgetEntity$DefaultWidgetType[type.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                tmpEntity._id = "pausedwidget_0";
                tmpEntity._name = "PausedWidget";
                tmpEntity._link = Factory.getInstance().getMainNavigationActivity().getResources().getString(C0186R.string.pausedContentUrl) + Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName();
                tmpEntity._width = HttpStatus.SC_MULTIPLE_CHOICES;
                tmpEntity._height = HttpStatus.SC_MULTIPLE_CHOICES;
                tmpEntity._updateTime = 0;
                tmpEntity._tabName = StringUtils.EMPTY;
                tmpEntity._tabIcon = StringUtils.EMPTY;
                tmpEntity._injectJS = StringUtils.EMPTY;
                tmpEntity._loadingCurtainType = LoadingCurtainType.NONE;
                tmpEntity._userAgent = StringUtils.EMPTY;
                tmpEntity._tabId = "-1";
                return tmpEntity;
            default:
                return null;
        }
    }

    public void addScript(IncludeScriptConfigEntity includeScriptConfigEntity) {
        if (this._injectScripts == null) {
            this._injectScripts = new ArrayList();
        }
        this._injectScripts.add(includeScriptConfigEntity);
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getLink() {
        return this._link;
    }

    public void setLink(String link) {
        this._link = link;
    }

    public int getWidth() {
        return this._width;
    }

    public void setWidth(int width) {
        this._width = width;
    }

    public int getHeight() {
        return this._height;
    }

    public void setHeight(int height) {
        this._height = height;
    }

    public int getUpdateTime() {
        return this._updateTime;
    }

    public void setUpdateTime(int update) {
        this._updateTime = update;
    }

    public String getTabName() {
        return this._tabName;
    }

    public void setTabName(String tabName) {
        this._tabName = tabName;
    }

    public String getTabIcon() {
        return this._tabIcon;
    }

    public void setTabIcon(String tabIcon) {
        this._tabIcon = tabIcon;
    }

    public String getInjectJS() {
        return this._injectJS;
    }

    public ArrayList<IncludeScriptConfigEntity> getInjectScripts() {
        return this._injectScripts;
    }

    public void setInjectJS(String injectJS) {
        this._injectJS = injectJS;
    }

    public LoadingCurtainType getLoadingCurtainType() {
        return this._loadingCurtainType;
    }

    public void setLoadingCurtainType(LoadingCurtainType loadingCurtainType) {
        this._loadingCurtainType = loadingCurtainType;
    }

    public String getUserAgent() {
        return this._userAgent;
    }

    public void setUserAgent(String userAgentString) {
        this._userAgent = userAgentString;
    }

    public void setTabId(String tabId) {
        this._tabId = tabId;
    }

    public String getTabId() {
        return this._tabId;
    }
}
