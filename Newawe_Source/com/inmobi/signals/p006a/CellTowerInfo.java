package com.inmobi.signals.p006a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/* renamed from: com.inmobi.signals.a.b */
public class CellTowerInfo {
    private static final String f1692a;
    private String f1693b;
    private int f1694c;
    private int f1695d;

    static {
        f1692a = CellTowerInfo.class.getSimpleName();
    }

    @TargetApi(18)
    public CellTowerInfo(CellInfo cellInfo, String str, String str2, int i) {
        if (cellInfo instanceof CellInfoGsm) {
            this.f1695d = i;
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            this.f1694c = cellInfoGsm.getCellSignalStrength().getDbm();
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            this.f1693b = m1852a(str, str2, cellIdentity.getLac(), cellIdentity.getCid(), -1, ASContentModel.AS_UNBOUNDED);
        } else if (cellInfo instanceof CellInfoCdma) {
            this.f1695d = i;
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            this.f1694c = cellInfoCdma.getCellSignalStrength().getDbm();
            CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
            this.f1693b = m1851a(str, cellIdentity2.getSystemId(), cellIdentity2.getNetworkId(), cellIdentity2.getBasestationId());
        } else if (VERSION.SDK_INT >= 18) {
            if (cellInfo instanceof CellInfoWcdma) {
                this.f1695d = i;
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                this.f1694c = cellInfoWcdma.getCellSignalStrength().getDbm();
                CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                this.f1693b = m1852a(str, str2, cellIdentity3.getLac(), cellIdentity3.getCid(), cellIdentity3.getPsc(), ASContentModel.AS_UNBOUNDED);
            }
        } else if (cellInfo instanceof CellInfoLte) {
            this.f1695d = i;
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            this.f1694c = cellInfoLte.getCellSignalStrength().getDbm();
            CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
            this.f1693b = m1852a(str, str2, cellIdentity4.getTac(), cellIdentity4.getCi(), -1, cellIdentity4.getPci());
        }
    }

    public String m1851a(String str, int i, int i2, int i3) {
        return str + "#" + i + "#" + i2 + "#" + i3;
    }

    public String m1852a(String str, String str2, int i, int i2, int i3, int i4) {
        return str + "#" + str2 + "#" + i + "#" + i2 + "#" + (i3 == -1 ? StringUtils.EMPTY : Integer.valueOf(i3)) + "#" + (i4 == ASContentModel.AS_UNBOUNDED ? StringUtils.EMPTY : Integer.valueOf(i4));
    }

    public void m1854a(int i) {
        this.f1695d = i;
    }

    public void m1855a(String str) {
        this.f1693b = str;
    }

    public void m1856b(int i) {
        this.f1694c = i;
    }

    public JSONObject m1853a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.f1693b);
            if (this.f1694c != ASContentModel.AS_UNBOUNDED) {
                jSONObject.put("ss", this.f1694c);
            }
            jSONObject.put("nt", this.f1695d);
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1692a, "Error while converting CellTowerInfo to string.", e);
        }
        return jSONObject;
    }
}
