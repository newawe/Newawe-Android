package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzw;
import java.util.ArrayList;
import java.util.List;

@zzhb
public class zziv {
    private final String[] zzMn;
    private final double[] zzMo;
    private final double[] zzMp;
    private final int[] zzMq;
    private int zzMr;

    public static class zza {
        public final int count;
        public final String name;
        public final double zzMs;
        public final double zzMt;
        public final double zzMu;

        public zza(String str, double d, double d2, double d3, int i) {
            this.name = str;
            this.zzMt = d;
            this.zzMs = d2;
            this.zzMu = d3;
            this.count = i;
        }

        public boolean equals(Object other) {
            if (!(other instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zziv_zza = (zza) other;
            return zzw.equal(this.name, com_google_android_gms_internal_zziv_zza.name) && this.zzMs == com_google_android_gms_internal_zziv_zza.zzMs && this.zzMt == com_google_android_gms_internal_zziv_zza.zzMt && this.count == com_google_android_gms_internal_zziv_zza.count && Double.compare(this.zzMu, com_google_android_gms_internal_zziv_zza.zzMu) == 0;
        }

        public int hashCode() {
            return zzw.hashCode(this.name, Double.valueOf(this.zzMs), Double.valueOf(this.zzMt), Double.valueOf(this.zzMu), Integer.valueOf(this.count));
        }

        public String toString() {
            return zzw.zzy(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzMt)).zzg("maxBound", Double.valueOf(this.zzMs)).zzg("percent", Double.valueOf(this.zzMu)).zzg("count", Integer.valueOf(this.count)).toString();
        }
    }

    public static class zzb {
        private final List<String> zzMv;
        private final List<Double> zzMw;
        private final List<Double> zzMx;

        public zzb() {
            this.zzMv = new ArrayList();
            this.zzMw = new ArrayList();
            this.zzMx = new ArrayList();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.internal.zziv.zzb zza(java.lang.String r7, double r8, double r10) {
            /*
            r6 = this;
            r0 = 0;
            r1 = r0;
        L_0x0002:
            r0 = r6.zzMv;
            r0 = r0.size();
            if (r1 >= r0) goto L_0x0026;
        L_0x000a:
            r0 = r6.zzMx;
            r0 = r0.get(r1);
            r0 = (java.lang.Double) r0;
            r2 = r0.doubleValue();
            r0 = r6.zzMw;
            r0 = r0.get(r1);
            r0 = (java.lang.Double) r0;
            r4 = r0.doubleValue();
            r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
            if (r0 >= 0) goto L_0x003e;
        L_0x0026:
            r0 = r6.zzMv;
            r0.add(r1, r7);
            r0 = r6.zzMx;
            r2 = java.lang.Double.valueOf(r8);
            r0.add(r1, r2);
            r0 = r6.zzMw;
            r2 = java.lang.Double.valueOf(r10);
            r0.add(r1, r2);
            return r6;
        L_0x003e:
            r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r0 != 0) goto L_0x0046;
        L_0x0042:
            r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
            if (r0 < 0) goto L_0x0026;
        L_0x0046:
            r0 = r1 + 1;
            r1 = r0;
            goto L_0x0002;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zziv.zzb.zza(java.lang.String, double, double):com.google.android.gms.internal.zziv$zzb");
        }

        public zziv zzhA() {
            return new zziv();
        }
    }

    private zziv(zzb com_google_android_gms_internal_zziv_zzb) {
        int size = com_google_android_gms_internal_zziv_zzb.zzMw.size();
        this.zzMn = (String[]) com_google_android_gms_internal_zziv_zzb.zzMv.toArray(new String[size]);
        this.zzMo = zzk(com_google_android_gms_internal_zziv_zzb.zzMw);
        this.zzMp = zzk(com_google_android_gms_internal_zziv_zzb.zzMx);
        this.zzMq = new int[size];
        this.zzMr = 0;
    }

    private double[] zzk(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public List<zza> getBuckets() {
        List<zza> arrayList = new ArrayList(this.zzMn.length);
        for (int i = 0; i < this.zzMn.length; i++) {
            arrayList.add(new zza(this.zzMn[i], this.zzMp[i], this.zzMo[i], ((double) this.zzMq[i]) / ((double) this.zzMr), this.zzMq[i]));
        }
        return arrayList;
    }

    public void zza(double d) {
        this.zzMr++;
        int i = 0;
        while (i < this.zzMp.length) {
            if (this.zzMp[i] <= d && d < this.zzMo[i]) {
                int[] iArr = this.zzMq;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzMp[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
