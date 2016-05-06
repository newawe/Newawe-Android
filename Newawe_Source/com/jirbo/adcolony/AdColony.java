package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.os.EnvironmentCompat;
import android.view.ViewGroup;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.jirbo.adcolony.ADCData.C0721i;
import com.jirbo.adcolony.ADCData.C1240g;
import com.jirbo.adcolony.C0807n.ad;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

public class AdColony {
    static boolean f1829b;
    static boolean f1830c;
    boolean f1831a;

    /* renamed from: com.jirbo.adcolony.AdColony.1 */
    static class C07271 implements Runnable {
        C07271() {
        }

        public void run() {
            C0745a.f1971H = false;
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColony.2 */
    static class C07292 implements Runnable {
        final /* synthetic */ Activity f1825a;

        /* renamed from: com.jirbo.adcolony.AdColony.2.1 */
        class C07281 implements Runnable {
            final /* synthetic */ C07292 f1824a;

            C07281(C07292 c07292) {
                this.f1824a = c07292;
            }

            public void run() {
                for (int i = 0; i < C0745a.aq.size(); i++) {
                    AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) C0745a.aq.get(i);
                    if (!(adColonyNativeAdView == null || C0745a.m2148b() != adColonyNativeAdView.f1935d || adColonyNativeAdView.f1952u)) {
                        adColonyNativeAdView.f1909A = false;
                        adColonyNativeAdView.invalidate();
                        if (adColonyNativeAdView.f1928T != null) {
                            adColonyNativeAdView.f1928T.f1907a = false;
                            adColonyNativeAdView.f1928T.invalidate();
                        }
                    }
                }
            }
        }

        C07292(Activity activity) {
            this.f1825a = activity;
        }

        public void run() {
            this.f1825a.runOnUiThread(new C07281(this));
        }
    }

    /* renamed from: com.jirbo.adcolony.AdColony.a */
    private static class C0730a extends AsyncTask<Void, Void, Void> {
        Activity f1826a;
        String f1827b;
        boolean f1828c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2113a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m2114a((Void) obj);
        }

        C0730a(Activity activity) {
            this.f1827b = StringUtils.EMPTY;
            this.f1826a = activity;
        }

        protected Void m2113a(Void... voidArr) {
            try {
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f1826a);
                this.f1827b = advertisingIdInfo.getId();
                this.f1828c = advertisingIdInfo.isLimitAdTrackingEnabled();
            } catch (NoClassDefFoundError e) {
                C0777l.f2242d.m2353b((Object) "Google Play Services SDK not installed! Collecting Android Id instead of Advertising Id.");
            } catch (NoSuchMethodError e2) {
                C0777l.f2242d.m2353b((Object) "Google Play Services SDK is out of date! Collecting Android Id instead of Advertising Id.");
            } catch (Exception e3) {
                if (!Build.MANUFACTURER.equals("Amazon")) {
                    C0777l.f2242d.m2353b((Object) "Advertising Id not available! Collecting Android Id instead of Advertising Id.");
                    e3.printStackTrace();
                }
            }
            return null;
        }

