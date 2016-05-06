package com.Newawe.javascriptinterface;

import com.Newawe.Factory;
import com.Newawe.configuration.WebWidgetConfiguration;
import com.Newawe.utils.Hasher;

public abstract class BaseSecureJsInterface {
    protected boolean _checkSecurityCode(String hashCode) {
        WebWidgetConfiguration config = Factory.getInstance().getMainNavigationActivity().getConfig();
        int appId = config.getApplicationId();
        return hashCode.equalsIgnoreCase(Hasher.md5(config.getAppGuid() + String.valueOf(appId)));
    }
}
