package com.Newawe.suggestions;

import org.apache.commons.lang.StringUtils;

public class LocalSuggestionItem implements SuggestionItem {
    String title;
    String url;

    public LocalSuggestionItem(String title, String url) {
        if (title == null) {
            title = StringUtils.EMPTY;
        }
        this.title = title;
        if (url == null) {
            url = StringUtils.EMPTY;
        }
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAutocompleteText() {
        return this.url;
    }
}
