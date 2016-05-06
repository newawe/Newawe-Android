package com.inmobi.signals.p006a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.google.android.gms.common.ConnectionResult;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p000a.SdkContext;
import com.inmobi.signals.SignalsComponent;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;

@TargetApi(17)
/* renamed from: com.inmobi.signals.a.c */
public class CellularInfoUtil {
    private static final String f1696a;

    static {
        f1696a = CellularInfoUtil.class.getSimpleName();
    }

    public static CellOperatorInfo m1857a() {
        if (!SignalsComponent.m4580a().m4585e().m2074n()) {
            return null;
        }
        int m = SignalsComponent.m4580a().m4585e().m2073m();
        boolean a = CellularInfoUtil.m1859a(m, 2);
        boolean a2 = CellularInfoUtil.m1859a(m, 1);
        CellOperatorInfo cellOperatorInfo = new CellOperatorInfo();
        TelephonyManager telephonyManager = (TelephonyManager) SdkContext.m1258b().getSystemService("phone");
        if (!a) {
            int[] a3 = CellularInfoUtil.m1860a(telephonyManager.getNetworkOperator());
            cellOperatorInfo.m1846a(a3[0]);
            cellOperatorInfo.m1848b(a3[1]);
        }
        if (!a2) {
            int[] a4 = CellularInfoUtil.m1860a(telephonyManager.getSimOperator());
            cellOperatorInfo.m1849c(a4[0]);
            cellOperatorInfo.m1850d(a4[1]);
        }
        return cellOperatorInfo;
    }

    private static boolean m1859a(int i, int i2) {
        return (i & i2) == i2;
    }

    private static int[] m1860a(String str) {
        int[] iArr = new int[]{-1, -1};
        if (!(str == null || str.equals(StringUtils.EMPTY))) {
            try {
                int parseInt = Integer.parseInt(str.substring(0, 3));
                int parseInt2 = Integer.parseInt(str.substring(3));
                iArr[0] = parseInt;
                iArr[1] = parseInt2;
            } catch (Throwable e) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1696a, "Error while collecting cell info.", e);
            } catch (Throwable e2) {
                Logger.m1441a(InternalLogLevel.INTERNAL, f1696a, "Error while collecting cell info.", e2);
            }
        }
        return iArr;
    }

    public static CellTowerInfo m1861b() {
        if (SignalsComponent.m4580a().m4585e().m2076p() && CellularInfoUtil.m1865f()) {
            return CellularInfoUtil.m1866g();
        }
        return null;
    }

    public static Map<String, String> m1862c() {
        CellTowerInfo b = CellularInfoUtil.m1861b();
        Map hashMap = new HashMap();
        if (b != null) {
            hashMap.put("c-sc", b.m1853a().toString());
        }
        return hashMap;
    }

    private static boolean m1865f() {
        boolean z;
        Context b = SdkContext.m1258b();
        String str = "android.permission.ACCESS_COARSE_LOCATION";
        if (b.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            z = false;
        } else {
            z = true;
        }
        String str2 = "android.permission.ACCESS_FINE_LOCATION";
        boolean z2;
        if (b.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    private static CellTowerInfo m1866g() {
        TelephonyManager telephonyManager = (TelephonyManager) SdkContext.m1258b().getSystemService("phone");
        int[] a = CellularInfoUtil.m1860a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                CellInfo cellInfo;
                CellInfo cellInfo2 = null;
                for (int i = 0; i < allCellInfo.size(); i++) {
                    cellInfo2 = (CellInfo) allCellInfo.get(i);
                    if (cellInfo2.isRegistered()) {
                        cellInfo = cellInfo2;
                        break;
                    }
                }
                cellInfo = cellInfo2;
                if (cellInfo != null) {
                    return new CellTowerInfo(cellInfo, valueOf, valueOf2, telephonyManager.getNetworkType());
                }
            }
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation == null || a[0] == -1) {
            return null;
        }
        CellTowerInfo cellTowerInfo = new CellTowerInfo();
        if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            cellTowerInfo.m1856b(ASContentModel.AS_UNBOUNDED);
            cellTowerInfo.m1854a(telephonyManager.getNetworkType());
            cellTowerInfo.m1855a(cellTowerInfo.m1851a(valueOf, cdmaCellLocation.getSystemId(), cdmaCellLocation.getNetworkId(), cdmaCellLocation.getBaseStationId()));
            return cellTowerInfo;
        }
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        cellTowerInfo.m1856b(ASContentModel.AS_UNBOUNDED);
        cellTowerInfo.m1854a(telephonyManager.getNetworkType());
        cellTowerInfo.m1855a(cellTowerInfo.m1852a(valueOf, valueOf2, gsmCellLocation.getLac(), gsmCellLocation.getCid(), gsmCellLocation.getPsc(), ASContentModel.AS_UNBOUNDED));
        return cellTowerInfo;
    }

    public static Map<String, String> m1863d() {
        List e = CellularInfoUtil.m1864e();
        Map hashMap = new HashMap();
        if (!(e == null || e.isEmpty())) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(((CellTowerInfo) e.get(e.size() - 1)).m1853a());
            hashMap.put("v-sc", jSONArray.toString());
        }
        return hashMap;
    }

    public static List<CellTowerInfo> m1864e() {
        if (!CellularInfoUtil.m1867h() || !SignalsComponent.m4580a().m4585e().m2075o()) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) SdkContext.m1258b().getSystemService("phone");
        List<CellTowerInfo> arrayList = new ArrayList();
        int[] a = CellularInfoUtil.m1860a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                for (CellInfo cellInfo : allCellInfo) {
                    if (!cellInfo.isRegistered()) {
                        arrayList.add(new CellTowerInfo(cellInfo, valueOf, valueOf2, telephonyManager.getNetworkType()));
                    }
                }
                return arrayList;
            }
        }
        List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
        if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
            return null;
        }
        Iterator it = neighboringCellInfo.iterator();
        if (!it.hasNext()) {
            return null;
        }
        NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) it.next();
        CellTowerInfo cellTowerInfo = new CellTowerInfo();
        int networkType = neighboringCellInfo2.getNetworkType();
        cellTowerInfo.m1854a(networkType);
        if (neighboringCellInfo2.getRssi() == 99) {
            cellTowerInfo.m1856b(ASContentModel.AS_UNBOUNDED);
        } else if (CellularInfoUtil.m1858a(networkType)) {
            cellTowerInfo.m1856b(neighboringCellInfo2.getRssi() - 116);
        } else {
            cellTowerInfo.m1856b((neighboringCellInfo2.getRssi() * 2) - 113);
        }
        cellTowerInfo.m1855a(cellTowerInfo.m1852a(valueOf, valueOf2, neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid(), -1, ASContentModel.AS_UNBOUNDED));
        arrayList.add(cellTowerInfo);
        return arrayList;
    }

    private static boolean m1858a(int i) {
        switch (i) {
            case ConnectionResult.SERVICE_DISABLED /*3*/:
            case ConnectionResult.INTERNAL_ERROR /*8*/:
            case ConnectionResult.SERVICE_INVALID /*9*/:
            case MetaData.DEFAULT_MAX_ADS /*10*/:
            case ConnectionResult.INTERRUPTED /*15*/:
                return true;
            default:
                return false;
        }
    }

    private static boolean m1867h() {
        String str = "android.permission.ACCESS_COARSE_LOCATION";
        if (SdkContext.m1258b().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return false;
        }
        return true;
    }
}
