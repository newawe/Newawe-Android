package com.Newawe.ui.navigationdrawer;

import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;

public class ListItem implements IOptionsMenuItemSelectedListener {
    int _iconId;
    int _id;
    String _title;

    /* renamed from: com.Newawe.ui.navigationdrawer.ListItem.1 */
    class C02651 implements Runnable {
        final /* synthetic */ MainNavigationActivity val$activity;

        C02651(MainNavigationActivity mainNavigationActivity) {
            this.val$activity = mainNavigationActivity;
        }

        public void run() {
            this.val$activity.onOptionsItemSelected(ListItem.this._id, null);
        }
    }

    public int getIconId() {
        return this._iconId;
    }

    public void setIconId(int _icon) {
        this._iconId = _icon;
    }

    public String getTitle() {
        return this._title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public ListItem(String _title, int _iconId, int _id) {
        this._iconId = _iconId;
        this._title = _title;
        this._id = _id;
    }

    public void select() {
        MainNavigationActivity activity = Factory.getInstance().getMainNavigationActivity();
        if (activity != null) {
            activity.runOnUiThread(new C02651(activity));
        }
    }
}
