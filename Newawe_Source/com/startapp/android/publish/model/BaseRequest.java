package com.startapp.android.publish.model;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.startapp.android.publish.model.adrules.SessionManager;
import com.startapp.android.publish.p022h.StartAppSDK.StartAppSDK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mf.org.apache.xerces.impl.Constants;
import org.apache.commons.lang.StringUtils;

/* compiled from: StartAppSDK */
public abstract class BaseRequest implements NameValueSerializer {
    private static final String OS = "android";
    private int appCode;
    private String appVersion;
    private String blat;
    private String blon;
    private String bssid;
    private String cid;
    private String clientSessionId;
    private float density;
    private String deviceVersion;
    private int height;
    private Set<String> inputLangs;
    private String installerPkg;
    private String isp;
    private String ispName;
    private String lac;
    private String locale;
    private String manufacturer;
    private String model;
    private String netOper;
    private String networkType;
    private String os;
    private String packageId;
    private Map<String, String> parameters;
    private String productId;
    private String publisherId;
    private int sdkId;
    private String sdkVersion;
    private String signalLevel;
    private String ssid;
    private String subProductId;
    private String subPublisherId;
    private String unityWrapperVersion;
    private Boolean unknownSourcesAllowed;
    private StartAppSDK userAdvertisingId;
    private String wfScanRes;
    private int width;

    /* compiled from: StartAppSDK */
    static class WifiScanResult {
        private static final char DELIMITER = ',';
        private ScanResult sr;

