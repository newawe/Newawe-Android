package com.Newawe.controllers;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.ui.views.TabFragment;
import com.astuetz.PagerSlidingTabStrip;

public class TabsController implements ITabsController {
    private final int OFFSCREEN_TABS_LIMIT;
    private int _selectedPosition;
    private TabsAdapter _tabsAdapter;
    public SimpleOnPageChangeListener onPageChangeListener;

    /* renamed from: com.Newawe.controllers.TabsController.1 */
    class C13011 extends SimpleOnPageChangeListener {
        C13011() {
        }

        public void onPageSelected(int position) {
            TabsController.this._selectedPosition = position;
            TabsController.this.loadDefaultUrlForPosition(position);
        }
    }

    public TabsController() {
        this._selectedPosition = 0;
        this.OFFSCREEN_TABS_LIMIT = 10;
        this.onPageChangeListener = new C13011();
    }

    public void initWithTabs(WidgetsController widgetsController) {
        try {
            Activity activity = Factory.getInstance().getMainNavigationActivity();
            ViewPager viewPager = (ViewPager) activity.findViewById(C0186R.id.tabcontents_panel);
            viewPager.setOffscreenPageLimit(10);
            PagerSlidingTabStrip tagPanel = (PagerSlidingTabStrip) activity.findViewById(C0186R.id.tabtags_panel);
            viewPager.addOnPageChangeListener(this.onPageChangeListener);
            this._tabsAdapter = new TabsAdapter();
            viewPager.setAdapter(this._tabsAdapter);
            this._selectedPosition = viewPager.getCurrentItem();
            tagPanel.setViewPager(viewPager);
            ViewCompat.setElevation(tagPanel, 16.0f);
            int count = widgetsController.tabsCount();
            if (count == 1) {
                tagPanel.setVisibility(8);
            } else if (count == 0) {
                tagPanel.setVisibility(8);
            }
        } catch (Exception e) {
            Log.e("initWithTabs Error", e.getMessage());
            System.out.println("initWithTabs Error" + e.getMessage());
        }
    }

    public void loadDefaultUrlForPosition(int position) {
        if (!getSelectedTab().getWebContent().isInitialized()) {
            getSelectedTab().getWebContent().loadDefaultUrl();
        }
    }

    public void setBannerInjectionJs(String jsCode) {
        this._tabsAdapter.setInjectionJs(jsCode);
    }

    public WebContentController getSelectedTab() {
        TabFragment fragment = this._tabsAdapter.getFragmentByPosition(this._selectedPosition);
        if (fragment == null) {
            return null;
        }
        return (WebContentController) fragment.getContentController();
    }

    public boolean onBackKeyDown() {
        WebContentController controller = getSelectedTab();
        if (controller != null) {
            return controller.onBackKeyDown();
        }
        return false;
    }

    public void destroy() {
        for (int i = 0; i < this._tabsAdapter.getCount(); i++) {
            TabFragment fragment = this._tabsAdapter.getFragmentByPosition(i);
            if (!(fragment == null || fragment.getContentController() == null)) {
                fragment.getContentController().destroy();
            }
        }
    }
}
