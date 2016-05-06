package com.Newawe.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;

public class DefaultUrlsHolder {
    private static DefaultUrlsHolder instance;
    private ArrayList<String> domains;
    private ArrayList<String> urls;

    public static DefaultUrlsHolder getInstance() {
        if (instance == null) {
            instance = new DefaultUrlsHolder();
        }
        return instance;
    }

    private DefaultUrlsHolder() {
        this.urls = null;
        this.domains = null;
        this.urls = new ArrayList();
        this.domains = new ArrayList();
    }

    private String convertUrl(String string) {
        if (string == null || string.length() <= 0) {
            return string;
        }
        string = string.trim();
        if (string.charAt(string.length() - 1) == '/') {
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    public String[] getUrls() {
        return (String[]) this.urls.toArray();
    }

    public boolean containsUrl(String url) {
        return this.urls.contains(convertUrl(url));
    }

    public boolean isDefaultUrl(String url) {
        if (url.startsWith("file://")) {
            return true;
        }
        return this.urls.contains(convertUrl(url));
    }

    public boolean isPermittedDomain(String url) {
        if (url.startsWith("file://")) {
            return true;
        }
        String host = _getDomainFromUrl(url);
        if (host == null || host.equals(StringUtils.EMPTY) || !_isDefaultHost(host)) {
            return false;
        }
        return true;
    }

    public void addUrl(String url) {
        if (url != null) {
            url = convertUrl(url);
            if (!containsUrl(url)) {
                this.urls.add(url);
            }
            String host = _getDomainFromUrl(url);
            if (host != null && !this.domains.contains(host)) {
                this.domains.add(host.toLowerCase());
            }
        }
    }

    private boolean _isDefaultHost(String host) {
        host = host.toLowerCase();
        Iterator i$ = this.domains.iterator();
        while (i$.hasNext()) {
            if (host.contains((String) i$.next())) {
                return true;
            }
        }
        return false;
    }

    private String _getDomainFromUrl(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (uri != null) {
            return uri.getHost();
        }
        return null;
    }
}
