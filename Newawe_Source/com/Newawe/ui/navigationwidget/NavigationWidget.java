package com.Newawe.ui.navigationwidget;

import android.content.Context;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.CursorToStringConverter;
import android.widget.Toast;
import com.Newawe.C0186R;
import com.Newawe.Factory;
import com.Newawe.configuration.UrlBarMenuButton;
import com.Newawe.controllers.WebContentController;
import com.Newawe.storage.BrowsingHistoryItem;
import com.Newawe.storage.BrowsingHistoryStorage;
import com.Newawe.storage.DatabaseOpenHelper;
import com.Newawe.suggestions.SuggestionsClient;
import com.Newawe.suggestions.SuggestionsListener;
import com.Newawe.ui.views.BrowserWebView;
import com.Newawe.utils.ShortcutFabric;
import com.Newawe.utils.UserAgentManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

public abstract class NavigationWidget implements INavigationWidget, SuggestionsListener {
    protected static final int MAX_SUGGESTIONS_COUNT = 6;
    protected BrowserWebView _browser;
    protected HashMap<String, NavigationWidgetCustomIcon> _customIcons;
    protected int _defaultBottomBrowserMargin;
    protected int _defaultLeftBrowserMargin;
    protected int _defaultRightBrowserMargin;
    protected int _defaultTopBrowserMargin;
    protected String _defaultUrl;
    protected boolean _hideOnInternalUrls;
    protected BrowsingHistoryStorage _history;
    protected boolean _menuVisible;
    protected OnCheckedChangeListener _onUaChangeListener;
    protected ViewGroup _parent;
    protected RelativeLayout _progressBarContainer;
    protected OnClickListener _refreshOnclickListener;
    protected OnClickListener _stopOnclickListener;
    protected boolean _suggestionsVisible;
    protected boolean _visible;
    protected ImageButton clearTextButton;
    protected OnFocusChangeListener focusChangeListener;
    protected Handler handler;
    protected boolean inFocus;
    protected ViewGroup overlay;
    protected ViewGroup overlayScroll;
    protected int refreshImage;
    protected LinearLayout searchBackground;
    protected ImageView searchBtn;
    protected ViewGroup searchField;
    protected Animation slideDown;
    protected Animation slideUp;
    protected int stopImage;
    protected ImageButton stopRefreshButton;
    protected SuggestionsClient suggestionsClient;
    protected ViewGroup suggestionsView;
    protected ViewGroup suggestionsViewScroll;
    protected Toolbar urlBar;
    protected int urlBarButton;
    protected int urlBarCheckBox;
    protected int urlBarIcon;
    protected TextWatcher urlBarTextChangeListener;
    AutoCompleteTextView urlTextBox;

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.13 */
    class AnonymousClass13 implements AnimationListener {
        final /* synthetic */ boolean val$toShowUrlBar;

