package com.Newawe.ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Newawe.Factory;
import com.Newawe.controllers.ITabContentController;
import com.Newawe.controllers.WebContentController;

public class TabFragment extends Fragment {
    private static final String ARGUMENT_INJECTION_JS = "INJECTION_JS";
    private static final String ARGUMENT_TAB_ID = "TAB_ID";
    private ITabContentController _tabContentController;

    public static TabFragment newInstance(String tabId, String injectionJs) {
        TabFragment tabFragment = new TabFragment();
        tabFragment.setRetainInstance(false);
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TAB_ID, tabId);
        arguments.putString(ARGUMENT_INJECTION_JS, injectionJs);
        tabFragment.setArguments(arguments);
        return tabFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._tabContentController = Factory.getInstance().getTabContentController(Factory.getInstance().getWidgetsController().getWidgetByTabId(getArguments().getString(ARGUMENT_TAB_ID)));
        ((WebContentController) this._tabContentController).setBannerInjectionJs(getArguments().getString(ARGUMENT_INJECTION_JS));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            return this._tabContentController.createTabContent(inflater, container);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ITabContentController getContentController() {
        return this._tabContentController;
    }
}
