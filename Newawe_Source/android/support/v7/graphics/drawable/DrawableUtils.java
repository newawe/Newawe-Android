package android.support.v7.graphics.drawable;

import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;

public class DrawableUtils {
    public static Mode parseTintMode(int value, Mode defaultMode) {
        switch (value) {
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return Mode.SRC_OVER;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                return Mode.SRC_IN;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                return Mode.SRC_ATOP;
            case ConnectionResult.TIMEOUT /*14*/:
                return Mode.MULTIPLY;
            case ConnectionResult.INTERRUPTED /*15*/:
                return Mode.SCREEN;
            case ConnectionResult.API_UNAVAILABLE /*16*/:
                if (VERSION.SDK_INT >= 11) {
                    return Mode.valueOf("ADD");
                }
                return defaultMode;
            default:
                return defaultMode;
        }
    }
}
