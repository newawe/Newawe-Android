package com.Newawe.utils;

import android.content.res.AssetFileDescriptor;
import android.webkit.WebView;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang.StringUtils;

public class UrlConverter {
    private WebView _parentWebView;

    public UrlConverter(WebView parentWebView) {
        this._parentWebView = parentWebView;
    }

    public InputStream toStream(String url) {
        String absoluteUrl = toAbsolute(url);
        if (absoluteUrl.length() == 0) {
            return null;
        }
        try {
            if (absoluteUrl.startsWith("file:///")) {
                return this._parentWebView.getContext().getAssets().open(absoluteUrl.replace("file:///android_asset/", StringUtils.EMPTY));
            }
            return new URL(absoluteUrl).openStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AssetFileDescriptor toFileDescriptor(String url) {
        AssetFileDescriptor assetFileDescriptor = null;
        String absoluteUrl = toAbsolute(url);
        if (absoluteUrl.length() != 0) {
            try {
                if (absoluteUrl.startsWith("file:///")) {
                    assetFileDescriptor = this._parentWebView.getContext().getAssets().openFd(absoluteUrl.replace("file:///android_asset/", StringUtils.EMPTY));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return assetFileDescriptor;
    }

    public String toAbsolute(String url) {
        try {
            if (url.contains("://")) {
                return url;
            }
            String absoluteUrl = url;
            URL currentUrl = new URL(this._parentWebView.getUrl());
            if (url.startsWith("/")) {
                return currentUrl.getProtocol() + "://" + currentUrl.getHost() + url;
            }
            return currentUrl.getProtocol() + "://" + currentUrl.getFile().replaceAll("/[^/]+$", "/") + url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }
}
