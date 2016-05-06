package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzr;
import java.util.LinkedList;
import java.util.List;

@zzhb
class zzdw {
    private final List<zza> zzpH;

    /* renamed from: com.google.android.gms.internal.zzdw.7 */
    class C05657 implements Runnable {
        final /* synthetic */ zzdw zzAc;
        final /* synthetic */ zza zzAo;
        final /* synthetic */ zzdx zzAp;

        C05657(zzdw com_google_android_gms_internal_zzdw, zza com_google_android_gms_internal_zzdw_zza, zzdx com_google_android_gms_internal_zzdx) {
            this.zzAc = com_google_android_gms_internal_zzdw;
            this.zzAo = com_google_android_gms_internal_zzdw_zza;
            this.zzAp = com_google_android_gms_internal_zzdx;
        }

        public void run() {
            try {
                this.zzAo.zzb(this.zzAp);
            } catch (Throwable e) {
                zzb.zzd("Could not propagate interstitial ad event.", e);
            }
        }
    }

    interface zza {
        void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException;
    }

    /* renamed from: com.google.android.gms.internal.zzdw.1 */
    class C13141 extends com.google.android.gms.ads.internal.client.zzq.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.1.1 */
        class C11411 implements zza {
            final /* synthetic */ C13141 zzAd;

            C11411(C13141 c13141) {
                this.zzAd = c13141;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdClosed();
                }
                zzr.zzbN().zzee();
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.1.2 */
        class C11422 implements zza {
            final /* synthetic */ C13141 zzAd;
            final /* synthetic */ int zzAe;

