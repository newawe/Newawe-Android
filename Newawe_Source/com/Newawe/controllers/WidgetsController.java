package com.Newawe.controllers;

import com.Newawe.model.WidgetEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class WidgetsController implements Serializable {
    private Vector<WidgetEntity> _widgetCollection;

    class WidgetEntityComparator implements Comparator<WidgetEntity> {
        WidgetEntityComparator() {
        }

        public int compare(WidgetEntity o1, WidgetEntity o2) {
            String id1 = o1.getId();
            String id2 = o2.getId();
            if (id1.indexOf("tab") == 0 && id2.indexOf("tab") == 0) {
                return Integer.valueOf(Integer.parseInt(id1.substring(3))).compareTo(Integer.valueOf(Integer.parseInt(id2.substring(3))));
            }
            return o1.getId().compareTo(o2.getId());
        }
    }

    public WidgetsController() {
        this._widgetCollection = new Vector();
    }

    public void addWidget(WidgetEntity wdgt) {
        this._widgetCollection.add(wdgt);
        Collections.sort(this._widgetCollection, new WidgetEntityComparator());
    }

    public WidgetEntity getWidgetById(String wdgtId) {
        Enumeration<WidgetEntity> enumerator = this._widgetCollection.elements();
        while (enumerator.hasMoreElements()) {
            WidgetEntity weCurrent = (WidgetEntity) enumerator.nextElement();
            if (weCurrent.getId().equals(wdgtId)) {
                return weCurrent;
            }
        }
        return null;
    }

    public WidgetEntity getWidgetByPosition(int position) {
        try {
            return (WidgetEntity) this._widgetCollection.get(position);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void removeWidgetById(String wdgtId) {
        Iterator<WidgetEntity> iterator = this._widgetCollection.iterator();
        while (iterator.hasNext()) {
            if (((WidgetEntity) iterator.next()).getId().equals(wdgtId)) {
                iterator.remove();
            }
        }
    }

    public void removeAll() {
        this._widgetCollection.removeAllElements();
    }

    public Enumeration<WidgetEntity> getEnumeration() {
        return this._widgetCollection.elements();
    }

    public int widgetsCount() {
        return this._widgetCollection.size();
    }

    public WidgetEntity getWidgetByTabId(String tabId) {
        Enumeration<WidgetEntity> enumerator = this._widgetCollection.elements();
        while (enumerator.hasMoreElements()) {
            WidgetEntity weCurrent = (WidgetEntity) enumerator.nextElement();
            if (weCurrent.getTabId().equalsIgnoreCase(tabId)) {
                return weCurrent;
            }
        }
        return null;
    }

    public int tabsCount() {
        int i = 0;
        Iterator i$ = this._widgetCollection.iterator();
        while (i$.hasNext()) {
            if (((WidgetEntity) i$.next()).isShowAsTab()) {
                i++;
            }
        }
        return i;
    }

    public WidgetEntity getTabByPosition(int position) {
        int i = 0;
        Iterator i$ = this._widgetCollection.iterator();
        while (i$.hasNext()) {
            WidgetEntity entity = (WidgetEntity) i$.next();
            if (entity.isShowAsTab()) {
                if (i == position) {
                    return entity;
                }
                i++;
            }
        }
        return null;
    }
}
