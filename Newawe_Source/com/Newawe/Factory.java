package com.Newawe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Newawe.controllers.ITabContentController;
import com.Newawe.controllers.ITabsController;
import com.Newawe.controllers.TabsController;
import com.Newawe.controllers.WebContentController;
import com.Newawe.controllers.WidgetsController;
import com.Newawe.model.WidgetEntity;
import com.Newawe.storage.BookmarksManager;
import com.Newawe.ui.navigationwidget.INavigationWidget;
import com.Newawe.ui.views.TabContent.ContentType;
import java.util.HashMap;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class Factory {
    private static Factory _instance;
    private MainNavigationActivity _activity;
    private BookmarksManager _homePageManager;
    private INavigationWidget _navigationWidget;
    private ITabsController _tabsController;
    private WidgetsController _widgetsController;
    private HashMap<String, BookmarksManager> bookmarkManagers;

    /* renamed from: com.Newawe.Factory.1 */
    static /* synthetic */ class C01741 {
        static final /* synthetic */ int[] $SwitchMap$com$Newawe$ui$views$TabContent$ContentType;

        static {
            $SwitchMap$com$Newawe$ui$views$TabContent$ContentType = new int[ContentType.values().length];
            try {
                $SwitchMap$com$Newawe$ui$views$TabContent$ContentType[ContentType.WEB.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    static {
        _instance = null;
    }

    private Factory() {
        this._navigationWidget = null;
        this.bookmarkManagers = new HashMap();
        this._widgetsController = new WidgetsController();
        this._tabsController = new TabsController();
    }

    public static Factory getInstance() {
        if (_instance == null) {
            _instance = new Factory();
        }
        return _instance;
    }

    public void destroy() {
        this._widgetsController = null;
        this._tabsController.destroy();
        this._tabsController = null;
        _instance = null;
    }

    public void Init(MainNavigationActivity activity) {
        this._activity = activity;
    }

    public void setNavigationWidget(INavigationWidget widget) {
        this._navigationWidget = widget;
    }

    public INavigationWidget getNavigationWidget() {
        return this._navigationWidget;
    }

    public MainNavigationActivity getMainNavigationActivity() {
        return this._activity;
    }

    public WidgetsController getWidgetsController() {
        return this._widgetsController;
    }

    public void setWidgetsController(WidgetsController widgetsController) {
        this._widgetsController = widgetsController;
    }

    public ITabsController getTabsController() {
        return this._tabsController;
    }

    public View getTabContent(ContentType type, LayoutInflater inflater, ViewGroup container) throws Exception {
        switch (C01741.$SwitchMap$com$Newawe$ui$views$TabContent$ContentType[type.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return inflater.inflate(C0186R.layout.web_content, container, false);
            default:
                throw new Exception("Unknown Content Type");
        }
    }

    public ITabContentController getTabContentController(WidgetEntity wdgtInfo) {
        ITabContentController tccRes = new WebContentController(wdgtInfo);
        tccRes.setMainNavigationActivity(this._activity);
        return tccRes;
    }

    public BookmarksManager getHomePageManager() {
        if (this._homePageManager == null) {
            this._homePageManager = new BookmarksManager("Homepage", this._activity);
        }
        return this._homePageManager;
    }

    public BookmarksManager getBookmarkManager(String BookmarkPage) {
        if (!this.bookmarkManagers.containsKey(BookmarkPage)) {
            this.bookmarkManagers.put(BookmarkPage, new BookmarksManager(BookmarkPage, this._activity));
        }
        return (BookmarksManager) this.bookmarkManagers.get(BookmarkPage);
    }
}