        protected void m2114a(Void voidR) {
            C0772g.f2202a = this.f1827b;
            C0772g.f2203b = this.f1828c;
            AdColony.f1830c = true;
        }
    }

    public AdColony() {
        this.f1831a = false;
    }

    static {
        f1829b = true;
    }

    public static void disable() {
        C0745a.f2014y = true;
    }

    public static void configure(Activity activity, String client_options, String app_id, String... zone_ids) {
        f1830c = false;
        if (f1829b) {
            f1829b = false;
            if (VERSION.SDK_INT >= 11) {
                new C0730a(activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                new C0730a(activity).execute(new Void[0]);
            }
            C0745a.aq.clear();
            Handler handler = new Handler();
            Runnable c07271 = new C07271();
            if (!C0745a.f1971H || C0745a.f1972I) {
                if (!C0745a.f2014y) {
                    if (app_id == null) {
                        C0745a.m2143a("Null App ID - disabling AdColony.");
                        return;
                    } else if (zone_ids == null) {
                        C0745a.m2143a("Null Zone IDs array - disabling AdColony.");
                        return;
                    } else if (zone_ids.length == 0) {
                        C0745a.m2143a("No Zone IDs provided - disabling AdColony.");
                        return;
                    } else {
                        C0745a.m2149b(activity);
                        C0745a.f2001l.m2253a(client_options, app_id, zone_ids);
                        C0745a.f2012w = true;
                        C0745a.f1971H = true;
                        handler.postDelayed(c07271, 120000);
                    }
                } else {
                    return;
                }
            }
            if (C0745a.f1984U == null) {
                C0745a.f1968E = true;
            }
            C0745a.ao.clear();
            C0745a.ap.clear();
            C0745a.ar = new HashMap();
            for (Object put : zone_ids) {
                C0745a.ar.put(put, Boolean.valueOf(false));
            }
            return;
        }
        C0745a.ao.clear();
        C0745a.ap.clear();
    }

    public static void setCustomID(String new_custom_id) {
        if (!new_custom_id.equals(C0745a.f2001l.f2141a.f2137y) && C0745a.f2001l != null && C0745a.f2001l.f2142b != null) {
            C0745a.f2001l.f2141a.f2137y = new_custom_id;
            if (C0745a.f2013x) {
                C0745a.f2001l.f2142b.m4707h();
            }
        }
    }

    public static String getCustomID() {
        return C0745a.f2001l.f2141a.f2137y;
    }

    public static boolean isConfigured() {
        return !f1829b;
    }

    public static void setDeviceID(String new_device_id) {
        if (!new_device_id.equals(C0745a.f2001l.f2141a.f2138z)) {
            C0745a.f2001l.f2141a.f2138z = new_device_id;
            C0745a.f1971H = false;
            C0745a.f2001l.f2142b.f3995d = true;
            C0745a.f2001l.f2142b.f3993b = false;
            C0745a.f2001l.f2142b.f3994c = true;
        }
    }

    public static String getDeviceID() {
        return C0745a.f2001l.f2141a.f2138z;
    }

    public static boolean isTablet() {
        return C0772g.m2306i();
    }

    public static void resume(Activity activity) {
        C0777l.f2241c.m2353b((Object) "[ADC] AdColony resume called.");
        C0745a.f1965B = false;
        C0745a.f2007r = false;
        C0745a.m2138a(activity);
        C0745a.f1964A = false;
        C0745a.m2163h();
        if (activity == null) {
            C0777l.f2242d.m2353b((Object) "Activity reference is null. Disabling AdColony.");
            disable();
            return;
        }
        if (C0745a.f2011v != null) {
            C0745a.f1986W.m2133a(C0745a.f2011v);
            C0745a.f2011v = null;
        }
        new Thread(new C07292(activity)).start();
        C0745a.f1976M = false;
    }

    public static void pause() {
        C0777l.f2241c.m2353b((Object) "[ADC] AdColony pause called.");
        C0745a.f2007r = true;
        C0745a.f1965B = true;
        for (int i = 0; i < C0745a.aq.size(); i++) {
            if (C0745a.aq.get(i) != null) {
                AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) C0745a.aq.get(i);
                adColonyNativeAdView.f1909A = true;
                if (!(adColonyNativeAdView.ag == null || adColonyNativeAdView.f1952u || !adColonyNativeAdView.ag.isPlaying())) {
                    if (C0745a.f1968E) {
                        adColonyNativeAdView.f1928T.setVisibility(0);
                    }
                    adColonyNativeAdView.m2131c();
                }
            }
        }
    }

    public static void onBackPressed() {
        int i = 0;
        if (C0745a.f1982S == null) {
            return;
        }
        if ((C0745a.f1982S instanceof ab) || (C0745a.f1982S instanceof ac)) {
            ((ViewGroup) C0745a.f1982S.getParent()).removeView(C0745a.f1982S);
            C0745a.f1968E = true;
            C0745a.f1982S.f2216G.m4691c(false);
            while (i < C0745a.an.size()) {
                ((Bitmap) C0745a.an.get(i)).recycle();
                i++;
            }
            C0745a.an.clear();
            C0745a.f1982S = null;
        }
    }

    public static Activity activity() {
        return C0745a.m2148b();
    }

    public static boolean isZoneV4VC(String zone_id) {
        if (C0745a.f2001l == null || C0745a.f2001l.f2142b == null || C0745a.f2001l.f2142b.f4000i == null || C0745a.f2001l.f2142b.f4000i.f2378n == null) {
            return false;
        }
        return C0745a.f2001l.f2142b.m4697a(zone_id, false);
    }

    public static boolean isZoneNative(String zone_id) {
        if (C0745a.f2001l == null || C0745a.f2001l.f2142b == null || C0745a.f2001l.f2142b.f4000i == null || C0745a.f2001l.f2142b.f4000i.f2378n == null || C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id) == null || C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id).f2316m == null || C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id).f2316m.f2364a == null) {
            return false;
        }
        for (int i = 0; i < C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id).f2316m.f2364a.size(); i++) {
            if (C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id).f2316m.m2421a(i).f2255A.f2458a) {
                return true;
            }
        }
        return false;
    }

    public static void addV4VCListener(AdColonyV4VCListener listener) {
        if (!C0745a.ao.contains(listener)) {
            C0745a.ao.add(listener);
        }
    }

    public static void removeV4VCListener(AdColonyV4VCListener listener) {
        C0745a.ao.remove(listener);
    }

    public static void addAdAvailabilityListener(AdColonyAdAvailabilityListener listener) {
        if (!C0745a.ap.contains(listener)) {
            C0745a.ap.add(listener);
        }
    }

    public static void removeAdAvailabilityListener(AdColonyAdAvailabilityListener listener) {
        C0745a.ap.remove(listener);
    }

    public static void notifyIAPComplete(String product_id, String trans_id) {
        notifyIAPComplete(product_id, trans_id, null, 0.0d);
    }

    public static void notifyIAPComplete(String product_id, String trans_id, String currency_code, double price) {
        C0777l.f2241c.m2353b((Object) "notifyIAPComplete() called.");
        C0721i c1240g = new C1240g();
        c1240g.m4655b("product_id", product_id);
        if (price != 0.0d) {
            c1240g.m4653b("price", price);
        }
        c1240g.m4655b("trans_id", trans_id);
        c1240g.m4654b("quantity", 1);
        if (currency_code != null) {
            c1240g.m4655b("price_currency_code", currency_code);
        }
        if (C0745a.f1978O) {
            C0745a.f2001l.f2144d.m4730a("in_app_purchase", (C1240g) c1240g);
        } else {
            C0745a.aj.m4612a(c1240g);
        }
    }

    public static void cancelVideo() {
        if (C0745a.f1984U != null) {
            C0745a.f1984U.finish();
            C0745a.ak = true;
            C0745a.f1986W.m2134b(null);
        }
    }

    public static String statusForZone(String zone_id) {
        if (C0745a.f2001l == null || C0745a.f2001l.f2142b == null || C0745a.f2001l.f2142b.f4000i == null || C0745a.f2001l.f2142b.f4000i.f2378n == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (C0745a.f2014y) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        ad a = C0745a.f2001l.f2142b.f4000i.f2378n.m2412a(zone_id);
        if (a != null) {
            if (!a.f2310g) {
                return "off";
            }
            if (a.f2311h && C0745a.f2001l.f2142b.m4702c(zone_id, true)) {
                return "active";
            }
            return "loading";
        } else if (C0745a.f2013x) {
            return "invalid";
        } else {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void get_images(String zone_id) {
        C0745a.f2001l.f2141a.m2240b(zone_id);
    }

    public static void disableDECOverride() {
        C0745a.f1994e = null;
    }

    public static void forceMobileCache() {
        if (!C0745a.f1977N) {
            C0745a.f1977N = true;
            C0745a.f1971H = false;
            C0745a.f2001l.f2142b.f3995d = true;
            C0745a.f2001l.f2142b.f3993b = false;
            C0745a.f2001l.f2142b.f3994c = true;
        }
    }
}
