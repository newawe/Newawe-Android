package com.Newawe.storage;

public class BrowsingHistoryItem {
    private String date;
    private String id;
    private String title;
    private String url;

    public BrowsingHistoryItem(String id, String date, String title, String url) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.url = url;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
