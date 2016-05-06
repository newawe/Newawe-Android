package com.startapp.android.publish.model;

import com.startapp.android.publish.p022h.StartAppSDK;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class SdkDownloadRequest extends BaseRequest {
    private static final String PLACEMENT = "INAPP_DOWNLOAD";

    public List<NameValueObject> getNameValueMap() {
        List nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new ArrayList();
        }
        StartAppSDK.m3013a(nameValueMap, "placement", PLACEMENT, true);
        return nameValueMap;
    }
}
