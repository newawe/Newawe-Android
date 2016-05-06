package com.Newawe.plugins;

import android.content.Context;
import android.webkit.WebView;

public class PluginsLoader {
    public static void loadPlugins(Context context, WebView webView) {
        for (IApplicationPlugin plugin : new IApplicationPlugin[0]) {
            plugin.load(context, webView);
        }
    }
}
