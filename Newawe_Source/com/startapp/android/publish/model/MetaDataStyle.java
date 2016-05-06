package com.startapp.android.publish.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public class MetaDataStyle implements Serializable {
    public static final int DEFAULT_ITEM_BOTTOM = -8750199;
    public static final Integer DEFAULT_ITEM_DESC_TEXT_COLOR;
    public static final Set<String> DEFAULT_ITEM_DESC_TEXT_DECORATION;
    public static final Integer DEFAULT_ITEM_DESC_TEXT_SIZE;
    public static final Integer DEFAULT_ITEM_TITLE_TEXT_COLOR;
    public static final Set<String> DEFAULT_ITEM_TITLE_TEXT_DECORATION;
    public static final Integer DEFAULT_ITEM_TITLE_TEXT_SIZE;
    public static final int DEFAULT_ITEM_TOP = -14014151;
    private static final long serialVersionUID = 1;
    private Integer itemDescriptionTextColor;
    private Set<String> itemDescriptionTextDecoration;
    private Integer itemDescriptionTextSize;
    private Integer itemGradientBottom;
    private Integer itemGradientTop;
    private Integer itemTitleTextColor;
    private Set<String> itemTitleTextDecoration;
    private Integer itemTitleTextSize;
    private String name;

    public MetaDataStyle() {
        this.name = StringUtils.EMPTY;
        this.itemGradientTop = Integer.valueOf(DEFAULT_ITEM_TOP);
        this.itemGradientBottom = Integer.valueOf(DEFAULT_ITEM_BOTTOM);
        this.itemTitleTextSize = DEFAULT_ITEM_TITLE_TEXT_SIZE;
        this.itemTitleTextColor = DEFAULT_ITEM_TITLE_TEXT_COLOR;
        this.itemTitleTextDecoration = DEFAULT_ITEM_TITLE_TEXT_DECORATION;
        this.itemDescriptionTextSize = DEFAULT_ITEM_DESC_TEXT_SIZE;
        this.itemDescriptionTextColor = DEFAULT_ITEM_DESC_TEXT_COLOR;
        this.itemDescriptionTextDecoration = DEFAULT_ITEM_DESC_TEXT_DECORATION;
    }

    static {
        DEFAULT_ITEM_TITLE_TEXT_SIZE = Integer.valueOf(18);
        DEFAULT_ITEM_TITLE_TEXT_COLOR = Integer.valueOf(-1);
        DEFAULT_ITEM_TITLE_TEXT_DECORATION = new HashSet(Arrays.asList(new String[]{MetaData.TEXT_DECORATION_BOLD}));
        DEFAULT_ITEM_DESC_TEXT_SIZE = Integer.valueOf(14);
        DEFAULT_ITEM_DESC_TEXT_COLOR = Integer.valueOf(-1);
        DEFAULT_ITEM_DESC_TEXT_DECORATION = new HashSet();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemGradientTop() {
        return this.itemGradientTop;
    }

    public Integer getItemGradientBottom() {
        return this.itemGradientBottom;
    }

    public Integer getItemTitleTextSize() {
        return this.itemTitleTextSize;
    }

    public Integer getItemTitleTextColor() {
        return this.itemTitleTextColor;
    }

    public Set<String> getItemTitleTextDecoration() {
        return this.itemTitleTextDecoration;
    }

    public Integer getItemDescriptionTextSize() {
        return this.itemDescriptionTextSize;
    }

    public Integer getItemDescriptionTextColor() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> getItemDescriptionTextDecoration() {
        return this.itemDescriptionTextDecoration;
    }

    public void setItemGradientTop(Integer itemGradientTop) {
        this.itemGradientTop = itemGradientTop;
    }

    public void setItemGradientBottom(Integer itemGradientBottom) {
        this.itemGradientBottom = itemGradientBottom;
    }

    public void setItemTitleTextSize(Integer itemTitleTextSize) {
        this.itemTitleTextSize = itemTitleTextSize;
    }

    public void setItemTitleTextColor(Integer itemTitleTextColor) {
        this.itemTitleTextColor = itemTitleTextColor;
    }

    public void setItemTitleTextDecoration(Set<String> itemTitleTextDecoration) {
        this.itemTitleTextDecoration = itemTitleTextDecoration;
    }

    public void setItemDescriptionTextSize(Integer itemDescriptionTextSize) {
        this.itemDescriptionTextSize = itemDescriptionTextSize;
    }

    public void setItemDescriptionTextColor(Integer itemDescriptionTextColor) {
        this.itemDescriptionTextColor = itemDescriptionTextColor;
    }

    public void setItemDescriptionTextDecoration(Set<String> itemDescriptionTextDecoration) {
        this.itemDescriptionTextDecoration = itemDescriptionTextDecoration;
    }
}