            C11422(C13141 c13141, int i) {
                this.zzAd = c13141;
                this.zzAe = i;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdFailedToLoad(this.zzAe);
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.1.3 */
        class C11433 implements zza {
            final /* synthetic */ C13141 zzAd;

            C11433(C13141 c13141) {
                this.zzAd = c13141;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdLeftApplication();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.1.4 */
        class C11444 implements zza {
            final /* synthetic */ C13141 zzAd;

            C11444(C13141 c13141) {
                this.zzAd = c13141;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdLoaded();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.1.5 */
        class C11455 implements zza {
            final /* synthetic */ C13141 zzAd;

            C11455(C13141 c13141) {
                this.zzAd = c13141;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdOpened();
                }
            }
        }

        C13141(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAdClosed() throws RemoteException {
            this.zzAc.zzpH.add(new C11411(this));
        }

        public void onAdFailedToLoad(int errorCode) throws RemoteException {
            this.zzAc.zzpH.add(new C11422(this, errorCode));
            zzin.m4114v("Pooled interstitial failed to load.");
        }

        public void onAdLeftApplication() throws RemoteException {
            this.zzAc.zzpH.add(new C11433(this));
        }

        public void onAdLoaded() throws RemoteException {
            this.zzAc.zzpH.add(new C11444(this));
            zzin.m4114v("Pooled interstitial loaded.");
        }

        public void onAdOpened() throws RemoteException {
            this.zzAc.zzpH.add(new C11455(this));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdw.2 */
    class C13152 extends com.google.android.gms.ads.internal.client.zzw.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.2.1 */
        class C11461 implements zza {
            final /* synthetic */ String val$name;
            final /* synthetic */ String zzAf;
            final /* synthetic */ C13152 zzAg;

            C11461(C13152 c13152, String str, String str2) {
                this.zzAg = c13152;
                this.val$name = str;
                this.zzAf = str2;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAq != null) {
                    com_google_android_gms_internal_zzdx.zzAq.onAppEvent(this.val$name, this.zzAf);
                }
            }
        }

        C13152(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAppEvent(String name, String info) throws RemoteException {
            this.zzAc.zzpH.add(new C11461(this, name, info));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdw.3 */
    class C13163 extends com.google.android.gms.internal.zzgd.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.3.1 */
        class C11471 implements zza {
            final /* synthetic */ zzgc zzAh;
            final /* synthetic */ C13163 zzAi;

            C11471(C13163 c13163, zzgc com_google_android_gms_internal_zzgc) {
                this.zzAi = c13163;
                this.zzAh = com_google_android_gms_internal_zzgc;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAr != null) {
                    com_google_android_gms_internal_zzdx.zzAr.zza(this.zzAh);
                }
            }
        }

        C13163(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void zza(zzgc com_google_android_gms_internal_zzgc) throws RemoteException {
            this.zzAc.zzpH.add(new C11471(this, com_google_android_gms_internal_zzgc));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdw.4 */
    class C13174 extends com.google.android.gms.internal.zzcf.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.4.1 */
        class C11481 implements zza {
            final /* synthetic */ zzce zzAj;
            final /* synthetic */ C13174 zzAk;

            C11481(C13174 c13174, zzce com_google_android_gms_internal_zzce) {
                this.zzAk = c13174;
                this.zzAj = com_google_android_gms_internal_zzce;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAs != null) {
                    com_google_android_gms_internal_zzdx.zzAs.zza(this.zzAj);
                }
            }
        }

        C13174(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void zza(zzce com_google_android_gms_internal_zzce) throws RemoteException {
            this.zzAc.zzpH.add(new C11481(this, com_google_android_gms_internal_zzce));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdw.5 */
    class C13185 extends com.google.android.gms.ads.internal.client.zzp.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.5.1 */
        class C11491 implements zza {
            final /* synthetic */ C13185 zzAl;

            C11491(C13185 c13185) {
                this.zzAl = c13185;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAt != null) {
                    com_google_android_gms_internal_zzdx.zzAt.onAdClicked();
                }
            }
        }

        C13185(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAdClicked() throws RemoteException {
            this.zzAc.zzpH.add(new C11491(this));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdw.6 */
    class C13196 extends com.google.android.gms.ads.internal.reward.client.zzd.zza {
        final /* synthetic */ zzdw zzAc;

        /* renamed from: com.google.android.gms.internal.zzdw.6.1 */
        class C11501 implements zza {
            final /* synthetic */ C13196 zzAm;

            C11501(C13196 c13196) {
                this.zzAm = c13196;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdLoaded();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.2 */
        class C11512 implements zza {
            final /* synthetic */ C13196 zzAm;

            C11512(C13196 c13196) {
                this.zzAm = c13196;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdOpened();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.3 */
        class C11523 implements zza {
            final /* synthetic */ C13196 zzAm;

            C11523(C13196 c13196) {
                this.zzAm = c13196;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoStarted();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.4 */
        class C11534 implements zza {
            final /* synthetic */ C13196 zzAm;

            C11534(C13196 c13196) {
                this.zzAm = c13196;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdClosed();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.5 */
        class C11545 implements zza {
            final /* synthetic */ C13196 zzAm;
            final /* synthetic */ com.google.android.gms.ads.internal.reward.client.zza zzAn;

            C11545(C13196 c13196, com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) {
                this.zzAm = c13196;
                this.zzAn = com_google_android_gms_ads_internal_reward_client_zza;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.zza(this.zzAn);
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.6 */
        class C11556 implements zza {
            final /* synthetic */ C13196 zzAm;

            C11556(C13196 c13196) {
                this.zzAm = c13196;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdLeftApplication();
                }
            }
        }

        /* renamed from: com.google.android.gms.internal.zzdw.6.7 */
        class C11567 implements zza {
            final /* synthetic */ int zzAe;
            final /* synthetic */ C13196 zzAm;

            C11567(C13196 c13196, int i) {
                this.zzAm = c13196;
                this.zzAe = i;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdFailedToLoad(this.zzAe);
                }
            }
        }

        C13196(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onRewardedVideoAdClosed() throws RemoteException {
            this.zzAc.zzpH.add(new C11534(this));
        }

        public void onRewardedVideoAdFailedToLoad(int errorCode) throws RemoteException {
            this.zzAc.zzpH.add(new C11567(this, errorCode));
        }

        public void onRewardedVideoAdLeftApplication() throws RemoteException {
            this.zzAc.zzpH.add(new C11556(this));
        }

        public void onRewardedVideoAdLoaded() throws RemoteException {
            this.zzAc.zzpH.add(new C11501(this));
        }

        public void onRewardedVideoAdOpened() throws RemoteException {
            this.zzAc.zzpH.add(new C11512(this));
        }

        public void onRewardedVideoStarted() throws RemoteException {
            this.zzAc.zzpH.add(new C11523(this));
        }

        public void zza(com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) throws RemoteException {
            this.zzAc.zzpH.add(new C11545(this, com_google_android_gms_ads_internal_reward_client_zza));
        }
    }

    zzdw() {
        this.zzpH = new LinkedList();
    }

    void zza(zzdx com_google_android_gms_internal_zzdx) {
        Handler handler = zzir.zzMc;
        for (zza c05657 : this.zzpH) {
            handler.post(new C05657(this, c05657, com_google_android_gms_internal_zzdx));
        }
    }

    void zzc(zzk com_google_android_gms_ads_internal_zzk) {
        com_google_android_gms_ads_internal_zzk.zza(new C13141(this));
        com_google_android_gms_ads_internal_zzk.zza(new C13152(this));
        com_google_android_gms_ads_internal_zzk.zza(new C13163(this));
        com_google_android_gms_ads_internal_zzk.zza(new C13174(this));
        com_google_android_gms_ads_internal_zzk.zza(new C13185(this));
        com_google_android_gms_ads_internal_zzk.zza(new C13196(this));
    }
}
