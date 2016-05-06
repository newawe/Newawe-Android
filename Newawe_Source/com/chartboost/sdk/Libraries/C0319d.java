package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0318c.C0317a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

/* renamed from: com.chartboost.sdk.Libraries.d */
public final class C0319d {
    private C0319d() {
    }

    protected static String m120a() {
        Info advertisingIdInfo;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(C0351c.m378y());
        } catch (IOException e) {
            advertisingIdInfo = null;
        } catch (GooglePlayServicesRepairableException e2) {
            advertisingIdInfo = null;
        } catch (GooglePlayServicesNotAvailableException e3) {
            advertisingIdInfo = null;
        } catch (Throwable e4) {
            CBLogging.m76a("CBIdentityAdv", "Security Exception when retrieving AD id", e4);
            advertisingIdInfo = null;
        } catch (Throwable e42) {
            CBLogging.m76a("CBIdentityAdv", "General Exception when retrieving AD id", e42);
            advertisingIdInfo = null;
        }
        if (advertisingIdInfo == null) {
            C0318c.m107a(C0317a.UNKNOWN);
            return null;
        }
        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
            C0318c.m107a(C0317a.TRACKING_DISABLED);
        } else {
            C0318c.m107a(C0317a.TRACKING_ENABLED);
        }
        try {
            UUID fromString = UUID.fromString(advertisingIdInfo.getId());
            ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
            wrap.putLong(fromString.getMostSignificantBits());
            wrap.putLong(fromString.getLeastSignificantBits());
            return C0315b.m103b(wrap.array());
        } catch (Throwable e5) {
            CBLogging.m78b("CBIdentityAdv", "Exception raised retrieveAdvertisingID", e5);
            return advertisingIdInfo.getId();
        }
    }
}
