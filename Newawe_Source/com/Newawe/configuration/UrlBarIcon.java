package com.Newawe.configuration;

import com.Newawe.configuration.UrlBarMenuButton.UrlBarMenuButtonTypes;
import java.io.Serializable;

public class UrlBarIcon extends UrlBarMenuLinkButton implements Serializable {
    private String icon;

    public UrlBarIcon(String title, String url, String icon) {
        super(title, url);
        setType(UrlBarMenuButtonTypes.ICON);
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
