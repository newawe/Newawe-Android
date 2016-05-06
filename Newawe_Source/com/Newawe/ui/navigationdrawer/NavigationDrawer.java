package com.Newawe.ui.navigationdrawer;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import java.util.ArrayList;

public class NavigationDrawer {
    private final int SLIDER_HANDLE_SHOW_TIME;
    private final String USER_KNOWS_ABOUT_DRAWER_KEY;
    private MainNavigationActivity _activity;
    private Handler _hideHandle;
    private Animation _sliderHandleAnimation;
    private Animation _sliderHandleHideAnimation;
    private boolean _userKnowsAboutDrawer;
    private boolean isOpened;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    private ImageView mSliderHandle;

    /* renamed from: com.Newawe.ui.navigationdrawer.NavigationDrawer.2 */
    class C02662 implements Runnable {
        C02662() {
        }

        public void run() {
            NavigationDrawer.this.hideSliderHandle();
        }
    }

    /* renamed from: com.Newawe.ui.navigationdrawer.NavigationDrawer.3 */
    class C02673 implements AnimationListener {
        C02673() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            NavigationDrawer.this.mSliderHandle.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public class DrawerItemClickListener implements OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            if (NavigationDrawer.this.mDrawerList != null && (NavigationDrawer.this.mDrawerList.getItemAtPosition(position) instanceof ListItem)) {
                ((ListItem) NavigationDrawer.this.mDrawerList.getItemAtPosition(position)).select();
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationdrawer.NavigationDrawer.1 */
    class C13021 extends ActionBarDrawerToggle {
        C13021(Activity x0, DrawerLayout x1, int x2, int x3, int x4) {
            super(x0, x1, x2, x3, x4);
        }

        public void onDrawerClosed(View view) {
            NavigationDrawer.this.isOpened = false;
        }

        public void onDrawerOpened(View drawerView) {
            NavigationDrawer.this.isOpened = true;
            NavigationDrawer.this.hideSliderHandle();
        }
    }

    public NavigationDrawer(MainNavigationActivity activity) {
        this._hideHandle = new Handler();
        this.USER_KNOWS_ABOUT_DRAWER_KEY = "userKnowsAboutDrawer";
        this.SLIDER_HANDLE_SHOW_TIME = 20000;
        this._userKnowsAboutDrawer = false;
        this.isOpened = false;
        if (activity != null) {
            this._activity = activity;
            this.mDrawerLayout = (DrawerLayout) this._activity.findViewById(C0186R.id.drawer_layout);
            this.mDrawerList = (ListView) this._activity.findViewById(C0186R.id.left_drawer);
            this.mDrawerList.setVisibility(8);
            this.mSliderHandle = (ImageView) this._activity.findViewById(C0186R.id.slider_handle);
            this._sliderHandleAnimation = AnimationUtils.loadAnimation(activity, C0186R.anim.slider_handle_animation);
            this._sliderHandleAnimation.setRepeatCount(-1);
            this._sliderHandleHideAnimation = AnimationUtils.loadAnimation(activity, C0186R.anim.slider_handle_hide_animation);
            this._userKnowsAboutDrawer = activity.getPreferences(0).getBoolean("userKnowsAboutDrawer", false);
            if (!this._userKnowsAboutDrawer) {
                showSliderHandle();
            }
            this.mDrawerToggle = new C13021(this._activity, this.mDrawerLayout, C0186R.drawable.icon, C0186R.string.drawer_open, C0186R.string.drawer_close);
            this._hideHandle.postDelayed(new C02662(), 20000);
            this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
            this.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        }
    }

    public void setOptions(ArrayList<ListItem> items) {
        this.mDrawerList.setAdapter(new Adapter(this._activity, items));
    }

    public void close() {
        if (this.mDrawerLayout != null && this.mDrawerList != null) {
            this.mDrawerLayout.closeDrawer(this.mDrawerList);
        }
    }

    public void remove() {
        ViewGroup group;
        if (this.mDrawerList != null) {
            group = (ViewGroup) this.mDrawerList.getParent();
            if (group != null) {
                group.removeView(this.mDrawerList);
            }
        }
        if (this.mSliderHandle != null) {
            group = (ViewGroup) this.mSliderHandle.getParent();
            if (group != null) {
                group.removeView(this.mSliderHandle);
            }
        }
    }

    public void show() {
        this.mDrawerList.setVisibility(0);
        this.mSliderHandle.bringToFront();
        this.mDrawerLayout.setDrawerLockMode(0);
    }

    public void hide() {
        this.mDrawerList.setVisibility(8);
        this.mDrawerLayout.setDrawerLockMode(1);
    }

    public void showSliderHandle() {
        this.mSliderHandle.setVisibility(0);
        this.mSliderHandle.bringToFront();
        this.mSliderHandle.startAnimation(this._sliderHandleAnimation);
    }

    public void hideSliderHandle() {
        if (!this._userKnowsAboutDrawer) {
            this._userKnowsAboutDrawer = true;
            this._activity.getPreferences(0).edit().putBoolean("userKnowsAboutDrawer", this._userKnowsAboutDrawer).apply();
        }
        this.mSliderHandle.clearAnimation();
        this._sliderHandleHideAnimation.setAnimationListener(new C02673());
        this.mSliderHandle.startAnimation(this._sliderHandleHideAnimation);
    }

    public boolean isOpened() {
        return this.isOpened;
    }
}
