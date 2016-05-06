package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Base64;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0314a;
import com.chartboost.sdk.Libraries.C0323e;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.C0328g;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.az.C0399c;
import com.chartboost.sdk.impl.ba;
import java.io.File;
import java.util.EnumMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpStatus;

public class CBAnalytics {

    public enum CBIAPPurchaseInfo {
        PRODUCT_ID,
        PRODUCT_TITLE,
        PRODUCT_DESCRIPTION,
        PRODUCT_PRICE,
        PRODUCT_CURRENCY_CODE,
        GOOGLE_PURCHASE_DATA,
        GOOGLE_PURCHASE_SIGNATURE,
        AMAZON_PURCHASE_TOKEN,
        AMAZON_USER_ID
    }

    public enum CBIAPType {
        GOOGLE_PLAY,
        AMAZON
    }

    public enum CBLevelType {
        HIGHEST_LEVEL_REACHED(1),
        CURRENT_AREA(2),
        CHARACTER_LEVEL(3),
        OTHER_SEQUENTIAL(4),
        OTHER_NONSEQUENTIAL(5);
        
        private int f265a;

        private CBLevelType(int value) {
            this.f265a = value;
        }

        public int getLevelType() {
            return this.f265a;
        }
    }

    /* renamed from: com.chartboost.sdk.Tracking.CBAnalytics.1 */
    static class C10191 implements C0399c {
        final /* synthetic */ String f3398a;
        final /* synthetic */ CBIAPType f3399b;

        C10191(String str, CBIAPType cBIAPType) {
            this.f3398a = str;
            this.f3399b = cBIAPType;
        }

        public void m3731a(C0321a c0321a, az azVar) {
        }

