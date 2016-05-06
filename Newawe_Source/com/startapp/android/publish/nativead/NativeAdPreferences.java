package com.startapp.android.publish.nativead;

import com.astuetz.PagerSlidingTabStrip;
import com.startapp.android.publish.model.AdPreferences;

/* compiled from: StartAppSDK */
public class NativeAdPreferences extends AdPreferences {
    private static final int DEFAULT_ADS_NUMBER = 1;
    private static final boolean DEFAULT_AUTO_DOWNLOAD_BITMAP = false;
    private static final NativeAdBitmapSize DEFAULT_BITMAP_SIZE;
    private static final boolean DEFAULT_USE_SIMPLE_TOKEN = true;
    private static String EXCEPTION_LOW_ADS_NUMBER = null;
    private static final long serialVersionUID = 1;
    private int adsNumber;
    private boolean autoBitmapDownload;
    private NativeAdBitmapSize bitmapSize;
    private boolean useSimpleToken;

    /* compiled from: StartAppSDK */
    public enum NativeAdBitmapSize {
        SIZE72X72(72, 72),
        SIZE100X100(100, 100),
        SIZE150X150(PagerSlidingTabStrip.DEF_VALUE_TAB_TEXT_ALPHA, PagerSlidingTabStrip.DEF_VALUE_TAB_TEXT_ALPHA),
        SIZE340X340(340, 340);
        
        int height;
        int width;

        private NativeAdBitmapSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }
    }

    public NativeAdPreferences() {
        this.adsNumber = DEFAULT_ADS_NUMBER;
        this.autoBitmapDownload = DEFAULT_AUTO_DOWNLOAD_BITMAP;
        this.bitmapSize = DEFAULT_BITMAP_SIZE;
        this.useSimpleToken = DEFAULT_USE_SIMPLE_TOKEN;
    }

    static {
        EXCEPTION_LOW_ADS_NUMBER = "Ads Number must be >= 1";
        DEFAULT_BITMAP_SIZE = NativeAdBitmapSize.SIZE150X150;
    }

    public int getAdsNumber() {
        return this.adsNumber;
    }

    public NativeAdPreferences setAdsNumber(int adsNumber) {
        if (adsNumber <= 0) {
            throw new IllegalArgumentException(EXCEPTION_LOW_ADS_NUMBER);
        }
        this.adsNumber = adsNumber;
        return this;
    }

    public boolean isAutoBitmapDownload() {
        return this.autoBitmapDownload;
    }

    public NativeAdPreferences setAutoBitmapDownload(boolean autoBitmapDownload) {
        this.autoBitmapDownload = autoBitmapDownload;
        return this;
    }

    public NativeAdBitmapSize getImageSize() {
        return this.bitmapSize;
    }

    public NativeAdPreferences setImageSize(NativeAdBitmapSize bitmapSize) {
        this.bitmapSize = bitmapSize;
        return this;
    }

    public boolean isSimpleToken() {
        return this.useSimpleToken;
    }

    public NativeAdPreferences useSimpleToken(boolean simpleToken) {
        this.useSimpleToken = simpleToken;
        return this;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== NativeAdConfig =====\n");
        stringBuffer.append("    adsNumber: [" + getAdsNumber() + "]\n");
        stringBuffer.append("    autoBitmapDownload: [" + isAutoBitmapDownload() + "]\n");
        stringBuffer.append("    bitmapSize: [" + getImageSize() + "]\n");
        stringBuffer.append("    useSimpleToken: [" + isSimpleToken() + "]\n");
        stringBuffer.append("===== End NativeAdConfig =====");
        return stringBuffer.toString();
    }
}
