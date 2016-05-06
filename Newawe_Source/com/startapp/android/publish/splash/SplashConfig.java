package com.startapp.android.publish.splash;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.startapp.android.publish.model.MetaData;
import java.io.Serializable;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.time.DateUtils;

/* compiled from: StartAppSDK */
public class SplashConfig implements Serializable {
    private static long DEFAULT_MAX_LOAD = 0;
    private static final int INT_EMPTY_VALUE = -1;
    private static final String STRING_EMPTY_VALUE = "";
    private static final MaxAdDisplayTime VALUE_DEFAULT_MAXADDISPLAY;
    private static final long VALUE_DEFAULT_MAXLOAD;
    private static final MinSplashTime VALUE_DEFAULT_MINSPLASHTIME;
    private static final Orientation VALUE_DEFAULT_ORIENTATION;
    private static final Theme VALUE_DEFAULT_THEME;
    private static final long serialVersionUID = 1;
    private String appName;
    private int customScreen;
    private MaxAdDisplayTime defaultMaxAdDisplayTime;
    private Long defaultMaxLoadTime;
    private MinSplashTime defaultMinSplashTime;
    private Orientation defaultOrientation;
    private Theme defaultTheme;
    private transient String errMsg;
    private transient Drawable logo;
    private byte[] logoByteArray;
    private int logoRes;

