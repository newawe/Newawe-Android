package com.jirbo.adcolony;

import android.os.StatFs;
import java.io.File;
import org.apache.commons.lang.StringUtils;

public class ADCStorage {
    C0762d f1812a;
    String f1813b;
    String f1814c;
    String f1815d;
    File f1816e;
    File f1817f;

    ADCStorage(C0762d controller) {
        this.f1812a = controller;
    }

    void m2109a() {
        C0777l.f2239a.m2353b((Object) "Configuring storage");
        C0777l.f2240b.m2353b((Object) "Using internal storage:");
        this.f1813b = m2111c() + "/adc/";
        this.f1814c = this.f1813b + "media/";
        C0777l.f2239a.m2353b(this.f1814c);
        this.f1816e = new File(this.f1814c);
        if (!this.f1816e.isDirectory()) {
            this.f1816e.delete();
            this.f1816e.mkdirs();
        }
        if (!this.f1816e.isDirectory()) {
            C0745a.m2143a("Cannot create media folder.");
        } else if (m2108a(this.f1814c) < 2.097152E7d) {
            C0745a.m2143a("Not enough space to store temporary files (" + m2108a(this.f1814c) + " bytes available).");
        } else {
            this.f1815d = m2111c() + "/adc/data/";
            if (C0745a.f2003n == 0) {
                this.f1815d = this.f1813b + "data/";
            }
            C0777l.f2239a.m2349a("Internal data path: ").m2353b(this.f1815d);
            this.f1817f = new File(this.f1815d);
            if (!this.f1817f.isDirectory()) {
                this.f1817f.delete();
            }
            this.f1817f.mkdirs();
            C0771f c0771f = new C0771f("iap_cache.txt");
            c0771f.m2297c();
            C0776k.m2329a(c0771f, C0745a.aj);
        }
    }

    void m2110b() {
        if (this.f1816e != null && this.f1817f != null) {
            if (!this.f1816e.isDirectory()) {
                this.f1816e.delete();
            }
            if (!this.f1817f.isDirectory()) {
                this.f1817f.delete();
            }
            this.f1816e.mkdirs();
            this.f1817f.mkdirs();
        }
    }

    String m2111c() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        return AdColony.activity().getFilesDir().getAbsolutePath();
    }

    String m2112d() {
        if (C0745a.f1979P == null) {
            return StringUtils.EMPTY;
        }
        return AdColony.activity().getExternalFilesDir(null).getAbsolutePath();
    }

    double m2108a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        } catch (RuntimeException e) {
            return 0.0d;
        }
    }
}
