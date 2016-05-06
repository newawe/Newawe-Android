package com.Newawe.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Newawe.model.WidgetEntity;

public interface ITabContentController extends InjectMainNavigationController {
    View createTabContent(LayoutInflater layoutInflater, ViewGroup viewGroup) throws Exception;

    void destroy();

    WidgetEntity getWidgetInfo();

    boolean onBackKeyDown();
}