    /* renamed from: com.startapp.android.publish.splash.SplashConfig.1 */
    static /* synthetic */ class StartAppSDK {
        static final /* synthetic */ int[] f3218x67b27dff;

        static {
            f3218x67b27dff = new int[Theme.values().length];
            try {
                f3218x67b27dff[Theme.USER_DEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3218x67b27dff[Theme.DEEP_BLUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3218x67b27dff[Theme.SKY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3218x67b27dff[Theme.ASHEN_SKY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3218x67b27dff[Theme.BLAZE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3218x67b27dff[Theme.GLOOMY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3218x67b27dff[Theme.OCEAN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    public enum MaxAdDisplayTime {
        SHORT(5000),
        LONG(10000),
        FOR_EVER(DateUtils.MILLIS_PER_DAY);
        
        private long index;

        private MaxAdDisplayTime(long index) {
            this.index = index;
        }

        public long getIndex() {
            return this.index;
        }

        public static MaxAdDisplayTime getByIndex(long index) {
            MaxAdDisplayTime maxAdDisplayTime = SHORT;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }

        public static MaxAdDisplayTime getByName(String name) {
            MaxAdDisplayTime maxAdDisplayTime = FOR_EVER;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }
    }

    /* compiled from: StartAppSDK */
    public enum MinSplashTime {
        REGULAR(CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS),
        SHORT(2000),
        LONG(5000);
        
        private long index;

        private MinSplashTime(int index) {
            this.index = (long) index;
        }

        public long getIndex() {
            return this.index;
        }

        public static MinSplashTime getByIndex(long index) {
            MinSplashTime minSplashTime = SHORT;
            MinSplashTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }

        public static MinSplashTime getByName(String name) {
            MinSplashTime minSplashTime = LONG;
            MinSplashTime[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }
    }

    /* compiled from: StartAppSDK */
    public enum Orientation {
        PORTRAIT(1),
        LANDSCAPE(2),
        AUTO(3);
        
        private int index;

        private Orientation(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public static Orientation getByIndex(int index) {
            Orientation orientation = PORTRAIT;
            Orientation[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    orientation = values[i];
                }
            }
            return orientation;
        }

        public static Orientation getByName(String name) {
            Orientation orientation = AUTO;
            Orientation[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    orientation = values[i];
                }
            }
            return orientation;
        }
    }

    /* compiled from: StartAppSDK */
    public enum Theme {
        DEEP_BLUE(1),
        SKY(2),
        ASHEN_SKY(3),
        BLAZE(4),
        GLOOMY(5),
        OCEAN(6),
        USER_DEFINED(0);
        
        private int index;

        private Theme(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public static Theme getByIndex(int index) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getIndex() == index) {
                    theme = values[i];
                }
            }
            return theme;
        }

        public static Theme getByName(String name) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    theme = values[i];
                }
            }
            return theme;
        }
    }

    static {
        DEFAULT_MAX_LOAD = 7500;
        VALUE_DEFAULT_THEME = Theme.OCEAN;
        VALUE_DEFAULT_MINSPLASHTIME = MinSplashTime.REGULAR;
        VALUE_DEFAULT_MAXLOAD = DEFAULT_MAX_LOAD;
        VALUE_DEFAULT_MAXADDISPLAY = MaxAdDisplayTime.FOR_EVER;
        VALUE_DEFAULT_ORIENTATION = Orientation.AUTO;
    }

    public SplashConfig() {
        this.customScreen = INT_EMPTY_VALUE;
        this.appName = STRING_EMPTY_VALUE;
        this.logo = null;
        this.logoByteArray = null;
        this.logoRes = INT_EMPTY_VALUE;
        this.defaultTheme = VALUE_DEFAULT_THEME;
        this.defaultMinSplashTime = VALUE_DEFAULT_MINSPLASHTIME;
        this.defaultMaxLoadTime = Long.valueOf(VALUE_DEFAULT_MAXLOAD);
        this.defaultMaxAdDisplayTime = VALUE_DEFAULT_MAXADDISPLAY;
        this.defaultOrientation = VALUE_DEFAULT_ORIENTATION;
        this.errMsg = STRING_EMPTY_VALUE;
    }

    public static SplashConfig getDefaultSplashConfig() {
        SplashConfig splashConfig = new SplashConfig();
        splashConfig.setTheme(VALUE_DEFAULT_THEME).setMinSplashTime(VALUE_DEFAULT_MINSPLASHTIME).setMaxLoadAdTimeout(VALUE_DEFAULT_MAXLOAD).setMaxAdDisplayTime(VALUE_DEFAULT_MAXADDISPLAY).setOrientation(VALUE_DEFAULT_ORIENTATION);
        return splashConfig;
    }

    private static void applyDefaultSplashConfig(SplashConfig config) {
        SplashConfig defaultSplashConfig = getDefaultSplashConfig();
        if (config.getTheme() == null) {
            config.setTheme(defaultSplashConfig.getTheme());
        }
        if (config.getMinSplashTime() == null) {
            config.setMinSplashTime(defaultSplashConfig.getMinSplashTime());
        }
        if (config.getMaxLoadAdTimeout() == null) {
            config.setMaxLoadAdTimeout(defaultSplashConfig.getMaxLoadAdTimeout().longValue());
        }
        if (config.getMaxAdDisplayTime() == null) {
            config.setMaxAdDisplayTime(defaultSplashConfig.getMaxAdDisplayTime());
        }
        if (config.getOrientation() == null) {
            config.setOrientation(defaultSplashConfig.getOrientation());
        }
    }

    public SplashConfig setTheme(Theme theme) {
        this.defaultTheme = theme;
        return this;
    }

    public SplashConfig setCustomScreen(int resource) {
        this.customScreen = resource;
        return this;
    }

    public SplashConfig setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public SplashConfig setLogo(int resource) {
        this.logoRes = resource;
        return this;
    }

    public SplashConfig setLogo(byte[] logoByteArray) {
        this.logoByteArray = logoByteArray;
        return this;
    }

    private SplashConfig setLogo(Drawable logo) {
        this.logo = logo;
        return this;
    }

    protected SplashConfig setMaxLoadAdTimeout(long timeout) {
        this.defaultMaxLoadTime = Long.valueOf(timeout);
        return this;
    }

    public SplashConfig setOrientation(Orientation orientation) {
        this.defaultOrientation = orientation;
        return this;
    }

    public SplashConfig setMinSplashTime(MinSplashTime minSplashTime) {
        this.defaultMinSplashTime = minSplashTime;
        return this;
    }

    public SplashConfig setMaxAdDisplayTime(MaxAdDisplayTime maxDisplayTime) {
        this.defaultMaxAdDisplayTime = maxDisplayTime;
        return this;
    }

    private void setErrorMsg(String msg) {
        this.errMsg = msg;
    }

    public int getCustomScreen() {
        return this.customScreen;
    }

    public String getAppName() {
        return this.appName;
    }

    public Drawable getLogo() {
        return this.logo;
    }

    protected int getLogoResource() {
        return this.logoRes;
    }

    public byte[] getLogoByteArray() {
        return this.logoByteArray;
    }

    protected Long getMaxLoadAdTimeout() {
        return this.defaultMaxLoadTime;
    }

    public String getErrorMessage() {
        return this.errMsg;
    }

    protected Theme getTheme() {
        return this.defaultTheme;
    }

    public Orientation getOrientation() {
        return this.defaultOrientation;
    }

    public MinSplashTime getMinSplashTime() {
        return this.defaultMinSplashTime;
    }

    public MaxAdDisplayTime getMaxAdDisplayTime() {
        return this.defaultMaxAdDisplayTime;
    }

    protected boolean validate(Context context) {
        switch (StartAppSDK.f3218x67b27dff[getTheme().ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (getCustomScreen() != INT_EMPTY_VALUE) {
                    return true;
                }
                setErrorMsg("StartApp: Exception getting custom screen resource id, make sure it is set");
                return false;
            default:
                if (getAppName().compareTo(STRING_EMPTY_VALUE) == 0) {
                    setAppName(com.startapp.android.publish.p022h.StartAppSDK.m2990a(context, "Welcome!"));
                }
                if (getLogo() != null || getLogoByteArray() != null) {
                    return true;
                }
                if (getLogoResource() == INT_EMPTY_VALUE) {
                    setLogo(context.getResources().getDrawable(context.getApplicationInfo().icon));
                    return true;
                }
                setLogo(context.getResources().getDrawable(getLogoResource()));
                return true;
        }
    }

    protected View getLayout(Context context) {
        switch (StartAppSDK.f3218x67b27dff[getTheme().ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                try {
                    return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getCustomScreen(), null);
                } catch (NotFoundException e) {
                    throw new NotFoundException("StartApp: Can't find Custom layout resource");
                } catch (InflateException e2) {
                    throw new InflateException("StartApp: Can't inflate layout in Custom mode, Are you sure layout resource is valid?");
                }
            default:
                return StartAppSDK.m3583a(context, this);
        }
    }

    public void setDefaults(Context context) {
        SplashConfig splashConfig = MetaData.getInstance().getSplashConfig();
        if (splashConfig == null) {
            splashConfig = getDefaultSplashConfig();
        }
        applyDefaultSplashConfig(splashConfig);
        if (getMaxAdDisplayTime() == null) {
            setMaxAdDisplayTime(splashConfig.getMaxAdDisplayTime());
        }
        if (getMaxLoadAdTimeout() == null) {
            setMaxLoadAdTimeout(splashConfig.getMaxLoadAdTimeout().longValue());
        }
        if (getMinSplashTime() == null) {
            setMinSplashTime(splashConfig.getMinSplashTime());
        }
        if (getOrientation() == null) {
            setOrientation(splashConfig.getOrientation());
        }
        if (getTheme() == null) {
            setTheme(splashConfig.getTheme());
        }
    }

    public void initSplashLogo(Activity activity) {
        if (getLogo() == null && getLogoResource() == INT_EMPTY_VALUE && getLogoByteArray() != null) {
            byte[] logoByteArray = getLogoByteArray();
            setLogo(new BitmapDrawable(activity.getResources(), BitmapFactory.decodeByteArray(logoByteArray, 0, logoByteArray.length)));
        }
    }
}
