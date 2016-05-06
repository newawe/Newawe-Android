package com.Newawe.controllers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ContentSwipeAwareViewPager extends ViewPager {
    public ContentSwipeAwareViewPager(Context context) {
        super(context);
    }

    public ContentSwipeAwareViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getChildCount() == 1) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 1) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
