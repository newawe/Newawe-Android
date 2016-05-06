package com.Newawe.ui.navigationdrawer;

import com.Newawe.MainNavigationActivity;

public class NavigationDrawerHolder {
    private static NavigationDrawer instance;

    static {
        instance = null;
    }

    public static NavigationDrawer getNavigationDrawer(MainNavigationActivity _activity) {
        if (instance == null) {
            instance = new NavigationDrawer(_activity);
        }
        return instance;
    }
}
