package com.Newawe.controllers;

public interface ITabsController {
    void destroy();

    WebContentController getSelectedTab();

    void initWithTabs(WidgetsController widgetsController);

    boolean onBackKeyDown();
}
