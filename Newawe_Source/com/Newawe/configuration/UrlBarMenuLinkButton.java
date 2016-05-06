package com.Newawe.configuration;

import com.Newawe.configuration.UrlBarMenuButton.UrlBarMenuButtonTypes;
import java.io.Serializable;

public class UrlBarMenuLinkButton extends UrlBarMenuButton implements Serializable {
    private String title;
    private String url;

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    UrlBarMenuLinkButton(String title, String url) {
        super(UrlBarMenuButtonTypes.LINK);
        this.title = title;
        this.url = url;
    }
}
