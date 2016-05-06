package com.Newawe.utils;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import java.util.List;

public class Geolocation {
    public static double[] getCoords(Activity activity) {
        Location l = getLocation(activity);
        double[] gps = new double[2];
        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return gps;
    }

    public static Location getLocation(Activity activity) {
        try {
            LocationManager lm = (LocationManager) activity.getSystemService("location");
            List<String> providers = lm.getProviders(true);
            Location l = null;
            for (int i = providers.size() - 1; i >= 0; i--) {
                l = lm.getLastKnownLocation((String) providers.get(i));
                if (l != null) {
                    return l;
                }
            }
            return l;
        } catch (Exception e) {
            return null;
        }
    }
}
