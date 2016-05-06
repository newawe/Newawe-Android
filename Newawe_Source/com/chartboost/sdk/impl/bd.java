package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.Libraries.C0318c;
import com.chartboost.sdk.Libraries.C0318c.C0317a;
import com.chartboost.sdk.Libraries.C0323e;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBUtility;
import com.google.android.gcm.GCMConstants;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

public final class bd extends az {
    private C0321a f3543c;
    private C0321a f3544d;
    private C0321a f3545e;
    private C0321a f3546f;

    /* renamed from: com.chartboost.sdk.impl.bd.1 */
    static /* synthetic */ class C04151 {
        static final /* synthetic */ int[] f571a;

        static {
            f571a = new int[C0416a.values().length];
            try {
                f571a[C0416a.AD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bd.a */
    public enum C0416a {
        AD
    }

    public bd(String str) {
        super(str);
        this.f3543c = C0321a.m121a();
        this.f3544d = C0321a.m121a();
        this.f3545e = C0321a.m121a();
        this.f3546f = C0321a.m121a();
    }

    protected void m3960c() {
        int i = 1;
        this.f3544d.m128a(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT, b.f493o);
        this.f3544d.m128a("bundle", b.f483e);
        this.f3544d.m128a("bundle_id", b.f484f);
        if (TextUtils.isEmpty(b.f496r)) {
            b.f496r = StringUtils.EMPTY;
        }
        this.f3544d.m128a("custom_id", b.f496r);
        this.f3544d.m128a("session_id", StringUtils.EMPTY);
        this.f3544d.m128a("ui", Integer.valueOf(-1));
        this.f3544d.m128a("test_mode", Boolean.valueOf(false));
        this.a.m128a(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT, this.f3544d);
        this.f3545e.m128a("carrier", C0323e.m157a(C0323e.m158a("carrier_name", b.f495q.m138e("carrier-name")), C0323e.m158a("mobile_country_code", b.f495q.m138e("mobile-country-code")), C0323e.m158a("mobile_network_code", b.f495q.m138e("mobile-network-code")), C0323e.m158a("iso_country_code", b.f495q.m138e("iso-country-code")), C0323e.m158a("phone_type", Integer.valueOf(b.f495q.m140f("phone-type")))));
        this.f3545e.m128a("model", b.f479a);
        this.f3545e.m128a("device_type", b.f494p);
        this.f3545e.m128a("os", b.f480b);
        this.f3545e.m128a("country", b.f481c);
        this.f3545e.m128a(SchemaSymbols.ATTVAL_LANGUAGE, b.f482d);
        this.f3545e.m128a("timestamp", b.f491m);
        this.f3545e.m128a("reachability", Integer.valueOf(ay.m543a().m547b()));
        this.f3545e.m128a("scale", b.f492n);
        C0321a c0321a = this.f3545e;
        String str = "is_portrait";
        if (!CBUtility.m93c().m165a()) {
            i = 0;
        }
        c0321a.m128a(str, Integer.valueOf(i));
        this.f3545e.m128a("rooted_device", Boolean.valueOf(b.f499u));
        this.f3545e.m128a("timezone", b.f500v);
        this.f3545e.m128a("mobile_network", b.f501w);
        this.f3545e.m128a("dw", b.f488j);
        this.f3545e.m128a("dh", b.f489k);
        this.f3545e.m128a("dpi", b.f490l);
        this.f3545e.m128a("w", b.f486h);
        this.f3545e.m128a("h", b.f487i);
        this.f3545e.m128a("device_family", StringUtils.EMPTY);
        this.f3545e.m128a("retina", Boolean.valueOf(false));
        this.f3545e.m128a(HTTP.IDENTITY_CODING, C0318c.m109b());
        C0317a c = C0318c.m111c();
        if (c.m105b()) {
            this.f3545e.m128a("tracking", Integer.valueOf(c.m104a()));
        }
        this.a.m128a("device", this.f3545e);
        this.f3543c.m128a("framework", StringUtils.EMPTY);
        this.f3543c.m128a("sdk", b.f485g);
        this.f3543c.m128a("framework_version", b.f497s);
        this.f3543c.m128a("wrapper_version", b.f498t);
        this.a.m128a("sdk", this.f3543c);
        this.f3546f.m128a("session", Integer.valueOf(CBUtility.m87a().getInt("cbPrefSessionCount", 0)));
        if (this.f3546f.m127a("cache").m131b()) {
            this.f3546f.m128a("cache", Boolean.valueOf(false));
        }
        if (this.f3546f.m127a("amount").m131b()) {
            this.f3546f.m128a("amount", Integer.valueOf(0));
        }
        if (this.f3546f.m127a("retry_count").m131b()) {
            this.f3546f.m128a("retry_count", Integer.valueOf(0));
        }
        if (this.f3546f.m127a("location").m131b()) {
            this.f3546f.m128a("location", StringUtils.EMPTY);
        }
        this.a.m128a("ad", this.f3546f);
    }

    public void m3959a(String str, Object obj, C0416a c0416a) {
        if (this.a == null) {
            this.a = C0321a.m121a();
        }
        switch (C04151.f571a[c0416a.ordinal()]) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                this.f3546f.m128a(str, obj);
                this.a.m128a("ad", this.f3546f);
            default:
        }
    }
}