        public WifiScanResult(ScanResult sr) {
            this.sr = sr;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.sr != null) {
                stringBuilder.append(this.sr.SSID).append(DELIMITER);
                stringBuilder.append(this.sr.BSSID).append(DELIMITER);
                stringBuilder.append(WifiManager.calculateSignalLevel(this.sr.level, 5));
            }
            return stringBuilder.toString();
        }
    }

    public BaseRequest() {
        this.parameters = new HashMap();
        this.sdkVersion = com.startapp.android.publish.StartAppSDK.f2831g;
        this.unityWrapperVersion = com.startapp.android.publish.StartAppSDK.m2799a().m2826h();
        this.os = OS;
        this.sdkId = 3;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getInstallerPkg() {
        return this.installerPkg;
    }

    public void setInstallerPkg(String installerPkg) {
        this.installerPkg = installerPkg;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getSignalLevel() {
        return this.signalLevel;
    }

    private void setNetworkType(Context context) {
        this.networkType = com.startapp.android.publish.p022h.StartAppSDK.m2938a(context);
    }

    private void setSignalLevel(String networkType) {
        this.signalLevel = "e106";
        com.startapp.android.publish.p022h.StartAppSDK a = com.startapp.android.publish.p022h.StartAppSDK.m2931a();
        if (a != null) {
            this.signalLevel = a.m2935a(networkType);
        }
    }

    public StartAppSDK getUserAdvertisingId() {
        return this.userAdvertisingId;
    }

    public void setUserAdvertisingId(StartAppSDK userAdvertisingId) {
        this.userAdvertisingId = userAdvertisingId;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIspName() {
        return this.ispName;
    }

    public void setIspName(String ispName) {
        this.ispName = ispName;
    }

    public String getNetOper() {
        return this.netOper;
    }

    public void setNetOper(String netOper) {
        this.netOper = netOper;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLac() {
        return this.lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getBlat() {
        return this.blat;
    }

    public void setBlat(String blat) {
        this.blat = blat;
    }

    public String getBlon() {
        return this.blon;
    }

    public void setBlon(String blon) {
        this.blon = blon;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getWfScanRes() {
        return this.wfScanRes;
    }

    public void setWfScanRes(String wfScanRes) {
        this.wfScanRes = wfScanRes;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSubPublisherId() {
        return this.subPublisherId;
    }

    public void setSubPublisherId(String subPublisherId) {
        this.subPublisherId = subPublisherId;
    }

    public String getSubProductId() {
        return this.subProductId;
    }

    public void setSubProductId(String subProductId) {
        this.subProductId = subProductId;
    }

    public String getOs() {
        return this.os;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getSessionId() {
        if (this.clientSessionId == null) {
            return StringUtils.EMPTY;
        }
        return this.clientSessionId;
    }

    public void setSessionId(String sessionId) {
        this.clientSessionId = sessionId;
    }

    public Boolean isUnknownSourcesAllowed() {
        return this.unknownSourcesAllowed;
    }

    public void setUnknownSourcesAllowed(Boolean unknownSourcesAllowed) {
        this.unknownSourcesAllowed = unknownSourcesAllowed;
    }

    public float getDensity() {
        return this.density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public Set<String> getInputLangs() {
        return this.inputLangs;
    }

    public void setInputLangs(Set<String> inputLangs) {
        this.inputLangs = inputLangs;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getAppCode() {
        return this.appCode;
    }

    public void setAppCode(int appCode) {
        this.appCode = appCode;
    }

    public String toString() {
        return "BaseRequest [parameters=" + this.parameters + "]";
    }

    public void fillApplicationDetails(Context context, AdPreferences adPreferences) {
        setPublisherId(adPreferences.getPublisherId());
        setProductId(adPreferences.getProductId());
        setUserAdvertisingId(com.startapp.android.publish.p022h.StartAppSDK.m2852a(context));
        setPackageId(context.getPackageName());
        setInstallerPkg(com.startapp.android.publish.p022h.StartAppSDK.m3048g(context));
        setManufacturer(Build.MANUFACTURER);
        setModel(Build.MODEL);
        setDeviceVersion(Integer.toString(VERSION.SDK_INT));
        setLocale(context.getResources().getConfiguration().locale.toString());
        setInputLangs(com.startapp.android.publish.p022h.StartAppSDK.m2884c(context));
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(context.getResources().getDisplayMetrics().heightPixels);
        setDensity(context.getResources().getDisplayMetrics().density);
        setSessionId(SessionManager.getInstance().getSessionId());
        setUnknownSourcesAllowed(Boolean.valueOf(com.startapp.android.publish.p022h.StartAppSDK.m2876a(context)));
        setNetworkType(context);
        setSignalLevel(getNetworkType());
        setAppVersion(com.startapp.android.publish.p022h.StartAppSDK.m3053i(context));
        setAppCode(com.startapp.android.publish.p022h.StartAppSDK.m3055j(context));
        fillTelephonyDetails(context);
        fillWifiDetails(context);
    }

    public List<NameValueObject> getNameValueMap() {
        List arrayList = new ArrayList();
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "publisherId", this.publisherId, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "productId", this.productId, true);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "os", this.os, true);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "sdkVersion", this.sdkVersion, false);
        if (com.startapp.android.publish.p022h.StartAppSDK.m3031b()) {
            com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "unityWrapperVersion", this.unityWrapperVersion, false);
        }
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "packageId", this.packageId, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "installerPkg", this.installerPkg, false);
        if (this.userAdvertisingId != null) {
            com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "userAdvertisingId", this.userAdvertisingId.m2846a(), false);
            if (this.userAdvertisingId.m2849b()) {
                com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "limat", Boolean.valueOf(this.userAdvertisingId.m2849b()), false);
            }
        }
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "model", this.model, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "manufacturer", this.manufacturer, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "deviceVersion", this.deviceVersion, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, Constants.LOCALE_PROPERTY, this.locale, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3015a(arrayList, "inputLangs", this.inputLangs, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "isp", this.isp, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "ispName", this.ispName, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "netOper", getNetOper(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "cid", getCid(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "lac", getLac(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "blat", getBlat(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "blon", getBlon(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "ssid", getSsid(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "bssid", getBssid(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "wfScanRes", getWfScanRes(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "subPublisherId", this.subPublisherId, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "subProductId", this.subProductId, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "grid", getNetworkType(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "silev", getSignalLevel(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "outsource", isUnknownSourcesAllowed(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "width", String.valueOf(this.width), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "height", String.valueOf(this.height), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "density", String.valueOf(this.density), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "sdkId", String.valueOf(this.sdkId), true);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "clientSessionId", this.clientSessionId, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "appVersion", this.appVersion, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(arrayList, "appCode", Integer.valueOf(this.appCode), false);
        return arrayList;
    }

    public String getRequestString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<NameValueObject> nameValueMap = getNameValueMap();
        if (nameValueMap == null) {
            return stringBuilder.toString();
        }
        stringBuilder.append('?');
        for (NameValueObject nameValueObject : nameValueMap) {
            if (nameValueObject.getValue() != null) {
                stringBuilder.append(nameValueObject.getName()).append('=').append(nameValueObject.getValue()).append('&');
            } else if (nameValueObject.getValueSet() != null) {
                Set<String> valueSet = nameValueObject.getValueSet();
                if (valueSet != null) {
                    for (String append : valueSet) {
                        stringBuilder.append(nameValueObject.getName()).append('=').append(append).append('&');
                    }
                }
            }
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString().replace("+", "%20");
    }

    private void fillTelephonyDetails(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            fillSimDetails(telephonyManager);
            fillNetworkOperatorDetails(context, telephonyManager);
        }
    }

    private void fillSimDetails(TelephonyManager tm) {
        if (tm.getSimState() == 5) {
            setIsp(tm.getSimOperator());
            setIspName(tm.getSimOperatorName());
        }
    }

    private void fillNetworkOperatorDetails(Context context, TelephonyManager tm) {
        int phoneType = tm.getPhoneType();
        if (!(phoneType == 0 || phoneType == 2)) {
            String networkOperator = tm.getNetworkOperator();
            if (networkOperator != null) {
                setNetOper(com.startapp.android.publish.p022h.StartAppSDK.m3033c(networkOperator));
            }
        }
        if (!com.startapp.android.publish.p022h.StartAppSDK.m2877a(context, "android.permission.ACCESS_FINE_LOCATION") && !com.startapp.android.publish.p022h.StartAppSDK.m2877a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return;
        }
        if (phoneType == 1) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) tm.getCellLocation();
            if (gsmCellLocation != null) {
                setCid(com.startapp.android.publish.p022h.StartAppSDK.m3033c(String.valueOf(gsmCellLocation.getCid())));
                setLac(com.startapp.android.publish.p022h.StartAppSDK.m3033c(String.valueOf(gsmCellLocation.getLac())));
            }
        } else if (phoneType == 2) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) tm.getCellLocation();
            if (cdmaCellLocation != null) {
                setBlat(com.startapp.android.publish.p022h.StartAppSDK.m3033c(String.valueOf(cdmaCellLocation.getBaseStationLatitude())));
                setBlon(com.startapp.android.publish.p022h.StartAppSDK.m3033c(String.valueOf(cdmaCellLocation.getBaseStationLongitude())));
            }
        }
    }

    private void fillWifiDetails(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager != null && com.startapp.android.publish.p022h.StartAppSDK.m2877a(context, "android.permission.ACCESS_WIFI_STATE")) {
            if (getNetworkType().equals("WIFI")) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String ssid = connectionInfo.getSSID();
                    String bssid = connectionInfo.getBSSID();
                    if (ssid != null) {
                        setSsid(com.startapp.android.publish.p022h.StartAppSDK.m3033c(ssid));
                    }
                    if (bssid != null) {
                        setBssid(com.startapp.android.publish.p022h.StartAppSDK.m3033c(bssid));
                    }
                }
            } else if (MetaData.getInstance().isWfScanEnabled()) {
                List a = com.startapp.android.publish.p022h.StartAppSDK.m2860a(context, wifiManager);
                if (a != null && !a.equals(Collections.EMPTY_LIST)) {
                    Iterable arrayList = new ArrayList();
                    for (int i = 0; i < Math.min(5, a.size()); i++) {
                        arrayList.add(new WifiScanResult((ScanResult) a.get(i)));
                    }
                    setWfScanRes(com.startapp.android.publish.p022h.StartAppSDK.m3033c(TextUtils.join(";", arrayList)));
                }
            }
        }
    }
}
