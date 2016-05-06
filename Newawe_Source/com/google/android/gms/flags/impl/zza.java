package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import com.google.android.gms.internal.zzpl;
import java.util.concurrent.Callable;

public abstract class zza<T> {

    public static class zza extends zza<Boolean> {

        /* renamed from: com.google.android.gms.flags.impl.zza.zza.1 */
        static class C05331 implements Callable<Boolean> {
            final /* synthetic */ SharedPreferences zzaBT;
            final /* synthetic */ String zzaBU;
            final /* synthetic */ Boolean zzaBV;

            C05331(SharedPreferences sharedPreferences, String str, Boolean bool) {
                this.zzaBT = sharedPreferences;
                this.zzaBU = str;
                this.zzaBV = bool;
            }

            public /* synthetic */ Object call() throws Exception {
                return zzvt();
            }

            public Boolean zzvt() {
                return Boolean.valueOf(this.zzaBT.getBoolean(this.zzaBU, this.zzaBV.booleanValue()));
            }
        }

        public static Boolean zza(SharedPreferences sharedPreferences, String str, Boolean bool) {
            return (Boolean) zzpl.zzb(new C05331(sharedPreferences, str, bool));
        }
    }

    public static class zzb extends zza<Integer> {

        /* renamed from: com.google.android.gms.flags.impl.zza.zzb.1 */
        static class C05341 implements Callable<Integer> {
            final /* synthetic */ SharedPreferences zzaBT;
            final /* synthetic */ String zzaBU;
            final /* synthetic */ Integer zzaBW;

            C05341(SharedPreferences sharedPreferences, String str, Integer num) {
                this.zzaBT = sharedPreferences;
                this.zzaBU = str;
                this.zzaBW = num;
            }

            public /* synthetic */ Object call() throws Exception {
                return zzvu();
            }

            public Integer zzvu() {
                return Integer.valueOf(this.zzaBT.getInt(this.zzaBU, this.zzaBW.intValue()));
            }
        }

        public static Integer zza(SharedPreferences sharedPreferences, String str, Integer num) {
            return (Integer) zzpl.zzb(new C05341(sharedPreferences, str, num));
        }
    }

    public static class zzc extends zza<Long> {

        /* renamed from: com.google.android.gms.flags.impl.zza.zzc.1 */
        static class C05351 implements Callable<Long> {
            final /* synthetic */ SharedPreferences zzaBT;
            final /* synthetic */ String zzaBU;
            final /* synthetic */ Long zzaBX;

            C05351(SharedPreferences sharedPreferences, String str, Long l) {
                this.zzaBT = sharedPreferences;
                this.zzaBU = str;
                this.zzaBX = l;
            }

            public /* synthetic */ Object call() throws Exception {
                return zzvv();
            }

            public Long zzvv() {
                return Long.valueOf(this.zzaBT.getLong(this.zzaBU, this.zzaBX.longValue()));
            }
        }

        public static Long zza(SharedPreferences sharedPreferences, String str, Long l) {
            return (Long) zzpl.zzb(new C05351(sharedPreferences, str, l));
        }
    }

    public static class zzd extends zza<String> {

        /* renamed from: com.google.android.gms.flags.impl.zza.zzd.1 */
        static class C05361 implements Callable<String> {
            final /* synthetic */ SharedPreferences zzaBT;
            final /* synthetic */ String zzaBU;
            final /* synthetic */ String zzaBY;

            C05361(SharedPreferences sharedPreferences, String str, String str2) {
                this.zzaBT = sharedPreferences;
                this.zzaBU = str;
                this.zzaBY = str2;
            }

            public /* synthetic */ Object call() throws Exception {
                return zzkp();
            }

            public String zzkp() {
                return this.zzaBT.getString(this.zzaBU, this.zzaBY);
            }
        }

        public static String zza(SharedPreferences sharedPreferences, String str, String str2) {
            return (String) zzpl.zzb(new C05361(sharedPreferences, str, str2));
        }
    }
}
