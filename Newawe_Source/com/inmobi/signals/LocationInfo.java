package com.inmobi.signals;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.p003c.TelemetryEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.PublisherProvidedUserInfo;
import com.inmobi.commons.p000a.SdkContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.inmobi.signals.n */
public class LocationInfo {
    private static final String f1758a;
    private static LocationInfo f1759b;
    private static Object f1760c;
    private static boolean f1761d;
    private static LocationManager f1762e;
    private static Object f1763f;
    private static boolean f1764g;

    /* renamed from: com.inmobi.signals.n.a */
    private static class LocationInfo implements InvocationHandler {
        private LocationInfo() {
        }

        public void m1980a(Bundle bundle) {
            Logger.m1440a(InternalLogLevel.INTERNAL, LocationInfo.f1758a, "Successfully connected to Google API client.");
            LocationInfo.f1764g = true;
        }

        public void m1979a(int i) {
            LocationInfo.f1764g = false;
            Logger.m1440a(InternalLogLevel.INTERNAL, LocationInfo.f1758a, "Google API client connection suspended");
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str = "onConnected";
            str = "onConnectionSuspended";
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    m1980a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    m1979a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    static {
        f1758a = LocationInfo.class.getSimpleName();
        f1760c = new Object();
        f1761d = false;
        f1763f = null;
        f1764g = false;
    }

    public static LocationInfo m1981a() {
        LocationInfo locationInfo = f1759b;
        if (locationInfo == null) {
            synchronized (f1760c) {
                locationInfo = f1759b;
                if (locationInfo == null) {
                    f1759b = new LocationInfo();
                    locationInfo = f1759b;
                }
            }
        }
        return locationInfo;
    }

    private LocationInfo() {
        f1762e = (LocationManager) SdkContext.m1258b().getSystemService("location");
    }

    void m1992b() {
        if (f1761d && GoogleApiClientWrapper.m1949a()) {
            m1984a(SdkContext.m1258b());
        }
    }

    private void m1984a(Context context) {
        if (f1763f == null) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1758a, "Connecting Google API client for location.");
            f1763f = GoogleApiClientWrapper.m1947a(context, new LocationInfo(), new LocationInfo(), "com.google.android.gms.location.LocationServices");
            GoogleApiClientWrapper.m1948a(f1763f);
        }
    }

    public synchronized HashMap<String, Object> m1993c() {
        return m1983a(m1988g(), true);
    }

    public synchronized HashMap<String, String> m1994d() {
        HashMap<String, String> hashMap;
        hashMap = new HashMap();
        Location g = m1988g();
        HashMap a;
        if (g != null) {
            a = m1983a(g, true);
        } else {
            a = m1983a(PublisherProvidedUserInfo.m1512d(), false);
        }
        for (Entry entry : r0.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public void m1991a(boolean z) {
        f1761d = z;
    }

    private boolean m1987f() {
        Context b = SdkContext.m1258b();
        if (f1762e == null) {
            return false;
        }
        boolean z;
        boolean isProviderEnabled;
        if (b.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            isProviderEnabled = f1762e.isProviderEnabled("gps");
            z = false;
        } else if (b.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            z = f1762e.isProviderEnabled("network");
            isProviderEnabled = false;
        } else {
            isProviderEnabled = false;
            z = false;
        }
        if (z || r1) {
            return true;
        }
        return false;
    }

    private Location m1988g() {
        boolean z = true;
        Context b = SdkContext.m1258b();
        Location location = null;
        if (f1761d && m1987f()) {
            if (f1764g) {
                Location h = m1989h();
                Logger.m1440a(InternalLogLevel.INTERNAL, f1758a, "Location info provided by Google Api client:" + (h != null));
                location = h;
            }
            if (location == null && f1762e != null) {
                Criteria criteria = new Criteria();
                if (b.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                    criteria.setAccuracy(1);
                } else if (b.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                    criteria.setAccuracy(2);
                }
                criteria.setCostAllowed(false);
                String bestProvider = f1762e.getBestProvider(criteria, true);
                if (bestProvider != null) {
                    location = f1762e.getLastKnownLocation(bestProvider);
                    if (location == null) {
                        location = m1990i();
                    }
                }
                InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
                String str = f1758a;
                StringBuilder append = new StringBuilder().append("Location info provided by Location manager:");
                if (location == null) {
                    z = false;
                }
                Logger.m1440a(internalLogLevel, str, append.append(z).toString());
            }
        }
        if (location == null) {
            TelemetryComponent.m4448a().m4468a(new TelemetryEvent("signals", "LocationFixFailed"));
        }
        return location;
    }

    private Location m1989h() {
        try {
            Field declaredField = Class.forName("com.google.android.gms.location.LocationServices").getDeclaredField("FusedLocationApi");
            Class cls = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
            return (Location) Class.forName("com.google.android.gms.location.FusedLocationProviderApi").getMethod("getLastLocation", new Class[]{cls}).invoke(declaredField.get(null), new Object[]{f1763f});
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1758a, "Unable to request activity updates from ActivityRecognition client", e);
            return null;
        } catch (Throwable e2) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1758a, "Unable to request activity updates from ActivityRecognition client", e2);
            return null;
        } catch (Throwable e22) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1758a, "Unable to request activity updates from ActivityRecognition client", e22);
            return null;
        } catch (Throwable e222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1758a, "Unable to request activity updates from ActivityRecognition client", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1758a, "Unable to request activity updates from ActivityRecognition client", e2222);
            return null;
        }
    }

    private Location m1990i() {
        if (f1762e == null) {
            return null;
        }
        List providers = f1762e.getProviders(true);
        int size = providers.size() - 1;
        Location location = null;
        while (size >= 0) {
            Location lastKnownLocation;
            String str = (String) providers.get(size);
            if (f1762e.isProviderEnabled(str)) {
                lastKnownLocation = f1762e.getLastKnownLocation(str);
                if (lastKnownLocation != null) {
                    return lastKnownLocation;
                }
            } else {
                lastKnownLocation = location;
            }
            size--;
            location = lastKnownLocation;
        }
        return location;
    }

    private HashMap<String, Object> m1983a(Location location, boolean z) {
        int i = 1;
        HashMap<String, Object> hashMap = new HashMap();
        if (location != null) {
            if (location.getTime() > 0) {
                hashMap.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            hashMap.put("u-latlong-accu", m1982a(location));
            hashMap.put("sdk-collected", Integer.valueOf(z ? 1 : 0));
        }
        if (f1761d) {
            String str = "loc-allowed";
            if (!m1987f()) {
                i = 0;
            }
            hashMap.put(str, Integer.valueOf(i));
        }
        return hashMap;
    }

    private String m1982a(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append((int) location.getAccuracy());
        return stringBuilder.toString();
    }
}
