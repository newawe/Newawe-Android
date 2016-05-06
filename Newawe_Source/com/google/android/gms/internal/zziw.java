package com.google.android.gms.internal;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import mf.org.apache.xml.serialize.LineSeparator;

@zzhb
public class zziw {
    private static zzl zzMy;
    public static final zza<Void> zzMz;
    private static final Object zzqy;

    public interface zza<T> {
        T zzgp();

        T zzh(InputStream inputStream);
    }

    /* renamed from: com.google.android.gms.internal.zziw.1 */
    static class C11801 implements zza {
        C11801() {
        }

        public /* synthetic */ Object zzgp() {
            return zzhB();
        }

        public /* synthetic */ Object zzh(InputStream inputStream) {
            return zzi(inputStream);
        }

        public Void zzhB() {
            return null;
        }

        public Void zzi(InputStream inputStream) {
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zziw.2 */
    class C11812 implements com.google.android.gms.internal.zzm.zza {
        final /* synthetic */ zzc zzMA;
        final /* synthetic */ zziw zzMB;
        final /* synthetic */ String zzzP;

        C11812(zziw com_google_android_gms_internal_zziw, String str, zzc com_google_android_gms_internal_zziw_zzc) {
            this.zzMB = com_google_android_gms_internal_zziw;
            this.zzzP = str;
            this.zzMA = com_google_android_gms_internal_zziw_zzc;
        }

        public void zze(zzr com_google_android_gms_internal_zzr) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaK("Failed to load URL: " + this.zzzP + LineSeparator.Web + com_google_android_gms_internal_zzr.toString());
            this.zzMA.zzb(null);
        }
    }

    private static class zzb<T> extends zzk<InputStream> {
        private final zza<T> zzMD;
        private final com.google.android.gms.internal.zzm.zzb<T> zzaG;

        /* renamed from: com.google.android.gms.internal.zziw.zzb.1 */
        class C11821 implements com.google.android.gms.internal.zzm.zza {
            final /* synthetic */ com.google.android.gms.internal.zzm.zzb zzME;
            final /* synthetic */ zza zzMF;

            C11821(com.google.android.gms.internal.zzm.zzb com_google_android_gms_internal_zzm_zzb, zza com_google_android_gms_internal_zziw_zza) {
                this.zzME = com_google_android_gms_internal_zzm_zzb;
                this.zzMF = com_google_android_gms_internal_zziw_zza;
            }

            public void zze(zzr com_google_android_gms_internal_zzr) {
                this.zzME.zzb(this.zzMF.zzgp());
            }
        }

        public zzb(String str, zza<T> com_google_android_gms_internal_zziw_zza_T, com.google.android.gms.internal.zzm.zzb<T> com_google_android_gms_internal_zzm_zzb_T) {
            super(0, str, new C11821(com_google_android_gms_internal_zzm_zzb_T, com_google_android_gms_internal_zziw_zza_T));
            this.zzMD = com_google_android_gms_internal_zziw_zza_T;
            this.zzaG = com_google_android_gms_internal_zzm_zzb_T;
        }

        protected zzm<InputStream> zza(zzi com_google_android_gms_internal_zzi) {
            return zzm.zza(new ByteArrayInputStream(com_google_android_gms_internal_zzi.data), zzx.zzb(com_google_android_gms_internal_zzi));
        }

        protected /* synthetic */ void zza(Object obj) {
            zzj((InputStream) obj);
        }

        protected void zzj(InputStream inputStream) {
            this.zzaG.zzb(this.zzMD.zzh(inputStream));
        }
    }

    /* renamed from: com.google.android.gms.internal.zziw.3 */
    class C13213 extends zzab {
        final /* synthetic */ zziw zzMB;
        final /* synthetic */ Map zzMC;

        C13213(zziw com_google_android_gms_internal_zziw, String str, com.google.android.gms.internal.zzm.zzb com_google_android_gms_internal_zzm_zzb, com.google.android.gms.internal.zzm.zza com_google_android_gms_internal_zzm_zza, Map map) {
            this.zzMB = com_google_android_gms_internal_zziw;
            this.zzMC = map;
            super(str, com_google_android_gms_internal_zzm_zzb, com_google_android_gms_internal_zzm_zza);
        }

        public Map<String, String> getHeaders() throws zza {
            return this.zzMC == null ? super.getHeaders() : this.zzMC;
        }
    }

    private class zzc<T> extends zzjd<T> implements com.google.android.gms.internal.zzm.zzb<T> {
        final /* synthetic */ zziw zzMB;

        private zzc(zziw com_google_android_gms_internal_zziw) {
            this.zzMB = com_google_android_gms_internal_zziw;
        }

        public void zzb(T t) {
            super.zzg(t);
        }
    }

    static {
        zzqy = new Object();
        zzMz = new C11801();
    }

    public zziw(Context context) {
        zzMy = zzS(context);
    }

    private static zzl zzS(Context context) {
        zzl com_google_android_gms_internal_zzl;
        synchronized (zzqy) {
            if (zzMy == null) {
                zzMy = zzac.zza(context.getApplicationContext());
            }
            com_google_android_gms_internal_zzl = zzMy;
        }
        return com_google_android_gms_internal_zzl;
    }

    public <T> zzjg<T> zza(String str, zza<T> com_google_android_gms_internal_zziw_zza_T) {
        Object com_google_android_gms_internal_zziw_zzc = new zzc();
        zzMy.zze(new zzb(str, com_google_android_gms_internal_zziw_zza_T, com_google_android_gms_internal_zziw_zzc));
        return com_google_android_gms_internal_zziw_zzc;
    }

    public zzjg<String> zzb(String str, Map<String, String> map) {
        Object com_google_android_gms_internal_zziw_zzc = new zzc();
        zzMy.zze(new C13213(this, str, com_google_android_gms_internal_zziw_zzc, new C11812(this, str, com_google_android_gms_internal_zziw_zzc), map));
        return com_google_android_gms_internal_zziw_zzc;
    }
}
