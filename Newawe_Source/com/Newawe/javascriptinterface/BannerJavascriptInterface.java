package com.Newawe.javascriptinterface;

import android.webkit.JavascriptInterface;
import com.Newawe.Factory;
import com.Newawe.ads.AdMobUtils.AdMobParameters;
import com.Newawe.ads.AdsLoader;
import com.Newawe.ads.BottomBannerLayout;
import com.Newawe.controllers.TabsController;
import com.Newawe.deviceidparser.DeviceIdParser;
import com.Newawe.utils.WebViewScreenShooter;
import org.apache.commons.lang.StringUtils;

public class BannerJavascriptInterface extends BaseSecureJsInterface {
    public static String JS_INTERFACE_NAME;
    private AdsLoader _adsLoader;
    private String _androidId;
    private BottomBannerLayout _layout;

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.1 */
    class C02231 implements Runnable {
        C02231() {
        }

        public void run() {
            BannerJavascriptInterface.this._androidId = DeviceIdParser.getInstance().getAndroidId(Factory.getInstance().getMainNavigationActivity());
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.2 */
    class C02242 implements Runnable {
        C02242() {
        }

        public void run() {
            BannerJavascriptInterface.this._layout.hideBanner();
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.3 */
    class C02253 implements Runnable {
        final /* synthetic */ String val$birthday;
        final /* synthetic */ String val$genderString;
        final /* synthetic */ String val$keywords;
        final /* synthetic */ String val$latitude;
        final /* synthetic */ String val$longtitude;
        final /* synthetic */ String val$publisherId;

        C02253(String str, String str2, String str3, String str4, String str5, String str6) {
            this.val$publisherId = str;
            this.val$keywords = str2;
            this.val$genderString = str3;
            this.val$birthday = str4;
            this.val$latitude = str5;
            this.val$longtitude = str6;
        }

        public void run() {
            BannerJavascriptInterface.this._layout.switchToAdMobAd(new AdMobParameters(this.val$publisherId, this.val$keywords, this.val$genderString, this.val$birthday, this.val$latitude, this.val$longtitude));
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.4 */
    class C02264 implements Runnable {
        final /* synthetic */ String val$hash;
        final /* synthetic */ String val$jsCode;

        C02264(String str, String str2) {
            this.val$hash = str;
            this.val$jsCode = str2;
        }

        public void run() {
            if (BannerJavascriptInterface.this._checkSecurityCode(this.val$hash)) {
                TabsController controller = (TabsController) Factory.getInstance().getTabsController();
                if (controller != null) {
                    controller.setBannerInjectionJs(this.val$jsCode);
                }
            }
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.5 */
    class C02275 implements Runnable {
        final /* synthetic */ String val$hash;
        final /* synthetic */ String val$url;

        C02275(String str, String str2) {
            this.val$hash = str;
            this.val$url = str2;
        }

        public void run() {
            if (BannerJavascriptInterface.this._checkSecurityCode(this.val$hash)) {
                BannerJavascriptInterface.this._adsLoader.setClickUrl(this.val$url);
            }
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BannerJavascriptInterface.6 */
    class C02286 implements Runnable {
        final /* synthetic */ String val$hash;

        C02286(String str) {
            this.val$hash = str;
        }

        public void run() {
            if (BannerJavascriptInterface.this._checkSecurityCode(this.val$hash)) {
                BannerJavascriptInterface.this._adsLoader.reload();
            }
        }
    }

    static {
        JS_INTERFACE_NAME = "AppsgeyserBanner";
    }

    public BannerJavascriptInterface(BottomBannerLayout layout, AdsLoader loader) {
        this._layout = layout;
        this._adsLoader = loader;
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02231());
    }

    @JavascriptInterface
    public void close() {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02242());
    }

    @JavascriptInterface
    public void showAdMobAd(String publisherId, String keywords, String genderString, String birthday, String latitude, String longtitude) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02253(publisherId, keywords, genderString, birthday, latitude, longtitude));
    }

    @JavascriptInterface
    public void addJs(String jsCode, String hash) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02264(hash, jsCode));
    }

    @JavascriptInterface
    public void setClickUrl(String url, String hash) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02275(hash, url));
    }

    @JavascriptInterface
    public String getAndroidId(String hash) {
        if (_checkSecurityCode(hash)) {
            return this._androidId;
        }
        return StringUtils.EMPTY;
    }

    @JavascriptInterface
    public void reload(String hash) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new C02286(hash));
    }

    @JavascriptInterface
    public String takeScreenShot() {
        return WebViewScreenShooter.takeScreenShotInBase64(this._layout.getBannerWebView());
    }

    @JavascriptInterface
    public void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this._adsLoader.forceOpenInNativeBrowser(openInNativeBrowser);
    }
}
