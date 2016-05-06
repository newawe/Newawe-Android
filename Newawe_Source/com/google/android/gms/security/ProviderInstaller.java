package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final zzc zzbgP;
    private static Method zzbgQ;
    private static final Object zzqy;

    /* renamed from: com.google.android.gms.security.ProviderInstaller.1 */
    static class C06471 extends AsyncTask<Void, Void, Integer> {
        final /* synthetic */ ProviderInstallListener zzbgR;
        final /* synthetic */ Context zzxh;

        C06471(Context context, ProviderInstallListener providerInstallListener) {
            this.zzxh = context;
            this.zzbgR = providerInstallListener;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return zzc((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            zze((Integer) obj);
        }

        protected Integer zzc(Void... voidArr) {
            try {
                ProviderInstaller.installIfNeeded(this.zzxh);
                return Integer.valueOf(0);
            } catch (GooglePlayServicesRepairableException e) {
                return Integer.valueOf(e.getConnectionStatusCode());
            } catch (GooglePlayServicesNotAvailableException e2) {
                return Integer.valueOf(e2.errorCode);
            }
        }

        protected void zze(Integer num) {
            if (num.intValue() == 0) {
                this.zzbgR.onProviderInstalled();
                return;
            }
            this.zzbgR.onProviderInstallFailed(num.intValue(), ProviderInstaller.zzbgP.zza(this.zzxh, num.intValue(), "pi"));
        }
    }

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    static {
        zzbgP = zzc.zzoK();
        zzqy = new Object();
        zzbgQ = null;
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzx.zzb((Object) context, (Object) "Context must not be null");
        zzbgP.zzak(context);
        Context remoteContext = zze.getRemoteContext(context);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        synchronized (zzqy) {
            try {
                if (zzbgQ == null) {
                    zzaV(remoteContext);
                }
                zzbgQ.invoke(null, new Object[]{remoteContext});
            } catch (Exception e) {
                Log.e("ProviderInstaller", "Failed to install provider: " + e.getMessage());
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener listener) {
        zzx.zzb((Object) context, (Object) "Context must not be null");
        zzx.zzb((Object) listener, (Object) "Listener must not be null");
        zzx.zzcD("Must be called on the UI thread");
        new C06471(context, listener).execute(new Void[0]);
    }

    private static void zzaV(Context context) throws ClassNotFoundException, NoSuchMethodException {
        zzbgQ = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
    }
}
