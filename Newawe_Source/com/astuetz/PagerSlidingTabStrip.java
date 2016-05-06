package com.astuetz;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.astuetz.pagerslidingtabstrip.C0302R;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] ANDROID_ATTRS;
    public static final int DEF_VALUE_TAB_TEXT_ALPHA = 150;
    private static final int PADDING_INDEX = 1;
    private static final int PADDING_LEFT_INDEX = 2;
    private static final int PADDING_RIGHT_INDEX = 3;
    private static final int TEXT_COLOR_PRIMARY = 0;
    private OnGlobalLayoutListener firstTabGlobalLayoutListener;
    private boolean isCustomTabs;
    private boolean isExpandTabs;
    private boolean isPaddingMiddle;
    private boolean isTabTextAllCaps;
    private final PagerAdapterObserver mAdapterObserver;
    private int mCurrentPosition;
    private float mCurrentPositionOffset;
    public OnPageChangeListener mDelegatePageListener;
    private int mDividerColor;
    private int mDividerPadding;
    private Paint mDividerPaint;
    private int mDividerWidth;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private int mLastScrollX;
    private int mPaddingLeft;
    private int mPaddingRight;
    private final PageListener mPageListener;
    private ViewPager mPager;
    private Paint mRectPaint;
    private int mScrollOffset;
    private int mTabBackgroundResId;
    private int mTabCount;
    private LayoutParams mTabLayoutParams;
    private int mTabPadding;
    private OnTabReselectedListener mTabReselectedListener;
    private ColorStateList mTabTextColor;
    private int mTabTextSize;
    private Typeface mTabTextTypeface;
    private int mTabTextTypefaceStyle;
    private LinearLayout mTabsContainer;
    private int mUnderlineColor;
    private int mUnderlineHeight;

    /* renamed from: com.astuetz.PagerSlidingTabStrip.1 */
    class C02991 implements OnClickListener {
        final /* synthetic */ int val$position;

        C02991(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            if (PagerSlidingTabStrip.this.mPager.getCurrentItem() != this.val$position) {
                PagerSlidingTabStrip.this.unSelect(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(PagerSlidingTabStrip.this.mPager.getCurrentItem()));
                PagerSlidingTabStrip.this.mPager.setCurrentItem(this.val$position);
            } else if (PagerSlidingTabStrip.this.mTabReselectedListener != null) {
                PagerSlidingTabStrip.this.mTabReselectedListener.onTabReselected(this.val$position);
            }
        }
    }

    /* renamed from: com.astuetz.PagerSlidingTabStrip.2 */
    class C03002 implements OnGlobalLayoutListener {
        C03002() {
        }

        public void onGlobalLayout() {
            View view = PagerSlidingTabStrip.this.mTabsContainer.getChildAt(0);
            if (VERSION.SDK_INT < 16) {
                removeGlobalLayoutListenerPreJB();
            } else {
                removeGlobalLayoutListenerJB();
            }
            if (PagerSlidingTabStrip.this.isPaddingMiddle) {
                PagerSlidingTabStrip.this.mPaddingLeft = PagerSlidingTabStrip.this.mPaddingRight = (PagerSlidingTabStrip.this.getWidth() / PagerSlidingTabStrip.PADDING_LEFT_INDEX) - (view.getWidth() / PagerSlidingTabStrip.PADDING_LEFT_INDEX);
            }
            PagerSlidingTabStrip.this.setPadding(PagerSlidingTabStrip.this.mPaddingLeft, PagerSlidingTabStrip.this.getPaddingTop(), PagerSlidingTabStrip.this.mPaddingRight, PagerSlidingTabStrip.this.getPaddingBottom());
            if (PagerSlidingTabStrip.this.mScrollOffset == 0) {
                PagerSlidingTabStrip.this.mScrollOffset = (PagerSlidingTabStrip.this.getWidth() / PagerSlidingTabStrip.PADDING_LEFT_INDEX) - PagerSlidingTabStrip.this.mPaddingLeft;
            }
            PagerSlidingTabStrip.this.mCurrentPosition = PagerSlidingTabStrip.this.mPager.getCurrentItem();
            PagerSlidingTabStrip.this.mCurrentPositionOffset = 0.0f;
            PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.mCurrentPosition, 0);
            PagerSlidingTabStrip.this.updateSelection(PagerSlidingTabStrip.this.mCurrentPosition);
        }

        private void removeGlobalLayoutListenerPreJB() {
            PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }

        @TargetApi(16)
        private void removeGlobalLayoutListenerJB() {
            PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    public interface CustomTabProvider {
        View getCustomTabView(ViewGroup viewGroup, int i);

        void tabSelected(View view);

        void tabUnselected(View view);
    }

    public interface OnTabReselectedListener {
        void onTabReselected(int i);
    }

    private class PagerAdapterObserver extends DataSetObserver {
        private boolean attached;

        private PagerAdapterObserver() {
            this.attached = false;
        }

        public void onChanged() {
            PagerSlidingTabStrip.this.notifyDataSetChanged();
        }

        public void setAttached(boolean attached) {
            this.attached = attached;
        }

        public boolean isAttached() {
            return this.attached;
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int currentPosition;

        /* renamed from: com.astuetz.PagerSlidingTabStrip.SavedState.1 */
        static class C03011 implements Creator<SavedState> {
            C03011() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.currentPosition = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.currentPosition);
        }

        static {
            CREATOR = new C03011();
        }
    }

    private class PageListener implements OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            PagerSlidingTabStrip.this.mCurrentPosition = position;
            PagerSlidingTabStrip.this.mCurrentPositionOffset = positionOffset;
            PagerSlidingTabStrip.this.scrollToChild(position, PagerSlidingTabStrip.this.mTabCount > 0 ? (int) (((float) PagerSlidingTabStrip.this.mTabsContainer.getChildAt(position).getWidth()) * positionOffset) : 0);
            PagerSlidingTabStrip.this.invalidate();
            if (PagerSlidingTabStrip.this.mDelegatePageListener != null) {
                PagerSlidingTabStrip.this.mDelegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        public void onPageScrollStateChanged(int state) {
            if (state == 0) {
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.mPager.getCurrentItem(), 0);
            }
            PagerSlidingTabStrip.this.select(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(PagerSlidingTabStrip.this.mPager.getCurrentItem()));
            if (PagerSlidingTabStrip.this.mPager.getCurrentItem() - 1 >= 0) {
                PagerSlidingTabStrip.this.unSelect(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(PagerSlidingTabStrip.this.mPager.getCurrentItem() - 1));
            }
            if (PagerSlidingTabStrip.this.mPager.getCurrentItem() + PagerSlidingTabStrip.PADDING_INDEX <= PagerSlidingTabStrip.this.mPager.getAdapter().getCount() - 1) {
                PagerSlidingTabStrip.this.unSelect(PagerSlidingTabStrip.this.mTabsContainer.getChildAt(PagerSlidingTabStrip.this.mPager.getCurrentItem() + PagerSlidingTabStrip.PADDING_INDEX));
            }
            if (PagerSlidingTabStrip.this.mDelegatePageListener != null) {
                PagerSlidingTabStrip.this.mDelegatePageListener.onPageScrollStateChanged(state);
            }
        }

        public void onPageSelected(int position) {
            PagerSlidingTabStrip.this.updateSelection(position);
            if (PagerSlidingTabStrip.this.mDelegatePageListener != null) {
                PagerSlidingTabStrip.this.mDelegatePageListener.onPageSelected(position);
            }
        }
    }

    static {
        ANDROID_ATTRS = new int[]{16842806, 16842965, 16842966, 16842968};
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mAdapterObserver = new PagerAdapterObserver();
        this.mPageListener = new PageListener();
        this.mTabReselectedListener = null;
        this.mCurrentPosition = 0;
        this.mCurrentPositionOffset = 0.0f;
        this.mIndicatorHeight = PADDING_LEFT_INDEX;
        this.mUnderlineHeight = 0;
        this.mDividerWidth = 0;
        this.mDividerPadding = 0;
        this.mTabPadding = 12;
        this.mTabTextSize = 14;
        this.mTabTextColor = null;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.isExpandTabs = false;
        this.isPaddingMiddle = false;
        this.isTabTextAllCaps = true;
        this.mTabTextTypeface = null;
        this.mTabTextTypefaceStyle = PADDING_INDEX;
        this.mLastScrollX = 0;
        this.mTabBackgroundResId = C0302R.drawable.psts_background_tab;
        this.firstTabGlobalLayoutListener = new C03002();
        setFillViewport(true);
        setWillNotDraw(false);
        this.mTabsContainer = new LinearLayout(context);
        this.mTabsContainer.setOrientation(0);
        addView(this.mTabsContainer);
        this.mRectPaint = new Paint();
        this.mRectPaint.setAntiAlias(true);
        this.mRectPaint.setStyle(Style.FILL);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.mScrollOffset = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mScrollOffset, dm);
        this.mIndicatorHeight = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mIndicatorHeight, dm);
        this.mUnderlineHeight = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mUnderlineHeight, dm);
        this.mDividerPadding = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mDividerPadding, dm);
        this.mTabPadding = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mTabPadding, dm);
        this.mDividerWidth = (int) TypedValue.applyDimension(PADDING_INDEX, (float) this.mDividerWidth, dm);
        this.mTabTextSize = (int) TypedValue.applyDimension(PADDING_LEFT_INDEX, (float) this.mTabTextSize, dm);
        this.mDividerPaint = new Paint();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setStrokeWidth((float) this.mDividerWidth);
        TypedArray a = context.obtainStyledAttributes(attrs, ANDROID_ATTRS);
        int textPrimaryColor = a.getColor(0, getResources().getColor(17170444));
        this.mUnderlineColor = textPrimaryColor;
        this.mDividerColor = textPrimaryColor;
        this.mIndicatorColor = textPrimaryColor;
        int padding = a.getDimensionPixelSize(PADDING_INDEX, 0);
        this.mPaddingLeft = padding > 0 ? padding : a.getDimensionPixelSize(PADDING_LEFT_INDEX, 0);
        if (padding <= 0) {
            padding = a.getDimensionPixelSize(PADDING_RIGHT_INDEX, 0);
        }
        this.mPaddingRight = padding;
        a.recycle();
        String tabTextTypefaceName = "sans-serif";
        if (VERSION.SDK_INT >= 21) {
            tabTextTypefaceName = "sans-serif-medium";
            this.mTabTextTypefaceStyle = 0;
        }
        a = context.obtainStyledAttributes(attrs, C0302R.styleable.PagerSlidingTabStrip);
        this.mIndicatorColor = a.getColor(C0302R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.mIndicatorColor);
        this.mIndicatorHeight = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.mIndicatorHeight);
        this.mUnderlineColor = a.getColor(C0302R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, this.mUnderlineColor);
        this.mUnderlineHeight = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.mUnderlineHeight);
        this.mDividerColor = a.getColor(C0302R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.mDividerColor);
        this.mDividerWidth = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsDividerWidth, this.mDividerWidth);
        this.mDividerPadding = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.mDividerPadding);
        this.isExpandTabs = a.getBoolean(C0302R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.isExpandTabs);
        this.mScrollOffset = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.mScrollOffset);
        this.isPaddingMiddle = a.getBoolean(C0302R.styleable.PagerSlidingTabStrip_pstsPaddingMiddle, this.isPaddingMiddle);
        this.mTabPadding = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.mTabPadding);
        this.mTabBackgroundResId = a.getResourceId(C0302R.styleable.PagerSlidingTabStrip_pstsTabBackground, this.mTabBackgroundResId);
        this.mTabTextSize = a.getDimensionPixelSize(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextSize, this.mTabTextSize);
        this.mTabTextColor = a.hasValue(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextColor) ? a.getColorStateList(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextColor) : null;
        this.mTabTextTypefaceStyle = a.getInt(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextStyle, this.mTabTextTypefaceStyle);
        this.isTabTextAllCaps = a.getBoolean(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextAllCaps, this.isTabTextAllCaps);
        int tabTextAlpha = a.getInt(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextAlpha, DEF_VALUE_TAB_TEXT_ALPHA);
        String fontFamily = a.getString(C0302R.styleable.PagerSlidingTabStrip_pstsTabTextFontFamily);
        a.recycle();
        if (this.mTabTextColor == null) {
            this.mTabTextColor = createColorStateList(textPrimaryColor, textPrimaryColor, Color.argb(tabTextAlpha, Color.red(textPrimaryColor), Color.green(textPrimaryColor), Color.blue(textPrimaryColor)));
        }
        if (fontFamily != null) {
            tabTextTypefaceName = fontFamily;
        }
        this.mTabTextTypeface = Typeface.create(tabTextTypefaceName, this.mTabTextTypefaceStyle);
        setTabsContainerParentViewPaddings();
        this.mTabLayoutParams = this.isExpandTabs ? new LayoutParams(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) : new LayoutParams(-2, -1);
    }

    private void setTabsContainerParentViewPaddings() {
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), this.mIndicatorHeight >= this.mUnderlineHeight ? this.mIndicatorHeight : this.mUnderlineHeight);
    }

    public void setViewPager(ViewPager pager) {
        this.mPager = pager;
        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.isCustomTabs = pager.getAdapter() instanceof CustomTabProvider;
        pager.setOnPageChangeListener(this.mPageListener);
        pager.getAdapter().registerDataSetObserver(this.mAdapterObserver);
        this.mAdapterObserver.setAttached(true);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        this.mTabsContainer.removeAllViews();
        this.mTabCount = this.mPager.getAdapter().getCount();
        for (int i = 0; i < this.mTabCount; i += PADDING_INDEX) {
            View tabView;
            if (this.isCustomTabs) {
                tabView = ((CustomTabProvider) this.mPager.getAdapter()).getCustomTabView(this, i);
            } else {
                tabView = LayoutInflater.from(getContext()).inflate(C0302R.layout.psts_tab, this, false);
            }
            addTab(i, this.mPager.getAdapter().getPageTitle(i), tabView);
        }
        updateTabStyles();
    }

    private void addTab(int position, CharSequence title, View tabView) {
        TextView textView = (TextView) tabView.findViewById(C0302R.id.psts_tab_title);
        if (!(textView == null || title == null)) {
            textView.setText(title);
        }
        tabView.setFocusable(true);
        tabView.setOnClickListener(new C02991(position));
        this.mTabsContainer.addView(tabView, position, this.mTabLayoutParams);
    }

    private void updateTabStyles() {
        for (int i = 0; i < this.mTabCount; i += PADDING_INDEX) {
            View v = this.mTabsContainer.getChildAt(i);
            v.setBackgroundResource(this.mTabBackgroundResId);
            v.setPadding(this.mTabPadding, v.getPaddingTop(), this.mTabPadding, v.getPaddingBottom());
            TextView tab_title = (TextView) v.findViewById(C0302R.id.psts_tab_title);
            if (tab_title != null) {
                tab_title.setTextColor(this.mTabTextColor);
                tab_title.setTypeface(this.mTabTextTypeface, this.mTabTextTypefaceStyle);
                tab_title.setTextSize(0, (float) this.mTabTextSize);
                if (this.isTabTextAllCaps) {
                    if (VERSION.SDK_INT >= 14) {
                        tab_title.setAllCaps(true);
                    } else {
                        tab_title.setText(tab_title.getText().toString().toUpperCase(getResources().getConfiguration().locale));
                    }
                }
            }
        }
    }

    private void scrollToChild(int position, int offset) {
        if (this.mTabCount != 0) {
            int newScrollX = this.mTabsContainer.getChildAt(position).getLeft() + offset;
            if (position > 0 || offset > 0) {
                newScrollX -= this.mScrollOffset;
                Pair<Float, Float> lines = getIndicatorCoordinates();
                newScrollX = (int) (((((Float) lines.second).floatValue() - ((Float) lines.first).floatValue()) / 2.0f) + ((float) newScrollX));
            }
            if (newScrollX != this.mLastScrollX) {
                this.mLastScrollX = newScrollX;
                scrollTo(newScrollX, 0);
            }
        }
    }

    private Pair<Float, Float> getIndicatorCoordinates() {
        View currentTab = this.mTabsContainer.getChildAt(this.mCurrentPosition);
        float lineLeft = (float) currentTab.getLeft();
        float lineRight = (float) currentTab.getRight();
        if (this.mCurrentPositionOffset > 0.0f && this.mCurrentPosition < this.mTabCount - 1) {
            View nextTab = this.mTabsContainer.getChildAt(this.mCurrentPosition + PADDING_INDEX);
            lineLeft = (this.mCurrentPositionOffset * ((float) nextTab.getLeft())) + ((DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.mCurrentPositionOffset) * lineLeft);
            lineRight = (this.mCurrentPositionOffset * ((float) nextTab.getRight())) + ((DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.mCurrentPositionOffset) * lineRight);
        }
        return new Pair(Float.valueOf(lineLeft), Float.valueOf(lineRight));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.isPaddingMiddle || this.mPaddingLeft > 0 || this.mPaddingRight > 0) {
            int width;
            if (this.isPaddingMiddle) {
                width = getWidth();
            } else {
                width = (getWidth() - this.mPaddingLeft) - this.mPaddingRight;
            }
            this.mTabsContainer.setMinimumWidth(width);
            setClipToPadding(false);
        }
        if (this.mTabsContainer.getChildCount() > 0) {
            this.mTabsContainer.getChildAt(0).getViewTreeObserver().addOnGlobalLayoutListener(this.firstTabGlobalLayoutListener);
        }
        super.onLayout(changed, l, t, r, b);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.mTabCount != 0) {
            int height = getHeight();
            if (this.mDividerWidth > 0) {
                this.mDividerPaint.setStrokeWidth((float) this.mDividerWidth);
                this.mDividerPaint.setColor(this.mDividerColor);
                for (int i = 0; i < this.mTabCount - 1; i += PADDING_INDEX) {
                    View tab = this.mTabsContainer.getChildAt(i);
                    canvas.drawLine((float) tab.getRight(), (float) this.mDividerPadding, (float) tab.getRight(), (float) (height - this.mDividerPadding), this.mDividerPaint);
                }
            }
            if (this.mUnderlineHeight > 0) {
                this.mRectPaint.setColor(this.mUnderlineColor);
                canvas.drawRect((float) this.mPaddingLeft, (float) (height - this.mUnderlineHeight), (float) (this.mTabsContainer.getWidth() + this.mPaddingRight), (float) height, this.mRectPaint);
            }
            if (this.mIndicatorHeight > 0) {
                this.mRectPaint.setColor(this.mIndicatorColor);
                Pair<Float, Float> lines = getIndicatorCoordinates();
                Canvas canvas2 = canvas;
                canvas2.drawRect(((float) this.mPaddingLeft) + ((Float) lines.first).floatValue(), (float) (height - this.mIndicatorHeight), ((float) this.mPaddingLeft) + ((Float) lines.second).floatValue(), (float) height, this.mRectPaint);
            }
        }
    }

    public void setOnTabReselectedListener(OnTabReselectedListener tabReselectedListener) {
        this.mTabReselectedListener = tabReselectedListener;
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.mDelegatePageListener = listener;
    }

    private void updateSelection(int position) {
        int i = 0;
        while (i < this.mTabCount) {
            View tv = this.mTabsContainer.getChildAt(i);
            if (i == position) {
                select(tv);
            } else {
                unSelect(tv);
            }
            i += PADDING_INDEX;
        }
    }

    private void unSelect(View tab) {
        if (tab != null) {
            TextView tab_title = (TextView) tab.findViewById(C0302R.id.psts_tab_title);
            if (tab_title != null) {
                tab_title.setSelected(false);
            }
            if (this.isCustomTabs) {
                ((CustomTabProvider) this.mPager.getAdapter()).tabUnselected(tab);
            }
        }
    }

    private void select(View tab) {
        if (tab != null) {
            TextView tab_title = (TextView) tab.findViewById(C0302R.id.psts_tab_title);
            if (tab_title != null) {
                tab_title.setSelected(true);
            }
            if (this.isCustomTabs) {
                ((CustomTabProvider) this.mPager.getAdapter()).tabSelected(tab);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mPager != null && !this.mAdapterObserver.isAttached()) {
            this.mPager.getAdapter().registerDataSetObserver(this.mAdapterObserver);
            this.mAdapterObserver.setAttached(true);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPager != null && this.mAdapterObserver.isAttached()) {
            this.mPager.getAdapter().unregisterDataSetObserver(this.mAdapterObserver);
            this.mAdapterObserver.setAttached(false);
        }
    }

    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPosition = savedState.currentPosition;
        if (this.mCurrentPosition != 0 && this.mTabsContainer.getChildCount() > 0) {
            unSelect(this.mTabsContainer.getChildAt(0));
            select(this.mTabsContainer.getChildAt(this.mCurrentPosition));
        }
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.mCurrentPosition;
        return savedState;
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public int getIndicatorHeight() {
        return this.mIndicatorHeight;
    }

    public int getUnderlineColor() {
        return this.mUnderlineColor;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getUnderlineHeight() {
        return this.mUnderlineHeight;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getScrollOffset() {
        return this.mScrollOffset;
    }

    public boolean getShouldExpand() {
        return this.isExpandTabs;
    }

    public int getTextSize() {
        return this.mTabTextSize;
    }

    public boolean isTextAllCaps() {
        return this.isTabTextAllCaps;
    }

    public ColorStateList getTextColor() {
        return this.mTabTextColor;
    }

    public int getTabBackground() {
        return this.mTabBackgroundResId;
    }

    public int getTabPaddingLeftRight() {
        return this.mTabPadding;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.mIndicatorColor = getResources().getColor(resId);
        invalidate();
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.mIndicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public void setUnderlineColor(int underlineColor) {
        this.mUnderlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.mUnderlineColor = getResources().getColor(resId);
        invalidate();
    }

    public void setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.mDividerColor = getResources().getColor(resId);
        invalidate();
    }

    public void setDividerWidth(int dividerWidthPx) {
        this.mDividerWidth = dividerWidthPx;
        invalidate();
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.mUnderlineHeight = underlineHeightPx;
        invalidate();
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.mDividerPadding = dividerPaddingPx;
        invalidate();
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.mScrollOffset = scrollOffsetPx;
        invalidate();
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.isExpandTabs = shouldExpand;
        if (this.mPager != null) {
            requestLayout();
        }
    }

    public void setAllCaps(boolean textAllCaps) {
        this.isTabTextAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.mTabTextSize = textSizePx;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        setTextColor(getResources().getColor(resId));
    }

    public void setTextColor(int textColor) {
        setTextColor(createColorStateList(textColor));
    }

    public void setTextColorStateListResource(int resId) {
        setTextColor(getResources().getColorStateList(resId));
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.mTabTextColor = colorStateList;
        updateTabStyles();
    }

    private ColorStateList createColorStateList(int color_state_default) {
        int[][] iArr = new int[PADDING_INDEX][];
        iArr[0] = new int[0];
        int[] iArr2 = new int[PADDING_INDEX];
        iArr2[0] = color_state_default;
        return new ColorStateList(iArr, iArr2);
    }

    private ColorStateList createColorStateList(int color_state_pressed, int color_state_selected, int color_state_default) {
        int[][] iArr = new int[PADDING_RIGHT_INDEX][];
        int[] iArr2 = new int[PADDING_INDEX];
        iArr2[0] = 16842919;
        iArr[0] = iArr2;
        iArr2 = new int[PADDING_INDEX];
        iArr2[0] = 16842913;
        iArr[PADDING_INDEX] = iArr2;
        iArr[PADDING_LEFT_INDEX] = new int[0];
        iArr2 = new int[PADDING_RIGHT_INDEX];
        iArr2[0] = color_state_pressed;
        iArr2[PADDING_INDEX] = color_state_selected;
        iArr2[PADDING_LEFT_INDEX] = color_state_default;
        return new ColorStateList(iArr, iArr2);
    }

    public void setTypeface(Typeface typeface, int style) {
        this.mTabTextTypeface = typeface;
        this.mTabTextTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.mTabBackgroundResId = resId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.mTabPadding = paddingPx;
        updateTabStyles();
    }
}
