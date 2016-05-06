package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }
        switch (info.getType()) {
            case DurationDV.DURATION_TYPE /*0*/:
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.SERVICE_DISABLED /*3*/:
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                return true;
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return false;
            default:
                return true;
        }
    }
}