        AnonymousClass13(boolean z) {
            this.val$toShowUrlBar = z;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.val$toShowUrlBar) {
                NavigationWidget.this.show();
            } else {
                NavigationWidget.this.hide();
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.14 */
    class AnonymousClass14 extends Animation {
        final /* synthetic */ boolean val$toShowUrlBar;

        AnonymousClass14(boolean z) {
            this.val$toShowUrlBar = z;
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newMargin;
            if (this.val$toShowUrlBar) {
                newMargin = (int) (((float) NavigationWidget.this._defaultTopBrowserMargin) * interpolatedTime);
            } else {
                newMargin = (int) (((float) NavigationWidget.this._defaultTopBrowserMargin) - (((float) NavigationWidget.this._defaultTopBrowserMargin) * interpolatedTime));
            }
            LayoutParams params = (LayoutParams) NavigationWidget.this._browser.getLayoutParams();
            params.setMargins(NavigationWidget.this._defaultLeftBrowserMargin, newMargin, NavigationWidget.this._defaultRightBrowserMargin, NavigationWidget.this._defaultBottomBrowserMargin);
            NavigationWidget.this._browser.setLayoutParams(params);
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.15 */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ boolean val$isVisible;

        AnonymousClass15(boolean z) {
            this.val$isVisible = z;
        }

        public void run() {
            if (NavigationWidget.this.clearTextButton != null) {
                NavigationWidget.this.clearTextButton.setVisibility(this.val$isVisible ? 0 : 8);
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.1 */
    class C02711 implements TextWatcher {
        C02711() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            NavigationWidget.this.onTextChanged();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            NavigationWidget.this.onTextChanged();
        }

        public void afterTextChanged(Editable s) {
            NavigationWidget.this.onTextChanged();
            NavigationWidget.this.requestSuggestions();
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.2 */
    class C02722 implements OnClickListener {
        C02722() {
        }

        public void onClick(View v) {
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().stopLoading();
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.3 */
    class C02733 implements OnClickListener {
        C02733() {
        }

        public void onClick(View v) {
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().reload();
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.4 */
    class C02744 implements OnCheckedChangeListener {
        C02744() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.5 */
    class C02755 implements OnFocusChangeListener {
        C02755() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            NavigationWidget.this.inFocus = hasFocus;
            Context context = NavigationWidget.this._parent.getContext();
            if (hasFocus) {
                ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(v, 0);
                if (NavigationWidget.this.isHomePageUrl()) {
                    NavigationWidget.this.focusOnUrlBarHomePage();
                    return;
                } else {
                    NavigationWidget.this.focusOnUrlBarExternalLink();
                    return;
                }
            }
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
            NavigationWidget.this.urlTextBox.setSelection(0, 0);
            if (NavigationWidget.this.isHomePageUrl()) {
                NavigationWidget.this.focusOffUrlBarHomePage();
            } else {
                NavigationWidget.this.focusOffUrlBarExternalLink();
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.6 */
    class C02766 implements Runnable {
        final /* synthetic */ Context val$context;

        C02766(Context context) {
            this.val$context = context;
        }

        public void run() {
            NavigationWidget.this.hideSuggestionsView();
            NavigationWidget.this.requestSuggestions();
            NavigationWidget.this.searchBackground.setBackgroundDrawable(this.val$context.getResources().getDrawable(C0186R.drawable.url_input));
            NavigationWidget.this.searchBtn.setImageResource(C0186R.drawable.ic_search_black_24dp);
            NavigationWidget.this.urlTextBox.setPadding(NavigationWidget.this.urlTextBox.getPaddingLeft(), NavigationWidget.this.urlTextBox.getPaddingTop(), NavigationWidget.this.dpToPx(25), NavigationWidget.this.urlTextBox.getPaddingBottom());
            NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarTextDark));
            NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarHintDark));
            if (NavigationWidget.this.urlTextBox.getText().length() > 0) {
                NavigationWidget.this.clearTextButton.setVisibility(0);
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.7 */
    class C02777 implements Runnable {
        C02777() {
        }

        public void run() {
            NavigationWidget.this.hideSuggestionsView();
            NavigationWidget.this.searchBtn.setImageResource(C0186R.drawable.ic_search_white_24dp);
            NavigationWidget.this.stopRefreshButton.setVisibility(0);
            NavigationWidget.this.clearTextButton.setVisibility(8);
            NavigationWidget.this.urlTextBox.setPadding(NavigationWidget.this.urlTextBox.getPaddingLeft(), NavigationWidget.this.urlTextBox.getPaddingTop(), NavigationWidget.this.dpToPx(0), NavigationWidget.this.urlTextBox.getPaddingBottom());
            NavigationWidget.this.searchBackground.setBackgroundColor(NavigationWidget.this.getColor(C0186R.color.transparent));
            NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarTextLight));
            NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarHintLight));
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.8 */
    class C02788 implements Runnable {
        final /* synthetic */ Context val$context;

        C02788(Context context) {
            this.val$context = context;
        }

        public void run() {
            NavigationWidget.this.hideSuggestionsView();
            NavigationWidget.this.requestSuggestions();
            NavigationWidget.this.searchBackground.setBackgroundDrawable(this.val$context.getResources().getDrawable(C0186R.drawable.url_input));
            NavigationWidget.this.searchBtn.setImageResource(C0186R.drawable.ic_public_black_24dp);
            NavigationWidget.this.urlTextBox.setPadding(NavigationWidget.this.urlTextBox.getPaddingLeft(), NavigationWidget.this.urlTextBox.getPaddingTop(), NavigationWidget.this.dpToPx(25), NavigationWidget.this.urlTextBox.getPaddingBottom());
            NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarTextDark));
            NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarHintDark));
            if (NavigationWidget.this.urlTextBox.getText().length() > 0) {
                NavigationWidget.this.clearTextButton.setVisibility(0);
            }
        }
    }

    /* renamed from: com.Newawe.ui.navigationwidget.NavigationWidget.9 */
    class C02799 implements Runnable {
        final /* synthetic */ Context val$context;

        C02799(Context context) {
            this.val$context = context;
        }

        public void run() {
            NavigationWidget.this.hideSuggestionsView();
            NavigationWidget.this.searchBackground.setBackgroundDrawable(this.val$context.getResources().getDrawable(C0186R.drawable.url_input));
            NavigationWidget.this.searchBtn.setImageResource(C0186R.drawable.ic_public_black_24dp);
            NavigationWidget.this.urlTextBox.setPadding(NavigationWidget.this.urlTextBox.getPaddingLeft(), NavigationWidget.this.urlTextBox.getPaddingTop(), NavigationWidget.this.dpToPx(0), NavigationWidget.this.urlTextBox.getPaddingBottom());
            NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarTextDark));
            NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(C0186R.color.urlBarHintDark));
            NavigationWidget.this.clearTextButton.setVisibility(8);
        }
    }

    public abstract void hideSuggestionsView();

    public abstract void requestSuggestions();

    protected void onTextChanged() {
        if (!this.inFocus) {
            return;
        }
        if (this.urlTextBox.getText().length() <= 0) {
            this.clearTextButton.setVisibility(8);
        } else {
            this.clearTextButton.setVisibility(0);
        }
    }

    public ArrayList<BrowsingHistoryItem> getWeeklyHistory() {
        Cursor cursor = this._history.loadWeeklyHistory();
        ArrayList<BrowsingHistoryItem> localItems = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    String title = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.HISTORY_ROW_TITLE));
                    String url = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.HISTORY_ROW_URL));
                    String date = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.HISTORY_ROW_VISIT_TIME));
                    if (!isHomePageUrl(url)) {
                        localItems.add(new BrowsingHistoryItem(id, date, title, url));
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            return localItems;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public int removeHistoryItem(long id) {
        return this._history.removeHistoryItemById(id);
    }

    public void reloadWithChangedUserAgent(boolean isDesktop) {
        Context context = this._parent.getContext();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        WebSettings settings = webView.getSettings();
        if (isDesktop) {
            settings.setUserAgentString(UserAgentManager.getDesktopUserAgent(context));
        } else {
            settings.setUserAgentString(UserAgentManager.getDefaultUserAgent(context));
        }
        webView.reload();
    }

    public int dpToPx(int dp) {
        return (int) (((float) dp) * (((float) this._parent.getContext().getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public int pxToDp(int px) {
        return (int) (((float) px) / (((float) this._parent.getContext().getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public NavigationWidget(ViewGroup parent, String defaultUrl, BrowserWebView browser, Collection<UrlBarMenuButton> collection) {
        this.clearTextButton = null;
        this._menuVisible = false;
        this._suggestionsVisible = false;
        this.overlay = null;
        this.overlayScroll = null;
        this.searchField = null;
        this.searchBackground = null;
        this.slideDown = null;
        this.slideUp = null;
        this.searchBtn = null;
        this.stopRefreshButton = null;
        this.inFocus = false;
        this.suggestionsView = null;
        this.suggestionsViewScroll = null;
        this.handler = new Handler();
        this.urlBar = null;
        this.suggestionsClient = null;
        this._hideOnInternalUrls = false;
        this._browser = null;
        this._defaultLeftBrowserMargin = 0;
        this._defaultRightBrowserMargin = 0;
        this._defaultTopBrowserMargin = 0;
        this._defaultBottomBrowserMargin = 0;
        this.refreshImage = C0186R.drawable.reload_item;
        this.stopImage = C0186R.drawable.cross_item;
        this.urlBarIcon = C0186R.layout.url_bar_icon;
        this.urlBarButton = C0186R.layout.url_bar_menu_button;
        this.urlBarCheckBox = C0186R.layout.url_bar_menu_checkbox;
        this._visible = true;
        this._customIcons = new HashMap();
        this.urlBarTextChangeListener = new C02711();
        this._stopOnclickListener = new C02722();
        this._refreshOnclickListener = new C02733();
        this._onUaChangeListener = new C02744();
        this.focusChangeListener = new C02755();
    }

    public void onPageStart(WebView view, String url) {
        hideSuggestionsView();
        ImageButton button = (ImageButton) this._parent.findViewById(C0186R.id.stopRefreshButton);
        button.setImageResource(this.stopImage);
        button.setOnClickListener(this._stopOnclickListener);
        setUrl(url);
        if (this.inFocus) {
            if (isHomePageUrl(url)) {
                focusOnUrlBarHomePage();
            } else {
                focusOnUrlBarExternalLink();
            }
        } else if (isHomePageUrl(url)) {
            focusOffUrlBarHomePage();
        } else {
            focusOffUrlBarExternalLink();
        }
    }

    protected void focusOnUrlBarHomePage() {
        System.out.println("focuschange_focusOnUrlBarHomePage");
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02766(this._parent.getContext()));
    }

    protected void focusOffUrlBarHomePage() {
        System.out.println("focuschange_focusOffUrlBarHomePage");
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02777());
    }

    protected void focusOnUrlBarExternalLink() {
        System.out.println("focuschange_focusOnUrlBarExternalLink");
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02788(this._parent.getContext()));
    }

    protected void focusOffUrlBarExternalLink() {
        System.out.println("focuschange_focusOffUrlBarExternalLink");
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02799(this._parent.getContext()));
    }

    protected boolean isHomePageUrl(String url) {
        if (url == null) {
            return false;
        }
        String homeLink = Factory.getInstance().getTabsController().getSelectedTab().getWidgetInfo().getLink();
        if (homeLink != null) {
            return url.toLowerCase().contains(homeLink.toLowerCase());
        }
        return false;
    }

    protected boolean isHomePageUrl() {
        WebContentController tab = Factory.getInstance().getTabsController().getSelectedTab();
        return tab.getWebView().getUrl().equalsIgnoreCase(tab.getWidgetInfo().getLink());
    }

    private int getColor(int id) {
        if (VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(this._parent.getContext(), id);
        }
        return this._parent.getContext().getResources().getColor(id);
    }

    public void setHideOnInternalUrls(boolean isHidden) {
        this._hideOnInternalUrls = isHidden;
    }

    protected void initEventHandlers() {
        this.urlTextBox.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return NavigationWidget.this._onUrlTextBoxKeyEvent(v, keyCode, event);
            }
        });
    }

    protected boolean _onUrlTextBoxKeyEvent(View v, int keyCode, KeyEvent event) {
        AutoCompleteTextView urlTextBox = (AutoCompleteTextView) v;
        if (event.getAction() != 1 || keyCode != 66) {
            return false;
        }
        String url = urlTextBox.getText().toString();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        webView.loadUrl(url);
        _setClearButtonVisibility(false);
        webView.requestFocus();
        ((InputMethodManager) this._parent.getContext().getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 2);
        return true;
    }

    protected void _forceGoToUrl(AutoCompleteTextView urlTextBox) {
        String url = urlTextBox.getText().toString();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        webView.loadUrl(url);
        _setClearButtonVisibility(false);
        webView.requestFocus();
        ((InputMethodManager) this._parent.getContext().getSystemService("input_method")).hideSoftInputFromWindow(urlTextBox.getWindowToken(), 2);
    }

    public void onPinToHomeScreenButtonClick() {
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        String url = webView.getUrl();
        String title = webView.getTitle();
        if (url.equalsIgnoreCase(this._defaultUrl)) {
            url = StringUtils.EMPTY;
        }
        if (title == null || title.length() == 0) {
            title = Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName();
        }
        ShortcutFabric.createShortcut(url, title, this._parent.getContext());
    }

    public void onHomeButtonClick() {
        WebContentController tab = Factory.getInstance().getTabsController().getSelectedTab();
        tab.getWebView().loadUrl(tab.getWidgetInfo().getLink());
        _setClearButtonVisibility(false);
    }

    public void onAddToStartPageClick() {
        WebContentController tab = Factory.getInstance().getTabsController().getSelectedTab();
        WebView view = tab.getWebView();
        String homeLink = tab.getWidgetInfo().getLink();
        String currentLink = view.getUrl();
        if (!currentLink.equals(homeLink)) {
            String title = view.getTitle();
            if (title == null) {
                title = currentLink;
            }
            Factory.getInstance().getHomePageManager().addBookmark(title, currentLink);
            Toast.makeText(this._parent.getContext(), this._parent.getContext().getString(C0186R.string.new_start_page_item_added), 0).show();
        }
    }

    public void onClickBackButton() {
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        if (webView.canGoBack()) {
            webView.stopLoading();
            webView.goBack();
        }
    }

    public void onClickForwardButton() {
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        if (webView.canGoForward()) {
            webView.stopLoading();
            webView.goForward();
        }
    }

    public void onClickLinkButton(String url) {
        Factory.getInstance().getTabsController().getSelectedTab().getWebView().loadUrl(url);
        _setClearButtonVisibility(false);
    }

    public void initHistory() {
        if (this._history == null) {
            this._history = new BrowsingHistoryStorage(this._parent.getContext());
        }
    }

    public void attachAutocomplete() {
        if (this._history == null) {
            this._history = new BrowsingHistoryStorage(this._parent.getContext());
        }
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this._parent.getContext(), C0186R.layout.history_autocomplete_layout, null, new String[]{DatabaseOpenHelper.HISTORY_ROW_TITLE, DatabaseOpenHelper.HISTORY_ROW_URL}, new int[]{C0186R.id.titleItem, C0186R.id.urlItem});
        cursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return NavigationWidget.this._history.getHistoryItemsGroupedByUrl(constraint != null ? constraint.toString() : null);
            }
        });
        cursorAdapter.setCursorToStringConverter(new CursorToStringConverter() {
            public String convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.HISTORY_ROW_URL));
            }
        });
        ((AutoCompleteTextView) this._parent.findViewById(C0186R.id.urlTextbox)).setAdapter(cursorAdapter);
    }

    public void onPageFinished(WebView view, String url) {
        if (URLUtil.isValidUrl(url)) {
            this._history.addHistoryItem(view.getTitle(), url, new Date());
            ImageButton button = (ImageButton) this._parent.findViewById(C0186R.id.stopRefreshButton);
            button.setImageResource(this.refreshImage);
            button.setOnClickListener(this._refreshOnclickListener);
        }
    }

    public void changeVisibilityAnimated(boolean toShowUrlBar) {
        if (this._parent.findViewById(C0186R.id.topNavigationRow) == null) {
            return;
        }
        if (!this._visible || !toShowUrlBar) {
            if (this._visible || toShowUrlBar) {
                Animation animation = AnimationUtils.loadAnimation(this._parent.getContext(), toShowUrlBar ? C0186R.anim.slide_down : C0186R.anim.slide_up);
                animation.setAnimationListener(new AnonymousClass13(toShowUrlBar));
                animation.setDuration(300);
                this._parent.findViewById(C0186R.id.topNavigationRow).startAnimation(animation);
                Animation a = new AnonymousClass14(toShowUrlBar);
                a.setDuration(300);
                this._parent.startAnimation(a);
            }
        }
    }

    public void hideAnimated() {
        changeVisibilityAnimated(false);
    }

    public void showAnimated() {
        changeVisibilityAnimated(true);
    }

    public void hide() {
        if (this._parent.findViewById(C0186R.id.topNavigationRow) != null) {
            this._parent.findViewById(C0186R.id.topNavigationRow).setVisibility(8);
            ((LayoutParams) this._browser.getLayoutParams()).setMargins(0, 0, 0, 0);
            ((LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(0, 0, 0, 0);
            this._visible = false;
        }
    }

    public void show() {
        if (this._parent.findViewById(C0186R.id.topNavigationRow) != null) {
            this._parent.findViewById(C0186R.id.topNavigationRow).setVisibility(0);
            ((LayoutParams) this._browser.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            ((LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            this._visible = true;
        }
    }

    public boolean isVisible() {
        return this._visible;
    }

    public void setUrl(String url) {
        if (this._hideOnInternalUrls) {
            if (_isLocalUrl(url)) {
                hide();
            } else {
                show();
            }
        }
        if (!url.equalsIgnoreCase(this._parent.getContext().getString(C0186R.string.errorHtmlPath))) {
            AutoCompleteTextView urlTextBox = (AutoCompleteTextView) this._parent.findViewById(C0186R.id.urlTextbox);
            if (url.startsWith(this._defaultUrl) || _isLocalUrl(url)) {
                urlTextBox.setText(StringUtils.EMPTY);
            } else {
                urlTextBox.setText(url);
            }
        }
    }

    protected boolean _isLocalUrl(String url) {
        return url.startsWith("file://");
    }

    public HashMap<String, NavigationWidgetCustomIcon> getCustomIcons() {
        return this._customIcons;
    }

    private void _setClearButtonVisibility(boolean isVisible) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new AnonymousClass15(isVisible));
    }
}
