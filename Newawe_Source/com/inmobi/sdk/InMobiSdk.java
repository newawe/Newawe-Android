package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.configs.ConfigComponent;
import com.inmobi.commons.core.configs.ConfigDao;
import com.inmobi.commons.core.p001a.InMobiCrashHandler;
import com.inmobi.commons.core.p003c.TelemetryComponent;
import com.inmobi.commons.core.utilities.ApplicationFocusChangeObserver.ApplicationFocusChangeObserver;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.PublisherProvidedUserInfo;
import com.inmobi.commons.core.utilities.p004a.EncryptionDao;
import com.inmobi.commons.core.utilities.uid.UidHelper;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.commons.p000a.SdkInfo;
import com.inmobi.rendering.mraid.MraidJsDao;
import com.inmobi.rendering.p005a.ClickManager;
import com.inmobi.signals.CarbDao;
import com.inmobi.signals.SignalsComponent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public final class InMobiSdk {
    private static final String TAG;

    /* renamed from: com.inmobi.sdk.InMobiSdk.2 */
    static /* synthetic */ class C07182 {
        static final /* synthetic */ int[] f1682a;

        static {
            f1682a = new int[LogLevel.values().length];
            try {
                f1682a[LogLevel.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1682a[LogLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1682a[LogLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_20("between18and20"),
        BETWEEN_21_AND_24("between21and24"),
        BETWEEN_25_AND_34("between25and34"),
        BETWEEN_35_AND_54("between35and54"),
        ABOVE_55("above55");
        
        private String f1683a;

        private AgeGroup(String str) {
            this.f1683a = str;
        }

        public String toString() {
            return this.f1683a;
        }
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");
        
        private String f1684a;

        private Education(String str) {
            this.f1684a = str;
        }

        public String toString() {
            return this.f1684a;
        }
    }

    public enum Ethnicity {
        AFRICAN_AMERICAN("africanamerican"),
        ASIAN("asian"),
        CAUCASIAN("caucasian"),
        HISPANIC("hispanic"),
        OTHER("other");
        
        private String f1685a;

        private Ethnicity(String str) {
            this.f1685a = str;
        }

        public String toString() {
            return this.f1685a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE("m");
        
        private String f1686a;

        private Gender(String str) {
            this.f1686a = str;
        }

        public String toString() {
            return this.f1686a;
        }
    }

    public enum HouseHoldIncome {
        BELOW_USD_5K("belowusd5k"),
        BETWEEN_USD_5K_AND_10K("betweenusd5kand10k"),
        BETWEEN_USD_10K_AND_15K("betweenusd10kand15k"),
        BETWEEN_USD_15K_AND_20K("betweenusd15kand20k"),
        BETWEEN_USD_20K_AND_25K("betweenusd20kand25k"),
        BETWEEN_USD_25K_AND_50K("betweenusd25kand50k"),
        BETWEEN_USD_50K_AND_75K("betweenusd50kand75k"),
        BETWEEN_USD_75K_AND_100K("betweenusd75kand100k"),
        BETWEEN_USD_100K_AND_150K("betweenusd100kand150k"),
        ABOVE_USD_150K("aboveusd150k");
        
        private String f1687a;

        private HouseHoldIncome(String str) {
            this.f1687a = str;
        }

        public String toString() {
            return this.f1687a;
        }
    }

    public enum ImIdType {
        LOGIN,
        SESSION
    }

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    /* renamed from: com.inmobi.sdk.InMobiSdk.1 */
    static class C12331 implements ApplicationFocusChangeObserver {
        C12331() {
        }

        public void m4572a(boolean z) {
            SdkContext.m1256a(z);
            if (z) {
                InMobiSdk.initComponents();
            } else {
                InMobiSdk.deInitComponents();
            }
        }
    }

    static {
        TAG = InMobiSdk.class.getSimpleName();
    }

    public static void init(Context context, String str) {
        if (context == null) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
        } else if (str == null || str.trim().length() == 0) {
            Logger.m1440a(InternalLogLevel.ERROR, TAG, "Account ID cannot be null or empty. Please provide a valid Account ID.");
        } else {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
            if (context.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                Logger.m1440a(InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                return;
            }
            String trim = str.trim();
            if (!(trim.length() == 32 || trim.length() == 36)) {
                Logger.m1440a(InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id");
            }
            if (SdkContext.m1257a()) {
                Logger.m1440a(InternalLogLevel.INTERNAL, TAG, "SDK already initialized");
                return;
            }
            if (hasSdkVersionChanged(context)) {
                clearCachedData(context);
                SdkInfo.m1267a(context, SdkInfo.m1268b());
            }
            SdkContext.m1254a(context, trim);
            initComponents();
            ConfigComponent.m1352a().m1367d();
            com.inmobi.commons.core.utilities.ApplicationFocusChangeObserver a = com.inmobi.commons.core.utilities.ApplicationFocusChangeObserver.m1462a();
            if (a != null) {
                a.m1468a(new C12331());
            }
        }
    }

    @SuppressLint({"SdCardPath"})
    private static void clearCachedData(Context context) {
        int i;
        File file;
        List asList = Arrays.asList(new String[]{"carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref"});
        List asList2 = Arrays.asList(new String[]{CarbDao.m1868a(), ConfigDao.m1368a(), EncryptionDao.m1445a(), MraidJsDao.m1814a()});
        for (i = 0; i < asList.size(); i++) {
            File file2 = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) asList.get(i)) + ".xml");
            if (file2.exists()) {
                file2.delete();
            }
        }
        for (i = 0; i < asList2.size(); i++) {
            File file3 = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) asList2.get(i)) + ".xml");
            if (file3.exists()) {
                file3.delete();
            }
        }
        asList = Arrays.asList(new String[]{"inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getCacheDir() != null) {
                file = new File(context.getCacheDir(), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        asList = Arrays.asList(new String[]{"eventlog", "imai_click_events"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getDir("data", 0) != null) {
                file = new File(context.getDir("data", 0), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        context.deleteDatabase("adcache.db");
        context.deleteDatabase("appengage.db");
        context.deleteDatabase("im.db");
        context.deleteDatabase("ltvp.db");
        context.deleteDatabase("analytics.db");
        context.deleteDatabase("com.im.db");
    }

    private static boolean hasSdkVersionChanged(Context context) {
        if (SdkInfo.m1266a(context) == null || !SdkInfo.m1266a(context).equals(SdkInfo.m1268b())) {
            return true;
        }
        return false;
    }

    private static void initComponents() {
        UidHelper.m1550a().m1560b();
        UidHelper.m1550a().m1562d();
        ConfigComponent.m1352a().m1365b();
        ClickManager.m4554a().m4566b();
        InMobiCrashHandler.m1273a();
        TelemetryComponent.m4448a().m4472b();
        SignalsComponent.m4580a().m4582b();
    }

    private static void deInitComponents() {
        ConfigComponent.m1352a().m1366c();
        TelemetryComponent.m4448a().m4474c();
        SignalsComponent.m4580a().m4583c();
    }

    public static String getVersion() {
        return SdkInfo.m1268b();
    }

    public static void setLogLevel(LogLevel logLevel) {
        switch (C07182.f1682a[logLevel.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                Logger.m1439a(InternalLogLevel.NONE);
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                Logger.m1439a(InternalLogLevel.ERROR);
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                Logger.m1439a(InternalLogLevel.DEBUG);
            default:
        }
    }

    public static final void addIdType(ImIdType imIdType, String str) {
        if (imIdType == ImIdType.LOGIN) {
            PublisherProvidedUserInfo.m1523n(str);
        } else if (imIdType == ImIdType.SESSION) {
            PublisherProvidedUserInfo.m1524o(str);
        }
    }

    public static final void removeIdType(ImIdType imIdType) {
        if (imIdType == ImIdType.LOGIN) {
            PublisherProvidedUserInfo.m1523n(null);
        } else if (imIdType == ImIdType.SESSION) {
            PublisherProvidedUserInfo.m1524o(null);
        }
    }

    public static final void setAge(int i) {
        PublisherProvidedUserInfo.m1503a(i);
    }

    public static final void setAgeGroup(AgeGroup ageGroup) {
        PublisherProvidedUserInfo.m1505a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setAreaCode(String str) {
        PublisherProvidedUserInfo.m1508b(str);
    }

    public static final void setPostalCode(String str) {
        PublisherProvidedUserInfo.m1511c(str);
    }

    public static final void setLocationWithCityStateCountry(String str, String str2, String str3) {
        PublisherProvidedUserInfo.m1513d(str);
        PublisherProvidedUserInfo.m1514e(str2);
        PublisherProvidedUserInfo.m1515f(str3);
    }

    public static final void setYearOfBirth(int i) {
        PublisherProvidedUserInfo.m1507b(i);
    }

    public static final void setGender(Gender gender) {
        PublisherProvidedUserInfo.m1516g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEthnicity(Ethnicity ethnicity) {
        PublisherProvidedUserInfo.m1517h(ethnicity.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEducation(Education education) {
        PublisherProvidedUserInfo.m1518i(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setLanguage(String str) {
        PublisherProvidedUserInfo.m1519j(str);
    }

    public static final void setIncome(int i) {
        PublisherProvidedUserInfo.m1510c(i);
    }

    public static final void setHouseHoldIncome(HouseHoldIncome houseHoldIncome) {
        PublisherProvidedUserInfo.m1520k(houseHoldIncome.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setInterests(String str) {
        PublisherProvidedUserInfo.m1521l(str);
    }

    public static final void setNationality(String str) {
        PublisherProvidedUserInfo.m1522m(str);
    }

    public static final void setLocation(Location location) {
        PublisherProvidedUserInfo.m1504a(location);
    }
}
