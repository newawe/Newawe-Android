package com.inmobi.signals;

import android.content.pm.PackageManager.NameNotFoundException;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.SignalsConfig.SignalsConfig;
import java.util.ArrayList;
import java.util.List;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.time.DateUtils;

/* renamed from: com.inmobi.signals.g */
public class CarbWorker {
    private static final String f1734a;
    private SignalsConfig f1735b;
    private boolean f1736c;
    private CarbDao f1737d;
    private CarbNetworkClient f1738e;

    /* renamed from: com.inmobi.signals.g.1 */
    class CarbWorker implements Runnable {
        final /* synthetic */ CarbWorker f1733a;

        CarbWorker(CarbWorker carbWorker) {
            this.f1733a = carbWorker;
        }

        public void run() {
            CarbGetListNetworkResponse a = this.f1733a.m1945c();
            if (!a.m1926a()) {
                this.f1733a.f1737d.m1869a(System.currentTimeMillis());
                if (!a.m1930e()) {
                    this.f1733a.m1938a(a, this.f1733a.m1937a(a.m1927b()));
                }
            }
            this.f1733a.f1736c = false;
        }
    }

    static {
        f1734a = CarbWorker.class.getSimpleName();
    }

    public CarbWorker() {
        this.f1736c = false;
        this.f1737d = new CarbDao();
        this.f1738e = new CarbNetworkClient();
    }

    public synchronized void m1946a(SignalsConfig signalsConfig) {
        this.f1735b = signalsConfig;
        if (this.f1736c || !m1940a()) {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1734a, "Carb worker did not admit Carb start request.");
        } else {
            Logger.m1440a(InternalLogLevel.INTERNAL, f1734a, "Starting Carb worker");
            this.f1736c = true;
            m1944b();
        }
    }

    private boolean m1940a() {
        long b = this.f1737d.m1870b();
        if (b != 0 && System.currentTimeMillis() - b < ((long) (this.f1735b.m2018d() * DateUtils.MILLIS_IN_SECOND))) {
            return false;
        }
        return true;
    }

    private void m1944b() {
        new Thread(new CarbWorker(this)).start();
    }

    private CarbGetListNetworkResponse m1945c() {
        CarbGetListNetworkRequest carbGetListNetworkRequest = new CarbGetListNetworkRequest(this.f1735b.m2016b(), this.f1735b.m2019e(), this.f1735b.m2020f(), SignalsComponent.m4580a().m4584d());
        carbGetListNetworkRequest.m1400a(this.f1735b.m2022h());
        carbGetListNetworkRequest.m1402b(this.f1735b.m2021g() * DateUtils.MILLIS_IN_SECOND);
        carbGetListNetworkRequest.m1404c(this.f1735b.m2021g() * DateUtils.MILLIS_IN_SECOND);
        return this.f1738e.m1933a(carbGetListNetworkRequest);
    }

    private List<CarbInfo> m1937a(List<CarbInfo> list) {
        List<CarbInfo> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!m1942a(((CarbInfo) list.get(i)).m1931a())) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    private boolean m1942a(String str) {
        try {
            SdkContext.m1258b().getPackageManager().getPackageInfo(str, NodeFilter.SHOW_DOCUMENT);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void m1938a(CarbGetListNetworkResponse carbGetListNetworkResponse, List<CarbInfo> list) {
        CarbPostListNetworkRequest carbPostListNetworkRequest = new CarbPostListNetworkRequest(this.f1735b.m2017c(), this.f1735b.m2019e(), this.f1735b.m2020f(), SignalsComponent.m4580a().m4584d(), list, carbGetListNetworkResponse);
        carbPostListNetworkRequest.m1402b(this.f1735b.m2021g() * DateUtils.MILLIS_IN_SECOND);
        carbPostListNetworkRequest.m1404c(this.f1735b.m2021g() * DateUtils.MILLIS_IN_SECOND);
        this.f1738e.m1934a(carbPostListNetworkRequest);
    }
}
