package com.startapp.android.publish.p010d;

import com.Newawe.server.StatController;
import com.startapp.android.publish.model.BaseRequest;
import com.startapp.android.publish.model.NameValueObject;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.d.c */
public class StartAppSDK extends BaseRequest {
    private StartAppSDK f4310a;

    public StartAppSDK(StartAppSDK startAppSDK) {
        this.f4310a = startAppSDK;
    }

    public List<NameValueObject> getNameValueMap() {
        List nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new ArrayList();
        }
        Object a = com.startapp.android.publish.p022h.StartAppSDK.m2888a();
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, com.startapp.android.publish.p022h.StartAppSDK.f2938b, a, true);
        com.startapp.android.publish.p022h.StartAppSDK.m3014a(nameValueMap, com.startapp.android.publish.p022h.StartAppSDK.f2939c, com.startapp.android.publish.p022h.StartAppSDK.m2890b(a), true, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "category", this.f4310a.m2716a().m2715a(), true);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "value", this.f4310a.m2718b(), true);
        com.startapp.android.publish.p022h.StartAppSDK.m3014a(nameValueMap, "d", this.f4310a.m2722d(), false, false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "orientation", this.f4310a.m2724e(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "usedRam", this.f4310a.m2725f(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "freeRam", this.f4310a.m2726g(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "sessionTime", this.f4310a.m2727h(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, "appActivity", this.f4310a.m2728i(), false);
        com.startapp.android.publish.p022h.StartAppSDK.m3013a(nameValueMap, StatController.KEY_GET_PARAM_DETAILS, this.f4310a.m2720c(), false);
        return nameValueMap;
    }
}
