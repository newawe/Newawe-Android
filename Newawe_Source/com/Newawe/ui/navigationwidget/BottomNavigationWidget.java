package com.Newawe.ui.navigationwidget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.UrlBarMenuButton;
import com.Newawe.configuration.WebWidgetConfiguration.ApplicationThemes;
import com.Newawe.suggestions.RemoteSuggestionItem;
import com.Newawe.suggestions.SuggestionsClient;
import com.Newawe.ui.views.BrowserWebView;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;

public class BottomNavigationWidget extends NavigationWidget {

    /* renamed from: com.Newawe.ui.navigationwidget.BottomNavigationWidget.1 */
    class C02681 implements OnClickListener {
        C02681() {
        }

        public void onClick(View v) {
            BottomNavigationWidget.this.urlTextBox.setText(StringUtils.EMPTY);
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.BottomNavigationWidget.2 */
    class C02692 implements OnClickListener {
        C02692() {
        }

        public void onClick(View v) {
            BottomNavigationWidget.this.focusChangeListener.onFocusChange(v, true);
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.BottomNavigationWidget.3 */
    class C02703 implements Runnable {
        final /* synthetic */ MainNavigationActivity val$mainActivity;

        C02703(MainNavigationActivity mainNavigationActivity) {
            this.val$mainActivity = mainNavigationActivity;
        }

        public void run() {
            this.val$mainActivity.setSupportActionBar(BottomNavigationWidget.this.urlBar);
        }
    }

    public BottomNavigationWidget(ViewGroup parent, String defaultUrl, BrowserWebView browser, Collection<UrlBarMenuButton> urlBarMenuButtons) {
        super(parent, defaultUrl, browser, urlBarMenuButtons);
        this._parent = parent;
        this._progressBarContainer = (RelativeLayout) parent.findViewById(C0186R.id.progressbarPanel);
        this._defaultUrl = defaultUrl;
        this._browser = browser;
        createWidgetLayout();
        this.suggestionsClient = new SuggestionsClient(Factory.getInstance().getMainNavigationActivity());
        this.suggestionsClient.setListener(this);
        this.refreshImage = C0186R.drawable.ic_refresh_white_24dp;
        this.stopImage = C0186R.drawable.ic_close_white_24dp;
        this.urlBarButton = C0186R.layout.url_bar_top_menu_button;
        this.urlBarCheckBox = C0186R.layout.url_bar_top_menu_checkbox;
        this.urlBarIcon = C0186R.layout.url_bar_top_icon;
        this.suggestionsView = (ViewGroup) this._parent.findViewById(C0186R.id.suggestions);
        this.suggestionsViewScroll = (ViewGroup) this._parent.findViewById(C0186R.id.suggestionsScroll);
        this.overlay = (ViewGroup) this._parent.findViewById(C0186R.id.menuOverlay);
        this.overlayScroll = (ViewGroup) this._parent.findViewById(C0186R.id.menuOverlayScroll);
        this.urlTextBox = (AutoCompleteTextView) this._parent.findViewById(C0186R.id.urlTextbox);
        this.searchBackground = (LinearLayout) this._parent.findViewById(C0186R.id.search_background);
        this.stopRefreshButton = (ImageButton) this._parent.findViewById(C0186R.id.stopRefreshButton);
        this.searchBtn = (ImageView) this.searchBackground.findViewById(C0186R.id.search_icon);
        this.clearTextButton = (ImageButton) this._parent.findViewById(C0186R.id.clearText);
        this.clearTextButton.setOnClickListener(new C02681());
        this.urlTextBox.addTextChangedListener(this.urlBarTextChangeListener);
        this.urlTextBox.setOnFocusChangeListener(this.focusChangeListener);
        this.urlTextBox.setOnClickListener(new C02692());
        initEventHandlers();
    }

    public void createWidgetLayout() {
        Context context = this._parent.getContext();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0186R.layout.navigation_bar, this._parent, true);
        this.urlBar = (Toolbar) this._parent.findViewById(C0186R.id.bottomNavigationRow);
        this.urlBar.bringToFront();
        if (!Factory.getInstance().getMainNavigationActivity().getConfig().getApplicationTheme().equals(ApplicationThemes.ACTION_BAR)) {
            this.urlBar.inflateMenu(C0186R.menu.webapp_menu);
            MainNavigationActivity mainActivity = Factory.getInstance().getMainNavigationActivity();
            mainActivity.runOnUiThread(new C02703(mainActivity));
        }
        this._defaultTopBrowserMargin = 0;
        this._defaultBottomBrowserMargin = (int) TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics());
        show();
    }

    public void requestSuggestions() {
    }

    public void hideSuggestionsView() {
    }

    public void onReceiveSuggestions(ArrayList<RemoteSuggestionItem> arrayList, String initialSearch) {
    }
}
