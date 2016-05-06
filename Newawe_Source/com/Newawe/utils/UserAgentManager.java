package com.Newawe.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Newawe.C0186R;
import java.lang.reflect.Constructor;

public class UserAgentManager {

    @TargetApi(17)
    static class NewApiWrapper {
        NewApiWrapper() {
        }

        static String getDefaultUserAgent(Context context) {
            return WebSettings.getDefaultUserAgent(context);
        }
    }

    public static String getDesktopUserAgent(Context context) {
        return context.getString(C0186R.string.chromeUserAgent);
    }

    public static String getDefaultUserAgent(Context context) {
        if (VERSION.SDK_INT >= 17) {
            return NewApiWrapper.getDefaultUserAgent(context);
        }
        Constructor<WebSettings> constructor;
        try {
            constructor = WebSettings.class.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
            constructor.setAccessible(true);
            String userAgentString = ((WebSettings) constructor.newInstance(new Object[]{context, null})).getUserAgentString();
            constructor.setAccessible(false);
            return userAgentString;
        } catch (Exception e) {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable th) {
            constructor.setAccessible(false);
        }
    }
}