        public void m3732a(C0321a c0321a, az azVar, CBError cBError) {
            try {
                if (this.f3398a.equals("iap") && c0321a.m134c() && c0321a.m140f(NotificationCompatApi21.CATEGORY_STATUS) == HttpStatus.SC_BAD_REQUEST && this.f3399b == CBIAPType.GOOGLE_PLAY) {
                    CBLogging.m75a("CBPostInstallTracker", this.f3398a + " 400 response from server!!");
                    ba a = ba.m610a(C0351c.m378y());
                    C0330h i = a.m631i();
                    ConcurrentHashMap h = a.m630h();
                    if (i != null && h != null) {
                        i.m216e((File) h.get(azVar));
                        h.remove(azVar);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private CBAnalytics() {
    }

    public synchronized void trackInAppPurchaseEvent(EnumMap<CBIAPPurchaseInfo, String> map, CBIAPType iapType) {
        if (!(map == null || iapType == null)) {
            if (!(TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_ID)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE)))) {
                m294a((String) map.get(CBIAPPurchaseInfo.PRODUCT_ID), (String) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION), (String) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE), (String) map.get(CBIAPPurchaseInfo.AMAZON_USER_ID), (String) map.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN), iapType);
            }
        }
        CBLogging.m77b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
    }

    private static synchronized void m294a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            if (C0351c.m378y() == null) {
                CBLogging.m77b("CBPostInstallTracker", "You need call Chartboost.init() before calling any public API's");
            } else if (!C0351c.m369p()) {
                CBLogging.m77b("CBPostInstallTracker", "You need call Chartboost.OnStart() before tracking in-app purchases");
            } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
                CBLogging.m77b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
            } else {
                try {
                    Matcher matcher = Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher(str4);
                    matcher.find();
                    Object group = matcher.group();
                    if (TextUtils.isEmpty(group)) {
                        CBLogging.m77b("CBPostInstallTracker", "Invalid price object");
                    } else {
                        float parseFloat = Float.parseFloat(group);
                        C0321a c0321a = null;
                        if (cBIAPType == CBIAPType.GOOGLE_PLAY) {
                            if (TextUtils.isEmpty(str6) || TextUtils.isEmpty(str7)) {
                                CBLogging.m77b("CBPostInstallTracker", "Null object is passed for for purchase data or purchase signature");
                            } else {
                                c0321a = C0323e.m157a(C0323e.m158a("purchaseData", str6), C0323e.m158a("purchaseSignature", str7), C0323e.m158a("type", Integer.valueOf(CBIAPType.GOOGLE_PLAY.ordinal())));
                            }
                        } else if (cBIAPType == CBIAPType.AMAZON) {
                            if (TextUtils.isEmpty(str8) || TextUtils.isEmpty(str9)) {
                                CBLogging.m77b("CBPostInstallTracker", "Null object is passed for for amazon user id or amazon purchase token");
                            } else {
                                c0321a = C0323e.m157a(C0323e.m158a("userID", str8), C0323e.m158a("purchaseToken", str9), C0323e.m158a("type", Integer.valueOf(CBIAPType.AMAZON.ordinal())));
                            }
                        }
                        if (c0321a == null) {
                            CBLogging.m77b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
                        } else {
                            String encodeToString = Base64.encodeToString(c0321a.toString().getBytes(), 2);
                            m293a(C0323e.m157a(C0323e.m158a("localized-title", str2), C0323e.m158a("localized-description", str3), C0323e.m158a("price", Float.valueOf(parseFloat)), C0323e.m158a("currency", str5), C0323e.m158a("productID", str), C0323e.m158a("receipt", encodeToString)), "iap", cBIAPType);
                        }
                    }
                } catch (IllegalStateException e) {
                    CBLogging.m77b("CBPostInstallTracker", "Invalid price object");
                }
            }
        }
    }

    public static synchronized void trackInAppGooglePlayPurchaseEvent(String title, String description, String price, String currency, String productID, String purchaseData, String purchaseSignature) {
        synchronized (CBAnalytics.class) {
            m294a(productID, title, description, price, currency, purchaseData, purchaseSignature, null, null, CBIAPType.GOOGLE_PLAY);
        }
    }

    public static synchronized void trackInAppAmazonStorePurchaseEvent(String title, String description, String price, String currency, String productID, String userID, String purchaseToken) {
        synchronized (CBAnalytics.class) {
            m294a(productID, title, description, price, currency, null, null, userID, purchaseToken, CBIAPType.AMAZON);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, String description) {
        synchronized (CBAnalytics.class) {
            trackLevelInfo(eventLabel, type, mainLevel, 0, description);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, int subLevel, String description) {
        synchronized (CBAnalytics.class) {
            if (TextUtils.isEmpty(eventLabel)) {
                CBLogging.m77b("CBPostInstallTracker", "Invalid value: event label cannot be empty or null");
            } else {
                if (type != null) {
                    if (type instanceof CBLevelType) {
                        if (mainLevel < 0 || subLevel < 0) {
                            CBLogging.m77b("CBPostInstallTracker", "Invalid value: Level number should be > 0");
                        } else if (description.isEmpty()) {
                            CBLogging.m77b("CBPostInstallTracker", "Invalid value: description cannot be empty or null");
                        } else {
                            m292a(C0323e.m157a(C0323e.m158a("event_label", eventLabel), C0323e.m158a("event_field", Integer.valueOf(type.getLevelType())), C0323e.m158a("main_level", Integer.valueOf(mainLevel)), C0323e.m158a("sub_level", Integer.valueOf(subLevel)), C0323e.m158a("description", description), C0323e.m158a("timestamp", Long.valueOf(System.currentTimeMillis())), C0323e.m158a("data_type", "level_info")), "tracking");
                        }
                    }
                }
                CBLogging.m77b("CBPostInstallTracker", "Invalid value: level type cannot be empty or null, please choose from one of the CBLevelType enum values");
            }
        }
    }

    private static synchronized void m293a(C0321a c0321a, String str, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            az azVar = new az(String.format(Locale.US, "%s%s", new Object[]{"/post-install-event/", str}));
            azVar.m565a(str, (Object) c0321a);
            azVar.m559a(C0328g.m176a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a)));
            azVar.m570b(str);
            azVar.m567a(true);
            azVar.m560a(new C10191(str, cBIAPType));
        }
    }

    private static synchronized void m292a(C0321a c0321a, String str) {
        synchronized (CBAnalytics.class) {
            az azVar = new az("/post-install-event/".concat("tracking"));
            azVar.m565a("track_info", (Object) c0321a);
            azVar.m559a(C0328g.m176a(C0328g.m178a(NotificationCompatApi21.CATEGORY_STATUS, C0314a.f87a)));
            azVar.m570b(str);
            azVar.m567a(true);
            azVar.m590s();
        }
    }
}
