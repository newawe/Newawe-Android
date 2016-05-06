package com.Newawe.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.Newawe.Factory;
import com.Newawe.ui.views.TabFragment;
import java.util.HashMap;

public class TabsAdapter extends FragmentPagerAdapter {
    private String _injectionJs;
    private HashMap<Integer, TabFragment> _tabFragments;
    private WidgetsController _widgetsController;

    public TabsAdapter() {
        super(Factory.getInstance().getMainNavigationActivity().getSupportFragmentManager());
        this._tabFragments = new HashMap();
        this._widgetsController = Factory.getInstance().getWidgetsController();
    }

    public void setInjectionJs(String js) {
        this._injectionJs = js;
        for (TabFragment fragment : this._tabFragments.values()) {
            if (!(fragment == null || fragment.getContentController() == null)) {
                ((WebContentController) fragment.getContentController()).setBannerInjectionJs(this._injectionJs);
            }
        }
    }

    public Fragment getItem(int position) {
        TabFragment fragment = TabFragment.newInstance(this._widgetsController.getTabByPosition(position).getTabId(), this._injectionJs);
        this._tabFragments.put(Integer.valueOf(position), fragment);
        return fragment;
    }

    public TabFragment getFragmentByPosition(int position) {
        return (TabFragment) this._tabFragments.get(Integer.valueOf(position));
    }

    public int getCount() {
        return this._widgetsController.tabsCount();
    }

    public CharSequence getPageTitle(int position) {
        return this._widgetsController.getTabByPosition(position).getTabName();
    }
}
