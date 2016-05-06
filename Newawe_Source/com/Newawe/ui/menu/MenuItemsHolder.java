package com.Newawe.ui.menu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity.ApplicationMode;
import com.Newawe.configuration.UrlBarMenuButton;
import com.Newawe.configuration.UrlBarMenuButton.UrlBarMenuButtonTypes;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.configuration.WebWidgetConfiguration.UrlBarStates;
import com.Newawe.ui.navigationdrawer.ListItem;
import java.util.ArrayList;
import java.util.Iterator;

public class MenuItemsHolder {
    private Menu _menu;

    public MenuItemsHolder(WebWidgetConfiguration config, Activity activity) {
        ApplicationMode mode = config.getApplicationMode();
        if (activity != null) {
            this._menu = MenuGenerator.newEmptyMenuInstance(activity);
            _init(config, mode, activity);
        }
    }

    public MenuItemsHolder(WebWidgetConfiguration config, ApplicationMode mode, Activity activity, Menu menu) {
        if (activity != null) {
            this._menu = menu;
            _init(config, mode, activity);
        }
    }

    private void _init(WebWidgetConfiguration config, ApplicationMode mode, Activity _activity) {
        _activity.getMenuInflater().inflate(C0186R.menu.webapp_menu, this._menu);
        _applyItemsVisibility(config, mode);
    }

    private void _applyItemsVisibility(WebWidgetConfiguration config, ApplicationMode mode) {
        if (!config.getUrlOverlayState().equals(UrlBarStates.DISABLED)) {
            Iterator i$ = config.getUrlBarMenuButtons().iterator();
            while (i$.hasNext()) {
                UrlBarMenuButton urlBarMenuButton = (UrlBarMenuButton) i$.next();
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.BACK)) {
                    this._menu.findItem(C0186R.id.webapp_back).setVisible(true);
                }
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.FORWARD)) {
                    this._menu.findItem(C0186R.id.webapp_forward).setVisible(true);
                }
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.REQUEST_DESKTOP)) {
                    this._menu.findItem(C0186R.id.webapp_request_desktop).setVisible(true);
                }
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.PIN_TO_DESKTOP)) {
                    this._menu.findItem(C0186R.id.webapp_pin_to_desktop).setVisible(true);
                }
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.ADD_TO_HOME)) {
                    this._menu.findItem(C0186R.id.webapp_add_to_home).setVisible(true);
                }
                if (urlBarMenuButton.getType().equals(UrlBarMenuButtonTypes.HOME)) {
                    this._menu.findItem(C0186R.id.webapp_home).setVisible(true);
                }
            }
        }
        if (mode == ApplicationMode.CUSTOM) {
            this._menu.findItem(C0186R.id.webapp_share).setVisible(false);
        } else {
            this._menu.findItem(C0186R.id.webapp_share).setVisible(true);
        }
        if (!config.getIsAboutScreenEnabled()) {
            this._menu.findItem(C0186R.id.webapp_about).setVisible(false);
        }
        this._menu.findItem(C0186R.id.webapp_rate).setVisible(config.getRateItemVisibility());
        this._menu.findItem(C0186R.id.webapp_refresh).setVisible(config.getShowRefreshMenuItem());
        this._menu.findItem(C0186R.id.webapp_exit).setVisible(config.getShowExitMenuItem());
        this._menu.findItem(C0186R.id.webapp_share).setVisible(config.getShowShareMenuItem());
        this._menu.findItem(C0186R.id.webapp_about).setVisible(config.getShowAboutMenuItem());
    }

    public ArrayList<ListItem> getAllItems() {
        ArrayList<ListItem> result = new ArrayList();
        for (int i = 0; i < this._menu.size(); i++) {
            MenuItem currentItem = this._menu.getItem(i);
            if (currentItem.getItemId() != C0186R.id.webapp_request_desktop && currentItem.isVisible()) {
                int id = currentItem.getItemId();
                result.add(new ListItem(currentItem.getTitle().toString(), MenuStructure.getIconResourceIdByItemId(id), id));
            }
        }
        return result;
    }

    public Menu getMenu() {
        return this._menu;
    }
}
