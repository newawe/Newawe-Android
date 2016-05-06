package com.Newawe.ads.sdk;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class SdkWrapperFactory {
    private static SdkWrapperFactory _instance;
    private HashMap<String, SdkWrapper> map;

    public static SdkWrapperFactory getInstance() {
        SdkWrapperFactory localInstance = _instance;
        if (localInstance == null) {
            synchronized (SdkWrapperFactory.class) {
                try {
                    localInstance = _instance;
                    if (localInstance == null) {
                        SdkWrapperFactory localInstance2 = new SdkWrapperFactory();
                        try {
                            _instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    private SdkWrapperFactory() {
        this.map = null;
        this.map = new HashMap();
    }

    public SdkWrapper getWrapperByKey(String sdkKey) throws InvalidParameterException {
        if (this.map.get(sdkKey) == null) {
            SdkWrapper wrapper = newInstance(sdkKey);
            if (wrapper == null) {
                throw new InvalidParameterException("Could not create wrapper for " + sdkKey);
            }
            this.map.put(sdkKey, wrapper);
        }
        return (SdkWrapper) this.map.get(sdkKey);
    }

    private SdkWrapper newInstance(String sdkKey) {
        if (sdkKey.equals(SdkWrapper.KEY_INMOBI)) {
            return new InmobiSdkWrapper();
        }
        if (sdkKey.equals(SdkWrapper.KEY_CHARTBOOST)) {
            return new ChartBoostSdkWrapper();
        }
        if (sdkKey.equals(SdkWrapper.KEY_ADCOLONY)) {
            return new AdColonySdkWrapper();
        }
        return null;
    }
}
