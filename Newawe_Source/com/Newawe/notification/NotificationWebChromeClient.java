package com.Newawe.notification;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

public class NotificationWebChromeClient extends WebChromeClient {
    public static final String WEB_VIEW_LOG_PREFIX = "webConsoleMessageN";

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(WEB_VIEW_LOG_PREFIX, String.format("%s @ %d: %s", new Object[]{consoleMessage.message(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.sourceId()}));
        return true;
    }
}
