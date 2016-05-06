package com.Newawe.configuration;

import java.io.Serializable;

public class UrlBarMenuButton implements Serializable {
    private static final long serialVersionUID = 1;
    private UrlBarMenuButtonTypes type;

    public enum UrlBarMenuButtonTypes {
        BACK,
        FORWARD,
        REFRESH,
        REQUEST_DESKTOP,
        PIN_TO_DESKTOP,
        ADD_TO_HOME,
        HOME,
        URL_BUTTON,
        LINK,
        ICON
    }

    UrlBarMenuButton(UrlBarMenuButtonTypes type) {
        this.type = type;
    }

    public UrlBarMenuButtonTypes getType() {
        return this.type;
    }

    public void setType(UrlBarMenuButtonTypes type) {
        this.type = type;
    }
}
